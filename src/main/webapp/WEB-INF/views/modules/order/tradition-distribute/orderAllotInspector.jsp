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
	
		var inspectorPhone;
		var inspectorName;
		var inspectorId;
		function getMangagerName(){
			//拿到质检员的姓名
			var r =document.getElementsByName("ids");
			/* var inspectorName;
			var inspectorId; */
		    for(var i=0;i<r.length;i++){
		         if(r[i].checked){
		       	 var nameAndId = r[i].value;
		         var arr =  r[i].value.split("@-#-@");
		         inspectorName = arr[0];
		       	 inspectorId = arr[1];
		       	 inspectorPhone = arr[2];
		       }
		    }
			$("#orderInspector").val(inspectorName);	 
			$("#orderInspectorId").val(inspectorId);	 
		}
		
		function save(id,communityName,buildNumber,buildUnit,buildRoom,customerName,customerPhone){
			var itemManagerId;
			var itemManagerPhone;
			var itemManagerName;
			var employeeName = $("#orderInspector").val();	 
			var employeeId = $("#orderInspectorId").val();
			var isReDistribute = "${tradition}";
			var isLongwayCommission = $("input[name='isLongwayCommission']:checked").val();
		  	var size = $("input[name='ids']:checked").size();
		    if(size == 1){	
		    	//重新分配
		    	if(1==isReDistribute){
		    		window.location.href = "${ctx}/order2/order2/resendInspector?id="+id+"&inspectorName="+employeeName+"&inspectorId="+employeeId+"&inspectorPhone="+inspectorPhone+"&isLongwayCommission="+isLongwayCommission+"&tradition="+1;
					  	
		    		
		    		
		    	}else{
			    window.location.href = "${ctx}/order2/order2/saveInspector?id="+id+"&inspectorName="+employeeName+"&inspectorId="+employeeId+"&inspectorPhone="+inspectorPhone+"&isLongwayCommission="+isLongwayCommission+"&tradition="+1;
		    		
		    	}
			    //window.location.href = "${ctx}/order2/order2/saveInspector?id="+id+"&inspectorName="+employeeName+"&inspectorId="+employeeId+"&inspectorPhone="+inspectorPhone;
		    }else{
		    	alert("请选择质检员！");
		    	return;
		    }
		}
		
	
		//返回
		function fanhui(){
			window.location.href = "${ctx}/order2/order2/listAllotInspectorTradition"
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order2/order2/allotInspectorTradition?id=${order.id}">分配质检员</a></li>
	</ul>
	<div class="breadcrumb form-search">
	<form:form id="searchForm1" modelAttribute="order2" class="breadcrumb form-search">
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
				<td><label>设计师：</label>${order.designerName }</td>
				<td><label>质检员：</label>
					<input id="orderInspector" name="orderInspector" type="text" value="${order.orderInspector }" readonly="readonly" required="required"/>
					<input id="orderInspectorId" name="orderInspectorId" type="hidden" value="${order.orderInspectorId }" readOnly="readonly" required="required"/>				
				</td>
			</tr>
			<tr>
				<td><label>是否有远程费提成：</label>
					<input type="radio" name="isLongwayCommission" value="0" checked="checked" />无
					<input type="radio" name="isLongwayCommission" value="1" />有
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input id="btnSubmit" class="btn btn-primary" name="btnSubmit" type="button" value="保存" onclick="save('${order.id}','${order.communityName }','${order.buildNumber }','${order.buildUnit }','${order.buildRoom }','${order.customerName }','${order.customerPhone }')"/>
					<input id="btnBack" class="btn btn-primary" type="button" value="返回" onclick ="fanhui()"/></li>
				</td>
			</tr>
		</table>
	</form:form>
		<sys:message content="${message}"/>
	</div>
	 
	<form:form id="searchForm" modelAttribute="inspector" action="${ctx}/order2/order2/allotInspectorTradition?id=${order.id}" method="post" class="breadcrumb form-search">
	
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
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
				<th>质检员编号</th>
				<th>质检员姓名</th>
				<th>工程模式</th>
				<th>星级</th>
				<th>NPS值</th>
				<th>排名</th>
				<th>在施在检工数</th>
				<th>累计检查工地数</th>
				<th>住址</th>
				<th>住址和工地距离(km)</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="bizEmployee">
			<tr>
				<td>
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
					${bizEmployee.star}
				</td>
				<td>
					${bizEmployee.nps}
				</td>
				<td>
					${bizEmployee.sort}
				</td>
				<td>
					${bizEmployee.checkingCount} 
				</td>
				<td>
					${bizEmployee.checkedCount} 
				</td>
				<td>
					${bizEmployee.address}
				</td>
				<td>
					${bizEmployee.distance}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>