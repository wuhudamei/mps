<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>图纸管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordercad/orderCadfile/list?orderId=${orderCadfile.orderId}&orderNumber=${orderCadfile.orderNumber}&flag=${orderCadfile.flag}">图纸列表</a></li>
		<shiro:hasPermission name="ordercad:orderCadfile:edit">
			<li><a
				href="${ctx}/ordercad/orderCadfile/form?orderId=${orderCadfile.orderId}&orderNumber=${orderCadfile.orderNumber}&flag=${orderCadfile.flag}">图纸添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orderCadfile"
		action="${ctx}/ordercad/orderCadfile/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input  name="flag" type="hidden" value="${orderCadfile.flag}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<%-- <li class="btns"><input id="btnCancel" class="btn btn-primary"
				type="button" value="返回订单列表"
				onclick="javascript:window.location.href='${ctx}/order/order/list'" /></li> --%>
				<li>
					<c:if test="${orderCadfile.flag == 1}">
						<input id="btnCancel" class="btn btn-primary" type="button" value="返回订单列表" onclick="javascript:window.location.href='${ctx}/bizprepareorder/bizPrepareOrder/list1'" />
					</c:if>
					<c:if test="${empty orderCadfile.flag}">
						<input id="btnCancel" class="btn btn-primary" type="button" value="返回订单列表" onclick="javascript:window.location.href='${ctx}/order/order/list'" />
					</c:if>
					<c:if test="${orderCadfile.flag == 2}">
						<input id="btnCancel" class="btn btn-primary" type="button" value="返回订单列表" onclick="javascript:window.location.href='${ctx}/projectOrderList/list'" />
					</c:if>
					
				</li>
				
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单图纸文件名:</th>
				<th>创建人:</th>
				<th>创建时间:</th>
				<th>版本:</th>
				<shiro:hasPermission name="ordercad:orderCadfile:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="orderCadfileval">


				<tr>
					<td>${orderCadfileval.fileName}</td>
						
					<td>${fns:getDictLabel(orderCadfileval.createBy, 'sys_user_type','')}</td>
						
					<td><fmt:formatDate value="${orderCadfileval.createDate}"
							pattern="yyyy-MM-dd" /></td>
					<td>${orderCadfileval.version}</td>
					<shiro:hasPermission name="ordercad:orderCadfile:edit">
						<td><a href="${ctx}/ordercad/orderCadfile/details?id=${orderCadfileval.id}&orderNumber=${orderCadfile.orderNumber}&flag=${orderCadfile.flag}">详情</a>
							<a href="${ctx}/ordercad/orderCadfile/delete?id=${orderCadfileval.id}&orderNumber=${orderCadfile.orderNumber}&flag=${orderCadfile.flag}"
							onclick="return confirmx('确认要删除该图片吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>