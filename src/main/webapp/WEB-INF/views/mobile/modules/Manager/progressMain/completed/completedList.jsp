<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>申请竣工</title>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/ensure.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new/mask.css"/>
</head>

<body>
<div class="g-ensure">
    <header>
        <a class="back_btn" href="${ctx}/app/manager/progressList"></a>
        <h2 class="title">申请竣工</h2>
    </header>
    <section class="ensure_list">
        <c:forEach items="${orderList}" var="orderList">
            <ul class="shadow">
                <li class="clearfix">
                    <span>顾客信息：</span>
                    <p>${orderList.communityName }-${orderList.buildNumber }-${orderList.buildUnit }-${orderList.buildRoom }-${orderList.customerName }</p>
                </li>
                <li class="clearfix">
                    <span>实际开工日期：</span>
                    <p><fmt:formatDate type="date" value="${orderList.actualStartDate }"/></p>
                </li>
                <li class="clearfix">
                    <span>计划竣工时间：</span>
                    <p><fmt:formatDate type="date" value="${orderList.contractEndDate }"/></p>
                </li>
                <li class="clearfix">
                    <span>订单状态：</span>
                    <p class="col_blue">${fns:getDictLabel(orderList.orderStatusNumber, 'order_status', '')}</p>
                </li>
                <c:if test="${orderList.orderStatusNumber >= '300'}">
                    <a class="ensure_btn" href="${ctx }/app/manager/completedtDetail?orderID=${orderList.id}">查看详情</a>
                </c:if>
                <c:if test="${(orderList.orderStatusNumber >= '200' && orderList.orderStatusNumber < '300') ||
							orderList.orderStatusNumber == '330'}">
                    <%-- <a class="ensure_btn" href="${ctx }/app/manager/applyCompleted?orderID=${orderList.id}">申请竣工</a> --%>
                    <a class="ensure_btn" onclick="applyFinish('${orderList.id}',${orderList.projectMode })">申请竣工</a>
                </c:if>
            </ul>
        </c:forEach>
    </section>


    <div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id="mask">
        <div class="alertMask">
            <div class="maskWrapper">
                <p class="col_3 maskTit">提示</p>
                <div class="font28 col_6 maskContent"></div>
                <div class="maskBtns clearfix">
                    <a class="maskBtn font33 col_f" href="javascript:;" id="sureId">确定</a>
                </div>
            </div>
        </div>
    </div>


</div>

<script type="text/javascript">


    function hide() {
        $("#mask").hide()

    }
    function applyFinish(orderId, projectMode) {


            if (projectMode == '2') {
                $.ajax({
                    url: "${ctx }/app/manager/checkCompleted",
                    data: {"orderId": orderId},
                    type: "post",
                    success: function (data) {
                        if (data == 0) {
                            window.location.href = "${ctx }/app/manager/applyCompleted?orderID=" + orderId;
                        } else {
                            $(".maskContent").text("您还有未通过的结算阶段!");
                            $("#mask").show();
                            $("#sureId").bind("click", hide);

                        }
                    }
                });

            } else {
                $.ajax({
                    url: "${ctx }/app/manager/isAllChecked",
                    data: {"orderId": orderId},
                    type: "post",
                    success: function (data) {
                        if (data == 1) {

                            window.location.href = "${ctx }/app/manager/applyCompleted?orderID=" + orderId;
                        } else {
                            $(".maskContent").text("您还有未确认验收的约检节点!");
                            $("#mask").show();
                            $("#sureId").bind("click", hide);


                        }
                    }
                });
            }






    }
</script>
</body>
</html>