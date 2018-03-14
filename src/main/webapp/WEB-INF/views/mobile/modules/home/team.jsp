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
	<title>项目团队</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/footer.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/team.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/home/isLogin"></a>
			<h2 class="title">项目团队</h2>
		</header><!-- /header -->
		<section class="total">
			<div class="nav_sec">
				<div class="mask undis"></div>
				<div class="nav_box">
					<a class="nav_bar font0">
						<span class="select font28 col_3">${order1.communityName }-${order1.buildNumber }-${order1.buildUnit }-${order1.buildRoom }</span>
						<img class="select_btn" src="${ctxStatic}/mobile/modules/home/images/select_btn.png" alt="">
					</a>
					<div class="options bg_f undis">
						<c:forEach items="${ orders }" var="order" begin="0" end="0">
							<a class="col_blue font24" href="javascript:;" onclick="gaibian('${order.id }')" >
								${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }
							</a>
						</c:forEach>
						<c:forEach items="${ orders }" var="order" begin="1" end="${fn:length(orders)}">
							<a class="col_grey font24" href="javascript:void(0)" onclick="gaibian('${order.id }')" >
								${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }<!-- ( <span class="col_red">1</span> ) -->
							</a>
						</c:forEach>
					</div>
				</div>
			</div>
			<ul class="members">
				<li class="bg_f shadow mb24">
					<div class="left">
						<img src="${ctxStatic}/mobile/modules/home/images/server.png" alt="">
					</div>
					<div class="right font0">
						<div class="dot_line">
							<p class="name font28"><span class="col_grey">客服经理：</span><span class="col_3">${order1.serviceName }</span></p>
							<p class="tele_num font22"><span class="col_grey">电话：</span><span class="col_3">${order1.servicePhone }</span></p>
						</div>
						<a class="tele" href="tel:${order1.servicePhone }">
							<img class="phone" src="${ctxStatic}/mobile/modules/home/images/call.png" alt="拨打电话">
						</a>
					</div>
				</li>
				<li class="bg_f shadow mb24">
					<div class="left">
						<img src="${ctxStatic}/mobile/modules/home/images/design.png" alt="">
					</div>
					<div class="right font0">
						<div class="dot_line">
							<p class="name font28"><span class="col_grey">设计师：</span><span class="col_3">${order1.designerName }</span></p>
							<p class="tele_num font22"><span class="col_grey">电话：</span><span class="col_3">${order1.designerPhone }</span></p>
						</div>
						<a class="tele" href="tel:${order1.designerPhone }">
							<img class="phone" src="${ctxStatic}/mobile/modules/home/images/call.png" alt="拨打电话">
						</a>
					</div>
				</li>
				<li class="bg_f shadow mb24 clearfix">
					<div class="left">
						<img src="${ctxStatic}/mobile/modules/home/images/manager.png" alt="">
					</div>
					<div class="right font0">
						<div class="dot_line">
							<p class="name font28"><span class="col_grey">项目经理：</span><span class="col_3">${order1.itemManager }</span></p>
							<p class="tele_num font22"><span class="col_grey">电话：</span><span class="col_3">${order1.itemManagerPhone }</span></p>
							<div class="star0">
								<!-- <span class="bling"></span>
								<span class="bling"></span>
								<span class="bling"></span>
								<span class="bling"></span>
								<span class="bling"></span> -->
								<c:if test="${order1.managerStar == null || order1.managerStar == 0 }">
									<span></span>
									<span></span>
									<span></span>
									<span></span>
									<span></span>
								</c:if>
								<c:if test="${order1.managerStar != null && order1.managerStar !=0 }">
									<c:forEach begin="0" end="${order1.managerStar-1}" step="1">
										<span class="bling"></span>
									</c:forEach>
									<c:forEach begin="1" end="${5-order1.managerStar }" step="1">
										<span></span>
									</c:forEach>
								</c:if>
							</div>
						</div>
						<a class="tele" href="tel:${order1.itemManagerPhone }">
							<img class="phone" src="${ctxStatic}/mobile/modules/home/images/call.png" alt="拨打电话">
						</a>
					</div>
				</li>
				<li class="bg_f shadow mb24 clearfix">
					<div class="left">
						<img src="${ctxStatic}/mobile/modules/home/images/pqc.png" alt="">
					</div>
					<div class="right font0">
						<div class="dot_line">
							<p class="name font28"><span class="col_grey">质检员：</span><span class="col_3">${order1.orderInspector }</span></p>
							<p class="tele_num font22"><span class="col_grey">电话：</span><span class="col_3">${order1.orderInspectorPhone }</span></p>
							<div class="star0">
								<c:if test="${order1.inspectorStar == null || order1.inspectorStar == 0 }">
									<span></span>
									<span></span>
									<span></span>
									<span></span>
									<span></span>
								</c:if>
								<c:if test="${order1.inspectorStar != null && order1.inspectorStar !=0 }">
									<c:forEach begin="0" end="${order1.inspectorStar-1}" step="1">
										<span class="bling"></span>
									</c:forEach>
									<c:forEach begin="1" end="${5-order1.inspectorStar }" step="1">
										<span></span>
									</c:forEach>
								</c:if>
							</div>
						</div>
						<a class="tele" href="tel:${order1.orderInspectorPhone }">
							<img class="phone" src="${ctxStatic}/mobile/modules/home/images/call.png" alt="拨打电话">
						</a>
					</div>
				</li>
				<c:forEach items="${teams }" var="team">
					<li class="bg_f shadow mb24 clearfix">
						<div class="left">
							<img src="${ctxStatic}/mobile/modules/home/images/worker.png" alt="">
						</div>
						<div class="right font0">
							<div class="">
								<p class="mt70 font28"><span class="col_grey">工人：</span><span class="col_3">${team.workerName }</span></p>
								<p class="mt30 tele_num font22"><span class="col_grey">电话：</span><span class="col_3">${team.workerPhone }</span></p>
								<div class="star">
									<c:if test="${team.workerStar  == null || team.workerStar == 0 }">
										<span></span>
										<span></span>
										<span></span>
										<span></span>
										<span></span>
									</c:if>
									<c:if test="${team.workerStar != null && team.workerStar !=0 }">
										<c:forEach begin="0" end="${team.workerStar-1}" step="1">
											<span class="bling"></span>
										</c:forEach>
										<c:forEach begin="1" end="${5-team.workerStar }" step="1">
											<span></span>
										</c:forEach>
									</c:if>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</section>
		<!-- <footer>
			<a class="home_btn" href="javascript:void(0)">首页</a>
			<a class="mine_btn active" href="javascript:void(0)">我的</a>
		</footer> -->
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<!-- <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/global.js"></script> -->
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/history.js"></script>
	<script type="text/javascript">
		var ordersLength = '${ordersLength}';
		//alert(ordersLength);
		if(ordersLength == 1){
			$(".options").hide();
			$(".select_btn").hide();
			$(".mask").hide();
		}
		
		$(function(){
			/* $(document).ready(function(){
				loadFirst();
			}); */
			$(document).on('click', '.nav_bar', function(){
				$('.nav_box').css({
					'z-index': 10
				});
				$('.options a').css({'color':'#7f7f7f'});
				for (var i = 0; i < $('.options a').length; i++) {
					if($('.options a').eq(i).text() == $('.select').text()){
						$('.options a').eq(i).css({'color':'#0780ec'});
					}
				} 
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
				//var orderId = $(this).find('input').val();
				//console.log(address);
				$('.options').addClass('undis');
				$('.mask').addClass('undis');
				$('.nav_bar span').text(address);
			});
		}); 
	 	/* function loadTeam(){
			alert($('#orderId').val());
		} */
	</script>
</body>
</html>