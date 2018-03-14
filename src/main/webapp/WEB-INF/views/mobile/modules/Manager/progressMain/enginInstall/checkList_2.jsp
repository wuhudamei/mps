<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>安装验收</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/installcheck/css/new1/applyDoneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/apply.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/mask.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/orderInstallPlan/enginInstall"></a>
			<h2 class="title">安装验收</h2>
		</header><!-- /header -->
		<section class="pad_top88">
			<p class="font30 col_0780ec pt25 pb18 align_center">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom}-${order.customerName }</p>
			<p class="font30 col_0780ec pb25 align_center">实际开工日期：<fmt:formatDate type="date" value="${order.actualStartDate }"/></p>

			<c:forEach items="${installPlanList }" var="installAcceptance">
				<c:if test="${installAcceptance.installMode eq 1}">
					<c:if test="${installAcceptance.status eq '330' || installAcceptance.status eq '401'}">

						<div class="sec font0 border_top border_btm mb24 bg_f clearfix">
							<ul class="pl30 pr30 pad_top3 bor_dashed ">
								<li class="mb30 clearfix">
									<span class="col_grey font28 flo_left pl1em">安装项名称：</span>
									<p class="font28 col_3 pad_rgt3 flow installItemName">${installAcceptance.installItemName }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="col_grey font28 flo_left pl2em">安装模式：</span>
									<p class="font28 col_3 pad_rgt3 flow"> ${installAcceptance.installModeName} </p>
								</li>
								<li class="mb30 rele clearfix">
									<span class="col_grey font28 flo_left">计划安装日期：</span>
									<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate type="date" value="${installAcceptance.planIntoDate }"/></p>
								</li>
								<li class="mb30 clearfix">
									<span class="col_grey font28 flo_left pl2em">期望进场日期：</span>
									<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate type="date" value="${installAcceptance.applyIntoDate }"/></p>
								</li>
								<li class="mb30 clearfix">
									<span class="col_grey font28 flo_left pl1em">安装项状态：</span>
									<p class="font28 col_3 pad_rgt3 flow">${installAcceptance.statusName}</p>
								</li>
							</ul>
							<div class="btn_wrapper clearfix">
								<a class="btnBlueOne" onclick="acceptancePM(this,${installAcceptance.id })">去验收</a>
								<a class="btnBlueTwo" href="${ctx}/app/manager/orderInstallPlan/acceptUnqualifiedLog?id=${installAcceptance.id}&orderId=${installAcceptance.orderId}">验收日志</a>
							</div>
						</div>
					</c:if>
				</c:if>
				<c:if test="${installAcceptance.installMode eq '2'}">
					<c:if test="${installAcceptance.status eq '3' || installAcceptance.status eq '401'}">

						<div class="sec font0 border_top border_btm mb24 bg_f clearfix">
							<ul class="pl30 pr30 pad_top3 bor_dashed ">
								<li class="mb30 clearfix">
									<span class="col_grey font28 flo_left pl1em">安装项名称：</span>
									<p class="font28 col_3 pad_rgt3 flow installItemName">${installAcceptance.installItemName }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="col_grey font28 flo_left pl2em">安装模式：</span>
									<p class="font28 col_3 pad_rgt3 flow"> ${installAcceptance.installModeName} </p>
								</li>
								<li class="mb30 rele clearfix">
									<span class="col_grey font28 flo_left">计划安装日期：</span>
									<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate type="date" value="${installAcceptance.planIntoDate }"/></p>
								</li>
								<li class="mb30 clearfix">
									<span class="col_grey font28 flo_left pl2em">期望进场日期：</span>
									<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate type="date" value="${installAcceptance.applyIntoDate }"/></p>
								</li>
								<li class="mb30 clearfix">
									<span class="col_grey font28 flo_left pl1em">安装项状态：</span>
									<p class="font28 col_3 pad_rgt3 flow">${installAcceptance.statusName}</p>
								</li>
							</ul>
							<div class="btn_wrapper clearfix">
								<a class="btnBlueOne" onclick="acceptancePM(this,${installAcceptance.id })">去验收</a>
								<a class="btnBlueTwo" href="${ctx}/app/manager/orderInstallPlan/acceptUnqualifiedLog?id=${installAcceptance.id}&orderId=${installAcceptance.orderId}">验收日志</a>
							</div>

						</div>
					</c:if>
				</c:if>

			</c:forEach>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>


	<!-- /.wrap -->
	<!--公共弹层背景  class 「_in」 显示弹层背景-->
	<div class="alertMask "  id="alertMask">
	</div>
	<!-- /.alertMask -->
	<!--公共弹层内容-->
	<!-- 两个button情况 -->
	<div class="maskWrapper " id="maskWrapper">
		<div class="maskTit">通知</div>
		<div class="maskContent">
			<p class="font30">请确认安装项【<span id="installItemNameRemarks"></span>】是否合格？</p>
			<div class="radio-box flex flex-between">
                <span class="radio-span">
                        <input type="radio" id="yes2" name="isQualified" value="1" class="">
                        <label data-name="isQualified" for="yes2" class="checked">合格</label>
                </span>
				<span class="radio-span">
                        <input type="radio" id="no2" name="isQualified" value="0" class="">
                        <label data-name="isQualified" for="no2">不合格</label>
                </span>
			</div>
		</div>
		<div class="maskBtns twoBtns clearfix">
			<button class="maskBtn" type="button" onclick="agree()">确 定</button>
			<button class="maskBtn cancel" type="button" onclick="cancel()">取 消</button>
		</div>
	</div>


	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/css/installcheck/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/css/installcheck/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript">

        function run_waitMe(effect,text){
            $('body').waitMe({
                effect: effect,
                text: text,
                bg: 'rgba(255,255,255,0.7)',
                color:'#000',
                sizeW:'',
                sizeH:'',
                source: 'img.svg'
            });
        }

        $('.radio-span label').click(function() {
            var thisName = $(this).attr('data-name');
            $('label[data-name="' + thisName + '"]').removeAttr('class') && $(this).attr('class', 'checked');
        });

		var idGlobal;

		function acceptancePM(obj,id) {
			var installItemName = $(obj).parent().parent().find(".installItemName").text();

            idGlobal = id;

            $('label[data-name="isQualified"]').removeAttr('class');
            $('label[data-name="isQualified"][for="yes2"]').attr('class', 'checked');


            $("#installItemNameRemarks").text(installItemName);
            $("#alertMask").addClass('_in');
            $("#maskWrapper").addClass('_in');

        }

        //取消
        function cancel(){

            $("#alertMask").removeClass('_in');
            $("#maskWrapper").removeClass('_in');
        }

        //确认
        function agree(){
            var isQualified = $('label[data-name="isQualified"][class="checked"]').prev().val();
            if(isQualified){
                //防止重复提交
                run_waitMe('正在提交数据,请稍等');
                window.location.href = "${ctx }/app/manager/orderInstallPlan/acceptancePM?id="+idGlobal+"&isQualified="+isQualified;
            }
        }
	</script>
</body>
</html>