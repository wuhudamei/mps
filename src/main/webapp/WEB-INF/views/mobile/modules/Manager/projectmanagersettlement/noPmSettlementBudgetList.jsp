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
    <title>订单结算金额预览--无数据</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/list.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/help.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/style.css" />

</head>

<body>
    <div class="wrap">
        <header>
            <a class="back_btn" href="${ctx }/app/manager/toQueryPmGuaranteeMoneyLog"></a>
            <h2 class="title">订单结算金额预览--无数据</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88">
            <div class="item-tips">
                <p><i class="icon icon-tips">icon</i>温馨提示：</p>
                <p><i class="icon-num">1.</i>“涂饰工程及基装验收”的约检节点质检员【确认验收】之后，才显示中期预计结算明细。</p>
                <p><i class="icon-num">2.</i>“竣工验收”的约检节点质检员【确认验收】之后，才显示竣工预计结算明细。</p>
                <p><i class="icon-num">3.</i>涂饰工程及基装验收”的约检节点结算员审核通过之后，中期预计结算明细将不会再显示。</p>
                <p><i class="icon-num">4.</i>您操作的“申请竣工”结算员审核通过之后，竣工预计结算明细将不会再显示。</p>
            </div>
            <div class="item-nodate align-center"><i class="icon icon-nodate">icon</i>
                <p>您名下没有可预览的订单结算金额</p>
            </div>
        </section>
        <div style="padding-bottom:300px;"></div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/history.js"></script>
</body>

</html>