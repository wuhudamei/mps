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
	<title>质检罚款详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/publishDetails.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn"  onclick="history.go(-1)"></a>
			<h2 class="title">质检罚款详情</h2>
		</header><!-- /header -->
		<section class="pad_top88 font0">
			<c:forEach items="${reports }" var="report">
				<div class="box bg_f">
					<div class="inspector-info pad_left3 pad_rgt3 mar_top24 flow">
						<span class="inspector font32 col_3">质检员-${report.inspector }</span>
						<span class="timestamp font32 col_3 flo_rgt">
							<fmt:formatDate value="${report.checkDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</span>
					</div>
					<p class="check-content font30 col_6">检查内容：
						<c:if test="${report.qcBillType == '1' }">
							【约检】
						</c:if>
						<c:if test="${report.qcBillType == '2' }">
							【抽检】
						</c:if>
						${report.qcCheckNodeName }
					</p>
					<ul class="pad_btm24">
						<c:forEach items ="${report.checkItemList }" var="checkItem">
							<li class="table">
								<p class="font30 col_3 publish-item">${ checkItem.qcCheckItemName}</p>
								<div class="cell-line bg_f6 flow bor-btm-blue">
									<span class="cell-left font30 col_6">违规形式</span>
									<c:forEach items="${checkItem.checkBreakList}" var="checkBreak">
										<span class="cell-rgt font30 col_6">${checkBreak.breakDescribe }</span>
									</c:forEach>
								</div>
								<div class="cell-line bg_f6 flow">
									<span class="cell-left font30 col_6">处理方式</span>
									<!-- <span class="cell-rgt font30 col_6">扣分/罚款：项目经理50元，工人张三50元项目经理50元，工人张三50元</span> -->
									<span class="cell-rgt font30 col_6">
										<c:if test="${ checkItem.isWarned =='1'}">
											警告，
										</c:if>
										<c:if test="${ checkItem.isLocaleRepaire =='1'}">
											现场整改，
										</c:if>
										<c:if test="${ checkItem.isLimitDateRepaire =='1'}">
											限期整改，整改期限：<fmt:formatDate value="${checkItem.limitDate}" pattern="yyyy-MM-dd"/> ，
										</c:if>
										扣分/罚款：
										<c:if test="${ checkItem.punishMoneyAmountReal != null}">
											项目经理${ checkItem.pmPunishScore}分/${ checkItem.punishMoneyAmountReal}元,
										</c:if>
										<c:if test="${ checkItem.workerPunishAmount != null}">
											工人${ checkItem.workerPunishScore}分/${ checkItem.workerPunishAmount}元,
										</c:if>
										<c:if test="${ checkItem.qcPunishAmount != null}">
											质检员${ checkItem.qcPunishScore}分/${ checkItem.qcPunishAmount}元
										</c:if>
									</span>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:forEach>
			<!-- <div class="box bg_f">
				<div class="inspector-info pad_left3 pad_rgt3 mar_top24 flow">
					<span class="inspector font32 col_3">质检员-叶伟作</span>
					<span class="timestamp font32 col_3 flo_rgt">2017-01-12  07:47:20</span>
				</div>
				<p class="check-content font30 col_6">检查内容：【约检】涂饰工程及基装验收</p>
				<ul class="pad_btm24">
					<li class="table">
						<p class="font30 col_3 publish-item">19临时取电</p>
						<div class="cell-line bg_f6 flow bor-btm-blue">
							<span class="cell-left font30 col_6">违规形式</span>
							<span class="cell-rgt font30 col_6">未接临时配电箱</span>
						</div>
						<div class="cell-line bg_f6 flow">
							<span class="cell-left font30 col_6">处理方式</span>
							<span class="cell-rgt font30 col_6">罚款-项目经理50元，工人张三50元项目经理50元，工人张三50元</span>
						</div>
					</li>
					<li class="table">
						<p class="font30 col_3 publish-item">21喝酒</p>
						<div class="cell-line bg_f6 flow bor-btm-blue">
							<span class="cell-left font30 col_6">违规形式</span>
							<span class="cell-rgt font30 col_6">工作时间喝酒</span>
						</div>
						<div class="cell-line bg_f6 flow">
							<span class="cell-left font30 col_6">处理方式</span>
							<span class="cell-rgt font30 col_6">罚款-项目经理50元，工人张三50元</span>
						</div>
					</li>
				</ul>
			</div> -->
			<!-- <div class="box bg_f">
				<div class="inspector-info pad_left3 pad_rgt3 mar_top24 flow">
					<span class="inspector font32 col_3">质检员-叶伟作</span>
					<span class="timestamp font32 col_3 flo_rgt">2017-01-12  07:47:20</span>
				</div>
				<p class="check-content font30 col_6">检查内容：【约检】涂饰工程及基装验收</p>
				<ul class="pad_btm24">
					<li class="table">
						<p class="font30 col_3 publish-item">19临时取电</p>
						<div class="cell-line bg_f6 flow bor-btm-blue">
							<span class="cell-left font30 col_6">违规形式</span>
							<span class="cell-rgt font30 col_6">未接临时配电箱</span>
						</div>
						<div class="cell-line bg_f6 flow">
							<span class="cell-left font30 col_6">处理方式</span>
							<span class="cell-rgt font30 col_6">罚款-项目经理50元，工人张三50元项目经理50元，工人张三50元</span>
						</div>
					</li>
					<li class="table">
						<p class="font30 col_3 publish-item">21喝酒</p>
						<div class="cell-line bg_f6 flow bor-btm-blue">
							<span class="cell-left font30 col_6">违规形式</span>
							<span class="cell-rgt font30 col_6">工作时间喝酒</span>
						</div>
						<div class="cell-line bg_f6 flow">
							<span class="cell-left font30 col_6">处理方式</span>
							<span class="cell-rgt font30 col_6">罚款-项目经理50元，工人张三50元</span>
						</div>
					</li>
				</ul>
			</div> -->
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>