<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材管理</title>
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
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mainmaterials/bizMainMaterials/mainMaterialsList">主材管理列表</a></li>
		<li class="active"><a href="${ctx}/mainmaterials/bizMainMaterials/form?id=${bizMainMaterials.id}">主材管理<shiro:hasPermission name="mainmaterials:bizMainMaterials:edit">${not empty bizMainMaterials.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mainmaterials:bizMainMaterials:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizMainMaterials" onsubmit="onFormSubmit(this)" action="${ctx}/mainmaterials/bizMainMaterials/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-xlarge required" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-xlarge required">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料编号：</label>
			<div class="controls">
				<form:input path="mainMaterialsNo" htmlEscape="false" maxlength="100" class="input-xlarge " placeholder="系统自动生成" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料名称：</label>
			<div class="controls">
				<form:input path="mainMaterialsName" htmlEscape="false" maxlength="100" class="input-xlarge " required="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">常用工种：</label>
			<div class="controls">
				<form:select path="empWorkType" class="input-xlarge " required="required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('emp_work_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料类别：</label>
			<div class="controls">
				<form:select path="categoryId" class="input-xlarge" required="required">
					<form:option value="" label=""/>
					<form:options items="${fns:getMainMaterialCategoryList()}" itemLabel="categoryName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格：</label>
			<div class="controls">
				<form:input path="specifications" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<form:select path="measurementUnit" class="input-xlarge required" required="required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_material_unit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge " required="required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">品牌：</label>
			<div class="controls">
				<form:input path="brands" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		
		</div>
		<div class="control-group">
			<label class="control-label">排序值：</label>
			<div class="controls">
				<form:input path="sortIndex" htmlEscape="false" maxlength="255" class="input-xlarge "    onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  
    onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否计算标准数量：</label>
			<div class="controls">
				<input type="radio" name="isCounted" value="1" <c:if test="${empty bizMainMaterials.id}">checked="checked" </c:if>
					                                                  <c:if test="${not empty bizMainMaterials.id}"><c:if test="${bizMainMaterials.isCounted eq '1'}">checked="checked"</c:if></c:if>
						>是&nbsp;&nbsp;
				<input type="radio" name="isCounted" value="0" <c:if test="${bizMainMaterials.isCounted eq '0'}">checked="checked" </c:if>>否
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主材图片路径：</label>
			<div class="controls">
				<form:hidden id="picUrl" path="picUrl" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="picUrl" type="images" uploadPath="/upload/materials" selectMultiple="false"/>
			</div>
		</div>
        <%-- <div class="control-group">
        	<div id="headPic" style="width: 200px; height: 200px; margin-left: 100px; text-align: center; border: solid 1px;">
				<img alt="" src="${bizMainMaterials.picUrl}">
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="mainmaterials:bizMainMaterials:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>