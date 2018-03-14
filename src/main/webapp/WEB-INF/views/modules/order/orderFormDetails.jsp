<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>订单管理</title>
<meta name="decorator" content="default" />

<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});
	
	

</script>
</head>
<body>
	<ul class="nav nav-tabs">
	<c:if test="${empty order.flag}">
		<li><a href="${ctx}/order/order/list">订单列表</a></li>
		<li class="active"><a
			href="${ctx}/order/order/details?id=${order.id}">订单详情
				<shiro:lacksPermission name="order:order:edit">查看</shiro:lacksPermission></a></li>
	</c:if>
	<c:if test="${order.flag == 2}">
		<li class="active"><a
			href="javascript:void(0);">订单详情
				<shiro:lacksPermission name="order:order:edit">查看</shiro:lacksPermission></a></li>
	</c:if>
	</ul>
	<br />
	<br />
	<form:form id="inputForm" modelAttribute="order"
		action="${ctx}/order/order/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">门店：</label>
						<div class="controls">
							<form:select path="storeId" class="input-medium" id="sel">
								<form:option value="" label="" />
								<form:options items="${fns:getStoreList()}" itemLabel="label"
									itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>

						</div>
					</div>
				</td>
				
				<td><div class="control-group">
						<label class="control-label">工程模式：</label>
						<div class="controls">
							<form:radiobuttons path="projectMode"
								items="${fns:getDictList('project_mode')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class=" required"  disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>

			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">订单编号：</label>
						<div class="controls">
							<form:input path="orderNumber" htmlEscape="false" maxlength="100"
								class="input-xlarge required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">合同编号：</label>
						<div class="controls">
							<form:input path="contractNumber" htmlEscape="false"
								maxlength="100" class="input-xlarge required" disabled="true"/>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">区域：</label>
						<div class="controls">
							<form:select path="engineDepartId" class="input-medium " disabled="true">
								<form:options items="${fns:getEngineListWithUserConditions()}" itemLabel="label"
									itemValue="value" htmlEscape="false" />
				 			</form:select>
				 			<span class="help-inline"><font color="red">*</font> </span>

						</div>
					</div></td>
					
				<td><div class="control-group">
						<label class="control-label">接单时间：</label>
						<div class="controls">
							<input name="acceptOrderDate" type="text" readonly="readonly" disabled="true"
								 maxlength="20"
								class="input-medium Wdate required" 
								value="<fmt:formatDate value="${order.acceptOrderDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				</tr>
			
			<tr>
				<td><div class="control-group">
						<label class="control-label">客户类型：</label>
						<div class="controls">
							<form:select path="customerType" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('cus_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">客户属性描述：</label>
						<div class="controls">
							<form:input path="customerDescription" htmlEscape="false"
								maxlength="64" class="input-xlarge " disabled="true"/>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">客户姓名：</label>
						<div class="controls">
							<form:input path="customerName" htmlEscape="false" maxlength="16"
								class="input-xlarge required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">客户电话：</label>
						<div class="controls">
							<form:input path="customerPhone" htmlEscape="false"
								maxlength="11" class="input-medium required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>
			
			<tr>

				<td>
					<div class="control-group">
						<label class="control-label">客服经理：</label>
						<div class="controls">
							<form:input path="cusManager" htmlEscape="false" maxlength="100"
								class="input-medium" />
						</div>
					</div>

				</td>
				<td>
					<div class="control-group">
						<label class="control-label">远程费金额：</label>
						<div class="controls">
							<form:input path="distanceFee" htmlEscape="false" maxlength="100"
								class="input-medium" />
						</div>
					</div>

				</td>
</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">小区名称：</label>
						<div class="controls">
							<form:input path="communityName" htmlEscape="false"
								maxlength="32" class="input-xlarge required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group"">
						<label class="control-label">楼：</label>

						<div class="controls">
							<form:input path="buildNumber" htmlEscape="false" maxlength="4"
								class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">单元：</label>
						<div class="controls">
							<form:input path="buildUnit" htmlEscape="false" maxlength="4"
								class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">室：</label>
						<div class="controls">
							<form:input path="buildRoom" htmlEscape="false" maxlength="4"
								class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">楼层：</label>
						<div class="controls">
							<form:input path="floor" htmlEscape="false" maxlength="4"
										class="input-mini required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				
				
				</td>
				
				
				<td>
					<div class="control-group">
						<label class="control-label">合同金额：</label>
						<div class="controls">
							<form:input path="contractAmount" htmlEscape="false" maxlength="100"
								class="input-medium number required" />
						<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">详细地址：</label>
						<div class="controls">
							<form:input path="detailAddress" htmlEscape="false"
								maxlength="32" class="input-xlarge required" disabled="true" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>



					<div class="control-group">
						<label class="control-label">省：</label>
						<div class="controls">
							<form:input path="province" htmlEscape="false" maxlength="4"
								class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label">市：</label>
						<div class="controls">
							<form:input path="city" htmlEscape="false" maxlength="4"
								class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">县：</label>
						<div class="controls">
							<form:input path="county" htmlEscape="false" maxlength="4"
								class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>

				</td>
			</tr>
				
				<td ><div class="control-group">
						<label class="control-label">地图坐标：</label>
						<div class="controls">
							<form:input path="mapCoordinate" htmlEscape="false"
								maxlength="255" class="input-xlarge " disabled="true"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td style="width: 260px;"><div class="control-group">
						<label class="control-label">套餐类型：</label>
						<div class="controls">
							<form:input path="saleType" htmlEscape="false" maxlength="32"
								class="input-xlarge " disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td style="width: 260px;"><div class="control-group">
						<label class="control-label">片区：</label>
						<div class="controls">
							<form:input path="area" htmlEscape="false" maxlength="32"
								class="input-xlarge " disabled="true"/>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">房屋类型：</label>
						<div class="controls">
							<form:select path="buildType" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('emp_house_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">户型：</label>
						<div class="controls">
							<form:select path="houseType" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('home_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
							<span class="help-inline"><font color="red"> </font> </span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">新/老房：</label>
						<div class="controls">
							<form:radiobuttons path="houseIsNew"
								items="${fns:getDictList('biz_is_new_house')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="required" disabled="true"/>
							<span class="help-inline"><font color="red"> </font>*</span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">有无电梯:</label>
						<div class="controls">
							<form:radiobuttons path="isElevator"
								items="${fns:getDictList('is_elevator')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="required" disabled="true"/>
							<span class="help-inline"><font color="red"> </font>*</span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">设计师姓名：</label>
						<div class="controls">
							<form:input path="designerName" htmlEscape="false" maxlength="12"
								class="input-xlarge " disabled="true"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">设计师电话：</label>
						<div class="controls">
							<form:input path="designerPhone" htmlEscape="false"
								maxlength="12" class="input-xlarge " disabled="true"/>
								<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">审计员姓名：</label>
						<div class="controls">
							<form:input path="auditorName" htmlEscape="false" maxlength="12" class="input-xlarge " disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">审计员电话：</label>
						<div class="controls">
							<form:input path="auditorPhone" htmlEscape="false" maxlength="12" class="input-xlarge " disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">跟单员姓名：</label>
						<div class="controls">
							<form:input path="orderReporterName" htmlEscape="false"
								maxlength="12" class="input-xlarge " disabled="true"/>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">跟单员电话：</label>
						<div class="controls">
							<form:input path="orderReporterPhone" htmlEscape="false"
								maxlength="12" class="input-xlarge " disabled="true"/>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">客服姓名：</label>
						<div class="controls">
							<form:input path="serviceName" htmlEscape="false" maxlength="12"
								class="input-xlarge " disabled="true"/>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">客服电话：</label>
						<div class="controls">
							<form:input path="servicePhone" htmlEscape="false" maxlength="12"
								class="input-xlarge " disabled="true"/>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">合同开工日期：</label>
						<div class="controls">
							<input name="contractStartDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate " disabled="disabled"
								value="<fmt:formatDate value="${order.contractStartDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">合同竣工日期：</label>
						<div class="controls">
							<input name="contractEndDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate " disabled="disabled"
								value="<fmt:formatDate value="${order.contractEndDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">建筑面积：</label>
						<div class="controls">
							<form:input path="coveredArea" htmlEscape="false" maxlength="12"
								class="input-xlarge " disabled="true"/>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">合同面积：</label>
						<div class="controls">
							<form:input path="contractArea" htmlEscape="false" maxlength="12"
								class="input-xlarge " disabled="true" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">合同工期：</label>
						<div class="controls">
							<form:input path="contractTime" htmlEscape="false" maxlength="12"
								class="input-xlarge" disabled="true" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">签约日期：</label>
						<div class="controls">
							<input name="signContractDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate " disabled="disabled"
								value="<fmt:formatDate value="${order.signContractDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					
					</td>
					
					
					
			</tr>
			<tr>
				<td>

					<div class="control-group">
						<label class="control-label">接单区域：</label>
						<div class="controls">
								<form:input path="acceptArea" htmlEscape="false" maxlength="4"
								class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>



				</td>
					
			
			</tr>
			
			
			
			<%-- <tr id="installTRID">
		
			<c:if test="${not empty installItemList && installItemList !=null}">
		<td>	<span>订单相关安装项</span></td>
			<c:forEach items="${installItemList }" var="item">
			<!-- 订单下的安装项选中 -->
			<c:if test="${item.isChoosed=='1' }">
			<td><label>${item.projectInstallItemName }</label><input type="checkbox" value="${item.projectInstallItemId }"  checked="checked" onclick="return false" />	</td>	
			</c:if>
			<!-- 不是订单下的安装项 -->
			<c:if test="${item.isChoosed!='1' }">
			<td><label>${item.projectInstallItemName }</label><input type="checkbox" value="${item.projectInstallItemId }"  onclick="return false"   /></td>
			</c:if>
			</c:forEach>
			</c:if>
			
			</tr>
			 --%>
			
			
			
			
			
			
			
			
			
		</table>
		
		
		
		
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>

</body>
</html>