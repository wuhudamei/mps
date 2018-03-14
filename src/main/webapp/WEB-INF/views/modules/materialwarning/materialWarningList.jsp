<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>材料预警查询</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	

</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>材料预警查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="materialWarning"
		action="${ctx}/materialWarning/materialWarningQuery/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>门店：</label>
                    <form:select path="storeId" class="input-medium needClear">
                        <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                    </form:select>
            </li>
			<li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear" disabled="true">
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>新房老房：</label>  
				<form:select path="isNewHouse" class="input-medium needClear">
                     <form:option value="" label=""/>	
                     <form:options items="${fns:getDictList('biz_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
             </li>
             <li><label>进度节点名称：</label>  
				<form:input path="constructionScheduleName" class="input-medium needClear"/>
             </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"	type="submit" value="查询" /></li>
            <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>新老房</th>
				<th>进度节点名称</th>
				<%-- <shiro:hasPermission name="bizarea:bizArea:edit"> --%>
				<th>延期订单数</th>
				<%-- </shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="number">
				<tr>
					<td>${fns:getStoreLabel(number.storeId, '')}</td>
					<td>${fns:getDictLabel(number.projectMode, 'project_mode', '')}</td>
					<td>
					<c:if test="${number.isNewHouse=='1'}">
						新房
					</c:if>
					<c:if test="${number.isNewHouse =='0'}">
						老房
					</c:if>
					
					</td>
					<td>${number.constructionScheduleName}</td>
					
					<td>
					<a href="${ctx}/materialWarning/materialWarningQuery/form?storeId=${number.storeId}&projectMode=${number.projectMode}&isNewHouse=${number.isNewHouse}&sort=${number.sort}">${number.allOrderSum}</a>
					</td>
					<%-- <shiro:hasPermission name="bizarea:bizArea:edit">
						<td><a href="${ctx}/bizarea/bizArea/form?id=${bizArea.id}">修改</a>
							<a href="${ctx}/bizarea/bizArea/delete?id=${bizArea.id}"
							onclick="return confirmx('确认要删除该区域吗？', this.href)">删除</a></td>
					</shiro:hasPermission> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>