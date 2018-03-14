<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投诉问题处理管理</title>
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
		<li class="active"><a href="${ctx}/ordercomplan/bizOrderComplaintProblemDeal/">投诉问题处理列表</a></li>
		<shiro:hasPermission name="ordercomplan:bizOrderComplaintProblemDeal:edit"><li><a href="${ctx}/ordercomplan/bizOrderComplaintProblemDeal/form">投诉问题处理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderComplaintProblemDeal" action="${ctx}/ordercomplan/bizOrderComplaintProblemDeal/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>deal_person_type：</label>
				<form:input path="dealPersonType" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>deal_employee_id：</label>
				<form:input path="dealEmployeeId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>处理表ID</th>
				<th>问题表ID</th>
				<th>deal_person_type</th>
				<th>deal_employee_id</th>
				<th>deal_status</th>
				<th>deal_status_datetime</th>
				<th>deal_describe</th>
				<th>remarks</th>
				<th>update_date</th>
				<shiro:hasPermission name="ordercomplan:bizOrderComplaintProblemDeal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderComplaintProblemDeal">
			<tr>
				<td><a href="${ctx}/ordercomplan/bizOrderComplaintProblemDeal/form?id=${bizOrderComplaintProblemDeal.id}">
					${bizOrderComplaintProblemDeal.id}
				</a></td>
				<td>
					${bizOrderComplaintProblemDeal.orderComplaintProblemId}
				</td>
				<td>
					${bizOrderComplaintProblemDeal.dealPersonType}
				</td>
				<td>
					${bizOrderComplaintProblemDeal.dealEmployeeId}
				</td>
				<td>
					${bizOrderComplaintProblemDeal.dealStatus}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderComplaintProblemDeal.dealStatusDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizOrderComplaintProblemDeal.dealDescribe}
				</td>
				<td>
					${bizOrderComplaintProblemDeal.remarks}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderComplaintProblemDeal.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ordercomplan:bizOrderComplaintProblemDeal:edit"><td>
    				<a href="${ctx}/ordercomplan/bizOrderComplaintProblemDeal/form?id=${bizOrderComplaintProblemDeal.id}">修改</a>
					<a href="${ctx}/ordercomplan/bizOrderComplaintProblemDeal/delete?id=${bizOrderComplaintProblemDeal.id}" onclick="return confirmx('确认要删除该投诉问题处理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>