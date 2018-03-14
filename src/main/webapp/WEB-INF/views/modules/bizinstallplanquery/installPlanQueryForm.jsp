<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安装项计划详情</title>
	<meta name="decorator" content="default"/>
	
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizinstallplanquery/installPlanQuery/list2"></a></li>
	</ul><br/>
	<div>
		<a class="btn" href="javascript:" onclick="goToHistory()">返回</a>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>生成安装计划日期</th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>实际开工日期</th>
				<th>安装项顺序</th>
				<th>安装项名称</th>
				<th>计划安装日期</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="installPlanQuery" varStatus="status">
			<tr>
			<td>${status.index+1}</td>
				<td>	<fmt:formatDate value="${installPlanQuery.generateInstallPlanTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>${installPlanQuery.communityName }-${installPlanQuery.buildNumber }-${installPlanQuery.buildUnit }-${installPlanQuery.buildRoom}-${installPlanQuery.customerName }</td>		
				<td>${installPlanQuery.managerName}</td>
				<td><fmt:formatDate value="${installPlanQuery.actualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${installPlanQuery.installOrder}</td>
				<td>${installPlanQuery.installName}</td>
					<td><fmt:formatDate value="${installPlanQuery.planIntoDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>