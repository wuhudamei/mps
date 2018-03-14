<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>质检工作报告统计</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            closeTip();
            $("#btnSubmit").bind("click", submit);
            $("tbody>tr").click(function(){

                $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

            });
        });


        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

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

        function submit() {
            var bl = true;

            if ($("#storeId").val() == "") {
                alert("请选择门店")
                bl = false;
                return;
            }
            if ($("#projectMode").val() == "") {
                alert("请选择工程模式")
                bl = false;  return;
            }
//            if ($("#inspectName").val() == "") {
//                alert("请填写质检员姓名")
//                bl = false;  return;
//            }
//            if ($("#beginCheckDatetime").val() == "" || $("#endCheckDatetime").val() == "") {
//                alert("请选择要查询的日期")
//                bl = false;  return;
//            }


            if(bl){

                loading("查找数据中...");
                $("#searchForm").submit();

            }
        }
    </script>
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/inspectCheckTimesQuery/list">质检工作统计</a></li>
</ul>
<form:form id="searchForm" modelAttribute="inspectCheckTimesEntity" action="${ctx}/inspectCheckTimesQuery/listInfo"
           method="post" class="breadcrumb form-search">
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

                <form:select path="projectMode" class="input-medium needClear">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('project_mode')}"
                                  itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
            <c:if test="${user.projectMode !=3}">

                <form:select path="projectMode" class="input-medium needClear">

                    <form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
                                 value="${user.projectMode}"/>
                </form:select>
            </c:if>
        </li>
        <li><label>质检员：</label>
            <form:input path="inspectName" htmlEscape="false" maxlength="11" class="input-large"/>
        </li>

        <li><label>提交报告日期：</label>
            <input name="checkStartDate" type="text" readonly="readonly"
                   value='<fmt:formatDate value="${startDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>'
                   id="beginCheckDatetime" maxlength="20" class="input-medium Wdate"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCheckDatetime\')}',isShowClear:false});"/>
            <input name="checkEndDate" type="text" readonly="readonly"
                   value='<fmt:formatDate value="${endDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="endCheckDatetime"
                   maxlength="20" class="input-medium Wdate"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCheckDatetime\')}',isShowClear:false});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/></li>
        <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/>
        </li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>门店</th>
        <th>工程模式</th>
        <th>质检员</th>
        <th>检查工地次数</th>
        <th>签到次数</th>
        <th>质检报告数</th>
        <th>其中抽检报告数</th>
        <th>其中约检报告数</th>
        <th>复检报告数</th>
        <th>复检次数</th>
        <th>罚款次数</th>
        <th>罚款总金额</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="workerInfo" varStatus="status">
        <tr>

            <td>
                    ${fns:getStoreLabel(workerInfo.storeId, '')}
            </td>
            <td>${fns:getDictLabel(workerInfo.projectMode, 'project_mode', '')}</td>
            <td>
                    ${workerInfo.inspectName}
            </td>
            <td>
                    ${workerInfo.checkConstructionSiteTimes}
            </td>
            <td>
                    ${workerInfo.signTimes}
            </td>
            <td>
                    ${workerInfo.inspectReportTimes}
            </td>
            <td>
                    ${workerInfo.randomCheckReportTimes}
            </td>
            <td>
                    ${workerInfo.aboutCheckReportTimes}
            </td>
            <td>
                    ${workerInfo.recheckReportTimes}
            </td>
            <td>
                    ${workerInfo.recheckTimes}
            </td>
            <td>
                    ${workerInfo.fineTimes}
            </td>
            <td>
                    ${workerInfo.findTotalMoney}
            </td>


        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>