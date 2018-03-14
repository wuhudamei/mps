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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/managerInfo.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/manage_home.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/message2.css"/>
</head>
<body>
	<div class="g-info">
		<div class="alls sec home_sec">
			<header class="header">
				<h2 class="title">首页</h2>
			</header>
			<div class="sec task_sec">
				<nav class="of_nav font0 clearfix">
					<a class="of_btn" href="${ctx}/app/manager/auxiliary/order?index=1">
						<i><img src="${ctxStatic}/mobile/modules/Manager/images/of_auxi_icon.png" alt="申请辅料"></i>
						<span class="explain font28 col_3">申请辅料</span>
					</a>
					<a class="of_btn" href="${ctx}/app/manager/enginInstall?index=1&apply=1">
						<i><img src="${ctxStatic}/mobile/modules/Manager/images/of_anhzuang.png" alt="工程安装"></i>
						<span class="explain font28 col_3">安装申请</span>
					</a>
					<a class="of_btn" href="${ctx}/app/manager/packageSettlementList?index=1">
						<i><img src="${ctxStatic}/mobile/modules/Manager/images/of_budget_icon.png" alt="任务包结算"></i>
						<span class="explain font28 col_3">任务包结算</span>
					</a>
				</nav>
				<ul class="home_ul font0">
					<li class="hgt_88 mete"><a class="font28 col_3 list_a" href="${ctx}/app/manager/meterialManagementList">材料管理</a></li>
					<li class="hgt_88 progess"><a class="font28 col_3 list_a" href="${ctx}/app/manager/progressList">进度管理</a></li>
					<li class="hgt_88 forward"><a class="font28 col_3 list_a" href="javascript:;">进度预警</a></li>
					<li class="hgt_88 task"><a class="font28 col_3 list_a" href="${ctx}/app/manager/taskPackageManager">任务包管理</a></li>
					<li class="hgt_88 qulity"><a class="font28 col_3 list_a" href="${ctx}/app/manager/qualityControlList">质量管理</a></li>
					<li class="hgt_88 anzhuang"><a class="font28 col_3 list_a" href="${ctx}/app/manager/projectInstallMenu">工程安装</a></li>
					<li class="hgt_88 change"><a class="font28 col_3 list_a" href="${ctx}/app/manager/changeManagement/index">变更管理</a></li>
					<li class="hgt_88 cost"><a class="font28 col_3 list_a" href="${ctx}/app/manager/toQueryPmGuaranteeMoneyLog">结算管理</a></li>
					<li class="hgt_88 return"><a class="font28 col_3 list_a" href="${ctx}/app/manager/orderReport/toOrderReportManage">小美返单</a></li>
				</ul>
			</div>
		</div>
		<footer>
			<a class="footer_btn home_btn active" href="javascript:void(0)">工作</a>
			<!-- <a class="footer_btn queue_btn" href="javascript:void(0)">待办</a> -->
			<a class="footer_btn msg_btn" href="javascript:void(0)">待办<div class="msg_countmine" style="right: 30%;">${toDoDealNum}</div></a>
			<a class="footer_btn msg_btn" href="${ctx}/app/manager/message">消息<div class="msg_countmine">${unreadMessageNum }</div></a>
			<a class="footer_btn mine_btn" href="${ctx}/app/manager/indexMine">我的</a>
		</footer>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
</body>
</html>