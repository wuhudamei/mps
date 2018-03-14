<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>现场签到</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/sign.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
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
	<div class="g-sign">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/progressList"></a>
			<h2 class="title">现场签到</h2>
		</header>
		<!-- /header -->
		<section class="sign_list">


			<c:forEach items="${signList }" var="order">
				<ul class="clearfix">
					<li class="clearfix"><span>顾客信息：</span>
						<p>${order.communityName }-${order.buildNumber}-${order.buildUnit}-${order.buildRoom }-${order.customerName }</p>
					</li>
					<li class="clearfix"><span>合同开工：</span>
						<p>
							<fmt:formatDate value="${order.contractStartDate}"
								pattern="yyyy-MM-dd" />
						</p></li>
					<li class="clearfix"><span>实际开工：</span>
						<p>
							<fmt:formatDate value="${order.actualStartDate }"
								pattern="yyyy-MM-dd" />
						</p></li>
					<li class="clearfix"><span>合同工期：</span>
						<p>${order.contractTime }</p></li>
					<li class="clearfix"><span>订单状态：</span>
						<p class="col_blue">${order.orderStatus }</p></li>
					<div class="btns clearfix">
						<%--<c:if test="${order.signFlag=='timeForbidden'}">
							<span class="btn font_col_grey">已签到</span>
						</c:if>
						<c:if test="${order.signFlag=='timeAllowed'}">--%>
							<%-- <span class="btn" onclick="signByGPS('${order.id}')">签到</span> --%>
							<a class="btn signButton"  taskId="${order.id}" lon="${order.lon}" lat="${order.lat}" href="javascript:void(0)">到场签到</a>
						<%--</c:if>--%>
						<span class="btn" onclick="signDetail('${order.id}')">查看详情</span>
					</div>
				</ul>

			</c:forEach>

		</section>
	</div>
	
	<div class="alertMask undis" id ="alert">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent" id="alertRemarks">签到成功！误差范围在1000米以内，签到状态为合格！</div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"   href="${ctx}/app/manager/sign/signIndex">确定</a>
			</div>
		</div>
	</div>
	
	<div class="alertMask undis" id ="alert2">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">签到成功！但误差范围大于1000米，签到状态为不合格！请选择离工地更近的位置【重新签到】或联系劳资部报备。</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtn font33 col_fdfcfa" href="javascript:void(0);" onclick="sure2()">
					重新签到
				</a>
				<a class="maskBtn font33 col_0780ec" href="${ctx}/app/manager/sign/signIndex" >取消</a>
			</div>
		</div>
	</div>
	<input id="lat" hidden="hidden"/>
	<input id="lon" hidden="hidden"/>
	<input id="packId"  hidden="hidden"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript">
		/* function signByGPS(orderId) {
			window.location.href = "${ctx}/app/manager/signByGPS?orderId="
					+ orderId;
		} */
		function signDetail(orderId) {
	
			window.location.href = "${ctx}/app/manager/signDetail?orderId="
					+ orderId;
		}
		function sure2(){
			$(".signButton").trigger("click");
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
					var taskId = $(this).attr("taskId");
					var lon = $(this).attr("lon");
					var lat = $(this).attr("lat");
					$("#packId").val(taskId);
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
					var orderId = $("#packId").val();
					var signAddress = data.address;
					$.post("${ctx}/app/manager/sign", {lat:lat,lon:lon,orderId:orderId,signDistance:signDistance,signAddress:signAddress},
							function(result){
								if(result == "success"){
									if(signDistance<=1000){
										$('#alert').show();
									}else{
										$('#alert2').show();
									}
									//globalUtil.fn.alert("签到成功!误差范围在1000米以内，签到状态为合格!",2.0);
									//setTimeout('window.location.href="${ctx}/app/manager/signIndex"', 2000);
								}else{
									globalUtil.fn.alert("签到失败!",2.0);
								}
							});
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
						var orderId = $("#packId").val();
						var signAddress = data.address;
						$.post("${ctx}/app/manager/sign", {lat:lat,lon:lon,orderId:orderId,signDistance:signDistance,signAddress:signAddress},
								function(result){
							if(result == "success"){
								if(signDistance<=1000){
									$('#alert').show();
								}else{
									$('#alert2').show();
								}
								/* globalUtil.fn.alert("签到成功!",2.0);
								setTimeout('window.location.href="${ctx}/app/manager/signIndex"', 2000); */
							}else{
								globalUtil.fn.alert("签到失败!",2.0);
							}
						});
					}
					/*if(data.type == "back"){
						window.location.href="${ctx}/app/manager/packageSettlementList";
					}*/
				})

				$(".signButton").each(function(){
					$(this).click(function(){
						var taskId = $(this).attr("taskId");
						var lon = $(this).attr("lon");
						var lat = $(this).attr("lat");
						$("#packId").val(taskId);
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