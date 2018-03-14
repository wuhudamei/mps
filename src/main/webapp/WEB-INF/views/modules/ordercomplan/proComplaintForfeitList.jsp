<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客诉罚款管理</title>
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
			var html3 = '<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
			if (storeId=="" ||undefined==storeId ) {
				return false;
			}
			//根据门店个   动态加载工程区域     + "&projectModeValue=" + projectModeValue ,工程模式 
			$.ajax({

						url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
								+ storeId ,
						type : "get",
						success : function(data) {
							if (null!= data && data.length > 0) {

								for (var v = 0; v < data.length; v++) {
									if('${proComplaintForfeit.orderacceptarea}' == data[v].engineDepartId){
										$("#s2id_engineDepartSelect .select2-chosen").html(data[v].engineDepartName);
										html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
									}else{
										html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
									}
								}
								
								$("#engineDepartSelect").html(html3);
							} else {
								$("#engineDepartSelect").html(html3);
							}

						}

					});		
			}
		
		
		
		//拒绝触发的事件
		function abandoned(id){
			$("#myAbandonedModal").modal('show');
			$("#myId").val(id );
			$("#workOrderCode").val( workOrderCode);
		}
		//拒绝 原因提交的事件
		function onclickAbandoned(){
			var v = $("#myId").val();
			var date = $("#reson").val();
// 			var workOrderCode = $("#workOrderCode").val();
			//有没有输入原因
			if(date == ""){
				$("#myNoAbandonedModal").modal('show');
			}else{
					window.location.href="${ctx}/ordercomplan/proComplaintForfeit/refuse?id="+v+"&exaOpinion="+date;
					}
			$("#reson").val('');
			$('#myAbandonedModal').modal('hide');		
			}
		
		//拒绝 取消 事件
		function onclickNoAbandoned(){
			$('#myAbandonedModal').modal('hide');
			$("#reson").val('');
		}	
		
		
		
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordercomplan/proComplaintForfeit/">客诉罚款列表</a></li>
		<shiro:hasPermission name="ordercomplan:proComplaintForfeit:edit"><li><a href="${ctx}/ordercomplan/proComplaintForfeit/form">客诉罚款添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="proComplaintForfeit" action="${ctx}/ordercomplan/proComplaintForfeit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<ul class="ul-form">

			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" id="storeId">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
				<li><label>区域：</label>
				<form:select path="orderacceptarea" class="input-medium needClear" id="engineDepartSelect">
					<%-- <form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
			</li>
			
			<li><label>接单时间：</label>
				<input name="beginGetOrderDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${proComplaintForfeit.beginGetOrderDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endGetOrderDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${proComplaintForfeit.endGetOrderDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			<li><label>承诺完成时间：</label>
				<input name="beginPromiseComDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${proComplaintForfeit.beginPromiseComDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endPromiseComDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${proComplaintForfeit.endPromiseComDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>实际完成时间：</label>
				<input name="beginActualComDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${proComplaintForfeit.beginActualComDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endActualComDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${proComplaintForfeit.endActualComDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>序号</th>
				<th>门店</th>
				<th>区域</th>
				<th>接单日期</th>
				<th>接单人</th>
				<th>投诉部门</th>
				<th>投诉人</th>
				<th>订单编号</th>
				<th>客户名称</th>
				<th>客户电话</th>
				<th>地址</th>
				<th>项目经理</th>
				<th>项目经理电话</th>
				<th>事项名称</th>
				<th>承诺完成时间</th>
				<th>实际完成时间</th>
				<th>惩罚金额</th>
				<th>审批意见</th>
				<th>状态</th> 
				<th>操作</th> 
<%-- 				<shiro:hasPermission name="ordercomplan:proComplaintForfeit:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proComplaintForfeit">
			<tr>
