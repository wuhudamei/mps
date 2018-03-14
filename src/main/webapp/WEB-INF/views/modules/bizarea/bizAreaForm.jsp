<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>区域管理管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									rules : {
										storeId : "required",
										name : "required",
									},
									messages : {
										storeId : "请选择门店",
										name : "请输入区域名称",
									},
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
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
		<li><a href="${ctx}/bizarea/bizArea/">区域管理列表</a></li>
		<li class="active"><a
			href="${ctx}/bizarea/bizArea/form?id=${bizArea.id}">区域管理<shiro:hasPermission
					name="bizarea:bizArea:edit">${not empty bizArea.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="bizarea:bizArea:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizArea" onsubmit="onFormSubmit(this)" 
		action="${ctx}/bizarea/bizArea/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">区域编号：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="100"
					class="input-xlarge required" placeholder="系统自动生成" disabled="true" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域名称：</label>
			<div class="controls">
				<form:input path="name" id="name" name="name" htmlEscape="false" maxlength="64"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">门店：</label>
            <div class="controls">
                <c:if test="${empty storeDropEnable}">
                    <form:select path="storeId" class="input-xlarge required" disabled="true">
                        <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                    </form:select>
                </c:if>
                <c:if test="${!empty storeDropEnable}">
                    <form:select path="storeId" class="input-xlarge required">
                        <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                    </form:select>
                </c:if>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<input type="radio" name="projectMode" value="2"
						<c:if test="${empty projectModeEnable}"><c:if test="${empty bizArea.projectMode or bizArea.projectMode eq '2'}"> checked="checked"</c:if></c:if>
						<c:if test="${not empty projectModeEnable}"><c:if test="${projectModeEnable eq '2'}"> checked="checked"</c:if><c:if test="${projectModeEnable eq '1'}"> disabled="disabled"</c:if></c:if>
						/>传统&nbsp;
				<input type="radio" name="projectMode" value="1"
						<c:if test="${empty projectModeEnable}"><c:if test="${bizArea.projectMode eq '1'}"> checked="checked"</c:if></c:if>
						<c:if test="${not empty projectModeEnable}"><c:if test="${projectModeEnable eq '2'}"> disabled="disabled"</c:if><c:if test="${projectModeEnable eq '1'}"> checked="checked"</c:if></c:if>
						/>产业
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizarea:bizArea:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>