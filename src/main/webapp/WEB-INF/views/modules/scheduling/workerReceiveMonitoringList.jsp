<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>派工管理管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		findEngineList();
	});
	function page(n, s) {
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
	
	function findEngineList(){
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
							if('${orderTaskpackage.engineDepartId}' == data[i].value){
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
		<li class="active"><a
			href="${ctx}/scheduling/orderTaskpackage/workerList">派工监控</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderTaskpackage"
		action="${ctx}/scheduling/orderTaskpackage/workerList2" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">

			<li><label>门店：</label> <form:select path="storeId"
					class="input-medium needClear" onchange="findEngineList()">
					<form:option value="" label="请选择..." />
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li>
				<label>工程模式</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="findEngineList()">
						<form:option value="" label="请选择..."/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="findEngineList()">
						<form:option value="" label="请选择..."/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>	
			</li>	
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>订单编号：</label> <form:input path="orderNumber"
					htmlEscape="false" maxlength="18" class="input-medium needClear" /></li>
			<li><label>派单员：</label> <form:input path="leaflet"
					htmlEscape="false" maxlength="255" class="input-medium needClear" /></li>
			<li><label>任务包编号：</label>
				<form:input path="orderTaskpackageCode" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>	
			<li><label>任务包名称：</label>
				<form:input path="packageName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>任务包状态：</label> 
				<form:select path="packageStateId"
					class="input-medium needClear">
					<form:option value="" label="请选择..." />
					<form:options items="${fns:getDictList('taskpackage_status_new')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			
			<li><label>客户姓名：</label> <form:input path="customerName"
					htmlEscape="false" maxlength="255" class="input-medium needClear" /></li>
			<li><label>项目经理：</label> <form:input path="itemCustomer"
					htmlEscape="false" maxlength="255" class="input-medium needClear" /></li>
			<li><label>工人组：</label> <form:input path="groupRealname"
					htmlEscape="false" maxlength="255" class="input-medium needClear" /></li>
			<li><label>已派工：</label> <form:input path="time" 
					htmlEscape="false" maxlength="5" class="input-medium digits needClear" 
					onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
				onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>分钟</li>
			<li><label>派工时间：</label>
				<input name="beginleafletdate" type="text" id="beginPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${orderTaskpackage.beginleafletdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endPlanStartdate\')}',isShowClear:false});"/> - 
				<input name="endleafletdate" type="text" id="endPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${orderTaskpackage.endleafletdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginPlanStartdate\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
					<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
		<!-- <ul class="ul-form">
			<li>已派工到工人，但工人组长超过：90分钟，未系统操作【接收】任务包</li>
		</ul> -->
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>派工时间</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>派单员</th>
				<th>任务包状态</th>
				<th>工人组</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="orderTaskpackage">
				<tr>

					<td>${fns:getStoreLabel(orderTaskpackage.storeId, '')}</td>
					
					<td>
						${fns:getDictLabel(orderTaskpackage.projectMode, 'project_mode', '')}
					</td>
					<td>
					${fns:getElacLabel(orderTaskpackage.engineDepartId, '')}
					</td>	
					
					
					<td><fmt:formatDate value="${orderTaskpackage.leafletDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
						
							
							
					<td>${orderTaskpackage.orderNumber}</td>
					<td>
						${orderTaskpackage.customerMessage}-${orderTaskpackage.customerName }
					</td>
					<td>${orderTaskpackage.itemCustomer}</td>
					<td>${orderTaskpackage.orderTaskPackageCode}</td>
					<td>${orderTaskpackage.packageName}</td>
					<td>${orderTaskpackage.leaflet}</td>
					<td>${orderTaskpackage.packageStatename}</td>
					<td><a
						href="${ctx}/scheduling/orderTaskpackage/findById?empGroupid=${orderTaskpackage.empGroupid}">${orderTaskpackage.groupRealname}</a>
					</td>
					<shiro:hasPermission name="scheduling:orderTaskpackage:edit">
						<td style="width: 70px;">
							<c:if test="${orderTaskpackage.isOvertime=='1'}">
								<a href="${ctx}/AllotWorkerGroup/allotWorkerGroup/list?packageId=${orderTaskpackage.id}&isRedistribute=1&packageCode=${orderTaskpackage.packageCode}&itemManageName=${orderTaskpackage.itemCustomer}&itemManagerId=${orderTaskpackage.itemManagerId}&projectMode=${orderTaskpackage.projectMode}">重新分派</a>
							</c:if>
							
							<c:if test="${ orderTaskpackage.packageStateId =='60'}">
								<a href="${ctx}/AllotWorkerGroup/allotWorkerGroup/list?packageId=${orderTaskpackage.id}&isRedistribute=1&packageCode=${orderTaskpackage.packageCode}&itemManageName=${orderTaskpackage.itemCustomer}&itemManagerId=${orderTaskpackage.itemManagerId}&projectMode=${orderTaskpackage.projectMode}">重新分派</a>
							</c:if>
							<c:if test="${ orderTaskpackage.packageStateId =='70'}">
								<a href="${ctx}/AllotWorkerGroup/allotWorkerGroup/list?packageId=${orderTaskpackage.id}&isRedistribute=1&packageCode=${orderTaskpackage.packageCode}&itemManageName=${orderTaskpackage.itemCustomer}&itemManagerId=${orderTaskpackage.itemManagerId}&projectMode=${orderTaskpackage.projectMode}">重新分派</a>
							</c:if>
							<c:if test="${ orderTaskpackage.packageStateId =='80'}">
								<a href="${ctx}/AllotWorkerGroup/allotWorkerGroup/list?packageId=${orderTaskpackage.id}&isRedistribute=1&packageCode=${orderTaskpackage.packageCode}&itemManageName=${orderTaskpackage.itemCustomer}&itemManagerId=${orderTaskpackage.itemManagerId}&projectMode=${orderTaskpackage.projectMode}">重新分派</a>
							</c:if>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>