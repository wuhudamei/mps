<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息公告接收人管理</title>
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
		<li><a href="${ctx}/noticereceiver/bizNoticeReceiver/">消息公告接收人列表</a></li>
		<li class="active"><a href="${ctx}/noticereceiver/bizNoticeReceiver/form?id=${bizNoticeReceiver.id}">消息公告接收人<shiro:hasPermission name="noticereceiver:bizNoticeReceiver:edit">${not empty bizNoticeReceiver.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="noticereceiver:bizNoticeReceiver:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizNoticeReceiver" action="${ctx}/noticereceiver/bizNoticeReceiver/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">&aring;&hellip;&not;&aring;&lsquo;&Scaron;id -- '：</label>
			<div class="controls">
				<form:input path="noticeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&aelig;Ž&yen;&aelig;&rdquo;&para;&ccedil;&plusmn;&raquo;&aring;ž&lsaquo; -- '1.&egrave;&sect;&rsquo;&egrave;&permil;&sup2;&iuml;&frac14;&rsaquo;2.&aelig;&OElig;&Dagger;&aring;&reg;&scaron;&aring;&lsquo;&tilde;&aring;&middot;&yen;：</label>
			<div class="controls">
				<form:input path="receiverType" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&aelig;Ž&yen;&aelig;&rdquo;&para;&egrave;&sect;&rsquo;&egrave;&permil;&sup2; -- '1.&eacute;&iexcl;&sup1;&ccedil;&rsaquo;&reg;&ccedil;&raquo;&ccedil;&dagger;&iuml;&frac14;&rsaquo;2.&egrave;&acute;&uml;&aelig;&pound;&euro;&aring;&lsquo;&tilde;&iuml;&frac14;&rsaquo;3.&aring;&middot;&yen;&auml;&ordm;&ordm;：</label>
			<div class="controls">
				<form:input path="receiverRole" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&aelig;Ž&yen;&aelig;&rdquo;&para;&aring;&lsquo;&tilde;&aring;&middot;&yen;id -- '：</label>
			<div class="controls">
				<form:input path="receiverEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&aring;&curren;&Dagger;&aelig;&sup3;&uml; -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="noticereceiver:bizNoticeReceiver:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>