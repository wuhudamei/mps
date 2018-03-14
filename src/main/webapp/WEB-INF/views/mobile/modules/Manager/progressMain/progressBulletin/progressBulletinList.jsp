<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>进度通报</title>
	
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/project_build.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/progress.css"/>
</head>

<body>
	<div class="g-project_build">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/progressList"></a>
			<h2 class="title">进度通报</h2>
		</header><!-- /header -->
		<section class="project_build_list">
		<c:forEach items="${progressBulletinList}" var="p" varStatus="index">
			<ul class="mar shadow">
				<li class="clearfix">
					<span>顾客信息：</span>
					<p>${p.communityName }-${p.buildNumber }-${p.buildUnit }-${p.buildRoom }-${p.customerName }</p>
				</li>
				<li class="clearfix">
					<span>实际开工日期：</span>
					<input type="hidden" value="${p.actualStartDate }" id="actualStartDate${index.index }"/>
					<input type="hidden" value="${p.nodePlanId}" id="nodePlanId"/>
					<p><fmt:formatDate value="${p.actualStartDate }" type="date"/></p>
				</li>
				<li class="clearfix">
					<span>合同工期：</span>
					<p>${p.contractTime }天</p>
				</li>
				<li class="clearfix">
					<span>当前进度：</span>
					<p class="col_blue">${p.nodePlanNodeName }</p>
				</li>
				<c:if test="${p.nodeIndexOrderId != null }">
				<c:if test="${p.nodeIndexIndex != null }">
					<div class="btns pro_btns clearfix" style="margin-top:.3rem;">
						<a href="JavaScript:void(0)" class="btn" onclick="checkActualStartDate(${p.id },${index.index })">进度通报</a>
					</div>
				</c:if>
				</c:if>
			</ul>
		</c:forEach>
		</section>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript">
		function checkActualStartDate(orderId,index){
			if(orderId){
				var actualStartDate = $("#actualStartDate"+index).val();
				//alert("actualStartDate=="+nodePlanId);
				var url = "${ctx }/app/manager/progressCondition?orderId="+orderId;
				if(actualStartDate){
					window.location.href = url;//action跳转
				}else{
					alert("请先确认开工后，再通报进度!");
					return false;
				}
			}
		}
	</script>
</body>
</html>