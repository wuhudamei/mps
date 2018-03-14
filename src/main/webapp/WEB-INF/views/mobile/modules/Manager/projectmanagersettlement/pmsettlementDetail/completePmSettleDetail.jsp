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
    <title>竣工结算详情</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/homePage.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/flex.css" />
</head>

<body>
    <div class="wrap">
        <header class="header">
             <a class="back_btn" href="javascript:;" onclick="history.go(-1)"></a>
            <h2 class="title">竣工结算详情</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88 font0">
            <div class="pt30 pb30 pl30 pr30">
                <p class="font30 col_blue align-center">${bizPmSettleBill.customerMessage}</p>
            </div>
            <ul>
                <a class="list-line mtb-25 bg_f icon detailsIcon01" 
                    <c:if test="${bizPmSettleBill.completeCommissionAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmCommissionDetail?orderId=${bizPmSettleBill.orderId}&type=2" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>竣工提成</span><span class="pdr-80">+ ¥${bizPmSettleBill.completeCommissionAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon02"  
                    <c:if test="${bizPmSettleBill.completQcCheckPunishAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmQcCheckPunishDetail?orderId=${bizPmSettleBill.orderId}&type=2" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>竣工质检罚款</span><span class="pdr-80">- ¥${bizPmSettleBill.completQcCheckPunishAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon03"    
                    <c:if test="${bizPmSettleBill.completeRewardAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmRewardDetail?orderId=${bizPmSettleBill.orderId}&type=2" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>竣工奖励</span><span class="pdr-80">+ ¥${bizPmSettleBill.completeRewardAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon04"     
                    <c:if test="${bizPmSettleBill.completePunishAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmPunishDetail?orderId=${bizPmSettleBill.orderId}&type=2" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>竣工扣款</span><span class="pdr-80">- ¥${bizPmSettleBill.completePunishAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon05"      
                    <c:if test="${bizPmSettleBill.materialSelfbuyReimburseAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/querySelfbuyMaterial?orderId=${bizPmSettleBill.orderId}" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>自采材料</span><span class="pdr-80">+ ¥${bizPmSettleBill.materialSelfbuyReimburseAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon06"       
                    <c:if test="${bizPmSettleBill.guaranteeMoneyAmount > 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/queryGuaranteeMoney?orderId=${bizPmSettleBill.orderId}&pmGuaranteeMoney=${bizPmSettleBill.guaranteeMoneyAmount}" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>质保金</span><span class="pdr-80">- ¥${bizPmSettleBill.guaranteeMoneyAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon07"     
                    <c:if test="${bizPmSettleBill.completeAuxiliaryMaterialsSettleAmount != 0}"> 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/queryPmMaterials?orderId=${bizPmSettleBill.orderId}&type=2" 
                    </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>竣工任务包材料结算</span>
                    <span class="pdr-80">
                    <c:if test="${bizPmSettleBill.completeAuxiliaryMaterialsSettleAmount < 0}">
                      - ¥${bizPmSettleBill.completeAuxiliaryMaterialsSettleAmount}
                    </c:if>
                    <c:if test="${bizPmSettleBill.completeAuxiliaryMaterialsSettleAmount >= 0}">
                      + ¥${bizPmSettleBill.completeAuxiliaryMaterialsSettleAmount}
                    </c:if>
                    
                    </span>
                    </li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon08" <c:if test="${bizPmSettleBill.completeInspectionRewardAmount > 0}">
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmRewardDetail?orderId=${bizPmSettleBill.orderId}&type=4"
                </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>竣工巡检奖励金额</span><span class="pdr-80">+ ¥${bizPmSettleBill.completeInspectionRewardAmount}</span></li>
                </a>
                <a class="list-line mtb-25  bg_f icon detailsIcon04" <c:if test="${bizPmSettleBill.completeInspectionPunishAmount > 0}">
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmPunishDetail?orderId=${bizPmSettleBill.orderId}&type=4"
                </c:if> >
                    <li class="list-text _fullwidth flex flex-between"><span>竣工巡检罚款金额</span><span class="pdr-80">- ¥${bizPmSettleBill.completeInspectionPunishAmount}</span></li>
                </a>
            </ul>
            <div class="item-total align-right">当前合计：<span class="item-price">¥ ${bizPmSettleBill.totalAmount}<i class="f30">元</i></span></div>
        </section>
        <div style="padding-bottom:300px;"></div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/history.js"></script>
</body>

</html>