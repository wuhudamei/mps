<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算汇总单管理</title>
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
					'projectMode':'1',
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null || data == ""){
						$("#enginDepartId").html("");
						$("#s2id_enginDepartId .select2-chosen").html("");
					}else{
						var content = "<option></option>";
						for(var i=0;i<data.length;i++){
							if('${bizPmSettleSummaryBill.enginDepartId}' == data[i].value){
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

		function exportExcel(){
			$("#searchForm").attr("action", "${ctx}/pmsettlesummarybill/bizPmSettleSummaryBill/exportExcel");
			$("#searchForm").submit();
		}

		function searchButton(){
			$("#searchForm").attr("action", "${ctx}/pmsettlesummarybill/bizPmSettleSummaryBill/loadList");
			$("#searchForm").submit();
		}

		function clearEnginDepart(){
			$("#enginDepartId").html("");
			$("#s2id_enginDepartId .select2-chosen").html("");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pmsettlesummarybill/bizPmSettleSummaryBill/loadList">结算汇总单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmSettleSummaryBill" action="${ctx}/pmsettlesummarybill/bizPmSettleSummaryBill/loadList" method="post" class="breadcrumb form-search">
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
			<li><label>区域：</label>
				<select id="enginDepartId" name="enginDepartId" class="input-medium needClear">

				</select>
			</li>
			<li><label>月度：</label>
				<input name="settleMonth" id="settleMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="${bizPmSettleSummaryBill.settleMonth}" onclick="WdatePicker({dateFmt:'yyyyMM',isShowClear:true});"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="searchButton()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearEnginDepart()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出excel" onclick="exportExcel()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>区域</th>
				<th>月度</th>
				<th>项目经理</th>
				<th>中期提成</th>
				<th>竣工提成</th>
				<th>自主支配项</th>
				<th>标化辅材</th>
				<th>中期质检罚款</th>
				<th>竣工质检罚款</th>
				<th>中期奖励</th>
				<th>竣工奖励</th>
				<th>中期扣款</th>
				<th>竣工扣款</th>
				<th>中期巡检奖励</th>
				<th>竣工巡检奖励</th>
				<th>中期巡检扣款</th>
				<th>竣工巡检扣款</th>
				<th>中期任务包材料结算</th>
				<th>竣工任务包材料结算</th>
				<th>自采材料</th>
				<th>质保金</th>
				<th>合计</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmSettleSummaryBill">
			<tr>
				<td>${fns:getStoreLabel(bizPmSettleSummaryBill.storeId, '')}</td>
				<td>${bizPmSettleSummaryBill.enginDepartName}</td>
				<td>${bizPmSettleSummaryBill.settleMonth}</td>
				<td>${bizPmSettleSummaryBill.itemManager}</td>
				<td>${bizPmSettleSummaryBill.midwayCommissionAmount}</td>
				<td>${bizPmSettleSummaryBill.completeCommissionAmount}</td>
				<td>${bizPmSettleSummaryBill.ownpayAmount}</td>
				<td>${bizPmSettleSummaryBill.materialsStandardAmount}</td>
				<td>${bizPmSettleSummaryBill.midwayQcCheckPunishAmount}</td>
				<td>${bizPmSettleSummaryBill.completQcCheckPunishAmount}</td>
				<td>${bizPmSettleSummaryBill.midwayRewardAmount}</td>
				<td>${bizPmSettleSummaryBill.completeRewardAmount}</td>
				<td>${bizPmSettleSummaryBill.midwayPunishAmount}</td>
				<td>${bizPmSettleSummaryBill.completePunishAmount}</td>
				<td>${bizPmSettleSummaryBill.midwayInspectionRewardAmount}</td>
				<td>${bizPmSettleSummaryBill.completeInspectionRewardAmount}</td>
				<td>${bizPmSettleSummaryBill.midwayInspectionPunishAmount}</td>
				<td>${bizPmSettleSummaryBill.completeInspectionPunishAmount}</td>
				<td>${bizPmSettleSummaryBill.midwayAuxiliaryMaterialsSettleAmount}</td>
				<td>${bizPmSettleSummaryBill.completeAuxiliaryMaterialsSettleAmount}</td>
				<td>${bizPmSettleSummaryBill.materialSelfbuyReimburseAmount}</td>
				<td>${bizPmSettleSummaryBill.guaranteeMoneyAmount}</td>
				<td>${bizPmSettleSummaryBill.totalAmount}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>