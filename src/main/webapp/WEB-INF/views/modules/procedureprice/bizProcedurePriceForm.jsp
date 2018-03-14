<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工序价格管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					if($("#laborPrice").val()>999999){
						alert("人工结算价不能超过999999！");
					}else if($("#accessoriesPrice").val()>999999){
						alert("辅料结算价不能超过999999！");
					}else{
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
		});
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	    <li><a href="${ctx}/procedure/bizProcedure/">工序列表</a></li>
		<li><a href="${ctx}/procedureprice/bizProcedurePrice/list?procedureNo=${bizProcedurePrice.procedureNo}">工序价格列表</a></li>
		<li class="active"><a href="${ctx}/procedureprice/bizProcedurePrice/form?id=${bizProcedurePrice.id}">工序价格<shiro:hasPermission name="procedureprice:bizProcedurePrice:edit">${not empty bizProcedurePrice.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="procedureprice:bizProcedurePrice:edit">添加/修改</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" onsubmit="onFormSubmit(this)" modelAttribute="bizProcedurePrice" action="${ctx}/procedureprice/bizProcedurePrice/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>	
		<form:hidden path="id"/>
		<form:hidden path="isEnable" value="0"/>
		<form:hidden path="delFlag" value="0"/>
		<form:hidden path="procedureNo" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
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
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode" items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">人工结算价：</label>
			<div class="controls">
				<form:input path="laborPrice" htmlEscape="false" maxlength="8" class="input-xlarge number required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">辅料结算价：</label>
			<div class="controls">
				<form:input path="accessoriesPrice" htmlEscape="false" maxlength="8" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">综合价：</label>
			<div class="controls">
				<form:input path="synthesizePrice" htmlEscape="false" class="input-xlarge  number " placeholder="系统自动计算" disabled="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		

		<div class="control-group">
			<label class="control-label">生效日期：</label>
			<div class="controls">
				<c:if test="${!empty dateEnable}">
				<input name="effectiveDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${bizProcedurePrice.effectiveDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({minDate: '%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</c:if>
				<c:if test="${empty dateEnable}">
				<input name="effectiveDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${bizProcedurePrice.effectiveDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
		
			<shiro:hasPermission name="procedureprice:bizProcedurePrice:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>