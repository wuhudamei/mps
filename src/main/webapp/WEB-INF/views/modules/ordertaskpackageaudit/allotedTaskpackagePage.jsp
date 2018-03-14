<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待分配工人任务包列表</title>
	<meta name="decorator" content="default"/>
	<meta content="*" name="Access-Control-Allow-Origin"><!--跨域请求  -->
	<script type="text/javascript">
	$(document).ready(function() {
		findEngineList();
	    $("#packageStateId option").each(function(index,element){
			var val = $(this).val();
			if(val == '20' || val == '10'){
				$("#packageStateId option[value="+val+"]").remove();
			}
			
		});
	});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		 $(function() {
	           $("#checkAll").click(function() {
	                $('input[name="status"]').attr("checked",this.checked); 
	            });
	            var $status = $("input[name='status']");
	            $status.click(function(){
	                $("#checkAll").attr("checked",$subBox.length == $("input[name='status']:checked").length ? true : false);
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
									if('${bizOrderTaskpackage.engineDepartId}' == data[i].value){
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
		<li class="active"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/allotedTaskpackagePage">任务包列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackage" action="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/allotedTaskpackage" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineList()">
					<form:option value="" label="请选择..." />
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li>
				<label>工程模式</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findEngineList()">
						<form:option value="" label="请选择..." />
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineList()">
						<form:option value="" label="请选择..." />
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>				
			</li>
			<li>
				<label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>任务包编号：</label>
				<form:input path="orderTaskpackageCode" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			
			<li><label>任务包名称：</label>
				<form:input path="packageName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li>
				<label>任务包状态：</label>
				<form:select path="packageStateId" class="input-medium needClear">
					<form:option value="" label="请选择..." />
					<form:options items="${fns:getDictList('taskpackage_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemCustomer" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>工人组长姓名：</label>
				<form:input path="groupRealname" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>派单人：</label>
				<form:input path="leaflet" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>派工时间：</label>
				<input name="beginleafletdate" type="text" id="beginPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginleafletdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endPlanStartdate\')}',isShowClear:false});"/> - 
				<input name="endleafletdate" type="text" id="endPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endleafletdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginPlanStartdate\')}',isShowClear:false});"/>
			</li>
			<li><label>是否作废：</label>
				<form:select path="isScrap" class="input-medium needClear">
					<form:option value="0" label=""/>
					<form:options items="${fns:getDictList('dict_iscountsquare')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns" style="width:100px"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>项目经理</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>任务包状态</th>
				<th>计划开工日期</th>
				<th>计划完工日期</th>
				<th>工人组长</th>
				<th>派单人</th>
				<th>派单时间</th>
				<th>是否作废</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="taskpackage" varStatus="index">
			<tr>
				<td>
					${index.index + 1}
				</td>
				<td>
					${fns:getStoreLabel(taskpackage.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(taskpackage.projectMode, 'project_mode', '')}
				</td>
				<td>
					${fns:getElacLabel(taskpackage.engineDepartId, '')}
				</td>
				<td>
					${taskpackage.itemCustomer }
				</td>
				<td>
					${taskpackage.orderNumber }
				</td>
				<td>
					${taskpackage.customerMessage }-${taskpackage.customerName }
				</td>
				
				<td>
					${taskpackage.orderTaskpackageCode }
				</td>
				<td>
					${taskpackage.packageName }
				</td>
				<td>
					${taskpackage.packageStateId }-${taskpackage.packageStateName }
				</td>
				
				<td>
					<fmt:formatDate value="${taskpackage.planStartdate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.planEnddate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${taskpackage.groupRealname }
				</td>
				<td>${taskpackage.leaflet}</td>
				<td><fmt:formatDate value="${taskpackage.leafletDate}" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:if test="${taskpackage.isScrap eq 1}">是</c:if>
					<c:if test="${taskpackage.isScrap ne 1}">否</c:if>
				</td>
				<td>
					<a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/procedureDetail?id=${taskpackage.id}">查看详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>	
</body>
</html>