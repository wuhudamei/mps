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
							if('${bizPmAttendMonth.enginDepartId}' == data[i].value){
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
		<li class="active"><a href="${ctx}/attend/bizPmAttendMonth/list">考勤月度表列表</a></li>
		<%-- <shiro:hasPermission name="attend:bizPmAttendMonth:edit"><li><a href="${ctx}/attend/bizPmAttendMonth/form">考勤月度表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmAttendMonth" action="${ctx}/attend/bizPmAttendMonth/findList" method="post" class="breadcrumb form-search">
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
					<form:option value="${bizPmAttendMonth.enginDepartId }" label="${bizPmAttendMonth.departmentName }" />
				</form:select>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium" />
			</li>
			<li><label>考勤月份：</label>
				<input name="attendMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${bizPmAttendMonth.attendMonth}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'maxMonth\')}',isShowClear:true});"/>
				<input id="maxMonth" type="hidden" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${maxMonth}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/>
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
				<!-- <th>考勤月份</th> -->
				<th>考勤状态</th>
				<shiro:hasPermission name="attend:bizPmAttendMonth:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmAttendMonth">
			<tr>
				<td>
					${fns:getStoreLabel(bizPmAttendMonth.storeId, '')}
				</td>
				<td>
					${bizPmAttendMonth.departmentName}
				</td>
				<td>
					${bizPmAttendMonth.itemManager}
				</td>
				<td>
					${bizPmAttendMonth.phone}
				</td>
				<%-- <td>
					${bizPmAttendMonth.attendMonth}
				</td> --%>
				<td>
					<c:if test="${bizPmAttendMonth.status!='35'}">10-未生成考勤单</c:if>
					<c:if test="${bizPmAttendMonth.status=='35'}">35-工资单作废</c:if>
				</td>
				<shiro:hasPermission name="attend:bizPmAttendMonth:edit"><td>
    				<a href="${ctx}/attend/bizPmAttendMonth/form?id=${bizPmAttendMonth.id}&itemManager=${bizPmAttendMonth.itemManager}&itemManagerId=${bizPmAttendMonth.itemManagerId}&storeId=${bizPmAttendMonth.storeId}&enginDepartId=${bizPmAttendMonth.enginDepartId}&attendMonth=${bizPmAttendMonth.attendMonth}&status=${bizPmAttendMonth.status}&pmAttendMonthId=${bizPmAttendMonth.pmAttendMonthId}">生成考勤单</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>