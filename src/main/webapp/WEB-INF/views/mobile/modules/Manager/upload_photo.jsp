<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>现场签到</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/upload_photo.css"/>
</head>

<script type="text/javascript">
	var allHtml='';
	
	function sign(){
		
		alert("签到成功!");
		$("#jvForm").submit(); 
	}
	
	
function uploadPic(){
	var options={
		url: "${ctx}/app/manager/savePhoto?orderId=${orderId}",
		type : "post",
		dataType : "json" ,
		success : function(data){
			var html='';
			for(var i =0;i<data.length;i++){
			
				 html +='<div> <img src="/mdn'+data[i]+'" alt="图片" name="pic" >'+
				 '<a href="javascript:void(0)" onclick="jQuery(this).parent().remove()">删除</a></div>';
				
			
			}
			allHtml+=html;
			$("#uploadPhoto").html(allHtml);
		}
		
		
		
	}
	
	$("#jvForm").ajaxSubmit(options);
	
	
	
	
	
	
}

	

</script>
<body>
	<div class="g-upload_photo">
		<header >
			<a class="back_btn"  onclick="history.go(-1)" href="javascript:void(0)"></a>
			<h2 class="title">现场签到</h2>
		</header><!-- /header -->
		<div class="tips">
			<p>请上传到达现场照片</p>
			<p>必须上传【本人+背景】图片，至少上传一张照片</p>
		</div>
		
		<section class="upload_photo_list clearfix shadow">
		<form id="jvForm" action="${ctx }/app/manager/sign" method="post" enctype="multipart/form-data">
			
			<input type="text" hidden="hidden" name="orderId" value="${orderId }">
			<div class="camera">
				<img src="${ctxStatic}/mobile/modules/Manager/images/upload_pic.png" alt="调取摄像头">
				<input type="file" name="pic" onchange="uploadPic()" id="pic" multiple="multiple"  value="请选择图片">
				</div>
				
			
			<span class="pic" id="uploadPhoto">
				
			</span>
			
		
			<div>
				<a href="#" onclick="sign()">上传</a>
			</div>
			
		</form>
	
			
		</section>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
</body>
</html>