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
		<a class="back_btn" href="${ctx}/app/manager/packageSettlementList"></a>
		<h2 class="title">签到</h2>
	</header><!-- /header -->
	<form  id="signForm" method="post">
		<input id="lat" name="lat" hidden="hidden">
		<input id="lon" name="lon" hidden="hidden">
		<input id="packId"  hidden="hidden" name="relatedBusinessIdInt" value="${pack.id}">
		<input id="distance" hidden="hidden" name="signErrorDistance">
		<input id="address" hidden="hidden" name="signAddress">
	</form>
</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript">
		/*var curWwwPath=window.document.location.href;
		alert(curWwwPath);
		var pathName=window.document.location.pathname;
		alert(pathName);
		var pos=curWwwPath.indexOf(pathName);
		var localhostPaht=curWwwPath.substring(0,pos);
		alert(localhostPaht);*/

		var u = navigator.userAgent;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		window.onerror = function(err) {
			log('window.onerror: ' + err)
		}
		if(isAndroid){
			android.userlocation();

			function locationResult(json){
				var data = eval(json);
				if(data.type == "succ"){
					$("#lat").val(data.latitude);
					$("#lon").val(data.longitude);
					$("#address").val(data.address);
					var distance = getDistance(${pack.lat},${pack.lon},data.latitude,data.longitude);
					$("#distance").val(distance);
					$.post("${ctx}/app/manager/saveOrUpdateSign", $("#signForm").serialize(), function(result){
						if(result == "success"){
							globalUtil.fn.alert("签到成功!",2.0);
							setTimeout('window.location.href="${ctx}/app/manager/packageSettlementList"', 1000);
						}else{
							globalUtil.fn.alert("签到失败!",2.0);
						}
					});
				}
				if(data.type == "back"){
					window.location.href="${ctx}/app/manager/packageSettlementList";
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
					//log('ObjC called testJavascriptHandler with', data)
					if(data.type == "succ"){
						$("#lat").val(data.latitude);
						$("#lon").val(data.longitude);
						$("#address").val(data.address);
						var distance = getDistance(${pack.lat},${pack.lon},data.latitude,data.longitude);
						$("#distance").val(distance);
						$.post("${ctx}/app/manager/saveOrUpdateSign", $("#signForm").serialize(), function(result){
							if(result == "success"){
								globalUtil.fn.alert("签到成功!",2.0);
								setTimeout('window.location.href="${ctx}/app/manager/packageSettlementList"', 1000);
							}else{
								globalUtil.fn.alert("签到失败!",2.0);
							}
						});
					}
					if(data.type == "back"){
						window.location.href="${ctx}/app/manager/packageSettlementList";
					}
					/*var responseData = { 'Javascript Says':'Right back atcha!' }
					 //log('JS responding with', responseData)
					 responseCallback(responseData)*/
				})

				/* bridge.registerHandler('locationsuccess', function(data, responseCallback) {
				 //log('ObjC called testJavascriptHandler with', data);
				 alert(1);
				 var responseData = { 'Javascript Says':'Right back atcha!' };
				 //log('JS responding with', responseData);
				 responseCallback(responseData);
				 alert(responseData);
				 }) */

				//var callbackButton = document.getElementById('buttons').appendChild(document.createElement('button'))
				//callbackButton.innerHTML = 'Fire testObjcCallback'
				/*var callbackButton = document.getElementById('signButton');
				 callbackButton.onclick = function(e) {
				 e.preventDefault()
				 bridge.callHandler('userlocation', function(response) {
				 })
				 }*/
				bridge.callHandler('userlocation', function(response) {
				})
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