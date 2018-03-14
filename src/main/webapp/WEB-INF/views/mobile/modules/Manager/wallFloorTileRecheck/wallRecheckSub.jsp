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
	<title>墙地砖实测面积复核</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/Details.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/apply.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/wallSub.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
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
			<a class="back_btn"  onclick="backBtn()"></a>
			<h2 class="title">墙地砖实测面积复核</h2>
		</header>
		<section class="pad_top88">
			
			<form id="jvForm" class="jvForm"  method="post" enctype="multipart/form-data">
			
				<input type="text" hidden="hidden" id="backBtnFlag" name="backBtnFlag" value="1" />
				
				<input type="text" hidden="hidden" id="flag" name="flag" value="${wallFloorTileRecheck.flag }" />
				<input type="text" hidden="hidden" id="orderId" name="orderId" value="${wallFloorTileRecheck.orderId }" />
				<input type="text" hidden="hidden" id="wallFloorTileRecheckId" name="wallFloorTileRecheckId" value="${wallFloorTileRecheck.wallFloorTileRecheckId }" />
				
				<p class="pt20 pb20 align_center">
					<span class="font30 col_blue">${wallFloorTileRecheck.communityName }-${wallFloorTileRecheck.buildNumber }-${wallFloorTileRecheck.buildUnit }-${wallFloorTileRecheck.buildRoom }-${wallFloorTileRecheck.customerName }</span>
				</p>
				<div class="sec pl30 pr30 pb30 font0 bor_top_e5 bg_f shadow clearfix">
					<ul class="pt30">
						<li class="mb30 clearfix">
							<span class="col_grey font30 fl">预算面积：</span>
							<p class="font30 col_3 flow">${wallFloorTileRecheck.squareBudget } ㎡<span class="font26 col_e90f0f fr">图纸面积*108%</span></p>
						</li> 
						<li class="mb30 clearfix">
							<span class="col_grey font30 fl">定额面积：</span>
							<p class="font30 col_3 flow">${wallFloorTileRecheck.squareQuota } ㎡<span class="font26 col_e90f0f fr">预算面积+1㎡损耗</span></p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font30 fl">实际申请：</span>
							<p class="font30 col_3">${wallFloorTileRecheck.squarePurchase } ㎡<a class="detailsBtn font26 col_blue fr" href="javascript:;">查看明细</a></p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font30 fl">计划测量：</span>
							<p class="font30 col_3 flow"><fmt:formatDate value="${wallFloorTileRecheck.planMeasureDate }" pattern="yyyy-MM-dd"/></p>
						</li>
	
	
						<li class="mb30 clearfix" id="a1">
							<span class="col_grey font30">现场实测：</span>
							<input class="real" type="text" style="width:3rem" value="${wallFloorTileRecheck.squareMeasure }" name="squareMeasure" id="squareMeasure" onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)" onchange="changeSquareMeasure()">
							<span class="col_3 font26">m<sup>2</sup> <span class="col_e90f0f">含损耗面积</span></span>
						</li>
						<li class="rele clearfix" id="a2">
							<span class="col_grey font30">实测说明：</span>
							<textarea class="instruc" placeholder="" id="measureRemarks" name="measureRemarks" onchange="changeMeasureRemarks()">${wallFloorTileRecheck.measureRemarks }</textarea>
						</li>
						
						<li class="mb30 clearfix undis" id="b1">
							<span class="col_grey font30 fl">实测面积：</span>
							<p class="font30 col_3 flow"><span id="squareMeasure2">${wallFloorTileRecheck.squareMeasure }</span>
								<span class="col_3 font36">m<sup>2</sup></span>
							</p>
						</li>
						<li class="mb30 clearfix undis" id="b2">
							<span class="col_grey font30 fl">实测说明：</span>
							<p class="font30 col_3 flow" id="measureRemarks2">${wallFloorTileRecheck.measureRemarks }</p>
						</li>
						
					</ul>
				</div>
				<section class="pt24 mb30 shadow">
					<p class="pb20">
						<span class="pl30 font30 col_blue bold">实测照片</span>
					</p>
					<div class="pics bor_top_ddd font0 shadow bg_f clearfix">
						
						<%-- <div class="pic">
							<img src="${ctxStatic}/mobile/modules/Manager/img/common/eg.png" alt="">
							<a class="delBtn" href="javascript:void(0)"></a>
						</div> --%>
						
						
						<div class="pic camera" id="camera" href="javascript:void(0)">
							<img src="${ctxStatic}/mobile/modules/Manager/img/common/upPic.png" alt="">
							<input type="file" accept="image/*" onchange="preview(this)">
						</div>
						<input type="text" hidden="hidden" id="shit" value="1">
						<input type="text" hidden="hidden" id="num" value="">
						
					</div>
				</section>
			</form>
		</section>
		
		<div class="pt44" id="a3">
			<a class="subBtn" onclick="theNext()">下一步</a>
		</div>
		<div class="pt44 undis" id="b3">
			<a class="subBtn" id="start">确定提交</a>
		</div>
		
		
		<div style="padding-bottom:200px;"></div>
		
		<div id="detailsMask" class="mask undis">
			<ul class="ulWrapper">
				<li class="ulList clearfix">
					<span class="col_6 font30">墙砖：</span>
					<span class="font30 col_3 flow">${wallSquareCount } m<sup>2</sup></span>
				</li>
				<li class="ulList clearfix">
					<span class="col_6 font30">地　砖：</span>
					<span class="font30 col_3 flow">${floorSquareCount } m<sup>2</sup></span>
				</li>
				<p class="font34 col_blue pt26 pb38">合计：${wallFloorSquareCount } ㎡</p>
				<a class="ulBtn" >确定</a>
			</ul>
		</div>
	</div>
	
	
	
	<div class="alertMask undis" id ="alert">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent" id="alertRemarks"></div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"  onclick="sure()" >我知道了</a>
			</div>
		</div>
	</div>
	
	
	<div class="alertMask undis" id ="alert2">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">您确认要提交吗？</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtn font33 col_fdfcfa" onclick="sure2()">
					确定
				</a>
				<a class="maskBtn font33 col_0780ec" onclick="cancel()">取消</a>
			</div>
		</div>
	</div>
	
	<div class="alertMask undis" id ="alert3">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">墙地砖实测面积复核成功</div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"  onclick="backlast()">我知道了</a>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script>
		$(document).on('click','.detailsBtn',function(){
        	$('#detailsMask').removeClass('undis');
        });
        $(document).on('click','.ulBtn',function(){
        	$('#detailsMask').addClass('undis');
        });
        
        
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
        
        
      //图片上传显示 
		function preview(file) {  
			var shit = $("#shit").val();
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><a class="delBtn" href="javascript:void(0)">删除</a></div>').insertBefore('#camera');
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
		
		//校验数量
		function fix_amountthis(obj){
	    	
	        //先把非数字的都替换掉，除了数字和. 
		    obj.value = obj.value.replace(/[^\d.]/g,""); 
		    //必须保证第一个为数字而不是. 
		    obj.value = obj.value.replace(/^\./g,""); 
		    //保证只有出现一个.而没有多个. 
		    obj.value = obj.value.replace(/\.{2,}/g,"."); 
		    //保证.只出现一次，而不能出现两次以上 
		    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		    //只能输入两个小数 
		    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
		    //只能输入6个整数
		    var reg = /.*\..*/;
		    if(reg.test(obj.value)){
		    	var aa = obj.value.substring(0,obj.value.indexOf("."));
		    	if(aa.length>6){
		    		obj.value = obj.value.substring(0,6); 
		    	}
		    }else{
		    	if(obj.value.length>6){
		    		obj.value = obj.value.substring(0,6);
		    	}
		    }
	    }
        
		
		$(document).ready(function(){
			$("#start").bind('click',tijiao);
		});
		
		//页面的返回键
		function backBtn(){
			var backBtnFlag = $("#backBtnFlag").val();
			if(backBtnFlag=="1"){
				window.location.href = "${ctx}/app/manager/wallFloorTileRecheck/list";
			}else if(backBtnFlag=="2"){
				$("#backBtnFlag").val("1");
				$(".delBtn").show();
				$("#camera").show();
				$("#a1").show();
				$("#a2").show();
				$("#a3").show();
				$("#b1").hide();
				$("#b2").hide();
				$("#b3").hide();
				
			}
		}
		//下一步
		function theNext(){
			var backBtnFlag = $("#backBtnFlag").val();
			if(backBtnFlag=="1"){
				//实测面积
				var squareMeasure = $("#squareMeasure").val();
				if(squareMeasure=="" || squareMeasure<=0){
					$("#alertRemarks").text("请输入实测面积");
					$("#alert").show();
					return false;
				}
				//至少上传1张图片			
				var num = $("#num").val();
				if(null==num || ""==num ||num<1){
					$("#alertRemarks").text("请至少上传一张实测照片");
					$("#alert").show();
					return false;
				}
				$("#backBtnFlag").val("2");
				$(".delBtn").hide();
				$("#camera").hide();
				$("#a1").hide();
				$("#a2").hide();
				$("#a3").hide();
				$("#b1").show();
				$("#b2").show();
				$("#b3").show();
			}
		}
		//实测面积改变
		function changeSquareMeasure(){
			var squareMeasure = $("#squareMeasure").val();
			$("#squareMeasure2").text(squareMeasure);
		}
		//实测说明改变
		function changeMeasureRemarks(){
			var measureRemarks = $("#measureRemarks").val();
			$("#measureRemarks2").text(measureRemarks);
		}
		
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
				
			window.location.href = "${ctx}/app/manager/wallFloorTileRecheck/list";
				
		}
		
		//提交问题--页面数据校验
		function tijiao(){
			
			//实测面积
			var squareMeasure = $("#squareMeasure").val();
			if(squareMeasure=="" || squareMeasure<=0){
				$("#alertRemarks").text("请输入实测面积");
				$("#alert").show();
				return false;
			}
			//至少上传1张图片			
			var num = $("#num").val();
			if(null==num || ""==num ||num<1){
				$("#alertRemarks").text("请至少上传一张实测照片");
				$("#alert").show();
				return false;
			}
			
			$("#alert2").show();
		}
		
		//提交数据
		function sure2(){
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();	
			
			//防止重复提交
			run_waitMe('正在提交数据,请稍等');
			$("#start").css("color","#CCC");
			$("#start").unbind("click");
			
			//上报提交表单
			var options ={
					url : "${ctx}/app/manager/wallFloorTileRecheck/wallFloor_tile_recheck_submit_ajax",
					type: "post",
					success : function(data){
						$('body').waitMe('hide');
						if(null!=data && data=="0"){
							$("#alert3").show();
						}else if(data=="1"){
							$("#alertRemarks").text("墙地砖复尺单id为空");
		        			$("#alert").show();
						}else if(data=="2"){
							$("#alertRemarks").text("实测面积为空");
		        			$("#alert").show();
						}else if(data=="3"){
							$("#alertRemarks").text("实测照片为空");
		        			$("#alert").show();
						}else if(data=="4"){
							$("#alertRemarks").text("项目经理未登录");
		        			$("#alert").show();
						}else if(data=="5"){
							$("#alertRemarks").text("该复尺单项目经理已经确认过了");
		        			$("#alert").show();
						}else if(data=="6"){
							$("#alertRemarks").text("墙地砖复尺单更新失败");
		        			$("#alert").show();
						}else if(data=="7"){
							$("#alertRemarks").text("日志保存失败");
		        			$("#alert").show();
						}else if(data=="8"){
							$("#alertRemarks").text("订单相关信息更新失败");
							$("#alert").show();
						}else if(data=="9"){
							$("#alertRemarks").text("图片保存失败");
							$("#alert").show();
						}
					}
				}
			//ajax提交form
			$("#jvForm").ajaxSubmit(options);
			
		}
	</script>
</body>
</html>