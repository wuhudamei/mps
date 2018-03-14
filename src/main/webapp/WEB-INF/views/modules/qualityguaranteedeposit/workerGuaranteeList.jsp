<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工人质保金查询</title>
<meta name="decorator" content="default" />
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

		
		 
		
		
		window.onload = function(){
			
			 var obj=document.getElementsByName('orderStatusNumber');  
			  var s='';    
			  for(var i=0; i<obj.length; i++){    
			    if(!obj[i].checked){
			    	s+="11"+obj[i].value+',';  //如果选中，将value添加到变量s中    
			    }
			  } 
			  if(s==null || s==""){
				  $("#chkAll").prop("checked", "checked");
			  }
		}
		
		
		function searchButton(){
			$("#searchForm").submit();
		}
		function getDepartments(){
			$("#engineDepartId").html('');
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':$('#projectMode').val(),
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null){
						$("#engineDepartId").append('');
					} else {
						var html = "<option value=''></option>";
						for(var i=0;i<data.length;i++){
							var sec = "";
							if('${bizGuaranteeMoneyBalance.engineDepartId}' == data[i].value){
			            		sec = "selected='selected'";
			            		$("#s2id_engineDepartId .select2-chosen").html(data[i].label);
			            	}
							html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
						}
						html+='';
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
			href="${ctx}/guarantee/guaranteeWorker3/queryWorkerGuarantee">工人质保金查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizGuaranteeMoneyBalance"
		action="${ctx}/guarantee/guaranteeWorker3/queryWorkerGuarantee"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li class="clearfix"></li>
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true"
						onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear"
						onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
				<li><label>区域：</label>
				<form:select path="engineDepartId" id ="engineDepartId" class="input-medium needClear">
				</form:select>
			</li>

			<li><label>工人组长：</label> <form:input path="empName"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			
			<li><label>手机号：</label> <form:input path="empPhone"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="button" value="查询" onclick="searchButton()" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearCondition()" /></li>
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
				<th>工人组长</th>
				<th>手机号</th>
				<th>星级</th>
				<th>工种</th>
				<th>质保金余额</th>
				<th>结算上缴质保金总额</th>
				<th>线下上缴质保金总额</th>
				<th>使用质保金总额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="bizGuaranteeMoneyBalance">
				<tr>
					<td>${fns:getStoreLabel(bizGuaranteeMoneyBalance.storeId, '')}
					</td>
					<td>${fns:getDictLabel(bizGuaranteeMoneyBalance.projectMode, 'project_mode', '')}
					</td>
					<td>${fns:getElacLabel(bizGuaranteeMoneyBalance.engineDepartId, '')}</td>
					<td>${bizGuaranteeMoneyBalance.empName}</td>
					<td>${bizGuaranteeMoneyBalance.empPhone}</td>
					<td>${bizGuaranteeMoneyBalance.star}</td>
				    <td>${fns:getDictLabel(bizGuaranteeMoneyBalance.workType,'emp_work_type', '')}</td>
					<td>${bizGuaranteeMoneyBalance.guaranteeMoneyBalance}</td>
					<td><a href="${ctx}/guarantee/guaranteeWorker3/queryWorkerGuaranteePaidSettleDetail?usedEmployeeId=${bizGuaranteeMoneyBalance.employeeId}">${bizGuaranteeMoneyBalance.guaranteeMoneyAmountPaidSettle}</a></td>
					<td><a href="${ctx}/guarantee/guaranteeWorker3/queryWorkerGuaranteePaidOffineDetail?usedEmployeeId=${bizGuaranteeMoneyBalance.employeeId}">${bizGuaranteeMoneyBalance.guaranteMoneyAmountPaidOffline}</a></td>
					<td><a href="${ctx}/guarantee/guaranteeWorker3/queryWorkerGuaranteeUsedDetail?usedEmployeeId=${bizGuaranteeMoneyBalance.employeeId}">${bizGuaranteeMoneyBalance.guaranteeMoneyAmountPaidUsed}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
<script type="text/javascript">

</script>
</html>