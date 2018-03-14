<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理质保金明细</title>
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
		<li class="active"><a href="${ctx}/order2/order2/orderManagerGuranteeMoneyLoadList">项目经理质保金明细</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/order2/order2/orderManagerGuranteeMoneyLoadList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label style="width: 110px">扣质保金日期：</label>
				<input name="beginGeneratedDatetime" id="beginGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium needClear Wdate"
					   value="<fmt:formatDate value="${order.beginGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endGeneratedDatetime\')}',isShowClear:false});"/> -
				<input name="endGeneratedDatetime" id="endGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium needClear Wdate"
					   value="<fmt:formatDate value="${order.endGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginGeneratedDatetime\')}',isShowClear:false});"/>
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
				<th>序号</th>
				<th>门店</th>
				<th>小区名称</th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>扣质保金时间</th>
				<th>扣除质保金金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${fns:getStoreLabel(order.storeId, '')}</td>
				<td>${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}</td>
				<td>${order.customerName}</td>
				<td>${order.itemManager}</td>
				<td><fmt:formatDate value="${order.settleDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${order.guaranteeMoneyAmount}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>