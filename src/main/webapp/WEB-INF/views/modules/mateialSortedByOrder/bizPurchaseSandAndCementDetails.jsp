<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购单管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<ul class="nav nav-tabs">
			<li class="active">沙子水泥明细</li>
		</ul>
		<div>
			<a class="btn" href="javascript:" onclick="history.go(-1);">返回</a>
		</div>
		<div>
				<div class="col-md-12 column">
					<div >
						<h2>
							<center>
								沙子水泥-采购单详情
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
						<td style="width:50%"><label>订单编号：</label>${bizMaterialsSandAndCementByOrder.orderNumber}</td>
						<td style="width:50%"><label>工程模式：</label>${fns:getDictLabel(bizMaterialsSandAndCementByOrder.projectMode,'project_mode', '')}</td>
					</tr>
					<tr>
						<td style="width:50%"><label>区域：</label>${bizMaterialsSandAndCementByOrder.enginDepartName}</td>
						<td style="width:50%"><label>项目经理：</label>${bizMaterialsSandAndCementByOrder.itemManager}</td>
					</tr>
					<tr>
						<td style="width:50%"><label>申请沙子水泥次数：</label>${bizMaterialsSandAndCementByOrder.applyTimes}</td>
						<td style="width:50%"><label>订单状态：</label>${bizMaterialsSandAndCementByOrder.orderStatus}</td>
					</tr>
					<tr>
						<td style="width:50%"><label>客户姓名：</label>${bizMaterialsSandAndCementByOrder.customerName}</td>
						<td style="width:50%"><label>客户地址：</label>${bizMaterialsSandAndCementByOrder.customerAddress}</td>
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
						<th>商品编号 </th>
						<th>材料类别</th>
						<th>品牌</th>
						<th>商品名称</th>
						<th>规格 </th>
						<th>单位</th>
						<th>单价</th>
						<th>申请数量</th>
						<th>已收货数量</th>
						<th>欠货数量</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${findHejiByOrderId}" var="findHejiByOrderId" varStatus="status">
					<tr>
						<td>
							${status.index+1} 
						</td>
						<td>
							${findHejiByOrderId.auxiMateCode} 
						</td>
						<td>
							${findHejiByOrderId.categoryName}
						</td>
						<td>
							${findHejiByOrderId.brands} 
						</td>
						<td>
							${findHejiByOrderId.auxiMaterialName}
						</td>
						<td>
							${findHejiByOrderId.specifications}
						</td>
						<td>
							${fns:getDictLabel(findHejiByOrderId.measurementUnit, 'biz_material_unit', '')}
						</td>
						<td>
							${findHejiByOrderId.price}
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
							<th>采购单状态 </th>
							<th>期望到货日期</th>
							<th>提交申请日期</th>
							<th>材料类别</th>
							<th>品牌 </th>
							<th>商品名称</th>
							<th>规格</th>
							<th>单位</th>
							<th>单价</th>
							<th>申请数量</th>
							<th>已收货数量</th>
							<th>欠货数量</th>
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
								${findMingxiByOrderId.categoryName}
							</td>
							<td>
								${findMingxiByOrderId.brands} 
							</td>
							<td>
								${findMingxiByOrderId.auxiMaterialName}
							</td>
							<td>
								${findMingxiByOrderId.specifications}
							</td>
							<td>
								${fns:getDictLabel(findMingxiByOrderId.measurementUnit, 'biz_material_unit', '')}
							</td>
							<td>
								${findMingxiByOrderId.price}
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