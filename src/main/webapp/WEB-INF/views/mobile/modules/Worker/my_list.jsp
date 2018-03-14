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
	<title>任务列表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/done_apply.css"/>
</head>
<body>
	<div class="g-done_apply">
		<header>
			<a class="back_btn" href="javascript:void(0)" onclick="history.go(-1)"></a>
			<h2 class="title">任务列表</h2>
		</header><!-- /header -->
		<section class="done_apply_list">
		<!-- 未完结的项目加背景 添加删除类名bg_e6 -->
			<c:forEach items ="${list }" var="taskpackage">
					<c:if test="${ taskpackage.packageStateid == '160' }">
						<div class="bg_e6">
							<a href="${ctx}/app/worker/taskpackageDetail?taskpackageId=${taskpackage.id}">
								<ul>
									<li class="clearfix">
										<span>项目经理：</span>
										<p>${taskpackage.itemCustomer }</p>
									</li>
									<li class="clearfix">
										<span>施工地点：</span>
										<p>${taskpackage.customerMessage }-${taskpackage.customerName }</p>
									</li>
									<li class="clearfix">
										<span>施工时间：</span>
										<c:if test="${taskpackage.actualStartdate == null || taskpackage.actualEnddate == null }">
											<p>
											<fmt:formatDate value="${taskpackage.planStartdate }" pattern="yyyy-MM-dd" />
											至
											<fmt:formatDate value="${taskpackage.planEnddate }" pattern="yyyy-MM-dd" />
											</p>
										</c:if>
										<c:if test="${taskpackage.actualStartdate != null && taskpackage.actualEnddate != null }">
											<p>
											<fmt:formatDate value="${taskpackage.actualStartdate }" pattern="yyyy-MM-dd" />
											至
											<fmt:formatDate value="${taskpackage.actualEnddate }" pattern="yyyy-MM-dd" />
											</p>
										</c:if>
									</li>
									<li class="clearfix">
										<span>订单状态：</span>
										<p class="col_blue">${fns:getDictLabel(taskpackage.packageStateid, 'taskpackage_status', '')}</p>
									</li>
								</ul>
							</a>
						</div>
				</c:if>
					<c:if test="${ taskpackage.packageStateid != '160' }">
						<div class="bg_e6">
							<a href="${ctx}/app/worker/taskpackageDetail?taskpackageId=${taskpackage.id}">
								<ul>
									<li class="clearfix">
										<span>任务包名称：</span>
										<p>${taskpackage.packageName }</p>
									</li>
									<li class="clearfix">
										<span>项目经理：</span>
										<p>${taskpackage.itemCustomer }</p>
									</li>
									<li class="clearfix">
										<span>施工地点：</span>
										<p>${taskpackage.customerMessage }-${taskpackage.customerName }</p>
									</li>
									<li class="clearfix">
										<span>施工时间：</span>
										<c:if test="${taskpackage.actualStartdate == null || taskpackage.actualEnddate == null }">
											<p>
											<fmt:formatDate value="${taskpackage.planStartdate }" pattern="yyyy-MM-dd" />
											至
											<fmt:formatDate value="${taskpackage.planEnddate }" pattern="yyyy-MM-dd" />
											</p>
										</c:if>
										<c:if test="${taskpackage.actualStartdate != null && taskpackage.actualEnddate != null }">
											<p>
											<fmt:formatDate value="${taskpackage.actualStartdate }" pattern="yyyy-MM-dd" />
											至
											<fmt:formatDate value="${taskpackage.actualEnddate }" pattern="yyyy-MM-dd" />
											</p>
										</c:if>
									</li>
									<li class="clearfix">
										<span>订单状态：</span>
										<p class="col_blue">${fns:getDictLabel(taskpackage.packageStateid, 'taskpackage_status', '')}</p>
									</li>
								</ul>
							</a>
						</div>
				</c:if>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
</body>
</html>