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
	<title>申请完工</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/globalUtil2.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/done_demand.css"/>
</head>

<body>
	<div class="g-done_demand">
		<header>
			<a class="back_btn" onclick="history.go(-1)"></a>
			<h2 class="title">申请完工</h2>
		</header><!-- /header -->
		<section class="demand_details">
			
			
			
				<p>
					<span class="task">任务包：</span>
					<span class="project">${doneDemandList.packageName}</span>
				</p>
				<div class="des">
					<div>
						<span>施工地点：</span>
						<p>${doneDemandList.customerMessage }--${doneDemandList.customerName}</p>
					</div>
					
					<div class="intro">请上传完工图片（至少上传4张）</div>
					<div class="pics clearfix" >
						<form id="jvForm" method="post" enctype="multipart/form-data">
							<input type="text" hidden="hidden" id="num" name="num" value="">
							
							<input type="text" hidden="hidden" name="orderTaskpackageId" value="${doneDemandList.id }">
							<input type="text" hidden="hidden" name="customerMessage" value="${doneDemandList.customerMessage }">
							<input type="text" hidden="hidden" name="customerName" value="${doneDemandList.customerName}">
							<input type="text" hidden="hidden" name="packageName" value="${doneDemandList.packageName}">
							
							<div class="pic clearfix" id="camera" href="javascript:void(0)">
									<img src="${ctxStatic}/mobile/modules/Worker/images/camera_icon.png" alt="调取摄像头">
									<input type="file" accept="image/*"  onchange="preview(this)">
							</div>
							
								<input type="text" hidden="hidden" id="shit"  value="1">
							<div class="pic" id="uploaddone">
								
							</div>
						</form>
						
					</div>
				</div>
				<a class="done_btn" href="javascript:void(0)" onclick="toSubmit()">申请完工</a>
		</section>
		<div class="g-mask undis">
			<div id="g-done_ask">
				<p class="first">确认要申请完工吗？</p>
				<p class="second">
					<a href="">取消</a>
					<a href="#" id="submit">确认</a>
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/global.js"></script>
	<script>
		/* $('.g-done_demand .pic > a').click(function(){
			$(this).parent().remove();
		}); */
		/* $('.done_btn').on('click',function(){
			$('.g-done_demand .g-mask').show();
		}); */
		$(document).on('click', '.g-done_demand .pic > a', function(){
			$(this).parent().remove();
		});
		
		//图片上传显示 
		function preview(file) {  
			if(!checkPic(file)){
				return false;
			}
			var num = $("#shit").val();
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="new"><img style="width:100%;height:2rem;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id= "'+num+'" /><a class="del" href="javascript:void(0)">删除</a></div>').appendTo('#uploaddone');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		} 
		$(document).on('click', '.del', function(){
			var numReal=$(this).prev().attr("id");
			$(("#num"+numReal)).remove();
			
			var num = $("#num").val();
			num--;
			$("#num").val(num);
			
			$(this).parent().remove();
			
		});
		
		function uploadpic(pic){
			
			var hiddenForm = document.getElementById("jvForm");
			var input =document.createElement("input");
			
			
			var num = $("#num").val();
		var shit =	$("#shit").val();
			if(pic){
				num++;
				
				input.setAttribute("id","num"+shit);
				input.setAttribute('hidden', 'hidden'); 
				input.setAttribute('name', 'photo'); 
				input.setAttribute("type","text");
				input.setAttribute("value", pic);
				hiddenForm.appendChild(input);
				shit++;
				$("#num").val(num);
				$("#shit").val(shit);
			}
		}
		
		
		
		function toSubmit(){
			var count = $("#num").val();
			
			if(count!=null && count>=1){
				if(count>=1 && count<4){
					globalUtil.fn.alert('请至少上传4张图片！',2.0);
				}else{
					$('.g-done_demand .g-mask').show();
				}
			}else{
				globalUtil.fn.alert('请上传完工图片！',2.0);
			}
		}
		
		
		
		
		
		$(document).ready(function() {
			//绑定onclick事件
			$("#submit").bind('click',submitData);
		});
		//上传中删除无效的file域
        $(".file").each(function(){
            $(this).remove();
        });
		function submitData(){
			$("#submit").css("color","#CCC");
			$("#submit").unbind("click");
			var options ={
					url: "${ctx}/app/worker/applyTaskPackage",
					type: "post",
					success:function(data){
						if(data==0){
							globalUtil.fn.alert('申请完工操作成功',2.0);			
							window.location.href="${ctx}/app/worker/doneApplyList";
						}else if(data == 1){
							globalUtil.fn.alert('已申请完工！',2.0);
						}
						
					}
			}
			
			$("#jvForm").ajaxSubmit(options);
			
		}
		function checkPic(file){
			   if($(file).val()==''||null==$(file).val()){
				   //$(file).parent().find("img").attr("src","${ctxStatic}/mobile/modules/Manager/images/upload_pic.png");
		    		return false;
		    	}
				 if (!/.(jpg|jpeg|png|JPG)$/.test($(file).val())) {       //判断上传图片是否符合格式
				        alert('上传图片格式不正确，请重新上传jpg、png类型的图片!');
				        return false;
				    }
				 var fileSize=$(file)[0].files[0].size;
				 //alert(fileSize);
				 fileSize = Math.round(fileSize / 1024 * 100) / 5120;
				 if (fileSize >= 100) {
				        alert('照片最大尺寸大于5M，请重新上传!');
				        return false;
				    }
				 return true;
			}
	</script>
</body>
</html>
