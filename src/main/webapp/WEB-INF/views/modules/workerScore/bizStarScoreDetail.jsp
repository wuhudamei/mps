<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人评分查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findEngineListByStoreIdAndProjectMode();
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
		
		
		function findEngineListByStoreIdAndProjectMode(){
			$("#engineDepartSelect").html('');
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

							for (var v = 0; v < data.length; v++) {
								html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
							}
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});		
		}
		
		function btnSubmit(){
			$("#searchForm").submit()
		}
		
		function returnHistory(){
			var url = "${ctx}/workerScore/workerScoreSelect/list";
			 window.location.href= url;
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/workerScore/workerScoreSelect/">任务包评价详情</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizWorkerScore" action="${ctx}/workerScore/workerScoreSelect/queryBizWorkerScoreDetail" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="storeId" name="storeId" type="hidden" value="${storeId}"/>
		<input id="projectMode" name="projectMode" type="hidden" value="${projectMode}"/>
		<input id="empId" name="empId" type="hidden" value="${empId}"/>
		<ul class="ul-form">
			<li><label>客户姓名：</label>
				<form:input path="customerName" value="" class="input-medium"/>
			</li>
			
			<li><label>评价类别：</label>
				<form:select path="evalRoleType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('eval_role_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input class="btn btn-primary" type="button"  value="查询" onclick="btnSubmit()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>评价时间</th>
				<th>工人组长</th>
				<th>客户信息</th>
				<th>任务包名称</th>
				<th>评价类别</th>
				<th>总分</th>
				<th>实际得分</th>
				<c:if test="${page.list != null && page.list.size()>0}">
				<c:forEach items="${page.list.get(0).bizEvalActivityIndexList}" var="evaLIndex">
				  <th>${evaLIndex.indexName}</th>
				</c:forEach>
				</c:if> 
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEvalWorkGrade" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td><fmt:formatDate value="${bizEvalWorkGrade.gradeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${bizEvalWorkGrade.groupRealName}</td>
				<td>${bizEvalWorkGrade.customerMessage}-${bizEvalWorkGrade.customerName}</td>
				<td>${bizEvalWorkGrade.packageName}</td>
				<td>${fns:getDictLabel(bizEvalWorkGrade.evalRoleType, 'eval_role_type', '')}</td>
				<td>${bizEvalWorkGrade.evaltotalScore}</td>
				<td>${bizEvalWorkGrade.gradtotalScore}</td>
				<c:forEach items="${bizEvalWorkGrade.bizEvalActivityIndexList}" var="evaLIndexScore">
				  <c:if test="${evaLIndexScore.indexScore == null}">
				   <td>--</td>
				  </c:if>
				   <c:if test="${evaLIndexScore.indexScore != null}">
				   <td>${evaLIndexScore.indexScore}</td>
				   </c:if>
				  
				</c:forEach>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div style="margin-top:15px;text-align: center;">
	<input id="btnCancel" align="center" class="btn" type="button" value="返 回"	onclick="returnHistory()" />
	</div>
</body>
</html>