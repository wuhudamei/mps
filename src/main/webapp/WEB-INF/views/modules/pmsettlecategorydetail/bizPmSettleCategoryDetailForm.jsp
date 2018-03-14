<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算类目明细管理</title>
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
		<li><a href="${ctx}/pmsettlecategorydetail/bizPmSettleCategoryDetail/">结算类目明细列表</a></li>
		<li class="active"><a href="${ctx}/pmsettlecategorydetail/bizPmSettleCategoryDetail/form?id=${bizPmSettleCategoryDetail.id}">结算类目明细<shiro:hasPermission name="pmsettlecategorydetail:bizPmSettleCategoryDetail:edit">${not empty bizPmSettleCategoryDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pmsettlecategorydetail:bizPmSettleCategoryDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPmSettleCategoryDetail" action="${ctx}/pmsettlecategorydetail/bizPmSettleCategoryDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
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
			<label class="control-label">结算类目 -- '1.标化辅材;2.自主支配;3.中期提成;4.质检罚款;5.竣工提成;6.质保金;：</label>
			<div class="controls">
				<form:input path="settleCategory" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算金额 -- '：</label>
			<div class="controls">
				<form:input path="settleAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目经理结算类目汇总id -- '：</label>
			<div class="controls">
				<form:input path="pmSettleCategorySummaryId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算状态 -- '：</label>
			<div class="controls">
				<form:input path="settleStatus" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算状态日期时间 -- '：</label>
			<div class="controls">
				<input name="settleStatusDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizPmSettleCategoryDetail.settleStatusDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算备注 -- '：</label>
			<div class="controls">
				<form:input path="settleRemark" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联业务id -- '：</label>
			<div class="controls">
				<form:input path="relatedBusinessId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pmsettlecategorydetail:bizPmSettleCategoryDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>