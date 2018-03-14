<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公告管理</title>
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
		<li class="active"><a href="${ctx}/notice/bizNotice/loadList">公告列表</a></li>
		<shiro:hasPermission name="notice:bizNotice:edit"><li><a href="${ctx}/notice/bizNotice/form">公告添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizNoticeVo" action="${ctx}/notice/bizNotice/loadList" method="post" class="breadcrumb form-search">
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
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:options items="${fns:getDictList('employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>公告标题：</label>
				<form:input path="noticeTitle" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
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
				<!-- <th>接收对象</th> -->
				<th>公告类型</th>
				<th>公告标题</th>
				<th>公告内容</th>
				<th>发布时间</th>
				<th>未阅/已阅</th>
				<!-- <th>未阅/已阅</th> -->
				<th>状态</th>
				<shiro:hasPermission name="notice:bizNotice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizNoticeVo">
			<tr>
				<td>${fns:getStoreLabel(bizNoticeVo.storeId, '')}</td>
				<td><c:if test="${bizNoticeVo.projectMode eq '1'}">产业</c:if>
					<c:if test="${bizNoticeVo.projectMode eq '2'}">传统</c:if>
					<c:if test="${bizNoticeVo.projectMode eq '3'}">全部</c:if></td>
				<%-- <td>${bizNoticeVo.receiverRoleName}</td> --%>
				<td><c:if test="${bizNoticeVo.noticeType eq '1'}">紧急通知</c:if>
					<c:if test="${bizNoticeVo.noticeType eq '2'}">重要通知</c:if>
					<c:if test="${bizNoticeVo.noticeType eq '3'}">日常通知</c:if></td>
				<td>${bizNoticeVo.noticeTitle}</td>
				<td style="text-align: center"><a href="${ctx}/notice/bizNotice/detail?id=${bizNoticeVo.id}">查看</a></td>
				<td><fmt:formatDate value="${bizNoticeVo.publishDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><a href="${ctx}/notice/bizNotice/noReadAndyesRead?id=${bizNoticeVo.id}" style="font-weight:600">查看</a></td>
				<%-- <td><a href="${ctx}/notice/bizNotice/yesRead?id=${bizNoticeVo.id}">${bizNoticeVo.alreadyReceiverNum}人</a></td> --%>
				<td><c:if test="${bizNoticeVo.noticeStatus eq '1'}">暂存</c:if>
					<c:if test="${bizNoticeVo.noticeStatus eq '2'}">已发布</c:if>
					<c:if test="${bizNoticeVo.noticeStatus eq '3'}">已撤回</c:if></td>
				<shiro:hasPermission name="notice:bizNotice:edit"><td>
    				<c:if test="${bizNoticeVo.noticeStatus eq '1'}">
						<a href="${ctx}/notice/bizNotice/form?id=${bizNoticeVo.id}">编辑</a>
					</c:if>
					<c:if test="${bizNoticeVo.noticeStatus eq '2' and bizNoticeVo.dateDiff eq 0}">
						<a href="${ctx}/notice/bizNotice/revoked?id=${bizNoticeVo.id}" onclick="return confirmx('确认要撤回该公告吗？', this.href)">撤回</a>
					</c:if>
					<c:if test="${bizNoticeVo.noticeStatus eq '1'}">
						<a href="${ctx}/notice/bizNotice/delete?id=${bizNoticeVo.id}" onclick="return confirmx('确认要删除该公告吗？', this.href)">删除</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>