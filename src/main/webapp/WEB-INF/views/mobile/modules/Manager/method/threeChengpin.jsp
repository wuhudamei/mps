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
	<title>成品保护</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/three.css"/>
</head>
<body>
	<div class="mar_btm300">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="${ctx}/app/manager/method/methodOne"></a>
			<h2 class="title">成品保护</h2>
		</header><!-- /header -->
		<div class="pad_top88 font0" id="contentList"></div>
		<div class="tanchuceng undis" id="noticeBox"></div>
	</div>
</body>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/art-template.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script id="contentListTpl" type="text/html">
	<div>
		<a class="item zhankai" href="javascript:;">成品保护：成品保护服务</a>
		<ul class="ele bg_f undis">
		{{each list as value i}}
      <li class="font30 col_3 pad_top3 pad_btm3 mar_left3 mar_rgt3 dashed">
				{{ value.title }}
				<a class="oraBtn" href="javascript:;">考核方式</a>
			</li>
    {{/each}}
		</ul>
	</div>
</script>
<script id="noticeBoxTpl" type="text/html">
	<div class="tTop"></div>
	<a class="shutBtn" href="javascript:;"></a>
	<p class="mask-tit">成品保护</p>
	<div class="notice-box">
		<ul class="bg_f">
			<li class="font28 col_3 pad_top31 pad_btm31 border_btm">
				<p class="topOne leftIcon">违规内容或事项:</p>
				<p class="cont">{{ title }}</p>
			</li>
			<li class="font28 col_3 pad_top31 pad_btm31 border_btm pad_left70 leftIcon">整改方式：{{ zgfs }}</li>
			<li class="font28 col_3 pad_top31 pad_btm31 border_btm pad_left70 leftIcon">被处罚人：{{ bcfr[0] }}</li>
			<li class="font28 col_3 pad_top31 pad_btm31 border_btm pad_left70">被处罚人：{{ bcfr[1] }}</li>
			<li class="font28 col_3 pad_top31 pad_btm31 border_btm pad_left70">被处罚人：{{ bcfr[2] }}</li>
		</ul>
	</div>
</script>
<script>
	$(function(){
		var noticeList = [{
  		title: '1.地砖、地板表面未进行成品保护',
  		zgfs: '直接处罚',
  		bcfr: ['质检员1（分数/金额）：2分/20元', '项目经理1（分数/金额）：2分/20元', '施工工人1（分数/金额）：2分/50元']
  	},
  	{
  		title: '2.主材安装各阶段，项目经理应及时检查成品是否被破坏，如破坏，应及时拍照记录取证并书面上报大区经理，如不能及时（安装后48小时内）自证清白，发生业主索赔时，由项目经理自行承担。',
  		zgfs: '直接处罚',
  		bcfr: ['质检员2（分数/金额）：2分/20元', '项目经理2（分数/金额）：2分/20元', '施工工人2（分数/金额）：2分/50元']
  	},
  	{
  		title: '3.各工序做到活完场清，每天做到至少2扫1清（既中午、下午至少各扫一次，晚上收工，清完当天垃圾；要求地面无尘，垃圾入袋），如发现前一天未清理完毕的垃圾。',
  		zgfs: '直接处罚',
  		bcfr: ['质检员3（分数/金额）：2分/20元', '项目经理3（分数/金额）：2分/20元', '施工工人3（分数/金额）：2分/50元']
  	}];
		
  	// 初始化 #contentList
  	var contentListTplData = {list: noticeList};
		var contentListTpl = template('contentListTpl', contentListTplData);
		$('#contentList').html(contentListTpl);

		$(document).on('click','.item',function() {
			if($(this).hasClass('zhankai')){
				$(this).removeClass('zhankai').addClass('shouqi');
				$(this).parent().find('.ele').removeClass("undis");
			}else{
				$(this).removeClass('shouqi').addClass('zhankai');
				$(this).parent().find('.ele').addClass("undis");
			}
		})
		$(document).on('click','.oraBtn',function(){
			var clickIndex = $(this).parent().index();
			var noticeBoxTpl = template('noticeBoxTpl', noticeList[clickIndex]);
			$('#noticeBox').html(noticeBoxTpl);
			$('#noticeBox').removeClass('undis');
		})
		$(document).on('click','.shutBtn',function(){
			$('#noticeBox').html('');
			$('#noticeBox').addClass('undis');
		})
	}());
</script>
</html>