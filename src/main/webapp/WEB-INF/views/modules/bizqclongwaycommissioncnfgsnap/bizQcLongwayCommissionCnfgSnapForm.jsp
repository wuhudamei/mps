<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检员远程费快照管理</title>
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
		<li><a href="${ctx}/bizqclongwaycommissioncnfgsnap/bizQcLongwayCommissionCnfgSnap/">质检员远程费快照列表</a></li>
		<li class="active"><a href="${ctx}/bizqclongwaycommissioncnfgsnap/bizQcLongwayCommissionCnfgSnap/form?id=${bizQcLongwayCommissionCnfgSnap.id}">质检员远程费快照<shiro:hasPermission name="bizqclongwaycommissioncnfgsnap:bizQcLongwayCommissionCnfgSnap:edit">${not empty bizQcLongwayCommissionCnfgSnap.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizqclongwaycommissioncnfgsnap:bizQcLongwayCommissionCnfgSnap:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizQcLongwayCommissionCnfgSnap" action="${ctx}/bizqclongwaycommissioncnfgsnap/bizQcLongwayCommissionCnfgSnap/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">&eacute;&mdash;&uml;&aring;&ordm;&mdash;id -- '：</label>
			<div class="controls">
				<form:input path="storeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&egrave;&reg;&cent;&aring;&bull;id -- '：</label>
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&eacute;&iexcl;&sup1;&ccedil;&rsaquo;&reg;&ccedil;&raquo;&ccedil;&dagger;&aring;&lsquo;&tilde;&aring;&middot;&yen;id -- '：</label>
			<div class="controls">
				<form:input path="pmEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&egrave;&iquest;&oelig;&ccedil;&uml;&lsaquo;&egrave;&acute;&sup1;&eacute;&Dagger;&lsquo;&eacute;&cent; -- '：</label>
			<div class="controls">
				<form:input path="commissionAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&auml;&cedil;&shy;&aelig;&oelig;&Yuml;&aelig;&aelig;&circ;&aelig;&macr;&rdquo;&auml;&frac34;&lsaquo; -- '：</label>
			<div class="controls">
				<form:input path="commissionRateMidway" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&ccedil;&laquo;&pound;&aring;&middot;&yen;&aelig;&aelig;&circ;&aelig;&macr;&rdquo;&auml;&frac34;&lsaquo; -- '：</label>
			<div class="controls">
				<form:input path="commissionRateComplete" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&aelig;&tilde;&macr;&aring;&brvbar;&aring;&macr;&ccedil;&rdquo;&uml; -- '1.&aring;&macr;&ccedil;&rdquo;&uml;&iuml;&frac14;&rsaquo;0.&aring;&oelig;&ccedil;&rdquo;&uml;：</label>
			<div class="controls">
				<form:input path="isEnabled" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&aring;&curren;&Dagger;&aelig;&sup3;&uml; -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizqclongwaycommissioncnfgsnap:bizQcLongwayCommissionCnfgSnap:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>