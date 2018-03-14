<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>安装申请回复</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/apply.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/mask.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="wrap">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/newEnginInstallController/progress_log?id=${id}"></a>
			<h2 class="title">安装申请回复</h2>
		</header>
		<!-- /header -->
		<form id="searchForm">
			<input type="text" hidden="hidden" id="id" name="id" value="${id }">
			
			<section class="pad_top88">
				<div class="sec pl30 pr30 font0 clearfix">
					<ul class="pad_top3">
						<li class="mb30 rele clearfix">
							<span class="col_grey font30">内容：</span>
							<textarea class="applyCont" id="operateContent" name="operateContent" maxlength="50"></textarea>
						</li>
					</ul>
				</div>
			</section>
		</form>
		<!-- /section -->
		<div class="pt50">
			<a class="subBtn" href="javascript:;">提交回复内容</a>
		</div>
		<div style="padding-bottom:300px;"></div>
	</div>
	<!-- /.wrap -->
	
	
	
    <!--公共弹层背景  class 「_in」 显示弹层背景-->
    <div class="alertMask" id="alertMask">
    </div>
    <!-- /.alertMask -->
    <!--公共弹层  class 「_in」 显示弹层内容-->
    <div class="maskWrapper" id="alertAlert">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p id="alertRemark">
            </p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="sure()">确 定</button>
        </div>
    </div>
    <!-- /.maskWrapper -->
    <div class="maskWrapper" id="alertAlertTwo">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p>您确定要提交吗？
            </p>
        </div>
        <div class="maskBtns twoBtns clearfix">
            <button class="maskBtn" type="button" onclick="sure2()">确定</button>
            <button class="maskBtn" type="button" onclick="cancel()">取消</button>
        </div>
    </div>
    <!-- /.maskWrapper -->
    <div class="maskWrapper" id="alertAlertThree">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p>安装申请回复成功
            </p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="backlast()">确 定</button>
        </div>
    </div>
    
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script>
		/* $(document).on('click', '.delBtn', function(){
			$(this).parent().remove();
		}); */
		
		
		var id = '${id}';
		
		
		//返回
		function backlast(){
				
			window.location.href="${ctx}/app/manager/newEnginInstallController/progress_log?id="+id;
				
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
				
				$("#alertRemark").text("请输入安装申请回复内容");
				$("#alertMask").addClass('_in');
		        $("#alertAlert").addClass('_in');
			}else{
				$("#alertMask").addClass('_in');
		        $("#alertAlertTwo").addClass('_in');
			}
		}
	    
		function sure(){
			$("#alertMask").removeClass('_in');
	        $("#alertAlert").removeClass('_in');
		}
		function cancel(){
			$("#alertMask").removeClass('_in');
	        $("#alertAlertTwo").removeClass('_in');
		}
		
		function sure2(){
			
			$("#alertMask").removeClass('_in');
	        $("#alertAlertTwo").removeClass('_in');
			
			run_waitMe('正在提交数据,请稍等');
			$(".subBtn").css("color","#CCC");
			$(".subBtn").unbind("click");
			
			var options ={
					url: "${ctx}/app/manager/newEnginInstallController/save_install_reply_ajax",
					type: "post",
					success:function(data){
						$('body').waitMe('hide');
						if(null!=data && data=="0"){
		        			$("#alertMask").addClass('_in');
		    		        $("#alertAlertThree").addClass('_in');
						}else if(data=="1"){
							$("#alertRemark").text("回复安装项ID为空");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data=="2"){
							$("#alertRemark").text("项目经理未登录");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data=="3"){
							$("#alertRemark").text("您刚刚提交过安装申请回复，请耐心等待五分钟后再次操作");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data=="4"){
							$("#alertRemark").text("安装申请回复内容为空");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data=="5"){
							$("#alertRemark").text("安装申请回复保存失败");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else{
							$("#alertRemark").text("安装申请回复图片保存失败");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}
					}
			}
			$("#searchForm").ajaxSubmit(options);
	    	
		}
		
		
		
		
		
		
	</script>
</body>
</html>