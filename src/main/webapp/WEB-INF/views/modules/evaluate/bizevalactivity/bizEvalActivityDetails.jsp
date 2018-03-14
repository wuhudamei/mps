<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价活动设置管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/list">评价活动设置列表</a></li>
		<li class="active"><a href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/details?id=${bizEvalActivity.id}">评价活动详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizEvalActivity" action="${ctx}/evaluate/bizevalactivity/bizEvalActivity/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required" disabled="true">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" disabled="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价对象：</label>
			<div class="controls">
				<form:select path="evalTargetType" class="input-xlarge required" disabled="true">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('eval_target_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">有效时间：</label>
			<div class="controls">
				<input id="evalStartDatetime" disabled="disabled" name="evalStartDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${bizEvalActivity.evalStartDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'evalEndDatetime\')}',isShowClear:false});"/>
				至
				<input id="evalEndDatetime" disabled="disabled" name="evalEndDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${bizEvalActivity.evalEndDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'evalStartDatetime\')}',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group" hidden="hidden">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled" disabled="true" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<c:if test="${bizEvalActivity.evalTargetType == '1'}">
		<div class="control-group">
			<label class="control-label">任务包类型：</label>
			<div class="controls" id="taskpackages">
				<c:if test="${bizEvalActivity.id != '' }">
					<c:forEach items="${taskPackageList }" var="taskPackageList" varStatus="status">
						<span><input id="taskpackTempId${status.index+1 }" disabled="disabled" name="taskpackTempId" type="checkbox" class="taskpackTempId" value="${taskPackageList.id }" 
							<c:forEach items="${nowTaskPackageList }" var="nowTaskPackageList">
								<c:if test="${taskPackageList.id == nowTaskPackageList.taskpackTempId }">
									checked="checked"
								</c:if>
							</c:forEach>
						><label for="taskpackTempId${status.index+1 }">${taskPackageList.templatName }</label></span>
					</c:forEach>
					<span class="help-inline"><font color="red">*</font> </span>
				</c:if> 
			</div>
		</div>
		</c:if>
		
		<c:if test="${bizEvalActivity.evalTargetType == '2'}">
		<div class="control-group">
			<label class="control-label">项目经理评价阶段：</label>
			<table>
			   <c:forEach items="${stageList}" var="managerEvalStage" varStatus="status">
				<tr>
					<td style="width: 300px">
					  <input type="checkbox" checked="checked" disabled="disabled" name="managerEvalStage" value="${managerEvalStage.relatedStage}" />${fns:getDictLabel(managerEvalStage.relatedStage, 'manager_eval_stage', '')}
					</td>
					<td>
					评价关联的约检节点：
					 <select name='evalStageCheckNode' class='input-xlarge' disabled="disabled">
					    <c:forEach items="${checkNodeList}" var="checkNode">
					       <c:if test="${managerEvalStage.relatedQcCheckNodeId == checkNode.value}">
					         <option value='${checkNode.value}'>${checkNode.label}</option>
					       </c:if>
					    </c:forEach>
					 </select>
					</td>
				</tr>
			  </c:forEach>
			</table>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">评价指标设置：</label>
			<div class="controls">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"></label>
			<div class="controls">
				<table id="eval" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>评价类别</th>
							<th>评价指标</th>
							<th>分值</th>
						</tr>
					</thead>
					<tbody id="pingjia">
						<c:forEach items="${nowIndexList }" var="item"> 
							<tr>
								<th>
									<select name="evalRoleType" class="input-xlarge required" disabled="disabled">
										<c:forEach items="${dictList }" var="dict">
											<option value="${dict.value }" <c:if test="${item.evalRoleType == dict.value }">selected="selected"</c:if>>${dict.label }</option>
										</c:forEach>
									</select>
								</th>
								<th>
									<select name="evalIndexId" class="input-xlarge required" disabled="disabled">
										
										<c:forEach items="${bizEvalIndexList }" var="evalIndex">
											<option value="${evalIndex.id }" <c:if test="${item.evalIndexId == evalIndex.id }">selected="selected"</c:if>>${evalIndex.indexName }</option>
										</c:forEach>
									</select>
								</th>
								<th>
									<input type="text" value="${item.evalTotalScore }" disabled="disabled" name="evalTotalScore" class="required" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
								</th>
							</tr> 
						</c:forEach>  
					</tbody>
				</table>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>