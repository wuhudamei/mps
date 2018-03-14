<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>同步数据管理</title>
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
		<li><a href="${ctx}/bizsyndata/bizSynData/">同步数据列表</a></li>
		<li class="active"><a href="${ctx}/bizsyndata/bizSynData/form?id=${bizSynData.id}">同步数据<shiro:hasPermission name="bizsyndata:bizSynData:edit">${not empty bizSynData.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizsyndata:bizSynData:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizSynData" action="${ctx}/bizsyndata/bizSynData/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">&Ecirc;&yacute;&frac34;&Yacute;&times;&szlig;&Iuml;&ograve; -- '1.&frac12;&oslash;&Agrave;&acute;&pound;&raquo;2.&sup3;&ouml;&Egrave;&yen;：</label>
			<div class="controls">
				<form:input path="dataDirection" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Ograve;&micro;&Icirc;&ntilde;&Agrave;&agrave;&ETH;&Iacute; -- '：</label>
			<div class="controls">
				<form:input path="businessType" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Ograve;&micro;&Icirc;&ntilde;&Icirc;&uml;&Ograve;&raquo;&plusmn;&ecirc;&Ecirc;&para;&Otilde;&ucirc;&ETH;&Iacute; -- '：</label>
			<div class="controls">
				<form:input path="businessOnlyMarkInt" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Ograve;&micro;&Icirc;&ntilde;&Icirc;&uml;&Ograve;&raquo;&plusmn;&ecirc;&Ecirc;&para;&times;&Ouml;&middot;&ucirc;&ETH;&Iacute; -- '：</label>
			<div class="controls">
				<form:input path="businessOnlyMarkVarchar" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Ograve;&micro;&Icirc;&ntilde;&Ecirc;&yacute;&frac34;&Yacute; -- '：</label>
			<div class="controls">
				<form:input path="businessData" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Iacute;&not;&sup2;&frac12;&times;&acute;&Igrave;&not; -- '1.&sup3;&Eacute;&sup1;&brvbar;&pound;&raquo;2.&Ecirc;&sect;&deg;&Uuml;：</label>
			<div class="controls">
				<form:input path="synStatus" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Iacute;&not;&sup2;&frac12;&Egrave;&Otilde;&AElig;&Uacute;&Ecirc;&plusmn;&frac14;&auml; -- '：</label>
			<div class="controls">
				<input name="synDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizSynData.synDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Ecirc;&Ccedil;&middot;&ntilde;&times;&Ocirc;&para;&macr;&Ouml;&Oslash;&ETH;&Acirc;&Iacute;&not;&sup2;&frac12; -- '0.&sup2;&raquo;&times;&Ocirc;&para;&macr;&Iacute;&not;&sup2;&frac12;&pound;&raquo;1.&times;&Ocirc;&para;&macr;&Iacute;&not;&sup2;&frac12;：</label>
			<div class="controls">
				<form:input path="isAutoSyn" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&plusmn;&cedil;&times;&cent; -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizsyndata:bizSynData:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>