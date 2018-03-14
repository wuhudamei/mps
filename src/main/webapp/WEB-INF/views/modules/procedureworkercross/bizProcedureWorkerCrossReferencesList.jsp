<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工序和工人星级对照管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//如果是非总部，则直接查出任务包
			if(typeof($("#hiddenStoreId").text())!="undefined"&&$("#hiddenStoreId").text()!=""){
				changeStore($("#hiddenStoreId").text());
			}else if($("#storeId").val()!=""){ //如果门店有值，则直接查出任务包
				changeStore($("#storeId").val());
			}
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		

		function changeStore(storeId){
			$('#taskPackageId').attr("disabled",true);
			$("#taskPackageId").parent().find('span.select2-chosen').html("");
			$('#procedureNo').attr("disabled",true);
			$("#procedureNo").parent().find('span.select2-chosen').html("");
			document.getElementById("procedureNo").innerHTML=""; 
			
			//document.getElementById("taskPackageId").innerHTML="<option value='' selected='true'></option>";
			bulidTaskPakageSelect(storeId);
		}
		//根据选择的门店，构造任务包下拉框的选项
		function bulidTaskPakageSelect(storeId){
			$.ajax({
			    url: "${ctx}/procedureworkercross/bizProcedureWorkerCrossReferences/selectTaskPakgDictByStoreId",    //请求的url地址
			    dataType: "json",   //返回格式为json
			    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
			    data: { 
			    	"storeId": storeId
			    	},    //参数值
			    type: "POST",   //请求方式
			    success: function(req) {
			    	var jsonObj = eval(req);
			    	var htmls = "<option value='' selected='true'></option>";
			    	for (var i = 0; i < jsonObj.length; i++) {
			    		htmls+="<option value='"+jsonObj[i].value+"'>"+jsonObj[i].label+"</option>";
                    }
			    	document.getElementById("taskPackageId").innerHTML=htmls;  
			    	$('#taskPackageId').attr("disabled",false);
			    },
			    complete: function() {
			        //请求完成的处理
			    },
			    error: function() {
			    	 
			    }
			});
		}
		function cleanDisabledFun(){
			$('#procedureNo').attr("disabled",true);
			$('#taskPackageId').attr("disabled",true);
		}
		function changeProcedure(taskPakgId){
			$('#procedureNo').attr("disabled",true);
			$("#procedureNo").parent().find('span.select2-chosen').html("");
			//document.getElementById("procedureNo").innerHTML="<option value='' selected='true'></option>";
			bulidProcedureSelect(taskPakgId);
		}
		//根据选择的门店，构造任务包下拉框的选项
		function bulidProcedureSelect(taskPakgId){
			$.ajax({
			    url: "${ctx}/procedureworkercross/bizProcedureWorkerCrossReferences/selectprocedureDictByTaskPakgId",    //请求的url地址
			    dataType: "json",   //返回格式为json
			    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
			    data: { 
			    	"taskPackageTemplatId": taskPakgId
			    	},    //参数值
			    type: "POST",   //请求方式
			    success: function(req) {
			    	var jsonObj = eval(req);
			    	var htmls = "<option value='' selected='true'></option>";
			    	for (var i = 0; i < jsonObj.length; i++) {
			    		htmls+="<option value='"+jsonObj[i].value+"'>"+jsonObj[i].label+"</option>";
                    }
			    	document.getElementById("procedureNo").innerHTML=htmls;  
			    	$('#procedureNo').attr("disabled",false);
			    },
			    complete: function() {
			        //请求完成的处理
			    },
			    error: function() {
			    	 
			    }
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/procedureworkercross/bizProcedureWorkerCrossReferences/">工序和工人星级对照列表</a></li>
		<shiro:hasPermission name="procedureworkercross:bizProcedureWorkerCrossReferences:edit"><li><a href="${ctx}/procedureworkercross/bizProcedureWorkerCrossReferences/form">工序和工人星级对照添加</a></li></shiro:hasPermission>
	</ul>
	<div style="display: none" id="hiddenStoreId">${hiddenStoreId}</div >
	<form:form id="searchForm" modelAttribute="bizProcedureWorkerCrossReferences" action="${ctx}/procedureworkercross/bizProcedureWorkerCrossReferences/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" onclick="changeStore(this.options[this.selectedIndex].value)" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" onclick="changeStore(this.options[this.selectedIndex].value)" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>任务包：</label>
				<form:select path="taskPackageId" class="input-medium needClear" id="taskPackageId" disabled="true" onclick="changeProcedure(this.options[this.selectedIndex].value)">
					<form:option value="" label=""/>
					
				</form:select>
			</li>
			<li><label>工序编号：</label>
				<form:select path="procedureNo" id="procedureNo" disabled="true" class="input-medium needClear">
					<form:option value="" label=""/>
					
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" onclick="cleanDisabledFun()" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>任务包</th>
				<th>工序编号</th>
				<th>工序名称</th>
				<th>工人组星级</th>
				<th>结算比率</th>
				<shiro:hasPermission name="procedureworkercross:bizProcedureWorkerCrossReferences:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizProcedureWorkerCrossReferences">
			<tr>
				<td><a href="${ctx}/procedureworkercross/bizProcedureWorkerCrossReferences/form?id=${bizProcedureWorkerCrossReferences.id}">
					${fns:getStoreLabel(bizProcedureWorkerCrossReferences.storeId, '')}
				</a></td>
				<td>
					${fns:getTaskPackageTemplateLabel(bizProcedureWorkerCrossReferences.taskPackageId, '')}
				</td>
				<td>
					${bizProcedureWorkerCrossReferences.procedureNo}
				</td>
				<td>
					${fns:getProcedureLabel(bizProcedureWorkerCrossReferences.procedureNo, '')}
				</td>
				<td>
					${fns:getDictLabel(bizProcedureWorkerCrossReferences.workerGroupStar, 'emp_star', '')}
				</td>
				<td>
					${bizProcedureWorkerCrossReferences.settlementRate}%
				</td>
				<shiro:hasPermission name="procedureworkercross:bizProcedureWorkerCrossReferences:edit"><td>
    				<a href="${ctx}/procedureworkercross/bizProcedureWorkerCrossReferences/form?id=${bizProcedureWorkerCrossReferences.id}">修改</a>
					<a href="${ctx}/procedureworkercross/bizProcedureWorkerCrossReferences/delete?id=${bizProcedureWorkerCrossReferences.id}" onclick="return confirmx('确认要删除该工序和工人星级对照吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>