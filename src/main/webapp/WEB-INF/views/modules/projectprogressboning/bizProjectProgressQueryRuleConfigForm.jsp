<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>大看板查询基础规则配置添加</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/projectprogressboning/bizProjectProgressQueryRuleConfig/configList">配置列表</a></li>
    <li class="active"><a
            href="${ctx}/projectprogressboning/bizProjectProgressQueryRuleConfig/form?id=${bizProjectProgressQueryRuleConfig.id}&parent.id=${bizProjectProgressQueryRuleConfig.parentId}">配置${not empty bizProjectProgressQueryRuleConfig.id?'修改':'添加'}</a>
    </li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="bizProjectProgressQueryRuleConfig" action="${ctx}/projectprogressboning/bizProjectProgressQueryRuleConfig/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">上级节点配置:</label>
        <div class="controls">
            <sys:treeselect id="parentConfig" name="parentId" value="${parentConfig.id}"
                            labelName="${parentConfig.cnColumnName}" labelValue="${parentConfig.cnColumnName}"
                            title="配置" url="/projectprogressboning/bizProjectProgressQueryRuleConfig/treeData"
                            extId="${bizProjectProgressQueryRuleConfig.id}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">中文字段:</label>
        <div class="controls">
            <form:input path="cnColumnName" htmlEscape="false" maxlength="50" class="required input-xlarge"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">英文字段:</label>
        <div class="controls">
            <form:input path="enColumnName" htmlEscape="false" maxlength="50" class="required input-xlarge"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">是否特殊:</label>
        <div class="controls">
            <form:radiobuttons path="isSpecial" items="${fns:getDictList('is_special')}" itemLabel="label"
                               itemValue="value" htmlEscape="false" class="required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">下标索引:</label>
        <div class="controls">
            <form:input path="indexNo" htmlEscape="false" maxlength="50" class="required digits input-small"/>
            <span class="help-inline">排列顺序，升序。</span>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">sql内容:</label>
        <div class="controls">
            <form:textarea path="mainSqlContent" htmlEscape="false" cssStyle="height: 100px;"
                           class="input-xxlarge"/>
        </div>
    </div>



    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit"  value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>