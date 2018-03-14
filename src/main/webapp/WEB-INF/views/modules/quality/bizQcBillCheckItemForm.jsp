<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检检查项查询管理</title>
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
		<li><a href="${ctx}/quality/bizQcBillCheckItem/">质检检查项查询列表</a></li>
		<li class="active"><a href="${ctx}/quality/bizQcBillCheckItem/form?id=${bizQcBillCheckItem.id}">质检检查项查询<shiro:hasPermission name="quality:bizQcBillCheckItem:edit">${not empty bizQcBillCheckItem.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="quality:bizQcBillCheckItem:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizQcBillCheckItem" action="${ctx}/quality/bizQcBillCheckItem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">质检单id：</label>
			<div class="controls">
				<form:select path="qcBillId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联质检单检查项id：</label>
			<div class="controls">
				<form:select path="relatedQcBillCheckItemId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查项id：</label>
			<div class="controls">
				<form:select path="qcCheckItemId" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否合格 -- '0.不合格；1.合格：</label>
			<div class="controls">
				<form:input path="isPassed" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目分数 -- '：</label>
			<div class="controls">
				<form:input path="itemScore" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">得分 -- '：</label>
			<div class="controls">
				<form:input path="gotScore" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否警告 -- '0.不警告；1.警告：</label>
			<div class="controls">
				<form:input path="isWarned" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否现场整改 -- '0.否；1.是：</label>
			<div class="controls">
				<form:input path="isLocaleRepaire" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否限期整改 -- '0.否；1.是：</label>
			<div class="controls">
				<form:input path="isLimitDateRepaire" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">限期整改期限 -- '：</label>
			<div class="controls">
				<input name="limitDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizQcBillCheckItem.limitDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">限期整改检查方式 -- '0：线上检查；1：线下检查：</label>
			<div class="controls">
				<form:input path="limitDateRepaireCheckStyle" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否罚款 -- '0.否；1.是：</label>
			<div class="controls">
				<form:input path="isPunishMoney" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">罚款默认金额 -- '：</label>
			<div class="controls">
				<form:input path="punishMoneyAmountDefault" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">罚款实际金额 -- '：</label>
			<div class="controls">
				<form:checkboxes path="punishMoneyAmountReal" items="${fns:getDictList('processing_method')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查方式 -- '0：线上；1：线下；默认为1线下：</label>
			<div class="controls">
				<form:input path="checkStyle" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注 -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单问题包ID：</label>
			<div class="controls">
				<form:input path="workerPunishOrderTaskpackageId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚工人ID：</label>
			<div class="controls">
				<form:input path="workerPunishEmployeegroupId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">worker_punish_amount：</label>
			<div class="controls">
				<form:input path="workerPunishAmount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">worker_punish_score：</label>
			<div class="controls">
				<form:input path="workerPunishScore" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚项目经理ID：</label>
			<div class="controls">
				<form:input path="pmPunishEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">pm_punish_score：</label>
			<div class="controls">
				<form:input path="pmPunishScore" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">qc_punish_employee_id：</label>
			<div class="controls">
				<form:input path="qcPunishEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">qc_punish_score：</label>
			<div class="controls">
				<form:input path="qcPunishScore" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">qc_punish_amount：</label>
			<div class="controls">
				<form:input path="qcPunishAmount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">责任项目经理id：</label>
			<div class="controls">
				<form:input path="projectManagerId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">责任工人组id：</label>
			<div class="controls">
				<form:input path="workerGroupId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="quality:bizQcBillCheckItem:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>