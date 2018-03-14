<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理保证金快照管理</title>
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
		<li><a href="${ctx}/guaranteemoneysnap/bizPmGuaranteeMoneyCnfgSnap/">项目经理保证金快照列表</a></li>
		<li class="active"><a href="${ctx}/guaranteemoneysnap/bizPmGuaranteeMoneyCnfgSnap/form?id=${bizPmGuaranteeMoneyCnfgSnap.id}">项目经理保证金快照<shiro:hasPermission name="guaranteemoneysnap:bizPmGuaranteeMoneyCnfgSnap:edit">${not empty bizPmGuaranteeMoneyCnfgSnap.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="guaranteemoneysnap:bizPmGuaranteeMoneyCnfgSnap:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPmGuaranteeMoneyCnfgSnap" action="${ctx}/guaranteemoneysnap/bizPmGuaranteeMoneyCnfgSnap/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店id -- '：</label>
			<div class="controls">
				<form:input path="storeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
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
			<label class="control-label">质保金上限定额 -- '：</label>
			<div class="controls">
				<form:input path="guaranteeMoneyMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">每个订单扣除额度 -- '：</label>
			<div class="controls">
				<form:input path="guaranteeMoneyPerOrder" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生成日期时间 -- '：</label>
			<div class="controls">
				<input name="generatedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizPmGuaranteeMoneyCnfgSnap.generatedDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注 -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="guaranteemoneysnap:bizPmGuaranteeMoneyCnfgSnap:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>