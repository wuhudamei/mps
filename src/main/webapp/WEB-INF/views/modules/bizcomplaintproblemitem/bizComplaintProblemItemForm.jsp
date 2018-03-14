<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投诉分类项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		    var isEnabled ="${bizComplaintProblemItem.isEnabled}";
		 if(""==isEnabled){
             $(":radio[name='isEnabled'][value='" + 1 + "']").attr("checked","checked");

         }else{
             $(":radio[name='isEnabled'][value='" + isEnabled + "']").attr("checked","checked");

         }



            var isChange = "${bizComplaintProblemItem.id}";
            if (undefined != isChange && "" != isChange) {

                findTypeMapByStoreId();
            }



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



        function findTypeMapByStoreId(){
            var isChange = "${bizComplaintProblemItem.complaintProblemTypeId}";
            var storeId =$("#storeId").val();
            var html='<option value=""></option>';
            if(undefined!=storeId &&""!=storeId){
                $.ajax({
                    url: "${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/findTypeMapByStoreId",
                    data: {storeId:storeId},
                    type :"get",
                    success: function(data){

                        var length =data.length;

                        for(var x = 0;x<length;x++){

                            if (undefined != isChange && "" != isChange &&isChange==data[x].typeId) {

                                $("#s2id_complaintProblemTypeId").find(".select2-chosen").text(data[x].typeName);
                                $("#complaintProblemTypeId").val(data[x].typeId);
                                html+='<option value="'+data[x].typeId+'" selected="selected">'+data[x].typeName+'</option>';

                            }else{

                                html+='<option value="'+data[x].typeId+'">'+data[x].typeName+'</option>';
                            }




                        }

                        $("#complaintProblemTypeId").html(html);

                    }




                })




            }


        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/">投诉分类项列表</a></li>
		<li class="active"><a href="${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/form?id=${bizComplaintProblemItem.id}">投诉分类项<shiro:hasPermission name="bizcomplaintproblemitem:bizComplaintProblemItem:edit">${not empty bizComplaintProblemItem.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizcomplaintproblemitem:bizComplaintProblemItem:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizComplaintProblemItem" action="${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-medium required" onchange="findTypeMapByStoreId()">
					<form:options items="${fns:getStoreList()}" itemLabel="label"
								  itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问题分类：</label>


			<div class="controls">
				<select id="complaintProblemTypeId" class="input-medium required" name="complaintProblemTypeId">
					<!-- 根据门店获取-->


				</select>

				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>





		<div class="control-group">
			<label class="control-label">分类项名称：</label>
			<div class="controls">
				<form:input path="itemName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">响应时间：</label>
			<div class="controls">
				<form:input path="responseTime" htmlEscape="false" class="input-xlarge required  number"
							onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
							onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
				<span class="help-inline"><font color="red">*</font> 天</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ִ执行时限：</label>
			<div class="controls">
				<form:input path="executeTimeLimit" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">特殊时间：</label>
			<div class="controls">
				<form:input path="specialTimeLimit" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扣分：</label>
			<div class="controls">
				<form:input path="deductScore" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">罚款：</label>
			<div class="controls">
				<form:input path="punishMoney" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="itemRemarks" htmlEscape="false" rows="4" maxlength="20" class="input-xxlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled"
								   items="${fns:getDictList('biz_enable_status')}" itemLabel="label"
								   itemValue="value" htmlEscape="false" class=" required"/>

			</div>
		</div>


		<div class="form-actions">
			<shiro:hasPermission name="bizcomplaintproblemitem:bizComplaintProblemItem:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>