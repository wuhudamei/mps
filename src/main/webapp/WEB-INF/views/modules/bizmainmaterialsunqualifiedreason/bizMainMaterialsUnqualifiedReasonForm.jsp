<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材安装项验收不合格原因配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

            var idGlobal  ="${bizProjectInstallItem.id}";
            if("" == idGlobal){
                $(":radio[name='status'][value='" + 1 + "']").prop("checked", "checked");
            }
            findInstallName();
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


        function findInstallName(){
            var html='<option value=""></option>';
            var storeId = $("#storeId").val();
            var projectMode = $("#projectMode").val();
            var installMode = $("#installMode").val();

            if (storeId=="" || projectMode=="" || installMode=="" || undefined==storeId || undefined==projectMode || undefined==installMode) {
                return;
            }
            $.ajax({
                url:"${ctx}/mainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReason/queryInstallItemList",
                type: "post",
                data:{
                    storeId:storeId,
                    projectMode:projectMode,
                    installMode:installMode
                },
				success: function(data){
                    if(null!=data&&data.length>0){
                            for (var v = 0; v < data.length; v++) {
								if('${bizMainMaterialsUnqualifiedReason.mainMaterialsInstallItemId}' == data[v].id){
									$("#s2id_mainMaterialsInstallItemId .select2-chosen").html(data[v].installItemName);
									html = html + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
								}else{
									html = html + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								}
                            }
                        $("#mainMaterialsInstallItemId").html(html);
                    } else {
                        $("#mainMaterialsInstallItemId").html(html);
                    }
                }
            })
        }


	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReason/list">主材安装项验收不合格原因配置列表</a></li>
		<li class="active"><a href="${ctx}/mainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReason/form?id=${bizMainMaterialsUnqualifiedReason.id}">主材安装项验收不合格原因配置<shiro:hasPermission name="mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:edit">${not empty bizMainMaterialsUnqualifiedReason.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizMainMaterialsUnqualifiedReason" action="${ctx}/mainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReason/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required" onchange="findInstallName()">
					<form:option value="" label=""/>
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:select path="projectMode" class="input-xlarge required" onchange="findInstallName()">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">安装模式：</label>
			<div class="controls">
				<form:select path="installMode" class="input-xlarge required" onchange="findInstallName()">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('install_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主材安装项：</label>
			<div class="controls">
				<form:select path="mainMaterialsInstallItemId" class="input-xlarge required">
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">不合格原因：</label>
			<div class="controls">
				<form:input path="unqualifiedReason" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<form:radiobuttons path="status" class="input-xlarge required" items="${fns:getDictList('biz_enable_status')}"
								   itemLabel="label" itemValue="value" htmlEscape="false" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>