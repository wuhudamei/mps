<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>辅料用量扣款</title>
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
		<li class="active"><a href="javascript:void(0)">辅料用量扣款</a></li>
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
						<center>辅料用量扣款</center>
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
				<div>
					<h3>
						<center>辅料用量扣款清单</center>
					</h3>
					辅料扣款金额：<input type="text" value="${auMaterAmount}"
						readonly="readonly" />
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>序号</th>
							<th>辅料商品编号</th>
							<th>辅料商品名称</th>
							<th>单位</th>
							<th>结算价</th>
							<th>使用数量</th>
							<th>总价</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${auxiliaryList}" var="auxiliary"
							varStatus="status">
						<tr>
							<td>${status.index +1}</td>
							<td>${auxiliary.auxiliaryMaterialsNo}</td>
							<td>${auxiliary.auxiliaryMaterialsName}</td>
							<td>${fns:getDictLabel(auxiliary.measurementUnit, 'biz_material_unit', '')}</td>
							<td>${auxiliary.laborPrice}</td>
							<td>${auxiliary.usedCount}</td>
                            <td>${auxiliary.price}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>