<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程问题管理</title>
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
		<li class="active"><a href="${ctx}/ordercomplan/bizOrderComplaintProblem/">工程问题列表</a></li>
		<shiro:hasPermission name="ordercomplan:bizOrderComplaintProblem:edit"><li><a href="${ctx}/ordercomplan/bizOrderComplaintProblem/form">工程问题添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderComplaintProblem" action="${ctx}/ordercomplan/bizOrderComplaintProblem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>工程信息ID：</label>
				<form:input path="orderComplaintId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>问题分类ID：</label>
				<form:input path="complaintProblemTypeId" htmlEscape="false" maxlength="11" class="input-medium"/>
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
				<th>工程信息ID</th>
				<th>问题分类ID</th>
				<th>任务包ID</th>
				<th>订单任务包ID</th>
				<th>被投诉对象</th>
				<th>投诉问题描述</th>
				<th>状态</th>
				<th>remarks</th>
				<th>update_date</th>
				<shiro:hasPermission name="ordercomplan:bizOrderComplaintProblem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderComplaintProblem">
			<tr>
				<td><a href="${ctx}/ordercomplan/bizOrderComplaintProblem/form?id=${bizOrderComplaintProblem.id}">
					${bizOrderComplaintProblem.id}
				</a></td>
				<td>
					${bizOrderComplaintProblem.orderComplaintId}
				</td>
				<td>
					${bizOrderComplaintProblem.complaintProblemTypeId}
				</td>
				<td>
					${bizOrderComplaintProblem.taskPackageTemplatId}
				</td>
				<td>
					${bizOrderComplaintProblem.orderTaskpackageId}
				</td>
				<td>
					${bizOrderComplaintProblem.complaintRoleType}
				</td>
				<td>
					${bizOrderComplaintProblem.complaintProblemDescribe}
				</td>
				<td>
					${bizOrderComplaintProblem.status}
				</td>
				<td>
					${bizOrderComplaintProblem.remarks}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderComplaintProblem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ordercomplan:bizOrderComplaintProblem:edit"><td>
    				<a href="${ctx}/ordercomplan/bizOrderComplaintProblem/form?id=${bizOrderComplaintProblem.id}">修改</a>
					<a href="${ctx}/ordercomplan/bizOrderComplaintProblem/delete?id=${bizOrderComplaintProblem.id}" onclick="return confirmx('确认要删除该工程问题吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>