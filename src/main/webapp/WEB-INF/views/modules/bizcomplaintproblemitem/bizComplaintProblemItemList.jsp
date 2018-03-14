<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>投诉分类项管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            var typeId = "${bizComplaintProblemItem.complaintProblemTypeId}";
            if (undefined != typeId && "" != typeId) {

                findTypeMapByStoreId(typeId);
            }
        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

        function findTypeMapByStoreId(typeId) {
            var storeId = $("#storeId").val();
            var html = '<option value=""></option>';
            if (undefined != storeId && "" != storeId) {
                $.ajax({
                    url: "${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/findTypeMapByStoreId",
                    data: {storeId: storeId},
                    type: "get",
                    success: function (data) {

                        var length = data.length;

                        for (var x = 0; x < length; x++) {
                            if (data[x].typeId == typeId) {
                                $("#s2id_complaintProblemTypeId").find(".select2-chosen").text(data[x].typeName);
                                $("#complaintProblemTypeId").val(data[x].typeId);
                                html += '<option value="' + data[x].typeId + '"  selected="selected">' + data[x].typeName + '</option>';
                            } else {

                                html += '<option value="' + data[x].typeId + '">' + data[x].typeName + '</option>';
                            }


                        }

                        $("#complaintProblemTypeId").html(html);

                    }


                })


            }


        }
        function clearCondition() {
            $("#s2id_complaintProblemTypeId").find(".select2-chosen").text("");
            $("#complaintProblemTypeId").val("");

        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/">投诉分类项列表</a></li>
    <shiro:hasPermission name="bizcomplaintproblemitem:bizComplaintProblemItem:edit">
        <li><a href="${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/form">投诉分类项添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="bizComplaintProblemItem"
           action="${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>门店：</label>
            <form:select path="storeId" class="input-medium needClear" onchange="findTypeMapByStoreId(0)">
                <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>问题分类：</label>
            <select class="input-medium" name="complaintProblemTypeId" id="complaintProblemTypeId">
                <!--动态插入 -->


            </select>
        </li>


        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
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
        <th>问题分类</th>
        <th>对应任务包</th>
        <th>处理人员</th>
        <th>问题事项</th>
        <th>响应时间(分)</th>
        <th>执行时限(天)</th>
        <th>扣分</th>
        <th>罚款</th>
        <th>备注</th>
        <th>状态</th>
        <shiro:hasPermission name="bizcomplaintproblemitem:bizComplaintProblemItem:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="bizComplaintProblemItem">
        <tr>
            <td>
                    ${fns:getStoreLabel(bizComplaintProblemItem.storeId, '')}
            </td>


            <td>
                    ${bizComplaintProblemItem.typeName}
            </td>
            <td>
                    ${bizComplaintProblemItem.packName}
            </td>
            <td>
                    ${fns:getDictLabel(bizComplaintProblemItem.dealPersonType, 'deal_person_type', '')}
            </td>
            <td>
                    ${bizComplaintProblemItem.itemName}
            </td>
            <td>
                    ${bizComplaintProblemItem.responseTime}
            </td>
            <td>
                    ${bizComplaintProblemItem.executeTimeLimit}
            </td>
            <td>
                    ${bizComplaintProblemItem.deductScore}
            </td>

            <td>
                    ${bizComplaintProblemItem.punishMoney}
            </td>

            <td>
                    ${bizComplaintProblemItem.itemRemarks}
            </td>
            <td>
                    ${fns:getDictLabel(bizComplaintProblemItem.isEnabled, 'biz_enable_status', '')}
            </td>


            <shiro:hasPermission name="bizcomplaintproblemitem:bizComplaintProblemItem:edit">
                <td>
                    <a href="${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/form?id=${bizComplaintProblemItem.id}">修改</a>

                    <c:if test="${bizComplaintProblemItem.isEnabled==1}">
                        <a href="${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/delete?id=${bizComplaintProblemItem.id}&isEnabled=0"
                           onclick="return confirmx('确认要停用该问题事项吗？', this.href)">停用</a>

                    </c:if>

                    <c:if test="${bizComplaintProblemItem.isEnabled==0}">
                        <a href="${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/delete?id=${bizComplaintProblemItem.id}&isEnabled=1"
                           onclick="return confirmx('确认要启用该问题事项吗？', this.href)">启用</a>

                    </c:if>

                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>