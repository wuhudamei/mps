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
	<title>首页</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/mine_li.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/info.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/manager_info.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/message2.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/managerInfo.css"/>
</head>
<body>
	<div class="g-info">
		<div class="alls sec mine_sec">
			<div class="top"></div>
			<div class="user_info">
				<img src="${ctxStatic}/mobile/modules/Manager/images/headimg.png" alt="headimg">
				<span class="user_name">${manager.realname }</span>
				<div class="star">
				<c:if test="${manager.star == null || manager.star == 0 }">
					<span></span>
					<span></span>
					<span></span>
					<span></span>
					<span></span>
				</c:if>
				<c:if test="${manager.star != null && manager.star !=0 }">
					<c:forEach begin="0" end="${manager.star-1}" step="1">
						<span class="bling"></span>
					</c:forEach>
					<c:forEach begin="1" end="${5-manager.star }" step="1">
						<span></span>
					</c:forEach>
				</c:if>
			</div>
			</div>
			<nav class="info_nav">
				<a href="javascript:void(0)">
					<span class="number">${mov.buildingCount }</span>
					<span class="explain">在施工数</span>
				</a>
				<a href="javascript:void(0)">
					<span class="number">${mov.totalCount }</span>
					<span class="explain">总订单数</span>
				</a>
				<a href="javascript:void(0)">
					<span class="number">3</span>
					<span class="explain">本月排行</span>
				</a>
			</nav>
			<ul class="info_ul">
				<!-- <li><a class="list_a font28 col_3" href="javascript:void(0)">基本信息</a></li> -->
				<li><a class="list_a font28 col_3" href="${ctx}/app/manager/queryPmGuaranteeMoneyLog">质保金查询</a></li>
				<li><a class="list_a font28 col_3" href="${ctx}/app/manager/appOrderList">我的订单</a></li>
				<li><a class="list_a font28 col_3" href="${ctx}/app/manager/method/methodOne">工艺工法</a></li>
				<li><a class="list_a font28 col_3" href="${ctx}/app/manager/common-issue">常见问题</a></li>
				<%-- <li><a class="list_a font28 col_3" href="${ctx}/app/manager/method">质保金</a></li> --%>
				<li><a id ="clearId" class="list_a font28 col_3" href="javascript:void(0)">清除缓存</a></li>
			</ul>
			<a class="quit font30 col_0780ec" href="javascript:void(0)" onclick="showTishi()">退出登录</a>
		</div>
		<footer>
			<a class="footer_btn home_btn" href="${ctx}/app/manager/toindex">首页</a>
			<%-- <a class="footer_btn queue_btn" href="${ctx}/app/manager/backlog/list">待办</a> --%>
			<a class="footer_btn msg_btn" href="${ctx}/app/manager/backlog/list">待办<div class="msg_countmine" style="right: 30%;">${toDoDealNum}</div></a>
			<a class="footer_btn msg_btn" href="${ctx}/app/manager/message">消息<div class="msg_countmine" style="right: 30%;">${unreadMessageNum}</div></a>
			<a class="footer_btn mine_btn active" href="javascript:void(0)">我的</a>
		</footer>
		<div id="tishi" class="alertDialog undis">
	    	<div class="tip-wrap">
	    		<p class="tip-tit">提示</p>
		    	<p class="tip-content">您确定要退出登录吗？</p>
		    	<a class="tip-btn bor_rgt" href="javascript:;" onclick="cancel()">取消</a>
		    	<a class="tip-btn" href="javascript:;" onclick="logout()">确定</a>
	    	</div>
	    </div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
		var u = navigator.userAgent;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端	
		if(isAndroid){
			var callbackButton = document.getElementById("clearId");
			callbackButton.onclick = function(e){
				android.cacheclear();
			}
		}
		if(isiOS){
			window.onerror = function(err) {
	     		log('window.onerror: ' + err)
	     	}
	        function setupWebViewJavascriptBridge(callback) {
	             if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
	             if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
	             window.WVJBCallbacks = [callback];
	             var WVJBIframe = document.createElement('iframe');
	             WVJBIframe.style.display = 'none';
	             WVJBIframe.src = 'https://__bridge_loaded__';
	             document.documentElement.appendChild(WVJBIframe);
	             setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0);
	        }
	        setupWebViewJavascriptBridge(function(bridge) {
	     		//var callbackButton = document.getElementById('buttons').appendChild(document.createElement('button'))
	     		var callbackButton = document.getElementById("clearId");
	     		//callbackButton.innerHTML = 'Fire testObjcCallback';
	     		callbackButton.onclick = function(e) {
	     			e.preventDefault()
	     			bridge.callHandler('cacheclear', function(response) {
	     			});
	     		}
	     	})
		}
		
		function showTishi(){
			$("#tishi").show();
		}
		function cancel(){
			$("#tishi").hide();
		}
		function logout(){
			window.location.href="${ctx}/app/manager/toLogout";
		}
	</script>
</body>
</html>