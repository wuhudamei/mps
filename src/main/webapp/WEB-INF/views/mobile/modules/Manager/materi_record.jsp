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
	<title>收货记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/intent.css"/>
</head>
<body>
	<div class="g-sign_list">
		<header class="header">
			<a class="back_btn" href="${ctx}/app/manager/meterialManagementList"></a>
			<h2 class="title">收货记录</h2>
		</header><!-- /header -->
		<nav>
			<a style="width: 25%;" class="nav" href="javascript:void(0)"><span class="active">辅料</span></a>
			<a style="width: 25%;" class="nav" href="javascript:void(0)"><span>墙地砖</span></a>
			<a style="width: 25%;" class="nav" href="javascript:void(0)"><span>开关面板</span></a>
			<a style="width: 25%;" class="nav" href="javascript:void(0)"><span>沙子水泥</span></a> 
		</nav>
		<section class="sign_list materi_list">
			<c:forEach items = "${auxiBills }" var = "bill">
				<div class="clearfix shadow">
					<ul>
						<li class="clearfix">
							<span>收货单编号：</span>
							<p class="">${bill.purchaseReceiveBillCode }</p>
						</li>
						<li class="clearfix">
							<span>收货日期 ：</span>
							<p class="">
								<fmt:formatDate value="${bill.receiveDate }" pattern="yyyy-MM-dd" />
							</p>
						</li>
						<li class="clearfix">
							<span>顾客信息 ：</span>
							<p><%-- ${bill.customerAddress }- --%>${bill.cummunityName }-${bill.buildNumber }-${bill.builtUnit }-${bill.builtRoom }-${bill.customerName }</p>
						</li>
						<li class="clearfix">
							<span>采购单编号：</span>
							<p class="">${bill.purchaseCode }</p>
						</li>
					</ul>
					<a class="sign_btn" href="${ctx}/app/manager/receivedBillDetail?id=${bill.id}&type=${bill.purchaseType}">详情</a>
				</div>
			</c:forEach>
		</section>
		<section class="sign_list materi_list undis">
			<c:forEach items = "${tileBills }" var = "bill">
				<div class="clearfix shadow">
					<ul>
						<li class="clearfix">
							<span>收货单编号：</span>
							<p class="">${bill.purchaseReceiveBillCode }</p>
						</li>
						<li class="clearfix">
							<span>收货日期 ：</span>
							<p class=""><fmt:formatDate value="${bill.receiveDate }" pattern="yyyy-MM-dd" /></p>
						</li>
						<li class="clearfix">
							<span>顾客信息 ：</span>
							<p><%-- ${bill.customerAddress }- --%>${bill.cummunityName }-${bill.buildNumber }-${bill.builtUnit }-${bill.builtRoom }-${bill.customerName }</p>
						</li>
						<li class="clearfix">
							<span>采购单编号：</span>
							<p class="">${bill.purchaseCode }</p>
						</li>
					</ul>
					<a class="sign_btn" href="${ctx}/app/manager/receivedBillDetail?id=${bill.id}&type=${bill.purchaseType}">详情</a>
				</div>
			</c:forEach>
		</section>
		<section class="sign_list materi_list undis">
			<c:forEach items = "${panelBills }" var = "bill">
				<div class="clearfix shadow">
					<ul>
						<li class="clearfix">
							<span>收货单编号：</span>
							<p class="">${bill.purchaseReceiveBillCode }</p>
						</li>
						<li class="clearfix">
							<span>收货日期 ：</span>
							<p class=""><fmt:formatDate value="${bill.receiveDate }" pattern="yyyy-MM-dd" /></p>
						</li>
						<li class="clearfix">
							<span>顾客信息 ：</span>
							<p><%-- ${bill.customerAddress }- --%>${bill.cummunityName }-${bill.buildNumber }-${bill.builtUnit }-${bill.builtRoom }-${bill.customerName }</p>
						</li>
						<li class="clearfix">
							<span>采购单编号：</span>
							<p class="">${bill.purchaseCode }</p>
						</li>
					</ul>
					<a class="sign_btn" href="${ctx}/app/manager/receivedBillDetail?id=${bill.id}&type=${bill.purchaseType}">详情</a>
				</div>
			</c:forEach>
		</section>
		<section class="sign_list materi_list undis">
			<c:forEach items = "${sandBills }" var = "bill">
				<div class="clearfix shadow">
					<ul>
						<li class="clearfix">
							<span>收货单编号：</span>
							<p class="">${bill.purchaseReceiveBillCode }</p>
						</li>
						<li class="clearfix">
							<span>收货日期 ：</span>
							<p class="">
								<fmt:formatDate value="${bill.receiveDate }" pattern="yyyy-MM-dd" />
							</p>
						</li>
						<li class="clearfix">
							<span>顾客信息 ：</span>
							<p><%-- ${bill.customerAddress }- --%>${bill.cummunityName }-${bill.buildNumber }-${bill.builtUnit }-${bill.builtRoom }-${bill.customerName }</p>
						</li>
						<li class="clearfix">
							<span>采购单编号：</span>
							<p class="">${bill.purchaseCode }</p>
						</li>
					</ul>
					<a class="sign_btn" href="${ctx}/app/manager/receivedBillDetail?id=${bill.id}&type=${bill.purchaseType}">详情</a>
				</div>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script>
		$(function(){
			var index0 = 0;
			$(document).on('click', '.nav', function(){
				index0 = $(this).index();
				$('.nav').find('span').removeClass('active');
				$(this).find('span').addClass('active');
				$('.materi_list').addClass('undis');
				$('.materi_list').eq(index0).removeClass('undis');
			});
		}());
	</script>
</body>
</html>