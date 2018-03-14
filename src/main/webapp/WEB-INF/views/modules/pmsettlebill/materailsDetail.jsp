<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>标化辅材领取详情</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">标化辅材明细</a></li>
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
						<center>标化材料明细</center>
					</h2>
				</div>
			</div>
		</div>

		<div class="row clearfix">
			<div class="col-md-12 column">
				<div>
					<h3>
						<center>标化材料明细</center>
					</h3>
					标化材料扣款金额：<input type="text" value="${details3.receiveBillAmount}"
						readonly="readonly" />
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>申请单号</th>
							<th>物料类别</th>
							<th>物料名称</th>
							<th>单价</th>
							<th>领取数量</th>
							<th>总价</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${materialsStandardList}" var="item">
							<tr>
								<td>${item.materialsStandardReceiveBillCode}</td>
								<td>${item.materialsType}</td>
								<td>${item.materialsName}</td>
								<td>${item.materialsPrice}</td>
								<td>${item.receiveNumberTotal}</td>
								<td>${item.materialsAmount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>


	</div>


</body>
</html>