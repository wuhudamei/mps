<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>墙地砖问题上报详情</title>
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
			
			<li class="active"><a href="javascript:void(0)">墙地砖问题上报详情</a></li>
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
								小区：${bizOrderInstallItemProblemVo.communityName }-${bizOrderInstallItemProblemVo.buildNumber }-${bizOrderInstallItemProblemVo.buildUnit }-${bizOrderInstallItemProblemVo.buildRoom }
							</td>
							<td width="50%">
								客户：${bizOrderInstallItemProblemVo.customerName }-${bizOrderInstallItemProblemVo.customerPhone }
							</td>
						</tr>
						<tr>
							<td width="50%">
								项目经理：${bizOrderInstallItemProblemVo.itemManager }-${bizOrderInstallItemProblemVo.itemManagerPhone }
							</td>
							<td width="50%">
								问题分类：${bizOrderInstallItemProblemVo.problemTypeName }
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
			<c:if test="${bizOrderInstallItemProblemVo.status>9 }">
				<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
					<li class="mb12 clearfix">
						<span class="font20 col_3">项目经理上报</span>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">上报时间：</span>
						<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${bizOrderInstallItemProblemVo.createDate }"/></p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">项目经理：</span>
						<p class="font14 col_3 flow">${bizOrderInstallItemProblemVo.problemApplyEmployeeName }-${bizOrderInstallItemProblemVo.problemApplyEmployeePhone }</p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">问题分类：</span>
						<p class="font14 col_3 flow">${bizOrderInstallItemProblemVo.problemTypeName }</p>
					</li>
					<c:if test="${bizOrderInstallItemProblemVo.isDelay eq 1 }">
						<li class="mb12 wid_per50 flo_left clearfix">
							<span class="col_grey font14 flo_left spanRgt">是否影响工期：</span>
							<p class="font14 col_3 flow">是</p>
						</li>
						<li class="mb12 wid_per50 flo_left clearfix">
							<span class="col_grey font14 flo_left spanRgt">影响工期天数：</span>
							<p class="font14 col_3 flow">${bizOrderInstallItemProblemVo.delayDays }天</p>
						</li>
					</c:if>
					<c:if test="${bizOrderInstallItemProblemVo.isDelay eq 0 }">
						<li class="mb12 wid_per50 flo_left clearfix">
							<span class="col_grey font14 flo_left spanRgt">是否影响工期：</span>
							<p class="font14 col_3 flow">否</p>
						</li>
					</c:if>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">描述：</span>
						<p class="font14 col_3 flow">${bizOrderInstallItemProblemVo.problemDescribe }天</p>
					</li>
				</ul>
			</c:if>
			
			
			<c:if test="${bizOrderInstallItemProblemVo.status eq 50}">
				<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
					<li class="mb12 clearfix">
						<span class="font20 col_3">材料部处理</span>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">处理时间：</span>
						<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${bizOrderInstallItemProblemVo.materialCreateDate }"/></p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">材料部人员：</span>
						<c:if test="${empty bizOrderInstallItemProblemVo.materialApplyEmployeeName }">
							<c:if test="${bizOrderInstallItemProblemVo.materialCreateBy eq 1 }">
							
								<p class="font14 col_3 flow">系统管理员</p>
							</c:if>
						</c:if>
						<c:if test="${not empty bizOrderInstallItemProblemVo.materialApplyEmployeeName }">
							<p class="font14 col_3 flow">${bizOrderInstallItemProblemVo.materialApplyEmployeeName }</p>
						</c:if>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">回复：</span>
						<p class="font14 col_3 flow">${bizOrderInstallItemProblemVo.materialNote }</p>
					</li>
				</ul>
			</c:if>
			
			
		</div>	
	</body>
</html>