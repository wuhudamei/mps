<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程进度节点管理</title>
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
		<li class="active"><a href="#">结算补助列表</a></li>
		<li><a href="${ctx}/pmsubsidycnfg/form">结算补助添加</a></li>
	</ul><br/>
	
	<%-- <form:form id="searchForm" modelAttribute="bizConstructionSchedule" action="${ctx}/constructionschedule/bizConstructionSchedule/list1" method="post" class="breadcrumb form-search"> --%>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
	<%-- </form:form> --%>
	<%-- <a href="${ctx }/pmsubsidycnfg/form" class="btn btn-primary">添加</a> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>计价面积开始值m²</th>
				<th>计价面积结束值m²</th>
				<th>补助单价（元/m²）</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pmsubsidycnfg">
			<tr>
				<td>
					${fns:getStoreLabel(pmsubsidycnfg.storeId,'')}
				</td>
				<td>
					${fns:getDictLabel(pmsubsidycnfg.projectMode, 'project_mode', '')}
				</td>
				<td>
					${pmsubsidycnfg.squareMin}
				</td>
				<td>
					${pmsubsidycnfg.squareMax}
				</td>
				<td>
					${pmsubsidycnfg.subsidyPrice}
				</td>
				<td>
					<c:if test="${pmsubsidycnfg.isEnabled == 1}">
						<span style="color:green;">${fns:getDictLabel(pmsubsidycnfg.isEnabled, 'biz_enable_status', '')}</span>
					
					</c:if>
					<c:if test="${pmsubsidycnfg.isEnabled == 0}">
						<span style="color: red;">${fns:getDictLabel(pmsubsidycnfg.isEnabled, 'biz_enable_status', '')}</span>
					
					</c:if>
					
				</td>
				<td>
					<c:if test="${pmsubsidycnfg.isEnabled == 1 }">
						<a href="${ctx }/pmsubsidycnfg/update?id=${pmsubsidycnfg.id}&isEnabled=0" onclick="return confirmx('确认停用吗？', this.href)">停用</a>
						<a href="${ctx }/pmsubsidycnfg/form?id=${pmsubsidycnfg.id}">修改</a>
					</c:if>
					<c:if test="${pmsubsidycnfg.isEnabled == 0 }">
						<a href="${ctx }/pmsubsidycnfg/update?id=${pmsubsidycnfg.id}&isEnabled=1" onclick="return confirmx('确认要启用吗？', this.href)">启用</a>
						<a href="${ctx }/pmsubsidycnfg/form?id=${pmsubsidycnfg.id}">修改</a>
					</c:if>
				
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>