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
    <title>中期任务包材料结算明细</title>
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
            <h2 class="title">中期任务包材料结算明细</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88">
            <div class="item-tips">
                <p><i class="icon icon-tips">icon</i>温馨提示：</p>
                <p class="pad0">如果预计结算明细有异议，请与结算员联系。
                </p>
            </div>
            <div class="item-total align-right bg_f margin-tb25 font30">中期任务包材料结算金额：<span class="font34 dark-red">+${pmMaterialsSettleAmount} </span>元</div>
            <c:forEach items="${pmMaterials}" var="materials">
            <div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3 pad_rgt2">
                    <li class="mb30 clearfix">
                        <span class="font30 flo_left pl1em col_blue">任务包编号：</span>
                        <p class="font30 col_3 pad_rgt3 flow col_blue">${materials.taskPackageNo}</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">任务包名称：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${materials.taskPackageName}
                        </p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">辅料费结算总金额：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${materials.auxiliaryMaterialsSettleAmount}元</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">辅料扣款金额：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${materials.auxiliaryMaterialsAmount}元</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">沙子水泥扣款金额： </span>
                        <p class="font30 col_3 pad_rgt3 flow">${materials.sandCementAmount}元</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">项目经理材料结算金额： </span>
                        <p class="font30 col_3 pad_rgt3 flow">${materials.pmMaterialsSettleAmount}元</p>
                    </li>
                    <li class="mb30 clearfix"><span class="font30 flo_left dark-red">（项目经理材料结算金额 = 辅料结算总金额 - 辅料扣款金额 - 沙子水泥扣款金额）</span></li>
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