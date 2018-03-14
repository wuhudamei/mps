<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工人银行卡管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/cusumerValidate/cusumerValidate.js">
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				getEmpByStoreid();
				//$("#name").focus();
				
					$.validator.addMethod("checkBankCardName", function(value) {
						var ret=false;
						$.ajax({
							dataType:"json",
							type:"post",
							url:"${ctx}/empbankcard/bizEmployeeBankcard/checkBankCardName",
							data:{bankCardNo:value,id:"${bizEmployeeBankcard.id}"},
							async:false,
							success:function(data){
								if(data>0){
									ret=false;
								}else{
									ret= true;	
								}
							}
						});
						return ret;
					}, "银行卡号已经存在!");
				
				$("#inputForm")
						.validate(
								{
									debug : false,
									rules : {
										storeId : "required",
										empId : "required",
										idCardNo : {
											required : true,
											isIdCardNo : true,
										},
										bankId : "required",
										branchBank : "required",
										bankCardNo : {
											required : true,
											isDigits : true,
											checkBankCardName:true
										},
									},
									messages : {
										storeId : "请选择门店",
										empId : "请选择员工",
										idCardNo : {
											required : "身份证号不能为空",
											isIdCardNo : "请您正确输入身份证号"

										},
										bankId : "开户行不能为空",
										branchBank : "支行不能为空",
										bankCardNo : {
											required : "银行卡不能为空",
											isDigits : "银行卡只能数字",
										},
									},
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});
	
	function getEmpByStoreid()
	{
		//alert( $("#storeId").val());
		$("#empId").html('');
		//$("#empId").attr("disabled","true");
		 $.ajax({
		        type : 'POST',
		        dataType : 'json',
		        url : '${ctx}/employee/bizEmployee/employee_list_byStoreId',
		        data : {
		            'storeid' : $("#storeId").val(),
		        },
		        success : function(data) {
		            var html = "";
		           // alert(data.length);
		            for (var i = 0; i < data.length; i++) {
		            	//alert($("#empId").val());
		            	var sec = "";
		            	if($("#empIdStoreId").val() == data[i].value){
		            		//alert("dd");
		            		sec = "selected='selected'";
		            		//alert(data[i].label);
		            	}
		            	html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
		            }
		            html+="";
		           	//alert(html);
		           // alert("dd");
		            
		            $("#empId").append(html);
		           // $("#empId").attr("disabled","");
		        }
		    })
	}
	function autoFillTheCard(){
		
	 $.ajax({
	        type : 'POST',
	        dataType : 'json',
	        url : '${ctx}/employee/bizEmployee/findEmployeeById',
	        data : {
	            'id' : $("#empId").val(),
	        },
	        success : function(data) {
	            
	        	if(null!=data){
	        		
	        		$("#idCardNo").val(data.idcardno);
	        		
	        	}
	        	
	        }
	    })
		
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/empbankcard/bizEmployeeBankcard/">工人银行卡列表</a></li>
		<li class="active"><a
			href="${ctx}/empbankcard/bizEmployeeBankcard/form?id=${bizEmployeeBankcard.id}">工人银行卡<shiro:hasPermission
					name="empbankcard:bizEmployeeBankcard:edit">${not empty bizEmployeeBankcard.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="empbankcard:bizEmployeeBankcard:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizEmployeeBankcard"
		action="${ctx}/empbankcard/bizEmployeeBankcard/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<input type="hidden" id="empIdStoreId" value="${bizEmployeeBankcard.empId }"/>
		<sys:message content="${message}" />


		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" id="storeId" name="storeId" class="input-xlarge " onChange="getEmpByStoreid()">
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" id="storeId" name="storeid" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">员工：</label>
			<div class="controls" id="empOptions"> 
				<form:select path="empId" class="input-medium" id="empId"
					name="empId" onchange="autoFillTheCard()">
					<form:option value="${bizEmployeeBankcard.empId }" label="${bizEmployeeBankcard.empRealName }" />
					<!--<form:options items="${fns:getEmployeeList('2')}" itemLabel="label"
						itemValue="value" htmlEscape="ssfalse" />-->
				</form:select> 
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="idCardNo" htmlEscape="false" maxlength="18"
					class="input-xlarge " />
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">银行卡号：</label>
			<div class="controls">
				<form:input path="bankCardNo" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行号：</label>
			<div class="controls">
				<form:select path="bankNo" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('biz_bank_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行名称：</label>
			<div class="controls">
				<form:input path="bankName" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> 
		
		<div class="control-group">
			<label class="control-label">账号所属省份：</label>
			<div class="controls">
				<form:input path="provinceName" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="empbankcard:bizEmployeeBankcard:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>