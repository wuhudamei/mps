<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>约检数量配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
				    if(checkPrice($("#qcMaxCount").val())){

					}else{

                        alertx("数量必须为整数")
                        return;
					}





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
        function checkPrice(price){
            return (/^(([1-9]\d*)|\d)?$/).test(price.toString());
        }
		function storeOnly(){

var storeId = $("#storeId").val();
if(storeId!=undefined && storeId!= ''){


                //根据门店加载结算节点
                $.ajax({

                    url : "${ctx}/bizqccount/bizQcMaxCount/storeOnly?storeId="
                    + storeId ,
                    type : "get",
                    async:true,
                    success : function(data) {
					if(data!=1){
                        $("#btnSubmit").attr("disabled",true);
                        alertx("门店配置过了");

                    }else{
					    $("#btnSubmit").attr("disabled",false);

                    }

                    }

                });

			}


		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizqccount/bizQcMaxCount/">约检数量配置列表</a></li>
		<li class="active"><a href="${ctx}/bizqccount/bizQcMaxCount/form?id=${bizQcMaxCount.id}">约检数量配置<shiro:hasPermission name="bizqccount:bizQcMaxCount:edit">${not empty bizQcMaxCount.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizqccount:bizQcMaxCount:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizQcMaxCount" action="${ctx}/bizqccount/bizQcMaxCount/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required" onchange="storeOnly()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">每天约检的订单数量：</label>
			<div class="controls">
				<form:input path="qcMaxCount" htmlEscape="false" maxlength="11" class="input-xlarge digital"/>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="bizqccount:bizQcMaxCount:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>