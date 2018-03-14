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
	<title>复尺记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/Details.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="wrap">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/checksize/checksizeList"></a>
			<h2 class="title">复尺记录</h2>
		</header>
		<!-- /header -->
		<section class="pt112" id="sectionRemark">
			<!-- <div class="mar_btm14 font0 search-area">
				<input class="search-box" type="text" placeholder=" 小区名称、客户姓名、项目经理">
				<a class="search-btn" href="javascript:;"></a>
			</div>
			<div class="shadow">
				<div class="font28 col_0780ec pt20 pb16 pad_left30 pad_rgt30 bor_btm_ddd rela">2017-02-13  14:50:52<a class="font28 col_0780ec seeBtn" href="../viewPic.html">查看照片</a></div>
				<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">
					<li class="mb30 clearfix">
						<span class="font30 col_grey pl3em">复尺内容：</span>
						<p class="font30 col_3">工人未到场</p>
					</li>
					<li class="mb30 clearfix">
						<span class="font30 col_grey pl3em">复尺状态：</span>
						<p class="font30 col_3">否</p>
					</li>
					<li class="mb30 clearfix">
						<span class="font30 col_grey pl1em">期望复尺日期：</span>
						<p class="font30 col_3">3天</p>
					</li>
					<li class="mb30 clearfix">
						<span class="font30 col_grey">供应商确认日期：</span>
						<p class="font30 col_3">供应商未到按约定的时间到场，打电话也不接，不知道什么情况</p>
					</li>
					<li class="mb30 clearfix">
						<span class="font30 col_grey pl5em">回复：</span>
						<p class="font30 col_3">2017-02-16 12:10:15已联系厂家2017-02-18上门安装</p>
					</li>
				</ul>
			</div> -->
		</section>
		<!-- /section -->
		<div style="padding-bottom:300px;"></div>
	</div>
	<!-- /.wrap -->
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
</body>


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
		
		
		searchChecksizeList();
		
		
		
		function searchChecksizeList(){
			
			var sectionObj=$("#sectionRemark");
	        var html ='';
	        
	        html += '<div class="mar_btm14 font0 search-area">'+
		                '<input class="search-box" type="text" placeholder="复尺内容">'+
		                '<a class="search-btn" href="javascript:;" onclick="searchChecksizeList()" ></a>'+
		            '</div>';

	        var text =$(".search-box").val();
	        var orderId = '${orderId}';
	       
	        $.ajax({
	            url: "${ctx}/app/manager/checksize/checksize_record_list_ajax",
	            type: "post",
	            data: {
	            	orderId:orderId,
	            	text:text
	            },
	            success: function(data){
	            	
	            	if(null!=data && data.length>0){
	            		
		            	for(var v=0;v<data.length;v++){
		            		
		            		html += '<div class="shadow">'+
				        				'<div class="font28 col_0780ec pt20 pb16 pad_left30 pad_rgt30 bor_btm_ddd rela">'+data[v].createDateString;
				        				
				        	
				        	if(null!=data[v].picCount && ""!=data[v].picCount && data[v].picCount>0){
		        				html += '<a class="font28 col_0780ec seeBtn" href="${ctx}/app/manager/checksize/checksizeRecordPic?checksizeId='+data[v].id+'">查看照片</a>';
				        	}
				        	
				        	
				        	html += '</div>'+
				        				'<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">'+
				        					'<li class="mb30 clearfix">'+
				        						'<span class="font30 col_grey pl3em">复尺内容：</span>'+
				        						'<p class="font30 col_3">'+data[v].checksizeTypeName+'</p>'+
				        					'</li>'+
				        					'<li class="mb30 clearfix">'+
				        						'<span class="font30 col_grey pl3em">复尺状态：</span>'+
				        						'<p class="font30 col_3">'+data[v].checksizeStatusName+'</p>'+
				        					'</li>'+
				        					'<li class="mb30 clearfix">'+
				        						'<span class="font30 col_grey pl1em">期望复尺日期：</span>'+
				        						'<p class="font30 col_3">'+data[v].checksizeDateString+'</p>'+
				        					'</li>'+
				        					'<li class="mb30 clearfix">'+
				        						'<span class="font30 col_grey">供应商确认日期：</span>'+
				        						'<p class="font30 col_3">'+data[v].supplierConfirmDateString+'</p>'+
				        					'</li>'+
				        					'<li class="mb30 clearfix">'+
				        						'<span class="font30 col_grey pl5em">回复：</span>'+
				        						'<p class="font30 col_3">'+data[v].materialDepartmentDealReply+'</p>'+
				        					'</li>'+
				        				'</ul>'+
				        			'</div>';
								
		            	}
	            	}
					$(sectionObj).html(html);
		            $(".search-box").val(text);
					$('body').waitMe('hide');	
	            }
	        })
		}




</script>
</html>