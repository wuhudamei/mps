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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/sign_list.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newCommon/common.css"/>
</head>
<body>
	<div class="g-sign_list">
		<header>
			<a class="back_btn" href="${ctx}/app/worker/toindex"></a>
			<c:if test="${empty error }">
				<h2 class="title">现场签到</h2>
			</c:if>
			<c:if test="${not empty error }">
				<h2 class="title">${error }</h2>
			</c:if>
		</header>
		<!-- /header -->
		<section class="sign_list" id="section">
				<c:forEach items="${signList }" var="pack">

						<div class="clearfix shadow">
							<ul <c:if test="${pack.isScrap eq '1'}"> style="background: url(${ctxStatic}/mobile/modules/Manager/img/common/abandon.png) no-repeat;background-size: 1.28rem;background-position: 95% .8rem;"</c:if> onclick="packDetail('${pack.id}','${pack.settleStyle }')">
								<li class="clearfix">
									<span>任务包名称：</span>
									<p>${pack.packageName}</p>
								</li>
								<li class="clearfix">
									<span>项目经理：</span>
									<p>${pack.itemManagerName}</p>
								</li>
								<li class="clearfix">
									<span>手机号：</span>
									<p>${pack.itemManagerPhone}</p>
								</li>
								<li class="clearfix">
									<span>地点：</span>
									<p>${pack.customerMessage }-${pack.customerName }</p>
								</li>
								<li class="clearfix">
									<span>时间：</span>
									<p>
										<fmt:formatDate value="${pack.planStartDate}" pattern="yyyy-MM-dd" />
										至
										<fmt:formatDate value="${pack.planEndDate}" pattern="yyyy-MM-dd" />
									</p>
								</li>
								<li class="clearfix">
									<span>状态：</span>
									<p>${pack.packStateName}</p>
								</li>
							</ul>
							<%-- <a class="sign_btn" href="#" onclick="signByGPS('${pack.id}')">快速签到</a> --%>
							<a class="sign_btn signButton"  taskId="${pack.id}" lon="${pack.lon}" lat="${pack.lat}" href="javascript:void(0)">快速签到</a>
						</div>

				</c:forEach>
		</section>
	</div>
	<input id="lat" hidden="hidden"/>
	<input id="lon" hidden="hidden"/>
	<input id="packId"  hidden="hidden"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript">

		/* function signByGPS(packId) {
			window.location.href = "${ctx}/app/worker/sign/signByGPS?packId="
					+ packId;
		} */
		function signDetail(packId) {
	
			window.location.href = "${ctx}/app/worker/sign/signDetail?packageId="
					+ packId;
		}
		function packDetail(packId, settleStyle) {
	
			window.location.href = "${ctx}/app/worker/packDetail?packageId="
					+ packId + "&settleStyle=" + settleStyle;
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
					var packId = $("#packId").val();
					var signAddress = data.address;
					$.post("${ctx}/app/worker/sign/workersign", {lat:lat,lon:lon,packId:packId,signDistance:signDistance,signAddress:signAddress},
							function(result){
								if(result == "success"){
									globalUtil.fn.alert("签到成功!",2.0);
									setTimeout('window.location.href="${ctx}/app/worker/sign/signIndex"', 2000);
								}else{
									globalUtil.fn.alert("签到失败!",2.0);
								}
							});
				}
				/*if(data.type == "back"){
					window.location.href="${ctx}/app/manager/packageSettlementList";
				}*/
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
						var packId = $("#packId").val();
						var signAddress = data.address;
						$.post("${ctx}/app/worker/sign/workersign", {lat:lat,lon:lon,packId:packId,signDistance:signDistance,signAddress:signAddress},
								function(result){
							if(result == "success"){
								globalUtil.fn.alert("签到成功!",2.0);
								setTimeout('window.location.href="${ctx}/app/worker/sign/signIndex"', 2000);
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