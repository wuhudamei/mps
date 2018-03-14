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
    <title>中期结算详情</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/homePage.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/flex.css" />
</head>

<body>
    <div class="">
        <header class="header">
            <a class="back_btn" href="javascript:;" onclick="history.go(-1)"></a>
            <h2 class="title">中期结算详情</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88 font0">
            <div class="pt30 pb30 pl30 pr30">
                <p class="font30 col_blue align-center">${bizPmSettleBill.customerMessage}</p>
            </div>
            <ul>
                <a class="list-line mtb-25 bg_f icon detailsIcon01" 
                    <c:if test="${bizPmSettleBill.midwayCommissionAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmCommissionDetail?orderId=${bizPmSettleBill.orderId}&type=1" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between" ><span>中期提成</span><span class="pdr-80">+ ¥${bizPmSettleBill.midwayCommissionAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon02" 
                    <c:if test="${bizPmSettleBill.ownpayAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmOwnpayDetail?orderId=${bizPmSettleBill.orderId}" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>自主支配项</span><span class="pdr-80">+ ¥${bizPmSettleBill.ownpayAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon03" 
                    <c:if test="${bizPmSettleBill.materialsStandardAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmMaterialsStandardDetail?orderId=${bizPmSettleBill.orderId}" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>标化辅料</span><span class="pdr-80">-¥${bizPmSettleBill.materialsStandardAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon04"  
                    <c:if test="${bizPmSettleBill.midwayQcCheckPunishAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmQcCheckPunishDetail?orderId=${bizPmSettleBill.orderId}&type=1" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>中期质检罚款</span><span class="pdr-80">- ¥${bizPmSettleBill.midwayQcCheckPunishAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon05"   
                    <c:if test="${bizPmSettleBill.midwayRewardAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmRewardDetail?orderId=${bizPmSettleBill.orderId}&type=1" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>中期奖励</span><span class="pdr-80">+ ¥${bizPmSettleBill.midwayRewardAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon06"    
                    <c:if test="${bizPmSettleBill.midwayPunishAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmPunishDetail?orderId=${bizPmSettleBill.orderId}&type=1" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>中期扣款</span><span class="pdr-80">- ¥${bizPmSettleBill.midwayPunishAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon07"    
                    <c:if test="${bizPmSettleBill.midwayAuxiliaryMaterialsSettleAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/queryPmMaterials?orderId=${bizPmSettleBill.orderId}&type=1" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>中期任务包材料结算</span>
                    <span class="pdr-80">
                    <c:if test="${bizPmSettleBill.midwayAuxiliaryMaterialsSettleAmount < 0}">
                      - ¥${bizPmSettleBill.midwayAuxiliaryMaterialsSettleAmount}
                    </c:if>
                    <c:if test="${bizPmSettleBill.midwayAuxiliaryMaterialsSettleAmount >= 0}">
                    + ¥${bizPmSettleBill.midwayAuxiliaryMaterialsSettleAmount}
                    </c:if>

                    </span>
                    </li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon08"
                <c:if test="${bizPmSettleBill.midwayInspectionRewardAmount == 0}">
                   href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmRewardDetail?orderId=${bizPmSettleBill.orderId}&type=3"
                </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>中期巡检奖励金额</span><span class="pdr-80">+ ¥${bizPmSettleBill.midwayInspectionRewardAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon04" <c:if test="${bizPmSettleBill.midwayInspectionPunishAmount == 0}"> href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmPunishDetail?orderId=${bizPmSettleBill.orderId}&type=3"
                </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>中期巡检罚款金额</span><span class="pdr-80">- ¥${bizPmSettleBill.midwayInspectionPunishAmount}</span></li>
                </a>
            </ul>
            <div class="item-total align-right">当前合计：<span class="item-price">¥ ${bizPmSettleBill.totalAmount} <i class="f30">元</i></span></div>
        </section>
        <div style="padding-bottom:300px;"></div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/history.js"></script>
</body>

</html>