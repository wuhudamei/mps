<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理星级设置</title>
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
	<form:form id="searchForm" modelAttribute="bizStarBasicSalary" action="${ctx}/bizstar/bizManagerStar/bizStarBasicSalary/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>星级</th>
				<th>对应底薪</th>
				<th>版本号</th>
				<th>生效日期</th>
				<th>状态</th>
				<shiro:hasPermission name="bizmanagerstar:bizmanagerstar:edit"><th>状态</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizStarBasicSalary">
			<tr>
				<td>
					${fns:getStoreLabel(bizStarBasicSalary.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizStarBasicSalary.projectMode, 'project_mode','')}
				</td>
				<td>
					${fns:getDictLabel(bizStarBasicSalary.starLevel, 'manager_star', '')}
				</td>
				<td>
					${bizStarBasicSalary.basicSalary}
				</td>
				<td>
					${bizStarBasicSalary.version}
				</td>
				<td>
					<fmt:formatDate value="${bizStarBasicSalary.effectDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizStarBasicSalary.status==0?'停用':'启用'}
				</td>						
				<shiro:hasPermission name="bizmanagerstar:bizmanagerstar:edit"><td>
					<c:if test="${bizStarBasicSalary.status==0}">
						<a href="${ctx}/bizstar/bizManagerStar/bizStarBasicSalary/updateStatus?id=${bizStarBasicSalary.id}&status=1">启用</a>
					</c:if>
    				<c:if test="${bizStarBasicSalary.status==1}">
						<a href="${ctx}/bizstar/bizManagerStar/bizStarBasicSalary/updateStatus?id=${bizStarBasicSalary.id}&status=0">停用</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>