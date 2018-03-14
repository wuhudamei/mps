<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价奖励任务包模板管理</title>
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
		<li><a href="${ctx}/bizevalrewardtaskpacktemp/bizEvalRewardTaskpackTemp/">评价奖励任务包模板列表</a></li>
		<li class="active"><a href="${ctx}/bizevalrewardtaskpacktemp/bizEvalRewardTaskpackTemp/form?id=${bizEvalRewardTaskpackTemp.id}">评价奖励任务包模板<shiro:hasPermission name="bizevalrewardtaskpacktemp:bizEvalRewardTaskpackTemp:edit">${not empty bizEvalRewardTaskpackTemp.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizevalrewardtaskpacktemp:bizEvalRewardTaskpackTemp:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizEvalRewardTaskpackTemp" action="${ctx}/bizevalrewardtaskpacktemp/bizEvalRewardTaskpackTemp/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">&AElig;&Agrave;&frac14;&Ucirc;&frac12;&plusmn;&Agrave;&oslash;&Eacute;&egrave;&Ouml;&Atilde;id -- '：</label>
			<div class="controls">
				<form:input path="evalRewardCfgId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Egrave;&Icirc;&Icirc;&ntilde;&deg;&uuml;&Auml;&pound;&deg;&aelig;id -- '：</label>
			<div class="controls">
				<form:input path="taskpackTempId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&plusmn;&cedil;&times;&cent; -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizevalrewardtaskpacktemp:bizEvalRewardTaskpackTemp:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>