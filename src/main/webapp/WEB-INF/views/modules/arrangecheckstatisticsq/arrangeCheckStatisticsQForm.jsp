<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>约检节点验收统计管理</title>
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
		<li><a href="${ctx}/arrangecheckstatisticsq/arrangeCheckStatisticsQ/">约检节点验收统计列表</a></li>
		<li class="active"><a href="${ctx}/arrangecheckstatisticsq/arrangeCheckStatisticsQ/form?id=${arrangeCheckStatisticsQ.id}">约检节点验收统计<shiro:hasPermission name="arrangecheckstatisticsq:arrangeCheckStatisticsQ:edit">${not empty arrangeCheckStatisticsQ.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="arrangecheckstatisticsq:arrangeCheckStatisticsQ:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="arrangeCheckStatisticsQ" action="${ctx}/arrangecheckstatisticsq/arrangeCheckStatisticsQ/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">质检单编号 -- '：</label>
			<div class="controls">
				<form:input path="qcBillCode" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:input path="qcBillType" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否复检 -- '0.否；1.是；默认0：</label>
			<div class="controls">
				<form:input path="isRecheck" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联质检单id -- '：</label>
			<div class="controls">
				<form:input path="relatedQcBillId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单id -- '：</label>
			<div class="controls">
				<form:input path="orderId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查节点id -- '：</label>
			<div class="controls">
				<form:input path="qcCheckNodeId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请备注 -- '：</label>
			<div class="controls">
				<form:input path="applyRemarks" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态 -- '复检单状态：1.创建；2.项目经理已申请；3.复检不合格；4.复检合格；约检单抽检单状态：0.暂存；2.项目经理已申请；5.已检查完成；6.已确认验收；  抽检单: -1 :只签到   0:暂存  5:检查完成：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">质检申请人员工id -- '：</label>
			<div class="controls">
				<form:input path="applyEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际质检人员工id -- '：</label>
			<div class="controls">
				<form:input path="checkEmployeeId" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">质检日期时间 -- '：</label>
			<div class="controls">
				<input name="checkDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${arrangeCheckStatisticsQ.checkDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">期望质检日期时间 -- '：</label>
			<div class="controls">
				<input name="expectCheckDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${arrangeCheckStatisticsQ.expectCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">验收日期时间 -- '：</label>
			<div class="controls">
				<input name="acceptCheckDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${arrangeCheckStatisticsQ.acceptCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总分 -- '：</label>
			<div class="controls">
				<form:input path="totalScore" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际得分 -- '：</label>
			<div class="controls">
				<form:input path="gotScore" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注 -- '：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">recheck_times：</label>
			<div class="controls">
				<form:input path="recheckTimes" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">check_words：</label>
			<div class="controls">
				<form:input path="checkWords" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Eacute;&oacute;&ordm;&Euml;&times;&acute;&Igrave;&not;&pound;&uml;&Igrave;&iacute;&frac14;&Oacute;&pound;&copy; -- '：</label>
			<div class="controls">
				<form:input path="reviewStatus" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Eacute;&oacute;&ordm;&Euml;&Ograve;&acirc;&frac14;&ucirc;&pound;&uml;&Igrave;&iacute;&frac14;&Oacute;&pound;&copy; -- '：</label>
			<div class="controls">
				<form:input path="reviewRemark" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&Eacute;&oacute;&ordm;&Euml;&Egrave;&Otilde;&AElig;&Uacute;&Ecirc;&plusmn;&frac14;&auml;&pound;&uml;&Igrave;&iacute;&frac14;&Oacute;&pound;&copy; -- '：</label>
			<div class="controls">
				<input name="reviewDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${arrangeCheckStatisticsQ.reviewDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">plan_check_datetime：</label>
			<div class="controls">
				<input name="planCheckDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${arrangeCheckStatisticsQ.planCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">delay_reason_pm：</label>
			<div class="controls">
				<form:input path="delayReasonPm" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">delay_reason_qc：</label>
			<div class="controls">
				<form:input path="delayReasonQc" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="arrangecheckstatisticsq:arrangeCheckStatisticsQ:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>