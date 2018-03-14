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
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new/list.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new/mask.css" />
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/Worker/css/complaint/complaint.css">
</head>

<body>
    <div class="">
        <header>
            <a class="back_btn" href="${ctx}/app/worker/project-issue/list.php"></a>
            <h2 class="title">工程投诉</h2>
        </header>
        <!-- /header -->
        <section class="pad_top11 pad_top88 bg_f">
            <p class="font30 col_3 pt34 pb34 pl30">${customerInfo}</p>


            <c:forEach items="${mapList}" var="item">

                <div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
                    <ul class="pad_top3 pad_left3 bor_dotted">
                        <li class="mar_btm24 clearfix">
                            <span class="col_grey font28 flo_left w2">问题分类：</span>
                            <p class="font28 col_3 pad_rgt3 flow">${item.typeName}</p>
                        </li>
                        <li class="mar_btm24 clearfix">
                            <span class="col_grey font28 flo_left w2">问题事项：</span>
                            <p class="font28 col_3 pad_rgt3 flow">${item.itemName}</p>
                        </li>
                        <li class="mar_btm24 clearfix">
                            <span class="col_grey font28 flo_left w2">问题创建时间：</span>
                            <p class="font28 col_3 pad_rgt3 flow"> <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </p>
                        </li>
                        <li class="mar_btm24 clearfix">
                            <span class="col_grey font28 flo_left w2">问题描述：</span>
                            <p class="font28 col_3 pad_rgt3 flow">${item.problemDescribe}</p>
                        </li>
                        <li class="mar_btm24 clearfix">
                            <span class="col_grey font28 flo_left w2">工单状态：</span>
                            <p class="font28 pad_rgt3 flow"> ${fns:getDictLabel(item.problemStatus, 'project_problem_status', '')}</p>
                        </li>
                        <li class="mar_btm24 clearfix">
                            <span class="col_grey font28 flo_left w2">附件：</span>
                            <p class="font28 col_blue pad_rgt3 flow"><a href="${ctx}/app/worker/project-issue/checkPic?relatedId=${item.relatedId}" class="col_blue">查看图片 &gt;&gt; </a></p>
                        </li>
                    </ul>
                    <c:if test="${item.dealStatus==0 and item.problemStatus!=30}">
                        <div class="clearfix">
                            <a class="one_btn" href="#" onclick="applyProblem(${item.handleId})">确认接收</a>
                        </div>

                    </c:if>

                    <c:if test="${item.dealStatus!=0 or item.problemStatus==30}">
                        <div class="clearfix">
                            <a class="one_btn grey_btn" href="#">已处理</a>
                        </div>

                    </c:if>

                </div>

            </c:forEach>

        </section>
        <div style="padding-bottom:300px;"></div>
        <div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id="mask">
            <div class="alertMask">
                <div class="maskWrapper">
                    <p class="col_3 maskTit">提示</p>
                    <div class="font28 col_6 maskContent">接收成功，请尽快整改！并让项目经理完成验收！</div>
                    <div class="maskBtns clearfix">
                        <a class="maskBtn font33 col_f"  onclick="toList()" href="javascript:;">确定</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/modules/complaint.js"></script>

<script type="text/javascript">

    var baseWorkerUrl ="${ctx}/app/worker/";

</script>
</body>

</html>