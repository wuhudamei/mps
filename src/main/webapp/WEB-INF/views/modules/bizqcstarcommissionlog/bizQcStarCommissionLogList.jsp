<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检员星级提成记录管理</title>
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
		<li class="active"><a href="${ctx}/bizqcstarcommissionlog/bizQcStarCommissionLog/">质检员星级提成记录列表</a></li>
		<shiro:hasPermission name="bizqcstarcommissionlog:bizQcStarCommissionLog:edit"><li><a href="${ctx}/bizqcstarcommissionlog/bizQcStarCommissionLog/form">质检员星级提成记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizQcStarCommissionLog" action="${ctx}/bizqcstarcommissionlog/bizQcStarCommissionLog/" method="post" class="breadcrumb form-search">
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
				<th>&plusmn;&cedil;&times;&cent; -- '</th>
				<th>&cedil;&uuml;&ETH;&Acirc;&Egrave;&Otilde;&AElig;&Uacute;&Ecirc;&plusmn;&frac14;&auml; -- '</th>
				<shiro:hasPermission name="bizqcstarcommissionlog:bizQcStarCommissionLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizQcStarCommissionLog">
			<tr>
				<td><a href="${ctx}/bizqcstarcommissionlog/bizQcStarCommissionLog/form?id=${bizQcStarCommissionLog.id}">
					${bizQcStarCommissionLog.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${bizQcStarCommissionLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bizqcstarcommissionlog:bizQcStarCommissionLog:edit"><td>
    				<a href="${ctx}/bizqcstarcommissionlog/bizQcStarCommissionLog/form?id=${bizQcStarCommissionLog.id}">修改</a>
					<a href="${ctx}/bizqcstarcommissionlog/bizQcStarCommissionLog/delete?id=${bizQcStarCommissionLog.id}" onclick="return confirmx('确认要删除该质检员星级提成记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>