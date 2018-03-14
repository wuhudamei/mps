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
	<title>播报图片</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/broadcast_details.css"/>
	<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/globalUtil2.css" />
</head>
<body>
	<div class="g-">
		<header class="header">
			<a class="back_btn" href="javascript:void(0)" onclick="history.go(-1)"></a>
			<h2 class="title">播报图片</h2>
		</header><!-- /header -->
		<div class="pad_top11">
			<div class="pad_left3 bg_f font0 shadow">
				<p class="tit_line">
					<span class="com_icon pad_rgt2"></span>
					
					<c:if test="${not empty error }">
					<span class="font28 col_3">${error }  </span>
					</c:if>
					<c:if test="${empty error }">
				<span class="font28 col_3">基本信息  </span>
					
					
				</p>
				<ul class="com_lis">
					<li class="font28">
						<span class="col_6">小区名称：</span>
						<p class="col_3 flow pad_rgt3">${broadcast.communityName }-${broadcast.buildNumber }-${broadcast.buildUnit }-${broadcast.buildName }-${broadcast.customerName }</p>
					</li>
					<li class="font28">
						<span class="col_6">申 请 人：</span>
						<p class="col_3 flow pad_rgt3">${broadcast.applyBroadCastName }-${broadcast.applyBroadCastPhone }</p>
					</li>
					<li class="font28">
						<span class="col_6">播报类型：</span>
						<p class="col_3 flow pad_rgt3">${broadcast.broadcastName }</p>
					</li>
					<li class="font28">
						<span class="col_6">申请播报日期：</span>
						<p class="col_3 flow pad_rgt3"><fmt:formatDate value="${broadcast.applyBroadCastDate }" pattern="yyyy-MM-dd"/> </p>
					</li>
					<a id="tele" href="tel:${broadcast.applyBroadCastPhone }">
						<img class="phone" src="${ctxStatic}/mobile/modules/Manager/images/tele.png" alt="拨打电话">
					</a>
				</ul>
			</div>
			<form id ="jvForm">
				<input  type="text"  name="broadcastId" hidden="hidden" value="${broadcast.broadcastId}" />
			<div class="mar_top2 bg_f font0 shadow">
				<p class="mar_left3 tit_line">
					<span class="com_icon pic_icon pad_rgt2"></span>
					<span class="font28 col_3">图片信息</span>
					<a class="up">
						<img src="${ctxStatic}/mobile/modules/Manager/images/camera.png" alt="上传照片" >
						<input type="file" accept="image/*" onchange="preview(this)">
					</a>
				</p>
				<section class="pic_tot bg_f clearfix" id="picShow">
					<!-- 此处的div也是点击上传图片的结构 -->
					<c:forEach items="${list }" var="pic">
					<div class="pic_wrapper">
						<div class="pic_container"><img src="${picPrefix}${pic.picPath}" alt="播报图片" onerror="nofind()"></div>
						<p class="font20 col_9">客户端状态：<i>展示</i></p>
						<a class="show_btn" href="javascript:;">不展示</a>
						<input  type="text"  name="isShow" hidden="hidden" value="1" />
						<input  type="text"  name="picIds" hidden="hidden" value="${pic.picId}" />
					
					</div>
					</c:forEach>
				</section>
			</div>
				</form>
			</div>
		</div>
		<footer>
			<a class="sure_btn" href="javascript:;" onclick="submitData()">确定</a>
		</footer>
		</c:if>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script> //图片没有 就使用默认图片
	 function nofind(){

		 var img=event.srcElement;
		
		 img.src="/static/mobile/modules/Manager/css/photo/auxiliaryPhotoForNothing.png";

		 img.onerror=null; 

		 } 
	 
	
		$(function(){
			$(document).on('click', '.show_btn', function(){
				var text = $(this).text();
			var isShow = $(this).next();
				if(text == '展示'){
					$(this).text('不展示');
					$(isShow).val("1")
				}else{
					$(this).text('展示');
					$(isShow).val("0")
				}
				$(this).parent().find('i').text(text);
			});
		});
		

		//图片上传显示 
		function preview(file) {
			var prevDiv = $('#picShow'); 
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
				$("#picShow").append('<div class="pic_wrapper"><div class="pic_container"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic);"/></div><p class="font20 col_9">客户端状态：<i>展示</i></p><a class="show_btn" href="javascript:;" onclick="show()">不展示</a><input  type="text"  name="isShow" hidden="hidden"  value="1" /></div>');
				/* 	$().insertAfter('#picShow'); */
				}  
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		}
		function uploadpic(pic){
			var hiddenFrom = document.getElementById("jvForm");
			var input =document.createElement("input");
			if(pic){
				input.setAttribute("type","hidden");
				input.setAttribute("name","photos");
				input.setAttribute("value", pic);
				hiddenFrom.appendChild(input);//将元素添加到form中
			}
		}
		
	
		
		function  submitData(){
			var redirectUrl = "${ctx}/app/manager/broadcast/index";
			var options ={
					url : "${ctx}/app/manager/broadcast/check_broadcast_pic_and_status.php",
					type: "POST",
					success : function(data){
						if(data == 1){
							globalUtil.fn.alert('操作成功...',2.0);			
							window.location.href = redirectUrl;
						}else if(data==2){
							globalUtil.fn.alert('请至少展示一张图片...',2.0);			
						
						}else{
							
							globalUtil.fn.alert('操作失败',2.0);			
							
							
						}
					}
						
				}
				//ajax提交FORM
				$("#jvForm").ajaxSubmit(options);
			
		}
		
		function show(){
			var text = $(this).text();
			var isShow = $(this).next();
				if(text == '展示'){
					$(this).text('不展示');
					$(isShow).val("1")
				}else{
					$(this).text('展示');
					$(isShow).val("0")
				}
				$(this).parent().find('i').text(text);
			
		}
		
	</script>
</body>
</html>