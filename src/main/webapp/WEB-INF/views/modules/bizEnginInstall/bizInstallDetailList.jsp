<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>安装项明细查询</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {

        });

        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        // --全选框被单击---
        function ChkAllClick(sonName, cbAllId){
            var arrSon = document.getElementsByName(sonName);
            var cbAll = document.getElementById(cbAllId);
            var tempState=cbAll.checked;
            for(i=0;i<arrSon.length;i++) {
                if(arrSon[i].checked!=tempState)
                    arrSon[i].click();
            }
        }
        function ChkSonClick(sonName, cbAllId) {
            var arrSon = document.getElementsByName(sonName);
            var cbAll = document.getElementById(cbAllId);
            for(var i=0; i<arrSon.length; i++) {
                if(!arrSon[i].checked) {
                    cbAll.checked = false;
                    return;
                }
            }
            cbAll.checked = true;
        }

        window.onload = function(){

            var obj=document.getElementsByName('installStatus');
            var s='';
            for(var i=0; i<obj.length; i++){
                if(!obj[i].checked){
                    s+="11"+obj[i].value+',';  //如果选中，将value添加到变量s中
                }
            }
            if(s==null || s==""){
                $("#chkAll").prop("checked", "checked");
            }
        }




        //清空查询条件
        function clearCondition(){
            document.getElementById("searchForm").reset();

            var inputObjs=jQuery("#searchForm input[type='text']");
            for(var i=0;i<inputObjs.length;i++){
                var inputObj = inputObjs[i];
                inputObj.value="";
            }

            var selectObjs = jQuery("#searchForm input[class='input-medium']");
            for(var i=0;i<selectObjs.length;i++){
                var selectObj = selectObjs[i];
                selectObj.value="";
            }
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/bizorderinstalldetail/bizOrderInstallDetail/preList">安装项明细列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="bizOrderInstallDetail" action="${ctx}/bizorderinstalldetail/bizOrderInstallDetail/list" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
            <%-- <li><label>门店：</label>
                <form:select path="storeId" class="input-medium needClear" id="storeId" onchange="nextValue()">
                    <form:option value="" label="" />
                    <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
            </li> --%>
        <li><label>门店：</label>
            <c:if test="${empty storeDropEnable}">
                <form:select path="storeId" class="input-medium" disabled="true">
                   <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
            <c:if test="${!empty storeDropEnable}">
                <form:select path="storeId" class="input-medium needClear">
                   <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
        </li>
        <li><label>工程模式：</label>
            <c:if test="${empty gongcheng}">
                <form:select path="projectMode" class="input-medium" disabled="true">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
            <c:if test="${!empty gongcheng}">
                <form:select path="projectMode" class="input-medium needClear">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </c:if>
        </li>
        <li><label>安装项名称：</label>
            <form:input path="installItemName" htmlEscape="false" maxlength="64" class="input-medium"/>
        </li>
        <li><label>订单编号：</label>
            <form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
        </li>
        <li><label>客户姓名：</label>
            <form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
        </li>
        <li><label>项目经理：</label>
            <form:input path="employeeRealName" htmlEscape="false" maxlength="64" class="input-medium"/>
        </li>
        <li><label>申请日期：</label>
            <input name="installCreateDateStart" type="text" id="installCreateDateStart" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.installCreateDateStart}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installCreateDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;

            <input name="installCreateDateEnd" type="text" id="installCreateDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.installCreateDateEnd}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installCreateDateStart\')}',isShowClear:false});"/>
        </li>
        <li><label>期望进场日期：</label>
            <input name="installApplyIntoDateStart" type="text" id="installApplyIntoDateStart" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.installApplyIntoDateStart}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installApplyIntoDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;

            <input name="installApplyIntoDateEnd" type="text" id="installApplyIntoDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.installApplyIntoDateEnd}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installApplyIntoDateStart\')}',isShowClear:false});"/>
        </li>
        <li><label>提交验收日期：</label>
            <input name="confirmAcceptanceDateBegin" type="text" id="confirmAcceptanceDateBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.confirmAcceptanceDateBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'confirmAcceptanceDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;

            <input name="confirmAcceptanceDateEnd" type="text" id="confirmAcceptanceDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.confirmAcceptanceDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'confirmAcceptanceDateBegin\')}',isShowClear:false});"/>
        </li>
        <li><label>实际进场日期：</label>
            <input name="installRealIntoDateStart" type="text" id="installRealIntoDateStart" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.installRealIntoDateStart}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installRealIntoDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;

            <input name="installRealIntoDateEnd" type="text" id="installRealIntoDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.installRealIntoDateEnd}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installRealIntoDateStart\')}',isShowClear:false});"/>
        </li>
        <li><label>实际完工日期：</label>
            <input name="installRealCompleteDateStart" type="text" readonly="readonly" id="installRealCompleteDateStart" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.installRealCompleteDateStart}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installRealCompleteDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;

            <input name="installRealCompleteDateEnd" type="text" readonly="readonly" id="installRealCompleteDateEnd" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.installRealCompleteDateEnd}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installRealCompleteDateStart\')}',isShowClear:false});"/>
        </li>
        <li><label style="width: 120px;">供应商确认日期：</label>
            <input name="supplierIntoDateStart" type="text" readonly="readonly" id="supplierIntoDateStart" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.supplierIntoDateStart}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'supplierIntoDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;

            <input name="supplierIntoDateEnd" type="text" readonly="readonly" id="supplierIntoDateEnd" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bizOrderInstallDetail.supplierIntoDateEnd}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'supplierIntoDateStart\')}',isShowClear:false});"/>
        </li>
                <li><label style="width: 180px;">最近验收不合格提交日期：</label>
                    <input name="beginUnqualifiedAcceptTime" type="text" readonly="readonly" id = "beginUnqualifiedAcceptTime"  maxlength="20" class="input-medium Wdate"
                           value="<fmt:formatDate value="${bizOrderInstallDetail.beginUnqualifiedAcceptTime}" pattern="yyyy-MM-dd"/>"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endUnqualifiedAcceptTime\')}',isShowClear:false});"/> &nbsp;至&nbsp;

                    <input name="endUnqualifiedAcceptTime" type="text" readonly="readonly" id = "endUnqualifiedAcceptTime" maxlength="20" class="input-medium Wdate"
                           value="<fmt:formatDate value="${bizOrderInstallDetail.endUnqualifiedAcceptTime}" pattern="yyyy-MM-dd"/>"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginUnqualifiedAcceptTime\')}',isShowClear:false});"/>
                </li>
       <%-- <li><label>安装项状态：</label>
            <input type="checkbox" id="allCheck"/>全选
            <form:checkboxes path="installStatus" items="${fns:getDictList('install_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
        </li>--%>

                <li style="width: 100%"><label>安装项状态：</label>
                    <input name="chkAll" id="chkAll" title="全部" onclick="ChkAllClick('installStatus','chkAll')" type="checkbox" />全部
                </li>
                <li style="height: 80px">
                        <%-- <form:checkboxes path="orderStatusNumber" items="${fns:getDictList('order_status')}" onclick="ChkSonClick('orderStatusNumber','chkAll')" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
                    <c:forEach items="${fns:getDictList('install_status')}" var="dict">
                        <input type="checkbox" name="installStatus" id="installStatus" value="${dict.value}"  onclick="ChkSonClick('installStatus','chkAll')"  <c:if test="${fns:checkIsExits(bizOrderInstallDetail.installStatus,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
                    </c:forEach>
                </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>门店</th>
        <th>工程模式</th>
        <th>订单编号</th>
        <th>顾客</th>
        <th>项目经理</th>
        <th>安装项名称</th>
        <th>安装项状态</th>
        <th>申请日期</th>
        <th>期望进场日期</th>
        <th>供应商进场日期</th>
        <th>最近验收不合格提交日期</th>
        <th>实际进场日期</th>
        <th>实际完工日期</th>
        <th>是否延期</th>
        <th>安装验收单据</th>
        <th>备注说明 </th>
        <th>操作 </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="order" varStatus="status">
        <tr>
            <td>${fns:getStoreLabel(order.storeId, '')}</td>
            <td>${fns:getDictLabel(order.projectMode, 'project_mode','')}</td>
            <td>${order.orderNumber}</td>
            <td>${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}-${order.customerName}</td>
            <td>${order.employeeRealName }</td>
            <td>${order.installItemName }</td>
            <td>${fns:getDictLabel(order.installStatus,'install_status','')}</td>
            <td><fmt:formatDate type="both" value="${order.installCreateDate }"/></td>
            <td><fmt:formatDate type="date" value="${order.installApplyIntoDate }"/></td>
            <td><fmt:formatDate type="date" value="${order.supplierIntoDate }"/></td>
            <td><fmt:formatDate type="date" value="${order.unqualifiedAcceptTime }"/></td>
            <td><fmt:formatDate type="date" value="${order.installRealIntoDate }"/></td>
            <td><fmt:formatDate type="date" value="${order.installRealCompleteDate }"/></td>
            <td>${fns:getDictLabel(order.installIsCompleteDelay,'install_is_no_extension','')}</td>
            <td>

                    <%-- 				<a href="${ctx}/bizorderinstallplanpic/bizOrderInstallPlanPic/installDetailPhotos?installID=${order.installID}&orderID=${order.id }">查看</a> --%>
                <a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${order.installID}','${order.id }')">查看</a>


            </td>
            <td>${order.installDeplayRemarks }</td>
            <td><a href="${ctx}/bizengininstall/bizEnginInstall/urgeLog?id=${order.installID}&orderID=${order.id}">日志</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>

