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
	<title>复检列表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/date_check.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil2.css"/>
</head>

<body>
	<div class="g-task_list">
		<header>
			<a class="back_btn" href="${ctx }/app/pqc/pqcIndex"></a>
			<h2 class="title">复检列表</h2>
		</header><!-- /header -->
		<section class="task_list">
		
		<c:forEach items="${list }" var="recheck">
			<div class="shadow">
				<ul>
					<li class="clearfix">
						<span>顾客信息：</span>
						<p>${recheck.customerAddress }-${recheck.customerName }</p>
					</li>
					<li class="clearfix">
						<span>客户手机：</span>
						<p>${recheck.customerPhone }</p>
					</li>
					<li class="clearfix">
						<span>项目经理：</span>
						<p>${recheck.managerName }</p>
					</li>
					<li class="clearfix">
						<span>项目经理：</span>
						<p>${recheck.managerPhone }</p>
					</li>
					<li class="clearfix">
						<span>复检内容：</span>
						
						<c:if test="${recheck.recheckType=='1' }"><p>约检-${recheck.checkNodeName }</p></c:if>
						<c:if test="${recheck.recheckType=='2' }"><p>抽检-${recheck.checkNodeName }</p></c:if>
					</li>
					<li class="clearfix">
						<span>期望复检日期：</span>
						<p> <fmt:formatDate value="${recheck.hopeCheckTime }" pattern="yyyy-MM-dd" />  </p>
					</li>
					</ul>
				<div class="btns clearfix">
					<%-- <a class="sel_btn btn1" href="${ctx}/app/pqc/recheck/signByGPS?recheckId=${recheck.recheckId}&orderId=${recheck.orderId}">到场签到</a> --%>
					<a class="sel_btn btn1 signButton" orderId="${recheck.orderId}" taskId="${recheck.recheckId}" lon="${recheck.lon}" lat="${recheck.lat}" href="javascript:void(0)">到场签到</a>
					<a class="sel_btn btn2" href="${ctx}/app/pqc/recheck/checkRecheckCheckItem?recheckId=${recheck.recheckId}&customerInfo=${recheck.customerAddress }-${recheck.customerName }">检查项目</a>
				</div>
			</div>
			</c:forEach>
		</section>
	</div>
	<input id="lat" hidden="hidden"/>
	<input id="lon" hidden="hidden"/>
	<input id="packId"  hidden="hidden"/>
	<input id="orderId"  hidden="hidden"/>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script type="text/javascript">
	
		//到场签到
		var u = navigator.userAgent;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		window.onerror = function(err) {
			log('window.onerror: ' + err)
		}

		if(isAndroid){
			$(".signButton").each(function(){
				$(this).click(function(){
					var orderId = $(this).attr("orderId");
					var taskId = $(this).attr("taskId");
					var lon = $(this).attr("lon");
					var lat = $(this).attr("lat");
					$("#orderId").val(orderId);
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
					var relatedBusinessId = $("#packId").val();
					var signAddress = data.address;
					$.post("${ctx}/app/pqc/recheck/pqcsign", {lat:lat,lon:lon,relatedBusinessId:relatedBusinessId,signErrorDistance:signErrorDistance,signAddress:signAddress},
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
						var relatedBusinessId = $("#packId").val();
						var signAddress = data.address;
						$.post("${ctx}/app/pqc/recheck/pqcsign", {lat:lat,lon:lon,relatedBusinessId:relatedBusinessId,signErrorDistance:signErrorDistance,signAddress:signAddress},
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
						var orderId = $(this).attr("orderId");
						var taskId = $(this).attr("taskId");
						var lon = $(this).attr("lon");
						var lat = $(this).attr("lat");
						$("#orderId").val(orderId);
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