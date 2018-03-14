<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务包辅料对照表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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

			init();
		});

		function init(){
			var storeId = $("#storeId").val();
			var projectMode = $("#projectMode").val();
			if(storeId == undefined || storeId == null || storeId == ""){
				return;
			}

			showTaskPackage(storeId,projectMode);
			showSKU(storeId);
		}

		function showTaskPackage(storeId,projectMode){
			$.post("${ctx}/taskpackage/bizTaskPackageTemplat/taskListByStoreIdAndProjectMode",{storeId:storeId,projectMode:projectMode},function(data){
				var options = "";
				if(data.length > 0){
					options = options + "<option></option>";
				}
				for(var i=0;i<data.length;i++){
					var sel = "";
					if(data[i].value == '${bizTaskPackageAuxiliaryMaterials.bizTaskPackageTemplatId}'){
						$("#s2id_bizTaskPackageTemplatId").find(".select2-chosen").html(data[i].label);
						sel = "selected='selected'";
					}
					options = options + "<option value='"+data[i].value+"' "+sel+">"+data[i].label+"</option>";
				}
				$("#bizTaskPackageTemplatId").html(options);
			});
		}

		function showSKU(shoreId){
			$.post("${ctx}/auxiliarymaterials/bizAuxiliaryMaterials/getSKUNo",{storeId:shoreId},function(data){
				var options = "";
				if(data.length > 0){
					options = options + "<option></option>";
				}
				for(var i=0;i<data.length;i++){
					var sel = "";
					if(data[i].auxiliaryMaterialsNo == '${bizTaskPackageAuxiliaryMaterials.bizAuxiliaryMaterialsNo}'){
						$("#s2id_bizAuxiliaryMaterialsNo").find(".select2-chosen").html(data[i].auxiliaryMaterialsNo+"     "+data[i].auxiliaryMaterialsName);
						sel = "selected='selected'";
					}
					options = options + "<option value='"+data[i].auxiliaryMaterialsNo+"' "+sel+">"+data[i].auxiliaryMaterialsNo+"     "+data[i].auxiliaryMaterialsName+"</option>";
				}
				$("#bizAuxiliaryMaterialsNo").html(options);
			});
		}

		function storeIdSelectChange(){
			var storeId = $("#storeId").val();
			var projectMode = $("#projectMode").val();
			$("#s2id_bizTaskPackageTemplatId").find(".select2-chosen").html("");
			$("#s2id_bizAuxiliaryMaterialsNo").find(".select2-chosen").html("");
			if(storeId == undefined || storeId == null || storeId == ""){
				$("#bizTaskPackageTemplatId").html("");
				return;
			}
			showTaskPackage(storeId,projectMode);
			showSKU(storeId);
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/taskpackage/bizTaskPackageAuxiliaryMaterials/">任务包辅料对照表列表</a></li>
		<li class="active"><a href="${ctx}/taskpackage/bizTaskPackageAuxiliaryMaterials/form?id=${bizTaskPackageAuxiliaryMaterials.id}">任务包辅料对照表<shiro:hasPermission name="taskpackage:bizTaskPackageAuxiliaryMaterials:edit">${not empty bizTaskPackageAuxiliaryMaterials.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="taskpackage:bizTaskPackageAuxiliaryMaterials:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" onsubmit="onFormSubmit(this)" modelAttribute="bizTaskPackageAuxiliaryMaterials" action="${ctx}/taskpackage/bizTaskPackageAuxiliaryMaterials/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-xlarge required" disabled="true" id="storeId">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-xlarge required" id="storeId" onchange="storeIdSelectChange()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" id="projectMode" >
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
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">任务包：</label>
			<div class="controls">
				<%--<form:select path="bizTaskPackageTemplatId" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getTaskListByNowStoreId()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>--%>
					<select name="bizTaskPackageTemplatId" class="input-xlarge required" id="bizTaskPackageTemplatId">

					</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SKU编号：</label>
			<div class="controls">
				<%--<form:select path="bizAuxiliaryMaterialsNo" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getAuxiliaryMaterialsList()}" itemLabel="dropDisp" itemValue="auxiliaryMaterialsNo" htmlEscape="false"/>
				</form:select>--%>
					<select name="bizAuxiliaryMaterialsNo" class="input-xlarge required" id="bizAuxiliaryMaterialsNo">

					</select>
			</div>
		</div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">remarks：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="form-actions">
			<shiro:hasPermission name="taskpackage:bizTaskPackageAuxiliaryMaterials:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>