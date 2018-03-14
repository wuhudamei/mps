<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("tbody>tr").click(function(){

			    $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

			});
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function findEngineList(){

            var html3 = '<option value=""></option>';
            var storeId = $("#storeId").val();
            if (storeId=="" ||undefined==storeId ) {
                return;
            }
            //根据门店    动态加载工程区域
            $.ajax({
                url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
                + storeId,
                type : "get",
                success : function(data) {
                    if (null!= data && data.length > 0) {
                        for (var v = 0; v < data.length; v++) {
                            html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'
                        }
                        $("#orderAreaId").html(html3);
                    } else {
                        $("#orderAreaId").html(html3);
                    }

                }

            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/orderMaterialsStandard/bizOrderMaterialsLight/list">筒灯申请记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderMaterialsStandardQuery" action="${ctx}/orderMaterialsStandard/bizOrderMaterialsLight/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId"  class="input-medium needClear" >
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>订单号：</label>
				<input type="text" name="orderNumber"  class="input-medium needClear" value="${bizOrderMaterialsStandardQuery.orderNumber}"  >
			</li>
			<li>
				<label>客户姓名：</label>
				<input type="text" name="customerName"  class="input-medium needClear" value="${bizOrderMaterialsStandardQuery.customerName}"  >
			</li>
			<li>
				<label>申请人：</label>
				<input type="text" name="itemManager"  class="input-medium needClear" value="${bizOrderMaterialsStandardQuery.itemManager}"  >
			</li>
			<li><label>订单是否作废：</label>
				<form:select path="isScrap"   class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dict_iscountsquare')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="orderAreaId" class="input-medium needClear">
					<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>订单号</th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>区域</th>
				<th>订单是否作废</th>
				<th>配送费</th>
				<th>灯具和五金总额</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderMaterialsStandardQuery">
			<tr>
				<td>
					${bizOrderMaterialsStandardQuery.storeName}
				</td>
				<td>
					${bizOrderMaterialsStandardQuery.orderNumber}
				</td>
				<td>
					${bizOrderMaterialsStandardQuery.customerName}
				</td>
				<td>
					${bizOrderMaterialsStandardQuery.itemManager}
				</td>
				<td>
						${bizOrderMaterialsStandardQuery.orderArea}
				</td>
				<td>
						${fns:getDictLabel(bizOrderMaterialsStandardQuery.isScrap, 'dict_iscountsquare', '')}
				</td>
				<td>
						${bizOrderMaterialsStandardQuery.shippingFee}元
				</td>
				<td>
					${bizOrderMaterialsStandardQuery.sum}元
				</td>
				<td>
					<a href="${ctx}/orderMaterialsStandard/bizOrderMaterialsLight/form?orderId=${bizOrderMaterialsStandardQuery.orderId}">详情</a>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>