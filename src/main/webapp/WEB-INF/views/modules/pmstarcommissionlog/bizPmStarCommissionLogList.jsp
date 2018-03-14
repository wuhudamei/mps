<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>biz_pm_star_commission_log管理</title>
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
		<li class="active"><a href="${ctx}/pmstarcommissionlog/bizPmStarCommissionLog/">biz_pm_star_commission_log列表</a></li>
		<shiro:hasPermission name="pmstarcommissionlog:bizPmStarCommissionLog:edit"><li><a href="${ctx}/pmstarcommissionlog/bizPmStarCommissionLog/form">biz_pm_star_commission_log添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmStarCommissionLog" action="${ctx}/pmstarcommissionlog/bizPmStarCommissionLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>remarks</th>
				<th>update_date</th>
				<shiro:hasPermission name="pmstarcommissionlog:bizPmStarCommissionLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmStarCommissionLog">
			<tr>
				<td><a href="${ctx}/pmstarcommissionlog/bizPmStarCommissionLog/form?id=${bizPmStarCommissionLog.id}">
					${bizPmStarCommissionLog.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${bizPmStarCommissionLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pmstarcommissionlog:bizPmStarCommissionLog:edit"><td>
    				<a href="${ctx}/pmstarcommissionlog/bizPmStarCommissionLog/form?id=${bizPmStarCommissionLog.id}">修改</a>
					<a href="${ctx}/pmstarcommissionlog/bizPmStarCommissionLog/delete?id=${bizPmStarCommissionLog.id}" onclick="return confirmx('确认要删除该biz_pm_star_commission_log吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>