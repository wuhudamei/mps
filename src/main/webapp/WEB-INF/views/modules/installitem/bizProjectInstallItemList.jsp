<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>安装项管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
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
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/installitem/bizProjectInstallItem">安装项列表</a></li>
		<shiro:hasPermission name="installitem:bizProjectInstallItem:edit">
			<li><a href="${ctx}/installitem/bizProjectInstallItem/form">安装项添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizProjectInstallItem"
		action="${ctx}/installitem/bizProjectInstallItem/list1" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<%-- <li><label>工程安装项id：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li>
			<li>
			<label>门店：</label>
			 <form:select path="storeId" class="input-medium" id="sel">
				<form:option value="" label="" />
				<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select>
			</li>
			<li>
			
			<label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium required needClear">
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select>
			
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium required">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
				</form:select>
			</c:if>
		</li>
		<li>
		<label class="control-label">安装模式：</label>
		<form:select  path="installMode"    cssStyle="width:190px" id="complaintSource"    class="input-medium required needClear" >
			<form:option value="" label=""/>
			<form:options items="${fns:getDictList('install_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
		</form:select>
		</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店名称</th>
				<th>工程模式</th>
				<th>安装项名称</th>
				<th>安装项顺序</th>
				<th>安装模式</th>
				<th>项目经理实际开工<br>后第几天申请复尺</th>
				<th>项目经理实际开工<br>后第几天申请安装</th>
				<th>项目经理实际开工<br>后第几天安装</th>
				<th>项目经理实际开工<br>后第几天验收</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>是/否复尺</th>
				<th>是/否展示安装说明</th>
				<th>状态</th>
				<shiro:hasPermission name="installitem:bizProjectInstallItem:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="bizProjectInstallItem">
				<tr>
					<td>${fns:getStoreLabel(bizProjectInstallItem.storeId, '')}</td>
					
						
						
					<td>${fns:getDictLabel(bizProjectInstallItem.projectMode, 'project_mode', '')}</td>
					<td>${bizProjectInstallItem.installItemName}</td>
					<td>${bizProjectInstallItem.installItemSequence}</td>
					<td>${fns:getDictLabel(bizProjectInstallItem.installMode, 'install_mode', '')}</td>
					
					<td>${bizProjectInstallItem.daysPlanChecksize}</td>
					
					<td>${bizProjectInstallItem.workApplyDay}</td>
					<td>${bizProjectInstallItem.workEnterDay}</td>
					<td>${bizProjectInstallItem.workCompleteDay}</td>


					<td><fmt:formatDate
							value="${bizProjectInstallItem.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${bizProjectInstallItem.remarks}</td>
					
					<td>
						<c:if test="${bizProjectInstallItem.isToChecksize=='1'}">
							<span style="color: #00EC00;">是</span>
						</c:if> <c:if test="${bizProjectInstallItem.isToChecksize=='0'}">
							<span style="color: red">否</span>
						</c:if>
					</td>
					<td>
						<c:if test="${bizProjectInstallItem.isShowInstallDescription=='1'}">
							<span style="color: #00EC00;">是</span>
						</c:if> 
						<c:if test="${bizProjectInstallItem.isShowInstallDescription!='1'}">
							<span style="color: red">否</span>
						</c:if>
					</td>
					<td><c:if test="${bizProjectInstallItem.isOn=='1'}">
							<span style="color: #00EC00;">启用</span>
						</c:if> <c:if test="${bizProjectInstallItem.isOn=='0'}">
							<span style="color: red">停止</span>
						</c:if></td>
					<shiro:hasPermission name="installitem:bizProjectInstallItem:edit">
						<td>
							<%-- <a href="${ctx}/installitem/bizProjectInstallItem/form?id=${bizProjectInstallItem.id}">修改</a>  --%>
							<a id="stop" href="${ctx}/installitem/bizProjectInstallItem/delete?id=${bizProjectInstallItem.id}">
								<c:if test="${bizProjectInstallItem.isOn=='1'}">停止</c:if> 
								<c:if test="${bizProjectInstallItem.isOn=='0'}">启用</c:if>
							</a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>