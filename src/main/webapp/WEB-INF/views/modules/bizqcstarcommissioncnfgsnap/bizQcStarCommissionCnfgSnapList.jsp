<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检员星级提成快照管理</title>
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
		<li class="active"><a href="${ctx}/bizqcstarcommissioncnfgsnap/bizQcStarCommissionCnfgSnap/">质检员星级提成快照列表</a></li>
		<shiro:hasPermission name="bizqcstarcommissioncnfgsnap:bizQcStarCommissionCnfgSnap:edit"><li><a href="${ctx}/bizqcstarcommissioncnfgsnap/bizQcStarCommissionCnfgSnap/form">质检员星级提成快照添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizQcStarCommissionCnfgSnap" action="${ctx}/bizqcstarcommissioncnfgsnap/bizQcStarCommissionCnfgSnap/" method="post" class="breadcrumb form-search">
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
				<th>&aring;&curren;&Dagger;&aelig;&sup3;&uml; -- '</th>
				<th>&aelig;&rsaquo;&acute;&aelig;&ndash;&deg;&aelig;&mdash;&yen;&aelig;&oelig;&Yuml;&aelig;&mdash;&para;&eacute;&mdash;&acute; -- '</th>
				<shiro:hasPermission name="bizqcstarcommissioncnfgsnap:bizQcStarCommissionCnfgSnap:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizQcStarCommissionCnfgSnap">
			<tr>
				<td><a href="${ctx}/bizqcstarcommissioncnfgsnap/bizQcStarCommissionCnfgSnap/form?id=${bizQcStarCommissionCnfgSnap.id}">
					${bizQcStarCommissionCnfgSnap.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${bizQcStarCommissionCnfgSnap.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bizqcstarcommissioncnfgsnap:bizQcStarCommissionCnfgSnap:edit"><td>
    				<a href="${ctx}/bizqcstarcommissioncnfgsnap/bizQcStarCommissionCnfgSnap/form?id=${bizQcStarCommissionCnfgSnap.id}">修改</a>
					<a href="${ctx}/bizqcstarcommissioncnfgsnap/bizQcStarCommissionCnfgSnap/delete?id=${bizQcStarCommissionCnfgSnap.id}" onclick="return confirmx('确认要删除该质检员星级提成快照吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>