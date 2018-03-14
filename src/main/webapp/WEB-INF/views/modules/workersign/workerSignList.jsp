<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人签到查询</title>
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
		<li class="active"><a href="${ctx}/workersign/workerSign/">工人签到列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="workerSign" action="${ctx}/workersign/workerSign/list2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>工程模式</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			
			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="100" class="input-medium needClear "/>
			</li>
			<li><label>签到日期：</label>
				<input name="signDate1" id="signDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${signDate1}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'signDate2\')}',isShowClear:false});"/> &nbsp;至&nbsp;
				<input name="signDate2"  id="signDate2" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${signDate2}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'signDate1\')}',isShowClear:false});"/>
			</li>
			<li><label>工人姓名：</label>
				<form:input path="workerName" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			
			<li><label>误差(单位:米)：</label>
				<form:input path="conditionDistance1" htmlEscape="false" class="input-medium needClear" maxlength="7"   />&nbsp;至&nbsp;
					<form:input path="conditionDistance2" htmlEscape="false" class="input-medium needClear" maxlength="7"/>
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
				<th>签到日期时分秒</th>
				<th>工人姓名</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>订单编号</th>
				<th>顾客信息</th>
				<th>项目经理 </th>
				<th>签到地址</th>
				<th>误差(单位:米)</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="workerSign">
			<tr>
				<td> 
					<fmt:formatDate value="${workerSign.signDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<td>
					${workerSign.workerName}
				</td>
				<td>
					${workerSign.storeName}
				</td>
				<td>
					${fns:getDictLabel(workerSign.projectMode, 'project_mode', '')}
				</td>
				<td>
					${workerSign.orderNumber}
				</td>
				<td>
					${workerSign.customerInfo}
				</td>
				<td>
					${workerSign.managerName}
				</td>
				<td>
					${workerSign.signAddress}
				</td>
				<td>
					${workerSign.signDistance}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>