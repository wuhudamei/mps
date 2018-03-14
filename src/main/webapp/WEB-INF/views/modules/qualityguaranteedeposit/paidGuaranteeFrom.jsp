<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>上缴质保金添加</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	getDepartments();
	//$("#name").focus();
	$("#inputForm").validate({
		submitHandler : function(form) {
			var guaranteeMoney = $("#guaranteeMoney").val();
			var guaranteeMoneyBalance = $("#guaranteeMoneyBalance").val();
			loading('正在提交，请稍等...');
			$("#btnSubmit").attr(
					"disabled", true);
			form.submit();
			/* if(parseFloat(guaranteeMoney)+parseFloat(guaranteeMoneyBalance)>2000){
				alertx("质保金总额不能超过2000！");
			}else{
				loading('正在提交，请稍等...');
				$("#btnSubmit").attr(
						"disabled", true);
				form.submit();
			}  */
			 
			
		},
		errorContainer : "#messageBox",
		errorPlacement : function(error,
				element) {
			$("#messageBox").text(
					"输入有误，请先更正。");
			if (element.is(":checkbox")
					|| element.is(":radio")
					|| element
							.parent()
							.is(
									".input-append")) {
				error.appendTo(element
						.parent().parent());
			} else {
				error.insertAfter(element);
			}
		}
	});
});

function getDepartments(){
	
	$("#engineDepartId").html('');
	$.ajax({
		url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
		type:'post',
		dataType : 'json',
		data:{
			'storeId':$('#storeId').val(),
			'projectMode':$('#projectMode').val(),
			'employeeId':$('#employeeId').val()
		},
		success:function(data){
			if(data == null){
				$("#engineDepartId").append('');
			} else {
				var html = "<option value=''></option>";
				for(var i=0;i<data.length;i++){
					var sec = "";
					if('${bizGuaranteeMoneyPaidUsed.engineDepartId}' == data[i].value){
	            		sec = "selected='selected'";
	            		$("#s2id_engineDepartId .select2-chosen").html(data[i].label);
	            	}
					html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
				}
				html+='';
        		$("#engineDepartId").append(html);
			}
		}
	});
}
	
	function findOrderInfo(){
		var html3 = '<option value=""></option>';
		var storeId = $("#storeId").val();
		var projectModeValue = $("#projectMode").val();
		var engineDepartId = $("#engineDepartId").val();
		//根据 门店、工程模式、区域查询订单
		$.ajax({
			url:"${ctx}/order/order/findOrderBystoreIdAndProjectModeAndengineDepartId?storeId="
				+ storeId + "&projectModeValue=" + projectModeValue+"&engineDepartId="+engineDepartId,
			type:"post",
			success : function(data) {
				if (null != data && data.length > 0) {
					for (var v = 0; v < data.length; v++) {
						var label = data[v].communityName+"-"+data[v].buildRoom+"-"+data[v].customerName
						html3 += '<option value="'+data[v].orderId+'">'
								+ label
								+ '</option>'
					}

					$("#orderSelect").html(html3);
				} else {
					$("#orderSelect").html(html3);
				}
			}
		});
	}
	
	function findOrderInfoById(){
		var orderId = $("#orderSelect").val();
		$.ajax({
			url:"${ctx}/order/order/findOrderById?orderId="+orderId,
		    type:"post",
		    success:function(data){
		    	if(data!=null){
		    		$("#communityName").val(data.communityName);
		    		$("#buildNumber").val(data.buildNumber);
		    		$("#buildUnit").val(data.buildUnit);
		    		$("#buildRoom").val(data.buildRoom);
		    	}
		    }
		});
		findUseGuaranteeEmp();
	}
	
	//获取质保金的使用人（项目经理或者工人）
	function findUseGuaranteeEmp(){
		var html3 = '<option value=""></option>';
		var orderId = $("#orderSelect").val();
		var usedEmployeeType = $("#usedEmployeeType").val();
	    if(usedEmployeeType == 1){//项目经理  找到订单的项目经理
	    	$.ajax({
				url:"${ctx}/guarantee/guaranteeManager/findItemManagerInfoByOrderId?orderId="+orderId,
			    type:"post",
			    success:function(data){
			    	if (null != data && data.length > 0) {
						for (var v = 0; v < data.length; v++) {
							html3 += '<option value="'+data[v].id+'">'
							+ data[v].realname+"-"+data[v].phone
							+ '</option>'
							
						}
						$("#usedEmployee").html(html3);
					} else {
						$("#usedEmployee").html(html3);
					}
			    }
			});
		}else if(usedEmployeeType == 2){//工人  找到订单下的所有的任务包的工人组，显示工人组长的姓名
			$.ajax({
				url:"${ctx}/guarantee/guaranteeManager/findWorkGroupInfoByOrderId?orderId="+orderId,
			    type:"post",
			    success:function(data){
			    	if (null != data && data.length > 0) {
						for (var v = 0; v < data.length; v++) {
							html3 += '<option value="'+data[v].id+'">'
							+ data[v].realname+"-"+data[v].phone
							+ '</option>'
							
						}
						$("#usedEmployee").html(html3);
					} else {
						$("#usedEmployee").html(html3);
					}
			    }
			});
		}
	}
	
	function findGuaranteeMoneyBalanceByEmployeeId(){
		var employeeId = $("#usedEmployee").val();
		$.ajax({
			url:"${ctx}/guarantee/guaranteeManager/findGuaranteeMoneyBalanceByEmployeeId?employeeId="+employeeId,
		    type:"post",
		    success:function(data){
		    	 if (null != data && data != '') {
		    		 if(data.guaranteeMoneyBalance != null && data.guaranteeMoneyBalance != ''){
		    			 $("#guaranteeMoneyBalance").val(data.guaranteeMoneyBalance);
		    		 }else{
		    			 $("#guaranteeMoneyBalance").val(0);
		    		 }
					
				} else {
					$("#guaranteeMoneyBalance").val(0);
				}
		    }
		});
	}
	
