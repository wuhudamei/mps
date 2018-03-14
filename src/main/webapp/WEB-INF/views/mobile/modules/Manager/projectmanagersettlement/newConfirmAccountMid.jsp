<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>确认结算单</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/mask.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/account.css" />
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</head>
<script type="text/javascript">
		
		
		 $(document).ready(function() {
			 /* 竣工 */
			 <c:if test="${settleBill.settleBillType == 2}">
             var contractAmount = "${settleBill.contractAmount}";
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
             var settleRule  = 100-parseFloat("${midwaySettleBill.midwayContractSettleRate}");

             var contractTotal = parseFloat(contractAmount)+
                 parseFloat(baseInstalledAmount)
                 +parseFloat(auMaterAmount)+parseFloat(sandAmount)
                 +parseFloat(standardMaAmount)
                 +parseFloat(mainPanelAmount)+parseFloat(workerAmount)
                 +parseFloat(pmQcFine)+parseFloat(midwayReward)
                 +parseFloat(midwayPunish)+parseFloat(carryCostAccountAmount)
                 +parseFloat(midwayChangeAddAmount)+parseFloat(midwayChangeReductAmount)
                 +parseFloat(midwayCompleteGuaranteeMoneyAmount);
             var contractSettleTotal = (contractTotal.toFixed(2)*settleRule)/100;
             var v11;
             if(contractSettleTotal>0){
                 v11 = contractTotal.toFixed(2)+'*'+settleRule+'%'+'='+'+'+contractSettleTotal.toFixed(2);
             }else{
                 v11 = contractTotal.toFixed(2)+'*'+settleRule+'%'+'='+contractSettleTotal.toFixed(2);
             }
             var completeLongwayCommissionAmount = "${settleBill.completeLongwayCommissionAmount}";
             var orderChangeAddAmount = "${settleBill.orderChangeAddAmount}";
             var rewardAmount="${settleBill.rewardAmount}";
             var punishAmount="${settleBill.punishAmount}";
             var jzpmQcFine= "${settleBill.midwayQcCheckPunishAmount}";
             var orderChangeReduceAmount = "${settleBill.orderChangeReduceAmount}";
             var completeSettleTotal =
                 contractSettleTotal+parseFloat(completeLongwayCommissionAmount)+parseFloat(orderChangeAddAmount)
                 +parseFloat(rewardAmount)+parseFloat(jzpmQcFine)
                 +parseFloat(punishAmount)+parseFloat(orderChangeReduceAmount);


             $("#jungong").text(v11);
             </c:if>



             <c:if test="${settleBill.settleBillType == 1}">
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
             var settleRule = parseFloat("${settleBill.midwayContractSettleRate}");


             var contractTotal = parseFloat(contractAmount)+
                 parseFloat(baseInstalledAmount)
                 +parseFloat(auMaterAmount)+parseFloat(sandAmount)
                 +parseFloat(standardMaAmount)
                 +parseFloat(mainPanelAmount)+parseFloat(workerAmount)
                 +parseFloat(pmQcFine)+parseFloat(midwayReward)
                 +parseFloat(midwayPunish)+parseFloat(carryCostAccountAmount)
                 +parseFloat(midwayChangeAddAmount)+parseFloat(midwayChangeReductAmount)
                 +parseFloat(midwayCompleteGuaranteeMoneyAmount);
                var contractSettleTotal = ((contractTotal.toFixed(2)*settleRule)/100).toFixed(2);
                var v11;
				if(contractSettleTotal>0){
					v11 = contractTotal.toFixed(2)+'*'+settleRule+'%'+'='+'+'+contractSettleTotal;
				}else{
					v11 = contractTotal.toFixed(2)+'*'+settleRule+'%'+'='+contractSettleTotal;
				}
			 $("#settled").text(v11);
             </c:if>
		 })
		 
		 function tip(a){
			 if(a == 1){
				 $("#agree").show();
			 }
			 if(a == 2){
				 $("#agree2").show();
			 }
		 }
		 function hide(){
			 
			 $("#agree").hide();
			 $("#agree2").hide();
			 
		 }









