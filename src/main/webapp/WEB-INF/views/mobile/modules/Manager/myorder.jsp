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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/myorder.css"/>
</head>
<script type="text/javascript">
	function subForm(value,id,name){
		
		$.ajax({
			url : "${ctx}/app/manager/appOrderCondition?"+name+"="+value ,
			type : "get",
			dataType : "json" ,
			success:function (data){
				$("#section").remove();
				$("#all").append("<section class='order_list' id='section'>");
				
				jQuery.each(data, function(i,appOrderList){ 
					
					var customerInfo = appOrderList.communityName+"-"+appOrderList.buildNumber+"-"+appOrderList.buildUnit+"-"+appOrderList.buildRoom+"-"+appOrderList.customerName;
				
					var actualStartDate =format(appOrderList.actualStartDate,"yyyy-MM-dd");
					
					var actualEndDate = format(appOrderList.actualEndDate,"yyyy-MM-dd");
					
					var contractTime = appOrderList.contractTime;
					
					var contractArea = appOrderList.contractArea;
					
					var orderStatusDescription = appOrderList.orderStatusDescription;
					
					$("#section").append("<a href= 	'${ctx}/app/manager/appOrderDetails?id="+appOrderList.id+"' class='order_section clearfix'><ul><li class='clearfix'><span>顾客信息：</span><p>"+customerInfo+"</p></li><li class= 	'clearfix'><span>实际开工：</span><p>"+actualStartDate+"</p></li><li class='clearfix'><span>实际竣工：</span><p>"+actualEndDate+"</p></li><li class='clearfix'><span>合同工期：</span><p>"+contractTime+"</p></li><li class='clearfix'><span>合同面积：</span><p>"+contractArea+"</p></li><li class='clearfix'><span>订单状态：</span><p>"+orderStatusDescription+"</p></li></ul></a>");
	            
				});
				
			}
			 
			 
		 });
	
	}

</script>
<body>
	<div class="myorder" id="all">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/indexMine"></a>
			<h2 class="title">我的订单</h2>
		</header><!-- /header -->
		<%-- <nav class="order_nav clearfix">
			<a class="nav_a" href="javascript:void(0);">
				<span>订单状态</span>
				<img src="${ctxStatic}/mobile/modules/Manager/images/down_btn.png" height="8" width="12" alt="btn">
			</a>
			<a class="nav_a" href="javascript:void(0);">
				<span>智能排序</span>				
				<img src="${ctxStatic}/mobile/modules/Manager/images/down_btn.png" height="8" width="12" alt="btn">
			</a>
			<div class="options undis">
				<c:forEach items="${stateName }" var="state">
			
					<a href="javascript:void(0)" onclick="subForm('${state}','orderStatusDescription','orderStatusDescription')">
					<input type="text" hidden="hidden"  id="orderStatusDescription" />
					${state}</a>
				</c:forEach>
			</div>
			<div class="options undis">
				<a href="javascript:void(0)"  onclick="subForm('a.customer_name','name','orderBy')">
					<input type="text" hidden="hidden"  id="name"/>
					客户姓名</a>
				<a href="javascript:void(0)" onclick="subForm('a.actual_start_date','start','orderBy')">
					<input type="text" hidden="hidden"  id="start"/>
					实际开工日期</a>
				<a href="javascript:void(0)" onclick="subForm('a.actual_end_date','end','orderBy')">
					<input type="text" hidden="hidden"  id="end"/>
					实际竣工日期</a>
			</div>
		</nav> --%>
		<section class="order_list" id="section">
			<c:forEach items="${order }" var="order">
				<div class="lists">
					<a href="${ctx }/app/manager/appOrderDetails?id=${order.id}" class="order_section clearfix shadow">
						<ul>
							<li class="clearfix">
								<span>顾客信息：</span>
								<p>${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
							</li>
							<li class="clearfix">
								<span>实际开工：</span>
								<p><fmt:formatDate value="${order.actualStartDate }" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="clearfix">
								<span>实际竣工：</span>
								<p><fmt:formatDate value="${order.actualEndDate }" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="clearfix">
								<span>合同工期：</span>
								<p>${order.contractTime }</p>
							</li>
							<li class="clearfix">
								<span>合同面积：</span>
								<p>${order.contractArea }</p>
							</li>
							<li class="clearfix">
								<span>订单状态：</span>
								<p class="col_blue">${order.orderStatusDescription }</p>
							</li>
						</ul>
					</a>
				<%-- 	<a class="draw" href="${ctx }/app/manager/appOrderCadfile?id=${order.id}">图纸</a> --%>
				</div>
			</c:forEach>
			
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/myorder.js"></script>
</body>
</html>