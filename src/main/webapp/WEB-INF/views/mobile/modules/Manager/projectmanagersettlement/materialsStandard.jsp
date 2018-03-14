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
    <title>标化辅料明细</title>
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
            <h2 class="title">标化辅料明细</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88">
            <div class="item-tips">
                <p><i class="icon icon-tips">icon</i>温馨提示：</p>
                <p class="pad0">如果以下结算明细不正确，请在收到短信24小时之内下线与结算员联系。
                </p>
            </div>
            <div class="item-total align-right bg_f margin-tb25 font30">标化辅料扣款合计金额：<span class="font34 dark-red">-${details3.receiveBillAmount} </span>元</div>
            <c:forEach items="${codeList}" var="code">
               <div class="item-column">申请单号：${code}</div>
              <c:forEach items="${materialsStandardList}" var="materialsStandard">
                <c:if test="${code == materialsStandard.materialsStandardReceiveBillCode}">
                    <div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3">
                    <li class="mb30 clearfix">
                        <span class="font30 flo_left pl1em col_blue">物料类别：</span>
                        <p class="font30 col_3 pad_rgt3 flow col_blue">${materialsStandard.materialsType}</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">物料名称：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${materialsStandard.materialsName}
                        </p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价： </span>
                        <p class="font30 col_3 pad_rgt3 flow">${materialsStandard.materialsPrice}元</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">领取数量：</span>
                        <p class="font30 col_3 pad_rgt3 flow">${materialsStandard.receiveNumberTotal}</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">合&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计： </span>
                        <p class="font30 col_3 pad_rgt3 flow">${materialsStandard.materialsAmount}元</p>
                    </li>
                </ul>
            </div>
                </c:if>
              </c:forEach>
            </c:forEach>
        </section>
        <div style="padding-bottom:300px;"></div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/history.js"></script>
</body>

</html>