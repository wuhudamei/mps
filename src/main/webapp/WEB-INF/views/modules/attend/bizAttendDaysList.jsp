<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理星级设置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//全
			var attendDaysWhole =0;
			//半
			var attendDaysHalf =0;
			
			$("select option:selected").each(function(){
				var val=$(this).val();
				if(val==1){
					attendDaysWhole++;
				}else if(val==2){
					attendDaysHalf++;
				}
			});
			
			//实际出勤天数
			$("input[name='attendDaysTotal']").val(attendDaysWhole+attendDaysHalf);
			//全勤天数
			$("input[name='attendDaysWhole']").val(attendDaysWhole);
			//半勤
			$("input[name='attendDaysHalf']").val(attendDaysHalf);
			
			
			$("#inputForm").validate({
				rules : {
					attendDaysTotal:"number",
					attendDaysWhole : "number",
					attendDaysHalf : "number",
					
					basicSalary : "number",
					
					attendDaysMust : {
						required:true,
						number:true
					},
					
					leaveDaysThing : "number",
					
					leaveDaysSick : "number",
					leaveDaysAnnual:"number",
					restDays : "number",
				},
				messages : {
					
					attendDaysTotal : "请输入数字",
					attendDaysWhole : "请输入数字",
					attendDaysHalf : "请输入数字",
					
					basicSalary : "请输入数字",
					
					attendDaysMust:{
						required:"不能为空",
						number : "请输入数字",
					},
					leaveDaysThing : "请输入数字",
					
					leaveDaysSick : "请输入数字",
					leaveDaysAnnual : "请输入数字",
					restDays : "请输入数字",
				},
				errorContainer: "#messageBox",
				
			});
			
				$("#basicSalary").bind("change",function(){
					
				$("#btnSubmit").unbind("click");
				$("#btnSubmit").bind("click",function(){
					
					var attendRemarks = $("#attendRemarks").val();
					
					if(attendRemarks==null || attendRemarks == ''){
						alert("修改底薪后，备注不能为空，最多输入50字");
						return false;
					}
				});
				
			});
			
			$("#btnSubmit").unbind("click");
			$("#btnSubmit").bind("click",function(){
				
				var attendMonth = $("#attendMonth").val();
				
				if(attendMonth==null || attendMonth == ''){
					alert("没有签到数据，无法生成考勤单");
					return ;
				}
			});
			
		});
		
		//修改考勤状态
		function changVal(val,id){
			
			var url ="${ctx}/bizAttend/bizAttendDays/updateVal";
			var param = {"attendType":val,"id":id};
			$.post(url,param,function(date){
				//如果成功 重新计算
				if(date=='1'){
					//全
					var attendDaysWhole =0;
					//半
					var attendDaysHalf =0;
					
					$("select option:selected").each(function(){
						var val=$(this).val();
						if(val==1){
							attendDaysWhole++;
						}else if(val==2){
							attendDaysHalf++;
						}
					});
					
					//实际出勤天数
					$("input[name='attendDaysTotal']").val(attendDaysWhole+attendDaysHalf);
					//全勤天数
					$("input[name='attendDaysWhole']").val(attendDaysWhole);
					//半勤
					$("input[name='attendDaysHalf']").val(attendDaysHalf);	
				}
			},"json")
		};
		
	</script>
