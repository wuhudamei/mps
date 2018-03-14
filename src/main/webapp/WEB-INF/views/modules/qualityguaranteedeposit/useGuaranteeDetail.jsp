<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>使用质保金详情</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
function findGuaranteeMoneyBalanceByEmployeeId(){
	var employeeId = $("#usedEmployee").val();
	$.ajax({
		url:"${ctx}/guarantee/guaranteeManager/findGuaranteeMoneyBalanceByEmployeeId?employeeId="+employeeId,
	    type:"post",
	    success:function(data){
	    	 if (null != data && data != '') {
				$("#guaranteeMoneyBalance").val(data.guaranteeMoneyBalance);
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
		<li><a href="${ctx}/guarantee/guaranteeManager/queryUseGuarantee">质保金使用查询</a></li>
		<li class="active"><a
			href="${ctx}/guarantee/guaranteeManager/getUseGuaranteeDetail">质保金使用详情</a></li>
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
								<form:select path="storeId" class="input-medium needClear">
									<form:option value="${bizGuaranteeMoneyPaidUsed.storeId}" label="${fns:getStoreLabel(bizGuaranteeMoneyPaidUsed.storeId, '')}" />
								</form:select>
						</div>
					</div>
				</td>
				<td><div class="control-group">
						<label class="control-label">工程模式：</label>
						<div class="controls">
								<form:select path="projectMode" class="input-medium">
									<form:option value="${bizGuaranteeMoneyPaidUsed.projectMode}" label="${fns:getDictLabel(bizGuaranteeMoneyPaidUsed.projectMode, 'project_mode', '')}" />
								</form:select>
						</div>
					</div></td>
			</tr>

			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">区域：</label>
						<div class="controls">
							<form:select path="engineDepartId" class="input-medium needClear"
								id="engineDepartSelect">
								<form:option value="${bizGuaranteeMoneyPaidUsed.engineDepartId}" label="${fns:getElacLabel(bizGuaranteeMoneyPaidUsed.engineDepartId, '')}" />
							</form:select>
						</div>
					</div>
				</td>

				<td><div class="control-group">
						<label class="control-label">客户姓名：</label>
						<div class="controls">
							<form:select path="orderId" id="orderSelect" class="input-medium needClear">
							  <form:option value="${bizGuaranteeMoneyPaidUsed.orderId}" label="${bizGuaranteeMoneyPaidUsed.customerName}-${bizGuaranteeMoneyPaidUsed.customerPhone}" />
							</form:select>
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
						<label class="control-label">使用人员类型：</label>
						<div class="controls">
						    <form:select path="usedEmployeeType" id="usedEmployeeType" class="input-medium">
									<form:option value="${bizGuaranteeMoneyPaidUsed.usedEmployeeType}" label="${fns:getDictLabel(bizGuaranteeMoneyPaidUsed.usedEmployeeType,'use_guarantee_emp_type', '')}" />
							 </form:select>
						</div>
					</div>

				</td>

				<td>
					<div class="control-group">
						<label class="control-label">使用质保金人员：</label>
						<div class="controls">
							<form:select path="usedEmployeeId" id="usedEmployee" class="input-medium">
								<form:option value="${bizGuaranteeMoneyPaidUsed.usedEmployeeId}" label="${bizGuaranteeMoneyPaidUsed.usedEmpName}-${bizGuaranteeMoneyPaidUsed.usedEmpPhone}" />
							 </form:select>
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
									<form:option value="${bizGuaranteeMoneyPaidUsed.guaranteeMoneyFor}" label="${fns:getDictLabel(bizGuaranteeMoneyPaidUsed.guaranteeMoneyFor,'use_guarantee_type', '')}" />
							 </form:select>
						</div>
					</div>
				</td>
				
				<td>
					<div class="control-group">
						<label class="control-label">质保金余额：</label>
						<div class="controls">
							<form:input path="guaranteeMoneyBalance" id="guaranteeMoneyBalance" readonly="true" htmlEscape="false"
								maxlength="32" class="input-medium required" />
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">使用质保金金额：</label>
						<div class="controls">
							<form:input path="guaranteeMoneyAmount" id="guaranteeMoney" readonly="true" style="width:160px" htmlEscape="false" min="0"  class="input-xlarge number " required="required"/>
						</div>
					</div>
				</td>
				
				<td>
					<div class="control-group">
						<label class="control-label">使用质保金日期：</label>
						<div class="controls">
							<input name="guaranteeMoneyDateTime" type="text" readonly="readonly"
								 maxlength="20" id="guaranteeMoneyDateTime"
								class="input-medium Wdate required" value="<fmt:formatDate value="${bizGuaranteeMoneyPaidUsed.guaranteeMoneyDateTime}" pattern="yyyy-MM-dd"/>"/>
						</div>
					</div>
					<script>
					findGuaranteeMoneyBalanceByEmployeeId();
					</script>
				</td>
			</tr>
			<tr>
			<td>
					<div class="control-group">
						<label class="control-label">原因说明：</label>
						<div class="controls">
							<form:textarea path="guaranteeMoneyReason" htmlEscape="false" rows="4"
								maxlength="30" class="input-xxlarge " id="reportRemarks" readonly="true"
								name="reportRemarks"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>