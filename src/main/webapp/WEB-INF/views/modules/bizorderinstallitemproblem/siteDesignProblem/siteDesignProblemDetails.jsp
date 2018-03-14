<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>工程设计问题详情</title>
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
			
			<li class="active"><a href="javascript:void(0)">工程设计问题详情</a></li>
		</ul>  
		<ul class="ul-form breadcrumb">
			<li class="btns"><input class="btn btn-primary" type="button" value="返回" onclick="history.go(-1)"/></li>
		</ul>
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2 style="font-size: 20px">
						
								基本信息
							
						</h2>
					</div>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-12 column">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tr>
							<td colspan="2">
								小区：${siteDesignProblem.communityName }-${siteDesignProblem.buildNumber }-${siteDesignProblem.buildUnit }-${siteDesignProblem.buildRoom }
							</td>
						</tr>
						<tr>
							<td width="50%">
								客户：${siteDesignProblem.customerName }-${siteDesignProblem.customerPhone }
							</td>
							<td width="50%">
							
								项目经理：${siteDesignProblem.itemManager }-${siteDesignProblem.itemManagerPhone }
							</td>
						</tr>
						<tr>
							<td colspan="2">
								问题分类：${siteDesignProblem.problemTypeName }
							</td>
						</tr>
						<%-- <tr>
							<td width="50%">
								扣除分数：${siteDesignProblem.punishScore }
							</td>
							<td width="50%">
								罚款金额：${siteDesignProblem.punishMoney }
							</td>
						</tr> --%>
					</table>
					<%-- <ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix">
						<li class="mb12 wid_per50 flo_left clearfix">
							<span class="col_grey font14 flo_left spanRgt">小区：</span>
							<p class="font14 col_3 flow">${siteDesignProblem.communityName }-${siteDesignProblem.buildNumber }-${siteDesignProblem.buildUnit }-${siteDesignProblem.buildRoom }</p>
						</li>
						</br>
						<li class="mb12 wid_per50 flo_left clearfix">
							<span class="col_grey font14 flo_left spanRgt">客户：</span>
							<p class="font14 col_3 flow">${siteDesignProblem.customerName }-${siteDesignProblem.customerPhone }</p>
						</li>
						<li class="mb12 wid_per50 flo_left clearfix">
							<span class="col_grey font14 flo_left spanRgt">项目经理：</span>
							<p class="font14 col_3 flow">${siteDesignProblem.itemManager }-${siteDesignProblem.itemManagerPhone }</p>
						</li>
						<li class="mb12 wid_per50 flo_left clearfix">
							<span class="col_grey font14 flo_left spanRgt">问题分类：</span>
							<p class="font14 col_3 flow">${siteDesignProblem.problemTypeName }</p>
						</li>
						
						<li class="mb12 wid_per50 flo_left clearfix">
							<span class="col_grey font14 flo_left spanRgt">扣除分数：</span>
							<p class="font14 col_3 flow">${siteDesignProblem.problemTypeName }</p>
						</li>
						<li class="mb12 wid_per50 flo_left clearfix">
							<span class="col_grey font14 flo_left spanRgt">扣除金额：</span>
							<p class="font14 col_3 flow">${siteDesignProblem.problemTypeName }</p>
						</li>
					
					</ul> --%>
					
				</div>
			</div>
				
				
				
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2 style="font-size: 20px">
						
								流程日志
							
						</h2>
					</div>
				</div>
			</div>
			
				<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix" style="border:11px solid #dedede">
					<li class="mb12 clearfix">
						<span class="font20 col_3" style="font-size: 15px">项目经理上报</span>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">上报时间：</span>
						<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${siteDesignProblem.createDate }"/></p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">项目经理：</span>
						<p class="font14 col_3 flow">${siteDesignProblem.itemManager }-${siteDesignProblem.itemManagerPhone }</p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">问题分类：</span>
						<p class="font14 col_3 flow">${siteDesignProblem.problemTypeName }</p>
					</li>
				
						<li class="mb12 wid_per50 flo_left clearfix">
							<span class="col_grey font14 flo_left spanRgt">期望完成日期：</span>
							<p class="font14 col_3 flow"><fmt:formatDate value="${siteDesignProblem.expectSolveDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						</li>
						<li class="mb12 wid_per50 flo_left clearfix">
							<span class="col_grey font14 flo_left spanRgt">责任人：</span>
							<p class="font14 col_3 flow">${siteDesignProblem.inchargeName }</p>
						</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">问题描述：</span>
						<p class="font14 col_3 flow">${siteDesignProblem.problemDescribe}</p>
					</li>
				</ul>
			
			
			
			
				<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor clearfix" style="border:11px solid #dedede">
					<li class="mb12 clearfix">
						<span class="font20 col_3" style="font-size: 15px">设计部处理</span>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">处理时间：</span>
						<p class="font14 col_3 flow"><fmt:formatDate type="both" value="${siteDesignProblem.materialCreateDate }"/></p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">设计部处理人员：</span>
						<c:if test = "${siteDesignProblem.status == '70' }">
						
							<c:if test="${empty siteDesignProblem.handleName }">
									<p class="font14 col_3 flow">系统管理员</p>
							</c:if>
							<c:if test="${not empty siteDesignProblem.handleName }">
								<p class="font14 col_3 flow">${siteDesignProblem.handleName }</p>
							</c:if>
						</c:if>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">设计师处理描述：</span>
						<p class="font14 col_3 flow">${siteDesignProblem.materialNote }</p>
					</li>
					<li class="mb12 wid_per50 flo_left clearfix">
						<span class="col_grey font14 flo_left spanRgt">设计师处理照片：</span>
						<p class="font14 col_3 flow">
							<c:if test="${siteDesignProblem.countPic > '0'}">
								<a style="color: blue;" href="${ctx}/bizorderinstallitemproblem/siteDesignProblem/photo?id=${siteDesignProblem.problemId}&picType=2082">共有${siteDesignProblem.countPic}张</a>
							</c:if>
							<%-- <c:if test="${siteDesignProblem.countPic == '0'}">
								<a style="color: blue;">共有0张</a>
							</c:if>
							<c:if test="${siteDesignProblem.countPic == ''}">
								<a style="color: blue;">共有0张</a>
							</c:if> --%>
							
						</p>
					</li>
				</ul>
		
			
			
		</div>	
	</body>
</html>