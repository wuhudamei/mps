<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>结算单详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/AccessoriesDeduct.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/comment.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/account.css"/>
</head>
<body>
	<div class="">
		<header class="header">
			<a class="back_btn" href="${ctx}/app/manager/taskBudgetManage"></a> 
			<h2 class="title"> 结算单详情</h2>
		</header><!-- /header -->
		<div class="accessories sand_And_water">
			<section> 
			<h3 class="title_content"><i class="blue_line"></i><p>结算单基本信息</p></h3>
				<ul class="get_price">
					<li>
						<span>任务包名称 ：</span>
						<span>${workTaskPackage.packageName }</span>
					</li>
					<li>
						<span>结算单状态 ：</span>
						<span>${fns:getDictLabel(bizOrderTaskpackageSettlement.status, 'settlement_status', '')}</span>
					</li>
					<li class="mb24">
						<span>验收日期 ：</span>
						<span>
							<fmt:formatDate value="${bizOrderTaskpackageSettlement.checkDate }" pattern="yyyy-MM-dd" />
						</span>
					</li>
					<li class="speacil_line font28">
					<p>工料费预算总金额：<span class="col_red">${budgetTotalMoney}元</span></p>
					<p>工料费结算总金额：<span class="col_red">${settleTotalMoney}元</span></p>
					<span class="look_cont"><a href="${ctx}/app/manager/accountDetails?id=${workTaskPackage.id }&settleStyle=1">查看详情 <i class="icon_arrowRight"></i></a></span>
					</li>
					<li class="SeePhotos_cont">
						<p class="SeePhotos"><a href="${ctx}/app/manager/seePhoto?id=${workTaskPackage.id }&settleStyle=1">查看完工照片</a></p>
					</li>
				</ul>
			</section>
				<section> 
					<h3 class="title_content"><i class="blue_line"></i><p>工期</p></h3>
				<ul class="get_price">
					<li>
						<span>要求工期 ：</span>
						<span>
							<fmt:formatDate value="${workTaskPackage.planStartdate }" pattern="yyyy-MM-dd" />
							至
							<fmt:formatDate value="${workTaskPackage.planEnddate }" pattern="yyyy-MM-dd" />
						</span>
					</li>
					<li>
						<span>实际工期 ：</span>
						<span>
							<fmt:formatDate value="${workTaskPackage.actualStartdate }" pattern="yyyy-MM-dd" />
							至
							<fmt:formatDate value="${workTaskPackage.actualEnddate }" pattern="yyyy-MM-dd" />
						</span>
					</li>
					<c:if test="${bizOrderTaskpackageSettlement.isDelay =='1' }">
						<li>
							<span>延期天数 ：</span>
							<span>${bizOrderTaskpackageSettlement.delayDays }天（罚款${bizOrderTaskpackageSettlement.delayAmerce}元）</span>
						</li>
					</c:if>
					<c:if test="${bizOrderTaskpackageSettlement.isDelay == null || bizOrderTaskpackageSettlement.isDelay =='0' }">
						<span>延期天数：</span>
						<span>0天</span>
					</c:if>
				</ul>
			</section>
				<section>
				<h3 class="title_content"><i class="blue_line"></i><p>管理处罚</p></h3> 
				<ul class="get_price">
					<c:if test="${bizOrderTaskpackageSettlement.isManagePunish =='1' }">
						<li>
							<i>罚款 ${bizOrderTaskpackageSettlement.punishAmerce } 元</i>
						</li>
						<li>
							<p>原因 ：${bizOrderTaskpackageSettlement.punishReason }</p>
						</li>
					</c:if>
					<c:if test="${bizOrderTaskpackageSettlement.isManagePunish == null || bizOrderTaskpackageSettlement.isManagePunish =='0' }">
						<li>
							<i>无</i>
						</li>
					</c:if>
				</ul>
			</section>
			<section> 
				<h3 class="title_content"><i class="blue_line"></i><p>公司扣款</p></h3>
				<ul class="get_price">
					<li>
						<i>
							<c:if test="${bizOrderTaskpackageSettlement.companyDeductAmount == null}">罚款0.0元</c:if>
							<c:if test="${bizOrderTaskpackageSettlement.companyDeductAmount != null}">罚款${bizOrderTaskpackageSettlement.companyDeductAmount}元</c:if>
						</i>
					</li>
				</ul>
			</section>
			
			<section> 
				<h3 class="title_content"><i class="blue_line"></i><p>辅料用量</p></h3>
				<ul class="get_price rela">
					<li>
						<i>
							辅料扣除金额：
							<c:if test="${bizOrderTaskpackageSettlement.auxiliaryMaterialsAmount == null}">0.0</c:if>
							<c:if test="${bizOrderTaskpackageSettlement.auxiliaryMaterialsAmount != null}">${bizOrderTaskpackageSettlement.auxiliaryMaterialsAmount}</c:if>
							元
						</i>
						<a class="seeBtn" style="top:.2rem;position: absolute;right: 0.3rem;color: #0780ec;" href="${ctx}/app/manager/auxiliaryDetails?id=${workTaskPackage.id }">查看详情 <i class="icon_arrowRight"></i></a>
					</li>
				</ul>
			</section>
			
			<section> 
				<h3 class="title_content"><i class="blue_line"></i><p>沙子水泥</p></h3>
				<ul class="get_price rela">
					<li>
						<i>
							沙子水泥扣除金额：
							<c:if test="${bizOrderTaskpackageSettlement.sandCementAmount == null}">0.0</c:if>
							<c:if test="${bizOrderTaskpackageSettlement.sandCementAmount != null}">${bizOrderTaskpackageSettlement.sandCementAmount}</c:if>
							元
						</i>
					</li>
					<a class="seeBtn"  style="top:.2rem;position: absolute;right: 0.3rem;color: #0780ec;" href="${ctx}/app/manager/sandDetails?id=${workTaskPackage.id}">查看详情 <i class="icon_arrowRight"></i></a>
				</ul>
			</section>
			
			
			<section> 
				<h3 class="title_content"><i class="blue_line"></i><p>质保金</p></h3>
				<ul class="get_price">
					<li>
						<p>质保金扣款金额 ：${bizOrderTaskpackageSettlement.guaranteeMoneyAmount}元</p>
					</li>
				</ul>
			</section>
			<section class="retention">
				<h3 class="title_content"><i class="blue_line"></i><p>结算汇总</p></h3>
				<table>
					<caption>结算汇总金额统计</caption>
					<tbody>
						<tr><td>工料费结算总金额</td><td>${settleTotalMoney}元</td></tr>
						<tr><td>延期扣款</td><td>-<c:if test="${bizOrderTaskpackageSettlement.delayAmerce == null}">0.0</c:if><c:if test="${bizOrderTaskpackageSettlement.delayAmerce != null}">${bizOrderTaskpackageSettlement.delayAmerce}</c:if>元</td></tr>
						<tr><td>管理处罚</td><td>-<c:if test="${bizOrderTaskpackageSettlement.punishAmerce == null}">0.0</c:if><c:if test="${bizOrderTaskpackageSettlement.punishAmerce != null}">${bizOrderTaskpackageSettlement.punishAmerce}</c:if>元</td></tr>
						<tr><td>质检罚款</td><td>-${bizOrderTaskpackageSettlement.qcPunishMoneyAmount }元</td></tr>
						<tr><td>公司扣款</td><td>-<c:if test="${bizOrderTaskpackageSettlement.companyDeductAmount == null}">0.0</c:if><c:if test="${bizOrderTaskpackageSettlement.companyDeductAmount != null}">${bizOrderTaskpackageSettlement.companyDeductAmount}</c:if>元</td></tr>
						<tr><td>辅料扣款</td><td>-<c:if test="${bizOrderTaskpackageSettlement.auxiliaryMaterialsAmount == null}">0.0</c:if><c:if test="${bizOrderTaskpackageSettlement.auxiliaryMaterialsAmount != null}">${bizOrderTaskpackageSettlement.auxiliaryMaterialsAmount}</c:if>元</td></tr>
						<tr><td>沙子水泥扣款</td><td>-<c:if test="${bizOrderTaskpackageSettlement.sandCementAmount == null || bizOrderTaskpackageSettlement.sandCementAmount<=0}">0.0</c:if><c:if test="${bizOrderTaskpackageSettlement.sandCementAmount>0}">${bizOrderTaskpackageSettlement.sandCementAmount}</c:if>元</td></tr>
						<tr><td>质保金扣款</td><td>-${bizOrderTaskpackageSettlement.guaranteeMoneyAmount}元</td></tr>
						<tr><td>奖励金额</td><td>+<c:if test="${bizOrderTaskpackageSettlement.rewardAmount == null}">0.0</c:if><c:if test="${bizOrderTaskpackageSettlement.rewardAmount>0}">${bizOrderTaskpackageSettlement.rewardAmount}</c:if>元</td></tr>
						<tr><td>工人组结算金额</td><td class="col_red">${bizOrderTaskpackageSettlement.workerGroupSettleAmount}元</td></tr>
					</tbody>
				</table>
			</section>
			</div>

			<div style="padding-bottom:300px;"></div>
		</div>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script>
		$(function(){
			showStore();
		});
		
		function showStore(){
			var length = '${fn:length(evalStoreList)}';
			for(var i=0;i<length;i++){
				var selectCount = $("#selectCount"+i).val();
				$("#starP"+i).find(".star1:lt("+selectCount+")").addClass("star2");
			}
		}
	</script>
	</body>
</html>