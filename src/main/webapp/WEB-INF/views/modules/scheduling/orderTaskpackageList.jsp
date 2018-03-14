<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>待派工查询</title>
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
	function allotWorker(packId,managerName,packageCode,itemManagerId,projectMode){
		if(""==managerName ||null==managerName){
			alertx("订单分配项目经理后任务包才可以分配工人");
			return;
		}
		
	window.location.href="${ctx}/AllotWorkerGroup/allotWorkerGroup/list?packageId="+packId+"&packageCode="+packageCode+"&itemManagerId="+itemManagerId+"&itemManageName="+managerName+"&projectMode="+projectMode+"&flag=1";
	}
	function clearCondition(){
		 document.getElementById("searchForm").reset();
		
		
	}

	 $(function(){
		 $("#isZeroId").click(function() {
           	 $('input[name="isZero"]').attr("checked",this.checked); 
       	 });
       	 var $status = $("input[name='isZero']");
       	 $status.click(function(){
            $("#isZeroId").attr("checked",$status.length == $("input[name='isZero']:checked").length ? true : false);
         });
	 });
	 
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
			href="${ctx}/scheduling/orderTaskpackage/list">任务包分配</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderTaskpackage"
		action="${ctx}/scheduling/orderTaskpackage/list2" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">

			<%-- <li><label>订单编号：</label> <form:input path="orderId"
					htmlEscape="false" maxlength="18" class="input-medium" /></li> --%>
			
			<%-- <li><label>任务包类型：</label> <form:select path="taskPackageType"
					class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getTaskPackageTypeList()}"
						itemLabel="name" itemValue="name" htmlEscape="false" />
				</form:select></li> --%>
			

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
						<form:option value="" label="请选择..." />
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
				
			<li>
				<label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>订单编号：</label> <form:input path="orderNumber"
					htmlEscape="false" maxlength="255" class="input-medium" /></li>
			<li><label>项目经理：</label> <form:input path="itemCustomer"
					htmlEscape="false" maxlength="255" class="input-medium" /></li>
			<li><label>客户姓名：</label> <form:input path="customerName"
					htmlEscape="false" maxlength="255" class="input-medium" /></li>
			<li><label>任务包名称：</label> <form:input path="packageName"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
					
			<li><label>任务包状态：</label>
					<form:select path="packageStateId" class="input-medium needClear">
					<form:option value="" label="请选择..." />
					<form:option value="20" label="20-预算员审核通过 " />
					<form:option value="55" label="55-工人拒绝任务包" />
				</form:select></li>
			<li><label>任务包编号：</label> <form:input path="orderTaskPackageCode"
					htmlEscape="false" maxlength="18" class="input-medium" /></li>
			<li><label style="width:150px;">计划开始时间：</label>	<input name="planStartdate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${planStartDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></li>
			<%-- <li>
				<input type="checkbox" name="isZero" value="1" id="isZeroId" <c:if test="${fns:checkIsExits(orderTaskpackage.isZero,'1')}"> checked="checked"</c:if>/>任务包金额不为零
			</li>	 --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
				<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>计划开始时间</th>
				<th>计划结束时间</th>
				<th>任务包状态</th>
				<th>工人组</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="orderTaskpackage">
				<tr>
					<td>${fns:getStoreLabel(orderTaskpackage.storeId, '')}</td>
					<td>${fns:getDictLabel(orderTaskpackage.projectMode, 'project_mode', '')}</td>
					<td>${fns:getElacLabel(orderTaskpackage.engineDepartId, '')}</td>
					<td>${orderTaskpackage.orderNumber}</td>
					<td>
						${orderTaskpackage.customerMessage}-${orderTaskpackage.customerName }
					</td>
					<td>${orderTaskpackage.itemCustomer}</td>
					<td>${orderTaskpackage.orderTaskPackageCode}</td>
					<td>${orderTaskpackage.packageName}</td>
					<td>
					<fmt:formatDate value="${orderTaskpackage.planStartdate}" pattern="yyyy-MM-dd"/>
					</td>
					<td><fmt:formatDate value="${orderTaskpackage.planEnddate}" pattern="yyyy-MM-dd"/></td>

					<td>${orderTaskpackage.packageStatename}</td>

					<td><c:if test="${orderTaskpackage.empGroupid==''}">无</c:if> <c:if
							test="${orderTaskpackage.empGroupid!=''}">
							<a
								href="${ctx}/scheduling/orderTaskpackage/findById?empGroupid=${orderTaskpackage.empGroupid}">${orderTaskpackage.groupRealname}</a>
						</c:if></td>


					<shiro:hasPermission name="scheduling:orderTaskpackage:edit">
						<td>
							<a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/procedureDetail?id=${orderTaskpackage.id}">查看详情</a>
							<!-- <a href="">预览</a> --> <c:if
								test="${orderTaskpackage.packageStateId=='20' ||orderTaskpackage.packageStateId =='55'}">
								</br><a onclick="allotWorker('${orderTaskpackage.id}','${orderTaskpackage.itemCustomer}','${orderTaskpackage.packageCode}','${orderTaskpackage.itemManagerId }','${orderTaskpackage.projectMode }')" href="#">分派工人</a>
							</c:if>
							
						</td>
						
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>

<script type="text/javascript">


</script>
</html>