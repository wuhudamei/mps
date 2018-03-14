<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>项目进度详情</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/projectbulletin/projectBulletin/list">项目进度列表</a></li>
		<li class="active"><a href="">项目进度详情</a></li>
		<%-- <shiro:hasPermission name="projectbulletin:projectBulletin:edit"><li><a href="${ctx}/projectbulletin/projectBulletin/form">订单添加</a></li></shiro:hasPermission> --%>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
				<th>顾客</th>
				<th>合同工期</th>
				<th>合同开工日期</th>
				<th>实际开工日期</th>
				<th>进度节点名称</th>
				<th>实际完工日期</th>
				<th>完工实景图</th>
				<%-- <shiro:hasPermission name="projectbulletin:projectBulletin:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pbList}" var="projectBulletin">
			<tr>
				<td><%-- <a href="${ctx}/projectbulletin/projectBulletin/form?id=${projectBulletin.id}"> --%>
					${projectBulletin.orderNumber}
				</a></td>
				<%-- <td>
					<!-- 门店列表 -->
					${fns:getStoreLabel(projectBulletin.storeId, '')}
				</td>
				<td>
					<!-- 获取字典表 -->
					${fns:getDictLabel(projectBulletin.houseIsNew, 'house_is_new', '')}
				</td> --%>
				<td>
					${projectBulletin.communityName} - ${projectBulletin.buildNumber} - ${projectBulletin.buildUnit} - ${projectBulletin.buildRoom} - ${projectBulletin.customerName}  
				</td>
				<%-- <td>
					${projectBulletin.itemManager}
				</td> --%>
				<td>
					<c:if test="${projectBulletin.contractTime != null}">
						${projectBulletin.contractTime}天
					</c:if>
					<c:if test="${projectBulletin.contractTime == null}">
						0天
					</c:if>
				</td>
				<td>
					<fmt:formatDate type="date" value="${projectBulletin.contractStartDate}"/>
				</td>
				<td>
					<fmt:formatDate type="date" value="${projectBulletin.actualStartDate}"/>
				</td>
				<td>
					${projectBulletin.nodeName}
				</td>
				<td>
					<fmt:formatDate type="date" value="${projectBulletin.nodeRealDoneDate}"/>
				</td>
				<td>
					<c:if test="${projectBulletin.nodePlanPicCount == null }">0</c:if>
					<c:if test="${projectBulletin.nodePlanPicCount != null }">
						<a href="${ctx}/biznodeplanpic/bizNodePlanPic/showPhoto?id=${projectBulletin.nodeId }&orderId=${projectBulletin.id }">
							${projectBulletin.nodePlanPicCount }
						</a>
					</c:if>张
				</td>
				<%-- <shiro:hasPermission name="projectbulletin:projectBulletin:view"><td>
    				<a href="${ctx}/projectbulletin/projectBulletin/getByIdAndNodePlanOrderId?id=${projectBulletin.id}">详情</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>