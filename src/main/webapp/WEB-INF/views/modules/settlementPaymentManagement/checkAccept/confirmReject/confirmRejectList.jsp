<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>约检验收驳回原因查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("tbody>tr").click(function(){

			    $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

			});
			findEngineList();
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//回显区域
		function findEngineList(){
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
								
								if('${inspectorConfirmReject.enginDepartId}' == data[v].engineDepartId){
									$("#s2id_enginDepartId .select2-chosen").html(data[v].engineDepartName);
									html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
								}
								
							}
							
							$("#enginDepartId").html(html3);
						} else {
							$("#enginDepartId").html(html3);
						}

					}

				});		
		}
		//动态加载区域
		function findEngineListTwo(){
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
							
							$("#enginDepartId").html(html3);
						} else {
							$("#enginDepartId").html(html3);
						}

					}

				});		
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/modules/settlementPaymentManagement/checkAccept/confirmReject/web/InspectorConfirmReject/preList">约检验收驳回原因查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="inspectorConfirmReject" action="${ctx}/modules/settlementPaymentManagement/checkAccept/confirmReject/web/InspectorConfirmReject/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
		<ul class="ul-form">
			<li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear"  onchange="findEngineListTwo()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findEngineListTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label style="width: 150px;">质检员确认验收日期：</label>
				<input name="beginAcceptCheckDatetime" type="text" readonly="readonly" id="beginAcceptCheckDatetime" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${inspectorConfirmReject.beginAcceptCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endAcceptCheckDatetime\')}',isShowClear:false});"/> - 
				<input name="endAcceptCheckDatetime" type="text" readonly="readonly" id="endAcceptCheckDatetime" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${inspectorConfirmReject.endAcceptCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginAcceptCheckDatetime\')}',isShowClear:false});"/>
			</li>
			<li><label>结算驳回时间：</label>
				<input name="beginReviewDatetime" type="text" readonly="readonly" id="beginReviewDatetime" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${inspectorConfirmReject.beginReviewDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endReviewDatetime\')}',isShowClear:false});"/> - 
				<input name="endReviewDatetime" type="text" readonly="readonly" id="endReviewDatetime" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${inspectorConfirmReject.endReviewDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginReviewDatetime\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>订单区域</th>
				<th>订单编号</th>
				<th>客户</th>
				<th>小区</th>
				<th>质检单编号</th>
				<th>约检内容</th>
				<th>项目经理</th>
				<th>质检员</th>
				<th>质检员确认验收日期</th>
				<th>结算员驳回时间</th>
				<th>结算员驳回原因</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="inspectorConfirmReject">
				<tr>
					<td>
						${inspectorConfirmReject.storeName}
					</td>
					<td>
						${inspectorConfirmReject.projectModeName}
					</td>
					<td>
						${inspectorConfirmReject.departmentName}
					</td>
					<td>
						${inspectorConfirmReject.orderNumber}
					</td>
					<td>
						${inspectorConfirmReject.customerName}
					</td>
					<td>
						${inspectorConfirmReject.communityName }-${inspectorConfirmReject.buildNumber }-${inspectorConfirmReject.buildUnit }-${inspectorConfirmReject.buildRoom }
					</td>
					<td>
						${inspectorConfirmReject.qcBillCode}
					</td>
					<td>
						${inspectorConfirmReject.qcCheckNodeName }
					</td>
					<td>
						${inspectorConfirmReject.itemManager }
					</td>
					<td>
						${inspectorConfirmReject.orderInspector }
					</td>
					<td>
						<fmt:formatDate value="${inspectorConfirmReject.acceptCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${inspectorConfirmReject.reviewDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						${inspectorConfirmReject.reviewRemark }
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>