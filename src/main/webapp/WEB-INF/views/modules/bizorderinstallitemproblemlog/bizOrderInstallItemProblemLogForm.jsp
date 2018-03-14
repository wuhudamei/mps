<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单安装项问题日志管理</title>
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
		<li><a href="${ctx}/bizorderinstallitemproblemlog/bizOrderInstallItemProblemLog/">订单安装项问题日志列表</a></li>
		<li class="active"><a href="${ctx}/bizorderinstallitemproblemlog/bizOrderInstallItemProblemLog/form?id=${bizOrderInstallItemProblemLog.id}">订单安装项问题日志<shiro:hasPermission name="bizorderinstallitemproblemlog:bizOrderInstallItemProblemLog:edit">${not empty bizOrderInstallItemProblemLog.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizorderinstallitemproblemlog:bizOrderInstallItemProblemLog:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizOrderInstallItemProblemLog" action="${ctx}/bizorderinstallitemproblemlog/bizOrderInstallItemProblemLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">订单安装项问题id：</label>
			<div class="controls">
				<form:input path="orderInstallItemProblemId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问题处理角色：</label>
			<div class="controls">
				<form:input path="problemSolveRole" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问题处理员工id：</label>
			<div class="controls">
				<form:input path="problemSolveEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问题处理说明：</label>
			<div class="controls">
				<form:input path="problemSolveNotes" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&plusmn;&cedil;&times;&cent; -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizorderinstallitemproblemlog:bizOrderInstallItemProblemLog:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>