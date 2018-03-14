<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>中期结算单明细</title>
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
		<li class="active"><a href="javascript:void(0)">中期结算单明细</a></li>
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
						<center>中期结算单明细</center>
						<center>${settleBill.itemCustomer}-${settleBill.settleMonth}</center>
					</h2>
				</div>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div>
					<h3>
						<center>结算汇总</center>
					</h3>
				</div>
				<form id="inputSubmit"
					action="${ctx}/proIndustryPmSettle/proIndustryPmSettle/createMidwaySettleBille">
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>结算类目</th>
								<th>金额（元）</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>承包总价</td>
								<td>+${settleBill.contractAmount}</td>
							</tr>

							<tr>
								<td>基装增项</td>
								<td>+${settleBill.midwayBasicworkAddAmount}</td>
							</tr>

							<tr>
								<td>辅料用量扣款</td>
								<td>${settleBill.midwayMaterialsAuxiliaryAmount}</td>
							</tr>

							<tr>
								<td>沙子水泥扣款</td>
								<td>${settleBill.midwaySandCementAmount}</td>
							</tr>

							<tr>
								<td>标化材料扣款</td>
								<td>${settleBill.midwayMaterialsStandardAmount}</td>
							</tr>

							<tr>
								<td>开关面板扣款</td>
								<td>${settleBill.midwaySwitchPanelAmount}</td>
							</tr>

							<tr>
								<td>工人人工费扣款</td>
								<td>${settleBill.midwayWorkerSalaryAmount}</td>
							</tr>

							<tr>
								<td>中期质检罚款</td>
								<td>${settleBill.midwayQcCheckPunishAmount}</td>
							</tr>

							<tr>
								<td>中期奖励</td>
								<td>+${settleBill.rewardAmount}</td>
							</tr>

							<tr>
								<td>中期扣款</td>
								<td>${settleBill.punishAmount}</td>
							</tr>

							<tr>
								<td>材料搬运及运输费</td>
								<td>+${settleBill.midwayMaterialCarryCostAmount}</td>
							</tr>

							<tr>
								<td>中期变更增项</td>
								<td>+${settleBill.orderChangeAddAmount}</td>
							</tr>

							<tr>
								<td>中期变更减项</td>
								<td>${settleBill.orderChangeReduceAmount}</td>
							</tr>

							<tr>
								<td>中期承包价结算金额</td>
								<td>${settleBill.contractSettleAmount}</td>
							</tr>

							<tr>
								<td>中期实际结算金额</td>
								<td>${settleBill.realSettleAmount}</td>
							</tr>

						</tbody>
					</table>
				</form>
			</div>
			<div class="form-actions" style="text-align: center">
				<input class="btn" type="button"
					value="返 回" onclick="history.go(-1)" />
			</div>
		</div>
	</div>
</body>
</html>