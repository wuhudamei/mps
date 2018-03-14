<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查看售后问题详情</title>
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
		<li><a href="${ctx}/cusserviceproblem/bizCusServiceProblem/list">售后问题列表管理</a></li>
<%-- 		<li class="active"><a href="${ctx}/cusserviceproblem/bizCusServiceProblem/form?id=${bizCusServiceProblem.id}">无<shiro:hasPermission name="cusserviceproblem:bizCusServiceProblem:edit">${not empty bizCusServiceProblem.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cusserviceproblem:bizCusServiceProblem:edit">查看</shiro:lacksPermission></a></li> --%>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizCusServiceProblem" action="${ctx}/cusserviceproblem/bizCusServiceProblem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
	<table>
		
		<tr>
		<td>
			<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<input name="storeId" type="text" readonly="readonly" maxlength="20" class="input-xlarge"
					value="${fns:getStoreLabel(bizCusServiceProblem.storeId, '')}" />
			</div>
		</div>
		</td>
		<td>
		<div class="control-group">
			<label class="control-label">地址：</label>
			<div class="controls">
				<form:input path="customerAddress" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		</td>
		
		
		</tr>
		
		
		
		<tr>
		<td>
		<div class="control-group">
			<label class="control-label">客户姓名：</label>
			<div class="controls">
				<form:input path="customerName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		</td>
		<td>
		<div class="control-group">
			<label class="control-label">客户手机：</label>
			<div class="controls">
				<form:input path="customerMobile" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		</div>
		</td>
		</tr>
	
		<tr>
		<td>
		<div class="control-group">
			<label class="control-label">项目经理：</label>
			<div class="controls">
				<form:input path="contractorName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		</td>
		<td>
		<div class="control-group">
			<label class="control-label">项目经理手机：</label>
			<div class="controls">
				<form:input path="contractorMobile" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		</div>
		</td>
		</tr>
	
	
		<tr>
		<td>
		<div class="control-group">
			<label class="control-label">质检：</label>
			<div class="controls">
				<form:input path="supervisorName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		</td>
		<td>
		<div class="control-group">
			<label class="control-label">质检手机号：</label>
			<div class="controls">
				<form:input path="supervisorMobile" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		</td>
		</tr>
	
	
		<tr>
		<td>
		<div class="control-group">
			<label class="control-label">问题创建时间：</label>
			<div class="controls">
				<input style="width: 220" name="problemCreateDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizCusServiceProblem.problemCreateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		</td>
		<td>
		<div class="control-group">
			<label class="control-label">问题描述：</label>
			<div class="controls">
				<form:input path="problemDescribe" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		</td>
		</tr>
	
	
		<tr>
		<td>
		<div class="control-group">
			<label class="control-label">责任部门：</label>
			<div class="controls">
				<form:input path="liableDepartmentCode" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		</td>
		<td>
		<div class="control-group">
			<label class="control-label">责任类别1：</label>
			<div class="controls">
				<form:input path="liableTypeCode1" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		</td>
		</tr>
	
	
		<tr>
		<td>
		<div class="control-group">
			<label class="control-label">责任类别2：</label>
			<div class="controls">
				<form:input path="liableTypeCode2" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		</td>
		<td>
		<div class="control-group">
			<label class="control-label">重要程度：</label>
			<div class="controls">
				<form:input path="importantDegreeCode1" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		</td>
		</tr>
	
		<tr>
		<td>

		<div class="control-group">
			<label class="control-label">重要程度2：</label>
			<div class="controls">
				<form:input path="importantDegreeCode2" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		</td>
		<td>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:input path="photoUrl" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		</td>
		</tr>
	
	
	
		<tr>
		<td>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
			
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		</td>
		<td>
		</td>
		</tr>
	
	
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">store_code：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="storeCode" htmlEscape="false" maxlength="50" class="input-xlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">order_id：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="orderId" htmlEscape="false" maxlength="11" class="input-xlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">order_code：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="orderCode" htmlEscape="false" maxlength="100" class="input-xlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">work_order_code：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="workOrderCode" htmlEscape="false" maxlength="100" class="input-xlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
	



			<tr>
		<td>
		<div class="control-group">
			<label class="control-label">处理记录：</label>
			<div class="controls">
			
					<form:select id="statuszhuan1"    path="status"  disabled="true"   class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('status_cus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			
			</div>
		</div>

		
		
		
		
		</td>
		<td>
		<div class="control-group">
			<label class="control-label">操作时间：</label>
				<div class="controls">
				<input style="width: 220" name="problemCreateDatetime" type="text" disabled="true" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizCusServiceProblem.statusdatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		</td>
		</tr>

		<tr>
		<td>
				<div >
<%-- 					<shiro:hasPermission name="cusserviceproblem:bizCusServiceProblem:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
					<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
				</div>
		</td>
		</tr>

		</table>
	</form:form>
</body>
</html>