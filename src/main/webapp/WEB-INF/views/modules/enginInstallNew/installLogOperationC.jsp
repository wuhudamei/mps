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
			
			<li class="active"><a href="javascript:void(0)">安装项操作日志</a></li>
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
								小区：${enginInstallNew.communityName }-${enginInstallNew.buildNumber }-${enginInstallNew.buildUnit }-${enginInstallNew.buildRoom }
							</td>
							<td width="50%">
								客户姓名：${enginInstallNew.customerName }
							</td>
						</tr>
						<tr>
							<td width="50%">
								客户电话：${enginInstallNew.customerPhone }
							</td>
							<td width="50%">
								安装项名称：${enginInstallNew.installItemName }
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
			
			<!-- 申请 -->
			<c:if test="${not empty statusLogList}">
				<c:forEach items="${statusLogList}" var="statusLog">
					<c:if test="${statusLog.businessStatus eq 2 }">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<li class="mb12 clearfix">
								<span class="font20 col_3">${statusLog.businessStatusName }</span>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${statusLog.statusDatetime }"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">${statusLog.businessEmployeeName }</p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">期望进场日期：</span>
								<p class="font14 col_3 flow">${statusLog.remarks }</p>
							</li>
						</ul>
					</c:if>
				</c:forEach>
			</c:if>
			
			
			<!-- 重新申请、驳回 -->
			<c:if test="${not empty statusLogTwoList}">
				<c:forEach items="${statusLogTwoList}" var="statusLogTwo">
					<!-- 驳回 -->
					<c:if test="${statusLogTwo.businessStatus eq 5 }">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<li class="mb12 clearfix">
								<span class="font20 col_3">${statusLogTwo.businessStatusName }</span>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">操作时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${statusLogTwo.statusDatetime }"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">操作人：</span>
								<p class="font14 col_3 flow">${statusLogTwo.businessEmployeeName }</p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">驳回原因：</span>
								<p class="font14 col_3 flow">
									${statusLogTwo.rejectedIdName }
									<c:if test="${not empty statusLogTwo.businessRemarks }">
										-${statusLogTwo.businessRemarks }
									</c:if>
								</p>
							</li>
						</ul>
					</c:if>
					<!-- 重新申请-->
					<c:if test="${statusLogTwo.businessStatus eq 6 }">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<li class="mb12 clearfix">
								<span class="font20 col_3">${statusLogTwo.businessStatusName }</span>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${statusLogTwo.statusDatetime }"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">申请人：</span>
								<p class="font14 col_3 flow">${statusLogTwo.businessEmployeeName }</p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">期望进场日期：</span>
								<p class="font14 col_3 flow">${statusLogTwo.remarks }</p>
							</li>
						</ul>
					</c:if>
				</c:forEach>
			</c:if>
			
			
			<!-- 转给供应商、验收-->
			<c:if test="${not empty statusLogList}">
				<c:forEach items="${statusLogList}" var="statusLog">
					<!-- 转给供应商 -->
					<c:if test="${statusLog.businessStatus eq 3 }">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<li class="mb12 clearfix">
								<span class="font20 col_3">${statusLog.businessStatusName }</span>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${statusLog.statusDatetime }"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">操作人：</span>
								<p class="font14 col_3 flow">${statusLog.businessEmployeeName }</p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">供应商：</span>
								<p class="font14 col_3 flow">${statusLog.remarks }</p>
							</li>
<!-- 							<li class="mb12 wid_per50 flo_left clearfix"> -->
<!-- 								<span class="col_grey font14 flo_left spanRgt">内容：</span> -->
<%-- 								<p class="font14 col_3 flow">${statusLog.businessRemarks }</p> --%>
<!-- 							</li> -->
						</ul>
					</c:if>
					<!-- 供应商分派工人组 -->
					<c:if test="${statusLog.businessStatus eq 310 }">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<li class="mb12 clearfix">
								<span class="font20 col_3">${statusLog.businessStatusName }</span>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${statusLog.statusDatetime }"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">操作人：</span>
								<p class="font14 col_3 flow">${statusLog.businessEmployeeName }</p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">供应商：</span>
								<p class="font14 col_3 flow">${statusLog.remarks }</p>
							</li>
