<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>标化辅材领取详情</title>
</head>
<body>
	<ul class="nav nav-tabs">
			<li><a href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBill/list">标化辅材领取记录列表</a></li>
			<li class="active"><a href="javascript:void(0)">标化辅材明细</a></li>
		</ul>
		<div>
				<div class="col-md-12 column">
					<div >
						<h3>
							<center>
								大美装饰管理平台--标化辅材明细--${ bizMaterialsStandardReceiveBillVo.materialsStandardReceiveBillCode }
							</center>
						</h3>
					</div>
				</div>
				<table style="width:100%" align="center" valign="center" class="table table-striped table-bordered table-condensed">
					<tr>
						<td style="width:50%"><label>客户姓名：</label>
							${bizMaterialsStandardReceiveBillVo.customerName }
						</td>
						<td style="width:50%"><label>订单编号：</label>${bizMaterialsStandardReceiveBillVo.orderNo }</td>
					</tr>
					<tr>
						<td style="width:50%"><label>领取人：</label>${ bizMaterialsStandardReceiveBillVo.receiveEmployeeName }</td>
						<td style="width:50%"><label>领取时间：</label>
							<fmt:formatDate value="${ bizMaterialsStandardReceiveBillVo.receiveDatetime }" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<td style="width:50%"><label>合计金额：</label>${ bizMaterialsStandardReceiveBillVo.receiveBillAmount }</td>
						<td style="width:50%"></td>
					</tr>
				</table>
		
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>序号</th>
						<th>物料类别</th>
						<th>物料名称</th>
						<th>单价</th>
						<th>领取数量</th>
						<th>总价</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${list}" var="BizMaterialsStandardReceiveDetail" varStatus="status">
					<tr>
						<td>
							${status.index+1} 
						</td>
						<td>
							${BizMaterialsStandardReceiveDetail.materialsType} 
						</td>
						<td>
							${BizMaterialsStandardReceiveDetail.materialsName}
						</td>
						<td>
							${BizMaterialsStandardReceiveDetail.materialsPrice} 
						</td>
						<td>
							${BizMaterialsStandardReceiveDetail.receiveNumber}
						</td>
						<td>
							${BizMaterialsStandardReceiveDetail.materialsAmount}
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
	</div>
</body>
</html>