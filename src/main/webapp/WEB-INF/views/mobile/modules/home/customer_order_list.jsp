<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/mobile/modules/home/footer.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/common.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/globalUtil.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/bxyTopCom.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/setIndex.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/bxyMask.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/changeConstrucionwarp.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/Bxy_oeder.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
    <title>我的订单</title>
</head>
<body>
<div class="bxyOrder_warp">
    <!--顶部公共样式start-->
    <div class="bxy_setIndex_warp_header">
        <header><a class="" href="${ctx}/app/home/isLogin"><span class="icon_back"></span></a><p>我的订单</p></header>
    </div>
    <!--顶部公共结构end-->
    <div class="bxyOrder_warp_con" style="padding-top:.88rem;">
        <c:forEach items ="${orders }" var="order">
	        <div class="bxyOrder_warp_con_first">
	                <div class="bxyOrder_warp_con_first_top">
	                	<c:if test="${order.houseIsNew == '1' }">
	                   	 	<i class="oldHouse"></i>
	                    </c:if>
	                    <c:if test="${order.houseIsNew == '0' }">
	                   	 	<i class="newsHouse"></i>
	                    </c:if>
	                    <h3><i class="infoBlue"></i><span>订单号:</span><i class="col_blue ml10">${order.orderNumber }</i></h3>
	                </div>
	                <div class="bxyOrder_warp_con_first_bottom">
	                    <ul>

		                        <li><i>客户姓名：</i> <span>${order.customerName }</span></li>
		                        <li><i>设 计  师 ：</i> <span>${order.designerName }-${order.designerPhone }</span></li>
		                        <li><i>项目经理：</i> <span><c:if test="${ not empty order.itemManager }"> ${order.itemManager }-${order.itemManagerPhone } </c:if></span></li>
		                        <li><i>小区名称：</i> <span>${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }</span></li>

	                    </ul>
	                    <p> <a href="${ctx}/app/home/orderDetails?orderId=${order.id}"><img src="${ctxStatic}/mobile/modules/home/images/backLeft.png" alt="" ></a></p>
	                </div>
	        </div>
        </c:forEach>
       <%--  <div class="bxyOrder_warp_con_first">
            <div class="bxyOrder_warp_con_first_top">
                <i class="oldHouse"></i>
                <h3><i class="infoBlue"></i><span>订单号:</span><i class="col_blue ml10">JPD12456892</i></h3>
            </div>
            <div class="bxyOrder_warp_con_first_bottom">
                <ul>
                    <a href="#">
                        <li><i>客户姓名：</i> <span>张玉华</span></li>
                    <li><i>设计师：</i> <span>孙伟-133333333</span></li>
                    <li><i>项目经理：</i> <span>孙伟-133333333</span></li>
                    <li><i>客户姓名：</i> <span>南湖西里-2-3-102</span></li>
                    </a>
                </ul>
                <p><img src="${ctxStatic}/mobile/modules/home/images/backLeft.png" alt=""></p>
            </div>
        </div> --%>
    </div>
    <!--底部公共结构start-->
  <!--   <div class="bxy_setIndex_warp_footer">
        <footer class="clearfix">
            <div class=" fl myIndex_box">
                <p class=" myIndex"><span class="icon_myHome"></span>首页</p>
            </div>
            <div class=" myHome_boxC fl">
                <p class="myHome"><span class="icon_myIndex"></span>我的</p>
            </div>
        </footer>
    </div> -->
    <!--底部公共结构end-->
</div>
</body>
<script src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/global.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/setIndex.js"></script>
</html>