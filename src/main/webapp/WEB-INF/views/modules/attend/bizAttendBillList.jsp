<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>员工信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
				
				var v=$("#attendMonth").val();
				if(v==null || v =='' || v.length==0){
					//初始化默认为上一月
					var myDate = new Date();
					
					var year=myDate.getFullYear();
					var month=myDate.getMonth();
					
					var flag =true;
					
					if(month<'10' && month !='0'){
						month='0'+month;
					}else if(month=='0'){
						year = year-1;
						month='12';
					
					}
					var d=year + '-'+month;
					if(flag){
						flag=false;
						$("#attendMonth").val(d);
					}
				}
		});
		
		//全选
		function checkBox(name,checked){
			$("input[name='"+name+"']").attr("checked", checked);
		}
		//批量提交 批次
		function optSubmit(){
			var s=$("input[name='ids']:checked").size();
			if(s<=0){
				alert("至少选择一个");
				return;
			}
			if(!confirm("你确定提交吗")){
				return;
			}else{
				alert("你共选择了"+s+"个项目经理生成考勤批次")
			}
			
			var enginDepartId = $("#enginDepartId").val();
			
			var storeId =$("#storeId").val();
			if(storeId==null || storeId=='' || storeId != '${bizAttendBill.storeId}'){
				alertx("请至少点击门店查询后再生成考勤批次")
				return;
			}
			var attendBatchMonth =$("#attendBatchMonth").val();
			
			$("#jform").attr("action","${ctx}/bizAttend/bizAttendBills/createBatch"+"?enginDepartId="+enginDepartId+"&storeId="+storeId+
							"&attendBatchMonth="+attendBatchMonth);
			$("#jform").attr("method","post").submit();
			
		}
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
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
		
		
		function getDepartments(){
		
			var html3 = '<option value=""></option>';
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

						/* for (var v = 0; v < data.length; v++) {
							html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
						} */
						
						for (var v = 0; v < data.length; v++) {
							if('${bizAttendBill.enginDepartId}' == data[v].engineDepartId){
								$("#s2id_enginDepartId .select2-chosen").html(data[v].engineDepartName);
								html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
							}else{
								html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
							}
							
							/* html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'  */
						}
								
						$("#enginDepartId").html(html3);
					} else {
						$("#enginDepartId").html(html3);
					}
				}

			});
	}
		
	</script>
