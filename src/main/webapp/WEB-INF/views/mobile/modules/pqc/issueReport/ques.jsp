<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>问题上报</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/quesDone.css"/>
	<link type="text/css" rel="stylesheet" 	href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
	<link rel="stylesheet" type="text/css"
		  href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css" />
</head>
<style>
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
	.maskBtn{display: block;width: 60%;text-align: center;line-height: .76rem;}
	.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
</style>
<body>
	<div class="">
		<header>
			<a class="back_btn"  href="${ctx}/app/pqc/issueReport/toIssueReportIndex"></a>
			<h2 class="title">问题上报</h2>
		</header><!-- /header -->
		<section class="pad_top88">
			<div class="font30 col_3 pad_top24 pad_btm24 pad_left30 pad_rgt30">基础信息</div>
			<form id="issueReportFormId">
				<input type="text" hidden="hidden" name="qcId" value="${entityInfo.pqcId}">
			<ul class="bg_f pad_left30 pad_rgt30 pad_top3 pad_btm24 list">
				<li class="mar_btm26 clearfix">
					<span class="font28 col_grey flo_left">顾客信息：</span>
					<p class="font28 col_grey overflow">${entityInfo.customerInfo}</p>
				</li>
				<li class="mar_btm26 clearfix">
					<span class="font28 col_grey flo_left">约检内容：</span>
					<p class="font28 col_grey overflow">${entityInfo.nodeName}</p>
				</li>
				<li class="mar_btm26 clearfix">
					<span class="font28 col_grey flo_left">约检日期：</span>
					<p class="font28 col_grey overflow">${entityInfo.qcExpectCheckDate}</p>
				</li>
			</ul>
			<div class="font30 col_3 pad_top24 pad_btm24 pad_left30 pad_rgt30">问题上报</div>
			<ul class="item_lists bg_f font0">
				<li class="mar_btm50">
					<span class="font28 col_6 spanRgt"><span class="font30 col_ce0f0f">*</span>问题类型：</span>
					<select class="select font24 col_9" id="issueSelectId" name="typeId">
						<option value=""></option>
						<c:forEach items="${mapList}" var="map">
							<option value="${map.typeId}">${map.typeName}</option>

						</c:forEach>

					</select>
				</li>


				<li class="mar_btm50 clearfix">
					<span class="font28 col_6 spanRgt">备注信息：</span>
					<input class="tip" type="text" name="problemDescribe">
				</li>

				<li class="mar_btm50 clearfix">
					<span class="font28 col_6 spanRgt">延期天数：</span>
					<input class="tip" type="text"  name="delayDays" id="delayDaysId" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
					<p class="font24 col_c20707 pad_left26 pad_top2" hidden="hidden">
						<em><img class="tipImg" src="${ctxStatic}/mobile/modules/pqc/images/tip.png" alt=""></em>
						延期天数只能输入数字，请进行修改！
					</p>
				</li>

				<%--<li class="mar_btm50 clearfix">--%>
					<%--<span class="font28 col_6 mar_btm50 spanRgt">上传图片：</span>--%>
					<%--<div class="pics font0 clearfix">--%>
						<%--<div class="pic camera" id="camera" href="javascript:void(0)">--%>
							<%--<img src="${ctxStatic}/mobile/modules/Worker/images/append.png" alt="">--%>
							<%--<input type="file" accept="image/*" onchange="preview(this)">--%>
						<%--</div>--%>
						<%--<div class="pic">--%>
							<%--<img src="${ctxStatic}/mobile/modules/Worker/images/eg.png" alt="上传图片">--%>
							<%--<a class="del_pic" href="javascript:void(0)"></a>--%>
						<%--</div>--%>
						<%--<div class="pic">--%>
							<%--<img src="${ctxStatic}/mobile/modules/Worker/images/canlender.png" alt="上传图片">--%>
							<%--<a class="del_pic" href="javascript:void(0)"></a>--%>
						<%--</div>--%>
						<%--<div class="pic">--%>
							<%--<img src="${ctxStatic}/mobile/modules/Worker/images/budget_icon1.png" alt="上传图片">--%>
							<%--<a class="del_pic" href="javascript:void(0)"></a>--%>
						<%--</div>--%>
						<%--<div class="pic">--%>
							<%--<img src="${ctxStatic}/mobile/modules/Worker/images/budget_icon2.png" alt="上传图片">--%>
							<%--<a class="del_pic" href="javascript:void(0)"></a>--%>
						<%--</div>--%>
						<%--<div class="pic">--%>
							<%--<img src="${ctxStatic}/mobile/modules/Worker/images/eg.png" alt="上传图片">--%>
							<%--<a class="del_pic" href="javascript:void(0)"></a>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</li>--%>
			</ul>
			</form>
		</section>
		<div class="alertMask undis">
			<div class="maskWrapper">
				<p class="col_3 maskTit">质检员您好</p>
				<div class="font28 col_6 maskContent"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f" href="javascript:;">确认</a>
				</div>
			</div>
		</div>

		<a class="subBtn" href="javascript:;">提交</a>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/issueReport/issueReport.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script type="text/javascript">
var baseUrl = "${ctx}";
        $(".maskBtn").on("click", function () {
            $(".alertMask").hide();
        })
		$(function(){
			$(document).on('click', '.del_pic', function(){
				$(this).parent().remove();
			});
		});

		$
		(".subBtn").bind("click",reportIssue);
        //蒙层效果
        function run_waitMe(text){
            $('body').waitMe({
                effect: 'win8',
                text: text,
                bg: 'rgba(255,255,255,0.7)',
                color:'#000',
                sizeW:'',
                sizeH:'',
                source: 'img.svg'
            });
        }
	</script>

</body>
</html>