<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>网真对账单详情</title>
<meta name="decorator" content="default" />
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
	   <li><a href="${ctx}/verify/bizAuxiliaryMaterialsVerify/wangzhenVerifyList">对账单列表</a></li>
		<li class="active"><a
			href="#">对账单详情</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizAuxiliaryMaterialsVerifyInclude"
		action="${ctx}/verify/bizAuxiliaryMaterialsVerify/verifyAuxiliaryDetail" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input type="hidden" id="groupType" name="groupType" value="${groupType}" />
		<input type="hidden" id="auxiliaryMaterialsVerifyId" name="auxiliaryMaterialsVerifyId" value="${auxiliaryMaterialsVerifyId}" />
	</form:form>
	<div class="control-group">
		<label class="control-label">辅料总金额：</label>
		<span>${verify.auxiliaryMaterialsWangzhenPriceAmount}</span>&nbsp;&nbsp;&nbsp;&nbsp;
		<label class="control-label">账单生成日期：</label>
		<span><fmt:formatDate value="${verify.generateDatetime}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;&nbsp;&nbsp;
		<label class="control-label">供应商：</label>
		<span>${verify.supplierName}</span>
		
	</div>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>辅料商品编号</th>
				<th>辅料商品名称</th>
				<th>规格型号</th>
				<th>单位</th>
				<th>供应商价格</th>
				<th>门店价格</th>
				<th>使用数量</th>
				<th>总价</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="verifyAuxi" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${verifyAuxi.auxiliaryMaterialsNo}</td>
					<td>${verifyAuxi.auxiliaryMaterilasName}</td>
					<td>${verifyAuxi.specifications}</td>
					<td>${fns:getDictLabel(verifyAuxi.measurementUnit, 'biz_material_unit', '')}</td>
					<td>${verifyAuxi.auxiliaryMaterialsSupplierPrice}</td>
					<td>${verifyAuxi.auxiliaryMaterialsWangzhenPrice}</td>
					<td>${verifyAuxi.auxiliaryMaterialsCount}</td>
					<td>${verifyAuxi.auxiliaryMaterialsWangzhenPriceAmount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="pagination">${page}</div>
</body>
</html>