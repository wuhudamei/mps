<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考核条例分类</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function clearCondition(){
			 document.getElementById("searchForm").reset();
			
			 var inputObjs=jQuery("#searchForm input[type='text']"); 
			 for(var i=0;i<inputObjs.length;i++){ 
			 var inputObj = inputObjs[i]; 
			 inputObj.value=""; 
			 } 
			
			 var selectObjs = jQuery("#searchForm input[class='input-medium']"); 
			 for(var i=0;i<selectObjs.length;i++){ 
			 var selectObj = selectObjs[i]; 
			 selectObj.value=""; 
			 } 
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
		<ul class="nav nav-tabs">
				<li class="active"><a href="#">考核条例分类</a></li>
			</ul>
			<li>
				<a href="${ctx}/bizAssessRuleType/bizAssessRuleType/openBizAssessRuleTypeForm">考核条例分类添加</a>
			</li>
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizAssessRuleType" action="${ctx}/bizAssessRuleType/bizAssessRuleType/queryBizAssessRuleType" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" onchange="getDepartments()" id="storeId">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" onchange="getDepartments()" id="storeId">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="getDepartments()" id="projectMode">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="getDepartments()" id="projectMode">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>

			<li><label>是否月度巡检：</label>
					<form:select path="isMonthInspection" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('is_month_inspection')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>

			<li><label>分类名称：</label>
				<form:input path="typeName" htmlEscape="false" class="input-medium needClear"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>序号</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>分类名称</th>
				<th>奖励惩罚类别</th>
				<th>奖励惩罚对象</th>
				<th>是否月度巡检</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizAssessRuleType" varStatus="status">
			<tr>
			    <td>${status.index +1}</td>
				<td>
					${fns:getStoreLabel(bizAssessRuleType.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizAssessRuleType.projectMode, 'project_mode','')}
				</td>
				<td>
					${bizAssessRuleType.typeName}
				</td>
				<td>
					${fns:getDictLabel(bizAssessRuleType.isRewardOrPunish, 'is_reward_or_punish','')}
				</td>
				<td>
					${fns:getDictLabel(bizAssessRuleType.rewardPunishTargetType, 'reward_punish_target_type','')}
				</td>
				<td>
						${fns:getDictLabel(bizAssessRuleType.isMonthInspection, 'is_month_inspection','')}
				</td>
				<td>
					${bizAssessRuleType.isEnabled==1?'启用':'停用'}
				</td>						
				<td>
					<c:if test="${bizAssessRuleType.isEnabled==0}">
    					<a href="${ctx}/bizAssessRuleType/bizAssessRuleType/enable?id=${bizAssessRuleType.id}&isEnabled=1">启用</a>
    				</c:if>
    				<c:if test="${bizAssessRuleType.isEnabled==1}">
    					<a href="${ctx}/bizAssessRuleType/bizAssessRuleType/enable?id=${bizAssessRuleType.id}&isEnabled=0">停用</a>
    				</c:if>
    				<a href="${ctx}/bizAssessRuleType/bizAssessRuleType/openBizAssessRuleTypeForm?id=${bizAssessRuleType.id}">修改</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>