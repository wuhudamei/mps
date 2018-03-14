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
	<title>申请厂家复尺</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil2.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/intent.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/twoBtn.css"/>
</head>
<body>
	<div class="g-sign_list">
		<header class="header">
			<a class="back_btn" href="${ctx }/app/manager/projectInstallMenu"></a>
			<h2 class="title">申请厂家复尺</h2>
		</header><!-- /header -->
		<section class="sign_list">
			<c:forEach items="${checksizeOrder }" var="checksizeOrder">
				<div class="clearfix shadow">
					<ul>
						<li class="clearfix">
							<span>顾客信息：</span>
							<p>${checksizeOrder.info }</p>
						</li>
						<li class="clearfix">
							<span>合同开工日期：</span>
							<p class=""><fmt:formatDate type="date" value="${checksizeOrder.contractStartDate }"/></p>
						</li>
						<li class="clearfix">
							<span>合同竣工日期：</span>
							<p class=""><fmt:formatDate type="date" value="${checksizeOrder.contractEndDate }"/></p>
						</li>
						<li class="clearfix">
							<span>合同工期：</span>
							<p>${checksizeOrder.contractTime }天</p>
						</li>
						<li class="clearfix">
							<span>订单状态：</span>
							<p class="">${checksizeOrder.orderStatusDescription }</p>
						</li>
					</ul>
					<div class="btns clearfix">
						<a href="${ctx}/app/manager/checksizeDetails?id=${checksizeOrder.id}" class="btn">申请厂家复尺</a>
						<a href="${ctx}/app/manager/checksizeRecord?id=${checksizeOrder.id}" class="btn">复尺记录</a>
					</div>
				</div>
			</c:forEach>
			
		</section>
		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="timeAlert">
			<div id="g-done_ask">
				<p class="first">同一个订单两次厂家复尺申请操作时间必须间隔5分钟，请过5分钟后再申请</p>
				<p class="second">
					
					<a href="#" onclick="queren()">确认</a>
				</p>
			</div> 
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/global.js"></script>
	<script type="text/javascript">
		$(function(){
				var time = "${timeForbidden}";
				if(""!=time &&"1"==time && null!=time){
					$("#timeAlert").show();
				}
			})
			
		function queren(){
			 $('#timeAlert').hide();
		}
	</script>
</body>
</html>