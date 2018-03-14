<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标化辅材领取详情管理</title>
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
		<li><a href="${ctx}/standradmaterialsdetail/bizMaterialsStandardReceiveDetail/">标化辅材领取详情列表</a></li>
		<li class="active"><a href="${ctx}/standradmaterialsdetail/bizMaterialsStandardReceiveDetail/form?id=${bizMaterialsStandardReceiveDetail.id}">标化辅材领取详情<shiro:hasPermission name="standradmaterialsdetail:bizMaterialsStandardReceiveDetail:edit">${not empty bizMaterialsStandardReceiveDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="standradmaterialsdetail:bizMaterialsStandardReceiveDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizMaterialsStandardReceiveDetail" action="${ctx}/standradmaterialsdetail/bizMaterialsStandardReceiveDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标化辅材领取单id -- '：</label>
			<div class="controls">
				<form:input path="materialsStandardReceiveBillId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物料类别：</label>
			<div class="controls">
				<form:input path="materialsType" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物料名称：</label>
			<div class="controls">
				<form:input path="materialsName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物料单位：</label>
			<div class="controls">
				<form:input path="materialsUnit" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物料单价：</label>
			<div class="controls">
				<form:input path="materialsPrice" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">领取数量：</label>
			<div class="controls">
				<form:input path="receiveNumber" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物料金额：</label>
			<div class="controls">
				<form:input path="materialsAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="standradmaterialsdetail:bizMaterialsStandardReceiveDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>