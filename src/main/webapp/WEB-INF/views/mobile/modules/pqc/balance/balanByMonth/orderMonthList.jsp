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
	<title>月度结算明细</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/accountList.css"/>
</head>
<body>
<c:set var="now" value="<%=new java.util.Date()%>" />
	<div class="">
		<header>
			<a class="back_btn" href="javascript:;" onclick="history.go(-1)"></a>
			<h2 class="title">月度结算明细</h2>
		</header><!-- /header -->
		<section class="pad_top11">
			<div><a class="font26 month-check" href="javascript:;" id="monthId"><fmt:formatDate type="date" pattern="yyyyMM" 
            value="${now}" /><i class="spread-rgt"></i></a></div>
			<ul>
				<li class="bg_f border_top border_btm font0 list"><span class="font30 col_3 descript">中期提成</span><span class="font30 col_3 numbers" id="mid"><fmt:formatNumber pattern="0.00" value="${balance.midBalanceMoney }"></fmt:formatNumber></span></li>
				<li class="bg_f border_top border_btm font0 list zhong"><span class="font30 col_3 descript">中期远程费提成</span><span class="font30 col_3 numbers" id="midDistanceFee"><fmt:formatNumber pattern="0.00" value="${balance.midDistanceFee }"></fmt:formatNumber></span></li>
				<li class="bg_f border_top border_btm font0 list finish"><span class="font30 col_3 descript">竣工提成</span><span class="font30 col_3 numbers" id="complete"><fmt:formatNumber pattern="0.00" value="${balance.completeBalanceMoney }"></fmt:formatNumber></span></li>
				<li class="bg_f border_top border_btm font0 list jun"><span class="font30 col_3 descript">竣工远程费提成</span><span class="font30 col_3 numbers" id="completeDistanceFee"><fmt:formatNumber pattern="0.00" value="${balance.completeDistanceFee }"></fmt:formatNumber></span></li>
			</ul>
			<div class="font0 tot-area">
				<span class="font26 col_3">当前合计</span>
				<span class="tot-money" id="totalMoney"><fmt:formatNumber pattern="0.00" value="${balance.totalMoney }"></fmt:formatNumber></span>
				<span class="font30 yuan">元</span>
			</div>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/lCalendar_nodate.js"></script>
	<script>
		$(function(){
			 var now = new Date(),
	    		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
	    		start = new Date(now.setDate(now.getDate()+3)),
	    		// start = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');
	    		end = new Date(now.setFullYear(now.getFullYear()+5)),
	    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');
	    	var lcalendar = start+','+end;
	    	// 将时间限制加到input标签上。
	    	$('.month-check').attr('data-lcalendar', lcalendar);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '.month-check',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
		}());
		
function queryByMonth(quertMonth,voTime){
			
			$("#monthId").text(quertMonth)
	 	$.ajax({
				url:"${ctx}/app/pqc/balance/query_by_month.php?queryMonth="+quertMonth,
				success:function(data){
					$("#mid").text(data.midBalanceMoney);
					$("#complete").text(data.completeBalanceMoney);
					$("#midDistanceFee").text(data.midDistanceFee);
					$("#completeDistanceFee").text(data.completeDistanceFee);
					$("#totalMoney").text(data.totalMoney);
					
					
					
				}		
				
				
				
				
			})
			
		}	
		
	</script>
</body>
</html>