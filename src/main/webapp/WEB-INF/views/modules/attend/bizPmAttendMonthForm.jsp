<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤月度表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#form2").hide();
			$("#form3").hide();
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

		function nextForm2(){
			$("#itemTr").html("");
			$.ajax({
				url:"${ctx}/attend/bizPmAttendMonth/getForm2List",
				type : "get",
				dataType : "json",
				data:{
					itemManagerId: $("#itemManagerId").val(),
					attendStartDate: $("#attendStartDate").val(),
					attendEndDate: $("#attendEndDate").val(),
					storeId: $("#storeId").val(),
					attendMonth:$("#attendMonth").val()
				},
				success : function(data){
					var td=""
					var realSignTimes = "";
					var mustSignTimes = "";
					var orderId = "";
					var owedSginTime = "";
					var actualValue="";
					if(data.resultMap.length > 0){
						for(var i=0;i<data.resultMap.length;i++){
							td+="<tr><td>"+data.resultMap[i].customerName+"</td>"
									+"<td>"+data.resultMap[i].customerAddress+"</td>"
									+"<td>"+data.resultMap[i].orderStatusNumber+"</td>"
									+"<td>"+data.resultMap[i].mustSignTimes+"</td>"
									+"<td>"+data.resultMap[i].realSignTimes+"</td>"
									+"<td><input type='number' id = 'actualValueInput' name='actualValueInput' onKeypress='javascript:if(event.keyCode == 32)event.returnValue = false' onkeyup='this.value=this.value.replace(/\D/g,"+''+")' onafterpaste='this.value=this.value.replace(/\D/g,"+''+")'  value ="+data.resultMap[i].actualValue+"></td>"
									+"<td>"+(data.resultMap[i].mustSignTimes-data.resultMap[i].realSignTimes<0?0:data.resultMap[i].mustSignTimes-data.resultMap[i].realSignTimes)+"</td>"
									+"</tr>"

							realSignTimes+=data.resultMap[i].realSignTimes+","
							mustSignTimes+=data.resultMap[i].mustSignTimes+","
							actualValue+=data.resultMap[i].actualValue+","
							orderId+=data.resultMap[i].orderId+",";
							owedSginTime+=(data.resultMap[i].mustSignTimes-data.resultMap[i].actualValue<0?0:data.resultMap[i].mustSignTimes-data.resultMap[i].actualValue)+","
						}
						$("#realSignTimes2").val(realSignTimes);
						$("#mustSignTimes2").val(mustSignTimes);
						$("#orderId2").val(orderId);
						$("#owedSginTime2").val(owedSginTime);
						$("#actualValue2").val(actualValue);
						$("#itemTr").html(td);
						$("#form1").hide();
						$("#form2").show();
					}else{
						alertx("没有数据！");
						$("#form1").hide();
						$("#form2").show();
						$("#hideButtonAfter").hide();
					}
				}
			});
		}
		function nextForm3(){
			var actualvalCount = 0;
			var flag=false;
			$.each($("input[name='actualValueInput']"),function () {
				var num = $(this).val();
				if(num==""){
                    alertx('实际取值不能为空!');
                    flag=true;
                    return;
				}
                actualvalCount = parseInt(num) + actualvalCount;
			})
			if(flag){
			    return;
			}

			$.ajax({
				url:"${ctx}/attend/bizPmAttendMonth/getForm3Count",
				type : "get",
				dataType : "json",
				data:{
					itemManagerId: $("#itemManagerId").val(),
					attendStartDate: $("#attendStartDate").val(),
					attendEndDate: $("#attendEndDate").val(),
					storeId: $("#storeId").val(),
					attendMonth:$("#attendMonth").val()
				},
				success : function(data){
					var td="";
					var kql="";
					var countDay = 0;
					var sjCountDay = 0;
					var actualValue=0;
					for(var i=0;i<data.resultMap.length;i++){
						countDay+=parseInt(data.resultMap[i].mustSignTimes);
						sjCountDay+=parseInt(data.resultMap[i].realSignTimes);
						actualValue+=parseInt(data.resultMap[i].actualValue);
					}
					/*if(sjCountDay==0||countDay==0){
					 kql="0";
					 } else*/
					/*if(sjCountDay>=countDay){
					 kql="100";
					 }else{
					 kql=(($("#actualValueInput").val()/countDay)*100).toFixed(2);
					 }*/
					if(actualvalCount == 0){
						kql="0";
					}else if(actualvalCount>=countDay){
						kql="100";
					}else{
						kql=((actualvalCount/countDay)*100).toFixed(2);
					}


					td+="本月应签到总次数："+countDay+"次</br>"
							+"本月实际签到次数："+sjCountDay+"次</br>"
							+"实际取值总和："+(actualvalCount)+"次</br>"
							+"欠缺总次数："+(countDay-actualvalCount<0?0:countDay-actualvalCount)+"次</br>"
							+"考勤率：<input type='text' disabled='true' value="+kql+'%'+" />";

					$("#mustSignTimes").val(countDay);
					$("#realSignTimes").val(sjCountDay);
					$("#owedSignTimes").val(countDay-actualvalCount<0?0:countDay-actualvalCount);
					$("#actualValue").val(actualvalCount);
					$("#attendRate").val(kql);
					$("#jlDiv").html(td);
					$("#form2").hide();
					$("#form3").show();
				}
			});
		}
		function prevForm1(){
			$("#form1").show();
			$("#form2").hide();
		}
		function prevForm2(){
			$("#form2").show();
			$("#form3").hide();
		}
		function refreshForm2(){
			nextForm2();
		}
	</script>
