<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>超定额复检查询列表</title>
	<meta name="decorator" content="default"/>
	<meta content="*" name="Access-Control-Allow-Origin"><!--跨域请求  -->
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
		 $(function() {
		       $("#checkAll").click(function() {
		            $('input[name="packageStateId"]').attr("checked",this.checked); 
		        });
		        var $status = $("input[name='packageStateId']");
		        $status.click(function(){
		            $("#checkAll").attr("checked",$status.length == $("input[name='packageStateId']:checked").length ? true : false);
		        });
		  });
		 
		 function getDepartments(){
				$("#enginDepartId").html('');
				$.ajax({
					url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
					type:'post',
					dataType:'json',
					data:{
						'storeId':$('#storeId').val(),
						'projectMode':$('#projectMode').val(),
						'employeeId':$('#employeeId').val()
					},
					success:function(data){
						if(data == null){
							$("#enginDepartId").append('');
						} else {
							var html = "<option value=''></option>";
							for(var i=0;i<data.length;i++){
								var sec = "";
								if('${bizOrderTaskpackage.engineDepartId}' == data[i].value){
				            		sec = "selected='selected'";
				            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
				            	}
								html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
							}
							html+='';
			        		$("#enginDepartId").append(html);
						}
					}
				});
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/recheckTaskpackagePage">超定额复检查询列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackage" action="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/recheckTaskpackage" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="getDepartments()" id="storeId">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>区域：</label>
				<form:select path="engineDepartId" id="enginDepartId" class="input-medium needClear">
					<form:option value="${bizOrderTaskpackage.engineDepartId }" label="${bizOrderTaskpackage.departmentName }" />
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>任务包编号：</label>
				<form:input path="orderTaskpackageCode" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemCustomer" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>质检员：</label>
				<form:input path="inspectorName" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label style="width:160px;">质检复核时间：</label>
				<input name="beginRecheckTime" type="text" id ="beginRecheckTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginRecheckTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endRecheckTime\')}',isShowClear:false});"/> - 
				<input name="endRecheckTime" type="text" id ="endRecheckTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endRecheckTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginRecheckTime\')}',isShowClear:false});"/>
			</li>
			<li><label style="width:160px;">验收日期：</label>
				<input name="beginAcceptanceTime" type="text" id="beginAcceptanceTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginAcceptanceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endAcceptanceTime\')}',isShowClear:false});"/> - 
				<input name="endAcceptanceTime" type="text" id="endAcceptanceTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endAcceptanceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginAcceptanceTime\')}',isShowClear:false});"/>
			</li>
			<li style="width:800px">
				<label>任务包状态：</label><input id="checkAll" type="checkbox" value="全选" />全选
				<input type="checkbox" name="packageStateId" value="90" <c:if test="${fns:checkIsExits(bizOrderTaskpackage.packageStateId,'90')}"> checked="checked"</c:if>/>90-待质检复核结算单
				<input type="checkbox" name="packageStateId" value="95" <c:if test="${fns:checkIsExits(bizOrderTaskpackage.packageStateId,'95')}"> checked="checked"</c:if>/>95-质检已复核
			</li>
			<li class="btns" style="width:100px"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>验收日期</th>
				<th>质检复核时间</th>
				<th>质检员</th>
				<th>质检员手机号</th>
				<th>订单编号</th>
				<th>顾客信息</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>工人组长</th>
				<th>工人组长手机号</th>
				<th>当前状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="taskpackage" varStatus="index">
			<tr>
				<td>
					${fns:getStoreLabel(taskpackage.storeId, '')}
				</td>
				<td>${fns:getDictLabel(taskpackage.projectMode, 'package_project_mode', '')}</td>
				<td>
					${taskpackage.departmentName }
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.acceptanceTime }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.recheckTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${taskpackage.inspectorName }
				</td>
				<td>
					${taskpackage.inspectorPhone }
				</td>
				<td>
					${taskpackage.orderNumber }
				</td>
				<td>
					${taskpackage.customerMessage }-${taskpackage.customerName }
				</td>
				<td>
					${taskpackage.itemCustomer }
				</td>
				<td>
					${taskpackage.managerPhone }
				</td>
				<td>
					${taskpackage.orderTaskpackageCode }
				</td>
				<td>
					${taskpackage.packageName }
				</td>
				<td>
					${taskpackage.groupRealname }
				</td>
				<td>
					${taskpackage.groupPhone }
				</td>
				<td>
					${taskpackage.packageStateId }-${taskpackage.packageStateName }
				</td>
				<td>
					<a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/details?id=${taskpackage.settlementId}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>