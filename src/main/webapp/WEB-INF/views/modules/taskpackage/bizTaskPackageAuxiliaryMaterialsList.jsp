<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务包辅料对照表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			init();
		});

		function init(){
			var storeId = $("#storeId").val();
			var projectMode = $("#projectMode").val();
			if(storeId == undefined || storeId == null || storeId == ""){
				return;
			}
			getTaskPackageName(storeId,projectMode);
		}

		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function getTaskPackageName(storeId,projectMode){
			$.post("${ctx}/taskpackage/bizTaskPackageTemplat/taskListByStoreIdAndProjectMode",{storeId:storeId,projectMode:projectMode},function(data){
				var options = "";
				if(data.length > 0){
					options = options + "<option></option>";
				}
				for(var i=0;i<data.length;i++){
					var sel="";
					if(data[i].value == '${bizTaskPackageAuxiliaryMaterials.bizTaskPackageTemplatId}'){
						$("#s2id_bizTaskPackageTemplatId").find(".select2-chosen").html(data[i].label);
						sel = "selected='selected'";
					}
					options = options + "<option value='"+data[i].value+"' "+sel+">"+data[i].label+"</option>";
				}
				$("#bizTaskPackageTemplatId").html(options);
			});
		}

		function storeIdSelectChange(){
			$("#s2id_bizTaskPackageTemplatId").find(".select2-chosen").html("");
			var storeId = $("#storeId").val();
			var projectMode = $("#projectMode").val();
			if(storeId == undefined || storeId == null || storeId == ""){
				return;
			}
			if(projectMode == undefined || projectMode == null || projectMode == ""){
				return;
			}
			getTaskPackageName(storeId,projectMode);
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
		<li class="active"><a href="${ctx}/taskpackage/bizTaskPackageAuxiliaryMaterials/">任务包辅料对照表列表</a></li>
		<shiro:hasPermission name="taskpackage:bizTaskPackageAuxiliaryMaterials:edit"><li><a href="${ctx}/taskpackage/bizTaskPackageAuxiliaryMaterials/form">任务包辅料对照表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizTaskPackageAuxiliaryMaterials" action="${ctx}/taskpackage/bizTaskPackageAuxiliaryMaterials/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" id="storeId" onchange="storeIdSelectChange()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="storeIdSelectChange()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" id="projectMode" onchange="storeIdSelectChange()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" id="projectMode" onchange="storeIdSelectChange()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>任务包：</label>
				<select name="bizTaskPackageTemplatId" class="input-medium" id="bizTaskPackageTemplatId">
					<%--<form:option value="" label=""/>
					<form:options items="${fns:getTaskListByNowStoreId()}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				</select>
			</li>
			
			<li>
			<label>SKU编号：</label>
			<form:input path="bizAuxiliaryMaterialsNo" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</li>
			
			<li>
			<label>SKU名称：</label>
			<form:input path="bizAuxiliaryMaterialsName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
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
				<th>任务包</th>
				<th>SKU</th>
				<th>SKU名称</th>
				<shiro:hasPermission name="taskpackage:bizTaskPackageAuxiliaryMaterials:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizTaskPackageAuxiliaryMaterials">
			<tr>
				<td><%-- <a href="${ctx}/taskpackage/bizTaskPackageAuxiliaryMaterials/form?id=${bizTaskPackageAuxiliaryMaterials.id}">
					${fns:getStoreLabel(bizTaskPackageAuxiliaryMaterials.storeId, '')}
				</a> --%>${fns:getStoreLabel(bizTaskPackageAuxiliaryMaterials.storeId, '')}</td>
				<td>${fns:getDictLabel(bizTaskPackageAuxiliaryMaterials.projectMode, 'package_project_mode', '')}</td>
				<td>
					${fns:getTaskPackageTemplateLabel(bizTaskPackageAuxiliaryMaterials.bizTaskPackageTemplatId, '')}
				</td>
				<td>
					${bizTaskPackageAuxiliaryMaterials.bizAuxiliaryMaterialsNo}
				</td>
				<td>
					<%-- ${fns:getAuxiliaryMaterialsLabel(bizTaskPackageAuxiliaryMaterials.bizAuxiliaryMaterialsNo, '')} --%>
					${bizTaskPackageAuxiliaryMaterials.bizAuxiliaryMaterialsName}
				</td>
				<shiro:hasPermission name="taskpackage:bizTaskPackageAuxiliaryMaterials:edit"><td>
    				<a href="${ctx}/taskpackage/bizTaskPackageAuxiliaryMaterials/form?id=${bizTaskPackageAuxiliaryMaterials.id}">修改</a>
					<a href="${ctx}/taskpackage/bizTaskPackageAuxiliaryMaterials/delete?id=${bizTaskPackageAuxiliaryMaterials.id}" onclick="return confirmx('确认要删除该任务包辅料对照表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>