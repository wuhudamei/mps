<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>质保金使用查询</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	getDepartments();
});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function clearCondition() {
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

	window.onload = function() {

		var obj = document.getElementsByName('orderStatusNumber');
		var s = '';
		for (var i = 0; i < obj.length; i++) {
			if (!obj[i].checked) {
				s += "11" + obj[i].value + ','; //如果选中，将value添加到变量s中    
			}
		}
		if (s == null || s == "") {
			$("#chkAll").prop("checked", "checked");
		}
	}

	function searchButton() {
		$("#searchForm").submit();
	}
	function getDepartments() {
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
						if('${bizGuaranteeMoneyPaidUsed.engineDepartId}' == data[i].value){
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
			href="${ctx}/guarantee/guaranteeManager/queryUseGuarantee">质保金使用查询</a></li>
		<li><a
			href="${ctx}/guarantee/guaranteeManager/openUseGuaranteFrom">质保金使用信息添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizGuaranteeMoneyPaidUsed"
		action="${ctx}/guarantee/guaranteeManager/queryUseGuarantee"
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

			<li><label>客户姓名：</label> <form:input path="customerName"
					htmlEscape="false" maxlength="16" class="input-medium" /></li>

			<li><label>使用人：</label> <form:input path="usedEmpName"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>

			<li><label style="width: 100px">使用人员类型：</label> <form:select
					path="usedEmployeeType" class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('use_guarantee_emp_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>使用类型：</label> <form:select path="guaranteeMoneyFor"
					class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('use_guarantee_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>使用日期：</label> <input name="start"
				id="beginContractStartDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizGuaranteeMoneyPaidUsed.start}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endContractStartDate\')}',isShowClear:true});" />
				至 <input name="end" id="endContractStartDate" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizGuaranteeMoneyPaidUsed.end}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginContractStartDate\')}',isShowClear:true});" />
			</li>
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
				<th>使用质保金日期</th>
				<th>使用类型</th>
				<th>使用金额</th>
				<th>使用人员类型</th>
				<th>姓名</th>
				<th>工种</th>
				<th>小区</th>
				<th>客户</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="bizGuaranteeMoneyPaidUsed">
				<tr>
					<td>${fns:getStoreLabel(bizGuaranteeMoneyPaidUsed.storeId, '')}
					</td>
					<td>${fns:getDictLabel(bizGuaranteeMoneyPaidUsed.projectMode, 'project_mode', '')}
					</td>
					<td>
						${fns:getElacLabel(bizGuaranteeMoneyPaidUsed.engineDepartId, '')}
					</td>
					<td><fmt:formatDate
							value="${bizGuaranteeMoneyPaidUsed.guaranteeMoneyDateTime}"
							pattern="yyyy-MM-dd" /></td>
					<td>
						${fns:getDictLabel(bizGuaranteeMoneyPaidUsed.guaranteeMoneyFor,'use_guarantee_type', '')}
					</td>
					<td>${bizGuaranteeMoneyPaidUsed.guaranteeMoneyAmount}</td>
					<td>
						${fns:getDictLabel(bizGuaranteeMoneyPaidUsed.usedEmployeeType,'use_guarantee_emp_type', '')}
					</td>
					<td>
						${bizGuaranteeMoneyPaidUsed.usedEmpName}-${bizGuaranteeMoneyPaidUsed.usedEmpPhone}
					</td>
					<td>
						${fns:getDictLabel(bizGuaranteeMoneyPaidUsed.worktype,'emp_work_type', '')}
					</td>
					<td>${bizGuaranteeMoneyPaidUsed.communityName}</td>
					<td>
						${bizGuaranteeMoneyPaidUsed.customerName}-${bizGuaranteeMoneyPaidUsed.customerPhone}
					</td>
					<td><a
						href="${ctx}/guarantee/guaranteeManager/openUseGuaranteeUpdate?id=${bizGuaranteeMoneyPaidUsed.id}">修改</a>
						<a
						href="${ctx}/guarantee/guaranteeManager/getUseGuaranteeDetail?id=${bizGuaranteeMoneyPaidUsed.id}">详情</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
<script type="text/javascript">

</script>
</html>