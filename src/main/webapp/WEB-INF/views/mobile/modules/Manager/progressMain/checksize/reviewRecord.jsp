<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>申请复尺记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/quesDone.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/checksizeList"></a>
			<h2 class="title">申请复尺记录</h2>
		</header><!-- /header -->
		
		<section class="pad_top11">
			<div class="mar_btm14 font0 search-area">
				<input class="search-box" type="text" placeholder="复尺内容">
				<a class="search-btn" href="javascript:;" onclick="findText()"></a>
			</div>
			<c:forEach items="${list }" var="checksize">
				<ul class="item_lists bg_f font0 mar_btm3">
					<div class="tit_div">
						<span class="font28 col_blue"><fmt:formatDate type="both" value="${checksize.createDate }"/></span>
						<c:if test="${checksize.picCount>0 }">
							<a class="see font28 col_blue" href="${ctx}/app/manager/checksizeRecordPic?checksizeId=${checksize.id}">查看照片</a>
						</c:if>
					</div>
					<li class="mar_btm30">
						<span class="font28 col_6">复　尺　内　容：</span>
						<span class="font28 col_3 searchName">${checksize.checksizeTypeName }</span>
					</li>
					<li class="mar_btm30 clearfix">
						<span class="font28 col_6">复　尺　状　态：</span>
						<span class="font28 col_3">${checksize.checksizeStatusName }</span>
					</li>
					<li class="mar_btm30 clearfix">
						<span class="font28 col_6">期望复尺日期　：</span>
						<span class="font28 col_3"><fmt:formatDate type="date" value="${checksize.checksizeDate }"/></span>
					</li>
					<li class="mar_btm30 clearfix">
						<span class="font28 col_6">供应商确认日期：</span>
						<span class="font28 col_3"><fmt:formatDate type="date" value="${checksize.supplierConfirmDate }"/></span>
					</li>
					<li class="mar_btm30 clearfix">
						<span class="font28 col_6 flo_left">回　　　　　复：</span>
						<p class="font28 col_3 flow pad_rgt3">${checksize.materialDepartmentDealReply }</p>
					</li>
				</ul>
				
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<style type="text/css">
        .highlight { background-color:yellow; }
    </style>
	<script type="text/javascript">
		function findText(){
			var searchText = $('.search-box').val();
			if(null==searchText || searchText==""){
				clearSelection();
				$(".item_lists").show();
				return false;
			}
			
			$(".item_lists").hide();
			clearSelection();
			var regExp = new RegExp(searchText, 'g');
			
			$('.searchName').each(function()//遍历文章；
		            {
		                var html = $(this).html();
		                var newHtml = html.replace(regExp, '<span class="highlight">'+searchText+'</span>');//将找到的关键字替换，加上highlight属性；
		 
		                $(this).html(newHtml);//更新文章；
		            });
			$(".highlight").parent().parent().parent().show();		
		}
		function clearSelection(){
				
				 $('.searchName').each(function()//遍历
	            {
	                $(this).find('.highlight').each(function()//找到所有highlight属性的元素；
	                {
	                    $(this).replaceWith($(this).html());//将他们的属性去掉；
	                });
	            });
	    }
	
	</script>
</body>
</html>