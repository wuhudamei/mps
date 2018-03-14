<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单明细管理</title>
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
		<li><a href="${ctx}/ordertaskpackagepaymentdetail/bizOrderTaskpackagePaymentDetail/">付款单明细列表</a></li>
		<li class="active"><a href="${ctx}/ordertaskpackagepaymentdetail/bizOrderTaskpackagePaymentDetail/form?id=${bizOrderTaskpackagePaymentDetail.id}">付款单明细<shiro:hasPermission name="ordertaskpackagepaymentdetail:bizOrderTaskpackagePaymentDetail:edit">${not empty bizOrderTaskpackagePaymentDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ordertaskpackagepaymentdetail:bizOrderTaskpackagePaymentDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizOrderTaskpackagePaymentDetail" action="${ctx}/ordertaskpackagepaymentdetail/bizOrderTaskpackagePaymentDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">订单任务包付款单id -- '：</label>
			<div class="controls">
				<form:input path="orderTaskpackagePaymentId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">员工id -- '：</label>
			<div class="controls">
				<form:input path="employeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
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
		<div class="control-group">
			<label class="control-label">状态产生日期时间 -- '：</label>
			<div class="controls">
				<input name="statusDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentDetail.statusDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款日期时间 -- '：</label>
			<div class="controls">
				<input name="payDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentDetail.payDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ordertaskpackagepaymentdetail:bizOrderTaskpackagePaymentDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>