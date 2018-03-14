<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>材料自采表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					
					var result = submitCheck();
					
					if(result){
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
		});
		
		
		//项目经理结算比例   校验
		function materialSettleRate(a){
			var reg=/^(100|[1-9]\d|\d)$/;
			if(null==a || a==""){
				$("#alertRemarks").text("请输入项目经理结算比例");
        		$("#subReport").show();
			}else if(!reg.test(a)){
				$("#settleRate").val("");
				$("#alertRemarks").text("项目经理结算比例只允许输入0~100之间的正整数");
        		$("#subReport").show();
			}
		}
		
		//请输入自采材料名称   校验
		function materialNameJiaoyan(a){
			
			if(null==a || a==""){
				$("#alertRemarks").text("请输入自采材料名称");
        		$("#subReport").show();
			}else if(a.length > 20){
				$("#materialName").val("");
				$("#alertRemarks").text("自采材料名称，不超过20个汉字");
        		$("#subReport").show();
			}
		}
		
		function sendMessage(){
			$("#subReport").hide();
		}
		
		function submitCheck(){
			
			//1.项目经理结算比例   校验
			var settleRate = $("#settleRate").val();
			var reg=/^(100|[1-9]\d|\d)$/;
			if(null==settleRate || settleRate==""){
				$("#alertRemarks").text("请输入项目经理结算比例");
        		$("#subReport").show();
        		return false;
			}else if(!reg.test(settleRate)){
				$("#settleRate").val("");
				$("#alertRemarks").text("项目经理结算比例只允许输入0~100之间的正整数");
        		$("#subReport").show();
        		return false;
			}
			
			//2.请输入自采材料名称   校验
			var materialName = $("#materialName").val();
			if(null==materialName || materialName==""){
				$("#alertRemarks").text("请输入自采材料名称");
        		$("#subReport").show();
        		return false;
			}else if(materialName.length > 20){
				$("#materialName").val("");
				$("#alertRemarks").text("自采材料名称，不超过20个汉字");
        		$("#subReport").show();
        		return false;
			}
			
			//3.门店
			var storeId = $("#storeId").val();
			if(null==storeId || storeId=="" || storeId < 1){
				$("#alertRemarks").text("请选择门店");
        		$("#subReport").show();
        		return false;
			}
			
			//4.工程模式
			var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
			if(null==projectMode || projectMode==""){
				$("#alertRemarks").text("请选择工程模式");
        		$("#subReport").show();
        		return false;
			}
			
			var id = '${bizMaterialSelfbuy.id}';
			//5.校验是否存在（同一门店+工程模式下，自采材料名称不允许重复）
			var result = materialIsExista(id,storeId,projectMode,materialName);
	        if(result){
	        	return true;
	        }else{
	        	$("#alertRemarks").text("同一门店+工程模式下，自采材料名称不允许重复");
        		$("#subReport").show();
	        	return false;
	        }    	
	            	
		}
	
		function materialIsExista(id,storeId,projectMode,materialName){
			//5.校验是否存在（同一门店+工程模式下，自采材料名称不允许重复）
			var result = false;
			$.ajax({
				url: "${ctx}/bizmaterialselfbuy/bizMaterialSelfbuy/material_selfbuy_ajax_isExists",
	            type: "post",
	            async:false,
	            data:{
	            		id:id,
	            		storeId:storeId,
	            		projectMode:projectMode,
	            		materialName:materialName
	            	},
	            success: function(data) {
	            	if(data=="0"){
	            		result = true;
	            	}else{
	            		result = false;
	            	}
	            }
			})
			return result;
		}
	</script>
	<style type="text/css">
		.undis{display:none;}
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 380px;border-radius: 4px;background: #fff;font-size: 16px;
		    position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		.maskBtn{display: block;width: 60%;text-align: center;line-height: 38px;}
		.maskBtn{background: #0780ec;border-radius: 4px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16px;margin: 0 auto;}
    		
	
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizmaterialselfbuy/bizMaterialSelfbuy/preList">材料自采表列表</a></li>
		<li class="active"><a href="${ctx}/bizmaterialselfbuy/bizMaterialSelfbuy/form?id=${bizMaterialSelfbuy.id}">材料自采表<shiro:hasPermission name="bizmaterialselfbuy:bizMaterialSelfbuy:edit">${not empty bizMaterialSelfbuy.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizmaterialselfbuy:bizMaterialSelfbuy:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizMaterialSelfbuy" action="${ctx}/bizmaterialselfbuy/bizMaterialSelfbuy/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料名称：</label>
			<div class="controls">
				<form:input path="materialName" htmlEscape="false" maxlength="20" class="input-xlarge required" onblur="materialNameJiaoyan(this.value)"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目经理结算比例：</label>
			<div class="controls">
				<form:input path="settleRate" htmlEscape="false" maxlength="3" class="input-xlarge digits required" onblur="materialSettleRate(this.value)"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属结算阶段：</label>
			<div class="controls">
				<form:select path="settleStage" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('settle_stage')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizmaterialselfbuy:bizMaterialSelfbuy:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
	
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarks"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	
</body>
</html>