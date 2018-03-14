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
	<title>上报问题</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/quesDone.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/ProblemReport/ProblemReport.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
	<script>var baseUrl = '${ctx}'</script>
	<style>
		.pad_btm40{padding-bottom:.4rem;}
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
	
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/problem/siteDesign/list"></a>
			<h2 class="title">上报问题</h2>
		</header><!-- /header -->
		<div class="show_line">
			<p class="every bg_f h130  mb20 col_blue col_blue">${materialManagement.communityName }-${materialManagement.buildNumber }-${materialManagement.buildUnit }-${materialManagement.buildRoom }-${materialManagement.customerName }</p>
			<i class="bg_lace"></i>
		</div>
		<form id="jvForm" action="" method="post" enctype="multipart/form-data">
			<input type="text" hidden="hidden" id="orderId" name="orderId" value="${orderId }">
			<section class="problemReport">
				<ul class=" bg_f font0 pt40">
					<li class="mar_btm40 clearfix">
						<span class="font28 col_6 flo_left">问题分类 ：</span>
						<div class="select_cont flo_left">
							<c:if test="${not empty problemList }">
								<c:forEach items="${problemList }" var="problemFirst" varStatus="status">
									<c:if test="${status.index eq 0 }">
										<input type="text" hidden="hidden" id="problemTypeId" name="problemTypeId" value="${problemFirst.id }">
										<p class="select_choice font24 icon_blue_select">${problemFirst.typeName }</p>
										<i class=""></i>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${empty problemList }">
								<input type="text" hidden="hidden" id="problemTypeId" name="problemTypeId" >
								<p class="select_choice font24 icon_blue_select"></p>
								<i class=""></i>
							</c:if>
							<ul class="font24 select_option">
								<c:forEach items="${problemList }" var="problem">
									<li>
										<input type="hidden" name="quest" value="${problem.id }"> 
										<p>${problem.typeName }</p>
									</li>
								</c:forEach>
							</ul>
						</div>
						
					</li>
					<li class="mar_btm40 clearfix ">
						<span class="font30 col_6">期望完成日期：</span>
						<!-- <p class="pos_re"> -->
							<input id="txtDate" class="goods_date bor_73b7f3 enter_input font24 icon_canlender " type="text" value="" name="txtBeginDate" readonly="readonly" placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
							<i class="icon_canlender"></i>
						<!-- </p> -->
						
					</li>
					<li class="mar_btm40 clearfix ">
						<span class="font30 col_6">责任人：</span>
						<input class="enter_input bg_f8 climit_cont font24" name="inchargeName" id="inchargeName" type="text" value="${materialManagement.designerName }" placeholder="输入姓名" >
					</li>
					<li class=" clearfix">
						<span class="font30 col_6 flo_left">问题描述：</span>
						<textarea class="describ bg_f8" name="problemDescribe" id="problemDescribe" placeholder="输入问题详情" maxlength="100"></textarea>
					</li>
				</ul>
				<div class="bg_f bor_bot_1">
					<div class="intro font0 pad_left3">
						<span class="font30 col_3">上传图片</span>
					</div>
					<div class="pics font0 clearfix">
						<div class="pic camera" id="camera" href="javascript:void(0)">
							<img src="${ctxStatic}/mobile/modules/Manager/images/add_click.png" alt="调取摄像头">
							<input type="file" accept="image/*" capture="camera" onchange="preview(this)">
						</div>
						<input type="text" hidden="hidden" id="shit" value="1">
						<input type="text" hidden="hidden" id="num" value="">
						<%-- <div class="pic">
							<img src="${ctxStatic}/mobile/modules/Manager/images/eg.png" alt="上传图片">
							<a class="del_pic" href="javascript:void(0)"></a>
						</div>
						<div class="pic">
							<img src="${ctxStatic}/mobile/modules/Manager/images/canlender.png" alt="上传图片">
							<a class="del_pic" href="javascript:void(0)"></a>
						</div> --%>
					
					</div>
				</div>
			</section>
		</form>
		<footer class="footer">
			<a id="start" class="footer_btn" href="javascript:;">提交问题</a>
		</footer>
	</div>
	
	
	<div class="alertMask undis" id ="alert">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent" id="alertRemarks"></div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"  onclick="sure()" href="javascript:;">我知道了</a>
			</div>
		</div>
	</div>
	
	
	<div class="alertMask undis" id ="alert2">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">您确认要提交问题吗？</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtn font33 col_fdfcfa" href="javascript:void(0);" onclick="sure2()">
					确定
				</a>
				<a class="maskBtn font33 col_0780ec" href="javascript:void(0);" onclick="cancel()">取消</a>
			</div>
		</div>
	</div>
	
	<div class="alertMask undis" id ="alert3">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">工地设计问题上报成功</div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"  onclick="backlast()" href="javascript:;">我知道了</a>
			</div>
		</div>
	</div>
		
		
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/mobiscroll.custom-2.16.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/select_choice.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript">
	  
        
      var now = new Date();
		var currYear = now.getFullYear();
		var currMonth = now.getMonth() + 1;
		var currDay = now.getDate();
		$("#txtDate").val(globalUtil.fn.formatDate(new Date(currYear, currMonth - 1, currDay),'yyyy-MM-dd'));
		//mobiScroll插件选项
		var opt = {
			theme: "ios",
			lang: 'zh',
			dateFormat: 'yyyy-mm-dd', // 面板日期格式
			dateOrder: 'yyyymmdd', //面板中日期排列格式
			showNow: false,
			nowText: "今",
			endYear: currYear + 3, //结束年份
			minDate: new Date(currYear, currMonth - 1, currDay),
			onSelect: function (textVale, inst) { //选中时触发事件
				console.log("我被选中了.....");
			},
			onClose: function (textVale, inst) { //插件效果退出时执行 inst:表示点击的状态反馈：set/cancel
				console.log("textVale--" + textVale);
				console.log(this.id); //this表示调用该插件的对象
			}
		};
		$("#txtDate").mobiscroll().date(opt)
        
	</script>
</body>
</html>