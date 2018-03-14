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
	<title>材料管理</title>
	<%-- <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/info.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/progress_manage.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/manage_meteri.css"/> --%>
	 <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20171024/homePage.css" />
</head>
<body>
	<%-- <div class="g_info g-materi">
		<header>
			<a class="back_btn"  href="${ctx }/app/manager/toindex"></a>
			<h2 class="title">材料管理</h2>
		</header><!-- /header -->
		<ul class="info_ul">
			<li><a href="${ctx}/app/manager/auxiliary/order?index=0">申请辅料</a></li>
			<li class="wallSqure"><a href="${ctx}/app/manager/wallFloorTileRecheck/list">墙地砖实测面积复核</a></li>
			<li><a href="${ctx}/app/manager/materialManagement/wallAndFloorNew/list">申请墙地砖</a></li>
			<li><a href="${ctx}/app/manager/problem/wallAndFloor/list?wallAndFloorProblem=1">墙地砖问题上报</a></li>
			<li><a href="${ctx}/app/manager/applySwitchPanel/orderList">申请开关面板</a></li>
			<li><a href="${ctx}/app/manager/applyStandardMaterial/standarMaterialList">申请标化材料</a></li>
			<li><a href="${ctx}/app/manager/applyLightMaterial/lightMaterialList">申请灯具和五金</a></li>
			<li><a href="${ctx}/app/manager/applySand/orderList">申请沙子水泥</a></li> 
			<li><a href="${ctx}/app/manager/purchaseList">我要收货</a></li>
			<li><a href="${ctx}/app/manager/receivedBillList">收货记录</a></li>
			<li><a href="${ctx}/app/manager/applyMaterialSelfbuyReimburse/list">自采材料报销申请</a></li>
		</ul>
	</div> --%>
	 <div class="">
        <header class="header">
            <a class="back_btn" href="${ctx }/app/manager/toindex"></a>
            <h2 class="title">材料管理</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88 font0">
            <ul class="mar_top24">
                <a class="list-line bg_f icon meterialAuxiliary" href="${ctx}/app/manager/auxiliary/order?index=0">
                    <li class="list-text">申请辅料</li>
                </a>
                <a class="list-line bg_f icon meterialwallFloorTileRecheck" href="${ctx}/app/manager/wallFloorTileRecheck/list">
                    <li class="list-text">墙地砖实测面积复核</li>
                </a>
                <a class="list-line bg_f icon meterialwallAndFloorNew" href="${ctx}/app/manager/materialManagement/wallAndFloorNew/list">
                    <li class="list-text">申请墙地砖</li>
                </a>
                <a class="list-line bg_f icon meterialwallAndFloorQus" href="${ctx}/app/manager/problem/wallAndFloor/list?wallAndFloorProblem=1">
                    <li class="list-text">墙地砖问题上报</li>
                </a>
                <a class="list-line bg_f icon meterialwallAndFloor" href="${ctx}/app/manager/applySwitchPanel/orderList">
                    <li class="list-text">申请开关面板</li>
                </a>
                <a class="list-line bg_f icon meterialapplySwitchPanel" href="${ctx}/app/manager/applyLightMaterial/lightMaterialList">
                    <li class="list-text">申请筒灯灯带</li>
                </a>
                <a class="list-line bg_f icon meterialapplyStandard" href="${ctx}/app/manager/applyStandardMaterial/standarMaterialList">
                    <li class="list-text">申请标化材料</li>
                </a>
                <a class="list-line bg_f icon meterialapplyLight" href="${ctx}/app/manager/applySand/orderList">
                    <li class="list-text">申请沙子水泥</li>
                </a>
               <!--  <a class="list-line bg_f icon meterialapplySand disN" href="javascript:void(0)">
                    <li class="list-text">申请灯具和五金</li>
                </a> -->
            </ul>
            <ul class="mar_top44">
                <a class="list-line bg_f icon meterialPurchase" href="${ctx}/app/manager/purchaseList">
                    <li class="list-text">我要收货</li>
                </a>
                <a class="list-line bg_f icon meterialreceivedBill" href="${ctx}/app/manager/receivedBillList">
                    <li class="list-text">收货记录</li>
                </a>
            </ul>
            <ul class="mar_top44">
                <a class="list-line bg_f icon meterialapplyExpense" href="${ctx}/app/manager/applyMaterialSelfbuyReimburse/list">
                    <li class="list-text">自采材料报销申请</li>
                </a>
            </ul>
        </section>
        <div style="padding-bottom:300px;"></div>
    </div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>
</html>