</script>




</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/guarantee/guaranteeManager/queryPaidGuarantee">质保金上缴查询</a></li>
		<li class="active"><a
			href="${ctx}/guarantee/guaranteeManager/openPaidGuaranteeFrom">质保金上缴添加</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizGuaranteeMoneyPaidUsed"
		action="${ctx}/guarantee/guaranteeManager/savePaidGuarantee" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<input type="hidden" name="guaranteeMoneyType" value="1"/>
		<sys:message content="${message}" />
		<table>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">门店：</label>
						<div class="controls">
							<c:if test="${empty storeDropEnable}">
								<form:select path="storeId" class="input-medium" disabled="true"
									onchange="getDepartments()">
									<form:option value="" label="" />
									<form:options items="${fns:getStoreList()}" itemLabel="label"
										itemValue="value" htmlEscape="false" />
								</form:select>
							</c:if>
							<c:if test="${!empty storeDropEnable}">
								<form:select path="storeId" class="input-medium needClear"
									onchange="getDepartments()">
									<form:option value="" label="" />
									<form:options items="${fns:getStoreList()}" itemLabel="label"
										itemValue="value" htmlEscape="false" />
								</form:select>
							</c:if>
							<span class="help-inline"><font color="red">*</font> </span>

						</div>
					</div>
				</td>
				<td><div class="control-group">
						<label class="control-label">工程模式：</label>
						<div class="controls">
							<c:if test="${empty gongcheng}">
								<form:select path="projectMode" class="input-medium" disabled="true"
									onchange="getDepartments()">
									<form:option value="" label="" />
									<form:options items="${fns:getDictList('project_mode')}"
										itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select>
							</c:if>
							<c:if test="${!empty gongcheng}">
								<form:select path="projectMode" class="input-medium needClear"
									onchange="getDepartments()">
									<form:option value="" label="" />
									<form:options items="${fns:getDictList('project_mode')}"
										itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select>
							</c:if>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>

			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">区域：</label>
						<div class="controls">
							<form:select path="engineDepartId" class="input-medium needClear"
								id="engineDepartId" onchange="findOrderInfo()">
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>

				<td><div class="control-group">
						<label class="control-label">客户姓名：</label>
						<div class="controls">
							<form:select path="orderId" id="orderSelect" class="input-medium needClear" onchange="findOrderInfoById()">
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			<tr>
				<td><div class="control-group">
						<label class="control-label">小区名称：</label>
						<div class="controls">
						<form:input path="communityName" id="communityName" htmlEscape="false"
								class="input-medium required" readonly="true"/>
						</div>
					</div></td>

				<td><div class="control-group">
						<label class="control-label">楼号：</label>
						<div class="controls">
							<form:input path="buildNumber" id="buildNumber" htmlEscape="false"
								class="input-medium required" readonly="true"/>
						</div>
					</div></td>
			</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label">单元门：</label>
						<div class="controls">
							<form:input path="buildUnit" id="buildUnit" readonly="true" htmlEscape="false" 
								class="input-medium required"/>
						</div>
					</div></td>

				<td><div class="control-group">
						<label class="control-label">门牌号：</label>
						<div class="controls">
							<form:input path="buildRoom" id="buildRoom" readonly="true" htmlEscape="false" 
								class="input-medium required"/>
						</div>
					</div></td>
			</tr>
			<tr>

				<td>
					<div class="control-group">
						<label class="control-label">上缴人员类型：</label>
						<div class="controls">
						    <form:select path="usedEmployeeType" id="usedEmployeeType" class="input-medium"
									onchange="findUseGuaranteeEmp()">
									<form:option value="" label="" />
									<form:options items="${fns:getDictList('use_guarantee_emp_type')}" itemLabel="label"
										itemValue="value" htmlEscape="false" />
							 </form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>

				</td>

				<td>
					<div class="control-group">
						<label class="control-label">上缴质保金人员：</label>
						<div class="controls">
							<form:select path="usedEmployeeId" id="usedEmployee" class="input-medium" onchange="findGuaranteeMoneyBalanceByEmployeeId()">
								<form:option value="" label="" />
							 </form:select>
							 <span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>

				</td>
			</tr>

			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">质保金用途：</label>
						<div class="controls">
						     <form:select path="guaranteeMoneyFor" class="input-medium">
									<form:option value="" label="" />
									<form:options items="${fns:getDictList('use_guarantee_type')}" itemLabel="label"
										itemValue="value" htmlEscape="false" />
							 </form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				
				<td>
					<div class="control-group">
						<label class="control-label">质保金余额：</label>
						<div class="controls">
							<form:input path="guaranteeMoneyBalance" id="guaranteeMoneyBalance" readonly="true" htmlEscape="false"
								maxlength="32" class="input-medium required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">上缴质保金金额：</label>
						<div class="controls">
							<form:input path="guaranteeMoneyAmount" id="guaranteeMoney" style="width:160px" htmlEscape="false" min="0"  class="input-xlarge number " required="required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				
				<td>
					<div class="control-group">
						<label class="control-label">上缴质保金日期：</label>
						<div class="controls">
							<input name="guaranteeMoneyDateTime" type="text" readonly="readonly"
								 maxlength="20" id="guaranteeMoneyDateTime"
								class="input-medium Wdate required" 
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
			<td>
					<div class="control-group">
						<label class="control-label">原因说明：</label>
						<div class="controls">
							<form:textarea path="guaranteeMoneyReason" htmlEscape="false" rows="4"
								maxlength="100" class="input-xxlarge " id="reportRemarks"
								name="reportRemarks" />
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div class="form-actions">

				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" onmousemove="formSubmit()" />&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>