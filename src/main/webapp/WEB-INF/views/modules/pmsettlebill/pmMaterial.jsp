<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目经理中期提成</title>
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
		<li class="active"><a href="javascript:void(0)">任务包材料结算明细</a></li>
	</ul>
	<ul class="ul-form breadcrumb">
		<li class="btns"><input class="btn" type="button" value="返回"
			onclick="history.go(-1)" /></li>
	</ul>
	<div class="container">
		
		<div class="row clearfix">

			<div class="col-md-12 column">
				<div>
					<h3>
						<center>任务包材料结算明细</center>
					</h3>
					<c:if test="${settleCategory == '121'}">
					中期任务包材料结算总金额：
					</c:if>
					<c:if test="${settleCategory == '122'}">
					竣工任务包材料结算总金额：
					</c:if>
					<input type="text" value="${pmMaterialsSettleAmount}"
						readonly="readonly" />
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>任务包编号</th>
							<th>任务包名称</th>
							<th>辅料费结算总金额</th>
							<th>辅料扣款金额</th>
							<th>沙子水泥扣款金额</th>
							<th>项目经理材料结算金额</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pmMaterials}" var="pmMaterial">
							<tr>
								<td>${pmMaterial.taskPackageNo}</td>
								<td>${pmMaterial.taskPackageName}</td>
								<td>${pmMaterial.auxiliaryMaterialsSettleAmount}</td>
								<td>${pmMaterial.auxiliaryMaterialsAmount}</td>
								<td>${pmMaterial.sandCementAmount}</td>
								<td>${pmMaterial.pmMaterialsSettleAmount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>