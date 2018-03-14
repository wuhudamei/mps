<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程事项和工程问题对照表管理</title>
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
		<li class="active"><a href="${ctx}/ordercomplan/bizOrderComplaintProblemItem/">工程事项和工程问题对照表列表</a></li>
		<shiro:hasPermission name="ordercomplan:bizOrderComplaintProblemItem:edit"><li><a href="${ctx}/ordercomplan/bizOrderComplaintProblemItem/form">工程事项和工程问题对照表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderComplaintProblemItem" action="${ctx}/ordercomplan/bizOrderComplaintProblemItem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>工程问题：</label>
				<form:input path="orderComplaintProblemId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>问题事项ID：</label>
				<form:input path="complaintProblemItemId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>工程问题</th>
				<th>问题事项ID</th>
				<th>remarks</th>
				<th>update_date</th>
				<shiro:hasPermission name="ordercomplan:bizOrderComplaintProblemItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderComplaintProblemItem">
			<tr>
				<td><a href="${ctx}/ordercomplan/bizOrderComplaintProblemItem/form?id=${bizOrderComplaintProblemItem.id}">
					${bizOrderComplaintProblemItem.id}
				</a></td>
				<td>
					${bizOrderComplaintProblemItem.orderComplaintProblemId}
				</td>
				<td>
					${bizOrderComplaintProblemItem.complaintProblemItemId}
				</td>
				<td>
					${bizOrderComplaintProblemItem.remarks}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderComplaintProblemItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ordercomplan:bizOrderComplaintProblemItem:edit"><td>
    				<a href="${ctx}/ordercomplan/bizOrderComplaintProblemItem/form?id=${bizOrderComplaintProblemItem.id}">修改</a>
					<a href="${ctx}/ordercomplan/bizOrderComplaintProblemItem/delete?id=${bizOrderComplaintProblemItem.id}" onclick="return confirmx('确认要删除该工程事项和工程问题对照表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>