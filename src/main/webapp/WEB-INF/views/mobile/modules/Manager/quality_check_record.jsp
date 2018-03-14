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
	<title>申请约检记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/list.css"/>
	<style>
		.pad_top34{padding-top:.14rem;}
		.pad_btm20{padding-bottom:.4rem;}
		.col_378afa{color:#378afa;}
		.bor_btm_ccc{border-bottom: 1px solid #ccc;}
	</style>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/qualityApply" ></a>
			<h2 class="title">申请约检记录</h2>
		</header><!-- /header -->
		<section class="pad_top88">
			<c:if test="${not empty list }">
				<c:forEach items="${list }" var="qcBill">
					<!-- <div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
						<ul class="pad_top3 pad_left3">
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left spanRgt33">约检节点名称 ：</span>
								<p class="font28 col_3 pad_rgt3 flow">XXX</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left spanRgt33">申请时间 ：</span>
								<p class="font28 col_3 pad_rgt3 flow">XXX</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left spanRgt33">计划验收日期：</span>
								<p class="font28 col_3 pad_rgt3 flow">XXX</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left spanRgt33">期望验收日期：</span>
								<p class="font28 col_3 pad_rgt3 flow">XXX</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left spanRgt33">质检单状态：</span>
								<p class="font28 col_3 pad_rgt3 flow">XXX</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left spanRgt33">质检员：</span>
								<p class="font28 col_3 pad_rgt3 flow">XXX</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left spanRgt33">质检员手机号：</span>
								<p class="font28 col_3 pad_rgt3 flow">XXX</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left spanRgt33">延期原因：</span>
								<p class="font28 col_3 pad_rgt3 flow">XXX延期延期延期延期延期延期延期延期延期延期延期延期延期延期延期延期延期延期延期延期延期延期延期延期</p>
							</li>
						</ul>
					</div> -->
					<div class="font30 col_blue pad_top34 pad_btm20 pad_left3 pad_rgt3 bor_btm_ccc rela">
					<!-- <a class="font28 col_378afa flo_rgt seeBtn" href="javascript:;">查看照片&gt;&gt;</a> -->
						<c:if test="${count eq 0 }">
							<a class="font28 col_378afa flo_rgt seeBtn" style="color: #red;" >查看照片</a>
						</c:if>
						<c:if test="${count ne 0 }">
							<a class="font28 col_378afa flo_rgt seeBtn" href="${ctx}/app/manager/viewPic?qcBillId=${qcBill.id}">查看照片</a>
						</c:if>
					</div>
					
					<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
						<ul class="pad_top3 pad_left3">
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">约检节点名称 ：</span>
								<p class="font28 col_3 pad_rgt3 flow">${qcBill.qcCheckNodeName }</p>
							</li>
							<%-- <li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">申 请 日 期 ：</span>
								<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${qcBill.createDate }" pattern="yyyy-MM-dd"/></p>
							</li> --%>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">计划验收日期：</span>
								<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${qcBill.planCheckDate }" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">期望验收日期：</span>
								<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${qcBill.expectCheckDatetime }" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">质检单状态：</span>
								<p class="font28 col_3 pad_rgt3 flow">${qcBill.statusName }</p>
							</li>
							<c:if test="${qcBill.status ==2}">
								<li class="mar_btm24 clearfix">
									<span class="col_grey font28 flo_left">质检员 ：</span>
									<p class="font28 col_3 pad_rgt3 flow">${qcBill.preCheckManName }</p>
								</li>
								<li class="mar_btm24 clearfix">
									<span class="col_grey font28 flo_left">质检员手机号：</span>
									<p class="font28 col_3 pad_rgt3 flow">${qcBill.preCheckManPhone }</p>
								</li>
								<li class="mar_btm24 clearfix">
									<span class="col_grey font28 flo_left">延期原因：</span>
									<p class="font28 col_3 pad_rgt3 flow">${qcBill.delayReasonPm }</p>
								</li>

							</c:if>

							<c:if test="${qcBill.status !=2}">
								<li class="mar_btm24 clearfix">
									<span class="col_grey font28 flo_left">质 检 员 ：</span>
									<p class="font28 col_3 pad_rgt3 flow">${qcBill.checkManName }</p>
								</li>
								<li class="mar_btm24 clearfix">
									<span class="col_grey font28 flo_left">质检员手机号：</span>
									<p class="font28 col_3 pad_rgt3 flow">${qcBill.checkManPhone }</p>
								</li>
								<li class="mar_btm24 clearfix">
									<span class="col_grey font28 flo_left">延期原因：</span>
									<p class="font28 col_3 pad_rgt3 flow">${qcBill.delayReasonPm }</p>
								</li>

							</c:if>

						</ul>
					</div>
				</c:forEach>
			</c:if>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>