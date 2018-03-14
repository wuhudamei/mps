<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>结算单下的付款单信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:goToHistory()">返回</a></li>
	</ul>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>付款单编号</th>
				<th>结算单编号</th>
				<th>任务包名称</th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>工人组长</th>
				<th>付款单状态</th>
				<th>付款类型</th>
				<th>付款单金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="payment">
				<tr>
					<td>${payment.orderTaskpackagePaymentCode}</td>
					<td>${payment.settlementNo}</td>
					<td>${payment.packageName}</td>
					<td>${payment.customerMessage}-${payment.customerName}</td>
					<td>${payment.itemCustomer}</td>
					<td>${payment.groupRealname}</td>
					<td>${fns:getDictLabel(payment.status, 'payment_status', '')}</td>
					<td><c:if test="${payment.orderTaskpackagePaymentType eq '0'}">首款</c:if>
						<c:if test="${payment.orderTaskpackagePaymentType eq '1'}">尾款</c:if>
					</td>
					<td>${payment.amount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>