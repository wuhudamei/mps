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
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/constructionschedule/bizConstructionSchedule/list">工程进度节点列表</a></li>
		<shiro:hasPermission name="constructionschedule:bizConstructionSchedule:edit"><li><a href="${ctx}/constructionschedule/bizConstructionSchedule/form">工程进度节点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizConstructionSchedule" action="${ctx}/constructionschedule/bizConstructionSchedule/list1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
			<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium needClear">
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium needClear">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
				</form:select>
			</c:if>
			</li>
			<li><label>是否老房：</label>
				<form:select path="isOldHouse" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>是否老房</th>
				<th>进度节点名称</th>
				<th>进度节点顺序</th>
				<th>正常完工天数</th>
				<th>最晚完工天数</th>
				<th>备注信息</th>
				<th>状态</th>
				<shiro:hasPermission name="constructionschedule:bizConstructionSchedule:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizConstructionSchedule">
			<tr>
				<td>
					${fns:getStoreLabel(bizConstructionSchedule.storeId, '')}
			</td>
				<td>${fns:getDictLabel(bizConstructionSchedule.projectMode, 'project_mode', '')}
				
				</td>
				
				
				<td>
					${fns:getDictLabel(bizConstructionSchedule.isOldHouse, 'biz_is_new_house', '')}
				</td>
				<td>
					${bizConstructionSchedule.constructionScheduleName}
				</td>
				<td>
					${bizConstructionSchedule.sort}
				</td>
				<td>
					${bizConstructionSchedule.normalCompletionDays}
				</td>
				<td>
					${bizConstructionSchedule.lateCompletionDays}
				</td>
				<td>
					${bizConstructionSchedule.remarks}
				</td>
				<td>
					${fns:getDictLabel(bizConstructionSchedule.isEnable, 'biz_enable_status', '')}
				</td>
				<shiro:hasPermission name="constructionschedule:bizConstructionSchedule:edit"><td>
    				<a href="${ctx}/constructionschedule/bizConstructionSchedule/form?id=${bizConstructionSchedule.id}">修改</a>
					<a href="${ctx}/constructionschedule/bizConstructionSchedule/delete?id=${bizConstructionSchedule.id}&isEnable=<c:if test="${bizConstructionSchedule.isEnable==1}">
							0
						</c:if>
						<c:if test="${bizConstructionSchedule.isEnable==0}">
							1
						</c:if>" onclick="return confirmx('确认要<c:if test="${bizConstructionSchedule.isEnable==1}">
								停用
								</c:if>
								<c:if test="${bizConstructionSchedule.isEnable==0}">
									启用
								</c:if>该工序吗？', this.href)">

                        <c:if test="${bizConstructionSchedule.isEnable==1}">
							停用
						</c:if>
						<c:if test="${bizConstructionSchedule.isEnable==0}">
							启用
						</c:if></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>