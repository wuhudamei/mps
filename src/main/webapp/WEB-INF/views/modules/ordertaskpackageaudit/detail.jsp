<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>详情</title>
<meta name="decorator" content="default" />
<meta content="*" name="Access-Control-Allow-Origin">
<!--跨域请求  -->
<style>
.none {
	display: none;
}

.order-total {
	width: 100%;
	font-size: 12px;
	color: #5a5858;
}

.order-row {
	border: 1px solid #ccc;
	display: flex;
	border-top: 0;
	line-height: 2;
}

.order-row.row-title {
	border-width: 0 0 1px 0;
	text-align: center;
}

.order-item {
	flex: 1;
	display: inline-block;
}

.order-total .item-price {
	font-weight: bold;
	color: #3b3939;
	font-size: 14px;
}

.order-total .item-price._total {
	font-size: 18px;
	color: #d80101;
}
</style>
<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	    <div class="order-total">
		<li><a href="">任务包详情</a></li>
		<li><label
			style="width: 300px; margin-left: 40px; margin-top: 7px;">
				${orderTaskpackage.packageName}工料费预算总金额：<span
				class="item-price _total">${orderTaskpackage.total}</span> 元
		</label></li>
		</div>
	</ul>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务包名称</th>
				<th>工序编号</th>
				<th>工序名称</th>
				<th>预算数量</th>
				<th>单位</th>
				<th>人工结算价</th>
				<th>辅料结算价</th>
				<th>工料结算价</th>
				<th>人工费预算金额</th>
				<th>辅料费预算金额</th>
				<th>工料费预算金额</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="procedure" varStatus="status">
				<tr>
					<td>${procedure.packageName}</td>
					<td>${procedure.procedureNo}</td>
					<td>${procedure.procedureName}</td>
					<td>${procedure.budgetNumber }</td>
					<td>${procedure.measurementUnit}</td>
					<td>${procedure.laborPrice}</td>
					<td>${procedure.accessoriesPrice}</td>
					<td>${procedure.synthesizePrice }</td>
					<td>${procedure.laborBudgetAmount }</td>
					<td>${procedure.auxiliaryMaterialsBudgetAmount }</td>
					<td>${procedure.laborAuxiliaryMaterialsBudgetAmount }</td>
					<td>${procedure.remarks}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="errorMessage">
		<a class="btn" href="javascript:" onclick="history.go(-1);">返回上一页</a>
	</div>
</body>
</html>