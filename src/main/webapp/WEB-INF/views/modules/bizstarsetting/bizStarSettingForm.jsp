<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html>
<head>
	<title>星级分数区间设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	//var a=0;
	$(document).ready(function() {
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
	function checksquare(obj){
		
		var val = $(obj).val();
		//alert(val)
		
	}
	function checkStartScore(){
		var storeId=$("#storeId").val();
		var projectMode=$("input[name='projectMode']:checked").val();
		var star=$("#star").val();
		var startScore=$("#startScore").val();
		var endScore=$("#endScore").val();
		//alert(${fn:length(bbb)});
		if(star == null || star=='' ||storeId == null || storeId=='' || projectMode ==null || projectMode=='' ||startScore ==null || startScore == '' || endScore == null || endScore == ''){
			return;
		}
		  $.ajax({
	             type: "get",
	             url: "${ctx}/starSetting/starRatingSetting/findIsCopy?storeId="+storeId+"&projectMode="+projectMode+"&star="+star+"&startScore="+startScore+"&endScore="+endScore,
	             async: true,
	             success: function(data){
	                        if(data.startScore>=0&&data.startScore!=10000){
	                        	alertx("已经存在起始分数或者结束分数的配置项了（"+data.startScore+"~"+data.endScore+"），开始分数和结束分数是不允许重复的，请您修改开始分数和结算分数。")
	                        	return;
	                        }
	                        if(data.startScore<0){
	                        	alertx("星级配置项已经存在了，不允许重复配置星级对应的分数区间值。");
	                        	return;
	                        }
	                        if(data.startScore==10000){
	                        	$("#inputForm").submit();
	                        }
	                     }
	         });
	
	}
	//校验数量
	function fix_amountthis(obj){
    	
        //先把非数字的都替换掉，除了数字和. 
	    obj.value = obj.value.replace(/[^\d.]/g,""); 
	    //必须保证第一个为数字而不是. 
	    obj.value = obj.value.replace(/^\./g,""); 
	    //保证只有出现一个.而没有多个. 
	    obj.value = obj.value.replace(/\.{2,}/g,"."); 
	    //保证.只出现一次，而不能出现两次以上 
	    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	    //只能输入两个小数 
	    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
	    //只能输入6个整数
	    var reg = /.*\..*/;
	    if(reg.test(obj.value)){
	    	var aa = obj.value.substring(0,obj.value.indexOf("."));
	    	if(aa.length>6){
	    		obj.value = obj.value.substring(0,6); 
	    	}
	    }else{
	    	if(obj.value.length>6){
	    		obj.value = obj.value.substring(0,6);
	    	}
	    }
    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/starSetting/starRatingSetting/list">星级分数区间设置列表</a></li>
		<li class="active"><a href="${ctx}/starSetting/starRatingSetting/form?id=${bizStarSetting.id}">星级分数区间设置${not empty bizStarSetting.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizStarSetting" action="${ctx}/starSetting/starRatingSetting/save" method="post" class="form-horizontal">
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
				<form:radiobuttons path="projectMode" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" checked="true" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">起始分数：</label>
			<div class="controls">
				<form:input path="startScore" htmlEscape="false" maxlength="8"  class="input-xlarge number required" onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束分数：</label>
			<div class="controls">
				<form:input path="endScore" htmlEscape="false" maxlength="8"  class="input-xlarge number required" onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">星级：</label>
			<div class="controls">
				<form:select path="star" class="input-xlarge required"  htmlEscape="false" >
						<form:option value=""></form:option>
						<form:option value="5">五星</form:option>
						<form:option value="4">四星</form:option>
						<form:option value="3">三星</form:option>
						<form:option value="2">二星</form:option>
						<form:option value="1">一星</form:option>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" checked="true" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button"  value="保 存" onclick="checkStartScore()"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
</body>
</html>