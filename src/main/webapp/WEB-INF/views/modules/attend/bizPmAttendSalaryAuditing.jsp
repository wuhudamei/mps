<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤月度表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
            $("#allCheck").click(function () {
                if ($(this).attr("checked")) { // 全选
                    $("input[name='salaryBillIds']").each(function () {
                        $(this).attr("checked", true);
                    });
                } else { // 取消全选
                    $("input[name='salaryBillIds']").each(function () {
                        $(this).attr("checked", false);
                    });
                }
            });

            $("input[name='salaryBillIds']").bind("click",function(){
                //全选
                if($("input[name='salaryBillIds']:not(:checked)").length==0){
                    $("#allCheck").attr("checked",true);
                }else{
                    //不选
                    $("#allCheck").attr("checked",false);
                }
            });

            $("#createButton").click(function() {
                if ($("input[name='salaryBillIds']:checked").length == 0) {
                    alertx("请选择工资单!");
                    return;
                }
				/*var count = 0;*/
                var salaryBillIds = "";
                var billCount = $("input[name='salaryBillIds']:checked").length;
                $("input[name='salaryBillIds']:checked").each(function () {
                    if (salaryBillIds == "") {
                        salaryBillIds = $(this).val();
                    } else {
                        salaryBillIds = salaryBillIds + "," + $(this).val();
                    }
                    //alert(salaryBillIds);
                    //alert($('#pmAttendMonthId').val());
                });
               top.$.jBox.confirm("确认审核通过吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        loading('正在提交，请稍等...');
                        $("#createButton").unbind("click");
                        window.location.href="${ctx}/attend/bizPmAttendSalaryBill/updateStatus?pmAttendMonthId="+$('#pmAttendMonthId').val()+"&status="+40+"&salaryBillIds="+salaryBillIds+"&storeId="+$('#storeId').val()+"&enginDepartId="+$('#enginDepartId').val();
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
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
        });
		function page(n,s){
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
					'projectMode':$('#projectMode').val(),
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null){
						$("#enginDepartId").html('');
					} else {
						var html = "<option value=''></option>";
						for(var i=0;i<data.length;i++){
							var sec = "";
							if('${bizPmAttendSalaryBill.enginDepartId}' == data[i].value){
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
		<li class="active"><a href="${ctx}/attend/bizPmAttendSalaryBill/findSalaryBillAuditing">月度工资审核</a></li>
		<%-- <shiro:hasPermission name="attend:bizPmAttendMonth:edit"><li><a href="${ctx}/attend/bizPmAttendMonth/form">考勤月度表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmAttendSalaryBill" action="${ctx}/attend/bizPmAttendSalaryBill/findSalaryBillAuditing" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear required" onchange="getDepartments()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId" class="input-medium needClear required">
					<%-- <form:options items="${fns:getEngineListWithUserConditionsForBiddenChangeForOrder()}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<form:option value="${bizPmAttendSalaryBill.enginDepartId }" label="${bizPmAttendSalaryBill.departmentName }" />
				</form:select>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium" />
			</li>
			<li><label>考勤月份：</label>
				<input name="attendMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${bizPmAttendSalaryBill.attendMonth}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true,maxDate:'#F{$dp.$D(\'hidTime\')}'});"/>
					
				<input name="hidTime" id="hidTime" type="hidden" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${maxMonth}"/>
			</li>
			<li>
				<label>考勤率：</label>
				<form:input type="text" path="attendRateStart" htmlEscape="false" maxlength="3" maxvalue="100" class="input-medium"/>-
				<form:input type="text" path="attendRateEnd" htmlEscape="false" maxlength="3" maxvalue="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="审核通过" id="createButton"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" id="allCheck"/>全选</th>
				<th>门店</th>
				<th>区域</th>
				<th>项目经理</th>
				<th>手机号</th>
				<th>考勤月份</th>
				<th>星级</th>
				<th>底薪标准</th>
				<th>工资单编号</th>
				<th>应签到次数</th>
				<th>实际签到次数</th>
				<th>实际取值总和</th>
				<th>欠缺签到次数</th>
				<%--<th>扣款总金额</th>--%>
				<th>考勤率</th>
				<th>实际薪酬</th>
				<th>考勤单状态</th>
				<shiro:hasPermission name="attend:bizPmAttendMonth:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="bizPmAttendSalaryBill">
			<tr>
				<td>
					<input type="checkbox" id="salaryBillIds" name="salaryBillIds" value="${bizPmAttendSalaryBill.salaryBillId}"/>
					<input id="pmAttendMonthId" name="pmAttendMonthId" type="hidden" value="${bizPmAttendSalaryBill.pmAttendMonthId}"/>
				</td>
				<td>
					${fns:getStoreLabel(bizPmAttendSalaryBill.storeId, '')}
				</td>
				<td>
					${bizPmAttendSalaryBill.departmentName}
				</td>
				<td>
					${bizPmAttendSalaryBill.itemManager}
				</td>
				<td>
					${bizPmAttendSalaryBill.phone}
				</td>
				<td>
					${bizPmAttendSalaryBill.attendMonth}
				</td>
				<td>
					${fns:getDictLabel(bizPmAttendSalaryBill.pmStar, 'manager_star', '')}
				</td>
				<td>
					${bizPmAttendSalaryBill.pmStarSalary }
				</td>
				<td>
					${bizPmAttendSalaryBill.pmAttendSalaryBillCode}
				</td>
				<td>
					${bizPmAttendSalaryBill.countDay}
				</td>
				<td>
					${bizPmAttendSalaryBill.sjCountDay}
				</td>
				<td>
					${bizPmAttendSalaryBill.actualValueTotal}
				</td>
				<td>
					${bizPmAttendSalaryBill.wcDay}
				</td>
				<%--<td>
					${bizPmAttendSalaryBill.pmStarSalaryPunish}
				</td>--%>
				<td>
					${bizPmAttendSalaryBill.attendRate}
				</td>
				<td>
					${bizPmAttendSalaryBill.pmStarSalaryReal}
				</td>
				<td>
					<c:if test="${bizPmAttendSalaryBill.status=='30'}">30-已生成工资单</c:if>
					<c:if test="${bizPmAttendSalaryBill.status=='90'}">90-工资批次作废</c:if>
				</td>
				<shiro:hasPermission name="attend:bizPmAttendMonth:edit">
					<td>
    					<a href="${ctx}/attend/bizPmAttendSalaryBill/bizPmAttendSalaryBillDetail?salaryBillId=${bizPmAttendSalaryBill.salaryBillId}&itemManager=${bizPmAttendSalaryBill.itemManager}&itemManagerId=${bizPmAttendSalaryBill.itemManagerId}&storeId=${bizPmAttendSalaryBill.storeId}&enginDepartId=${bizPmAttendSalaryBill.enginDepartId}&attendMonth=${bizPmAttendSalaryBill.attendMonth}&flag=1&pmStarSalaryReal=${bizPmAttendSalaryBill.pmStarSalaryReal}">查看</a>
    					<%--<a href="${ctx}/attend/bizPmAttendSalaryBill/updateStatus?salaryBillId=${bizPmAttendSalaryBill.salaryBillId}&pmAttendMonthId=${bizPmAttendSalaryBill.pmAttendMonthId}&status=40" onclick="return confirmx('确认审核通过吗?',this.href)">审核通过</a>--%>
    					<a href="${ctx}/attend/bizPmAttendSalaryBill/deleteStatus?salaryBillId=${bizPmAttendSalaryBill.salaryBillId}&pmAttendMonthId=${bizPmAttendSalaryBill.pmAttendMonthId}&status=35" onclick="return confirmx('确认要删除吗?',this.href)">废除</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>