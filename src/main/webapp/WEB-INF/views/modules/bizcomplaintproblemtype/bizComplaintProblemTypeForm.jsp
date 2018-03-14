<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>问题分类管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            var html =' <option value=""></option>'
                +' <option value="1">项目经理</option>'
                +'  <option value="2">项目经理+工人</option>'
                +'  <option value="3">质检员</option>';

            $("#dealPersonTypeId").html(html)


            var isEnabled ="${bizComplaintProblemType.isEnabled}";
            if(""==isEnabled){
                $(":radio[name='isEnabled'][value='" + 1 + "']").attr("checked","checked");

            }else{
                $(":radio[name='isEnabled'][value='" + isEnabled + "']").attr("checked","checked");

            }

            var isChange = "${bizComplaintProblemType.id}";

            if ( "" != isChange) {

                findtTaskPackageByStoreId();


                var typeVal =${bizComplaintProblemType.dealPersonType}

                $("#dealPersonTypeId option").each(function(){


                    if(typeVal==$(this).val()){

                        $("#dealPersonTypeId").val($(this).val());
                        $("#s2id_dealPersonTypeId").find(".select2-chosen").text($(this).text());
                    }
                })

            }


            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
        function findtTaskPackageByStoreId() {
            var storeId = $("#storeId").val();
            var html = '<option value=""></option>';

            var taskPackageTemplatId = "${bizComplaintProblemType.taskPackageTemplatId}";


            $.ajax({
                url: "${ctx}/bizcomplaintproblemtype/bizComplaintProblemType/findPackByStoreId",
                data: {storeId: storeId},
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

        }

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/bizcomplaintproblemtype/bizComplaintProblemType/">问题分类列表</a></li>
    <li class="active"><a
            href="${ctx}/bizcomplaintproblemtype/bizComplaintProblemType/form?id=${bizComplaintProblemType.id}">问题分类<shiro:hasPermission
            name="bizcomplaintproblemtype:bizComplaintProblemType:edit">${not empty bizComplaintProblemType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="bizcomplaintproblemtype:bizComplaintProblemType:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="bizComplaintProblemType"
           action="${ctx}/bizcomplaintproblemtype/bizComplaintProblemType/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">门店：</label>
        <div class="controls">
            <form:select path="storeId" class="input-medium required" onchange="findtTaskPackageByStoreId()">
                <form:options items="${fns:getStoreList()}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>

        </div>
    </div>
    <div class="control-group">
        <label class="control-label">问题分类：</label>
        <div class="controls">
            <form:input path="typeName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
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
        <label class="control-label">处理人员：</label>
        <div class="controls">
            <select class="input-medium required" name="dealPersonType" id="dealPersonTypeId">



            </select>

            <span class="help-inline"><font color="red">*</font> </span>
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
        <shiro:hasPermission name="bizcomplaintproblemtype:bizComplaintProblemType:edit"><input id="btnSubmit"
                                                                                                class="btn btn-primary"
                                                                                                type="submit"
                                                                                                value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>