<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>中期提成结算日志</title>
<link rel="stylesheet"
	href="${ctxStatic}/modules/orderReportRelatedContract/css/packahesChoice.css">
<style type="text/css">
.Page_back {
	float: right;
	margin-top: 20px;
	margin-bottom: 40px;
}

.Page_back .page_backBtn {
	border: 1px solid #1296db;
	background: #1296db;
	color: #fff;
	padding: 8px 20px;
}
</style>
</head>
<body>
	<div class="packagesChoice_warp">
		<div class="packagesChoice_warpper">
			<div class="packagesChoice_content">
				<div class="basic">
					<h2 class="col_blue mb20 mt33">
						<i class="icon_line"></i>
						<p class="pl16">基本信息</p>
					</h2>
					<div class="basic_content">
						<ul class="clearfix">
							<li class="fl"><span>小区 :</span><span class="ml12">${bizPmStarCommissionCnfgSnap.communityName}-${bizPmStarCommissionCnfgSnap.buildNumber}-${bizPmStarCommissionCnfgSnap.buildUnit}-${bizPmStarCommissionCnfgSnap.buildRoom}</span></li>
							<li class="fl ml134"><span>客户 :</span><span class="ml12">${bizPmStarCommissionCnfgSnap.customerName}-${bizPmStarCommissionCnfgSnap.customerPhone}</span></li>
						</ul>
						<ul class="clearfix">
							<li class="fl"><span>新/老房 :</span><span class="ml12">${fns:getDictLabel(bizPmStarCommissionCnfgSnap.isOldNew,'biz_is_new_house', '')}</span></li>
						</ul>
						<ul class="clearfix">
							<li class="fl"><span>项目经理 :</span><span class="ml12">${bizPmStarCommissionCnfgSnap.itemManager}-${bizPmStarCommissionCnfgSnap.itemManagerPhone}</span></li>
							<li class="fl ml134 w61"><span class="fl">派单时星级:</span><span
								class="ml12 content_info_cont">${bizPmStarCommissionCnfgSnap.starLever}</span></li>
						</ul>
					</div>

				</div>
				<div class="basic  processesLog">
					<h2 class="col_blue mb20 mt33">
						<i class="icon_line"></i>
						<p class="pl16">流程日志</p>
					</h2>

					<div class="process_warp mt40">
						<h4 class="col_333 mb14">项目经理申请约检节点</h4>
						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>申请时间 :</span><span class="ml12"><fmt:formatDate value="${bizQcBill.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
								<li class="fl ml134"><span>申请人 :</span><span class="ml12">${bizQcBill.applyEmployeeName}</span></li>
							</ul>
							<ul class="clearfix">
								<li class="fl"><span>期望验收日期 :</span><span class="ml12"><fmt:formatDate value="${bizQcBill.expectCheckDatetime}" pattern="yyyy-MM-dd"/></span></li>
								<li class="fl ml134"><span>约检节点名称 :</span><span class="ml12">${bizQcBill.qcCheckNodeName}</span></li>
							</ul>
						</div>
					</div>
					
					<div class="process_warp mt40">
						<h4 class="col_333 mb14">质检员确认验收约检节点</h4>
						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate value="${bizQcBill.acceptCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
								<li class="fl ml134"><span>操作人 :</span><span class="ml12">${bizQcBill.checkRealName}</span></li>
							</ul>
						</div>
					</div>
					
					 <div class="process_warp mt40">
						<h4 class="col_333 mb14">结算员通过约检节点</h4>
						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate value="${log1.statusDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
								<li class="fl ml134"><span>操作人 :</span><span class="ml12">${log1.businessEmployeeName}</span></li>
							</ul>
						</div>
					</div>
					
					<div class="process_warp mt40">
						<h4 class="col_333 mb14">确认二期款</h4>
						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate value="${log2.statusDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
								<li class="fl ml134"><span>操作人 :</span><span class="ml12">${log2.businessEmployeeName}</span></li>
							</ul>
						</div>
					</div>
					
					<div class="process_warp mt40">
						<h4 class="col_333 mb14">生成月度结算单</h4>
						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate value="${log3.statusDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
								<li class="fl ml134"><span>操作人 :</span><span class="ml12">${log3.businessEmployeeName}</span></li>
							</ul>
						</div>
					</div>

				</div>
				<div class="Page_back">
					<input type="button" value="返回" class="page_backBtn"
						<%--onclick="window.location.href='${ctx}/pmsettlebill/bizPmCommissionSettleController/queryPmComkissionSettle'">--%>
						   onclick="goToHistory()">
				</div>
			</div>
		</div>

	</div>
</body>
</html>