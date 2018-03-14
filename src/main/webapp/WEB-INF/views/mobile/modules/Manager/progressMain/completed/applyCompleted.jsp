<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>竣工申请</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/complete.css"/>
</head>
<style type="text/css">
/* .picSty1{
	width:2rem;
	height:1.5rem;
	position:absolute;
	left:1.25rem;
	top:1rem;
} */
/* .picSty2{
	width:4rem;
	height:1.5rem;
	position:absolute;
	left:6.25rem;
	top:1rem;
}
.picSty3{
	width:2rem;
	height:1.5rem;
	position:absolute;
	left:1.25rem;
	top:3.95rem;
} */
.new_pic1{
	width:100%;
	height:100%;
}
.new_pic2{
	width:100%;
	height:100%;
}
.new_pic3{
	width:100%;
	height:100%;
}
</style>

<body>
	<div class="g-sign_list">
		<header class="header">
			<a class="back_btn" href="${ctx}/app/manager/completedList"></a>
			<h2 class="title">竣工申请</h2>
		</header><!-- /header -->
		<form id="jvForm" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" name="orderID" value="${order.id }"/>
		<ul class="pad_top2 pad_btm2 intro">
			<li class="clearfix">
				<span class="intro_left">顾客信息：</span>
				<p class="intro_right">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
			</li>
			<li class="clearfix">
				<span class="intro_left">计划竣工日期：</span>
				<p class="intro_right"><fmt:formatDate type="date" value="${order.contractEndDate }"/></p>
			</li>
			<li class="clearfix">
				<span class="intro_left"><label for="input_date">实际竣工日期：</label></span>
				<p class="intro_right">
					<input id="txtBeginDate" class="date" type="text" value="2016-08-01" name="realFinishProjectDate" placeholder="请输入日期" 
						data-lcalendar="2000-01-01,2000-01-01" />
				</p>
			</li>
			<c:if test="${!empty finishProjectBill}">
			<li class="clearfix">
				<span class="intro_left">拒绝原因：</span>
				<p class="intro_right">${finishProjectBill.checkWords }</p>
			</li>
			</c:if>
		</ul>
		</form>
		<section class="photo_contain">
			<div class="camera_container clearfix">
				<p class="col_6 font28 float_left"><em class="before font28 col_blue">*</em>工程竣工验收单<em class="after font28 col_blue">*</em></p>
				<div class="pic camera cameras" id="camera1">
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="调取摄像头">
					<input style="z-index:9;" class="file" type="file" accept="image/*"  onchange="preview1(this)" multiple="multiple" value="请选择图片">
				</div>
			</div>
			<div class="camera_container clearfix">
				<p class="col_6 font28 float_left"><em class="before font28 col_blue">*</em>项目总结书<em class="after font28 col_blue">*</em></p>
				<div class="pic camera cameras" id="camera2">
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="调取摄像头">
					<input class="file" type="file" accept="image/*"  onchange="preview2(this)" multiple="multiple" value="请选择图片">
				</div>
			</div>
			<div class="camera_container clearfix">
				<p class="col_6 font28 float_left">延期确认单</p>
				<div class="pic camera cameras" id="camera3">
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="调取摄像头">
					<input class="file" type="file" accept="image/*"  onchange="preview3(this)" multiple="multiple" value="请选择图片">
				</div>
			</div>
		</section>
		<footer>
			<a class="apply_btn" id="apply_btn" href="javascript:void(0)">确认申请</a>
		</footer>
	</div>

	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript">
	(function(){
		// 获取当前日期，结束日期
    	var now = new Date(),
    		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
    		end = new Date(now.setFullYear(now.getFullYear()+1)),
    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');

    	var lcalendar = start+','+end;
    	$('#txtBeginDate').val(start);
    	// 将时间限制加到input标签上。
    	$('#txtBeginDate').attr('data-lcalendar', lcalendar);
		var calendar = new lCalendar();
		calendar.init({
		    'trigger': '#txtBeginDate',//标签id
		    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
		});
	}())
	
	//图片上传显示 
	function preview1(file) {
		var prevDiv = $('#camera1');
		$(".picSty1").remove();
		$("#num1").attr('name','');//每次进来清空当前input中name属性值
		$("#num1").val('');//每次进来清空当前input中value属性值
		if (file.files && file.files[0]) {  
			var reader = new FileReader();  
			reader.onload = function(evt){
				$('<div class="picSty1 plus"><img class="new_pic1" src="' + evt.target.result + '" onload="compresspic(this,uploadpic1);"/></div>').appendTo('#camera1');
			}    
			reader.readAsDataURL(file.files[0]);  
		}else {
			prevDiv.innerHTML = '<div class="new_pic1" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
			var pic = '<div class="new_pic1" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
			console.log(file,file.value);
		}
	}
	
	function uploadpic1(pic){
		var hiddenFrom = document.getElementById("jvForm");
		var input = document.createElement("input");
		if(pic){
			input.setAttribute("type","hidden");
			input.setAttribute("id","num1");
			input.setAttribute("name","photos1");
			input.setAttribute("value", pic);
			hiddenFrom.appendChild(input);//将元素添加到form中
		}
	}
	
	//图片上传显示 
	function preview2(file) {
		var prevDiv = $('#camera2');
		$(".picSty2").remove();
		$("#num2").attr('name','');//每次进来清空当前input中name属性值
		$("#num2").val('');//每次进来清空当前input中value属性值
		if (file.files && file.files[0]) {  
			var reader = new FileReader();  
			reader.onload = function(evt){
				$('<div class="picSty2 plus"><img class="new_pic2" src="' + evt.target.result + '" onload="compresspic(this,uploadpic2);"/></div>').appendTo('#camera2');
			}    
			reader.readAsDataURL(file.files[0]);  
		}else {
			prevDiv.innerHTML = '<div class="new_pic2" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
			var pic = '<div class="new_pic2" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
			console.log(file,file.value);
		}
	}
	
	function uploadpic2(pic){
		var hiddenFrom = document.getElementById("jvForm");
		var input = document.createElement("input");
		if(pic){
			input.setAttribute("type","hidden");
			input.setAttribute("id","num2");
			input.setAttribute("name","photos2");
			input.setAttribute("value", pic);
			hiddenFrom.appendChild(input);//将元素添加到form中
		}
	}
	
	//图片上传显示 
	function preview3(file) {
		var prevDiv = $('#camera3');
		$(".picSty3").remove();
		$("#num3").attr('name','');//每次进来清空当前input中name属性值
		$("#num3").val('');//每次进来清空当前input中value属性值
		if (file.files && file.files[0]) {  
			var reader = new FileReader();  
			reader.onload = function(evt){
				$('<div class="picSty3 plus"><img class="new_pic3" src="' + evt.target.result + '" onload="compresspic(this,uploadpic3);"/></div>').appendTo('#camera3');
			}    
			reader.readAsDataURL(file.files[0]);  
		}else {
			prevDiv.innerHTML = '<div class="new_pic3" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
			var pic = '<div class="new_pic3" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
			console.log(file,file.value);
		}
	}
	
	function uploadpic3(pic){
		var hiddenFrom = document.getElementById("jvForm");
		var input = document.createElement("input");
		if(pic){
			input.setAttribute("type","hidden");
			input.setAttribute("id","num3");
			input.setAttribute("name","photos3");
			input.setAttribute("value", pic);
			hiddenFrom.appendChild(input);//将元素添加到form中
		}
	}
	
	//删除按钮
 	/* $(document).on('click', '.g-sign_list .pic > a', function(){
		$(this).parent().remove();
		//$("#num").val("0");
	}); */
	$(document).ready(function() {
		//绑定onclick事件
		$("#apply_btn").bind('click',cofirmCompletedSubmit);
	});
	
	function cofirmCompletedSubmit(){
		var pic1 = $("#num1").val();
		if(typeof(pic1) == "undefined"){
			globalUtil.fn.alert("工程竣工验收单是必选项...",1.0);
			return false;
		}
		
		var pic2 = $("#num2").val();
		if(typeof(pic2) == "undefined"){
			globalUtil.fn.alert("项目总结书是必选项...",1.0);
			return false;
		}
		
		$("#apply_btn").css("color","#CCC");
		$("#apply_btn").unbind("click");
		var options ={
			url : "${ctx}/app/manager/cofirmCompletedSubmit",
			type: "post",
			success : function(data){
				//alert(data);
				if(data == 0){
					 globalUtil.fn.alert("操作成功...",2.0);
					 window.location.href = "${ctx}/app/manager/completedList";
				  }
				  if(data == 1){
				  	 globalUtil.fn.alert("参数为空...",2.0);
				  }
				  if(data == 2){
					  globalUtil.fn.alert("修改竣工数据失败...",2.0);
				  }
				  if(data == 3){
					  globalUtil.fn.alert("修改订单状态失败...",2.0);
				  }
			}
		}
		//ajax提交form
		$("#jvForm").ajaxSubmit(options);
	}
	</script>
</body>
</html>