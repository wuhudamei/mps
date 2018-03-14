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
        var jzpmQcFine= "${settleBill.midwayQcCheckPunishAmount}";

        var settleRule  = 100-parseFloat("${midwaySettleBill.midwayContractSettleRate}");

        /*-----------------------------------竣工承包价结算金额----------------------------------------------*/
        var baseInstalledAmount = "${midwaySettleBill.midwayBasicworkAddAmount}";
        var auMaterAmount = "${midwaySettleBill.midwayMaterialsAuxiliaryAmount}";
        var sandAmount = "${midwaySettleBill.midwaySandCementAmount}";
        var standardMaAmount = "${midwaySettleBill.midwayMaterialsStandardAmount}";
        var mainPanelAmount = "${midwaySettleBill.midwaySwitchPanelAmount}";
        var workerAmount = "${midwaySettleBill.midwayWorkerSalaryAmount}";
        var pmQcFine = "${midwaySettleBill.midwayQcCheckPunishAmount}";
        var midwayReward="${midwaySettleBill.rewardAmount}";
        var midwayPunish="${midwaySettleBill.punishAmount}";
        var carryCostAccountAmount="${midwaySettleBill.midwayMaterialCarryCostAmount}";
        var midwayChangeAddAmount = "${midwaySettleBill.orderChangeAddAmount}";
        var midwayChangeReductAmount = "${midwaySettleBill.orderChangeReduceAmount}";
        var midwayCompleteGuaranteeMoneyAmount = "${midwaySettleBill.completeGuaranteeMoneyAmount}";
        var contractTotal = parseFloat(contractAmount)
            +parseFloat(baseInstalledAmount)
            +parseFloat(auMaterAmount)+parseFloat(sandAmount)
            +parseFloat(standardMaAmount)
            +parseFloat(mainPanelAmount)+parseFloat(workerAmount)
            +parseFloat(pmQcFine)+parseFloat(midwayReward)
            +parseFloat(midwayPunish)+parseFloat(carryCostAccountAmount)
            +parseFloat(midwayChangeAddAmount)+parseFloat(midwayChangeReductAmount)
            +parseFloat(midwayCompleteGuaranteeMoneyAmount);


        var contractSettleTotal = (contractTotal*settleRule)/100;

//       竣工承包价结算金额
        $("#contractTotal").text(parseFloat(contractTotal).toFixed(2));

        $("#contractSettleTotal").text(contractSettleTotal.toFixed(2));
