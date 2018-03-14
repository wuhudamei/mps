<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算补助添加</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var v = '${pmSubsidyCnfg.id}';
			if(v == null || v == ''){
				$(":radio[name=isEnabled][value=1]").attr("checked",true);
			}
			
	    	var radioValtemp = '${bizConstructionSchedule.projectMode}';
			var userProjectMode = '${userProjectMode}';
		/* 	$(":input[name=projectMode][value=3]").hide(); */
			/* $(":input[name=projectMode][value=2]").hide();
			$("[for=projectMode1]").hide(); */
			/* $("[for=projectMode3]").hide(); */
	    	if(userProjectMode == '3' || userProjectMode == "" ||userProjectMode == '2'){
	    		$(":input[name=projectMode][value="+userProjectMode+"]").attr("checked",true);
	    		$(":input[name=projectMode][value="+radioValtemp+"]").attr("checked",true);
	    		
	    	}else{
	    		if(radioValtemp == ''){
	    			$(":input[name=projectMode]").attr("disabled",true);
		        	$(":input[name=projectMode][value="+userProjectMode+"]").removeAttr("disabled");
		        	$(":input[name=projectMode][value="+userProjectMode+"]").attr("checked",true);
	    		}else{
	    			$(":input[name=projectMode]").attr("disabled",true);
		        	$(":input[name=projectMode][value="+radioValtemp+"]").removeAttr("disabled");
	    		}
	    	}
			
			var id  ="${bizConstructionSchedule.id}";
			
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
		
		function checksquare(obj){
			
			var val = $(obj).val();
			alert(val)
			
		}
		
		function tip(){
			var storeId = $("#storeId").val();
			var projectMode = $(":radio[name=projectMode][checked=checked]").val();
			var squareMin = $("#squareMin").val();
			var squareMax = $("#squareMax").val();
			var id = $("#id").val();
			if(storeId == null || storeId=='' || projectMode ==null || projectMode=='' ||squareMin ==null || squareMin == '' || squareMax == null || squareMax == ''){
				return;
			}
			  $.ajax({
		             type: "post",
		             url: "${ctx}/pmsubsidycnfg/checkedSquare",
		             data: {storeId:storeId, projectMode:projectMode, squareMin:squareMin, squareMax:squareMax, id:id},
		             dataType: "json",
		             success: function(data){
		                        if(data!=null){
		                        	alertx("已经存在开始值或者结束值的配置项了（"+data.squareMin+"~"+data.squareMax+"），开始值和结算值是不允许重复的，请您修改开始值和结算值。")
		                        	return;
		                        }else{
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
		<li><a href="${ctx}/pmsubsidycnfg/list">结算补助列表</a></li>
		<li class="active"><a href="#">结算补助${not empty pmSubsidyCnfg.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pmSubsidyCnfg"  method="post" class="form-horizontal" action="${ctx}/pmsubsidycnfg/save">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
			<form:select path="storeId" class="input-medium required">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
						<label class="control-label">工程模式：</label>
						<div class="controls">
							<form:radiobuttons path="projectMode"
								items="${fns:getDictList('project_mode')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="required"
								 />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
		
		
		<div class="control-group">
			<label class="control-label">计价面积开始值：</label>
			<div class="controls">
				<form:input path="squareMin" htmlEscape="false" maxlength="8"  class="input-xlarge number required" onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计价面积结束值：</label>
			<div class="controls">
				<form:input path="squareMax" htmlEscape="false" maxlength="8" class="input-xlarge number required" onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">补助单价：</label>
			<div class="controls">
				<form:input path="subsidyPrice" htmlEscape="false" maxlength="8" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled" class="required" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="保 存" onclick="tip()"/>
			<a id="btnCancel" class="btn" href="${ctx }/pmsubsidycnfg/list">返 回</a>
		</div>
	</form:form>
</body>
</html>