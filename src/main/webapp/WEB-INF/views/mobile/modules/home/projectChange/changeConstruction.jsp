<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/mobile/modules/home/footer.jsp" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>施工变更</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/footer.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/report_list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/none.css"/>
</head>
<body>
	<div class="order-none">
		<header>
			<a class="back_btn" href="${ctx }/app/home/isLogin"></a>
			<h2 class="title">施工变更</h2>
		</header><!-- /header -->
		<section class="total">
			<div class="nav_sec">
				<div class="mask undis"></div>
				<div class="nav_box">
					<a class="nav_bar font0">
						<span class="select font28 col_3">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }</span>
						<img class="select_btn" src="${ctxStatic}/mobile/modules/home/images/select_btn.png" alt="">
					</a>
					<div class="options bg_f undis">
						<c:forEach items="${list }" var="orderList">
							<c:if test="${order.orderId==orderList.orderId}">
								<a class="col_blue font24" href="${ctx }/app/home/NewApplyProjectChange/list?orderId=${orderList.orderId}">
									${orderList.communityName }-${orderList.buildNumber }-${orderList.buildUnit }-${orderList.buildRoom } 
									<c:if test="${orderList.number!='0' }">
										<span class="col_red">( ${orderList.number } )</span>
									</c:if>
								</a>
							</c:if>
							<c:if test="${order.orderId!=orderList.orderId}">
								<a class="col_grey font24" href="${ctx }/app/home/NewApplyProjectChange/list?orderId=${orderList.orderId}">
									${orderList.communityName }-${orderList.buildNumber }-${orderList.buildUnit }-${orderList.buildRoom } 
									<c:if test="${orderList.number!='0' }">
										<span class="col_red">( ${orderList.number } )</span>
									</c:if>
								</a>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
			<c:if test="${exists=='1' }">
				<ul class="members font0">
					<c:forEach items="${order.projectChangeList }" var="projectChange">
						<c:if test="${projectChange.projectChangeId!=null }">
							
							<li class="member_li pad_top3 bg_f">
								<ul class="mar_left3 mar_rgt3 dot_line">
									<li class="mar_btm24 clearfix">
										<span class="font28 col_grey fl_left">订单编号：</span>
										<p class="font28 col_3">${order.orderNumber }</p>
									</li>
									<li class="mar_btm24 clearfix">
										<span class="font28 col_grey fl_left">变更申请人：</span>
										<p class="font28 col_3">${order.itemManager }-${order.itemManagerPhone }</p>
									</li>
									<li class="mar_btm24 clearfix">
										<span class="font28 col_grey fl_left">申请日期：</span>
										<p class="font28 col_3"><fmt:formatDate value="${projectChange.applyDate }" pattern="yyyy-MM-dd"/></p>
									</li>
									<li class="mar_btm24 clearfix">
										<span class="font28 col_grey fl_left">增项总价：</span>
										<p class="font28 col_3">${projectChange.addItemTotalPrice }元</p>
									</li>
									<li class="mar_btm24 clearfix">
										<span class="font28 col_grey fl_left">减项总价：</span>
										<p class="font28 col_3">${projectChange.subItemTotalPrice }元</p>
									</li>
								</ul>
								<a class="tele_btn" href="tel:${order.itemManagerPhone }">
									<img class="" src="${ctxStatic}/mobile/modules/home/images/tele.png" alt="拨打电话">
								</a>
								<a class="wtc_btn font28" href="${ctx }/app/home/NewApplyProjectChange/details?projectChangeId=${projectChange.projectChangeId}">查看变更</a>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${exists=='0' }">
				<div class="img-wrapper">
					<p class="tips col_grey">这里什么都没有耶！</p>
				</div>
			</c:if>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
		var ordersLength = '${ordersLength}';
		if(ordersLength == 1){
			$(".options").hide();
			$(".select_btn").hide();
			$(".mask").hide();
		}
		/* $('.options a').css({'color':'#7f7f7f'});
		for (var i = 0; i < $('.options a').length; i++) {
			if($('.options a').eq(i).text() == $('.select').text()){
				$('.options a').eq(i).css({'color':'#0780ec'});
			}
		} */
		$(function(){
			$(document).on('click', '.nav_bar', function(){
				$('.nav_box').css({'z-index': 10});
				if($('.options').hasClass("undis")){
					$('.options').removeClass('undis');
					$('.mask').removeClass('undis');
				}else{
					$('.options').addClass('undis');
					$('.mask').addClass('undis');
				}
			});
			$(document).on('click', '.options a', function(){
				$('.nav_box').removeAttr("style");
				var address = $(this).text();
				console.log($(this));
				$('.options').addClass('undis');
				$('.mask').addClass('undis');
				$('.nav_bar span').text(address);
			});
		});
	</script>
</body>
</html>