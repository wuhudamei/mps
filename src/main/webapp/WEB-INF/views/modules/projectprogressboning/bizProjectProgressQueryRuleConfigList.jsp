<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>大看板查询基础规则配置</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/treetable.jsp" %>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#treeTable").treeTable({expandLevel : 3}).show();
        });
        function updateSort() {
            loading('正在提交，请稍等...');
            $("#listForm").attr("action", "${ctx}/sys/menu/updateSort");
            $("#listForm").submit();
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/projectprogressboning/bizProjectProgressQueryRuleConfig/configList">配置列表</a></li>
    <li><a href="${ctx}/projectprogressboning/bizProjectProgressQueryRuleConfig/form">配置添加</a></li>
</ul>
<sys:message content="${message}"/>
<form id="listForm" method="post">
    <table id="treeTable" class="table table-striped table-bordered table-condensed hide">
        <thead>
          <tr>
              <th>名称</th>
              <th>是否特殊</th>
              <th>下标索引</th>
              <th>操作</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="config">
            <tr id="${config.id}" pId="${config.parentId}">
                <td nowrap><a href="${ctx}/projectprogressboning/bizProjectProgressQueryRuleConfig/form?id=${config.id}">${config.cnColumnName}[${config.enColumnName}]</a></td>
                <td>${fns:getDictLabel(config.isSpecial, 'is_special', '')}</td>
                <td>${config.indexNo}</td>
                <td>
                    <a href="${ctx}/projectprogressboning/bizProjectProgressQueryRuleConfig/form?id=${config.id}">修改</a>
                    <a href="${ctx}/projectprogressboning/bizProjectProgressQueryRuleConfig/form?parentId=${config.id}">添加下级节点</a>
                    <a href="${ctx}/projectprogressboning/bizProjectProgressQueryRuleConfig/delete?id=${config.id}" onclick="return confirmx('要删除该节点及所有子节点吗？', this.href)">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
   <%--<div class="form-actions pagination-left">--%>
        <%--<input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateSort();"/>--%>
    <%--</div>--%>
</form>
</body>
</html>