<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手机app版本管理</title>
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

			init();

			$("#btnSubmit").click(function(){
				$.post("${ctx}/sysappversion/sysAppVersion/checkVersionIsExits",$("#inputForm").serialize(),function(data){
					if(data == "1"){
						$("#inputForm").submit();
					}else{
						alertx("此版本号已存在！");
					}
				});
			});
		});

		function init(){
			var appType = $("#appType").val();
			if(appType == undefined || appType == null || appType == ""){
				return;
			}
			getMaxVersion(appType);
		}

		function changeAppType(obj){
			var appType = $(obj).val();
			if(appType == undefined || appType == null || appType == ""){
				return;
			}
			getMaxVersion(appType);
		}

		function getMaxVersion(appType){
			$.post("${ctx}/sysappversion/sysAppVersion/queryMaxVersion",{appType:appType},function(data){
				$("#maxVersion").val(data);
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sysappversion/sysAppVersion/list">手机app版本列表</a></li>
		<li class="active"><a href="${ctx}/sysappversion/sysAppVersion/form?id=${sysAppVersion.id}">手机app版本<shiro:hasPermission name="sysappversion:sysAppVersion:edit">${not empty sysAppVersion.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sysappversion:sysAppVersion:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysAppVersion" action="${ctx}/sysappversion/sysAppVersion/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">APP名称：</label>
			<div class="controls">
				<form:select path="appType" class="input-xlarge " required="required" id="appType" onchange="changeAppType(this)">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictListByType('app_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大版本号：</label>
			<div class="controls">
				<input type="text" class="input-xlarge" disabled="disabled" id="maxVersion"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本号：</label>
			<div class="controls">
				<form:input path="version" htmlEscape="false" maxlength="20" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上传安装包：</label>
			<div class="controls">
				<form:hidden id="downloadUrl" path="downloadUrl" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="downloadUrl" type="files" uploadPath="/upload/appVersion" selectMultiple="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否可用：</label>
			<div class="controls">
				<input type="radio" name="isEnabled" value="1" <c:if test="${empty sysAppVersion.id}">checked </c:if> <c:if test="${not empty sysAppVersion.id}"><c:if test="${sysAppVersion.isEnabled eq '1'}">checked </c:if></c:if> />是&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="isEnabled" value="0" <c:if test="${not empty sysAppVersion.id}"><c:if test="${sysAppVersion.isEnabled eq '0'}">checked </c:if></c:if> />否
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sysappversion:sysAppVersion:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>