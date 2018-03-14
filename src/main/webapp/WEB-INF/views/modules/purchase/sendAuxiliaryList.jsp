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
		<li class="active"><a href="${ctx}/purchase/bizPurchase/list">辅材送货列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPurchaseVo" action="${ctx}/purchase/bizPurchase/sendAuxiliaryList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li> --%>
			<%-- <li><label>采购单编码：</label>
				<form:input path="purchaseCode" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li> --%>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label style="width:120px">要求送货到场日期：</label>
				<input name="beginApplyReceiveTime" type="text" id="beginApplyReceiveTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizPurchaseVo.beginApplyReceiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endApplyReceiveTime\')}',isShowClear:false});"/> - 
				<input name="endApplyReceiveTime" type="text" id="endApplyReceiveTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizPurchaseVo.endApplyReceiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginApplyReceiveTime\')}',isShowClear:false});"/>
			</li>
			<li><label>提交申请日期 ：</label>
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
				<th>项目经理申请时间 </th>
				<th>采购单号 </th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>期望到货日期</th>
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
					<fmt:formatDate value="${bizPurchaseVo.applyTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizPurchaseVo.purchaseCode}
				</td>
				<td>
					${bizPurchaseVo.communityName }-${bizPurchaseVo.buildNumber }-${bizPurchaseVo.buildUnit }-${bizPurchaseVo.buildRoom }-${bizPurchaseVo.customerName }
				</td>
				<td>
					${bizPurchaseVo.itemManager }
				</td>
				<td>
					<fmt:formatDate value="${bizPurchaseVo.applyReceiveTime}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="purchase:bizPurchase:edit"><td>
    				<a href="${ctx}/purchase/bizPurchase/details?id=${bizPurchaseVo.id}">查看明细</a>
					<a href="${ctx}/purchase1/purchase1/updateStatus?id=${bizPurchaseVo.id}&status=50">
						已送货
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>