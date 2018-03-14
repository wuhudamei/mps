<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>消息通知</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/info.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/message2.css"/>
</head>
<body>
<div class="g-info">
	<div class="alls sec msg_sec g-message" style="margin-bottom: 300px;">
		<header class="">
			<h2 class="title">消息通知</h2>
		</header><!-- /header -->
		<nav class="msg_nav bg_f mar_btm2 font0">
			<a class="nav_a font30 col_6" href="${ctx}/app/worker/message"><span>系统通知</span></a>
			<a class="nav_a font30 col_6" href="javascript:void(0)"><span class="active">公告</span></a>
		</nav>
		<!-- <section class="main"> -->
		<input id="pageNo" type="hidden" value="1"/>
		<input id="pageTotal" type="hidden"/>
		<ul class="list bg_f font0" id="noticeListUl">
			<%--<a href="javascript:void(0)">
				<li class="noread pad_left45 pad_rgt3 border_btm pad_btm5">
					<!-- <span class="red_rnd"></span> -->
					<p class="title font30 col_3 pad_top3">春节放假</p>
					<span class="time font26 col_9">2016-08-01 18:30:00</span>
					<p class="msg_content font28 col_6 mar_top2"><em class="col_blue">【重要通知】</em><span>有新的可接任务包为您呈上，任务包名称，业主姓名，地点，预算价格，工期，请尽有新的可接任务包为您呈上，任务包名称，业主姓名，地点，预算价格，工期，请尽有新的可接任务包为您呈上，任务包名称，业主姓名，地点，预算价格，工期，请尽</span></p>
					<!-- <span class="showDetail font28 col_blue" href="#">查看详情</span> -->
				</li>
			</a>--%>
		</ul>
		<p class="load font28 col_6" id="loadMore">加载更多</p>
		<!-- </section> -->
	</div>
	<footer>
		<a class="home_btn" href="${ctx}/app/worker/toindex">首页</a>
		<a class="msg_btn active" href="${ctx}/app/worker/message">消息<div class="msg_countmine">${unreadMessageNum }</div></a>
		<a class="mine_btn" href="${ctx}/app/worker/myindex">我的</a>
	</footer>
</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		//title省略号的添加
		$('.title').each(function(index, val){
			var hidetext0 = $(this).text();
			if (hidetext0.length >= 10) {
				hidetext0 = hidetext0.substring(0, 10) + "...";
			}
			$(this).text(hidetext0);
		});
		//msg省略号的添加
		$('.msg_content span').each(function(index, val){
			var hidetext = $(this).text();
			if (hidetext.length >= 30) {
				hidetext = hidetext.substring(0, 30) + "...";
			}
			$(this).text(hidetext);
		});
		//通过判断li里边有没有叫noread的class名字，添加小红点
		var redrnd = '<span class="red_rnd"></span>';
		$('.g-message li.noread').prepend(redrnd);

		//以事件委托的形式，绑定元素点击事件
		$(document).on('click', '.showAll', function(){
			$(this).parent().find('.some').hide();
			$(this).parent().find('.all').show();
			$(this).text('收起').addClass('showSome').removeClass('showAll');
		});
		$(document).on('click', '.showSome', function(){
			$(this).parent().find('.all').hide();
			$(this).parent().find('.some').show();
			$(this).text('展开').addClass('showAll').removeClass('showSome');
		});

		$("#pageTotal").val(Math.ceil(parseInt(${count})/6));
		loadList();

		$("#loadMore").click(function(){
			$("#pageNo").val(parseInt($("#pageNo").val())+1);
			loadList();
		});
	});

	function loadList(){
		if($("#pageNo").val() == $("#pageTotal").val()){
			$("#loadMore").hide();
		}
		$.post("${ctx}/app/worker/notice/queryLoadNoticePageList",{pageNo:$("#pageNo").val(),pageSize:6,receiverEmployeeId:${workerId}},function(result){
			var content = "";
			for(var i=0;i<result.list.length;i++){
				var alreadContent = "";
				if(result.list[i].yesReadNum == 0){
					alreadContent = "<span class='red_rnd'></span>";
				}
				content = content + "<a href='${ctx}/app/worker/notice/queryNoticePageListDetail?id="+result.list[i].id+"'><li class='noread pad_left45 pad_rgt3 border_btm pad_btm5'>" +alreadContent+"<p class='title font30 col_3 pad_top3'>"+
				"</p><span class='time font26 col_9'>"+format(result.list[i].publishDatetime,"yyyy-MM-dd HH:mm:ss")+"</span>" +
				"<p class='msg_content font28 col_6 pad_top2'><em class='col_blue'>【"+getNoticeTypeName(result.list[i].noticeType)+"】</em><span>"+result.list[i].noticeTitle+"</span></p>"+
				"</li></a>";
			}
			$("#noticeListUl").append(content);
		});
	}

	function getNoticeTypeName(noticeType){
		if(noticeType == '1'){
			return "紧急通知";
		}else if(noticeType == '2'){
			return "重要通知";
		}else if(noticeType == '3'){
			return "日常通知";
		}
	}

	var format = function(time, format){
		var t = new Date(time);
		var tf = function(i){return (i < 10 ? '0' : '') + i};
		return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
			switch(a){
				case 'yyyy':
					return tf(t.getFullYear());
					break;
				case 'MM':
					return tf(t.getMonth() + 1);
					break;
				case 'mm':
					return tf(t.getMinutes());
					break;
				case 'dd':
					return tf(t.getDate());
					break;
				case 'HH':
					return tf(t.getHours());
					break;
				case 'ss':
					return tf(t.getSeconds());
					break;
			}
		})
	}
</script>
</body>
</html>