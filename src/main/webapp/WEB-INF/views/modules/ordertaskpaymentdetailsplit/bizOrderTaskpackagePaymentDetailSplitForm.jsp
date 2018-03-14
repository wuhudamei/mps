<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单明细拆分管理</title>
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
		<li><a href="${ctx}/ordertaskpaymentdetailsplit/bizOrderTaskpackagePaymentDetailSplit/">付款单明细拆分列表</a></li>
		<li class="active"><a href="${ctx}/ordertaskpaymentdetailsplit/bizOrderTaskpackagePaymentDetailSplit/form?id=${bizOrderTaskpackagePaymentDetailSplit.id}">付款单明细拆分<shiro:hasPermission name="ordertaskpaymentdetailsplit:bizOrderTaskpackagePaymentDetailSplit:edit">${not empty bizOrderTaskpackagePaymentDetailSplit.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ordertaskpaymentdetailsplit:bizOrderTaskpackagePaymentDetailSplit:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizOrderTaskpackagePaymentDetailSplit" action="${ctx}/ordertaskpaymentdetailsplit/bizOrderTaskpackagePaymentDetailSplit/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">付款单明细id -- '：</label>
			<div class="controls">
				<form:input path="orderTaskpackagePaymentDetailId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款员工id -- '：</label>
			<div class="controls">
				<form:input path="salaryEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">员工银行卡id -- '：</label>
			<div class="controls">
				<form:input path="employeeBankcardId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">员工银行卡关联身份证id -- '：</label>
			<div class="controls">
				<form:input path="employeeBankcardRelatedIdcardId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款员工姓名 -- '：</label>
			<div class="controls">
				<form:input path="salaryEmployeeName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款员工身份证号 -- '：</label>
			<div class="controls">
				<form:input path="salaryEmployeeIdcardNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款员工银行卡号 -- '：</label>
			<div class="controls">
				<form:input path="salaryEmployeeBankcard" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款总金额 -- '：</label>
			<div class="controls">
				<form:input path="payAmountTotal" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联人姓名 -- '：</label>
			<div class="controls">
				<form:input path="relatedName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联人身份证号 -- '：</label>
			<div class="controls">
				<form:input path="relatedIdcardNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">打款金额 -- '：</label>
			<div class="controls">
				<form:input path="payAmountSplit" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注 -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ordertaskpaymentdetailsplit:bizOrderTaskpackagePaymentDetailSplit:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>