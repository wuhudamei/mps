<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目经理月度结算查询</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
			
			var arrSon = document.getElementsByName("status");
			var cbAll = document.getElementById("chkAll");
			var boo = true;
			for(var i = 0; i < arrSon.length; i++){
				if(arrSon[i].checked == false){
					boo=false;
				}
			}
			if(boo){
				cbAll.checked=true;
			}
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);

			$("#searchForm").submit();
        	return false;
        }
		
		function clearCondition(){
			document.getElementById("searchForm").reset();

			var inputObjs = jQuery("#searchForm input[type='text']");
			for (var i = 0; i < inputObjs.length; i++) {
				var inputObj = inputObjs[i];
				inputObj.value = "";
			}

			var selectObjs = jQuery("#searchForm input[class='input-medium']");
			for (var i = 0; i < selectObjs.length; i++) {
				var selectObj = selectObjs[i];
				selectObj.value = "";
			}
			
			var arrSon = document.getElementsByName("orderStatusNumber");
			for (i = 0; i < arrSon.length; i++) {
				if(arrSon[i].checked){
					arrSon[i].checked=false;
				}
			}
		}

		
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
							if('${bizPmPreIndustrySettleSummaryBill.enginDepartId}' == data[i].value){
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
		
		
		function querySettleBillInfo(id,settleBillType){
			window.location.href="${ctx}/bizPmPreIndustrySettleSummaryBill/bizPmPreIndustrySettleSummaryBill/querySettleBillInfo?id="+id+"&settleBillType="+settleBillType;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">项目经理月度结算查询</a></li>
	</ul>
	<br />


	<form:form id="searchForm" modelAttribute="bizPmPreIndustrySettleSummaryBill"
		action="${ctx}/bizPmPreIndustrySettleSummaryBill/bizPmPreIndustrySettleSummaryBill/queryPmMonthSettleList"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />

		<ul class="ul-form">
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true"
						id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" id="storeId"
						onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if>
			</li>

			<li><label>工程模式：</label> <form:select path="projectMode"
					id="projectMode" class="input-medium"
					disabled="true">
					<form:option value="4" label="准产业" />
				</form:select></li>

			<li><label>区域：</label> <select id="enginDepartId"
				name="enginDepartId" class="input-medium">

			</select></li>
			
			<li><label>月度：</label>
				<input name="settleMonth" id="settleMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="${bizPmPreIndustrySettleSummaryBill.settleMonth}" onclick="WdatePicker({dateFmt:'yyyyMM',isShowClear:true});"/>
			</li>
			
			<li><label>项目经理：</label> <form:input path="pmEmployeeName"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearCondition()" /></li>
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
				<th>结算月份</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>中期结算合计金额</th>
				<th>竣工结算合计金额</th>
				<th>实际结算合计</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="settleSummaryBill">
				<tr>
					<td>${fns:getStoreLabel(settleSummaryBill.storeId, '')}</td>
					<td>准产业</td>
					<td>${settleSummaryBill.enginDepartName}</td>
					<td>${settleSummaryBill.settleMonth}</td>
					<td>${settleSummaryBill.pmEmployeeName}</td>
					<td>${settleSummaryBill.pmEmployeePhone}</td>
					<td><a href="#" onclick="querySettleBillInfo(${settleSummaryBill.id},1)">${settleSummaryBill.midwayRealSettleAmount}</a></td>
					<td><a href="#" onclick="querySettleBillInfo(${settleSummaryBill.id},2)">${settleSummaryBill.completeRealSettleAmount}</a></td>
					<td>${settleSummaryBill.realSettleAmount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>