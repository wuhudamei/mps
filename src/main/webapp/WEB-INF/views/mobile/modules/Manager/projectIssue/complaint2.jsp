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
    <title>朝阳区华纺易城-3-3-802-郝斐</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new/mask.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/order/orderIndex.css" />
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/complaint/complaint.css">
</head>

<body>
    <div class="">
        <header>
            <a class="back_btn" href="${ctx}/app/manager/project-issue/list.php"></a>
            <h2 class="title">${customerInfo}</h2>
        </header>
        <!-- /header -->
        <section class="pad_top11 pad_top88">

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
                        <p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></p>
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
                        <span class="col_grey font28 flo_left w2">被投诉人员：</span>
                        <p class="font28 pad_rgt3 flow">${fns:getDictLabel(item.dealPersonType, 'deal_person_type', '')}</p>
                    </li>
                    <li class="mar_btm24 clearfix">
                        <span class="col_grey font28 flo_left w2">附件：</span>
                        <p class="font28 col_blue pad_rgt3 flow"><a href="${ctx}/app/manager/project-issue/checkPic?relatedId=${item.relatedId}" class="col_blue">查看图片 &gt;&gt; </a></p>
                    </li>
                </ul>
                <div class="clearfix">
                    <c:if test="${item.isWorkerDone>0}">

                        <a class="one_btn" href="${ctx}/app/manager/project-issue/findWorkerInfoByRelatedId?relatedId=${item.relatedId}">工人反馈</a>

                        <c:if test="${item.dealStatus==20}">

                            <a class="one_btn col_w" href="${ctx}/app/manager/project-issue/managerCheckDealDetail?handleId=${item.handleId}">查看答复</a>
                        </c:if>


                        <c:if test="${item.dealStatus!=20}">

                            <a class="one_btn col_w" href="${ctx}/app/manager/project-issue/managerDealByHandleId?handleId=${item.handleId}">投诉答复</a>
                        </c:if>

                    </c:if>






                    <c:if test="${item.isWorkerDone <1 or   empty item.isWorkerDone}">
                        <c:if test="${item.dealPersonType==2}">

                            <a class="one_btn grey_btn" href="javascript:;">工人反馈</a>

                            <c:if test="${item.dealStatus==20}">

                                <a class="one_btn col_w" href="${ctx}/app/manager/project-issue/managerCheckDealDetail?handleId=${item.handleId}">查看答复</a>
                            </c:if>


                            <c:if test="${item.dealStatus!=20}">

                                <a class="one_btn col_w" href="${ctx}/app/manager/project-issue/managerDealByHandleId?handleId=${item.handleId}">投诉答复</a>
                            </c:if>

                        </c:if>



                        <c:if test="${item.dealPersonType==1}">

                            <a class="one_btn grey_btn" href="javascript:;">工人反馈</a>

                            <c:if test="${item.dealStatus==20}">

                                <a class="one_btn col_w" href="${ctx}/app/manager/project-issue/managerCheckDealDetail?handleId=${item.handleId}">查看答复</a>
                            </c:if>


                            <c:if test="${item.dealStatus!=20}">

                                <a class="one_btn col_w" href="${ctx}/app/manager/project-issue/managerDealByHandleId?handleId=${item.handleId}">投诉答复</a>
                            </c:if>

                        </c:if>


                    </c:if>




                </div>
            </div>

            </c:forEach>

        </section>
        <div style="padding-bottom:300px;"></div>


        <div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id="mask">
            <div class="alertMask">
                <div class="maskWrapper">
                    <p class="col_3 maskTit">提示</p>
                    <div class="font28 col_6 maskContent">待工人反馈后才可以进行投诉答复</div>
                    <div class="maskBtns clearfix">
                        <a class="maskBtn font33 col_f"  onclick="hide()" href="javascript:;">确定</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>


<script type="text/javascript">
    function show(){

$("#mask").show();
    }

    function hide(){
        $("#mask").hide();

    }


</script>
</body>

</html>