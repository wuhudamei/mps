<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>返单信息添加</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/cusumerValidate/cusumerValidate.js"></script>
<script type="text/javascript">
	//手机校验


	function checkPhone(phoneId) {
		$("#btnSubmit").attr('disabled', false);
		var phone = $("#" + phoneId).val();


        if(!isNaN(phone)&& phone.length==11){
            if(phoneId=="customerPhone"){
                $.ajax({
                    url : "${ctx}/orderReport/bizOrderReport/checkCustomerPhone?customerPhone=" + phone,
                    type : "post",
                    success : function(data) {
                        if (data == "0") {
                            $("#error").hide();
                            $("#btnSubmit").attr("disabled", false);
                        } else {
                            $("#error").show();
                            $("#btnSubmit").attr('disabled', true);
                        }
                    }
                });
            }


        }else{

            alertx('您的手机格式不正确,请重新输入');
            $("#btnSubmit").attr('disabled', true);
            return false;
        }


		
	}
	
	$(document).ready(
					function() {
						$("#inputForm").validate(
										{
											submitHandler : function(form) {
												loading('正在提交，请稍等...');
												$("#btnSubmit").attr(
														"disabled", true);
												form.submit();
												
											},
											errorContainer : "#messageBox",
											errorPlacement : function(error,
													element) {
												$("#messageBox").text(
														"输入有误，请先更正。");
												if (element.is(":checkbox")
														|| element.is(":radio")
														|| element
																.parent()
																.is(
																		".input-append")) {
													error.appendTo(element
															.parent().parent());
												} else {
													error.insertAfter(element);
												}
											}
										});
					});
	
	
	
   function  ajaxedicheck (thi) {
// 	   alert(thi.checked)
        if (thi.checked) {
        	$("#checkboxid").val("1");
        } else {
        	$("#checkboxid").val("0");

        }
    };

	
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
	        
	     }
  
	
</script>

</head>
<body>
	<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/ordermainmate/bizOrderMainMate/list?orderId=${bizOrderMainMate.orderId}">墙地砖列表</a></li>

	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizOrderMainMate"
		action="${ctx}/ordermainmate/bizOrderMainMate/updateuni" method="post"
		class="form-horizontal">
		<input name="orderId" type="hidden" value="${bizOrderMainMate.orderId}"/>
			<form:hidden path="id"/>

		<sys:message content="${message}" />
		<table>
			<tr>
				<td><div class="control-group">
						<label class="control-label">项目名称：</label>
						<div class="controls">
							${fns:getDictLabel(bizOrderMainMate.mainMateType, 'main_material_type', '')}
						</div>
					</div></td>
			</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label">位置：</label>
						<div class="controls">
						${bizOrderMainMate.position}
						</div>
					</div></td>
	</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label">品牌及套餐：</label>
						<div class="controls">
			${bizOrderMainMate.brandCombo}

						</div>
					</div></td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">型号：</label>
						<div class="controls">
					${bizOrderMainMate.model}
						</div>
					</div>
				</td>
	</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label">规格：</label>
						<div class="controls">
					${bizOrderMainMate.specification}
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">单位：</label>
						<div class="controls">
				${bizOrderMainMate.unit}
						</div>
					</div></td>
						</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label">数量：</label>
						<div class="controls">
						${bizOrderMainMate.count}
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">含损耗数量：</label>
						<div class="controls">
						${bizOrderMainMate.includLossCount}
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">实发数量：</label>
						<div class="controls">
								${bizOrderMainMate.applyCounta}
						</div>
					</div></td>
						</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label">单位面积：</label>
						<div class="controls">
						<form:input onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"   path="unitsquare" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
						
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">是否计算面积：</label>
						<div class="controls">
						<form:radiobuttons    path="iscountsquare" items="${fns:getDictList('dict_iscountsquare')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
<%-- 						<form:checkboxe id="checkboxid"  onclick="ajaxedicheck(this)"  type="checkbox" name="iscountsquare"  value="0" ></form:checkboxe> --%>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label">备注：</label>
						<div class="controls">
							${bizOrderMainMate.remarks}
						</div>
					</div></td>
			</tr>







		</table>
		<div class="form-actions">
			<shiro:hasPermission name="orderReport:orderReport:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>