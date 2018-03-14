<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务包模板管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					if(checkData()){
						loading('正在提交，请稍等...');
						form.submit();
					}
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
			
			var projectMode = "${bizTaskPackageTemplat.id}"
				var readOnly = "${bizTaskPackageTemplat.projectMode}"
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
						$(":radio[name='projectMode'][value='" + 1 + "']")
								.prop("checked", "checked");
					} else {
						//如果是产业或者是传统的人   工程模式也一样,并且不能修改
						$(
								":radio[name='projectMode'][value='" + readOnly
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
		
		//数据检查
		function checkData(){
			//检查首尾款
			var advancePaymentRates = $("#advancePaymentRates").val();
			var restPaymentRates = $("#restPaymentRates").val();
			var total = advancePaymentRates/1+restPaymentRates/1;
			if(total!=100){
				alertx("首付款+尾付款必须等于100！");
				return false;
			}
			//检查是否有工序
			var procedureItemsValues = $("#procedureItems").val();
			var temp = procedureItemsValues.replace(/,/g,"");
			if(temp==""){
				alertx("工序不能为空！");
				return false;
			}
			
			
			//门店+工程模式+工种的唯一性
			var storeId = $("#storeId").val();
			var projectMode = $("#projectMode").val();
			var empWorkType = $("#empWorkType").val();
			var empWorkType = $("#empWorkType").val();
			var result;
			//如果工种不为空时
			if(empWorkType != ""){
				$.ajax({
					url: "${ctx}/taskpackage/bizTaskPackageTemplat/check_emp_work_type_ajax",
		            type: "post",
		            async: false,
		            data:{
		            		id:'${bizTaskPackageTemplat.id}',
		            		storeId:storeId,
		            		projectMode:projectMode,
		            		empWorkType:empWorkType
		            	},
		            success: function(data) {
		            	
		            	if(null!=data && data=="0"){
		            		result = "0";
		            	}else{
		            		var empWorkType = $("#s2id_empWorkType .select2-chosen").html();
		            		alertx("“"+empWorkType+"”已经配置了任务包模板，不允许重复配置。");
		            		result = "1";
		            	}
		            	
		            }
		    	});
			}
			
			if(result == "1"){
				return false;
			}else{
				return true;
			}
			
			
		}
		
		//添加工序
		function addProcedureRow(){
			var no= $("#procedureSelect option:selected").val();
			if(no==null || no==""){
				alertx("请先选择要添加的工序!");
				return;
			}
			
			var procedureItemsValues = $("#procedureItems").val();
			
			if(procedureItemsValues.indexOf(no) >= 0){
				alertx("该工序已在模板中！");
				return;
			}
			
			procedureItemsValues += ","+no
			
			console.info(procedureItemsValues);
			
			$("#procedureItems").val(procedureItemsValues);
			
			var text= $("#procedureSelect option:selected").html();
			var html = '';
			html+= '<tr id="row'+no+'">';
			html+=		'<th>'+no+'</th>';
			html+=		'<th>'+text+'</th>';
 			html+=		'<th width="10"><a href="javascript:delProcedureRow(\''+no+'\');">删除</a></th>';
			html+=	'</tr>';
			$("#gongxuList").append(html);
		}
		
		//删除工序
		function delProcedureRow(no){
			$("#row"+no).remove();
			var procedureItemsValues = $("#procedureItems").val();
			procedureItemsValues = procedureItemsValues.replace(no,''); 
			$("#procedureItems").val(procedureItemsValues);
		} 
		function qualityGuaranteeChange(obj){
			if($(obj).val()==0){
				$("#qualityGuaranteeRate").val(0);
				$("#s2id_qualityGuaranteeRate .select2-chosen").html(0);
				$("#qualityGuaranteeRate").attr("disabled","true");
			}else{
				$("#qualityGuaranteeRate").removeAttr("disabled");
			}
		}
		
		//只能输入0-100整数
		function fix_amountthis(obj){
	    	
	        //先把非数字的都替换掉，除了数字和. 
		    obj.value = obj.value.replace(/[^\d]/g,""); 
	        //如果字符串长度大于1，替换掉字符串前的0
	    	if(obj.value.length>1){
		    	obj.value = obj.value.replace(/^[0]/g,""); 
	    	}
	    	//如果字符串长度大于1，替换掉字符串前的0
	    	if(obj.value.length>1){
		    	obj.value = obj.value.replace(/^[0]/g,""); 
	    	}
		    //只能输入0-100整数
		    var reg = /^(?:0|[1-9][0-9]?|100)$/;
		    if(!reg.test(obj.value)){
	    		obj.value = obj.value.substring(0,2);
		    }
	     }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/taskpackage/bizTaskPackageTemplat/">任务包模板列表</a></li>
		<li class="active"><a href="${ctx}/taskpackage/bizTaskPackageTemplat/form?id=${bizTaskPackageTemplat.id}">任务包模板<shiro:hasPermission name="taskpackage:bizTaskPackageTemplat:edit">${not empty bizTaskPackageTemplat.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taskpackage:bizTaskPackageTemplat:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm"  modelAttribute="bizTaskPackageTemplat" action="${ctx}/taskpackage/bizTaskPackageTemplat/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">任务包模板编号：</label>
			<div class="controls">
				<form:input path="no" htmlEscape="false" maxlength="18" class="input-xlarge " placeholder="系统自动生成" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务包模板名称：</label>
			<div class="controls">
				<form:input path="templatName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务包类型：</label>
			<div class="controls">
				<form:select path="taskPackageTypeId" class="input-xlarge  required">
					<form:option value="" label=""/>
					<form:options items="${fns:getTaskPackageTypeList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-xlarge required" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-xlarge required">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">门店顺序：</label>
			<div class="controls">
				<form:input  path="storeOrder" htmlEscape="false" maxlength="11" class="input-xlarge digits required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode" items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">最大派单量：</label>
			<div class="controls">
				<form:input path="projectMaxCount" htmlEscape="false" maxlength="7" class="input-xlarge digits required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">首付款比例：</label>
			<div class="controls">
				<form:input path="advancePaymentRates" htmlEscape="false" maxlength="7" class="input-xlarge digits required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付尾款比例：</label>
			<div class="controls">
				<form:input path="restPaymentRates" htmlEscape="false" maxlength="7" class="input-xlarge digits required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否扣质保金：</label>
			<div class="controls">
				<form:radiobuttons path="isQualityGuarantee" onchange="qualityGuaranteeChange(this)"
					items="${fns:getDictList('yes_no')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="" /><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">质保金扣除比例：</label>
			<div class="controls">
				<c:if test="${bizTaskPackageTemplat.isQualityGuarantee eq 1 }">
					<form:select path="qualityGuaranteeRate" class="input-xlarge required">
						<form:option value="0" label="0"/>
						<form:options items="${fns:getqualityGuaranteeRateList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${bizTaskPackageTemplat.isQualityGuarantee ne 1 }">
					<form:select path="qualityGuaranteeRate" class="input-xlarge required" disabled="true">
						<form:option value="0" label="0"/>
						<form:options items="${fns:getqualityGuaranteeRateList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工人结算方式：</label>
			<div class="controls">
				<form:radiobuttons path="settleStyle" 
					items="${fns:getDictList('settle_style')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="" /><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工种：</label>
			<div class="controls">
				<form:select path="empWorkType" class="input-xlarge">
					<form:option value="" label=""/>
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('emp_work_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请辅料预算比例：</label>
			<div class="controls">
				<form:input path="applyBudgetRatio" htmlEscape="false" maxlength="3" class="input-xlarge digits" 
				onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"/>%
			</div>
		</div>
		<!--
		<div class="control-group">
			<label class="control-label">remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label">选择工序：</label>
			<div class="controls">
				<form:select path="procedureSelect" class="input-xlarge ">
					<form:option value="" label="请选择要添加的工序"/>
					<form:options items="${fns:getProcedureList()}" itemLabel="procedureName" itemValue="procedureNo" htmlEscape="false"/>
				</form:select>
<!-- 				<select id="procedureSelect"> -->
<!-- 					<option value="10">这里是测试1</option> -->
<!-- 					<option value="20">这里是测试2</option> -->
<!-- 				</select> -->
				<input type="button" value="添加工序" class="btn btn-primary" onclick="addProcedureRow()"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已包含的工序：</label>
			<div class="controls">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
							<tr>
								<th>工序编号</th>
								<th>工序名称</th>
								<shiro:hasPermission name="test:testDataMain:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="gongxuList">
							<c:forEach items="${bizTaskPackageTemplat.bizTaskPackageTemplatRefs}" var="item" varStatus="status">  
								<tr id="row${item.procedureNo}">
									<th>${item.procedureNo}</th>
									<th>${item.procedureName}</th>
						 			<th width="10"><a href="javascript:delProcedureRow('${item.procedureNo}');">删除</a></th>
								</tr>
							</c:forEach>
						</tbody>
				</table>
			</div>
			<input id="procedureItems" name="procedureItems" type="hidden" value="${bizTaskPackageTemplat.procedureSelect}"/>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="taskpackage:bizTaskPackageTemplat:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
<div >
</body>
</html>