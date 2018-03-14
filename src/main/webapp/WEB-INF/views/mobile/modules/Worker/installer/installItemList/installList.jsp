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
	<title>安装项列表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/applyDoneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/waitMe.css" >
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/worker/install/installIndex/toindex"></a>
			<h2 class="title">安装项列表</h2>
		</header><!-- /header -->
		<section class="pad_top88">
			<nav class="chooseNav clearfix font0">
				<a href="javascript:;"><span class="font32 col_6 pb30 activeA">未完工安装项</span></a>
				<a href="javascript:;"><span class="font32 col_6 pb30" onclick="findInstallConstructBillList('2')">已完工安装项</span></a>
			</nav>
			<div  class="sec" id="boxA">
				<%-- <div class="pl30 pr30 font0 border_top border_btm mb24 bg_f clearfix">
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
				</div>--%>
			</div>
			<div class="sec undis" id="boxB">
			</div>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/waitMe.js"></script>
	<script>
		$(document).on('click', '.chooseNav a', function(){
			var i = $(this).index();
			$('.chooseNav a').find('span').removeClass('activeA');
			$(this).find('span').addClass('activeA');
			$('.sec').addClass('undis');
			$('.sec').eq(i).removeClass('undis');
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
		
		
		findInstallConstructBillList("1");
		
		//安装项列表
		function findInstallConstructBillList(type){
			
			run_waitMe('正在加载数据,请稍等');
			
			var sectionObj;
			if(type=="1"){
				sectionObj=$("#boxA");
			}else{
				sectionObj=$("#boxB");
			}
			var html = '';
			$.ajax({
				url: "${ctx}/app/worker/install/installItemList/install_construct_bill_ajax_list",
	            type: "post",
	            data:{type:type},
	            success: function(data) {
	            	
	            	if(null!=data && data.length>0){
		            	for(var v=0;v<data.length;v++){
		            		
		            		html += '<div class="pl30 pr30 font0 border_top border_btm mb24 bg_f clearfix">'+
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
										'</div>';
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