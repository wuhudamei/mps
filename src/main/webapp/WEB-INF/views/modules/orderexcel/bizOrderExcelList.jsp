<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>材料清单</title>
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
		<li class="active"><a href="${ctx}/orderexcel/bizOrderExcel/list?orderId=${bizOrderExcel.orderId}&flag=${bizOrderExcel.flag}">材料清单</a></li>
		<shiro:hasPermission name="orderexcel:bizOrderExcel:edit"><li><a href="${ctx}/orderexcel/bizOrderExcel/form?orderId=${bizOrderExcel.orderId}&flag=${bizOrderExcel.flag}">材料清单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderExcel" action="${ctx}/orderexcel/bizOrderExcel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns">
			<c:if test = "${bizOrderExcel.flag == 2}">
				<input id="btnCancel" class="btn" type="button" value="返回列表" onclick="javascript:window.location.href='${ctx}/projectOrderList/list'"/>
			</c:if>
			<c:if test = "${empty bizOrderExcel.flag}">
				<input id="btnCancel" class="btn" type="button" value="返回订单列表" onclick="javascript:window.location.href='${ctx}/order/order/list'"/>
			</c:if>
			
			
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>文件名称</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>版本</th>
				<th>备注</th>
				<shiro:hasPermission name="orderexcel:bizOrderExcel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderExcel">
			<tr>
				<td>
					${orderExcel.fileName}
				</td>
				<td>
					${fns:getDictLabel(orderExcel.createBy, 'sys_user_type','')}
				</td>
				<td>
					<fmt:formatDate value="${orderExcel.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${orderExcel.version}
				</td>
				
				<td>
					${orderExcel.remarks}
				</td>
				<shiro:hasPermission name="orderexcel:bizOrderExcel:edit"><td>
    				<a href="${ctx}/orderexcel/bizOrderExcel/details?id=${orderExcel.id}&flag=${bizOrderExcel.flag}">详情</a>
					<a href="${ctx}/orderexcel/bizOrderExcel/delete?id=${orderExcel.id}&flag=${bizOrderExcel.flag}" onclick="return confirmx('确认要删除该excel文件上传吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>