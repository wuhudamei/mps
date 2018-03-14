<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务包计划</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findProjectMode();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function clearCondition(){
			
			 document.getElementById("searchForm").reset();
			
			
		}

	/* 	 function findEngineListByStoreIdAndProjectMode(){
			 	$("#engineDepartId").html("");
			 	$("#s2id_engineDepartId .select2-chosen").html("");
	            //根据门店个,工程模式    动态加载工程区域
	            $.ajax({
	            	url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
	            	type:'post',
					dataType:'json',
					data:{
						'storeId':$('#storeId').val(),
						'projectMode':$("#projectMode").val(),
						'employeeId':$('#employeeId').val()
					},
	                success : function(data) {
	                	if(data == null){
							$("#engineDepartId").append('');
						} else {
							var html = "<option value=''></option>";
							for(var i=0;i<data.length;i++){
								var sec = "";
								if('${orderTaskpackageAudit.engineDepartId}' == data[i].value){
				            		sec = "selected='selected'";
				            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
				            	}
								html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
							}
			        		$("#engineDepartId").append(html);
						}

	                }

	            });
	        } */
		 function findProjectMode(){
			 $("#engineDepartId").html("");
			 $("#s2id_engineDepartId .select2-chosen").html("");
			 $.ajax({
	            	url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
	            	type:'post',
					dataType:'json',
					data:{
						'storeId':$('#storeId').val(),
						'projectMode':$("#projectMode").val(),
						'employeeId':$('#employeeId').val()
					},
	                success : function(data) {
	                	if(data.length<1){
							$("#engineDepartId").append("<option value='' selected='selected' >请选择...</option>");
							$("#s2id_engineDepartId .select2-chosen").html("请选择...");
						} else {
							var html = "<option value=''>请选择...</option>";
							$("#s2id_engineDepartId .select2-chosen").html("请选择...");
							for(var i=0;i<data.length;i++){
								var sec = "";
								if('${orderVo.engineDepartId}' == data[i].value){
				            		$("#s2id_engineDepartId .select2-chosen").html(data[i].label);
				            		html += "<option value='" + data[i].value +"' selected='selected' >" + data[i].label + "</option>"
				            	}else{
				            		html += "<option value='" + data[i].value +"'>" + data[i].label + "</option>"
				            	}
							}
			        		$("#engineDepartId").append(html);
						}
	                }
	            });
		 }
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/taskpackageschedule/list">任务包计划列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderVo" action="${ctx}/taskpackageschedule/list2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findProjectMode()">
					<form:option value="" label="请选择..."/>
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="findProjectMode()">
						<form:option value="" label="请选择..."/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="findProjectMode()">
						<form:option value="" label="请选择..."/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>	
			</li>
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear">
				</form:select>
			</li>
			
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
		
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
		
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
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
				<th>区域</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>合同计划开工时间</th>
				<th>实际开工时间</th>
				<th>订单状态</th>
				<th>任务包计划</th>
				<shiro:hasPermission name="taskpackageschedule:taskpackageschedule:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order">
			<tr>
				<td>
					${fns:getStoreLabel(order.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(order.projectMode, 'project_mode', '')}
				</td>
				<td>${fns:getElacLabel(order.engineDepartId, '')}</td>
				<td>
					${order.orderNumber}
				</td>
				<td>
					${order.communityName}
				
					${order.buildNumber}-
				
					${order.buildUnit}-
				
					${order.buildRoom}-
					${order.customerName}
				
				<td>
					${order.itemManager}
				</td>
				<td>
					<fmt:formatDate value="${order.contractStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						<fmt:formatDate value="${order.actualStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${order.orderStatusDescription }
				</td>
				<td>
				<c:if test="${order.orderTaskPackStatus =='1' }">已生成</c:if>
				<c:if test="${order.orderTaskPackStatus =='0' }">未生成</c:if>
				
				</td>
				
				
				<shiro:hasPermission name="taskpackageschedule:taskpackageschedule:view"><td>
				<c:if test="${order.orderTaskPackStatus =='0' }"></c:if>
    					<c:if test="${order.orderTaskPackStatus =='1' }"><a href="${ctx}/taskpackageschedule/changepackageplan?orderId=${order.orderId}">计划调整</a></c:if>
    				
				</td></shiro:hasPermission>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
<script type="text/javascript">

</script>
</html>