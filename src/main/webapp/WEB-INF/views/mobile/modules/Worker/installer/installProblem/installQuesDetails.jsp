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
	<title>问题上报详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/installQuesDetails.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/waitMe.css" >
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/worker/install/installProblem/list"></a>
			<h2 class="title">问题上报详情</h2>
		</header><!-- /header -->
		<div class="pad_top88 pad_btm3 bg_bo align_center">
			<p class="font30 col_0780ec pt24 pl30 pr30 pb30 align_center">${installItem.communityName }-${installItem.buildNumber }-${installItem.buildUnit }-${installItem.buildRoom }-${installItem.installItemName }</p>
		</div>
		<div id="boxA">
			<!-- <div class="bor_top_ea bor_btm_ea">
				<div class="font28 col_0780ec pt34 pb15 pad_left30 pad_rgt30 bor_btm_ddd rela">2017-02-13  14:50:52<a class="font28 col_0780ec seeBtn" href="javascript:;">查看现场照片</a></div>
				<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">
					<li class="mb30 clearfix">
						<span class="font28 col_9">问题分类：</span>
						<p class="font28 col_10">签单前套餐内项目介绍不清，造成后期增项，引起客户不满/投诉</p>
					</li>
					<li class="mb30 clearfix">
						<span class="font28 col_9">问题描述：</span>
						<p class="font28 col_10">供应商未到按约定的时间到场，打电话也不接，不知道什么情况</p>
					</li>
				</ul>
			</div> -->
		</div>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/global.js"></script>
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
		
		var constructBillId = '${constructBillId}';
		
		findProblemLogList();
		
		//查询施工单列表(状态为10-已确定工人组/20-施工中/30-工人已申请完工）
		function findProblemLogList(){
			
			run_waitMe('正在加载数据,请稍等');
			
			var sectionObj = $("#boxA");
			
			var html = '';
			
			$.ajax({
				url: "${ctx}/app/worker/install/installProblem/problem_log_ajax_list",
	            type: "post",
	            data:{constructBillId:constructBillId},
	            success: function(data) {
	            	
	            	if(null!=data && data.length>0){
		            	for(var v=0;v<data.length;v++){
		            		html += '<div class="bor_top_ea bor_btm_ea">'+
				        				'<div class="font28 col_0780ec pt34 pb15 pad_left30 pad_rgt30 bor_btm_ddd rela">'+data[v].createDateString+
				        					'<a class="font28 col_0780ec seeBtn" href="${ctx}/app/worker/install/installProblem/picture?id='+data[v].id+'">查看现场照片</a>'+
				        				'</div>'+
				        				'<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">'+
				        					'<li class="mb30 clearfix">'+
				        						'<span class="font28 col_9">问题分类：</span>'+
				        						'<p class="font28 col_10">'+data[v].typeName+'</p>'+
				        					'</li>'+
				        					'<li class="mb30 clearfix">'+
				        						'<span class="font28 col_9">问题描述：</span>'+
				        						'<p class="font28 col_10">'+data[v].problemDescribe+'</p>'+
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