</head>
<body>
	<form:form id="inputForm" action="${ctx}/bizAttend/bizAttendBills/createAttendBill" method="post" class="breadcrumb form-search">
		
		<ul class="ul-form">
		
			<li><label>考勤月份：</label>
				<input name="attendMonth" id="attendMonth" value="${attendMonth}" class="input-medium needClear" readonly="readonly"/>
			</li>
			<li><label>实际出勤天数：</label>
				<input name="attendDaysTotal" id="attendDaysTotal" class="input-medium needClear" readonly="readonly"/>
			</li>
			<li><label>全勤：</label>
				<input name="attendDaysWhole" id="attendDaysWhole" class="input-medium needClear" readonly="readonly" />
			</li>
			<li><label>半勤：</label>
				<input name="attendDaysHalf" id="attendDaysHalf"  class="input-medium needClear"  readonly="readonly"/>
			</li>
			<c:if test="${empty bizAttendBill.id}">
				<li><label>底薪：</label>
					<input name="basicSalary" value="${salary}" id="basicSalary" class="input-medium needClear"/>
				</li>
			</c:if>
			
			<c:if test="${!empty bizAttendBill.id }">
				<li><label>底薪：</label>
					<input name="basicSalary" value="${bizAttendBill.basicSalary}" id="basicSalary" class="input-medium needClear"/>
				</li>
			</c:if>			
			<li><label>备注：</label>
				<input name="attendRemarks" id="attendRemarks" value="${bizAttendBill.attendRemarks}" maxlength="50" class="input-medium needClear"/>
			</li>
			<li><label>应出勤天数：</label>
				<input name="attendDaysMust" value="${bizAttendBill.attendDaysMust}" class="input-medium needClear"/>
			</li>
			<li><label>事假：</label>
				<input name="leaveDaysThing"  value="${bizAttendBill.leaveDaysThing}" class="input-medium needClear"/>
			</li>
			<li><label>病假：</label>
				<input name="leaveDaysSick" value="${bizAttendBill.leaveDaysSick}" class="input-medium needClear"/>
			</li>
			<li><label>年假：</label>
				<input name="leaveDaysAnnual" value="${bizAttendBill.leaveDaysAnnual}" class="input-medium needClear"/>
			</li>
			<li><label>补休：</label>
				<input name="restDays" value="${bizAttendBill.restDays}" class="input-medium needClear"/>
				
				<input type="hidden" name="attendEmployeeRole" value="${attendEmployeeRole}">
				<input type="hidden" name="attendEmployeeId" value="${attendEmployeeId}">
				<input type="hidden" name="storeId" value="${storeId}">
				<input type="hidden" name="projectMode" value="${projectMode }">
				
				<input type="hidden" name="id" value="${bizAttendBill.id}">
			</li>
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="生成考勤单"/></li>
				<li><a title="返 回" href="javascript:history.go(-1);" class="btn">返 回</a></li>
		</ul>
		</form:form>
			
	<sys:message content="${message}"/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目经理名称</th>
				<th>考勤日期</th>
				<th>最早签到时间</th>
				<th>最早签到误差</th>
				<th>最晚签到时间</th>
				<th>最晚签到误差</th>
				<shiro:hasPermission name="bizAttendBills:bizAttendBills:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${attendDays}" var="bizAttendDay">
			<tr>	
				<td>
					${bizAttendDay.managerName }
				</td>
				<td>
					<fmt:formatDate value="${bizAttendDay.attendDate }" pattern="yyyy年MM月dd日"/>
				</td>
				<td>
					<fmt:formatDate value="${bizAttendDay.earlySignDate }" pattern="yyyy/MM/dd/ HH:mm:ss"/>
				</td>
				<td>
					${bizAttendDay.earlySignReeorDistance }米
				</td>
				<td>
					<fmt:formatDate value="${bizAttendDay.lateSignDate }" pattern="yyyy/MM/dd/ HH:mm:ss"/>
				</td>
				<td>
					${bizAttendDay.lateSignErrorDistance }米
				</td>						
				<shiro:hasPermission name="bizAttendBills:bizAttendBills:edit">
				<td>
					<select name="attendType" id="attendType"  onchange="changVal(this.value,${bizAttendDay.id})">
						<option value="1" <c:if test="${bizAttendDay.attendType=='1'}">selected="selected"</c:if>>全勤</option>
						<option value="2" <c:if test="${bizAttendDay.attendType=='2'}">selected="selected"</c:if>>半勤</option>
						<option value="3" <c:if test="${bizAttendDay.attendType=='3'}">selected="selected"</c:if>>缺勤</option>
					</select>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>