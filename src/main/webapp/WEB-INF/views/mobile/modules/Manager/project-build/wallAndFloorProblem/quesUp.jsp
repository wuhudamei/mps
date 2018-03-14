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
	<title>墙地砖问题上报</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/progress/signList.css"/>
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
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/problem/wallAndFloor/wallAndFloor_problem_back"></a>
			<h2 class="title">墙地砖问题上报</h2>
		</header><!-- /header -->
		<section class="pad_top11">
			<!-- <div class="mar_btm14 font0 search-area">
				<input class="search-box" type="text" placeholder=" 小区名称、客户姓名">
				<a class="search-btn" href="javascript:;"></a>
			</div>
			<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
				<ul class="pad_top3 pad_left3 bor_dotted">
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">顾　　　　客：</span>
						<p class="font28 col_3 pad_rgt3 flow">鹿港嘉苑-10-3-2001-张三丰鹿港嘉苑-10-3-2001-张三丰</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">合同开工日期：</span>
						<p class="font28 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">实际开工日期：</span>
						<p class="font28 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">合 同 工 期 ：</span>
						<p class="font28 col_3 pad_rgt3 flow">60天</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">订 单 状 态 ：</span>
						<p class="font28 col_3 pad_rgt3 flow">已派项目经理</p>
					</li>
				</ul>
				<div class="btn_wrapper clearfix">
					<a class="two_btn1" href="javascript:;">上报问题</a>
					<a class="two_btn2" href="javascript:;">上报记录</a>
				</div>
			</div>
			<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
				<ul class="pad_top3 pad_left3 bor_dotted">
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">顾　　　　客：</span>
						<p class="font28 col_3 pad_rgt3 flow">鹿港嘉苑-10-3-2001-张三丰鹿港嘉苑-10-3-2001-张三丰</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">合同开工日期：</span>
						<p class="font28 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">实际开工日期：</span>
						<p class="font28 col_3 pad_rgt3 flow">2016-07-30</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">合 同 工 期 ：</span>
						<p class="font28 col_3 pad_rgt3 flow">60天</p>
					</li>
					<li class="mar_btm24 clearfix">
						<span class="col_grey font28 flo_left spanRgt">订 单 状 态 ：</span>
						<p class="font28 col_3 pad_rgt3 flow">已派项目经理</p>
					</li>
				</ul>
				<div class="btn_wrapper clearfix">
					<a class="two_btn1" href="javascript:;">上报问题</a>
					<a class="two_btn2" href="javascript:;">上报记录</a>
				</div>
			</div> -->
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	
	
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="messageContext">您刚刚提交过墙地砖问题上报，请耐心等待五分钟后再次操作</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
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
		
		run_waitMe('正在加载数据,请稍等');
		var managerId = "${managerId}";
		searchPurchaseList();
		
		function searchPurchaseList(){
			
			var sectionObj=$(".pad_top11");
	        var html ='';
	        
	        html+='<div class="mar_btm14 font0 search-area"> <input class="search-box" type="text" placeholder="小区名称、客户姓名">  <a class="search-btn"  onclick="searchPurchaseList()"  href="javascript:;"></a> </div>';

	        var text =$(".search-box").val();
	        
	        param={
		        	managerId:managerId,
		            text:text
		        }
	        
	        $.ajax({
	            url: "${ctx}/app/manager/problem/wallAndFloor/problem_wallAndFloor_ajax_list",
	            type: "post",
	            data:param,
	            success: function(data){
	            	if(null!=data){
	 
		            	for(var v=0;v<data.length;v++){
		                   	if(data[v].isScrap=='1'){
		                  		html += '<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">'+
		        				'<ul class="pad_top3 pad_left3 bor_dotted  abandon">'+
									'<li class="mar_btm24 clearfix">'+
										'<span class="col_grey font28 flo_left spanRgt">顾　　　　客：</span>'+
										'<p class="font28 col_3 pad_rgt3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'-'+data[v].customerName+'</p>'+
									'</li>'+
									'<li class="mar_btm24 clearfix">'+
										'<span class="col_grey font28 flo_left spanRgt">合同开工日期：</span>'+
										'<p class="font28 col_3 pad_rgt3 flow">'+format(data[v].contractStartDate,'yyyy-MM-dd')+'</p>'+
									'</li>'+
									'<li class="mar_btm24 clearfix">'+
										'<span class="col_grey font28 flo_left spanRgt">实际开工日期：</span>'+
										'<p class="font28 col_3 pad_rgt3 flow">'+format(data[v].actualStartDate,'yyyy-MM-dd')+'</p>'+
									'</li>';
									
									
            		if(null==data[v].contractTime || data[v].contractTime=='undefined' || data[v].contractTime==0 || data[v].contractTime==""){
						
						html +=	'<li class="mar_btm24 clearfix">'+
										'<span class="col_grey font28 flo_left spanRgt">合 同 工 期 ：</span>'+
										'<p class="font28 col_3 pad_rgt3 flow">天</p>'+
									'</li>';
					}else{
						
						html +=	'<li class="mar_btm24 clearfix">'+
										'<span class="col_grey font28 flo_left spanRgt">合 同 工 期 ：</span>'+
										'<p class="font28 col_3 pad_rgt3 flow">'+ data[v].contractTime +'天</p>'+
									'</li>';
					}
									
					html +=	'<li class="mar_btm24 clearfix">'+
									'<span class="col_grey font28 flo_left spanRgt">订 单 状 态 ：</span>'+
									'<p class="font28 col_3 pad_rgt3 flow">'+data[v].orderStatusDescription+'</p>'+
								'</li>'+
							'</ul>'+
							'<div class="btn_wrapper clearfix">'+
// 								'<a class="two_btn1" href="javascript:;" onclick="problem('+data[v].orderId+')">上报问题</a>'+
								'<a class="two_btn2" href="${ctx}/app/manager/problem/wallAndFloor/problemRecord?orderId='+data[v].orderId+'">上报记录</a>'+
							'</div>'+
						'</div>';
			            	}else{
			              		html += '<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">'+
		        				'<ul class="pad_top3 pad_left3 bor_dotted">'+
									'<li class="mar_btm24 clearfix">'+
										'<span class="col_grey font28 flo_left spanRgt">顾　　　　客：</span>'+
										'<p class="font28 col_3 pad_rgt3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'-'+data[v].customerName+'</p>'+
									'</li>'+
									'<li class="mar_btm24 clearfix">'+
										'<span class="col_grey font28 flo_left spanRgt">合同开工日期：</span>'+
										'<p class="font28 col_3 pad_rgt3 flow">'+format(data[v].contractStartDate,'yyyy-MM-dd')+'</p>'+
									'</li>'+
									'<li class="mar_btm24 clearfix">'+
										'<span class="col_grey font28 flo_left spanRgt">实际开工日期：</span>'+
										'<p class="font28 col_3 pad_rgt3 flow">'+format(data[v].actualStartDate,'yyyy-MM-dd')+'</p>'+
									'</li>';
									
									
            		if(null==data[v].contractTime || data[v].contractTime=='undefined' || data[v].contractTime==0 || data[v].contractTime==""){
						
						html +=	'<li class="mar_btm24 clearfix">'+
										'<span class="col_grey font28 flo_left spanRgt">合 同 工 期 ：</span>'+
										'<p class="font28 col_3 pad_rgt3 flow">天</p>'+
									'</li>';
					}else{
						
						html +=	'<li class="mar_btm24 clearfix">'+
										'<span class="col_grey font28 flo_left spanRgt">合 同 工 期 ：</span>'+
										'<p class="font28 col_3 pad_rgt3 flow">'+ data[v].contractTime +'天</p>'+
									'</li>';
					}
									
					html +=	'<li class="mar_btm24 clearfix">'+
									'<span class="col_grey font28 flo_left spanRgt">订 单 状 态 ：</span>'+
									'<p class="font28 col_3 pad_rgt3 flow">'+data[v].orderStatusDescription+'</p>'+
								'</li>'+
							'</ul>'+
							'<div class="btn_wrapper clearfix">'+
								'<a class="two_btn1" href="javascript:;" onclick="problem('+data[v].orderId+')">上报问题</a>'+
								'<a class="two_btn2" href="${ctx}/app/manager/problem/wallAndFloor/problemRecord?orderId='+data[v].orderId+'">上报记录</a>'+
							'</div>'+
						'</div>';
			            	}
		          
		            	}
	            	}
	            	
	            	$(sectionObj).html(html);
		            $(".search-box").val(text);
					$('body').waitMe('hide');	
	            }
	        })
			
		}
	
		function sendMessage(){
			$("#subReport").hide();
		}
	
	
		//问题上报
		function problem(orderId){
			var businessType = "2";
			//校验5分钟内的重复提交
			 $.ajax({
		            url: "${ctx}/app/manager/problem/wallAndFloor/problem_wallAndFloor_ajax_time",
		            type: "post",
		            data: {
		            		orderId:orderId,
		            		businessType:businessType
		            	 },
		            success: function(data){
		            	if(null!=data && data == "0"){
		            		
		            		window.location.href = "${ctx}/app/manager/problem/wallAndFloor/problem_wallAndFloor?orderId="+orderId;
		            	
		            	}else if(data == "1"){
		            		$("#messageContext").text("订单id传送失败");
		            		$("#subReport").show();
		            	}else{
		            		$("#messageContext").text("您刚刚提交过墙地砖问题上报，请耐心等待五分钟后再次操作");
		            		$("#subReport").show();
		            	}
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