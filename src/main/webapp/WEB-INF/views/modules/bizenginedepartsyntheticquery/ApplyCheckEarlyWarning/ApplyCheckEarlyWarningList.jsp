<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>约检验收预警查询</title>
    <meta name="decorator" content="default" />
    <script type="text/javascript">
        $(document).ready(function() {
        	var readOnly = "${readOnly}";
        	console.log(readOnly);
   			//如果是产业或者是传统的人,  就不允许修改
   			if ("1" == readOnly) {
   				$("#projectMode option[value=2]").remove();
   				$("#projectMode option[value=4]").remove();
   			}
   			if ("2" == readOnly) {
   				$("#projectMode option[value=1]").remove();
   				$("#projectMode option[value=4]").remove();
   			}
   			if ("4" == readOnly) {
   				$("#projectMode option[value=1]").remove();
   				$("#projectMode option[value=2]").remove();
   			}
        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }


    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/ApplyCheckEarlyEarningQuery/list">约检验收预警查询</a></li>
</ul>
<form:form id="searchForm" modelAttribute="applyCheckEarlyWarningQueryEntity"
           action="${ctx}/ApplyCheckEarlyEarningQuery/list" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
    <input id="pageSize" name="pageSize" type="hidden"
           value="${page.pageSize}" />
    <ul class="ul-form">
        <li><label>门店：</label>
            <form:select path="storeId" class="input-medium needClear">
                <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>工程模式：</label>
            <c:if test="${!empty projectModeEnable}">
                <form:select path="projectMode" class="input-medium needClear" disabled="true">
                    <form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
            <c:if test="${empty projectModeEnable}">
                <form:select path="projectMode" id="projectMode" class="input-medium needClear">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
        </li>

        <li><label>进度节点名称：</label>
            <form:input path="constructionScheduleName" class="input-medium needClear"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary"	type="submit" value="查询" /></li>
        <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}" />
<table id="contentTable"
       class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>门店</th>
        <th>工程模式</th>
        <th>进度节点名称</th>
        <th>延期订单数</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="number">
        <tr>
            <td>${fns:getStoreLabel(number.storeId, '')}</td>
            <td>${fns:getDictLabel(number.projectMode, 'project_mode', '')}</td>

            <td>${number.constructionScheduleName}</td>

            <td>
                <a href="${ctx}/ApplyCheckEarlyEarningQuery/delayOrderInfo?storeId=${number.storeId}&projectMode=${number.projectMode}&nodeId=${number.nodeId}">${number.delayOrderCount}</a>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>