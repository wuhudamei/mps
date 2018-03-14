<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目进度查询</title>
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
		
		//按门店
		function nextValue(){
			//$("#nodeIndex").empty();
			//获取当前选中的值
			var storeIdSelected = $("#storeId option:selected").text();
			var storeIdValue = $("#storeId").val();
			//alert("storeIdSelected="+storeIdSelected +"\t" +"storeIdValue=="+storeIdValue);
			if(storeIdValue){
				$.ajax({
					url : "${ctx}/constructionschedule/bizConstructionSchedule/getByStoreIdList",
		        	type : "post",
		        	data : {storeId : storeIdValue},
		        	success : function(data){
		        		var htmls = "";//<option value='' selected='true'></option>
				   		for(var i = 0; i < data.length; i++){
				   			htmls += "<option value='"+data[i].sort+"'>"+data[i].constructionScheduleName+"</option>";
				   		}
				   		$("#nodeIndex").append(htmls);
					  }
				});
			}
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
		<li class="active"><a href="${ctx}/projectbulletin/projectBulletin/preList">项目进度列表</a></li>
		<%-- <shiro:hasPermission name="projectbulletin:projectBulletin:edit"><li><a href="${ctx}/projectbulletin/projectBulletin/form">订单添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="projectBulletin" action="${ctx}/projectbulletin/projectBulletin/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
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
			<li><label>新老房：</label> 
				<form:select path="houseIsNew" class="input-medium needClear">
					<form:option value="" label="">全部</form:option>
					<form:option value="0" label="">0 - 老房</form:option>
					<form:option value="1" label="">1 - 新房</form:option>
				</form:select>
			</li>
			<%-- <li><label>项目进度节点：</label> 
				<form:select path="nodeIndex" class="input-medium needClear" id="nodeIndex" name="nodeIndex">
					<form:option value="" label="">全部</form:option>
				</form:select>
			</li> --%>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="" class="input-medium"/>
			</li>
			<li><label>合同开工日期：</label>
				<input name="contractBegin" type="text" id="contractBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${projectBulletin.contractBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'contractEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="contractEnd" type="text" id="contractEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${projectBulletin.contractEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'contractBegin\')}',isShowClear:false});"/>
			</li>
			<li><label>实际开工日期：</label>
				<input name="actualBegin" type="text" readonly="readonly" id="actualBegin" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${projectBulletin.actualBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'actualEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="actualEnd" type="text" readonly="readonly" id="actualEnd" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${projectBulletin.actualEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'actualBegin\')}',isShowClear:false});"/>
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
				<th>订单编号</th>
				<th>工程模式</th>
				<th>门店编号</th>
				<th>新房/老房</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>合同工期</th>
				<th>合同开工日期</th>
				<th>实际开工日期</th>
				<th>当前完成进度</th>
				<!-- <th>进度图</th> -->
				<shiro:hasPermission name="projectbulletin:projectBulletin:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="projectBulletin">
			<tr>
				<td><%-- <a href="${ctx}/projectbulletin/projectBulletin/form?id=${projectBulletin.id}"> --%>
					${projectBulletin.orderNumber}
				</td>
				<td>${fns:getDictLabel(projectBulletin.projectMode, 'project_mode','')}</td>
				<td>
					<!-- 门店列表 -->
					${fns:getStoreLabel(projectBulletin.storeId, '')}
				</td>
				<td>
					<!-- 获取字典表 -->
					${fns:getDictLabel(projectBulletin.houseIsNew, 'house_is_new', '')}
				</td>
				<td>
					${projectBulletin.communityName}-${projectBulletin.buildNumber}-${projectBulletin.buildUnit}-${projectBulletin.buildRoom}-${projectBulletin.customerName}  
				</td>
				<td>
					${projectBulletin.managerName}
				</td>
				<td>
					<c:if test="${projectBulletin.contractTime != null}">
						${projectBulletin.contractTime}天
					</c:if>
					<c:if test="${projectBulletin.contractTime == null}">
						0天
					</c:if>
				</td>
				<td>
					<fmt:formatDate type="date" value="${projectBulletin.contractStartDate}"/>
				</td>
				<td>
					<fmt:formatDate type="date" value="${projectBulletin.actualStartDate}"/>
				</td>
				<td>
					${projectBulletin.nodeName}
				</td>
				<%-- <td>
					<a href="${ctx}/projectbulletin/projectBulletin/showViewNode?id=${projectBulletin.id}">查看</a>
				</td> --%>
				<shiro:hasPermission name="projectbulletin:projectBulletin:view"><td>
    				<a href="${ctx}/projectbulletin/projectBulletin/getByIdAndNodePlanOrderId?id=${projectBulletin.id}">详情</a>
					<%-- <a href="${ctx}/projectbulletin/projectBulletin/delete?id=${projectBulletin.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<script type="text/javascript">
	window.onload = function(){
		var storeIdSelected = $("#storeId option:selected").text();
		var storeIdValue = $("#storeId").val();
		//alert("storeIdSelected="+storeIdSelected +"\t" +"storeIdValue=="+storeIdValue);
		if(storeIdValue){
			$.ajax({
				url : "${ctx}/constructionschedule/bizConstructionSchedule/getByStoreIdList",
	        	type : "post",
	        	data : {storeId : storeIdValue},
	        	success : function(data){
	        		var htmls = "";//<option value='' selected='true'></option>
			   		for(var i = 0; i < data.length; i++){
			   			htmls += "<option value='"+data[i].sort+"'>"+data[i].constructionScheduleName+"</option>";
			   		}
			   		$("#nodeIndex").append(htmls);
				  }
			});
		}
	}
	</script>
</body>
</html>