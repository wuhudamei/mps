<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>待办管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            findBusinessType();
            findIdByType();
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


        function findBusinessType() {

            var type ="${bizTodoItemType.relatedBusinessType}"


            var storeId = $("#storeId").val();

            var projectMode = $("input[name='projectMode']:checked").val();

            var html = '<option value="">无</option>';
            if (undefined != storeId && "" != storeId && undefined != projectMode && "" != projectMode) {

                $.ajax({
                    url: "${ctx}/biztodotype/bizTodoItemType/findRelatedBusinessTypeByStoreIdProjectMode",
                    type: "get",
                    async:false,
                    data: {storeId: storeId, projectMode: projectMode},
                    success: function (data) {

                        var length = data.length;

                        if(length>0){

                            for (var v = 0; v < length; v++) {

                                if (data[v].value == type) {
                                    $("#s2id_relatedBusinessType").find(".select2-chosen").text(data[v].label)
                                    html += '<option value="' + data[v].value + '" selected="selected">' + data[v].label + '</option>';

                                } else {

                                    html += '<option value=' + data[v].value + '>' + data[v].label + '</option>';
                                }


                            }
                        }else{
                            $("#s2id_relatedBusinessType").find(".select2-chosen").text("无")

                        }

                        $("#relatedBusinessType").html(html);

                    }


                })


            }

        }

        function findIdByType() {
            var id =
            "${bizTodoItemType.relatedBusinessId}"

            var type = $("#relatedBusinessType").val();

            var html = '<option value="">无</option>';
            if ("" != type) {
                $.ajax({
                    url: "${ctx}/biztodotype/bizTodoItemType/findIdByBusinessType",
                    type: "get",
                    data: {relatedBusinessType: type},
                    success: function (data) {

                        var length = data.length;

                        if(length>0){
                            for (var v = 0; v < length; v++) {

                                if (data[v].value == id) {
                                    $("#s2id_relatedBusinessId").find(".select2-chosen").text(data[v].label)

                                    html += '<option value="' + data[v].value + '" selected="selected">' + data[v].label + '</option>';
                                } else {
                                    html += '<option value=' + data[v].value + '>' + data[v].label + '</option>';

                                }


                            }

                        }else{

                            $("#s2id_relatedBusinessId").find(".select2-chosen").text("无")
                        }



                        $("#relatedBusinessId").html(html);

                    }


                })

            }


        }

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/biztodotype/bizTodoItemType/">待办列表</a></li>
    <li class="active"><a href="${ctx}/biztodotype/bizTodoItemType/form?id=${bizTodoItemType.id}">待办<shiro:hasPermission
            name="biztodotype:bizTodoItemType:edit">${not empty bizTodoItemType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="biztodotype:bizTodoItemType:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="bizTodoItemType" action="${ctx}/biztodotype/bizTodoItemType/save"
           method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">门店：</label>
        <div class="controls">
            <form:select path="storeId" class="input-medium required"
                         onchange="findBusinessType()">
                <form:options items="${fns:getStoreList()}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>

        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工程模式：</label>
        <div class="controls">
            <form:radiobuttons path="projectMode"
                               items="${fns:getDictList('project_mode')}" itemLabel="label"
                               itemValue="value" htmlEscape="false" class=" required"
                               onchange="findBusinessType()"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">阶段：</label>
        <div class="controls">
            <form:input path="businessStep" htmlEscape="false" maxlength="50" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">提醒时间：</label>
        <div class="controls">
            <form:input path="daysToRemind" htmlEscape="false" maxlength="11" class="input-xlarge number"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">事项/动作：</label>
        <div class="controls">
            <form:input path="todoItemName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">是否提醒：</label>
        <div class="controls">
            <form:radiobuttons path="isToRemind"
                               items="${fns:getDictList('yes_no')}" itemLabel="label"
                               itemValue="value" htmlEscape="false" class=""/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">代办生成方式：</label>
        <div class="controls">

            <form:input path="todoItemGenerateStyle"  htmlEscape="false" class="" readonly="true"/>
        </div>
    </div>


    <div class="control-group">
        <label class="control-label">业务类型：</label>
        <div class="controls">
            <select class="input-medium required" name="relatedBusinessType" id="relatedBusinessType"
                    onchange="findIdByType()" disabled ="true">
                <option value=""></option>

            </select>
            <span class="help-inline"><font color="red">*</font> </span>

        </div>
    </div>

    <div class="control-group">
        <label class="control-label">具体业务：</label>
        <div class="controls">
            <select class="input-medium required" name="relatedBusinessId" id="relatedBusinessId"  disabled ="true">
                <option value=""></option>

            </select>
            <span class="help-inline"><font color="red">*</font> </span>

        </div>
    </div>


    <div class="control-group">
        <label class="control-label">备注：</label>
        <div class="controls">
            <form:textarea path="todoItemRemarks" htmlEscape="false" maxlength="100" class="input-xlarge "/>
        </div>
    </div>

    <div class="form-actions">
        <shiro:hasPermission name="biztodotype:bizTodoItemType:edit"><input id="btnSubmit" class="btn btn-primary"
                                                                            type="submit"
                                                                            value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>