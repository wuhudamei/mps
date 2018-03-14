<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>施工变更</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newCommon/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/mask.css"/>
</head>
<body>
	<div class="g-review">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/changeManagement/index"></a>
			<h2 class="title">施工变更</h2>
		</header>
		<section class="pad_top10 mar_btm50">
		
		<c:forEach items="${orderList }" var="order">
		
			<ul class="clearfix shadow bg_f radius1 mar_left2 mar_rgt2 mar_btm2 pad_left2 pad_rgt2 font0 has_two<c:if test="${order.isScrap eq '1'}"> abandon</c:if>">
				<li class="line_hgt clearfix">
					<span class="font28 col_6 flo_left">顾客信息：</span>
					<p class="font28 col_3 flow">${order.xiaoqu }-${order.lou }-${order.danyuan }-${order.shi }-${order.customerName}</p>
				</li>
				<li class="line_hgt clearfix">
					<span class="font28 col_6 flo_left">合同开工：</span>
					<p class="font28 col_3 flow">  <fmt:formatDate value="${order.contractStartDate }" pattern="yyyy-MM-dd"/>  </p>
				</li>
				<li class="line_hgt clearfix">
					<span class="font28 col_6 flo_left">订单状态：</span>
					<p class="font28 col_3 flow">${order.orderStatus }</p>
				</li>
				<div class="btns clearfix">
					<a href="javascript:void(0)" class="two_btn"  onclick = "applyToChange('${order.orderId}','this','${order.isScrap}','${order.storeId}')">提报变更</a>
				<c:if test="${not empty order.orderCount && order.orderCount !=0}">
					<a href="javascript:void(0)" class="two_btn"  onclick = "changeRecord('${order.orderId}','this','${order.storeId}')">申请记录</a>
				</c:if>	
					
				</div>
			</ul>
			</c:forEach>
			
		</section>
	</div>
		<div class="g-mask undis" id = "tip">
			<div class="alertMask">
				<div class="maskWrapper">
					<p class="col_3 maskTit">通知</p>
					<div class="font28 col_6 maskContent align_ctr">该订单已作废了，不允许提报变更了！</div>
					<div class="maskBtns clearfix">
						<a class="maskBtn font33 col_f" onclick="closewin()">我知道了</a>
					</div>
				</div>
			</div>
		</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script>
		function closewin(){
			$("#tip").hide();
		}
	
	
	//提报变更
		function applyToChange(orderId,obj,data,storeId){
			if(data == '1'){
				$("#tip").show();
			}else{
				$(obj).css("color","#CCC")
				window.location.href="${ctx}/app/manager/changeManagement/applyToChange?orderId="+orderId+"&storeId="+storeId;
			}
		
			
		}
		
	//申请记录	
		function changeRecord(orderId,obj,storeId){
			$(obj).css("color","#CCC")
			window.location.href="${ctx}/app/manager/changeManagement/applyRecord?orderId="+orderId+"&storeId="+storeId;
		
	}
		
	
	
	
		$(document).on('click','.select-value',function(){
			$(this).parent().find('.options').toggle();
		});
		$(document).on('click','.options span',function(){
			$(this).parent().parent().find('.select-value span').html($(this).html());
			$(this).parent().css({'display':'none'});
		});
	</script>
</body>
</html>