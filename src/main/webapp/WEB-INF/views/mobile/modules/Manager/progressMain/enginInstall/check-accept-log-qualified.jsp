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
	<title>安装验收详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/apply.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/layout.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/public/style.css" />

</head>

<body>
<div class="wrap">
	<header>
		<a class="back_btn" href="${ctx}/app/manager/orderInstallPlan/installAcceptanceDetail?orderId=${orderInstallPlan.orderId}"></a>
		<h2 class="title">安装验收详情</h2>
	</header>
	<!-- /header -->
	<section class="pt112 bor_btm_e5">

		<!-- 产业 -->
		<c:if test="${orderInstallPlan.installMode eq 1}">
			<div class="sec">
				<p class="pb20">
					<span class="pl30 font30 col_blue">基本信息</span>
				</p>
				<ul class="item pad_top3 mar_btm0 clearfix">
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow"><span class="item-name">顾客信息：</span>${engineInstall.communityName }-${engineInstall.buildNumber }-${engineInstall.buildUnit }-${engineInstall.buildRoom}-${engineInstall.customerName }</p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow"><span class="item-name">安装项名称：</span>${engineInstall.installItemName}</p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow"><span class="item-name">安装项状态：</span>${engineInstall.installstatus}-${engineInstall.installstatusName}</p>
					</li>
					<li class="mb30 rele clearfix posR">
						<p class="font30 col_6 flow"><span class="item-name">工人组长：</span>${engineInstall.empGroupName}-${engineInstall.empGroupPhone}</p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow"><span class="item-name">实际工期：</span>${engineInstall.supplierintodate }至${engineInstall.suppliercompletedate }</p>
					</li>
				</ul>
				<p class="item-view shadow"><a href="${ctx }/app/manager/orderInstallPlan/construction_pic?constructionId=${engineInstall.shigongId}" class="col_blue"><span>查看完工图 >></span></a></p>
			</div>
			<div class="sec">
				<p class="pad_top3 pb20">
					<span class="pl30 font30 col_blue">安装评定</span>
				</p>
				<ul class="sec item shadow pad_top3 margin_bt0 clearfix">
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow"><span class="item-name">验收日期：</span><fmt:formatDate type="date" value="${orderInstallPlan.realAcceptDate}"/></p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow">
							<span class="item-name">是否合格：</span>
							<c:if test="${orderInstallPlan.isQualified eq 1}">合格</c:if>
							<c:if test="${orderInstallPlan.isQualified eq 0}">不合格</c:if>
						</p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow">
							<span class="item-name">是否延期：</span>
							<c:if test="${orderInstallPlan.isCompleteDelay eq 1}">是</c:if>
							<c:if test="${orderInstallPlan.isCompleteDelay eq 0}">否</c:if>
						</p>
					</li>
					<c:if test="${orderInstallPlan.isCompleteDelay eq 1}">
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">延期天数：</span>${orderInstallPlan.delayDays}天</p>
						</li>
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">延期原因：</span>${orderInstallPlan.completeDelayReasonName}</p>
						</li>
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow clearfix">
								<span class="item-name pull-left">延期说明：</span>
								<span class="item-txt pull-left">${orderInstallPlan.completeDelayRemarks}</span>
							</p>
						</li>
					</c:if>
				</ul>
			</div>
		</c:if>



		<!-- 传统 -->
		<c:if test="${orderInstallPlan.installMode eq 2}">
			<div class="sec">
				<p class="pb20 text-center">
					<span class="pl30 font30 col_blue">${engineInstall.communityName }-${engineInstall.buildNumber }-${engineInstall.buildUnit }-${engineInstall.buildRoom}-${engineInstall.customerName }</span>
				</p>
				<ul class="item pad_top3 mar_btm0 clearfix">
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow"><span class="item-name">安装项目：</span><span class="col_blue">${orderInstallPlan.installItemName}</span></p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow">
							<span class="item-name">是否合格：</span>
							<c:if test="${orderInstallPlan.isQualified eq 1}">合格</c:if>
							<c:if test="${orderInstallPlan.isQualified eq 0}">不合格</c:if>
						</p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow"><span class="item-name">验收日期：</span><fmt:formatDate type="date" value="${orderInstallPlan.realAcceptDate}"/></p>
					</li>
					<li class="mb30 rele clearfix posR">
						<p class="font30 col_6 flow"><span class="item-name">实际进场日期：</span><fmt:formatDate type="date" value="${orderInstallPlan.realIntoDate}"/></p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow"><span class="item-name">实际完工日期：</span><fmt:formatDate type="date" value="${orderInstallPlan.realCompleteDate}"/></p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow"><span class="item-name">计划完工日期：</span><fmt:formatDate type="date" value="${orderInstallPlan.planCompleteDate}"/></p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow">
							<span class="item-name">是否延期：</span>
							<c:if test="${orderInstallPlan.isCompleteDelay eq 1}">是</c:if>
							<c:if test="${orderInstallPlan.isCompleteDelay eq 0}">否</c:if>
						</p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow"><span class="item-name">延期天数：</span>${orderInstallPlan.delayDays}天</p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow"><span class="item-name">延期原因：</span>${orderInstallPlan.completeDelayReasonName}</p>
					</li>
					<li class="mb30 rele clearfix">
						<p class="font30 col_6 flow clearfix"><span class="item-name pull-left">延期说明：</span><span class="item-txt pull-left">${orderInstallPlan.completeDelayRemarks}</span></p>
					</li>
				</ul>
			</div>
		</c:if>

	</section>
	<!-- /section -->
	<section>
		<p class="pad_top3 pb20">
			<span class="pl30 font28 col_blue">安装验收单+现场施工图:</span>
		</p>
		<div class="pics bor_top_ddd font0 shadow bg_f clearfix">
			<c:forEach items="#{list}" var="p">
				<div class="pic">
					<img src="${baseUrl}${p.picUrl}" alt="">
				</div>
			</c:forEach>
		</div>
	</section>
	<!-- /section -->
	<div class="pt44">
		<a class="subBtn" href="${ctx}/app/manager/orderInstallPlan/installAcceptanceDetail?orderId=${orderInstallPlan.orderId}">返回</a>
	</div>
	<div style="padding-bottom:300px;"></div>
</div>
<!-- /.wrap -->
<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
</body>

</html>
