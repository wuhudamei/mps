<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标化辅材记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("tbody>tr").click(function(){

			    $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function addRecord(){
			
			window.location.href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBill/formVo";
		}
	</script> 
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBill/list">标化辅材领取记录列表</a></li>
		<shiro:hasPermission name="standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit"><li><a href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBill/formVo">标化辅材领取记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialsStandardReceiveBillVo" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBill/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单号：</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>领取人：</label>
				<form:input path="receiveEmployeeName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>领取日期：</label>
				<input name="beginReceiveDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizMaterialsStandardReceiveBill.beginReceiveDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endReceiveDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizMaterialsStandardReceiveBill.endReceiveDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnAdd" class="btn btn-primary" type="button" value="添加" onclick="addRecord()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>领取日期 </th>
				<th>申请单号</th>
				<th>领取人</th>
				<th>订单号 </th>
				<th>客户姓名</th>
				<th>金额</th>
				<shiro:hasPermission name="standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialsStandardReceiveBill">
			<tr>
				<td>
					${fns:getStoreLabel(bizMaterialsStandardReceiveBill.storeId, '')}
				</td>
				<td>
					<fmt:formatDate value="${bizMaterialsStandardReceiveBill.receiveDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					<%-- ${bizMaterialsStandardReceiveBill.receiveDatetime } --%>
				</td>
				<td>
					${bizMaterialsStandardReceiveBill.materialsStandardReceiveBillCode }
				</td>
				<td>
					${bizMaterialsStandardReceiveBill.receiveEmployeeName }
				</td>
				<td>
					${bizMaterialsStandardReceiveBill.orderNo }
				</td>
				<td>
					${bizMaterialsStandardReceiveBill.customerName }
				</td>
				<td>
					${bizMaterialsStandardReceiveBill.receiveBillAmount }
				</td>
				<shiro:hasPermission name="standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit"><td>
    				<c:if test="${bizMaterialsStandardReceiveBill.isSettled == '0' }"><a href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBill/formVo?id=${bizMaterialsStandardReceiveBill.id}">修改</a></c:if>
					<a href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBill/detail?id=${bizMaterialsStandardReceiveBill.id}">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>