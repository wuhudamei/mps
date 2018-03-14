<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单批次管理</title>
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
		<li><a href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/">付款单批次列表</a></li>
		<li class="active"><a href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/form?id=${bizOrderTaskpackagePaymentSummary.id}">付款单批次<shiro:hasPermission name="ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:edit">${not empty bizOrderTaskpackagePaymentSummary.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizOrderTaskpackagePaymentSummary" action="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">订单任务包付款汇总单编号 -- '：</label>
			<div class="controls">
				<form:input path="orderTaskpackagePaymentSummaryCode" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款单数量 -- '：</label>
			<div class="controls">
				<form:input path="orderTaskpackagePaymentCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生成日期时间 -- '：</label>
			<div class="controls">
				<input name="generatedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummary.generatedDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请人员工id -- '：</label>
			<div class="controls">
				<form:input path="applyEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态 -- '：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核人员工id -- '：</label>
			<div class="controls">
				<form:input path="examineEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核日期时间 -- '：</label>
			<div class="controls">
				<input name="examineDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummary.examineDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核意见 -- '：</label>
			<div class="controls">
				<form:input path="examineWords" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">撤回原因 -- '：</label>
			<div class="controls">
				<form:input path="cancleReason" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注 -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>