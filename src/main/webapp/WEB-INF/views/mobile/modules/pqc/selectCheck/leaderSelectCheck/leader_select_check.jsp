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
	<title>抽检列表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil2.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/date_check.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newCommon/common.css"/>
</head>
<body>
	<div class="g-task_list">
		<header>
			<a class="back_btn" href="${ctx }/app/pqc/pqcIndex"></a>
			<h2 class="title">抽检列表</h2>
		</header><!-- /header -->
		<section class="task_list">
			<p class="sch_div clearfix">
				<input class="sch_text" type="text" placeholder="小区名字、客户姓名、项目经理">
				<a class="sch_btn" href="javascript:;">搜索</a>
				<input type="text" hidden="hidden" value="${text }" id="text">
			</p>
			<c:forEach items="${list }" var="p">
				<div class="shadow">
					<ul <c:if test="${p.isScrap eq '1'}"> class="abandon" </c:if>>
						<li class="clearfix">
							<span>顾客信息：</span>
							<p>${p.communityName }-${p.buildNumber }-${p.buildUnit }-${p.buildRoom }-${p.customerName }</p>
						</li>
						<li class="clearfix">
							<span>客户手机：</span>
							<p>${p.customerPhone }</p>
						</li>
						<li class="clearfix">
							<span>项目经理：</span>
							<p>${p.itemManager }</p>
						</li>
						<li class="clearfix">
							<span>管家手机：</span>
							<p>${p.managerPhone }</p>
						</li>
						<li class="clearfix">
							<span>实际开工日期：</span>
							<p><fmt:formatDate value="${p.actualStartDate }" pattern="yyyy-MM-dd" /></p>
						</li>
					</ul>
					<c:if test="${p.isScrap ne '1' }">
						<div class="btns clearfix">
							
							<%-- <a class="sel_btn btn1" href="${ctx}/app/pqc/selectCheck/sign/list?orderId=${p.orderId }&qcBillId=${p.qcBillId}">到场签到</a> --%>
							<a class="sel_btn btn1 signButton" orderId="${p.orderId}" taskId="${p.qcBillId}" lon="${p.lon}" lat="${p.lat}" href="javascript:void(0)">到场签到</a>
						
							<c:if test="${p.qcBillStatus=='-1' }">
								<a class="btn" href="${ctx}/app/pqc/selectCheckList/selectItemsList?orderId=${p.orderId }&qcBillId=${p.qcBillId}">检查项目</a>
							</c:if>
							<c:if test="${p.qcBillStatus=='0' }">
								<a class="rgt_btn" href="${ctx}/app/pqc/selectCheckList/selectItemsList?orderId=${p.orderId }&qcBillId=${p.qcBillId}">检查项目</a>
							</c:if>
							<c:if test="${p.qcBillStatus=='-2' }">
								<a class="btn" href="${ctx}/app/pqc/selectCheckList/selectItemsList?orderId=${p.orderId }&qcBillId=${p.qcBillId}">检查项目</a>
							</c:if>
						</div>
					</c:if>
				</div>
			</c:forEach>
		</section>
		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="timeAlert">
			<div id="g-done_ask">
				<p class="first">同一个订单两次抽检操作时间必须间隔5分钟，请过5分钟后再申请</p>
				<p class="second">
					
					<a href="#" onclick="queren()">确认</a>
				</p>
			</div> 
		</div>
	</div>
	
	<input id="lat" hidden="hidden"/>
	<input id="lon" hidden="hidden"/>
	<input id="packId"  hidden="hidden"/>
	<input id="orderId"  hidden="hidden"/>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/global.js"></script>
	<script type="text/javascript">
		
		$(function(){
			
			var text = $("#text").val();
			if(null!=text && text!=""){
				$(".sch_text").val(text);
			}
			
			var time = "${timeForbidden}";
			if(""!=time &&"1"==time && null!=time){
				$("#timeAlert").show();
			}
			
			$(".sch_btn").bind("click",ajaxSearch);
			
			
			
		})

		function queren(){
			 $('#timeAlert').hide();
		}
		

		function ajaxSearch(){
			var text = $(".sch_text").val();
			window.location.href="${ctx}/app/pqc/selectCheckList/list?text="+text;
		}
		
		
		 var format = function(time, format){
			    var t = new Date(time);
			    var tf = function(i){return (i < 10 ? '0' : '') + i};
			    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
			        switch(a){
			            case 'yyyy':
			                return tf(t.getFullYear());
			                break;
			            case 'MM':
			                return tf(t.getMonth() + 1);
			                break;
			            case 'mm':
			                return tf(t.getMinutes());
			                break;
			            case 'dd':
			                return tf(t.getDate());
			                break;
			            case 'HH':
			                return tf(t.getHours());
			                break;
			            case 'ss':
			                return tf(t.getSeconds());
			                break;
			        }
			    })
			}
		 
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
						var orderId = $("#orderId").val();
						var signAddress = data.address;
						var text = $(".sch_text").val();
						$.post("${ctx}/app/pqc/selectCheck/sign/pqcsign", {lat:lat,lon:lon,relatedBusinessId:relatedBusinessId,orderId:orderId,signErrorDistance:signErrorDistance,signAddress:signAddress},
								function(result){
									if(result == "success"){
										globalUtil.fn.alert("签到成功!",2.0);
										setTimeout('window.location.href="${ctx}/app/pqc/selectCheckList/list?text='+text+'"', 2000);
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
							var orderId = $("#orderId").val();
							var signAddress = data.address;
							var text = $(".sch_text").val();
							$.post("${ctx}/app/pqc/selectCheck/sign/pqcsign", {lat:lat,lon:lon,relatedBusinessId:relatedBusinessId,orderId:orderId,signErrorDistance:signErrorDistance,signAddress:signAddress},
									function(result){
								if(result == "success"){
									globalUtil.fn.alert("签到成功!",2.0);
									setTimeout('window.location.href="${ctx}/app/pqc/selectCheckList/list?text='+text+'"', 2000);
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