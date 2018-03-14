<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>考核条例细则添加</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            getRuleType();
        });

        $(function () {
            $("#inputForm").validate({
                rules: {
                    storeId: "required",
                    projectMode: "required",
                    bizAssessRuleTypeId: "required",
                    bizAssessRuleDescribe: "required",
                    rewardPunishAmount: "required",
                    rewardPunishScore: "required",
                    isEnabled: "required",
                },
                messages: {
                    storeId: "请选择门店",
                    projectMode: "请选择工程模式",
                    bizAssessRuleTypeId: "请选择分类",
                    bizAssessRuleDescribe: "请填写考核条件细则说明",
                    rewardPunishAmount: "请填写奖励惩罚金额",
                    rewardPunishScore: "请填写奖励惩罚分数",
                    isEnabled: "请选择是否启用",
                },
                errorContainer: "#messageBox",
                submitHandler : function(form) {
                    var obj = document.getElementsByName('rewardPunishTargetEmployeeType');
                    var s = '';
                    var j = 0;
                    for (var i = 0; i < obj.length; i++) {
                        if (obj[i].checked) {
                            s += obj[i].value + ','; //如果选中，将value添加到变量s中
                            j++;
                        }
                    }
                    if (j < 1) {
                        alertx("请选择责任人")
                        return;
                    }
                    loading('正在提交，请稍等...');
                    form.submit();
                },

            });
        });

        function checkNumber(obj) {
            //先把非数字的都替换掉，除了数字和.
            obj.value = obj.value.replace(/[^\d.]/g, "");
            //必须保证第一个为数字而不是.
            obj.value = obj.value.replace(/^\./g, "");
            //保证.只出现一次，而不能出现两次以上
            obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
            //只能输入两个小数
            obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
        }

        function getRuleType() {
            $("#bizAssessRuleTypeId").html('');
            $.ajax({
                url: '${ctx}/bizAssessRuleType/bizAssessRuleType/queryRuleTypeByParam',
                type: 'post',
                dataType: 'json',
                data: {
                    'storeId': $('#storeId').val(),
                    'projectMode': $('#projectMode').val(),
                    /* 'isEnabled':1, */
                },
                success: function (data) {
                    if (data == null) {
                        $("#bizAssessRuleTypeId").append('');
                    } else {
                        var html = "<option value=''></option>";
                        for (var i = 0; i < data.length; i++) {
                            var sec = "";
                            if ('${bizAssessRule.bizAssessRuleTypeId}' == data[i].id) {
                                sec = "selected='selected'";
                                $("#s2id_bizAssessRuleTypeId .select2-chosen").html(data[i].typeName);
                            }
                            html += "<option value='" + data[i].id + "' " + sec + ">" + data[i].typeName + "</option>"
                        }
                        html += '';
                        $("#bizAssessRuleTypeId").append(html);
                    }
                }
            });
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/bizAssessRule/bizAssessRule/queryBizAssessRule">考核条例细则列表</a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="bizAssessRule" action="${ctx}/bizAssessRule/bizAssessRule/saveBizAssessRule"
           method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">门店：</label>
        <div class="controls">
            <c:if test="${empty storeDropEnable}">
                <form:hidden path="storeId"/>
                <form:select path="storeId" class="input-xlarge required" disabled="true" onchange="getRuleType()">
                   <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
            <c:if test="${!empty storeDropEnable}">
                <form:select path="storeId" class="input-xlarge required" onchange="getRuleType()">
                   <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工程模式：</label>
        <div class="controls">
            <c:if test="${empty gongcheng}">
                <form:hidden path="projectMode"/>
                <form:select path="projectMode" class="input-xlarge required" disabled="true" onchange="getRuleType()">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value"
                                  htmlEscape="false"/>
                </form:select>
            </c:if>
            <c:if test="${!empty gongcheng}">
                <form:select path="projectMode" class="input-xlarge required" onchange="getRuleType()">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value"
                                  htmlEscape="false"/>
                </form:select>
            </c:if>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">分类：</label>
        <div class="controls">
            <form:select path="bizAssessRuleTypeId" class="input-xlarge required" id="bizAssessRuleTypeId">
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">考核条件细则说明：</label>
        <div class="controls">
            <form:textarea path="bizAssessRuleDescribe" htmlEscape="false" class="input-xxlarge "/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">奖励/惩罚金额：</label>
        <div class="controls">
            <form:input path="rewardPunishAmount" htmlEscape="false" class="input-xlarge " oninput="checkNumber(this)"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">奖励/惩罚分数：</label>
        <div class="controls">
            <form:input path="rewardPunishScore" htmlEscape="false" class="input-xlarge " oninput="checkNumber(this)"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">责任人：</label>
        <div class="controls">
            <c:forEach items="${fns:getDictList('responsible_person')}" var="dict">
                <input type="checkbox" name="rewardPunishTargetEmployeeType" value="${dict.value}"  <c:if
                        test="${fns:checkIsExits(bizAssessRule.rewardPunishTargetEmployeeType,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
            </c:forEach>
                <%--<form:checkboxes path="rewardPunishTargetEmployeeType" items="${fns:getDictList('responsible_person')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>--%>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">是否启用：</label>
        <div class="controls">
            <form:radiobuttons path="isEnabled" items="${fns:getDictList('biz_enable_status')}" itemLabel="label"
                               itemValue="value" htmlEscape="false" class="required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>