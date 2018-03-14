<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>状态</title>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/reset.css">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/common.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/search.css" />
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/state.css">
</head>

<body>
	<div class="wrap">
		<header>
			<a class="back_btn New_btns" href="${ctx}/app/manager/tradition-manager-settle/settleApply.php?orderId=${entity.orderEntity.orderId}&settleId=${entity.settleId}"></a>
			<h2 class="title">${entity.settleStatusName}</h2>
		</header>
		<section class="pad_top88">
			<div class="state">
				<p>
					<span class="col_6">客户姓名：</span>
					<span class="col_3">${entity.orderEntity.customerName}</span>
				</p>
				<p>
					<span class="col_6">工程地址：</span>
					<span class="col_3">${entity.orderEntity.communityName }-${entity.orderEntity.buildNumber }-${entity.orderEntity.buildUnit }-${entity.orderEntity.buildRoom }</span>
				</p>
				<p>
					<span class="col_6">订单编号：</span>
					<span class="col_3">${entity.orderEntity.orderNumber}</span>
				</p>
			</div>
			<div class="state">
				<p>
					<span class="col_6">项目经理：</span>
					<span class="col_3">${entity.orderEntity.itemManager}</span>
				</p>
				<p>
					<span class="col_6">项目经理电话：</span>
					<span class="col_3">${entity.orderEntity.managerPhone}</span>
				</p>
				<p>
					<span class="col_6">质检：</span>
					<span class="col_3">${entity.orderEntity.orderInspector}</span>
				</p>
				<p>
					<span class="col_6">质检电话：</span>
					<span class="col_3">${entity.orderEntity.orderInspectorPhone}</span>
				</p>
			</div>
			<div class="state">
				<p>
					<span class="col_6">结算类型：</span>
					<span class="col_3">${entity.settleNodeName}</span>
				</p>
				<p>
					<span class="col_6">申请时间：</span>
					<span class="col_3"><fmt:formatDate value="${entity.applyTime}" pattern="yyyy-MM-dd hh:mm:ss"/></span>
				</p>
				<p>
					<span class="col_6">描述：</span>
					<span class="col_3">${entity.orderEntity.settleApplyRemarks}</span>
				</p>

				<c:if test="${entity.settleStatus>1}">
				<p>
					<span class="col_6">操作人：</span>
					<span class="col_3">

							<c:if test="${empty entity.checkManName}">

								系统管理员

							</c:if>

					<c:if test="${not empty entity.checkManName}">

						${ entity.checkManName}

					</c:if>


					</span>
				</p>
				<c:if test="${entity.settleStatus==3}">
				<p>
					<span class="col_6">回复内容：</span>
					<span class="col_3">${entity.checkReply}</span>
				</p>

				</c:if>


				<c:if test="${entity.settleStatus==4}">
				<p>
					<span class="col_6">结算时间：</span>
					<span class="col_3"><fmt:formatDate value="${entity.checkTime}" pattern="yyyy-MM-dd"/></span>
				</p>
				<p>
					<span class="col_6">结算金额：</span>
					<span class="col_red"><fmt:formatNumber value="${entity.settleAmount}" pattern="0.00" /></span>
				</p>
				</c:if>
				</c:if>
			</div>
			<div class="Enclosure">
				<p>附件</p>
				<div class="pic">

					<c:forEach items="${picList}" var="pic">
						<c:if test="${not empty pic.picUrl}">
						<img src="${picPrefix}${pic.picUrl}" alt="结算图片">
						</c:if>
						<c:if test="${empty pic.picUrl}">
							<p>
								<span class="col_6">项目经理没有上传图片</span>

							</p>
						</c:if>

					</c:forEach>

				</div>
			</div>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>

</html>