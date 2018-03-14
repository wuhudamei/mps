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
	<title>道德品行</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/three.css"/>
</head>
<body>
	<div class="mar_btm300">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="${ctx}/app/manager/method/methodDaode"></a>
			<h2 class="title">道德品行</h2>
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
		<a class="item zhankai" href="javascript:;">道德品行：道德品行考核要求</a>
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
	<p class="mask-tit">道德品行</p>
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
  		title: '1.在“美得你”公司外的其他公司供职接单',
  		zgfs: '违：辞退',
  		bcfr: ['质检员（分数/金额）：辞退', '项目经理（分数/金额）：辞退', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '2.将所接工地转包给其他人员进行施工，将工地交与其他人代为管理',
  		zgfs: '违：罚款停单',
  		bcfr: ['质检员（分数/金额）：10分/100元', '项目经理（分数/金额）：10分/1000元/停单两个月', '施工工人（分数/金额）：10分/1000元']
  	},
  	{
  		title: '3.未严格按照“合同报价单”内容施工。 业主未要求增项，诱导业主增项。业主要求增项，项目经理未按照公司相应系统提报，自行或与施工工人私下承揽。',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：5分/100元', '项目经理（分数/金额）：10分/变更金额5倍罚款', '施工工人（分数/金额）：10分/200元']
  	},
  	{
  		title: '4.无故拒单、挑单',
  		zgfs: '违：罚款停单',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：5分/首次罚款200元；二次拒单，10分；罚款500元/停单两月。', '施工工人（分数/金额）：两次机会/停单']
  	},
  	{
  		title: '5.业主原有房屋进行拆除中，未做到轻拿轻放，导致业主财产损失，由项目经理承担30%，施工工人承担70%。',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：3分', '施工工人（分数/金额）：3分']
  	},
  	{
  		title: '6.拆除中发现意外财物，未主动交还业主，自行留存人员直接开除',
  		zgfs: '违：辞退',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：辞退', '施工工人（分数/金额）：辞退']
  	},
  	{
  		title: '7.发现安全隐患未及时告知业主',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：10分/200元', '项目经理（分数/金额）：10分/200元', '施工工人（分数/金额）：5分/100元']
  	},
  	{
  		title: '7.施工现场脏、乱、差',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：1分/10元', '项目经理（分数/金额）：2分/20元', '施工工人（分数/金额）：3分/30元']
  	},
  	{
  		title: '8.未及时清扫室外公共走道部位施工中所遗撒的垃圾，导致业主邻里间的矛盾',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：1分/50元/次', '项目经理（分数/金额）：2分/100元/次', '施工工人（分数/金额）：2分/100元/次']
  	},
  	{
  		title: '9.在施工区域内私自接业务，或与设计师串通私下接单施工',
  		zgfs: '违：辞退',
      bcfr: ['质检员（分数/金额）：辞退', '项目经理（分数/金额）：辞退', '施工工人（分数/金额）：辞退']
  	},
  	{
  		title: '10.与公司质检串通徇私舞弊欺骗客户、收受客户礼物或宴请',
  		zgfs: '违：辞退',
      bcfr: ['质检员（分数/金额）：辞退', '项目经理（分数/金额）：辞退', '施工工人（分数/金额）：辞退']
    },
  	{
  		title: '11.项目经理假冒客户签字做竣工验收',
  		zgfs: '违：辞退',
      bcfr: ['质检员（分数/金额）：辞退', '项目经理（分数/金额）：辞退', '施工工人（分数/金额）：0']
    },
  	{
  		title: '12.核算工程量时为工人多报工程量',
  		zgfs: '违：罚款辞退',
  		bcfr: ['质检员（分数/金额）：执行质检考核细则', '项目经理（分数/金额）：10分/损失额10倍罚款（最低2000元）', '施工工人（分数/金额）：核算工程量两倍处罚辞退']
  	},
  	{
  		title: '13.被公司责令除名的工人仍滞留在施工现场',
  		zgfs: '违：罚款',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：3分/200元/次', '施工工人（分数/金额）：清退']
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