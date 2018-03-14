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
	<title>厂家复尺</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/recheck_pic.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/mask.css"/>
<%-- 	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reRuler.css"/> --%>
</head>
<body id="aboveId">
	<div class="g-recheck"  >
		<header class="header">
			<a class="back_btn" href="${ctx}/app/manager/checksizeList"></a>
			<h2 class="title">厂家复尺</h2>
		</header><!-- /header -->
		<section class="bg_f">
			
			<h3 class="blue_rnd">${info }</h3>
			
			<form id="jvForm" method="post" enctype="multipart/form-data">
				<input type="text" hidden="hidden" name="orderId" value="${orderId }">
				<ul class="inputs">
					<li class="clearfix">
						<span class="left">期望复尺日期　：</span>
						<p class="right">
							<input id="txtBeginDate" class="date mar_top17" type="text" readonly="" value="" name="checksizeDate" placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
						</p>
					</li>
					<li class="clearfix">
						<span class="left">复　尺　内　容：</span>
						<p class="right">
							<select class="mar_top17" id="checksizeType" name="checksizeType">
								<option value=""></option>
								<c:forEach items="${list }" var="type">
			                        <option value="${type.type }">${type.name }</option>
								</c:forEach>
		                    </select>
						</p>
					</li>
					<li class="clearfix area" style="margin-top:.2rem;">
						<span class="left">备　注　说　明：</span>
						<p class="right"><textarea name="remarks" placeholder=""></textarea></p>
					</li>
				</ul>
				<div class="photo_sec">
				
					<input type="text" hidden="hidden" id="num" name="num" value="">
							
					<div class="pic camera" id="camera" href="javascript:void(0)">
						<img class="up" src="${ctxStatic}/mobile/modules/Manager/images/upload_pic.png" alt="调取摄像头">
						<input type="file" accept="image/*" onchange="preview(this)">
					</div>
						<input type="text" hidden="hidden" id="shit"  value="1">
					<div id="uploaddone" class="photo_sec" style="border:0;">
								
					</div>
					
					<!-- 注释部分是上传图片的样式 -->
					<!-- <div class="pic">
						<img src="${ctxStatic}/mobile/modules/Manager/images/pic2.png" alt="上传图片">
						<a class="delBtn" href="javascript:void(0)">删除</a>
					</div>
					<div class="pic">
						<img src="${ctxStatic}/mobile/modules/Manager/images/pic2.png" alt="上传图片">
						<a class="delBtn" href="javascript:void(0)">删除</a>
					</div> -->
				</div>
			</form>
		</section>
		<footer>
			<a class="subBtn" href="javascript:void(0)" id="submit">提交厂家复尺</a>
		</footer>
	<div id="alertMask" class="alertMask" hidden="hidden">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通 知</p>
			<div class="font28 col_6 maskContent" ><span id="alertMasktext"></span></div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtnOne font33 col_fdfcfa" href="javascript:;" onclick="hide()">确定</a>
			</div>
		</div>
	</div>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/mobiscroll.custom-2.16.1.min.js"></script>
	<script>
	
	
		function hide(){
			$("#alertMask").attr("hidden",true);
		}
	
		function run_waitMe(effect,text){
			$('#aboveId').waitMe({
				effect: effect,
				text: text,
				bg: 'rgba(255,255,255,0.7)',
				color:'#000',
				sizeW:'',
				sizeH:'',
				source: 'img.svg'
			});
		}
	
		(function(){
			// 获取当前日期，结束日期
	    	var now = new Date(),
	    		start = new Date(now.setDate(now.getDate()+3)),
	    		start = globalUtil.fn.formatDate(start, 'yyyy-MM-dd'),
	    		end = new Date(now.setFullYear(now.getFullYear()+1)),
	    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');

	    	var lcalendar = start+','+end;
	    	// 将时间限制加到input标签上。
	    	$('#txtBeginDate').attr('data-lcalendar', lcalendar);
	    	$("#txtBeginDate").val(start);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
		}());
		
		
		//图片上传显示 
		function preview(file) { 
			var num = $("#shit").val();
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic);" id= "'+num+'"/><a class="delBtn" href="javascript:void(0)">删除</a></div>').insertAfter('.camera');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		} 
		$(document).on('click', '.delBtn', function(){
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
		
		
		
		
		
		
		
		
		
		$(document).ready(function() {
			//绑定onclick事件
			$("#submit").bind('click',submitData);
		});
		function submitData(){
			
			
			//var count = $("#num").val();
			var checksizeType = $("#checksizeType").val();
			var txtBeginDate = $("#txtBeginDate").val();
			var tst = $(":selected").text();
		
			if(txtBeginDate == null || txtBeginDate == ""){
				globalUtil.fn.alert('请输入实际复尺日期！',2.0);
				return false;
			}
			if(checksizeType == null || checksizeType == ""){
				globalUtil.fn.alert('请选择复尺内容！',2.0);
				return false;
			}
				
			$("#submit").css("color","#CCC");
			$("#submit").unbind("click");
			run_waitMe("win8",'请稍等 ,拼命提交中....');
			var options={
					url: "${ctx}/app/manager/checksize",
					type : "post",
					dataType : "json",
					success : function(data){
						
						if(data.code == 3){
							$('#aboveId').waitMe('hide');
							/* globalUtil.fn.alert("主材复尺("+tst+")在"+data.message+"(含)以后的日期才能申请厂商复尺！",5.0); */
							$("#alertMasktext").text("")
							//$("#alertMasktext").text("主材复尺("+tst+")在"+data.message+"(含)以后的日期才能申请厂商复尺！")
							$("#alertMasktext").text("该工地"+data.actualStartDate+"开工，按照工程部规定主材（"+tst+"）开工"+data.dayCount+"天后（"+data.message+"）才可以申请"+tst+"复尺，如有提前完工或疑问请联系大区经理。")
							$("#alertMask").attr("hidden",false);
							$("#submit").css("color","#fff");
							$("#submit").bind('click',submitData);
						}
						if(data == 0){
							  $('#aboveId').waitMe('hide');
							  globalUtil.fn.alert('上报厂家复尺成功',3.0);
							  var a = "${ctx}/app/manager/checksizeList";//成功后跳转URL
							  setTimeout('window.location.href="'+a+'"', 3000);
						 }
						if(data == 1){
							 globalUtil.fn.alert('您刚刚提交过厂家复尺，请耐心等待五分钟后再次操作！',3.0);
							 $('#aboveId').waitMe('hide');
							 $("#submit").bind('click',submitData);
							 $("#submit").css("color","#fff");
						}
						if(data == 2){
							 $('#aboveId').waitMe('hide');
							 globalUtil.fn.alert('上报厂家复尺信息保存失败！',3.0);
							 var b = "${ctx}/app/manager/checksizeList";
							 setTimeout('window.location.href="'+b+'"', 3000);
						}
					}
			}
			
			$("#jvForm").ajaxSubmit(options);	
			
		}
		
	</script>
</body>
</html>