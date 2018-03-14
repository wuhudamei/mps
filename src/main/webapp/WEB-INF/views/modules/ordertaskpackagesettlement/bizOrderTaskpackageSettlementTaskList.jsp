<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>超定额任务包</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settlementTaskLoadList">超定额任务包列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackageSettlementVo" action="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settlementTaskLoadList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeid" class="input-medium" disabled="true" id="storeid" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeid" class="input-medium" id="storeid" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemCustomer" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>工人组长：</label>
				<form:input path="groupName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>订单编号</th>
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>任务包状态</th>
				<th>小区名称</th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>工人组长</th>
				<th>实际开工日期</th>
				<th>实际完工日期</th>
				<th>质检员复核时间</th>
				<shiro:hasPermission name="ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderTaskpackageSettlementVo">
			<tr>
				<td>${fns:getStoreLabel(bizOrderTaskpackageSettlementVo.storeid, '')}</td>
				<td>${fns:getDictLabel(bizOrderTaskpackageSettlementVo.projectMode, 'package_project_mode', '')}</td>
				<td>${bizOrderTaskpackageSettlementVo.orderNo}</td>
				<td>${bizOrderTaskpackageSettlementVo.taskPackageNo}</td>
				<td>${bizOrderTaskpackageSettlementVo.orderTaskpackageName}</td>
				<td>${bizOrderTaskpackageSettlementVo.packageStatename}</td>
				<td>${bizOrderTaskpackageSettlementVo.communityName}-${bizOrderTaskpackageSettlementVo.buildNumber}-${bizOrderTaskpackageSettlementVo.buildUnit}-${bizOrderTaskpackageSettlementVo.buildRoom}</td>
				<td>${bizOrderTaskpackageSettlementVo.customerName}	</td>
				<td>${bizOrderTaskpackageSettlementVo.itemCustomer}	</td>
				<td>${bizOrderTaskpackageSettlementVo.groupName}</td>
				<td><fmt:formatDate value="${bizOrderTaskpackageSettlementVo.actualStartdate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${bizOrderTaskpackageSettlementVo.actualEnddate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${bizOrderTaskpackageSettlementVo.recheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<shiro:hasPermission name="ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view"><td>
					<a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/details?id=${bizOrderTaskpackageSettlementVo.id}">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>