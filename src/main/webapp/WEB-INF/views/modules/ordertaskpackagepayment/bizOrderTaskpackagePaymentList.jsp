<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#allCheck").click(function () {
	            if ($(this).attr("checked")) { // 全选 
	                $("input[name='ids']").each(function () {
	                    $(this).attr("checked", true);
	                });
	            } else { // 取消全选 
	                $("input[name='ids']").each(function () {
	                    $(this).attr("checked", false);
	                });
	            }
	        });
			
			$("#guaranteePaymentSummary").click(function(){
				if($("input[name='ids']:checked").length == 0){
					alertx("请选择付款单!");
					return;
				}
				//判断是否是同一区域的订单
				var payments = $("input[name='ids']:checked");
				var flag = false;
				if(payments.length > 1){
					for(var i = 0 ;i<payments.length-1 ;i++){
						if($("#enginDepartId"+payments[i].value).val() != $("#enginDepartId"+payments[i+1].value).val()){
							flag = true;
							break;
						}
					}
				}
				if(flag){
					alert("请选择相同的区域付款单！");
					return;
				}
				
				$(this).unbind(); //移除所有
				//loading('正在提交，请稍等...');
				var ids = "";
				$("input[name='ids']:checked").each(function () {
					if(ids == ""){
						ids = $(this).val();
					}else{
						ids = ids + "," + $(this).val();
					}
                });
				$("#paymentIds").val(ids);
				var boo =false;
				$.ajax({
					url:'${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/checkPaymentByIds?ids='+ids,
					type:'post',
					success:function(data){
						if(data == 0){
							$("#summaryForm").submit();
						}else{
							alertx("存在已经生成批次的付款单!");
						}
					}
				});
				
				
			});

			getDepartments();
		});

		function getDepartments(){
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
					if(data == null || data == ""){
						$("#enginDepartId").html("");
						$("#s2id_enginDepartId .select2-chosen").html("");
					}else{
						var content = "<option></option>";
						for(var i=0;i<data.length;i++){
							if('${bizOrderTaskpackagePaymentVo.enginDepartId}' == data[i].value){
								$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
								content = content + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
							}else{
								content = content + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
							}
						}
						$("#enginDepartId").html(content);
					}
				}
			});
		}
		
		function page(n,s){
			$("#searchForm").submit();
        	return false;
        }
		
		function clearCondition(){
			// document.getElementById("searchForm").reset();
			
			 var inputObjs=jQuery("#searchForm input[type='text']");
			 for(var i=0;i<inputObjs.length;i++){ 
			 var inputObj = inputObjs[i]; 
			 inputObj.value=""; 
			 }

			 /*var selectObjs = jQuery("#searchForm input[class='input-medium']");
			 for(var i=0;i<selectObjs.length;i++){ 
			 var selectObj = selectObjs[i]; 
			 selectObj.value=""; 
			 }*/
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/loadList">付款单列表</a></li>
	</ul>
	<form id="summaryForm" action="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/guaranteeOrderTaskpackagePaymentSummary" method="post">
		<input type="hidden" name="paymentIds" id="paymentIds"/>
	</form>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackagePaymentVo" action="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/loadList" method="post" class="breadcrumb form-search">
		<input type="hidden" id="employeeId" name="employeeId" value="${employeeId}">
		<ul class="ul-form">
			
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" id="storeId" onchange="getDepartments()">
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
				<select id="enginDepartId" name="enginDepartId" class="input-medium needClear">

				</select>
			</li>
			<li>
			  <label>客户姓名：</label>
			  <form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li>
			  <label>项目经理：</label>
			  <form:input path="realName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li>
			  <label>工人组长：</label>
			  <form:input path="groupRealname" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li>
			  <label>付款单状态：</label>
			  <form:select path="status" class="input-medium required">
                 <form:option value=""></form:option>
                 <form:option value="15">已审核通过待打款</form:option>
                 <form:option value="90">批次已作废</form:option>
			  </form:select>
			</li>
			
			<li><label>付款单日期：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentVo.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true});"/> 至  
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentVo.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li><input class="btn btn-primary clearBtn" type="button" value="生成付款批次" id="guaranteePaymentSummary"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" id="allCheck"/>全选</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>付款单编号</th>
				<th>结算单编号</th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>工人组长</th>
				<th>任务包名称</th>
				<th>付款单状态</th>
				<th>付款单类型</th>
				<th>结算金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="payment">
			<tr>
				<td><input type="checkbox" name="ids" value="${payment.id}"/></td>
				<td>${fns:getStoreLabel(payment.storeId, '')}</td>
				<td>${fns:getDictLabel(payment.projectMode, 'package_project_mode', '')}</td>
				<td>${payment.enginDepartName}<input type="hidden" value="${payment.enginDepartId }" id="enginDepartId${payment.id}"></td>
				<td>${payment.orderTaskpackagePaymentCode}</td>
				<td>${payment.settlementNo}</td>
				<td>${payment.customerMessage}-${payment.customerName}</td>
				<td>${payment.realName}</td>
				<td>${payment.groupRealname}</td>
				<td>${payment.packageName}</td>
				<td>${payment.statusName}</td>
				<td>
					<c:if test="${payment.orderTaskpackagePaymentType eq '0'}">首款</c:if>
					<c:if test="${payment.orderTaskpackagePaymentType eq '1'}">尾款</c:if>
				</td>
				<td>${payment.amount}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>