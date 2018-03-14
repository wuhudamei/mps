<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人组长</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findEngineList();
		});
		
		function findEngineList(){
			$("#enginDepartId").html("");
			 $("#s2id_enginDepartId .select2-chosen").html("");
			 $.ajax({
	            	url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
	            	type:'post',
					dataType:'json',
					data:{
						'storeId':$('#storeid').val(),
						'projectMode':$("#projectMode").val(),
						'employeeId':$('#employeeId').val()
					},
	                success : function(data) {
	                	if(data.length<1){
							$("#enginDepartId").append("<option value='' selected='selected' >请选择...</option>");
							$("#s2id_enginDepartId .select2-chosen").html("请选择...");
						} else {
							var html = "<option value=''>请选择...</option>";
							$("#s2id_enginDepartId .select2-chosen").html("请选择...");
							for(var i=0;i<data.length;i++){
								var sec = "";
								if('${bizEmployee.enginDepartId}' == data[i].value){
				            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
				            		html += "<option value='" + data[i].value +"' selected='selected' >" + data[i].label + "</option>"
				            	}else{
				            		html += "<option value='" + data[i].value +"'>" + data[i].label + "</option>"
				            	}
							}
			        		$("#enginDepartId").append(html);
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

		function clearButton(){
			$("input[name='noRelateCard']").removeAttr("checked");
			$("input[name='noRelateGroup']").removeAttr("checked");
			var inputObjs = jQuery("#searchForm input[type='text']");
			for (var i = 0; i < inputObjs.length; i++) {
				var inputObj = inputObjs[i];
				inputObj.value = "";
			}

		}
		function detailInfo(id){
			var startExChangeDate = $("#beginCheckDatetime").val();
			var endExChangeDate = $("#endCheckDatetime").val();
			window.location.href='${ctx}/exchangeOrderDetails/exchangeOrderDetails/details?id='+id+'&startExChangeDate='+startExChangeDate+'&endExChangeDate='+endExChangeDate;
		}
		function exportExcel(){
			 //门店
	        var storeId1 = '${bizEmployee.storeid}';
	        var storeId = $("#storeid").val();
	        if(storeId1 != storeId){
	            alertx("请先点击查询按钮后，再导出");
	            return false;

	        }
	        /* //工程模式
	        var projectMode1 = '${bizEmployee.projectMode}';
	        var projectMode = $("#projectMode").val();
	        if(projectMode1 != projectMode){
	            alertx("请先点击查询按钮后，再导出1");
	            return false;

	        } */
	        //区域
	        var enginDepartId1 = '${bizEmployee.enginDepartId}';
	        var enginDepartId= $("#enginDepartId").val();
	        if(enginDepartId1 != enginDepartId){
	            alertx("请先点击查询按钮后，再导出");
	            return false;

	        }
	        //电话
	        var phone1 = '${bizEmployee.phone}';
	        var phone= $("#phone").val();
	        if(phone1 != phone){
	            alertx("请先点击查询按钮后，再导出");
	            return false;

	        }
	      //工人组长
	        var realname1 = '${bizEmployee.realname}';
	        var realname= $("#realname").val();
	        if(realname1 != realname){
	            alertx("请先点击查询按钮后，再导出");
	            return false;

	        }
	       /*  //被换开始时间
	        var startExchanegeDate1 = $("#startTime").val();
	        var startExchanegeDate= $("#beginCheckDatetime").val();
	        if(startExchanegeDate1 != startExchanegeDate){
	            alertx("请先点击查询按钮后，再导出");
	            return false;
	        }
	        //被换截至时间
	        var endExchanegeDate1 = $("#endTime").val();
	        var endExchanegeDate= $("#endCheckDatetime").val();
	        if(endExchanegeDate1 != endExchanegeDate){
	            alertx("请先点击查询按钮后，再导出");
	            return false;

	        } */
	        
			$("#searchForm").attr("action", "${ctx}/exchangeOrderDetails/exchangeOrderDetails/exportWorkerExchangeToExcel");

	        $("#searchForm").submit();

	        $("#searchForm").attr("action", "${ctx}/exchangeOrderDetails/exchangeOrderDetails/worker_list?empType=2");
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	<shiro:hasPermission name="worker:bizWorkerExchange:view">
		<li class="active"><a href="${ctx}/exchangeOrderDetails/exchangeOrderDetails/worker_list?empType=2">工人信息</a></li>
	</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEmployee" action="${ctx}/exchangeOrderDetails/exchangeOrderDetails/worker_list?empType=2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden"   value='<fmt:formatDate value="${startExchanegeDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="startTime"/>
		<input type="hidden"   value='<fmt:formatDate value="${endExchanegeDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="endTime"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeid" class="input-medium needClear" onchange="findEngineList()">
					<form:option value="" label="请选择..."/>
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>工程模式</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="findEngineList()">
						<form:option value="" label="请选择..." />
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="findEngineList()">
						<form:option value="" label="请选择..."/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>		
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId"  class="input-medium needClear" >
				</form:select>
			</li>
			
			<li><label>工人组长：</label>
				<form:input path="realname" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			<%-- <li><label>被换时间：</label>
				<input name="startExchanegeDate"  type="text" readonly="readonly"     value='<fmt:formatDate value="${startExchanegeDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="beginCheckDatetime" maxlength="20" class="input-medium Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCheckDatetime\')}',isShowClear:false});"/>
				-
				<input name="endExchanegeDate" type="text" readonly="readonly" value='<fmt:formatDate value="${endExchanegeDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="endCheckDatetime" maxlength="20" class="input-medium Wdate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCheckDatetime\')}',isShowClear:false});"/>   
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>

			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearButton()"/></li>
			
			<li class="btns"><input class="btn btn-primary" type="button" value="导出" onclick="exportExcel()"/></li>

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>工人组长姓名</th>
				<th>手机号</th>
				<th>星级</th>
				<th>工种</th>
				<th>被换累计次数</th>
				<shiro:hasPermission name="worker:bizWorkerExchange:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEmployee">
			<tr>
				<td>
					${fns:getStoreLabel(bizEmployee.storeid, '')}
				</td>
				<td>
					${fns:getDictLabel(bizEmployee.projectMode, 'project_mode', '')}
				</td>
				<td>
					${bizEmployee.departmentName}
				</td>
				<td>
					${bizEmployee.realname}
				</td>
				<td>
					${bizEmployee.phone}
				</td>
				<td>
					${fns:getDictLabel(bizEmployee.star, 'emp_star', '')}
				</td>
				<td>
                    ${fns:getDictLabel(bizEmployee.worktype, 'emp_work_type', '')}
				</td>
				<td>
					${bizEmployee.exchangeOrderTimes}
				</td>
				<shiro:hasPermission name="worker:bizWorkerExchange:view">
				<td>
    				<%-- <a href="${ctx}/exchangeOrderDetails/exchangeOrderDetails/details?id=${bizEmployee.id}">查看详情</a> --%>
    				<a href="javascript:detailInfo('${bizEmployee.id}');">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>