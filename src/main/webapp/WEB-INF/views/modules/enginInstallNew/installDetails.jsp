<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>安装项详情</title>
		<meta name="decorator" content="default"/>
	</head>
	<body>
	    <ul class="nav nav-tabs">                                                                                      
			
			<li class="active"><a href="javascript:void(0)">安装项详情</a></li>
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
								${enginInstallNew.communityName }-${enginInstallNew.buildNumber }-${enginInstallNew.buildUnit }-${enginInstallNew.buildRoom }-${enginInstallNew.customerName }
							</td>
						</tr>
						<tr>
							<td width="50%">
								计划安装日期：<fmt:formatDate type="date" value="${enginInstallNew.applyIntoDate }"/>
							</td>
							<td width="50%">
								安装项内容：${enginInstallNew.installItemName }
							</td>
						</tr>
						<tr>
							<td width="50%">
								备注：${enginInstallNew.remarks }
							</td>
							<td width="50%">
								申请日期：<fmt:formatDate type="both" value="${enginInstallNew.applyIntoCreateDatetime }"/>
							</td>
						</tr>
						<tr>
							<td width="50%">
								供应商确认日期：<fmt:formatDate type="date" value="${enginInstallNew.supplierConfirmIntoDate }"/>
							</td>
							<td width="50%">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>	
	</body>
</html>