<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/footer.css"/>
</head>
<body>
	<footer>
		<a class="home_btn active" href="javascript:void(0)">首页</a>
		<%-- <a class="mine_btn" href="${ctx}/app/home/toMyHome">我的</a> --%>
		<a class="mine_btn" onclick="customerLogin()">我的</a>
	</footer>
	<script type="text/javascript">
		var u = navigator.userAgent;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端	
	  	function customerLogin(){
			$.ajax({
				url:"${ctx}/app/home/toMyHome",
				type:"post",
				success:function(data){
					if(data == '2'){
						//调用原生的方法
						if(isAndroid){
							var data = android.tokenid();
							//alert(data);
							var json = eval("("+data+")");
							//alert(json);
							//alert(json.tokenid);
							if(json.tokenid != null){
								$("#tokenid").val(json.tokenid);
                            	$("#appType").val(json.appType);
                            	$("#sign").val(json.sign);
                            	$("#loginForm").submit();
							}else{//调用原生打开登录页面
								android.login();
							} 
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
						    	var uniqueId = 1
								function log(message, data) {
									var log = document.getElementById('log')
									var el = document.createElement('div')
									el.className = 'logLine'
									el.innerHTML = uniqueId++ + '. ' + message + ':<br/>' + JSON.stringify(data)
									if (log.children.length) { log.insertBefore(el, log.children[0]) }
									else { log.appendChild(el) }
								}
						    	/* bridge.registerHandler('pageSkip', function(data, responseCallback) {
									//log('ObjC called testJavascriptHandler with', data)
									alert("pageSkip");
									window.location.href="${ctx}/app/home/isLogin";
								}); */
								bridge.callHandler('tokenid', function(data,response) {
                                    if(data != null){
                                    	$("#tokenid").val(data.tokenid);
                                    	$("#appType").val(data.appType);
                                    	$("#sign").val(data.sign);
                                    	$("#loginForm").submit();
									}else{//调用原生打开登录页面
										bridge.callHandler('login', function(response) {
										});
									}
                                })
							});
						}
					}
					if(data == '1'){
						window.location.href ="${ctx}/app/home/toweichat";
					}
					if(data == '3'){
						window.location.href ="${ctx}/app/home/isLogin";
					}
				}
			});
		} 
	</script>
	<form id ="loginForm" action="/api/api/checkTokenId" method="post">
		<input type="hidden" value="" name="tokenid" id="tokenid">
		<input type="hidden" value="" name="appType" id="appType">
		<input type="hidden" value="" name="sign" id="sign">
	</form>
</body>
</html>