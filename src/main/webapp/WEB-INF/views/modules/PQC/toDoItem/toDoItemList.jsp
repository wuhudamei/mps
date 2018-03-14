<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待办事项监控</title>
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
		function findEngineListByStoreIdAndProjectMode(){
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
	        function formSubmit() {
	            $("#searchForm").attr("action", "${ctx}/toDoItemController/toDoItemList");
	            $("#searchForm").submit();
	        }
		
		
		
	</script>
</head>	
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
	<li class="active"><a href="#">待办事项监控</a></li>
	</ul>
	<form:form id="searchForm"  modelAttribute="toDoItemEntity" action="${ctx}/toDoItemController/toDoItemList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId"  class="input-medium needClear"  onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" >
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
				</form:select>
			</c:if>
			
			</li>
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单编号：</label>
			<form:input path="orderNumber" htmlEscape="false" maxlength="30" class="input-large"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			<li>
				<label>是否查看：</label>
				<select class="input-medium needClear"  name="isViewd">
				  <option  value =""></option>
				  <option value ="0" <c:if test='${toDoItemEntity.isViewd =="0"}'>selected="selected"</c:if> >未查看</option>
				  <option value ="1" <c:if test='${toDoItemEntity.isViewd =="1"}'>selected="selected"</c:if> >已查看</option>
				</select>
			</li>
			<li>
				<label>状态：</label>
				<select class="input-medium needClear"  name="isSolved">
				  <option  value =""></option>
				  <option value ="0" <c:if test='${toDoItemEntity.isSolved =="0"}'>selected="selected"</c:if> >未处理</option>
				  <option value ="1" <c:if test='${toDoItemEntity.isSolved =="1"}'>selected="selected"</c:if> >已处理</option>
				</select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="formSubmit()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;          </li>
			<li class="btns"><a style="font-size: 16px;" href="${ctx}/toDoItemController/toDoItemListToday"> 今日待办事项</a>	</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>门店</th>
				<th>区域</th>
				<th>项目经理</th>
				<th>顾客</th>
				<th>待办内容</th>
				<th>提醒时间</th>
				<th>是否查看</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="report" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${fns:getStoreLabel(report.storeId, '')}</td>
				<td>${report.engineDepartName }</td>
				<td>${report.managerName}</td>
				<td>${report.xiaoqu}-${report.lou}-${report.danyuan}-${report.shi}-${report.customerName}</td>
				<td>${report.toDoItemRemindTitle}</td>
				<td>
					<fmt:formatDate value="${report.toDoItemRemindDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${report.isViewd==0 }">
					未查看
					</c:if>
					<c:if test="${report.isViewd==1 }">
					已查看
					</c:if>
				</td>
				<td>
					<c:if test="${report.isSolved==0 }">
					未处理
					</c:if>
					<c:if test="${report.isSolved==1 }">
					已处理
					</c:if>
				</td>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>