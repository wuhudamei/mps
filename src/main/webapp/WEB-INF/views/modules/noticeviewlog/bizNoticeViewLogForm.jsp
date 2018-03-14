<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息公告查看日志管理</title>
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
		<li><a href="${ctx}/noticeviewlog/bizNoticeViewLog/">消息公告查看日志列表</a></li>
		<li class="active"><a href="${ctx}/noticeviewlog/bizNoticeViewLog/form?id=${bizNoticeViewLog.id}">消息公告查看日志<shiro:hasPermission name="noticeviewlog:bizNoticeViewLog:edit">${not empty bizNoticeViewLog.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="noticeviewlog:bizNoticeViewLog:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizNoticeViewLog" action="${ctx}/noticeviewlog/bizNoticeViewLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">&aring;&hellip;&not;&aring;&lsquo;&Scaron;id -- '：</label>
			<div class="controls">
				<form:input path="noticeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&aelig;&Yuml;&yen;&ccedil;&oelig;&lsaquo;&aring;&lsquo;&tilde;&aring;&middot;&yen;id -- '：</label>
			<div class="controls">
				<form:input path="viewEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&aelig;&Yuml;&yen;&ccedil;&oelig;&lsaquo;&aelig;&mdash;&yen;&aelig;&oelig;&Yuml;&aelig;&mdash;&para;&eacute;&mdash;&acute; -- '：</label>
			<div class="controls">
				<input name="viewDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizNoticeViewLog.viewDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&aring;&curren;&Dagger;&aelig;&sup3;&uml; -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="noticeviewlog:bizNoticeViewLog:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>