<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>检查项工人组和项目经理管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            findEngineListByStoreIdAndProjectMode();
            $("#searchForm").validate({
                errorPlacement: function (error, element) {
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            })
        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }


        function findEngineListByStoreIdAndProjectMode() {
            var html3 = '<option value=""></option>';
            var storeId = $("#sel").val();
            var projectModeValue = $("#projectMode").val();
            if (storeId == "" || projectModeValue == "" || undefined == storeId || undefined == projectModeValue) {
                return;
            }
            //根据门店个,工程模式    动态加载工程区域
            $.ajax({
                url: "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
                + storeId + "&projectModeValue=" + projectModeValue,
                type: "get",
                success: function (data) {
                    if (null != data && data.length > 0) {
                        for (var v = 0; v < data.length; v++) {
                            if ('${qcItemGroupManger.enginDepartId}' == data[v].engineDepartId) {
                                $("#s2id_engineDepartSelect .select2-chosen").html(data[v].engineDepartName);
                                html3 = html3 + "<option value='" + data[v].engineDepartId + "' selected='selected'>" + data[v].engineDepartName + "</option>";
                            } else {
                                html3 = html3 + "<option value='" + data[v].engineDepartId + "'>" + data[v].engineDepartName + "</option>";
                            }
                        }
                        $("#engineDepartSelect").html(html3);
                    } else {
                        $("#engineDepartSelect").html(html3);
                    }

                }

            });
        }


        //清除方法
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
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/qualitygroupmanger/qcItemGroupManger/listManagerall">检查项工人组和项目经理列表</a></li>
    <shiro:hasPermission name="qualitygroupmanger:qcItemGroupManger:edit">
        <li><a href="${ctx}/qualitygroupmanger/qcItemGroupManger/form">检查项工人组和项目经理添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="qcItemGroupManger"
           action="${ctx}/qualitygroupmanger/qcItemGroupManger/listManagerall" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label><font color="red">*</font>门店：</label>
            <form:select path="storeId" id="sel" class="input-medium needClear required"
                         onchange="findEngineListByStoreIdAndProjectMode()">
                <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </li>
        <li><label><font color="red">*</font>工程模式：</label>
            <form:select path="projectMode" id="projectMode" class="input-medium needClear required"
                         onchange="findEngineListByStoreIdAndProjectMode()">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>

        <li><label><font color="red">*</font>区域：</label>
            <form:select path="enginDepartId" class="input-medium needClear required" id="engineDepartSelect">
                <%-- <form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
            </form:select>
        </li>
        <li><label>项目经理</label>
            <form:input path="mnagerPerson" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
        </li>
        <li><label>提报告时间：</label>
            <input name="beginCreateDate" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate  needClear"
                   value="<fmt:formatDate value="${qcItemGroupManger.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
            <input name="endCreateDate" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate  needClear"
                   value="<fmt:formatDate value="${qcItemGroupManger.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </li>

        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="btns"><input id="btnSubmitclean" class="btn btn-primary clearBtn" type="button" value="清除"
                                onclick="clearCondition()"/></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>门店</th>
        <th>工程模式</th>
        <th>区域</th>
        <th>项目经理</th>
        <th>项目经理手机号</th>
        <th>违规次数</th>
        <th>被罚款次数</th>
        <th>被罚款金额</th>
        <shiro:hasPermission name="qualitygroupmanger:qcItemGroupManger:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="qcItemGroupManger">
        <tr>
            <td>
                <a href="${ctx}/qualitygroupmanger/qcItemGroupManger/form?id=${qcItemGroupManger.id}">
                        ${fns:getDictLabel(qcItemGroupManger.qcBillId, '', '')}
                </a>
                    ${qcItemGroupManger.storeName}
            </td>

            <td>
                    ${qcItemGroupManger.projectModeName}
            </td>
            <td>

                    ${qcItemGroupManger.enginDepartName}
            </td>

            <td>
                    ${qcItemGroupManger.mnagerPerson}
            </td>
            <td>
                    ${qcItemGroupManger.mnagerPersonPhone}
            </td>
            <td>
                <a href="${ctx}/qualitygroupmanger/qcItemGroupManger/queryQcItemMangerIllegalDetails?projectManagerId=${qcItemGroupManger.projectManagerId}&mnagerPersonIllegalCount=${qcItemGroupManger.mnagerPersonIllegalCount}&mnagerPerson=${qcItemGroupManger.mnagerPerson}">
                        ${qcItemGroupManger.mnagerPersonIllegalCount}
                </a>
            </td>

            <td>


                <a href="${ctx}/qualitygroupmanger/qcItemGroupManger/queryQcItemMangerPunishDetails?projectManagerId=${qcItemGroupManger.projectManagerId}&mnagerPersonPunishCount=${qcItemGroupManger.mnagerPersonPunishCount}&mnagerPerson=${qcItemGroupManger.mnagerPerson}">
                        ${qcItemGroupManger.mnagerPersonPunishCount}
                </a>
            </td>
            <td>
                <a href="${ctx}/qualitygroupmanger/qcItemGroupManger/queryQcItemMangerPunishDetails?projectManagerId=${qcItemGroupManger.projectManagerId}&mnagerPersonPunishCount=${qcItemGroupManger.mnagerPersonPunishCount}&mnagerPerson=${qcItemGroupManger.mnagerPerson}">
                        ${qcItemGroupManger.punishMoneyAmountRealSum}
                </a>
            </td>

            <shiro:hasPermission name="qualitygroupmanger:qcItemGroupManger:edit">
                <td>
                    <a href="${ctx}/qualitygroupmanger/qcItemGroupManger/form?id=${qcItemGroupManger.id}">修改</a>
                    <a href="${ctx}/qualitygroupmanger/qcItemGroupManger/delete?id=${qcItemGroupManger.id}"
                       onclick="return confirmx('确认要删除该检查项工人组和项目经理吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>