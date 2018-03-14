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
    <title>中期预结算详情</title>
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
            <h2 class="title">中期预结算详情</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88 font0">
            <div class="pt30 pb30 pl30 pr30">
                <p class="font30 col_blue align-center">${pmSettlementBudget.communityName}-${pmSettlementBudget.buildNumber}-${pmSettlementBudget.buildUnit}-${pmSettlementBudget.buildRoom}-${pmSettlementBudget.customerName}</p>
            </div>
            <ul>
                <a class="list-line mtb-25 bg_f icon detailsIcon01" <c:if test="${pmSettlementBudget.midwayCommissionAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmCommissionDetail?orderId=${pmSettlementBudget.orderId}&type=1" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>中期提成</span><span class="pdr-80">+ ¥${pmSettlementBudget.midwayCommissionAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon02" <c:if test="${pmSettlementBudget.ownpayAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmOwnpayDetail?orderId=${pmSettlementBudget.orderId}" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>自主支配项</span><span class="pdr-80">+ ¥${pmSettlementBudget.ownpayAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon03" <c:if test="${pmSettlementBudget.materialsStandardAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmMaterialsStandardDetail?orderId=${pmSettlementBudget.orderId}" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>标化辅料</span><span class="pdr-80">- ¥${pmSettlementBudget.materialsStandardAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon04" <c:if test="${pmSettlementBudget.midwayQcCheckPunishAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmQcCheckPunishDetail?orderId=${pmSettlementBudget.orderId}&type=1" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>中期质检罚款</span><span class="pdr-80">- ¥${pmSettlementBudget.midwayQcCheckPunishAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon05" <c:if test="${pmSettlementBudget.midwayRewardAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmRewardDetail?orderId=${pmSettlementBudget.orderId}&type=1" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>中期奖励</span><span class="pdr-80">+ ¥${pmSettlementBudget.midwayRewardAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon06" <c:if test="${pmSettlementBudget.midwayPunishAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmPunishDetail?orderId=${pmSettlementBudget.orderId}&type=1" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>中期扣款</span><span class="pdr-80">- ¥${pmSettlementBudget.midwayPunishAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon07" <c:if test="${pmSettlementBudget.midwayAuxiliaryMaterialsSettleAmount != 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmAuxiliaryMaterialsDetail?orderId=${pmSettlementBudget.orderId}&type=1" </c:if>>
                    <li class="list-text _fullwidth flex flex-between">
                    <span>中期任务包材料结算</span><span class="pdr-80">
                    <c:if test="${pmSettlementBudget.midwayAuxiliaryMaterialsSettleAmount >= 0}">
                     + ¥${pmSettlementBudget.midwayAuxiliaryMaterialsSettleAmount}
                    </c:if>
                    <c:if test="${pmSettlementBudget.midwayAuxiliaryMaterialsSettleAmount < 0}">
                     - ¥${pmSettlementBudget.midwayAuxiliaryMaterialsSettleAmount}
                    </c:if>
                    </span>
                    </li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon08" <c:if test="${pmSettlementBudget.midwayInspectionRewardAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmRewardDetail?orderId=${pmSettlementBudget.orderId}&type=3" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>中期巡检奖励金额</span><span class="pdr-80">+ ¥${pmSettlementBudget.midwayInspectionRewardAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon04" <c:if test="${pmSettlementBudget.midwayInspectionPunishAmount > 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmPunishDetail?orderId=${pmSettlementBudget.orderId}&type=3" </c:if>>
                    <li class="list-text _fullwidth flex flex-between"><span>中期巡检罚款金额</span><span class="pdr-80">- ¥${pmSettlementBudget.midwayInspectionPunishAmount}</span></li>
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