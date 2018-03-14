<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理</title>
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
		
        function clearCondition(){
			 document.getElementById("searchForm").reset();
			
			 var inputObjs=jQuery("#searchForm input[type='text']"); 
			 for(var i=0;i<inputObjs.length;i++){ 
			 var inputObj = inputObjs[i]; 
			 inputObj.value=""; 
			 } 
			
			 var selectObjs = jQuery("#searchForm input[class='input-medium']"); 
			 for(var i=0;i<selectObjs.length;i++){ 
			 var selectObj = selectObjs[i]; 
			 selectObj.value=""; 
			 } 
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/supplier/bizSupplier/">供应商列表</a></li>
		<shiro:hasPermission name="supplier:bizSupplier:edit"><li><a href="${ctx}/supplier/bizSupplier/form">供应商添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizSupplier" action="${ctx}/supplier/bizSupplier/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>业务分类：</label>
				<form:select  path="installitemtype"   cssStyle="width:190px"    class="input-medium needClear">
					 <form:option value="" label=""/>
					 <form:options items="${fns:getDictList('install_item_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>供应商名称：</label>
				<form:input path="supplierName" htmlEscape="false" maxlength="255" class="input-medium needClear" />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>业务分类</th>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>邮箱</th>
				<th>状态</th>
				<shiro:hasPermission name="supplier:bizSupplier:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizSupplier">
			<tr>
				<td>
					${fns:getDictLabel(bizSupplier.installitemtype, 'install_item_type', '未知')}
				</td>
				<td><a href="${ctx}/supplier/bizSupplier/form?id=${bizSupplier.id}">
					${bizSupplier.supplierNo}
				</a></td>
				<td>
					${bizSupplier.supplierName}
				</td>
				<td>
					${bizSupplier.contacts}
				</td>
				<td>
					${bizSupplier.contactsPhone}
				</td>
				<td>
					${bizSupplier.email}
				</td>
				<td>
					${fns:getDictLabel(bizSupplier.status, 'biz_enable_status', '未知')}
				</td>
				<shiro:hasPermission name="supplier:bizSupplier:edit"><td>
				<a href="${ctx}/supplier/bizSupplier/form?id=${bizSupplier.id}">修改</a>
						<c:if test="${bizSupplier.status==1}">
							<a href="${ctx}/supplier/bizSupplier/enable?id=${bizSupplier.id}" onclick="return confirmx('确认要停用吗？', this.href)"><span style="color:red;">停用</span></a>
						</c:if>
						<c:if test="${bizSupplier.status==0}">
							<a href="${ctx}/supplier/bizSupplier/enable?id=${bizSupplier.id}" onclick="return confirmx('确认要启用吗？', this.href)"><span style="color:#00EC00;">启用</span></a>
						</c:if>
					<%-- <a href="${ctx}/supplier/bizSupplier/delete?id=${bizSupplier.id}" onclick="return confirmx('确认要删除该供应商吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>