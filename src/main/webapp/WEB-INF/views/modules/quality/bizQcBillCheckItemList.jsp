<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检检查项查询管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

            findEngineListByStoreIdAndProjectMode();
            $("#searchForm").validate({
                errorPlacement: function(error, element) {
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            })
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }




        function findEngineListByStoreIdAndProjectMode(){
            var html3 = '<option value=""></option>';
            var storeId = $("#sel").val();
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
                            if('${bizQcBillCheckItem.enginDepartId}' == data[v].engineDepartId){
                                $("#s2id_engineDepartSelect .select2-chosen").html(data[v].engineDepartName);
                                html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
                            }else{
                                html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
                            }
                        }
                        $("#engineDepartSelect").html(html3);
                    } else {
                        $("#engineDepartSelect").html(html3);
                    }

                }

            });
        };

        //清除方法
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
		<li class="active"><a href="${ctx}/quality/bizQcBillCheckItem/list">质检检查项查询列表</a></li>
		<shiro:hasPermission name="quality:bizQcBillCheckItem:edit"><li><a href="${ctx}/quality/bizQcBillCheckItem/form">质检检查项查询添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizQcBillCheckItem" action="${ctx}/quality/bizQcBillCheckItem/querylist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">

			<li><label><font color="red">*</font>门店：</label>
				<form:select path="storeId" id="sel" class="input-medium needClear required" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
				 <li><label><font color="red">*</font>工程模式：</label>
                        <form:select path="projectMode" id="projectMode" class="input-medium needClear required" onchange="findEngineListByStoreIdAndProjectMode()" >
                            <form:option value="" label=""/>
                            <form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                        </form:select>
                </li>

			<li><label><font color="red">*</font> 区域：</label>
				<form:select path="enginDepartId" class="input-medium needClear required" id="engineDepartSelect">
					<%-- <form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="14" class="input-medium  needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" class="input-medium needClear"/>
			</li>
			<li><label>质检单编号：</label>
				<form:input path="qcBillCode" htmlEscape="false" maxlength="14" class="input-medium needClear"/>
			</li>
			<li><label>检查人：</label>
				<form:input path="orderInspector" htmlEscape="false" maxlength="14"  class="input-medium needClear" />
			</li>
			<li><label>责任人：</label>
				<form:input path="mnagerPerson" htmlEscape="false" maxlength="14" class="input-medium needClear"/>
			</li>
			<li><label>被罚组长：</label>
				<form:input path="workGroupPerson" htmlEscape="false" maxlength="14" class="input-medium needClear"/>
			</li>
			<li><label>被罚经理：</label>
				<form:input  path="itemManager"  htmlEscape="false" maxlength="14" class="input-medium needClear"/>
			</li>
			<li><label>不合格项：</label>
				<form:input path="qcCheckItemName" htmlEscape="false" maxlength="14" class="input-medium needClear" />
			</li>
			<li><label>违规形式：</label>
				<form:input path="breakDescribe" htmlEscape="false" maxlength="14" class="input-medium needClear"/>
			</li>
			<li><label>不合格方式：</label>
				<form:checkboxes path="isPassed"  class="input-medium needClear" items="${fns:getDictList('processing_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmitclean" class="btn btn-primary clearBtn" type="button" value="清除" onclick="clearCondition()"/></li>

		</ul>
	</form:form>
	<%--<sys:message content="${message}"/>--%>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>提交报告时间</th>
				<th>质检单编号</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>客户</th>
				<th>检查项分类</th>
				<th>检查项</th>
				<th>违规形式</th>
				<th>处理方式</th>
				<th>检查人</th>
				<th>被罚项目经理</th>
				<th>罚款金额</th>
				<th>被罚工人组长</th>
				<th>罚款金额</th>
				<th>责任人-项目经理</th>
				<th>责任人-工人</th>
				<shiro:hasPermission name="quality:bizQcBillCheckItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizQcBillCheckItem">
			<tr>
				<td>
		<%--	<a href="${ctx}/quality/bizQcBillCheckItem/form?id=${bizQcBillCheckItem.id}">
					${fns:getDictLabel(bizQcBillCheckItem.qcBillId, '', '')}
				</a>--%>
				<fmt:formatDate value="${bizQcBillCheckItem.quCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${bizQcBillCheckItem.qcBillCode}
				</td>
				<td>
						${fns:getStoreLabel(bizQcBillCheckItem.storeId, '')}
				</td>
				<td>
						${fns:getDictLabel(bizQcBillCheckItem.projectMode, 'project_mode', '')}
				</td>
				<td>
						${fns:getElacLabel(bizQcBillCheckItem.enginDepartId, '')}

				</td>
				<td>
					${bizQcBillCheckItem.customerName}
				</td>
				<td>
					${bizQcBillCheckItem.qcCheckKindName}
				</td>
				<td>
					${bizQcBillCheckItem.qcCheckItemName}
				</td>
				<td>
					${bizQcBillCheckItem.breakDescribe}
				</td>
				<td>
					<c:if test="${bizQcBillCheckItem.isPassed=='0' }">
						<c:if test="${bizQcBillCheckItem.isWarned=='1' }">警告</br></c:if>
						<c:if test="${bizQcBillCheckItem.isLocaleRepaire=='1' }">现场整改</br></c:if>
						<c:if test="${bizQcBillCheckItem.isLimitDateRepaire=='1' }">期限整改 -
							<c:if test="${bizQcBillCheckItem.limitDateRepaireCheckStyle=='1' }">线下整改</c:if>
							<c:if test="${bizQcBillCheckItem.limitDateRepaireCheckStyle=='0' }">线上整改</c:if>
							- <fmt:formatDate value="${bizQcBillCheckItem.limitDate }" pattern="yyyy-MM-dd" /></br>
						</c:if>
						<c:if test="${itemDetails.isPunishMoney=='1' }">
							扣分/罚款 - 项目经理 ${bizQcBillCheckItem.pmPunishScore }分/${bizQcBillCheckItem.punishMoneyAmountReal }元 工人 ${bizQcBillCheckItem.workerPunishScore }分/${bizQcBillCheckItem.workerPunishAmount }元 质检员 ${bizQcBillCheckItem.qcPunishScore }分/${bizQcBillCheckItem.qcPunishAmount }元</br>
						</c:if>
					</c:if>
				</td>
				<td>
						${bizQcBillCheckItem.orderInspector}
				</td>
				<td>
						<%--${bizQcBillCheckItem.pmPunishEmployeeId}--%>
						${bizQcBillCheckItem.itemManager}
				</td>
				<td>
					${bizQcBillCheckItem.punishMoneyAmountReal}
				</td>
				<td>
					<%--${bizQcBillCheckItem.workerPunishEmployeegroupId}--%>
					${bizQcBillCheckItem.workGroupPerson}
				</td>
				<td>
					${bizQcBillCheckItem.workerPunishAmount}
				</td>
				<td>
						<%--${bizQcBillCheckItem.projectManagerId}--%>
						${bizQcBillCheckItem.mnagerPerson}

				</td>
				<td>
						<%--${bizQcBillCheckItem.workerGroupId}--%>
						${bizQcBillCheckItem.workerGroupName}
				</td>

				<shiro:hasPermission name="quality:bizQcBillCheckItem:edit"><td>
    				<a href="${ctx}/quality/bizQcBillCheckItem/form?id=${bizQcBillCheckItem.id}">修改</a>
					<a href="${ctx}/quality/bizQcBillCheckItem/delete?id=${bizQcBillCheckItem.id}" onclick="return confirmx('确认要删除该质检检查项查询吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>