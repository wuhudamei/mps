<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选材变更单表管理</title>
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
		<li class="active"><a href="${ctx}/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBill/preList">选材变更单表列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialsChoiceChangeBill" action="${ctx}/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBill/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label>客户地址：</label>
				<form:input path="communityName" htmlEscape="false" maxlength="32" class="input-medium needClear"/>
			</li>
			<li><label>客户手机号：</label>
				<form:input path="customerPhone" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			<li><label>设计师：</label>
				<form:input path="orderDesignerName" htmlEscape="false" maxlength="12" class="input-medium needClear"/>
			</li>
			<li><label>设计师手机号：</label>
				<form:input path="orderDesignerPhone" htmlEscape="false" maxlength="12" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>变更单号：</label>
				<form:input path="changeBillCode" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
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
				<th>订单编号</th>
				<th>变更单号</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>客户手机号</th>
				<th>设计师姓名</th>
				<th>设计师电话</th>
				<th>项目经理</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialsChoiceChangeBill">
			<tr>
				<td>
					${bizMaterialsChoiceChangeBill.storeName}
				</td>
				<td>
					${bizMaterialsChoiceChangeBill.orderNumber}
				</td>
				<td>
					${bizMaterialsChoiceChangeBill.changeBillCode}
				</td>
				<td>
					${bizMaterialsChoiceChangeBill.customerName}
				</td>
				<td>
					${bizMaterialsChoiceChangeBill.communityName}-${bizMaterialsChoiceChangeBill.buildNumber}-${bizMaterialsChoiceChangeBill.buildUnit}-${bizMaterialsChoiceChangeBill.buildRoom}
				</td>
				<td>
					${bizMaterialsChoiceChangeBill.customerPhone}
				</td>
				<td>
					${bizMaterialsChoiceChangeBill.orderDesignerName}
				</td>
				<td>
					${bizMaterialsChoiceChangeBill.orderDesignerPhone}
				</td>
				<td>
					${bizMaterialsChoiceChangeBill.itemManager}
				</td>
				<td>
    				<a href="${ctx}/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBill/materialsChoiceChangeBillDetail?id=${bizMaterialsChoiceChangeBill.id}">变更单</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>