<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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

		var managerPhone;//项目经理手机号
		var managerName;//项目经理姓名
		var managerId;//项目经理id
		function getMangagerName(){
			//拿到项目经理的姓名
			var r =document.getElementsByName("ids");
			/* var managerName;
			var managerId; */
		    for(var i=0;i<r.length;i++){
		         if(r[i].checked){
		       	 var nameAndId = r[i].value;
		         var arr =  r[i].value.split("@-#-@");
		         managerName = arr[0];
		         managerId = arr[1];
		         managerPhone = arr[2];
		       }
		    }
			$("#itemManager").val(managerName);	 
			$("#itemManagerId").val(managerId);
		}
		
		//参数：小区名 楼牌号 设计师名 设计师手机号  客户名 客户手机
		function save(id,storeId,communityName,buildNumber,buildUnit,buildRoom,designerName,designerPhone,customerName,customerPhone){
			
			var employeeName = $("#itemManager").val();	 
			var employeeId = $("#itemManagerId").val();
		  	
			var size = $("input[name='ids']:checked").size();
		    if(size == 1){	
		    	window.location.href = "${ctx}/order2/order2/saveManager1?id="+id+"&managerName="+employeeName+"&managerId="+employeeId+"&managerPhone="+managerPhone;
		    	
		    }else{
		    	alert("请选择项目经理！");
		    	return;
		    }
		}
		
		
		//地图分配项目经理
		function mapManager(id){
			window.location.href = "${ctx}/order2/order2/mapManager?orderId="+id;
		}
		
		//返回
		function fanhui(){
			var hidProjectMode = $("#hidProjectMode").val();
			if(hidProjectMode == "1"){
				window.location.href = "${ctx}/order2/order2/listUnAllotCY";
			}else{
				window.location.href = "${ctx}/order2/order2/listUnAllotManagerTraditionCT";
			}
			
		}
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order2/order2/allotManager?id=${order.id}">分配项目经理</a></li>
	</ul>
	<div class="breadcrumb form-search">
	<form:form id="searchForm1" modelAttribute="order2" class="breadcrumb form-search">
		<input type="hidden" id="hidProjectMode" value="${order.projectMode }">
		<table>
			<tr >
				<td style="width:33%"><label>订单编号：</label>${order.orderNumber }</td>
				<td style="width:33%"><label>客户姓名：</label>${order.customerName }</td>
				<td style="width:33%"><label>客户手机号：</label>${order.customerPhone }</td>
			</tr>
			<tr>
				<td><label>客户类型：</label>
					${fns:getDictLabel(order.customerType, 'cus_type', '')}
				</td>
				<td><label>合同面积：</label>${order.contractArea }</td>
				<td><label>户型：</label>
					${fns:getDictLabel(order.houseType, 'home_type', '')}
				</td>
			</tr>
			<tr>
				<td><label>小区名称：</label>${order.communityName }</td>
				<td><label>门牌号：</label>${order.buildNumber }-${order.buildUnit }-${order.buildRoom }</td>
				<td><label>详细地址：</label>${order.communityName }</td>
			</tr>
			<tr>
			
				<td><label>签约日期：</label>
					<fmt:formatDate value="${order.signContractDate }" pattern="yyyy-MM-dd" />
				</td>
				<td><label>合同开工日期：</label>
				<fmt:formatDate value="${order.contractStartDate }" pattern="yyyy-MM-dd" />
				</td>
				<td><label>合同工期：</label>${order.contractTime }天</td>
			</tr>
			<tr>
				<td><label>客户经理：</label>${order.cusManager }</td>
				<td>
					<label>设计师：</label>${order.designerName }
					<input id="designerPhone" name="designerPhone" type="hidden" value="${order.designerPhone }"/>
				</td>
				<td>
				<label>项目经理：</label>
					<input id="itemManager" name="itemManager" type="text" value="${order.itemManager }" readOnly="readonly"/>
					<input id="itemManagerId" name="itemManagerId" type="hidden" value="${order.itemManagerId }" readOnly="readonly"/>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input id="btnSubmit" class="btn btn-primary" name="btnSubmit" type="button" value="保存" onclick="save('${order.id}','${order.storeId}','${order.communityName }','${order.buildNumber }','${order.buildUnit }','${order.buildRoom }','${order.designerName }','${order.designerPhone }','${order.customerName }','${order.customerPhone }')"/>
					<input id="btnBack" class="btn btn-primary" type="button" value="返回" onclick ="fanhui()"/></li>
					<input id="map" class="btn btn-primary" name="btnSubmit" type="button" value="地图分配项目经理" onclick="mapManager('${order.id}')"/>
				</td>
			</tr>
		</table>
	</form:form>
		<sys:message content="${message}"/>
	</div>
	 
	<form:form id="searchForm" modelAttribute="itemManager" action="${ctx}/order2/order2/allotManager?id=${order.id}" method="post" class="breadcrumb form-search">
	
		<%-- <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/> --%>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="realname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>选择</th>
				<th>项目经理编号</th>
				<th>项目经理</th>
				<th>工程模式</th>
				<th>排名</th>
				<th>被换单次数</th>
				<th>星级</th>
				<th>NPS值</th>
				<th>星级承接量</th>
				<th>在施工数</th>
				<th>承接量负荷</th>
				<th>总订单数</th>
				<th>住址</th>
				<th>推荐工人数</th>
				<th>住址和工地距离(km)</th>
				<!-- <shiro:hasPermission name="employee:bizEmployee:edit"><th>操作</th></shiro:hasPermission> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="bizEmployee" varStatus="status">
			<tr>
				<td>
					<%-- <input type="radio" name="ids" value="${bizEmployee.realname }" onclick="getMangagerName()"/> --%>
					<input type="radio" name="ids" value="${bizEmployee.realname }@-#-@${bizEmployee.id}@-#-@${bizEmployee.phone}" onclick="getMangagerName()"/>
				</td>
				<td>
					${bizEmployee.no}
				</td>
				<td>
					${bizEmployee.realname}
				</td>
				<td>
					${fns:getDictLabel(bizEmployee.projectMode, 'employee_project_mode', '')}
				</td>
				<td>
					${bizEmployee.sort}
				</td>
				<td>
				
				<c:if test="${bizEmployee.exchangeOrderTimes == null}">
					0
					</c:if>
					<c:if test="${bizEmployee.exchangeOrderTimes != null}">
						${bizEmployee.exchangeOrderTimes}
					</c:if>
				</td>
				<td>
					${bizEmployee.star}
				</td>
				<td>
					${bizEmployee.nps}
				</td>
				<td>
					${bizEmployee.totalCount}
				</td>
				<td>
					${bizEmployee.buildingCount}
				</td>
				<td>
					${bizEmployee.totalCountWeight}
				</td>
				<td>
					${bizEmployee.orderCount}
				</td>
				<td>
					${bizEmployee.address}
				</td>
				<td>
					${bizEmployee.workerIntroduceCount}
				</td>
				<td>
					${bizEmployee.distance}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>