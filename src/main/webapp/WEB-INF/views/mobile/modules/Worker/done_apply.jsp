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
	<title>申请完工</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/done_apply.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newCommon/common.css"/>
</head>
<script type="text/javascript">
	function last(){
		window.location.href="${ctx}/app/worker/toindex";
	}
</script>
<body>
	<div class="g-done_apply">
		<header>
			<a class="back_btn" onclick="last()"></a>
			<h2 class="title">申请完工</h2>
		</header><!-- /header -->
		<section class="done_apply_list">
			<c:forEach	items="${doneApplyList }" var="doneApplyList">
			
				<div class="">
					<a href="${ctx}/app/worker/packDetail?packageId=${doneApplyList.id}&settleStyle=${doneApplyList.settleStyle}" class="task_query_section clearfix" id="Atag">
						<ul <c:if test="${doneApplyList.delFlag eq '1'}"> style="background: url(${ctxStatic}/mobile/modules/Manager/img/common/abandon.png) no-repeat;background-size: 1.28rem;background-position: 95% .8rem;" </c:if>>
							<li class="clearfix">
						 		<span>任务包名称：</span>
								<p>${doneApplyList.packageName }</p>
							</li>
							<li class="clearfix">
								<span>项目经理：</span>
								<p>${doneApplyList.itemCustomer }</p>
							</li>
							<li class="clearfix">
								<span>手机号：</span>
								<p>${doneApplyList.managerPhone }</p>
							</li>
							<li class="clearfix">
								<span>施工地点：</span>
								<p>${doneApplyList.customerMessage }-${doneApplyList.customerName }</p>
							</li>
							<li class="clearfix">
								<span>施工时间：</span>
								<p>
									<fmt:formatDate value="${doneApplyList.planStartdate }" pattern="yyyy-MM-dd" /> 至
									<fmt:formatDate value="${doneApplyList.planEnddate }" pattern="yyyy-MM-dd" />
								</p>
							</li>
							<li class="clearfix">
								<span>订单状态：</span>
								<p class="col_blue">${doneApplyList.packageStatename }</p>
							</li>
						</ul>
					</a>
					<a class="done_apply_btn" href="${ctx}/app/worker/doneDemandList?packageId=${doneApplyList.id}"  id="Atag">申请完工</a>
				</div>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
</body>
</html>