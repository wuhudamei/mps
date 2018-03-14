<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理考勤基础设置管理</title>
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
		<li class="active"><a href="${ctx}/bizpmattendcnfg/bizPmAttendCnfg/">项目经理考勤基础设置列表</a></li>
		<shiro:hasPermission name="bizpmattendcnfg:bizPmAttendCnfg:edit"><li><a href="${ctx}/bizpmattendcnfg/bizPmAttendCnfg/form">项目经理考勤基础设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmAttendCnfg" action="${ctx}/bizpmattendcnfg/bizPmAttendCnfg/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
			<%-- 	<label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
						<form:option value=""></form:option>
						<c:forEach items="${storeList}" var = "store">
							<form:option value="${store.value }">${store.label }</form:option>
						</c:forEach>
				</form:select> --%>
				<label>门店：</label>
				<form:select path="storeId"  class="input-medium needClear" >
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			</li>
			<li><label>工程模式：</label>
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>		
			</li>
			<li><label>生效月份 ：</label>
				<%-- <input name="effectMonth" id="effectMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizPmAttendCnfg.effectMonth}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/> --%>
					<input name="effectMonth" id="effectMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${bizPmAttendCnfg.effectMonth}"
					
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
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
				<th>生效月份</th>
				<th>启用状态</th>
				<shiro:hasPermission name="bizpmattendcnfg:bizPmAttendCnfg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmAttendCnfg">
			<tr>
				<td>
					${fns:getStoreLabel(bizPmAttendCnfg.storeId, '')}
				</td>

				<td>
					${fns:getDictLabel(bizPmAttendCnfg.projectMode, 'project_mode', '')}
				</td>
				<td>
					${bizPmAttendCnfg.effectMonth }
				</td>
				<td>
					<c:if test="${bizPmAttendCnfg.isEnabled==0 }">停用</c:if>
					<c:if test="${bizPmAttendCnfg.isEnabled==1 }">启用</c:if>
				</td>
				<shiro:hasPermission name="bizpmattendcnfg:bizPmAttendCnfg:edit"><td>
					<c:if test="${bizPmAttendCnfg.isEnabled==0 }">
						<a href="${ctx}/bizpmattendcnfg/bizPmAttendCnfg/updateIsEnabledById?id=${bizPmAttendCnfg.id}&isEnabled=1">启用</a>
				   </c:if>
					<c:if test="${bizPmAttendCnfg.isEnabled==1 }">
						<a href="${ctx}/bizpmattendcnfg/bizPmAttendCnfg/updateIsEnabledById?id=${bizPmAttendCnfg.id}&isEnabled=0">停用</a>
				   </c:if>
					<c:if test="${bizPmAttendCnfg.isEnabled==0 }">
    				<a href="${ctx}/bizpmattendcnfg/bizPmAttendCnfg/form?id=${bizPmAttendCnfg.id}">修改</a>
					</c:if>
					<a href="${ctx}/bizpmattendcnfg/bizPmAttendCnfg/delete?id=${bizPmAttendCnfg.id}" onclick="return confirmx('确认要删除该项目经理考勤基础设置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>