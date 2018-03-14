<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>订单地板地砖面积查询</title>
    <meta name="decorator" content="default"/>
    <link href="${ctxStatic}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${ctxStatic}/vendor/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/vendor/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet" type="text/css">
    <link href="${ctxStatic}/modules/popUp/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/modules/popUp/css/swiper.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctxStatic}/vendor/jquery/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/vendor/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${ctxStatic}/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/vendor/jquery/swiper.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            getDepartments();
        });

        function getDepartments(){
            $.ajax({
                url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
                type:'post',
                dataType : 'json',
                data:{
                    'storeId':$('#storeId').val(),
                    'projectMode':$('#projectMode').val(),
                    'employeeId':$('#employeeId').val()
                },
                success:function(data){
                    if(data == null || data == ""){
                        $("#enginDepartId").html("");
                        $("#s2id_enginDepartId .select2-chosen").html("");
                    }else{
                        var content = "<option></option>";
                        for(var i=0;i<data.length;i++){
                            if('${orderFloor.enginDepartId}' == data[i].value){
                                $("#s2id_enginDepartId .select2-chosen").html(data[i].label);
                                content = content + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
                            }else{
                                content = content + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
                            }
                        }
                        $("#enginDepartId").html(content);
                    }
                }
            });
        }

        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

        function clearCondition(){
            // document.getElementById("searchForm").reset();

            var inputObjs=jQuery("#searchForm input[type='text']");
            for(var i=0;i<inputObjs.length;i++){
                var inputObj = inputObjs[i];
                inputObj.value="";
            }

            /*var selectObjs = jQuery("#searchForm input[class='input-medium']");
             for(var i=0;i<selectObjs.length;i++){
             var selectObj = selectObjs[i];
             selectObj.value="";
             }*/
        }

        /**
         *
         * @param orderId  订单Id
         * @param customerInfo 客户信息
         * @param type  1：木地板面积详情  2：地砖预算/结算面积详情
         */
        function queryProcedureInfo(orderTaskPackageId, customerInfo, type) {
            var info = "";
            if(type == 1){
                info = "木地板面积详情";
            }else if(type = 2){
                info = "地砖预算/结算面积详情";
            }
             $("#myModalLabel").html(info);
            $("#customerInfo").html(customerInfo);
            $.ajax({
                url:'${ctx}/orderfloor/orderFloor/queryProduceInfoByParam?taskpackageId='+orderTaskPackageId+'&type='+type,
                type:'POST',
                success:function (data) {
                    if(data != null && data.length >0){
                        var trHtml="";
                        for(var i = 0;i<data.length;i++){
                            trHtml=trHtml+"<tr class='row'>";
                            trHtml=trHtml+"<td>"+data[i].procedureNo+"</td>";
                            trHtml=trHtml+"<td>"+data[i].procedureName+"</td>";
                            trHtml=trHtml+"<td>"+data[i].measurementUnitLabel+"</td>";
                            trHtml=trHtml+"<td>"+data[i].budgetNumber+"</td>";
                            trHtml=trHtml+"<td>"+data[i].realNumber+"</td>";
                            trHtml=trHtml+"<td>"+data[i].recheckNumber+"</td>";
                            trHtml=trHtml+"<td>"+data[i].settlementNumber+"</td>";
                            trHtml=trHtml+"</tr>";
                        }
                        $("#produceTbody").html(trHtml);
                    }
                }
            });
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/orderfloor/orderFloor/queryOrderFloor">订单地板地砖面积查询</a></li>
</ul>

<form:form id="searchForm" modelAttribute="orderFloor" action="${ctx}/orderfloor/orderFloor/queryOrderFloor" method="post" class="breadcrumb form-search">
    <input type="hidden" id="employeeId" name="employeeId" value="${employeeId}">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden"
           value="${page.pageSize}"/>
    <ul class="ul-form">

        <li><label>门店：</label>
            <c:if test="${empty storeDropEnable}">
                <form:select path="storeId" class="input-medium" disabled="true" id="storeId" onchange="getDepartments()">
                    <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
            <c:if test="${!empty storeDropEnable}">
                <form:select path="storeId" class="input-medium needClear" id="storeId" onchange="getDepartments()">
                    <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
        </li>

        <li><label>工程模式：</label>
            <c:if test="${empty gongcheng}">
                <form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
            <c:if test="${!empty gongcheng}">
                <form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
        </li>

      <li><label>区域：</label>
            <select id="enginDepartId" name="enginDepartId" class="input-medium needClear">

            </select>
        </li>


        <li>
            <label>订单编号：</label>
            <form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium needClear" cssStyle="height: 28px;"/>
        </li>

        <li>
            <label>客户姓名：</label>
            <form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear" cssStyle="height: 28px;"/>
        </li>

        <li>
            <label>项目经理：</label>
            <form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear" cssStyle="height: 28px;"/>
        </li>

        <li><label>付款完成时间：</label>
            <input name="startDate" id="startDate" type="text" readonly="readonly" style="height: 28px;" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${orderFloor.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/> "
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true});"/> 至
            <input name="endDate" id="endDate" type="text" readonly="readonly" style="height: 28px;" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${orderFloor.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
        <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
        <li class="btns">
            <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#myModal-notice">面积取值说明</a>
        </li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>门店</th>
        <th>泥瓦工程任务包首款付款完成时间</th>
        <th>工程模式</th>
        <th>区域</th>
        <th>订单编号</th>
        <th>小区</th>
        <th>客户</th>
        <th>地址</th>
        <th>木地板面积（㎡）
        </th>
        <th>地砖预算面积（㎡）
        </th>
        <th>地砖结算面积（㎡）
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="orderFloor">
        <tr>
            <td>${fns:getStoreLabel(orderFloor.storeId, '')}</td>
            <td><fmt:formatDate value="${orderFloor.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${fns:getDictLabel(orderFloor.projectMode, 'package_project_mode', '')}</td>
            <td>${orderFloor.enginDepartName}</td>
            <td>${orderFloor.orderNumber}</td>
            <td>${orderFloor.communityName}-${orderFloor.buildNumber}-${orderFloor.buildUnit}-${orderFloor.buildRoom}</td>
            <td>${orderFloor.customerName}</td>
            <td>${orderFloor.customerAddress}</td>
            <td>
                <c:if test="${orderFloor.floorSettleArea>0}"><a href="#"
                                                                onclick="queryProcedureInfo(${orderFloor.orderTaskPackageId},'${orderFloor.communityName}-${orderFloor.buildNumber}-${orderFloor.buildUnit}-${orderFloor.buildRoom}-${orderFloor.customerName}',1)"
                                                                data-toggle="modal"
                                                                data-target="#myModal-view">${orderFloor.floorSettleArea}</a></c:if>
                <c:if test="${orderFloor.floorSettleArea<=0}">${orderFloor.floorSettleArea}</c:if>
            </td>

            <td>
                        <c:if test="${orderFloor.floorTileBudgetArea>0}"><a href="#"
                                                                        onclick="queryProcedureInfo(${orderFloor.orderTaskPackageId},'${orderFloor.communityName}-${orderFloor.buildNumber}-${orderFloor.buildUnit}-${orderFloor.buildRoom}-${orderFloor.customerName}',2)"
                                                                        data-toggle="modal"
                                                                        data-target="#myModal-view">${orderFloor.floorTileBudgetArea}</a></c:if>
                        <c:if test="${orderFloor.floorTileBudgetArea<=0}">${orderFloor.floorTileBudgetArea}</c:if>
            </td>
            <td>
                <c:if test="${orderFloor.floorTileSettleArea>0}"><a href="#"
                                                                    onclick="queryProcedureInfo(${orderFloor.orderTaskPackageId},'${orderFloor.communityName}-${orderFloor.buildNumber}-${orderFloor.buildUnit}-${orderFloor.buildRoom}-${orderFloor.customerName}',2)"
                                                                    data-toggle="modal"
                                                                    data-target="#myModal-view">${orderFloor.floorTileSettleArea}</a></c:if>
                <c:if test="${orderFloor.floorTileSettleArea<=0}">${orderFloor.floorTileSettleArea}</c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
<div class="pagination">${page}</div>

<!--面积详情-->
<div class="modal _large fade disN" id="myModal-view" tabindex="-4" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                <h4 class="modal-title text-center" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <h4 class="modal-title text-center" id="customerInfo"></h4>
                <table class="table table-striped table-bordered table-condensed"
                       style="display:inline-block; height: 300px; overflow-y: scroll">
                    <thead>
                    <tr class="row">
                        <th>工序编号</th>
                        <th>工序名称</th>
                        <th>单位</th>
                        <th>预算工程量</th>
                        <th>实际工程量</th>
                        <th>复核工程量</th>
                        <th>结算工程量</th>
                    </tr>
                    </thead>
                    <tbody id="produceTbody">
                    <%--<tr class="row">--%>
                        <%--<td>--%>
                            <%--GX000041--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--水泥砂浆地面找平施工--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--㎡--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--35.0--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--35.0--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--大美装饰管理平台窗帘--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--35.0--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<!-- 面积取值说明 -->
<div class="modal fade _large disN" id="myModal-notice" tabindex="-2" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                <h4 class="modal-title text-center" id="myModalLabel">面积取值说明</h4>
            </div>
            <div class="modal-body">
                <form action="" role="form">
                    <fieldset>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="clearfix">
                                    <div class="txt-left pull-left">木&nbsp;地&nbsp;板&nbsp;面&nbsp;积：</div>
                                    <div class="txt-right pull-left">泥瓦工程任务包结算单中的1个工序【GX000041-水泥砂浆地面找平施工】的【结算工程量】</div>
                                </div>
                                <div class="margin-top10 clearfix">
                                    <div class="txt-left pull-left">地砖预算面积：</div>
                                    <div class="txt-right pull-left">
                                        <p>泥瓦工程任务包结算单中的5个工序【GX000264--地砖铺贴（宽边＜200mm）、</p>
                                        <p>GX000161--地砖铺贴（200≦宽边＜300mm）、</p>
                                        <p>GX000100--地砖铺贴（宽边≥300mm）、</p>
                                        <p>GX000267--地砖斜铺/拼花（宽边
                                            < 450）、</p>
                                        <p>GX000266--地砖斜铺/拼花（宽边≥450）】的【<span class="text-red">预算工程量</span>】之和</p>
                                    </div>
                                </div>
                                <div class="margin-top10 clearfix">
                                    <div class="txt-left pull-left">地砖结算面积：</div>
                                    <div class="txt-right pull-left">
                                        <p>泥瓦工程任务包结算单中的5个工序【GX000264--地砖铺贴（宽边＜200mm）、</p>
                                        <p>GX000161--地砖铺贴（200≦宽边＜300mm）、</p>
                                        <p>GX000100--地砖铺贴（宽边≥300mm）、</p>
                                        <p>GX000267--地砖斜铺/拼花（宽边
                                            < 450）、</p>
                                        <p>GX000266--地砖斜铺/拼花（宽边≥450）】的【<span class="text-red">结算工程量</span>】之和</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.form-group -->
                        <div class="form-btn text-center">
                            <button type="button" class="btn btn-primary col-md-3" data-dismiss="modal" aria-label="Close">返回</button>
                        </div>
                        <!-- /.form-btn -->
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>