<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务包模板预算金额上限管理</title>
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
		
		function enable(id,isEnabled,budgetSquareMin,budgetSquareMax,taskpackageTemplatId){
			$.ajax({
				url:'${ctx}/taskpackage/bizTaskPackageTemplat/checkDate',
				type:'post',
				dataType : 'json',
				data:{
					'taskpackageTemplatId':taskpackageTemplatId,
					'budgetSquareMin':budgetSquareMin,
					'budgetSquareMax':budgetSquareMax,
					'isEnabled':'1',
					'id':id
				},
				success:function(data){
					if(data.result == "1"){
					 window.location.href="${ctx}/taskpackage/bizTaskPackageTemplat/enableTemplatBugetAmount?id="+id+"&isEnabled="+isEnabled;
					}else if(data.result == "2"){
						var result="已经存在开始值或者结束值的配置项了（"+data.templatBugetAmount.budgetSquareMin+"~"+data.templatBugetAmount.budgetSquareMax+"），开始值和结算值是不允许重复的，请您修改开始值和结算值。";
						alertx(result);
					}
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	    <li class="active"><a href="#">任务包模板预算金额列表</a></li>
		<li><a href="${ctx}/taskpackage/bizTaskPackageTemplat/">任务包模板列表</a></li>
		<li><a href="${ctx}/taskpackage/bizTaskPackageTemplat/openTemplatBudgetAmountForm?taskpackageTemplatId=${bizTaskPackageTemplatBugetAmount.taskpackageTemplatId}">预算金额上限添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizTaskPackageTemplatBugetAmount" action="${ctx}/taskpackage/bizTaskPackageTemplat/queryTemplatBudgetAmountList" method="post" class="breadcrumb form-search">
		<form:input path="frontSort" htmlEscape="false"  type="hidden" maxlength="18" class="input-medium"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable"  class="table table-striped table-bordered table-condensed sortable">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>任务包名称</th>
				<th>面积开始值m²</th>
				<th>面积结束值m²</th>
				<th>预算金额上限（元）</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizTaskPackageTemplatBugetAmount">
			<tr>
			    <td>
					${fns:getStoreLabel(bizTaskPackageTemplatBugetAmount.storeId, '')}
				</td>
				
				<td>${fns:getDictLabel(bizTaskPackageTemplatBugetAmount.projectMode, 'package_project_mode', '')}</td>
				
				<td>
					${bizTaskPackageTemplatBugetAmount.templatName}
				</td>
				
				<td>
					${bizTaskPackageTemplatBugetAmount.budgetSquareMin }
				</td>
				<td>
					${bizTaskPackageTemplatBugetAmount.budgetSquareMax }
				</td>
				<td>
					${bizTaskPackageTemplatBugetAmount.laborAuxiliaryMaterialsBudgetAmountMax }
				</td>
				<td>
				   ${fns:getDictLabel(bizTaskPackageTemplatBugetAmount.isEnabled, 'biz_enable_status', '')}
				</td>
				<td>
					
						<c:if test="${bizTaskPackageTemplatBugetAmount.isEnabled==1}">
							<a href="${ctx}/taskpackage/bizTaskPackageTemplat/enableTemplatBugetAmount?id=${bizTaskPackageTemplatBugetAmount.id}&isEnabled=0">停用</a>
						</c:if>
						<c:if test="${bizTaskPackageTemplatBugetAmount.isEnabled==0}">
							<a href="#" onclick="enable(${bizTaskPackageTemplatBugetAmount.id},1,${bizTaskPackageTemplatBugetAmount.budgetSquareMin},${bizTaskPackageTemplatBugetAmount.budgetSquareMax},${bizTaskPackageTemplatBugetAmount.taskpackageTemplatId})">启用</a>
						</c:if>
					
    				<a href="${ctx}/taskpackage/bizTaskPackageTemplat/openTemplatBudgetAmountForm?id=${bizTaskPackageTemplatBugetAmount.id}">修改</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>