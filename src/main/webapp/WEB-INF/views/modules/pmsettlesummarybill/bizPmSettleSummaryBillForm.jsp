<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算汇总单管理</title>
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
		<li><a href="${ctx}/pmsettlesummarybill/bizPmSettleSummaryBill/">结算汇总单列表</a></li>
		<li class="active"><a href="${ctx}/pmsettlesummarybill/bizPmSettleSummaryBill/form?id=${bizPmSettleSummaryBill.id}">结算汇总单<shiro:hasPermission name="pmsettlesummarybill:bizPmSettleSummaryBill:edit">${not empty bizPmSettleSummaryBill.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pmsettlesummarybill:bizPmSettleSummaryBill:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPmSettleSummaryBill" action="${ctx}/pmsettlesummarybill/bizPmSettleSummaryBill/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目经理结算月度汇总单编号 -- '：</label>
			<div class="controls">
				<form:input path="pmSettleSummaryBillCode" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">门店id -- '：</label>
			<div class="controls">
				<form:input path="storeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月度 -- '：</label>
			<div class="controls">
				<form:input path="monthNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目经理员工id -- '：</label>
			<div class="controls">
				<form:input path="pmEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中期提成金额 -- '：</label>
			<div class="controls">
				<form:input path="midwayCommissionAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">竣工提成金额 -- '：</label>
			<div class="controls">
				<form:input path="completeCommissionAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">自主支配项金额 -- '：</label>
			<div class="controls">
				<form:input path="ownpayAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标化辅材金额 -- '：</label>
			<div class="controls">
				<form:input path="materialsStandardAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中期质检罚款金额 -- '：</label>
			<div class="controls">
				<form:input path="midwayQcCheckPunishAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">竣工质检罚款金额 -- '：</label>
			<div class="controls">
				<form:input path="completQcCheckPunishAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">质保金金额 -- '：</label>
			<div class="controls">
				<form:input path="guaranteeMoneyAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合计金额 -- '：</label>
			<div class="controls">
				<form:input path="totalAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pmsettlesummarybill:bizPmSettleSummaryBill:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>