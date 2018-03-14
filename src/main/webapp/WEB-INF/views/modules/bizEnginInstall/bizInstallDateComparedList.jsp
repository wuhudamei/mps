<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>到场日期对比表查询</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#allCheck").click(function () {
	            if ($(this).attr("checked")) { // 全选 
	                $("input[name='installStatus']").each(function () {
	                    $(this).attr("checked", true);
	                });
	            } else { // 取消全选 
	                $("input[name='installStatus']").each(function () {
	                    $(this).attr("checked", false);
	                });
	            }
	        });	
		});
	
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//清空查询条件
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
		<li class="active"><a href="${ctx}/bizorderinstalldatecompared/bizOrderInstallDateCompared/preList">到场日期对比列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderInstallDateCompared" action="${ctx}/bizorderinstalldatecompared/bizOrderInstallDateCompared/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="nextValue()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li> --%>
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>安装项名称：</label>
				<form:input path="installItemName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="employeeRealName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>期望进场日期：</label>
				<input name="installApplyIntoDateStart" type="text" id="installApplyIntoDateStart" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderInstallDateCompared.installApplyIntoDateStart}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installApplyIntoDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="installApplyIntoDateEnd" type="text" id="installApplyIntoDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderInstallDateCompared.installApplyIntoDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installApplyIntoDateStart\')}',isShowClear:false});"/>
			</li>
			<li><label>实际进场日期：</label>
				<input name="installRealIntoDateStart" type="text" id="installRealIntoDateStart" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderInstallDateCompared.installRealIntoDateStart}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installRealIntoDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="installRealIntoDateEnd" type="text" id="installRealIntoDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderInstallDateCompared.installRealIntoDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installRealIntoDateStart\')}',isShowClear:false});"/>
			</li>
			<%-- <li><label>安装项状态：</label>
				<input type="checkbox" id="allCheck"/>全选
				<form:checkboxes path="installStatus" items="${fns:getDictList('install_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>安装项名称</th>
				<th>安装项状态</th>
				<th>期望进场日期</th>
				<th>实际进场日期</th>
				<th>延期天数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="order" varStatus="status">
			<tr>
				<td>${fns:getStoreLabel(order.storeId, '')}</td>
				<td>${fns:getDictLabel(order.projectMode, 'project_mode','')}</td>
				<td>${order.orderNumber}</td>
				<td>${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}-${order.customerName}</td>
				<td>${order.employeeRealName }</td>
				<td>${order.installItemName }</td>
				<td>${fns:getDictLabel(order.installStatus,'install_status','')}</td>
				<td><fmt:formatDate type="date" value="${order.installApplyIntoDate }"/></td>
				<td><fmt:formatDate type="date" value="${order.installRealIntoDate }"/></td>
				<td>
				<c:if test="${order.installDeplayDays == ''} "></c:if>
				<c:if test="${order.installDeplayDays != '' && order.installDeplayDays != null}">${order.installDeplayDays }天</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>