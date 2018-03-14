<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>已删除员工</title>
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
		<li class="active"><a href="${ctx}/employee/bizEmployee2/deleteEmployeeLoadList">已删除员工</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEmployee2" action="${ctx}/employee/bizEmployee2/deleteEmployeeLoadList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeid" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>员工编号：</label>
				<form:input path="no" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="realname" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
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
				<th>员工编号</th>
				<th>用户名</th>
				<th>真实姓名</th>
				<th>手机号</th>
				<th>员工类型</th>
				<th>操作人</th>
				<th>删除日期时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEmployee">
			<tr>
				<td>${fns:getStoreLabel(bizEmployee.storeid, '')}</td>
				<td>${bizEmployee.no}</td>
				<td>${bizEmployee.loginname}</td>
				<td>${bizEmployee.realname}</td>
				<td>${bizEmployee.phone}</td>
				<td>${fns:getDictLabel(bizEmployee.empType, 'emp_type', '')}</td>
				<td>${bizEmployee.name}</td>
				<td><fmt:formatDate value="${bizEmployee.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<shiro:hasPermission name="employee:bizEmployee2:edit"><td>
					<a href="${ctx}/employee/bizEmployee2/reduction?id=${bizEmployee.id}" onclick="return confirmx('确认要还原该员工信息吗？', this.href)">还原</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>