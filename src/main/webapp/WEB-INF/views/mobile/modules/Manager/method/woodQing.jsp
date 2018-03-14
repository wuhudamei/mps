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
	<title>轻钢龙骨吊顶、隔墙</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/three.css"/>
</head>
<body>
	<div class="mar_btm300">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="${ctx}/app/manager/method/methodWood"></a>
			<h2 class="title">轻钢龙骨吊顶、隔墙</h2>
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
		<a class="item zhankai" href="javascript:;">木工工程：轻钢龙骨吊顶、隔墙</a>
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
	<p class="mask-tit">轻钢龙骨吊顶、隔墙</p>
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
  		title: '1.施工上游：水电隐蔽工程完工；',
  		dw: '',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：20元', '项目经理（分数/金额）：10元', '施工工人（分数/金额）：20元']
  	},
  	{
  		title: '2.吊顶龙骨按设计标高弹线水平垂直位置安装牢固；',
  		dw: '',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：20元', '项目经理（分数/金额）：10元', '施工工人（分数/金额）：20元']
  	},
  	{
  		title: '3.石膏板基层使用螺丝钉固定，并保证螺丝钉的足够尺寸，间距≦200mm，并低于石膏线0.5-1mm；',
  		dw: '',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：20元', '项目经理（分数/金额）：10元', '施工工人（分数/金额）：20元']
  	},
  	{
  		title: '4.轻钢龙骨吊顶主龙骨间距≦600mm，副龙骨间距≤400mm，靠墙部位用沿边龙骨，吊筋间距≤1000mm，严禁使用木方；（拍照）',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：8分/100元', '项目经理（分数/金额）：5分/50元', '施工工人（分数/金额）：8分/100元']
  	},
  	{
  		title: '5.天地龙骨和边龙骨必须使用膨胀螺丝固定，安装踢脚线位置使用9厘板加固，高度＞8mm；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：8分/100元', '项目经理（分数/金额）：5分/50元', '施工工人（分数/金额）：8分/100元']
  	},
  	{
  		title: '6.平整度≤3mm，垂直度≤3mm，方正度≤3mm；',
  		dw: '',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：20元', '项目经理（分数/金额）：10元', '施工工人（分数/金额）：20元']
  	},];
		
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