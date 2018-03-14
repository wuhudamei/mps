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
	<title>我要评价</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/footer.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/team.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/comment.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx }/app/home/isLogin"></a>
			<h2 class="title">我要评价</h2>
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
								<a class="col_blue font24" href="${ctx }/app/home/evaluate/evalWorker/list?orderId=${orderList.orderId}">
									${orderList.communityName }-${orderList.buildNumber }-${orderList.buildUnit }-${orderList.buildRoom } 
									<c:if test="${orderList.number!='0' }">
										<span class="col_red">( ${orderList.number } )</span>
									</c:if>
								</a>
							</c:if>
							<c:if test="${order.orderId!=orderList.orderId}">
								<a class="col_grey font24" href="${ctx }/app/home/evaluate/evalWorker/list?orderId=${orderList.orderId}">
									${orderList.communityName }-${orderList.buildNumber }-${orderList.buildUnit }-${orderList.buildRoom } 
									<c:if test="${orderList.number!='0' }">
										<span class="col_red">( ${orderList.number } )</span>
									</c:if>
								</a>
							</c:if>
						</c:forEach>
				</div>
			</div>
			<ul class="members">
				<c:forEach items="${evaluateWorkerList }" var="item">
					<%-- <li class="bg_f shadow mb24 rele clearfix">
						<div class="left">
							<img src="${ctxStatic}/mobile/modules/home/images/manager.png" alt="">
						</div>
						<div class="right font0">
							<div class="">
								<p class="mt70 font28"><span class="col_grey">项目经理：</span><span class="col_3">目经理</span></p>
								<p class="mt30 tele_num font22"><span class="col_grey">竣工完成</span></p>
								<a href="javascript:;" class="comment-btn">去评价</a>
							</div>
						</div>
					</li> --%>
					<c:if test="${item.count==0 }">
						<li class="bg_f shadow mb24 rele clearfix">
							<div class="left">
								<img src="${ctxStatic}/mobile/modules/home/images/worker.png" alt="">
							</div>
							<div class="right font0">
								<div class="">
									<p class="mt70 font28"><span class="col_grey">工人姓名：</span><span class="col_3">${item.groupRealname }</span></p>
									<p class="mt30 tele_num font22"><span class="col_grey">${item.packageName }</span></p>
									<a href="javascript:;" class="comment-btn" onclick="toEvaluate('${item.evalTaskpackScoreId}')">去评价</a>
								</div>
							</div>
						</li>
					</c:if>
					<%-- <li class="bg_f shadow mb24 rele clearfix">
						<div class="left">
							<img src="${ctxStatic}/mobile/modules/home/images/manager.png" alt="">
						</div>
						<div class="right font0">
							<div class="">
								<p class="mt70 font28"><span class="col_grey">项目经理：</span><span class="col_3">目经理</span></p>
								<p class="mt30 tele_num font22"><span class="col_grey">竣工完成</span></p>
								<a href="javascript:;" class="comment-det">查看评价</a>
							</div>
						</div>
					</li> --%>
					<c:if test="${item.count!=0 }">
						<li class="bg_f shadow mb24 rele clearfix">
							<div class="left">
								<img src="${ctxStatic}/mobile/modules/home/images/worker.png" alt="">
							</div>
							<div class="right font0">
								<div class="">
									<p class="mt70 font28"><span class="col_grey">工人姓名：</span><span class="col_3">${item.groupRealname }</span></p>
									<p class="mt30 tele_num font22"><span class="col_grey">${item.packageName }</span></p>
									<a href="javascript:;" class="comment-det" onclick="toDetails('${item.evalTaskpackScoreId}')">查看评价</a>
								</div>
							</div>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</section>
		<!-- <footer>
			<a class="home_btn active" href="javascript:void(0)">首页</a>
			<a class="mine_btn" href="javascript:void(0)">我的</a>
		</footer> -->
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<!-- <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/global.js"></script> -->
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
				$('.options a').removeClass('col_blue');
				$(this).removeClass('col_grey').addClass('col_blue');
				var address = $(this).text();
				console.log($(this));
				$('.options').addClass('undis');
				$('.mask').addClass('undis');
				$('.nav_bar span').text(address);
			});
		});
		
		function toEvaluate(id){
			window.location.href="${ctx}/app/home/evaluate/evalWorker/toEvaluate?evalTaskpackScoreId="+id;
		}
		
		function toDetails(id){
			window.location.href="${ctx}/app/home/evaluate/evalWorker/toDetails?evalTaskpackScoreId="+id;
		}
	</script>
</body>
</html>