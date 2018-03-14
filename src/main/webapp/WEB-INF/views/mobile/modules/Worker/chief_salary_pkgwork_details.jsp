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
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/Packagebudget.css"/>
</head>

<body>
    <div class="">
        <header>
            <a class="back_btn"  onclick="myhistory.back()" href="${ctx }/app/worker/salaryList"></a>
            <h2 class="title">分配金额</h2>
        </header>
        <!-- /header -->
        <section class="pad_top11 section">
            <div class="sec_list">
                <div class="sec_title"><h2>结算单基本信息</h2></div>
                <div class="sec_top">
                    <p>
                        <span>任务包名称：</span>
                        <span>${workTaskPackage.packageName }</span>
                    </p>
                    <p>
                      <span class="sec_place_left">施工地点：</span>
                        <span class="sec_place">${workTaskPackage.customerMessage }-${workTaskPackage.customerName }</span>
                    </p>
                    <p>
                        <span>实际工期：</span>
                        <span>
							<fmt:formatDate value="${workTaskPackage.actualStartdate }" pattern="yyyy-MM-dd" />
							至
							<fmt:formatDate value="${workTaskPackage.actualEnddate }" pattern="yyyy-MM-dd" />
                        </span>
                    </p>
                    <p>
                        <span>验收日期：</span>
                        <span><fmt:formatDate value="${bizOrderTaskpackageSettlement.checkDate }" pattern="yyyy-MM-dd" /></span>
                    </p>
                    <p>
                        <span>项目经理：</span>
                        <span>${workTaskPackage.itemCustomer }-${workTaskPackage.managerPhone }</span>
                        <a href="tel:${workTaskPackage.managerPhone }" class="tel"><i></i>拨打电话</a>
                    </p>
                </div>
                <div class="sec_title"><h2>结算汇总</h2></div>
                <table>
                 	<caption>结算汇总金额统计</caption>
                 	<tbody>
                 		<tr>
                 			<td>工料结算总金额</td>
                 			<td>${settleTotalMoney}元</td>
                 		</tr>
                 		<tr class="BackWhite">
                 			<td>延期扣款</td>
                 			<td>
               					<c:if test="${bizOrderTaskpackageSettlement.delayAmerce == null }">
									<span>-0.0元</span>
								</c:if>
								<c:if test="${bizOrderTaskpackageSettlement.delayAmerce != null }">
									<span>-${bizOrderTaskpackageSettlement.delayAmerce }元</span>
								</c:if>
                 			</td>
                 		</tr>
                 		<tr>
                 			<td>管理处罚</td>
                 			<td>
                 				<c:if test="${bizOrderTaskpackageSettlement.punishAmerce == null}">
									<span>-0.0元</span>
								</c:if>
								<c:if test="${bizOrderTaskpackageSettlement.punishAmerce != null}">
									<span>-${bizOrderTaskpackageSettlement.punishAmerce }元</span>
								</c:if>
                 			</td>
                 		</tr>
                 		<tr class="BackWhite">
                 			<td>质检罚款</td>
                 			<td>-${bizOrderTaskpackageSettlement.qcPunishMoneyAmount }元</td>
                 		</tr>
                 		<tr>
                 			<td>公司扣款</td>
                 			<td>-<c:if test="${bizOrderTaskpackageSettlement.companyDeductAmount == null}">0.0</c:if><c:if test="${bizOrderTaskpackageSettlement.companyDeductAmount != null}">${bizOrderTaskpackageSettlement.companyDeductAmount}</c:if>元</td>
                 		</tr>
                 		<tr class="BackWhite">
                 			<td>质保金扣款</td>
                 			<td>-${bizOrderTaskpackageSettlement.guaranteeMoneyAmount}元</td>
                 		</tr>
                 		<c:if test="${not empty rewardAmount}">
	                 		<tr>
	                 			<td class="BorderNone">奖励金额</td>
	                 			<td>${rewardAmount}元</td>
	                 		</tr>
                 		</c:if>
                 		<tr class="BackWhite">
                 			<td class="BorderNone">工人组结算金额</td>
                 			<td>${bizOrderTaskpackageSettlement.workerGroupSettleAmount}元</td>
                 		</tr>
                 	</tbody>
                 </table>
                 <div class="sec_title"><h2>分配薪酬</h2></div>
                 <c:forEach items ="${payments }" var ="payment">
	                 <div class="WrapInfo">
	                 	<p>
	                 		<span>工人姓名：</span>
	                 		<span class="ColorY">${payment.employeeName }</span>
	                 	</p>
	                 	<p>
	                 		<span>分配薪酬金额：</span>
	                 		<span class="ColorRed">${payment.paymentAmount }元</span>
	                 	</p>
	                 </div>
                 </c:forEach>
            </div>
        </section>
        <c:if test="${paymentGroup.status != '1' }">
	        <div class="WrapBot">
	        	<span><a href="javascript:void(0)" onclick="accept()" style="color:#fff;">接受</a></span>
	        	<span><a href="javascript:void(0)" onclick="refuse()" style="color:#fff;">拒绝</a></span>
	        </div>
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
					<span class="tip">如果拒绝将重新分配金额</span>
				</p>
				<p class="second">
					<a href="javascript:void(0)" onclick="noRefuse()">取消</a>
					<a href="javascript:void(0)" onclick="yesRefuse('${workTaskPackage.groupId}','${workTaskPackage.id}')">确认</a>
				</p>
			</div>
		</div>
        <div style="padding-bottom:300px;"></div>
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
			window.location.href = "${ctx}/app/worker/refuseSalary?taskPackageId="+taskPackageId+"&groupId="+groupId+"&packageStateid=110&packageStatename=工人不认可分配金额";
		}
		function noRefuse(){
			$("#refuseSalary").addClass("undis");
		}
	</script>
</body>

</html>