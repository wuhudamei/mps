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
	<title>申请墙地砖</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil2.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/auxiliary_apply.css"/>
</head>

<script type="text/javascript">
	function applyWallAndFloor(){
		 $('#alert').show();
	}
	function queren(){
		 $('#alert').hide();
	}
	function queren2(){
		 $('#timeAlert').hide();
	}
	function queren3(){
		 $('#isRead').hide();
	}

</script>
<body>
	<div class="g-sign">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/meterialManagementList"></a>
			<h2 class="title">申请墙地砖</h2>
		</header><!-- /header -->
		<section class="sign_list">
			<c:forEach items="${order }" var="order">
				<ul class="clearfix shadow">
					<li class="clearfix">
						<span>顾客信息：</span>
						<p>${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
					</li>
					<li class="clearfix">
						<span>合同开工：</span>
						<p><fmt:formatDate value="${order.contractStartDate }" pattern="yyyy-MM-dd"/></p>
					</li>
					<li class="clearfix">
						<span>实际开工：</span>
						<p><fmt:formatDate value="${order.actualStartDate }" pattern="yyyy-MM-dd"/></p>
					</li>
					<li class="clearfix">
						<span>合同工期：</span>
						<p>${order.contractTime }</p>
					</li>
					<li class="clearfix">
						<span>订单状态：</span>
						<p class="">${order.orderStatusDescription }</p>
					</li>
					<div class="btns clearfix">
						<c:if test="${order.projectMode=='1' }">
							<c:if test="${order.orderStatusNumber>=200||order.allCount>0 }">
								<a href="${ctx}/app/manager/wallAndFloorBrick?id=${order.id}" class="btn">墙地砖申请</a>
							</c:if>
							<c:if test="${order.orderStatusNumber<200&&order.allCount==0}">
								<a class="btn" href="javascript:void(0)" onclick="applyWallAndFloor()">墙地砖申请</a>
							</c:if>
						</c:if>
						<c:if test="${order.projectMode!='1' }">
							<a href="${ctx}/app/manager/wallAndFloorBrick?id=${order.id}" class="btn">墙地砖申请</a>
						</c:if>
						<a href="${ctx}/app/manager/wallAndFloorBrickRecord?id=${order.id}" class="btn">申请记录</a>
					</div>
				</ul>
			</c:forEach>
		</section>
		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="alert">
			<div id="g-done_ask">
				<p class="first">请先到进度管理中点击确认开工后,才可以申请墙地砖</p>
				<p class="second">
					<a href="#" onclick="queren()">确认</a>
				</p>
			</div> 
		</div>	
		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="timeAlert">
			<div id="g-done_ask">
				<p class="first">同一个订单两次墙地砖申请操作时间必须间隔5分钟，请过5分钟后再申请</p>
				<p class="second">
					
					<a href="#" onclick="queren2()">确认</a>
				</p>
			</div> 
		</div>
		 <div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="isRead">
			<div id="g-done_ask">
				<p class="first">您有未阅读的墙地砖采购单，请到【申请记录】去查阅后再申请墙地砖</p>
				<p class="second">
					
					<a href="#" onclick="queren3()">确认</a>
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
				
				var isRead = "${isRead}";
				if(""!=isRead &&"0"==isRead && null!=isRead){
					$("#isRead").show();
				}
				
				
			})
	</script>
</body>
</html>