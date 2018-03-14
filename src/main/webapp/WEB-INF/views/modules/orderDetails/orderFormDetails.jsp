<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>员工信息管理</title>
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
	<form:form id="inputForm" modelAttribute="orderDetails"
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
						<label class="control-label">工程模式：</label>
						<div class="controls">
							<form:radiobuttons path="projectMode" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class=" required"  disabled="true"/>
						</div>
					</div></td>
			</tr>
			<tr>		
				<td>
					<div class="control-group">
						<label class="control-label">订单编号：</label>
						<div class="controls" id ="gongchengmoshi">
						<form:input path="orderNumber" htmlEscape="false" maxlength="100"
								class="input-medium " id="realname" value="${orderNumber }" />
						</div>
					</div>
					
				</td>	
				
				<td><div class="control-group">
						<label class="control-label">合同编号：</label>
						<div class="controls">
						<form:input path="orderNumber" htmlEscape="false" maxlength="100"
								class="input-medium " id="realname" value="${orderNumber }" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">区域：</label>
						<div class="controls">
						 <form:input path="name" htmlEscape="false" maxlength="100"
								class="input-medium "/>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">套餐类型：</label>
						<div class="controls">
						<form:input path="saleType" htmlEscape="false" maxlength="100"
								class="input-medium " value="${saleType }" />
						</div>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">远程金额费：</label>
						<div class="controls">
						<form:input path="longwayAmount" htmlEscape="false" maxlength="100"
								class="input-medium "  value="${longwayAmount }" />
						</div>
					</div></td>
			</tr>
			
			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
						时间信息：
					</div>
				</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">接单时间：</label>
						<div class="controls">
							<input path="signContractDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${orderDetails.signContractDate }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="entrytime" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">合同工期：</label>
						<div class="controls">
							<form:input path="contractTime" htmlEscape="false" maxlength="100"
								class="input-medium " value="${contractTime }" />
								
						</div>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">合同开工时间：</label>
						<div class="controls">
							<input path="contractStartDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${orderDetails.contractStartDate }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="birthday" />
							</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">合同竣工时间：</label>
						<div class="controls">
						<input path="contractEndDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${orderDetails.contractEndDate }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="birthday" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">实际开工时间：</label>
						<div class="controls">
						<input path="atualStartDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${orderDetails.actualStartDate }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="birthday" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">实际竣工时间：</label>
						<div class="controls">
						<input path="actualEndDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${orderDetails.actualEndDate }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="birthday" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">签约时间：</label>
						<div class="controls">
						<input path="signContractDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${orderDetails.signContractDate }" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"
								id="birthday" />
						</div>
					</div></td>
				
			</tr>
				<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
						人员信息
					</div>
				</td>
			</tr>
			<tr >
				<td><div class="control-group">
						<label class="control-label">客户类型：</label>
						<div class="controls">
							<form:select path="customerType" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('cus_type')}" itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
						</div>
					</div></td>
					<td><div class="control-group">
						<label class="control-label">客户描述：</label>
						<div class="controls">
							<form:input path="customerDescription" htmlEscape="false" maxlength="100"
								class="input-medium " value="${customerDescription }" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">客户姓名：</label>
						<div class="controls">
							<form:input path="customerName" htmlEscape="false" maxlength="100"
								class="input-medium " value="${customerName }" />
					</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">客户电话：</label>
						<div class="controls">
						<form:input path="customerPhone" htmlEscape="false" maxlength="100"
								class="input-medium " value="${customerPhone }" />
					</div></div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">客服姓名：</label>
						<div class="controls">
							<form:input path="serviceName" htmlEscape="false" maxlength="100"
								class="input-medium " value="${serviceName }" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">客服电话：</label>
						<div class="controls">
							<form:input path="servicePhone" htmlEscape="false" maxlength="100"
								class="input-medium " value="${servicePhone }" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">设计师姓名：</label>
						<div class="controls">
							<form:input path="designerName" htmlEscape="false" maxlength="100"
								class="input-medium" value="${designerName }" />
						</div>
					</div></td>
					<td><div class="control-group">
						<label class="control-label">设计师电话：</label>
						<div class="controls">
							<form:input path="designerPhone" htmlEscape="false" maxlength="100"
								class="input-medium " value="${designerPhone }" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">审计员姓名：</label>
						<div class="controls">
							<form:input path="auditorName" htmlEscape="false" maxlength="100"
								class="input-medium"  />
						</div>
					</div></td>
					<td><div class="control-group">
						<label class="control-label">审计员电话：</label>
						<div class="controls">
							<form:input path="auditorPhone" htmlEscape="false" maxlength="100"
								class="input-medium " />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">跟单员姓名：</label>
						<div class="controls">
							<form:input path="orderReporterName" htmlEscape="false" maxlength="100"
								class="input-medium " value="${orderReporterName }" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">跟单员电话：</label>
						<div class="controls">
							<form:input path="orderReporterPhone" htmlEscape="false" maxlength="100"
								class="input-medium " value="${orderReporterPhone }" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">项目经理：</label>
						<div class="controls">
							<form:input path="realName" htmlEscape="false" maxlength="100"
								class="input-medium " value="${realName }" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">项目经理电话：</label>
						<div class="controls">
							<form:input path="phone" htmlEscape="false" maxlength="100"
								class="input-medium " value="${phone }" />
						</div>
					</div></td>
			
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">质检员：</label>
						<div class="controls">
							<form:input path="orderInspector" htmlEscape="false" maxlength="100"
								class="input-medium " />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">质检员电话：</label>
						<div class="controls">
							<input  type="text" htmlEscape="false" maxlength="100"
								class="input-medium" value="${inspectorPhone }"/>
						</div>
					</div></td>
			</tr>
			<c:forEach items="${employee }" var ="emp">
			<tr>
				<td><div class="control-group">
						<label class="control-label">${emp.name }：</label>
						<div class="controls">
							<input  type="text" value="${emp.realName }" htmlEscape="false" maxlength="100"
								class="input-medium "  />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">${emp.name }电话：</label>
						<div class="controls">
							<input type="text" value = "${emp.phone }" htmlEscape="false" maxlength="100"
								class="input-medium "/>
						</div>
					</div></td>
			
			</tr>
			</c:forEach>
			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
						地理信息
					</div>
				</td>
			</tr>
		<tr>
				<td><div class="control-group">
						<label class="control-label">小区名称：</label>
						<div class="controls">
							<form:input path="communityName" htmlEscape="false" maxlength="100"
								class="input-medium " value="${communityName }" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">详细地址：</label>
						<div class="controls">
							<form:input path="detailAddress" htmlEscape="false" maxlength="100"
								class="input-medium " value="${detailAddress }" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">楼：</label>
						<div class="controls">
							<form:input path="buildNumber" htmlEscape="false" maxlength="100"
								class="input-medium " value="${buildNumber }" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">省：</label>
						<div class="controls">
							<form:input path="province" htmlEscape="false" maxlength="100"
								class="input-medium " value="${province }" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">单元：</label>
						<div class="controls">
							<form:input path="buildUnit" htmlEscape="false" maxlength="100"
								class="input-medium " value="${buildUnit }" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">市：</label>
						<div class="controls">
							<form:input path="city" htmlEscape="false" maxlength="100"
								class="input-medium " value="${city }" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">室：</label>
						<div class="controls">
							<form:input path="buildRoom" htmlEscape="false" maxlength="100"
								class="input-medium " value="${buildRoom }" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">县：</label>
						<div class="controls">
							<form:input path="county" htmlEscape="false" maxlength="100"
								class="input-medium " value="${county }" />
						</div>
					</div></td>
			</tr>
		<tr>
				<td><div class="control-group">
						<label class="control-label">地图坐标：</label>
						<div class="controls">
							<form:input path="mapCoordinate" htmlEscape="false" maxlength="100"
								class="input-medium " value="${mapCoordinate }" />
						</div>
					</div></td>
				
			</tr>
				<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
						房屋信息
					</div>
				</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">片区：</label>
						<div class="controls">
							<form:input path="area" htmlEscape="false" maxlength="100"
								class="input-medium " value="${area }" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">房屋类型：</label>
						<div class="controls">
							<form:select path="buildType" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('emp_house_type')}" itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">户型：</label>
						<div class="controls">
							<form:select path="houseType" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('home_type')}" itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
						</div>
					</div>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">新/老房：</label>
						<div class="controls">
							<form:radiobuttons path="houseIsNew" items="${fns:getDictList('biz_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" disabled="true"/>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">有无电梯:</label>
						<div class="controls">
							<form:radiobuttons path="isElevator" items="${fns:getDictList('is_elevator')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" disabled="true"/>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">建筑面积：</label>
						<div class="controls">
							<form:input path="coveredArea" htmlEscape="false" maxlength="100"
								class="input-medium " value="${coveredArea }" />
						</div>
						</div>
					
					</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">合同面积：</label>
						<div class="controls">
							<form:input path="contractArea" htmlEscape="false" maxlength="100"
								class="input-medium " value="${contractArea }" />
						</div>
					</div></td>
				
			</tr>
			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
						订单相关安装项
					</div>
				</td>
			</tr>
			
				<c:forEach items="${listInstall }" var="install">
				<tr>
					<td>
						<div class="control-group" style="font-size: 15px" style="width:100px">
							<label class="control-label" style="font-size: 15px;">${install.installItemName }</label>
							<div class="controls"  style="width:600px">
								计划安装时间:&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${install.planIntoDate }" pattern="yyyy-MM-dd"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								实际安装时间:&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${install.realIntoDate }" pattern="yyyy-MM-dd"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								验收时间:&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${install.realAcceptDate }" pattern="yyyy-MM-dd"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</td>
					
			</tr>
				</c:forEach>
			
			<tr>
				<td>
					<div class="control-group" style="font-size: 10px;text-align: center;" >
						<a href="javascript:" onclick="history.go(-1);" class="btn" style="font-size: 15px">返回上一页</a> &nbsp; 
					</div>
				</td>
			
			</tr>
		</table>
		</form:form>
</body>
</html>