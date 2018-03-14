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
    <title>订单结算明细</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/search.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/help.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/style.css" />

</head>

<body>
    <div class="wrap">
        <header>
            <a class="back_btn" href="${ctx}/app/manager/toQueryPmGuaranteeMoneyLog"></a>
            <h2 class="title">订单结算明细列表</h2>
        </header>
        <!-- /header -->
        <section class="pt112">
            <div class="font0 search-area">
                <input class="search-box" id="searchParam" type="text" placeholder="小区名称、客户姓名" value="${queryParam}">
                <a class="search-btn" href="javascript:;" onclick="query()"></a>
            </div>
            <c:forEach items="${list}" var="pmSettlement">
               <div class="sec font0 border_top border_btm mb24 bg_f clearfix">
                <ul class="pad_top3 pl30 pr30 bor_dashed">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">顾&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${pmSettlement.communityName}-${pmSettlement.buildNumber}-${pmSettlement.buildUnit}-${pmSettlement.buildRoom}-${pmSettlement.customerName}</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">结算合计金额：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${pmSettlement.totalAmount}</p>
                    </li>
                </ul>
                <div class="btn_wrapper clearfix">
                    <a class="btnBlueOne" href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmSettleDetail?orderId=${pmSettlement.orderId}&settleBillType=1">中期结算详情</a>
                    <a class="btnBlueTwo" href="${root}/mobile/modules/manager/projectmanagersettlement/web/pmSettlementController/pmSettleDetail?orderId=${pmSettlement.orderId}&settleBillType=2">竣工结算详情</a>
                </div>
            </div>
            </c:forEach>
        </section>
        <div style="padding-bottom:300px;"></div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/history.js"></script>
    <script>
     function query(){
    	 var queryParam = $("#searchParam").val();
    	 window.location.href = "${ctx}/app/manager/balancebyorder/list?queryParam="+queryParam;
     }
    </script>
</body>

</html>