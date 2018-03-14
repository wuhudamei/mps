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
	<br/>
	<form:form id="inputForm" modelAttribute="orderCadfile" action="${ctx}/ordercad/orderCadfile/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" hidden="true">
			
			<div class="controls">
				<form:input  path="orderId" htmlEscape="false" maxlength="100" class="input-xxlarge required"  readonly="true"  cssStyle="display:none" ></form:input>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">订单图纸名称：</label>
			<div class="controls">
				<form:input path="fileName" htmlEscape="false" maxlength="200" class="input-xlarge required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">CAD添加：</label>
			<div class="controls">
				<form:hidden id="filePath" path="filePath" htmlEscape="false" maxlength="255" class="input-xlarge" disabled="true"/>
				<sys:ckfinder input="filePath" type="files" uploadPath="/ordercad/orderCadfile" selectMultiple="true" readonly="true"/>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>