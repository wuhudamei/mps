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
	<title>我要签到</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/applyDoneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/waitMe.css" >
	<style>
		.pad_btm40{padding-bottom:.4rem;}
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 84%;margin: 30% auto 0;border-radius: .1rem;background: #fff;font-size: .32rem;}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: .28rem;}
		.font33{font-size: .33rem;}
		.maskTit{height: 1rem;line-height: 1rem;text-align: center;}
		.maskContent{padding: .5rem;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: .2rem;padding-top: .2rem;}
		/* .maskBtn{display: block;width: 60%;} */
		.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
    		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}
	</style>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/worker/install/installIndex/toindex"></a>
			<h2 class="title">我要签到</h2>
		</header><!-- /header -->
		<section class="pad_top88">
			<div class="font0 search-area">
				<input class="search-box" type="text" placeholder=" 安装项名称、项目经理">
				<a class="search-btn" href="javascript:;" onclick="findInstallConstructBillSignList()"></a>
			</div>
			<div id="boxA">
				<%-- <div class="sec pl30 pr30 font0 border_top border_btm mb24 bg_f clearfix">
					<p class="pt22 pb22"><span class="font30 col_0780ec pl25 blueBar">安装项名称：铝扣板安装</span></p>
					<ul class="pad_top3 bor_top_e9e8e8 bor_dashed">
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">施工单编号：</span>
							<p class="font28 col_3 pad_rgt3 flow">SG201707100001</p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">小区名称：</span>
							<p class="font28 col_3 pad_rgt3 flow">合顺家园-1-2-203合顺家园-1-2-203合顺家园-1-2-203合顺家园-1-2-203</p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">项目经理：</span>
							<p class="font28 col_3 pad_rgt3 flow">张丰丰</p>
						</li>
						<li class="mb30 rele clearfix">
							<span class="col_grey font28 flo_left">项目经理手机号：</span>
							<p class="font28 col_3 pad_rgt3 flow">13020201021</p>
							<a class="teleBtn" href="javascript:;"><i class="teleIcon"><img src="${ctxStatic}/mobile/modules/Worker/img/tele.png" alt=""></i>拨打电话</a>
						</li>
					</ul>
					<ul class="pad_top3 bor_dashed">
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">施工地址：</span>
							<p class="font28 col_3 pad_rgt3 flow">海淀区金沟河路12号院7号楼21号号院7号</p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">确认开始日期：</span>
							<p class="font28 col_3 pad_rgt3 flow">2017-06-29</p>
						</li>
						<li class="mb30 rele clearfix">
							<span class="col_grey font28 flo_left">确认结束日期：</span>
							<p class="font28 col_3 pad_rgt3 flow">2017-07-10</p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">施工单状态：</span>
							<p class="font28 col_3 pad_rgt3 flow">施工中</p>
						</li>
					</ul>
					<div class="btn_wrapper clearfix">
						<a class="btnBlueBg" href="javascript:;">快速签到</a>
					</div>
				</div> --%>
			</div>
			
		</section>
		<div style="padding-bottom:300px;"></div>
		
		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
			<div class="alertMask">
				<div class="maskWrapper">
					<p class="col_3 maskTit">通知</p>
					<div class="font28 col_6 maskContent" id="alertRemarks"></div>
					<div class="maskBtns clearfix">
						<a class="maskBtn font33 col_f"  onclick="sendMessage()" href="javascript:;">我知道了</a>
					</div>
				</div>
			</div>
		</div>
		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport1">
			<div class="alertMask">
				<div class="maskWrapper">
					<p class="col_3 maskTit">通知</p>
					<div class="font28 col_6 maskContent" id="alertRemarks1"></div>
					<div class="maskBtns clearfix">
						<a class="maskBtn font33 col_f"  onclick="sendMessage1()" href="javascript:;">我知道了</a>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	<input id="lat" hidden="hidden"/>
	<input id="lon" hidden="hidden"/>
	<input id="constructBillId"  hidden="hidden"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/waitMe.js"></script>
	<script type="text/javascript">
	
	 	function sendMessage(){
	    	$("#subReport").hide();
	    }
	    function sendMessage1(){
	    	window.location.href = "${ctx}/app/worker/install/installSign/list";
	    }
	
		function run_waitMe(text){
		    $('body').waitMe({
		        effect: 'win8',
		        text: text,
		        bg: 'rgba(255,255,255,0.7)',
		        color:'#000',
		        sizeW:'',
		        sizeH:'',
		        source: 'img.svg'
		    });
		}
		
		
		findInstallConstructBillSignList();
		
		//查询施工单列表(状态为10-已确定工人组）
		function findInstallConstructBillSignList(){
			
			run_waitMe('正在加载数据,请稍等');
			
			var sectionObj = $("#boxA");
			
			var text =$(".search-box").val();
			
			var html = '';
			
			$.ajax({
				url: "${ctx}/app/worker/install/installSign/install_construct_bill_sign_ajax_list",
	            type: "post",
	            data:{text:text},
	            success: function(data) {
	            	
	            	if(null!=data && data.length>0){
		            	for(var v=0;v<data.length;v++){
		            		
		            		html += '<div class="sec pl30 pr30 font0 border_top border_btm mb24 bg_f clearfix">'+
			            				'<p class="pt22 pb22"><span class="font30 col_0780ec pl25 blueBar">安装项名称：'+data[v].installItemName+'</span></p>'+
					        				'<ul class="pad_top3 bor_top_e9e8e8 bor_dashed">'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">施工单编号：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].constructBillCode+'</p>'+
												'</li>'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">小区名称：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'</p>'+
												'</li>'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">项目经理：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].itemManagerName+'</p>'+
												'</li>'+
												'<li class="mb30 rele clearfix">'+
													'<span class="col_grey font28 flo_left">项目经理手机号：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].itemManagerPhone+'</p>'+
													'<a class="teleBtn" href="tel:'+data[v].itemManagerPhone+'"><i class="teleIcon"><img src="${ctxStatic}/mobile/modules/Worker/img/tele.png" alt=""></i>拨打电话</a>'+
												'</li>'+
											'</ul>'+
											'<ul class="pad_top3 bor_dashed">'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">施工地址：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].detailAddress+'</p>'+
												'</li>'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">确认开始日期：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].supplierConfirmIntoDateString+'</p>'+
												'</li>'+
												'<li class="mb30 rele clearfix">'+
													'<span class="col_grey font28 flo_left">确认结束日期：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].supplierConfirmCompleteDateString+'</p>'+
												'</li>'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">施工单状态：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].constructBillStatusName+'</p>'+
												'</li>'+
											'</ul>'+
											'<div class="btn_wrapper clearfix">'+
												'<a class="btnBlueBg signButton" constructBillId="'+data[v].constructBillId+'" lon="'+data[v].lon+'" lat="'+data[v].lat+'" href="javascript:;">快速签到</a>'+
											'</div>'+
										'</div>';
		            	}
	            	}
	            	
	            	$(sectionObj).html(html);
	    	        $('body').waitMe('hide');
	    	        
	            }
			})
			
			
		}
		
		
		
		var u = navigator.userAgent;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		window.onerror = function(err) {
			log('window.onerror: ' + err)
		}
		
		if(isAndroid){
			$(".signButton").each(function(){
				$(this).click(function(){
					var constructBillId = $(this).attr("constructBillId");
					var lon = $(this).attr("lon");
					var lat = $(this).attr("lat");
					$("#constructBillId").val(constructBillId);
					$("#lat").val(lat);
					$("#lon").val(lon);

					android.userlocation();
				});
			});

			function locationResult(json){
				var data = eval(json);
				if(data.type == "succ"){
					var signDistance = getDistance($("#lat").val(),$("#lon").val(),data.latitude,data.longitude);
					var lon = data.longitude;
					var lat = data.latitude;
					var constructBillId = $("#constructBillId").val();
					var signAddress = data.address;
					run_waitMe('正在提交数据,请稍等');
					$.ajax({
						url: "${ctx}/app/worker/install/installSign/install_construct_bill_sign_submit",
			            type: "post",
			            data:{
			            		lat:lat,
								lon:lon,
								constructBillId:constructBillId,
								signDistance:signDistance,
								signAddress:signAddress
			            	},
			            success: function(data) {
			            	
			            	$('body').waitMe('hide');
			            	if(null!=data && data=="0"){
			            		$("#alertRemarks1").text("签到成功");
			            		$("#subReport1").show();
			            	}else if(data=="1"){
			            		$("#alertRemarks").text("纬度为空");
			            		$("#subReport").show();
			            	}else if(data=="2"){
			            		$("#alertRemarks").text("经度为空");
			            		$("#subReport").show();
			            	}else if(data=="3"){
			            		$("#alertRemarks").text("施工单id为空");
			            		$("#subReport").show();
			            	}else if(data=="4"){
			            		$("#alertRemarks").text("签到距离为空");
			            		$("#subReport").show();
			            	}else if(data=="5"){
			            		$("#alertRemarks").text("签到地址为空");
			            		$("#subReport").show();
			            	}else if(data=="6"){
			            		$("#alertRemarks").text("当前登录人未登录");
			            		$("#subReport").show();
			            	}else if(data=="7"){
			            		$("#alertRemarks").text("施工单相关信息为空");
			            		$("#subReport").show();
			            	}else if(data=="8"){
			            		$("#alertRemarks").text("您已经签到过了");
			            		$("#subReport").show();
			            	}else{
			            		$("#alertRemarks").text("签到失败");
			            		$("#subReport").show();
			            	}
			            	
			            }
			    	})
					
				}
			}
		}
		
		if(isiOS){
			function setupWebViewJavascriptBridge(callback) {
				if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
				if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
				window.WVJBCallbacks = [callback];
				var WVJBIframe = document.createElement('iframe');
				WVJBIframe.style.display = 'none';
				WVJBIframe.src = 'https://__bridge_loaded__';
				document.documentElement.appendChild(WVJBIframe);
				setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0)
			}

			setupWebViewJavascriptBridge(function(bridge) {
				var uniqueId = 1
				function log(message, data) {
					var log = document.getElementById('log')
					var el = document.createElement('div')
					el.className = 'logLine'
					el.innerHTML = uniqueId++ + '. ' + message + ':<br/>' + JSON.stringify(data)
					if (log.children.length) { log.insertBefore(el, log.children[0]) }
					else { log.appendChild(el) }
				}

				bridge.registerHandler('locationResult', function(data, responseCallback) {
					if(data.type == "succ"){
						var signDistance = getDistance($("#lat").val(),$("#lon").val(),data.latitude,data.longitude);
						var lon = data.longitude;
						var lat = data.latitude;
						var constructBillId = $("#constructBillId").val();
						var signAddress = data.address;
						run_waitMe('正在提交数据,请稍等');
						$.ajax({
							url: "${ctx}/app/worker/install/installSign/install_construct_bill_sign_submit",
				            type: "post",
				            data:{
				            		lat:lat,
									lon:lon,
									constructBillId:constructBillId,
									signDistance:signDistance,
									signAddress:signAddress
				            	},
				            success: function(data) {
				            	
				            	$('body').waitMe('hide');
				            	if(null!=data && data=="0"){
				            		$("#alertRemarks1").text("签到成功");
				            		$("#subReport1").show();
				            	}else if(data=="1"){
				            		$("#alertRemarks").text("纬度为空");
				            		$("#subReport").show();
				            	}else if(data=="2"){
				            		$("#alertRemarks").text("经度为空");
				            		$("#subReport").show();
				            	}else if(data=="3"){
				            		$("#alertRemarks").text("施工单id为空");
				            		$("#subReport").show();
				            	}else if(data=="4"){
				            		$("#alertRemarks").text("签到距离为空");
				            		$("#subReport").show();
				            	}else if(data=="5"){
				            		$("#alertRemarks").text("签到地址为空");
				            		$("#subReport").show();
				            	}else if(data=="6"){
				            		$("#alertRemarks").text("当前登录人未登录");
				            		$("#subReport").show();
				            	}else if(data=="7"){
				            		$("#alertRemarks").text("施工单相关信息为空");
				            		$("#subReport").show();
				            	}else if(data=="8"){
				            		$("#alertRemarks").text("您已经签到过了");
				            		$("#subReport").show();
				            	}else{
				            		$("#alertRemarks").text("签到失败");
				            		$("#subReport").show();
				            	}
				            	
				            }
				    	})
				    	
					}
				})

				$(".signButton").each(function(){
					$(this).click(function(){
						var constructBillId = $(this).attr("constructBillId");
						var lon = $(this).attr("lon");
						var lat = $(this).attr("lat");
						$("#constructBillId").val(constructBillId);
						$("#lat").val(lat);
						$("#lon").val(lon);

						bridge.callHandler('userlocation', function(response) {
						})
					});
				});
			})
		}

		function toRad(d) {
			return d * Math.PI / 180;
		}
		function getDistance(lat1, lng1, lat2, lng2) {
			var dis = 0;
			var radLat1 = toRad(lat1);
			var radLat2 = toRad(lat2);
			var deltaLat = radLat1 - radLat2;
			var deltaLng = toRad(lng1) - toRad(lng2);
			var dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLng / 2), 2)));
			return dis * 6378137;
		}
	
	
	
	
	
	</script>
</body>
</html>