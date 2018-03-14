<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购单管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<ul class="nav nav-tabs">
			<li class="active">墙地砖明细</li>
		</ul>
		<div>
			<a class="btn" href="javascript:" onclick="history.go(-1);">返回</a>
		</div>
		<div>
				<div class="col-md-12 column">
					<div >
						<h2>
							<center>
								墙地砖-采购单详情
							</center>
						</h2>
					</div>
				</div>
			<div class="row clearfix">
				<div class="col-md-6 column">
					<div class="nav nav-tabs">
						<h3>
							<center>
								基本信息
							</center>
						</h3>
					</div>
				</div>
			</div>
				<table style="width:100%" align="center" valign="center" class="table table-striped table-bordered table-condensed">
					<tr>
						<td style="width:50%"><label>订单编号：</label>${bizMaterialsWallAndFloorByOrder.orderNumber}</td>
						<td style="width:50%"><label>工程模式：</label>${fns:getDictLabel(bizMaterialsWallAndFloorByOrder.projectMode,'project_mode', '')}</td>
					</tr>
					<tr>
						<td style="width:50%"><label>区域：</label>${bizMaterialsWallAndFloorByOrder.enginDepartName}</td>
						<td style="width:50%"><label>项目经理：</label>${bizMaterialsWallAndFloorByOrder.itemManager}</td>
					</tr>
					<tr>
						<td style="width:50%"><label>申请墙地砖次数：</label>${bizMaterialsWallAndFloorByOrder.applyTimes}</td>
						<td style="width:50%"><label>订单状态：</label>${bizMaterialsWallAndFloorByOrder.orderStatus}</td>
					</tr>
					<tr>
						<td style="width:50%"><label>客户姓名：</label>${bizMaterialsWallAndFloorByOrder.customerName}</td>
						<td style="width:50%"><label>客户地址：</label>${bizMaterialsWallAndFloorByOrder.customerAddress}</td>
					</tr>
				</table>
			</div>
			<div class="row clearfix">
				<div class="col-md-6 column">
					<div class="nav nav-tabs">
						<h3>
							<center>
								<ul>  
				                    <li id="tab1" style="float:left; list-style:none">  
				                                                      合计/	
				                    </li>  
				                    <li id="tab2" style="float:left; list-style:none" >  
				                                                      明细 
				                    </li>  
				                </ul>  
							</center>
						</h3>
					</div>
				</div>
			</div>
		<div class="tab_css" id="tab1_content" style="display: block">  		
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>序号</th>
						<th>项目名称</th>
						<th>位置</th>
						<th>品牌</th>
						<th>型号</th>
						<th>规格 </th>
						<th>单位</th>
						<th>数量</th>
						<th>含损耗数量</th>
						<th>实发数量</th>
						<th>已收货数量</th>
						<th>欠货数量</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${findHejiByOrderId}" var="findHejiByOrderId" varStatus="status">
					<tr>
						<td>
							${status.index+1} 
						</td>
						<td>
							${findHejiByOrderId.label}
						</td>
						<td>
							${findHejiByOrderId.position}
						</td>
						<td>
							${findHejiByOrderId.brands} 
						</td>
						<td>
							${findHejiByOrderId.model}
						</td>
						<td>
							${findHejiByOrderId.specifications}
						</td>
						<td>
							${findHejiByOrderId.measurementUnit}
						</td>
						<td>
							${findHejiByOrderId.mainMateCount}
						</td>
						<td>
							${findHejiByOrderId.includLossCount}
						</td>
						<td>
							${findHejiByOrderId.auxiMateCount}
						</td>
						<td>
							${findHejiByOrderId.receivedAuxiMateCount}
						</td>	
						<td>
							${findHejiByOrderId.owedAuxiMateCount}
						</td>
						<td>
							${findHejiByOrderId.remarks}
						</td>																							
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div> 
			<div class="tab_css" id="tab2_content" style="display: none">  
                <div>
	                <table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>采购单编号</th>
							<th>采购单状态</th>
							<th>期望到货日期</th>
							<th>提交申请时间</th>
							<th>项目名称</th>
							<th>位置</th>
							<th>品牌</th>
							<th>型号</th>
							<th>规格 </th>
							<th>单位</th>
							<th>申请数量</th>
							<th>已收货数量</th>
							<th>欠货数量</th>
							<th>备注</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${findMingxiByOrderId}" var="findMingxiByOrderId" varStatus="status">
						<tr>
							<td>
								${findMingxiByOrderId.purchaseCode} 
							</td>
							<td>
								${fns:getDictLabel(findMingxiByOrderId.status, 'purchase_status', '')}
							</td>
							<td>
								<fmt:formatDate value="${findMingxiByOrderId.applyReceiveTime}" pattern="yyyy-MM-dd"/>
							</td>
							<td>
								<fmt:formatDate value="${findMingxiByOrderId.applyTime}" pattern="yyyy-MM-dd"/>
							</td>
							<td>
								${findMingxiByOrderId.label}
							</td>
							<td>
								${findMingxiByOrderId.position}
							</td>
							<td>
								${findMingxiByOrderId.brands} 
							</td>
							<td>
								${findMingxiByOrderId.model}
							</td>
							<td>
								${findMingxiByOrderId.specifications}
							</td>
							<td>
								${findMingxiByOrderId.measurementUnit}
							</td>
							<td>
								${findMingxiByOrderId.auxiMateCount}
							</td>
							<td>
								${findMingxiByOrderId.receivedAuxiMateCount}
							</td>	
							<td>
								${findMingxiByOrderId.owedAuxiMateCount}
							</td>
							<td>
								${findMingxiByOrderId.remarks}
							</td>						
						</tr>
					</c:forEach>
					</tbody>
				</table>
                </div>  
            </div>
	<script type="text/javascript">
		$("#tab2").click(function(){
			document.getElementById('tab1_content').style.display='none';
			document.getElementById('tab2_content').style.display='';
		}); 
		$("#tab1").click(function(){
			document.getElementById('tab2_content').style.display='none';
			document.getElementById('tab1_content').style.display='';
		}); 
	</script>	
</body>
</html>