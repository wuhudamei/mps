<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购单管理</title>
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
		<li class="active"><a href="${ctx}/purchase/bizPurchase/sand/listPage">沙子水泥申请列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPurchaseVo" action="${ctx}/purchase/bizPurchase/sand/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${not empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium"  >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>采购单编码：</label>
				<form:input path="purchaseCode" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>订单是否作废：</label>
			<form:select path="isScrap"   class="input-medium needClear" >
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('dict_iscountsquare')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>	
			</li>
			<li><label>供应商名称：</label>
				<form:select path="supplierId"  style="width:180px" class="input-xlarge needClear" Style="" >
					<form:option value="" label=""/>
					<c:forEach items="${bizSupplierList }" var="supplier">
						<form:option value="${supplier.supplierId }" label="${supplier.supplierName }"/>
					</c:forEach>
					<%-- <form:options items="${fns:getSupplierList()}" itemLabel="supplierName" itemValue="id" htmlEscape="false"/> --%>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('purchase_auxiliary_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>期望送货日期：</label>
				<input name="beginApplyReceiveTime" type="text" id="beginApplyReceiveTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizPurchaseVo.beginApplyReceiveTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endApplyReceiveTime\')}',isShowClear:false});"/> - 
				<input  name="endApplyReceiveTime" type="text" id="endApplyReceiveTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizPurchaseVo.endApplyReceiveTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginApplyReceiveTime\')}',isShowClear:false});"/>
			</li>
			<li><label>提交申请时间 ：</label>
				<input name="beginApplyTime" type="text" id="beginApplyTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizPurchaseVo.beginApplyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endApplyTime\')}',isShowClear:false});"/> - 
				<input name="endApplyTime" type="text" id="endApplyTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizPurchaseVo.endApplyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginApplyTime\')}',isShowClear:false});"/>
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
				<th>工程模式</th>
				<th>申请时间 </th>
				<th>订单编号</th>
				<th>采购单号 </th>
				<th>供应商名称 </th>
				<th>供应商联系人 </th>
				<th>客户信息</th>
				<th>订单是否作废</th>
				<th>申请人（项目经理）</th>
				<th>期望到货日期</th>
				<th>状态 </th>
				<shiro:hasPermission name="purchase:bizPurchase:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPurchaseVo">
			<tr>
				<td>
					${fns:getStoreLabel(bizPurchaseVo.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizPurchaseVo.projectMode, 'project_mode', '')}
				</td>
				<td>
					<fmt:formatDate value="${bizPurchaseVo.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
					<td>
					${bizPurchaseVo.orderNumber}
				</td>
				<td>
					${bizPurchaseVo.purchaseCode}
				</td>
				<td>
					${bizPurchaseVo.supplierName}
				</td>
				<td>
					${bizPurchaseVo.supplierContacts}</br>${bizPurchaseVo.supplierContactsPhone}
				</td>
				<td>
					${bizPurchaseVo.communityName }-${bizPurchaseVo.buildNumber }-${bizPurchaseVo.buildUnit }-${bizPurchaseVo.buildRoom }-${bizPurchaseVo.customerName }
				</td>
				<td>
				${fns:getDictLabel(bizPurchaseVo.isScrap, 'dict_iscountsquare', '')}
				</td>
				<td>
					${bizPurchaseVo.applyName }${bizPurchaseVo.applyEmployeePhone }
				</td>
				<td>
					<fmt:formatDate value="${bizPurchaseVo.applyReceiveTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(bizPurchaseVo.status, 'purchase_auxiliary_status', '')}
				</td>
				<shiro:hasPermission name="sand:sand:view"><td>
    				<a href="${ctx}/purchase/bizPurchase/sand/details?id=${bizPurchaseVo.id}">查看明细</a>
					<c:if test="${bizPurchaseVo.status eq 10 }">
						<a href="${ctx}/purchase1/purchase1/reviseSandStatus?id=${bizPurchaseVo.id}&status=40">
							转给供应商
						</a>
					</c:if>
					<c:if test="${bizPurchaseVo.status eq 10 || bizPurchaseVo.status eq 40}">
						<a href="${ctx}/purchase1/purchase1/reviseSandStatus?id=${bizPurchaseVo.id}&status=21">
							废弃
						</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>