<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/orderMaterialsStandard/bizOrderMaterialsStandard/list">标化辅料列表</a></li>
		<li class="active"><a href="${ctx}/orderMaterialsStandard/bizOrderMaterialsStandard/form?orderId=${bizOrderMaterialsStandardQuery.orderId}">标化辅料详情<shiro:hasPermission name="managersettlement:bizOrderMaterialsStandard:edit">${not empty bizOrderMaterialsStandard.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="managersettlement:bizOrderMaterialsStandard:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
		<div class="col-md-12 column">
					<div >
						<h3>
							<center>
								美得你--标化辅材申请明细
							</center>
						</h3>
					</div>
				</div>
	
	<h4>基础信息</h4>
	<br/>
	<table style="width:100%" align="center" valign="center" class="table table-striped table-bordered table-condensed">
					<tr>
						<td style="width:50%"><label>客户姓名：</label>
							${bizOrderMaterialsStandardQuery.customerName }
						</td>
						<td style="width:50%"><label>订单编号：</label>${bizOrderMaterialsStandardQuery.orderNumber }</td>
					</tr>
					<tr>
						<td style="width:50%"><label>项目经理：</label>${ bizOrderMaterialsStandardQuery.itemManager }</td>
						<td style="width:50%"> </td>
					</tr>
					<tr>
						<td style="width:50%"><label>配送费：</label>${ bizOrderMaterialsStandardQuery.shippingFee }</td>
						<td style="width:50%"><label>累计金额：</label>${ bizOrderMaterialsStandardQuery.sum + bizOrderMaterialsStandardQuery.shippingFee } </td>
					</tr>
	</table>
	<br/>
	<h4>申请单详情</h4>
	<br/>
	<table style="width:100%" align="center" valign="center" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>申请单号</th>
			<th>申请日期</th>
			<th>领取/配送方式</th>
			<th>领取/配送日期</th>
		</thead>
		<tbody>
			<c:forEach items="${billList}" var="bill">
				<tr>
					<td>${ bill.materialsStandardReceiveBillCode }</td>
					<td><fmt:formatDate value="${ bill.applyDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${fns:getDictLabel(bill.shippingType, 'biz_materials_shipping_type', '')}</td>
					<td><fmt:formatDate value="${ bill.receiveDatetime }" pattern="yyyy-MM-dd "/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/>
	<h4>标化辅料明细</h4>
	<br/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>序号</th>
						<th>物料类别</th>
						<th>物料名称</th>
						<th>单价</th>
						<th>已领取数量</th>
						<th>总价</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${list}" var="materialsByOrderId" varStatus="status">
					<tr>
						<td>
							${status.index+1} 
						</td>
						<td>
							${materialsByOrderId.materialsType} 
						</td>
						<td>
							${materialsByOrderId.materialsName}
						</td>
						<td>
							${materialsByOrderId.materialsPrice} 
						</td>
						<td >
							 ${materialsByOrderId.receiveNumberTotal} 
						</td>
						<td>
							${materialsByOrderId.materialsAmount}
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
</body>
</html>