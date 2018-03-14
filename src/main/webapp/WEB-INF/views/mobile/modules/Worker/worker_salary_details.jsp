<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>分配金额</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/budget.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/account.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="g-budget g-account">
		<header>
			<a class="back_btn" href="${ctx }/app/worker/salaryList"></a>
			<h2 class="title">分配金额</h2>
		</header><!-- /header -->
		<section class="budget_section">
			<h3>任务包信息</h3>
			<ul class="shadow">
				<li class="clearfix">
					<span>任务包名称：</span>
					<p>${workTaskPackage.packageName }</p>
				</li>
				<li class="clearfix">
					<span>施工地点：</span>
					<p>${workTaskPackage.customerMessage }-${workTaskPackage.customerName }</p>
				</li>
				<li class="clearfix">
					<span>实际工期：</span>
					<p>
						<fmt:formatDate value="${workTaskPackage.actualStartdate }" pattern="yyyy-MM-dd" />
						至
						<fmt:formatDate value="${workTaskPackage.actualEnddate }" pattern="yyyy-MM-dd" />
					</p>
				</li>
				<li class="clearfix">
					<span>验收时间：</span>
					<p>
						<fmt:formatDate value="${bizOrderTaskpackageSettlement.checkDate }" pattern="yyyy-MM-dd" />
					</p>
				</li>
				<li class="clearfix">
					<span>项目经理：</span>
					<p>${workTaskPackage.itemCustomer }-${workTaskPackage.managerPhone }</p>
				</li>
				<a id="tele" href="tel:${workTaskPackage.managerPhone }"></a>
			</ul>
		</section>
		<section class="account_section">
			<h3>分配薪酬</h3>
			<ul class="table shadow" style="margin-bottom: 200px;">
				<li class="clearfix">
					<span class="wid33 borderrgt0">工人姓名</span>
					<span class="wid67">分配薪酬金额</span>
				</li>
				<li class="clearfix">
					<span class="wid33">${payment.employeeName }</span>
					<span class="wid67">${payment.paymentAmount }元</span>
				</li>
			</ul>
		</section>
		<c:if test="${payment.status != '1' }">
			<footer class="">
				<a href="javascript:void(0)" onclick ="accept()"><span class="btn check_btn font28">接受</span></a>
				<a href="javascript:void(0)" onclick ="refuse()"><span class="btn check_btn font28">拒绝</span></a>
			</footer>
		</c:if>
		<!-- 弹框 -->
		<div class="g-mask undis" id="acceptSalary">
			<div id="g-done_ask">
				<p class="first">您确认接受当前分配金额吗？</p>
				<p class="second">
					<a href="javascript:void(0)" onclick="noAccept()">取消</a>
					<a href="javascript:void(0)" onclick="yesAccept('${workTaskPackage.groupId}','${workTaskPackage.id}')">确认</a>
				</p>
			</div>
		</div>
		<div class="g-mask undis" id="refuseSalary">
			<div id="g-done_ask">
				<p class="refuse">
					您确认拒绝当前分配金额吗？
					<span class="tip">如果拒绝将重新分配金额　　</span>
				</p>
				<p class="second">
					<a href="javascript:void(0)" onclick="noRefuse()">取消</a>
					<a href="javascript:void(0)" onclick="yesRefuse('${workTaskPackage.groupId}','${workTaskPackage.id}')">确认</a>
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript">
	function run_waitMe(effect,text){
		$('#aboveId').waitMe({
			effect: effect,
			text: text,
			bg: 'rgba(255,255,255,0.7)',
			color:'#000',
			sizeW:'',
			sizeH:'',
			source: 'img.svg'
		});
	}
		function accept(){
			$("#acceptSalary").removeClass("undis");
		}
		function yesAccept(groupId,taskPackageId){
			$("#acceptSalary").addClass("undis");
			//$("footer").addClass('undis');
			run_waitMe("win8",'请稍等 ,拼命提交中....');
			$.ajax({
				url:"${ctx}/app/worker/acceptSalary",
				type:"post",
				data:"groupId="+groupId+"&taskPackageId="+taskPackageId,
				success:function(data){
					$('#aboveId').waitMe('hide');
					if(data == '1'){
						globalUtil.fn.alert('薪酬确认成功,结算单进行审核!',2.0);
						//工人已确认分配金额
						//window.location.href = "${ctx}/app/worker/updateOrderTaskPackageStatus?taskPackageId="+taskPackageId+"&packageStateid=120&packageStatename=工人已确认分配金额";
						window.location.href = "${ctx}/app/worker/salaryList";
					}else if(data == '2'){
						// 工人不认可分配金额 直接跳转页面
						globalUtil.fn.alert('请等待其他工人确认薪酬!',2.0);
						//window.location.href = "${ctx}/app/worker/updateOrderTaskPackageStatus?taskPackageId="+taskPackageId+"&packageStateid=110&packageStatename=工人不认可分配金额";
						window.location.href = "${ctx}/app/worker/salaryList";
					}else if(data == '3'){
						globalUtil.fn.alert('请不要重复提交!',2.0);
						window.location.href = "${ctx}/app/worker/salaryList";
					}
				}
			});
		}
		function noAccept(){
			$("#acceptSalary").addClass("undis");
		}
		function refuse(){
			$("#refuseSalary").removeClass("undis");
		}
		function yesRefuse(groupId,taskPackageId){
			$("#refuseSalary").addClass("undis");
			run_waitMe("win8",'请稍等 ,拼命提交中....');
			window.location.href = "${ctx}/app/worker/refuseSalary?taskPackageId="+taskPackageId+"&groupId="+groupId+"&packageStateid=110&packageStatename=工人不认可分配金额"
		}
		function noRefuse(){
			$("#refuseSalary").addClass("undis");
		}
	</script>
</body>
</html>