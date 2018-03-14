<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理星级提成快照管理</title>
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
		<li><a href="${ctx}/starcommissionsnap/bizPmStarCommissionCnfgSnap/">项目经理星级提成快照列表</a></li>
		<li class="active"><a href="${ctx}/starcommissionsnap/bizPmStarCommissionCnfgSnap/form?id=${bizPmStarCommissionCnfgSnap.id}">项目经理星级提成快照<shiro:hasPermission name="starcommissionsnap:bizPmStarCommissionCnfgSnap:edit">${not empty bizPmStarCommissionCnfgSnap.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="starcommissionsnap:bizPmStarCommissionCnfgSnap:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPmStarCommissionCnfgSnap" action="${ctx}/starcommissionsnap/bizPmStarCommissionCnfgSnap/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店id -- '：</label>
			<div class="controls">
				<form:input path="storeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单id -- '：</label>
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目经理员工id -- '：</label>
			<div class="controls">
				<form:input path="pmEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">新老房 -- '0.老房；1.新房：</label>
			<div class="controls">
				<form:input path="isOldNew" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">星级 -- '：</label>
			<div class="controls">
				<form:input path="starLever" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提成金额 -- '：</label>
			<div class="controls">
				<form:input path="commissionAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中期提成比例 -- '：</label>
			<div class="controls">
				<form:input path="commissionRateMidway" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">竣工提成比例 -- '：</label>
			<div class="controls">
				<form:input path="commissionRateComplete" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注 -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="starcommissionsnap:bizPmStarCommissionCnfgSnap:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>