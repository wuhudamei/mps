<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工资单批次审批管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/*$("#searchForm").validate({
				errorPlacement: function(error, element) {
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			})*/
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/attend/bizPmAttendSalarySummaryBillRel/findSalarySummaryBillRel">工资单批次审批列表</a></li>
		<%-- <shiro:hasPermission name="attend:bizPmAttendSalarySummaryBillRel:edit"><li><a href="${ctx}/attend/bizPmAttendSalarySummaryBillRel/form">工资单批次审批添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmAttendSalarySummaryBillRel" action="${ctx}/attend/bizPmAttendSalarySummaryBillRel/findSalarySummaryBillRel" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear required">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>考勤月份：</label>
				<input name="attendMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${bizPmAttendSalarySummaryBillRel.attendMonth}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true,maxDate:'#F{$dp.$D(\'hidTime\')}'});"/>

				<input name="hidTime" id="hidTime" type="hidden" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${maxMonth}"/>
			</li>
			<li><label>批次编号：</label>
				<form:input path="pmAttendSalarySummaryBillCode" htmlEscape="false" maxlength="64" class="input-medium" />
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
				<th>工资批次编号</th>
				<th>考勤月份</th>
				<th>批次生成日期</th>
				<th>工资单数量</th>
				<th>工资批次状态</th>
				<th>操作人</th>
				<shiro:hasPermission name="attend:bizPmAttendSalarySummaryBillRel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmAttendSalarySummaryBillRel">
			<tr>
				<td>
					${fns:getStoreLabel(bizPmAttendSalarySummaryBillRel.storeId, '')}
				</td>
				<td>
					${bizPmAttendSalarySummaryBillRel.pmAttendSalarySummaryBillCode }
				</td>
				<td>
					${bizPmAttendSalarySummaryBillRel.attendMonth }
				</td>
				<td>
					<fmt:formatDate value="${bizPmAttendSalarySummaryBillRel.generatedDatetime }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizPmAttendSalarySummaryBillRel.salrayBillCount }
				</td>
				<td>
					<c:if test="${bizPmAttendSalarySummaryBillRel.status=='10'}">10-已生成批次</c:if>
				</td>
				<td>
					<c:if test="${empty bizPmAttendSalarySummaryBillRel.realName }">
						系统管理员
					</c:if>
					<c:if test="${!empty bizPmAttendSalarySummaryBillRel.realName }">
						${bizPmAttendSalarySummaryBillRel.realName }
					</c:if>
				</td>
				<shiro:hasPermission name="attend:bizPmAttendSalarySummaryBillRel:edit"><td>
    				<a href="${ctx}/attend/bizPmAttendSalarySummaryBillRel/updateStatus?pmAttendSalarySummaryBillId=${bizPmAttendSalarySummaryBillRel.pmAttendSalarySummaryBillId}&status=20&storeId=${bizPmAttendSalarySummaryBillRel.storeId}&attendMonth=${bizPmAttendSalarySummaryBillRel.attendMonth}">审核通过</a>
					<a href="${ctx}/attend/bizPmAttendSalarySummaryBillRel/updateStatus?pmAttendSalarySummaryBillId=${bizPmAttendSalarySummaryBillRel.pmAttendSalarySummaryBillId}&status=30&storeId=${bizPmAttendSalarySummaryBillRel.storeId}&attendMonth=${bizPmAttendSalarySummaryBillRel.attendMonth}" onclick="return confirmx('确认要删除该工资单批次审批吗？', this.href)">批次作废</a>
					<a href="${ctx}/attend/bizPmAttendSalarySummaryBillRel/form?pmAttendSalarySummaryBillId=${bizPmAttendSalarySummaryBillRel.pmAttendSalarySummaryBillId}&flag=1" >详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>