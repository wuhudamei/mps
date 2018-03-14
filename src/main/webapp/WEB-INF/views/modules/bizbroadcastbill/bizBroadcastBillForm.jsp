<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>播报管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript">
	function showFile(){
		$("#up").trigger("click");
		
	}
	function submitData(){
		$("#jvForm").submit();
		
	}

		
		$(document).on('click', '.show_btn', function(){
			var text = $(this).text();
			var isShowDiv = $(this).next();
			var isShow = $(isShowDiv).find("input[name='isShow']");
			
			if(text == '展示'){
				$(this).text('不展示');
				$(isShow).val("1")
			}else{
				$(this).text('展示');
				$(isShow).val("0")
			}
			$(this).parent().find('i').text(text);
			
		});
		
		
	


	//图片上传显示 
	function preview(file) {
		var prevDiv = $('#ulForm'); 
		if (file.files && file.files[0]) {  
			var reader = new FileReader();  
			reader.onload = function(evt){
				$("#picShow").append('<div class="pic_wrapper"><div class="pic_container" style="height: 210px;"><img style="height: 200px;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic);"/></div><p class="font20 col_9" style="line-height: 0.6rem;">客户端状态：<i>展示</i></p><a class="show_btn" href="javascript:;" onclick="show()">不展示</a><div class="pic_container" hidden="hidden"><input  type="text"  name="isShow" hidden="hidden"  value="1" /></div></div>');
				
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

	function show(){
		var text = $(this).text();
		var isShowDiv = $(this).next();
		var isShow = $(isShowDiv).find("input[name='isShow']");
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

<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/broadcast_details.css"/>
</head>
<body>

	<ul class="nav nav-tabs">                                                                                      
		<li class="active"><a href="javascript:void(0)">查看图片</a></li>
	</ul>  
	<ul class="ul-form breadcrumb" id="ulForm">
		<li class="btns"><input class="btn" type="button" value="返回" onclick="history.go(-1)"/></li>
		<li class="btns"><input class="btn btn-primary" type="button" value="上传图片" onclick="showFile()" /></li>
	</ul>



	<div class="g-">
			<form id ="jvForm" action="${ctx}/bizbroadcastbill/bizBroadcastBill/save" method="post" >
		<div class="pic_container" hidden="hidden">
			<input  type="text"  name="broadcastId" hidden="hidden" value="${broadcast.broadcastId}" />
			</div>
				<div class="mar_top2 bg_f font0 shadow">
				
					<p class="mar_left3 tit_line">
						<span class="com_icon pic_icon pad_rgt2"></span>
						<span class="font28 col_3">图片信息</span>
						<a class="up" hidden="hidden">
							<img src="${ctxStatic}/mobile/modules/Manager/images/camera.png" alt="上传照片">
							<input type="file" id="up" accept="image/*"  onchange="preview(this)">
						</a>
					</p>
					<section class="pic_tot bg_f clearfix" id="picShow">
						<!-- 此处的div也是点击上传图片的结构 -->
						<c:forEach items="${list }" var="pic">
							<div class="pic_wrapper">
								<div class="pic_container" style="height: 210px;"><img style="height: 200px;" src="${picPrefix}${pic.picUrl}" alt="播报图片" width="200" height="200"></div>
								<p class="font20 col_9" style="line-height: 0.6rem;">客户端状态：<i>展示</i></p> 
								<a class="show_btn" href="javascript:;" >不展示</a>
								<div class="pic_container" hidden="hidden">
								<input  type="text"  name="isShow" hidden="hidden" value="1" />
								<input  type="text"  name="picIds" hidden="hidden" value="${pic.picId}"/>
							
								</div>
								
							</div>
						</c:forEach>
					</section>
				</div>
			</form>
		</div>
		<footer>
			<a class="sure_btn" href="javascript:;" onclick="submitData()">确定</a>
		</footer>

</body>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>

</html>