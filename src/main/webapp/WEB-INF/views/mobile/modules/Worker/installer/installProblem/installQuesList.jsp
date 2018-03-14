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
	<title>安装问题上报</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/applyDoneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/waitMe.css" >
	<style>
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
		.maskBtn{display: block;width: 60%;text-align: center;line-height: .76rem;}
		.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
    		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
	</style>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/worker/install/installIndex/toindex"></a>
			<h2 class="title">安装问题上报</h2>
		</header><!-- /header -->
		<section class="pad_top88">
			<div class="font0 search-area">
				<input class="search-box" type="text" placeholder=" 任务包名称、项目经理">
				<a class="search-btn" href="javascript:;" onclick="findInstallConstructBillProblemList()"></a>
			</div>
			<div id="boxA">
				<%-- <div class="sec pl30 pr30 font0 border_top border_btm mb24 bg_f clearfix">
					<p class="pt22 pb22"><span class="font30 col_0780ec pl25 blueBar">安装项名称：铝扣板安装</span></p>
					<ul class="pad_top3 bor_top_e9e8e8 bor_dashed">
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">施工单编号：</span>
							<p class="font28 col_3 pad_rgt3 flow">SG201707100001</p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">小区名称：</span>
							<p class="font28 col_3 pad_rgt3 flow">合顺家园-1-2-203合顺家园-1-2-203合顺家园-1-2-203合顺家园-1-2-203</p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">项目经理：</span>
							<p class="font28 col_3 pad_rgt3 flow">张丰丰</p>
						</li>
						<li class="mb30 rele clearfix">
							<span class="col_grey font28 flo_left">项目经理手机号：</span>
							<p class="font28 col_3 pad_rgt3 flow">13020201021</p>
							<a class="teleBtn" href="javascript:;"><i class="teleIcon"><img src="${ctxStatic}/mobile/modules/Worker/img/tele.png" alt=""></i>拨打电话</a>
						</li>
					</ul>
					<ul class="pad_top3 bor_dashed">
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">施工地址：</span>
							<p class="font28 col_3 pad_rgt3 flow">海淀区金沟河路12号院7号楼21号号院7号</p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">确认开始日期：</span>
							<p class="font28 col_3 pad_rgt3 flow">2017-06-29</p>
						</li>
						<li class="mb30 rele clearfix">
							<span class="col_grey font28 flo_left">确认结束日期：</span>
							<p class="font28 col_3 pad_rgt3 flow">2017-07-10</p>
						</li>
						<li class="mb30 clearfix">
							<span class="col_grey font28 flo_left">施工单状态：</span>
							<p class="font28 col_3 pad_rgt3 flow">施工中</p>
						</li>
					</ul>
					<div class="btn_wrapper clearfix">
						<a class="btnBlueOne" href="javascript:;">上报问题</a>
						<a class="btnBlueTwo" href="javascript:;">上报问题记录</a>
					</div>
				</div> --%>
			</div>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	
	
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="messageContext"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/waitMe.js"></script>
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
	
		findInstallConstructBillProblemList();
		
		//查询施工单列表(状态为10-已确定工人组/20-施工中/30-工人已申请完工）
		function findInstallConstructBillProblemList(){
			
			run_waitMe('正在加载数据,请稍等');
			
			
			var sectionObj = $("#boxA");
			
			var text =$(".search-box").val();
			
			var html = '';
			
			$.ajax({
				url: "${ctx}/app/worker/install/installProblem/install_construct_bill_problem_ajax_list",
	            type: "post",
	            data:{text:text},
	            success: function(data) {
	            	
	            	if(null!=data && data.length>0){
		            	for(var v=0;v<data.length;v++){
		            		
		            		html += '<div class="sec pl30 pr30 font0 border_top border_btm mb24 bg_f clearfix">'+
			            				'<p class="pt22 pb22"><span class="font30 col_0780ec pl25 blueBar">安装项名称：'+data[v].installItemName+'</span></p>'+
					        				'<ul class="pad_top3 bor_top_e9e8e8 bor_dashed">'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">施工单编号：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].constructBillCode+'</p>'+
												'</li>'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">小区名称：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'</p>'+
												'</li>'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">项目经理：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].itemManagerName+'</p>'+
												'</li>'+
												'<li class="mb30 rele clearfix">'+
													'<span class="col_grey font28 flo_left">项目经理手机号：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].itemManagerPhone+'</p>'+
													'<a class="teleBtn" href="tel:'+data[v].itemManagerPhone+'"><i class="teleIcon"><img src="${ctxStatic}/mobile/modules/Worker/img/tele.png" alt=""></i>拨打电话</a>'+
												'</li>'+
											'</ul>'+
											'<ul class="pad_top3 bor_dashed">'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">施工地址：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].detailAddress+'</p>'+
												'</li>'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">确认开始日期：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].supplierConfirmIntoDateString+'</p>'+
												'</li>'+
												'<li class="mb30 rele clearfix">'+
													'<span class="col_grey font28 flo_left">确认结束日期：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].supplierConfirmCompleteDateString+'</p>'+
												'</li>'+
												'<li class="mb30 clearfix">'+
													'<span class="col_grey font28 flo_left">施工单状态：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].constructBillStatusName+'</p>'+
												'</li>'+
											'</ul>'+
											'<div class="btn_wrapper clearfix">'+
												'<a class="btnBlueOne" href="javascript:;" onclick="problem('+data[v].constructBillId+')">上报问题</a>'+
												'<a class="btnBlueTwo" href="${ctx}/app/worker/install/installProblem/problem_record_list?constructBillId='+data[v].constructBillId+'">上报问题记录</a>'+
											'</div>'+
										'</div>';
		            	}
	            	}
	            	
	            	$(sectionObj).html(html);
	    	        $('body').waitMe('hide');
	    	        
	            }
			})
		}
		
		function sendMessage(){
			$("#subReport").hide();
		}
		
		//问题上报
		function problem(constructBillId){
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
		            		
		            		window.location.href = "${ctx}/app/worker/install/installProblem/problem_list?constructBillId="+constructBillId;
		            	
		            	}else if(data == "1"){
		            		$("#messageContext").text("施工单id传送失败");
		            		$("#subReport").show();
		            	}else if(data == "2"){
		            		$("#messageContext").text("问题上报类型传送失败");
		            		$("#subReport").show();
		            	}else{
		            		$("#messageContext").text("您刚刚提交过安装问题，请耐心等待五分钟后再次操作");
		            		$("#subReport").show();
		            	}
		            }
			 })
		}
		
	
	</script>
</body>
</html>