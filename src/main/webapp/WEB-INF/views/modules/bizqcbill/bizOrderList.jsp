<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		
		$(function(){
			/*  $("#isZeroId").click(function() {
              	 $('input[name="isNullActualStartDate"]').attr("checked",this.checked); 
          	 }); */
          	 var $status = $("input[name='isNullActualStartDate']");
          	 $status.click(function(){
               $("#isZeroId").attr("checked",$status.length == $("input[name='isNullActualStartDate']:checked").length ? true : false);
            });
		 });
		
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
		<li class="active"><a href="${ctx}/bizorderqcbill/bizOrderQcBill/list">订单报告</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderQcBill" action="${ctx}/bizorderqcbill/bizOrderQcBill/bizOrderQcBillList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>质检员：</label>
				<form:input path="orderInspector" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li>
				<input type="checkbox" name="isNullActualStartDate" value="1" id="isZeroId" <c:if test="${fns:checkIsExits(bizOrderQcBill.isNullActualStartDate,'1')}"> checked="checked"</c:if>/>实际开工日期不为空
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
				<th>订单编号</th>
				<th>顾客</th>
				<th>实际开工日期</th>
				<th>项目经理</th>
				<th>质检员</th>
				<th>质检签到次数</th>
				<th>质检报告数</th>
				<th>其中抽检报告数</th>
				<th>其中约检报告数</th>
				<th>复检报告数</th>
				<th>复检次数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order">
			<tr>
				<td>
					${fns:getStoreLabel(order.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(order.projectMode, 'project_mode', '')}
				</td>
				<td>
					${order.orderNumber}
				</td>
				<td>
					${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}-${order.customerName}
				</td>
				<td>
					<fmt:formatDate value="${order.actualStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${order.itemManager}
				</td>
				<td>
					${order.orderInspector}
				</td>
				<td>
					<c:if test="${order.signCount!='0' }">
						${order.signCount}
    				</c:if>
				</td>
				<td>
					<c:if test="${order.count!='0' }">
						${order.count}
    				</c:if>
				</td>
				<td>
					<c:if test="${order.selectCheckCount!='0' }">
						${order.selectCheckCount}
    				</c:if>
				</td>
				<td>
					<c:if test="${order.checkCount!='0' }">
						${order.checkCount}
    				</c:if>
				</td>
				<td>
					<c:if test="${order.recheckCount!='0' }">
						${order.recheckCount}
    				</c:if>
				</td>
				<td>
					<c:if test="${order.recheckTimes!='0' }">
						${order.recheckTimes}
    				</c:if>
				</td>
				<td>
					<c:if test="${order.count!='0' }">
    					<a href="${ctx}/bizorderqcbill/bizOrderQcBill/report?orderId=${order.orderId}">详情</a>
    				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
<script type="text/javascript">

</script>
</html>