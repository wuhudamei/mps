<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理工资批次查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            getDepartments();
			/*$("#searchForm").validate({
				errorPlacement: function(error, element) {
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			})*/
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

        //加载区域信息
        function getDepartments(){
            $("#enginDepartId").html('');
            $.ajax({
                url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
                type:'post',
                dataType : 'json',
                data:{
                    'storeId':$('#storeId').val(),
                    'employeeId':$('#employeeId').val()
                },
                success:function(data){
                    if(data == null){
                        $("#enginDepartId").html('');
                    } else {
                        var html = "<option value=''></option>";
                        for(var i=0;i<data.length;i++){
                            var sec = "";
                            if('${bizPmAttendSalarySummaryBillRel.enginDepartId}' == data[i].value){
                                sec = "selected='selected'";
                                $("#s2id_enginDepartId .select2-chosen").html(data[i].label);
                            }
                            html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>";
                        }
                        html+='';
                        $("#enginDepartId").append(html);
                    }
                }
            });
        }

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
		<li class="active"><a href="${ctx}/attend/bizPmAttendSalarySummaryBillRel/findList">工资单批次审批列表</a></li>
		<%-- <shiro:hasPermission name="attend:bizPmAttendSalarySummaryBillRel:edit"><li><a href="${ctx}/attend/bizPmAttendSalarySummaryBillRel/form">工资单批次审批添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmAttendSalarySummaryBillRel" action="${ctx}/attend/bizPmAttendSalarySummaryBillRel/findSalarySummaryBillRelList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear required"  onchange="getDepartments()">
					<form:option value="" label=""/>
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId" class="input-medium needClear required">
					<%-- <form:options items="${fns:getEngineListWithUserConditionsForBiddenChangeForOrder()}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<form:option value="${bizPmAttendSalarySummaryBillRel.enginDepartId }" label="${bizPmAttendSalarySummaryBillRel.departmentName }" />
				</form:select>
			</li>
			<li><label>考勤月份：</label>
				<input name="attendMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${bizPmAttendSalarySummaryBillRel.attendMonth}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true,maxDate:'#F{$dp.$D(\'hidTime\')}'});"/>
					
				<input name="hidTime" id="hidTime" type="hidden" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${maxMonth}"/>
			</li>
			<li><label>批次状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:option value="10" label="已生成批次"/>
					<form:option value="20" label="批次审核通过"/>
				</form:select>
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
				<th>工资批次编号</th>
				<th>考勤月份</th>
				<th>批次生成日期</th>
				<th>工资单数量</th>
				<th>工资批次状态</th>
				<th>操作人</th>
				<shiro:hasPermission name="attend:bizPmAttendSalarySummaryBillRel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmAttendSalarySummaryBillRel">
			<tr>
				<td>
					${fns:getStoreLabel(bizPmAttendSalarySummaryBillRel.storeId, '')}
				</td>
				<td>
					${bizPmAttendSalarySummaryBillRel.pmAttendSalarySummaryBillCode }
				</td>
				<td>
					${bizPmAttendSalarySummaryBillRel.attendMonth }
				</td>
				<td>
					<fmt:formatDate value="${bizPmAttendSalarySummaryBillRel.generatedDatetime }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizPmAttendSalarySummaryBillRel.salrayBillCount }
				</td>
				<td>
					<c:if test="${bizPmAttendSalarySummaryBillRel.status=='10'}">10-已生成批次</c:if>
					<c:if test="${bizPmAttendSalarySummaryBillRel.status=='20'}">20-工资批次审核通过</c:if>
					<c:if test="${bizPmAttendSalarySummaryBillRel.status=='30'}">30-工资批次已作废</c:if>
				</td>
				<td>
					<c:if test="${empty bizPmAttendSalarySummaryBillRel.realName }">
						系统管理员
					</c:if>
					<c:if test="${!empty bizPmAttendSalarySummaryBillRel.realName }">
						${bizPmAttendSalarySummaryBillRel.realName }
					</c:if>
				</td>
				<shiro:hasPermission name="attend:bizPmAttendSalarySummaryBillRel:edit"><td>
					<c:if test="${bizPmAttendSalarySummaryBillRel.status!='30'}">
						<a href="${ctx}/attend/bizPmAttendSalarySummaryBillRel/form?pmAttendSalarySummaryBillId=${bizPmAttendSalarySummaryBillRel.pmAttendSalarySummaryBillId}&flag=2">详情</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>