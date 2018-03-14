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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/project/installProgressRecord.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/problem/wallAndFloor/list"></a>
			<h2 class="title">问题上报详情</h2>
		</header><!-- /header -->
		<div class="pad_top88 pad_btm3 bg_bo align_center">
			<p class="font30 col_blue pad_top3 pad_left3 pad_rgt3">${materialManagement.communityName }-${materialManagement.buildNumber }-${materialManagement.buildUnit }-${materialManagement.buildRoom }-${materialManagement.customerName }</p>
		</div>
		<div id="urgeDetails">
			<!-- <div class="mar_btm26 bor_top_ea bor_btm_ea">
				<div class="font28 col_0780ec pad_top24 pad_btm24 pad_left30 pad_rgt30 bor_btm_ccc rela">2017-02-13<a class="font28 col_0780ec seeBtn" href="javascript:;">查看照片</a></div>
				<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">问题分类：</span>
						<p class="font28 col_10">工人未到场</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">是否影响工期：</span>
						<p class="font28 col_10">否</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">影响工期天数：</span>
						<p class="font28 col_10">3天</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">描述：</span>
						<p class="font28 col_10">供应商未到按约定的时间到场，打电话也不接，不知道什么情况</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">材料部处理：</span>
						<p class="font28 col_10">2017-02-16 12:10:15已联系厂家，2017-02-18上门安装</p>
					</li>
				</ul>
			</div>
			<div class="mar_btm26 bor_top_ea bor_btm_ea">
				<div class="font28 col_0780ec pad_top24 pad_btm24 pad_left30 pad_rgt30 bor_btm_ccc rela">2017-02-13<a class="font28 col_0780ec seeBtn" href="javascript:;">查看照片</a></div>
				<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">问题分类：</span>
						<p class="font28 col_10">工人未到场</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">是否影响工期：</span>
						<p class="font28 col_10">否</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">影响工期天数：</span>
						<p class="font28 col_10">3天</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">描述：</span>
						<p class="font28 col_10">供应商未到按约定的时间到场，打电话也不接，不知道什么情况</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">材料部处理：</span>
						<p class="font28 col_10">2017-02-16 12:10:15已联系厂家，2017-02-18上门安装</p>
					</li>
				</ul>
			</div> -->
		</div>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript">
	
	
			function run_waitMe(text){
			    $('.font0').waitMe({
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
			
			var orderId = "${orderId}";
			
			searchProblemList();
	
			function searchProblemList(){
				var sectionObj=$("#urgeDetails");
				var html = '';
				
				$.ajax({
					url: "${ctx}/app/manager/problem/wallAndFloor/problem_log_ajax_list",
		            type: "post",
		            data:{orderId:orderId},
		            success: function(data) {
		            	
		            	if(null!=data){
			            	for(var v=0;v<data.length;v++){

			            		html += '<div class="mar_btm26 bor_top_ea bor_btm_ea">'+
			            					'<div class="font28 col_0780ec pad_top24 pad_btm24 pad_left30 pad_rgt30 bor_btm_ccc rela">'+format(data[v].createDate,'yyyy-MM-dd HH:mm:ss')+'</div>'+
					        				'<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">'+
					        					'<li class="mar_btm26 clearfix">'+
					        						'<span class="font28 col_9 spanRgt30">问题分类：</span>'+
					        						'<p class="font28 col_10">'+data[v].typeName+'</p>'+
					        					'</li>';
					        	
					        	if(null!=data[v].isDelay && data[v].isDelay=="1"){
					        		
						        	html += '<li class="mar_btm26 clearfix">'+
						        						'<span class="font28 col_9 spanRgt30">是否影响工期：</span>'+
						        						'<p class="font28 col_10">是</p>'+
						        					'</li>'+
						        					'<li class="mar_btm26 clearfix">'+
						        						'<span class="font28 col_9 spanRgt30">影响工期天数：</span>'+
						        						'<p class="font28 col_10">'+data[v].delayDays+'天</p>'+
						        					'</li>';
					        	}else{
					        		
						        	html += '<li class="mar_btm26 clearfix">'+
						        						'<span class="font28 col_9 spanRgt30">是否影响工期：</span>'+
						        						'<p class="font28 col_10">否</p>'+
						        					'</li>';
					        	}
					        	html += '<li class="mar_btm26 clearfix">'+
					        						'<span class="font28 col_9 spanRgt30">描述：</span>'+
					        						'<p class="font28 col_10">'+data[v].problemDescribe+'</p>'+
					        					'</li>';
					        	if(null!=data[v].logDate && data[v].logDate!='undefined'){
					        		html += '<li class="mar_btm26 clearfix">'+
				        						'<span class="font28 col_9 spanRgt30">材料部处理：</span>'+
				        						'<p class="font28 col_10">'+format(data[v].logDate,'yyyy-MM-dd HH:mm:ss')+'<br />'+data[v].problemDescribe+'</p>'+
				        					'</li>';
					        	}
					        					
					        	html +=	'</ul>'+
					        			'</div>';
			            		
			            	}
		            	}
		            	$(sectionObj).html(html);
		    	        $('.font0').waitMe('hide');
		            }
				})
				
			}
			
			 var format = function(time, format){
			         var t = new Date(time);

			         var tf = function(i){return (i < 10 ? '0' : '') + i};
			         return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
			             switch(a){
			                 case 'yyyy':
			                     return tf(t.getFullYear());
			                     break;
			                 case 'MM':
			                     return tf(t.getMonth() + 1);
			                     break;
			                 case 'mm':
			                     return tf(t.getMinutes());
			                     break;
			                 case 'dd':
			                     return tf(t.getDate());
			                     break;
			                 case 'HH':
			                     return tf(t.getHours());
			                     break;
			                 case 'ss':
			                     return tf(t.getSeconds());
			                     break;
			             }
			         })
			     }
	</script>
</html>