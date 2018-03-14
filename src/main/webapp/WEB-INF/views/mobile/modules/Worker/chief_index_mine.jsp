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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/info.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/message2.css"/>
	<!-- <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/manager_info.css"/> -->
</head>
<body>
	<div class="g-info">
		<div class="top">
			<div class="user_info">
				<img src="${ctxStatic}/mobile/modules/Worker/images/headimg.png" alt="headimg">
				<span class="user_name">${worker.realname }</span>
				<div class="star">
					<c:if test="${employeeGroup.star == null || employeeGroup.star == 0 }">
						<span></span>
						<span></span>
						<span></span>
						<span></span>
						<span></span>
					</c:if>
					<c:if test="${employeeGroup.star != null && employeeGroup.star !=0 }">
						<c:forEach begin="0" end="${employeeGroup.star-1}" step="1">
							<span class="bling"></span>
						</c:forEach>
						<c:forEach begin="1" end="${5-employeeGroup.star }" step="1">
							<span></span>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
		<div class="alls sec mine_sec">
			<nav class="info_nav">
				<a href="javascript:void(0)">
					<span class="number">${settled }</span>
					<span class="explain">已结算完任务包</span>
				</a>
				<a href="javascript:void(0)">
					<span class="number">${settling }</span>
					<span class="explain">未结算完任务包</span>
				</a>
			</nav>
			<ul class="info_ul">
				<li><a href="${ctx}/app/worker/myTaskpackageList">我的任务</a></li>
				<li><a href="${ctx}/app/worker/bookList">我的账本</a></li>
				<li><a href="${ctx}/app/worker/guarMoneyList">质保金</a></li>
				<li><a href="${ctx}/app/worker/showRewardAmount">我的奖金</a></li>
				<li><a href="${ctx}/app/worker/method/methodOne">工艺工法</a></li>
				<li><a href="${ctx}/app/worker/orderReport/toOrderReportRecord">推荐记录</a></li>
				<li><a href="${ctx}/app/worker/common-issue.php">常见问题</a></li>
				<li><a id ="clearId" class="list_a font28 col_3" href="javascript:void(0)">清除缓存</a></li>
			</ul>
			<a class="quit font30 col_0780ec" href="javascript:void(0)" onclick="showTishi()">退出登录</a>
			<div style="padding-top:200px;"></div>
		</div>
		<footer>
			<a class="home_btn" href="${ctx}/app/worker/toindex">首页</a>
			<a class="msg_btn" style="position:relative;" href="${ctx}/app/worker/message">消息<div class="msg_count">${unreadMessageNum }</div></a>
			<a class="mine_btn active" href="javascript:void(0)">我的</a>
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
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
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
			window.location.href="${ctx}/app/worker/toLogout";
		}
	</script>
</body>
</html>