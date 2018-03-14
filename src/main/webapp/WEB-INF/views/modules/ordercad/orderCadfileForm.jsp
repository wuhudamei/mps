<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图纸管理</title>
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
		<li><a href="${ctx}/ordercad/orderCadfile?orderNumber=${orderCadfile.orderNumber}&flag=${orderCadfile.flag}">图纸列表</a></li>
		<li class="active"><a href="${ctx}/ordercad/orderCadfile/form?orderNumber=${orderCadfile.orderNumber}&flag=${orderCadfile.flag}">图纸<shiro:hasPermission name="ordercad:orderCadfile:edit">${not empty orderCadfile.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ordercad:orderCadfile:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="orderCadfile" action="${ctx}/ordercad/orderCadfile/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="orderNumber" value="${orderCadfile.orderNumber}"/>
		<form:hidden path="flag" value="${orderCadfile.flag}"/>
		<sys:message content="${message}"/>		
		<div class="control-group" hidden="true">
			
			<div class="controls">
				<form:input  path="orderId" htmlEscape="false" maxlength="100" class="input-xxlarge required"  readonly="true"  cssStyle="display:none" ></form:input>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">订单图纸名称：</label>
			<div class="controls">
				<form:input path="fileName" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">CAD添加：</label>
			<div class="controls">
				<form:hidden id="filePath" path="filePath" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="filePath" type="files" uploadPath="/ordercad/orderCadfile" selectMultiple="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="ordercad:orderCadfile:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>