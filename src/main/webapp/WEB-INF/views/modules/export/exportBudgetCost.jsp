<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>预算成本导出</title>
    <meta name="decorator" content="default" />
    <script type="text/javascript">
        $(document).ready(function() {
        });

        function clearCondition() {
            document.getElementById("searchForm").reset();

            var inputObjs = jQuery("#searchForm input[type='text']");
            for (var i = 0; i < inputObjs.length; i++) {
                var inputObj = inputObjs[i];
                inputObj.value = "";
            }

            var selectObjs = jQuery("#searchForm input[class='input-medium']");
            for (var i = 0; i < selectObjs.length; i++) {
                var selectObj = selectObjs[i];
                selectObj.value = "";
            }
        }



        function btnSubmit() {
            var storeId = $("#storeId").val();
            var projectMode = $("#projectMode").val();
            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();
            if(startDate == null || startDate == "" || endDate == null || endDate == ""){
                alertx("请选择竣工日期后再导出。");
                return;
            }
            window.location.href="${ctx}/export/ExportBudgetCost/exportBudgetCost?storeId="+storeId+"&projectMode="+projectMode+"&startDate="+startDate+"&endDate="+endDate;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a
            href="javascript:void(0);">预算成本导出</a></li>
</ul>
<form:form id="searchForm" modelAttribute="exportBudgetCost"
           action="${ctx}/evaluate/exportEvalInfo/exportEvalInfo"
           method="get" class="breadcrumb form-search">
    <ul class="ul-form">
        <li><label>门店：</label> <c:if test="${empty storeDropEnable}">
            <form:select id="storeId" path="storeId" class="input-medium"
                         disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
                <form:options items="${fns:getStoreList()}" itemLabel="label"
                              itemValue="value" htmlEscape="false" />
            </form:select>
            <input type="hidden" id="storeId" value="${exportBudgetCost.storeId}" />
        </c:if> <c:if test="${!empty storeDropEnable}">
            <form:select id="storeId" path="storeId"
                         class="input-medium needClear"
                         onchange="findEngineListByStoreIdAndProjectMode()">
                <form:options items="${fns:getStoreList()}" itemLabel="label"
                              itemValue="value" htmlEscape="false" />
            </form:select>
        </c:if></li>

        <li><label>工程模式：</label>
            <form:select id="projectMode" path="projectMode"
                         class="input-medium" disabled="true">
                <form:option value="1" label="产业"/>
            </form:select>
        </li>

        <li><label style="width: 120px">竣工时间：</label> <input
                name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20"
                class="input-medium Wdate"
                value="<fmt:formatDate value="${exportBudgetCost.startDate}" pattern="yyyy-MM-dd"/>"
                onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
            - <input name="endDate" id="endDate" type="text" readonly="readonly"
                     maxlength="20" class="input-medium Wdate"
                     value="<fmt:formatDate value="${exportBudgetCost.endDate}" pattern="yyyy-MM-dd"/>"
                     onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
        <li class="btns"><input class="btn btn-primary" type="button"
                                value="导出" onclick="btnSubmit()" /></li>
        <li class="clearfix"></li>
    </ul>
</form:form>

</body>
</html>
