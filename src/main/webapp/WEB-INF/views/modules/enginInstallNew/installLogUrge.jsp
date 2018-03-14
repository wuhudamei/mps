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
			
			<li class="active"><a href="javascript:void(0)">安装项催促日志</a></li>
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
								催促日志
							</center>
						</h2>
					</div>
				</div>
			</div>
			
			<!-- 催促 -->
			<c:if test="${not empty businessUrgeList}">
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
			
				
		</div>	
		
		
		
	</body>
</html>