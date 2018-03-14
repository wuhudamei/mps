<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>施工项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//使用平台回显
			var checkeds = '${projectIntem.usingPlatform}';
			var v1 = checkeds.replace(/\s/g,'')
			var checkArray =v1.split(",");
			for(var i=0;i<checkArray.length;i++){
				$("input[value="+checkArray[i]+"][name=usingPlatform]").attr("checked",true);
		 	}
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
			 //清空使用平台
			 $.each($("input[type = checkbox]"),function(){
				 $(this).attr("checked",false);
			 });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/projectitem/projectIntem/">施工项列表</a></li>
		<shiro:hasPermission name="projectitem:projectIntem:edit"><li><a href="${ctx}/projectitem/projectIntem/form">施工项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="projectIntem" action="${ctx}/projectitem/projectIntem/list2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
		<li><label>施工项名称：</label>
				<form:input path="projectIntemName" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			
			
			<li><label>施工项分类：</label>
				<form:select path="projectIntemTypeId" class="input-medium needClear">
					<form:option value="" label=""/>
					<c:forEach items="${projectItemTypeList }" var="type">
						<form:option value="${type.projectItemTypeId }">${type.projectIntemTypeName }</form:option>
					</c:forEach>
				</form:select>
			</li>
			
			<li><label>施工项类型：</label>
				<form:select path="projectIntemMold" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_item_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					<form:option value="1,2" label="减项/增项"/>
				</form:select>
			</li>
			
			<li><label>套餐类型：</label>
				<form:select path="groupType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('group_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>使用平台：</label>
				<form:checkboxes class="needClear" path="usingPlatform" items="${fns:getDictList('using_platform')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>施工项编码</th>
				<th>施工项名称</th>
				<th>施工项分类</th>
				<th>施工项计量单位</th>
				<th>施工项类型</th>
				<th>套餐类型</th>
				<th>施工项详情</th>
				<th>状态</th>
				<shiro:hasPermission name="projectitem:projectIntem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="projectIntem">
			<tr>
				<td>
					${projectIntem.projectIntemCode}
				</td>
				<td>
					${projectIntem.projectIntemName}
				</td>
				<td>
					${projectIntem.projectIntemTypeName}
				</td>
				<td>
					${projectIntem.projectIntemUnit}
				</td>
				<td>
					<c:if test="${projectIntem.groupType=='2' }">套餐外</c:if>
					<c:if test="${projectIntem.groupType=='1' }">套餐内</c:if>
				</td>
				<td>
					<c:if test="${projectIntem.projectIntemMold=='2' }">减项</c:if>
					<c:if test="${projectIntem.projectIntemMold=='1' }">增项</c:if>
					<c:if test="${projectIntem.projectIntemMold=='1,2' }">减项/增项</c:if>
				</td>
				<td>
					${projectIntem.projectIntemDetail}
				</td>
				<td>
				<c:if test="${projectIntem.status=='1' }"><span style="color:#00EC00;">启用</span></c:if>
					<c:if test="${projectIntem.status=='0' }"><span style="color:red;">停用</span></c:if>
				</td>
				
				<shiro:hasPermission name="projectitem:projectIntem:edit"><td>
    				<a href="${ctx}/projectitem/projectIntem/form?id=${projectIntem.projectItemId}">修改基本信息</a>
    				<a href="${ctx}/projectitemprice/projectItemPrice/list?projectIntemId=${projectIntem.projectItemId}">修改价格</a>
    			
    			
    				<c:if test="${projectIntem.status=='0' }">
    				<a href="${ctx}/projectitem/projectIntem/delete?id=${projectIntem.projectItemId}&status=1" onclick="return confirmx('确认要启用该施工项吗？', this.href)">
					<span style="color:#00EC00;">启用</span></a>
					</c:if>
					
					
						
					<c:if test="${projectIntem.status=='1' }">
					<a href="${ctx}/projectitem/projectIntem/delete?id=${projectIntem.projectItemId}&status=0" onclick="return confirmx('确认要停用该施工项吗？', this.href)">
					<span style="color:red;">停用</span>
					</a>
					</c:if>
    				
    				
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>