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
	<title>现场签到</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/map.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
</head>
<script type="text/javascript">
function sign(){
	$("#signButton").removeAttr("onclick");
	var signAddress = $("#address").val();
	var signDistance = $("#distance").val();
	$.post("${ctx}/app/manager/saveOrUpdateSign", $("#signForm").serialize(), function(data){
		if(data == "success"){
			globalUtil.fn.alert("签到成功!",2.0);
			window.location.href="${ctx}/app/manager/packageSettlementList";
		}else{
			$("#signButton").attr("onclick", "sign();");
			globalUtil.fn.alert("签到失败!",2.0);
		}
	});
	//window.location.href="${ctx}/app/manager/sign?signAddress="+signAddress+"&signDistance="+signDistance;
}

</script>
<body>
<form method="post" id="signForm" enctype="multipart/form-data">
	<div class="g-sign">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="javascript:void(0)"></a>
			<h2 class="title">现场签到</h2>
		</header><!-- /header -->
		<p class="position_text">正在定位...</p>
		<span hidden="hidden">${error}</span>
		<input id="lat" name="lat" hidden="hidden">
		<input id="lon" name="lon" hidden="hidden">
		<input id="packId"  hidden="hidden" name="relatedBusinessIdInt" value="${pack.id}">
		<input id="distance" hidden="hidden" name="signErrorDistance">
		<input id="address" hidden="hidden" name="signAddress">
		<div id="map"></div>
		<div class="btns">
			<!-- <div class="position_point"></div> -->
			<!-- <div class="right"> -->
				<!-- <a class="dispos_btn undis" href="${ctx }/app/manager/uploadPhoto?orderId=${orderId}">定位不准，我要上传照片</a> -->
				<a class="pos_btn undis" href="javascript:void(0)" onclick="sign()" id="signButton">签到</a>
			<!-- </div> -->
			<!-- <div class="wrong"> -->
				<a class="unpos_btn undis" href="${ctx}/app/manager/uploadPhoto?orderId=${orderId}">定位失败，请上传照片</a>
			<!-- </div> -->
		</div>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<%--<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/map_init3.js"></script>--%>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
</form>
</body>

<script type="text/javascript">
/*function userlocation(callback){
	var lng = 39.89553;
	var lat = 116.32531;
	var address = "北京市丰台区莲花池南里27号楼";
	callback(lng,lat,address);
}*/

function fun(lon,lat,address){
	$("#lat").val(lat);
	$("#lon").val(lon);
	$("#address").val(address);
	var distance = getDistance(${pack.lat},${pack.lon},lat,lon);
	$("#distance").val(distance);
}

userlocation(fun);

function toRad(d) {
	return d * Math.PI / 180;
}
function getDistance(lat1, lng1, lat2, lng2) {
	var dis = 0;
	var radLat1 = toRad(lat1);
	var radLat2 = toRad(lat2);
	var deltaLat = radLat1 - radLat2;
	var deltaLng = toRad(lng1) - toRad(lng2);
	var dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLng / 2), 2)));
	return dis * 6378137;
}
</script>

</html>