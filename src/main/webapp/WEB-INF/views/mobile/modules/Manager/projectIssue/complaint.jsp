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
    <title>工程投诉</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css" />
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/complaint/complaint.css">
</head>

<body>
<div class="">
    <header>
        <a class="back_btn" href="${ctx}/app/manager/toindex"></a>
        <h2 class="title">工程投诉</h2>
    </header>
    <section class="pad_top11">

        <c:forEach items="${mapList}" var="item">


        <div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
            <ul class="pad_top3 pad_left3 bor_dotted">
                <li class="mar_btm24 clearfix">
                    <span class="col_grey font28 flo_left w2">顾客 ：</span>
                    <p class="font28 col_3 flow">${item.customerInfo}</p>
                </li>
                <li class="mar_btm24 clearfix">
                    <span class="col_grey font28 flo_left w2">合同开工日期：</span>
                    <p class="font28 col_3 flow">${item.contractStartDate}</p>
                </li>
                <li class="mar_btm24 clearfix">
                    <span class="col_grey font28 flo_left w2">实际开工日期：</span>
                    <p class="font28 col_3 flow">${item.contractStartDate}</p>
                </li>
                <li class="mar_btm24 clearfix">
                    <span class="col_grey font28 flo_left w2">合 同 工 期：</span>
                    <p class="font28 col_3 flow">${item.contractTime}天</p>
                </li>
                <li class="mar_btm24 clearfix">
                    <span class="col_grey font28 flo_left w2">状态：</span>
                    <p class="font28 col_blue flow">${item.status}</p>
                </li>
            </ul>
            <div class="clearfix">
                <a class="one_btn"  href="${ctx}/app/manager/project-issue/checkIssueProblemByOrderId?orderId=${item.orderId}">查看投诉</a>
            </div>
        </div>

        </c:forEach>
    </section>
    <div style="padding-bottom:300px;"></div>
</div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>

</html>