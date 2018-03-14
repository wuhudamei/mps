<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>自采材料报销详情</title>
		<meta name="decorator" content="default"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/popUp/css/reset.css"/>
		<style>
			.bor{border:1px solid #dedede;}
			.font0{font-size: 0;}
			.mb12{margin-bottom: 12px;}
			.mb2{margin-bottom: 20px;}
			.font20{font-size: 20px;}
			.col_3{color: #333;}
			.font12{font-size: 12px;}
			.col_blue{color: #0780ec;}
			.col_grey{color: #666;}
			.font14{font-size: 14px;}
			.flo_left{float: left;}
			.spanRgt{text-align: right;width: 30%;}
			.pt3{padding-top: 30px;}
			.pl3{padding-left: 30px;}
			.pb3{padding-bottom: 30px;}
			.bg_f{background: #fff;}
			.flow{overflow: hidden;}
			.seeBtn{float: right;padding-right: 30px;}
			.wid_per50{width: 50%;}
			.clearfix{ /*display:block;*/ zoom:1; }
			.clearfix:after { content:"."; display:block; height:0; clear:both; visibility:hidden; font-size:0; }
		</style>
	</head>
	<body>
	    <ul class="nav nav-tabs">                                                                                      
			
			<li class="active"><a href="javascript:void(0)">自采材料报销详情</a></li>
		</ul>  
		<ul class="ul-form breadcrumb">
			<li class="btns"><input class="btn btn-primary" type="button" value="返回" onclick="history.go(-1)"/></li>
		</ul>
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2>
							<center>
								基本信息
							</center>
						</h2>
					</div>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tr>
							<td colspan="2">
								小区：${bizMaterialSelfbuyReimburse.communityName }-${bizMaterialSelfbuyReimburse.buildNumber }-${bizMaterialSelfbuyReimburse.buildUnit }-${bizMaterialSelfbuyReimburse.buildRoom }
							</td>
						</tr>
						<tr>
							<td width="50%">
								客户：${bizMaterialSelfbuyReimburse.customerName }-${bizMaterialSelfbuyReimburse.customerPhone }
							</td>
							<td width="50%">
								项目经理：${bizMaterialSelfbuyReimburse.itemManager }-${bizMaterialSelfbuyReimburse.itemManagerPhone }
							</td>
						</tr>
						<tr>
							<td width="50%">
								自采材料名称：${bizMaterialSelfbuyReimburse.materialName }
							</td>
							<td width="50%">
								当前状态：${bizMaterialSelfbuyReimburse.statusName }
							</td>
						</tr>
					</table>
				</div>
			</div>
				
				
				
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2>
							<center>
								流程日志
							</center>
						</h2>
					</div>
				</div>
			</div>
			<c:forEach items="${list }" var="material">
				<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
					<c:forEach  items="${statusLogList }" var="status">
						<c:if test="${status.businessOnlyMarkInt eq material.id }">
							<c:if test="${status.businessStatus eq 10 || status.businessStatus eq 15 }">
							
								<li class="mb12 clearfix">
									<span class="font20 col_3">${status.statusName}</span>
									<c:if test="${material.picCount >0 }">
										<a class="font12 col_blue seeBtn" href="${ctx}/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburse/photo?materialId=${material.id}">查看凭证</a>
									</c:if>
								</li>
								<li class="mb12 wid_per50 flo_left clearfix">
									<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
									<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${material.createDate}"/></p>
								</li>
								<li class="mb12 wid_per50 flo_left clearfix">
									<span class="col_grey font14 flo_left spanRgt">项目经理：</span>
									<p class="font14 col_3 flow">${status.businessEmployeeName }-${status.businessEmployeePhone}</p>
								</li>
							
							</c:if>
						</c:if>
					</c:forEach>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">自采材料名称：</span>
						<p class="font14 col_3 flow">${material.materialName }</p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">客户交付金额：</span>
						<p class="font14 col_3 flow">${material.customerPayAmount }元</p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">结算比例：</span>
						<p class="font14 col_3 flow">${material.settleRate }%</p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">实际结算金额：</span>
						<p class="font14 col_3 flow">${material.settleAmount }元</p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">说明：</span>
						<p class="font14 col_3 flow">${material.reimburseRemarks }</p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">上传凭证：</span>
						<p class="font14 col_3 flow">已经上传${material.picCount} 张凭证</p>
					</li>
					
				</ul>
				
				
				<c:forEach  items="${statusLogList }" var="log">
					<c:if test="${log.businessOnlyMarkInt eq material.id }">
						<c:if test="${log.businessStatus eq 25}">
							<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							
								<li class="mb12 clearfix">
									<span class="font20 col_3">${log.statusName}</span>
								</li>
								<li class="mb12 wid_per50 flo_left clearfix">
									<span class="col_grey font14 flo_left spanRgt">驳回时间：</span>
									<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${log.statusDatetime}"/></p>
								</li>
								<li class="mb12 wid_per50 flo_left clearfix">
									<span class="col_grey font14 flo_left spanRgt">结算员：</span>
									<p class="font14 col_3 flow">
										<c:if test="${not empty log.businessEmployeeName}">
											${log.businessEmployeeName}
										</c:if>
										<c:if test="${empty log.businessEmployeeName && log.createById eq 1}">
											系统管理员
										</c:if>
									</p>
								</li>
								<li class="mb12 wid_per50 flo_left clearfix">
									<span class="col_grey font14 flo_left spanRgt">驳回原因：</span>
									<p class="font14 col_3 flow">${log.businessRemarks}</p>
								</li>
					
							</ul>
							
						</c:if>
						<c:if test="${log.businessStatus eq 20}">
							<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							
								<li class="mb12 clearfix">
									<span class="font20 col_3">${log.statusName}</span>
								</li>
								<li class="mb12 wid_per50 flo_left clearfix">
									<span class="col_grey font14 flo_left spanRgt">通过时间：</span>
									<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${log.statusDatetime}"/></p>
								</li>
								<li class="mb12 wid_per50 flo_left clearfix">
									<span class="col_grey font14 flo_left spanRgt">结算员：</span>
									<p class="font14 col_3 flow">
										<c:if test="${not empty log.businessEmployeeName}">
											${log.businessEmployeeName}
										</c:if>
										<c:if test="${empty log.businessEmployeeName && log.createById eq 1}">
											系统管理员
										</c:if>
									</p>
								</li>
							</ul>
						</c:if>
					</c:if>
				</c:forEach>
				
			</c:forEach>
			
			
		</div>	
	</body>
</html>