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
		<div class="alls sec msg_sec g-message">
			<header class="">
				<h2 class="title">消息通知</h2>
			</header>
			<nav class="msg_nav bg_f mar_btm2 font0">
				<a class="nav_a font30 col_6" href="javascript:void(0)"><span class="active">系统通知</span></a>
				<a class="nav_a font30 col_6" href="${ctx}/app/worker/notice/queryNoticePageList"><span>公告</span></a>
			</nav>
				<ul class="list bg_f font0">
					<c:forEach items="${messages }" var="message" varStatus="status">
						<a href="${ctx}/app/worker/messageDetail?msgId=${message.msgId}">
					<%-- 		<input id = "pageCount" type="hidden" value="${pageCount}"/> --%>
							<%-- <input id = "disreadedcount" type="hidden" value="${ fn:length(unreadMessage)}"/> --%>
							<input id="messageRecord${status.index }" name="messageRecord" type="hidden" value="${message.msgId}"/>
							<li class="pad_left45 pad_rgt3 border_btm pad_btm5">
								<c:if test="${message.isReaded == 0 }">
									<span class="red_rnd"></span>
								</c:if>
								<%-- <p class="title font30 col_3 pad_top3">${message.msgTitle}</p> --%>
								<span class="time font26 col_9">
									<fmt:formatDate value="${message.msgTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
								</span>
								<p class="msg_content font28 col_6 pad_top2">
									${message.msgContent }
								</p>
							</li>
						</a>
					</c:forEach>
				</ul>
				<p class="load font28 col_6" id="loadmore" onclick="loadmore()">加载更多</p>
		</div>
		<footer>
			<a class="home_btn" href="${ctx}/app/worker/toindex">首页</a>
			<a class="msg_btn active"  style="position:relative;" href="javascript:void(0)">消息<div class="msg_count">${unreadMessageNum }</div></a>
			<a class="mine_btn" href="${ctx}/app/worker/myindex">我的</a>
		</footer>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			//title省略号的添加
			/* $('.title').each(function(index, val){
				var hidetext0 = $(this).text();
				if (hidetext0.length >= 10) {
					hidetext0 = hidetext0.substring(0, 10) + "...";
				}
				$(this).text(hidetext0);
			}); */
			//msg省略号的添加
			/* $('.msg_content').each(function(index, val){
				var hidetext = $(this).text();
				if (hidetext.length >= 40) {
					hidetext = hidetext.substring(0, 40) + "...";
				}
				$(this).text(hidetext);
			}); */
			//通过判断li里边有没有叫noread的class名字，添加小红点
			var redrnd = '<span class="red_rnd"></span>';
			$('.g-message li.noread').prepend(redrnd);

			/* //以事件委托的形式，绑定元素点击事件
			$(document).on('click', '.showAll', function(){
				$(this).parent().find('.some').hide();
				$(this).parent().find('.all').show();
				$(this).text('收起').addClass('showSome').removeClass('showAll');
			});
			$(document).on('click', '.showSome', function(){
				$(this).parent().find('.all').hide();
				$(this).parent().find('.some').show();
				$(this).text('展开').addClass('showAll').removeClass('showSome');
			}); */
		});
		function getMyDate(str){   
			var oDate = new Date(str),
	        oYear = oDate.getFullYear(),  
	        oMonth = oDate.getMonth()+1,  
	        oDay = oDate.getDate(),  
	        oHour = oDate.getHours(),  
	        oMin = oDate.getMinutes(),  
	        oSen = oDate.getSeconds(),  
	        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间  
	        return oTime;  
	    };
	    //补0操作  
	    function getzf(num){  
	        if(parseInt(num) < 10){  
	            num = '0'+num;  
	        }  
	        return num;  
	    }
	    
		var page = 1;
		var myindex = 9;
		function loadmore(){
			page++;
	 		var pageCount = parseInt($("#pageCount").val());
	 		/* var disreadedcount = parseInt($("#disreadedcount").val()); */
			$.ajax({
				url:"${ctx}/app/worker/loadMore",
				type:"post",
				data:{page:page},
				dataType:"json",
				success:function(messagePage){
					if(messagePage.length == 0){
						$("#loadmore").html("所有数据已加载");
			 			return;
					}
					var result = '';
					for(var i = 0; i< messagePage.length;i++){
						result +='<a href="${ctx}/app/worker/messageDetail?msgId='+messagePage[i].msgId+'">'
							   +'<li class="pad_left45 pad_rgt3 border_btm pad_btm5">'
							   +'<input id="messageRecord'+myindex+'" type="hidden" value="'+messagePage[i].msgId+'"/>';
						/* if(myindex<disreadedcount){
							result += '<span class="red_rnd"></span>';
						} */
						if(messagePage[i].isReaded == 0){
							result += '<span class="red_rnd"></span>';
						}
						result += /* '<p class="title font30 col_3 pad_top3">'+messagePage[i].msgTitle+'</p>'
							   +  */'<span class="time font26 col_9">'
							   + getMyDate(messagePage[i].msgTime)
							   +'</span>'
							   +'<p class="msg_content font28 col_6 pad_top2">'+messagePage[i].msgContent+'</p></li></a>';
					}
					$('.list').append(result);
				}
			});
		}
	</script>
</body>
</html>
