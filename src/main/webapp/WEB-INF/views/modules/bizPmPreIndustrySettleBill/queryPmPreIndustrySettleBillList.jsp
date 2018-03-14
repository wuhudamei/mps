<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目经理结算单查询</title>
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
							if('${bizPmPreIndustrySettleBill.enginDepartId}' == data[i].value){
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
		
		// --全选框被单击---
		function ChkAllClick(sonName, cbAllId) {
			var arrSon = document.getElementsByName(sonName);
			var cbAll = document.getElementById(cbAllId);
			var tempState = cbAll.checked;
			for (i = 0; i < arrSon.length; i++) {
				if (arrSon[i].checked != tempState)
					arrSon[i].click();
			}
		}

		// --子项复选框被单击---
		function ChkSonClick(sonName, cbAllId) {
			var arrSon = document.getElementsByName(sonName);
			var cbAll = document.getElementById(cbAllId);
			for (var i = 0; i < arrSon.length; i++) {
				if (!arrSon[i].checked) {
					cbAll.checked = false;
					return;
				}
			}
			cbAll.checked = true;
		}
		
		function querySettleBillInfo(id){
			window.location.href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openSettleInfoInfo?settleBillId="+id;
		}
		
		function searchButton(){
			$("#searchForm").attr("action", "${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/queryPmSettleBillList");
			$("#searchForm").submit();
		}
		
		function exportExcel(){
			$("#searchForm").attr("action", "${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/exportPmSettleBill");
			$("#searchForm").submit();
            $("#searchForm").attr("action", "${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/queryPmSettleBillList");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">项目经理结算单查询</a></li>
	</ul>
	<br />


	<form:form id="searchForm" modelAttribute="bizPmPreIndustrySettleBill"
		action="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/queryPmSettleBillList"
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
				</c:if></li>

			<li><label>工程模式：</label> <form:select path="projectMode"
					id="projectMode" class="input-medium" disabled="true">
					<form:option value="4" label="准产业" />
				</form:select></li>

			<li><label>区域：</label> <select id="enginDepartId"
				name="enginDepartId" class="input-medium">

			</select></li>
			<li><label>订单编号：</label> <form:input path="orderNum"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>客户姓名：</label> <form:input path="customerName"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>项目经理：</label> <form:input path="itemCustomer"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>结算单编号：</label> <form:input
					path="pmPreIndustrySettleBillCode" htmlEscape="false"
					maxlength="64" class="input-medium needClear" /></li>
			<li><label>结算单类型 ：</label> <form:select id="settleBillType"
					path="settleBillType" class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('settle_bill_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>月度：</label>
				<input name="settleMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="${bizPmPreIndustrySettleBill.settleMonth}" onclick="WdatePicker({dateFmt:'yyyyMM',isShowClear:true});"/>
			</li>

			<li><label>生成月度结算时间：</label> <input name="createSettleMonthStartDate" type="text"
											id="createSettleMonthStartDate" readonly="readonly" maxlength="20"
											class="input-medium Wdate"
											value="<fmt:formatDate value="${bizPmPreIndustrySettleBill.createSettleMonthStartDate}" pattern="yyyy-MM-dd"/>"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'createSettleMonthEndDate\')}',isShowClear:false});" />
				&nbsp;至&nbsp; <input name="createSettleMonthEndDate" type="text" id="createSettleMonthEndDate"
									 readonly="readonly" maxlength="20" class="input-medium Wdate"
									 value="<fmt:formatDate value="${bizPmPreIndustrySettleBill.createSettleMonthEndDate}" pattern="yyyy-MM-dd"/>"
									 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createSettleMonthStartDate\')}',isShowClear:false});" />
			</li>

			<li><label>结算单创建时间：</label> <input name="createSettleStartDate" type="text"
												id="createSettleStartDate" readonly="readonly" maxlength="20"
												class="input-medium Wdate"
												value="<fmt:formatDate value="${bizPmPreIndustrySettleBill.createSettleStartDate}" pattern="yyyy-MM-dd"/>"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'createSettleEndDate\')}',isShowClear:false});" />
				&nbsp;至&nbsp; <input name="createSettleEndDate" type="text" id="createSettleEndDate"
									 readonly="readonly" maxlength="20" class="input-medium Wdate"
									 value="<fmt:formatDate value="${bizPmPreIndustrySettleBill.createSettleEndDate}" pattern="yyyy-MM-dd"/>"
									 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createSettleStartDate\')}',isShowClear:false});" />
			</li>

			<li style="width: 100%"><label>结算单状态：</label> <input id="chkAll"
				name="chkAll" type="checkbox" value="全选"
				onclick="ChkAllClick('status','chkAll')" />全选</li>
			<li><input type="checkbox" name="status" value="10"
				onclick="ChkSonClick('status','chkAll')"
				<c:forEach items="${bizPmPreIndustrySettleBill.statusList}" var="item"><c:if test="${item == '10'}">checked="checked"</c:if></c:forEach> />已创建结算单

				<input type="checkbox" name="status" value="35"
				onclick="ChkSonClick('status','chkAll')"
				<c:forEach items="${bizPmPreIndustrySettleBill.statusList}" var="item"><c:if test="${item == '35'}">checked="checked"</c:if></c:forEach> />已下发给项目经理

				<input type="checkbox" name="status" value="38"
				onclick="ChkSonClick('status','chkAll')"
				<c:forEach items="${bizPmPreIndustrySettleBill.statusList}" var="item"><c:if test="${item == '38'}">checked="checked"</c:if></c:forEach> />已重新下发给项目经理

				<input type="checkbox" name="status" value="40"
				onclick="ChkSonClick('status','chkAll')"
				<c:forEach items="${bizPmPreIndustrySettleBill.statusList}" var="item"><c:if test="${item == '40'}">checked="checked"</c:if></c:forEach> />项目经理已同意结算金额

				<input type="checkbox" name="status" value="45"
				onclick="ChkSonClick('status','chkAll')"
				<c:forEach items="${bizPmPreIndustrySettleBill.statusList}" var="item"><c:if test="${item == '45'}">checked="checked"</c:if></c:forEach> />项目经理拒绝结算金额

				<input type="checkbox" name="status" value="50"
				onclick="ChkSonClick('status','chkAll')"
				<c:forEach items="${bizPmPreIndustrySettleBill.statusList}" var="item"><c:if test="${item == '50'}">checked="checked"</c:if></c:forEach> />已生成月度结算
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="button" value="查询" onclick="searchButton()" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearCondition()" /></li>
			<li class="btns"><input class="btn btn-primary" type="button"
				value="导出excel" onclick="exportExcel()" /></li>

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
				<th>结算单创建时间</th>
				<th>订单编号</th>
				<th>小区</th>
				<th>客户</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>结算单编号</th>
				<th>结算单类型</th>
				<th>结算单金额</th>
				<th>结算单状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="settleBill">
				<tr>
					<td>${fns:getStoreLabel(settleBill.storeId, '')}</td>
					<td>${fns:getDictLabel(settleBill.projectMode, 'project_mode', '')}
					</td>
					<td>${settleBill.departmentName}</td>
					<td><fmt:formatDate value="${settleBill.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${settleBill.orderNum}</td>
					<td>
						${settleBill.communityName}-${settleBill.buildNumber}-${settleBill.buildUnit}-${settleBill.buildRoom}
					</td>
					<td>${settleBill.customerName}</td>
					<td>${settleBill.itemCustomer}</td>
					<td>${settleBill.itemPhone}</td>
					<td>${settleBill.pmPreIndustrySettleBillCode}</td>
					<td>${fns:getDictLabel(settleBill.settleBillType, 'settle_bill_type', '')}</td>
					<td>${settleBill.realSettleAmount}</td>
					<td>${fns:getDictLabel(settleBill.status, 'pm_settle_status', '')}</td>
					<td><a href="#"
						onclick="querySettleBillInfo(${settleBill.id})">查看结算单</a> 
						<a href="${ctx}/PreSettleBilllog/middetail?id=${settleBill.id }&orderId=${settleBill.orderId }&settleBillType=${settleBill.settleBillType }">日志</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>