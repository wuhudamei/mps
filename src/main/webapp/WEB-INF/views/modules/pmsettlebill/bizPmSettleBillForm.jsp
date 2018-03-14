<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算单管理</title>
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
		<li><a href="${ctx}/pmsettlebill/bizPmSettleBill/">结算单列表</a></li>
		<li class="active"><a href="${ctx}/pmsettlebill/bizPmSettleBill/form?id=${bizPmSettleBill.id}">结算单<shiro:hasPermission name="pmsettlebill:bizPmSettleBill:edit">${not empty bizPmSettleBill.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pmsettlebill:bizPmSettleBill:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPmSettleBill" action="${ctx}/pmsettlebill/bizPmSettleBill/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目经理结算单编号 -- '：</label>
			<div class="controls">
				<form:input path="bizPmSettleBillCode" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单id -- '：</label>
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目经理员工id -- '：</label>
			<div class="controls">
				<form:input path="pmEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式 -- '：</label>
			<div class="controls">
				<form:input path="projectMode" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算日期时间 -- '：</label>
			<div class="controls">
				<input name="settleDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizPmSettleBill.settleDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
			<shiro:hasPermission name="pmsettlebill:bizPmSettleBill:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>