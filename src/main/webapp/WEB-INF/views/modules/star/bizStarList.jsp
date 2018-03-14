<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>星级承接量管理</title>
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
		<li class="active"><a href="${ctx}/star/bizStar/list">星级承接量列表</a></li>
		<shiro:hasPermission name="star:bizStar:edit"><li><a href="${ctx}/star/bizStar/form">星级承接量添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizStar" action="${ctx}/star/bizStar/starList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
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
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
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
				<th>门店</th>
				<th>工程模式</th>
				<th>星级</th>
				<th>承接量</th>
				<shiro:hasPermission name="star:bizStar:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizStar">
			<tr>
				<td><a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">
					${fns:getStoreLabel(bizStar.storeId, '')}
				</a></td>
				<td>${fns:getDictLabel(bizStar.projectMode, 'project_mode', '')}</td>
				<td>
					试用
				</td>
				<td>
					${bizStar.star0}
				</td>
				<shiro:hasPermission name="star:bizStar:edit"><td>
    				<a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
			<tr>
				<td><a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">
					${fns:getStoreLabel(bizStar.storeId, '')}
				</a></td>
				<td>${fns:getDictLabel(bizStar.projectMode, 'project_mode', '')}</td>
				<td>
					一星
				</td>
				<td>
					${bizStar.star1}
				</td>
				<shiro:hasPermission name="star:bizStar:edit"><td>
    				<a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
			<tr>
				<td><a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">
					${fns:getStoreLabel(bizStar.storeId, '')}
				</a></td>
				<td>${fns:getDictLabel(bizStar.projectMode, 'project_mode', '')}</td>
				<td>
					二星
				</td>
				<td>
					${bizStar.star2}
				</td>
				<shiro:hasPermission name="star:bizStar:edit"><td>
    				<a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
			<tr>
				<td><a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">
					${fns:getStoreLabel(bizStar.storeId, '')}
				</a></td>
				<td>${fns:getDictLabel(bizStar.projectMode, 'project_mode', '')}</td>
				<td>
					三星
				</td>
				<td>
					${bizStar.star3}
				</td>
				<shiro:hasPermission name="star:bizStar:edit"><td>
    				<a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
			<tr>
				<td><a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">
					${fns:getStoreLabel(bizStar.storeId, '')}
				</a></td>
				<td>${fns:getDictLabel(bizStar.projectMode, 'project_mode', '')}</td>
				<td>
					四星
				</td>
				<td>
					${bizStar.star4}
				</td>
				<shiro:hasPermission name="star:bizStar:edit"><td>
    				<a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
			<tr>
				<td><a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">
					${fns:getStoreLabel(bizStar.storeId, '')}
				</a></td>
				<td>${fns:getDictLabel(bizStar.projectMode, 'project_mode', '')}</td>
				<td>
					五星
				</td>
				<td>
					${bizStar.star5}
				</td>
				<shiro:hasPermission name="star:bizStar:edit"><td>
    				<a href="${ctx}/star/bizStar/fedit?id=${bizStar.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
			
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>