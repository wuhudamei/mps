<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>竣工结算单明细</title>
<meta name="decorator" content="default" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript">
	$(document).ready(function() {
		initMoney();
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	function initMoney(){
		var contractAmount = "${settleBill.contractAmount}";
		var pmQcFine = "${settleBill.midwayQcCheckPunishAmount}";
		var completeGuaranteeMoneyAmount = "${settleBill.completeGuaranteeMoneyAmount}";
		var completeLongwayCommissionAmount = "${settleBill.completeLongwayCommissionAmount}";
		var rewardAmount="${settleBill.rewardAmount}";
		var punishAmount="${settleBill.punishAmount}";
		var orderChangeAddAmount = "${settleBill.orderChangeAddAmount}";
		var orderChangeReduceAmount = "${settleBill.orderChangeReduceAmount}";
		var midwayContractSettleAmount  = "${midwaySettleBill.contractSettleAmount}";
        var midwayBasicworkAddAmount = "${midwaySettleBill.midwayBasicworkAddAmount}";
        var midwayMaterialsAuxiliaryAmount = "${midwaySettleBill.midwayMaterialsAuxiliaryAmount}";
        var midwaySandCementAmount = "${midwaySettleBill.midwaySandCementAmount}";
        var midwayMaterialsStandardAmount = "${midwaySettleBill.midwayMaterialsStandardAmount}";
        var midwaySwitchPanelAmount = "${midwaySettleBill.midwaySwitchPanelAmount}";
        var midwayWorkerSalaryAmount = "${midwaySettleBill.midwayWorkerSalaryAmount}";
        var settleRule = "${settleBill.midwayContractSettleRate}";

        var contractTotal = parseFloat(contractAmount)+parseFloat(midwayBasicworkAddAmount)
            +parseFloat(midwayMaterialsAuxiliaryAmount)+parseFloat(midwaySandCementAmount)+parseFloat(midwayMaterialsStandardAmount)
            +parseFloat(midwaySwitchPanelAmount)+parseFloat(midwayWorkerSalaryAmount);
        var contractSettleTotal = (contractTotal*settleRule)/100;
        var completeSettleTotal = contractSettleTotal+parseFloat(completeLongwayCommissionAmount)+parseFloat(rewardAmount)
            +parseFloat(orderChangeAddAmount)+parseFloat(pmQcFine)+parseFloat(completeGuaranteeMoneyAmount)
            +parseFloat(punishAmount)+parseFloat(orderChangeReduceAmount);


        $("#contractTotal").text(parseFloat(contractTotal).toFixed(2));
		$("#contractSettleTotal").text(contractSettleTotal.toFixed(2));
		$("#contractSettleAmount").val(contractSettleTotal.toFixed(2));
		$("#completeSettleTotal").text(completeSettleTotal.toFixed(2));
		$("#realSettleAmount").val(completeSettleTotal.toFixed(2));
	}
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">竣工结算单明细</a></li>
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
						<center>竣工结算单明细</center>
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
						<center>结算汇总</center>
					</h3>
				</div>
				<form id="inputSubmit"
					action="${ctx}/proIndustryPmSettle/proIndustryPmSettle/createCompleteSettleBille">
					<input type="hidden" name="orderId" id="orderId" value="${order2.id}" />
					<input type="hidden" name="settleBillType" id="settleBillType" value="2" />
					<input type="hidden" name="pmEmployeeId" id="pmEmployeeId" value="${order2.itemManagerId}"/>
					
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>结算类目</th>
								<th>金额（元）</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>承包总价</td>
								<td>+${settleBill.contractAmount}</td>
								<td><a
									href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openContractTotal?orderId=${order2.id}&settleStage=20">查看</a></td>
							</tr>

							<tr>
								<td>竣工质检罚款</td>
								<td>${settleBill.midwayQcCheckPunishAmount}</td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmQcFineInfo?orderId=${order2.id}&settleBillId=${settleBill.id}">查看</a></td>
							</tr>
							
							<tr>
								<td>质保金</td>
								<td>${settleBill.completeGuaranteeMoneyAmount}</td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmGuaranteeMoney?orderId=${order2.id}">查看</a></td>
							</tr>

                            <tr>
								<td>远程费</td>
								<td>+${settleBill.completeLongwayCommissionAmount}</td>
								<td><a
									href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openCommissionLog?orderId=${order2.id}&longwayCommissionEmployeeId=${order2.itemManagerId}&longwayCommissionType=10">查看</a></td>
							</tr>

							<tr>
								<td>竣工奖励</td>
								<td>+${settleBill.rewardAmount}</td>
								<td><a
									href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openPmRewardPunish?orderId=${order2.id}&rewardPunishType=1&relatedBusinessType=2">查看</a></td>
							</tr>

							<tr>
								<td>竣工扣款</td>
								<td>${settleBill.punishAmount}</td>
								<td><a
									href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openPmRewardPunish?orderId=${order2.id}&rewardPunishType=2&relatedBusinessType=2">查看</a></td>
							</tr>

							<tr>
								<td>竣工变更增项</td>
								<td>+${settleBill.orderChangeAddAmount}</td>
								<td><a
									href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openBaseInstalled?orderId=${order2.id}&changeType=40">查看</a></td>
							</tr>

							<tr>
								<td>竣工变更减项</td>
								<td>${settleBill.orderChangeReduceAmount}</td>
								<td><a
									href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openBaseInstalled?orderId=${order2.id}&changeType=50">查看</a></td>
							</tr>

							<tr>
								<td>竣工承包价结算金额</td>
								<td colspan="2"><span id="contractTotal"></span> * ${settleBill.midwayContractSettleRate}%</span> = +<span
									id="contractSettleTotal"></span></td>
								<input type="hidden" name="contractSettleAmount" id="contractSettleAmount"/>
							</tr>

							<tr>
								<td>竣工实际结算金额</td>
								<td colspan="2">+<span id="completeSettleTotal"></span></td>
								<input type="hidden" name="realSettleAmount" id="realSettleAmount"/>
							</tr>

							<tr>
								<td colspan="3"><label>结算说明：<br />
									竣工承包价结算金额 = 【承包总价 + 基装增项 - 辅料 - 沙子水泥 - 标化材料 - 开关面板 - 工人人工费 】* ${settleBill.midwayContractSettleRate}%
                                 <br />
									竣工实发结算金额 = 竣工承包价结算金额 + 远程费 + 竣工变更增项 + 竣工奖励 - 竣工质检罚款 - 竣工变更减项  - 竣工扣款 - 质保金
								</label></td>
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