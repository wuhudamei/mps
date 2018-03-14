<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/modules/settleJs/layer/layer.js"></script>
	<script src="${ctxStatic}/modules/settleJs/layer/extend/layer.ext.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			findEngineListByStoreIdAndProjectMode();
			
			var v=$("#attendBatchMonth").val();
			
			if(v==null || v =='' || v.length==0){
				//初始化默认为上一月
				var myDate = new Date();
				
				var year=myDate.getFullYear();
				var month=myDate.getMonth();
				
				var flag =true;
				
				if(month<'10' && month !='0'){
					month='0'+month;
				}else if(month=='0'){
					year=year-1;
					month='12';
				}
				
				var d=year + '-'+month;
				if(flag){
					flag=false;
					$("#attendBatchMonth").val(d);
				}
			}
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function invalid(id,status){
			  layer.prompt({title: '请输入作废的原因', formType: 2}, function(text){
                  var param = {
                      status:status,
                      id:id,
                      remarks:text
                  };
                       $.ajax({
                           url : "${ctx}/bizAttend/bizAttendBatch/invalid",
                           type: "post",
                           data: param,
                           async:false,
                           success : function(data) {
                               if('1'==data){
                                   layer.msg("作废成功", {icon: 1});
                                   
                                   window.location.href="${ctx}/bizAttend/bizAttendBatch/list?repage";
                               }
                           }
                       });
              });
		};
		
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
		
		
		//加载区域信息
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

							/* for (var v = 0; v < data.length; v++) {
								html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
							} */
							for (var v = 0; v < data.length; v++) {
								if('${bizAttendBatch.enginDepartId}' == data[v].engineDepartId){
									$("#s2id_engineDepartSelect .select2-chosen").html(data[v].engineDepartName);
									html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
								}
								
								/* html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'  */
							}
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}
					}

				});		
		}
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="bizAttendBatch" action="${ctx}/bizAttend/bizAttendBatch/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:option value=""></form:option>
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
				<li><label>工程模式：</label>
				
					<form:select path="projectMode" class="input-medium needClear" id="projectMode" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
				
			<li><label>区域：</label>
				 <form:select path="enginDepartId" class="input-medium needClear" id="engineDepartSelect" >
					<form:option value=""></form:option>
					<%-- <form:option value="">全部</form:option> --%>
					<%-- <form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/> 		 --%>
				</form:select>
			</li>
			
			<li>
			<label >考勤月份：</label>
  		 		<input type="text" name="attendBatchMonth" id="attendBatchMonth" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM',maxDate:'%y-{%M-1}'})" class="input-medium Wdate" readonly="readonly" value="${bizAttendBatch.attendBatchMonth}"/>
				
			</li>
			
			<li>
			<label >批次状态：</label>
  		 		<form:select path="status" class="input-medium needClear" id="status" >
					<form:option value=""></form:option>
					<form:option value="1">已生成批次</form:option>
					<form:option value="2">批次审核通过</form:option>
				</form:select>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>区域</th>
				<th>考勤批次编号</th>
				<th>考勤批次月份</th>
				<th>批次生成日期</th>
				<th>考勤单数量</th>
				<th>考勤批次状态</th>
				<th>操作人</th>
				<shiro:hasPermission name="bizAttend:bizAttendBatch:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="batch">
			<c:if test="${batch.status !='3'}">
				<tr>
					<td>${fns:getStoreLabel(batch.storeId, '')}
					</td>
					<td>${fns:getElacLabel(batch.enginDepartId, '')}</td>
					<td>${batch.attendBatchCode}</td>
					<td>${batch.attendBatchMonth }</td>
					<td> <fmt:formatDate value="${batch.batchDatetime }" pattern="yyyy-MM-dd"/> </td>
					<td>${batch.attendBillCount }</td>
					<td>
						<c:if test="${batch.status=='1'}">已生成批次</c:if>
						<c:if test="${batch.status=='2'}">审核通过</c:if>
					</td>
					<td>${batch.createName}</td>
					<shiro:hasPermission name="bizAttend:bizAttendBatch:edit"><td>
						<c:if test="${batch.status=='1' }">
							<a href="${ctx}/bizAttend/bizAttendBatch/updateStatus?id=${batch.id}&status=2" id="verify" onclick="return confirm('确定审核吗')">审核通过</a>
	    				<a id="btn" href="javascript:void(0)" onclick="invalid(${batch.id},3)">批次作废</a>
						</c:if>
	    				
	    				<a href="${ctx}/bizAttend/bizAttendBatch/exportExcel?id=${batch.id}">导出Execl</a>
	    				
	    				<a href="javascript:void(0)" onclick="window.open('${ctx}/bizAttend/bizAttendBatch/view?id=${batch.id}')">查看</a>
					</td>
					</shiro:hasPermission>
				</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
</body>
</html>