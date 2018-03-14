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
    <title>订单结算金额预览</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/list.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/style.css" />
</head>

<body>
    <div class="">
        <header>
            <a class="back_btn" href="${ctx }/app/manager/toQueryPmGuaranteeMoneyLog"></a>
            <h2 class="title">订单结算金额预览</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88">
            <div class="item-tips">
                <p><i class="icon icon-tips">icon</i>温馨提示：</p>
                <p><i class="icon-num">1.</i>“涂饰工程及基装验收”的约检节点质检员【确认验收】 之后，才可显示中期结算明细。</p>
                <p><i class="icon-num">2.</i>“竣工验收”的约检节点质检员【确认验收】之后，才 可显示竣工预结算明细。</p>
                <p><i class="icon-num">3.</i> 如果预计结算明细有异议，请在质检员确认验收时间24 小时之内与结算员联系。</p>
            </div>
            <c:forEach items="${list}" var="pmSettlementBudget">
               <div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3 bor_dashed">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl1em">顾&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${pmSettlementBudget.communityName}-${pmSettlementBudget.buildNumber}-${pmSettlementBudget.buildUnit}-${pmSettlementBudget.buildRoom}-${pmSettlementBudget.customerName}</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">结算阶段：</span>
                        <p class="font30 col_3 pad_rgt3 flow">
                            <c:if test="${pmSettlementBudget.settleBillType == 10}">
							中期结算
							</c:if>
							<c:if test="${pmSettlementBudget.settleBillType == 20}">
								竣工结算
							</c:if>
						</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">预计金额： </span>
                        <p class="font30 col_3 pad_rgt3 flow">${pmSettlementBudget.settlementBudgetAmount}元</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">确认验收时间：</span>
                        <p class="font30 col_3 pad_rgt3 flow"><fmt:formatDate value="${pmSettlementBudget.acceptCheckDatetime}" pattern="yyyy-MM-dd"/></p>
                    </li>
                </ul>
                <div class="btn_wrapper clearfix">
                    <a class="btnBlueBg" 
                    href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget/pmSettlementBudgetDetail?orderId=${pmSettlementBudget.orderId}&settleBillType=${pmSettlementBudget.settleBillType}&communityName=${pmSettlementBudget.communityName}&buildNumber=${pmSettlementBudget.buildNumber}&buildUnit=${pmSettlementBudget.buildUnit}&buildRoom=${pmSettlementBudget.buildRoom}&customerName=${pmSettlementBudget.customerName}">详情</a>
                </div>
            </div>
            </c:forEach>
        </section>
        <div style="padding-bottom:300px;"></div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/history.js"></script>
</body>
</html>