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
		<header><h2 class="title">流程日志</h2></header>
		<section class="con">
			<div>
				<div class="item pl152">订单信息</div>
				<div class="pl152 bg_e tab pt32 pb32 info">
					<p class="col3 f14 mb20 indent2">门店：${fns:getStoreLabel(entity.storeId,'' )}</p>
					<p class="col3 f14 mb20">
						<span>客户姓名：${entity.customerName}</span>
						<span class="indent1">手机号：${entity.customerPhone}</span>
					</p>
					<p class="col3 f14 mb20">
						<span>项目经理：${entity.itemManager}</span>
						<span class="indent1">质检员：${entity.pqcName}</span>
					</p>
					<p class="col3 f14 mb20">
						<span>工程模式：${fns:getDictLabel(entity.projectMode,'project_mode' ,'' )}</span>
						<span>工程区域：${entity.departName}</span>
					</p>
					<p class="col3 f14 mb20">
						<span>订单编号：${entity.orderNumber}</span>
						<span>小区名称：${entity.detailAddress}</span>
					</p>
				</div>
			</div>
			<div>
				<div class="item pl152">投诉信息</div>
				<div class="pl152 bg_e clearfix">
					<div class="col3 f14 mb20 comp">
						<span class="compTit">投诉来源：${entity.complaintSource}</span>
						<span class="compTit">投诉人：${entity.complaintPersonName}</span>
						<span class="compTit">投诉人手机号：${entity.tsiphone}</span>
					</div>

					<c:forEach items="${list}" var="problem" varStatus="status">


						<div class="bor_d7 mb1d">
							<div class="lab">
								<p class="f16 col3 bold pb20">投诉问题${status.index+1}</p>
								<p class="col3 f14 mb20 clearfix">
									<span class="labItem">问题分类：${problem.typeName}</span>
									<span class="labItem">对应任务包：${problem.packName}</span>
									<span class="labItem">被投诉对象：${problem.complaintTo}</span>


									<span class="labItem">问题事项：&nbsp;
									<c:forEach items="${problem.itemList}" var="item">
										${item.itemName}
									</c:forEach>
										&nbsp;
											</span>
								</p>
								<div class="col3 f14 mb20 clearfix">
									<span class="fl">问题内容：</span>
									<p class="overflow">${problem.problemContent}</p>
								</div>
								<div class="col3 f14 pb20 msg bor_e4 clearfix">
									<span class="intent2">附件：<a class="picBtn" href="${ctx}/ordercomplan/bizOrderComplaint/formLogPic?problemId=${problem.problemId}">查看照片</a></span>
									<span class="">短信发送：已发</span>
									<span class="">接收时间：<fmt:formatDate value="${problem.infoDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
								</div>
							</div>


							<div class="lab">
								<p class="f16 col3 bold pb20">问题反馈</p>

								<c:forEach items="${problem.dealList}" var="deal">
									<c:if test="${ not empty deal.workerName}">
										<p class="col3 f14 mb20 clearfix">
											<span class="labItem">反馈人：工人-${deal.workerName}</span>
											<span class="labItem">答复时间：<fmt:formatDate value="${deal.workerDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </span>
										</p>
									</c:if>

									<c:if test="${not empty deal.replyDate}">
										<p class="col3 f14 mb20 clearfix">
											<span class="labItem">答复人：${deal.replyName}</span>
											<span class="labItem">答复时间：<fmt:formatDate value="${deal.replyDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
											<c:if test="${deal.isdatefla==0}">
											<span class="labItem">已超时：${deal.overDays} 天</span>
											</c:if>
											<c:if test="${deal.isdatefla==1}">
											<span class="labItem">未超时</span>
											</c:if>
										</p>

										<div class="col3 f14 mb20 clearfix">
											<span class="fl">答复内容：</span>
											<p class="overflow">${deal.replyContent}</p>
										</div>
										<div class="col3 f14 pb20 msg clearfix">
											<span class="intent2">附件：<a class="picBtn" href="${ctx}/ordercomplan/bizOrderComplaint/formLogPic?handleId=${deal.handleId}&handleType=${deal.dealPersonType}">查看照片</a></span>
										</div>

									</c:if>


								</c:forEach>

							</div>
						</div>


					</c:forEach>




					<%--<div class="bor_d7">--%>
						<%--<div class="lab">--%>
							<%--<p class="f16 col3 bold pb20">投诉问题2</p>--%>
							<%--<p class="col3 f14 mb20 clearfix">--%>
								<%--<span class="labItem">问题分类：水电调整</span>--%>
								<%--<span class="labItem">对应任务包：水电类</span>--%>
								<%--<span class="labItem">被投诉对象：项目经理 工人</span>--%>
								<%--<span class="labItem">问题事项：跳闸、断电</span>--%>
							<%--</p>--%>
							<%--<div class="col3 f14 mb20 clearfix">--%>
								<%--<span class="fl">问题内容：</span>--%>
								<%--<p class="overflow">问题是巴拉巴拉小魔仙巴拉巴拉小魔仙。2天内会完成修复，巴拉巴拉小魔仙巴拉巴拉小魔仙2天内会完成修复，巴拉巴拉小魔仙巴拉巴小魔仙2天内会完成修复，巴拉巴拉小魔仙巴拉巴拉小魔仙2天内会完成修复，巴拉巴拉小魔仙巴拉巴拉小魔仙2天内会完成修复，巴拉</p>--%>
							<%--</div>--%>
							<%--<div class="col3 f14 pb20 msg bor_e4 clearfix">--%>
								<%--<span class="indent2">附件：<a class="picBtn" href="javascript:;">查看照片</a></span>--%>
								<%--<span class="">短信发送：已发</span>--%>
							<%--</div>--%>
						<%--</div>--%>
						<%--<div class="lab">--%>
							<%--<p class="f16 col3 bold pb20">问题反馈</p>--%>
							<%--<p class="col3 f14 mb20 bor_e4 clearfix">--%>
								<%--<span class="labItem">反馈人：工人-张森</span>--%>
								<%--<span class="labItem">反馈时间：2017-06-27  19:20:36</span>--%>
							<%--</p>--%>
							<%--<p class="col3 f14 mb20 bor_e4 clearfix">--%>
								<%--<span class="labItem">反馈人：工人-张森</span>--%>
								<%--<span class="labItem">反馈时间：2017-06-27  19:20:36</span>--%>
							<%--</p>--%>
							<%--<p class="col3 f14 mb20 clearfix">--%>
								<%--<span class="labItem">答复人：项目经理-张森</span>--%>
								<%--<span class="labItem">答复时间：2017-06-27  19:20:36</span>--%>
							<%--</p>--%>
							<%--<div class="col3 f14 mb20 clearfix">--%>
								<%--<span class="fl">答复内容：</span>--%>
								<%--<p class="overflow">问题是巴拉巴拉小魔仙巴拉巴拉小魔仙。2天内会完成修复，巴拉巴拉小魔仙巴拉巴拉小魔仙2天内会完成修复，巴拉巴拉小魔仙巴拉巴小魔仙2天内会完成修复，巴拉巴拉小魔仙巴拉巴拉小魔仙2天内会完成修复，巴拉巴拉小魔仙巴拉巴拉小魔仙2天内会完成修复，巴拉</p>--%>
							<%--</div>--%>
							<%--<div class="col3 f14 pb20 msg clearfix">--%>
								<%--<span class="indent2">附件：<a class="picBtn" href="javascript:;">查看照片</a></span>--%>
							<%--</div>--%>
						<%--</div>--%>
					<%--</div>--%>

				</div>

			</div>

		</section>
		<div class="Page_back">
			<input type="button" value="返回" class="page_backBtn" onclick="window.location.href='${ctx}/ordercomplan/bizOrderComplaint/listall'">
		</div>

	</div>


	<script type="text/javascript" src="${ctxStatic}/modules/orderComplaint/jquery-2.1.1.min.js"></script>
</body>
</html>