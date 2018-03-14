<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>工程部统计查询</title>
    <meta name="decorator" content="default"/>
    <%--<link rel="stylesheet" type="text/css" href="/static/modules/css/xcConfirm.css"/>
    <script src="/static/modules/js/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="/static/modules/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>--%>
    <script type="text/javascript">


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


        function exportExcel() {
            var storeId = $("#storeId").val();
            if (null == storeId || storeId == "") {
                alertx("请先选择门店后，再导出");
                return;
            }
            // 判断门店
            if ($("#storeId").val() != null && '${entity.storeId}' != $("#storeId").val()) {
                alertx("请先查询后，再导出");
                return;
            }
            //日期  开始
            var queryDate1 = $("#startJspId").val();
            var queryDateId = $("#beginCreateDate").val();

            if (queryDateId != queryDate1) {
                alertx("请先查询后，再导出");
                return;
            }
            var queryDate2 = $("#endJspId").val();
            var queryDateId3 = $("#endCreateDate").val();

            if (queryDate2 != queryDateId3) {
                alertx("请先查询后，再导出");
                return;
            }
            loading("正在为您导出所有数据...请稍等....")
            setTimeout("closeTip()", 5000);

            $("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/exportExcel");
            $("#searchForm").submit();
            $("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/smallStatisticsList");
        }

        function searchList() {
            var storeId = $("#storeId").val();
            if (null == storeId || storeId < 1) {
                alertx("请先选择门店后，再查询");
                return;
            }
            var projectMode = $("#projectMode").val();
            if (null == projectMode || projectMode < 1) {
                alertx("请先选择工程模式后，再查询");
                return;
            }

            //日期  开始

            var queryDateId = $("#startId").val();

            if (""== queryDateId) {
                alertx("请选择开始日期");
                return;
            }

            var queryDateId3 = $("#endId").val();

            if (""== queryDateId3) {
                alertx("请选择结束日期");
                return;
            }
            loading("正在为您查询所有数据...请稍等....")


            $("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/pqcAboveSyntheticQuery");
            $("#searchForm").submit();
        }

    </script>
    <style>

        .tooltips {
            border-width: 1px;
            border-style: solid;
            position: absolute;
            display: none;
            border-radius: 3px;
            opacity: 0;
            filter: alpha(opacity=0);
            z-index: 999;
        }

        .tooltips p.content {
            padding: 5px;
        }

        .tooltips .triangle-front, .tooltips .triangle-back {
            width: 0;
            height: 0;
            overflow: hidden;
            border-width: 8px;
            border-style: solid;
            position: absolute;
            border-color: transparent;
            top: 100%;
            left: 50%;
            margin-left: -8px;
        }

        .tooltips .triangle-back {
            margin-top: -1px;
        }

        .yellow {
            border-color: #c7c08d;
            background-color: #fffac3;
        }

        .yellow .triangle-front {
            border-top-color: #c7c08d;
        }

        .yellow .triangle-back {
            border-top-color: #fffac3;
        }


    </style>
</head>
<body>

<c:set value="${fns:getStoreLabel(entity.storeId,'')}" var="storeName"></c:set>
<c:set value="${fns:getDictLabel(entity.projectMode,'project_mode','')}" var="projectMode"></c:set>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/engineDepartSyntheticQuery/pqcAboveSyntheticQueryPre">工程部统计查询</a></li>
</ul>
<form:form id="searchForm" modelAttribute="engineDepartSyntheticQueryEntity" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

    <!-- 日期 -->
    <input id="startJspId" type="hidden" value="<fmt:formatDate value="${entity.start}" pattern="yyyy-MM-dd"/>">
    <input id="endJspId" type="hidden" value="<fmt:formatDate value="${entity.end}" pattern="yyyy-MM-dd"/>">

    <ul class="ul-form">
        <li><label>门店：</label>

            <form:select path="storeId" class="input-medium needClear">
                <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>

        </li>
        <li><label>工程模式：</label>

            <form:select path="projectMode" class="input-medium needClear">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>

        </li>
        <li><label>日期：</label>
            <input name="start" id="startId" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${entity.start}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endId\')}',isShowClear:true});"/>

            <input name="end" id="endId" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${entity.end}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startId\')}',isShowClear:true});"/>

        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="searchList()"/>
        </li>
        <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/>
        </li>
            <%--<li class="btns"><input class="btn btn-primary" type="button" value="导出Excel" onclick="exportExcel()"/></li>--%>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">

    <thead>
    <tr>
        <th>门店</th>


        <th>工程模式</th>


        <th>项</th>
        <th>数值</th>


    </tr>
    </thead>
    <tbody>


    <c:if test="${not empty storeName}" >
        <tr>
            <td>${storeName}</td>
            <td>${projectMode}</td>

            <td> 质检报告数</td>
            <td>${map.qcBillReportCount}</td>
        </tr>

        <tr>
            <td>${storeName}</td>
            <td>${projectMode}</td>





            <td>项目经理申请约检数</td>

            <td>${map.applyCheckTotalCount}</td>
        </tr>

        <tr>
            <td>${storeName}</td>
            <td>${projectMode}</td>





            <td>质检上门签到数</td>

            <td>${map.qcSignCount}</td>
        </tr>

        <tr>
            <td>${storeName}</td>
            <td>${projectMode}</td>




            <td>质检上门后提交质检报告数</td>
            <td>${map.qcApplyCheckCount}</td>

        </tr>

        <tr>
            <td>${storeName}</td>
            <td>${projectMode}</td>



            <td> 质检验收合格数</td>
            <td>${map.qcBillDoneCount}</td>

        </tr>


        <tr>
            <td>${storeName}</td>
            <td>${projectMode}</td>

            <td> 质检上报问题数</td>
            <td>${map.issueReportCount}</td>
        </tr>


        <tr>
            <td>${storeName}</td>
            <td>${projectMode}</td>

            <td> 复检单产生数</td>
            <td>${map.qcRecheckCount}</td>
        </tr>


        <tr>
            <td>${storeName}</td>
            <td>${projectMode}</td>


            <td> 罚款工地数</td>
            <td>${map.fineOrderCount}</td>
        </tr>


        <tr>
            <td>${storeName}</td>
            <td>${projectMode}</td>

            <td>罚款总金额</td>
            <td> <fmt:formatNumber value="${map.fineMoneyTotal}" pattern="0.00"></fmt:formatNumber> </td>
        </tr>


    </c:if>

    </tbody>
</table>
<div class="pagination">${page}</div>

<%--<div class="table table-striped table-bordered table-condensed">--%>


<%--<b style="font-size: larger">说明: </b>--%>


<%--<br/>--%>


<%--<b>接单数：</b>--%>
<%--各大区查询日期内接受订单数量总和<br/>--%>
<%--<b>派单数：</b>--%>
<%--各大区查询日期内分配项目经理的订单数量总和<br/>--%>
<%--<b> 设计交底：</b>--%>
<%--各大区查询日期内项目经理设计交底的订单信息总和<br/>--%>
<%--<b> 确认开工：</b>--%>
<%--各大区查询日期内项目经理确认开工的订单数量总和<br/>--%>


<%--</div>--%>


</body>
</html>