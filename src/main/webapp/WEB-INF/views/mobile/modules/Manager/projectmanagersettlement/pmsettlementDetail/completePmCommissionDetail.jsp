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
    <title>竣工提成明细</title>
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
            <h2 class="title">竣工提成明细</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88">
            <div class="item-tips">
                <p><i class="icon icon-tips">icon</i>温馨提示：</p>
                <p class="pad0">新/老房：指的是订单是新房还是老房，新房与老房的提成总金额是不一样的。
                </p>
                <p class="pad0">派单时项目经理星级：指的是派单员派订单给项目经理时，项目经理当时的星级。</p>
                <p class="pad0">提成总金额：指的是每户订单的项目经理提成总金额。</p>
                <p class="pad0">提成比例：指的是每户订单中期提成发放的比例。</p>
                <p class="pad0">竣工提成实发金额：等于 提成总金额 * 提成比例。</p>
            </div>
            <div class="item-total align-right bg_f margin-tb25 font30">竣工提成实发金额：<span class="font34 dark-red">+${commissionAmount} </span>元</div>
            <div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl1em">新/老房：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${fns:getDictLabel(bizPmStarCommissionCnfgSnap.isOldNew, 'biz_is_new_house','')}</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">派单时项目经理星级：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${fns:getDictLabel(bizPmStarCommissionCnfgSnap.starLever, 'manager_star','')}</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">提成总金额： </span>
                        <p class="font30 col_3 pad_rgt3 flow">${bizPmStarCommissionCnfgSnap.commissionAmount}元</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">提成比例：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${bizPmStarCommissionCnfgSnap.commissionRateComplete * 100}%</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">竣工提成实发金额：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${commissionAmount}元</p>
                    </li>
                </ul>
            </div>
        </section>
        <div style="padding-bottom:300px;"></div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/history.js"></script>
</body>

</html>