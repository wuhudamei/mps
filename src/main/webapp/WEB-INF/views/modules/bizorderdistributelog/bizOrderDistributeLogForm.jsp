<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单分配记录管理</title>
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
		<li><a href="${ctx}/bizorderdistributelog/bizOrderDistributeLog/">订单分配记录列表</a></li>
		<li class="active"><a href="${ctx}/bizorderdistributelog/bizOrderDistributeLog/form?id=${bizOrderDistributeLog.id}">订单分配记录<shiro:hasPermission name="bizorderdistributelog:bizOrderDistributeLog:edit">${not empty bizOrderDistributeLog.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizorderdistributelog:bizOrderDistributeLog:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizOrderDistributeLog" action="${ctx}/bizorderdistributelog/bizOrderDistributeLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">&para;&copy;&micro;&yen;id -- '：</label>
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&lsquo;&rsquo;：</label>
			<div class="controls">
				<form:input path="orderTaskpackageId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分配类型：</label>
			<div class="controls">
				<form:input path="distributeType" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&plusmn;&raquo;&middot;&Ouml;&Aring;&auml;&Ocirc;&plusmn;&sup1;&curren;id -- '：</label>
			<div class="controls">
				<form:input path="distributedEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&plusmn;&raquo;&middot;&Ouml;&Aring;&auml;&sup1;&curren;&Egrave;&Euml;&times;&eacute;id -- '：</label>
			<div class="controls">
				<form:input path="distributedGroupId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&middot;&Ouml;&Aring;&auml;&Ccedil;&deg;&Icirc;&acute;&iquest;&cent;&sup1;&curren;&para;&copy;&micro;&yen;&Ecirc;&yacute;&Aacute;&iquest; -- '：</label>
			<div class="controls">
				<form:input path="unfinishedOrderCountBefore" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&plusmn;&frac34;&acute;&Icirc;&middot;&Ouml;&Aring;&auml;&para;&copy;&micro;&yen;&Ecirc;&yacute;&Aacute;&iquest; -- '：</label>
			<div class="controls">
				<form:input path="distributeOrderCount" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&middot;&Ouml;&Aring;&auml;&ordm;&oacute;&Icirc;&acute;&iquest;&cent;&sup1;&curren;&para;&copy;&micro;&yen;&Ecirc;&yacute;&Aacute;&iquest; -- '：</label>
			<div class="controls">
				<form:input path="unfinishedOrderCountAfter" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&plusmn;&cedil;&times;&cent; -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizorderdistributelog:bizOrderDistributeLog:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>