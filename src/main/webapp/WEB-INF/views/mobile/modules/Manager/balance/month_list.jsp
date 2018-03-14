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
    <title>月度结算明细</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/list.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/homePage.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/style.css" />

</head>

<body>
    <div class="wrap">
        <header>
            <a class="back_btn" href="${ctx}/app/manager/toQueryPmGuaranteeMoneyLog"></a>
            <h2 class="title">月度结算明细</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88">
            <div class="item-tips">
                <p><i class="icon icon-tips">icon</i>温馨提示：</p>
                <p class="pad0">本月合计结算金额 = 本月所有订单的中期或者竣工结算金额累计之和。</p>
                <p class="pad0">点击右侧箭头可以查看结算月份每个订单的结算明细。</p>
                <p class="pad0">默认只显示最近半年的月度结算信息，点“加载更多”可查看以前的结算信息。</p>
            </div>
            <div class="item-total align-right bg_f margin-tb25 font30">合计结算金额：<span class="font34 dark-red">+${totalMoney} </span>元</div>
            <c:forEach items="${list}" var="balanceDetail">
            <a href="${ctx}/app/manager/balancebymonth/querySettleBillByParam?settleMonth=${balanceDetail.settleMonth}">
            <div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3 list-text _autowidth">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl1em">结算月份：</span>
                        <p class="font30 col_3 pad_rgt3 flow"><fmt:formatDate value="${balanceDetail.settleMonthDate}" pattern="yyyy-MM" /></p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">本月合计结算金额：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${balanceDetail.totalMoney}元</p>
                    </li>
                </ul>
            </div>
            </a>
            </c:forEach>
            <div class="btn-area align-center"><a class="btn-more" href="${ctx}/app/manager/balancebymonth/queryBalanceDetailList?startSettleMonth=${startSettleMonth}">加载更多</a></div>
        </section>
        <div style="padding-bottom:300px;"></div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/history.js"></script>
</body>

</html>