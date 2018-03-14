<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安装项计划查询列表</title>
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
		<li class="active"><a href="${ctx}/bizinstallplanquery/installPlanQuery/">安装项计划查询列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="installPlanQuery" action="${ctx}/bizinstallplanquery/installPlanQuery/list2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
		<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
					<form:select path="projectMode" class="input-medium needClear" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				
					
			</li>
		
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
		
			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>生成安装计划日期：</label>
				<input name="startDate" type="text" readonly="readonly"     value='<fmt:formatDate value="${startDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="beginCheckDatetime" maxlength="20" class="input-medium Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCheckDatetime\')}',isShowClear:false});"/>
				<input name="endDate" type="text" readonly="readonly" value='<fmt:formatDate value="${endDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="endCheckDatetime" maxlength="20" class="input-medium Wdate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCheckDatetime\')}',isShowClear:false});"/>   
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
				<th>生成安装计划日期</th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>合同开工日期</th>
				<th>实际开工日期</th>
				<th>安装项数</th>
				<shiro:hasPermission name="bizinstallplanquery:installPlanQuery:view"><th>查看</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="installPlanQuery">
		<c:if test="${installPlanQuery.installCount!=0 }">
			<tr>
			<td>
                    ${fns:getStoreLabel(installPlanQuery.storeId, '')}
            </td>
				
			<td>${fns:getDictLabel(installPlanQuery.projectMode, 'project_mode', '')}</td>	
				
				<td>
					<fmt:formatDate value="${installPlanQuery.generateInstallPlanTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>${installPlanQuery.communityName }-${installPlanQuery.buildNumber }-${installPlanQuery.buildUnit }-${installPlanQuery.buildRoom}-${installPlanQuery.customerName }</td>		
				<td>${installPlanQuery.managerName}</td>
				<td><fmt:formatDate value="${installPlanQuery.contractStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${installPlanQuery.actualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${installPlanQuery.installCount}</td>
				
				<shiro:hasPermission name="bizinstallplanquery:installPlanQuery:view"><td>
    				<a href="${ctx}/bizinstallplanquery/installPlanQuery/form?id=${installPlanQuery.orderId}">查看</a>
				</td></shiro:hasPermission>
			</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>