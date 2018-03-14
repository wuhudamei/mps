<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工序和工人星级对照管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					if($("#settlementRate").val()>999){
						$("#messageBox").text("结算比率不能超过999！");
						alert("结算比率不能超过999！");
					}else{
						loading('正在提交，请稍等...');
						form.submit();
					}
					
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			//如果是非总部，则直接查出任务包
			if(typeof($("#hiddenStoreId").text())!="undefined"&&$("#hiddenStoreId").text()!=""&&$("#taskPackageId").val()==""){
				changeStore($("#hiddenStoreId").text());
			}
			
		});
		
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
		<li><a href="${ctx}/procedureworkercross/bizProcedureWorkerCrossReferences/">工序和工人星级对照列表</a></li>
		<li class="active"><a href="${ctx}/procedureworkercross/bizProcedureWorkerCrossReferences/form?id=${bizProcedureWorkerCrossReferences.id}">工序和工人星级对照<shiro:hasPermission name="procedureworkercross:bizProcedureWorkerCrossReferences:edit">${not empty bizProcedureWorkerCrossReferences.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="procedureworkercross:bizProcedureWorkerCrossReferences:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<div style="display: none" id="hiddenStoreId">${hiddenStoreId}</div >
	<form:form id="inputForm" modelAttribute="bizProcedureWorkerCrossReferences" onsubmit="onFormSubmit(this)" action="${ctx}/procedureworkercross/bizProcedureWorkerCrossReferences/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			
			<div class="controls">
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-xlarge required" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" onclick="changeStore(this.options[this.selectedIndex].value)" class="input-xlarge required">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</c:if>
			</div>
			
		</div>
		<div class="control-group">
			<label class="control-label">任务包：</label>
			<div class="controls">
				<form:select path="taskPackageId" id="taskPackageId" disabled="true" onclick="changeProcedure(this.options[this.selectedIndex].value)"  class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getTaskList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工序编号：</label>
			<div class="controls">
				<form:select path="procedureNo" id="procedureNo" disabled="true" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getProcedureList()}" itemLabel="procedureName" itemValue="procedureNo" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工人组星级：</label>
			<div class="controls">
				<form:select path="workerGroupStar" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('emp_star')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算比率：</label>
			<div class="controls">
				<form:input path="settlementRate" htmlEscape="false" maxlength="6"  class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="procedureworkercross:bizProcedureWorkerCrossReferences:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>