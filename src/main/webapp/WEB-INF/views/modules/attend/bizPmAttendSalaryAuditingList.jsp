<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤月度表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
			
			/*$("#searchForm").validate({
				errorPlacement: function(error, element) {
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			})*/
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//加载区域信息
		function getDepartments(){
			$("#enginDepartId").html('');
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
						$("#enginDepartId").html('');
					} else {
						var html = "<option value=''></option>";
						for(var i=0;i<data.length;i++){
							var sec = "";
							if('${bizPmAttendSalaryBill.enginDepartId}' == data[i].value){
			            		sec = "selected='selected'";
			            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
			            	}
							html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>";
						}
						html+='';
		        		$("#enginDepartId").append(html);
					}
				}
			});
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
		<li class="active"><a href="${ctx}/attend/bizPmAttendSalaryBill/salaryBillAuditingList">项目经理工资单查询</a></li>
		<%-- <shiro:hasPermission name="attend:bizPmAttendMonth:edit"><li><a href="${ctx}/attend/bizPmAttendMonth/form">考勤月度表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmAttendSalaryBill" action="${ctx}/attend/bizPmAttendSalaryBill/findSalaryBillAuditingList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear required" onchange="getDepartments()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId" class="input-medium needClear required">
					<%-- <form:options items="${fns:getEngineListWithUserConditionsForBiddenChangeForOrder()}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<form:option value="${bizPmAttendSalaryBill.enginDepartId }" label="${bizPmAttendSalaryBill.departmentName }" />
				</form:select>
			</li>
			<li><label>考勤月份：</label>
				<input name="attendMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${bizPmAttendSalaryBill.attendMonth}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true,maxDate:'#F{$dp.$D(\'hidTime\')}'});"/>
					
				<input name="hidTime" id="hidTime" type="hidden" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${maxMonth}"/>
			</li>
			<li>
				<label>项目经理：</label>
				<form:input type="text" path="itemManager" htmlEscape="false" maxlength="3" maxvalue="100" class="input-medium"/>
			</li>
			<li><label>考勤单状态：</label>
				<select id="status" name="status" class="input-medium needClear required">
					<option value="">请选择...</option>
					<option value="30" <c:if test="${'30' eq bizPmAttendSalaryBill.status}">selected</c:if>>30-已生成工资单</option>
					<%--<option value="35" <c:if test="${'35' eq bizPmAttendSalaryBill.status}">selected</c:if>>35-工资单作废</option>--%>
					<option value="40" <c:if test="${'40' eq bizPmAttendSalaryBill.status}">selected</c:if>>40-工资单审核通过</option>
					<option value="50" <c:if test="${'50' eq bizPmAttendSalaryBill.status}">selected</c:if>>50-生成工资批次</option>
					<option value="60" <c:if test="${'60' eq bizPmAttendSalaryBill.status}">selected</c:if>>60-工资批次审核通过</option>
					<option value="90" <c:if test="${'90' eq bizPmAttendSalaryBill.status}">selected</c:if>>90-工资批次作废</option>
				</select>
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
				<th>区域</th>
				<th>项目经理</th>
				<th>手机号</th>
				<th>考勤月份</th>
				<th>工资单编号</th>
				<th>考勤单状态</th>
				<shiro:hasPermission name="attend:bizPmAttendMonth:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmAttendSalaryBill">
			<tr>
				<td>
					${fns:getStoreLabel(bizPmAttendSalaryBill.storeId, '')}
				</td>
				<td>
					${bizPmAttendSalaryBill.departmentName}
				</td>
				<td>
					${bizPmAttendSalaryBill.itemManager}
				</td>
				<td>
					${bizPmAttendSalaryBill.phone}
				</td>
				<td>
					${bizPmAttendSalaryBill.attendMonth}
				</td>
				<td>
					${bizPmAttendSalaryBill.pmAttendSalaryBillCode}
				</td>
				<td>
					<c:if test="${bizPmAttendSalaryBill.status=='30'}">30-已生成工资单</c:if>
					<%--<c:if test="${bizPmAttendSalaryBill.status=='35'}">35-工资单作废</c:if>--%>
					<c:if test="${bizPmAttendSalaryBill.status=='40'}">40-工资单审核通过</c:if>
					<c:if test="${bizPmAttendSalaryBill.status=='50'}">50-生成工资批次</c:if>
					<c:if test="${bizPmAttendSalaryBill.status=='60'}">60-工资批次审核通过</c:if>
					<c:if test="${bizPmAttendSalaryBill.status=='90'}">90-工资批次作废</c:if>
				</td>
				<shiro:hasPermission name="attend:bizPmAttendMonth:edit">
					<td>
    					<a href="${ctx}/attend/bizPmAttendSalaryBill/bizPmAttendSalaryBillDetail?itemManager=${bizPmAttendSalaryBill.itemManager}&itemManagerId=${bizPmAttendSalaryBill.itemManagerId}&storeId=${bizPmAttendSalaryBill.storeId}&enginDepartId=${bizPmAttendSalaryBill.enginDepartId}&attendMonth=${bizPmAttendSalaryBill.attendMonth}&flag=2">查看</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>