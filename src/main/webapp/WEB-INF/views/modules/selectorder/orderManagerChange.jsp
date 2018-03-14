<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理更换记录查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
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
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/selectOrderChange/selectOrderChange/selectList">项目经理更换记录查询</a></li>
	</ul>
	<br/>
	
	<form:form id="searchForm" modelAttribute="orderManagerChange" action="${ctx}/selectOrderChange/selectOrderChange/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<ul class="ul-form">
			
				<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" id="storeId" >
					<form:option value=""></form:option>
					<form:options items="${fns:getStoreList()}" itemLabel="label" selectIndex="1" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			
			<li><label>更换时间：</label>
				<input name="changeDate" id="changeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orderManagerChange.changeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> 至 
				<input name="endChangeDate" id="endChangeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${orderManagerChange.endChangeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"  /></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>客户手机号</th>
				<th>更换时间</th>
				<th>原项目经理</th>
				<th>原项目经理手机号</th>
				<th>新项目经理</th>
				<th>新项目经理手机号</th>
				<th>更换原因</th>
				<th>操作人</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderManagerChange">
			<tr>
				<td>${fns:getStoreLabel(orderManagerChange.storeId, '')}</td>
				<td>${orderManagerChange.orderNumber }</td>
				<td>${orderManagerChange.customerName }</td>
				<td>${orderManagerChange.customerPhone }</td>
				<td>
					<fmt:formatDate value="${orderManagerChange.changeDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${orderManagerChange.oldManagerName }</td>
				<td>${orderManagerChange.oldManagerPhone }</td>
				<td>${orderManagerChange.newManagerName }</td>
				<td>${orderManagerChange.newManagerPhone }</td>
				<td>${orderManagerChange.remarks }</td>
				<td>${orderManagerChange.createName }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>