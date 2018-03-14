<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>财务收款2.0</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).keydown(function(event){

		})
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function clearCondition(){
			 document.getElementById("searchForm").reset();
			
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
		}

		//ajax 查询
		function selectMessage(){
			 var storeId = $("#storeId").val();
			 var orderNumber = ($("#orderNumber").val()).replace(/(^\s*)|(\s*$)/g, "");
			 var customerName = ($("#customerName").val()).replace(/(^\s*)|(\s*$)/g, "");
			 var customerPhone = ($("#customerPhone").val()).replace(/(^\s*)|(\s*$)/g, "");
			 if(orderNumber=="" && customerName=="" && customerPhone==""){
				 alertx("请输入查询条件后再查询");
				 return;
			 }
			 param={
						storeId:storeId,
						orderNumber:orderNumber,
						customerName:customerName,
						customerPhone:customerPhone
				}
			 $.ajax({
			    	
			        url: "${ctx}/orderFinancialPayment/orderFinancialPayment/order_financial_payment_ajax_list",
			        type: "post",
			        data:param,
			        success: function(data){
			        	var htmls = '';
			        	if(null!=data && data.length > 0){
		            		for(var v=0;v<data.length;v++){
		            			htmls += '<tr>'+
											'<td>'+data[v].storeNameParams+'</td>'+
											'<td>'+data[v].orderNumberParams+'</td>'+
											'<td>'+data[v].detailAddressParams+'</td>'+						
											'<td>'+data[v].customerNameParams+'</td>'+						
											'<td>'+data[v].customerPhoneParams+'</td>'+						
											'<td>'+data[v].contractTimeParams+'</td>'+						
											'<td>'+data[v].startMoneyParams+'</td>'+						
											'<td>'+data[v].secondMoneyParams+'</td>'+						
											'<td>'+data[v].lastMoneyParams+'</td>'+						
										 '</tr>';
		            		}
		            	}
		            		
			        	$("#details").html(htmls);
			        }
			 })
			 
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/orderFinancialPayment/orderFinancialPayment/list">订单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/order/order/list1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium"  disabled="true">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>

			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label>客户手机号：</label>
				<form:input path="customerPhone" htmlEscape="false" maxlength="11" class="input-medium number needClear"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="selectMessage()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店信息</th>
				<th>订单编号</th>
				<th>详细地址</th>
				<th>客户姓名</th>
				<th>客户手机号</th>
				<th>合同签约日期</th>
				<th>首期款缴纳时间</th>
				<th>二期款缴纳时间</th>
				<th>尾款缴纳时间</th>
			</tr>
		</thead>
		<tbody id="details">
		</tbody>
	</table>
</body>
<script type="text/javascript">

</script>
</html>