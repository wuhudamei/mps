<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>BizNodePlanPic管理</title>
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
		<li><a href="${ctx}/biznodeplanpic/bizNodePlanPic/">BizNodePlanPic列表</a></li>
		<li class="active"><a href="${ctx}/biznodeplanpic/bizNodePlanPic/form?id=${bizNodePlanPic.id}">BizNodePlanPic<shiro:hasPermission name="biznodeplanpic:bizNodePlanPic:edit">${not empty bizNodePlanPic.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="biznodeplanpic:bizNodePlanPic:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizNodePlanPic" action="${ctx}/biznodeplanpic/bizNodePlanPic/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">节点计划id -- '：</label>
			<div class="controls">
				<form:input path="nodePlanId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片url -- '：</label>
			<div class="controls">
				<form:input path="picUrl" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="biznodeplanpic:bizNodePlanPic:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>