<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>大看板</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        $(document).ready(function() {
            getDepartmentsFirst();

            $("tbody>tr").click(function(){

                $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

            });

        });

        function getDepartmentsFirst(){
            var html = '<option value=""></option>';
            var storeId = $("#storeId").val();
            var projectModeValue = $("#projectMode").val();
            if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
                return;
            }
            //根据门店个,工程模式    动态加载工程区域
            $.ajax({
                url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
                + storeId + "&projectModeValue=" + projectModeValue,
                type : "get",
                success : function(data) {
                    if (null!= data && data.length > 0) {
                        for (var v = 0; v < data.length; v++) {
                            if('${bizProjectProgressSummaryData.engineDepartId}' == data[v].engineDepartId){
                                $("#s2id_engineDepartId .select2-chosen").html(data[v].engineDepartName);
                                html = html + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
                            }else{
                                html = html + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
                            }
                        }
                        $("#engineDepartId").html(html);
                    } else {
                        $("#engineDepartId").html(html);
                    }
                }
            });
        }

        function getDepartments(){
            var html = '<option value=""></option>';
            var storeId = $("#storeId").val();
            var projectModeValue = $("#projectMode").val();
            if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
                return;
            }
            //根据门店个,工程模式    动态加载工程区域
            $.ajax({
                url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
                + storeId + "&projectModeValue=" + projectModeValue,
                type : "get",
                success : function(data) {
                    if (null!= data && data.length > 0) {
                        for (var v = 0; v < data.length; v++) {
                            html +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>';
                        }
                        $("#engineDepartId").html(html);
                    } else {
                        $("#engineDepartId").html(html);
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




		//更新
        function updateData(orderId) {
            top.$.jBox.confirm("您确认更新吗？","系统提示",function(v,h,f){
                if(v=="ok"){
                    loading('正在提交，请稍等...');
                    $.ajax({
                        url : "${ctx}/projectprogressboning/bizProjectProgressSummaryData/editOrderNode?orderId="+orderId,
                        type : "post",
                        success : function(data) {
                            if(data == "0"){
                                alertx("更新成功！");
                            }else if(data == "1"){
                                alertx("更新失败！");
                            }
                            $("#searchForm").attr("action", "${ctx}/projectprogressboning/bizProjectProgressSummaryData/list");
                            $("#searchForm").submit();
                        }
                    });
                }


            },{buttonsFocus:1});
            top.$('.jbox-body .jbox-icon').css('top','55px');

        }

        //查询
        function searchList(){
            var storeId = $("#storeId").val();
            if(null==storeId || '' == storeId){
                alertx("请先选择门店后，再查询");
                return;
            }
            $("#searchForm").attr("action", "${ctx}/projectprogressboning/bizProjectProgressSummaryData/list");
            $("#searchForm").submit();
        }

        //取值说明
        function boingExplain(){
            window.location.href="${ctx}/projectprogressboning/bizProjectProgressBoning/openBizProjectProgressBoningExplain";
        }

        //导出excel
		function exportExcel(){
            // 判断门店
			var storeId = $("#storeId").val();
            if('${bizProjectProgressSummaryData.storeId}' != storeId){
                alertx("请先选择门店查询后，再导出");
                return;
            }
            // 判断工程模式
            var projectMode = $("#projectMode").val();
            if('${bizProjectProgressSummaryData.projectMode}' != projectMode){
                alertx("请先查询后，再导出");
                return;
            }
            // 判断区域
            var engineDepartId = $("#engineDepartId").val();
            if('${bizProjectProgressSummaryData.engineDepartId}' != engineDepartId){
                alertx("请先查询后，再导出");
                return;
            }
            // 判断订单编号
            var orderNumber = $("#orderNumber").val();
            if('${bizProjectProgressSummaryData.orderNumber}' != orderNumber){
                alertx("请先查询后，再导出");
                return;
            }
            // 判断项目经理
            var itemManager = $("#itemManager").val();
            if('${bizProjectProgressSummaryData.itemManager}' != itemManager){
                alertx("请先查询后，再导出");
                return;
            }
            // 判断客户姓名
            var customerName = $("#customerName").val();
            if('${bizProjectProgressSummaryData.customerName}' != customerName){
                alertx("请先查询后，再导出");
                return;
            }
            // 判断新老房
            var houseIsNew = $("#houseIsNew").val();
            if('${bizProjectProgressSummaryData.houseIsNew}' != houseIsNew){
                alertx("请先查询后，再导出");
                return;
            }
            // 判断是否作废
            var isScrap = $("#isScrap").val();
            if('${bizProjectProgressSummaryData.isScrap}' != isScrap){
                alertx("请先查询后，再导出");
                return;
            }
            // 判断更新状态
            var updateStatus = $("#updateStatus").val();
            if('${bizProjectProgressSummaryData.updateStatus}' != updateStatus){
                alertx("请先查询后，再导出");
                return;
            }
            // 判断实际开工日期
			var beginActualStartDate = $("#beginActualStartDate").val();
			var endActualStartDate = $("#endActualStartDate").val();
			var beginActualStartDateOld = $("#beginActualStartDateOld").val();
			var endActualStartDateOld = $("#endActualStartDateOld").val();
            if(beginActualStartDateOld != beginActualStartDate){
                alertx("请先查询后，再导出");
                return;
            }
            if(endActualStartDateOld != endActualStartDate){
                alertx("请先查询后，再导出");
                return;
            }

            $("#searchForm").attr("action", "${ctx}/projectprogressboning/bizProjectProgressSummaryData/export");
            $("#searchForm").submit();
		}


	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/projectprogressboning/bizProjectProgressSummaryData/preList">大看板列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="bizProjectProgressSummaryData" action="${ctx}/projectprogressboning/bizProjectProgressSummaryData/list" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

	<!-- 实际开工日期 -->
	<input id="beginActualStartDateOld" type="hidden" value="<fmt:formatDate value="${bizProjectProgressSummaryData.beginActualStartDate}" pattern="yyyy-MM-dd"/>">
	<input id="endActualStartDateOld" type="hidden" value="<fmt:formatDate value="${bizProjectProgressSummaryData.endActualStartDate}" pattern="yyyy-MM-dd"/>">

	<ul class="ul-form">
		<li><label>门店：</label>
			<form:select path="storeId" class="input-medium" onchange="getDepartments()">
				<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</li>
		<li><label>工程模式：</label>
			<c:if test="${!empty gongcheng}">
				<form:select path="projectMode" class="input-medium" onchange="getDepartments()">
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</c:if>
			<c:if test="${empty gongcheng}">
				<form:select path="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</c:if>
		</li>
		<li><label>区域：</label>
			<select id="engineDepartId" name="engineDepartId" class="input-medium needClear">

			</select>
		</li>
		<li><label>订单编号：</label>
			<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
		</li>
		<li><label>项目经理：</label>
			<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
		</li>

		<li><label>客户姓名：</label>
			<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
		</li>

		<li><label>新老房：</label>
			<form:select path="houseIsNew" class="input-medium needClear">
				<form:option value="" label=""/>
				<form:option value="0" label="老房"/>
				<form:option value="1" label="新房"/>
			</form:select>
		</li>
		<li><label style="width: 110px">实际开工日期：</label>
			<input name="beginActualStartDate" id="beginActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
				   value="<fmt:formatDate value="${bizProjectProgressSummaryData.beginActualStartDate}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
			<input name="endActualStartDate" id="endActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
				   value="<fmt:formatDate value="${bizProjectProgressSummaryData.endActualStartDate}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		</li>

		<li><label>是否作废：</label>
			<form:select path="isScrap" class="input-medium needClear">
				<form:option value="" label=""/>
				<form:option value="0" label="否"/>
				<form:option value="1" label="是"/>
			</form:select>
		</li>
		<li><label>更新状态：</label>
			<form:select path="updateStatus" class="input-medium needClear">
				<form:option value="" label=""/>
				<form:option value="1" label="更新成功"/>
				<form:option value="2" label="更新失败"/>
			</form:select>
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="searchList()"/></li>
		<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
		<li class="btns"><input class="btn btn-primary" type="button" value="导出Excel" onclick="exportExcel()"/></li>
		<li class="btns"><input class="btn btn-primary" type="button" value="取值说明" onclick="boingExplain()"/></li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<c:forEach items="#{map.listMapOne}" var="one">
				<c:if test="${one.cellMerge eq 1}">
					<th rowspan="${one.cellMergeLengh}" style="vertical-align:middle">${one.cnColumnName}</th>
				</c:if>
				<c:if test="${one.cellMerge ne 1}">
					<th colspan="${one.cellMergeLengh}" style="text-align: center">${one.cnColumnName}</th>
				</c:if>
			</c:forEach>
			<th rowspan="2" style="vertical-align:middle">更新状态</th>
			<th rowspan="2" style="vertical-align:middle">操作</th>
		</tr>
		<tr>
			<c:forEach items="#{map.listMapTwo}" var="two">
				<th style="vertical-align: middle">${two.cnColumnName}</th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.list}" var="summaryData">
			<tr>
				<c:forEach items="#{map.listMapThree}" var="three">
					<td>${summaryData.map[three.enColumnName]}</td>
				</c:forEach>
				<td>
					<span style="color:red;">
						<c:if test="${summaryData.updateStatus eq 1}">更新成功</c:if>
						<c:if test="${summaryData.updateStatus ne 1}">更新失败</c:if>
					</span>
				</td>
				<shiro:hasPermission name="bizProjectProgressSummaryData:bizProjectProgressSummaryData:edit"><td><a href="#" onclick="updateData(${summaryData.orderId})">更新</a></td></shiro:hasPermission>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>