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
	<title>施工进度</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/team.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/buildProgressList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/none.css"/>
</head>
<body>
	<div class="font0 order-none">
		<header>
			<a class="back_btn" href="${ctx }/app/home/isLogin"></a>
			<h2 class="title">施工进度</h2>
		</header><!-- /header -->
		<section class="total ">
		<c:if test="${ not empty orderList}">
			<div class="nav_sec">
				<div class="mask undis"></div>
				<div class="nav_box">
				
					<a class="nav_bar font0">
						<span class="select font28 col_3">${info.communityName}-${info.buildNumber}-${info.buildUnit}-${info.buildRoom}</span>
						<img class="select_btn" src="${ctxStatic}/mobile/modules/home/images/select_btn.png" alt="">
					</a>
					<div class="options bg_f undis">
					
					<c:forEach items="${orderList }" var="order">
					<c:if test="${order.orderId !=info.orderId }">
					<a class="col_blue font24" href="javascript:void(0)">${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}
						<input type="text" hidden="hidden"  value="${order.orderId}" id="orderIdObj" name="orderId"/>
						<c:if test="${order.viewLogCount ==0 }">
						( <span class="col_red">1</span> )
						</c:if>
						</a>
					</c:if>
					</c:forEach>
					</div>
				</div>
			</div>
			</c:if>
			<c:if test="${ not empty none}">
			<div class="img-wrapper">
				<p class="tips col_grey">这里什么都没有耶！</p>
			</div>
			</c:if>
			
			
			<c:if test="${ not empty info}">
			<div class="mar_top24 mar_left46 pad_rgt36 border_left relative">
				<c:if test="${info.viewLogCount!=0 }">
				<div class="blue-left"></div>
				
				<ul class="wrapper relative" onclick="detail('${info.orderId}')">
					<li class="pad_btm24">
						<span class="font30 col_6">施工节点：</span>
						<span class="font30 col_blue">确认开工</span>
					</li>
					<li class="mar_top24 pad_btm3">
						<span class="font30 col_6">项目经理：</span>
						<span class="font30 col_3">${info.managerName }</span>
					</li>
					<li class="pad_btm3">
						<span class="font30 col_6">合同开工：</span>
						<span class="font30 col_3">
						<fmt:formatDate value="${info.contractStartDate }" pattern="yyyy-MM-dd"/>
						</span>
					</li>
					<li>
						<span class="font30 col_6">实际开工：</span>
						<span class="font30 col_3">
						<fmt:formatDate value="${info.actualStartDate }" pattern="yyyy-MM-dd"/>
						</span>
					</li>
					<a class="tele_btn" href="tel:${info.managerPhone}">
						<img class="" src="${ctxStatic}/mobile/modules/home/images/tele.png" alt="拨打电话">
					</a>
				</ul>
				</c:if>
				<c:if test="${info.viewLogCount==0 }">
				<div class="blue-left"></div>
				
				<ul class="wrapper relative noRead" onclick="detail('${info.orderId}')">
					<li class="pad_btm24">
						<span class="font30 col_6">施工节点：</span>
						<span class="font30 col_blue">确认开工</span>
					</li>
					<li class="mar_top24 pad_btm3">
						<span class="font30 col_6">项目经理：</span>
						<span class="font30 col_3">${info.managerName }</span>
					</li>
					<li class="pad_btm3">
						<span class="font30 col_6">合同开工：</span>
						<span class="font30 col_3">
						<fmt:formatDate value="${info.contractStartDate }" pattern="yyyy-MM-dd"/>
						</span>
					</li>
					<li>
						<span class="font30 col_6">实际开工：</span>
						<span class="font30 col_3">
						<fmt:formatDate value="${info.actualStartDate }" pattern="yyyy-MM-dd"/>
						</span>
					</li>
					<a class="tele_btn" href="tel:${info.managerPhone}">
						<img class="" src="${ctxStatic}/mobile/modules/home/images/tele.png" alt="拨打电话">
					</a>
				</ul>
				</c:if>
			</div>
			</c:if>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
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
				var orderObj = $(this).find("input[name=orderId]").val(); 
				query_order_by_order_id(orderObj)
				$('.options').addClass('undis');
				$('.mask').addClass('undis');
				$('.nav_bar span').text(address);
			});
		});
		
		function detail(orderId){
			window.location.href="${ctx}/app/home/projectprogress/findConfirmStartInfoByOrderId.php?orderId="+orderId;
		}
		function query_order_by_order_id(orderObj){
			
			window.location.href="${ctx}/app/home/projectprogress/list.php?orderId="+orderObj
			
			
		}
		
	</script>
</body>
</html>