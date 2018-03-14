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
	<title>设计问题上报记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/quesDone.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/ProblemReport/ProblemReport.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/ProblemReport/Problem_xq.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/ProblemReport/constructionSite.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/ProblemReport/swiper.min.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
	<style>
		.pad_btm40{padding-bottom:.4rem;}
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
		/* .maskBtn{display: block;width: 60%;} */
		.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
    		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}
	</style>
</head>
<body>
	<div class="" id="detail">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/problem/siteDesign/list"></a>
			<h2 class="title">设计问题上报记录</h2>
		</header><!-- /header -->
		<div class="show_line">
			<p class="every bg_f h130  mb20 col_blue  col_blue">${materialManagement.communityName }-${materialManagement.buildNumber }-${materialManagement.buildUnit }-${materialManagement.buildRoom }-${materialManagement.customerName }</p>
			<i class="bg_lace"></i>
		</div>
		
		<section class="problemReport border_none" id="urgeDetails">
			<!-- <div class="sec font0   mar_btm24 bg_f clearfix font30">
		   		<p class="bg_f6 h20 place_juli"></p>
				<div class="CheckTime clearfix ">
					<p class="flo_left font30">2016-6-5  12:03:02</p>
					<p class="find_image font24 flo_right font28"><a href="" class="col_blue">查看照片</a></p>
				</div>
				<ul class="pad_top3 pad_left3 bor_bt">
					<li class="mar_btm24 clearfix">   
						<span class="col_grey font28 flo_left spanRgt">问题分类 ：</span>
						<p class="font28 col_3 pad_rgt3 flow"> 签单前套餐内项目介绍不清，造成后期增项</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">合同开工日期 ：</span>
						<p class="font28 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">责任人 ：</span>
						<p class="font28 col_3 pad_rgt3 flow"> 张小小</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">问题描述 ：</span>
						<p class="font28 col_3 pad_rgt3 flow">供应商未到按约定的时间到场，打电话也不接，不知道什么情况</p>
					</li>
				</ul>
				<ul class="pad_top3 pad_left3 bor_bt bg_f climit_left_width">
					<li class="mar_btm24 clearfix">   
						<span class="col_grey font28 flo_left spanRgt">设计师处理时间 ：</span>
						<p class="font28 col_3 pad_rgt3 flow"> 2017-02-13  14:50:52</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">设计师处理描述 ：</span>
						<p class="font28 col_3 pad_rgt3 flow">供应商未到按约定的时间到场供应商未到按约定的时间到场供应商未到按约定的时间到场供应商未到按约定的时间到场供应商未到按约定的时间到场供应商未到按约定的时间到场供应商未到按约定的时间到场供应商未到按约定的时间到场供应商未到按约定的时间到场供应商未到按约定的时间到场供应商未到按约定的时间到场</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">设计师上传照片 ：</span>
						<p class="font28 col_3 pad_rgt3 flow">共上传3张照片</p>
						<p class="find_image font24 "><a href="" class="col_blue">查看照片</a></p>
					</li>
				</ul>
			</div> -->
				
		</section>
		
		
		
		
		<div class="show-bigimg undis">
			<div class="mask"></div>
			<div class="content">
				<a class="back_btn" href="javascript:void(0)" onclick="backBtn()"></a>
				<div class="now-page">
					<div>
						<span class="current-page"></span>
						<span class="pre">/</span>
						<span class="all-page">9</span>
					</div>
				</div>
				<div class="construct-state-box">
					<div class="swiper-container construct-state-big">
					  <div class="swiper-wrapper" id="pictureMask">
					    	<%-- <div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/home/images/constructionSite/biglunbo1.png" />
							</div> --%>
							<%-- <div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/Manager/images/problem_up/material.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/Manager/images/problem_up/design.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/Manager/images/problem_up/material.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/Manager/images/problem_up/design.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/Manager/images/problem_up/material.png" />
							</div>
							<div class="swiper-slide">
								<img width="100%" src="${ctxStatic}/mobile/modules/Manager/images/problem_up/design.png" />
							</div> --%>
							
					  </div>
					  <div class="swiper-button-prev"></div>
	    			<div class="swiper-button-next"></div>
					</div>
				</div>
			</div>
		</div>
		
		
		
		
		<div class="alertMask undis" id ="alert">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarks"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sure()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/swiper.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript">
	
	
			//图片弹出框--js
			function picLoading(){
				var photoAll = $('.swiper-wrapper .swiper-slide').length;
				$(".all-page").text(photoAll);
				var mySwiper = new Swiper ('.swiper-container',{
			    	loop: false,
			    	nextButton: '.swiper-button-next',
		    		prevButton: '.swiper-button-prev',
		    		onSlideChangeEnd: function(swiper){
		    			//console.log(swiper.activeIndex);
			   			$('.current-page').text(swiper.activeIndex+1);
					}
			    })
			}
	  
	    
			//蒙层
			function run_waitMe(text){
			    $('#detail').waitMe({
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
			var baseUrl = "${baseUrl}";
			
			searchProblemList();
	
			//加载页面上报记录
			function searchProblemList(){
				var sectionObj=$("#urgeDetails");
				var html = '';
				
				$.ajax({
					url: "${ctx}/app/manager/problem/siteDesign/sign_design_problem_log_ajax_list",
		            type: "post",
		            data:{orderId:orderId},
		            success: function(data) {
		            	
		            	if(null!=data){
			            	for(var v=0;v<data.length;v++){
			            		
			            		
			            		html += '<div class="sec font0   mar_btm24 bg_f clearfix font30">'+
					        		   		'<p class="bg_f6 h20 place_juli"></p>'+
					        				'<div class="CheckTime clearfix ">'+
					        					'<p class="flo_left font30">'+data[v].createDate+'</p>'+
					        					'<p class="find_image font24 flo_right font28"><a href="javascript:;" class="col_blue" onclick="selectPic('+data[v].id+',2081)">查看照片</a></p>'+
					        				'</div>'+
					        				'<ul class="pad_top3 pad_left3 bor_bt">'+
					        					'<li class="mar_btm24 clearfix">'+   
					        						'<span class="col_grey font28 flo_left spanRgt">问题分类 ：</span>'+
					        						'<p class="font28 col_3 pad_rgt3 flow">'+data[v].typeName+'</p>'+
					        					'</li>'+
					        					'<li class="mar_btm24 clearfix">'+
					        						'<span class="col_grey font28 flo_left spanRgt">期望完成日期 ：</span>'+
					        						'<p class="font28 col_3 pad_rgt3 flow">'+data[v].expectSolveDatetime+'</p>'+
					        					'</li>'+
					        					'<li class="mar_btm24 clearfix">'+
					        						'<span class="col_grey font28 flo_left spanRgt">责任人 ：</span>'+
					        						'<p class="font28 col_3 pad_rgt3 flow">'+data[v].inchargeName+'</p>'+
					        					'</li>'+
					        					'<li class="mar_btm24 clearfix">'+
					        						'<span class="col_grey font28 flo_left spanRgt">问题描述 ：</span>'+
					        						'<p class="font28 col_3 pad_rgt3 flow">'+data[v].problemDescribe+'</p>'+
					        					'</li>'+
					        				'</ul>';
					        	if(null!=data[v].logDate && data[v].logDate != ""){
					        		html += '<ul class="pad_top3 pad_left3 bor_bt bg_f climit_left_width">'+
						        					'<li class="mar_btm24 clearfix">'+
					        						'<span class="col_grey font28 flo_left spanRgt">设计师处理时间 ：</span>'+
					        						'<p class="font28 col_3 pad_rgt3 flow">'+data[v].logDate+'</p>'+
					        					'</li>'+
					        					'<li class="mar_btm24 clearfix">'+
					        						'<span class="col_grey font28 flo_left spanRgt">设计师处理描述 ：</span>'+
					        						'<p class="font28 col_3 pad_rgt3 flow">'+data[v].problemSolveNotes+'</p>'+
					        					'</li>'+
					        					'<li class="mar_btm24 clearfix">'+
					        						'<span class="col_grey font28 flo_left spanRgt">设计师上传照片 ：</span>'+
					        						'<p class="font28 col_3 pad_rgt3 flow">共上传'+data[v].picCount+'张照片</p>';
					        		if(data[v].picCount>0){
					        			
					        			html += '<p class="find_image font24 "><a href="javascript:;" class="col_blue" onclick="selectPic('+data[v].id+',2082)">查看照片</a></p>'+
						        						'</li>'+
							        				'</ul>'+
							        			'</div>';
					        		}else{
					        			html += '<p class="find_image font24 "></p>'+
						        						'</li>'+
							        				'</ul>'+
							        			'</div>';
					        		}
					        						
					        	}else{
					        		html += '<ul class="pad_top3 pad_left3 bor_bt bg_f climit_left_width">'+
						        					'<li class="mar_btm24 clearfix">'+
					        						'<span class="col_grey font28 flo_left spanRgt">设计师处理时间 ：</span>'+
					        						'<p class="font28 col_3 pad_rgt3 flow"></p>'+
					        					'</li>'+
					        					'<li class="mar_btm24 clearfix">'+
					        						'<span class="col_grey font28 flo_left spanRgt">设计师处理描述 ：</span>'+
					        						'<p class="font28 col_3 pad_rgt3 flow"></p>'+
					        					'</li>'+
					        					'<li class="mar_btm24 clearfix">'+
					        						'<span class="col_grey font28 flo_left spanRgt">设计师上传照片 ：</span>'+
					        						'<p class="font28 col_3 pad_rgt3 flow"></p>'+
					        						'<p class="find_image font24 "></p>'+
					        					'</li>'+
					        				'</ul>'+
					        			'</div>';
					        	}			
					        				

			            		
			            		
			            	}
		            	}
		            	
		            	$(sectionObj).html(html);
		    	        $('#detail').waitMe('hide');
		    	        
		            }
				})
				
			}
			
			
			//查看图片
			function selectPic(id,text){
				
				if(null == id || id == ""){
					$("#alertRemarks").text("问题上报编号为空");
					$("#alert").show();
					return false;
				}
				if(null == text || text == ""){
					$("#alertRemarks").text("问题上报图片类型为空");
					$("#alert").show();
					return false;
				}
				
				run_waitMe('正在加载图片,请稍等');
				
				var html2 = '';
				
				$.ajax({
					url: "${ctx}/app/manager/problem/siteDesign/sign_design_problem_log_pic",
		            type: "post",
		            data:{
		            		id:id,
		            		text:text
		            	},
		            success: function(data) {
		            	
		            	if(null!=data && data.length>0){
			            	for(var v=0;v<data.length;v++){
			            		
			            		html2 += '<div class="swiper-slide">'+
											'<img width="100%" src="'+baseUrl+data[v].picUrl+'" />'+
										'</div>';
										
			            	}
			            	
			            	$(".all-page").text(data.length);
			            	$('.current-page').text(1);
			            	$("#pictureMask").html(html2);
			            	$('#detail').waitMe('hide');
			            	$('.show-bigimg').removeClass('undis'); 
			            	picLoading();
			            	
		            	}else{
		            		$('#detail').waitMe('hide');
		            		$("#alertRemarks").text("暂无图片");
							$("#alert").show();
		            	}
		            }
	            }) 
			}
			
			//关闭弹框
			function sure(){
				$("#alert").hide();
			}
			//关闭页面蒙层
			function backBtn(){
				var html3 = '';
				$('.show-bigimg').addClass('undis');
				$(".all-page").text(0);
            	$('.current-page').text(0);
				$("#pictureMask").html(html3);
			}
	</script>
</body>
</html>