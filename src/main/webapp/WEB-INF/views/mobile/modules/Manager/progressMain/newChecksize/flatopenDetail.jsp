<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>平开门复尺详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/review_det_cm.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/recheckRecord?orderID=${orderID}"></a>
			<h2 class="title">平开门复尺详情</h2>
			<a class="tit_rgt" href="javascript:void(0)">查看照片</a>
		</header><!-- /header -->
		<section class="font0 pad_top10">
			<p class="day font30 col_3">预计<fmt:formatDate type="date" value="${recheckScaleBill.planInstallDate }"/>日安装</p>
			<ul class="sec clearfix">
				<c:forEach items="${flatopenList}" var="flatopenList" varStatus="status">
				<li class="mar_top2 pad_left2 pad_rgt2 flo_left">
					<p class="pos font30 col_3">${fns:getDictLabel(flatopenList.position, 'recheck_flatopen_position', '')}</p>
					<ul class="bars bg_f pad_left2 pad_top2 pad_btm2 shadow">
						<li>
							<span class="font28 col_6">包套方式：</span>
							<%-- <c:if test="${flatopenList.packageCover =='0'}"><span class="font28 col_3">单　　包</span></c:if>
							<c:if test="${flatopenList.packageCover =='1'}"><span class="font28 col_3">双　　包</span></c:if> --%>
							<span class="font28 col_3">${fns:getDictLabel(flatopenList.packageCover, 'recheck_flatopen_packagecover', '')}</span>
						</li>
						<li>
							<span class="font28 col_6">开锁方向：</span>
							<%-- <c:if test="${flatopenList.inOutOpen =='0'}"><span class="font28 col_3">内　　开</span></c:if>
							<c:if test="${flatopenList.inOutOpen =='1'}"><span class="font28 col_3">外　　开</span></c:if> --%>
							<span class="font28 col_3">${fns:getDictLabel(flatopenList.inOutOpen, 'recheck_flatopen_inoutopen', '')}</span>
						</li>
						<li>
							<span class="font28 col_6">内外开向：</span>
							<%-- <c:if test="${flatopenList.openDirection =='0'}"><span class="font28 col_3">左锁右开</span></c:if>
							<c:if test="${flatopenList.openDirection =='1'}"><span class="font28 col_3">右锁左开</span></c:if> --%>
							<span class="font28 col_3">${fns:getDictLabel(flatopenList.openDirection, 'recheck_flatopen_opendirection', '')}</span>
						</li>
						<li>
							<span class="font28 col_6">宽　　度：</span>
							<span class="font28 col_3"><fmt:formatNumber value="${flatopenList.width }" pattern="#"/>mm</span>
						</li>
						<li>
							<span class="font28 col_6">高　　度：</span>
							<span class="font28 col_3"><fmt:formatNumber value="${flatopenList.height }" pattern="#"/>mm</span>
						</li>
						<li>
							<span class="font28 col_6">厚　　度：</span>
							<span class="font28 col_3"><fmt:formatNumber value="${flatopenList.thickness }" pattern="#"/>mm</span>
						</li>
					</ul>
				</li>
				</c:forEach>
			</ul>
		</section>
		<!-- 照片弹出层 -->
		<div class="mask mask1 undis">
			<div class="pic_container clearfix">
				<%-- <c:if test="${picList == null || fn:length(picList) == 0}">
					<div class="pic_wraper clearfix">
						<img src="${ctxStatic}/mobile/modules/Manager/images/photo.png" alt="套口照片">
					</div>
				</c:if> --%>
				<c:if test="${picList != null || fn:length(picList) > 0}">
				<c:forEach items="${picList}" var="pics" varStatus="status">
					<div class="pic_wraper clearfix">
						<img src="${prefixName }${pics.picUrl }" alt="套口照片">
					</div>
				</c:forEach>
				</c:if>
			</div>
			<a class="back" href="javascript:void(0)">返回</a>
		</div>
		<!-- 查看大图弹出层 -->
		<div class="mask mask2 undis">
			<div class="big_pic_wraper">
				<img class="big_pic" id="big_pic" alt="套口照片">
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
		$(function(){
			$(document).on('click','.tit_rgt',function(){
				$('.mask1').removeClass('undis');
			});
			$(document).on('click','.pic_wraper img',function(){
				var purl = $(this).attr("src");
				$("#big_pic").attr("src",purl);
				$('.mask2').removeClass('undis');
			});
			$(document).on('click','.mask2',function(){
				$('.mask2').addClass('undis');
			});
			$(document).on('click','.back',function(){
				$('.mask1').addClass('undis');
			});
		}());
	</script>
</body>
</html>