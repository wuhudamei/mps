<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>墙地砖退货管理</title>
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
		<li class="active"><a href="${ctx}/materialwallfloor/wallFloorReturn/">墙地砖退货列表</a></li>
		<shiro:hasPermission name="materialwallfloor:wallFloorReturn:edit"><li><a href="${ctx}/materialwallfloor/wallFloorReturn/form">墙地砖退货添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wallFloorReturn" action="${ctx}/materialwallfloor/wallFloorReturn/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
<!-- 			<li><label>id：</label> -->
<%-- 				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/> --%>
<!-- 			</li> -->
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="13" class="input-medium needClear"/>
			</li>
			<li><label>退货日期：</label>
<!-- 				<input name="returnDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear" -->
<%-- 					value="<fmt:formatDate value="${wallFloorReturn.returnDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>" --%>
<!-- 					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss ',isShowClear:flase});"/> -->
					<input name="returnDatetime" id="returnDatetime1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${wallFloorReturn.returnDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			
			

			
			
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
<!-- 				<th>id</th> -->
				<th>订单编号</th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>退货面积(㎡)</th>
				<th>退货日期时间</th>
				<th>备注</th>
				<th>创建日期 </th>
<!-- 				<th>更新日期时间 </th> -->
				<shiro:hasPermission name="materialwallfloor:wallFloorReturn:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wallFloorReturn">
			<tr>
<%-- 				<td><a href="${ctx}/materialwallfloor/wallFloorReturn/form?id=${wallFloorReturn.id}"> --%>
<%-- 					${wallFloorReturn.id} --%>
<!-- 				</a></td> -->
				<td>
					${wallFloorReturn.sortId}
				</td>
				<td>
					${wallFloorReturn.orderNumber}
				</td>
				<td>
					${wallFloorReturn.customerName}
				</td>
				<td>
					${wallFloorReturn.itemManager}
				</td>

				<td>
					${wallFloorReturn.squareReturn}
				</td>
				<td>
					<fmt:formatDate value="${wallFloorReturn.returnDatetime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${wallFloorReturn.remarks}
				</td>
				<td>
					<fmt:formatDate value="${wallFloorReturn.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
<!-- 				<td> -->
<%-- 					<fmt:formatDate value="${wallFloorReturn.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
<!-- 				</td> -->
				<shiro:hasPermission name="materialwallfloor:wallFloorReturn:edit"><td>
    				<a href="${ctx}/materialwallfloor/wallFloorReturn/form?id=${wallFloorReturn.id}">修改</a>
					<a href="${ctx}/materialwallfloor/wallFloorReturn/delete?id=${wallFloorReturn.id}" onclick="return confirmx('确认要删除该墙地砖退货吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>