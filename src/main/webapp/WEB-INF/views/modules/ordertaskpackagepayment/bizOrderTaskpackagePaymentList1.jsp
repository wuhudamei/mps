<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.undis{display:none;}
		.g-mask{width:100%;height:100%;position:relative;z-index:99;font-size:0;}
		#g-done_ask{width:400px;height:200px;position:fixed;left:50%;top:50%;transform:translate(-50%,-50%);border:1px solid #333;border-radius:4px;}
		.refuse{height:50px;line-height:50px;font-size:20px;background:#54b4eb;margin:0;}
		.content{color:#000;width:400px;height:100px;resize: none;margin:0;}
		.second{width:400px;}
		.second a{display:block;width:200px;height:50px;line-height:50px;font-size:24px;text-decoration:none;float:left;text-align:center;background:#54b4eb;color:#fff;}
		.second a:first-child{box-sizing:border-box;border-right:1px solid #ccc;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {

		});
			
		function page(n,s){
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/list1">结算单尾款审核</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackagePaymentVo" action="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/alist1" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>结算单日期：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentVo.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true});"/> 至  
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentVo.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true});"/>
			</li>
			<%-- <li><label>结算单编号：</label>
				<form:input path="settlementNo" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li> --%>
			<li><label>结算单编号：</label>
				<form:input path="settlementNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>门店</th>
				<th>付款单编号</th>
				<th>结算单编号</th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>任务包名称</th>
				<th>结算金额</th>
				<th>质检员验收详情</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="payment" varStatus="status">
			<tr>
				<td>${status.index+1 }</td>
				<td>${fns:getStoreLabel(payment.storeId, '')}</td>
				<td>${payment.orderTaskpackagePaymentCode}</td>
				<td>${payment.settlementNo}</td>
				<td>${payment.customerMessage}-${payment.customerName}</td>
				<td>${payment.realName}</td>
				<td>${payment.packageName}</td>
				<td>${payment.amount}</td>
				<td>
					<a href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/list2?qcBillId=${payment.qcBillId}">查看</a>
				</td>
				<td>
				<c:if test="${payment.status == '10'}">
					<a href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/approvePass?qcBillId=${payment.qcBillId}&paymentId=${payment.id}" onclick="return confirmx('确认要通过吗？', this.href)">通过</a>
					<a href="#" onclick="reject('${payment.qcBillId}')">驳回</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="g-mask undis" id="refuseSalary">
		<div id="g-done_ask">
			<p class="refuse">请输入驳回理由：</p>
			<textarea class="content" id="reason"></textarea>
			<p class="second">
				<a href="javascript:void(0)" onclick="noReject()">取消</a>
				<a href="javascript:void(0)" onclick="yesReject()">确认</a>
			</p>
		</div>
	</div>
	<script type="text/javascript">
		
		var qcBillId;
		
		function reject(id){
			qcBillId = id;
			$("#refuseSalary").removeClass("undis");
		}
		function yesReject(){
			var reason = $("#reason").val();
			$("#reason").val("");
			$("#refuseSalary").addClass("undis");
			window.location.href = "${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/refusePass?qcBillId="+qcBillId+"&reason="+reason;
		}
		function noReject(){
			$("#reason").val("");
			$("#refuseSalary").addClass("undis");
		}
	
	</script>
</body>
</html>