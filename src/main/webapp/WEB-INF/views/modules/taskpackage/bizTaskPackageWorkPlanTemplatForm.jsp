<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>任务包派工计划模板管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					var packageOrder =$("#bizTaskPackageWorkPlanTemplatRelList0_packageOrder").val();
					if(packageOrder==undefined){
						alert("请添加任务包！");
						return;
					}
					
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			var searchStoreId = $("#storeId").val();
			var projectMode = $("#projectMode").val();
			if(searchStoreId == undefined || searchStoreId == null || searchStoreId == ""){
				alertx("请先选择门店。");
				return;
			}
			if(projectMode == null || projectMode == "" || projectMode == undefined){
				alertx("请先选择工程模式。");
				return;
			}

			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});

			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});

			$.post("${ctx}/taskpackage/bizTaskPackageTemplat/taskListByStoreIdAndProjectMode",{storeId:searchStoreId,projectMode:projectMode},function(data){
				var options = "";
				if(data.length > 0){
					options = options + "<option></option>";
				}
				for(var i=0;i<data.length;i++){
					options = options + "<option value="+data[i].value+">"+data[i].label+"</option>";
				}
				$("#bizTaskPackageWorkPlanTemplatRelList"+idx+"_taskPackageId").html(options);
				$("#bizTaskPackageWorkPlanTemplatRelList"+idx+"_taskPackageId").val($("#bizTaskPackageWorkPlanTemplatRelList"+idx+"_taskPackageId").attr("data-value"));
			});
		}

		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}

		function checkChange(obj){
			var storeId = $(obj).val();
			if(storeId == undefined || storeId == null || storeId == ""){
				$("#bizTaskPackageWorkPlanTemplatRelList").find("select").html("");
			}else{
				$.post("${ctx}/taskpackage/bizTaskPackageTemplat/taskListByStoreId",{storeId:storeId},function(data){
					var options = "";
					if(data.length > 0){
						options = options + "<option></option>";
					}
					for(var i=0;i<data.length;i++){
						options = options + "<option value="+data[i].value+">"+data[i].label+"</option>";
					}
					if(options != ""){
						$("#bizTaskPackageWorkPlanTemplatRelList").find("select").each(function(){
							$(this).html(options);
						});
					}
				});
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/taskpackage/bizTaskPackageWorkPlanTemplat/">任务包派工计划模板列表</a></li>
		<li class="active"><a
			href="${ctx}/taskpackage/bizTaskPackageWorkPlanTemplat/form?id=${bizTaskPackageWorkPlanTemplat.id}">任务包派工计划模板<shiro:hasPermission
					name="taskpackage:bizTaskPackageWorkPlanTemplat:edit">${not empty bizTaskPackageWorkPlanTemplat.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission
					name="taskpackage:bizTaskPackageWorkPlanTemplat:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" onsubmit="onFormSubmit(this)"
		modelAttribute="bizTaskPackageWorkPlanTemplat"
		action="${ctx}/taskpackage/bizTaskPackageWorkPlanTemplat/save"
		method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">门店：</label>
						<div class="controls">
							<c:if test="${empty storeDropEnable}">
								<form:select path="storeId" class="input-xlarge required"
									disabled="true" id="storeId">
									<form:option value="" label="" />
									<form:options items="${fns:getStoreList()}" itemLabel="label"
										itemValue="value" htmlEscape="false" />
								</form:select>
							</c:if>
							<c:if test="${!empty storeDropEnable}">
								<form:select path="storeId" class="input-xlarge required"
									id="storeId" onchange="checkChange(this)">
									<form:option value="" label="" />
									<form:options items="${fns:getStoreList()}" itemLabel="label"
										itemValue="value" htmlEscape="false" />
								</form:select>
							</c:if>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">工程模式：</label>
						<div class="controls">
							<c:if test="${empty gongcheng}">
								<form:select path="projectMode" class="input-medium"
									disabled="true" id="projectMode" onchange="storeIdSelectChange()">
									<form:option value="" label="" />
									<form:options
										items="${fns:getDictList('package_project_mode')}"
										itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select>
							</c:if>
							<c:if test="${!empty gongcheng}">
								<form:select path="projectMode" class="input-medium needClear"
									id="projectMode" onchange="storeIdSelectChange()">
									<form:option value="" label="" />
									<form:options
										items="${fns:getDictList('package_project_mode')}"
										itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select>
							</c:if>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">新旧房：</label>
						<div class="controls">
							<form:select path="isNewHouse" class="input-xlarge "
								required="required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('biz_is_new_house')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>

				<td>
					<div class="control-group">
						<label class="control-label">模板状态：</label>
						<div class="controls">
							<form:select path="status" class="input-xlarge "
								required="required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('biz_enable_status')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">工期天数：</label>
						<div class="controls">
							<form:input path="workSchedule" htmlEscape="false" maxlength="11"
								required="required" class="input-xlarge digits" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">模板名称：</label>
						<div class="controls">
							<form:input path="templatName" htmlEscape="false" maxlength="100"
								required="required" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div class="control-group">
			<label class="control-label">任务包：</label>
			<div class="controls">
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th class="hide"></th>
							<th>顺序</th>
							<th>任务包名称</th>
							<th>开工第几天开始</th>
							<th>开工第几天结束</th>
							<shiro:hasPermission
								name="taskpackage:bizTaskPackageWorkPlanTemplat:edit">
								<th width="10">&nbsp;</th>
							</shiro:hasPermission>
						</tr>
					</thead>
					<tbody id="bizTaskPackageWorkPlanTemplatRelList">
					</tbody>
					<shiro:hasPermission
						name="taskpackage:bizTaskPackageWorkPlanTemplat:edit">
						<tfoot>
							<tr>
								<td colspan="6"><a href="javascript:"
									onclick="addRow('#bizTaskPackageWorkPlanTemplatRelList', bizTaskPackageWorkPlanTemplatRelRowIdx, bizTaskPackageWorkPlanTemplatRelTpl);bizTaskPackageWorkPlanTemplatRelRowIdx = bizTaskPackageWorkPlanTemplatRelRowIdx + 1;"
									class="btn">新增</a></td>
							</tr>
						</tfoot>
					</shiro:hasPermission>
				</table>
				<script type="text/template"
					id="bizTaskPackageWorkPlanTemplatRelTpl">//<!--
						<tr id="bizTaskPackageWorkPlanTemplatRelList{{idx}}">
							<td class="hide">
								<input id="bizTaskPackageWorkPlanTemplatRelList{{idx}}_id" name="bizTaskPackageWorkPlanTemplatRelList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="bizTaskPackageWorkPlanTemplatRelList{{idx}}_delFlag" name="bizTaskPackageWorkPlanTemplatRelList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="bizTaskPackageWorkPlanTemplatRelList{{idx}}_packageOrder" name="bizTaskPackageWorkPlanTemplatRelList[{{idx}}].packageOrder" type="text" value="{{row.packageOrder}}" maxlength="11"  required="required"  class="input-small number"/>
							</td>
							<td>
								<select id="bizTaskPackageWorkPlanTemplatRelList{{idx}}_taskPackageId" name="bizTaskPackageWorkPlanTemplatRelList[{{idx}}].taskPackageId" data-value="{{row.taskPackageId}}"  required="required"  class="input-small ">
									<option value=""></option>
									<%--<c:forEach items="${fns:getTaskListByNowStoreId()}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>--%>
								</select>
							</td>
							<td>
								<input id="bizTaskPackageWorkPlanTemplatRelList{{idx}}_beginDay" name="bizTaskPackageWorkPlanTemplatRelList[{{idx}}].beginDay" type="text" value="{{row.beginDay}}" maxlength="11"  required="required"  class="input-small  number"/>
							</td>
							<td>
								<input id="bizTaskPackageWorkPlanTemplatRelList{{idx}}_endDay" name="bizTaskPackageWorkPlanTemplatRelList[{{idx}}].endDay" type="text" value="{{row.endDay}}" maxlength="11"   required="required"  class="input-small  number"/>
							</td>
							<shiro:hasPermission name="taskpackage:bizTaskPackageWorkPlanTemplat:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#bizTaskPackageWorkPlanTemplatRelList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
				<script type="text/javascript">
						var bizTaskPackageWorkPlanTemplatRelRowIdx = 0, bizTaskPackageWorkPlanTemplatRelTpl = $("#bizTaskPackageWorkPlanTemplatRelTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(bizTaskPackageWorkPlanTemplat.bizTaskPackageWorkPlanTemplatRelList)};
							for (var i=0; i<data.length; i++){
								addRow('#bizTaskPackageWorkPlanTemplatRelList', bizTaskPackageWorkPlanTemplatRelRowIdx, bizTaskPackageWorkPlanTemplatRelTpl, data[i]);
								bizTaskPackageWorkPlanTemplatRelRowIdx = bizTaskPackageWorkPlanTemplatRelRowIdx + 1;
							}
						});
					</script>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission
				name="taskpackage:bizTaskPackageWorkPlanTemplat:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>