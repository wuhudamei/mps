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
	<title>问题上报</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/doneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/problem/install_problem_back"></a>
			<h2 class="title">主材安装问题上报</h2>
		</header><!-- /header -->
		<section class="pad_top11">
			<%-- <div class="mar_btm14 font0 search-area">
				<input class="search-box" type="text" placeholder=" 小区名称、客户姓名">
				<a class="search-btn" href="javascript:;">搜索</a>
				<input type="text" hidden="hidden" value="${text }" id="text">
			</div>
			<c:forEach items="${list }" var="question">
				<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
					<a href="javascript:;" class="arrow_rgt pad_left3">
						<ul class="pad_top3">
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">顾　　　　客：</span>
								<p class="font28 col_3 flow">${question.communityName }-${question.buildNumber }-${question.buildUnit }-${question.buildRoom }-${question.customerName }</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">合 同 工 期 ：</span>
								<p class="font28 col_3 flow">${question.contractTime }天</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">合同开工日期：</span>
								<p class="font28 col_3 flow"><fmt:formatDate value="${question.contractStartDate }" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">合同竣工日期：</span>
								<p class="font28 col_3 flow"><fmt:formatDate value="${question.contractEndDate }" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">订 单 状 态 ：</span>
								<p class="font28 col_3 flow">${question.orderStatusDescription }</p>
							</li>
						</ul>
					</a>
					<div class="btn_wrapper clearfix">
						<a class="two_btn1" href="${ctx }/app/manager/problem/problem_report?orderId=${question.orderId}&text=${text}">问题上报</a>
						<a class="two_btn2" href="${ctx }/app/manager/problem/reported_record?orderId=${question.orderId}&text=${text}">上报记录</a>
					</div>
				</div>
			</c:forEach> --%>
		</section>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript">
		/* $(function(){
			var text = $("#text").val();
			if(null!=text && text!=""){
				$(".search-box").val(text);
			}
			$(".search-btn").bind("click",searchList);
		})
	
		function searchList(){
			var text = $(".search-box").val();
			window.location.href="${ctx}/app/manager/problem/list?text="+text;
		} */
		
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
		searchOrderList();
		
		function searchOrderList(){
			
			var sectionObj=$(".pad_top11");
	        var html ='';
	        
	        html+='<div class="mar_btm14 font0 search-area"> <input class="search-box" type="text" placeholder="小区名称、客户姓名">  <a class="search-btn"  onclick="searchOrderList()"  href="javascript:;"></a> </div>';
	        
	        var text =$(".search-box").val();
	        
	        param={
		        	managerId:managerId,
		            text:text
		        }
	        
	        $.ajax({
	            url: "${ctx}/app/manager/problem/problem_order_ajax_list",
	            type: "post",
	            data:param,
	            success: function(data){
	            	
	            	if(null!=data){
		            	for(var v=0;v<data.length;v++){
		            		
		            		
		            		html += '<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">'+
				    					'<a href="javascript:;" class="arrow_rgt pad_left3">'+
											'<ul class="pad_top3">'+
												'<li class="mar_btm24 clearfix">'+
													'<span class="col_grey font28 flo_left">顾　　　　客：</span>'+
													'<p class="font28 col_3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'-'+data[v].customerName+'</p>'+
												'</li>';
												
							if(null==data[v].contractTime || data[v].contractTime=='undefined' || data[v].contractTime==0 || data[v].contractTime==""){
								
								html +=	'<li class="mar_btm24 clearfix">'+
												'<span class="col_grey font28 flo_left">合 同 工 期 ：</span>'+
												'<p class="font28 col_3 flow">天</p>'+
											'</li>';
							}else{
								
								html +=	'<li class="mar_btm24 clearfix">'+
												'<span class="col_grey font28 flo_left">合 同 工 期 ：</span>'+
												'<p class="font28 col_3 flow">'+ data[v].contractTime +'天</p>'+
											'</li>';
							}
							
												
												
							html += '<li class="mar_btm24 clearfix">'+
											'<span class="col_grey font28 flo_left">合同开工日期：</span>'+
											'<p class="font28 col_3 flow">'+format(data[v].contractStartDate,'yyyy-MM-dd')+'</p>'+
										'</li>'+
										'<li class="mar_btm24 clearfix">'+
											'<span class="col_grey font28 flo_left">合同竣工日期：</span>'+
											'<p class="font28 col_3 flow">'+format(data[v].contractEndDate,'yyyy-MM-dd')+'</p>'+
										'</li>'+
										'<li class="mar_btm24 clearfix">'+
											'<span class="col_grey font28 flo_left">订 单 状 态 ：</span>'+
											'<p class="font28 col_3 flow">'+data[v].orderStatusDescription+'</p>'+
										'</li>'+
									'</ul>'+
								'</a>'+
								'<div class="btn_wrapper clearfix">'+
									'<a class="two_btn1" href="${ctx }/app/manager/problem/problem_report?orderId='+data[v].orderId+'">问题上报</a>'+
									'<a class="two_btn2" href="${ctx }/app/manager/problem/reported_record?orderId='+data[v].orderId+'">上报记录</a>'+
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