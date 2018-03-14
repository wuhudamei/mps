<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>厂商复尺详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/swiper-3.3.1.min.css">
	<style>
		.back{font-size: 14px;margin-left:10px;padding: 5px;color: #666;border: 1px solid #333;border-radius: 2px;}
		.swiper-container{margin-top: 10px;}
		.swiper-slide{text-align: center;background: #EEE;}
		.swiper-slide img{padding-top:20px;}
	</style>
</head>
<body>
	<div class="g-photo">
		<ul class="nav nav-tabs">
			<li><a href="#" onclick="history.go(-1)">上传厂家复尺列表</a></li>
			<li class="active"><a href="javascript:vodi(0)">厂商复尺详情</a></li>
		</ul>
		<header>
			<!-- <h2 class="title"></h2> -->
			<!-- <a class="back" href="javascript:vodi(0)" onclick="history.go(-1)">返回</a> -->
		</header>
		<section class="swiper-container">
			<div class="breadcrumb ">
				<form:form class="breadcrumb form-search">
					<div class="breadcrumb form-search">
						<table style="width:100%" align="center" valign="center">
							<tr>
								<td style="text-align:center" >
									<font><h3>大美装饰管理平台--复尺单</h3></font>
								</td>
							</tr>
							<tr>
								<td colspan="2" style="width:100%"><label>项目地址：</label>
									${bizOrderChecksize.communityName }-${bizOrderChecksize.buildNumber }号楼-${bizOrderChecksize.buildUnit }单元-${bizOrderChecksize.buildRoom }
								</td>
							</tr>
							<tr>
								<td style="width:50%"><label>项目经理：</label>${bizOrderChecksize.itemManager }</td>
								<td  style="width:50%"><label>手机号：</label>${bizOrderChecksize.phone }</td>
							</tr>
							<tr>
								<td style="width:50%"> <label>复尺日期：</label>
									<fmt:formatDate value="${bizOrderChecksize.checksizeDate }" type="date"/>
								</td>
								<td style="width:50%"><label>复尺内容：</label>
									${bizOrderChecksize.checksizeTypeName}
								</td>
							</tr>
							<tr>
								<td style="width:50%"><label>主材复尺项：</label>
									${bizOrderChecksize.installItemName}
								</td>
								<td style="width:50%"><label>备注：</label>${bizOrderChecksize.remarks }</td>
							</tr>
							<tr>
								
								<td style="width:50%"><label>提交复尺日期：</label><fmt:formatDate value="${bizOrderChecksize.createDate }" type="both"/></td>
								<td style="width:50%"><label>复尺状态：</label>${bizOrderChecksize.checksizeStatusName }</td>
							</tr>
							<tr>
								
								
								<td style="width:50%"><label>材料部处理人：</label>${bizOrderChecksize.materialDepartmentDealEmployeeName }</td>
								<td style="width:50%"><label>材料部处理时间：</label><fmt:formatDate value="${bizOrderChecksize.materialDepartmentDealDatetime }" type="both"/></td>
							</tr>
							<tr>
								<td style="width:50%"><label>供应商确认日期：</label><fmt:formatDate value="${bizOrderChecksize.supplierConfirmDate }" type="date"/></td>
								<td colspan="2" style="width:100%"><label>回复：</label>${bizOrderChecksize.materialDepartmentDealReply }</td>
							</tr>
						</table>
					</div>
				</form:form>
			</div>
			<div class="swiper-wrapper">
			<c:forEach items="${picList }" var="picture">
				<div class="swiper-slide">
					<img src="${baseUrl }${picture.picUrl}" alt="图片加载中...">
				</div>
			</c:forEach>
			</div> 
			<div class="swiper-button-prev" id="prev_btn"></div>
    		<div class="swiper-button-next" id="next_btn"></div>
    		<div class="swiper-pagination"></div> 
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/swiper-3.3.1.jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/swiper.js"></script>
	<script>
		;(function(){
			$(document).ready(function(){
				var photoAll = $('.g-photo .swiper-slide').length;
			   	$('.g-photo h2.title').text(1 + '/' + photoAll);
				var mySwiper = new Swiper ('.swiper-container',{
			    	loop: false,
			    	nextButton: '.swiper-button-next',
		    		prevButton: '.swiper-button-prev',
		    		pagination : '.swiper-pagination',
					paginationType : 'fraction',
			    })
			})
		}());
	</script>
</body>
</html>