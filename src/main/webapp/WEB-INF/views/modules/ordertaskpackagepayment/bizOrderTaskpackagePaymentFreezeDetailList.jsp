 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>付款单冻结/解冻详情</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/paymentFreezeList">付款单冻结/解冻</a></li>
		<li class="active"><a
			href="#">付款单冻结/解冻详情</a></li>
	</ul>

	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>操作时间</th>
				<th>操作人</th>
				<th>冻结/解冻说明</th>
				<th>付款单编号</th>
				<th>任务包名称</th>
				<th>客户信息</th>
				<th>工人组长</th>
				<th>付款单类型</th>
				<th>付款单金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="paymentFrrrze">
				<tr>
					<td><fmt:formatDate value="${paymentFrrrze.operatoTime}" pattern="yyyy-MM-dd"/></td>
					<td>${paymentFrrrze.operator}</td>
					<td>${paymentFrrrze.frozenRemarks}</td>
					<td>${paymentFrrrze.bizOrderTaskpackagePaymentCode}</td>
					<td>${paymentFrrrze.bizOrderTaskpackageName}</td>
					<td>${paymentFrrrze.customerMessage}-${paymentFrrrze.customerName}</td>
					<td>${paymentFrrrze.groupName}</td>
					<td><c:if test="${paymentFrrrze.orderTaskpackagePaymentType eq '0'}">首款</c:if>
						<c:if test="${paymentFrrrze.orderTaskpackagePaymentType eq '1'}">尾款</c:if>
					</td>
					<td>${paymentFrrrze.amount}</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>