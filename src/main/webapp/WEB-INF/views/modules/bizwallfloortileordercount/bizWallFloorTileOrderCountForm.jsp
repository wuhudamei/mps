<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>墙地砖订单统计表管理</title>
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
		<li><a href="${ctx}/bizwallfloortileordercount/bizWallFloorTileOrderCount/">墙地砖订单统计表列表</a></li>
		<li class="active"><a href="${ctx}/bizwallfloortileordercount/bizWallFloorTileOrderCount/form?id=${bizWallFloorTileOrderCount.id}">墙地砖订单统计表<shiro:hasPermission name="bizwallfloortileordercount:bizWallFloorTileOrderCount:edit">${not empty bizWallFloorTileOrderCount.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizwallfloortileordercount:bizWallFloorTileOrderCount:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizWallFloorTileOrderCount" action="${ctx}/bizwallfloortileordercount/bizWallFloorTileOrderCount/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">订单id：</label>
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预算面积：</label>
			<div class="controls">
				<form:input path="squareBudget" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购合计面积：</label>
			<div class="controls">
				<form:input path="squarePurchaseTotal" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">退货面积：</label>
			<div class="controls">
				<form:input path="squareReturn" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购实际面积：</label>
			<div class="controls">
				<form:input path="squarePurchaseReal" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算面积：</label>
			<div class="controls">
				<form:input path="squareSettle" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实测面积：</label>
			<div class="controls">
				<form:input path="squareMeasure" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizwallfloortileordercount:bizWallFloorTileOrderCount:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>