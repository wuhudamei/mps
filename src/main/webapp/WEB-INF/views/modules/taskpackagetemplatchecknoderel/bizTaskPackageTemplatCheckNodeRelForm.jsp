<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单付款尾款节点设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getNodeByStoreid()
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
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
		});
		
		
		function getNodeByStoreid()
			{
			//alert( $("#storeId").val());
			$("#qcCheckNodeId").html('');
			//$("#empId").attr("disabled","true");
			 $.ajax({
			        type : 'POST',
			        dataType : 'json',
			        url : '${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/nodeListByStoreId',
			        data : {
			            'storeid' : $("#storeId").val(),
			            'projectMode' : $("#projectMode").val(),
			        },
			        success : function(data) {
			            var html = "";
			           // alert(data.length);
			            for (var i = 0; i < data.length; i++) {
			            	//alert($("#qcCheckNodeId").val());
			            	var sec = "";
			            	if($("#qcCheckNodeIdStoreId").val() == data[i].value){
			            		//alert("dd");
			            		sec = "selected='selected'";
			            		//alert(data[i].label);
			            	}
			            	html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
			            }
			            html+="";
			           	//alert(html);
			           // alert("dd");
			            
			            $("#qcCheckNodeId").append(html);
			           // $("#qcCheckNodeId").attr("disabled","");
			        }
			    })
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/">付款单付款尾款节点设置列表</a></li>
		<li class="active"><a href="${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/form?id=${bizTaskPackageTemplatCheckNodeRel.id}">付款单付款尾款节点设置<shiro:hasPermission name="taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:edit">${not empty bizTaskPackageTemplatCheckNodeRel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizTaskPackageTemplatCheckNodeRel" action="${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" id="qcCheckNodeIdStoreId" value="${bizTaskPackageTemplatCheckNodeRel.qcCheckNodeId }"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
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
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
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
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">任务包名称：</label>
			<div class="controls">
				<form:input path="packageName" htmlEscape="false" maxlength="11" readonly="true" class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">尾款付款约检节点：</label>
			<div class="controls">
				<form:select path="qcCheckNodeId" id ="qcCheckNodeId" name="qcCheckNodeId" class="input-xlarge required">
					<form:option value="${bizTaskPackageTemplatCheckNodeRel.qcCheckNodeId }" label="${bizTaskPackageTemplatCheckNodeRel.qcCheckNodeName }" />
					<%-- <form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态 ：</label>
			<div class="controls">
				<%-- <c:if test="${bizTaskPackageTemplatCheckNodeRel.status}">
					<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge " />
				</c:if> --%>
				<form:select path="status" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>