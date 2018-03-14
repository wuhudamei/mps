<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>约检问题详情</title>
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
							<li class="fl"><span>订单编号 :</span><span class="ml12">${map.orderNumber}</span></li>
							<li class="fl ml134"><span>客户名称 :</span><span class="ml12">${map.customerName}</span></li>
						</ul>
						<ul class="clearfix">
							<li class="fl"><span>客户地址 :</span><span class="ml12">${map.customerInfo}</span></li>
							<li class="fl ml134"><span>项目经理 :</span><span class="ml12">${map.managerName}</span></li>
						</ul>
						<ul class="clearfix">
							<li class="fl"><span>质检员 :</span><span class="ml12">${map.pqcName}</span></li>
							<li class="fl ml134 w61"><span class="fl">约检日期 :</span><span class="ml12 content_info_cont">${map.qcExpectCheckDate}</span></li>
						</ul>
						<ul class="clearfix info_cont">
							<li class="fl"><span class="fl">上报日期 :</span><span class="ml12 content_info">${map.problemCreateDate}</span></li>
						</ul>
					</div>

				</div>
				<div class="basic  processesLog">
					<h2 class="col_blue mb20 mt33"><i class="icon_line"></i><p class="pl16">问题上报</p></h2>





					<div class="process_warp mt40">

						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>问题类型 :</span><span class="ml12">${map.typeName} </span></li>
							</ul>

						</div>
					</div><div class="process_warp mt40">

						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>备注 :</span><span class="ml12">${map.remarks}</span></li>
							</ul>

						</div>
					</div>

					<div class="process_warp mt40">

						<div class="basic_content">
							<ul class="clearfix">
								<li class="fl"><span>延期天数 :</span><span class="ml12"><fmt:formatNumber value=" ${map.delayDays}" pattern="0"></fmt:formatNumber> 天</span></li>
							</ul>

						</div>
					</div>

				</div>
				<div class="Page_back">
					<input type="button" value="返回" class="page_backBtn" onclick="window.location.href='${ctx}/bizbusinessproblemquery/bizBusinessProblemQuery/list'">
				</div>
			</div>
		</div>

	</div>
</body>
</html>