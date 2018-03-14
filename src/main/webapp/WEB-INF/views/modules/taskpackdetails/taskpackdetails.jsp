<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>任务包详情</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/cusumerValidate/cusumerValidate.js"></script>
<script type="text/javascript">
	$(document).ready({
		
		
	})
   
</script>

</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/employee/bizEmployee/">订单详情页</a></li>
		<li class="active"><a
			href="${ctx}/employee/bizEmployee/form?id=${bizEmployee.id}">订单详情页<shiro:hasPermission
					name="employee:bizEmployee:edit">${not empty bizEmployee.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="employee:bizEmployee:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br/> --%>
	<form:form id="inputForm" modelAttribute="details"
		action="${ctx}/employee/bizEmployee/save" method="post"
		class="form-horizontal">
		<table>
			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
						基本信息
					</div>
				</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">门店：</label>
						<div class="controls">
							<form:select path="storeId" class="input-medium">
								<form:option value="" label="" />
								<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
						</div>
					</div></td>
					
					<td><div class="control-group">
						<label class="control-label">区域：</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="100"
								class="input-medium " />
						</div>
					</div></td>
			</tr>
			<tr>		
				<td>
					<div class="control-group">
						<label class="control-label">订单编号：</label>
						<div class="controls" id ="gongchengmoshi">
						<form:input path="orderNumber" htmlEscape="false" maxlength="100"
								class="input-medium "/>
						</div>
					</div>
					
				</td>	
				
				<td><div class="control-group">
						<label class="control-label">任务包编号：</label>
						<div class="controls">
						<form:input path="orderTaskPackageCode" htmlEscape="false" maxlength="100"
								class="input-medium "/>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">任务包名称：</label>
						<div class="controls">
						 <form:input path="packageName" htmlEscape="false" maxlength="100"
								class="input-medium "/>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">施工地点：</label>
						<div class="controls">
						<form:input path="customerMessage" htmlEscape="false" maxlength="100"
								class="input-medium "/>
						</div>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">客户姓名：</label>
						<div class="controls">
						<form:input path="customerName" htmlEscape="false" maxlength="100"
								class="input-medium " />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">客户手机：</label>
						<div class="controls">
						<form:input path="customerPhone" htmlEscape="false" maxlength="100"
								class="input-medium " />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">任务包状态：</label>
						<div class="controls">
						<form:input path="packageStatename" htmlEscape="false" maxlength="100"
								class="input-medium " />
						</div>
					</div></td>
			</tr>
			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
						人员信息：
					</div>
				</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">项目经理：</label>
						<div class="controls">
						<form:input path="itemManager" htmlEscape="false" maxlength="100"
								class="input-medium " />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">项目经理手机：</label>
						<div class="controls">
						<form:input path="phone" htmlEscape="false" maxlength="100"
								class="input-medium " />
						</div>
					</div></td>
			</tr>
			<c:forEach items="${workers }" var="worker">
				<tr>
				<td><div class="control-group">
						<label class="control-label">${worker.isLeader }：</label>
						<div class="controls">
						<input type="text" value="${worker.realName}" htmlEscape="false" maxlength="100" class="input-medium " />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">${worker.isLeader }手机：</label>
						<div class="controls">
						<input type="text" value="${worker.phone }" htmlEscape="false" maxlength="100" class="input-medium " />
						</div>
					</div></td>
			</tr>
			
			</c:forEach>
			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
						时间信息：
					</div>
				</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">创建时间：</label>
						<div class="controls">
							<input path="createDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${details.createDate }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="entrytime" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">派工时间：</label>
						<div class="controls">
								<input path="dispatchTime" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${details.dispatchTime }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="entrytime" />
								
						</div>
						</div>
				</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">计划开始时间：</label>
						<div class="controls">
							<input path="planStartdate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${details.planStartdate }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="birthday" />
							</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">计划完成时间：</label>
						<div class="controls">
						<input path="planEnddate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${details.planEnddate }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="birthday" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">实际开工时间：</label>
						<div class="controls">
						<input path="actualStartdate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${details.actualStartdate }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="birthday" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">实际竣工时间：</label>
						<div class="controls">
						<input path="actualEnddate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${details.actualEnddate }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="birthday" />
						</div>
					</div></td>
			</tr>
			
			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
						金额信息
					</div>
				</td>
			</tr>
			<tr >
				<td><div class="control-group">
						<label class="control-label">工序预算总价：</label>
						<div class="controls">
							<form:input path="laborAuxiliaryMaterialsBudgetAmount" htmlEscape="false" maxlength="100"
								class="input-medium " />
						</div>
					</div></td>
					<td><div class="control-group">
						<a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/showView?orderId=${details.orderId}&id=${details.id}"><label class="control-label">工序详情</label></a>
						<div class="controls">
						</div>
					</div></td>
			</tr>
			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
						薪酬信息
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">人工预算费金额：</label>
						<div class="controls">
							<form:input path="laborBudgetAmount" htmlEscape="false" maxlength="100" class="input-medium " />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">辅料费预算费金额：</label>
						<div class="controls">
							<form:input path="auxiliaryMaterialsBudgetAmount" htmlEscape="false" maxlength="100" class="input-medium " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group" style="font-size: 10px;text-align: center;" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工人信息 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
				<td>
					<div class="control-group" style="font-size: 10px;text-align: center;" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
				<td>
					<div class="control-group" style="font-size: 10px;text-align: center;" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分配金额 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<c:forEach items="${workers }" var="worker">
				<tr>
				<td>
					<div class="control-group" style="font-size: 10px;text-align: center;" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${worker.realName } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
				<td>
					<div class="control-group" style="font-size: 10px;text-align: center;" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${worker.isLeader } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
				<td>
					<div class="control-group" style="font-size: 10px;text-align: center;" >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${worker.paymentAmount } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			
			
			</c:forEach>
			<tr>
				<td>
					<div class="control-group" style="font-size: 10px;text-align: center;" >
						<a href="javascript:" onclick="history.go(-1);" class="btn" style="font-size: 20px">返回上一页</a> &nbsp; 
					</div>
				</td>
			
			</tr>
		</table>
		</form:form>
</body>
</html>