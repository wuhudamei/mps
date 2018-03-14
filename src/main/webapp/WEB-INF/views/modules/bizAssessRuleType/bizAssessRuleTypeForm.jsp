<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考核条例分类添加</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
			
			$("#inputForm").validate({
				rules : {
					storeId : "required",
					projectMode:"required",
					typeName : "required",
					isRewardOrPunish : "required",
					rewardPunishTargetType : "required",
					isEnabled : "required",
				},
				messages : {
					storeId : "请选择门店",
					projectMode : "请选择工程模式",
					typeName : "请填写分类名称",
					isRewardOrPunish:"请选择奖励惩罚类别",
					rewardPunishTargetType:"请选择奖励惩罚对象",
					isEnabled : "请选择是否启用",
				},
				errorContainer: "#messageBox",
				
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizAssessRuleType/bizAssessRuleType/queryBizAssessRuleType">考核条例分类列表</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizAssessRuleType" action="${ctx}/bizAssessRuleType/bizAssessRuleType/saveBizAssessRuleType" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<c:if test="${empty storeDropEnable}">
				    <form:hidden path="storeId"/>
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
				<c:if test="${empty gongcheng}">
				    <form:hidden path="projectMode"/>
					<form:select path="projectMode" class="input-xlarge required" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">分类名称：</label>
			<div class="controls">
				<form:input path="typeName" htmlEscape="false" class="input-medium needClear" style="width:270px;"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">奖励惩罚类别：</label>
			<div class="controls">
				<form:select path="isRewardOrPunish" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_reward_or_punish')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">奖励惩罚对象：</label>
			<div class="controls">
				<form:select path="rewardPunishTargetType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('reward_punish_target_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">是否月度巡检：</label>
			<div class="controls">
				<form:radiobuttons path="isMonthInspection" items="${fns:getDictList('is_month_inspection')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>