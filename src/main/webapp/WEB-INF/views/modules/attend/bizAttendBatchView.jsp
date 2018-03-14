<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理考勤记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
			$("#btnMenu").hide();
			$("#menuContent").hide();
		});
		
	</script>
</head>
<body>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			 <tr> <th><h1 style="color:blue; text-align:center">项目经理考勤记录</h1></th></tr>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>项目经理</th>
				<th>手机号</th>
				<th>考勤月份</th>
				<th>星级</th>
				<th>底薪标准</th>
				<th>考勤单编号</th>
				<th>应出勤天数</th>
				<th>实际出勤天数</th>
				<th>事假</th>
				<th>病假</th>
				<th>年假</th>
				<th>全勤</th>
				<th>半勤</th>
				<th>补休</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${billList}" var="bizAttendBill">
			<tr>
				<td>
					${fns:getStoreLabel(bizAttendBill.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizAttendBill.projectMode, 'employee_project_mode', '')}
				</td>
				<td>
					${bizAttendBill.departmentName}
				</td>
				<td>
					${bizAttendBill.managerName}
				</td>
				<td>
					${bizAttendBill.phone}
				</td>
				<td>
					<fmt:formatDate value="${bizAttendBill.attendMonth}" pattern="yyyy年MM月"/>
				</td>
				<td>
					${fns:getDictLabel(bizAttendBill.starLevel, 'manager_star', '')}
				</td>
				<td>
                  ${bizAttendBill.basicSalary}元
				</td>
				<td>
                  ${bizAttendBill.attendBillCode }
				</td>
				<td>
                  ${bizAttendBill.attendDaysMust}
				</td>
				<td>
                  ${bizAttendBill.attendDaysTotal }
				</td>
				<td>
                  ${bizAttendBill.leaveDaysThing }
				</td>
				<td>
                  ${bizAttendBill.leaveDaysSick }
				</td>
				<td>
                  ${bizAttendBill.leaveDaysAnnual }
				</td>
				<td>
                  ${bizAttendBill.attendDaysWhole }
				</td>
				<td>
                  ${bizAttendBill.attendDaysHalf }
				</td>
				<td>
                  ${bizAttendBill.restDays }
				</td>
			</tr>
		</c:forEach>
		</tbody>
		
	</table>
	
</body>
</html>