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
	<title>项目图纸</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/draw.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
</head>

<script type="text/javascript">
	
	function downLoad(){
		window.location.href="";
		globalUtil.fn.alert('图片下载成功！',2.0);
		/* $.ajax({
			url:"${ctx}/app/manager/download",
			type : "POST",
			dataType : "json",
			data:{
				filePath : $("#filePath").val(),
				fileName : $("#fileName").val()
				},
			success : function(data){
				globalUtil.fn.alert('图片下载成功！',2.0);
		  	}
		}); */
	}

</script>
<body>
	<div class="g-method">
		<header>
            <a class="back_btn" onclick="history.go(-1)" href="javascript:void(0)"></a>
            <h2 class="title">项目图纸</h2>
        </header><!-- /header -->
		<section class="clearfix pad_top8">
			<h3 class="dot">${appOrder.communityName}-${appOrder.buildNumber}-${appOrder.buildUnit}-${appOrder.buildRoom}-${appOrder.customerName}</h3>
			<div class="shadow bg_f pad_btm3 clearfix">
				<c:forEach items="${appOrderCadfile }" var="appOrderCadfile">
					<div class="wid_50 align_c float_l pad_btm3">
						<input type="text" hidden="hidden" id="fileName" name="fileName" value="${appOrderCadfile.fileName }">
						<input type="text" hidden="hidden" id="filePath" name="filePath" value="${appOrderCadfile.filePath }">
						<p class="font28 col_6 align_c mar_btm">${appOrderCadfile.fileName }</p>
						<img class="draw_pic" src="${ctxStatic}/mobile/modules/Manager/images/draw.png" alt="图纸">
						<a class="block col_blue font28 btn"  href="${appOrderCadfile.filePath }">下载</a>
					</div>
				</c:forEach>
			</div>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
</body>
</html>