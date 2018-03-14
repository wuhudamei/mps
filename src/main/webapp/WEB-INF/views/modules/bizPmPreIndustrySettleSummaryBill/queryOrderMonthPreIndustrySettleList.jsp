<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>订单月度结算查询</title>
    <meta name="decorator" content="default" />
    <script type="text/javascript">
        $(document).ready(function() {

        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);

            $("#searchForm").submit();
            return false;
        }

        function clearCondition(){
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

            var arrSon = document.getElementsByName("orderStatusNumber");
            for (i = 0; i < arrSon.length; i++) {
                if(arrSon[i].checked){
                    arrSon[i].checked=false;
                }
            }
        }




        function querySettleBillInfo(id,settleBillType,orderId){
            window.location.href="${ctx}/bizPmPreIndustrySettleSummaryBill/bizPmPreIndustrySettleSummaryBill/querySettleBillInfo?id="+id+"&settleBillType="+settleBillType+"&orderId="+orderId;
        }

        function searchButton(){
            $("#searchForm").attr("action", "${ctx}/bizPmPreIndustrySettleSummaryBill/bizPmPreIndustrySettleSummaryBill/queryOrderMonthSettleList");
            $("#searchForm").submit();
        }

        function exportExcel(){
           /* $("#searchForm").attr("action", "${ctx}/bizPmPreIndustrySettleSummaryBill/bizPmPreIndustrySettleSummaryBill/queryOrderMonthSettleList");
            var storeId = $("#storeId").val();
            var settleMonth = $("#settleMonth").val();
            var customerName = $("#customerName").val();
            var pmEmployeeName = $("#pmEmployeeName").val();
            var createSettleMonthStartDate = $("#createSettleMonthStartDate").val();
            var createSettleMonthEndDate = $("#createSettleMonthEndDate").val();
            var url="${ctx}/bizPmPreIndustrySettleSummaryBill/bizPmPreIndustrySettleSummaryBill/export?"
                +"storeId="+storeId+"&settleMonth="+settleMonth+"&customerName="+customerName+"&pmEmployeeName="+pmEmployeeName
                +"&createSettleMonthStartDate="+createSettleMonthStartDate+"&createSettleMonthEndDate="+createSettleMonthEndDate;
            window.location.href=url;*/
            $("#searchForm").attr("action", "${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/exportPmSettleBill");
            $("#searchForm").submit();
            $("#searchForm").attr("action", "${ctx}/bizPmPreIndustrySettleSummaryBill/bizPmPreIndustrySettleSummaryBill/queryOrderMonthSettleList");
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">订单月度结算查询</a></li>
</ul>
<br />


<form:form id="searchForm" modelAttribute="bizPmPreIndustrySettleSummaryBill"
           action="${ctx}/bizPmPreIndustrySettleSummaryBill/bizPmPreIndustrySettleSummaryBill/queryOrderMonthSettleList"
           method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
    <input id="pageSize" name="pageSize" type="hidden"
           value="${page.pageSize}" />
    <input type="hidden" name="status" value="50" />

    <ul class="ul-form">
        <li><label>门店：</label> <c:if test="${empty storeDropEnable}">
            <form:select path="storeId" class="input-medium" disabled="true"
                         id="storeId" onchange="getDepartments()">
                <form:options items="${fns:getStoreList()}" itemLabel="label"
                              itemValue="value" htmlEscape="false" />
            </form:select>

            <input type="hidden" id="storeId" value="${bizPmPreIndustrySettleSummaryBill.storeId}" />
        </c:if> <c:if test="${!empty storeDropEnable}">
            <form:select path="storeId" class="input-medium" id="storeId"
                         onchange="getDepartments()">
                <form:option value=""></form:option>
                <form:options items="${fns:getStoreList()}" itemLabel="label"
                              itemValue="value" htmlEscape="false" />
            </form:select>
        </c:if></li>

        <li><label>月度：</label>
            <input name="settleMonth" id="settleMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
                   value="${bizPmPreIndustrySettleSummaryBill.settleMonth}" onclick="WdatePicker({dateFmt:'yyyyMM',isShowClear:true});"/>
        </li>
        <li><label>客户姓名：</label> <form:input path="customerName"
                                             htmlEscape="false" maxlength="64" class="input-medium needClear" />
        </li>
        <li><label>项目经理：</label> <form:input path="pmEmployeeName"
                                             htmlEscape="false" maxlength="64" class="input-medium needClear" />
        </li>



        <li><label>生成月度结算时间：</label> <input name="createSettleMonthStartDate" type="text"
                                            id="createSettleMonthStartDate" readonly="readonly" maxlength="20"
                                            class="input-medium Wdate"
                                            value="<fmt:formatDate value="${bizPmPreIndustrySettleSummaryBill.createSettleMonthStartDate}" pattern="yyyy-MM-dd"/>"
                                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'createSettleMonthEndDate\')}',isShowClear:false});" />
            &nbsp;至&nbsp; <input name="createSettleMonthEndDate" type="text" id="createSettleMonthEndDate"
                                 readonly="readonly" maxlength="20" class="input-medium Wdate"
                                 value="<fmt:formatDate value="${bizPmPreIndustrySettleSummaryBill.createSettleMonthEndDate}" pattern="yyyy-MM-dd"/>"
                                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createSettleMonthStartDate\')}',isShowClear:false});" />
        </li>


        <li class="btns"><input id="btnSubmit" class="btn btn-primary"
                                type="button" value="查询" onclick="searchButton()" /></li>
        <li class="btns"><input class="btn btn-primary clearBtn"
                                type="button" value="清空" onclick="clearCondition()" /></li>
        <li class="btns"><input class="btn btn-primary" type="button"
                                value="导出excel" onclick="exportExcel()" /></li>

        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}" />
<table id="contentTable"
       class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>门店</th>
        <th>生成月度结算时间</th>
        <th>月度</th>
        <th>订单号</th>
        <th>客户</th>
        <th>项目经理</th>
        <th>中期结算合计金额</th>
        <th>竣工结算合计金额</th>
        <th>实际结算合计</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="settleSummaryBill">
        <tr>
            <td>${fns:getStoreLabel(settleSummaryBill.storeId, '')}</td>
            <td><fmt:formatDate value="${settleSummaryBill.createDate}" pattern="yyyy-MM-dd"/></td>
            <td>${settleSummaryBill.settleMonth}</td>
            <td>${settleSummaryBill.orderNumber}</td>
            <td>${settleSummaryBill.customerName}</td>
            <td>${settleSummaryBill.pmEmployeeName}</td>
            <td><a href="#" onclick="querySettleBillInfo(${settleSummaryBill.id},1,${settleSummaryBill.orderId})">${settleSummaryBill.midwayRealSettleAmount}</a></td>
            <td><a href="#" onclick="querySettleBillInfo(${settleSummaryBill.id},2,${settleSummaryBill.orderId})">${settleSummaryBill.completeRealSettleAmount}</a></td>
            <td>${settleSummaryBill.realSettleAmount}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>