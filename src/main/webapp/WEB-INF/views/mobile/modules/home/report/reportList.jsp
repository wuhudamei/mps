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
	<title>质检报告</title>
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
			<a class="back_btn" href="${ctx }/app/home/isLogin" ></a>
			<h2 class="title">质检报告</h2>
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
								<a class="col_blue font24" href="${ctx }/app/home/report/list?orderId=${orderList.orderId}">
									${orderList.communityName }-${orderList.buildNumber }-${orderList.buildUnit }-${orderList.buildRoom } 
									<c:if test="${orderList.number!='0' }">
										<span class="col_red">( ${orderList.number } )</span>
									</c:if>
								</a>
							</c:if>
							<c:if test="${order.orderId!=orderList.orderId}">
								<a class="col_grey font24" href="${ctx }/app/home/report/list?orderId=${orderList.orderId}">
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
					<c:forEach items="${order.reportList }" var="report">
						<c:if test="${report.qcBillId!=null }">
							<c:if test="${report.viewCount=='0' }">
								<li class="item member_li pad_top3 bg_f">
							</c:if>
							<c:if test="${report.viewCount!='0' }">
								<li class="member_li pad_top3 bg_f">
							</c:if>
								<ul class="mar_left3 mar_rgt3 dot_line">
								
									<li class="mar_btm24">
										<span class="font28 col_grey fl_left">检查内容：</span>
										<p class="font28 col_3">${report.qcCheckNodeName }</p>
									</li>
									<li class="mar_btm24">
										<span class="font28 col_grey fl_left">项目经理：</span>
										<p class="font28 col_3">${order.itemManager }-${order.itemManagerPhone }</p>
									</li>
									<li class="mar_btm24">
										<span class="font28 col_grey fl_left">质 检 员：</span>
										<p class="font28 col_3">${order.orderInspector }-${order.orderInspectorPhone }</p>
									</li>
									<li class="mar_btm24">
										<span class="font28 col_grey fl_left">检查类型：</span>
										<c:if test="${report.isRecheck=='0' }">
											<p class="font28 col_3">约检</p>
										</c:if>
										<c:if test="${report.isRecheck=='1' }">
											<p class="font28 col_3">复检</p>
										</c:if>
									</li>
									<li class="mar_btm24">
										<span class="font28 col_grey fl_left">检查日期：</span>
										<p class="font28 col_3"><fmt:formatDate value="${report.checkDatetime }" pattern="yyyy-MM-dd"/></p>
									</li>
								</ul>
								<a class="tele_btn">
									<img class="" src="${ctxStatic}/mobile/modules/home/images/tele.png" alt="拨打电话">
								</a>
								<%--<a class="wtc_btn font28" href="${ctx }/app/home/report/details?qcBillId=${report.qcBillId}">查看报告</a>--%>
								<p class="wtc_btn font28">验收合格</p>
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
	<div class="mask1 undis">
		<div class="wrapper bg_f font0">
			<p class="tit_mask font30 col_3">选择您要拨打的号码</p>
			<div class="contact">
				<span class="font26 col_grey">项目经理：</span><span class="font26 col_lb">${order.itemManager }</span>
				<a class="tele_mask font0" href="tel:${order.itemManagerPhone }">
					<img class="tele_icon" src="${ctxStatic}/mobile/modules/home/images/tele-mask.png" alt=""><span>${order.itemManagerPhone }</span>
				</a>
			</div>
			<div class="contact">
				<span class="font26 col_grey">质 检 员：</span><span class="font26 col_lb">${order.orderInspector }</span>
				<a class="tele_mask font0" href="tel:${order.orderInspectorPhone }">
					<img class="tele_icon" src="${ctxStatic}/mobile/modules/home/images/tele-mask.png" alt=""><span>${order.orderInspectorPhone }</span>
				</a>
			</div>
			<a class="shut_btn" href="javascript:;">关闭</a>
		</div>
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
		$(function(){
			$(document).on('click', '.nav_bar', function(){
				$('.nav_box').css({
					'z-index': 10
				});
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
				console.log(address);
				$('.options').addClass('undis');
				$('.mask').addClass('undis');
				$('.nav_bar span').text(address);
			});
			$(document).on('click', '.tele_btn', function(){
				$('.mask1').removeClass('undis');
			});
			$(document).on('click', '.shut_btn', function(){
				$('.mask1').addClass('undis');
			});
		});
	</script>
</body>
</html>