<%-- 				<td><a href="${ctx}/ordercomplan/proComplaintForfeit/form?id=${proComplaintForfeit.id}"> --%>
<%-- 					${proComplaintForfeit.orderId} --%>
<!-- 				</a></td> -->
				<td>
					${proComplaintForfeit.sortId}
				</td>
				<td>
				${fns:getStoreLabel(proComplaintForfeit.storeId, '')}
				</td>
				<td>
				${fns:getElacLabel(proComplaintForfeit.orderacceptarea, '')}
				</td>
				<td>
					<fmt:formatDate value="${proComplaintForfeit.getOrderDatetime}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
<%-- 					${proComplaintForfeit.sortId} --%>
				</td>
				<td>
						${fns:getDictLabel(proComplaintForfeit.complaintSource, 'complaintSource','')}
				</td>
				<td>
					${proComplaintForfeit.complaintPersonName}
				</td>
				<td>
					${proComplaintForfeit.orderNmber}
				</td>
				<td>
					${proComplaintForfeit.customername}
				</td>
				<td>
					${proComplaintForfeit.customerPhone}
				</td>
				<td>
					${proComplaintForfeit.customerAddr}
				</td>
				<td>
					${proComplaintForfeit.itemManager}
				</td>
				<td>
					${proComplaintForfeit.itemManagerPhone}
				</td>
				<td>
					${proComplaintForfeit.itemName}
				</td>
					
				<td>
					<fmt:formatDate value="${proComplaintForfeit.promiseComDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${proComplaintForfeit.actualComDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${proComplaintForfeit.punishMoney}
				</td>
				<td>
					${proComplaintForfeit.exaOpinion}
				</td>
				<td>
						${fns:getDictLabel(proComplaintForfeit.status, 'complaint_forfeit_status','')}
<%-- 					${proComplaintForfeit.status} --%>
				</td>
				
<%-- 				<input id="itemManagerId" name="itemManagerId" type="hidden" value="${proComplaintForfeit.itemManagerId}"/> --%>
				<td>
					<c:if test="${proComplaintForfeit.status ==  10 || empty proComplaintForfeit.status}">
    				<a href="${ctx}/ordercomplan/proComplaintForfeit/agree?id=${proComplaintForfeit.id}&itemManagerId=${proComplaintForfeit.itemManagerId}">同意</a><br/>
    				<a href="javascript:void(0)" onclick="abandoned (${proComplaintForfeit.id})" >拒绝</a>
					</c:if>
		
<%--     				<a href="${ctx}/ordercomplan/proComplaintForfeit/refuse?id=${proComplaintForfeit.id}">拒绝</a><br/> --%>
				</td>

<%-- 				<shiro:hasPermission name="ordercomplan:proComplaintForfeit:edit"><td> --%>
<%--     				<a href="${ctx}/ordercomplan/proComplaintForfeit/form?id=${proComplaintForfeit.id}">修改</a> --%>
<%-- 					<a href="${ctx}/ordercomplan/proComplaintForfeit/delete?id=${proComplaintForfeit.id}" onclick="return confirmx('确认要删除该客诉罚款吗？', this.href)">删除</a> --%>
<%-- 				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	
	
							<!-- 拒绝btn弹框的model -->
		<div  class="modal fade" id="myAbandonedModal" tabindex="-1" role="dialog" style="width:350px">
					<input  id="myId" type="hidden">
					<input  id="workOrderCode" type="hidden">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal">×</button>
						<h4 id="myModalLabel" align="center" style="color: black;">处理内容</h3><br>
						<form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
							<input id = "ids"name="id" value="${bizMaterialsStandardReceiveBill.id}" type="hidden">
							<input id = "workOrderCode1"name="workOrderCode" value="${bizMaterialsStandardReceiveBill.workOrderCode}" type="hidden">
							<input id = "status" name="status" value="20" type="hidden">
						<div  style="margin:10px;min-height:22px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
							<textarea id="reson" class="mask-text"  onkeyup="this.value = this.value.substring(0,100)" placeholder="请输入处理内容，多行输入，不多于100个汉字" maxlength="100" ></textarea>
							<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandoned()" >确定</a>
							<a href="javascript:void(0)" class="btn" onclick="onclickNoAbandoned()">关闭</a>
						</div>
					</div>
						</form:form>
		</div>
</body>
</html>