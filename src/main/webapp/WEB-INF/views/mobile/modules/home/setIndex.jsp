<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/globalUtil.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/bxyTopCom.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/setIndex.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/bxyMask.css">
    <title>设置</title>
</head>
<body>

<!--页面浮层退出start-->
<div class="bxy_updata_mask">
    <div class="bxy_updata_mask_top">
        <p>小美温馨提示</p>
    </div>
    <div class="bxy_updata_mask_con">
        <p>客官,你是否要退出登录?</p>
    </div>
    <div class="bxy_updata_mask_bottom clearfix">
        <div class="bxy_updata_mask_bottom_left fl" id="changeQuite">
            确定
        </div>
        <div class="bxy_updata_mask_bottom_right fr">
            取消
        </div>
    </div>
</div>
<!--页面浮层版本升级end-->
<!--页面浮层清理缓存start-->
<div class="bxy_setIndex_mask">
    <div class="bxy_setIndex_mask_icon clearfix">
        <div class="bxy_setIndex_mask_brash">
            <img src="${ctxStatic}/mobile/modules/home/images/brush.png" alt="">
        </div>
        <p class="fl">客官正在清理缓存 请稍后！</p>
    </div>
</div>
<!--页面浮层清理缓end-->
<div class="bxy_setIndex_warp">
    <!--顶部公共样式start-->
    <div class="bxy_setIndex_warp_header">
        <header><a href="${ctx}/app/home/isLogin"><span class="icon_back"></span></a><p>设置</p></header>
    </div>
    <!--顶部公共结构end-->
<!--内容部分start-->
   <div class="bxy_setIndex_warp_con">
       <p>常用功能</p>
       <ul class="list_con">
           <li class="clearfix" id="clearCookie">
               <p id="clearBtn" class="fl"><span class="icon_clearCookie"></span>清除缓存</p>
               <p class="fr"><i></i><span class="icon_enter">&nbsp;&nbsp;&nbsp;&nbsp;</span></p>
           </li>
           <li class="clearfix" id="versionli">
               <p class="fl"><span class="icon_updata"></span>版本升级</p>
               <p class="fr"><span id="version"><i>1.0.0.1</i></span><span class="icon_enter"></span></p>
           </li>
       </ul>
       <input type="button" value="退出登录" class="BxyquiteLogin">
   </div>
</div>
<script src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/global.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/setIndex.js"></script>
<script type="text/javascript">
   $(document).ready(function() {
	   var u = navigator.userAgent;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端	
		if(isiOS){
			
			$("#versionli").hide();
			
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
			     setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0)
			}
			setupWebViewJavascriptBridge(function(bridge) {
					bridge.callHandler('version', function(response) {
						//alert(response);
						$("#version").html(response);
					})	
					var callbackButton = document.getElementById("clearCookie");
					callbackButton.onclick = function(e) {
						e.preventDefault()
						bridge.callHandler('cacheclear', function(response) {
						})
					}
				})
		}
		if(isAndroid){
			var version = android.getVersionName();
			$("#version").html(version);
			var callbackButton = document.getElementById("clearCookie");
			callbackButton.onclick = function(e){
				android.cacheclear();
			}
		}
	});
</script>

</body>

</html>