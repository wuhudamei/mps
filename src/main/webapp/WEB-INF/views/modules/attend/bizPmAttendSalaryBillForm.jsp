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
			var starSalaryReal = $("#starSalaryReal").val(((naturalDay/day)*starSalaryAllAttend).toFixed(2));
			
			
			$("#sjSalary").val(starSalaryReal.val());
			$("#pmStarSalaryAttendFullDefault").val(((starSalaryAllAttend/day)*day).toFixed(2));
			$("#pmStarSalaryAttendFull").val(((starSalaryAllAttend/day)*day).toFixed(2));
			var sjSalary = $("#sjSalary").val();
			var kql = $("#attendRate").val();
			//考勤实际薪资
			$("#pmStarSalaryAttend").val(((starSalaryAllAttend/day)*sjday*(kql/100)).toFixed(2));
			
			$("#finalSalary").val((sjSalary*(kql/100)).toFixed(2));
			$("#pmStarSalaryRealDefault").val((sjSalary*(kql/100)).toFixed(2));
			$("#pmStarSalaryReal").val((sjSalary*(kql/100)).toFixed(2));
			$("#sjSalaryHid").val((sjSalary*(kql/100)).toFixed(2))
			$("#inputForm").validate({
				submitHandler: function(form){
					aaa();
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
		
		function starSalaryVal(){
			$("#sjSalary").val("${bizMonthSalary.starSalaryAllAttend }");
			$("#sjSalaryHid").val("${bizMonthSalary.starSalaryAllAttend }");
			var sjSalary = $("#sjSalary").val();
			var kql = $("#attendRate").val();
			$("#finalSalary").val((sjSalary*(kql/100)).toFixed(2));
			$("#pmStarSalaryRealDefault").val((sjSalary*(kql/100)).toFixed(2));
			$("#pmStarSalaryReal").val((sjSalary*(kql/100)).toFixed(2));
		}
		
		//获取当前月份天数
		function mGetDate(year, month){
		    var d = new Date(year, month, 0);
		    return d.getDate();
		}
		
		
		function aaa(){
			var minSalary= $("#starSalaryMin").val();
			var finalSalary = $("#finalSalary").val();
			var pmStarSalaryAttendFull = $("#pmStarSalaryAttendFull").val();
			var pmStarSalaryRealDefault = $("#pmStarSalaryRealDefault").val();
			$("#pmStarSalaryPunish").val(pmStarSalaryAttendFull-pmStarSalaryRealDefault);
			
			$("#alertRemarks").text("星级对照最低额度："+minSalary+"元");
			$("#alertRemarks2").text("本月最终得到薪酬："+finalSalary+"元");
			$("#alert").show();
			
		}
		
		function sure(){
			loading('正在提交，请稍等...');
			$("#inputForm")[0].submit();
			$("#alert").hide();
		}
		function cancel(){
			var minSalary= $("#starSalaryMin").val();
			$("#finalSalary").val(minSalary);
			$("#pmStarSalaryRealDefault").val(minSalary);
			$("#pmStarSalaryReal").val(minSalary);
			loading('正在提交，请稍等...');
			$("#inputForm")[0].submit();
			$("#alert").hide();
		}
		function sjSalaryZ(){
			var sjSalary = $("#sjSalary").val();
			var kql = $("#attendRate").val();
			$("#finalSalary").val((sjSalary*(kql/100)).toFixed(2));
			$("#pmStarSalaryRealDefault").val((sjSalary*(kql/100)).toFixed(2));
			$("#pmStarSalaryReal").val((sjSalary*(kql/100)).toFixed(2));
		}
	</script>
	<style>
		a{color:#2fa4e7;}
		.undis{display:none;}
		body {
		    background-color: #fff;
		    font-size: 16px;
		}
		
		body {
		    width: 100%;
		    height: 100%;
		    position: relative
		}
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 380px;border-radius: 4px;background: #fff;font-size: 16px;
		    position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		.maskBtn{display: block;width: 60%;text-align: center;line-height: 38px;}
		.maskBtn{background: #0780ec;border-radius: 4px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16px;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}		
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/attend/bizPmAttendMonth/bizPmAttendList">考勤月度工资单列表</a></li>
		<li class="active"><a href="${ctx}/attend/bizPmAttendSalaryBill/form?id=${bizPmAttendSalaryBill.id}">考勤月度工资单<shiro:hasPermission name="attend:bizPmAttendSalaryBill:edit">${not empty bizPmAttendSalaryBill.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="attend:bizPmAttendSalaryBill:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPmAttendSalaryBill" action="${ctx}/attend/bizPmAttendSalaryBill/save" method="post" class="form-horizontal">
		<form:input path="pmAttendMonthId" type="hidden" value="${bizMonthSalary.pmAttendMonthId }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
		<form:input type="hidden" path="itemManagerId" id="itemManagerId" value="${bizMonthSalary.itemManagerId}"/>
		<form:input type="hidden" path="storeId" id="storeId" value="${bizMonthSalary.storeId}"/>
		<form:input type="hidden" path="enginDepartId" id="enginDepartId" value="${bizMonthSalary.enginDepartId}"/>
		<form:input type="hidden" path="itemManager"  value="${bizMonthSalary.itemManager}"/>
		<sys:message content="${message}"/>	
		<div align="center">
			<h1>${bizMonthSalary.itemManager }${bizMonthSalary.attendMonth }份工资单</h1>
		</div>	
		<div>
			<h3>基本信息设置</h3>
			<div style="margin-left:40px;">
				<label>考勤月份：</label>
				<form:input path="" disabled="true" value="${bizMonthSalary.attendMonth }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				<form:input path="attendMonth" type="hidden" value="${bizMonthSalary.attendMonth }" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				
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

				<label style="margin-left:40px;">实际取值总和：${bizMonthSalary.actualValue}</label>
				
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
				<input style="margin-left:20px;" type="button" value="按【星级对照底薪】算考勤底薪" onclick="starSalaryVal()">
				
				<br/><br/>


				<label>本月考勤底薪：</label>
				<form:input path="" id="starSalaryReal" disabled="true" value="" htmlEscape="false" style="width:90px;" class="input-xlarge "/>
				<span style="color:red">注：本月考勤底薪=星级对照底薪/考勤月全月天数*本月实际考勤天数（自然日）</span>
				<br/><br/>
				
				<label>本月实际考勤底薪：</label>
				<form:input path="pmStarSalaryAttendDefault" type="number" id="sjSalary" value="" htmlEscape="false" style="width:90px;" onkeyup="sjSalaryZ()" class="input-xlarge "/>
				
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
				
				<form:input path="status" value="30" type="hidden"/>
				<span style="color:red">注：项目经理本月最终得到薪酬=本月实际考勤底薪*考勤率</span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="attend:bizPmAttendSalaryBill:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="生成工资单"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
	
	<div class="alertMask undis" id ="alert">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">
				<p id="alertRemarks"></p>
				<p id="alertRemarks2"></p>
				是否按照【本月最终得到薪酬】发布项目经理薪酬。
			</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtn font33 col_fdfcfa" href="javascript:void(0);" onclick="sure()">是</a>
				<a class="maskBtn font33 col_0780ec" href="javascript:void(0);" onclick="cancel()">否</a>
			</div>
		</div>
	</div>


	
</body>
</html>