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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/meterialManagementList"></a>
			<h2 class="title">墙地砖实测面积复核</h2>
		</header><!-- /header -->
		<section class="pad_top88" id="pt112">
			
			<!-- <div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
				<ul class="pad_top3 pad_left3 bor_dashed">
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">顾客信息：</span>
						<p class="font30 col_3 pad_rgt3 flow">鹿港嘉苑-10-3-2001-张三丰鹿港嘉苑-10-3-2001-张三丰</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">实际开工：</span>
						<p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">现场实测：</span>
						<p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">订单状态：</span>
						<p class="font30 col_3 pad_rgt3 flow">施工中</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">实测状态：</span>
						<p class="font30 col_3 pad_rgt3 flow">同意复尺</p>
					</li>
				</ul>
				<div class="btn_wrapper clearfix">
					<a class="btnBlueBg" href="javascript:;">现场测量</a>
				</div>
			</div>
			<div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
				<ul class="pad_top3 pad_left3 bor_dashed">
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">顾客信息：</span>
						<p class="font30 col_3 pad_rgt3 flow">鹿港嘉苑-10-3-2001-张三丰鹿港嘉苑-10-3-2001-张三丰</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">实际开工：</span>
						<p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">现场实测：</span>
						<p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">订单状态：</span>
						<p class="font30 col_3 pad_rgt3 flow">施工中</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">实测状态：</span>
						<p class="font30 col_3 pad_rgt3 flow">待设计已确认</p>
					</li>
				</ul>
				<div class="btn_wrapper clearfix">
					<a class="btnBlueBg" href="javascript:;">查看详情</a>
				</div>
			</div>
			<div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
				<ul class="pad_top3 pad_left3 bor_dashed">
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">顾客信息：</span>
						<p class="font30 col_3 pad_rgt3 flow">鹿港嘉苑-10-3-2001-张三丰鹿港嘉苑-10-3-2001-张三丰</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">实际开工：</span>
						<p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">现场实测：</span>
						<p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">订单状态：</span>
						<p class="font30 col_3 pad_rgt3 flow">施工中</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">实测状态：</span>
						<p class="font30 col_3 pad_rgt3 flow">待设计已确认</p>
					</li>
				</ul>
				<div class="btn_wrapper clearfix">
					<a class="btnBlueOne" href="javascript:;">申请记录</a>
					<a class="btnBlueTwo" href="javascript:;">查看结果</a>
				</div>
			</div> -->
		</section>
		<div style="padding-bottom:300px;"></div>
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
		
		findWallFloorTileRecheckList();
		
		function findWallFloorTileRecheckList(){
			
			var sectionObj=$("#pt112");
	        var html ='';
			
	        $.ajax({
	            url: "${ctx}/app/manager/wallFloorTileRecheck/wallFloor_tile_recheck_ajax_list",
	            type: "post",
	            success: function(data){
	            	
	            	if(null!=data && data.length>0){
		            	for(var v=0;v<data.length;v++){
		            		
		            		if(data[v].isScrap=='1'){
					            		html += '<div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">'+
				        				'<ul class="pad_top3 pad_left3 bor_dashed  abandon">'+
											'<li class="mb30 clearfix">'+
												'<span class="col_grey font30 flo_left">顾客信息：</span>'+
												'<p class="font30 col_3 pad_rgt3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'-'+data[v].customerName+'</p>'+
											'</li>'+
											'<li class="mb30 clearfix">'+
												'<span class="col_grey font30 flo_left">实际开工：</span>'+
												'<p class="font30 col_3 pad_rgt3 flow">'+data[v].actualStartDateString+'</p>'+
											'</li>'+
											'<li class="mb30 clearfix">'+
												'<span class="col_grey font30 flo_left">现场实测：</span>'+
												'<p class="font30 col_3 pad_rgt3 flow">'+data[v].realMeasureDateString+'</p>'+
											'</li>'+
											'<li class="mb30 clearfix">'+
												'<span class="col_grey font30 flo_left">订单状态：</span>'+
												'<p class="font30 col_3 pad_rgt3 flow">'+data[v].orderStatusDescription+'</p>'+
											'</li>'+
											'<li class="mb30 clearfix">'+
												'<span class="col_grey font30 flo_left">实测状态：</span>'+
												'<p class="font30 col_3 pad_rgt3 flow">'+data[v].statusDescribe+'</p>'+
											'</li>'+
										'</ul>'+
										'<div class="btn_wrapper clearfix">';
								
									if(data[v].status == "40"){
										//同意复尺
				// 						html +=	'<a class="btnBlueBg" href="${ctx}/app/manager/wallFloorTileRecheck/wallFloorTileRecheckApply?wallFloorTileRecheckId='+data[v].wallFloorTileRecheckId+'">现场测量</a>';
									}else if(data[v].status == "70"){
										//已分责
										html +=	'<a class="btnBlueOne" href="${ctx}/app/manager/wallFloorTileRecheck/wallRecheckDetails?wallFloorTileRecheckId='+data[v].wallFloorTileRecheckId+'">申请记录</a>'+
												'<a class="btnBlueTwo" href="${ctx}/app/manager/wallFloorTileRecheck/wallRecheckResult?wallFloorTileRecheckId='+data[v].wallFloorTileRecheckId+'">查看结果</a>';
									}else{
										//待确认、已确定、项目经理已确认、设计是已确认
										html +=	'<a class="btnBlueBg" href="${ctx}/app/manager/wallFloorTileRecheck/wallRecheckDetails?wallFloorTileRecheckId='+data[v].wallFloorTileRecheckId+'">申请记录</a>';
									}			
									
									html +=	'</div></div>';
		            		}else{
		            			
							            		html += '<div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">'+
						        				'<ul class="pad_top3 pad_left3 bor_dashed">'+
													'<li class="mb30 clearfix">'+
														'<span class="col_grey font30 flo_left">顾客信息：</span>'+
														'<p class="font30 col_3 pad_rgt3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'-'+data[v].customerName+'</p>'+
													'</li>'+
													'<li class="mb30 clearfix">'+
														'<span class="col_grey font30 flo_left">实际开工：</span>'+
														'<p class="font30 col_3 pad_rgt3 flow">'+data[v].actualStartDateString+'</p>'+
													'</li>'+
													'<li class="mb30 clearfix">'+
														'<span class="col_grey font30 flo_left">现场实测：</span>'+
														'<p class="font30 col_3 pad_rgt3 flow">'+data[v].realMeasureDateString+'</p>'+
													'</li>'+
													'<li class="mb30 clearfix">'+
														'<span class="col_grey font30 flo_left">订单状态：</span>'+
														'<p class="font30 col_3 pad_rgt3 flow">'+data[v].orderStatusDescription+'</p>'+
													'</li>'+
													'<li class="mb30 clearfix">'+
														'<span class="col_grey font30 flo_left">实测状态：</span>'+
														'<p class="font30 col_3 pad_rgt3 flow">'+data[v].statusDescribe+'</p>'+
													'</li>'+
												'</ul>'+
												'<div class="btn_wrapper clearfix">';
												
												if(data[v].status == "40"){
													//同意复尺
													html +=	'<a class="btnBlueBg" href="${ctx}/app/manager/wallFloorTileRecheck/wallFloorTileRecheckApply?wallFloorTileRecheckId='+data[v].wallFloorTileRecheckId+'&orderId='+data[v].orderId+'">现场测量</a>';
												}else if(data[v].status == "70"){
													//已分责
													html +=	'<a class="btnBlueOne" href="${ctx}/app/manager/wallFloorTileRecheck/wallRecheckDetails?wallFloorTileRecheckId='+data[v].wallFloorTileRecheckId+'">申请记录</a>'+
															'<a class="btnBlueTwo" href="${ctx}/app/manager/wallFloorTileRecheck/wallRecheckResult?wallFloorTileRecheckId='+data[v].wallFloorTileRecheckId+'">查看结果</a>';
												}else if (data[v].status == "30"){
													//同意复尺
													html +=	'<a class="btnBlueBg" href="${ctx}/app/manager/wallFloorTileRecheck/wallFloorTileRecheckApply?wallFloorTileRecheckId='+data[v].wallFloorTileRecheckId+'&flag=30">再次现场测量</a>';
												}
												
												
												else{
													//待确认、已确定、项目经理已确认、设计是已确认
													html +=	'<a class="btnBlueBg" href="${ctx}/app/manager/wallFloorTileRecheck/wallRecheckDetails?wallFloorTileRecheckId='+data[v].wallFloorTileRecheckId+'">申请记录</a>';
												}			
												
												html +=	'</div></div>';
		            		}
		            		
	
		            		
		            	}
		            }
	            	
	            	
	            	$(sectionObj).html(html);
					$('body').waitMe('hide');
	            }
	        })
	        
		}
	
	
	
	
	
	
	</script>
</body>
</html>