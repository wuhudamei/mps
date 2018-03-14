<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人管理</title>
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
		<li class="active"><a href="${ctx}/employee/bizEmployee2/loadList">工人信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEmployee2" action="${ctx}/employee/bizEmployee2/loadList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeid" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工人：</label>
                <form:input path="realname" htmlEscape="false" class="input-medium needClear"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" class="input-medium needClear"/>
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
				<th>工人组长姓名</th>
				<th>工人手机号</th>
				<th>星级</th>
				<th>工种</th>
				<th>当前质保金总额</th>
				<shiro:hasPermission name="employee:bizEmployee2:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEmployee2">
			<tr>
				<td>${fns:getStoreLabel(bizEmployee2.storeid, '')}</td>
				<td>${bizEmployee2.no}</td>
				<td>${bizEmployee2.realname}</td>
				<td>${bizEmployee2.phone}</td>
				<td>${fns:getDictLabel(bizEmployee2.star, 'emp_star', '')}</td>
				<td>${fns:getDictLabel(bizEmployee2.worktype, 'emp_work_type', '')}</td>
				<td>${bizEmployee2.totalAmount}</td>
				<shiro:hasPermission name="employee:bizEmployee2:view"><td>
					<a href="${ctx}/employee/bizEmployee2/guaranteeMoneyDetail?id=${bizEmployee2.id}">质保金明细</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>