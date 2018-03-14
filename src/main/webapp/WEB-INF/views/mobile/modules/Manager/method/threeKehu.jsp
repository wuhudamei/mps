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
	<title>客户体验</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/three.css"/>
</head>
<body>
	<div class="mar_btm300">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="${ctx}/app/manager/method/methodKehu"></a>
			<h2 class="title">客户体验</h2>
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
		<a class="item zhankai" href="javascript:;">客户体验：综合体验</a>
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
	<p class="mask-tit">客户体验</p>
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
  		title: '1.工程人员未统一着“美得你”春夏工作服，赤膊施工',
  		dw: null,
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：3分/20元', '项目经理（分数/金额）：5分/100元/次', '施工工人（分数/金额）：8分/200元']
  	},
  	{
  		title: '2.业主指正错误或因不专业而咨询、质疑工程时，公司人员顶撞和抵触业主，与业主，吵架，打架，对业主态度恶劣，言行严重影响公司声誉或引起客户不满意',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：3分/200元', '项目经理（分数/金额）：1、被业主投诉到公司，则处以8分/500元/次罚款；2、被业主投诉到媒体，则处以10分/1000元/次罚款；3、施工现场斗殴或与客户争吵直接辞退', '施工工人（分数/金额）：10分/200元']
  	},
  	{
  		title: '3.开工前施工现场各种保护、宣传标贴张贴不到位',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：200元', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '4.工作期间喝酒而导致的工作失误、工程事故及产生的经济损失',
  		zgfs: '工作期间禁止烟酒',
  		bcfr: ['质检员（分数/金额）：辞退', '项目经理（分数/金额）：辞退并全额承担', '施工工人（分数/金额）：辞退']
  	},
  	{
  		title: '5.材料码放未分类别、材料性质，相互间易污染的材料未分开放置在标化指示牌下，垃圾未装袋处理。',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：1分/20元', '项目经理（分数/金额）：2分/100元/处', '施工工人（分数/金额）：100元']
  	},
  	{
  		title: '6.高空抛物，倾倒垃圾，如发生损失，由责任人承担。',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：8分/100元/次', '施工工人（分数/金额）：8分/200元/次']
  	},
  	{
  		title: '7.施工现场脏、乱、差',
  		dw: '间',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：1分/10元', '项目经理（分数/金额）：2分/20元', '施工工人（分数/金额）：3分/30元']
  	},
  	{
  		title: '8.不服从物业管理，没按规定时间施工或其它原因造成不良影响的',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：50元/次', '施工工人（分数/金额）：100元/次']
  	},
  	{
  		title: '9.客户参观工地，因现场卫生、质量、工人素质等问题引起跑单',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：3分/200元/次', '施工工人（分数/金额）：3分/200元/次']
  	},
  	{
  		title: '10.客户坚决要求更换项目经理，经工程部认定确属项目经理的原因',
  		dw:'单',
  		zgfs: '工作期间禁止烟酒',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：5分/500元/单;情节恶劣/1000元/单/停单', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '11.客服回访，业主抱怨项目经理，经查实后无误',
  		dw:'次',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：3分/200元', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '12.合同中已包含但仍向业主要钱买材料',
  		zgfs: '退还',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：所收的金额5倍处罚;情节恶劣/1000元/单/停单', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '13.工程完工，超过三个月，尾款无正当理由未收缴',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：1分/50元', '项目经理（分数/金额）：2分/500元;情节恶劣/1000元/单/停单', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '14.在业主面前抱怨公司，传递负能量，影响业主心情',
  		zgfs: '违：辞退',
  		bcfr: ['质检员（分数/金额）：辞退', '项目经理（分数/金额）：辞退', '施工工人（分数/金额）：辞退']
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