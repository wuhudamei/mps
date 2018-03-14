<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>安装验收</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/applyDoneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/projectInstallMenu"></a>
			<h2 class="title">安装验收</h2>
		</header><!-- /header -->
		<section class="pad_top88">

		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/css/installcheck/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/css/installcheck/js/utils/calcRootFontSize.js"></script>
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
		var managerId = "${managerId}";
		searchPurchaseList();
		
		function searchPurchaseList(){
			
			
			var sectionObj=$(".pad_top88");
	        var html ='';
	        
	        html+='<div class="mar_btm14 font0 search-area"> <input class="search-box" type="text" placeholder="小区名称、客户姓名">  <a class="search-btn"  onclick="searchPurchaseList()"  href="javascript:;"></a> </div>';

	        var text =$(".search-box").val();
	        
	        param={
		        	managerId:managerId,
		            text:text
		        }
	        
	        $.ajax({
	            url: "${ctx}/app/manager/orderInstallPlan/ajaxEngineInstallList",
	            type: "post",
	            data:param,
	            success: function(data){

	            	if(null!=data && data.length >0){
		            	for(var v=0;v<data.length;v++){
		            		html += '<div class="sec font0 border_top border_btm mb24 bg_f clearfix">'+
				        				'<ul class="pad_top3 pad_left3 bor_dotted">'+
											'<li class="mb30 clearfix">'+
												'<span class="col_grey font28 flo_left">顾　　　　客：</span>'+
												'<p class="font28 col_3 pad_rgt3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'-'+data[v].customerName+'</p>'+
											'</li>'+
											'<li class="mb30 clearfix">'+
												'<span class="col_grey font28 flo_left">合同工期：</span>'+
												'<p class="font28 col_3 pad_rgt3 flow">'+data[v].contractTime+'</p>'+
											'</li>'+
											'<li class="mb30 clearfix">'+
												'<span class="col_grey font28 flo_left">合同开工日期：</span>'+
												'<p class="font28 col_3 pad_rgt3 flow">'+format(data[v].contractStartDate,'yyyy-MM-dd')+'</p>'+
											'</li>'+
											'<li class="mb30 clearfix">'+
												'<span class="col_grey font28 flo_left">合同俊工日期：</span>'+
												'<p class="font28 col_3 pad_rgt3 flow">'+format(data[v].contractEndDate,'yyyy-MM-dd')+'</p>'+
											'</li>';
		            		if(null!=data[v].actualStartDate && data[v].actualStartDate!='undefined' && data[v].actualStartDate!=''){
				        		html += '<li class="mb30 clearfix">'+
			        						'<span class="col_grey font28 flo_left">实际开工日期：</span>'+
			        						'<p class="font28 col_3 pad_rgt3 flow">'+format(data[v].actualStartDate,'yyyy-MM-dd')+'</p>'+
			        					'</li>';
				        	}else{
				        		html += '<li class="mb30 clearfix">'+
			        						'<span class="col_grey font28 flo_left">实际开工日期：</span>'+
			        						'<p class="font28 col_3 pad_rgt3 flow"></p>'+
			        					'</li>';
				        	}				
											
							html +=	'<li class="mb30 clearfix">'+
											'<span class="col_grey font28 flo_left">订 单 状 态 ：</span>'+
											'<p class="font28 col_3 pad_rgt3 flow">'+data[v].orderStatusDescription+'</p>'+
										'</li>'+
									'</ul>'+
									'<div class="btn_wrapper clearfix">'+
										'<a class="btnBlueOne" href="${ctx }/app/manager/orderInstallPlan/installAcceptance?orderId='+data[v].id+'">安装项验收</a>'+
										'<a class="btnBlueTwo" href="${ctx }/app/manager/orderInstallPlan/installAcceptanceDetail?orderId='+data[v].id+'">安装项验收明细</a>'+
									'</div>'+
								'</div>';
		            	}
	            	}
	            	
	            	$(sectionObj).html(html);
		            $(".search-box").val(text);
					$('body').waitMe('hide');	
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
</body>
</html>