<!-- 							<li class="mb12 wid_per50 flo_left clearfix"> -->
<!-- 								<span class="col_grey font14 flo_left spanRgt">内容：</span> -->
<%-- 								<p class="font14 col_3 flow">${statusLog.businessRemarks }</p> --%>
<!-- 							</li> -->
						</ul>
					</c:if>
					<!-- 工人签到-->
					<c:if test="${statusLog.businessStatus eq 320 }">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<li class="mb12 clearfix">
								<span class="font20 col_3">工人签到</span>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${statusLog.statusDatetime }"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">操作人：</span>
								<p class="font14 col_3 flow">${statusLog.businessEmployeeName }</p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">签到地址：</span>
								<p class="font14 col_3 flow">${statusLog.tianshu }</p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">与订单距离：</span>
								<p class="font14 col_3 flow">${statusLog.remarks }</p>
							</li>
<!-- 							<li class="mb12 wid_per50 flo_left clearfix"> -->
<!-- 								<span class="col_grey font14 flo_left spanRgt">内容：</span> -->
<%-- 								<p class="font14 col_3 flow">${statusLog.businessRemarks }</p> --%>
<!-- 							</li> -->
						</ul>
					</c:if>
					<!-- 工人申请完工-->
					<c:if test="${statusLog.businessStatus eq 330 }">
						<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
							<li class="mb12 clearfix">
								<span class="font20 col_3">工人申请完工</span>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">操作时间：</span>
								<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${statusLog.statusDatetime }"/></p>
							</li>
							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">操作人：</span>
								<p class="font14 col_3 flow">${statusLog.businessEmployeeName } ${statusLog.remarks}</p>
							</li>

							<li class="mb12 wid_per50 flo_left clearfix">
								<span class="col_grey font14 flo_left spanRgt">查看照片：</span>
								<a class="col_grey font14 flo_left spanRgt" href="${ctx }/enginInstallNew/enginInstallNew/pic?id=${statusLog.remarks}&type=2071">查看完工图&gt;&gt;</a>
							</li>
						</ul>
					</c:if>

				</c:forEach>
			</c:if>

            <%--验收--%>
			<c:if test="${list.size > 0}" var="statusLog">
                <!-- 验收 -->

                    <ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
                        <li class="mb12 clearfix">
                            <c:if test="${statusLog.businessStatus eq 1 }">
                                <span class="font20 col_3">验收合格</span>
                            </c:if>
                            <c:if test="${statusLog.businessStatus eq 2 }">
                                <span class="font20 col_3">验收不合格</span>
                            </c:if>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">操作时间：</span>
                            <p class="font14 col_3 flow"><fmt:formatDate type="both" value="${statusLog.statusDatetime }"/></p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">操作人：</span>
                            <p class="font14 col_3 flow">${statusLog.businessEmployeeName }</p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">验收时间：</span>
                            <p class="font14 col_3 flow"><fmt:formatDate type="both" value="${statusLog.statusDatetime }"/></p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">延期天数：</span>
                            <p class="font14 col_3 flow">${statusLog.tianshu }天</p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">延期原因：</span>
                            <p class="font14 col_3 flow">${statusLog.yuany}</p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">延期说明：</span>
                            <p class="font14 col_3 flow">${statusLog.shuom}天</p>
                        </li>
                        <li class="mb12 wid_per50 flo_left clearfix">
                            <span class="col_grey font14 flo_left spanRgt">查看照片：</span>
                            <a class="col_grey font14 flo_left spanRgt" href="${ctx }/enginInstallNew/enginInstallNew/acceptPic?id=${statusLog.planId}">查看完工图&gt;&gt;</a>
                        </li>
                    </ul>
            </c:if>


				
		</div>	
		
		
		
	</body>
</html>