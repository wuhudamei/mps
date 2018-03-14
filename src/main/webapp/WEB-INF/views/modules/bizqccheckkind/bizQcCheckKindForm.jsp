<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>检查分类管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

            var obj = document.getElementsByName("projectMode");
            for(var i=0; i<obj.length; i ++){
                if(obj[i].checked){
                    findtTaskPackageByStoreId(obj[i]);
                }
            }




            var isChange = "${bizQcCheckKind.id}";

            if ( "" != isChange) {
                var typeVal =${bizQcCheckKind.taskPackageTemplatId}
                $("#taskPackageTemplatId").each(function(){
                        if(typeVal==$(this).val()){
                            $("#dealPersonTypeId").val($(this).val());
                            $("#s2id_dealPersonTypeId").find(".select2-chosen").text($(this).text());
                        }
                    })
            }


			var projectMode  ="${bizQcCheckKind.id}"
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
			if("4" == readOnly){
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
				$(":radio[name='status'][value='" + 1 + "']").prop("checked", "checked");
				//修改
			}
			//不做动作, 保证回显, 上面也保证readOnly	
			
			
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



        function findtTaskPackageByStoreId(obj) {

            $("#s2id_taskPackageTemplatId").find(".select2-chosen").text('')
            var storeId = $("#storeId").val();
            var  projectMode=null;
            if(null!=obj){
                 projectMode = obj.value;
            }

            var html = '<option value=""></option>';

            var taskPackageTemplatId = "${bizQcCheckKind.taskPackageTemplatId}";
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



        //驳回触发的事件
        function submitUpdate(id ){
          var  taskPackageTemplatId = $("#taskPackageTemplatId").val();
           $("#qckindId").val(id);
            $.ajax({
                url: "${ctx}/bizqccheckitem/bizQcCheckItem/ajaxQcCheckKindAll",
                data: {id: id,
                    taskPackageTemplatId:taskPackageTemplatId
                },
                type: "get",
                success: function (data) {
                    var length = data.length;
                    if (length > 0) {
                        for (var x = 0; x < length; x++) {
                            var notQcCheckKindcount= data[x].notQcCheckKind;

                            if(null!=notQcCheckKindcount ||notQcCheckKindcount!="" || undefined!=notQcCheckKindcount  ){
                                $("#showText").text("检查分类关联的任务包一共有"+0+"个不匹配的检查项，您确定要更新检查分类对应的检查项关联的任务包吗？");
                                $("#myAbandonedModalReject").modal('show');
							}else{
                                $("#showText").text("检查分类关联的任务包一共有"+notQcCheckKindcount+"个不匹配的检查项，您确定要更新检查分类对应的检查项关联的任务包吗？");
                                $("#myAbandonedModalReject").modal('show');
							}

                           // if(notQcCheckKindcount==0){
                               // $("#myAbandonedModalReject").modal('hide');
						 //  }else{
                              // $("#showText").text("检查分类关联的任务包一共有"+notQcCheckKindcount+"个不匹配的检查项，您确定要更新检查分类对应的检查项关联的任务包吗？");
                              //  $("#myAbandonedModalReject").modal('show');
						  // }

                        }
                    }
                }
            })


        }

        //驳回 取消 事件
        function onclickNoAbandonedReject(){
            $('#myAbandonedModalReject').modal('hide');
            $("#inputForm").attr("action","${ctx}/bizqccheckkind/bizQcCheckKind/save");
            $("#inputForm").submit();
        }

        //处驳回 原因提交的事件
        function onclickAbandonedReject(){
          var  qckindId = $("#qckindId").val();
           var taskPackageTemplatId = $("#taskPackageTemplatId").val();
        //  alert(qckindId+":"+taskPackageTemplatId);
            $.ajax({
                url: "${ctx}/bizqccheckitem/bizQcCheckItem/ajaxUpdatecheckItem",
                data: {qcCheckKindId: qckindId,
                    taskPackageTemplatId:taskPackageTemplatId
                },
                type: "get",
                success: function (data) {
                    var length = data.length;
                    if (length > 0) {
                        for (var x = 0; x < length; x++) {
                            alert("成功");
                        }
                    }
                }
            })
            $('#myAbandonedModalReject').modal('hide');
            $("#inputForm").attr("action","${ctx}/bizqccheckkind/bizQcCheckKind/save");
            $("#inputForm").submit();
        }

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizqccheckkind/bizQcCheckKind/list">检查分类列表</a></li>
		<li class="active"><a href="${ctx}/bizqccheckkind/bizQcCheckKind/form?id=${bizQcCheckKind.id}">检查分类<shiro:hasPermission name="bizqccheckkind:bizQcCheckKind:edit">${not empty bizQcCheckKind.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizqccheckkind:bizQcCheckKind:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizQcCheckKind" action="${ctx}/bizqccheckkind/bizQcCheckKind/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required"  onchange="findtTaskPackageByStoreId()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode"   id="projectModeid" class="input-medium required" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" onclick="findtTaskPackageByStoreId(this)"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">对应任务包：</label>
			<div class="controls">
				<select id="taskPackageTemplatId" class="input-medium" name="taskPackageTemplatId">
					<!-- 根据门店获取-->
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查分类名称：</label>
			<div class="controls">
				<form:input path="qcCheckKindName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
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

			<c:if test="${not empty bizQcCheckKind.id}">
			<input id="btnSubmit" class="btn btn-primary" type="button"   onclick="submitUpdate(${bizQcCheckKind.id})" value="修改"/>
			</c:if>
			<c:if test="${ empty bizQcCheckKind.id}">
			<shiro:hasPermission name="bizqccheckkind:bizQcCheckKind:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>



	<!-- 驳回btn弹框的model -->
	<div  class="modal fade" id="myAbandonedModalReject" tabindex="-1" role="dialog" style="width:350px">
		<input  id="qckindId" type="hidden">
		<input  id="workOrderCodeReject" type="hidden">
		<div class="modal-header">
			<button class="close" type="button" data-dismiss="modal">×</button>
			<h4 id="myModalLabel" align="center" style="color: black;">检查项是否更改提醒</h3><br>
				<form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
				<input id = "ids"name="id" value="${bizMaterialsStandardReceiveBill.id}" type="hidden">
				<input id = "workOrderCode2" name="workOrderCode" value="${bizMaterialsStandardReceiveBill.workOrderCode}" type="hidden">
				<input id = "status" name="status" value="20" type="hidden">
				<div  style="margin:10px;min-height:20px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
					<%--<textarea id="resonReject" class="mask-text"  onkeyup="this.value = this.value.substring(0,100)" placeholder="请输入驳回内容，多行输入，不多于100个汉字" maxlength="100" ></textarea>--%>
					<span id="showText">  </span>
					<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandonedReject()" >更新检查项</a>
					<a href="javascript:void(0)" class="btn" onclick="onclickNoAbandonedReject()">不更新检查项</a>
				</div>
		</div>
		</form:form>
	</div>


</body>
</html>