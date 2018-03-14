<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单付款尾款节点设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//getTaskpackageByStoreid();
			//$("#name").focus();
			getTaskpackageByStoreid();
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
		
		function getTaskpackageByStoreid(){
			if($("#storeId").val() == null || $("#storeId").val() == '' || $("#projectMode").val() == null || $("#projectMode").val() == ''){
				return;
			}
			$.ajax({
				type:"POST",
				dataType:'json',
				url:'${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/taskpackageList',
				data:{
					'storeid':$("#storeId").val(),
					'projectMode':$("#projectMode").val(),
				},
				success:function(data){
					//alert("aaaaaaaaaa");
					$("#div1").html("");
					var html = "";
					var html1 = "";
					var taskpackages = data[0];
					var nodes = data[1];
					//alert(taskpackages);
					//alert(nodes);
					for(var j=0;j<nodes.length;j++){
				 		html1 += "<option value='" + nodes[j].value +"'>" + nodes[j].label + "</option>"
			 		}
				 	for(var i=0;i<taskpackages.length;i++){
						html +='<label style="width:150px;text-align:center;line-height:25px;margin-bottom:5px;">任务包名称：</label>'
							+'<input style="line-height:25px;margin-bottom:5px;" htmlEscape="false" maxlength="11" value='+taskpackages[i].templatName+' class="input-xlarge" readonly="readonly"/>'
							+'<input type="hidden" value='+taskpackages[i].id+' name="ids" id="templateId"/>'
							+'<label style="width:150px;text-align:center;line-height:25px;margin-bottom:5px;">尾款付款约检节点：</label>'
							+'<select style="line-height:25px;margin-bottom:5px;" id ="qcCheckNodeId" name="qcCheckNodeIds" class="input-xlarge required">'
							+html1
							+'</select>'
							+'<input style="line-height:25px;margin-bottom:5px;" type="radio" name="status'+i+'" value="1" checked="checked"/>启用'
							+'<input style="line-height:25px;margin-bottom:5px;" type="radio" name="status'+i+'" value="0" />停用 '
							+'</br>'
				 		
					}
					//alert(html1);
					$("#div1").append(html);
					//console.log(data.length);
				}
			});
		} 
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="bizTaskPackageTemplatCheckNodeRel" action="${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/add" method="post" class="form-horizontal">
		<div class="control-group" id ="store" style="margin:0 auto;border:none;">
			<label class="control-label" style="text-align: center;">门店：</label>
			<div class="controls">
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-xlarge" disabled="true" onclick="getTaskpackageByStoreid()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-xlarge needClear" onclick="getTaskpackageByStoreid()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group" style="margin:0 auto;border:none;">
			<label class="control-label" style="text-align: center;">工程模式：</label>
			<div class="controls">
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-xlarge" disabled="true" onclick="getTaskpackageByStoreid()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-xlarge needClear" onclick="getTaskpackageByStoreid()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</div>
		</div>
		
		<div class="control-group" id="div1">
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
<script>
</script>
</body>
</html>