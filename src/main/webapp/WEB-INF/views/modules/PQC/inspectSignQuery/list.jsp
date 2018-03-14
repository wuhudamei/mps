<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检员签到查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
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
	</script>
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/pqc/inspectSignQuery/inspectSignList">质检员签到查询</a></li>
	</ul>
	<form:form id="searchForm"  modelAttribute="inspectSignQueryEntity" action="${ctx}/pqc/inspectSignQuery/inspectSignListCheck" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId"  class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium needClear">
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium needClear">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
				</form:select>
			</c:if>
			</li>
			<li><label>质检员：</label>
			<form:input path="inspectName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			<li><label style="width:150px;">签到日期时分秒：</label>
				<input name="startDate" type="text" readonly="readonly"     value='<fmt:formatDate value="${startDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="beginCheckDatetime" maxlength="20" class="input-medium Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCheckDatetime\')}',isShowClear:false});"/>
				<input name="endDate" type="text" readonly="readonly" value='<fmt:formatDate value="${endDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="endCheckDatetime" maxlength="20" class="input-medium Wdate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCheckDatetime\')}',isShowClear:false});"/>   
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
					<th>工程模式</th>
				<th>签到日期</th>
				<th>质检员</th>
				<th>订单编号</th>
				<th>顾客信息</th>
				<th>签到地址</th>
				<th>误差(单位:米)</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sign" varStatus="status">
			<tr>
			
				<td>
					${fns:getStoreLabel(sign.storeId, '')}
				</td>
				<td>${fns:getDictLabel(sign.projectMode, 'project_mode', '')}</td>
				<td>
					<fmt:formatDate value="${sign.signDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				${sign.inspectName}
				</td>
				<td>
				${sign.orderNumber}
				</td>
				<td>
					${sign.communityName}-${sign.buildNumber}-${sign.buildUnit}-${sign.buildRoom}-${sign.customerName}
				</td>
				<td>
					${sign.signAddress}
				</td>
				<td>
					${sign.signDistance}
				</td>
				
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>