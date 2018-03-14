<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检员远程费记录管理</title>
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
		<li><a href="${ctx}/bizqclongwaycommissionlog/bizQcLongwayCommissionLog/">质检员远程费记录列表</a></li>
		<li class="active"><a href="${ctx}/bizqclongwaycommissionlog/bizQcLongwayCommissionLog/form?id=${bizQcLongwayCommissionLog.id}">质检员远程费记录<shiro:hasPermission name="bizqclongwaycommissionlog:bizQcLongwayCommissionLog:edit">${not empty bizQcLongwayCommissionLog.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizqclongwaycommissionlog:bizQcLongwayCommissionLog:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizQcLongwayCommissionLog" action="${ctx}/bizqclongwaycommissionlog/bizQcLongwayCommissionLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">&para;&copy;&micro;&yen;id -- '：</label>
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Ouml;&Ecirc;&frac14;&igrave;&Ocirc;&plusmn;&Ocirc;&plusmn;&sup1;&curren;id -- '：</label>
			<div class="controls">
				<form:input path="qcEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&ETH;&Ccedil;&frac14;&para; -- '：</label>
			<div class="controls">
				<form:input path="starLevel" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Igrave;&aacute;&sup3;&Eacute;&frac12;&Uacute;&micro;&atilde; -- '1.&Ouml;&ETH;&AElig;&Uacute;&pound;&raquo;2.&iquest;&cent;&sup1;&curren;：</label>
			<div class="controls">
				<form:input path="commissionNode" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Igrave;&aacute;&sup3;&Eacute;&frac12;&eth;&para;&icirc; -- '：</label>
			<div class="controls">
				<form:input path="commissionAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Igrave;&aacute;&sup3;&Eacute;&plusmn;&Egrave;&Agrave;&yacute; -- '：</label>
			<div class="controls">
				<form:input path="commissionRate" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Igrave;&aacute;&sup3;&Eacute;&Egrave;&Otilde;&AElig;&Uacute;&Ecirc;&plusmn;&frac14;&auml; -- '：</label>
			<div class="controls">
				<input name="commissionDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizQcLongwayCommissionLog.commissionDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&plusmn;&cedil;&times;&cent; -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizqclongwaycommissionlog:bizQcLongwayCommissionLog:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>