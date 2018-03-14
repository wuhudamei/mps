<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务包计划</title>
	<meta name="decorator" content="default"/>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">调整计划</a></li>
	</ul>
	
		<ul class="ul-form">
			<li>
				${customerMessage }
			</li>
			
			
		</ul>
<form:form id="inputForm" 
		action="${ctx}/taskpackageschedule/reallychange" method="post" class="form-horizontal">
		
		<input type="hidden" name="orderId" value="${orderId }">
		<input type="hidden" name="customerMessage" value="${customerMessage }">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>顺序</th>
				<th>任务包名称</th>
				<th>开始日期</th>
				<th>结束日期</th>	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="pack" varStatus="status">
		<input type="hidden" name="id" value="${pack.id }">
		
			<tr>
				<td>
					${status.index+1 }
				</td>
				
				<td>
				${pack.packageName }
				</td>
				
				<td>
				<!-- 状态为20 50 55 60 位可修改,  不然已经施工不能修改 -->
				<c:if test="${pack.packageStateId =='20' or pack.packageStateId =='50' or pack.packageStateId =='55' or pack.packageStateId =='60' }">
				
				<input name="planStartdate" id="${pack.id }" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pack.planStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'${pack.packageName}\')}',isShowClear:true});"/>
				</c:if>
				
				
				<c:if test="${pack.packageStateId !='20' and pack.packageStateId !='50' and pack.packageStateId !='55' and pack.packageStateId != '60' }">
				<input name="planStartdate" id="${pack.id }" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="color:gray;"
					value="<fmt:formatDate value="${pack.planStartdate}" pattern="yyyy-MM-dd"/>" />
				</c:if>
				</td>
				
				
				
				
				<td>
				<!-- 状态为20 50 55 60 位可修改,   -->
				<c:if test="${pack.packageStateId =='20' or pack.packageStateId =='50' or pack.packageStateId =='55' or pack.packageStateId =='60' }">
				
				<input name="planEnddate" id="${pack.packageName }" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${pack.planEnddate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'${pack.id}\')}',isShowClear:true});"/>
				
				
				</c:if>
				<!-- 不然已经施工不能修改 -->
				
				<c:if test="${pack.packageStateId !='20' and pack.packageStateId !='50' and pack.packageStateId !='55' and pack.packageStateId != '60' }">
				<input name="planEnddate" id="${pack.packageName }" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="color:gray;"
					value="<fmt:formatDate value="${pack.planEnddate}" pattern="yyyy-MM-dd"/>" />
				
				
				</c:if>
				
				</td>
				
					
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
		<div class="form-actions">
		<shiro:hasPermission name="taskpackageschedule:taskpackageschedule:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
<script type="text/javascript">

</script>
</html>