<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta content="email=no" name="format-detection">
	<title>流程日志</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/orderComplaint/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/orderComplaint/complain.css"/>
	<style>
		.page_backBtn{position:absolute;top: 23px;left:10px;font-size: 15px;color: #333;padding: 3px 5px;}
	</style>
</head>
<script type="text/javascript">

function	fanhui(){

    window.location.href="${ctx}/ordercomplan/bizOrderComplaint/listall";

	}

</script>
<body>

	<div class="">
		<header><h2 class="title">订单作废</h2></header>
		
		<div class="Page_back">
			<input type="button" value="返回" class="page_backBtn" onclick="history.go(-1)">
		</div>
		
		<section class="con">
			<div>
				<div class="item pl152">订单信息</div>
				<div class="pl152 bg_e tab pt32 pb32 info">
					<p class="col3 f14 mb20">
						<span>订单编号：${bizOrderScrap.orderNumber}</span>
						<span class="indent1" style="width:60%;">顾客信息：${bizOrderScrap.communityName}-${bizOrderScrap.buildNumber}-${bizOrderScrap.buildUnit}-${bizOrderScrap.buildRoom}-${bizOrderScrap.customerName}</span>
					</p>
					<p class="col3 f14 mb20">
						<span>签约日期：<fmt:formatDate value="${bizOrderScrap.signContractDate}" pattern="yyyy-MM-dd "/></span>
						<span class="indent1">接单日期：<fmt:formatDate value="${bizOrderScrap.getOrderDatetime}" pattern="yyyy-MM-dd "/></span>
					</p>
					<p class="col3 f14 mb20">
						<span>合同开工日期：<fmt:formatDate value="${bizOrderScrap.contractStartDate}" pattern="yyyy-MM-dd "/></span>
						<span class="indent1">合同竣工日期：<fmt:formatDate value="${bizOrderScrap.contractEndDate}" pattern="yyyy-MM-dd "/></span>
					</p>
					<p class="col3 f14 mb20">
						<span>项目经理：${bizOrderScrap.itemManager}</span>
						<span class="indent1">设计师：${bizOrderScrap.designerName}</span>
					</p>
					<p class="col3 f14 mb20">
						<span>订单状态：${bizOrderScrap.orderStatusDescription}</span>
						<span class="indent1"></span>
					</p>
				</div>
			</div>
			<c:if test="${bizOrderScrap.isScrap eq 1}">
				<div>
					<div class="item pl152">作废原因</div>
					<div class="pl152 bg_e tab pt32 pb32 info">
						<p class="col3 f14 mb20 indent2">作废原因：${bizOrderScrap.scrapReasonTypeName}</p>
						<p class="col3 f14 mb20 indent2">作废说明：${bizOrderScrap.scrapDescribe}</p>
					</div>
				</div>
				<div>
					<div class="item pl152">操作记录</div>
					<div class="pl152 bg_e tab pt32 pb32 info">
						<p class="col3 f14 mb20 indent2">操作人：${bizOrderScrap.scrapOperatorEmployeeName}</p>
						<p class="col3 f14 mb20 indent2">操作时间：<fmt:formatDate value="${bizOrderScrap.scrapDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						<p class="col3 f14 mb20 indent2">订单节点：${bizOrderScrap.orderStatusDescription}</p>
					</div>
				</div>
			</c:if>
			
			
		</section>
		

	</div>


	<script type="text/javascript" src="${ctxStatic}/modules/orderComplaint/jquery-2.1.1.min.js"></script>
</body>
</html>