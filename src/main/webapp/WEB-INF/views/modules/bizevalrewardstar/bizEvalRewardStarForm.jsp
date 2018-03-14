<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价奖励星级管理</title>
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
		<li><a href="${ctx}/bizevalrewardstar/bizEvalRewardStar/">评价奖励星级列表</a></li>
		<li class="active"><a href="${ctx}/bizevalrewardstar/bizEvalRewardStar/form?id=${bizEvalRewardStar.id}">评价奖励星级<shiro:hasPermission name="bizevalrewardstar:bizEvalRewardStar:edit">${not empty bizEvalRewardStar.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizevalrewardstar:bizEvalRewardStar:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizEvalRewardStar" action="${ctx}/bizevalrewardstar/bizEvalRewardStar/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">&AElig;&Agrave;&frac14;&Ucirc;&frac12;&plusmn;&Agrave;&oslash;&Eacute;&egrave;&Ouml;&Atilde;id -- '：</label>
			<div class="controls">
				<form:input path="evalRewardCfgId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&ETH;&Ccedil;&frac14;&para;&frac14;&para;&plusmn;&eth; -- '：</label>
			<div class="controls">
				<form:input path="starLevel" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&times;&icirc;&ETH;&iexcl;&middot;&Ouml;&Ecirc;&yacute; -- '：</label>
			<div class="controls">
				<form:input path="minScore" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&times;&icirc;&acute;&oacute;&middot;&Ouml;&Ecirc;&yacute; -- '：</label>
			<div class="controls">
				<form:input path="maxScore" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&frac12;&plusmn;&Agrave;&oslash;&frac12;&eth;&para;&icirc; -- '：</label>
			<div class="controls">
				<form:input path="rewardAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Ecirc;&Ccedil;&middot;&ntilde;&AElig;&ocirc;&Oacute;&Atilde; -- '0.&Iacute;&pound;&Oacute;&Atilde;&pound;&raquo;1.&AElig;&ocirc;&Oacute;&Atilde;：</label>
			<div class="controls">
				<form:input path="isEnabled" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&plusmn;&cedil;&times;&cent; -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizevalrewardstar:bizEvalRewardStar:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>