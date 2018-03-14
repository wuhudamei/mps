<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单结算日志</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			findEngineListByStoreIdAndProjectMode();
			
			$("#allCheck").click(function () {
	            if ($(this).attr("checked")) { // 全选 
	                $("input[name='installStatus']").each(function () {
	                    $(this).attr("checked", true);
	                });
	            } else { // 取消全选 
	                $("input[name='installStatus']").each(function () {
	                    $(this).attr("checked", false);
	                });
	            }
	        });	
			
			
			/* findEngineListByStoreIdAndProjectMode(); */
		
			/* var v = $("#engineDepartIdInput").val();
			alert(v)
			("#engineDepartId option[value='"+v+"']").attr("selected", true) */
			
			
		});
	
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		 function findEngineListByStoreIdAndProjectMode(){
	            var html3 = '<option value=""></option>';
	            var storeId = $("#storeId").val();
	            var projectModeValue = $("#projectMode").val();
	            var temp = '${projectManagerSettlement.engineDepartId}';
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
	                        	if(data[v].engineDepartId == temp){
	                                $("#s2id_engineDepartId").find(".select2-chosen").text(data[v].engineDepartName);
	                                html3 +='<option selected="selected" value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'

	                           }else{
	                                html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'
	                           }
	                        }
	                        $("#engineDepartId").html(html3);
	                    } else {
	                        $("#engineDepartId").html(html3);
	                    } 
	                }

	            });
	        }
		
		//清空查询条件
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="">订单结算日志</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="projectManagerSettlement" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label> 
			
			
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>工程模式：</label>
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="4">准产业</form:option>
					</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear">
				</form:select>
			</li>
			
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManagerName" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>区域</th>
				<th>订单编号</th>
				<th>小区名称</th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>质检员</th>
				<th>中期结算日志</th>
				<th>竣工结算日志</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="order" varStatus="status">
			<tr>
				<td>${fns:getStoreLabel(order.storeId,'')}</td>
				<td>${fns:getDictLabel(order.projectMode, 'project_mode','')}</td>
				<td>${fns:getElacLabel(order.engineDepartId,'')}</td>
				<td>${order.orderNumber}</td>
				<td>${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}</td>
				<td>${order.customerName}-${order.customerPhone}</td>
				<td>${order.itemManagerName }</td>
				<td>${order.itemManagerPhone }</td>
				<td>${order.orderInspectorName}</td>
				<td><a href="${ctx }/PreSettleBilllog/middetail?id=${order.id}&orderId=${order.orderId}&settleBillType=1">查看</a></td>
				<td><a href="${ctx }/PreSettleBilllog/middetail?id=${order.id}&orderId=${order.orderId}&settleBillType=2">查看</a></td>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>