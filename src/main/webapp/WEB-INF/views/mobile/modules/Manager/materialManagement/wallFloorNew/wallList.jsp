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
	<title>申请墙地砖</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
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
			<a class="back_btn" href="${ctx}/app/manager/meterialManagementList"></a>
			<h2 class="title">申请墙地砖</h2>
		</header><!-- /header -->
		<section class="pt112" id="pt112">
			<!-- <div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
				<ul class="pad_top3 pad_left3 bor_dashed">
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl2em">顾客信息：</span>
						<p class="font30 col_3 pad_rgt3 flow">鹿港嘉苑-10-3-2001-张三丰鹿港嘉苑-10-3-2001-张三丰</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">合同开工日期：</span>
						<p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">实际开工日期：</span>
						<p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl2em">合同工期：</span>
						<p class="font30 col_3 pad_rgt3 flow">60天</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl2em">订单状态：</span>
						<p class="font30 col_blue pad_rgt3 flow">已分配项目经理</p>
					</li>
				</ul>
				<div class="btn_wrapper clearfix">
					<a class="btnBlueOne" href="javascript:;">墙地砖申请</a>
					<a class="btnBlueTwo" href="javascript:;">申请记录</a>
				</div>
			</div> -->
		
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	
	
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="messageContext"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage()">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
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
		
		run_waitMe('正在加载数据,请稍等');
		findOrderList();
		
		function findOrderList(){
			
			var sectionObj=$("#pt112");
	        var html ='';
	        
	        $.ajax({
	            url: "${ctx}/app/manager/materialManagement/wallAndFloorNew/apply_wallFloor_ajax_list",
	            type: "post",
	            success: function(data){
	            	
	            	if(null!=data && data.length>0){
		            	for(var v=0;v<data.length;v++){
		            		if(data[v].isScrap=='1'){
			            		html += '<div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">'+
		        				'<ul class="pad_top3 pad_left3 bor_dashed  abandon">'+
									'<li class="mb30 clearfix">'+
										'<span class="col_grey font30 flo_left pl2em">顾客信息：</span>'+
										'<p class="font30 col_3 pad_rgt3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'-'+data[v].customerName+'</p>'+
									'</li>'+
									'<li class="mb30 clearfix">'+
										'<span class="col_grey font30 flo_left">合同开工日期：</span>'+
										'<p class="font30 col_3 pad_rgt3 flow">'+data[v].contractStartDateString+'</p>'+
									'</li>'+
									'<li class="mb30 clearfix">'+
										'<span class="col_grey font30 flo_left">实际开工日期：</span>'+
										'<p class="font30 col_3 pad_rgt3 flow">'+data[v].actualStartDateString+'</p>'+
									'</li>'+
									'<li class="mb30 clearfix">'+
										'<span class="col_grey font30 flo_left pl2em">合同工期：</span>'+
										'<p class="font30 col_3 pad_rgt3 flow">'+data[v].contractTime+'天</p>'+
									'</li>'+
									'<li class="mb30 clearfix">'+
										'<span class="col_grey font30 flo_left pl2em">订单状态：</span>'+
										'<p class="font30 col_blue pad_rgt3 flow">'+data[v].orderStatusDescription+'</p>'+
									'</li>'+
								'</ul>'+
								'<div class="btn_wrapper clearfix">'+
									'<a class="btnBlueTwo" href="${ctx}/app/manager/wallAndFloorBrickRecord?id='+data[v].orderId+'">申请记录</a>'+
								'</div>'+
							'</div>';
		            		}else{
			            		html += '<div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">'+
		        				'<ul class="pad_top3 pad_left3 bor_dashed">'+
									'<li class="mb30 clearfix">'+
										'<span class="col_grey font30 flo_left pl2em">顾客信息：</span>'+
										'<p class="font30 col_3 pad_rgt3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'-'+data[v].customerName+'</p>'+
									'</li>'+
									'<li class="mb30 clearfix">'+
										'<span class="col_grey font30 flo_left">合同开工日期：</span>'+
										'<p class="font30 col_3 pad_rgt3 flow">'+data[v].contractStartDateString+'</p>'+
									'</li>'+
									'<li class="mb30 clearfix">'+
										'<span class="col_grey font30 flo_left">实际开工日期：</span>'+
										'<p class="font30 col_3 pad_rgt3 flow">'+data[v].actualStartDateString+'</p>'+
									'</li>'+
									'<li class="mb30 clearfix">'+
										'<span class="col_grey font30 flo_left pl2em">合同工期：</span>'+
										'<p class="font30 col_3 pad_rgt3 flow">'+data[v].contractTime+'天</p>'+
									'</li>'+
									'<li class="mb30 clearfix">'+
										'<span class="col_grey font30 flo_left pl2em">订单状态：</span>'+
										'<p class="font30 col_blue pad_rgt3 flow">'+data[v].orderStatusDescription+'</p>'+
									'</li>'+
								'</ul>'+
								'<div class="btn_wrapper clearfix">'+
									'<a class="btnBlueOne" onclick="applyWallAndFloor('+data[v].projectMode+','+data[v].orderStatusNumber+','+data[v].allCount+','+data[v].orderId+')">墙地砖申请</a>'+
									'<a class="btnBlueTwo" href="${ctx}/app/manager/wallAndFloorBrickRecord?id='+data[v].orderId+'">申请记录</a>'+
								'</div>'+
							'</div>';	
		            		}

		            		
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
		
		
		//申请墙地砖校验
		function applyWallAndFloor(projectMode,orderStatusNumber,allCount,orderId){
			
			//该订单是否开工
			if(projectMode==1 && orderStatusNumber<200 && allCount==0){
				$("#messageContext").text("请先到进度管理中点击确认开工后,才可以申请墙地砖");
        		$("#subReport").show();
        		return false;
			}
			
			$.ajax({
	            url: "${ctx}/app/manager/materialManagement/wallAndFloorNew/apply_wallFloor_viewAndTime_ajax",
	            type: "post",
	            data: {
	            		orderId:orderId
	            	 },
	            success: function(data){
	            	if(null!=data && data=="0"){
	            		window.location.href = "${ctx}/app/manager/materialManagement/wallAndFloorNew/wallFloorApply?orderId="+orderId;
	            	}else if(data=="1"){
	            		$("#messageContext").text("订单id传送失败");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#messageContext").text("同一个订单两次墙地砖申请操作时间必须间隔5分钟，请过5分钟后再申请");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#messageContext").text("您有未阅读的墙地砖采购单，请到【申请记录】去查阅后再申请墙地砖");
	            		$("#subReport").show();
	            	}
	            	
	            	
	            }
			})
		}
	
	</script>
</body>
</html>