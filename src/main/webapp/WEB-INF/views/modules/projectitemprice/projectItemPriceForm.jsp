<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>施工项价格管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
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

	function findMaxVersion() {

		var storeId = $("#sel").val();
		var projectItemId= "${projectItemPrice.projectIntemId}";
		$.ajax({

			url : "${ctx}/projectitemprice/projectItemPrice/findMaxVersion?storeId="
					+ storeId+"&projectItemId="+projectItemId,
			type : "get",
			async: false,
			success : function(data) {
		$("#version").val(data);
			}

		})
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

    function checkedData() {
	    /*if($("#projectIntemId").val() == '' || $("#effectDate").val() == '' || $("#sel").val() == ''){

            return false;
        }*/
        $.ajax({ url: "${ctx}/projectitemprice/projectItemPrice/checkedData",
            data:{'projectIntemId':$("#projectIntemId").val(),'effectDate':$("#effectDate").val(),'storeId':$("#sel").val()},
            success: function(data){
                if(data == true){
                    alertx("生效时间重复，不可提交，请修改生效时间！")
                }else{
                    $("#inputForm").submit();
                }
            }
        });
    }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/projectitemprice/projectItemPrice/list?projectIntemId=${projectItemPrice.projectIntemId}">施工项价格列表</a></li>
		<li class="active"><a
			href="${ctx}/projectitemprice/projectItemPrice/form?projectIntemId=${projectItemPrice.projectIntemId}">施工项价格<shiro:hasPermission
					name="projectitemprice:projectItemPrice:edit">${not empty projectItemPrice.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="projectitemprice:projectItemPrice:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="projectItemPrice"
		action="${ctx}/projectitemprice/projectItemPrice/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />

		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">施工项编号</label>
			<div class="controls">
				<form:input path="projectIntemCode" htmlEscape="false"
					class="input-xlarge  number required" disabled="true"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">施工项名称</label>
			<div class="controls">
				<form:input path="projectIntemName" htmlEscape="false"
					class="input-xlarge  number required" disabled="true"/>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">计价方式</label>
			<div class="controls">
				<form:select path="valuationMethod" class="input-xlarge required" disabled="true">
					<form:options items="${fns:getDictList('valuation_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-medium required" id="sel"
					onchange="findMaxVersion()">
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
<!-- -------------------------------------------------------------------------------------------------------------------- -->
		
			<c:if test="${projectItemPrice.valuationMethod eq 'fixedUnitPrice'}">
			<div class="control-group">
				<label class="control-label">销售单价：</label>
					<div class="controls">
						<form:input path="projectIntemPrice" htmlEscape="false"
							class="input-xlarge  number required" 
							onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"
							/>
					</div>
			</div>
			</c:if>
		
		
			<c:if test="${projectItemPrice.valuationMethod eq 'fixedUnitPrice'}">
			<div class="control-group">
				<label class="control-label">成本单价：</label>
					<div class="controls">
						<form:input path="projectIntemCostPrice" htmlEscape="false"
							class="input-xlarge  number required" 
							onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"
							/>
					</div>
			</div>
			</c:if>
		
		
		
		
			<c:if test="${projectItemPrice.valuationMethod eq 'foundationPileTotal'}">
			<div class="control-group">
				<label class="control-label">基装增项总价占比：</label>
					<div class="controls">
						<form:input path="projectIntemPrice" htmlEscape="false"
							class="input-xlarge  number required"
                                    onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"
							/>%
					</div>
			</div>
			</c:if>
		
		
			<c:if test="${projectItemPrice.valuationMethod eq 'foundationPileTotal'}">
			<div class="control-group">
				<label class="control-label">基装增项总成本占比：</label>
					<div class="controls">
						<form:input path="projectIntemCostPrice" htmlEscape="false"
							class="input-xlarge  number required"
                                    onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"
							/>%
					</div>
			</div>
			</c:if>
		
		
		
		
		
			<c:if test="${projectItemPrice.valuationMethod eq 'renovationFoundationPile'}">
			<div class="control-group">
				<label class="control-label">工程总价占比：</label>
					<div class="controls">
						<form:input path="projectIntemPrice" htmlEscape="false"
							class="input-xlarge  number required"
                                    onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"
							/>%
					</div>
			</div>
			</c:if>
		<script type="text/javascript">
		function checkedNum(a){
			var inputValue= $(a).val();
			var reg=/(^[1-9][0-9]$|^[0-9]$|^100$)/;
			if(!reg.test(inputValue)){
                var s = inputValue.substr(0,inputValue.length-1);
                $(a).val(s);
			}
		}
				
		</script>
		
			<c:if test="${projectItemPrice.valuationMethod eq 'renovationFoundationPile'}">
			<div class="control-group">
				<label class="control-label">工程总成本占比：</label>
					<div class="controls">
						<form:input path="projectIntemCostPrice" htmlEscape="false"
							class="input-xlarge  number required"
                                    onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"
							/>%
							
					</div>
			</div>
			</c:if>
		
		
		
		
		
			<c:if test="${projectItemPrice.valuationMethod eq 'dismantleFoundationPile'}">
			<div class="control-group">
				<label class="control-label">拆除基装定额总价占比：</label>
					<div class="controls">
						<form:input path="projectIntemPrice" htmlEscape="false"
							class="input-xlarge  number required"
                                    onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"
							/>%
					</div>
			</div>
			</c:if>
		
		
			<c:if test="${projectItemPrice.valuationMethod eq 'dismantleFoundationPile'}">
			<div class="control-group">
				<label class="control-label">拆除基装定额总成本占比：</label>
					<div class="controls">
						<form:input path="projectIntemCostPrice" htmlEscape="false"
							class="input-xlarge  number required"
                                    onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"
							/>%
					</div>
			</div>
			</c:if>
		
		
			<c:if test="${projectItemPrice.valuationMethod eq 'demolitionProjectTotal'}">
			<div class="control-group">
				<label class="control-label">拆除工程总价占比：</label>
					<div class="controls">
						<form:input path="projectIntemPrice" htmlEscape="false"
							class="input-xlarge  number required"
                                    onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"
							/>%
					</div>
			</div>
			</c:if>
		
		
			<c:if test="${projectItemPrice.valuationMethod eq 'demolitionProjectTotal'}">
			<div class="control-group">
				<label class="control-label">拆除工程总成本占比：</label>
					<div class="controls">
						<form:input path="projectIntemCostPrice" htmlEscape="false"
							class="input-xlarge  number required"
                                    onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"
							/>%
					</div>
			</div>
			</c:if>
		
		
		
<!-- -------------------------------------------------------------------------------------------------------------------- -->
		<div class="control-group">
			<label class="control-label">版本号</label>
			<div class="controls">

				<form:input path="projectIntemVersion" htmlEscape="false"
					maxlength="11" class="input-xlarge  digits" readonly="true" id="version" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生效日期：</label>
			<div class="controls">
				<input name="effectDate" type="text" readonly="readonly" id = "effectDate"
					maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${projectItemPrice.effectDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="projectIntemPriceRemarks" htmlEscape="false"
					maxlength="255" class="input-xlarge " />
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="projectitemprice:projectItemPrice:edit">
				<input id="btnSubmit" class="btn btn-primary" type="button" onclick="checkedData()"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<div hidden="hidden">
			<input type="text" hidden="hidden" name="projectIntemId" id = "projectIntemId"
				value="${projectItemPrice.projectIntemId}" />
		</div>
	</form:form>
</body>
<script>




</script>

</html>