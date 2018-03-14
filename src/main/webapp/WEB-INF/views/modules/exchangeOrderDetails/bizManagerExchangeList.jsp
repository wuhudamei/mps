<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
		});
		
		function getDepartments(){
			
			var html3 = '<option value=""></option>';
			var storeId = $("#storeid").val();
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
							if('${bizEmployee.enginDepartId}' == data[v].engineDepartId){
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
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function clearButton(){
			document.getElementById("searchForm").reset();
			var inputObjs = jQuery("#searchForm input[type='text']");
			for (var i = 0; i < inputObjs.length; i++) {
				var inputObj = inputObjs[i];
				inputObj.value = "";
			}

			var selectObjs = jQuery("#searchForm input[class='input-medium']");
			for (var i = 0; i < selectObjs.length; i++) {
				var selectObj = selectObjs[i];
				selectObj.value = "";
			}
		}
		function formSubmit() {
	        $("#searchForm").attr("action", "${ctx}/exchangeOrderDetails/exchangeOrderDetails/findmanagerlist?empType=3");
	        $("#searchForm").submit();
	    }

		function detailInfo(id){
			var startExChangeDate = $("#beginCheckDatetime").val();
			var endExChangeDate = $("#endCheckDatetime").val();
			window.location.href='${ctx}/exchangeOrderDetails/exchangeOrderDetails/managerDetails?id='+id+'&startExChangeDate='+startExChangeDate+'&endExChangeDate='+endExChangeDate;
		}
		function exportExcel(){
			 //门店
	        var storeId1 = '${bizEmployee.storeid}';
	        var storeId = $("#storeid").val();
	        if(storeId1 != storeId){
	            alertx("请先点击查询按钮后，再导出");
	            return false;

	        }
	        //工程模式
	        var projectMode1 = '${bizEmployee.projectMode}';
	        var projectMode = $("#projectMode").val();
	        if(projectMode1 != projectMode){
	            alertx("请先点击查询按钮后，再导出");
	            return false;

	        }
	        //区域
	        var enginDepartId1 = '${bizEmployee.enginDepartId}';
	        var enginDepartId= $("#enginDepartId").val();
	        if(enginDepartId1 != enginDepartId){
	            alertx("请先点击查询按钮后，再导出");
	            return false;

	        }
	        //项目经理
	        var realname1 = '${bizEmployee.realname}';
	        var realname= $("#realname").val();
	        if(realname1 != realname){
	            alertx("请先点击查询按钮后，再导出");
	            return false;

	        }
	      /*   //被换开始时间
	        var startExchanegeDate1 = $("#startTime").val();
	        var startExchanegeDate= $("#beginCheckDatetime").val();
	        if(startExchanegeDate1 != startExchanegeDate){
	        	 console.log("222");
	            alertx("请先点击查询按钮后，再导出");
	            return false;
	        }
	      //被换截至时间
	        var endExchanegeDate1 = $("#endTime").val();
	        console.log(endExchanegeDate1);
	        var endExchanegeDate= $("#endCheckDatetime").val();
	        console.log(endExchanegeDate1);
	        if(endExchanegeDate1 != endExchanegeDate){
	        	 console.log("1111");
	            alertx("请先点击查询按钮后，再导出");
	            return false;

	        } */
			$("#searchForm").attr("action", "${ctx}/exchangeOrderDetails/exchangeOrderDetails/exportManagerExchangeToExcel");

	        $("#searchForm").submit();

	        $("#searchForm").attr("action", "${ctx}/exchangeOrderDetails/exchangeOrderDetails/manager_list?empType=3");
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	<shiro:hasPermission name="manager:bizManagerExchange:view">
		<li class="active"><a href="${ctx}/exchangeOrderDetails/exchangeOrderDetails/manager_list?empType=3">项目经理信息</a></li>
	</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEmployee"    action="${ctx}/exchangeOrderDetails/exchangeOrderDetails/findmanagerlist?empType=3" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden"   value='<fmt:formatDate value="${startExchanegeDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="startTime"/>
		<input type="hidden"   value='<fmt:formatDate value="${endExchanegeDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="endTime"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeid" class="input-medium needClear" onchange="getDepartments()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<form:select path="projectMode" class="input-medium needClear"  onchange="getDepartments()">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId"  class="input-medium needClear" name="enginDepartId" id="enginDepartId" >
					<form:option value=""></form:option>
				</form:select>
			</li>
			
			<li><label>项目经理：</label>
				<form:input path="realname" htmlEscape="false" maxlength="11" class="input-medium needClear" name="realname"/>
			</li>
			<%-- <li><label>被换时间：</label>
				<input name="startExchanegeDate"  type="text" readonly="readonly"     value='<fmt:formatDate value="${startExchanegeDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="beginCheckDatetime" maxlength="20" class="input-medium Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCheckDatetime\')}',isShowClear:false});"/>
				-
				<input name="endExchanegeDate" type="text" readonly="readonly" value='<fmt:formatDate value="${endExchanegeDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="endCheckDatetime" maxlength="20" class="input-medium Wdate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCheckDatetime\')}',isShowClear:false});"/>   
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="formSubmit()" value="查询"/></li>

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
				<th>项目经理</th>
				<th>手机号</th>
				<th>星级</th>
				<th>被换累计次数</th>
				<shiro:hasPermission name="manager:bizManagerExchange:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEmployee">
			<tr>
				<td>
					${fns:getStoreLabel(bizEmployee.storeid, '')}
				</td>
				<td>
					${fns:getDictLabel(bizEmployee.projectMode, 'employee_project_mode', '')}
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
					${bizEmployee.exchangeOrderTimes}
				</td>
				<shiro:hasPermission name="manager:bizManagerExchange:view">
				<td>
					<a href="javascript:detailInfo('${bizEmployee.id}');">详情</a>
    			<%-- 	<a href="${ctx}/exchangeOrderDetails/exchangeOrderDetails/managerDetails?id=${bizEmployee.id}">详情</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>