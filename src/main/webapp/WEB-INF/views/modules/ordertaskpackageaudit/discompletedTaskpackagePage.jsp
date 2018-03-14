<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待分配工人任务包列表</title>
	<meta name="decorator" content="default"/>
	<meta content="*" name="Access-Control-Allow-Origin"><!--跨域请求  -->
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		 $(function() {
	           $("#checkAll").click(function() {
	                $('input[name="packageStateId"]').attr("checked",this.checked); 
	            });
	            var $status = $("input[name='packageStateId']");
	            $status.click(function(){
	                $("#checkAll").attr("checked",$status.length == $("input[name='packageStateId']:checked").length ? true : false);
	            });
	      });
		 
		 function getDepartments(){
				$("#enginDepartId").html('');
				$.ajax({
					url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
					type:'post',
					dataType:'json',
					data:{
						'storeId':$('#storeId').val(),
						'projectMode':$('#projectMode').val(),
						'employeeId':$('#employeeId').val()
					},
					success:function(data){
						if(data == null){
							$("#enginDepartId").append('');
						} else {
							var html = "<option value=''></option>";
							for(var i=0;i<data.length;i++){
								var sec = "";
								if('${bizOrderTaskpackage.engineDepartId}' == data[i].value){
				            		sec = "selected='selected'";
				            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
				            	}
								html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
							}
							html+='';
			        		$("#enginDepartId").append(html);
						}
					}
				});
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/discompletedTaskpackagePage">任务包列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackage" action="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/discompletedTaskpackage" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="getDepartments()" id="storeId">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>区域：</label>
				<form:select path="engineDepartId" id="enginDepartId" class="input-medium needClear">
					<form:option value="${bizOrderTaskpackage.engineDepartId }" label="${bizOrderTaskpackage.departmentName }" />
				</form:select>
			</li>
			<li><label>任务包编号：</label>
				<form:input path="orderTaskpackageCode" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			
			<li><label>任务包名称：</label>
				<form:input path="packageName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>

			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemCustomer" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>工人组长姓名：</label>
				<form:input path="groupRealname" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label style="width:160px;">计划开工日期：</label>
				<input name="beginPlanStartdate" type="text" id="beginPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginPlanStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endPlanStartdate\')}',isShowClear:false});"/> - 
				<input name="endPlanStartdate" type="text" id="endPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endPlanStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginPlanStartdate\')}',isShowClear:false});"/>
			</li>
			<li><label style="width:160px;">计划完工日期：</label>
				<input name="beginPlanEnddate" type="text" id="beginPlanEnddate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginPlanEnddate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endPlanEnddate\')}',isShowClear:false});"/> - 
				<input name="endPlanEnddate" type="text" id="endPlanEnddate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endPlanEnddate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginPlanEnddate\')}',isShowClear:false});"/>
			</li>
			<li style="width:800px">
				<label>任务包状态：</label><input id="checkAll" type="checkbox" value="全选" />全选
				<input type="checkbox" name="packageStateId" value="60" <c:if test="${fns:checkIsExits(bizOrderTaskpackage.packageStateId,'60')}"> checked="checked"</c:if>/>60-已确定工人组
				<input type="checkbox" name="packageStateId" value="70" <c:if test="${fns:checkIsExits(bizOrderTaskpackage.packageStateId,'70')}"> checked="checked"</c:if>/>70-施工中
			</li>
			<li class="btns" style="width:100px"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
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
				<th>任务包状态</th>
				<th>工人组长</th>
				<th>计划开工日期</th>
				<th>计划完工日期</th>
				<th>实际开工日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="taskpackage" varStatus="index">
			<tr>
				<td>
					${fns:getStoreLabel(taskpackage.storeId, '')}
				</td>
				<td>${fns:getDictLabel(taskpackage.projectMode, 'package_project_mode', '')}</td>
				<td>
					${taskpackage.departmentName }
				</td>
				<td>
					${taskpackage.orderNumber }
				</td>
				<td>
					${taskpackage.customerMessage }-${taskpackage.customerName }
				</td>
				<td>
					${taskpackage.itemCustomer }
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
					${taskpackage.groupRealname }
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.planStartdate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.planEnddate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.actualStartdate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>	
</body>
</html>