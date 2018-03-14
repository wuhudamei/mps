<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <title>催促送货</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/project/installApply.css">
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
    <header>
        <a class="back_btn" href="javascript:;" onclick="backlast()"></a>
        <h2 class="title">催促送货</h2>
    </header>
    <section class="pad_top88">
   		<form id="searchForm">
    		<input type="text" hidden="hidden" id="orderId" name="orderId" value="${orderId }">
			<input type="text" hidden="hidden" id="purchaseId" name="purchaseId" value="${purchaseId }">

	        <div class="pad_top30 pad_btm50 pad_left30 pad_rgt30">
	            <span class="font28 col_7f7f7f flo_left spanRgt">内容：</span>
	            <textarea class="" id="operateContent" name="operateContent" maxlength="50"></textarea>
	        </div>
	        <!-- <div class="pad_left3 pad_top46 p_camera bg_f">
	            <span class="font28 col_3">请上传现场照片：</span>
	            <span class="bd_camera"><input type="file"></span>
	        </div> -->
	        <%-- <div class="pics clearfix">
	            <div class="pic">
	                <img src="${ctxStatic}/mobile/modules/Manager/img/project/eg.png" alt="">
	                <a class="delBtn" href="javascript:void(0)"></a>
	            </div>
	            <div class="pic">
	                <img src="${ctxStatic}/mobile/modules/Manager/img/project/eg.png" alt="">
	                <a class="delBtn" href="javascript:void(0)"></a>
	            </div>
	            <div class="pic">
	                <img src="${ctxStatic}/mobile/modules/Manager/img/project/eg.png" alt="">
	                <a class="delBtn" href="javascript:void(0)"></a>
	            </div>
	            <div class="pic">
	                <img src="${ctxStatic}/mobile/modules/Manager/img/project/eg.png" alt="">
	                <a class="delBtn" href="javascript:void(0)"></a>
	            </div>
	        </div> --%>
		</form>
        <a class="subBtn" href="javascript:;">提交催促安装</a>
        <div style="padding-bottom:300px;"></div>
        
        
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
				<div class="font28 col_6 maskContent">催促送货成功</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="backlast()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
		
    </section>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/mobiscroll.custom-2.16.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
</body>
<script>


	var orderId = '${orderId}';
	var purchaseId = '${purchaseId}';
	
	//返回
	function backlast(){
			
		window.location.href="${ctx}/app/manager/urgeWallAndFloorList?orderId="+orderId+"&purchaseId="+purchaseId;
	}
	
	
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
	
	$(document).ready(function() {
		//绑定onclick事件
		$(".subBtn").bind('click',cartSubmit);
	});
	
	function cartSubmit() {
		
		var info = $("#operateContent").val();
		if(info == null || info == ""){
			$("#alertRemarks").text("请输入催促送货内容");
			$("#alert").show();
		}else{
			$("#alert2").show();
		}
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
	
	function sure2(){
		$("#alert").hide();
		$("#alert2").hide();
		$("#alert3").hide();
		
		$.ajax({
			url: "${ctx}/app/manager/wallAndFloor_push_installation_ajax",
            type: "post",
            data:{purchaseId:purchaseId},
            success: function(data) {
            	if(null!=data && data=="1"){
            		$("#alertRemarks").text("一天只能催促1次，请您明天再来催促。");
        			$("#alert").show();
            	}else{
            		applySubmit();
            	}
            }
    	})
	}
	
	function applySubmit(){
		
		run_waitMe('正在提交数据,请稍等');
		$(".subBtn").css("color","#CCC");
		$(".subBtn").unbind("click");
		
		var options ={
				url: "${ctx}/app/manager/save_wallAndFloor_push_installation_ajax",
				type: "post",
				success:function(data){
					$('body').waitMe('hide');
					if(null!=data && data=="0"){
	        			$("#alert3").show();
					}else if(data=="1"){
						$("#alertRemarks").text("催促送货采购单ID为空");
	        			$("#alert").show();
					}else if(data=="2"){
						$("#alertRemarks").text("项目经理未登录");
	        			$("#alert").show();
					}else if(data=="3"){
						$("#alertRemarks").text("您刚刚提交过催促送货，请耐心等待五分钟后再次操作");
	        			$("#alert").show();
					}else if(data=="4"){
						$("#alertRemarks").text("催促送货内容为空");
	        			$("#alert").show();
					}else if(data=="5"){
						$("#alertRemarks").text("催促送货保存失败");
	        			$("#alert").show();
					}else if(data=="6"){
						$("#alertRemarks").text("催促送货图片保存失败");
	        			$("#alert").show();
					}
				}
		}
		$("#searchForm").ajaxSubmit(options);
		
	}
	
</script>
</html>