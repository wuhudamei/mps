<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>员工身份证关联管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
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
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard/">员工身份证关联列表</a></li>
		<li class="active"><a href="${ctx}/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard/form?id=${bizEmployeeBankcardRelatedIdcard.id}">员工身份证关联<shiro:hasPermission name="employeebankcardrelatedidcard:bizEmployeeBankcardRelatedIdcard:edit">${not empty bizEmployeeBankcardRelatedIdcard.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="employeebankcardrelatedidcard:bizEmployeeBankcardRelatedIdcard:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizEmployeeBankcardRelatedIdcard" action="${ctx}/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">员工银行卡id -- '：</label>
			<div class="controls">
				<form:input path="employeeBankcardId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联身份证号 -- '：</label>
			<div class="controls">
				<form:input path="relatedIdcardNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联身份证姓名 -- '：</label>
			<div class="controls">
				<form:input path="relatedName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注 -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="employeebankcardrelatedidcard:bizEmployeeBankcardRelatedIdcard:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>