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
	<title>任务包结算</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/sign.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
</head>
<body>
	<div class="g-sign">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/taskPackageManager"></a>
			<h2 class="title">任务包结算</h2>
		</header><!-- /header -->
		<section class="sign_list">
		<c:forEach items="${list}" var="orderPackage">
			<ul class="pad_top2 pad_btm2 clearfix shadow">
				<li class="clearfix">
					<span>任务包编号：</span>
					<p>${orderPackage.orderTaskPackageCode}</p>
				</li>
				<li class="clearfix">
					<span>任务包名称：</span>
					<p>${orderPackage.packageName}</p>
				</li>
				<li class="clearfix">
					<span>顾客信息：</span>
					<p>${orderPackage.customerMessage}-${orderPackage.customerName}</p>
				</li>
				<li class="clearfix">
					<span>开工日期：</span>
					<p><fmt:formatDate value="${orderPackage.actualStartdate }" pattern="yyyy-MM-dd"/></p>
				</li>
				<li class="clearfix">
					<span>完工日期：</span>
					<p><fmt:formatDate value="${orderPackage.actualEnddate }" pattern="yyyy-MM-dd"/></p>
				</li>
				<div class="btns clearfix">
					<%--<a class="btn" href="${ctx}/app/manager/taskPackageMap?orderId=${orderPackage.orderId}&id=${orderPackage.id}">到场签到</a>--%>
					<a class="btn signButton"  taskId="${orderPackage.id}" lon="${orderPackage.lon}" lat="${orderPackage.lat}" href="javascript:void(0)">到场签到</a>
					<%-- <a class="btn" href="${ctx}/app/manager/taskpackageConfirm?id=${orderPackage.id}">确认验收</a> --%>
					<a class="btn" onclick="confirmButton(${orderPackage.id},'${orderPackage.packageStateId}')">确认验收</a>
				</div>
			</ul>
		</c:forEach>
		</section>
	</div>
	<input id="lat" hidden="hidden"/>
	<input id="lon" hidden="hidden"/>
	<input id="packId"  hidden="hidden"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript">
		function confirmButton(id,packageStateId){
			if(packageStateId == '80'){
				window.location.href="${ctx}/app/manager/taskpackageConfirm?id="+id;
			}else if(packageStateId == '95'){
				window.location.href="${ctx}/app/manager/taskpackageConfirmRecheck?taskPackageId="+id;
			}
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
					var signErrorDistance = getDistance($("#lat").val(),$("#lon").val(),data.latitude,data.longitude);
					var lon = data.longitude;
					var lat = data.latitude;
					var relatedBusinessIdInt = $("#packId").val();
					var signAddress = data.address;
					$.post("${ctx}/app/manager/saveOrUpdateSign", {lat:lat,lon:lon,relatedBusinessIdInt:relatedBusinessIdInt,signErrorDistance:signErrorDistance,signAddress:signAddress},
							function(result){
								if(result == "success"){
									globalUtil.fn.alert("签到成功!",2.0);
									/*window.location.href="${ctx}/app/manager/packageSettlementList";*/
									/*setTimeout('window.location.href="${ctx}/app/manager/packageSettlementList"', 1000);*/
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
						var signErrorDistance = getDistance($("#lat").val(),$("#lon").val(),data.latitude,data.longitude);
						var lon = data.longitude;
						var lat = data.latitude;
						var relatedBusinessIdInt = $("#packId").val();
						var signAddress = data.address;
						$.post("${ctx}/app/manager/saveOrUpdateSign", {lat:lat,lon:lon,relatedBusinessIdInt:relatedBusinessIdInt,signErrorDistance:signErrorDistance,signAddress:signAddress},
								function(result){
							if(result == "success"){
								globalUtil.fn.alert("签到成功!",2.0);
								/*window.location.href="${ctx}/app/manager/packageSettlementList";*/
								/*setTimeout('window.location.href="${ctx}/app/manager/packageSettlementList"', 1000);*/
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