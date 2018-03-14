<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤月度表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			nextForm2();
			nextForm3();
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
		
		function nextForm2(){
			$.ajax({
				url:"${ctx}/attend/bizPmAttendMonth/getDetailForm2List",
				type : "get",
				dataType : "json",
				data:{
					itemManagerId: $("#itemManagerId").val(),
					attendStartDate: $("#attendStartDate").val(),
					attendEndDate: $("#attendEndDate").val(),
					attendMonth:$("#attendMonth").val(),
					storeId: $("#storeId").val(),
					id:$("#id").val()
					},
					success : function(data){
						var td=""
						var realSignTimes = "";
						var mustSignTimes = "";
						var orderId = "";
						var owedSginTime = "";
						var actualValue="";
						if(data.resultMap.length > 0 ){
							for(var i=0;i<data.resultMap.length;i++){
								if(data.resultMap[i].customerName != undefined){
									td+="<tr><td>"+data.resultMap[i].customerName+"</td>"
									+"<td>"+data.resultMap[i].customerAddress+"</td>"
									+"<td>"+data.resultMap[i].orderStatusNumber+"</td>"
									+"<td>"+data.resultMap[i].mustSignTimes+"</td>"
									+"<td>"+data.resultMap[i].realSignTimes+"</td>"
									+"<td>"+data.resultMap[i].actualValue+"</td>"
									+"<td>"+(data.resultMap[i].mustSignTimes-data.resultMap[i].realSignTimes<0?0:data.resultMap[i].mustSignTimes-data.resultMap[i].realSignTimes)+"</td>"
									+"</tr>"
									
									realSignTimes+=data.resultMap[i].realSignTimes+","
									mustSignTimes+=data.resultMap[i].mustSignTimes+","
									actualValue+=data.resultMap[i].actualValue+","
									orderId+=data.resultMap[i].orderId+",";
									owedSginTime+=(data.resultMap[i].mustSignTimes-data.resultMap[i].realSignTimes<0?0:data.resultMap[i].mustSignTimes-data.resultMap[i].realSignTimes)+","
								}
							}
							$("#realSignTimes2").val(realSignTimes);
							$("#mustSignTimes2").val(mustSignTimes);
							$("#orderId2").val(orderId);
							$("#owedSginTime2").val(owedSginTime);
							$("#itemTr").html(td);
						}else{
							//alertx("没有数据！");
						}
					}
			});
		}
		function nextForm3(){
			$.ajax({
				url:"${ctx}/attend/bizPmAttendMonth/getDetailForm2List",
				type : "get",
				dataType : "json",
				data:{
					itemManagerId: $("#itemManagerId").val(),
					attendStartDate: $("#attendStartDate").val(),
					attendEndDate: $("#attendEndDate").val(),
					attendMonth: $("#attendMonth").val(),
					storeId: $("#storeId").val(),
					id:$("#id").val()
					},
					success : function(data){
						var td="";
						var kql="";
						var countDay = 0;
						var sjCountDay = 0;
						var attendRate=0.0;
						var allOwedSignTimes=0;
						var actualValueCount=0;
						if(data.resultMap != ""){
							for(var i=0;i<data.resultMap.length;i++){
								countDay+=parseInt(data.resultMap[i].mustSignTimes);
								sjCountDay+=parseInt(data.resultMap[i].realSignTimes);
                                actualValueCount+=parseInt(data.resultMap[i].actualValue);
                                attendRate=(data.resultMap[i].attendRate)
                                allOwedSignTimes=data.resultMap[i].allOwedSignTimes
							}
							if(sjCountDay==0||countDay==0){
								kql="0";
							} else if(sjCountDay>=countDay){
								kql="100";
							}else{
								kql=((attendRate)).toFixed(2);
							}
							td+="本月应签到总次数："+countDay+"次</br>"
							+"本月实际签到次数："+sjCountDay+"次</br>"
							+"实际取值总和："+actualValueCount+"次</br>"
							+"欠缺总次数："+(allOwedSignTimes)+"次</br>"
							+"考勤率：<input type='text' disabled='true' value="+attendRate+'%'+" />"
                            +"<span style='color:red'>注：考勤率=实际取值总和/应签到总次数*100%</span>" ;
							
							$("#mustSignTimes").val(countDay);
							$("#realSignTimes").val(sjCountDay);
							$("#owedSignTimes").val(countDay-sjCountDay<0?0:countDay-sjCountDay);
							$("#attendRate").val(kql);
							$("#jlDiv").html(td);
						}else{
							alertx("没有数据！");
						}
						
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/attend/bizPmAttendMonth/bizPmAttendFindList">项目经理考勤单查询</a></li>
		<li class="active"><a href="${ctx}/attend/bizPmAttendMonth/form?id=${bizPmAttendMonth.id}">考勤单明细</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPmAttendMonth" action="${ctx}/attend/bizPmAttendMonth/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
			<div align="center"><h3>基本信息设置</h3></div>
			<div class="control-group">
				<label class="control-label">考勤月份：</label>
				<div class="controls">
					<form:input path="" disabled="true" value="${bizPmAttendMonth.attendMonth}" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">项目经理：</label>
				<div class="controls">
					<form:input path="itemManager" disabled="true" value="${bizPmAttendMonth.itemManager}" htmlEscape="false" class="input-xlarge "/>
				</div>
				<form:input type="hidden" path="itemManagerId" id="itemManagerId" value="${bizPmAttendMonth.itemManagerId}"/>
				<form:input type="hidden" path="storeId" id="storeId" value="${bizPmAttendMonth.storeId}"/>
				
				<form:input type="hidden" id="mustSignTimes" path="mustSignTimes" value=""/>
				<form:input type="hidden" id="realSignTimes" path="realSignTimes" value=""/>
				<form:input type="hidden" id="owedSignTimes" path="owedSignTimes" value=""/>
				<form:input type="hidden" id="attendRate" path="attendRate" value=""/>
				<form:input type="hidden" id="status" path="status" value="20"/>
				<form:input type="hidden" id="statusDescribe" path="statusDescribe" value="已生成考勤单"/>
				<form:input type="hidden" id="attendMonth" path="attendMonth" value="${bizPmAttendMonth.attendMonth}"/>
 			</div>
		<div class="control-group">
			<label class="control-label">项目经理星级：</label>
			<div class="controls">
				<form:input path="" disabled="true" value="${fns:getDictLabel(bizPmAttendMonth.itemManagerStar, 'manager_star', '')}" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				<form:input path="itemManagerStar" type="hidden" value="${bizPmAttendMonth.itemManagerStar }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">星级对照底薪：</label>
			<div class="controls">
				<form:input path="starSalaryAllAttend" disabled="true" value="${bizPmAttendMonth.starSalaryAllAttend}" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">项目经理考勤周期：</label>
				<div class="controls">
					<input name="attendStartDate" id="attendStartDate" type="text" maxlength="20" class="input-medium Wdate " disabled="disabled"
						value="<fmt:formatDate value="${bizPmAttendMonth.attendStartDate}" pattern="yyyy-MM-dd"/>"
						/>-
					<input name="attendEndDate" id="attendEndDate" type="text" maxlength="20" class="input-medium Wdate " disabled="disabled"
					value="<fmt:formatDate value="${bizPmAttendMonth.attendEndDate}" pattern="yyyy-MM-dd"/>"
					/>
				</div>
			</div>
			<div align="center"><h3>签到详情</h3></div>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>客户姓名</th>
						<th>客户地址</th>
						<th>订单状态</th>
						<th>应签到次数</th>
						<th>实际签到次数</th>
						<th>实际取值</th>
						<th>欠缺签到次数</th>
					</tr>
				</thead>
				<tbody id="itemTr">
				
				</tbody>
			</table>
			<div align="center"><h3>结论</h3></div>
			<div class="control-group">
				<div class="controls" id="jlDiv">
					
				</div>
			</div>
			<div>
				<a class="btn"  style="text-align: center;" href="javascript:" onclick="history.go(-1);">返回</a>
			</div>
	</form:form>
</body>
</html>