<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>员工身份证关联管理</title>
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
		<li class="active"><a href="${ctx}/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard/">员工身份证关联列表</a></li>
		<shiro:hasPermission name="employeebankcardrelatedidcard:bizEmployeeBankcardRelatedIdcard:edit"><li><a href="${ctx}/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard/form">员工身份证关联添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEmployeeBankcardRelatedIdcard" action="${ctx}/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard/" method="post" class="breadcrumb form-search">
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
				<th>备注 -- '</th>
				<th>更新日期时间 -- '</th>
				<shiro:hasPermission name="employeebankcardrelatedidcard:bizEmployeeBankcardRelatedIdcard:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEmployeeBankcardRelatedIdcard">
			<tr>
				<td><a href="${ctx}/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard/form?id=${bizEmployeeBankcardRelatedIdcard.id}">
					${bizEmployeeBankcardRelatedIdcard.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${bizEmployeeBankcardRelatedIdcard.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="employeebankcardrelatedidcard:bizEmployeeBankcardRelatedIdcard:edit"><td>
    				<a href="${ctx}/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard/form?id=${bizEmployeeBankcardRelatedIdcard.id}">修改</a>
					<a href="${ctx}/employeebankcardrelatedidcard/bizEmployeeBankcardRelatedIdcard/delete?id=${bizEmployeeBankcardRelatedIdcard.id}" onclick="return confirmx('确认要删除该员工身份证关联吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>