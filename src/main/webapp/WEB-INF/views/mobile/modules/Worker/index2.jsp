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
</head>
<body>
	<div class="g-info">
		<div class="top">
			<div class="user_info">
				<img src="${ctxStatic}/mobile/modules/Worker/images/headimg.png" alt="headimg">
				<span class="user_name">${worker.realname }</span>
				<div class="star">
					<c:if test="${worker.star == null || worker.star == 0 }">
						<span></span>
						<span></span>
						<span></span>
						<span></span>
						<span></span>
					</c:if>
					<c:if test="${worker.star != null && worker.star !=0 }">
						<c:forEach begin="0" end="${worker.star-1}" step="1">
							<span class="bling"></span>
						</c:forEach>
						<c:forEach begin="1" end="${5-worker.star }" step="1">
							<span></span>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
		
		<ul class="info_ul">
			<li><a href="${ctx}/app/worker/salaryList">薪酬确认</a></li>
			<li><a href="sign_list.html">催促付款</a></li>
			<!-- <li><a href="done_apply.html">申请完工</a></li>
			<li><a href="message.html">消息通知</a></li>
			<li><a href="method.html">工艺工法</a></li> -->
		</ul>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
</body>
</html>