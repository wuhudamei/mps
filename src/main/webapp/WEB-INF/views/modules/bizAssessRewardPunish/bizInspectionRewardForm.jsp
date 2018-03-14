<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>项目经理巡检奖励添加</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/modules/settleJs/layer/layer.js"></script>
    <script src="${ctxStatic}/modules/settleJs/layer/extend/layer.ext.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            getDepartments();
            findOrderInfo();
            findOrderInfoById();
            initMin();
            $("#inputForm").validate({
                rules: {
                    storeId: "required",
                    projectMode: "required",
                    enginDepartId: "required",
                    relatedBusinessIdInt: "required",
                    rewardPunishTargetEmployeeId: "required",
                },
                messages: {
                    storeId: "请选择门店",
                    projectMode: "请选择工程模式",
                    enginDepartId: "请选择区域",
                    relatedBusinessIdInt: "请选择客户",
                    rewardPunishTargetEmployeeId: "请选择奖励人员",
                },
                submitHandler: function (form) {
                    var a = checkData();
                    if (a == "1") {
                        loading('正在提交，请稍等...');
                        var els1 = document.getElementsByName("assessRuleTypeIds");
                        for (var i = 0, j = els1.length; i < j; i++) {
                            els1[i].removeAttribute("disabled");
                        }

                        var els2 = document.getElementsByName("assessRuleIds");
                        for (var i = 0, j = els2.length; i < j; i++) {
                            els2[i].removeAttribute("disabled");
                        }
                        form.submit();
                    }
                },
                errorContainer: "#messageBox",

            });
        });

        function checkData() {
            //考核条例分类不能为空
            var els1 = document.getElementsByName("assessRuleTypeIds");
            if (els1.length < 1) {
                layer.msg("考核条例分类设置至少包含一条以上的数据");
                return "2";
            }
            for (var i = 0, j = els1.length; i < j; i++) {
                var role = els1[i].value;
                if (null == role || role == "") {
                    layer.msg("考核条例分类不能为空")
                    return "2";
                }
            }

            //考核条例细则说明不能为空
            var els2 = document.getElementsByName("assessRuleIds");
            if (els2.length < 1) {
                layer.msg("考核条例分类设置至少包含一条以上的数据");
                return "2";
            }
            for (var i = 0, j = els2.length; i < j; i++) {
                var role = els2[i].value;
                if (null == role || role == "") {
                    layer.msg("考核条例细则说明不能为空")
                    return "2";
                }
            }

            //奖励金额不能为空
            var els3 = document.getElementsByName("rewardPunishAmounts");
            if (els3.length < 1) {
                layer.msg("考核条例分类设置至少包含一条以上的数据");
                return "2";
            }
            for (var i = 0, j = els3.length; i < j; i++) {
                var role = els3[i].value;
                if (null == role || role == "") {
                    layer.msg("奖励金额不能为空")
                    return "2";
                }
            }

            //奖励分数不能为空
            var els4 = document.getElementsByName("rewardPunishScores");
            if (els4.length < 1) {
                layer.msg("考核条例分类设置至少包含一条以上的数据");
                return "2";
            }
            for (var i = 0, j = els4.length; i < j; i++) {
                var role = els4[i].value;
                if (null == role || role == "") {
                    layer.msg("奖励分数不能为空")
                    return "2";
                }
            }

            return "1";
        }

        function checkNumber(obj) {
            //先把非数字的都替换掉，除了数字和.
            obj.value = obj.value.replace(/[^\d.]/g, "");
            //必须保证第一个为数字而不是.
            obj.value = obj.value.replace(/^\./g, "");
            //保证.只出现一次，而不能出现两次以上
            obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
                "$#$", ".");
            //只能输入两个小数
            obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
            queryTotalAmount();
        }

        //加载区域信息
        function getDepartments() {
            $("#enginDepartId").html('');
            $.ajax({
                url: '${ctx}/engdept/bizEngineeringDepartment/departmentList',
                type: 'post',
                dataType: 'json',
                data: {
                    'storeId': $('#storeId').val(),
                    'projectMode': $('#projectMode').val()
                },
                success: function (data) {
                    if (data == null) {
                        $("#enginDepartId").append('');
                    } else {
                        var html = "<option value=''></option>";
                        for (var i = 0; i < data.length; i++) {
                            var sec = "";
                            if ('${bizAssessRewardPunish.enginDepartId}' == data[i].value) {
                                sec = "selected='selected'";
                                $("#s2id_enginDepartId .select2-chosen").html(data[i].label);
                            }
                            html += "<option value='" + data[i].value + "' " + sec + ">" + data[i].label + "</option>"
                        }
                        html += '';
                        $("#enginDepartId").append(html);
                    }
                }
            });
        }

        function findOrderInfo() {
            var html3 = '<option value=""></option>';
            var storeId = $("#storeId").val();
            var projectModeValue = $("#projectMode").val();
            var engineDepartId = null;
            var orderNumber = null;
            if ($("#enginDepartId").val() == null) {
                engineDepartId = '${bizAssessRewardPunish.enginDepartId}';
            } else {
                engineDepartId = $("#enginDepartId").val();
            }
            if ($("#orderNumber").val() != null) {
                orderNumber = $("#orderNumber").val();
            }
            //根据 门店、工程模式、区域查询订单
            $("#relatedBusinessIdInt").html("");
            $.ajax({
                url: "${ctx}/order/order/findOrderByParam?storeId="
                + storeId + "&projectModeValue=" + projectModeValue + "&engineDepartId=" + engineDepartId + "&orderNumber=" + orderNumber,
                type: "post",
                success: function (data) {
                    if (data == null || data == '' || data.length == 0) {
                        $("#relatedBusinessIdInt").append("");
                    } else {
                        var html = "<option value=''></option>";
                        for (var i = 0; i < data.length; i++) {
                            var label = data[i].communityName + "-" + data[i].buildRoom + "-" + data[i].customerName
                            var sec = "";
                            if ('${bizAssessRewardPunish.relatedBusinessIdInt}' == data[i].orderId) {
                                sec = "selected='selected'";
                                $("#s2id_relatedBusinessIdInt .select2-chosen").html(label);
                            }
                            html += "<option value='" + data[i].orderId + "' " + sec + ">" + label + "</option>"
                        }
                        html += '';
                        $("#relatedBusinessIdInt").append(html);
                    }
                }
            });
        }

        function findAssessRule(row) {
            $("#assessRuleId" + row).html("");
            var assessRuleTypeId = $("#assessRuleTypeId" + row).val();
            var html = "<option value=''>--请选择--</option>";
            $.ajax({
                url: '${ctx}/bizAssessRule/bizAssessRule/queryAssessRuleByAssessRuleType',
                type: 'post',
                dataType: 'json',
                data: {
                    'bizAssessRuleTypeId': assessRuleTypeId,
                    'rewardPunishTargetEmployeeType': $("#rewardPunishTargetEmployeeType").val(),
                    'isEnabled': 1
                },
                success: function (data) {
                    if (data == null || data.length < 1) {
                        $("#assessRuleId" + row).append(html);
                    } else {
                        for (var i = 0; i < data.length; i++) {
                            var sec='';
                            <c:if test="${rewardPunishs != null && rewardPunishs.size() > 0}">
                            <c:forEach items="${rewardPunishs}" var="ruleType" varStatus="status">
                            if(${status.index+1} == row){
                                if(data[i].id == ${ruleType.assessRuleId}){
                                   sec='selected';
                                }else{
                                    sec='';
                                }
                            }
                            </c:forEach>
                            </c:if>
                            html += "<option "+sec+"  value='" + data[i].id + "'>" + data[i].bizAssessRuleDescribe + "</option>"
                        }
                        $("#assessRuleId" + row).append(html);
                    }
                }
            });
        }

        function findBizAssessRuleById(row) {
            var id = $("#assessRuleId" + row).val();
            $.ajax({
                url: "${ctx}/bizAssessRule/bizAssessRule/findBizAssessRuleById?id=" + id,
                type: "post",
                success: function (data) {
                    if (data != null) {
                        $("#rewardPunishAmount" + row).val(data.rewardPunishAmount);
                        $("#rewardPunishScore" + row).val(data.rewardPunishScore);
                        if (data.rewardPunishAmount > 0) {
                            $("#rewardPunishAmount" + row).attr("readonly", "readonly");
                        } else {
                            $("#rewardPunishAmount" + row).removeAttr("readonly");
                        }
                        if (data.rewardPunishScore > 0) {
                            $("#rewardPunishScore" + row).attr("readonly", "readonly");
                        } else {
                            $("#rewardPunishScore" + row).removeAttr("readonly");
                        }
                        queryTotalAmount();
                    }
                }
            });
        }

        function findOrderInfoById() {
            var orderId = $("#relatedBusinessIdInt").val();
            if (orderId == null) {
                orderId = '${bizAssessRewardPunish.relatedBusinessIdInt}';
            }
            $.ajax({
                url: "${ctx}/order/order/findOrderById?orderId=" + orderId,
                type: "post",
                success: function (data) {
                    if (data != null) {
                        $("#communityName").val(data.communityName);
                        $("#buildNumber").val(data.buildNumber);
                        $("#buildUnit").val(data.buildUnit);
                        $("#buildRoom").val(data.buildRoom);
                        $("#orderNumber").val(data.orderNumber);
                    }
                }
            });
            findBizOrderDistributeLogByOrderId();
        }

        function findBizOrderDistributeLogByOrderId() {
            var orderId = $("#relatedBusinessIdInt").val();
            if (orderId == null) {
                orderId = '${bizAssessRewardPunish.relatedBusinessIdInt}';
            }
            $("#rewardPunishTargetEmployeeId").html('');
            $.ajax({
                url: "${ctx}/bizorderdistributelog/bizOrderDistributeLog/queryOrderPmDistributeLogByOrderId?orderId=" + orderId,
                type: "post",
                success: function (data) {
                    if (data == null) {
                        $("#rewardPunishTargetEmployeeId").append('');
                    } else {
                        var html = "<option value=''></option>";
                        for (var i = 0; i < data.length; i++) {
                            var sec = "";
                            if ('${bizAssessRewardPunish.rewardPunishTargetEmployeeId}' == data[i].distributedEmployeeId) {
                                sec = "selected='selected'";
                                $("#s2id_rewardPunishTargetEmployeeId .select2-chosen").html(data[i].distributedEmployeeName);
                            }
                            html += "<option value='" + data[i].distributedEmployeeId + "' " + sec + ">" + data[i].distributedEmployeeName + "</option>"
                        }
                        html += '';
                        $("#rewardPunishTargetEmployeeId").append(html);
                    }
                }
            });
        }

        function queryTotalAmount() {
            //奖励金额
            var totalAmount = 0;
            var els3 = document.getElementsByName("rewardPunishAmounts");
            if (els3 != null && els3.length > 0) {
                for (var i = 0; i < els3.length; i++) {
                    var amount = els3[i].value;
                    if (amount == null || amount == '') {
                        amount = 0;
                    }
                    totalAmount = parseFloat(totalAmount) + parseFloat(amount);
                }
            }
            $("#totalAmount").val(totalAmount);
            //奖励分数
            var totalScore = 0;
            var els4 = document.getElementsByName("rewardPunishScores");
            if (els4 != null && els4.length > 0) {
                for (var i = 0; i < els4.length; i++) {
                    var score = els4[i].value;
                    if (score == null || score == '') {
                        score = 0;
                    }
                    totalScore = parseFloat(totalScore) + parseFloat(score);
                }
            }
            $("#totalScore").val(totalScore);
        }

        var row = 1;
        function initMin() {
            <c:if test="${rewardPunishs != null && rewardPunishs.size() > 0}">
            $.ajax({
                url: '${ctx}/bizAssessRuleType/bizAssessRuleType/queryRuleTypeByParam',
                type: 'post',
                dataType: 'json',
                data: {
                    'storeId': $('#storeId').val(),
                    'projectMode': $('#projectMode').val(),
                    'isRewardOrPunish': $('#isRewardOrPunish').val(),
                    'rewardPunishTargetType': $('#rewardPunishTargetType').val(),
                    'isMonthInspection': $('#isMonthInspection').val(),
                    'isEnabled': 1
                },
                success: function (data) {
                    if (data == null || data.length < 1) {
                        layer.msg("没有有效的考核条例分类！");
                        return false;
                    } else {
                        <c:forEach items="${rewardPunishs}" var="ruleType" varStatus="status">
                        row = ${status.index+1};
                        var html = "<tr  class='trclass'  id='trid" + row + "'>";
                        html += "<th><select name='assessRuleTypeIds' id='assessRuleTypeId" + row + "' class='input-xlarge required' onchange='findAssessRule(" + row + ");'>";
                        html += "<option value=''>--请选择--</option>";
                        for (var i = 0; i < data.length; i++) {
                            var sec = '';
                            if (data[i].id == ${ruleType.assessRuleTypeId}) {
                                sec = 'selected';
                            } else {
                                sec = '';
                            }
                            html += "<option " + sec + " value='" + data[i].id + "'>" + data[i].typeName + "</option>"
                        }
                        html += "</select></th>";
                        html += "<th><select name='assessRuleIds' id='assessRuleId" + row + "' onchange='findBizAssessRuleById(" + row + ")' style='width:700px;' class='input-xlarge required'><option value=''>--请选择--</option></select></th>";
                        var read = "";
                        if(${ruleType.rewardPunishAmount} > 0){
                            read = "readonly='readonly'";
                        }else{
                            read = "";
                        }
                        html += "<th><input type='text' "+read+" name='rewardPunishAmounts' id='rewardPunishAmount" + row + "' value='${ruleType.rewardPunishAmount}' oninput='checkNumber(this)' class='input-medium required'/></th>";
                        if(${ruleType.rewardPunishScore} > 0){
                            read = "readonly='readonly'";
                        }else{
                            read = "";
                        }
                        html += "<th><input type='text' "+read+" name='rewardPunishScores' id='rewardPunishScore" + row + "' value='${ruleType.rewardPunishScore}'  oninput='checkNumber(this)' class='input-medium required'/></th>";


                        html += "<th><input class='btn btn-primary' type='button' name='${ruleType.detailRemarks}' value='备注' onclick ='updateRemarks(this," + row + ")'/>&nbsp;&nbsp;&nbsp;"+
                            "<input class='btn btn-primary' type='button' value='完成' fild='1' onclick='accomOrUpdate(this," + row + ")'/>&nbsp;&nbsp;&nbsp;" +
                            "<input class='btn btn-primary' type='button' value='删除' onclick='del(this)'/></th>";
                        html += "<th><input type='hidden' "+read+" name='detailRemarks' id='detailRemarksid" + row + "' value='${ruleType.detailRemarks}'  oninput='checkNumber(this)' class='input-medium required'/></th>";
                        html += "</tr>";
                        $("#ruleType").append(html);
                        findAssessRule(row);
                        </c:forEach>
                    }
                    queryTotalAmount();
                    row++;
                }
            });
            </c:if>
        }

        function addRuleType() {
            var storeId = $("#storeId").val();
            if (storeId == null || storeId == "") {
                layer.msg("请选择门店后再进行增加考核条例分类！");
                return false;
            }
            $.ajax({
                url: '${ctx}/bizAssessRuleType/bizAssessRuleType/queryRuleTypeByParam',
                type: 'post',
                dataType: 'json',
                data: {
                    'storeId': $('#storeId').val(),
                    'projectMode': $('#projectMode').val(),
                    'isRewardOrPunish': $('#isRewardOrPunish').val(),
                    'rewardPunishTargetType': $('#rewardPunishTargetType').val(),
                    'isMonthInspection': $('#isMonthInspection').val(),
                    'isEnabled': 1
                },
                success: function (data) {
                    if (data == null || data.length < 1) {
                        layer.msg("没有有效的考核条例分类！");
                        return false;
                    } else {
                        var html = "<tr class='trclass'  id='trid" + row + "'>";
                        html += "<th><select name='assessRuleTypeIds' id='assessRuleTypeId" + row + "' class='input-xlarge required' onchange='findAssessRule(" + row + ");'>";
                       /* html += "<option value=''>--请选择--</option>";*/
                        for (var i = 0; i < data.length; i++) {
                            html += "<option value='" + data[i].id + "'>" + data[i].typeName + "</option>"
                        }
                        html += "</select></th>";
                        html += "<th><select name='assessRuleIds' id='assessRuleId" + row + "' onchange='findBizAssessRuleById(" + row + ")' style='width:700px;' class='input-xlarge required'><option value=''>--请选择--</option></select></th>";

                        html += "<th><input type='text' name='rewardPunishAmounts' id='rewardPunishAmount" + row + "' value='' oninput='checkNumber(this)' class='input-medium required'/></th>";

                        html += "<th><input type='text' name='rewardPunishScores' id='rewardPunishScore" + row + "' value=''  oninput='checkNumber(this)' class='input-medium required'/></th>";


                        html += "<th><input class='btn btn-primary' type='button' value='备注' onclick ='addRemarks(this," + row + ")'/>&nbsp;&nbsp;&nbsp;"+
                            "<input class='btn btn-primary' type='button' value='完成' fild='1' onclick='accomOrUpdate(this," + row + ")'/>&nbsp;&nbsp;&nbsp;" +

                            "<input class='btn btn-primary' type='button' value='删除' onclick='del(this)'/></th>";
                        html += "</tr>";
                        $("#ruleType").append(html);
                        findAssessRule(row);
                        row++;
                    }
                }
            });
        }

        //删除
        function del(k) {
            $(k).parent().parent().remove();
            queryTotalAmount();
        }

        function accomOrUpdate(obj, row) {
            var assessRuleTypeId = $("#assessRuleTypeId" + row).val();
            if (assessRuleTypeId == null || assessRuleTypeId == "") {
                layer.msg("请选择考核条例分类！");
                return;
            }

            var assessRuleId = $("#assessRuleId" + row).val();
            if (assessRuleId == null || assessRuleId == "") {
                layer.msg("请选择考核条例细则说明！");
                return;
            }

            var rewardPunishAmount = $("#rewardPunishAmount" + row).val();
            if (rewardPunishAmount == null || rewardPunishAmount == "") {
                layer.msg("奖励金额不能为空！");
                return;
            }

            var rewardPunishScore = $("#rewardPunishScore" + row).val();
            if (rewardPunishScore == null || rewardPunishScore == "") {
                layer.msg("奖励分数不能为空！");
                return;
            }
            var fild = $(obj).attr("fild");
            if (fild == 1) {//点击完成
                $("#assessRuleTypeId" + row).attr("disabled", true);
                $("#assessRuleId" + row).attr("disabled", true);
                $("#rewardPunishAmount" + row).attr("readOnly", true);
                $("#rewardPunishScore" + row).attr("readOnly", true);
                $(obj).attr("fild", 2);
                $(obj).attr("value", "修改");
            } else if (fild == 2) {//点击修改
                $("#assessRuleTypeId" + row).attr("disabled", false);
                $("#assessRuleId" + row).attr("disabled", false);
                if ($("#rewardPunishAmount" + row).val() <= 0) {
                    $("#rewardPunishAmount" + row).attr("readOnly", false);
                }
                if ($("#rewardPunishScore" + row).val() <= 0) {
                    $("#rewardPunishScore" + row).attr("readOnly", false);
                }
                $(obj).attr("fild", 1);
                $(obj).attr("value", "完成");
            }
        }

        //修改明细备注触发的事件
        function updateRemarks(obj , row){
            var updateyh=$("#detailRemarksid"+row).val()
            if( updateyh === null || updateyh === undefined || updateyh === ''){
                $("#resonReject").val(obj.name);
            }else{
                $("#resonReject").val(updateyh);
            }

            $("#myAbandonedModalReject").modal('show');
            $("#myIdReject").val(row);
        }

        //添加明细备注触发的事件
        function addRemarks(obj , row){
            var updateyh=$("#detailRemarksid"+row).val()
           // alert(row+":"+updateyh);
            if( updateyh === null || updateyh === undefined || updateyh === ''){
            }else{
                $("#resonReject").val(updateyh);
            }
            $("#myAbandonedModalReject").modal('show');
            $("#myIdReject").val(row);
        }

        //添加明细备注
        function onclickAbandonedReject(){
            var row = $("#myIdReject").val();
            var date = $("#resonReject").val();
            //有没有输入原因
            if(date == ""){
                $("#myNoAbandonedModalReject").modal('show');
            }else{
                //根据trid得到子元素的长度判断是否大于5如果大于5说明就是重复添加了备注以前的备注就要删除
                if($("#trid"+row).children().length>5){
                    $("#trid"+row).children("th:last-child").remove();
                    var html = "<th><input type='hidden' name='detailRemarks' id='detailRemarksid" + row + "'  value='"+date+"'  oninput='checkNumber(this)' class='input-medium required'/></th>";
                    $("#trid"+row).append(html);
                }else{
                   var html = "<th><input type='hidden' name='detailRemarks' id='detailRemarksid" + row + "'  value='"+date+"'  oninput='checkNumber(this)' class='input-medium required'/></th>";
                   //获取tr上的动态ID添加到隐藏域中
                    $("#trid"+row).append(html);
                }

                //window.location.href="${ctx}/cusserviceproblem/bizCusServiceProblem/reject?id="+v+"&statusdescribe="+date+"&workOrderCode="+workOrderCodeReject;
            }
            $("#resonReject").val('');
            $('#myAbandonedModalReject').modal('hide');
        }
        //添加明细备注 取消 事件
        function onclickNoAbandonedReject(){
            $('#myAbandonedModalReject').modal('hide');
            $("#resonReject").val('');
        }




    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a
            href="${ctx}/bizAssessRewardPunish/bizAssessRewardPunish/queryBizAssessReward?isMonthInspection=1">项目经理巡检奖励列表</a>
    </li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="bizAssessRewardPunish"
           action="${ctx}/bizAssessRewardPunish/bizAssessRewardPunish/saveBizInspectionReward"
           method="post" class="form-horizontal">
    <form:hidden path="ids"/>
    <input type="hidden" value="1" id="isRewardOrPunish" name="isRewardOrPunish"/>
    <input type="hidden" value="10" id="rewardPunishTargetType" name="rewardPunishTargetType"/>
    <form:hidden path="isMonthInspection"/>
    <sys:message content="${message}"/>
    <table>
        <tr>
            <td>
                <div class="control-group">
                    <label class="control-label">门店：</label>
                    <div class="controls">
                        <c:if test="${empty storeDropEnable}">
                            <form:select path="storeId" id="storeId" class="input-medium"
                                         disabled="true" onchange="getDepartments()">
                                <form:option value="" label=""/>
                                <form:options items="${fns:getStoreList()}" itemLabel="label"
                                              itemValue="value" htmlEscape="false"/>
                            </form:select>
                        </c:if>
                        <c:if test="${!empty storeDropEnable}">
                            <form:select path="storeId" id="storeId"
                                         class="input-medium needClear" onchange="getDepartments()">
                                <form:option value="" label=""/>
                                <form:options items="${fns:getStoreList()}" itemLabel="label"
                                              itemValue="value" htmlEscape="false"/>
                            </form:select>
                        </c:if>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>
            </td>
            <td>
                <div class="control-group">
                    <label class="control-label">工程模式：</label>
                    <div class="controls">
                        <form:select path="projectMode" id="projectMode"
                                     class="input-medium" disabled="true" onchange="getDepartments()">
                            <form:option value="1" label="产业"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <div class="control-group">
                    <label class="control-label">区域：</label>
                    <div class="controls">
                        <form:select path="enginDepartId" id="enginDepartId"
                                     class="input-medium needClear" onchange="findOrderInfo()">
                        </form:select>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td>
                <div class="control-group">
                    <label class="control-label">订单编号：</label>
                    <div class="controls">
                        <form:input path="orderNumber" id="orderNumber"
                                    htmlEscape="false" class="input-medium required" onkeyup="findOrderInfo()"/>
                    </div>
                </div>
            </td>

            <td>
                <div class="control-group">
                    <label class="control-label">客户姓名：</label>
                    <div class="controls">
                        <form:select path="relatedBusinessIdInt"
                                     id="relatedBusinessIdInt" class="input-medium needClear"
                                     onchange="findOrderInfoById()">
                        </form:select>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td>
                <div class="control-group">
                    <label class="control-label">小区名称：</label>
                    <div class="controls">
                        <form:input path="communityName" id="communityName"
                                    htmlEscape="false" class="input-medium required" readonly="true"/>
                    </div>
                </div>
            </td>

            <td>
                <div class="control-group">
                    <label class="control-label">楼号：</label>
                    <div class="controls">
                        <form:input path="buildNumber" id="buildNumber"
                                    htmlEscape="false" class="input-medium required" readonly="true"/>
                    </div>
                </div>
            </td>
        </tr>


        <tr>
            <td>
                <div class="control-group">
                    <label class="control-label">单元门：</label>
                    <div class="controls">
                        <form:input path="buildUnit" id="buildUnit" readonly="true"
                                    htmlEscape="false" class="input-medium required"/>
                    </div>
                </div>
            </td>

            <td>
                <div class="control-group">
                    <label class="control-label">门牌号：</label>
                    <div class="controls">
                        <form:input path="buildRoom" id="buildRoom" readonly="true"
                                    htmlEscape="false" class="input-medium required"/>
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td>
                <div class="control-group">
                    <label class="control-label">责任人：</label>
                    <div class="controls">
                        <input type="hidden" value="1" name="rewardPunishTargetEmployeeType"/>
                        <form:select path="rewardPunishTargetEmployeeType" id="rewardPunishTargetEmployeeType"
                                     class="input-medium" disabled="true">
                            <form:option value="1" label="项目经理"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>
            </td>

            <td>
                <div class="control-group">
                    <label class="control-label">奖励人员：</label>
                    <div class="controls">
                        <form:select path="rewardPunishTargetEmployeeId" id="rewardPunishTargetEmployeeId"
                                     class="input-medium" onchange="queryTotalAmount()">
                        </form:select>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>
            </td>
        </tr>

        <tr>
            <td>
                <div class="control-group">
                    <label class="control-label">合计奖励金额：</label>
                    <div class="controls">
                        <input type="text" value="" class="input-medium required" id="totalAmount" readonly="readonly"/>元
                    </div>
                </div>
            </td>
            <td>
                <div class="control-group">
                    <label class="control-label">合计奖励分数：</label>
                    <div class="controls">
                        <input type="text" value="" class="input-medium required" id="totalScore" readonly="readonly"/>分
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="control-group"  >
                    <label class="control-label">备注：</label>
                    <div class="controls" >
                        <form:textarea    style='width: 520px'  onkeyup="this.value = this.value.substring(0,100)" placeholder="请输入备注内容，多行输入，不多于100个汉字" maxlength="100"  type="text" path="generalRemarks"  class="input-medium required" ></form:textarea>
                    </div>
                </div>
            </td>
        </tr>
        <div class="control-group">
            <table id="eval" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th>考核条例分类</th>
                    <th>考核条例细则说明</th>
                    <th>奖励金额</th>
                    <th>奖励分数</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="ruleType">

                </tbody>

                <tr>
                    <th colspan="5">
                        <input class="btn btn-primary" type="button" value="增加" onclick="addRuleType()"/>

                    </th>
                </tr>
            </table>
        </div>
    </table>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit"
               value="保 存"/>&nbsp; <input id="btnCancel" class="btn"
                                          type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>




<!-- 明细备注弹框的model-->
<div  class="modal fade" id="myAbandonedModalReject" tabindex="-1" role="dialog" style="width:350px">
    <input  id="myIdReject" type="hidden">
    <div class="modal-header">
        <button class="close" type="button" data-dismiss="modal">×</button>
        <h4 id="myModalLabel" align="center" style="color: black;">备注内容</h3><br>
            <form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
            <input id = "ids"name="id" value="${bizMaterialsStandardReceiveBill.id}" type="hidden">
            <input id = "workOrderCode2" name="workOrderCode" value="${bizMaterialsStandardReceiveBill.workOrderCode}" type="hidden">
            <input id = "status" name="status" value="20" type="hidden">
            <div  style="margin:10px;min-height:20px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
                <textarea id="resonReject" class="mask-text"  onkeyup="this.value = this.value.substring(0,100)" placeholder="请输入备注内容，多行输入，不多于100个汉字" maxlength="100" ></textarea>
               <p> <a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandonedReject()" >确定</a>
                <a href="javascript:void(0)" class="btn" onclick="onclickNoAbandonedReject()">关闭</a>
               </p>
            </div>
    </div>
    </form:form>
</div>
</body>
</html>