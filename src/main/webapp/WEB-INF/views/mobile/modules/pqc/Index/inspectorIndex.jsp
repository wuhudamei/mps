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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/info.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/message2.css"/>
</head><c:set var="now" value="<%=new java.util.Date()%>"/>
<body>
            <input type="text"  hidden="hidden" id="date" <fmt:formatDate type="date" pattern="yyyy-MM" 
            value="${now}" /> />
	<div class="g_info">
		<div class="top">
		<div class="user_info">
			<img src="${ctxStatic}/mobile/modules/Manager/images/headimg.png" alt="headimg">
			<span class="user_name">${inspector.realName }</span>
			<div class="star">
				<c:if test="${inspector.star == null || inspector.star == 0 }">
					<span></span>
					<span></span>
					<span></span>
					<span></span>
					<span></span>
				</c:if>
				<c:if test="${inspector.star != null && inspector.star !=0 }">
					<c:forEach begin="0" end="${inspector.star-1}" step="1">
						<span class="bling"></span>
					</c:forEach>
					<c:forEach begin="1" end="${5-inspector.star }" step="1">
						<span></span>
					</c:forEach>
				</c:if>
			</div>
		</div>
		</div>
		<%-- <nav class="info_nav">
			<a href="javascript:void(0)">
				<span class="number">${inspector.currentOrderCount }</span>
				<span class="explain">在施工数</span>
			</a>
			<a href="javascript:void(0)">
				<span class="number">${inspector.totalOrderCount }</span>
				<span class="explain">总订单数</span>
			</a>
			<a href="javascript:void(0)">
				<span class="number">${inspector.inspectReport}</span>
				<span class="explain">质检报告</span>
			</a>
		</nav> --%>
		<ul class="info_ul">
			<!-- <li class="order"><a href="javascript:void(0)">我的订单</a></li> -->
			<li class="fuhe"><a href="${ctx}/app/pqc/taskpackagerecheck/taskpackageRecheckList">任务包复核</a></li>
			<li class="chou"><a href="${ctx}/app/pqc/selectCheckList/list">抽检</a></li>
			<li class="yue"><a href="${ctx}/app/pqc/checkList/list">约检</a></li>
			<li class="yue"><a href="${ctx}/app/pqc/issueReport/toIssueReportIndex">约检问题上报</a></li>
			<li class="fu"><a href="${ctx }/app/pqc/recheck/recheckList">复检</a></li>
			<!-- <li class="jungong"><a href="javascript:void(0)">竣工审核</a></li> -->
			<%-- <li class="baogao"><a href="${ctx}/app/pqc/report/reportList">质检报告</a></li> --%>
			<%-- <li class="mingxi1"><a href="${ctx }/app/pqc/balanceOfOrder/balanceByOrderList">订单结算明细</a></li>
			<li class="mingxi2"><a href="${ctx }/app/pqc/balance/list">月度结算明细</a></li> --%>
			<%-- <li class="order1"><a href="${ctx }/app/pqc/myOrder/list.php">我的订单</a></li> --%>
			<li class="comment"><a href="${ctx }/app/pqc/evaluate/evalWorker/list">评价工人</a></li>
			<li class="brod"><a href="${ctx }/app/pqc/orderReport/orderreport">我要推荐</a></li>
			<li class="hgt_88 complain"><a href="${ctx }/app/pqc/project-issue/list.php">工程投诉</a></li>

			<!-- <li class="clears"><a id ="clearId" class="list_a font28 col_3" href="javascript:void(0)">清除缓存</a></li> -->
		</ul>
		
		
		
		<div style="padding-top:300px;"></div>
		<input type="text" hidden="hidden" value="${isevalCount }" id="evalCount">
	</div>
	<footer>
		<a class="footer_btn home_btn active" href="javascript:void(0)">工作</a>
		<a class="footer_btn todo_btn" href="${ctx}/app/pqc/backlog/preList.php">待办</a>
		<a class="footer_btn msg_btn" href="${ctx}/app/pqc/message">消息<div class="msg_countmine">${unreadMessageNum }</div></a>

		<a class="footer_btn mine_btn" href="${ctx}/app/pqc/indexMine">我的</a>
	</footer>
	<div class="alertDialog undis">
    	<div class="tip-wrap">
    		<p class="tip-tit">提示</p>
	    	<p class="tip-content">您有未评价的评价单，请及时进入【工人评价】进行评价。</p>
	    	<a class="tip-btn" href="javascript:;">关闭</a>
    	</div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
		var evalCount = $("#evalCount").val();
		if(null!=evalCount && evalCount>0){
			$('.alertDialog').show();
		}
		$(document).on('click', '.tip-btn', function(){
			$('.alertDialog').hide();
			$("#evalCount").val(0);
		});
		
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
	</script>
</body>
</html>