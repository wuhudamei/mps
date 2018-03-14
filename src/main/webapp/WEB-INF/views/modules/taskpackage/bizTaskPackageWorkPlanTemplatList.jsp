<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务包派工计划模板管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/taskpackage/bizTaskPackageWorkPlanTemplat/">任务包派工计划模板列表</a></li>
		<shiro:hasPermission name="taskpackage:bizTaskPackageWorkPlanTemplat:edit"><li><a href="${ctx}/taskpackage/bizTaskPackageWorkPlanTemplat/form">任务包派工计划模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizTaskPackageWorkPlanTemplat" action="${ctx}/taskpackage/bizTaskPackageWorkPlanTemplat/" method="post" class="breadcrumb form-search">
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
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>新旧房：</label>
				<form:select path="isNewHouse" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>新旧房</th>
				<th>模板名称</th>
				<th>工期天数</th>
				<th>创建人</th>
				<th>模板状态</th>
				<shiro:hasPermission name="taskpackage:bizTaskPackageWorkPlanTemplat:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizTaskPackageWorkPlanTemplat">
			<tr>
				<td>
					${fns:getStoreLabel(bizTaskPackageWorkPlanTemplat.storeId, '')}
				</td>
				<td>${fns:getDictLabel(bizTaskPackageWorkPlanTemplat.projectMode, 'package_project_mode', '')}</td>
				<td>
					${fns:getDictLabel(bizTaskPackageWorkPlanTemplat.isNewHouse, 'biz_is_new_house', '')}
				</td>
				<td><a href="${ctx}/taskpackage/bizTaskPackageWorkPlanTemplat/form?id=${bizTaskPackageWorkPlanTemplat.id}">
					${bizTaskPackageWorkPlanTemplat.templatName}
					</a>
				</td>
				<td>
					${bizTaskPackageWorkPlanTemplat.workSchedule}
				</td>
				<td>
					${bizTaskPackageWorkPlanTemplat.createBy.loginName}
				</td>
				<td>
					${fns:getDictLabel(bizTaskPackageWorkPlanTemplat.status, 'biz_enable_status', '')}
				</td>
				<shiro:hasPermission name="taskpackage:bizTaskPackageWorkPlanTemplat:edit"><td>
					<a href="${ctx}/taskpackage/bizTaskPackageWorkPlanTemplat/enable?id=${bizTaskPackageWorkPlanTemplat.id}">
						<c:if test="${bizTaskPackageWorkPlanTemplat.status==1}">
							停用
						</c:if>
						<c:if test="${bizTaskPackageWorkPlanTemplat.status==0}">
							启用
						</c:if>
					</a>
    				<a href="${ctx}/taskpackage/bizTaskPackageWorkPlanTemplat/form?id=${bizTaskPackageWorkPlanTemplat.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>