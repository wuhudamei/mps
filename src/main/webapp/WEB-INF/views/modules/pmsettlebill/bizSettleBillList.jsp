<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单月度工程结算单</title>
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
							if('${bizPmSettleBill.enginDepartId}' == data[i].value){
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
			$("#searchForm").attr("action", "${ctx}/pmsettlebill/bizPmSettleBill/exportExcel");
			$("#searchForm").submit();
		}

		function searchButton(){
			$("#searchForm").attr("action", "${ctx}/pmsettlebill/bizPmSettleBill/loadSettleList");
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
		<li class="active"><a href="${ctx}/pmsettlebill/bizPmSettleBill/loadSettleList">订单月度工程结算单</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmSettleBill" action="${ctx}/pmsettlebill/bizPmSettleBill/loadSettleList" method="post" class="breadcrumb form-search">
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
				<input name="settleMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="${bizPmSettleBill.settleMonth}" onclick="WdatePicker({dateFmt:'yyyyMM',isShowClear:true});"/>
			</li>
			
			<li><label>客户：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>生成日期：</label>
				<input name="beginSettleDatetime" id="beginSettleDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="<fmt:formatDate value="${bizPmSettleBill.beginSettleDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endSettleDatetime\')}',isShowClear:true});"/> 至
				<input name="endSettleDatetime" id="endSettleDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="<fmt:formatDate value="${bizPmSettleBill.endSettleDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginSettleDatetime\')}',isShowClear:true});"/>
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
				<th>生成时间</th>
				<th>月度</th>
				<th>订单号</th>
				<th>客户</th>
				<th>客户电话</th>
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
		<c:forEach items="${page.list}" var="bizPmSettleBill">
			<tr>
				<td>${fns:getStoreLabel(bizPmSettleBill.storeId, '')}</td>
				<td>${bizPmSettleBill.enginDepartName}</td>
				<td><fmt:formatDate value="${bizPmSettleBill.settleDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${bizPmSettleBill.settleMonth}</td>
				<td>${bizPmSettleBill.orderNumber}</td>
				<td>${bizPmSettleBill.customerName}</td>
				<td>${bizPmSettleBill.customerPhone}</td>
				<td>${bizPmSettleBill.itemManager}</td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryMidwayCommission?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}">${bizPmSettleBill.midwayCommissionAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryCompleteCommission?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}">${bizPmSettleBill.completeCommissionAmount}</a></td>
				<td><c:if test="${not empty bizPmSettleBill.ownpayAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/ownpayAmount?id=${fn:split(bizPmSettleBill.settleBillId,',')[0]}">${bizPmSettleBill.ownpayAmount}</a></c:if></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/materialsStandardDetails?orderId=${bizPmSettleBill.orderId}">${bizPmSettleBill.materialsStandardAmount}</a></td>
			<%-- 	<td><c:if test="${not empty bizPmSettleBill.midwayQcCheckPunishAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/midwayQcCheckPunishAmount?id=${fn:split(bizPmSettleBill.settleBillId,',')[0]}">${bizPmSettleBill.midwayQcCheckPunishAmount}</a></c:if></td>
				<td><c:if test="${not empty bizPmSettleBill.completQcCheckPunishAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/completQcCheckPunishAmount?id=<c:if test="${fn:length(fn:split(bizPmSettleBill.settleBillId,',')) eq 1}">${fn:split(bizPmSettleBill.settleBillId,',')[0]}</c:if><c:if test="${fn:length(fn:split(bizPmSettleBill.settleBillId,',')) eq 2}">${fn:split(bizPmSettleBill.settleBillId,',')[1]}</c:if>">${bizPmSettleBill.completQcCheckPunishAmount}</a></c:if></td>
				 --%>
				 <td><a href="${ctx}/pmsettlebill/bizPmSettleBill/midwayQcCheckPunishAmount?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}">${bizPmSettleBill.midwayQcCheckPunishAmount}</a></td>
				 <td><a href="${ctx}/pmsettlebill/bizPmSettleBill/completQcCheckPunishAmount?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}">${bizPmSettleBill.completQcCheckPunishAmount}</a></td>
				
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/querymidwayReward?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&midwayRewardAmount=${bizPmSettleBill.midwayRewardAmount}">${bizPmSettleBill.midwayRewardAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/querycompleteReward?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&completeRewardAmount=${bizPmSettleBill.completeRewardAmount}">${bizPmSettleBill.completeRewardAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/querymidwayPunish?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&midwayPunishAmount=${bizPmSettleBill.midwayPunishAmount}">${bizPmSettleBill.midwayPunishAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/querycompletePunish?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&completePunishAmount=${bizPmSettleBill.completePunishAmount}">${bizPmSettleBill.completePunishAmount}</a></td>

				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryMidwayInspectionReward?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&midwayInspectionRewardAmount=${bizPmSettleBill.midwayInspectionRewardAmount}">${bizPmSettleBill.midwayInspectionRewardAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryCompleteInspectionReward?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&completeInspectionRewardAmount=${bizPmSettleBill.completeInspectionRewardAmount}">${bizPmSettleBill.completeInspectionRewardAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryMidwayInspectionPunish?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&midwayInspectionPunishAmount=${bizPmSettleBill.midwayInspectionPunishAmount}">${bizPmSettleBill.midwayInspectionPunishAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryCompleteInspectionPunish?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&completeInspectionPunishAmount=${bizPmSettleBill.completeInspectionPunishAmount}">${bizPmSettleBill.completeInspectionPunishAmount}</a></td>

				<td><c:if test="${not empty bizPmSettleBill.midwayAuxiliaryMaterialsSettleAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryPmMaterials?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&settleCategory=121">${bizPmSettleBill.midwayAuxiliaryMaterialsSettleAmount}</a></c:if></td>
				<td><c:if test="${not empty bizPmSettleBill.completeAuxiliaryMaterialsSettleAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryPmMaterials?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&settleCategory=122">${bizPmSettleBill.completeAuxiliaryMaterialsSettleAmount}</a></c:if></td>
				<td><c:if test="${not empty bizPmSettleBill.materialSelfbuyReimburseAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/querySelfbuyMaterial?orderId=${bizPmSettleBill.orderId}">${bizPmSettleBill.materialSelfbuyReimburseAmount}</a></c:if></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryGuaranteeMoney?orderId=${bizPmSettleBill.orderId}&pmGuaranteeMoney=${bizPmSettleBill.guaranteeMoneyAmount}">${bizPmSettleBill.guaranteeMoneyAmount}</a></td>
				<td>${bizPmSettleBill.totalAmount}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>