<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安装项日志查询</title>
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
		<li class="active"><a href="${ctx}/bizorderinstalldetail/bizOrderInstallDetail/preList">安装项明细列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderInstallDetail" action="${ctx}/bizorderinstalldetail/bizOrderInstallDetail/listInstall" method="post" class="breadcrumb form-search">
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
			<li><label>区域：</label>
				<form:select path="acceptArea" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
			<li><label>工人组长：</label>
				<form:input path="elpGroupName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
		
		
<!-- 			<li><label>供應商a：</label> -->
<%-- 				<form:select path="acceptArea" class="input-medium needClear" id="engineDepartSelect"> --%>
<%-- 					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
<%-- 				</form:select> --%>
<!-- 			</li> -->
				<li><label>安装项名称：</label>
				<form:input path="installItemName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li>
			<label></label>
			</li>
			<li><label>安装项状态：</label>
				<input type="checkbox" id="allCheck"/>全选
				<form:checkboxes path="installStatus" items="${fns:getDictList('install_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>门店</th>
				<th>工程模式</th>
				<th>安装模式</th>
				<th>订单编号</th>
				<th>小区</th>
				<th>客户姓名</th>
				<th>安装项名称</th>
				<th>安装项状态</th>
				<th>工人组</th>
				<th>项目经理申请日期</th>
				<th>材料部转供应商日期</th>
				<th>供应商派工人日期</th>
				<th>工人签到日期</th>
				<th>工人申请完工日期</th>
				<th>项目经理验收时间</th>
				<th>操作 </th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="order" varStatus="status">
			<tr>
				<td>${fns:getStoreLabel(order.storeId, '')}</td>
				<td>${fns:getDictLabel(order.projectMode, 'project_mode','')}</td>
				<td>${fns:getDictLabel(order.installMode, 'install_mode','')}</td>
				<td>${order.orderNumber}</td>
				<td>${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}</td>
				<td>${order.customerName}</td>
				<td>${order.installItemName }</td>
				<td>${fns:getDictLabel(order.installStatus,'install_status','')}</td>
				<td>${order.elpGroupName }</td>
				<td><fmt:formatDate type="both" value="${order.applyDate }"/></td>
				<td><fmt:formatDate type="date" value="${order.materialforSuppDate }"/></td>
				<td><fmt:formatDate type="date" value="${order.supplierIntoDate }"/></td>
				<td><fmt:formatDate type="date" value="${order.groupSignDate }"/></td>
				<td><fmt:formatDate type="date" value="${order.elpSuccessDate }"/></td>
				<td><fmt:formatDate type="date" value="${order.manageSuccessDate }"/></td>
	
				<td><a href="${ctx}/enginInstallNew/enginInstallNew/installLogOperation?installId=${order.installID}&orderID=${order.id}&projectMode=${order.projectMode}&installMode=${order.installMode}">日志</a></td>
<%-- 				<td><a href="${ctx}/bizengininstall/bizEnginInstall/urgeLog?id=${order.installID}&orderID=${order.id}&projectMode=${order.projectMode}">日志</a></td> --%>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>