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
	<title>我的订单</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/doneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/search.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx }/app/pqc/indexMine"></a>
			<h2 class="title">我的订单</h2>
		</header><!-- /header -->
		<section class="pad_top11">
			<div class="mar_btm14 font0 search-area">
				<input class="search-box" type="text" placeholder=" 小区名称、客户姓名">
				<a class="search-btn" href="javascript:;"></a>
			</div>
			
			<c:forEach items="${list }" var="order">
			<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
				<a href="javascript:;" class="arrow_rgt pad_left3">
					<ul class="pad_top3">
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">顾　　　　客：</span>
							<p class="font28 col_3 flow">${order.communityName }-${order.buildNumber }-${order.buildUnit}-${order.buildRoom}-${order.customerName}</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">实际开工日期：</span>
							<p class="font28 col_3 flow">
							<fmt:formatDate value="${order.actualStartDate}" pattern="yyyy-MM-dd" />
							</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">实际竣工日期：</span>
							<p class="font28 col_3 flow">
							<fmt:formatDate value="${order.actualEndDate}" pattern="yyyy-MM-dd" />
							</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">合 同 工 期 ：</span>
							<p class="font28 col_3 flow">${order.contractDays }天</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">合 同 面 积 ：</span>
							<p class="font28 col_3 flow">${order.contractArea}平米</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">订 单 状 态 ：</span>
							<p class="font28 col_3 flow">${order.orderStatus }</p>
						</li>
					</ul>
				</a>
				<a class="one_btn" href="${ctx }/app/pqc/myOrder/orderDetail.php?orderId=${order.orderId}">详情</a>
			</div>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	
	<script type="text/javascript">
	
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
	$(function(){
		$(".search-btn").bind("click",ajaxSearch);
		
	});
function ajaxSearch(){
	
	var searchContent =$('.search-box').val(); 
	
	var html = '';
	
	$(".pad_top11").html('');
	
	html+='<div class="mar_btm14 font0 search-area"><input class="search-box" type="text" placeholder=" 小区名称、客户姓名"><a class="search-btn" href="javascript:;"></a></div>';
	
	$.ajax({
		url: "${ctx}/app/pqc/myOrder/ajaxSearch.php?searchContent="+searchContent,
		type : "get",
		success:function(data){
			for(var v = 0;v<data.length;v++){
				html+='<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix"><a href="javascript:;" class="arrow_rgt pad_left3"><ul class="pad_top3">'
				html+='<li class="mar_btm24 clearfix"><span class="col_grey font28 flo_left">顾　　　　客：</span><p class="font28 col_3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'-'+data[v].customerName+'</p></li>'
				if(data[v].actualStartDate==undefined){
					
					html+='<li class="mar_btm24 clearfix"><span class="col_grey font28 flo_left">实际开工日期：</span><p class="font28 col_3 flow"></p></li>'
					
				}else{
					html+='<li class="mar_btm24 clearfix"><span class="col_grey font28 flo_left">实际开工日期：</span><p class="font28 col_3 flow">'+format(data[v].actualStartDate,"yyyy-MM-dd")+'</p></li>'
					
				}
				if(data[v].actualEndDate==undefined){
					
					html+='<li class="mar_btm24 clearfix"><span class="col_grey font28 flo_left">实际开工日期：</span><p class="font28 col_3 flow"></p></li>'
					
				}else{
					html+='<li class="mar_btm24 clearfix"><span class="col_grey font28 flo_left">实际开工日期：</span><p class="font28 col_3 flow">'+format(data[v].actualEndDate,"yyyy-MM-dd")+'</p></li>'
					
				}
				html+='<li class="mar_btm24 clearfix"><span class="col_grey font28 flo_left">合同工期：</span><p class="font28 col_3 flow">'+data[v].contractDays+'天</p></li>'
				html+='<li class="mar_btm24 clearfix"><span class="col_grey font28 flo_left">合同面积：</span><p class="font28 col_3 flow">'+data[v].contractArea+'平米</p></li>'
				html+='<li class="mar_btm24 clearfix"><span class="col_grey font28 flo_left">订单状态：</span><p class="font28 col_3 flow">'+data[v].orderStatus+'</p></li>'
				
			html+='</ul></a><a class="one_btn" href="${ctx }/app/pqc/myOrder/orderDetail.php?orderId='+data[v].orderId+'">详情</a></div>';		
				
				
			}
			
			$(".pad_top11").html(html);
			$('.search-box').val(searchContent); 
			$(".search-btn").bind("click",ajaxSearch);
		}
		
		
	})

	
	
	
}
	
	
	</script>
</body>
</html>