<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工人任务包付款明细</title>
<meta name="decorator" content="default" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript">
	$(document).ready(function() {
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">工人任务包付款明细</a></li>
	</ul>
	<ul class="ul-form breadcrumb">
		<li class="btns"><input class="btn" type="button" value="返回"
			onclick="history.go(-1)" /></li>
	</ul>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="nav nav-tabs">
					<h2>
						<center>工人任务包付款明细</center>
					</h2>
				</div>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div>
					<h3>
						<center>基本信息</center>
					</h3>
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td width="50%">订单编号：${order2.orderNumber}</td>
						<td width="50%">小区名称：${order2.communityName}-${order2.buildNumber}-${order2.buildUnit}-${order2.buildRoom}</td>
					</tr>
					<tr>
						<td width="50%">客户：${order2.customerName}</td>
						<td width="50%">项目经理：${order2.itemManager}</td>
					</tr>
					<tr>
						<td width="50%">设计师：${order2.designerName}</td>
						<td width="50%">质检员：${order2.orderInspector}</td>
					</tr>

					<tr>
						<td width="50%">户型：${fns:getDictLabel(order2.houseType, 'home_type', '')}</td>
						<td width="50%">计价面积：${order2.contractArea}㎡</td>
					</tr>
				</table>
			</div>
			<div class="col-md-12 column">
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>任务包编号</th>
							<th>任务包名称</th>
							<th>结算单编号</th>
							<th>付款单编号</th>
							<th>工人</th>
							<th>付款类型</th>
							<th>付款单状态</th>
							<th>结算金额</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${paymentList}" var="item">
						<tr>
							<td>${item.packageCode}</td>

							<td>${item.packageName}</td>

							<td>${item.settlementNo}</td>

							<td>${item.orderTaskpackagePaymentCode}</td>

							<td>${item.groupRealname}</td>

							<td><c:if test="${item.orderTaskpackagePaymentType == 0}">首款</c:if>
							    <c:if test="${item.orderTaskpackagePaymentType == 1}">尾款</c:if>
							</td>

							<td>${fns:getDictLabel(item.status, 'payment_status', '')}</td>

							<td>${item.amount}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>