</head>
<body>
<%
	long token=System.currentTimeMillis();    //产生时间戳的token
	session.setAttribute("token",token);     //把token放进session
%>
<ul class="nav nav-tabs">
	<li><a href="${ctx}/attend/bizPmAttendMonth/list">考勤月度表列表</a></li>
	<li class="active"><a href="${ctx}/attend/bizPmAttendMonth/form?id=${bizPmAttendMonth.id}">考勤月度表添加<shiro:lacksPermission name="attend:bizPmAttendMonth:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="bizPmAttendMonth" action="${ctx}/attend/bizPmAttendMonth/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<sys:message content="${message}"/>
	<div id="form1">
		<div align="center"><h3>基本信息设置</h3></div>
		<div class="control-group">
			<label class="control-label">考勤月份：</label>
			<div class="controls">
				<form:input path="" disabled="true" value="${bizPmAttendMonth.attendMonth}" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				<form:input path="attendMonth" type="hidden" value="${bizPmAttendMonth.attendMonth}" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目经理：</label>
			<div class="controls">
				<form:input path="itemManager" disabled="true" value="${bizPmAttendMonth.itemManager}" htmlEscape="false" class="input-xlarge "/>
			</div>
			<form:input type="hidden" path="itemManagerId" id="itemManagerId" value="${bizPmAttendMonth.itemManagerId}"/>
			<form:input type="hidden" path="storeId" id="storeId" value="${bizPmAttendMonth.storeId}"/>
			<form:input type="hidden" path="enginDepartId" id="enginDepartId" value="${bizPmAttendMonth.enginDepartId}"/>
			<form:input type="hidden" path="itemManager"  value="${bizPmAttendMonth.itemManager}"/>
			<form:input type="hidden" id="mustSignTimes" path="mustSignTimes" value=""/>
			<form:input type="hidden" id="realSignTimes" path="realSignTimes" value=""/>
			<form:input type="hidden" id="owedSignTimes" path="owedSignTimes" value=""/>
			<form:input type="hidden" id="attendRate" path="attendRate" value=""/>
			<form:input type="hidden" id="actualValue" path="actualValue" value=""/>
			<form:input type="hidden" id="status" path="status" value="${bizPmAttendMonth.status }"/>
			<form:input type="hidden" id="statusDescribe" path="statusDescribe" value="已生成考勤单"/>


			<form:input type="hidden" id="pmAttendMonthId" path="pmAttendMonthId"/>
			<form:input type="hidden" id="orderId2" path="orderId2"/>
			<form:input type="hidden" id="mustSignTimes2" path="mustSignTimes2"/>
			<form:input type="hidden" id="realSignTimes2" path="realSignTimes2"/>
			<form:input type="hidden" id="owedSginTime2" path="owedSginTime2"/>
			<form:input type="hidden" id="actualValue2" path="actualValue2"/>
		</div>
		<div class="control-group">
			<label class="control-label">项目经理考勤周期：</label>
			<div class="controls">
				<input name="attendStartDate" id="attendStartDate" type="text" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${bizPmAttendMonth.attendStartDate}" pattern="yyyy-MM-dd 00:00:00"/>"
					   onclick="WdatePicker({maxDate:'#F{$dp.$D(\'attendEndDate\')}',minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:false});"/>-
				<input name="attendEndDate" id="attendEndDate" type="text" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${bizPmAttendMonth.attendEndDate}" pattern="yyyy-MM-dd 23:59:59"/>"
					   onclick="WdatePicker({minDate:'#F{$dp.$D(\'attendStartDate\')}',maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:false});"/>

				<input name="startDate" id="startDate" type="hidden" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${dd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<input name="endDate" id="endDate" type="hidden" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${end}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

			</div>
		</div>
		<div>
			<input onclick="nextForm2()" type="button" value="下一步"/>
		</div>
	</div>
	<div id="form2">
		<div align="center"><h3>签到详情</h3></div>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<input style="float:right" id="refresh" onclick="refreshForm2()" class="btn btn-primary" type="button" value="刷新"/>
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
		<div>
			<input onclick="prevForm1()" type="button" value="上一步"/>
			<input onclick="nextForm3()" type="button"  id="hideButtonAfter" value="下一步"/>
		</div>
	</div>
	<div id="form3">
		<div align="center"><h3>结论</h3></div>
		<div class="control-group">
			<div class="controls" id="jlDiv">

			</div>
		</div>
		<div>
			<input onclick="prevForm2()" type="button" value="上一步"/>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="生成考勤单"/>
		</div>
	</div>
</form:form>
</body>
</html>