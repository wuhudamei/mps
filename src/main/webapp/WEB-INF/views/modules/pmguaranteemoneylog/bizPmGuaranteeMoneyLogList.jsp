<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质保金日志管理</title>
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
		<li class="active"><a href="${ctx}/pmguaranteemoneylog/bizPmGuaranteeMoneyLog/">质保金日志列表</a></li>
		<shiro:hasPermission name="pmguaranteemoneylog:bizPmGuaranteeMoneyLog:edit"><li><a href="${ctx}/pmguaranteemoneylog/bizPmGuaranteeMoneyLog/form">质保金日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmGuaranteeMoneyLog" action="${ctx}/pmguaranteemoneylog/bizPmGuaranteeMoneyLog/" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="pmguaranteemoneylog:bizPmGuaranteeMoneyLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmGuaranteeMoneyLog">
			<tr>
				<td><a href="${ctx}/pmguaranteemoneylog/bizPmGuaranteeMoneyLog/form?id=${bizPmGuaranteeMoneyLog.id}">
					${bizPmGuaranteeMoneyLog.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${bizPmGuaranteeMoneyLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pmguaranteemoneylog:bizPmGuaranteeMoneyLog:edit"><td>
    				<a href="${ctx}/pmguaranteemoneylog/bizPmGuaranteeMoneyLog/form?id=${bizPmGuaranteeMoneyLog.id}">修改</a>
					<a href="${ctx}/pmguaranteemoneylog/bizPmGuaranteeMoneyLog/delete?id=${bizPmGuaranteeMoneyLog.id}" onclick="return confirmx('确认要删除该质保金日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>