<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>创建中期结算单</title>
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
		var contractAmount = "${contractAmount}";
		var baseInstalledAmount = "${baseInstalledAmount}";
		var auMaterAmount = "${auMaterAmount}";
		var sandAmount = "${sandAmount}";
		var standardMaAmount = "${standardMaAmount}";
		var mainPanelAmount = "${mainPanelAmount}";
		var workerAmount = "${workerAmount}";
		var pmQcFine = "${pmQcFine}";
		var midwayReward="${midwayReward.rewardPunishAmount}";
		var midwayPunish="${midwayPunish.rewardPunishAmount}";
		var carryCostAccountAmount="${bizOrderMaterialCarryCost.accountAmount}";
		var midwayChangeAddAmount = "${midwayChangeAddAmount}";
		var midwayChangeReductAmount = "${midwayChangeReductAmount}";
		var settleRule = 0;
		<c:if test="${bizNormalPmSettleNode.settleRule != null}">
          settleRule = parseFloat("${bizNormalPmSettleNode.settleRule}");
		</c:if>

		var contractTotal = parseFloat(contractAmount)+parseFloat(baseInstalledAmount)
		                    +parseFloat(auMaterAmount)+parseFloat(sandAmount)+parseFloat(standardMaAmount)
		                    +parseFloat(mainPanelAmount)+parseFloat(workerAmount);
		var contractSettleTotal = (contractTotal*settleRule)/100;
		
		var midwaySettleTotal = contractSettleTotal+parseFloat(midwayReward)+parseFloat(carryCostAccountAmount)
		                        +parseFloat(midwayChangeAddAmount)+parseFloat(pmQcFine)+parseFloat(midwayPunish)
		                        +parseFloat(midwayChangeReductAmount);
		                        
		$("#contractTotal").text(contractTotal.toFixed(2));
		
		$("#contractSettleTotal").text(contractSettleTotal.toFixed(2));
		$("#contractSettleAmount").val(contractSettleTotal.toFixed(2));
		$("#midwaySettleTotal").text(midwaySettleTotal.toFixed(2));
		$("#realSettleAmount").val(midwaySettleTotal.toFixed(2));
		
	}
	
	function submitForm(){
		top.$.jBox.confirm("您确认要保存吗？", "系统提示", function(v, h, f) {
			if (v == "ok") {
				$(obj).attr({
					"disabled" : "disabled"
				});
				$("#inputSubmit").submit();
			}
		}, {
			buttonsFocus : 1
		});
		top.$('.jbox-body .jbox-icon').css('top', '55px');
	}
	
	function brank(){
		window.location.href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/queryProIndustryPmMidwaySettleList?storeId="+${order2.storeId};
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">创建中期结算单</a></li>
	</ul>
	<ul class="ul-form breadcrumb">
		<li class="btns"><input class="btn" type="button" value="返回"
			onclick="brank()" /></li>
	</ul>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="nav nav-tabs">
					<h2>
						<center>创建中期结算单</center>
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
								<td>+${contractAmount}<input type="hidden" name="contractAmount" value="${contractAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openContractTotal?orderId=${order2.id}&settleStage=10&isEditSettleBill=0&isNewSettleBill=0">修改</a></td>
							</tr>

							<tr>
								<td>基装增项</td>
								<td>+${baseInstalledAmount}<input type="hidden" name="midwayBasicworkAddAmount" value="${baseInstalledAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openBaseInstalled?orderId=${order2.id}&changeType=10&isEditSettleBill=0&isNewSettleBill=0">添加</a></td>
							</tr>

							<tr>
								<td>辅料用量扣款</td>
								<td>${auMaterAmount}<input type="hidden" name="midwayMaterialsAuxiliaryAmount" value="${auMaterAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openAuxiliaryMaInfo?orderId=${order2.id}&isSandCement=0">查看</a></td>
							</tr>

							<tr>
								<td>沙子水泥扣款</td>
								<td>${sandAmount}<input type="hidden" name="midwaySandCementAmount" value="${sandAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openAuxiliaryMaInfo?orderId=${order2.id}&isSandCement=1">查看</a></td>
							</tr>

							<tr>
								<td>标化材料扣款</td>
								<td>${standardMaAmount}<input type="hidden" name="midwayMaterialsStandardAmount" value="${standardMaAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openMaterialsStandardInfo?orderId=${order2.id}">查看</a></td>
							</tr>

							<tr>
								<td>开关面板扣款</td>
								<td>${mainPanelAmount}<input type="hidden" name="midwaySwitchPanelAmount" value="${mainPanelAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openMainPanelInfo?orderId=${order2.id}">查看</a></td>
							</tr>

							<tr>
								<td>工人人工费扣款</td>
								<td>${workerAmount}<input type="hidden" name="midwayWorkerSalaryAmount" value="${workerAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/queryTaskpackPaymentInfo?orderId=${order2.id}">查看</a></td>
							</tr>

							<tr>
								<td>中期质检罚款</td>
								<td>${pmQcFine}<input type="hidden" name="midwayQcCheckPunishAmount" value="${pmQcFine}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmQcFineInfo?orderId=${order2.id}">查看</a></td>
							</tr>

							<tr>
								<td>中期奖励</td>
								<td>+${midwayReward.rewardPunishAmount}<input type="hidden" name="rewardAmount" value="${midwayReward.rewardPunishAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmRewardPunish?orderId=${order2.id}&rewardPunishType=1&relatedBusinessType=1&isEditSettleBill=0&isNewSettleBill=0">添加</a></td>
							</tr>

							<tr>
								<td>中期扣款</td>
								<td>${midwayPunish.rewardPunishAmount}<input type="hidden" name="punishAmount" value="${midwayPunish.rewardPunishAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openPmRewardPunish?orderId=${order2.id}&rewardPunishType=2&relatedBusinessType=1&isEditSettleBill=0&isNewSettleBill=0">添加</a></td>
							</tr>

							<tr>
								<td>材料搬运及运输费</td>
								<td>+${bizOrderMaterialCarryCost.accountAmount}<input type="hidden" name="midwayMaterialCarryCostAmount" value="${bizOrderMaterialCarryCost.accountAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openOrderMaterialCarryCost?orderId=${order2.id}&isEditSettleBill=0&isNewSettleBill=0">添加</a></td>
							</tr>

							<tr>
								<td>中期变更增项</td>
								<td>+${midwayChangeAddAmount}<input type="hidden" name="orderChangeAddAmount" value="${midwayChangeAddAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openBaseInstalled?orderId=${order2.id}&changeType=20&isEditSettleBill=0&isNewSettleBill=0">添加</a></td>
							</tr>

							<tr>
								<td>中期变更减项</td>
								<td>${midwayChangeReductAmount}<input type="hidden" name="orderChangeReduceAmount" value="${midwayChangeReductAmount}"/></td>
								<td><a
									href="${ctx}/proIndustryPmSettle/proIndustryPmSettle/openBaseInstalled?orderId=${order2.id}&changeType=30&isEditSettleBill=0&isNewSettleBill=0">添加</a></td>
							</tr>

							<tr>
								<td>中期承包价结算金额</td>
								<td colspan="2"><span id="contractTotal"></span> * ${bizNormalPmSettleNode.settleRule}% = +<span
									id="contractSettleTotal"></span></td>
								<input type="hidden" name="contractSettleAmount" id="contractSettleAmount"/>
								<input type="hidden" name="midwayContractSettleRate" value="${bizNormalPmSettleNode.settleRule}"/>
							</tr>

							<tr>
								<td>中期实际结算金额</td>
								<td colspan="2">+<span id="midwaySettleTotal"></span></td>
								<input type="hidden" name="realSettleAmount" id="realSettleAmount"/>
							</tr>

							<tr>
								<td colspan="3"><label>结算说明：<br /> 中期承包价结算金额 =
										【承包总价 + 基装增项 - 辅料 - 沙子水泥 - 标化材料 - 开关面板 - 工人人工费 】* ${bizNormalPmSettleNode.settleRule}% <br>
										中期实际结算金额 = 中期承包价结算金额 + 中期奖励 + 材料搬运及运输费 + 中期变更增项 - 中期质检罚款 -
										中期变更减项 - 中期扣款
								</label></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="form-actions" style="text-align: center">
				<input class="btn btn-primary" type="button" value="保 存"
					onclick="submitForm()" /> &nbsp; <input class="btn" type="button"
					value="返 回" onclick="brank()" />
			</div>
		</div>
	</div>
</body>
</html>