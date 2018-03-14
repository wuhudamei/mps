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
    <title>中期结算进度</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/search.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/help.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/style.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/flex.css" />
</head>

<body>
    <div class="wrap">
        <header>
            <a class="back_btn" href="javascript:;" onclick="history.go(-1)"></a>
            <h2 class="title">中期结算进度</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88">
            <div class="sec font0 border_top mar_btm24 pad_top3 flow clearfix">
                <ul class="pad_left3 date-line pad_btm3">
                    <li class="mb30 clearfix posA">
                        <i <c:if test="${orderSettlementSchedule.pqcCheckedDatetime != null}">class="icon icon-time"</c:if> 
                        <c:if test="${orderSettlementSchedule.pqcCheckedDatetime == null}">class="icon icon-time _undone"</c:if> >icon</i>
                        <p class="bg-list col_6 font30 flow flex flex-between">
                        <span <c:if test="${orderSettlementSchedule.pqcCheckedDatetime != null}">class="col_blue"</c:if> >质检员确认验收约检节点</span>
                        <span class="font26">
                        <fmt:formatDate value="${orderSettlementSchedule.pqcCheckedDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </span>
                        </p>
                    </li>
                    <li class="mb30 clearfix posA">
                        <i <c:if test="${orderSettlementSchedule.settlementClerkPassDatetime != null}">class="icon icon-time"</c:if>
                           <c:if test="${orderSettlementSchedule.settlementClerkPassDatetime == null}">class="icon icon-time _undone"</c:if> >icon</i>
                        <p class="bg-list col_6 font30 flow flex flex-between">
                        <span <c:if test="${orderSettlementSchedule.settlementClerkPassDatetime != null}">class="col_blue"</c:if> >结算员通过约检节点</span>
                        <span class="font26">
                           <fmt:formatDate value="${orderSettlementSchedule.settlementClerkPassDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </span>
                        </p>
                    </li>
                    <li class="mb30 clearfix posA">
                        <i <c:if test="${orderSettlementSchedule.financeAffirmDatetime != null}">class="icon icon-time"</c:if> 
                           <c:if test="${orderSettlementSchedule.financeAffirmDatetime == null}">class="icon icon-time _undone"</c:if> >icon</i>
                        <p class="bg-list col_6 font30 flow flex flex-between">
                        <span <c:if test="${orderSettlementSchedule.financeAffirmDatetime != null}">class="col_blue"</c:if> >财务确认二期</span>
                        <span class="font26">
                           <fmt:formatDate value="${orderSettlementSchedule.financeAffirmDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </span>
                        </p>
                    </li>
                    <li class="mb30 clearfix posA">
                        <i <c:if test="${orderSettlementSchedule.createMonthSettle != null}">class="icon icon-time"</c:if> 
                           <c:if test="${orderSettlementSchedule.createMonthSettle == null}">class="icon icon-time _undone"</c:if> >icon</i>
                        <p class="bg-list col_6 font30 flow flex flex-between">
                        <span <c:if test="${orderSettlementSchedule.createMonthSettle != null}">class="col_blue"</c:if> >劳资员生成月度结算单</span>
                        <span class="font26">
                           <fmt:formatDate value="${orderSettlementSchedule.createMonthSettle}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </span>
                        </p>
                    </li>
                    <li class="mb30 clearfix posA">
                        <i class="icon icon-time _undone">icon</i>
                        <p class="bg-list col_6 font30 flow flex flex-between">
                        <span>财务下线打款</span>
                        <span class="font26">
                        </span>
                        </p>
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