</head>
<body>
	<shiro:hasPermission name="bizAttendBills:bizAttendBills:view">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/bizAttend/bizAttendBills/list">考勤单列表</a></li>
		</ul>
	</shiro:hasPermission>
	<form:form id="searchForm" modelAttribute="bizAttendBill" action="${ctx}/bizAttend/bizAttendBills/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="getDepartments()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" selectIndex="1" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				
				<form:select path="projectMode" class="input-medium needClear" id="projectMode" onchange="getDepartments()">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId"  class="input-medium needClear" id="enginDepartId" >
					<form:option value=""></form:option>
					<%-- <form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}" itemLabel="label" itemValue="value" htmlEscape="false"/>  --%>
				</form:select>
			</li>
			
			<li>
			<label >考勤月份：</label>
  		 		<input type="text" name="attendMonth" id="attendMonth" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM',maxDate:'%y-{%M-1}'})" class="input-medium Wdate" readonly="readonly" value="<fmt:formatDate value='${bizAttendBill.attendMonth}' pattern='yyyy-MM'/>"/>
			</li>
			
			<li><label>考勤单状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label="" />
					<form:option value="10" label="未生成考勤单" />
					<form:option value="20" label="已生成考勤单" />
					
				</form:select>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<shiro:hasPermission name="bizAttendBills:bizAttendBills:edit">
			
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input type="button" id="pc" value="生成考勤批次" onclick="optSubmit()"> </li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<form id="jform">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" onclick="checkBox('ids',this.checked)"/></th>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>项目经理</th>
				<th>手机号</th>
				<th>考勤月份</th>
				<th>星级</th>
				<th>底薪标准</th>
				<th>考勤单编号</th>
				<th>应出勤天数</th>
				<th>实际出勤天数</th>
				<th>事假</th>
				<th>病假</th>
				<th>年假</th>
				<th>全勤</th>
				<th>半勤</th>
				<th>补休</th>
				<th>考勤单状态</th>
				<shiro:hasPermission name="bizAttendBills:bizAttendBills:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${page.list}" var="bizAttendBill">
			
			<tr>
				
				<c:choose>
						<c:when test="${!empty bizAttendBill.attendBatchId && bizAttendBill.attendBatchId ==0 && bizAttendBill.status!=30}">
							<td>
								<input type="checkbox" value="${bizAttendBill.id}" name="ids"/>
							</td>
						</c:when>
						<c:when test="${(empty bizAttendBill.attendBatchId) || bizAttendBill.attendBatchId !='0'}">
							<td>
							</td>
						</c:when>
						<c:otherwise><td></td></c:otherwise>
					</c:choose>
			
				<td>
					${fns:getStoreLabel(bizAttendBill.storeId, '')}
					<input type="hidden" name="attendEmployeeId" id="attendEmployeeId" value="${bizAttendBill.attendEmployeeId}">
				</td>
				<td>
					${fns:getDictLabel(bizAttendBill.projectMode, 'employee_project_mode', '')}
				</td>
				<td>
					${bizAttendBill.departmentName}
				</td>
				<td>
					${bizAttendBill.managerName}
				</td>
				<td>
					${bizAttendBill.phone}
				</td>
				<td>
					<c:if test="${!empty bizAttendBill.id }">
						<fmt:formatDate value="${bizAttendBill.attendMonth}" pattern="yyyy年MM月" />
						<input value="<fmt:formatDate value='${bizAttendBill.attendMonth}' pattern='yyyy-MM'/>" id="attendBatchMonth" type="hidden">
					</c:if>
					<c:if test="${empty bizAttendBill.id }">
						<fmt:formatDate value="${bizAttendBill.attendMonth2}" pattern="yyyy年MM月"/>
					</c:if>
					
				</td>
				<td>
					${fns:getDictLabel(bizAttendBill.starLevel, 'manager_star', '')}
				</td>
				<td>
                  ${bizAttendBill.basicSalary}元
				</td>
				<td>
                  ${bizAttendBill.attendBillCode }
				</td>
				<td>
                  ${bizAttendBill.attendDaysMust}
				</td>
				<td>
                  ${bizAttendBill.attendDaysTotal }
				</td>
				<td>
                  ${bizAttendBill.leaveDaysThing }
				</td>
				<td>
                  ${bizAttendBill.leaveDaysSick }
				</td>
				<td>
                  ${bizAttendBill.leaveDaysAnnual }
				</td>
				<td>
                  ${bizAttendBill.attendDaysWhole }
				</td>
				<td>
                  ${bizAttendBill.attendDaysHalf }
				</td>
				<td>
                  ${bizAttendBill.restDays }
				</td>
				<td>
					<c:choose>
						<c:when test="${bizAttendBill.status==20 }">
							已生成考勤单
						</c:when>
						<c:when test="${bizAttendBill.status==30 }">
							已生成考勤批次
						</c:when>
						<c:otherwise>未生成考勤单</c:otherwise>
					</c:choose>
				</td>
				<shiro:hasPermission name="bizAttendBills:bizAttendBills:edit">
				
				<c:if test="${!empty bizAttendBill.id && bizAttendBill.status!=30}">
					<td>
	    				<a href="${ctx}/bizAttend/bizAttendBills/form?id=${bizAttendBill.id}&attendEmployeeId=${bizAttendBill.attendEmployeeId}&attendMonth=<fmt:formatDate value="${bizAttendBill.attendMonth}" pattern="yyyy-MM"/>
	    					&storeId=${bizAttendBill.storeId}&projectMode=${bizAttendBill.projectMode}&starLevel=${bizAttendBill.starLevel}&attendEmployeeRole=${bizAttendBill.attendEmployeeRole}">修改</a>
					</td>
				</c:if>
				
				<c:if test="${empty bizAttendBill.id}">
				<td>
    				<a href="${ctx}/bizAttend/bizAttendBills/bill?attendEmployeeId=${bizAttendBill.attendEmployeeId}&attendMonth=<fmt:formatDate value="${bizAttendBill.attendMonth2}" pattern="yyyy-MM"/>
    					&storeId=${bizAttendBill.storeId}&projectMode=${bizAttendBill.projectMode}&starLevel=${bizAttendBill.starLevel}&attendEmployeeRole=${bizAttendBill.attendEmployeeRole}">生成考勤单</a>
				</td>
				</c:if>
				
				<c:if test="${bizAttendBill.status==30 && !empty bizAttendBill.id}">
					<td>
	    				已生成考勤批次
					</td>
				</c:if>
				</shiro:hasPermission>
			</tr>
			
		</c:forEach>
		</tbody>
		</form>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>