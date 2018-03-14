<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/mobile/modules/home/footer.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/bxyTopCom.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/unLogin.css">
</head>

<body>
    <div class="bxy_setIndex_warp">
        <!--顶部公共样式start-->
        <div class="bxy_setIndex_warp_header">
            <header><a href="${ctx}/app/home/index"><span class="icon_back"></span></a>
                <p>我的</p>
            </header>
        </div>
        <!--顶部公共结构end-->
        <!--内容部分start-->
        <div class="bxy_setIndex_warp_con">
            <div class="main">
                <img src="${ctxStatic}/mobile/modules/home/images/wei_bj.png" alt="">
            </div>
            <div class="main_info">
                <p>我是一个粉刷匠，粉刷本领强…</p>
                <p>小美会用心为您装修好每一个细节</p>
            </div>
            <div class="land">
            	<a id="loginButton">立即登录</a>
            </div>
        </div>
    </div>
    <script src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
    <script src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript">
    var u = navigator.userAgent;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	//alert('是否是Android：'+isAndroid);
	//alert('是否是iOS：'+isiOS);	
	if(isAndroid){
		var callbackButton = document.getElementById('loginButton');
		callbackButton.onclick = function(e){
			android.login();
			//android.loginActivity();
		}
		/* android.login(); */
	}
	if(isiOS){
		window.onerror = function(err) {
			log('window.onerror: ' + err);
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
			//callbackButton.innerHTML = 'Fire testObjcCallback'
			var callbackButton = document.getElementById('loginButton');
			callbackButton.onclick = function(e) {
				e.preventDefault();
				bridge.callHandler('login', function(response) {
				})
			}
			/* bridge.callHandler('login', function(response) {
			}); */
		})
	}
</script>
</body>
</html>