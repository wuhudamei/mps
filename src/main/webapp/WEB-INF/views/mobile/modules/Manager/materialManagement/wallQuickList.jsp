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
	<title>催促送货</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/project/installProgressRecord.css">
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
			<a class="back_btn" href="${ctx}/app/manager/wallAndFloorBrickRecord?id=${materialManagement.orderId}"></a>
			<h2 class="title">催促送货</h2>
		</header><!-- /header -->
		<div class="pad_top88 pad_btm3 bg_bo align_center">
			<p class="font30 col_blue pad_top3 pad_left3 pad_rgt3">${materialManagement.communityName }-${materialManagement.buildNumber }-${materialManagement.buildUnit }-${materialManagement.buildRoom }-${materialManagement.customerName }</p>
		</div>
		<div id="urgeDetails">
			<!-- <div class="mar_btm26 bor_top_ea bor_btm_ea">
				<div class="font28 col_0780ec pad_top24 pad_btm24 pad_left30 pad_rgt30 bor_btm_ccc rela">2017-02-13<a class="font28 col_0780ec seeBtn" href="javascript:;">查看照片</a></div>
				<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">内容：</span>
						<p class="font28 col_10">供应商未到按约定的时间到场，打电话也不接，不知道什么情况</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">操作人：</span>
						<p class="font28 col_10">张骞</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">操作人类型：</span>
						<p class="font28 col_10">项目经理</p>
					</li>
				</ul>
			</div> -->
			<!-- <div class="mar_btm26 bor_top_ea bor_btm_ea">
				<div class="font28 col_0780ec pad_top24 pad_btm24 pad_left30 pad_rgt30 bor_btm_ccc rela">2017-02-13<a class="font28 col_0780ec seeBtn" href="javascript:;">查看照片</a></div>
				<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">内容：</span>
						<p class="font28 col_10">供应商未到按约定的时间到场，打电话也不接，不知道什么情况</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">操作人：</span>
						<p class="font28 col_10">张骞</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">操作人类型：</span>
						<p class="font28 col_10">项目经理</p>
					</li>
				</ul>
			</div> -->
 			<!--<div class="btn_wrapper clearfix">
				<a class="one_btn" href="javascript:;" onclick="pushInstallation()">催促</a>
			</div> -->
		</div>
		<div style="padding-bottom:300px;"></div>
		
		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
			<div class="alertMask">
				<div class="maskWrapper">
					<p class="col_3 maskTit">通知</p>
					<div class="font28 col_6 maskContent">一天只能催促1次，请您明天再来催促。</div>
					<div class="maskBtns clearfix">
						<a class="maskBtn font33 col_f"  onclick="sendMessage()" href="javascript:;">我知道了</a>
					</div>
				</div>
			</div>
		</div>
		
		
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
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
		
		var purchaseId = "${purchaseId}";
		var purchaseStatus = "${materialManagement.purchaseStatus}";
		
		searchUrgeLogList();
		
		function searchUrgeLogList(){
			var sectionObj=$("#urgeDetails");
			var html = '';
			
			$.ajax({
				url: "${ctx}/app/manager/wallAndFloor_urgeLogList_ajax_list",
	            type: "post",
	            data: {purchaseId:purchaseId},
	            success: function(data) {
	            	if(null!=data){
		            	for(var v=0;v<data.length;v++){
		            		
			            	html += '<div class="mar_btm26 bor_top_ea bor_btm_ea">'+
					    				'<div class="font28 col_0780ec pad_top24 pad_btm24 pad_left30 pad_rgt30 bor_btm_ccc rela">'+format(data[v].operateDatetime,'yyyy-MM-dd HH:mm:ss')+
			
					    				'</div>'+
					    				'<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">'+
					    					'<li class="mar_btm26 clearfix">'+
					    						'<span class="font28 col_9">内容：</span>'+
					    						'<p class="font28 col_10">'+data[v].operateContent+'</p>'+
					    					'</li>'+
					    					'<li class="mar_btm26 clearfix">'+
					    						'<span class="font28 col_9">操作人：</span>';

			            	if(null!=data[v].operatorEmployeeName && data[v].operatorEmployeeName!=""){
				        		html += '<p class="font28 col_10">'+data[v].operatorEmployeeName+'</p>';
				        	}else if(data[v].createId == 1){
					        	html += '<p class="font28 col_10">系统管理员</p>';
				        	}else{
					        	html += '<p class="font28 col_10"></p>';
				        	}
					    				
					    	html +=	'</li>'+
					    					'<li class="mar_btm26 clearfix">'+
					    						'<span class="font28 col_9">操作人类型：</span>'+
					    						'<p class="font28 col_10">'+data[v].operatorTypeName+'</p>'+
					    					'</li>'+
					    				'</ul>'+
					    			'</div>';
		            	}
		            }
	            	
	            	if(purchaseStatus=="10" || purchaseStatus=="40" || purchaseStatus=="70"){
	            		html += '<div class="btn_wrapper clearfix">'+
				    				'<a class="one_btn" href="javascript:;" onclick="pushInstallation()">催促</a>'+
					    		'</div>';
	            	}
	            	
	    		
	            	$(sectionObj).html(html);
	    	        $('body').waitMe('hide');
	            }
	            
			})
		}
	
		
		 //催促安装
	    function pushInstallation(){
	    	
	    	
	    	$.ajax({
				url: "${ctx}/app/manager/wallAndFloor_push_installation_ajax",
	            type: "post",
	            data:{purchaseId:purchaseId},
	            success: function(data) {
	            	if(null!=data && data=="1"){
	            		$("#subReport").show();
	            	}else{
	            		window.location.href = "${ctx}/app/manager/wallAndFloor_push_installation?purchaseId="+purchaseId+"&orderId=${materialManagement.orderId}";
	            	}
	            }
	    	})
	    }
	    
	    //关闭 --催促安装--弹框
	    function sendMessage(){
	    	$("#subReport").hide();
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