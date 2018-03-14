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
	<title>上报问题</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/applyDoneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/waitMe.css" >
	<style>
		.pad_btm40{padding-bottom:.4rem;}
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 84%;margin: 30% auto 0;border-radius: .1rem;background: #fff;font-size: .32rem;}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: .28rem;}
		.font33{font-size: .33rem;}
		.maskTit{height: 1rem;line-height: 1rem;text-align: center;}
		.maskContent{padding: .5rem;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: .2rem;padding-top: .2rem;}
		/* .maskBtn{display: block;width: 60%;} */
		.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
    		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}
	</style>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/worker/install/installProblem/list"></a>
			<h2 class="title">上报问题</h2>
		</header><!-- /header -->
		<form id="jvForm" action="" method="post" enctype="multipart/form-data">
		
			<input type="text" hidden="hidden" name="constructBillId" id= "constructBillId" value="${installItem.constructBillId }">
			
			<section class="pad_top88">
				<p class="pt24 pb30 pl30 pr30 font30 col_0780ec align_center">${installItem.communityName }-${installItem.buildNumber }-${installItem.buildUnit }-${installItem.buildRoom }-${installItem.installItemName }</p>
				<div class="sec pl30 pr30 font0 border_top border_btm mb12 bg_f shadow clearfix">
					<ul class="pad_top3">
						<li class="mb30 clearfix">
							<span class="col_6 font30 flo_left lineHgt56">问题分类：</span>
							<select class="quesSelect" id="problemTypeId" name="problemTypeId">
								<c:forEach items="${problemList }" var="problem">
									<option value="${problem.id }">${problem.typeName }</option>
								</c:forEach>
							</select>
						</li>
						<li class="mb30 clearfix">
							<span class="col_6 font30 flo_left lineHgt146">问题描述：</span>
							<textarea class="descri"  name="problemDescribe" id="problemDescribe" maxlength="100"></textarea>
						</li>
					</ul>
				</div>
			</section>
			<section class="pt30">
				<p class="pt44 pb48 pl30 bg_f border_top font28 col_3">上传现场照片</p>
				<div class="pics font0 shadow clearfix">
					<div class="pic camera" id="camera" href="javascript:void(0)">
						<img src="${ctxStatic}/mobile/modules/Worker/img/upPic.png" alt="">
						<input type="file" accept="image/*" onchange="preview(this)">
					</div>
					<input type="text" hidden="hidden" id="shit" value="1">
					<input type="text" hidden="hidden" id="num" value="">
					<%-- <div class="pic">
						<img src="${ctxStatic}/mobile/modules/Worker/img/eg.png" alt="">
						<a class="delBtn" href="javascript:void(0)"></a>
					</div> --%>
				</div>
			</section>
		</form>
		<div class="pt80">
			<a class="subBtn" href="javascript:;" id="start">提交问题</a>
		</div>
		<div style="padding-bottom:300px;"></div>
	</div>
	
	<div class="alertMask undis" id ="alert">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent" id="alertRemarks"></div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"  onclick="sure()" href="javascript:;">我知道了</a>
			</div>
		</div>
	</div>
	
	
	<div class="alertMask undis" id ="alert2">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">您确定要提交吗？</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtn font33 col_fdfcfa" href="javascript:void(0);" onclick="sure2()">
					确定
				</a>
				<a class="maskBtn font33 col_0780ec" href="javascript:void(0);" onclick="cancel()">取消</a>
			</div>
		</div>
	</div>
	
	<div class="alertMask undis" id ="alert3">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">安装问题上报成功</div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"  onclick="backlast()" href="javascript:;">我知道了</a>
			</div>
		</div>
	</div>
		
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript">
	
	
		function run_waitMe(text){
		    $('body').waitMe({
		        effect: 'win8',
		        text: text,
		        bg: 'rgba(255,255,255,0.7)',
		        color:'#000',
		        sizeW:'',
		        sizeH:'',
		        source: 'img.svg'
		    });
		}
	
		
	
		var constructBillId = $("#constructBillId").val();
		
		
		$(document).ready(function(){
			$("#start").bind('click',tijiao);
		});
		
		function sure(){
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();
		}
		function cancel(){
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();
		}
		//返回
		function backlast(){
				
			window.location.href="${ctx}/app/worker/install/installProblem/problem_record_list?constructBillId="+constructBillId;
				
		}
		
		//提交问题--页面数据校验
		function tijiao(){
			//问题分类
		    var problemTypeId = $("#problemTypeId").val();
			if(null==problemTypeId || problemTypeId=="" || undefined==problemTypeId){
				$("#alertRemarks").text("请选择问题分类");
				$("#alert").show();
				return;
			}
			//问题描述
			/* var problemDescribe = $("#problemDescribe").val();
			if(problemDescribe == "" || problemDescribe == null){
				$("#alertRemarks").text("问题描述必须填写，最多不能超过100个汉字");
				$("#alert").show();
				return false;
			} */
			
			//至少上传1张图片			
			var count = $("#num").val();
			if(null==count || ""==count ||count<1){
				$("#alertRemarks").text("请上传现场问题照片。");
				$("#alert").show();
				return false;
			}
			
			$("#alert2").show();
		}
		
		function sure2(){
			
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();
			
			$("#start").css("color","#CCC");
			$("#start").unbind("click");
			
			var businessType = "5";
			//校验5分钟内的重复提交
			 $.ajax({
		            url: "${ctx}/app/worker/install/installProblem/problem_report_ajax_time",
		            type: "post",
		            data: {
		            		constructBillId:constructBillId,
		            		businessType:businessType
		            	 },
		            success: function(data){
		            	if(null!=data && data == "0"){
		            		
		            		applySubmit();
		            	
		            	}else if(data == "1"){
		            		$("#alertRemarks").text("施工单id传送失败");
		            		$("#alert").show();
		            	}else if(data == "2"){
		            		$("#alertRemarks").text("问题上报类型传送失败");
		            		$("#alert").show();
		            	}else{
		            		$("#alertRemarks").text("您刚刚提交过安装问题，请耐心等待五分钟后再次操作");
		            		$("#alert").show();
		            	}
		            }
			 })
		            		
		}
		
		//提交
		function applySubmit(){
			
			//防止重复提交
			run_waitMe('正在提交数据,请稍等');
			$("#start").css("color","#CCC");
			$("#start").unbind("click");
			
			//上报提交表单
			var options ={
					url : "${ctx}/app/worker/install/installProblem/problem_submit",
					type: "post",
					success : function(data){
						$('body').waitMe('hide');
						if(null!=data && data=="0"){
							$("#alert3").show();
						}else if(data=="1"){
							$("#alertRemarks").text("施工单id为空");
		        			$("#alert").show();
						}else if(data=="2"){
							$("#alertRemarks").text("问题分类为空");
		        			$("#alert").show();
						}else if(data=="3"){
							$("#alertRemarks").text("请上传现场问题照片");
		        			$("#alert").show();
						}else if(data=="4"){
							$("#alertRemarks").text("安装工未登录");
		        			$("#alert").show();
						}else if(data=="5"){
							$("#alertRemarks").text("施工单相关信息为空");
		        			$("#alert").show();
						}else if(data=="6"){
							$("#alertRemarks").text("施工单不可进行问题上报");
		        			$("#alert").show();
						}else if(data=="7"){
							$("#alertRemarks").text("该问题分类内容不存在");
		        			$("#alert").show();
						}else if(data=="8"){
							$("#alertRemarks").text("上报问题保存失败");
		        			$("#alert").show();
						}else if(data=="9"){
							$("#alertRemarks").text("上报问题日志保存失败");
		        			$("#alert").show();
						}else if(data=="10"){
							$("#alertRemarks").text("上报问题图片保存失败");
		        			$("#alert").show();
						}else if(data=="11"){
							$("#alertRemarks").text("安装问题上报失败");
		        			$("#alert").show();
							
						}
					}
				}
			//ajax提交form
			$("#jvForm").ajaxSubmit(options);
			
		}
		
		
		//图片上传显示 
		function preview(file) {  
			var shit = $("#shit").val();
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><a class="delBtn" href="javascript:void(0)">删除</a></div>').insertAfter('#camera');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		} 
		$(document).on('click', '.delBtn', function(){
			
			var numReal = $(this).prev().attr("id");
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
			var shit = $("#shit").val();
			
			if(pic){
				num++;
				input.setAttribute("id","num"+shit);
				input.setAttribute('hidden', 'hidden'); 
				input.setAttribute('name', 'photo'); 
				input.setAttribute("type","text");
				input.setAttribute("value", pic);
				hiddenForm.appendChild(input);
				$("#num").val(num);
				shit++;
				$("#shit").val(shit);
			}
		}
		
		
	
	
	</script>
</body>
</html>