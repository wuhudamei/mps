<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/mobile/modules/Worker/employeeAgreement/employeeAgreement.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>首页</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/info.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/message2.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/mask.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/common.css" />
</head>
<script type="text/javascript">

</script>
<body>
	<div class="g-info">
		<div class="top">
			<div class="user_info">
				<img src="${ctxStatic}/mobile/modules/Worker/images/headimg.png" alt="headimg">
				<span class="user_name">${worker.realname }</span>
				<div class="star">
					<c:if test="${worker.star == null || worker.star == 0 }">
						<span></span>
						<span></span>
						<span></span>
						<span></span>
						<span></span>
					</c:if>
					<c:if test="${worker.star != null && worker.star !=0 }">
						<c:forEach begin="0" end="${worker.star-1}" step="1">
							<span class="bling"></span>
						</c:forEach>
						<c:forEach begin="1" end="${5-worker.star }" step="1">
							<span></span>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
		<div class="sec task_sec">
			<nav class="info_nav">
				<a href="javascript:void(0)">
					<span class="number">${countDiscompleted }</span>
					<span class="explain">未完工</span>
				</a>
				<a href="javascript:void(0)">
					<span class="number">${countCompleted }</span>
					<span class="explain">已完工</span>
				</a>
			</nav>
			<ul class="info_ul">
				<li><a href="${ctx}/app/worker/salaryList">薪酬确认<c:if test="${confirmCount != 0}"><div class="msg_num">${confirmCount }</div></c:if></a></li>
				<!-- <li><a href="javascript:void(0)">催促付款</a></li> -->
				<li><a href="${ctx}/app/worker/orderReport/orderreport">我要推荐</a></li>
				<li class="hgt_88 complain"><a href="${ctx}/app/worker/project-issue/list.php">工程投诉</a></li>
				<li><a href="${ctx}/app/worker/urgeevaluation/list">催促评价<c:if test="${evaluationList != 0}"><div class="msg_num">${evaluationList }</div></c:if></a></li>
			</ul>
		</div>
		
		<footer>
			<a style="width: 50%;" class="home_btn active" href="javascript:void(0)">首页</a>
			<%-- <a class="msg_btn" href="${ctx}/app/worker/message">消息</a> --%>
			<a style="width: 50%;" class="mine_btn" href="${ctx}/app/worker/myindex1">我的</a>
		</footer>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<!-- 弹出协议 -->

	<script type="text/javascript">
	
	</script>
</body>
</html>