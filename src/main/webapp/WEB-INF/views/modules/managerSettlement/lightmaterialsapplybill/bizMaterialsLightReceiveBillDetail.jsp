<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>灯具和五金申请详情</title>
<script type="text/javascript">
	$(document).ready(function() {
		receive();
	});
	function receive(){
		var receive=${list.status}
		$("#receive").html('');
		if(receive==10){
			
		}
		if(receive==20){
			
		}
		if(receive==30){
			
		}
		
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
			<li><a href="${ctx}/standradmaterialsrecords/bizMaterialsLightReceiveBillApply/list">筒灯灯带申请记录列表</a></li>
			<li class="active"><a href="javascript:void(0)">灯具和五金明细</a></li>
		</ul>
		<div>
				<div class="col-md-12 column">
					<div >
						<h3>
							<center>
								美得你--筒灯灯带明细--${ list.materialsStandardReceiveBillCode }
							</center>
						</h3>
					</div>
				</div>
				<table style="width:100%" align="center" valign="center" class="table table-striped table-bordered table-condensed">
					<tr>
						<td style="width:50%"><label>客户姓名：</label>
							${list.customerName }
						</td>
						<td style="width:50%"><label>订单编号：</label>${list.orderNumber }</td>
					</tr>
					<tr>
						<td style="width:50%"><label>申请人：</label>${ list.realName }</td>
						<td style="width:50%"><label>申请时间：</label>
							<fmt:formatDate value="${ list.applyDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
					</tr>
					<tr>
						<td style="width:50%"><label>领取人：</label>${ list.name }</td>
						<td style="width:50%"><label>领取时间：</label>
							<fmt:formatDate value="${ list.receiveDatetime }" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<td style="width:50%"><label>合计金额：</label>${ list.receiveBillAmount }</td>
						<td style="width:50%"><label>状态：</label>
						<c:if test="${list.status =='10' }">
						项目经理已申请
						</c:if>
						<c:if test="${list.status =='20' }">
						已领取
						</c:if>
						<c:if test="${list.status =='30' }">
						已废弃
						</c:if>
						</td>
					</tr>
						<c:if test="${list.status =='30' }">
					<tr>
						<td style="width:50%"><label>废弃原因：</label>${list.abandonReason}</td>
						<td style="width:50%"><label></label></td>	
					</tr>
						</c:if>
				</table>
			<%-- <input id="status" value=${list.status }> --%>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>序号</th>
						<th>物料类别</th>
						<th>物料名称</th>
						<th>单价</th>
						<th>建议申请数量</th>
						<th>申请数量</th>
						<th>领取数量</th>
						<th>总价</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${listStandard}" var="list" varStatus="status">
					<tr>
						<td>
							${status.index+1} 
						</td>
						<td>
							${list.materialsType} 
						</td>
						<td>
							${list.materialsName}
						</td>
						<td>
							${list.materialsPrice} 
						</td>
						<td>
							<c:if test="${ list.applyNumberSuggest==0}">
							
							</c:if>
							<c:if test="${ list.applyNumberSuggest!=0}">
							${list.applyNumberSuggest}
							</c:if>
						</td>
						<td>
							${list.receiveNumber}
						</td>
						<td >
							<c:if test="${ list.receiveNumberTotalSnap==null}">
							0.0
							</c:if>
							<c:if test="${ list.receiveNumberTotalSnap!=null}">
							${list.receiveNumberTotalSnap}
							</c:if>
						</td>
						<td>
							${list.materialsAmount}
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
	</div>
</body>
</html>