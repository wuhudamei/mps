<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>中期结算日志</title>
		<meta name="decorator" content="default"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/popUp/css/reset.css"/>
		<style>
			.bor{border:1px solid #dedede;}
			.font0{font-size: 0;}
			.mb12{margin-bottom: 12px;}
			.mb2{margin-bottom: -9px;}
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
			
			<li class="active"><a href="javascript:void(0)">中期结算日志</a></li>
		</ul>  
		<ul class="ul-form breadcrumb">
			<li class="btns"><input class="btn btn-primary" type="button" value="返回" onclick="history.go(-1)"/></li>
		</ul>
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2>
							
							<center style="font-size: 20px;">基本信息</center>
						</h2>
					</div>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tr>
							<td width="50%">
								小区：${page.communityName }-${page.buildNumber }-${page.buildUnit }-${page.buildRoom }
							</td>
							<td width="50%">
								客户姓名：${page.customerName }
							</td>
						</tr>
						<tr>
							<td width="50%">
								项目经理：${page.itemManagerName }
							</td>
							<td width="50%">
								质检员：${page.orderInspectorName }
							</td>
						</tr>
						<tr style="width: 100px;">
							<td width="50%">
								结算阶段：
								<c:if test="${page.settleBillType == 1}">
									中期结算
								</c:if>
								<c:if test="${page.settleBillType == 2}">
									竣工结算
								</c:if>
								
							</td>
							<td width="20%"></td>
						</tr>
					</table>
				</div>
			</div>
				
				
				
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2>
							<center style="font-size: 20px;">
								流程日志
							</center>
						</h2>
					</div>
				</div>
			</div>
			<c:forEach items="${listLog }" var = "log">
			
			
			<c:if test="${log.businessType == 3100}">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
						
						<c:choose>
							<c:when test="${empty mysetone}">
								<li class="mb12 clearfix">
								<span class="font20 col_3">${log.businessRemarks }</span>
							</li>
							</c:when>
						</c:choose>
							
						<c:set var="mysetone" value="2"></c:set>
						
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate value="${log.statusDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">${log.businessEmployeeName }</p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">期望进场日期：</span>
								<p class="font14 col_3 flow">${log.businessDate }</p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">约检节点名称：</span>
								<p class="font14 col_3 flow">${log.businessName }</p>
							</li>
						</ul>
			</c:if>
			
			<c:if test="${log.businessType == 3500}">
			
			
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							
						<c:choose>
							<c:when test="${empty myset}">
								<li class="mb12 clearfix">
									<span class="font20 col_3" id = "myflag">${log.businessRemarks }</span>
								</li>
							</c:when>
						</c:choose>
							
						<c:set var="myset" value="2"></c:set>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt" >申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate value="${log.statusDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">${log.businessEmployeeName }</p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">拒绝原因：</span>
								<p class="font14 col_3 flow">${log.remarks }</p>
							</li>
							
						</ul>
			</c:if>
			<!-- --------------------------------------------------------------------- -->
			<c:if test="${log.businessType == 3200 || log.businessType == 303 }">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
						
							<c:choose>
							<c:when test="${empty mysettwo}">
								<li class="mb12 clearfix">
									<span class="font20 col_3" id = "myflag">${log.businessRemarks }</span>
								</li>
							</c:when>
						</c:choose>
							
						<c:set var="mysettwo" value="2"></c:set>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate value="${log.statusDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">
									<c:if test="${empty log.businessEmployeeName }">
										系统管理员
									</c:if>
									<c:if test="${!empty log.businessEmployeeName }">
											${log.businessEmployeeName }
									</c:if>
								</p>
							</li>
						</ul>
			</c:if>
				<!-- --------------------------------------------------------------------- -->
			<c:if test="${log.businessType == 3300}">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
						
						<c:choose>
							<c:when test="${empty mysettheer}">
								<li class="mb12 clearfix">
									<span class="font20 col_3" id = "myflag">${log.businessRemarks }</span>
								</li>
							</c:when>
						</c:choose>
							
						<c:set var="mysettheer" value="2"></c:set>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate value="${log.statusDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">
									<c:if test="${empty log.businessEmployeeName }">
										系统管理员
									</c:if>
									<c:if test="${!empty log.businessEmployeeName }">
											${log.businessEmployeeName }
									</c:if>

								</p>
							</li>
						</ul>
			</c:if>
				<!-- --------------------------------------------------------------------- -->
			<c:if test="${log.businessType == 3400}">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
						
						<c:choose>
							<c:when test="${empty mysetfour}">
								<li class="mb12 clearfix">
									<span class="font20 col_3" id = "myflag">${log.businessRemarks }</span>
								</li>
							</c:when>
						</c:choose>
							
						<c:set var="mysetfour" value="2"></c:set>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate value="${log.statusDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">
									<c:if test="${empty log.businessEmployeeName }">
										系统管理员
									</c:if>
									<c:if test="${!empty log.businessEmployeeName }">
											${log.businessEmployeeName }
									</c:if>
								</p>
							</li>
						</ul>
			</c:if>
			<c:if test="${log.businessType == 4100}">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
						
						<c:choose>
							<c:when test="${empty mysetfour}">
								<li class="mb12 clearfix">
									<span class="font20 col_3" id = "myflag">${log.businessRemarks }</span>
								</li>
							</c:when>
						</c:choose>
							
						<c:set var="mysetfour" value="2"></c:set>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate value="${log.statusDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">
									<c:if test="${empty log.businessEmployeeName }">
										系统管理员
									</c:if>
									<c:if test="${!empty log.businessEmployeeName }">
											${log.businessEmployeeName }
									</c:if>
								</p>
							</li>
						</ul>
			</c:if>
			
			<c:if test="${log.businessType == 3600 }">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<c:choose>
							<c:when test="${empty myset1}">
								<li class="mb12 clearfix">
									<span class="font20 col_3" id = "myflag">${log.businessRemarks }</span>
								</li>
							</c:when>
						</c:choose>
							
						<c:set var="myset1" value="2"></c:set>
						
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate value="${log.statusDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">
									<c:if test="${empty log.businessEmployeeName }">
										系统管理员
									</c:if>
									<c:if test="${!empty log.businessEmployeeName }">
											${log.businessEmployeeName }
									</c:if>
								</p>
							</li>
						</ul>
			</c:if>
			<c:if test="${log.businessType == 3700 }">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<c:choose>
							<c:when test="${empty myset2}">
								<li class="mb12 clearfix">
									<span class="font20 col_3" id = "myflag">${log.businessRemarks }</span>
								</li>
							</c:when>
						</c:choose>
							
						<c:set var="myset2" value="2"></c:set>
						
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate value="${log.statusDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">
									<c:if test="${empty log.businessEmployeeName }">
										系统管理员
									</c:if>
									<c:if test="${!empty log.businessEmployeeName }">
											${log.businessEmployeeName }
									</c:if>

								</p>
							</li>
						</ul>
			</c:if>
			<c:if test="${log.businessType == 3800}">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<c:choose>
							<c:when test="${empty myset3}">
								<li class="mb12 clearfix">
									<span class="font20 col_3" id = "myflag">${log.businessRemarks }</span>
								</li>
							</c:when>
						</c:choose>
							
						<c:set var="myset3" value="2"></c:set>
						
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate value="${log.statusDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">
									<c:if test="${empty log.businessEmployeeName }">
										系统管理员
									</c:if>
									<c:if test="${!empty log.businessEmployeeName }">
											${log.businessEmployeeName }
									</c:if>

								</p>
							</li>
						</ul>
			</c:if>
			<c:if test="${log.businessType == 3900}">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<c:choose>
							<c:when test="${empty myset4}">
								<li class="mb12 clearfix">
									<span class="font20 col_3" id = "myflag">${log.businessRemarks }</span>
								</li>
							</c:when>
						</c:choose>
						<c:set var="myset4" value="2"></c:set>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate value="${log.statusDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">
									<c:if test="${empty log.businessEmployeeName }">
										系统管理员
									</c:if>
									<c:if test="${!empty log.businessEmployeeName }">
											${log.businessEmployeeName }
									</c:if>
								</p>
							</li>
						</ul>
			</c:if>
			</c:forEach>
		</div>	
		
	</body>
</html>