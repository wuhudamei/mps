<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>约检问题管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            findInfo();
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
		<li class="active"><a href="${ctx}/bizbusinessproblemsynquery/bizBusinessProblemQuery/list">返回列表页</a></li>

	</ul>
	<form:form id="searchForm" modelAttribute="bizBusinessProblemQuery" action="${ctx}/bizbusinessproblemquery/bizBusinessProblemQuery/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">



			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>质检员</th>
				<th>约检节点</th>
				<th>约检日期</th>
				<th>上报日期</th>
				<th>问题类型</th>


			</tr>
		</thead>
		<tbody>
		<c:forEach items="${mapList}" var="bizBusinessSynProblemQuery">
			<tr>
				<td>${fns:getStoreLabel(bizBusinessSynProblemQuery.storeId, '')}</td>
				<td>${fns:getDictLabel(bizBusinessSynProblemQuery.projectMode,'project_mode' , '')}</td>
				<td>${bizBusinessSynProblemQuery.orderNumber}</td>
				<td>${bizBusinessSynProblemQuery.customerName}</td>
				<td>${bizBusinessSynProblemQuery.customerInfo}</td>
				<td>${bizBusinessSynProblemQuery.managerName}</td>
				<td>${bizBusinessSynProblemQuery.pqcName}</td>
				<td>${bizBusinessSynProblemQuery.qcCheckNodeName}</td>
				<td>${bizBusinessSynProblemQuery.qcExpectCheckDate}</td>
				<td>${bizBusinessSynProblemQuery.problemCreateDate}</td>
				<td>${bizBusinessSynProblemQuery.typeName}</td>


			</tr>
		</c:forEach>
		</tbody>
	</table>

</body>
</html>