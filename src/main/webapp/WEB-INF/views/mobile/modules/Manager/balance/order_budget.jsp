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
	<title>月度工程结算</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/order_budget.css"/>
	<style>
		.tit_rgt{
			position:absolute;
			top:1.1rem;  
			display: block;
		    width: 2.46rem;
		    height: .54rem;
		    line-height: .54rem;
		    text-align: center;
		    background: #a88daa;
		    border-radius: .27rem;
		    color: #fff;
		    font-size: .26rem;
		    margin-left:calc( 50% - 1.23rem );
	    }
	    .budget_list {
		    position: absolute;
		    top: 1.84rem;
		    width: 100%;
		}
	</style>
</head>
<body>
	<div class="">
	<c:set var="now" value="<%=new java.util.Date()%>" />
		<header>
			<a class="back_btn" href="javascript:;" onclick="history.go(-1)"></a>
			<h2 class="title">月度工程结算</h2>
			
		</header><!-- /header -->
		<a class="tit_rgt" href="javascript:;"><fmt:formatDate type="date" pattern="yyyy-MM" 
            value="${now}" /></a>
		<ul class="budget_list bg_f font0 shadow mar_btm50" >
		<li class="border_btm clearfix">
				<span class="left font30 col_3">中期提成</span>
				<span class="num font30 col_red" id="mid"><fmt:formatNumber pattern="0.00" value="${balance.midBalanceMoney }"></fmt:formatNumber>  </span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">竣工提成</span>
				<span class="num font30 col_9" id="complete"><fmt:formatNumber pattern="0.00" value="${balance.completeBalanceMoney }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">自主支配项</span>
				<span class="num font30 col_red" id="free"><fmt:formatNumber pattern="0.00" value="${balance.freePayMoney }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">标化辅料</span>
				<span class="num font30 col_red" id="biaohua"><fmt:formatNumber pattern="0.00" value="${balance.materialsStandardAmount }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">中期质检罚款</span>
				<span class="num font30 col_red" id="midFakuan"><fmt:formatNumber pattern="0.00" value="${balance.midFineMoney }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">竣工质检罚款</span>
				<span class="num font30 col_red" id="comFakuan"><fmt:formatNumber pattern="0.00" value="${balance.comleteFineMoney }"></fmt:formatNumber></span>
			</li>
			<!-- <li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">中期奖励</span>
				<span class="num font30 col_red">XXX</span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">竣工奖励</span>
				<span class="num font30 col_red">XXX</span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">中期扣款</span>
				<span class="num font30 col_red">XXX</span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">竣工扣款</span>
				<span class="num font30 col_red">XXX</span>
			</li> -->
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">自采材料</span>
				<span class="num font30 col_red" id="materialSelfbuy"><fmt:formatNumber pattern="0.00" value="${balance.materialSelfbuyReimburseAmount}"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">质保金</span>
				<span class="num font30 col_red" id="zhibaojin"><fmt:formatNumber pattern="0.00" value="${balance.guaranteMoney }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">中期材料结算金额</span>
				<span class="num font30 col_red" id="midwayAuxiliary"><fmt:formatNumber pattern="0.00" value="${balance.midwayAuxiliaryMaterialsDeductAmount }"></fmt:formatNumber></span>
			</li>
			<li class="border_btm bg_f clearfix">
				<span class="left font30 col_3">>竣工材料结算金额</span>
				<span class="num font30 col_red" id="completeAuxiliary"><fmt:formatNumber pattern="0.00" value="${balance.completeAuxiliaryMaterialsDeductAmount }"></fmt:formatNumber></span>
			</li>
		</ul>
		<footer class="bg_f">
			<span class="foot_des font28 col_6">当前合计：<em class="col_red" id="totalMoney">¥<fmt:formatNumber pattern="0.00" value="${balance.totalMoney}"></fmt:formatNumber></em></span>
		</footer>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar_nodate2.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
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
		    	$('.tit_rgt').attr('data-lcalendar', lcalendar);
				var calendar = new lCalendar();
				calendar.init({
				    'trigger': '.tit_rgt',//标签id
				    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
				});
		 
			
			
		}());
		
		
		function queryByMonth(quertMonth,voTime){
			
			$(".tit_rgt").text(voTime)
	 	$.ajax({
				url:"${ctx}/app/manager/balancebymonth/query_by_month?queryMonth="+quertMonth,
				success:function(data){
					$("#mid").text(data.midBalanceMoney);
					$("#complete").text(data.completeBalanceMoney);
					$("#free").text(data.freePayMoney);
					$("#biaohua").text(data.materialsStandardAmount);
					$("#midFakuan").text(data.midFineMoney);
					$("#comFakuan").text(data.comleteFineMoney);
					$("#zhibaojin").text(data.guaranteMoney);
					$("#materialSelfbuy").text(data.materialSelfbuyReimburseAmount);
					$("#midwayAuxiliary").text(data.midwayAuxiliaryMaterialsDeductAmount);
					$("#completeAuxiliary").text(data.completeAuxiliaryMaterialsDeductAmount);
					$("#totalMoney").text(data.totalMoney);
					
					
					
				}		
				
				
				
				
			})
			
		}
		
	</script>
</body>
</html>


