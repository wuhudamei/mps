<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
    <title>自采材料报销明细</title>
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
            <a class="back_btn" href="javascript:;" onclick="history.go(-1)"></a>
            <h2 class="title">自采材料报销明细</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88">
            <div class="item-tips">
                <p><i class="icon icon-tips">icon</i>温馨提示：</p>
                <p class="pad0">1、自采材料报销只有结算员审核通过了才显示。
                </p>
                <p class="pad0">2、如果预计结算明细有异议，请与结算员联系。
                </p>
            </div>
            <div class="item-total align-right bg_f margin-tb25 font30">自采材料报销金额：<span class="font34 dark-red">+${sinceMaterials} </span>元</div>
            <c:forEach items="${materialSelfbuyList}" var="materialSelfbuy">
               <div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl1em">自采材料名称：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${materialSelfbuy.materialName}</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">客户交费金额：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${materialSelfbuy.customerPayAmount}元</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">结  算  比  例  ： </span>
                        <p class="font30 col_3 pad_rgt3 flow">${materialSelfbuy.customerPayAmount.settleRate*100}%</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">实际结算金额： </span>
                        <p class="font30 col_3 pad_rgt3 flow">${materialSelfbuy.customerPayAmount.settleAmount}元</p>
                    </li>
                </ul>
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