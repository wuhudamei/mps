<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>筒灯五金配送费管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("tbody>tr").click(function(){
                $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");
            });
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
		<li class="active"><a href="${ctx}/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/">筒灯五金配送费管理</a></li>
		<shiro:hasPermission name="bizmaterialsstandardshipfees:bizMaterialsStandardShippingFees:edit"><li><a href="${ctx}/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/form">添加配送费用</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialsStandardShippingFees" action="${ctx}/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId"  class="input-medium needClear" >
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>材料：</label>
				<form:select path="bizMaterialsStandardType" class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('bizMaterialsStandardType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>材料</th>
				<th>配送费用</th>
				<th>启用状态</th>
				<th>操作人</th>
				<th>操作时间</th>
				<shiro:hasPermission name="bizmaterialsstandardshipfees:bizMaterialsStandardShippingFees:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialsStandardShippingFees">
			<tr>
				<td><%--<a href="${ctx}/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/form?id=${bizMaterialsStandardShippingFees.id}">
					${fns:getStoreLabel(bizMaterialsStandardShippingFees.storeId, '')}
				</a>--%>
						${fns:getStoreLabel(bizMaterialsStandardShippingFees.storeId, '')}
				</td>
				<td>
						${fns:getDictLabel(bizMaterialsStandardShippingFees.bizMaterialsStandardType, 'bizMaterialsStandardType', '')}
				</td>
				<td>
					${bizMaterialsStandardShippingFees.shippingFees}
				</td>
				<td>
						${fns:getDictLabel(bizMaterialsStandardShippingFees.status, 'biz_enable_status', '')}
				</td>
				<td>
					${bizMaterialsStandardShippingFees.createName}
				</td>
				<td>
					<fmt:formatDate value="${bizMaterialsStandardShippingFees.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bizmaterialsstandardshipfees:bizMaterialsStandardShippingFees:edit"><td>
					<c:if test="${bizMaterialsStandardShippingFees.status!=1 && bizMaterialsStandardShippingFees.flag}">
    				<a href="${ctx}/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/updateStatus?id=${bizMaterialsStandardShippingFees.id}&&status=1"
					   onclick="return confirmx('确认要启用吗？', this.href)" >启用</a>
					</c:if>
					<c:if test="${bizMaterialsStandardShippingFees.status==1 }">
    				<a href="${ctx}/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/updateStatus?id=${bizMaterialsStandardShippingFees.id}&&status=0"
					   onclick="return confirmx('确认要停用吗？', this.href)" >停用</a>
					</c:if>

				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>