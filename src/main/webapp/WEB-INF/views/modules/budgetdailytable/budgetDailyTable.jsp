<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生成任务包管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
		<li class="active"><a href="${ctx}/budgetdailytable/budgetDailyTable/preList">预算日报表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="budgetDailyTable" action="${ctx}/budgetdailytable/budgetDailyTable/list" method="post" class="breadcrumb form-search">
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" id="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" id="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li style="height:50px;">
				<label>日期：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${budgetDailyTable.startDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true});"/>
				&nbsp;&nbsp;
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${budgetDailyTable.endDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<span style="font-weight:bold;font-size:20px;">
		门店当前待生成任务包订单：<span style="color:red;">${storeStayCreatePkgOrdCount }</span>
		门店当前待审核任务包：<span style="color:red;">${storeStayAuditPkgCount }</span>
		<hr size="3px" noshade=true/>
	</span>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>区域</th>
				<th>当前未生成任务包订单数量</th>
				<th>当前未审核任务包数量</th>
				<th>选择日期已生成任务包订单数量</th>
				<th>选择日期已审核任务包数量</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="budgetDailyTable">
			<tr>
				<td>
					${budgetDailyTable.storeName }
				</td>
				<td>
					${budgetDailyTable.enginDepartName }
				</td>
				<td>
					${budgetDailyTable.currNotCreatePkgOrdCount }
				</td>
				<td>
					${budgetDailyTable.currNotAuditPkgCount }
				</td>
				<td>
					${budgetDailyTable.optDateCreatePkgOrdCount }
				</td>
				<td>
					${budgetDailyTable.optDateAuditPkgCount }
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>