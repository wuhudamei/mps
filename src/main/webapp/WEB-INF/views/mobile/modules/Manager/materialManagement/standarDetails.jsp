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
	<title>标化材料申请记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/standarDetails.css"/>

</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/applyStandardMaterial/record?orderId=${orderId}" onclick="myhistory.back()"></a>
			<h2 class="title">标化材料申请记录</h2>
		</header><!-- /header -->
		<section id="toptop" class="pad_top88">
		
			<div class="sec font0 mar_btm24 bg_f clearfix">
					<ul class="pad_top3 pad_left3 pad_rgt3">
						<li class="mar_btm30 clearfix">
							<span class="col_grey font30 flo_left">顾客信息：</span>
							<p class="font30 col_3 flow">${customerInfo.communityName }-${customerInfo.buildNumber }-${customerInfo.buildUnit }-${customerInfo.buildRoom }-${customerInfo.customerName }</p>
						</li>
						<li class="mar_btm30 clearfix">
							<span class="col_grey font30 flo_left">申请单号：</span>
							
							<p class="font30 col_3 flow">${applyMaterial.materialsStandardReceiveBillCode }</p>
						</li>
						<li class="mar_btm30 clearfix">
							<span class="col_grey font30 flo_left">申请状态：</span>
							<p class="font30 col_3 flow">
								
							<c:if test="${applyMaterial.status==10 }">
								项目经理已申请
							</c:if>
							<c:if test="${applyMaterial.status==20 }">
								已领取
							</c:if>
							<c:if test="${applyMaterial.status==30 }">
								已废弃
							</c:if>
							</p>
						</li>
						<c:if test="${applyMaterial.status==20 }">
						<li class="mar_btm30 clearfix">
							<span class="col_grey font30 flo_left">
									领取日期：
							</span>
							<p class="font30 col_3 flow">
							<fmt:formatDate value="${applyMaterial.receiveDatetime }" pattern="yyyy-MM-dd"/>
							</p>
						</li>
						</c:if>
						<c:if test="${applyMaterial.status==30 }">
						<li class="mar_btm30 clearfix">
							<span class="col_grey font30 flo_left">
									废弃原因：
							</span>
							<p class="font30 col_3 flow">
								${applyMaterial.abandonReason}
							</p>
						</li>
						</c:if>
						<!-- <li class="mar_btm30 clearfix">
							<span class="col_grey font30 flo_left">废弃原因：</span>
							<p class="font30 col_3 flow">原因原因原因原因原因因原因原因原因因原因原因原因原因原因因原因原因原因因原因原因原因原因原因因原因原因原因因</p>
						</li> -->
					</ul>
			</div>
			<div class="">
				<p class="tit-wrap bg_f font0"><span class="tit-p">材料明细</span></p>
				
				<ul class="metri bg_f">
				<c:forEach items="${applyMaterialDetail }" var="list">
					<li class="pad_left3 pad_top26 pad_btm26 bor_dotted clearfix">
						<p class="font30 col_3 line_hgt16 pad_btm26">【${list.materialsType }】${list.materialsName }</p>
						<p class="font28 pad_left88 pad_rgt3 col_6 flo_rgt"><span class="col_red4">${list.materialsPrice }</span>元/${list.materialsUnit }</p>
						<span class="font28 col_6 flo_rgt">X${list.receiveNumber }</span>
					</li>
					</c:forEach>
				</ul>
				
			</div>
		</section>
		<a class="gotToTop" href="#toptop"></a>
		<footer class="font28 col_6 pad_left3">共${totalSize }种商品，合计<span class="col_red5">${applyMaterial.receiveBillAmount }</span>元</footer>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
		<script type="text/javascript">
	 $(function(){
		 updateView();
	 })
	 function updateView(){
		var billId=${applyMaterial.id}; 
		console.log(billId);
		  $.ajax({
		    url: "${ctx}/app/manager/applyStandardMaterial/updateView",    //请求的url地址
		    dataType: "json",   //返回格式为json
		    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
		    data: { 
		    	'billId':billId
		    	},    //参数值
		    type: "POST",   //请求方式
		})  
	}	
	</script>
</body>
</html>