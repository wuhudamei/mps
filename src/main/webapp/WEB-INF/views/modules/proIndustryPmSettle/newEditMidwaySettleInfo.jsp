<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>中期结算单明细</title>
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
		var baseInstalledAmount = "${settleBill.midwayBasicworkAddAmount}";
		var auMaterAmount = "${settleBill.midwayMaterialsAuxiliaryAmount}";
		var sandAmount = "${settleBill.midwaySandCementAmount}";
		var standardMaAmount = "${settleBill.midwayMaterialsStandardAmount}";
		var mainPanelAmount = "${settleBill.midwaySwitchPanelAmount}";
		var workerAmount = "${settleBill.midwayWorkerSalaryAmount}";
		var pmQcFine = "${settleBill.midwayQcCheckPunishAmount}";
		var midwayReward="${settleBill.rewardAmount}";
		var midwayPunish="${settleBill.punishAmount}";
		var carryCostAccountAmount="${settleBill.midwayMaterialCarryCostAmount}";
		var midwayChangeAddAmount = "${settleBill.orderChangeAddAmount}";
		var midwayChangeReductAmount = "${settleBill.orderChangeReduceAmount}";
        var midwayCompleteGuaranteeMoneyAmount = "${settleBill.completeGuaranteeMoneyAmount}";
        if(midwayCompleteGuaranteeMoneyAmount == ""){
            midwayCompleteGuaranteeMoneyAmount = 0;
        }

        var settleRule = "${settleBill.midwayContractSettleRate}";
		
		/*var contractTotal = parseFloat(contractAmount)+parseFloat(baseInstalledAmount)
		                    +parseFloat(auMaterAmount)+parseFloat(sandAmount)+parseFloat(standardMaAmount)
		                    +parseFloat(mainPanelAmount)+parseFloat(workerAmount);*/

        var contractTotal = parseFloat(contractAmount)+parseFloat(baseInstalledAmount)
            +parseFloat(auMaterAmount)+parseFloat(sandAmount)
            +parseFloat(standardMaAmount)
            +parseFloat(mainPanelAmount)+parseFloat(workerAmount)
            +parseFloat(pmQcFine)+parseFloat(midwayReward)
            +parseFloat(midwayPunish)+parseFloat(carryCostAccountAmount)
            +parseFloat(midwayChangeAddAmount)+parseFloat(midwayChangeReductAmount)
            +parseFloat(midwayCompleteGuaranteeMoneyAmount);



		var contractSettleTotal = (contractTotal*parseFloat(settleRule))/100;

		$("#contractTotal").text(contractTotal.toFixed(2));

        if(contractSettleTotal < 0){
//		    中期承包价结算金额
            $("#contractSettleTotal").text(contractSettleTotal.toFixed(2));
//           中期实际结算金额
            $("#midwaySettleTotal").text(contractSettleTotal.toFixed(2));
        }else {
            $("#contractSettleTotal").text("+" + contractSettleTotal.toFixed(2));
            $("#midwaySettleTotal").text("+" + contractSettleTotal.toFixed(2));
        }
        $("#contractSettleAmount").val(contractSettleTotal.toFixed(2));
        $("#realSettleAmount").val(contractSettleTotal.toFixed(2));
		
	}
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">中期结算单明细</a></li>
	</ul>
	<ul class="ul-form breadcrumb">
		<li class="btns"><a href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/pmPreIndustrySettleBillList" class="btn">返 回</a></li>
	</ul>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="nav nav-tabs">
					<h2>
						<center>中期结算单明细</center>
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
					action="${ctx}/proIndustryPmSettle/proIndustryPmSettle/createMidwaySettleBille">
					<input type="hidden" name="orderId" id="orderId" value="${order2.id}" />
					<input type="hidden" name="settleBillType" id="settleBillType" value="1" />
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
                                <td><a href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openContractTotal?orderId=${order2.id}&settleStage=10&isEditSettleBill=1">修改</a></td>
							</tr>

							<tr>
								<td>基装增项</td>
								<td>+${settleBill.midwayBasicworkAddAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openBaseInstalled?orderId=${order2.id}&changeType=10&isEditSettleBill=1">添加</a></td>
							</tr>

							<tr>
								<td>辅料用量扣款</td>
								<td>${settleBill.midwayMaterialsAuxiliaryAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openAuxiliaryMaInfo?orderId=${order2.id}&isSandCement=0">查看</a></td>
							</tr>

							<tr>
								<td>沙子水泥扣款</td>
								<td>${settleBill.midwaySandCementAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openAuxiliaryMaInfo?orderId=${order2.id}&isSandCement=1">查看</a></td>
							</tr>

							<tr>
								<td>标化材料扣款</td>
								<td>${settleBill.midwayMaterialsStandardAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openMaterialsStandardInfo?orderId=${order2.id}">查看</a></td>
							</tr>

							<tr>
								<td>开关面板扣款</td>
								<td>${settleBill.midwaySwitchPanelAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openMainPanelInfo?orderId=${order2.id}">查看</a></td>
							</tr>

							<tr>
								<td>工人人工费扣款</td>
								<td>${settleBill.midwayWorkerSalaryAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/queryTaskpackPaymentInfo?orderId=${order2.id}">查看</a></td>
							</tr>

							<tr>
								<td>中期质检罚款</td>
								<td>${settleBill.midwayQcCheckPunishAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmQcFineInfo?orderId=${order2.id}">查看</a></td>
							</tr>

							<tr>
								<td>中期奖励</td>
								<td>+${settleBill.rewardAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmRewardPunish?orderId=${order2.id}&rewardPunishType=1&relatedBusinessType=1&isEditSettleBill=1">添加</a></td>
							</tr>

							<tr>
								<td>中期扣款</td>
								<td>${settleBill.punishAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmRewardPunish?orderId=${order2.id}&rewardPunishType=2&relatedBusinessType=1&isEditSettleBill=1">添加</a></td>
							</tr>

							<tr>
								<td>材料搬运及运输费</td>
								<td>+${settleBill.midwayMaterialCarryCostAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openOrderMaterialCarryCost?orderId=${order2.id}&isEditSettleBill=1">添加</a></td>
							</tr>

							<tr>
								<td>中期变更增项</td>
								<td>+${settleBill.orderChangeAddAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openBaseInstalled?orderId=${order2.id}&changeType=20&isEditSettleBill=1">添加</a></td>
							</tr>

							<tr>
								<td>中期变更减项</td>
								<td>${settleBill.orderChangeReduceAmount}</td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openBaseInstalled?orderId=${order2.id}&changeType=30&isEditSettleBill=1">添加</a></td>
							</tr>

                            <tr>
                                <td>质保金</td>
                                <td>${settleBill.completeGuaranteeMoneyAmount}<input type="hidden" name="completeGuaranteeMoneyAmount" value="${completeGuaranteeMoneyAmount}"/></td>
                                <td><a
                                        href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmGuaranteeMoney?orderId=${order2.id}">查看</a></td>
                            </tr>


							<tr>
								<td>中期承包价结算金额</td>
								<td colspan="2"><span id="contractTotal"></span> * ${settleBill.midwayContractSettleRate}% = <span
									id="contractSettleTotal"></span></td>
								<input type="hidden" name="contractSettleAmount" id="contractSettleAmount"/>
								<input type="hidden" name="midwayContractSettleRate" value="${settleBill.midwayContractSettleRate}"/>
							</tr>

							<tr>
								<td>中期实际结算金额</td>
								<td colspan="2"><span id="midwaySettleTotal"></span></td>
								<input type="hidden" name="realSettleAmount" id="realSettleAmount"/>
							</tr>

							<tr>
								<td colspan="3"><label>结算说明：<br /> 中期承包价结算金额 =
                                    【承包总价 + 基装增项  - 辅料 - 沙子水泥 - 标化材料 - 开关面板 - 工人人工费  + 中期奖励 + 材料搬运及运输费 + 中期变更增项 - 中期质检罚款 - 中期变更减项  - 中期扣款 - 质保金】* ${settleBill.midwayContractSettleRate}% <br>
                                    中期承包价结算金额 =
                                    【承包总价 + 基装增项  - 辅料 - 沙子水泥 - 标化材料 - 开关面板 - 工人人工费  + 中期奖励 + 材料搬运及运输费 + 中期变更增项 - 中期质检罚款 - 中期变更减项  - 中期扣款 - 质保金】* ${settleBill.midwayContractSettleRate}%
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