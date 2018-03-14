<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>不合格原因统计</title>
    <meta name="decorator" content="default" />

<script type="text/javascript">
    $(document).ready(function() {
        findInstallName('1');
    });

    function findInstallName(type){
        var html2='<option value=""></option>';
        var html3='<option value=""></option>';
        var storeId = $("#storeId").val();
        var projectModeId = $("#projectMode").val();

        if (storeId=="" ||projectModeId=="" ||undefined==storeId ||undefined==projectModeId) {
            return;
        }
        $.ajax({
            url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,
            success: function(data){

                if(null!=data&&data.length>0){
                    if ("1" == type){
                        for (var v = 0; v < data.length; v++) {

                            if(data[v].isOn == 1){
                                //启用
                                if('${bizOrderInstallPlan.orderInstallItemId}' == data[v].id){
                                    $("#s2id_orderInstallItemId .select2-chosen").html(data[v].installItemName);
                                    html2 = html2 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
                                }else{
                                    html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
                                }
                            }else{
                                //停用
                                if('${bizOrderInstallPlan.orderInstallItemIdStop}' == data[v].id){
                                    $("#s2id_orderInstallItemIdStop .select2-chosen").html(data[v].installItemName);
                                    html3 = html3 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
                                }else{
                                    html3 = html3 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
                                }
                            }
                        }
                    }else{
                        for (var v = 0; v < data.length; v++) {

                            if(data[v].isOn == 1){
                                //启用
                                html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
                            }else{
                                //停用
                                html3 = html3 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
                            }
                        }
                    }


                    $("#orderInstallItemId").html(html2);
                    $("#orderInstallItemIdStop").html(html3);
                } else {
                    $("#orderInstallItemId").html(html2);
                    $("#orderInstallItemIdStop").html(html3);
                }
            }
        })
    }


</script>
</head>

<body>

<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/bizorderinstallplan/orderinstallacceptquery/unqualifiedResonCount">订单安装项不合格原因统计</a></li>
</ul>
<form:form id="searchForm" modelAttribute="bizOrderInstallPlan"
           action="${ctx}/bizorderinstallplan/orderinstallacceptquery/unqualifiedResonCount"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>门店：</label>
            <form:select path="storeId" class="input-medium needClear" onchange="findInstallName('2')">
                <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>工程模式：</label>
            <c:if test="${empty gongcheng}">
                <form:select path="projectMode" class="input-medium needClear" onchange="findInstallName('2')">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value"
                                  htmlEscape="false"/>
                </form:select>
            </c:if>
            <c:if test="${!empty gongcheng}">
                <form:select path="projectMode" class="input-medium needClear" onchange="findInstallName('2')">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value"
                                  htmlEscape="false"/>
                </form:select>
            </c:if>
        </li>
        <li><label>安装项名称：</label>
            <form:select path="orderInstallItemId" class="input-medium needClear">
            </form:select>
        </li>
        <li><label style="width:140px;">安装项名称（停用）：</label>
            <form:select path="orderInstallItemIdStop" class="input-medium needClear">
            </form:select>
        </li>
        <li><label style="width: 120px;">验收不合格原因：</label>
            <form:input path="unqualifiedReasonConfigure" htmlEscape="false" maxlength="50" class="input-medium needClear"/>
        </li>

        <li><label style="width: 120px;">提交时间：</label>
            <input name="beginUnqualifiedAcceptTime" type="datetime" id="beginUnqualifiedAcceptTime" readonly="readonly"
                   maxlength="20" class="input-medium Wdate needClear"
                   value="<fmt:formatDate value="${bizOrderInstallPlan.beginUnqualifiedAcceptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endUnqualifiedAcceptTime\')}',isShowClear:false});"/>
            &nbsp;至&nbsp;

            <input name="endUnqualifiedAcceptTime" type="datetime" id="endUnqualifiedAcceptTime" readonly="readonly"
                   maxlength="20" class="input-medium Wdate needClear"
                   value="<fmt:formatDate value="${bizOrderInstallPlan.endUnqualifiedAcceptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginUnqualifiedAcceptTime\')}',isShowClear:false});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/>
        </li>
    </ul>
</form:form>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>门店</th>
        <th>工程模式</th>
        <th>主材安装项</th>
        <th>验收不合格原因</th>
        <th>提交不合格次数</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${page.list}" var="plan">
        <tr>
        <td>${fns:getStoreLabel(plan.storeId,"")}</td>
        <td>${fns:getDictLabel(plan.projectMode, 'project_mode', '')}</td>
        <td>${plan.installItemName}</td>
        <td>${plan.unqualifiedReasonConfigure}</td>
        <td>${plan.unqualifiedInstallItemCount }</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>