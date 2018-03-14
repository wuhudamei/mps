<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客诉统计</title>
	<style>
		.undis{display:none;}
		.g-mask{width:100%;height:100%;position:relative;z-index:99;font-size:0;}
		#g-done_ask{width:600px;height:360px;position:fixed;left:50%;top:50%;transform:translate(-50%,-50%);border:1px solid #333;border-radius:4px;}
		.refuse{height:40px;line-height:40px;font-size:20px;background:#54b4eb;margin:0;}
		.content{color:#000;width:600px;height:100px;resize: none;margin:0;}
		.second{width:600px;}
		.second a{display:block;width:300px;height:40px;line-height:40px;font-size:24px;text-decoration:none;float:left;text-align:center;background:#54b4eb;color:#fff;}
		.second a:first-child{box-sizing:border-box;border-right:1px solid #ccc;}
	</style>
	
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
    	return false;
    }
	function submitForm(type){
		if (type == 'query'){
			$("#searchForm").attr("action","${ctx}/score/complain/ScoreOrderlist2");
		}else {
			$("#searchForm").attr("action","${ctx}/score/complain/export");
		}
		$("#searchForm").submit();	
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
		 
		 var inputObjs=jQuery("#searchForm input[type='number']"); 
		 for(var i=0;i<inputObjs.length;i++){ 
		 var inputObj = inputObjs[i]; 
		 inputObj.value=""; 
		 }
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"></li>
	</ul>
		<form:form id="searchForm" modelAttribute="scoreOrderComplain" action="${ctx}/score/complain/ScoreOrderlist2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="name" id="storeName" class="input-medium needClear" >
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
			<label>客诉类型：</label>
				<form:select path="label" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getComplainType()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>起止日期 ：</label>
				<input name="dateBegin" type="text" id="beginCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${scoreOrderComplain.dateBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:false});"/> - 
				<input name="dateEnd" type="text" id="endCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${scoreOrderComplain.dateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:false});"/>
			</li>
			<li>
				<input type="text" name="query" value="${scoreOrderComplain.query}"  placeholder="客诉内容/客户姓名/订单地址/客户电话/订单号"  >
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick ="submitForm('query')"/></li>
			 <li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出" onclick ="submitForm('export')"/></li> 
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<%-- <sys:message content="${message}"/> --%>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分公司</th>
				<th>客诉编码</th>
				<th>订单号</th>
				<th>客诉分类</th>
				<th>客户姓名</th>
				<th>客户电话</th>
				<th>订单地址</th>
				<th style="max-width:300px;">客诉内容</th>
				<th>客诉员</th>
				<th>客诉时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="scoreOrder">
			<tr>
				<td title="${scoreOrder.name}">
					${scoreOrder.name}
				</td>
				<td title="${scoreOrder.complainCode}">
					${scoreOrder.complainCode}
				</td>
				
				<td title="${scoreOrder.orderNumber}">
					${scoreOrder.orderNumber}
				</td>
				<td title="${scoreOrder.label}">
					${scoreOrder.label}
				</td>
				
				<td title="${scoreOrder.customerName}">
					${scoreOrder.customerName}
				</td>
				<td title="${scoreOrder.customerPhone}">
					${scoreOrder.customerPhone}
				</td>
				<td title="${scoreOrder.province}${scoreOrder.city }${scoreOrder.county }${scoreOrder.detailAddress} ">
					<%-- ${scoreOrder.province}
					${scoreOrder.city }
					${scoreOrder.county } --%>
					<div style="max-height:20px;overflow:hidden">${scoreOrder.detailAddress} </div>
					
				</td>
				<td title="${scoreOrder.complainContent} " >
					<div style="max-height:20px;overflow:hidden;max-width:100px">${scoreOrder.complainContent} </div>
					
				</td>
				<td title="${scoreOrder.employeeName} ">
					${scoreOrder.employeeName} 
				</td>
				<td>
					<fmt:formatDate value="${scoreOrder.complainTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="g-mask undis" id="approve">
	</div>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		$(document).ready(function() {
		function submitForm(){
			var a = $("#storeId").val();
		}
		});
	</script>
	<script type="text/javascript">
	</script>
</body>
</html>