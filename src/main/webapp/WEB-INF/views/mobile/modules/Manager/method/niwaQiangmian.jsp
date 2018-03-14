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
	<title>墙面砖铺贴</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/three.css"/>
</head>
<body>
	<div class="mar_btm300">
		<header>
			<a class="back_btn"  onclick="myhistory.back()" href="${ctx}/app/manager/method/methodNiwa"></a>
			<h2 class="title">墙面砖铺贴</h2>
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
		<a class="item zhankai" href="javascript:;">泥瓦工程：墙面砖铺贴</a>
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
	<p class="mask-tit">墙面砖铺贴</p>
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
  		title: '1.铺贴前，必须先放样预排，优先考虑窗口高度，整砖与非整砖排版顺序；（拍照） ',
  		dw: '',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：0', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '2.墙砖铺贴前进行弾线统一定位；（拍照） ',
  		dw: '',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：0', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '3.铺贴基层必须结实，平整、清洁对墙面腻子基层，保温基层等其它光滑的基层进行凿除，打毛处理后方可进行下道工序；（拍照） ',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：8分/100元', '项目经理（分数/金额）：5分/50元', '施工工人（分数/金额）：8分/100元']
  	},
  	{
  		title: '4.使用釉面砖前须泡水时间充分，阴干后方可使用； ',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：3分/50元', '项目经理（分数/金额）：2分/30元', '施工工人（分数/金额）：3分/50元']
  	},
  	{
  		title: '5.非整砖应排放在次要部位或背角处；',
  		dw: '',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：0', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '6. 勾缝密实，线条顺直，表面洁净（铺贴完成后的24小时内 勾缝）；',
  		dw: '',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：0', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '7.铺贴前垫好底层，挂线铺贴；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：2分/100元', '项目经理（分数/金额）：2分/50元', '施工工人（分数/金额）：2分/100元']
  	},
  	{
  		title: '8.铺贴时底盒不能排在四片砖接合处的中间，出水口及底盒不能破坏；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：5分/100元', '项目经理（分数/金额）：3分/50元', '施工工人（分数/金额）：5分/100元']
  	},
  	{
  		title: '9.墙面砖铺贴必须墙砖压地砖，侧面压正面；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：2分/100元', '项目经理（分数/金额）：2分/50元', '施工工人（分数/金额）：2分/100元']
  	},
  	{
  		title: '10.浅色大理石与马赛克必须用白水泥铺贴；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：2分/100元', '项目经理（分数/金额）：2分/50元', '施工工人（分数/金额）：2分/100元']
  	},
  	{
  		title: '11.无明显色差，纹理顺畅；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：2分/100元', '项目经理（分数/金额）：2分/50元', '施工工人（分数/金额）：2分/100元']
  	},
  	{
  		title: '12.所有出水口必须与墙面平，不得低于墙面，标准为0mm—2mm；泥瓦工铺贴时如发现出水口陷于墙面，则将出水口往外调整；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：8分/100元', '项目经理（分数/金额）：5分/50元', '施工工人（分数/金额）：8分/100元']
  	},
  	{
  		title: '13.开孔作业必须使用开孔器；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：50元', '项目经理（分数/金额）：30元', '施工工人（分数/金额）：50元']
  	},
  	{
  		title: '14.垂直度≤2mm，平整度≤2mm，阴阳角方正度≤2mm；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：2分/100元', '项目经理（分数/金额）：2分/50元', '施工工人（分数/金额）：2分/100元']
  	},
  	{
  		title: '15.阳台、外墙有腻子底层，必须先铲除，然后水泥搓毛，方可铺贴；',
  		dw: '',
  		zgfs: '违：返工重做并罚款',
  		bcfr: ['质检员（分数/金额）：2分/100元', '项目经理（分数/金额）：2分/50元', '施工工人（分数/金额）：2分/100元']
  	},
  	{
  		title: '16.填缝顺直均匀，饱满，无色差；',
  		dw: '',
  		zgfs: '违：返工重做',
  		bcfr: ['质检员（分数/金额）：0', '项目经理（分数/金额）：0', '施工工人（分数/金额）：0']
  	},
  	{
  		title: '17.同一尺寸规格地砖与墙砖至少有一面墙对缝；',
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