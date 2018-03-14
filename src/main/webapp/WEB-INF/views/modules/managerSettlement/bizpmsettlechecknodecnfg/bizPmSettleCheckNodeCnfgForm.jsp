<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理结算关联约检节点设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var projectMode  ="${bizPmSettleCheckNodeCnfg.id}"
				var readOnly = "${readOnly}"
				//如果是产业或者是传统的人,  就不允许修改
					if ("1" == readOnly) {
						$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
						$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
					}
					if ("2" == readOnly) {
						$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
						$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
					}
					if("4" == readOnly){
						$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
						$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
					}
				//新增
				if ("" == projectMode) {
					//如果不是产业或者是传统的人 ,默认传统,并且可以修改
					if ("1" != readOnly && "2" != readOnly && "4" != readOnly) {
						$(":radio[name='projectMode'][value='" + 2 + "']").prop("checked", "checked");
					} else {
						//如果是产业或者是传统的人   工程模式也一样,并且不能修改
						$(":radio[name='projectMode'][value='" + readOnly + "']").prop("checked", "checked");
						if ("1" == readOnly) {
							$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
							$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
						}
						if ("2" == readOnly) {
							$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
							$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
						}
						if ("4" == readOnly) {
							$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
							$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
						}
					}
					$(":radio[name='isEnabled'][value='" + 1 + "']").prop("checked", "checked");
					//修改
				}
				//不做动作, 保证回显, 上面也保证readOnly
				
				
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
		
		function checkNode(){
			$("#qcCheckNodeId").val("");
			var store = $("#storeId").val();
			var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
			if (store < 1) {
				return;
			}
			$.ajax({
				url:"${ctx}/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/checkNode",
				type : "post",
				data:{
					storeId : store,
					projectMode:projectMode
					},
				success : function(data){
					var htmls = "<option value=''></option>";//<option value='' selected='true'></option>
			   		for(var i = 0; i < data.length; i++){
			   			htmls += "<option value='"+data[i].id+"'>"+data[i].qcCheckNodeName+"</option>";
			   		}
			   		$("#qcCheckNodeId").html(htmls);
			   		var htmls = "";
				  }
			});
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/list">项目经理结算关联约检节点设置列表</a></li>
		<li class="active"><a href="${ctx}/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/form?id=${bizPmSettleCheckNodeCnfg.id}">项目经理结算关联约检节点设置<shiro:hasPermission name="bizpmsettlechecknodecnfg:bizPmSettleCheckNodeCnfg:edit">${not empty bizPmSettleCheckNodeCnfg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizpmsettlechecknodecnfg:bizPmSettleCheckNodeCnfg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPmSettleCheckNodeCnfg" action="${ctx}/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required" onchange="checkNode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" onchange="checkNode()"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属结算人员：</label>
			<div class="controls">
				<form:select path="settleRole" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('settle_role')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目经理结算阶段：</label>
			<div class="controls">
				<form:select path="settleNode" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('settle_node')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联的约检节点：</label>
			<div class="controls">
				<form:select path="qcCheckNodeId" class="input-xlarge required">
					<form:option value="${bizPmSettleCheckNodeCnfg.qcCheckNodeId }">${bizPmSettleCheckNodeCnfg.qcCheckNodeName }</form:option>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizpmsettlechecknodecnfg:bizPmSettleCheckNodeCnfg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script type="text/javascript">
		
		window.onload = function(){
			
			var store = $("#storeId").val();
			var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
			var qcCheckNodeId = $("#qcCheckNodeId").val();
			if (store < 1) {
				return;
			}
			$.ajax({
				url:"${ctx}/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/checkNodeTwo",
				type : "post",
				data:{
					storeId : store,
					projectMode:projectMode,
					qcCheckNodeId:qcCheckNodeId
					},
				success : function(data){
					var htmls = "";//<option value='' selected='true'></option>
			   		for(var i = 0; i < data.length; i++){
			   			htmls += "<option value='"+data[i].id+"'>"+data[i].qcCheckNodeName+"</option>";
			   		}
			   		$("#qcCheckNodeId").append(htmls);
			   		var htmls = "";
				  }
			});
		}
	</script>
</body>
</html>