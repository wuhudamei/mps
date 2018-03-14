<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
	<meta name="decorator" content="default"/>
	<title>订单回访详情</title>
	<link rel="stylesheet" href="${ctxStatic}/modules/customerreturnvisit/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/modules/customerreturnvisit/css/list.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/list">回访记录列表</a></li>
	</ul><br/>
	<div class="header1">回访问卷-${nodeName}</div>
	<div class="wrap">
		<div class="basic">
			<h3>订单基本信息</h3>
			<p class="mt38">
				<span>客户姓名：<i>${order.customerName}</i></span>
				<span>客户电话：<i>${order.customerPhone}</i></span>
			</p>
			<p>
				<span>工程地址：<i>${order.detailAddress}</i></span>
				<span>验收时间：<i><fmt:formatDate value="${order.checkDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></i></span>
			</p>
			<p>
				<span>合同开工日期：<i><fmt:formatDate value="${order.contractStartDate}" pattern="yyyy-MM-dd"/></i></span>
				<span>合同竣工日期：<i><fmt:formatDate value="${order.contractEndDate}" pattern="yyyy-MM-dd"/></i></span>
			</p>
			<p>
				<span>设计师：<i>${order.designerName}</i></span>
				<span>设计师手机：<i>${order.designerPhone}</i></span>
			</p>
			<p>
				<span>项目经理：<i>${order.managerName}</i></span>
				<span>项目经理手机：<i>${order.managePhone}</i></span>
			</p>
			<p>
				<span>质检员：<i>${order.inspectorName}</i></span>
				<span>质检员手机：<i>${order.inspectorPhone}</i></span>
			</p>
			<p>
				<span>客服姓名: <i>${order.serviceName}</i></span>
				<span>客服手机：<i>${order.servicePhone}</i></span>
			</p>
		</div>
		<div class="question">
			<h3><span>回访问题</span><span>回访指标</span></h3>
			<c:forEach items="${ questionList}" var="item" varStatus="status">
				<p>
					<span class="quesle">${status.index + 1}.${item.returnVisitQuestion}</span>
					<span class="quesle">${item.questionAnswer}</span>
				</p>
			</c:forEach>
		</div>
		<div class="invalid">
			<h3>无效回访记录</h3>
			<c:forEach items="${invalidRecordList}" var="item">
				<div class="invalist">
					<p>
						<span class="quesle">无效回访时间：<fmt:formatDate value="${item.returnVisitTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						<span class="quesrg">回访人：${item.customServiceName }</span>
					</p>
					<div class="Remarks">
						<span>备注：</span>
						<p>${item.invalidReason }</p>
					</div>
				</div>		
			</c:forEach>
		</div>
		<p class="submit">
			<a href="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/list" style="margin-left: 40%;">返回</a>
		</p>
	</div>
	
</body>
</html>