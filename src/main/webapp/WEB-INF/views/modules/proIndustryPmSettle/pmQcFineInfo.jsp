<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>中期质检罚款</title>
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
		<li class="active"><a href="javascript:void(0)">中期质检罚款</a></li>
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
						<center>中期质检罚款</center>
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
						<center>中期质检罚款清单</center>
					</h3>
					中期质检罚款扣除金额：<input type="text" value="${pmQcFine}"
						readonly="readonly" />
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>质检单编号</th>
							<th>提交人</th>
							<th>提交时间</th>
							<th>检查项分类</th>
							<th>检查项</th>
							<th>违规形式</th>
							<th>罚款金额</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${inspectorPunishList}" var="inspectorPunish">
						<tr>
							<td>${inspectorPunish.qcBillCode}</td>
							<td>${inspectorPunish.checkEmployee}</td>
							<td><fmt:formatDate value="${inspectorPunish.actualCheckDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${inspectorPunish.qcBillCheckKindName}</td>
							<td>${inspectorPunish.qcBillCheckItemName}</td>
                            <td>${inspectorPunish.breakDescribe}</td>
                            <td>${inspectorPunish.money}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>