<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>大美装饰管理平台模板套餐</title>
	<link rel="stylesheet" href="${ctxStatic}/modules/orderReportRelatedContract/css/packahesChoice.css">
	<style type="text/css">
		.Page_back {
			float: right;
			margin-top: 20px;
			margin-bottom: 40px;
		}
		.Page_back .page_backBtn {
			border: 1px solid #1296db;
			background: #1296db;
			color: #fff;
			padding: 8px 20px;

		}
	</style>
</head>
<body>
	<div class="packagesChoice_warp">
		<div class="packagesChoice_warpper">
			<div class="packagesChoice_content">
				<div class="basic">
					<h2 class="col_blue mb20 mt33"><i class="icon_line"></i> <p class="pl16">基本信息</p></h2>
					<div class="basic_content">
						<ul class="clearfix">
							<li class="fl"><span>客户姓名 :</span><span class="ml12">${orderReport.customerName}</span></li>
							<li class="fl ml134"><span>客户手机 :</span><span class="ml12">${orderReport.customerPhone}</span></li>
						</ul>
						<ul class="clearfix">
							<li class="fl"><span>返单推荐人 :</span><span class="ml12">${orderReport.reporterName}</span></li>
							<li class="fl ml134"><span>返单推荐人手机号 :</span><span class="ml12">${orderReport.reporterPhone}</span></li>
						</ul>
						<ul class="clearfix">
							<li class="fl"><span>推荐时间 :</span><span class="ml12"> <fmt:formatDate value="${orderReport.reportDatetime}" pattern="yyyy-MM-dd"></fmt:formatDate></span></li>
							<li class="fl ml134 w61"><span class="fl">小区名称 :</span><span class="ml12 content_info_cont">${orderReport.communityName}</span></li>
						</ul>
						<ul class="clearfix info_cont">
							<li class="fl"><span class="fl">详细地址 :</span><span class="ml12 content_info_cont" >${orderReport.detailAddress}</span></li>
						</ul>
						<ul class="clearfix info_cont">
							<li class="fl"><span class="fl">备注 :</span><span class="ml12 content_info">${orderReport.reportRemarks}</span></li>
						</ul>
					</div>

				</div>
				<div class="basic  processesLog">
					<h2 class="col_blue mb20 mt33"><i class="icon_line"></i><p class="pl16">流程日志</p></h2>




					<c:if test="${orderReport.reportStatus==20}">
					<div class="process_warp">
						<h4 class="col_333 mb14">信息无效</h4>
						<div class="basic_content">
							<p>无效时间：<span><fmt:formatDate value="${orderReport.reportDatetime}" pattern="yyyy-MM-dd"></fmt:formatDate></span></p>
						</div>
					</div>
					</c:if>

					<c:if test="${not empty logList1}">

						<c:forEach items="${logList1}" var="item">
					<div class="process_warp mt40">
						<h4 class="col_333 mb14">分派客服</h4>
						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate value="${item.distributeServiceOperateDate}" pattern="yyyy-MM-dd"></fmt:formatDate></span></li>
								<li class="fl ml134"><span>操作人 :</span><span class="ml12">${item.distributeServiceOperateName}</span></li>
							</ul>
							<ul class="clearfix">
								<li class="fl"><span>分派客服 :</span><span class="ml12">${item.distributeServiceName}</span></li>
								<li class="fl ml134"><span>客服手机 :</span><span class="ml12">${item.distributeServicePhone}</span></li>
							</ul>
						</div>
					</div>

						</c:forEach>
					</c:if>



					<c:if test="${not empty logList2}">

					<c:forEach items="${logList2}" var="item">
					
					<div class="process_warp mt40">
						<h4 class="col_333 mb14">转派客服</h4>
						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate value="${item.transferServiceOperateDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </span></li>
								<li class="fl ml134"><span>操作人 :</span><span class="ml12">${item.transferServiceOperateName}</span></li>
							</ul>
							<ul class="clearfix">
								<li class="fl"><span>转派客服 :</span><span class="ml12">${item.transferServiceName}</span></li>
								<li class="fl ml134"><span>客服手机 :</span><span class="ml12">${item.transferServicePhone}</span></li>
							</ul>
						</div>
					</div>
					</c:forEach>
					</c:if>


					<c:if test="${not empty logList3}">

					<c:forEach items="${logList3}" var="item">
					<div class="process_warp mt40">
						<h4 class="col_333 mb14">客户进店未签单</h4>
						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate value="${item.inStoreOperateDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </span></li>
								<li class="fl ml134"><span>操作人 :</span><span class="ml12">${item.inStoreOperateName}</span></li>
							</ul>
							<ul class="clearfix info_cont">
								<li class="fl"><span>客户进店时间 :</span><span class="ml12"><fmt:formatDate value="${item.inStoreDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </span></li>
							</ul>
							<ul class="clearfix info_cont">
								<li class="fl"><span class="fl">备注 :</span><span class="ml12 content_info">${item.inStoreRemarks}</span></li>
							</ul>
							<ul class="clearfix info_cont">
								<li class="fl"><span class="fl">短信发送内容 :</span><span class="ml12 ">${item.inStorePhoneMessageContent}</span></li>

							</ul>
						</div>
					</div>
					</c:forEach>
					</c:if>


					<c:if test="${not empty logList4}">

					<c:forEach items="${logList4}" var="item">
					<div class="process_warp mt40">
						<h4 class="col_333 mb14">客户进店已签单</h4>
						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate value="${item.signOperateDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </span></li>
								<li class="fl"><span>操作人 :</span><span class="ml12">${item.signOperateName}</span></li>
							</ul>
							<ul class="clearfix">
								<li class="fl"><span>客户进店时间 :</span><span class="ml12"><fmt:formatDate value="${item.signDateTime}" pattern="yyyy-MM-dd"></fmt:formatDate></span></li>
								<li class="fl"><span>关联订单 :</span><span class="ml12">${item.signContractRelatedOrderNumbers}</span></li>
							</ul>
							<ul class="clearfix info_cont">
								<li class="fl"><span class="fl">备注 :</span><span class="ml12 content_info">${item.signRemarks}</span></li>
							</ul>
							<ul class="clearfix info_cont">
								<li class="fl"><span class="fl">短信发送内容 :</span><span class="ml12 ">${item.signPhoneMessageContent}</span></li>

							</ul>
						</div>
					</div>
					</c:forEach>
					</c:if>


					<c:if test="${not empty logList5}">

					<c:forEach items="${logList5}" var="item" varStatus="status">

					<div class="process_warp mt40">
						<h4 class="col_333 mb14">已签施工合同</h4>
						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate value="${item.signContractOperateDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </span></li>
								<li class="fl"><span>操作人 :</span><span class="ml12">${item.signContractOperateName}</span></li>
							</ul>
							<ul class="clearfix">
								<li class="fl"><span>签单时间 :</span><span class="ml12"><fmt:formatDate value="${item.signContractDateTime}" pattern="yyyy-MM-dd"></fmt:formatDate></span></li>
								<li class="fl"><span>关联合同 :</span><span class="ml12">${item.signContractRelatedOrderNumbers}</span></li>
							</ul>

							<ul class="clearfix info_cont">
								<li class="fl"><span class="fl">短信发送内容 :</span><span class="ml12 ">${item.signContractPhoneMessageContent}</span></li>

							</ul>
						</div>
					</div>

					</c:forEach>
					</c:if>

					<c:if test="${not empty logList6}">

					<c:forEach items="${logList6}" var="item">
					<div class="process_warp mt40">
						<h4 class="col_333 mb14">补签施工合同</h4>
						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate value="${item.replenishSignContractOperateDate}" pattern="yyyy-MM-dd"></fmt:formatDate></span></li>
								<li class="fl"><span>操作人 :</span><span class="ml12">${item.replenishSignContractOperateName}</span></li>
							</ul>
							<ul class="clearfix info_cont">
								<li class="fl"><span>关联合同 :</span><span class="ml12">${item.replenishSignContractRelatedOrderNumbers}</span></li>
							</ul>
							<ul class="clearfix info_cont">
								<li class="fl"><span class="fl">备注 :</span><span class="ml12 content_info">${item.replenishSignContractRemarks}</span></li>
							</ul>
							<ul class="clearfix info_cont">
								<li class="fl"><span class="fl">短信发送内容 :</span><span class="ml12 ">${item.replenishSignContractPhoneMessageContent}</span></li>

							</ul>
						</div>
					</div>
					</c:forEach>
					</c:if>


					<c:if test="${not empty logList7}">

					<c:forEach items="${logList7}" var="item">
						<div class="process_warp mt33">
							<h4 class="col_333 mb14"> 返单失效</h4>
							<div class="basic_content">
								<p>失效时间：<span><fmt:formatDate value="${item.outOfDateDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </span></p>
							</div>
						</div>
					</c:forEach>
					</c:if>

				</div>
				<div class="Page_back">
					<input type="button" value="返回" class="page_backBtn" onclick="window.location.href='${ctx}/orderReport/bizOrderReport/findByParam'">
				</div>
			</div>
		</div>

	</div>
</body>
</html>