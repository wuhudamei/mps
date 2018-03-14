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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/message.css"/>
	<script type="text/javascript">
		
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
	
		function save(index){
			var msgId = $("#messageRecord"+index).val();
			$.ajax({
				url:"${ctx}/app/worker/saveMessageRecord?msgId="+msgId,
				type:"get",
	    		dataType:"json",
	    		success:function(data){
	    		}
			});
			var $span = $("#readed"+index);
			$span.hide();
		}
		
		var page = 1;
		var myindex = 9;
	 	function loadmore(){
	 		page++;
	 		var pageCount = parseInt($("#pageCount").val());
	 		if(page>pageCount){
	 			$("#loadmore").html("所有数据已加载");
	 			return;
	 		}
	 		var disreadedcount = parseInt($("#disreadedcount").val());
			$.ajax({
				url:"${ctx}/app/worker/loadMore",
				type:"post",
				data:{page:page},
				dataType:"json",
				success:function(messagePage){
					var result = '';
					for(var i = 0; i< messagePage.length; i++){
						myindex++;
						if(myindex<disreadedcount){
							if(messagePage[i].busiId != null){
								result +=	'<li class="">'
									+'<input id="messageRecord'+myindex+'" type="hidden" value="'+messagePage[i].msgId+'"/>'
									+'<span class="red_rnd" id="readed'+myindex+'"></span>'
									+'<span class="time">'
									+getMyDate(messagePage[i].msgTime)
									+'</span>'
									+'<p class="content some">'+messagePage[i].msgContent+'</p>'
									+'<p class="content all undis">'+messagePage[i].msgContent
									+'<c:if test="'+messagePage[i].busiId != null +'">'
									+'<a class="showDetail" href="#">查看详情</a>'
									+'</c:if>'
									+'</p>'
									+'<a class="showAll" href="#" onclick="save('+myindex+')">展开</a>'
									+'</li>'
							}else{
								result +=	'<li class="">'
									+'<input id="messageRecord'+myindex+'" type="hidden" value="'+messagePage[i].msgId+'"/>'
									+'<span class="red_rnd" id="readed'+myindex+'"></span>'
									+'<span class="time">'
									+getMyDate(messagePage[i].msgTime)
									+'</span>'
									+'<p class="content some">'+messagePage[i].msgContent+'</p>'
									+'<p class="content all undis">'+messagePage[i].msgContent
									+'</p>'
									+'<a class="showAll" href="#" onclick="save('+myindex+')">展开</a>'
									+'</li>'
							}
						
						}else{
							if(messagePage[i].busiId != null){
							result +=	'<li class="">'
								+'<input id="messageRecord'+myindex+'" type="hidden" value="'+messagePage[i].msgId+'"/>'
								+'<p class="content some">'+messagePage[i].msgContent+'</p>'
								+'<span class="time">'
								+getMyDate(messagePage[i].msgTime)
								+'</span>'
								+'<p class="content all undis">'+messagePage[i].msgContent
								+'<a class="showDetail" href="#">查看详情</a>'
								+'</p>'
								+'<a class="showAll" href="#" onclick="save('+myindex+')">展开</a>'
								+'</li>'
							}else{
								result +=	'<li class="">'
									+'<input id="messageRecord'+myindex+'" type="hidden" value="'+messagePage[i].msgId+'"/>'
									+'<p class="content some">'+messagePage[i].msgContent+'</p>'
									+'<span class="time">'
									+getMyDate(messagePage[i].msgTime)
									+'</span>'
									+'<p class="content all undis">'+messagePage[i].msgContent
									+'</p>'
									+'<a class="showAll" href="#" onclick="save('+myindex+')">展开</a>'
									+'</li>'
							}
							
						}
					}
					$('.list').append(result);
				}
			});
			
		}
	</script>
</head>
<body>
	<div class="g-message">
		<header class="">
			<a class="back_btn" onclick="myhistory.back()" href="${ctx}/app/worker/toindex"></a>
			<h2 class="title">消息通知</h2>
		</header><!-- /header -->
		<section class="main">
			<ul class="list">
				<c:forEach var="message" items="${temp }" begin="0" end="9" varStatus="status">
					<li class="">
						<input id = "pageCount" type="hidden" value="${pageCount}"/>
						<input id = "disreadedcount" type="hidden" value="${ fn:length(allMessage)}"/>
						<input id="messageRecord${status.index }" name="messageRecord" type="hidden" value="${message.msgId}"/>
						<c:if test="${status.index < fn:length(allMessage) }">
							<span class="red_rnd" id="readed${status.index }"></span>
						</c:if>
						<span class="time">
							<fmt:formatDate value="${message.msgTime }" pattern="yyyy-MM-dd HH:mm:ss" />
						</span>
						<p class="content some">${message.msgContent }</p>
						<p class="content all undis">${message.msgContent }
							<c:if test="${message.busiId !=null }">
								<a class="showDetail" href="#">查看详情</a>
							</c:if>
						</p>
						<a class="showAll" href="#" onclick ="save('${status.index }')">展开</a>
					</li>
				</c:forEach>
			</ul>
			<p id="loadmore" class="load" onclick="loadmore()">加载更多</p>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script>
		//省略号的添加
		$('li .some').each(function(index, val){
			var hidetext = $(this).text();
			if (hidetext.length >= 40) {
				hidetext = hidetext.substring(0, 40) + "...";
			}
			// console.log(hidetext)
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
		
		
	</script>
</body>
</html>