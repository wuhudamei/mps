<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>大看板</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
			
			$("#projectMode option").each(function(index,element){
				var provalue = $(this).val();
				if(provalue == '2' || provalue == '3'){
					$("#projectMode option[value="+provalue+"]").remove();
				}
			});
			
			/* var pro = "${bizProjectProgressBoning.projectMode}";
			alert(pro)
			if(pro == null || pro == ''){
				alert("asd")
				$("#s2id_projectMode .select2-chosen").html("全部");
				$("#projectMode option[value="+3+"]").attr("selected",true);
			} */
			
		});

		function getDepartments(){
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':'1',
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null || data == ""){
						$("#enginDepartId").html("");
						$("#s2id_enginDepartId .select2-chosen").html("");
					}else{
						var content = "<option></option>";
						for(var i=0;i<data.length;i++){
							if('${bizProjectProgressBoning.enginDepartId}' == data[i].value){
								$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
								content = content + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
							}else{
								content = content + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
							}
						}
						$("#enginDepartId").html(content);
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

		// 批次通过
		function eidtOrderNode(obj,orderId){
			top.$.jBox.confirm("您确认更新吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					loading('正在提交，请稍等...');
					$(obj).removeAttr("href");
					$(obj).removeAttr("onclick");
					window.location.href="${ctx}/projectprogressboning/bizProjectProgressBoning/editOrderNode?orderId="+orderId;
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/projectprogressboning/bizProjectProgressBoning/loadList1">大看板列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizProjectProgressBoning" action="${ctx}/projectprogressboning/bizProjectProgressBoning/loadList1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>区域：</label>
				<select id="enginDepartId" name="enginDepartId" class="input-medium needClear">

				</select>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>新老房：</label>
				<form:select path="houseIsNew" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:option value="0" label="老房"/>
					<form:option value="1" label="新房"/>
				</form:select>
			</li>
			<li><label style="width: 110px">实际开工日期：</label>
				<input name="beginActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizProjectProgressBoning.beginActualStartDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizProjectProgressBoning.endActualStartDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th rowspan="2" style="vertical-align:middle">门店</th>
				<th rowspan="2" style="vertical-align:middle">工程模式</th>
				<th rowspan="2" style="vertical-align:middle">区域</th>
				<th rowspan="2" style="vertical-align:middle">接单日期</th>
				<th rowspan="2" style="vertical-align:middle">客户姓名</th>
				<th rowspan="2" style="vertical-align:middle">客户电话</th>
				<th rowspan="2" style="vertical-align:middle">工程地址</th>
				<th colspan="2" style="text-align: center">项目经理</th>
				<th colspan="2" style="text-align: center">开同开工时间</th>
				<th colspan="2" style="text-align: center">实际工期时间</th>
				<th rowspan="2" style="vertical-align:middle">开工延期天数</th>
				<th rowspan="2" style="vertical-align:middle">实际开工客<br/>户是否签字</th>
				<th rowspan="2" style="vertical-align:middle">变更/停电/停<br/>水/客户自装项<br/>目延期天数</th>
				<th rowspan="2" style="vertical-align:middle">客户是<br/>否签字</th>
				<th rowspan="2" style="vertical-align:middle">【当前进度】</th>
				<th rowspan="2" style="vertical-align:middle">【实际完成日期】</th>
			</tr>
			<tr>
				<th style="vertical-align: middle">姓名</th>
				<th style="vertical-align: middle">电话</th>
				<th style="vertical-align: middle">合同签订开工日期</th>
				<th style="vertical-align: middle">合同签订竣工日期</th>
				<th style="vertical-align: middle">实际开工日期</th>
				<th style="vertical-align: middle">实际竣工日期</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="boning">
			<tr>
				<td>${fns:getStoreLabel(boning.storeId, '')}</td>
				<td>${fns:getDictLabel(boning.projectMode,'employee_project_mode', '')}</td>
				<td>${boning.enginDepartName}</td>
				<td><fmt:formatDate value="${boning.orderCreateDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.customerName}</td>
				<td>${boning.customerPhone}</td>
				<td>${boning.detailAddress}</td>
				<td>${boning.itemManager}</td>
				<td>${boning.itemManagerPhone}</td>
				<td><fmt:formatDate value="${boning.contractStartDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.contractEndDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.actualStartDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.actualEndDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.startDiffDay}</td>
				<td><c:if test="${boning.isNeedSign eq '0'}">否</c:if><c:if test="${boning.isNeedSign eq '1'}">是</c:if></td>
				<td>${boning.selfDecorateDelayDays}</td>
				<td><c:if test="${boning.isSelfDecorateNeedSign eq '0'}">否</c:if><c:if test="${boning.isSelfDecorateNeedSign eq '1'}">是</c:if></td>
				<td>${boning.nodeName}</td>
				<td><fmt:formatDate value="${boning.nodeLastActualDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>