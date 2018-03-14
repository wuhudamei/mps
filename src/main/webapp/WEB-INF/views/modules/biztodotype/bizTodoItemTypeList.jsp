<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>待办管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            var isToremind ="${bizTodoItemType.isToRemind}"

            if(isToremind!=""){
                $("input[name='isToRemind'][value="+isToremind+"]").prop("selected","selected")
                $("#isToRemind").val(isToremind)
                $("#s2id_isToRemind").find(".select2-chosen").text(isToremind==1?"是":"否");

            }


        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        function clearCondition(){
            document.getElementById("searchForm").reset();

            var inputObjs=jQuery("#searchForm input[type='text']");
            for(var i=0;i<inputObjs.length;i++){
                var inputObj = inputObjs[i];
                inputObj.value="";
            }

            var selectObjs = jQuery("#searchForm input[class='input-medium']");
            for(var i=0;i<selectObjs.length;i++){
                var selectObj = selectObjs[i];
                selectObj.value="";
            }
        }
    </script>
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/biztodotype/bizTodoItemType/">待办列表</a></li>

</ul>
<form:form id="searchForm" modelAttribute="bizTodoItemType" action="${ctx}/biztodotype/bizTodoItemType/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>门店：</label>
            <form:select path="storeId" class="input-medium needClear">
                <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </li>

        <li><label class="control-label">工程模式：</label>


            <c:if test="${user.projectMode ==3}">

                <form:select path="projectMode" class="input-medium needClear"
                             onchange="findEngineListByStoreIdAndProjectMode()" id="projectModeId">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('project_mode')}"
                                  itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
            <c:if test="${user.projectMode !=3}">

                <form:select path="projectMode" class="input-medium needClear"
                             onchange="findEngineListByStoreIdAndProjectMode()" id="projectModeId">

                    <form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
                                 value="${user.projectMode}"/>
                </form:select>
            </c:if>
        </li>
        <li><label> 阶段：</label>
            <form:input path="businessStep" htmlEscape="false" maxlength="11" class="input-medium"/>
        </li>
        <li><label>事项/动作：</label>
            <form:input path="todoItemName" htmlEscape="false" maxlength="11" class="input-medium"/>
        </li>


        <li><label>待办生成方式：</label>
            <form:select path="todoItemGenerateStyle" class="input-medium needClear">
                <form:option value=""></form:option>
                <form:options items="${fns:getDictList('g_task_generate_way')}"   itemLabel="label" itemValue="label" htmlEscape="false"/>
            </form:select>
        </li>





        <li>

            <label class="control-label">是否提醒：</label>
        <select class="input-medium needClear" name="isToRemind" id="isToRemind">
            <option value=""></option>
            <option value="0">否</option>
            <option value="1">是</option>

        </select>
        </li>


        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th>
        <th>门店</th>
        <th>工程模式</th>
        <th>阶段</th>
        <th>提醒时间(天)</th>
        <th>事项/动作</th>
        <th>是否提醒</th>
        <th>排序</th>
        <th>待办生成方式</th>
        <th>业务类型</th>
        <th>具体业务</th>
        <th>备注</th>
        <shiro:hasPermission name="biztodotype:bizTodoItemType:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="bizTodoItemType" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>
                 ${fns:getStoreLabel(bizTodoItemType.storeId,'' )}
            </td>
            <td>
                ${fns:getDictLabel(bizTodoItemType.projectMode, 'project_mode','' )}

            </td>

            <td>${bizTodoItemType.businessStep}</td>

            <td>${bizTodoItemType.daysToRemind}</td>

            <td>${bizTodoItemType.todoItemName}</td>

            <td>${fns:getDictLabel(bizTodoItemType.isToRemind,'yes_no','')}</td>
            <td>${bizTodoItemType.sortIndex}</td>
            <td>${bizTodoItemType.todoItemGenerateStyle}</td>
            <td>${bizTodoItemType.relatedBusinessType}</td>
            <td>${bizTodoItemType.relatedBusinessId}</td>
            <td>${bizTodoItemType.todoItemRemarks}</td>
            <shiro:hasPermission name="biztodotype:bizTodoItemType:edit">
                <td>
                    <a href="${ctx}/biztodotype/bizTodoItemType/form?id=${bizTodoItemType.id}">修改</a>

                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>