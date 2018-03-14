<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单批次管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		
		$(document).ready(function() {
			getDepartments();
		});

		function getDepartments(){
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
					if(data == null || data == ""){
						$("#enginDepartId").html("");
						$("#s2id_enginDepartId .select2-chosen").html("");
					}else{
						var content = "<option></option>";
						for(var i=0;i<data.length;i++){
							if('${bizOrderTaskpackagePaymentSummary.enginDepartId}' == data[i].value){
								$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
								content = content + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
							}else{
								content = content + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
							}
						}
						$("#enginDepartId").html(content);
					}
				}
			});
		}

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
		
		function checkPaymentSummary(id){
			$.ajax({
				url:'${ctx}/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge/checkPaymentSummary?id='+id,
				type:'post',
				success:function(data){
					if(data == 1){
						alertx("该批次已付款！");
					}else{
						window.location.href="${ctx}/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge/list?summaryId="+id;
					}
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/summaryLoadList">付款单批次列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackagePaymentSummary" action="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/summaryLoadList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" id="employeeId" name="employeeId" value="${employeeId}">
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
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
				<select id="enginDepartId" name="enginDepartId" class="input-medium needClear">

				</select>
			</li>
			<li><label>操作人：</label>
				<form:input path="applyEmployeeName" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>付款批次状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getSummaryCheckedStatusList('summary_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>批次生成日期：</label>
				<input name="beginGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummary.beginGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummary.endGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
				<th>付款批次编号 </th>
				<th>批次生成日期</th>
				<th>付款单数量</th>
				<th>付款批次状态</th>
				<th>操作人</th>
				<shiro:hasPermission name="ordertaskpaymentdetailmerge:bizOrderTaskpackagePaymentDetailMerge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderTaskpackagePaymentSummary">
			<tr>
				<td>
					${fns:getStoreLabel(bizOrderTaskpackagePaymentSummary.storeId, '')}
				</td>
				<td>${fns:getDictLabel(bizOrderTaskpackagePaymentSummary.projectMode, 'package_project_mode', '')}</td>
				<td>${bizOrderTaskpackagePaymentSummary.departmentName}</td>
				<td>
					${bizOrderTaskpackagePaymentSummary.orderTaskpackagePaymentSummaryCode }
				</td>
				<td>
					<fmt:formatDate value="${bizOrderTaskpackagePaymentSummary.generatedDatetime }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizOrderTaskpackagePaymentSummary.orderTaskpackagePaymentCount }
				</td>
				<td>
					${fns:getDictLabel(bizOrderTaskpackagePaymentSummary.status , 'summary_status', '')}
				</td>
				<td>
					${bizOrderTaskpackagePaymentSummary.applyEmployeeName }
				</td>
				<shiro:hasPermission name="ordertaskpaymentdetailmerge:bizOrderTaskpackagePaymentDetailMerge:edit"><td>
					<c:if test="${bizOrderTaskpackagePaymentSummary.status == '100'}">
    					<a href="${ctx}/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge/list?summaryId=${bizOrderTaskpackagePaymentSummary.id}">查看详情</a>
					</c:if>
					<c:if test="${bizOrderTaskpackagePaymentSummary.status == '20' || bizOrderTaskpackagePaymentSummary.status == '50'}">
    					<a href="#" onclick="checkPaymentSummary(${bizOrderTaskpackagePaymentSummary.id})">去付款</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>