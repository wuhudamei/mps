<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价奖励设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var index = 0;
		$(document).ready(function() {
			//$("#name").focus();
			/*$("#inputForm").validate({
				submitHandler: function(form){
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
			});*/

			// 初始化显示任务包
			var storeId = $("#storeId").val();
			if(storeId != undefined && storeId != null && storeId != ""){
				showTaskpackage(storeId);
			}else{
				$("#taskPackage").html("");
			}
			
			
			var projectMode = "${bizEvalRewardCfg.id}"
				var readOnly = "${bizEvalRewardCfg.projectMode}"
					//如果是产业或者是传统的人,  就不允许修改
					if ("1" == readOnly) {
						$(":radio[name='projectMode'][value='" + 2 + "']").attr(
								"disabled", true);
						$(":radio[name='projectMode'][value='" + 4 + "']").attr(
								"disabled", true);
					}
					if ("2" == readOnly) {
						$(":radio[name='projectMode'][value='" + 1 + "']").attr(
								"disabled", true);
						$(":radio[name='projectMode'][value='" + 4 + "']").attr(
								"disabled", true);
					}
					if ("4" == readOnly) {
						$(":radio[name='projectMode'][value='" + 2 + "']").attr(
								"disabled", true);
						$(":radio[name='projectMode'][value='" + 1 + "']").attr(
								"disabled", true);
					}
					//新增
					if ("" == projectMode) {
						//如果不是产业或者是传统的人 ,默认传统,并且可以修改
						if ("1" != readOnly && "2" != readOnly && "4" != readOnly) {
							$(":radio[name='projectMode'][value='" + 2 + "']")
									.prop("checked", "checked");
						} else {
							//如果是产业或者是传统的人   工程模式也一样,并且不能修改
							$(":radio[name='projectMode'][value='" + readOnly
											+ "']").prop("checked", "checked");
							if ("1" == readOnly) {
								$(":radio[name='projectMode'][value='" + 2 + "']")
										.attr("disabled", true);
								$(":radio[name='projectMode'][value='" + 4 + "']")
										.attr("disabled", true);
							}
							if ("2" == readOnly) {
								$(":radio[name='projectMode'][value='" + 1 + "']")
										.attr("disabled", true);
								$(":radio[name='projectMode'][value='" + 4 + "']")
										.attr("disabled", true);
							}
							if ("4" == readOnly) {
								$(":radio[name='projectMode'][value='" + 1 + "']")
										.attr("disabled", true);
								$(":radio[name='projectMode'][value='" + 2 + "']")
										.attr("disabled", true);
							}
						}
					}
					$(":radio[name='isEnabled'][value='" + 1 + "']").prop(
							"checked", "checked");
		});

		function showTaskpackage(storeId,projectMode){
			
			$.post("${ctx}/taskpackage/bizTaskPackageTemplat/taskListByStoreIdAndProjectMode",{storeId:storeId,projectMode:projectMode},function(result){
				var content = "";
				for(var i=0;i<result.length;i++){
					content = content + "<input type='checkbox' name='taskpackTempId' value='"+result[i].value+"'/>"+result[i].label+"&nbsp;&nbsp;";
				}
				$("#taskPackage").html(content);
			});
		}

		function storeIdChange(){
			var storeId = $("#storeId").val();
			var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
			if(storeId != undefined && storeId != null && storeId != "" && projectMode != null && projectMode != "" && projectMode != undefined){
				showTaskpackage(storeId,projectMode);
			}else{
				$("#taskPackage").html("");
			}
		}

		function addStarButton(){
			var content = "<tr id='tr"+index+"'><td style='text-align: center'><select name='list["+index+"].starLevel'>";
			<c:forEach items="${fns:getDictListByType('eval_reward_star')}" var="star" varStatus="status">
				content = content + "<option value='${star.value}'>${star.label}</option>";
			</c:forEach>
			content = content + "</select></td><td style='text-align: center'>" +
			"<input type='text' name='list["+index+"].minScore' style='width: 100px' oninput='checkNumber(this)' maxlength='4'/>含-" +
			"<input type='text' name='list["+index+"].maxScore' style='width: 100px' oninput='checkNumber(this)' maxlength='4'/>含</td>" +
			"<td style='text-align: center'><input type='text' name='list["+index+"].rewardAmount' style='width: 150px' oninput='checkNumber(this)' maxlength='6'/>元</td>" +
			"<td><input type='button' class='btn btn-primary' value='删 除' onclick='removeStar("+index+")'/></td></tr>";
			$("#tbodyContent").append(content);
			index ++;
		}

		function removeStar(index){
			$("#tr"+index).remove();
		}

		function checkNumber(obj){
			//先把非数字的都替换掉，除了数字和.
			obj.value = obj.value.replace(/[^\d.]/g,"");
			//必须保证第一个为数字而不是.
			obj.value = obj.value.replace(/^\./g,"");
			//保证.只出现一次，而不能出现两次以上
			obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
			//只能输入两个小数
			obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
		}

		function saveButton(){

			// 奖励方案名称必填
			var rewardName = $("#rewardName").val();
			if(rewardName == undefined || rewardName == null || rewardName == ""){
				alertx("奖励方案名称必填!");
				return;
			}

			// 判断任务包必选
			var taskpackTempId = "";
			$("input[type='checkbox']:checked").each(function(){
				if(taskpackTempId == ""){
					taskpackTempId = $(this).val();
				}else{
					taskpackTempId = taskpackTempId + "," + $(this).val();
				}
			});

			if(taskpackTempId == undefined || taskpackTempId == null || taskpackTempId == ""){
				alertx("任务包类型必选");
				return;
			}

			// 判断任务包重复
			var flag = "";
			$.ajax({
				url: "${ctx}/bizevalrewardcfg/bizEvalRewardCfg/checkTaskPackage",    //请求的url地址
				dataType: "json",   //返回格式为json
				type: "POST",   //请求方式
				async: false, //请求是否异步，默认为异步，这也是ajax重要特性
				data: {
					"storeId":$("#storeId").val(),
					"projectMode": $("input[type='radio']:checked").val(),
					"rewardTargetType": $("#rewardTargetType").val(),
					"rewardStartDatetime": $("#rewardStartDatetime").val(),
					"rewardEndDatetime": $("#rewardEndDatetime").val(),
					"taskpackTempId":taskpackTempId
				},
				success: function(req) {
					if(req == '1'){
						flag = "1";
					}
				}
			});

			if(flag == "1"){
				alertx("此任务包奖励方案已存在，请勿重复添加!");
				return;
			}

			// 判断星级至少一条
			var length = $("#tbodyContent").find("tr").length;
			if(length == 0){
				alertx("星级设置至少包含一条以上的数据!");
				return;
			}

			// 判断星级是否重复
			var evalRewardStarMap = new Object();
			var repeat = "0";
			$("#tbodyContent select").each(function(){
				var value = $(this).val();
				if(evalRewardStarMap[value]){
					repeat = "1";
					return;
				}else{
					evalRewardStarMap[value] = "0";
				}
			});

			if(repeat == "1"){
				alertx("星级不可重复!");
				return;
			}

			// 判断分值与奖励金额不为空
			var textNullCount = "";
			$("#tbodyContent td").find("input[type='text']").each(function(){
				if($(this).val() == undefined || $(this).val() == null || $(this).val() == ""){
					textNullCount = "1";
					return;
				}
			});
			if(textNullCount == "1"){
				alertx("分值或奖励金额必填");
				return;
			}

			loading('正在提交，请稍等...');
			$("#inputForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizevalrewardcfg/bizEvalRewardCfg/loadList">评价奖励设置列表</a></li>
		<li class="active"><a href="${ctx}/bizevalrewardcfg/bizEvalRewardCfg/form?id=${bizEvalRewardCfg.id}">评价奖励设置<shiro:hasPermission name="bizevalrewardcfg:bizEvalRewardCfg:edit">${not empty bizEvalRewardCfg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizevalrewardcfg:bizEvalRewardCfg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizEvalRewardCfg" action="${ctx}/bizevalrewardcfg/bizEvalRewardCfg/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-xlarge required" disabled="true" id="storeId" onchange="storeIdChange()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-xlarge required" id="storeId" onchange="storeIdChange()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" onclick="storeIdChange()"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">奖励对象：</label>
			<div class="controls">
				<form:select path="rewardTargetType" class="input-xlarge " required="required" id="rewardTargetType">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictListByType('eval_target_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方案名称：</label>
			<div class="controls">
				<form:input path="rewardName" id="rewardName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动时间：</label>
			<div class="controls">
					<input name="rewardStartDatetime" id="rewardStartDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
						   value="<fmt:formatDate value="${bizEvalRewardCfg.rewardStartDatetime}" pattern="yyyy-MM-dd"/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'rewardEndDatetime\')}',isShowClear:false});"/> -
					<input name="rewardEndDatetime" id="rewardEndDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
						   value="<fmt:formatDate value="${bizEvalRewardCfg.rewardEndDatetime}" pattern="yyyy-MM-dd"/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'rewardStartDatetime\')}',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务包类型：</label>
			<div class="controls" id="taskPackage">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label" style="font-size: 30px;font-weight: bold">星级设置</label>
			<div class="controls">
				<input class="btn btn-primary" type="button" value="新 增" onclick="addStarButton()"/>
			</div>
		</div>
		<div class="control-group">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th style="text-align: center" width="30%">星级</th>
						<th style="text-align: center" width="30%">分值</th>
						<th style="text-align: center" width="30%">奖励金额</th>
						<th width="10%"></th>
					</tr>
				</thead>
				<tbody id="tbodyContent">

				</tbody>
			</table>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizevalrewardcfg:bizEvalRewardCfg:edit"><input class="btn btn-primary" type="button" value="保 存" onclick="saveButton()"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>