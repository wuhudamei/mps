<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <title>申请约检</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/list.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new/mask.css"/>
</head>
<body>
<div class="">
    <header>
        <a class="back_btn" href="${ctx}/app/manager/qualityControlList"></a>
        <h2 class="title">申请约检</h2>
    </header><!-- /header -->
    <section class="pad_top11">
        <c:forEach items="${order }" var="order">
            <div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
                <ul class="pad_top3 pad_left3 bor_dotted">
                    <li class="mar_btm24 clearfix">
                        <span class="col_grey font28 flo_left">顾 客 信 息 ：</span>
                        <p class="font28 col_3 pad_rgt3 flow">${order.info }-${order.customerName }</p>
                    </li>
                    <li class="mar_btm24 clearfix">
                        <span class="col_grey font28 flo_left">合同开工日期：</span>
                        <p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${order.contractStartDate }"
                                                                              pattern="yyyy-MM-dd"/></p>
                    </li>
                    <li class="mar_btm24 clearfix">
                        <span class="col_grey font28 flo_left">实际开工日期：</span>
                        <p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${order.actualStartDate }"
                                                                              pattern="yyyy-MM-dd"/></p>
                    </li>
                    <li class="mar_btm24 clearfix">
                        <span class="col_grey font28 flo_left">合 同 工 期 ：</span>
                        <p class="font28 col_3 pad_rgt3 flow">${order.contractTime }天</p>
                    </li>
                    <li class="mar_btm24 clearfix">
                        <span class="col_grey font28 flo_left">订 单 状 态 ：</span>
                        <p class="font28 col_3 pad_rgt3 flow">${order.orderStatusDescription }</p>
                    </li>
                </ul>
                <div class="btn_wrapper clearfix">
                    <a class="two_btn1" href="#"
                       onclick="checkOrderAndApply('${order.customerName}',${order.id},${order.projectMode})">申请约检</a>
                    <a class="two_btn2" href="${ctx}/app/manager/qualityCheckRecord?id=${order.id}">申请记录</a>
                </div>
            </div>
        </c:forEach>
    </section>
    <%--<div style="padding-bottom:300px;"></div>--%>

    <%--<div class="one_btn">非常抱歉</div>--%>
    <%--<div style="padding-bottom:200px;"></div>--%>

    <div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id="mask">
        <div class="alertMask">
            <div class="maskWrapper">
                <p class="col_3 maskTit">提示</p>
                <div class="font28 col_6 maskContent"></div>
                <div class="maskBtns clearfix">
                    <a class="maskBtn font33 col_f" href="javascript:;" onclick="closeUndis()">确定</a>
                </div>
            </div>
        </div>
    </div>

</div>
</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>

<script type="text/javascript">


    function closeUndis() {

        $("#mask").hide();


    }


    function checkOrderAndApply(customerName, orderId, projectMode) {
        <%--window.location.href = "${ctx}/app/manager/qualityCheck?id=" + orderId;--%>



        //待上线

        if (4 == projectMode) {
            $.ajax({
                //准产业检查当前节点是否为基装节点, 如果是 查询是否申请了开关面板
                url: "${ctx}/app/manager/checkOrderApply",
                data: {orderId: orderId},
                success: function (data) {
                    if (2 != data) {
                        $(".maskContent").text(customerName + "家的还没有申请开关面板，请先申请开关面板后，再申请【" + data.split("-")[1] + "】。")

                        $("#mask").show();

                    } else {

                        window.location.href = "${ctx}/app/manager/qualityCheck?id=" + orderId;

                    }


                }


            })


        } else {
            window.location.href = "${ctx}/app/manager/qualityCheck?id=" + orderId;

        }


    }


</script>
</html>