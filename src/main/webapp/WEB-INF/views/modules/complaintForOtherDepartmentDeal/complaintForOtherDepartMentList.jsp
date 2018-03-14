<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>投诉管理</title>
    <meta name="decorator" content="default"/>
    <style type="text/css">
        .mask-text {
            resize: none;
            width: 280px;
            padding: 5px;
            box-sizing: border-box;
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function () {


            $("tbody>tr").click(function () {

                $(this).find('td').css('background', "orange").parents('tr').siblings().find('td').css('background', "#f5f5f5");

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


        var commonPreComplaintId = 0;
        var obj = null;
        function accept(preComplaintId, objPara) {
            obj = objPara;
            commonPreComplaintId = preComplaintId;
            $("#myAbandonedModal").modal('show');
        }
        function reject(preComplaintRejectId, objPara) {

            commonPreComplaintId = preComplaintRejectId;
            obj = objPara;
            $("#myAbandonedModalReject").modal('show');
        }

        function closeModel() {
            $("#myAbandonedModal").modal('hide');
            $("#myAbandonedModalReject").modal('hide');

        }

        function sendRejectAjax() {

            var rejectText = $("#resonReject").val();
            //30已拒绝
            var status = "30";
            $.ajax({
                url: "${ctx}/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal/updatePreComplaintStatus",
                data: {id: commonPreComplaintId, text: rejectText, status: status},
                type: "post",
                success: function (data) {

                    if (data == 1) {
                        $(obj).parent().prev().text("已驳回")
                        $("#resonReject").val("");
                        layer.msg("拒绝成功~", {icon: 1});
                        $(obj).parent().html('  <a href="${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/detailLog?id=' + commonPreComplaintId + '">详情</a>')


                    } else {

                        layer.msg("网络有点不好,请刷新试试看", {icon: 1});

                    }
                }


            })
            closeModel();
        }

        function sendAcceptAjax() {


            var acceptReson = $("#reson").val();

            //40已处理
            var status = "40";
            $.ajax({
                url: "${ctx}/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal/updatePreComplaintStatus",
                data: {id: commonPreComplaintId, text: acceptReson, status: status},
                type: "post",
                success: function (data) {

                    if (data == 1) {
                        $(obj).parent().prev().text("已处理")
                        $("#reson").val("");
                        layer.msg("成功接收~", {icon: 1});
                        $(obj).parent().html('  <a href="${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/detailLog?id=' + commonPreComplaintId + '">详情</a>')


                    } else {

                        layer.msg("网络有点不好,请刷新试试看", {icon: 1});

                    }
                }


            })
            closeModel();

        }


    </script>
    <script type="text/javascript" src="${ctxStatic}/modules/settleJs/layer/layer.js"></script>
    <script src="${ctxStatic}/modules/settleJs/layer/extend/layer.ext.js"></script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal/">投诉列表</a></li>

</ul>
<form:form id="searchForm" modelAttribute="complaintForOtherDepartMentDeal"
           action="${ctx}/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal/list" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <li><label>门店：</label>
        <form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()"
                     id="sel">
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
    <li><label>区域：</label>
        <form:select path="departId" class="input-medium needClear" id="engineDepartSelect">
            <form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}" itemLabel="label"
                          itemValue="value" htmlEscape="false"/>
        </form:select>
    </li>
    <li><label>订单编号：</label>
        <form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
    </li>


    <li><label>状态：</label>
        <form:select path="status" class="input-medium needClear">
            <form:option value="" label=""/>
            <form:options items="${fns:getDictList('preComplaintStatus')}" itemLabel="label" itemValue="value"
                          htmlEscape="false"/>
        </form:select>


    </li>


    <li><label>客户姓名：</label>
        <form:input path="customerName" htmlEscape="false" maxlength="20" class="input-medium"/>
    </li>


    <li><label>项目经理：</label>
        <form:input path="managerName" htmlEscape="false" maxlength="20" class="input-medium"/>
    </li>


    <li><label>问题创建时间：</label>
        <input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
               value="<fmt:formatDate value="${complaintForOtherDepartMentDeal.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true});"/>
        至
        <input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
               value="<fmt:formatDate value="${complaintForOtherDepartMentDeal.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true});"/>
    </li>


    <li><label>投诉来源：</label>
        <form:select path="complaintSource" class="input-medium needClear">
            <form:option value="" label=""/>
            <form:options items="${fns:getDictList('complaintSource')}" itemLabel="label" itemValue="value"
                          htmlEscape="false"/>
        </form:select>


    </li>

    <ul class="ul-form">
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
        <th>订单编号</th>
        <th>工程模式</th>
        <th>区域</th>
        <th>地址</th>
        <th>客户姓名</th>
        <th>客户手机号</th>
        <th>项目经理</th>
        <th>项目经理手机号</th>
        <th>问题创建时间</th>
        <th>投诉人</th>
        <th>状态</th>
        <shiro:hasPermission name="complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="complaintForOtherDepartMent">
        <tr>
            <td>

                    ${fns:getStoreLabel(complaintForOtherDepartMent.storeId,'' )}

            </td>
            <td>${complaintForOtherDepartMent.orderNumber}</td>

            <td>
                    ${fns:getDictLabel(complaintForOtherDepartMent.projectMode,'project_mode','' )}

            </td>

            <td>${complaintForOtherDepartMent.departName}</td>
            <td>${complaintForOtherDepartMent.customerAddress}</td>
            <td>${complaintForOtherDepartMent.customerName}</td>
            <td>${complaintForOtherDepartMent.customerPhone}</td>
            <td>${complaintForOtherDepartMent.managerName}</td>
            <td>${complaintForOtherDepartMent.managerPhone}</td>
            <td><fmt:formatDate value="${complaintForOtherDepartMent.createDate}"
                                pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            <td>${complaintForOtherDepartMent.complaintPersonName}</td>

            <td>
                    ${fns:getDictLabel(complaintForOtherDepartMent.status,'preComplaintStatus','' )}

            </td>

            <shiro:hasPermission name="complaintforotherdepartmentDeal:complaintForOtherDepartMentDeal:edit">
                <td>

                    <c:if test="${complaintForOtherDepartMent.status==10}">
                        <a href="${ctx}/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal/add?id=${complaintForOtherDepartMent.id}">接收</a>
                        <a onclick="reject('${complaintForOtherDepartMent.id}',this)" href="#">驳回</a>
                        <a onclick="accept('${complaintForOtherDepartMent.id}',this)" href="#">处理</a>
                        <a href="${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/detailLog?id=${complaintForOtherDepartMent.id}">详情</a>

                    </c:if>


                    <c:if test="${complaintForOtherDepartMent.status!=10}">

                        <a href="${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/detailLog?id=${complaintForOtherDepartMent.id}">详情</a>

                    </c:if>

                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>


<!-- 处理btn弹框的model -->
<div class="modal fade" id="myAbandonedModal" tabindex="-1" role="dialog" style="width:350px">

    <div class="modal-header">
        <button class="close" type="button" data-dismiss="modal">×</button>
        <h4 id="myModalLabel" align="center" style="color: black;">处理内容</h4><br>
        <div style="margin:10px;min-height:22px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
            <textarea id="reson" class="mask-text" onkeyup="this.value = this.value.substring(0,100)"
                      placeholder="请输入处理内容，多行输入，不多于100个汉字" maxlength="100"></textarea>
            <a href="javascript:void(0)" class="btn btn-primary" onclick="sendAcceptAjax()">确定</a>
            <a href="javascript:void(0)" class="btn" onclick="closeModel()">关闭</a>
        </div>
    </div>

</div>
<!-- 驳回btn弹框的model -->
<div class="modal fade" id="myAbandonedModalReject" tabindex="-1" role="dialog" style="width:350px">

    <div class="modal-header">
        <button class="close" type="button" data-dismiss="modal">×</button>
        <h4 align="center" style="color: black;">驳回内容</h4><br>


        <div style="margin:10px;min-height:20px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
            <textarea id="resonReject" class="mask-text" onkeyup="this.value = this.value.substring(0,100)"
                      placeholder="请输入驳回内容，多行输入，不多于100个汉字" maxlength="100"></textarea>
            <a href="javascript:void(0)" class="btn btn-primary" onclick="sendRejectAjax()">确定</a>
            <a href="javascript:void(0)" class="btn" onclick="closeModel()">关闭</a>
        </div>
    </div>

</div>


</body>
</html>