<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>检查项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            //findtTaskPackageByStoreId();
            var obj = document.getElementsByName("projectMode");
            for(var i=0; i<obj.length; i ++){
                if(obj[i].checked){

                    findtTaskPackageByStoreId(obj[i]);
                }
            }
			
			var projectMode  ="${bizQcCheckItem.id}"
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
			if ("4" == readOnly) {
				$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
				$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
			}
			//新增
			if ("" == projectMode) {
				//如果不是产业或者是传统的人 ,默认传统,并且可以修改
				if ("1" != readOnly && "2" != readOnly) {
					$(":radio[name='projectMode'][value='" + 2 + "']").prop("checked", "checked");
				} else {
					//如果是产业或者是传统的人   工程模式也一样,并且不能修改
					$(":radio[name='projectMode'][value='" + readOnly + "']").prop("checked", "checked");
					if ("1" == readOnly) {
						$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
					}
					if ("2" == readOnly) {
						$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
					}
				}
				
				$(":radio[name='isRedline'][value='" + 0 + "']").prop("checked", "checked");
				$(":radio[name='isRequired'][value='" + 0 + "']").prop("checked", "checked");
				$(":radio[name='status'][value='" + 1 + "']").prop("checked", "checked");
				
				//修改
			}
			//不做动作, 保证回显, 上面也保证readOnly
			
			jQuery.validator.addMethod("ispunishAmount", function(value, element) {
				var reg=/^[0-9]{1,}(?:.[0-9]{0,2})?$/;
			    return this.optional(element) || (reg.test(value));
			}, "请正确填写金额");
			//$("#name").focus();
			$("#inputForm").validate({
				debug : false,
				rules : {
					punishAmount : {
						required : true,
						ispunishAmount : true,
					},
					workerPunishAmount : {
						ispunishAmount : true,
					},
					qcPunishAmount : {
						ispunishAmount : true,
					},
				},
				messages : {
					punishAmount : {
						required : "请输入处罚项目经理金额",
						ispunishAmount : "处罚项目经理金额格式错误",
					},
					workerPunishAmount : {
						ispunishAmount : "处罚工人金额格式错误",
					},
					qcPunishAmount : {
						ispunishAmount : "处罚质检员金额格式错误",
					},
				},
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
		
		
		function checkKind(){
            $("#s2id_qcCheckKindId").find(".select2-chosen").text('')
			var store = $("#storeId").val();
			var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
			$.ajax({
				url:"${ctx}/bizqccheckitem/bizQcCheckItem/checkKind",
				type : "post",
				data:{
					storeId : store,
					projectMode:projectMode
					},
				success : function(data){
					var htmls = "<option value=''></option>";//<option value='' selected='true'></option>
			   		for(var i = 0; i < data.length; i++){
			   			htmls += "<option value='"+data[i].id+"'>"+data[i].qcCheckKindName+"</option>";
			   		}
			   		$("#qcCheckKindId").html(htmls);
			   		var htmls = "";
				  }
			});
		};



        function findtTaskPackageByStoreId(obj) {

            $("#s2id_taskPackageTemplatId").find(".select2-chosen").text('')
            var storeId = $("#storeId").val();
            if(null!=obj){
                var projectMode = obj.value;
            }
            var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
            var html = '<option value=""></option>';

            var taskPackageTemplatId = $("#taskPackageTemplatIdValue").val();
            $.ajax({
                url: "${ctx}/bizcomplaintproblemtype/bizComplaintProblemType/findPackByStoreId",
                data: {storeId: storeId,
                    projectMode:projectMode
                },
                type: "get",
                success: function (data) {
                    var length = data.length;
                    if (length > 0) {
                        for (var x = 0; x < length; x++) {
                            if (data[x].packId == taskPackageTemplatId) {
                                $("#s2id_taskPackageTemplatId").find(".select2-chosen").text(data[x].packName);
                                html += '<option value="' + data[x].packId + '"selected="selected">' + data[x].packName + '</option>';
                                $("#taskPackageTemplatId").val(data[x].packId);
                            } else {
                                html += '<option value="' + data[x].packId + '">' + data[x].packName + '</option>';
                            }
                        }
                    }
                    $("#taskPackageTemplatId").html(html);
                }
            })
        };




function qcCheckKindOnchange(obj){
   var taskPackageTemplatIdValue=  obj.value;
    $.ajax({
        url: "${ctx}/bizqccheckitem/bizQcCheckItem/taskPackageTemplat",
        data: {id: taskPackageTemplatIdValue
        },
        type: "get",
        success: function (data) {
            var length = data.length;
            if (length > 0) {
                for (var x = 0; x < length; x++) {
                   var  taskPackageTemplatId= data[x].taskPackageTemplatId;
                    if(null!=taskPackageTemplatId && taskPackageTemplatId!="" && undefined!=taskPackageTemplatId){
                        $("#taskPackageTemplatIdValue").val(taskPackageTemplatId);
                        findtTaskPackageByStoreId();
                        $("#taskPackageTemplatIdValue").val('');
                    }
                }
            }
        }
    })

    findtTaskPackageByStoreId();
}




	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizqccheckitem/bizQcCheckItem/list">检查项列表</a></li>
		<li class="active"><a href="${ctx}/bizqccheckitem/bizQcCheckItem/form?id=${bizQcCheckItem.id}">检查项<shiro:hasPermission name="bizqccheckitem:bizQcCheckItem:edit">${not empty bizQcCheckItem.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizqccheckitem:bizQcCheckItem:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>

	<form:form id="inputForm" modelAttribute="bizQcCheckItem" action="${ctx}/bizqccheckitem/bizQcCheckItem/save" method="post" class="form-horizontal">
		<input id="taskPackageTemplatIdValue" name="taskPackageTemplatIdValue" type="hidden" value="${bizQcCheckItem.taskPackageTemplatId}"/>
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
				
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required" onchange="checkKind(),findtTaskPackageByStoreId()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode" class="input-medium required" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"  onchange="checkKind(),findtTaskPackageByStoreId(this)"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">检查分类：</label>
			<div class="controls">
				<form:select path="qcCheckKindId" class="input-xlarge required" id="qcCheckKindId" name="qcCheckKindId" onchange="qcCheckKindOnchange(this)">
					<form:option value="${a }">${name }</form:option>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">对应任务包：</label>
			<div class="controls">
				<select id="taskPackageTemplatId" class="input-xlarge " name="taskPackageTemplatId">

				</select>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">检查项名称：</label>
			<div class="controls">
				<form:input path="qcCheckItemName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚项目经理金额：</label>
			<div class="controls">
				<form:input path="punishAmount" htmlEscape="false" class="input-xlarge  number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扣项目经理分值：</label>
			<div class="controls">
				<form:input path="pmPunishScore" htmlEscape="false" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚工人金额：</label>
			<div class="controls">
				<form:input path="workerPunishAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扣工人分值：</label>
			<div class="controls">
				<form:input path="workerPunishScore" htmlEscape="false" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚质检员金额：</label>
			<div class="controls">
				<form:input path="qcPunishAmount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扣质检员分值：</label>
			<div class="controls">
				<form:input path="qcPunishScore" htmlEscape="false" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目分数：</label>
			<div class="controls">
				<form:input path="itemScore" htmlEscape="false" class="input-xlarge  number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否红线：</label>
			<div class="controls">
				<form:radiobuttons path="isRedline" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否必检：</label>
			<div class="controls">
				<form:radiobuttons path="isRequired" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizqccheckitem:bizQcCheckItem:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
	
	<script type="text/javascript">
		
		window.onload = function(){
			
			var store = $("#storeId").val();
			var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
			$.ajax({
				url:"${ctx}/bizqccheckitem/bizQcCheckItem/checkKind",
				type : "post",
				data:{
					storeId : store,
					projectMode:projectMode
					},
				success : function(data){
					var htmls = "";//<option value='' selected='true'></option>
			   		for(var i = 0; i < data.length; i++){
			   			htmls += "<option value='"+data[i].id+"'>"+data[i].qcCheckKindName+"</option>";
			   		}
			   		$("#qcCheckKindId").html(htmls);
			   		var htmls = "";
				  }
			});
		}
	
	
	
	
	
	</script>
</body>
</html>