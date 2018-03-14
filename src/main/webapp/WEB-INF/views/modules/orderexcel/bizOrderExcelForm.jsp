<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>材料清单</title>
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
		<li><a href="${ctx}/orderexcel/bizOrderExcel/list?orderId=${bizOrderExcel.orderId}&flag=${bizOrderExcel.flag}">材料清单</a></li>
		<li class="active"><a href="${ctx}/orderexcel/bizOrderExcel/form?orderId=${bizOrderExcel.orderId}&flag=${bizOrderExcel.flag}">材料清单<shiro:hasPermission name="orderexcel:bizOrderExcel:edit">${not empty bizOrderExcel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="orderexcel:bizOrderExcel:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizOrderExcel" action="${ctx}/orderexcel/bizOrderExcel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="flag" value="${bizOrderExcel.flag}"/>
		<sys:message content="${message}"/>		
		<div class="control-group" hidden="true">
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" maxlength="100" class="input-xlarge required"  readonly="true" cssStyle="display:none"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件名称：</label>
			<div class="controls">
				<form:input path="fileName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">文件上传：</label>
			<div class="controls">
				<form:hidden id="filePath" path="filePath" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="filePath" type="excels" uploadPath="/orderexcel/bizOrderExcel" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="orderexcel:bizOrderExcel:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>