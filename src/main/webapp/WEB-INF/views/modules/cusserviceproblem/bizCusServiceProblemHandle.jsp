<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>处理售后问题</title>
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
		<li><a href="${ctx}/cusserviceproblem/bizCusServiceProblem/list">售后问题列表</a></li>
<%-- 		<li class="active"><a href="${ctx}/cusserviceproblem/bizCusServiceProblem/form?id=${bizCusServiceProblem.id}">无<shiro:hasPermission name="cusserviceproblem:bizCusServiceProblem:edit">${not empty bizCusServiceProblem.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cusserviceproblem:bizCusServiceProblem:edit">查看</shiro:lacksPermission></a></li> --%>
	</ul><br/>
	<table>
	<form:form id="inputForm" modelAttribute="bizCusServiceProblem" action="${ctx}/cusserviceproblem/bizCusServiceProblem/handle" method="post" class="form-horizontal">
		<form:hidden path="id"/>
			<input id="pageNo" name="pageNo" type="hidden" value="${bizCusServiceProblem.statusdescribe}"/>
		<sys:message content="${message}"/>		
		
	
		<div class="control-group">
			<label class="control-label">处理内容：</label>
			<div class="controls">
				<form:textarea path="statusdescribe" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cusserviceproblem:bizCusServiceProblem:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
		</table>
</body>
</html>