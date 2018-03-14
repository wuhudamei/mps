<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算节点配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/">结算节点配置列表</a></li>
		<shiro:hasPermission name="bizsettlenodeconfig:bizNormalPmSettleNode:edit"><li><a href="${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/form">结算节点配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizNormalPmSettleNode" action="${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>结算类型</th>
				<th>结算节点</th>
				<th>验收阶段</th>
				<th>款项节点</th>
				<th>结算占比</th>
				<th>结算单价</th>
				<th>结算阶段</th>
				<th>是否必选</th>
				<th>操作人</th>
				<shiro:hasPermission name="bizsettlenodeconfig:bizNormalPmSettleNode:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizNormalPmSettleNode" varStatus="largeStatus" >
		
				<c:forEach items="${bizNormalPmSettleNode.childEntity}" var="childEntity" varStatus="status">
			<tr>
				<c:if test="${status.index eq 0}">
					<td rowspan="${bizNormalPmSettleNode.storeSettleNodeCount}">${largeStatus.index+1} </td>
					<td rowspan="${bizNormalPmSettleNode.storeSettleNodeCount}">${fns:getStoreLabel(bizNormalPmSettleNode.storeId, '')}</td>
					<td rowspan="${bizNormalPmSettleNode.storeSettleNodeCount}">${fns:getDictLabel(bizNormalPmSettleNode.projectMode,'project_mode','')}</td>
					<td rowspan="${bizNormalPmSettleNode.storeSettleNodeCount}">
						<c:if test="${bizNormalPmSettleNode.settleType eq 1}">
							百分比
						</c:if>
						<c:if test="${bizNormalPmSettleNode.settleType eq 2}">
							自定义
						</c:if>
					</td>
				</c:if>
				<td>
					${childEntity.settleNodeName}
				</td>
				<td>
					${childEntity.qcCheckNodeName}
				</td>
				<td>
					<c:if test="${childEntity.receiveMoneyType==2}">
						尾款
					</c:if>
					<c:if test="${childEntity.receiveMoneyType==1}">
						二期款
					</c:if>
				</td>
				<td>
					${childEntity.settleRule}
				</td>
				<td>
					${childEntity.settlePrice}
				</td>
				<td>
					${fns:getDictLabel(childEntity.settleStage, 'settlement_stage', '')}
				</td>
				<td>
					<c:if test="${childEntity.isRequired==2}">
						必选项
					</c:if>
					<c:if test="${childEntity.isRequired==1}">
					选填项
					</c:if>
				</td>
				<td >
					<c:if test="${empty childEntity.createMan}">
							最高管理员
					</c:if>
					<c:if test="${not empty childEntity.createMan}">
						${childEntity.createMan}
					</c:if>
				</td>
				<c:if test="${status.index==0}">
					<td rowspan="${bizNormalPmSettleNode.storeSettleNodeCount}">
						<a href="${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/form?storeId=${bizNormalPmSettleNode.storeId}&projectMode=${bizNormalPmSettleNode.projectMode}&settleType=${bizNormalPmSettleNode.settleType}">修改</a>
						<a href="${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/delete?storeId=${bizNormalPmSettleNode.storeId}" onclick="return confirmx('确认要删除该门店的结算节点配置吗？', this.href)">删除</a>
					</td>
				</c:if>

			</tr>
				</c:forEach>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>