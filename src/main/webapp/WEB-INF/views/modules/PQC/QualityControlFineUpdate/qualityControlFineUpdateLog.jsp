<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	System.out.println(basePath);
%>
<html>
	<head>
		<title>质检单罚款明细</title>
		<meta name="decorator" content="default"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/popUp/css/reset.css"/>
		<%-- <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/start.css"/> --%>
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
			.del_btn {
			    display: block;
			    font-size: .28rem;
			    color: #0780ec;
			    margin: .2rem auto;
			    line-height: .42rem;
			    width: 46%;
			    text-align: center;
			    border: 1px solid #0780ec;
			}
			h2{
				font-size:24px;
			}
			.pic {
			    float: left;
			    width: 30%;
			    margin-bottom: .8rem;
			    position: relative;
			    height: 2.6rem;
			}
			.camera {
			    /* width: 33%; */
			    width: 1.4rem;
			    float: left;
			    height: 1.4rem;
			    padding: 0;
			    position: relative;
			    overflow: hidden;
			}
			.camera input {
			    opacity: 0;
			    width: 100%;
			    height: 100%;
			    font-size: 100px;
			    overflow: hidden;
			    position: absolute;
			    top: 0;
			    left: 0;
			}
		</style>
	</head>
	<body>
	    <ul class="nav nav-tabs">                                                                                      
			
			<li class="active"><a href="javascript:void(0)">质检单罚款明细</a></li>
		</ul>  
		<ul class="ul-form breadcrumb">
			<li class="btns"><input class="btn btn-primary" type="button" value="返回" onclick="window.location.href='${ctx}/qualityControlFineUpdate/loglistInfo'"/></li>
		</ul>
		<form id="jvForm" class="jvForm"  method="post" action="#">
			<input hidden="hidden" name="checkItemId" value="${report.checkItemId }"/>
			<input hidden="hidden" name="orderId" value="${report.orderId }"/>
			
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
									订单编号：${report.orderNumber }
								</td>
								<td width="50%">
									小区名称：${report.xiaoqu}-${report.lou}-${report.danyuan}-${report.shi}
								</td>
							</tr>
							<tr>
								<td width="50%">
									客户：${report.customerName}-${report.customerPhone}
								</td>
								<td width="50%">
									项目经理：${report.managerName }
								</td>
							</tr>
							<tr>
								<td width="50%">
									质检报告编号：${report.reportCode}
								</td>
								<td width="50%">
									报告类型：
										<c:if test="${report.reportType==1 }">
											约检
										</c:if>
										<c:if test="${report.reportType==2 }">
											抽检
										</c:if>
								</td>
							</tr>
							<tr>
								<td width="50%">
									质检员：${report.orderInspectorName}
								</td>
								<td width="50%">
									检查人：${report.checkManName}
								</td>
							</tr>
							<tr>
								<td width="50%">
									提交质检报告时间：<fmt:formatDate value="${report.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td width="50%">
									检查项分类：${report.checkTypeName}
								</td>
							</tr>
							<tr>
								<td clospan="2">
									检查项：${report.checkItemName}
								</td>
							</tr>
							<tr>
								<td width="50%">
									操作人：${report.operatorName}
								</td>
								<td width="50%">
									操作时间：<fmt:formatDate value="${report.modifyDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<c:choose>
										   <c:when test="${report.picCount>0}">   
												上传的凭证：<a style="color: #2fa4e7;" href="${ctx}/qualityControlFineUpdate/photo?checkItemId=${report.checkItemId}&qcCheckItemId=${report.qcCheckItemId}&qcBillCode=${report.reportCode}">查看照片</a>
										   </c:when>
										   <c:otherwise>
										   		上传的凭证：查看照片
										   </c:otherwise>
									</c:choose>
									<%-- <c:if test="${fn:length(pList)>0 }">
										<div id="alreadyUploaded" class="clearfix alUpload" >
											<span class="font28 col_3">已上传凭证：</span>
											<div class="intro font0 pad_left3 pb68">
												<div  style="height:4.6rem;"  href="javascript:void(0)">	
													<c:forEach items="${pList}" var="p">
														<div class="camera " name="photoUploaded" style="height:4.7rem;width:20%;" >
															<img  style="height: 3.6rem;width:100%;" src="../..${p.picUrl }" picUrl="${p.picUrl }"  id="${p.id}"/>
														</div>
													</c:forEach>			
												</div>
											</div>
										</div>
									</c:if> --%>
								</td>
							</tr>
							
						</table>
					</div>
				</div>
			</div>	
			<div class="container">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="nav nav-tabs">
							<h2>
								<center>
									罚款金额
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
									项目经理：${report.managerName }
								</td>
							</tr>
							<tr>
						   		<td width="50%">
									原来的扣项目经理分值：
									<span id="managerScoreOld" name="managerScoreOld"  value="">${report.managerScoreOld }</span>
								</td>
								<td width="50%">
									原来的罚项目经理金额：
									<span id="punishMoneyOld" name="punishMoneyOld"  value="">${report.punishMoneyOld }</span>
								</td>
							</tr>
							<tr>
						   		<td width="50%">
									修改后的扣项目经理分值：
									<span id="managerScore" name="managerScore"  value="">${report.managerScore }</span>
								</td>
								<td width="50%">
									修改后的罚项目经理金额：
									<span id="punishMoney" name="punishMoney"  value="">${report.punishMoney }</span>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									工人组长：${report.workerGroupLeaderName}
								</td>
							</tr>
							<tr>
								<td width="50%">
									原来的扣工人分值：
									<span id="workerScoreOld" name="workerScoreOld"  value="">${report.workerScoreOld}</span>
								</td>
								<td width="50%">
									原来的罚工人金额：
									<span id="workerMoneyOld" name="workerMoneyOld" value="">${report.workerMoneyOld}</span>
								</td>
							</tr>
							<tr>
								<td width="50%">
									修改后的扣工人分值：
									<span id="workerScore" name="workerScore"  value="">${report.workerScore}</span>
								</td>
								<td width="50%">
									修改后的罚工人金额：
									<span id="workerMoney" name="workerMoney" value="">${report.workerMoney}</span>
								</td>
							</tr>
							<tr>
								<td clospan="2">
									质检员：${report.orderInspectorName}
								</td>
							</tr>
							<tr>
								<td width="50%">
									原来的扣质检员分值：
									<span id="inspectorScoreOld" name="inspectorScoreOld" readonly="readonly" value="">${report.inspectorScoreOld}</span>
								</td>
								<td width="50%">
									原来的罚质检员金额：
									<span id="inspectorMoneyOld" name="inspectorMoneyOld" readonly="readonly" value="">${report.inspectorMoneyOld}</span>
								</td>
							</tr>
							<tr>
								<td width="50%">
									扣质检员分值：
									<span id="inspectorScore" name="inspectorScore" readonly="readonly" value="">${report.inspectorScore}</span>
								</td>
								<td width="50%">
									罚质检员金额：
									<span id="inspectorMoney" name="inspectorMoney" readonly="readonly" value="">${report.inspectorMoney}</span>
								</td>
							</tr>
						</table>
					</div>
				</div>
				
				<!-- <div >
					<span class="font28 col_3">上传凭证：<input type="file" accept="image/*" onchange="preview(this)" class="inputFile"></span>
					<div  style="height:3.6rem;" id="uploaddone" href="javascript:void(0)">				
					</div>
				</div> -->
			</div>
		</form>	
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
		
	</body>
</html>