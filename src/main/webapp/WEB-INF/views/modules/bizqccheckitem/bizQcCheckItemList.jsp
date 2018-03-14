<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>检查项管理</title>
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
		
		function checkKind(){
			var store = $("#storeId").val();
			var projectMode = $("#projectMode").val();
			$.ajax({
				url:"${ctx}/bizqccheckitem/bizQcCheckItem/checkKind",
				type : "post",
				data:{
					storeId : store,
					projectMode:projectMode
					},
				success : function(data){
					var htmls = "<option value=''></option>";//<option value='' selected='true'></option>
			   		for(var i = 0; i < data.length; i++){
			   			htmls += "<option value='"+data[i].id+"'>"+data[i].qcCheckKindName+"</option>";
			   		}
			   		$("#qcCheckKindId").html(htmls);
			   		var htmls = "";
				  }
			});
			
			
			
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
		<li class="active"><a href="${ctx}/bizqccheckitem/bizQcCheckItem/list">检查项列表</a></li>
		<shiro:hasPermission name="bizqccheckitem:bizQcCheckItem:edit"><li><a href="${ctx}/bizqccheckitem/bizQcCheckItem/form">检查项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizQcCheckItem" action="${ctx}/bizqccheckitem/bizQcCheckItem/itemList" method="post" class="breadcrumb form-search">
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
					<form:select path="storeId" class="input-medium needClear" onchange="checkKind()">
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
					<form:select path="projectMode" class="input-medium needClear" onchange="checkKind()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>检查分类：</label>
				<form:select path="qcCheckKindId" class="input-medium" id="qcCheckKindId" name="qcCheckKindId">
					<form:option value="" label=""></form:option>
				</form:select>
			</li>
			<li><label>检查项名称：</label>
				<form:input path="qcCheckItemName" htmlEscape="false" maxlength="16" class="input-medium"/>
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
				<th>检查分类</th>
				<th>检查项</th>
				<th>处罚项目经理金额</th>
				<th>扣项目经理分值</th>
				<th>处罚工人金额</th>
				<th>扣工人分值</th>
				<th>处罚质检员金额</th>
				<th>扣质检员分值</th>
				<th>项目分数</th>
				<th>是否必检</th>
				<th>状态</th>
				<shiro:hasPermission name="bizqccheckitem:bizQcCheckItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizQcCheckItem">
			<tr>
				<td><a href="${ctx}/bizqccheckitem/bizQcCheckItem/form?id=${bizQcCheckItem.id}">
					${fns:getStoreLabel(bizQcCheckItem.storeId, '')}
				</a></td>
				<td>
					${fns:getDictLabel(bizQcCheckItem.projectMode, 'project_mode', '')}
				</td>
				<td>
					${bizQcCheckItem.qcCheckKind}
				</td>
				<td>
					${bizQcCheckItem.qcCheckItemName}
				</td>
				<td>
					${bizQcCheckItem.punishAmount}
				</td>
				<td>
					${bizQcCheckItem.pmPunishScore}
				</td>
				<td>
					${bizQcCheckItem.workerPunishAmount}
				</td>
				<td>
					${bizQcCheckItem.workerPunishScore}
				</td>
				<td>
					${bizQcCheckItem.qcPunishAmount}
				</td>
				<td>
					${bizQcCheckItem.qcPunishScore}
				</td>
				<td>
					${bizQcCheckItem.itemScore}
				</td>
				<td>
					${fns:getDictLabel(bizQcCheckItem.isRequired, 'yes_no', '')}
				</td>
				<td>
					<c:if test="${bizQcCheckItem.status=='1' }"><span style="color:#00EC00;">启用</span></c:if>
					<c:if test="${bizQcCheckItem.status=='0' }"><span style="color:red;">停用</span></c:if>
				</td>
				<shiro:hasPermission name="bizqccheckitem:bizQcCheckItem:edit"><td>
    				<a href="${ctx}/bizqccheckitem/bizQcCheckItem/form?id=${bizQcCheckItem.id}">修改</a>
					<a href="${ctx}/bizqccheckitem/bizQcCheckItem/delete?id=${bizQcCheckItem.id}">
						<c:if test="${bizQcCheckItem.status=='0' }"><span style="color:#00EC00;">启用</span></c:if>
						<c:if test="${bizQcCheckItem.status=='1' }"><span style="color:red;">停用</span></c:if>
					</a>
					<a href="${ctx}/illegalmodality/illegalModality/list?checkItemId=${bizQcCheckItem.id}&projectMode=${bizQcCheckItem.projectMode}">违规形式</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		
		window.onload = function(){
			var store = $("#storeId").val();
			var projectMode = $("#projectMode").val();
			$.ajax({
				url:"${ctx}/bizqccheckitem/bizQcCheckItem/checkKind",
				type : "post",
				data:{
					storeId : store,
					projectMode:projectMode
					},
				success : function(data){
					var htmls = "<option value=''></option>";;//<option value='' selected='true'></option>
			   		for(var i = 0; i < data.length; i++){
			   			htmls += "<option value='"+data[i].id+"'>"+data[i].qcCheckKindName+"</option>";
			   		}
			   		$("#qcCheckKindId").html(htmls);
			   		var htmls = "";
				  }
			});
		}
	
	
	
	
	
	</script>
</body>
</html>