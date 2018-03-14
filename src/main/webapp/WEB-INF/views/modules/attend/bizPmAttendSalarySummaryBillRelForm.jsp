<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工资单批次审批管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<c:if test="${flag==1 }">
			<li><a href="${ctx}/attend/bizPmAttendSalarySummaryBillRel/list">工资单批次审批</a></li>
		</c:if>
		<c:if test="${flag==2 }">
			<li><a href="${ctx}/attend/bizPmAttendSalarySummaryBillRel/findList">项目经理工资批次查询</a></li>
		</c:if>
		<li class="active"><a href="${ctx}/attend/bizPmAttendSalarySummaryBillRel/form?pmAttendSalarySummaryBillId=${bizPmAttendSalarySummaryBillRel.pmAttendSalarySummaryBillId}">工资单批次详情</a></li>
	</ul><br/>
	<div>
		<a class="btn"  style="text-align: center;" href="javascript:" onclick="history.go(-1);">返回</a>
	</div>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="bizPmAttendSalaryBill" action="${ctx}/attend/bizPmAttendSalarySummaryBillRel/form?pmAttendSalarySummaryBillId=${bizPmAttendSalarySummaryBillRel.pmAttendSalarySummaryBillId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>区域</th>
				<th>项目经理</th>
				<th>手机号</th>
				<th>考勤月份</th>
				<th>星级</th>
				<th>底薪标准</th>
				<th>工资单编号</th>
				<th>应签到次数</th>
				<th>实际签到次数</th>
				<th>实际取值总和</th>
				<th>欠缺签到次数</th>
				<!-- <th>扣款总金额</th> -->
				<th>考勤率</th>
				<th>实际薪酬</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmAttendSalaryBill">
			<tr>
				<td>
					${fns:getStoreLabel(bizPmAttendSalaryBill.storeId, '')}
				</td>
				<td>
					${bizPmAttendSalaryBill.departmentName}
				</td>
				<td>
					${bizPmAttendSalaryBill.itemManager}
				</td>
				<td>
					${bizPmAttendSalaryBill.phone}
				</td>
				<td>
					${bizPmAttendSalaryBill.attendMonth}
				</td>
				<td>
					${fns:getDictLabel(bizPmAttendSalaryBill.pmStar, 'manager_star', '')}
				</td>
				<td>
					${bizPmAttendSalaryBill.pmStarSalary }
				</td>
				<td>
					${bizPmAttendSalaryBill.pmAttendSalaryBillCode}
				</td>
				<td>
					${bizPmAttendSalaryBill.countDay}
				</td>
				<td>
					${bizPmAttendSalaryBill.sjCountDay}
				</td>
				<td>
					${bizPmAttendSalaryBill.actualValueTotal}
				</td>
				<td>
					${bizPmAttendSalaryBill.wcDay}
				</td>
				<%-- <td>
					${bizPmAttendSalaryBill.pmStarSalaryPunish}
				</td> --%>
				<td>
					${bizPmAttendSalaryBill.attendRate}
				</td>
				<td>
					${bizPmAttendSalaryBill.pmStarSalaryReal}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%--<div class="pagination">${page}</div>--%>
</body>
</html>