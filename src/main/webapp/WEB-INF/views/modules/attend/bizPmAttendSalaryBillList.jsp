<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤月度工资单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			//星级对照底薪
			var starSalaryAllAttend = $("#starSalaryAllAttend").val();
			//考勤月份
			var date = "${bizMonthSalary.attendMonth }";
			//考勤天数
			var naturalDay = $("#naturalDay").val();
			var day = mGetDate(date.substring(0,date.indexOf("-")),date.substring(date.indexOf("-")+1,date.length));
			
			//实际签到天数
			var sjday = "${bizMonthSalary.realSignTimes }";
			//本月考勤底薪
			var starSalaryReal = $("#starSalaryReal").val(((starSalaryAllAttend/day)*naturalDay).toFixed(2));
			
		});

		function starSalaryVal(){
			$("#sjSalary").val("${bizMonthSalary.starSalaryAllAttend }");
			$("#sjSalaryHid").val("${bizMonthSalary.starSalaryAllAttend }");
			var sjSalary = $("#sjSalary").val();
			var kql = $("#attendRate").val();
			$("#finalSalary").val((sjSalary*(kql/100)).toFixed(2));
		}
		
		//获取当前月份天数
		function mGetDate(year, month){
		    var d = new Date(year, month, 0);
		    return d.getDate();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<c:if test="${flag == 1 }">
			<li><a href="${ctx}/attend/bizPmAttendSalaryBill/salaryBillAuditing">工程部月度工资单审计</a></li>
		</c:if>
		<c:if test="${flag == 2 }">
			<li><a href="${ctx}/attend/bizPmAttendSalaryBill/salaryBillAuditingList">项目经理工资单查询</a></li>
		</c:if>
		<li class="active"><a href="${ctx}/attend/bizPmAttendSalaryBill/form?id=${bizPmAttendSalaryBill.id}">工资单明细<shiro:lacksPermission name="attend:bizPmAttendSalaryBill:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPmAttendSalaryBill" action="${ctx}/attend/bizPmAttendSalaryBill/save" method="post" class="form-horizontal">
		<form:input path="pmAttendMonthId" type="hidden" value="${bizMonthSalary.pmAttendMonthId }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
		<sys:message content="${message}"/>	
		<div align="center">
			<h1>${bizMonthSalary.itemManager }${bizMonthSalary.attendMonth }份工资单</h1>
		</div>	
		<div>
			<h3>基本信息设置</h3>
			<div style="margin-left:40px;">
				<label>考勤月份：</label>
				<form:input path="" disabled="true" value="${bizMonthSalary.attendMonth }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				
				<label style="margin-left:40px;">项目经理名称：</label>
				<form:input path="" disabled="true" value="${bizMonthSalary.itemManager }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				<form:input path="pmEmployeeId" type="hidden" value="${bizMonthSalary.itemManagerId }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				
				<label style="margin-left:40px;">项目经理星级：</label>
				<form:input path="" disabled="true" value="${fns:getDictLabel(bizMonthSalary.itemManagerStar, 'manager_star', '')}" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				<form:input path="pmStar" type="hidden" value="${bizMonthSalary.itemManagerStar }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
			</div>
		</div>
		<br/>
		<div>
			<h3>考勤单数据</h3>
			<div style="margin-left:40px;">
				<label>本月应签到总次数：${bizMonthSalary.mustSignTimes }</label>
				
				<label style="margin-left:40px;">本月实际签到总次数：${bizMonthSalary.realSignTimes }</label>

				<label style="margin-left:40px;">实际取值总和：${bizMonthSalary.actualValue }</label>
				
				<label style="margin-left:40px;">欠缺签到总次数：${bizMonthSalary.owedSignTimes }</label>
				
				<br/><br/>
				
				<label>项目经理本月考勤周期：<fmt:formatDate value="${bizMonthSalary.attendStartDate }" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${bizMonthSalary.attendEndDate }" pattern="yyyy-MM-dd"/></label>
				
				<label style="margin-left:40px;">本月实际考勤天数（自然日）：</label>
				<form:input path="" id="naturalDay" disabled="true" value="${bizMonthSalary.naturalDay }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				
				<br/><br/>
				
				<label>考勤率：</label>
				<form:input path="" disabled="true" value="${bizMonthSalary.attendRate }%" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				<form:input path="" id="attendRate" type="hidden" value="${bizMonthSalary.attendRate }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				<span style="color:red">注：考勤率=实际取值总和/应签到总次数*100%</span>
			</div>
		</div>
		<br/>
		<div>
			<h3>工资数据</h3>
			<div style="margin-left:40px;">
				<label>星级对照底薪：</label>
				<form:input path="" id="starSalaryAllAttend" disabled="true" value="${bizMonthSalary.starSalaryAllAttend }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				<form:input path="pmStarSalary" type="hidden" value="${bizMonthSalary.starSalaryAllAttend }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				
				<br/><br/>
				
				<label>本月考勤底薪：</label>
				<form:input path="" id="starSalaryReal" disabled="true" value="" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				<span style="color:red">注：本月考勤底薪=星级对照底薪/考勤月全月天数*本月实际考勤天数（自然日）</span>
				<br/><br/>
				
				<label>本月实际考勤底薪：</label>
				<form:input path="pmStarSalary" disabled="true" value="${bizMonthSalary.pmStarSalaryAttendss}" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				<%--<form:input path="" id="sjSalary" value="" type="hidden" htmlEscape="false" style="width:90px;" class="input-xlarge "/>--%>
				<br/><br/>
				<label>项目经理最终得到薪酬：</label>	
				
				<form:input path="" id="finalSalary" disabled="true" value="${bizMonthSalary.starSalaryReal }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>

				<!-- <label>实际薪水：</label> -->
				<form:input path="pmStarSalaryRealDefault" type="hidden" value="${bizMonthSalary.starSalaryReal }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>		
				<!-- <label>实际薪水：</label> -->
				<form:input path="pmStarSalaryReal" type="hidden" value="${bizMonthSalary.starSalaryReal }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>		
				<!-- <label>星级对照最低底薪：</label> -->
				<form:input path="pmStarSalaryMin" id="starSalaryMin" type="hidden" value="${bizMonthSalary.starSalaryMin }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				<!-- <label>考勤周期全勤薪水：</label> -->
				<form:input path="pmStarSalaryAttendFullDefault" value="" type="hidden"/>
				<form:input path="pmStarSalaryAttendFull" value="" type="hidden"/>
				<!-- <label>薪水扣款：</label> -->
				<form:input path="pmStarSalaryPunish" value="" type="hidden"/>
				<!-- <label>考勤薪水：</label> -->
				<form:input path="pmStarSalaryAttend" value="" type="hidden"/>
				<!-- <label>考勤薪水：</label> -->
				<form:input path="pmStarSalaryAttendDefault" value="" type="hidden"/>
				
				<form:input path="status" value="30" type="hidden"/>
				<span style="color:red">注：项目经理本月最终得到薪酬=本月实际考勤底薪*考勤率</span>
			</div>
		</div>
		<div>
			<a class="btn"  style="text-align: center;" href="javascript:" onclick="history.go(-1);">返回</a>
		</div>
	</form:form>
</body>
</html>