<link href="${ctxStatic}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctxStatic}/vendor/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/vendor/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet">
<link href="${ctxStatic}/modules/popUp/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/modules/popUp/css/swiper.css" rel="stylesheet" type="text/css" />



<script type="text/javascript" src="${ctxStatic}/vendor/jquery/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/vendor/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctxStatic}/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctxStatic}/vendor/jquery/swiper.min.js"></script>



<!-- 照片弹层 -->
<div class="modal _large fade disN" id="myModal-photo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                <h4 class="modal-title text-center" id="myModalLabel">图片预览</h4>
            </div>
            <div class="modal-body">
                <div class="swiper-container swiper-item01" id="swiper-item01">
                    <div class="swiper-wrapper clearfix" id="inputId">
                        <!--                             <a href="http://www.baidu.com" class="swiper-slide img-01"></a> -->

                    </div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                    <div class="swiper-pagination swiper-pagination-fraction">
                        <span class="swiper-pagination-current">1</span> / <span class="swiper-pagination-total">10</span>
                    </div>
                </div>
                <!--/.swiper-container-->
            </div>
        </div>
    </div>
</div>




<script type="text/javascript">

    $("#myModal-manage").on("shown.bs.modal", function() {
        $(".form_datetime2").datetimepicker({
            format: "dd MM yyyy - hh:ii"
        });
    });

    $("#myModal-photo").on("shown.bs.modal", function() {
        var mySwiper01 = new Swiper('#swiper-item01', {
//                 autoplay: 5000, //可选选项，自动滑动
            speed: 800,
            loop: true, //可选选项，开启循环
            paginationClickable: true,
            nextButton: '.swiper-button-next',
            prevButton: '.swiper-button-prev',
            pagination: '.swiper-pagination',
            paginationType: 'fraction'
        });
    });

    function ajaxGetImgs(installID,orderId){
        // 	 alert(installID+":"+orderId);
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '${ctx}/bizorderinstallplanpic/bizOrderInstallPlanPic/ajaxinstallAcceptPhotos',
            data: {
                installID:installID,
                orderID:orderId
            },
            success: function (data) {

                var modelList = data.mapObject;
                if (null!==modelList && modelList.length !== 0) {
// 		                        '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
                    var ainput='';
                    for (var i = 0; i < modelList.length; i++) {
                        ainput +=	 '<a class="swiper-slide " ><img src="' + modelList[i].picUrl + '" ></a>';
                    }
                    $("#inputId").html(ainput);
                }else{
                    var ainput='';
                    ainput ='<a class="swiper-slide " ><img src="${ctxStatic}/images/nopic.jpg" ></a>';
                    $("#inputId").html(ainput);
                }


            }
        })

    }

</script>


</body>
</html>