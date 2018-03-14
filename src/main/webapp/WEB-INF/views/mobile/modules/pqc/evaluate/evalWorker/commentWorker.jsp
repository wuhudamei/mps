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
	<title>评价工人</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/doneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/search.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx }/app/pqc/pqcIndex"></a>
			<h2 class="title">评价工人</h2>
		</header><!-- /header -->
		<section class="pad_top11">
			<div class="mar_btm14 font0 search-area">
				<input class="search-box" type="text" placeholder=" 小区名称、客户姓名、工人组长、任务包名称">
				<a class="search-btn" href="javascript:;"></a>
				<input type="text" hidden="hidden" value="${text }" id="text">
			</div>
			<c:forEach items="${list }" var="item">
				<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
					<a href="javascript:;" class="arrow_rgt pad_left3">
						<ul class="pad_top3">
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">顾　　客　：</span>
								<p class="font28 col_3 flow">${item.communityName}-${item.buildNumber}-${item.buildUnit}-${item.buildRoom}-${item.customerName}</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">工人组长　：</span>
								<p class="font28 col_3 flow">${item.groupRealname}</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">任务包名称：</span>
								<p class="font28 col_3 flow">${item.packageName}</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">验收日期　：</span>
								<p class="font28 col_3 flow"><fmt:formatDate value="${item.checkDate}" pattern="yyyy-MM-dd"/></p>
							</li>
						</ul>
					</a>
					<a class="one_btn" href="${ctx}/app/pqc/evaluate/evalWorker/toEvaluate?evalTaskpackScoreId=${item.evalTaskpackScoreId}&text=${text}">去评价</a>
				</div>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript">
		$(function(){
			var text = $("#text").val();
			if(null!=text && text!=""){
				$(".search-box").val(text);
			}
			$(".search-btn").bind("click",searchList);
		})
	
		function searchList(){
			var text = $(".search-box").val();
			window.location.href="${ctx}/app/pqc/evaluate/evalWorker/list?text="+text;
		}
	</script>
</body>
</html>