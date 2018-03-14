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
	
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Worker/css/message2.css">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/todo.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/managerInfo.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/todo.css"/>
	<style type="text/css">
		footer {
		    width: 100%;
		    height: .96rem; 
		    background: #fff;
		    position: fixed; 
		    bottom: 0;
		    left: 0;
		    border-top: 1px solid #d1d1d1;
		}
	
	</style>
</head>
<body>
		<header>
			<h2 class="title">待办事项</h2>
		</header><!-- /header -->
		<div class="section g-info">
			<p class="font32 col_0780ec bg_f mb24 todoP">共有<span class="col_ff503f">${totalCount}</span>个待办事项</p>
			<c:forEach items="${backklogList}" var = "backlog">
				<div class="pl30 mb24 bg_f">
					<div class="">
						<div class="locate">
							<p class="locateP">${backlog.communityName }-${backlog.buildNumber }-${backlog.buildUnit }-${backlog.buildRoom }-${backlog.customerName }-</p>
							<a class="locateBtn spread" href="javascript:;"></a>
						</div>
						<div class="details undis">
							<div>
								<div class="pl43 rela">
									<p class="bor_btm_d8d7d7 pt34 pb20"><span class="font30 col_0780ec pl18 blueBar">今日待办事项</span></p>
									<span class="count">${backlog.todayToDoListCount }</span>
									<a class="todoBtn spread" href="javascript:;"></a>
								</div>
								<ul class="pl93 items undis">
									<c:forEach items="${backlog.todayToDoList}" var = "todayToDo">
										<li class="pt28 pb28 bor_dash_c rela" url="${ctx}${todayToDo.toDoItemDealUrl }" keyid="${todayToDo.id }">
											<span class="font30 col_6">${todayToDo.toDoItemRemindTitle }</span>
											<a class="doBtn" href="javascript:toDoDeal('${ctx}${todayToDo.toDoItemDealUrl }','${todayToDo.id }')">去处理</a>
											<%-- <a class="doBtn" href="${ctx}${todayToDo.toDoItemDealUrl }">去处理</a> --%>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="pb70">
								<div class="pl43 rela">
									<p class="bor_btm_d8d7d7 pt34 pb20"><span class="font30 col_0780ec pl18 blueBar">其他待办事项</span></p>
									<span class="count">${backlog.otherToDoListCount }</span>
									<a class="todoBtn spread" href="javascript:;"></a>
								</div>
								<ul class="pl93 items undis">
									<c:forEach items="${backlog.otherToDoList}" var = "otherToDo">
										<li class="pt28 pb28 bor_dash_c rela" url="${ctx}${otherToDo.toDoItemDealUrl}" keyid="${otherToDo.id }">
											<span class="font30 col_6">${otherToDo. toDoItemRemindTitle}</span>
											<a class="doBtn" href="javascript:toDoDeal('${ctx}${otherToDo. toDoItemDealUrl}','${otherToDo.id }')">去处理</a>
										<%-- 	<a class="doBtn" href="${ctx}${otherToDo. toDoItemDealUrl}">去处理</a> --%>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<footer>
			<a class="footer_btn home_btn" href="${ctx}/app/manager/toindex">首页</a>
			<a class="footer_btn msg_btn" href="javascript:void(0)">待办<div class="msg_countmine" style="right: 30%;">${toDoDealNum}</div></a>
			<a class="footer_btn msg_btn" href="${ctx}/app/manager/message">消息<div class="msg_countmine" style="right: 30%;">${unreadMessageNum}</div></a>
			<a class="footer_btn mine_btn" href="${ctx}/app/manager/indexMine">我的</a>
		</footer>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.js"></script>
	<script src="${ctxStatic}/mobile/modules/Manager/js/todo.js"></script>
	<script>
	$(".details").on("click","ul li",function(){      
		var liUrl= $(this).attr("url");
		var id = $(this).attr("keyid");
		window.location.href="${ctx}/app/manager/backlog/updateViewed?id="+id+"&url="+liUrl;
	 });
	function toDoDeal(url,id){
		window.location.href="${ctx}/app/manager/backlog/updateViewed?id="+id+"&url="+url;
	}
		$(document).on('click', '.locate', function(){
			var obj = $(this).find('.locateBtn');
			if(obj.hasClass('spread')){
				obj.removeClass('spread').addClass('fold');
				obj.parent().parent().find('.details').removeClass('undis');
				obj.parent().parent().addClass('bor_btm_d8d7d7');
			}else{
				obj.removeClass('fold').addClass('spread');
				obj.parent().parent().find('.details').addClass('undis');
				obj.parent().parent().removeClass('bor_btm_d8d7d7');
			}
		});
		$(document).on('click', '.pl43', function(){
			var obj = $(this).find('.todoBtn');
			if(obj.hasClass('spread')){
				obj.removeClass('spread').addClass('fold');
				obj.parent().parent().find('.items').removeClass('undis');
			}else{
				obj.removeClass('fold').addClass('spread');
				obj.parent().parent().find('.items').addClass('undis');
			}
		});
	</script>
</body>
</html>