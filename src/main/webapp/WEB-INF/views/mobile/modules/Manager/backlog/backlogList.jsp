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
		<title>待办事项</title>
		<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/base.css" />
		<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
		<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/todo.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/manage_home.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/message2.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/managerInfo.css"/>
		</head>
	<body>
	<script src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
		<script src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.js"></script>
		<script src="${ctxStatic}/mobile/modules/Manager/js/todo.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		  $("#taskPackh2").click(function(){
			  if($("#taskPackDiv").css("display")=="none"){
				  $("#taskPackDiv").css("display","block");
				  $("#taskPackem").attr("class","em_sel");
				 }else{
				  $("#taskPackDiv").css("display","none");
				  $("#taskPackem").attr("class","");
				  }
		 /*  $("#taskPackDiv").css("display","none"); */
		  });
		  $("#material").click(function(){
			  if($("#materialDiv").css("display")=="none"){
				  $("#materialDiv").css("display","block");
				  $("#materialem").attr("class","em_sel");
				 }else{
				  $("#materialDiv").css("display","none");
				  $("#materialem").attr("class","");
				  }
		 /*  $("#taskPackDiv").css("display","none"); */
		  });
		  
		  
		  
		});
	
	
	</script>
		<header>
			<a class="back_btn New_btns" href="${ctx}/app/manager/toindex"></a>
			<h2 class="title">待办事项</h2>
		</header>
		<div class="section g-info">
			<h3>今日有<span>${count}</span>个待办事项</h3>
			<div class="list">
				<h2 id = "taskPackh2"><i class="img2"></i>任务包结算<span>${fn:length(list)}个<em id = "taskPackem" class=""></em></span></h2>
				<div id = "taskPackDiv" class="todo_list">
					<c:forEach items="${list}" var = "backlog">
						<div class="todo_infos">
							<p>【${backlog.taskPackageName}】${backlog.communityName}${backlog.buildNumber}-${backlog.buildUnit}-${backlog.buildRoom}-${backlog.customerName}</p>
							<c:if test="${backlog.status == '80'}">
								<a href="${ctx}/app/manager/taskpackageConfirm?id=${backlog.taskPackageId}">去验收</a>
							</c:if>
							<c:if test="${backlog.status == '95'}">
								<a href="${ctx}/app/manager/taskpackageConfirmRecheck?taskPackageId=${backlog.taskPackageId}">去验收</a>
							</c:if>
							<c:if test="${backlog.status == '110'}">
								<a href="${ctx}/app/manager/taskpackageConfirmAgain?orderTaskpackageId=${backlog.taskPackageId}&id=${backlog.settlementId}">去验收</a>
							</c:if>
							<c:if test="${backlog.status == '130'}">
								<a href="${ctx}/app/manager/taskpackageConfirmAgain?orderTaskpackageId=${backlog.taskPackageId}&id=${backlog.settlementId}">去验收</a>
							</c:if>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="list">
				<h2 id = "material"><i class="img1"></i>申请材料<span>${fn:length(material)}个<em id="materialem"></em></span></h2>
				<div class="todo_list" id = "materialDiv">
					<c:forEach items="${material}" var = "materialList">
						<div class="todo_infos">
							<p>【${materialList.purchaseType}】${materialList.communityName}${materialList.buildNumber}-${materialList.buildUnit}-${materialList.buildRoom}-${materialList.customerName}</p>
							<c:if test = "${materialList.purchaseTypeId == '1'}">
								<a href="${ctx}/app/manager/auxiliary/auxiliarychoose?orderId=${materialList.orderId}">去申请</a>
							</c:if>
							<c:if test = "${materialList.purchaseTypeId == '5'}">
								<a href="${ctx}/app/manager/wallAndFloorBrick?id=${materialList.orderId}">去申请</a>
							</c:if>
						</div>
					</c:forEach>
				</div>
			</div>
			<footer>
			<a class="footer_btn home_btn" href="${ctx}/app/manager/toindex">首页</a>
			<a class="footer_btn queue_btn active" href="javascript:void(0)">待办</a>
			<a class="footer_btn msg_btn" href="${ctx}/app/manager/message">消息<div class="msg_countmine" style="right: 30%;">${unreadMessageNum}</div></a>
			<a class="footer_btn mine_btn" href="${ctx}/app/manager/indexMine">我的</a>
		</footer>
		</div>
		
	</body>
	</html>