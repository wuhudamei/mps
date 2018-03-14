<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>施工项价格管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			$.each($("#valuationMethod option"),function(){
				
				var optionval = $(this).val();
				if(optionval == '${page.list[0].valuationMethod}'){
					 $("#s2id_valuationMethod").find(".select2-chosen").text($(this).text())
				}
				
			})
			
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
	<li><a href="${ctx}/projectitem/projectIntem/list">返回施工项</a></li>
		<li class="active"><a href="${ctx}/projectitemprice/projectItemPrice/list?projectIntemId=${itemId}">施工项价格列表</a></li>
		<shiro:hasPermission name="projectitemprice:projectItemPrice:edit"><li><a href="${ctx}/projectitemprice/projectItemPrice/form?projectIntemId=${itemId}&projectIntemCode=${page.list[0].projectIntemCode}&projectIntemName=${page.list[0].projectIntemName}&valuationMethod=${page.list[0].valuationMethod}">施工项价格添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute = "projectItemPrice" action="${ctx}/projectitemprice/projectItemPrice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<li><label>施工项编号：</label>
				<form:input path="projectIntemCode"  maxlength="100" class="input-medium needClear" value="${page.list[0].projectIntemCode}" disabled="true"/>
		</li>
		<li><label>施工项名称：</label>
				<form:input path="projectIntemName"  maxlength="100" class="input-medium needClear" disabled="true" value="${page.list[0].projectIntemName}"/>
		</li>
		<li><label>计价方式：</label>
				<form:select path="valuationMethod" class="input-xlarge required" disabled="true">
					<form:options items="${fns:getDictList('valuation_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
		</li>
	</form:form>
	<sys:message content="${message}"/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>销售单价或者总价占比 </th>
				<th>成本单价或者总成本占比</th>
				<th>版本号</th>
				<th>生效日期</th>
				<shiro:hasPermission name="projectitemprice:projectItemPrice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="projectItemPrice">
			<c:if test="${not empty projectItemPrice.storeId }">
		
			<tr>
				<td>
				${fns:getStoreLabel(projectItemPrice.storeId, '')}	
				</td>
				<td>
					${projectItemPrice.projectIntemPrice}
				</td>
				<td>
					${projectItemPrice.projectIntemCostPrice}
				</td>
				<td>
					${projectItemPrice.projectIntemVersion}
				</td>
				<td>
					<fmt:formatDate value="${projectItemPrice.effectDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="projectitemprice:projectItemPrice:edit"><td>
					<a href="${ctx}/projectitemprice/projectItemPrice/delete?itemPriceId=${projectItemPrice.itemPriceId}&projectIntemId=${itemId}" onclick="return confirmx('确认要删除该条价格记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>