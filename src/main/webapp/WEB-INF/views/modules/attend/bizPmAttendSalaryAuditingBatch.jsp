<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤月度表管理</title>
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
		//全选
		function checkBox(name,checked){
			$("input[name='"+name+"']").attr("checked", checked);
		}
		//生成项目经理考勤批次
		function submitForm(){
			var s=$("input[name='ids']:checked").size();
			if(s<=0){
				alert("至少选择一个");
				return;
			}
			var storeId = $("#storeId").val();
			var enginDepartId = $("#enginDepartId").val();
			var attendMonth = $("#attendMonth").val();
			var money = 0;
            //判断是否是同一门店、考勤月份的工资单
            var gongzicard = $("input[name='ids']:checked");

            var flag = false;
            if(gongzicard.length > 1){
                for(var i = 0 ;i<gongzicard.length ;i++){
					if(i>0){
                       // 当前和前一个比较
                        var storeBefore = $("#storeId"+gongzicard[i-1].value).val();
                        var storeAfter = $("#storeId"+gongzicard[i].value).val();
                        var attendMonthBefore = $("#attendMonth"+gongzicard[i-1].value).val();
                        var attendMonthAfter = $("#attendMonth"+gongzicard[i].value).val();
                        if(storeBefore!=storeAfter || attendMonthBefore!=attendMonthAfter){
                            flag = true;
                            break;
						}
					}

                }
            }
            if(flag){
                alert("请选择相同的门店与考勤月份的月度工资单！");
                return;
            }

			if(confirm("你确定提交吗")){
				alert("你共选择了"+s+"个项目经理生成考勤批次");
				var ids = "";
				var storeIds="";
				var attendMonths="";
				$("input[name='ids']:checked").each(function() {
					ids += $(this).val() + ",";
				});
				ids = ids.substring(0,ids.length-1);
				for(var i = 0; i< $("input[name='ids']:checked").size(); i++){
					money = parseFloat($("input[name='ids']:checked").parent().parent().children()[14].innerText);
                    storeIds=$("#storeId"+$("input[name='ids']:checked")[i].value).val();
                    attendMonths=$("#attendMonth"+$("input[name='ids']:checked")[i].value).val();
				}
			 	$("#iform").attr("action","${ctx}/attend/bizPmAttendSalarySummaryBill/save?enginDepartId="+enginDepartId+"&storeId="+storeIds+
						"&salaryMonth="+attendMonths+"&salaryBillIds="+ids+"&money="+money+"&salrayBillCount="+s+"&status=50");
				$("#iform").attr("method","post").submit(); 
			}
			
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
		<li class="active"><a href="${ctx}/attend/bizPmAttendSalaryBill/salaryBillAuditingBatch">月度工资审核</a></li>
		<%-- <shiro:hasPermission name="attend:bizPmAttendMonth:edit"><li><a href="${ctx}/attend/bizPmAttendMonth/form">考勤月度表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmAttendSalaryBill" action="${ctx}/attend/bizPmAttendSalaryBill/findSalaryBillAuditingBatch" method="post" class="breadcrumb form-search">
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
			<li><label>考勤月份：</label>
				<input name="attendMonth" id="attendMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${bizPmAttendSalaryBill.attendMonth}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true,maxDate:'#F{$dp.$D(\'hidTime\')}'});"/>
					
				<input name="hidTime" id="hidTime" type="hidden" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${maxMonth}"/>
			</li>
			<li>
				<label>项目经理：</label>
				<form:input type="text" path="itemManager" htmlEscape="false" maxlength="3" maxvalue="100" class="input-medium"/>
			</li>
			<li>
				<label>考勤率：</label>
				<form:input type="text" path="attendRateStart" htmlEscape="false" maxlength="3" maxvalue="100" class="input-medium"/>-
				<form:input type="text" path="attendRateEnd" htmlEscape="false" maxlength="3" maxvalue="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input id="batch" class="btn btn-primary" type="button" onclick="submitForm()" value="生成工资单批次"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
		<form id="iform">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th><input type="checkbox" onclick="checkBox('ids',this.checked)"/>全选</th>
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
					<!-- <th>扣款总金额</th> -->
					<th>考勤率</th>
					<th>实际薪酬</th>
					<th>考勤单状态</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page}" var="bizPmAttendSalaryBill">
				<tr>
					<td>
						<input type="checkbox" value="${bizPmAttendSalaryBill.salaryBillId}" name="ids"/>
					</td>
					<td hidden>
						<input type="text" hidden="hidden" id="storeId${bizPmAttendSalaryBill.salaryBillId}" value="${bizPmAttendSalaryBill.storeId}">
					</td>
					<td hidden>
						<input type="text" hidden="hidden" id="attendMonth${bizPmAttendSalaryBill.salaryBillId}" value="${bizPmAttendSalaryBill.attendMonth}">
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
					<%-- <td>
						${bizPmAttendSalaryBill.pmStarSalaryPunish}
					</td> --%>
					<td>
						${bizPmAttendSalaryBill.attendRate}
					</td>
					<td>
						${bizPmAttendSalaryBill.pmStarSalaryReal}
					</td>
					<td>
						<c:if test="${bizPmAttendSalaryBill.status=='40'}">40-工资单审核通过</c:if>
						<c:if test="${bizPmAttendSalaryBill.status=='90'}">90-工资批次作废</c:if>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>