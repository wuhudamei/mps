<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<meta http-equiv="Expires" CONTENT="0">
	<meta http-equiv="Cache-Control" CONTENT="no-cache">
	<meta http-equiv="Pragma" CONTENT="no-cache">
	<title>提交辅料</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/mui.min.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/auxiliary_choose.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170918/style.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css" />
	<link type="text/css" rel="stylesheet" 	href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
    <script>var baseUrl = '${ctx}'</script>
    <style>.pad_btm40{padding-bottom:.4rem;}
	.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
	.maskWrapper{width: 84%;margin: 30% auto 0;border-radius: .1rem;background: #fff;font-size: .32rem;}
	.col_3{color: #333}
	.col_6{color: #666;}
	.col_f{color: #fff;}
	.col_fdfcfa{color: #fdfcfa;}
	.col_0780ec{color: #0780ec;}
	.font28{font-size: .28rem;}
	.font33{font-size: .33rem;}
	.maskTit{height: 1rem;line-height: 1rem;text-align: center;}
	.maskContent{padding: .5rem;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
	.maskBtns{width: 83%;margin: 0 auto;padding-bottom: .2rem;padding-top: .2rem;}
	/* .maskBtn{display: block;width: 60%;} */
	.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
	.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}

	</style>
    <script src="${ctxStatic}/mobile/modules/Manager/js/lib/mui.min.js"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
    </script>
</head>
<body>
	<div class="g-auxiliary_choose g-auxiliary_count" id="aboveId">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/auxiliary/goOnChoose?orderId=${orderId}" href="javascript:void(0)"></a>
			<h2 class="title">提交辅料</h2>
		</header><!-- /header -->
		<div class="show_sec">
			
			<form action="${ctx }/app/manager/auxiliary/auxiliarypay" method="post" id="form">
			<div class="info shadow clearfix">
				<input type="text" hidden="hidden" id="orderId" name="orderId" value="${orderId}">
				<input type="text" hidden="hidden" id="projectMode" name="projectMode" value="${order.projectMode}">
				<div>
					<label for="inspire">期望到场日期：</label>
					<input id="txtBeginDate" class="date" type="text" readonly="" value="" name="hopeForTime" placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
				</div>
				<div>
					<label for="get_info">备注：</label>
					<input class="get_info" name="remarks" type="text" value="${remarks}">
				</div>
			</div>
			
			<c:forEach items="${packageStateList }" var="packageState">
        
		        <input type="text" hidden="hidden" id="packageState${packageState.empWorkType}" value="${packageState.isCanApplyAuxiliary}">
		        <input type="text" hidden="hidden" id="packageAfterAmount${packageState.empWorkType}" value="${packageState.afterAmount}">
		        <input type="text" hidden="hidden" id="packageTemplatName${packageState.empWorkType}" value="${packageState.templatName}">

	        </c:forEach>
	        
			<ul class="buy"> 
				<c:if test="${order.projectMode eq 1 || order.projectMode eq 4}">
					<c:forEach items="${auxiliaryList }" var="auxiliary">
						<c:forEach items="${packageStateList }" var="packageState">
							<c:if test="${auxiliary.workType eq packageState.empWorkType}">
								<c:if test="${packageState.isCanApplyAuxiliary eq 1 || packageState.isCanApplyAuxiliary eq 5 || packageState.isCanApplyAuxiliary eq 7}">
									<li class="shadow _height-auto" id="${auxiliary.auxiMateCode}">
										<input type="text" hidden="hidden" name="auxiMateCode" value="${auxiliary.auxiMateCode}">
										<input type="text" hidden="hidden" name="workType" value="${auxiliary.workType}">
										<div class="img_container">
											<img src="${auxiliary.pic }" alt="goods"   onerror="nofind()">
										</div>
										<input type="text" hidden="hidden" name="auxiliaryCount" value="${auxiliary.count}" class="count">
										<p class="brand brand-name _ellipsis3"><span>【</span>${auxiliary.workTypeName}-${auxiliary.brand }<span>】</span>${auxiliary.name } </p>
										<p>  ${auxiliary.specifications }</p>
										<p class="format calc">
											<span class="col_red">  <fmt:formatNumber type="number" value="${auxiliary.price}" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>   </span>    *    <span class="showCount"> ${auxiliary.count}</span>   =   <span class="showTotal"><fmt:formatNumber type="number" value="  ${auxiliary.totalPrice }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></span> 
										</p>
										<!-- <p>
											单价 &nbsp;* 数量&nbsp; = 总价&nbsp;
										</p> -->
										<div id="numbox" class="mui-numbox numbox-submit" data-numbox-step='1' data-numbox-min='0' data-numbox-max='999' style="">
									  <button class="mui-btn mui-numbox-btn-minus" type="button" style="" onclick="setTotal('${auxiliary.workType}')">-</button>
					 					  <input id="num" class="mui-numbox-input" type="number" value="${auxiliary.count}" style=""  onchange="setTotal('${auxiliary.workType}')" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" />
										  <button class="mui-btn mui-numbox-btn-plus" type="button" style="" onclick="setTotal('${auxiliary.workType}')">+</button> 
										</div>
										<a class="del_btn bottom-15 " href="javascript:void(0)" onclick="delteAuxiliary('${auxiliary.auxiMateCode}','${auxiliary.price }','${auxiliary.count }','${orderId }')">删除</a>
									</li>
								</c:if>
								<c:if test="${packageState.isCanApplyAuxiliary eq 2 || packageState.isCanApplyAuxiliary eq 3 || packageState.isCanApplyAuxiliary eq 4 || packageState.isCanApplyAuxiliary eq 6}">
									<li class="shadow _active _height-auto" id="${auxiliary.auxiMateCode}">
										<input type="text" hidden="hidden" name="auxiMateCode" value="${auxiliary.auxiMateCode}">
										<input type="text" hidden="hidden" name="workType" value="${auxiliary.workType}">
										<div class="img_container">
											<img src="${auxiliary.pic }" alt="goods"   onerror="nofind()">
										</div>
										<input type="text" hidden="hidden" name="auxiliaryCount" value="${auxiliary.count}" class="count">
										<p class="brand brand-name _ellipsis3"><span>【</span>${auxiliary.workTypeName}-${auxiliary.brand }<span>】</span>${auxiliary.name } </p>
										<p>  ${auxiliary.specifications }</p>
										<p class="format calc">
											<span class="col_red">  <fmt:formatNumber type="number" value="${auxiliary.price}" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>   </span>    *    <span class="showCount"> ${auxiliary.count}</span>   =   <span class="showTotal"><fmt:formatNumber type="number" value="  ${auxiliary.totalPrice }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></span> 
										</p>
										<!-- <p>
											单价 &nbsp;* 数量&nbsp; = 总价&nbsp;
										</p> -->
										<div id="numbox" class="mui-numbox numbox-submit" data-numbox-step='1' data-numbox-min='0' data-numbox-max='999' style="">
									  <button class="mui-btn mui-numbox-btn-minus" type="button" style="" onclick="setTotal('${auxiliary.workType}')">-</button>
					 					  <input id="num" class="mui-numbox-input" type="number" value="${auxiliary.count}" style=""  onchange="setTotal('${auxiliary.workType}')" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" />
										   <button class="mui-btn mui-numbox-btn-plus" type="button" style="" onclick="setTotal('${auxiliary.workType}')">+</button> 
										</div>
										<a class="del_btn bottom-15" href="javascript:void(0)" onclick="delteAuxiliary('${auxiliary.auxiMateCode}','${auxiliary.price }','${auxiliary.count }','${orderId }')">删除</a>
									</li>
								</c:if>
							</c:if>
						</c:forEach>
					</c:forEach>
				</c:if>
				<c:if test="${order.projectMode ne 1 && order.projectMode ne 4}">
					<c:forEach items="${auxiliaryList }" var="auxiliary">
						<li class="shadow _height-auto" id="${auxiliary.auxiMateCode}">
							<input type="text" hidden="hidden" name="auxiMateCode" value="${auxiliary.auxiMateCode}">
							<input type="text" hidden="hidden" name="workType" value="${auxiliary.workType}">
							<div class="img_container">
								<img src="${auxiliary.pic }" alt="goods"   onerror="nofind()">
							</div>
							<input type="text" hidden="hidden" name="auxiliaryCount" value="${auxiliary.count}" class="count">
							<p class="brand brand-name _ellipsis3"><span>【</span>${auxiliary.workTypeName}-${auxiliary.brand }<span>】</span>${auxiliary.name } </p>
							<p>  ${auxiliary.specifications }</p>
							<p class="format calc">
								<span class="col_red">  <fmt:formatNumber type="number" value="${auxiliary.price}" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>   </span>    *    <span class="showCount"> ${auxiliary.count}</span>   =   <span class="showTotal"><fmt:formatNumber type="number" value="  ${auxiliary.totalPrice }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></span> 
							</p>
							<!-- <p>
								单价 &nbsp;* 数量&nbsp; = 总价&nbsp;
							</p> -->
							<div id="numbox" class="mui-numbox numbox-submit" data-numbox-step='1' data-numbox-min='0' data-numbox-max='999' style="">
						  <button class="mui-btn mui-numbox-btn-minus" type="button" style="" onclick="setTotal('${auxiliary.workType}')">-</button>
		 					  <input id="num" class="mui-numbox-input" type="number" value="${auxiliary.count}" style=""  onchange="setTotal('${auxiliary.workType}')" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" />
							   <button class="mui-btn mui-numbox-btn-plus" type="button" style="" onclick="setTotal('${auxiliary.workType}')">+</button> 
							</div>
							<a class="del_btn bottom-15 " href="javascript:void(0)" onclick="delteAuxiliary('${auxiliary.auxiMateCode}','${auxiliary.price }','${auxiliary.count }','${orderId }')">删除</a>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		
		</div>
		
		<footer class="sub_footer">
			<div>
				<p class="col_red">合计：<span id="totalMoney"><fmt:formatNumber type="number" value="${allMoney } " pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>元</span></p>
				 <input type="text" hidden="hidden" name="AuxiliaryAllMoney" value="${allMoney }" id="total">
				<p class="goods" id="totalCount">共使用 <fmt:formatNumber value="${allCount }" pattern="0"></fmt:formatNumber> 个商品 </p>
			</div>
				</form>
			<a class="more_btn" href="javascript:void(0)" onclick="goOnForAuxiliary()">继续添加辅料</a>
			<a class="choose_btn" href="javascript:void(0)" onclick="pay()" id="sure">确认用量</a>
		</footer>
		
	</div>
	
	
	<div class="alertMask undis" id ="alert">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent" id="alertRemarks"></div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"  onclick="sure()" >我知道了</a>
			</div>
		</div>
	</div>
	
	
	<div class="alertMask undis" id ="alert2">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">您确认提交辅料吗？</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtn font33 col_fdfcfa" onclick="sure2()">
					确定
				</a>
				<a class="maskBtn font33 col_0780ec" onclick="cancel()">取消</a>
			</div>
		</div>
	</div>
	
	<div class="alertMask undis" id ="alert3">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">辅料申请成功</div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"  onclick="backlast()">我知道了</a>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/swiper-3.3.1.jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/auxiliaryApply/auxiliarySubmit.js"></script>
	<script>
		$(function () {
			  $('label').click(function(){
			    var radioId = $(this).attr('id');
			    $('label').removeAttr('class') && $(this).attr('class', 'checked');
			    $('input[type="radio"]').removeAttr('checked') && $('#' + radioId).attr('checked', 'checked');
			  });
	       		// 获取当前日期，结束日期
	    	var now = new Date(),
	    		start = new Date(now.setDate(now.getDate()+3)),
	    		
	    		start = globalUtil.fn.formatDate(start, 'yyyy-MM-dd'),
	    		end = new Date(now.setFullYear(now.getFullYear()+1)),
	    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');

	    	var lcalendar = start+','+end;
	    	// 将时间限制加到input标签上。
	    	$('#txtBeginDate').attr('data-lcalendar', lcalendar);
	    	$("#txtBeginDate").val(start);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
	    });
		
	</script>
</html>