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
    <title>竣工预结算详情</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/homePage.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/flex.css" />
</head>

<body>
    <div class="">
        <header class="header">
            <a class="back_btn" href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmSettlementBudgetList"></a>
            <h2 class="title">竣工预结算详情</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88 font0">
            <div class="pt30 pb30 pl30 pr30">
                <p class="font30 col_blue align-center">${pmSettlementBudget.communityName}-${pmSettlementBudget.buildNumber}-${pmSettlementBudget.buildUnit}-${pmSettlementBudget.buildRoom}-${pmSettlementBudget.customerName}</p>
            </div>
            <ul>
                <a class="list-line mtb-25 bg_f icon detailsIcon01" <c:if test="${pmSettlementBudget.completeCommissionAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmCommissionDetail?orderId=${pmSettlementBudget.orderId}&type=2" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>竣工提成</span><span class="pdr-80">+ ¥${pmSettlementBudget.completeCommissionAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon04" <c:if test="${pmSettlementBudget.completQcCheckPunishAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmQcCheckPunishDetail?orderId=${pmSettlementBudget.orderId}&type=2" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>竣工质检罚款</span><span class="pdr-80">- ¥${pmSettlementBudget.completQcCheckPunishAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon05" <c:if test="${pmSettlementBudget.completeRewardAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmRewardDetail?orderId=${pmSettlementBudget.orderId}&type=2" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>竣工奖励</span><span class="pdr-80">+ ¥${pmSettlementBudget.completeRewardAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon06" <c:if test="${pmSettlementBudget.completePunishAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmPunishDetail?orderId=${pmSettlementBudget.orderId}&type=2" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>竣工扣款</span><span class="pdr-80">- ¥${pmSettlementBudget.completePunishAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon03" <c:if test="${pmSettlementBudget.materialSelfbuyReimburseAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmMaterialSelfbuyReimburseDetail?orderId=${pmSettlementBudget.orderId}" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>自采材料</span><span class="pdr-80">- ¥${pmSettlementBudget.materialSelfbuyReimburseAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon03" <c:if test="${pmSettlementBudget.guaranteeMoneyAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmGuaranteeMoneyDetail?orderId=${pmSettlementBudget.orderId}" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>质保金</span><span class="pdr-80">- ¥${pmSettlementBudget.guaranteeMoneyAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon07" <c:if test="${pmSettlementBudget.completeAuxiliaryMaterialsSettleAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmAuxiliaryMaterialsDetail?orderId=${pmSettlementBudget.orderId}&type=2" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>竣工任务包材料结算</span><span class="pdr-80">+ ¥${pmSettlementBudget.completeAuxiliaryMaterialsSettleAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon08" <c:if test="${pmSettlementBudget.completeInspectionRewardAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmRewardDetail?orderId=${pmSettlementBudget.orderId}&type=4" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>竣工巡检奖励金额</span><span class="pdr-80">+ ¥${pmSettlementBudget.completeInspectionRewardAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon04" <c:if test="${pmSettlementBudget.completeInspectionPunishAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmPunishDetail?orderId=${pmSettlementBudget.orderId}&type=4" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>竣工巡检罚款金额</span><span class="pdr-80">- ¥${pmSettlementBudget.completeInspectionPunishAmount}</span></li>
                </a>
            </ul>
            <div class="item-total align-right">当前合计：<span class="item-price">¥ ${pmSettlementBudget.settlementBudgetAmount} <i class="f30">元</i></span></div>
        </section>
        <div style="padding-bottom:300px;"></div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/history.js"></script>
</body>

</html>