</script>
<body>
	<div class="font0">
		<header>
			
			<c:if test="${empty flag }">
				<a class="back_btn" href="${ctx }/app/manager/projectManagerSettlement/budgetSureList"></a>
			</c:if>
			<c:if test="${!empty flag }">
				<a class="back_btn" href="${ctx }/app/manager/projectManagerSettlement/orderBudgetList"></a>
			</c:if>
			
			<h2 class="title">确认结算单</h2>
		</header>
		<section class="pad_top88">
			<p class="pt32 pb20 pl30">
				<span class="font30 col_blue pl20 blue_bar">基本信息</span>
			</p>
			<div class="sec pl30 pr30 font0 bor_top_ddd bor_btm_ddd bg_f clearfix">
				<ul class="pt30 pb30">
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl1em">顾客信息：</span>
						<p class="font30 col_3 flow">${settleBill.communityName }-${settleBill.buildNumber }-${settleBill.buildUnit }-${settleBill.buildRoom }-${settleBill.customerName }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl1em">合同面积：</span>
						<p class="font30 col_3">${settleBill.contractArea }m<sup>2</sup></p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">结算单编号：</span>
						<p class="font30 col_3 flow">${settleBill.pmPreIndustrySettleBillCode }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl1em">结算阶段：</span>
						<p class="font30 col_3 flow">
							<c:if test="${settleBill.settleBillType == 1}">
							中期结算
							</c:if>
							<c:if test="${settleBill.settleBillType == 2}">
								竣工结算
							</c:if>
						</p>
					</li> 
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">结算单状态：</span>
						<p class="font30 col_3 flow">${settleBill.statusDescribe}</p>
					</li>
				</ul>
			</div>
		</section>
		<!-- ----------------------------------------------分割线 中期结算-------------------------------------- -->
        <c:if test="${!empty settleBill.settleBillType and settleBill.settleBillType == 1}">
			<p class="pt32 pb20 pl30">
				<span class="font30 col_blue pl20 blue_bar">结算类目金额</span>
			</p>

        <section class="">
			<div class="sec font0 bor_top_ddd bg_f clearfix">
				<ul class="tableUl3">
					<li class="clearfix">
						<span class="col_6 font30 fl">承包总价：</span>
						<span class="col_3 font30 fl">+${settleBill.contractAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">基装增项：</span>
						<span class="col_3 font30 fl">+${settleBill.midwayBasicworkAddAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">辅料用量扣款：</span>
						<span class="col_3 font30 fl">${settleBill.midwayMaterialsAuxiliaryAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">沙子水泥扣款：</span>
						<span class="col_3 font30 fl">${settleBill.midwaySandCementAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">标化材料扣款：</span>
						<span class="col_3 font30 fl">${settleBill.midwayMaterialsStandardAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">开关面板扣款：</span>
						<span class="col_3 font30 fl">${settleBill.midwaySwitchPanelAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">工人人工费扣款：</span>
						<span class="col_3 font30 fl">${settleBill.midwayWorkerSalaryAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">中期质检罚款：</span>
						<span class="col_3 font30 fl">${settleBill.midwayQcCheckPunishAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">中期奖励：</span>
						<span class="col_3 font30 fl">+${settleBill.rewardAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">中期扣款：</span>
						<span class="col_3 font30 fl">${settleBill.punishAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">材料搬运及运输费：</span>
						<span class="col_3 font30 fl">+${settleBill.midwayMaterialCarryCostAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">中期变更增项：</span>
						<span class="col_3 font30 fl">+${settleBill.orderChangeAddAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">中期变更减项：</span>
						<span class="col_3 font30 fl">${settleBill.orderChangeReduceAmount}元</span>
					</li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">质保金：</span>
                        <span class="col_6 font30 fl">${settleBill.completeGuaranteeMoneyAmount}元</span>
                    </li>
                    <c:if test="${settleBill.settleBillType == 1}">
					<li class="clearfix">
						<span class="col_6 font30 fl">中期承包价结算金额：</span>
						<span style="padding-left:0;font-size: 10px;" class="col_3 font30 fl" id="settled"></span>
					</li>
                    </c:if>

                <c:if test="${!empty settleBill.settleBillType and settleBill.settleBillType == 1}">
				<p class="totalP">
					<span class="font30 col_cd0707">中期实发结算金额：</span>
					<span class="font40 col_cd0707 pl10">￥${settleBill.realSettleAmount}</span>
				</p>

                    <div>
                        <p class="font28 col_c30606 pl30 pr30 pt30 pb30">结算说明：</p>
                        <p class="font26 col_c30606 pl30 pr30 lineHgt18">中期承包价结算金额  = 【承包总价 + 基装增项  - 辅料 - 沙子水泥 - 标化材料 - 开关面板 - 工人人工费  + 中期奖励 + 材料搬运及运输费 + 中期变更增项 - 中期质检罚款 - 中期变更减项  - 中期扣款 - 质保金】* ${settleBill.midwayContractSettleRate}%</p>
                        <p class="font26 col_c30606 pl30 pr30 lineHgt18 pt36">中期承包价结算金额  = 【承包总价 + 基装增项  - 辅料 - 沙子水泥 - 标化材料 - 开关面板 - 工人人工费  + 中期奖励 + 材料搬运及运输费 + 中期变更增项 - 中期质检罚款 - 中期变更减项  - 中期扣款 - 质保金】* ${settleBill.midwayContractSettleRate}%</p>
                    </div>
                </c:if>
			</div>
			</section>
        </c:if>
		<!-- ----------------------------------------------分割线 竣工结算-------------------------------------- -->
		<c:if test="${!empty settleBill.settleBillType and settleBill.settleBillType == 2}">
            <p class="pt32 pb20 pl30">
                <span class="font30 col_blue pl20 blue_bar">结算类目金额</span>
            </p>
			<section class="">
			<div class="sec font0 bor_btm_ddd bg_f clearfix">
				<ul class="tableUl3">

                    <li class="clearfix">
                        <span class="col_6 font30 fl">承包总价：</span>
                        <span class="col_3 font30 fl">+${settleBill.contractAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">基装增项：</span>
                        <span class="col_3 font30 fl">+${midwaySettleBill.midwayBasicworkAddAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">辅料用量扣款：</span>
                        <span class="col_3 font30 fl">${midwaySettleBill.midwayMaterialsAuxiliaryAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">沙子水泥扣款：</span>
                        <span class="col_3 font30 fl">${midwaySettleBill.midwaySandCementAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">标化材料扣款：</span>
                        <span class="col_3 font30 fl">${midwaySettleBill.midwayMaterialsStandardAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">开关面板扣款：</span>
                        <span class="col_3 font30 fl">${midwaySettleBill.midwaySwitchPanelAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">工人人工费扣款：</span>
                        <span class="col_3 font30 fl">${midwaySettleBill.midwayWorkerSalaryAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">中期质检罚款：</span>
                        <span class="col_3 font30 fl">${midwaySettleBill.midwayQcCheckPunishAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">中期奖励：</span>
                        <span class="col_3 font30 fl">+${midwaySettleBill.rewardAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">中期扣款：</span>
                        <span class="col_3 font30 fl">${midwaySettleBill.punishAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">材料搬运及运输费：</span>
                        <span class="col_3 font30 fl">+${midwaySettleBill.midwayMaterialCarryCostAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">中期变更增项：</span>
                        <span class="col_3 font30 fl">+${midwaySettleBill.orderChangeAddAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">中期变更减项：</span>
                        <span class="col_3 font30 fl">${midwaySettleBill.orderChangeReduceAmount}元</span>
                    </li>
                    <li class="clearfix">
                        <span class="col_6 font30 fl">质保金：</span>
                        <span class="col_6 font30 fl">${midwaySettleBill.completeGuaranteeMoneyAmount}元</span>
                    </li>

					<li class="clearfix">
						<span class="col_6 font30 fl">竣工质检罚款：</span>
						<span class="col_6 font30 fl">${settleBill.midwayQcCheckPunishAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">远程费：</span>
						<span class="col_6 font30 fl">+${settleBill.completeLongwayCommissionAmount}元</span>
					</li>
					
					<li class="clearfix">
						<span class="col_6 font30 fl">竣工奖励：</span>
						<span class="col_6 font30 fl">+${settleBill.rewardAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">竣工扣款：</span>
						<span class="col_6 font30 fl">${settleBill.punishAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">竣工变更增项：</span>
						<span class="col_6 font30 fl">+${settleBill.orderChangeAddAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">竣工变更减项：</span>
						<span class="col_6 font30 fl">${settleBill.orderChangeReduceAmount}元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">竣工承包价结算金额：</span>
						<span style="padding-left:0;font-size: 1px;" class="col_3 font30 fl" id="jungong"></span>
					</li>
				</ul>
				<p class="totalP">
					<span class="font30 col_cd0707">竣工实发结算金额：</span>
					<span class="font40 col_cd0707 pl10">￥${settleBill.realSettleAmount}</span>
				</p>
			</div>

			</section>
            <div>
                <p class="font28 col_c30606 pl30 pr30 pt30 pb30">结算说明：</p>
                <p class="font26 col_c30606 pl30 pr30 lineHgt18">竣工承包价结算金额 = 【承包总价 + 基装增项  - 辅料 - 沙子水泥 - 标化材料 - 开关面板 - 工人人工费  + 中期奖励 + 材料搬运及运输费 + 中期变更增项 - 中期质检罚款 - 中期变更减项  - 中期扣款 - 质保金】* ${settleBill.midwayContractSettleRate}%</p>
                <p class="font26 col_c30606 pl30 pr30 lineHgt18 pt36">竣工实发结算金额 =   【竣工承包价结算金额 + 远程费 + 竣工变更增项 + 竣工奖励 - 竣工质检罚款 - 竣工变更减项  - 竣工扣款 】</p>
            </div>
				
				
		</c:if>
		<!-- ----------------------------------------------结束-------------------------------------- -->
		<c:if test="${empty flag }">
			<div class="pt76 pl50 pr50">
				<a class="btnBlueOne" id = 'myagree' onclick="tip(1)">同意</a>
				<a class="btnBlueTwo" id = 'myrefus' onclick="tip(2)">拒绝</a>
			</div>
		</c:if>
		
		<div style="padding-bottom:200px;"></div>
		<div class="g-mask undis" style="text-align:center;" id="agree">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">确认要同意吗？</div>
				<div class="maskBtns twoBtns clearfix">
					<a class="maskBtn font33 col_f" onclick="comfim1()">确定</a>
					<a class="maskBtn font33 col_f" href="javascript:;" onclick="hide()">取消</a>
				</div>
			</div>
		</div>
	</div>
	<div class="g-mask undis" id = 'agree2'>
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent textareaWrapper">
					<textarea id ="maskTextarea" class="maskTextarea" placeholder="请输入您的拒绝的原因"></textarea>
					<span id = "myspan" style="color: red;display: none;">*必填项</span>
				</div>
				<div class="maskBtns twoBtns clearfix">
					<a class="maskBtn font33 col_f" onclick="comfim()">确定</a>
					<a class="maskBtn font33 col_f" onclick="hide()">取消</a>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript">
		function comfim(){
			var text = $("#maskTextarea").val();
			if(text == null || text ==''){
				$("#myspan").css("display","block");
				return;
			}
			
			window.location.href = "${ctx }/app/manager/projectManagerSettlement/agree?id=${settleBill.id}&status=45&remarks="+text;
		}
		
		function comfim1(){
			
			window.location.href = "${ctx }/app/manager/projectManagerSettlement/agree?id=${settleBill.id}&status=40&orderId=${settleBill.orderId}";
		}
	</script>
</body>
</html>