<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检罚款明细查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		function findEngineListByStoreIdAndProjectMode(){
			var html3 = '<option value=""></option>';
		var storeId = $("#storeId").val();
		var projectModeValue = $("#projectMode").val();
		if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
			return;
		}
		//根据门店个,工程模式    动态加载工程区域
		$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectModeValue,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
							}
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});		
		}
		
		

	        function formSubmit() {


	            $("#searchForm").attr("action", "${ctx}/qualityControlFineUpdate/listInfo");
	            $("#searchForm").submit();
	        }
		
		
		
	</script>
</head>	
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
	<li class="active"><a href="#">质检罚款修改</a></li>
	</ul>
	<form:form id="searchForm"  modelAttribute="qualityControlFineUpdateEntity" action="${ctx}/qualityControlFineUpdate/listInfo" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- 合同开工日期 -->
		<input id="startDate1" type="hidden" value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>">
		<input id="endDate1" type="hidden" value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>">
		
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId"  class="input-medium needClear"  onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" >
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
				</form:select>
			</c:if>
			
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			</li>
			<li><label>订单编号：</label>
			<form:input path="orderNumber" htmlEscape="false" maxlength="30" class="input-large"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			<li><label>质检员：</label>
			<form:input path="orderInspectorName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="formSubmit()"/></li>
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
				<th>订单编号</th>
				
				<th>小区</th>
				<th>顾客</th>
				<th>质检报告编号</th>
				<th>报告类型</th>
				<th>检查人</th>
				<th>检查项分类</th>
				<th>检查项</th>
				<th>项目经理</th>
				<th>罚项目经理金额</th>
				<th>工人组长</th>
				<th>罚工人金额</th>
				<th>质检员</th>
				<th>罚质检员金额</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="report" varStatus="status">
			<tr>
			
				<td>
				${fns:getStoreLabel(report.storeId, '')}
				</td>
				<td>${fns:getDictLabel(report.projectMode, 'project_mode', '')}</td>
				<td>${report.engineDepartName }</td>
				<td>
				${report.orderNumber}
				
				</td>
				<td>
				${report.xiaoqu}-${report.lou}-${report.danyuan}-${report.shi}
				</td>
				<td>${report.customerName}-${report.customerPhone}</td>
				<td>
					<a href="${ctx}/bizorderqcbill/bizOrderQcBill/details?qcBillId=${report.billId}">${report.reportCode}</a>
				</td>
				<td>
				<c:if test="${report.reportType==1 }">
				约检
				</c:if>
				<c:if test="${report.reportType==2 }">
				抽检
				</c:if>
				</td>
				<td>
					${report.checkManName}
				</td>
				<td>
					${report.checkTypeName}
				</td>
				<td>
					${report.checkItemName}
				</td>
				<td>
					${report.managerName}
				
				</td>
				<td>
					${report.punishMoney}
				</td>
				<td>
					${report.workerGroupLeaderName}
				</td>
				<td>
					${report.workerMoney}
				</td>
				
				<td>
					${report.orderInspectorName}
				</td>
				<td>
					${report.inspectorMoney}
				</td>
				<shiro:hasPermission name="qualityControlFineUpdate:qualityControlFineUpdate:view">
					<td>
    					<a href="${ctx}/qualityControlFineUpdate/fineUpdate?qcBillCode=${report.reportCode}&qcCheckItemId=${report.qcCheckItemId}">修改罚款</a>
					</td>
				</shiro:hasPermission>
			
				
			
				
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>