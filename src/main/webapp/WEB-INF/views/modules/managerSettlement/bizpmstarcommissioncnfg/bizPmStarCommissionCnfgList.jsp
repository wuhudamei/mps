<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理星级和提成设置管理</title>
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
		<li class="active"><a href="${ctx}/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfg/list">项目经理星级和提成设置列表</a></li>
		<shiro:hasPermission name="bizpmstarcommissioncnfg:bizPmStarCommissionCnfg:edit"><li><a href="${ctx}/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfg/form">项目经理星级和提成设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmStarCommissionCnfg" action="${ctx}/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfg/list2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>新老房</th>
				<th>星级</th>
				<th>提成金额</th>
				<th>中期提成比例 </th>
				<th>竣工提成比例</th>
				<shiro:hasPermission name="bizpmstarcommissioncnfg:bizPmStarCommissionCnfg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmStarCommissionCnfg">
			<tr>
				<td>
					${fns:getStoreLabel(bizPmStarCommissionCnfg.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizPmStarCommissionCnfg.isOldNew, 'biz_is_new_house', '')}
				</td>
				<td>
					${fns:getDictLabel(bizPmStarCommissionCnfg.starLevel, 'manager_star', '')}
				</td>
				<td>
					${bizPmStarCommissionCnfg.commissionAmount}
				</td>
				<td>
					${bizPmStarCommissionCnfg.commissionRateMidway}%
				</td>
				<td>
					${bizPmStarCommissionCnfg.commissionRateComplete}%
				</td>
				<shiro:hasPermission name="bizpmstarcommissioncnfg:bizPmStarCommissionCnfg:edit"><td>
    				<a href="${ctx}/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfg/form?id=${bizPmStarCommissionCnfg.id}">修改</a>
					<a href="${ctx}/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfg/delete?id=${bizPmStarCommissionCnfg.id}">
						<c:if test="${bizPmStarCommissionCnfg.isEnabled=='0' }"><span style="color:#00EC00;">启用</span></c:if>
						<c:if test="${bizPmStarCommissionCnfg.isEnabled=='1' }"><span style="color:red;">停用</span></c:if>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>