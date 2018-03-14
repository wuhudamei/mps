<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>安装项日志</title>
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
			
			<li class="active"><a href="javascript:void(0)">安装项日志</a></li>
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
							<td width="50%">
								小区：${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }
							</td>
							<td width="50%">
								客户姓名：${order.customerName }
							</td>
						</tr>
						<tr>
							<td width="50%">
								客户电话：${order.customerPhone }
							</td>
							<td width="50%">
								安装项名称：${InstallPlan.installItemName }
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
			<c:if test="${InstallPlan.status>1 }">
				<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
					<li class="mb12 clearfix">
						<span class="font20 col_3">项目经理申请</span>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
						<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${InstallPlan.applyIntoCreateDatetime }"/></p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">申请人：</span>
						<p class="font14 col_3 flow">
							<c:if test="${not empty businessStatusLogList }">
								<c:forEach items="${businessStatusLogList }" var="log">
									<c:if test="${log.businessStatus eq 2 }">
										<c:if test="${not empty log.businessEmployeeName }">
											${log.businessEmployeeName }
										</c:if>
										<c:if test="${empty log.businessEmployeeName }">
											<c:if test="${log.createBy.id eq 1 }">
												系统管理员
											</c:if>
										</c:if>
									</c:if>
								</c:forEach>
							</c:if>
						</p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">期望进场日期：</span>
						<p class="font14 col_3 flow"><fmt:formatDate type="date" value="${InstallPlan.applyIntoDate }"/></p>
					</li>
				</ul>
			</c:if>
			
			
			<c:if test="${InstallPlan.status>1 }">
				<c:if test="${not empty businessUrgeList }">
					<c:forEach items="${businessUrgeList }" var="urge">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<li class="mb12 clearfix">
								<span class="font20 col_3">${urge.operatorTypeName }${urge.operateTypeName }</span>
								<!-- <a class="font12 col_blue seeBtn" href="javascript:;">查看照片</a> -->
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${urge.operateDatetime }"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">操作人：</span>
								<c:if test="${empty urge.operatorEmployeeName }">
									<c:if test="${urge.createBy.id eq 1 }">
									
										<p class="font14 col_3 flow">系统管理员</p>
									</c:if>
								</c:if>
								<c:if test="${not empty urge.operatorEmployeeName }">
									<p class="font14 col_3 flow">${urge.operatorEmployeeName }</p>
								</c:if>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">内容：</span>
								<p class="font14 col_3 flow">${urge.operateContent }</p>
							</li>
						</ul>
					</c:forEach>
				</c:if>
			</c:if>
			
			
			<c:if test="${InstallPlan.status>2 }">
				<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
					<li class="mb12 clearfix">
						<span class="font20 col_3">材料部转给供应商</span>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">时间：</span>
						<p class="font14 col_3 flow">
							<c:if test="${not empty businessStatusLogList }">
								<c:forEach items="${businessStatusLogList }" var="log">
									<c:if test="${log.businessStatus eq 3 }">
										<fmt:formatDate type="both" value="${log.statusDatetime }"/>
									</c:if>
								</c:forEach>
							</c:if>
						</p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">操作人：</span>
						<p class="font14 col_3 flow">
							<c:if test="${not empty businessStatusLogList }">
								<c:forEach items="${businessStatusLogList }" var="log">
									<c:if test="${log.businessStatus eq 3 }">
										<c:if test="${not empty log.businessEmployeeName }">
											${log.businessEmployeeName }
										</c:if>
										<c:if test="${empty log.businessEmployeeName }">
											<c:if test="${log.createBy.id eq 1 }">
												系统管理员
											</c:if>
										</c:if>
									</c:if>
								</c:forEach>
							</c:if>
						</p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">供应商确认时间：</span>
						<p class="font14 col_3 flow">
                            <c:if test="${InstallPlan.installMode  == '2'}">
                                <fmt:formatDate type="date" value="${InstallPlan.supplierConfirmIntoDate }"/></p>
                            </c:if>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">内容：</span>
						<p class="font14 col_3 flow">${InstallPlan.supplierConfirmRemarks }</p>
					</li>
				</ul>
			</c:if>

            <c:forEach items="${unqualifiedList}" var = "unqualified">
                <ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
                    <li class="mb12 clearfix">
                        <span class="font20 col_3">
                            <c:if test="${unqualified.operateType == 1}">
                                已验收合格
                            </c:if>
                            <c:if test="${unqualified.operateType == 2}">
                                项目经理验收不合格
                            </c:if>

                        </span>
                    </li>

                    <c:if test="${unqualified.operateType == 2}" >
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">提交时间：</span>
                            <p class="font14 col_3 flow">
                                <fmt:formatDate type="date" value="${unqualified.createDate }"/>

                            </p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">操作人：</span>
                            <p class="font14 col_3 flow">
                                    ${unqualified.operatorEmployeeName }
                            </p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">验收不合格原因：</span>
                            <p class="font14 col_3 flow">
                                    ${unqualified.operateContent }
                            </p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">备注：</span>
                            <p class="font14 col_3 flow">
                                    ${unqualified.remarks }
                            </p>
                        </li>
                    </c:if>
                    <c:if test="${unqualified.operateType == 1}" >
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">提交时间：</span>
                            <p class="font14 col_3 flow">
                                <fmt:formatDate type="date" value="${unqualified.createDate }"/>

                            </p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">操作人：</span>
                            <p class="font14 col_3 flow">
                                    ${unqualified.operatorEmployeeName }
                            </p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">实际进场日期:</span>
                            <p class="font14 col_3 flow">
                                <c:if test="${InstallPlan.realIntoDate != null}">
                                    <fmt:formatDate type="date" value="${InstallPlan.realIntoDate }"/>
                                </c:if>

                            </p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">实际完工日期:</span>
                            <p class="font14 col_3 flow">
                                <c:if test="${InstallPlan.realCompleteDate != null}">
                                    <fmt:formatDate type="date" value="${InstallPlan.realCompleteDate }"/>
                                </c:if>

                            </p>
                        </li>
                    </c:if>

                </ul>
            </c:forEach>


				
		</div>	
	</body>
</html>