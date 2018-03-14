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
	<title>复检申请列表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/one_btn.css"/>
</head>
<body>
	<div class="g-recheck_apply">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/qualityControlList"></a>
			<h2 class="title">复检申请列表</h2>
		</header><!-- /header -->
		<section class="lists">
		<c:forEach items="${list }" var="recheck">
			<div class="list shadow">
				<ul class="info">
					<li class="clearfix">
							<span>顾客信息：</span>
						<p>${recheck.orderCustomerAddress }-${recheck.orderCustomerName }</p>
					</li>
					<li class="clearfix">
						<span>实际开工日期：</span>
						<p><fmt:formatDate value="${recheck.actualContractStartDate }" pattern="yyyy-MM-dd"/></p>
					</li>
					
					<li class="clearfix">
						<span>合同工期：</span>
						<p>${recheck.contractDays }天</p>
					</li>
					<li class="clearfix">
						<span>复检类型：</span>
						<p> <c:if test="${recheck.recheckType =='1' }"> 约检</c:if> 
						<c:if test="${recheck.recheckType !='1' }"> 抽检</c:if>
						
						 </p>
					</li>
					<li class="clearfix">
						<span>复检单状态：</span>
						<p class="">
						<c:if test="${recheck.recheckStatus =='1' }"> 创建</c:if> 
						<c:if test="${recheck.recheckStatus =='3' }"> 复检不合格</c:if> 
						
						</p>
					</li>
				</ul>
				<a class="btn" id="applyRecheck" onclick="apply('${recheck.recheckId}')">申请复检</a>
			</div>
			
			</c:forEach>
			
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
	<script type="text/javascript">
	
	
	function apply(recheckId){
		$("#applyRecheck").unbind("onclick");
		$("#applyRecheck").css("color","#CCC");
		window.location.href="${ctx}/app/manager/recheck/toRecheckCheckItemList?recheckId="+recheckId;
		
		
		
		
	}
	
	</script>
	

</html>