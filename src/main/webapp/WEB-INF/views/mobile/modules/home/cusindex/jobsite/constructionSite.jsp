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
	<title>工地直播</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/swiper.min.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/constructionSite.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/none.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx }/app/home/isLogin"></a>
			<h2 class="title">工地直播</h2>
		</header><!-- /header -->
	</div>
	<section class="total order-none">
		<div class="nav_sec">
				<div class="mask1 undis"></div>
				<div class="nav_box">
		
					<a class="nav_bar font0">
						<span class="select font28 col_3">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }</span>
							<c:if test="${ not empty order.count}">
							( <span class="col_red">${order.count}</span> ) 
							</c:if>
						<img class="select_btn" src="${ctxStatic}/mobile/modules/home/images/select_btn.png" alt="">
					</a>
					
					<div class="options bg_f undis">
					<c:forEach items="${list }" var="waitOrder">
					
					<c:if test="${order.orderId==waitOrder.orderId }">
					
					<a class="col_blue font24" href="javascript:void(0)">
							${waitOrder.communityName }-${waitOrder.buildNumber }-${waitOrder.buildUnit }-${waitOrder.buildRoom }
							<c:if test="${ not empty waitOrder.count}">
							( <span class="col_red">${waitOrder.count}</span> ) 
							</c:if>
							<input type="text" hidden="hidden"  value="${waitOrder.orderId}" id="orderIdObj" name="orderId"/>
						
						</a>
					</c:if>
					<c:if test="${order.orderId!=waitOrder.orderId }">
					
					<a class="col_grey font24" href="javascript:void(0)">
							${waitOrder.communityName }-${waitOrder.buildNumber }-${waitOrder.buildUnit }-${waitOrder.buildRoom }
							<c:if test="${ not empty waitOrder.count}">
							( <span class="col_red">${waitOrder.count}</span> ) 
							</c:if>
							<input type="text" hidden="hidden"  value="${waitOrder.orderId}" id="orderIdObj" name="orderId"/>
						
						</a>
					</c:if>
						</c:forEach>
						
					</div>
				</div>
			</div>
			<c:if test="${not empty none}">
			<div class="img-wrapper">
				<p class="tips col_grey">这里什么都没有耶！</p>
			</div>
			</c:if>
			
			<c:if test="${empty none}">
		<div class="mt20 construct-state">
			<c:forEach items="${broadcast }" var="broadCast" varStatus="status">
			<div class="item">
				<p class="mb10"><fmt:formatDate value="${broadCast.broadcastTime}" pattern="yyyy-MM-dd"/></p>
				<div class="view-box">
				<c:if test="${broadCast.readStatus==1}">
				<div class="mb10 state new" id="${broadCast.broadcastId}">
						<span class="f26">${broadCast.broadcastName }</span>
					</div>
				
				</c:if>
				<c:if test="${broadCast.readStatus==0}">
				<div class="mb10 state">
						<span class="f26">${broadCast.broadcastName }</span>
					</div>
				
				</c:if>
					
					<div id="swipercontainer${status.index }" class="swiper-container swipercontainer${status.index }">
						<div class="swiper-wrapper">
						<c:forEach items="${broadCast.picList }" var="pic">
						
							<div class="swiper-slide" >
								<img src="${picPrefix}${pic.picUrl}"/>
							</div>
							</c:forEach>
							
						</div>
					</div>
					<input hidden="hidden" type="text" name="isRead" value="${broadCast.broadcastId}">
					
				</div>
			</div>
			
			</c:forEach>
		
		</div>
		</c:if>
		<div class="show-bigimg undis">
			<div class="mask"></div>
			<div class="content">
				<a class="back_btn" href="javascript:void(0)"></a>
				<div class="now-page">
					<div>
						<span class="current-page"></span>
						<span class="pre">/</span>
						<span class="all-page">9</span>
					</div>
				</div>
				<div class="construct-state-box">
					<div class="swiper-container construct-state-big">
					  <div class="swiper-wrapper">
					    <div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/home/images/constructionSite/biglunbo1.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/home/images/constructionSite/biglunbo1.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/home/images/constructionSite/biglunbo1.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/home/images/constructionSite/biglunbo1.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/home/images/constructionSite/biglunbo1.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/home/images/constructionSite/biglunbo1.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/home/images/constructionSite/biglunbo1.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/home/images/constructionSite/biglunbo1.png" />
							</div>
					  </div>
					  <div class="swiper-button-prev"></div>
	    			<div class="swiper-button-next"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/swiper.min.js"></script>
	<!-- <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/global.js"></script> -->
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
		
 	/**
 	 * [initSiwper description] 初始化小图轮播 ======== onTap事件触发大图轮播的初始化
 	 * @param  {[type]} el [description] 元素的类名
 	 * @return {[type]}    [description]
 	 */
  function initSiwper(el){
 		
  	new Swiper('.'+el, {
			freeMode : true,
			slidesPerView : 'auto',
			onTap: function(swiper){
	      var isRead= $("."+el).next();
	  //发送update方法 表名该播报单 客户已读
	 update_status($(isRead).val())
	 		
	      var showBigHtml = '';
	      for(var i=0, len=swiper.imagesToLoad.length; i<len; i++){
			  var a = swiper.imagesToLoad[i].naturalWidth,
			  	  b = swiper.imagesToLoad[i].naturalHeight,
				  c = $('.swiper-container').width(),
				  d = $('.swiper-container').height();
			  if(((a/b) < (c/d))) { //图片宽高比小于容器宽高比，则垂直铺满
				  showBigHtml += '<div class="swiper-slide" style="background-color:#ddd;text-align:center">'+
	  								'<img height="100%" src="'+swiper.imagesToLoad[i].currentSrc+'" />'+
	  							'</div>';
			  } else {
				  showBigHtml += '<div class="swiper-slide">'+
	  								'<img width="100%" src="'+swiper.imagesToLoad[i].currentSrc+'" />'+
	  							'</div>';
			  }
			// console.log(swiper.imagesToLoad[i].naturalWidth,swiper.imagesToLoad[i].naturalHeight);
	      }
	      $('.construct-state-big .swiper-wrapper').html('');
	      $('.construct-state-big .swiper-wrapper').html(showBigHtml);
	      $('.show-bigimg').removeClass('undis');
	      $('.show-bigimg .current-page').html(swiper.clickedIndex+1);
	      $('.show-bigimg .all-page').html(swiper.imagesLoaded);

	      //初始化大图轮播
	      var bigSwiper = new Swiper('.construct-state-big', {
					prevButton:'.swiper-button-prev',
					nextButton:'.swiper-button-next',
			    onSlideChangeEnd: function(swiper1){
			      // console.log(swiper1.activeIndex) //切换结束时，告诉我现在是第几个slide
			      var currentPage = swiper1.activeIndex + 1;
			    	$('.current-page').html(currentPage);
			    }
		    });
		    //切换到点击的那个slide，速度为0秒
		    bigSwiper.slideTo(swiper.clickedIndex, 0, false);
	    }
    });
  }
  $(function(){
	  
  	// 关闭大图遮罩层
		$(document).on('click', '.show-bigimg .back_btn', function(){
			$('.show-bigimg').addClass('undis');
		});
		// 循环初始化小图列表
		var itemLen = $('.construct-state .item').size();
		for (var i = 0; i < itemLen; i++) {
			var className = 'swipercontainer'+i;
			initSiwper(className);
		}
	});
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
		var orderObj = $(this).find("input[name=orderId]").val(); 
		//查询订单
		query_order_by_order_id(orderObj)
		console.log(address);
		$('.options').addClass('undis');
		$('.mask1').addClass('undis');
		$('.nav_bar span').text(address);
	});
	
	
	
	function query_order_by_order_id(orderObj){
	window.location.href="${ctx}/app/home/jobsite/index.php?orderId="+orderObj;
		}
	function update_status(broadcastId){
		
		if($("#"+broadcastId).hasClass("new")){
		$.ajax({
			url: "${ctx}/app/home/jobsite/save_log.php?broadcastId="+broadcastId,
			success: function(data){
				
				if(data==1){
					$("#"+broadcastId).removeClass("new");
					
					
				}else{
					
					$("#"+broadcastId).removeClass("new");
				}
				
			}
			
			
		})
		
		}else{
		
		}
	}
		
	
	</script>
</body>
</html>