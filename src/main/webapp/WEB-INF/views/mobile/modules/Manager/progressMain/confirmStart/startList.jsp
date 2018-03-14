<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>确认开工</title>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/ensure.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
</head>

<body>
	<div class="g-ensure">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/progressList"></a>
			<h2 class="title">确认开工</h2>
		</header>
		<section class="ensure_list">
			<c:forEach items="${orderList}" var="orderList">
				<ul class="shadow">
				<li class="clearfix">
					<span>顾客信息：</span>
					<p>${orderList.communityName }-${orderList.buildNumber }-${orderList.buildUnit }-${orderList.buildRoom }-${orderList.customerName }</p>
				</li>
				<li class="clearfix">
					<span>合同开工：</span>
					<p><fmt:formatDate type="date" value="${orderList.contractStartDate }"/></p>
				</li>
				<li class="clearfix">
					<span>合同工期：</span>
					<p>${orderList.contractTime }天</p>
				</li>
				<li class="clearfix">
					<span>订单状态：</span>
					<p class="col_blue">${fns:getDictLabel(orderList.orderStatusNumber, 'order_status', '')}</p><!-- taskpackage_status -->
				</li>
				<%-- <c:if test="${not empty orderList.discloseId or orderList.projectMode !=1 or orderList.projectMode ==4}  ">
				<a class="ensure_btn" href="${ctx }/app/manager/confirmStartDetail?orderId=${orderList.id}">开工</a>
				</c:if>
				<c:if test="${empty orderList.discloseId and orderList.projectMode ==1 or orderList.projectMode == 4}">
				<a class="ensure_btn" href="#" onclick="showDialog()">开工</a>
				</c:if> --%>
				<c:if test="${orderList.projectMode == '1' || orderList.projectMode == '4'}">
				   <c:if test="${empty orderList.discloseId}">
				      <a class="ensure_btn" href="#" onclick="showDialog()">开工</a>
				   </c:if>
				   <c:if test="${not empty orderList.discloseId}">
				      <a class="ensure_btn" href="${ctx }/app/manager/confirmStartDetail?orderId=${orderList.id}">开工</a>
				   </c:if>
				</c:if>
				<c:if test="${orderList.projectMode == 2}">
				  <a class="ensure_btn" href="${ctx }/app/manager/confirmStartDetail?orderId=${orderList.id}">开工</a>
				</c:if>
			</ul>
			</c:forEach>
		</section>
	</div>
</body>
<script type="text/javascript">
function showDialog(){
	globalUtil.fn.alert("请先操作现场交底后，再确认开工",3.0);
	
	
	
	
}


</script>
</html>