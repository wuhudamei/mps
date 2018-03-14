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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/info.css"/>
</head>
<body>
	<div class="g_info">
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
			<li><a href="${ctx}/app/manager/appOrderList">我的订单</a></li>
			<li><a href="${ctx}/app/manager/meterialManagementList">材料管理</a></li>
			<li><a href="${ctx}/app/manager/progressList">进度管理</a></li>
			<%-- <li><a href="${ctx}/app/manager/packList">任务包查询</a></li> --%>
			<li><a href="${ctx}/app/manager/taskPackageManager">任务包管理</a></li>
			<li><a href="${ctx}/app/manager/changeManagement/index">变更管理</a></li>
			<li><a href="${ctx}/app/manager/qualityControlList">质量管理</a></li>
			<li><a href="${ctx}/app/manager/method/methodOne.html">工艺工法</a></li>
		</ul>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>
</html>