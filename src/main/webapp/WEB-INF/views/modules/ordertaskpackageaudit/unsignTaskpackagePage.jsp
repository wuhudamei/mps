<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人未签到任务包列表</title>
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
		function getDepartments(){
			$("#engineDepartId").html('');
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
						$("#engineDepartId").append("<option value='' selected='selected' ></option>");
						$("#s2id_engineDepartId .select2-chosen").html("");
					} else {
						var html = "<option value=''></option>";
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
		<li class="active"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/unsignTaskpackagePage">工人未签到任务包列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackage" action="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/unsignTaskpackage" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="getDepartments()" >
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li>
				<label>工程模式</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label="" />
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
				<form:select path="engineDepartId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			
			<li><label>工人组长姓名：</label>
				<form:input path="groupRealname" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label style="width:160px;">计划开工日期：</label>
				<input name="beginPlanStartdate" type="text" id ="beginPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginPlanStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endPlanStartdate\')}',isShowClear:false});"/> - 
				<input name="endPlanStartdate" type="text" id="endPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endPlanStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginPlanStartdate\')}',isShowClear:false});"/>
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
				<th>顾客信息</th>
				<th>任务包名称</th>
				<th>工人组长</th>
				<th>工人组长手机号</th>
				<th>任务包分派时间</th>
				<th>计划开工日期</th>
				<th>计划完工日期</th>
				<th>未签到延期天数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="taskpackage" varStatus="index">
			<tr>
				<td>
					${fns:getStoreLabel(taskpackage.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(taskpackage.projectMode, 'project_mode', '')}
				</td>
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
					${taskpackage.packageName }
				</td>
				<td>
					${taskpackage.groupRealname }
				</td>
				<td>
					${taskpackage.groupPhone }
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.dispatchTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.planStartdate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.planEnddate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${taskpackage.days}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>	
</body>
</html>