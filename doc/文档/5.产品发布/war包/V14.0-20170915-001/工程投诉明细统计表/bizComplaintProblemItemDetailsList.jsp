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
    <li class="active"><a href="#">工程投诉明细统计</a></li>
</ul>
<form:form id="searchForm" modelAttribute="bizComplaintProblemItem"
           action="${ctx}/bizcomplaintproblemitem/bizComplaintProblemItem/list1" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>门店：</label>
            <form:select path="storeId" class="input-medium needClear" onchange="findTypeMapByStoreId(0)">
                <form:option value="" label=""/>
                <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>问题分类：</label>
            <select class="input-medium" name="complaintProblemTypeId" id="complaintProblemTypeId">
                <!--动态插入 -->


            </select>
        </li>
		<li><label>创建时间：</label>
			<input name="startCreateTime" type="text" id="startCreateTime" readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizComplaintProblemItem.startCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateTime\')}',isShowClear:false});"/> &nbsp;至&nbsp;
				
			<input name="endCreateTime" type="text" id="endCreateTime" readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizComplaintProblemItem.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startCreateTime\')}',isShowClear:false});"/>
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
        <th>问题事项</th>
       	<th>出现次数</th>
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
                    ${bizComplaintProblemItem.itemName}
            </td>
            <td>${bizComplaintProblemItem.complaintProblemTimes}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>