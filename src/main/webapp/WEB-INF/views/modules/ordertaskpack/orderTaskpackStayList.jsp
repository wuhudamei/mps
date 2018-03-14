<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生成任务包管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//清空查询条件
		function clearCondition(){
			 document.getElementById("searchForm").reset();
			 //$("#enginDepartId").html('');
			 var inputObjs=jQuery("#searchForm input[type='text']"); 
			 for(var i=0;i<inputObjs.length;i++){ 
			 var inputObj = inputObjs[i]; 
			 inputObj.value=""; 
			 } 
			
			 var selectObjs = jQuery("#searchForm input[class='input-medium']"); 
			 for(var i=0;i<selectObjs.length;i++){ 
			 var selectObj = selectObjs[i]; 
			 selectObj.value=""; 
			 } 
			 // $('#orderStatusNumbers').removeAttr('checked');
		}
		
		$(function(){
			var taskpackType = '${taskpackType}';
			if(null!=taskpackType && taskpackType=="1"){
				alertx("生成任务包失败，请完善该订单数据，再操作此功能");
			}
		});
		
		//加载区域信息
		function getDepartments(){
			$("#enginDepartId").html('');
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':$("#projectMode").val(),
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null){
						$("#enginDepartId").append('');
					} else {
						var html = "<option value=''></option>";
						for(var i=0;i<data.length;i++){
							var sec = "";
							if('${orderTaskpack.enginDepartId}' == data[i].value){
			            		sec = "selected='selected'";
			            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
			            	}
							html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
						}
						html+='';
		        		$("#enginDepartId").append(html);
					}
				}
			});
		}
		
		// --子项复选框被单击---
		function ChkSonClick(sonName, cbAllId) {
			var arrSon = document.getElementsByName(sonName);
			var cbAll = document.getElementById(cbAllId);
			for (var i = 0; i < arrSon.length; i++) {
				if (!arrSon[i].checked) {
					cbAll.checked = false;
					return;
				}
			}
			cbAll.checked = true;
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpack/orderTaskpack/stayList">待生成任务包的订单</a></li>
		<%-- <shiro:hasPermission name="ordertaskpack:orderTaskpack:edit"><li><a href="${ctx}/ordertaskpack/orderTaskpack/form">订单添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="orderTaskpack" action="${ctx}/ordertaskpack/orderTaskpack/stayList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" id="storeId" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" id="storeId" class="input-medium needClear" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li> 
			
			<li><label>区域：</label>
				<form:select path="enginDepartId" id ="enginDepartId" class="input-medium needClear">
					<%-- <form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<!-- 
			<li><label>订单状态：</label> 
				<form:select path="orderStatusNumber" class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('order_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li>
				<label>任务包状态：</label>
				<form:select path="ordertaskpackStatus" class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('order_taskpack_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			 -->
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<span style="font-weight:bold;font-size:20px;">
		待生成任务包订单：<span style="color:red;">${stayCount }</span>
		<hr size="3px" noshade=true/>
	</span>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店编号</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>合同开工日期</th>
				<shiro:hasPermission name="ordertaskpack:orderTaskpack:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderTaskpack">
			<tr>
				<td><%-- <a href="${ctx}/ordertaskpack/orderTaskpack/form?id=${orderTaskpack.id}">
					${fns:getStoreLabel(orderTaskpack.storeId, '')}
				</a> --%>${fns:getStoreLabel(orderTaskpack.storeId, '')}</td>
				<td>${fns:getDictLabel(orderTaskpack.projectMode, 'package_project_mode', '')}</td>
				<td>
					${orderTaskpack.departmentName}
				</td>
				<td>
					${orderTaskpack.orderNumber}
				</td>
				<td>
					${orderTaskpack.customerName}
				</td>
				<td>
					${orderTaskpack.customerAddress}
				</td>
				<td>
					${orderTaskpack.itemManager}
				</td>
				<td>
					<fmt:formatDate value="${orderTaskpack.contractStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="ordertaskpack:orderTaskpack:edit"><td>
					<c:if test="${orderTaskpack.ordertaskpackStatus == '0'}">
						<a href="${ctx}/ordertaskpack/orderTaskpack/form?id=${orderTaskpack.id}&mark=1&storeId=
						${orderTaskpack.storeId }&projectMode=${orderTaskpack.projectMode}&contractStartDate=<fmt:formatDate type="both"
							value="${orderTaskpack.contractStartDate }"/>">生成任务包</a><!-- pattern="yyyy-MM-dd HH:mm:ss"  -->
					</c:if>
					<c:if test="${orderTaskpack.ordertaskpackStatus == '1'}">
						<a href="${ctx}/ordertaskpack/orderTaskpack/form?id=${orderTaskpack.id}&mark=2&storeId=
						${orderTaskpack.storeId }&packCode=0">查看详情</a>
						<a href="${ctx}/ordertaskpack/orderTaskpack/toCreateSpecialTaskpackage?id=${orderTaskpack.id}">生成特殊任务包</a>
					</c:if>
					<c:if test="${orderTaskpack.ordertaskpackStatus == null}">
						<a href="${ctx}/ordertaskpack/orderTaskpack/form?id=${orderTaskpack.id}&mark=1&storeId=
						${orderTaskpack.storeId}&projectMode=${orderTaskpack.projectMode}&contractStartDate=<fmt:formatDate type="both"
							value="${orderTaskpack.contractStartDate }"/>">生成任务包</a><!-- pattern="yyyy-MM-dd HH:mm:ss"  -->
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>