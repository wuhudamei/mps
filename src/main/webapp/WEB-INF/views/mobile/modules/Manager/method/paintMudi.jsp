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
	<title>铝扣板吊顶</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/three.css"/>
</head>
<body>
	<div class="mar_btm300">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="${ctx}/app/manager/method/methodPaint"></a>
			<h2 class="title">铝扣板吊顶</h2>
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
		<a class="item zhankai" href="javascript:;">木工工程：铝扣板吊顶</a>
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
	<p class="mask-tit">铝扣板吊顶</p>
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
		var noticeList = [
  	{
  		title: '1.施工上游：完成基础全部施工，包含基础类安装项目，包含墙漆施工完毕，无任何作业；',
  		dw: '',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：0', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '2. 地板安装前须先检查基层地面平整度，内门下方高度，基层含水比例≤14%方可铺贴；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：1分/20元', '项目经理（分数/金额）：2分/50元', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '3.安装前对地面的平整度进行检查，平整度≥3mm，必须处理，达到合格要求；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：1分/20元', '项目经理（分数/金额）：2分/50元', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '4.铺贴防潮膜，沿墙上返≥20mm防潮膜的接口必须用透明胶链接，铺贴中，严禁将防潮膜弄破；（拍照）',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：1分/20元', '项目经理（分数/金额）：2分/50元', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '5.铺贴木地板须根据设计要求，靠墙周边必须留伸缩缝，伸缩缝≤5mm；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：1分/20元', '项目经理（分数/金额）：2分/50元', '施工工人（分数/金额）：0']
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