<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.pad_btm40{padding-bottom:20px;}
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 30%;margin: 10% auto 0;border-radius: 4px;background: #fff;font-size: 16px;}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 75px;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		/* .maskBtn{display: block;width: 60%;} */
		.maskBtn{background: #0780ec;border-radius: 2px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16.5px;margin: 0 auto;}
    	.maskBtnOne{background: #0780ec;border-radius: 2px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16.5px;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: 2px;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: 2px;float: right;background: #fff;}
		.undis{display:none;}
	</style>
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
			checkNodeList();
			if(!$("#flag").val()==false){
				$("#warnModel").removeClass("undis");
			}else{
			}
		})
			/* //是不是修改
			if($("#qc").val()!='' && $("#qcqc").val()!=''){
			//是修改
		 	//材料下拉表 
				$.ajax({
					    url: "${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/getMaterialsType",    //请求的url地址
					    dataType: "json",   //返回格式为json
					    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
					    data: { 
					   	"type":"bizMaterialsStandardType"
					    	},    //参数值
					    type: "POST",   //请求方式
					    success: function(req) {
					    	var htmls='';
					    	$("#materialType").html('');
					    	for(var i=0;i<req.length;i++){
					    	 	if($("#qc").val()==req[i].value){
					    	 		$("#s2id_materialType .select2-chosen").html(req[i].label);
					    	 		htmls = htmls + "<option value='"+req[i].value+"' selected='selected'>"+req[i].label+"</option>";
					    	 	}else{
					    		htmls+="<option value="+req[i].value+" >"+req[i].label+"</option>";	
					    	 	}
					    	}
					    	$("#materialType").append(htmls);
					    }
					});  
			//节点下拉框
			 var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
				var storeId=$("#storeId").val()
				if(projectMode!=undefined && storeId!='' && storeId!=undefined){
					 $.ajax({
					    url: "${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/getCheckNode",    //请求的url地址
					    dataType: "json",   //返回格式为json
					    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
					    data: { 
					   	"storeId":storeId,
					   	"projectMode":projectMode
					    	},    //参数值
					    type: "POST",   //请求方式
					    success: function(req) {
					    	var htmls='';
					    	$("#qcCheckNodeId").html('');
					    	for(var i=0;i<req.length;i++){
					    		if($("#qcqc").val()==req[i].nodeId){	
					    			$("#s2id_qcCheckNodeId .select2-chosen").html(req[i].nodeName);
					    	 		htmls = htmls + "<option value='"+req[i].nodeId+"' selected='selected'>"+req[i].nodeName+"</option>";
					    		}else{
					    		htmls+="<option value="+req[i].nodeId+" >"+req[i].nodeName+"</option>";	
					    		}
					    	}
					    	$("#qcCheckNodeId").append(htmls);
					    }
					}); 
				}
			}else{//不是修改
			//材料下拉表 添加用
			 $.ajax({
				    url: "${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/getMaterialsType",    //请求的url地址
				    dataType: "json",   //返回格式为json
				    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
				    data: { 
				   	"type":"bizMaterialsStandardType"
				    	},    //参数值
				    type: "POST",   //请求方式
				    success: function(req) {
				    	var htmls='';
				    	$("#materialType").html('');
				    	htmls+="<option></option>";
				    	for(var i=0;i<req.length;i++){
				    	htmls+="<option value="+req[i].value+" >"+req[i].label+"</option>";	
				    	}
				    	$("#materialType").append(htmls);
				    }
				});
			}
		});
		*/
		
		
		//节点下拉表 添加用
		function checkNodeList(){
			
			var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
			var storeId=$("#storeId").val()
			if(projectMode!=undefined && storeId!='' && storeId!=undefined){
				 $.ajax({
				    url: "${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/getCheckNode",    //请求的url地址
				    dataType: "json",   //返回格式为json
				    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
				    data: { 
				   	"storeId":storeId,
				   	"projectMode":projectMode
				    	},    //参数值
				    type: "POST",   //请求方式
				    success: function(req) {
				    	var htmls='';
				    	$("#qcCheckNodeId").html('');
				    	htmls+="<option></option>";
				    	for(var i=0;i<req.length;i++){
				    		
					    	if('${bizMaterialsStandardQcCheckNode.qcCheckNodeId}' == req[i].nodeId){
								$("#s2id_qcCheckNodeId .select2-chosen").html(req[i].nodeName);
								htmls = htmls + "<option value='"+req[i].nodeId+"' selected='selected'>"+req[i].nodeName+"</option>";
							}else{
								htmls = htmls + "<option value='"+req[i].nodeId+"'>"+req[i].nodeName+"</option>";
							}
				    	}
				    	$("#qcCheckNodeId").append(htmls);
				    }
				}); 
			}
		} 
		//有提示框的 确定
		function sureTable(){
			$("#warnModel").addClass("undis");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/list">节点列表</a></li>
		<li class="active"><a href="${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/form?id=${bizMaterialsStandardQcCheckNode.id}">节点<shiro:hasPermission name="bizmaterialsstandardchecknode:bizMaterialsStandardQcCheckNode:edit">${not empty bizMaterialsStandardQcCheckNode.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizmaterialsstandardchecknode:bizMaterialsStandardQcCheckNode:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<input value="${flag}"  id="flag" type="hidden">
	<form:form id="inputForm" modelAttribute="bizMaterialsStandardQcCheckNode" action="${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select id="storeId" path="storeId" class="input-xlarge required" onchange="checkNodeList()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" onchange="checkNodeList()"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料类型：</label>
			<div class="controls">
				<form:select path="materialType" class="input-xlarge required" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('bizMaterialsStandardType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">约检节点：</label>
			<div class="controls">
				<form:select  path="qcCheckNodeId" class="input-xlarge required">
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="30" class="input-xxlarge " placeholder="此节点质检验收通过后，项目经理将不可再次申请标化材料" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizmaterialsstandardchecknode:bizMaterialsStandardQcCheckNode:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
	<div class="alertMask undis" id="warnModel">
			<div class="maskWrapper">
				<p class="col_3 maskTit">提示</p>
				<div class="font28 col_6 maskContent">该工程模式下节点已设置,不可重复添加</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtnOne font33 col_fdfcfa"  onclick="sureTable()" href="javascript:void(0);">确定</a>
				</div>
			</div>
		</div>
</body>
</html>