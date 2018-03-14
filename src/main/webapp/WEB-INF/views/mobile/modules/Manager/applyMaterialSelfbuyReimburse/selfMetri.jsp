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
	<title>自采材料报销申请</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/mask.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reRuler.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/applyMaterialSelfbuyReimburse/list"></a>
			<h2 class="title">自采材料报销申请</h2>
		</header><!-- /header -->
		<form id="jvForm" action="" method="post" enctype="multipart/form-data">
			<input type="text" hidden="hidden" id="orderId" name="orderId" value="${orderId }">
			<section class="pad_top11">
				<ul class="item_lists pl0 bg_f font0">
					<p class="font30 col_3 pad_left3 pad_rgt3 pad_btm3 mar_btm26 bor_dotted">${materialManagement.communityName }-${materialManagement.buildNumber }-${materialManagement.buildUnit }-${materialManagement.buildRoom }-${materialManagement.customerName }</p>
					<li class="mar_btm42">
						<span class="col_6 font30 spanRgt">自采材料名称：</span>
						<select class="selectRe font24 col_9 border_d0" id="materialSelfbuyId" name="materialSelfbuyId" onchange="changeMaterialSelfbuyId()">
							<option value=""></option>
							<c:forEach items="${materialSelfbuyList}" var="materialSelfbuy">
								<option value="${materialSelfbuy.id }">${materialSelfbuy.materialName }</option>
							</c:forEach>
						</select>
					</li>
					<li class="mar_btm42 clearfix undis">
						<c:forEach items="${materialSelfbuyList}" var="materialSelfbuyTwo">
							<input type="text" hidden="hidden" id="materialSettleRate${materialSelfbuyTwo.id }" value="${materialSelfbuyTwo.settleRate }">
							<input type="text" hidden="hidden" id="materialSettleRateTwo${materialSelfbuyTwo.id }" value="${materialSelfbuyTwo.settleRateTwo }">
							<input type="text" hidden="hidden" id="materialSettleStage${materialSelfbuyTwo.id }" value="${materialSelfbuyTwo.settleStage }">
						</c:forEach>
					</li>
					<li class="mar_btm42 clearfix">
						<span class="col_6 font30">
							<span class="spanRgt">客户交费金额：</span>
							<input class="inputWrt" placeholder="请输入自采材料施工项，客户交费的金额" type="text" onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)" onchange="getSettleAmount()" name="customerPayAmount" id="customerPayAmount">
							<span class="col_9">元</span>
						</span>
					</li>
					<li class="mar_btm42 clearfix">
						<span class="col_6 font30">
							<span class="spanRgt">结算比例：</span>
							<input class="inputWrt bg_f2" readonly placeholder="" type="text" id="settleRate">
							<input type="text" hidden="hidden" name="settleRateTwo" id="settleRateTwo">
							<input type="text" hidden="hidden" name="settleStage" id="settleStage">
						</span>
					</li>
					<li class="mar_btm42 clearfix">
						<span class="col_6 font30">
							<span class="spanRgt">实际结算金额：</span>
							<input class="inputWrt bg_f2" readonly placeholder="" type="text" id="settleAmount" name="settleAmount">
							<span class="col_9">元</span>
						</span>
						<p class="font22 col_c90606 mlp30 pad_top2">实际结算金额 = 客户交费金额  *  结算比例</p>
					</li>
					<li class="clearfix">
						<span class="font28 col_6 flo_left spanRgt">备注说明：</span>
						<textarea class="instruc wid420" name="reimburseRemarks" maxlength="30"></textarea>
					</li>
				</ul>
				<div class="bg_f bor_btm">
					<div class="intro font0 pad_left3">
						<span class="font28 col_3">上传自采材料报销凭证</span>
					</div>
					<div class="pics font0 clearfix">
						<div class="pic camera" id="camera" href="javascript:void(0)">
							<img src="${ctxStatic}/mobile/modules/Manager/img/upPic.png" alt="">
							<input type="file" accept="image/*" onchange="preview(this)">
						</div>
						<input type="text" hidden="hidden" id="shit" value="1">
						<input type="text" hidden="hidden" id="num" value="">
						<%-- <div class="pic">
							<img src="${ctxStatic}/mobile/modules/Manager/images/eg.png" alt="上传图片">
							<a class="del_pic" href="javascript:void(0)"></a>
						</div>
						<div class="pic">
							<img src="${ctxStatic}/mobile/modules/Manager/images/canlender.png" alt="上传图片">
							<a class="del_pic" href="javascript:void(0)"></a>
						</div> --%>
						
					</div>
				</div>
			</section>
		</form>
		<footer class="footer">
			<a id="start" class="footer_btn" href="javascript:;">提交申请</a>
		</footer>
	</div>
	<div class="alertMask undis" id="alert">
		<div class="maskWrapper">
			<p class="col_3 maskTit">提示</p>
			<div class="font28 col_6 maskContent">您确定要提交吗？</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtn font33 col_fdfcfa" href="javascript:;" onclick="sure2()">确定</a>
				<a class="maskBtn font33 col_blue" href="javascript:;" onclick="cancel()">取消</a>
			</div>
		</div>
	</div>
	<div class="alertMask undis" id="alert2">
		<div class="maskWrapper">
			<p class="col_3 maskTit">提示</p>
			<div class="font28 col_6 maskContent" id="alertRemarks">请至少上传一张自采材料报销凭证。</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtnOne font33 col_fdfcfa" href="javascript:;" onclick="sure()">确定</a>
			</div>
		</div>
	</div>
	<div class="alertMask undis" id="alert3">
		<div class="maskWrapper">
			<p class="col_3 maskTit">提示</p>
			<div class="font28 col_6 maskContent">自采材料报销申请成功。</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtnOne font33 col_fdfcfa" href="javascript:;" onclick="backlast()">确定</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script>
		
		
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
		
		var orderId = $("#orderId").val();

		$(document).ready(function(){
			$("#start").bind('click',tijiao);
		});
		
		
		//提交问题--页面数据校验
		function tijiao(){
			//自采材料名称
		    var materialSelfbuyId = $("#materialSelfbuyId").val();
			if(null==materialSelfbuyId || materialSelfbuyId=="" || undefined==materialSelfbuyId){
				$("#alertRemarks").text("请选择自采材料名称。");
				$("#alert2").show();
				return;
			}
			//客户交费金额
			var customerPayAmount = $("#customerPayAmount").val();
			if(customerPayAmount == "" || customerPayAmount == null){
				$("#alertRemarks").text("请输入客户交费金额。");
				$("#alert2").show();
				return false;
			}
			//结算比例
			var settleRate = $("#settleRate").val();
			if(settleRate == "" || settleRate == null){
				$("#alertRemarks").text("结算比例不可为空。");
				$("#alert2").show();
				return false;
			}
			//实际结算金额
			var settleAmount = $("#settleAmount").val();
			if(settleAmount == "" || settleAmount == null){
				$("#alertRemarks").text("实际结算金额不可为空。");
				$("#alert2").show();
				return false;
			}
			
			var num = $("#num").val();
			if(num==null || num<1){
				$("#alertRemarks").text("请至少上传一张自采材料报销凭证。");
				$("#alert2").show();
				return false;
			}
			$("#alert").show();
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
			window.location.href = "${ctx}/app/manager/applyMaterialSelfbuyReimburse/materialSelfbuyReimburseRecord?orderId="+orderId;
		}
		
		//提交数据--校验5分钟内的重复提交问题
		function sure2(){
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();

			$("#start").css("color","#CCC");
			$("#start").unbind("click");
			
			var materialSelfbuyReimburseType = "1";
			var relatedReimburseId;
			
			$.ajax({
	            url: "${ctx}/app/manager/applyMaterialSelfbuyReimburse/material_selfbuy_reimburse_ajax_time",
	            type: "post",
	            data: {
	            		orderId:orderId,
	            		materialSelfbuyReimburseType:materialSelfbuyReimburseType,
	            		relatedReimburseId:relatedReimburseId
	            	 },
	            success: function(data){
	            	if(null!=data && data == "0"){
	            		
	            		applySubmit();
	            	
	            	}else if(data == "1"){
	            		$("#alertRemarks").text("订单id传送失败");
	            		$("#alert2").show();
	            	}else{
	            		$("#alertRemarks").text("您刚刚提交自采材料报销申请，请耐心等待五分钟后再次操作");
	            		$("#alert2").show();
	            	}
	            }
			 })
		}
		
		
		//表单提交
		function applySubmit(){
			
			//防止重复提交
			run_waitMe('正在提交数据,请稍等');
			$("#start").css("color","#CCC");
			$("#start").unbind("click");
			
			//上报提交表单
			var options ={
					url : "${ctx}/app/manager/applyMaterialSelfbuyReimburse/material_selfbuy_reimburse_submit",
					type: "post",
					success : function(data){
						$('body').waitMe('hide');
						if(null!=data && data=="0"){
							$("#alert3").show();
						}else if(data=="1"){
							$("#alertRemarks").text("订单id为空");
		        			$("#alert2").show();
						}else if(data=="2"){
							$("#alertRemarks").text("自采材料名称为空");
		        			$("#alert2").show();
						}else if(data=="3"){
							$("#alertRemarks").text("客户交费金额为空");
		        			$("#alert2").show();
						}else if(data=="4"){
							$("#alertRemarks").text("结算比例为空");
		        			$("#alert2").show();
						}else if(data=="5"){
							$("#alertRemarks").text("所属结算阶段为空");
		        			$("#alert2").show();
						}else if(data=="6"){
							$("#alertRemarks").text("实际结算金额为空");
		        			$("#alert2").show();
						}else if(data=="7"){
							$("#alertRemarks").text("自采材料报销凭证为空");
		        			$("#alert2").show();
						}else if(data=="8"){
							$("#alertRemarks").text("项目经理未登录");
							$("#alert2").show();
						}else if(data=="9"){
							$("#alertRemarks").text("自采材料报销保存失败");
							$("#alert2").show();
						}else if(data=="10"){
							$("#alertRemarks").text("自采材料报销状态日志保存失败");
							$("#alert2").show();
						}else if(data=="11"){
							$("#alertRemarks").text("自采材料报销图片保存失败");
							$("#alert2").show();
						}
					}
				}
			//ajax提交form
			$("#jvForm").ajaxSubmit(options);
			
		}
		
		
		//根据自采材料名称  动态加载  结算比例
		function changeMaterialSelfbuyId(){
			
			var materialSelfbuyId = $("#materialSelfbuyId").val();
			if(null!=materialSelfbuyId && materialSelfbuyId!=""){
				
				var settleRate = $("#materialSettleRate"+materialSelfbuyId).val();
				var settleRateTwo = $("#materialSettleRateTwo"+materialSelfbuyId).val();
				var settleStage = $("#materialSettleStage"+materialSelfbuyId).val();
				
				$("#settleRate").val(settleRate+"%");
				$("#settleRateTwo").val(settleRateTwo);
				$("#settleStage").val(settleStage);
				
				var customerPayAmount = $("#customerPayAmount").val();
				var settleRateTwo1 = $("#settleRateTwo").val();
				if(customerPayAmount>0 && settleRateTwo1>0){
					var settleAmount = parseFloat(customerPayAmount) * parseFloat(settleRateTwo1);
					$("#settleAmount").val(settleAmount.toFixed(2));
				}
				
			}else{
				$("#settleRate").val("");
				$("#settleRateTwo").val("");
				$("#settleStage").val("");
				$("#customerPayAmount").val("");
				$("#settleAmount").val("");
			}
		}
		
		//校验金额
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
		
		//实际结算金额
		function getSettleAmount(){
			var customerPayAmount = $("#customerPayAmount").val();
			var settleRateTwo = $("#settleRateTwo").val();
			if(customerPayAmount>0 && settleRateTwo>0){
				var settleAmount = parseFloat(customerPayAmount) * parseFloat(settleRateTwo);
				$("#settleAmount").val(settleAmount.toFixed(2));
			}
		}
		
		
		//图片上传显示 
		function preview(file) {  
			var shit = $("#shit").val();
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><a class="del_pic" href="javascript:void(0)"></a></div>').insertAfter('#camera');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		} 
		$(document).on('click', '.del_pic', function(){
			
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