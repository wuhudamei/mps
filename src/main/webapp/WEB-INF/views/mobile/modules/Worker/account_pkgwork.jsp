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
	<title>结算单详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/account.css"/>
</head>
<body>
	<div class="g-account">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="${ctx }/app/worker/salaryList"></a>
			<h2 class="title">结算单详情</h2>
		</header><!-- /header -->
		<section class="account_section">
			<h3>结算单详情</h3>
			<ul class="front shadow">
				<div class="normal">
					<li class="clearfix">
						<span>任务包名称：</span>
						<p>${workTaskPackage.packageName }</p>
					</li>
					<li class="clearfix">
						<span>结算单状态：</span>
						<p>${fns:getDictLabel(bizOrderTaskpackageSettlement.status, 'settlement_status', '')}</p>
					</li>
					<li class="clearfix">
						<span>验 收 日 期：</span>
						<p>
							<fmt:formatDate value="${bizOrderTaskpackageSettlement.checkDate }" pattern="yyyy-MM-dd" />
						</p>
					</li>
				</div>
				<a class="money" href="${ctx}/app/worker/accountDetails?id=${workTaskPackage.id }&settleStyle=${settleStyle}">
					<li class="clearfix">
						<span>人工费预算总金额：</span>
						<p>${budgetTotalMoney}元</p>
					</li>
					<li class="clearfix">
						<span>人工费结算总金额：</span>
						<p>${settleTotalMoney}元</p>
					</li>
					<span class="detail">查看详情</span>
				</a>
			</ul>
			<a class="pics shadow" href="${ctx}/app/worker/seePhoto?id=${workTaskPackage.id }">查看完工照片</a>
			<h3>工期</h3>
			<ul class="front shadow">
				<div class="normal">
					<li class="clearfix">
						<span>要求工期：</span>
						<p>
							<fmt:formatDate value="${workTaskPackage.planStartdate }" pattern="yyyy-MM-dd" />
							至
							<fmt:formatDate value="${workTaskPackage.planEnddate }" pattern="yyyy-MM-dd" />
						</p>
					</li>
					<li class="clearfix">
						<span>实际工期：</span>
						<p>
							<fmt:formatDate value="${workTaskPackage.actualStartdate }" pattern="yyyy-MM-dd" />
							至
							<fmt:formatDate value="${workTaskPackage.actualEnddate }" pattern="yyyy-MM-dd" />
						</p>
					</li>
				</div>
				<p class="">
					<c:if test="${bizOrderTaskpackageSettlement.isDelay =='1' }">
						<li class="clearfix">
							<span>延期天数：</span>
							<p>${bizOrderTaskpackageSettlement.delayDays }天（罚款${bizOrderTaskpackageSettlement.delayAmerce}元）</p>
						</li>
					</c:if>
					<c:if test="${bizOrderTaskpackageSettlement.isDelay == null || bizOrderTaskpackageSettlement.isDelay =='0' }">
						<li class="clearfix">
							<span>延期天数：</span>
							<p>0天</p>
						</li>
					</c:if>
				</p>
			</ul>
			<h3>管理处罚</h3>
			<ul class="front shadow">
				<p class="">
					<c:if test="${bizOrderTaskpackageSettlement.isManagePunish =='1' }">
						<li class="clearfix">
							<p>罚款${bizOrderTaskpackageSettlement.punishAmerce }元，原因：${bizOrderTaskpackageSettlement.punishReason }</p>
						</li>
					</c:if>
					<c:if test="${bizOrderTaskpackageSettlement.isManagePunish == null || bizOrderTaskpackageSettlement.isManagePunish =='0' }">
						<li class="clearfix">
							<p>无</p>
						</li>
					</c:if>
				</p>
			</ul>
			<h3>公司扣款</h3>
			<ul class="front shadow">
				<p class="">
					<li class="clearfix">
						<p>
						   <c:if test="${bizOrderTaskpackageSettlement.companyDeductAmount == null}">罚款0.0元</c:if>
						   <c:if test="${bizOrderTaskpackageSettlement.companyDeductAmount != null}">罚款${bizOrderTaskpackageSettlement.companyDeductAmount}元，原因：${bizOrderTaskpackageSettlement.companyDeductReason}</c:if>
						</p>
					</li>
				</p>
			</ul>
			<h3>扣款质保金</h3>
			<ul class="front shadow">
				<li class="clearfix">
					<p>质保金扣款${bizOrderTaskpackageSettlement.guaranteeMoneyAmount}元</p>
				</li>
			</ul>
			<c:if test="${not empty rewardAmount}">
				<h3>奖励金额</h3>
				<ul class="front shadow">
					<li class="clearfix">
						<p>奖励金额${rewardAmount}元</p>
					</li>
				</ul>
			</c:if>
			<h3>结算汇总</h3>
			<ul class="table shadow">
				<li class="clearfix">
					结算金额统计
				</li>
				<li class="clearfix">
					<span>人工费结算总金额</span>
					<span>${settleTotalMoney}元</span>
				</li>
				<li class="clearfix">
					<span>延期扣款</span>
					<span>-${bizOrderTaskpackageSettlement.delayAmerce }元</span>
				</li>
				<li class="clearfix">
					<span>管理处罚</span>
					<span>-${bizOrderTaskpackageSettlement.punishAmerce }元</span>
				</li>
				<li class="clearfix">
					<span>质检罚款</span>
					<span>-${bizOrderTaskpackageSettlement.qcPunishMoneyAmount }元</span>
				</li>
				<li class="clearfix">
					<span>公司扣款</span>
					<span>-<c:if test="${bizOrderTaskpackageSettlement.companyDeductAmount == null}">0.0</c:if><c:if test="${bizOrderTaskpackageSettlement.companyDeductAmount != null}">${bizOrderTaskpackageSettlement.companyDeductAmount}</c:if>元</span>
				</li>
				<li class="clearfix">
					<span>质保金</span>
					<span>-${bizOrderTaskpackageSettlement.guaranteeMoneyAmount}元</span>
				</li>
				<c:if test="${not empty rewardAmount}">
					<li class="clearfix">
						<span>奖励金额</span>
						<span>${rewardAmount}元</span>
					</li>
				</c:if>
				<li class="clearfix">
					<span>工人组结算金额</span>
					<span class="col_red">${bizOrderTaskpackageSettlement.workerGroupSettleAmount}元</span>
				</li>
			</ul>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
</body>
</html>