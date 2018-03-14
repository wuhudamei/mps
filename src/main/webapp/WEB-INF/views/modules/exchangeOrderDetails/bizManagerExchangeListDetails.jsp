<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>被换详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
	
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
	    return false;
	    };
	</script>
</head>
<body>

	<form:form id="searchForm" action="${ctx}/exchangeOrderDetails/exchangeOrderDetails/managerDetails" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="id" name="id" value="${id }" type="hidden">
	</form:form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>被换时间</th>
				<th>客户</th>
				<th>原项目经理</th>
				<th>新项目经理</th>
				<th>原因</th>
				<th>说明</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="details">
			<tr>
				<td>
					<fmt:formatDate value="${details.exChangeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					
				</td>
				<td>
					${details.add}
				</td>
				<td>
					${details.oldLeaderName}
				</td>
				<td>
					${details.newLeaderName}
				</td>
				<td>
                    ${fns:getDictLabel(details.reasonType, 're_dispatch_cause', '')}
				</td>
				<td>
					${details.reasonRemarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%--<div class="pagination">${page}</div>--%>
	<div>
		<a class="btn"  style="text-align: center;" href="javascript:" onclick="goToHistory()">返回</a>
	</div>
</body>
</html>