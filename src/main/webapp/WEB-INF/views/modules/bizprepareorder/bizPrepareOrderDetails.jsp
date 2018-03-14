<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>预备订单表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizprepareorder/bizPrepareOrder/list">预备订单表列表</a></li>
		<li class="active"><a href="${ctx}/bizprepareorder/bizPrepareOrder/details?id=${bizPrepareOrder.id}">预备订单详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPrepareOrder" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<table>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">门店：</label>
						<div class="controls">
							<form:select path="storeId" class="input-medium">
								<form:option value="" label="" />
								<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">工程模式：</label>
						<div class="controls">
							<form:radiobuttons path="projectMode" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class=" required"  disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">订单编号：</label>
						<div class="controls">
							<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-xlarge required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">合同编号：</label>
						<div class="controls">
							<form:input path="contractNumber" htmlEscape="false" maxlength="100" class="input-xlarge required" disabled="true"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">区域：</label>
						<div class="controls">
							<form:select path="enginDepartId" class="input-medium " disabled="true">
								<form:option value="" label="" />
								<form:options items="${fns:getEngineListWithUserConditions()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				 			</form:select>
				 			<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">接单时间：</label>
						<div class="controls">
							<input name="getOrderDatetime" type="text" readonly="readonly" maxlength="20" id="getOrderDatetime"
								class="input-medium Wdate required" disabled="disabled"
								value="<fmt:formatDate value="${bizPrepareOrder.getOrderDatetime}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td> 
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">客户类型：</label>
						<div class="controls">
							<form:select path="customerType" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('cus_type')}" itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">客户属性描述：</label>
						<div class="controls">
							<form:input path="customerDescription" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
						</div>
					</div>
				</td>
				<%-- <td>
					<div class="control-group">
						<label class="control-label">客户地址：</label>
						<div class="controls">
							<form:input path="customerAddress" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
						</div>
					</div>
				</td> --%>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">客户姓名：</label>
						<div class="controls">
							<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-xlarge required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">客户电话：</label>
						<div class="controls">
							<form:input path="customerPhone" htmlEscape="false" maxlength="11" class="input-medium required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="width: 260px;">
					<div class="control-group">
						<label class="control-label">套餐类型：</label>
						<div class="controls">
							<form:input path="saleType" htmlEscape="false" maxlength="32" class="input-xlarge " disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">远程费金额：</label>
						<div class="controls">
							<form:input path="longwayAmount" htmlEscape="false" maxlength="100" class="input-medium number"  disabled="true"/>
						</div>
					</div>
				</td> 
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">小区名称：</label>
						<div class="controls">
							<form:input path="communityName" htmlEscape="false" maxlength="32" class="input-xlarge required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
					<div class="control-group"">
						<label class="control-label">楼：</label>
						<div class="controls">
							<form:input path="buildNumber" htmlEscape="false" maxlength="4" class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">单元：</label>
						<div class="controls">
							<form:input path="buildUnit" htmlEscape="false" maxlength="4" class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">室：</label>
						<div class="controls">
							<form:input path="buildRoom" htmlEscape="false" maxlength="4" class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">楼层：</label>
						<div class="controls">
							<form:input path="floor" htmlEscape="false" maxlength="4" class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
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
							<form:input path="detailAddress" htmlEscape="false" maxlength="32" class="input-xlarge required" disabled="true" />
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">省：</label>
						<div class="controls">
							<form:input path="province" htmlEscape="false" maxlength="4" class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">市：</label>
						<div class="controls">
							<form:input path="city" htmlEscape="false" maxlength="4" class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">县：</label>
						<div class="controls">
							<form:input path="county" htmlEscape="false" maxlength="4" class="input-mini required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div class="control-group">
					<div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">房屋类型：</label>
						<div class="controls">
							<form:select path="buildType" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('emp_house_type')}" itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">户型：</label>
						<div class="controls">
							<form:select path="houseType" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('home_type')}" itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">新/老房：</label>
						<div class="controls">
							<form:radiobuttons path="houseIsNew" items="${fns:getDictList('biz_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">有无电梯:</label>
						<div class="controls">
							<form:radiobuttons path="isElevator" items="${fns:getDictList('is_elevator')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">设计师姓名：</label>
						<div class="controls">
							<form:input path="designerName" htmlEscape="false" maxlength="12" class="input-xlarge " disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">设计师电话：</label>
						<div class="controls">
							<form:input path="designerPhone" htmlEscape="false" maxlength="12" class="input-xlarge " disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
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
				<td>
					<div class="control-group">
						<label class="control-label">跟单员姓名：</label>
						<div class="controls">
							<form:input path="orderReporterName" htmlEscape="false" maxlength="12" class="input-xlarge " disabled="true"/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">跟单员电话：</label>
						<div class="controls">
							<form:input path="orderReporterPhone" htmlEscape="false" maxlength="12" class="input-xlarge " disabled="true"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">客服姓名：</label>
						<div class="controls">
							<form:input path="serviceName" htmlEscape="false" maxlength="12" class="input-xlarge " disabled="true"/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">客服电话：</label>
						<div class="controls">
							<form:input path="servicePhone" htmlEscape="false" maxlength="12" class="input-xlarge " disabled="true"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">合同开工日期：</label>
						<div class="controls">
							<input name="contractStartDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate " disabled="disabled"
								value="<fmt:formatDate value="${bizPrepareOrder.contractStartDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">合同竣工日期：</label>
						<div class="controls">
							<input name="contractEndDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate " disabled="disabled"
								value="<fmt:formatDate value="${bizPrepareOrder.contractEndDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">建筑面积：</label>
						<div class="controls">
							<form:input path="coveredArea" htmlEscape="false" maxlength="12" class="input-xlarge " disabled="true"/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">合同面积：</label>
						<div class="controls">
							<form:input path="contractArea" htmlEscape="false" maxlength="12"
								class="input-xlarge " disabled="true" />
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">合同工期：</label>
						<div class="controls">
							<form:input path="contractTime" htmlEscape="false" maxlength="12" class="input-xlarge" disabled="true" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">签约日期：</label>
						<div class="controls">
							<input name="signContractDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate " disabled="disabled"
								value="<fmt:formatDate value="${bizPrepareOrder.signContractDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">接单区域：</label>
						<div class="controls">
							<select id="bizOrderAcceptArea" name="bizOrderAcceptArea" style="width: 170px"  disabled="true">
								<c:if test="${bizPrepareOrder.bizOrderAcceptArea!=null }">
									<option value="${bizPrepareOrder.bizOrderAcceptArea }" selected="selected">${bizPrepareOrder.bizOrderAcceptAreaName }</option>
								</c:if>
								<c:if test="${bizPrepareOrder.bizOrderAcceptArea==null }">
									<option value="" selected="selected">---请选择地区---</option>
								</c:if>
							</select>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">预备订单状态：</label>
						<div class="controls">
							<form:select path="status" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('prepare_order_status')}" itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span> 
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">地址：</label>
						<div class="controls">
							<form:input path="customerAddress" htmlEscape="false" maxlength="255" class="input-xlarge required" disabled="true"/>
							<span class="help-inline"><font color="red">*</font> </span> </br> 
						</div>
					</div>
				</td>
				<td>
				</td>
			</tr>
		</table>

		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>