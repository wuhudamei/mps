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
	<title>灯具安装</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/three.css"/>
</head>
<body>
	<div class="mar_btm300">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="${ctx}/app/manager/method/methodBuildOne"></a>
			<h2 class="title">灯具安装</h2>
		</header><!-- /header -->
		<div class="pad_top88 font0" id="contentList"></div>
		<div class="tanchuceng undis" id="noticeBox"></div>
	</div>
</body>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/art-template.js"></script>
<script id="contentListTpl" type="text/html">
	<div class="">
		<a class="item zhankai" href="javascript:;">强电工程：灯具安装</a>
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
	<p class="mask-tit">灯具安装</p>
	<div class="notice-box">
		<ul class="bg_f">
			<li class="font28 col_3 pad_top31 pad_btm31 border_btm">
				<p class="topOne leftIcon">违规内容或事项:</p>
				<p class="cont">{{ title }}</p>
			</li>
			<li class="font28 col_3 pad_top31 pad_btm31 border_btm pad_left70 leftIcon">单　　位：{{ dw }}</li>
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
  		title: '1.灯具安装前墙顶面漆工程完工，否则，严禁安装；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：2分/30元', '项目经理（分数/金额）：2分/20元', '施工工人（分数/金额）：2分/30元']
  	},
  	{
  		title: '2.超过1.5公斤灯具，必须采用膨胀钩；（灯具脱落造成经济损失及人身伤害由项目经理承担30%，施工工人承担70%）',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：10分/100元', '项目经理（分数/金额）：5分/50元', '施工工人（分数/金额）：10分/100元']
  	},
  	{
  		title: '3.所有灯线连接必须牢固，严实，无松动；',
  		dw: '',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：0', '施工工人（分数/金额）：0']
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