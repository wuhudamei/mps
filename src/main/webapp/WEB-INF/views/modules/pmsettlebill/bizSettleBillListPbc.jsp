<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算单管理</title>
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
			$("#searchForm").attr("action", "${ctx}/pmsettlebill/bizPmSettleBill/exportExcelPbc");
			$("#searchForm").submit();
		}

		function searchButton(){
			$("#searchForm").attr("action", "${ctx}/pmsettlebill/bizPmSettleBill/loadSettleListPbc");
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
		<li class="active"><a href="${ctx}/pmsettlebill/bizPmSettleBill/loadSettleListPbc">结算单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmSettleBill" action="${ctx}/pmsettlebill/bizPmSettleBill/loadSettleListPbc" method="post" class="breadcrumb form-search">
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
			<li><label>质检员：</label>
				<form:input path="orderInspector" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
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
				<th>质检员</th>
				<th>中期提成</th>
				<th>中期远程费提成</th>
				<th>竣工提成</th>
				<th>竣工远程费提成</th>
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
				<td>${bizPmSettleBill.orderInspector}</td>
				<td>${bizPmSettleBill.qcMidwayCommissionAmount}</td>
				<td>${bizPmSettleBill.qcMidwayLongwayAmount}</td>
				<td>${bizPmSettleBill.qcCompleteCommissionAmount}</td>
				<td>${bizPmSettleBill.qcCompleteLongwayAmount}</td>
				<td>${bizPmSettleBill.totalAmount}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>