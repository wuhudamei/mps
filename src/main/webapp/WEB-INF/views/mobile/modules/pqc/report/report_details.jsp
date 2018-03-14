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
	<title>质检报告</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/date_check.css"/>
	<style type="text/css">
		.pad_top3{padding-top:.3rem;}
		.pad_btm3{padding-bottom:.3rem;}
		.item span:first-child{display: block;width: 70%;}
	</style>
</head>
<body>
	<div class="g-task_list">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/report/reportList?orderId=${reportCheckDetails.orderId}" onclick="myhistory.back()"></a>
			<h2 class="title">质检报告</h2>
		</header><!-- /header -->
		<section class="task_list confirm">
			<div class="info">
				<ul class="pad_top2">
					<li class="clearfix">
						<span>顾客信息：</span>
						<p>${reportCheckDetails.communityName }-${reportCheckDetails.buildNumber }-${reportCheckDetails.buildUnit }-${reportCheckDetails.buildRoom }-${reportCheckDetails.customerName }</p>
					</li>
					<li class="clearfix">
						<span>项目经理：</span>
						<p>${reportCheckDetails.managerRealName }</p>
					</li>
					<li class="clearfix">
						<span>质 检 员：</span>
						<p>${reportCheckDetails.inspectorRealName }</p>
					</li>
					<li class="clearfix">
						<span>检查内容：</span>
						<c:if test="${reportCheckDetails.qcBillType=='1' }">
							<p>【约检】${reportCheckDetails.qcCheckNodeName }</p>
						</c:if>
						<c:if test="${reportCheckDetails.qcBillType=='2' }">
							<p>【抽检】</p>
						</c:if>
						<c:if test="${reportCheckDetails.isRecheck=='1' }">
							<p>【复检】</p>
						</c:if>
					</li>
					<li class="clearfix">
						<span>检查时间：</span>
						<p class=""><fmt:formatDate value="${reportCheckDetails.checkDatetime }" pattern="yyyy-MM-dd hh:mm:ss" /></p>
					</li>
					<li class="clearfix">
						<span>实际得分：</span>
						<p>
							<span class="col3">${reportCheckDetails.gotScore }</span><span class="col9 font24">（总分${reportCheckDetails.totalScore }）</span>
						</p>
					</li>
				</ul>
			</div>
			<div class="pic_div bor_top shadow font0">
				<span class="font28 col6">照片数量：</span>
				<span class="font28 col3">${count }张</span>
				<c:if test="${count eq 0 }">
					<a class="see_no_btn" style="color: #red;" >查看照片</a>
				</c:if>
				<c:if test="${count ne 0 }">
					<a class="see_btn" href="${ctx}/app/pqc/report/reportPic?qcBillId=${reportCheckDetails.id}">查看照片</a>
				</c:if>
			</div>
			<div class="design shadow">
				<c:forEach items="${itemDetails }" var="itemDetails" varStatus="status">
					<div class="bor_top num">
							<div class="item bor_top pad_top3 pad_btm3 pad_left3 font0 relative">
								<span class="font28 col3">${itemDetails.qcCheckItemName }</span>

									<span class="font24 col9 standard">
									<c:if test="${itemDetails.isPassed=='1' }">合格</c:if>
									<c:if test="${itemDetails.isPassed=='0' }">不合格</c:if>
									</span>

									<a class="details_btn font24 col9" href="javascript:void(0)">详情</a>
							</div>
							<ul class="result pad_top2 pad_btm2 pad_left7 undis">
								<li class="clearfix">
									<span class="col9">违规形式：</span>
									<div class="rightDiv">
										<c:forEach items="${itemDetails.reportCheckBreakSettleBillList }" var="reportBreak">
											<p class="col6">
												${reportBreak.breakDescribe }
												<c:if test="${reportBreak.isRequiredRemarks=='1' }">
												备注：${reportBreak.breakRemarks }
												</c:if>
											</p>
										</c:forEach>
									</div>
								</li>
								<li class="clearfix">
									<span class="col9">处理方式：</span>
									<div class="rightDiv">
										<c:if test="${itemDetails.isWarned=='1' }">
											<div class="font28 col6">
												警告
											</div>
										</c:if>
										<c:if test="${itemDetails.isLocaleRepaire=='1' }">
											<div class="font28 col6">
												现场整改
											</div>
										</c:if>
										<c:if test="${itemDetails.isLimitDateRepaire=='1' }">
											<div class="font28 col6">
												期限整改 -
												<c:if test="${itemDetails.limitDateRepaireCheckStyle=='1' }">线下整改</c:if>
												<c:if test="${itemDetails.limitDateRepaireCheckStyle=='0' }">线上整改</c:if>
												 - <fmt:formatDate value="${itemDetails.limitDate }" pattern="yyyy-MM-dd" />
											</div>
										</c:if>
										<c:if test="${itemDetails.isPunishMoney=='1' }">
											<div class="font28 col6">


												扣分/罚款 - 项目经理 ${itemDetails.pmPunishScore }分/${itemDetails.punishMoneyAmountReal }元 工人 ${itemDetails.workerPunishScore }分/${itemDetails.workerPunishAmount }元 质检员 ${itemDetails.qcPunishScore }分/${itemDetails.qcPunishAmount }元

												<%-- 罚款 - ${itemDetails.punishMoneyAmountReal }元 --%>
											</div>
										</c:if>
										<c:if test="${itemDetails.mnagerPerson!=null ||itemDetails.workGroupPerson!=null}">
											<div class="font28 col6">
												责任人 :  项目经理-${itemDetails.mnagerPerson }</br>
												工人 : ${itemDetails.taskName }- ${itemDetails.workGroupPerson   }
												<%-- 罚款 - ${itemDetails.punishMoneyAmountReal }元 --%>
											</div>
										</c:if>
										<div class="font28 col6"></div>
									</div>
								</li>
							</ul>
					</div>
				</c:forEach>

			</div>
		</section>

	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script>
		$(function(){
			$(document).on('click', '.details_btn', function(){
				$(this).parent().parent().find('.result').show();
				$(this).addClass('total_btn').removeClass('details_btn');
			})
			$(document).on('click', '.total_btn', function(){
				$(this).parent().parent().find('.result').hide();
				$(this).addClass('details_btn').removeClass('total_btn');
			})
		}());
	</script>
</body>
</html>


