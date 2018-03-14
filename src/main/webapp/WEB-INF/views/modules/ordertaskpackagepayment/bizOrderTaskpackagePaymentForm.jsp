<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单管理</title>
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
		<li><a href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/">付款单列表</a></li>
		<li class="active"><a href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/form?id=${bizOrderTaskpackagePayment.id}">付款单<shiro:hasPermission name="ordertaskpackagepayment:bizOrderTaskpackagePayment:edit">${not empty bizOrderTaskpackagePayment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ordertaskpackagepayment:bizOrderTaskpackagePayment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizOrderTaskpackagePayment" action="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">订单任务包结算单id -- '：</label>
			<div class="controls">
				<form:input path="orderTaskpackageSettlementId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款单编号 -- '：</label>
			<div class="controls">
				<form:input path="orderTaskpackagePaymentCode" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款单类型 -- '：</label>
			<div class="controls">
				<form:input path="orderTaskpackagePaymentType" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额 -- '：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态 -- '：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ordertaskpackagepayment:bizOrderTaskpackagePayment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>