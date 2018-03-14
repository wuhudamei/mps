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
						<input type="hidden" id="upOrderId" value="${order1.id }">
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
			$(document).ready(function(){
				var orderId = $("#upOrderId").val();
				//alert(orderId);
				gaibian(orderId);
			});
			$(document).on('click', '.nav_bar', function(){
				$('.nav_box').css({'z-index': 10});
				/* if($('.options a').text() == $('.select').text()){
					$('.options a').addClass('col_blue');
				} */
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
				$('.options').addClass('undis');
				$('.mask').addClass('undis');
				$('.nav_bar span').text(address);
			});
		}); 
	 	function gaibian(orderId){
			$.ajax({
				type:"post",
				url: "${ctx}/app/home/orderTeamChange",
				data:{
					orderId:orderId
				},
				success:function(data){
					var workers = data.works;
					var	html = "";
					html += '<li class="bg_f shadow mb24">'
							 + '<div class="left">'
							 	+ '<img src="${ctxStatic}/mobile/modules/home/images/server.png" alt="">'
							 + '</div>'
						 	 + '<div class="right font0">'
						 		+	'<div class="dot_line">'
						 			+	'<p class="name font28"><span class="col_grey">客服经理：</span><span class="col_3">'+data.serviceName+'</span></p>'
									+	'<p class="tele_num font22"><span class="col_grey">电话：</span><span class="col_3">'+data.servicePhone+'</span></p>'
								+ '</div>'
						 		+	'<a class="tele" href="tel:'+data.servicePhone+'">'
						 		+	'<img class="phone" src="${ctxStatic}/mobile/modules/home/images/call.png" alt="拨打电话">'
						 		+	'</a>'
						 	 + '</div>'
						 + '</li>'
						 +   '<li class="bg_f shadow mb24">'
						 +	'<div class="left">'
						 +	'<img src="${ctxStatic}/mobile/modules/home/images/design.png" alt="">'
						 +	'</div>'
						 +	'<div class="right font0">'
							 +	'<div class="dot_line">'
								 +	'<p class="name font28"><span class="col_grey">设计师：</span><span class="col_3">'+data.designerName+'</span></p>'
								 +	'<p class="tele_num font22"><span class="col_grey">电话：</span><span class="col_3">'+data.designerPhone+'</span></p>'
								 +'</div>'
								 +	'<a class="tele" href="tel:'+data.designerPhone+'">'
								 +	'<img class="phone" src="${ctxStatic}/mobile/modules/home/images/call.png" alt="拨打电话">'
								 +	'</a>'
									 +	'</div>'
										 +'</li>'
											 +	' <li class="bg_f shadow mb24 clearfix">'
												 +	'<div class="left">'
													 +	'<img src="${ctxStatic}/mobile/modules/home/images/manager.png" alt="">'
														 +	'</div>'
															 +	'<div class="right font0">'
																 +	'<div class="dot_line">';
																 if(data.itemManager == null){
																	 html +='<p class="name font28"><span class="col_grey">项目经理：</span><span class="col_3"></span></p>'
																	 +	'<p class="tele_num font22"><span class="col_grey">电话：</span><span class="col_3"></span></p>'
																		+'<div class="star0">';
																 }else{
																	 html +='<p class="name font28"><span class="col_grey">项目经理：</span><span class="col_3">'+data.itemManager+'</span></p>'
																		 +'<p class="tele_num font22"><span class="col_grey">电话：</span><span class="col_3">'+data.itemManagerPhone+'</span></p>'
																		+'<div class="star0">';
																 }
									if(data.managerStar == null || data.managerStar == '0'){
										html += '<span></span><span></span><span></span><span></span><span></span>';
									}else{
										for(var i=0;i<data.managerStar;i++){
											html+= '<span class="bling"></span>';
										}
										for(var i=1;i<(5-data.mangager);i++){
											html+='<span></span>';
										}
									}
									html += '</div>'
										 + '</div>'
											 +'<a class="tele" href="tel:'+data.itemManagerPhone+'">'
												 +'<img class="phone" src="${ctxStatic}/mobile/modules/home/images/call.png" alt="拨打电话">'
													 +'</a>'
														 +'</div>'
															 +'</li>'
																 +'	 <li class="bg_f shadow mb24 clearfix">'
																	 +'		<div class="left">'
																		 +'			<img src="${ctxStatic}/mobile/modules/home/images/pqc.png" alt="">'
																			 +'		</div>'
																				 +'		<div class="right font0">'
																					 +'		<div class="dot_line">';
																					 if(data.orderInspector == null){
																						 html +='			<p class="name font28"><span class="col_grey">质检员：</span><span class="col_3"></span></p>'
																						 +'			<p class="tele_num font22"><span class="col_grey">电话：</span><span class="col_3"></span></p>'
																							 +'			<div class="star0">'; 
																					 }else{
																						 html +='			<p class="name font28"><span class="col_grey">质检员：</span><span class="col_3">'+data.orderInspector+'</span></p>'
																						 +'			<p class="tele_num font22"><span class="col_grey">电话：</span><span class="col_3">'+data.orderInspectorPhone+'</span></p>'
																							 +'			<div class="star0">'; 
																					 }
																						
										if(data.inspectorStar == null || data.managerStar == '0'){
											html += '<span></span><span></span><span></span><span></span><span></span>';
										}else{
											for(var i=0;i<data.inspectorStar;i++){
												html+= '<span class="bling"></span>';
											}
											for(var i=1;i<(5-data.inspectorStar);i++){
												html+='<span></span>';
											}
										}
										html	+='	</div>'
												+'	</div>'
													+'	<a class="tele" href="tel:'+data.orderInspectorPhone+'">'
														+'<img class="phone" src="${ctxStatic}/mobile/modules/home/images/call.png" alt="拨打电话">'
															+'	</a>'
																+'</div>'
																	+'	</li>';
									if(workers != null){
										for(var j = 0 ; j<workers.length ; j++){
											html +='<li class="bg_f shadow mb24 clearfix">'
												+'<div class="left">'
													+'<img src="${ctxStatic}/mobile/modules/home/images/worker.png" alt="">'
														+'</div>'
															+	'<div class="right font0">'
																+	'<div class="">'
																	+	'<p class="mt70 font28"><span class="col_grey">工人：</span><span class="col_3">'+workers[j].workerName+'</span></p>'
																		+	'<p class="mt30 tele_num font22"><span class="col_grey">工种：</span><span class="col_3">'+workers[j].workerType+'</span></p>'
																			+'<div class="star">';
											if(workers[j].workerStar == null || workers[j].workerStar  == '0'){
												html += '<span></span><span></span><span></span><span></span><span></span>';
											}else{
												for(var m = 0 ; m < workers[j].workerStar;m++){
													html+= '<span class="bling"></span>';
												}
												for(var n=0; n<(5-workers[j].workerStar);n++){
													html+='<span></span>';
												}
											}
											html +='</div></div></div></li>';
										}
									}
					$(".members").html(html);
				}
			});
		} 
	</script>
</body>
</html>