//        结算比例
        $("#settleRule1").text(settleRule);
        $("#settleRule2").text(settleRule);

        var completeSettleTotal =
            contractSettleTotal+parseFloat(completeLongwayCommissionAmount)+parseFloat(orderChangeAddAmount)
            +parseFloat(rewardAmount)+parseFloat(jzpmQcFine)
            +parseFloat(punishAmount)+parseFloat(orderChangeReduceAmount);

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
		<li class="btns"><a href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/pmPreIndustrySettleBillList" class="btn">返 回</a></li>
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
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openContractTotal?orderId=${order2.id}&settleStage=20&isEditSettleBill=1">修改</a></td>
							</tr>
                            <tr>
                                <td>基装增项</td>
                                <td>+${midwaySettleBill.midwayBasicworkAddAmount}</td>
                                <td><a
                                        href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openBaseInstalled?orderId=${order2.id}&changeType=10">查看</a></td>
                            </tr>

                            <tr>
                                <td>辅料用量扣款</td>
                                <td>${midwaySettleBill.midwayMaterialsAuxiliaryAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openAuxiliaryMaInfo?orderId=${order2.id}&isSandCement=0">查看</a></td>
                            </tr>

                            <tr>
                                <td>沙子水泥扣款</td>
                                <td>${midwaySettleBill.midwaySandCementAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openAuxiliaryMaInfo?orderId=${order2.id}&isSandCement=1">查看</a></td>
                            </tr>

                            <tr>
                                <td>标化材料扣款</td>
                                <td>${midwaySettleBill.midwayMaterialsStandardAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openMaterialsStandardInfo?orderId=${order2.id}">查看</a></td>
                            </tr>

                            <tr>
                                <td>开关面板扣款</td>
                                <td>${midwaySettleBill.midwaySwitchPanelAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openMainPanelInfo?orderId=${order2.id}">查看</a></td>
                            </tr>

                            <tr>
                                <td>工人人工费扣款</td>
                                <td>${midwaySettleBill.midwayWorkerSalaryAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/queryTaskpackPaymentInfo?orderId=${order2.id}">查看</a></td>
                            </tr>

                            <tr>
                                <td>中期质检罚款</td>
                                <td>${midwaySettleBill.midwayQcCheckPunishAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmQcFineInfo?orderId=${order2.id}&settleBillId=${midwaySettleBill.id}">查看</a></td>
                            </tr>

                            <tr>
                                <td>中期奖励</td>
                                <td>+${midwaySettleBill.rewardAmount}</td>
                                <td><a
                                        href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openPmRewardPunish?orderId=${order2.id}&rewardPunishType=1&relatedBusinessType=1">查看</a></td>
                            </tr>

                            <tr>
                                <td>中期扣款</td>
                                <td>${midwaySettleBill.punishAmount}</td>
                                <td><a
                                        href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openPmRewardPunish?orderId=${order2.id}&rewardPunishType=2&relatedBusinessType=1">查看</a></td>
                            </tr>

                            <tr>
                                <td>材料搬运及运输费</td>
                                <td>+${midwaySettleBill.midwayMaterialCarryCostAmount}</td>
                                <td><a
                                        href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openOrderMaterialCarryCost?orderId=${order2.id}">查看</a></td>
                            </tr>

                            <tr>
                                <td>中期变更增项</td>
                                <td>+${midwaySettleBill.orderChangeAddAmount}</td>
                                <td><a
                                        href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openBaseInstalled?orderId=${order2.id}&changeType=20">查看</a></td>
                            </tr>

                            <tr>
                                <td>中期变更减项</td>
                                <td>${midwaySettleBill.orderChangeReduceAmount}</td>
                                <td><a
                                        href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openBaseInstalled?orderId=${order2.id}&changeType=30">查看</a></td>
                            </tr>

                            <tr>
                                <td>质保金</td>
                                <td>${midwaySettleBill.completeGuaranteeMoneyAmount}</td>
                                <td><a href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmGuaranteeMoney?orderId=${order2.id}">查看</a></td>
                            </tr>

							<tr>
								<td>竣工质检罚款</td>
								<td>${settleBill.midwayQcCheckPunishAmount}</td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmQcFineInfo?orderId=${order2.id}&settleBillId=${settleBill.id}">查看</a></td>
							</tr>

                            <tr>
								<td>远程费</td>
								<td>+${settleBill.completeLongwayCommissionAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openCommissionLog?orderId=${order2.id}&longwayCommissionEmployeeId=${order2.itemManagerId}&longwayCommissionType=10&isEditSettleBill=1">添加</a></td>
							</tr>

							<tr>
								<td>竣工奖励</td>
								<td>+${settleBill.rewardAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmRewardPunish?orderId=${order2.id}&rewardPunishType=1&relatedBusinessType=2&isEditSettleBill=1">添加</a></td>
							</tr>

							<tr>
								<td>竣工扣款</td>
								<td>${settleBill.punishAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmRewardPunish?orderId=${order2.id}&rewardPunishType=2&relatedBusinessType=2&isEditSettleBill=1">添加</a></td>
							</tr>

							<tr>
								<td>竣工变更增项</td>
								<td>+${settleBill.orderChangeAddAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openBaseInstalled?orderId=${order2.id}&changeType=40&isEditSettleBill=1">添加</a></td>
							</tr>

							<tr>
								<td>竣工变更减项</td>
								<td>${settleBill.orderChangeReduceAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openBaseInstalled?orderId=${order2.id}&changeType=50&isEditSettleBill=1">添加</a></td>
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
                                    竣工承包价结算金额 = 【承包总价 + 基装增项  - 辅料 - 沙子水泥 - 标化材料 - 开关面板 - 工人人工费  + 中期奖励 + 材料搬运及运输费 + 中期变更增项 - 中期质检罚款 - 中期变更减项  - 中期扣款 - 质保金】* <span id="settleRule2">${settleBill.midwayContractSettleRate}</span>%
                                    <br />
                                    竣工实发结算金额 = 【竣工承包价结算金额 + 远程费 + 竣工变更增项 + 竣工奖励 - 竣工质检罚款 - 竣工变更减项  - 竣工扣款 】
                                </label></td>
                            </tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="form-actions" style="text-align: center">
                <a href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/pmPreIndustrySettleBillList" class="btn">返 回</a>
			</div>
		</div>
	</div>
</body>
</html>