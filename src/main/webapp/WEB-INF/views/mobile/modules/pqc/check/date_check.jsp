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
	<title>约检列表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil2.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newCommon/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/date_check.css"/>
	
</head>
<body>
	<div class="g-task_list">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/pqcIndex"></a>
			<h2 class="title">约检列表</h2>
		</header><!-- /header -->
		<section class="task_list">
			<p class="sch_div clearfix">
				<input class="sch_text" type="text" value="${text}" placeholder="小区名字、客户姓名、项目经理">
				<a class="sch_btn" href="javascript:;" >搜索</a>
			</p>
			<c:forEach items="${list }" var="inspectInfo">				
			<div class="shadow">
			<ul <c:if test="${inspectInfo.isScrap eq '1'}"> class="abandon" </c:if>>
					<li class="clearfix">
						<span>顾客：</span>
						<p>${inspectInfo.communityAddress }-${inspectInfo.buildNumber }-${inspectInfo.buildUnit }-${inspectInfo.buildRoom } - ${inspectInfo.customerName }</p>
					</li>
					<li class="clearfix">
						<span>客户手机：</span>
						<p>${inspectInfo.customerPhone }</p>
					</li>
					<li class="clearfix">
						<span>项目经理：</span>
						<p>${inspectInfo.managerName }</p>
					</li>
					<li class="clearfix">
						<span>项目经理手机：</span>
						<p>${inspectInfo.managerPhone }</p>
					</li>
					<li class="clearfix">
						<span>实际开工日期：</span>
						<p> <fmt:formatDate value="${inspectInfo.actualStartDate }" pattern="yyyy-MM-d" /></p>
					</li>
					<li class="clearfix">
						<span>约检节点名称：</span>
						<p>${inspectInfo.checkNodeName }</p>
					</li>


					<li class="clearfix">
						<span>期望验收日期：</span>
						<p class=""><fmt:formatDate value="${inspectInfo.checkTime }" pattern="yyyy-MM-dd" /></p>
					</li>
				<li class="clearfix">
					<span>项目经理申请延期原因：</span>
					<p>${inspectInfo.delayDaysPm}</p>
				</li>
			</ul>
			
				
				<div class="btns clearfix">
					<%-- <a class="btn" href="${ctx}/app/pqc/checkList/pqcsign?id=${inspectInfo.id}&orderId=${inspectInfo.orderId}">到场签到</a> --%>
					<a class="btn signButton" orderId="${inspectInfo.orderId}" taskId="${inspectInfo.id}" lon="${inspectInfo.lon}" lat="${inspectInfo.lat}" href="javascript:void(0)">到场签到</a>
					
					<c:if test="${inspectInfo.status=='2'}">
						<c:if test="${inspectInfo.noScoreCount !=0}">
							<a class="btn" href="javascript:showNoScore();">检查项目</a>
						</c:if>
						<c:if test="${inspectInfo.noScoreCount ==0}">
							<a class="btn" href="${ctx}/app/pqc/checkItem/itemsList?inspectId=${inspectInfo.id}&customerInfo=${inspectInfo.communityAddress }-${inspectInfo.buildNumber }-${inspectInfo.buildUnit }-${inspectInfo.buildRoom }-${inspectInfo.customerName }">检查项目</a>
						</c:if>
						<%-- <a class="btn" href="${ctx}/app/pqc/checkItem/itemsList?inspectId=${inspectInfo.id}&customerInfo=${inspectInfo.communityAddress }-${inspectInfo.buildNumber }-${inspectInfo.buildUnit }-${inspectInfo.buildRoom }-${inspectInfo.customerName }">检查项目</a> --%>
					</c:if>
					
					<c:if test="${inspectInfo.status=='0' }"> 
						<c:if test="${inspectInfo.noScoreCount !=0}">
							<a class="rgt_btn" href="javascript:showNoScore();">检查项目</a>
						</c:if>
						<c:if test="${inspectInfo.noScoreCount ==0}">
							<a class="rgt_btn" href="${ctx}/app/pqc/checkItem/itemsList?inspectId=${inspectInfo.id}&customerInfo=${inspectInfo.communityAddress }-${inspectInfo.buildNumber }-${inspectInfo.buildUnit }-${inspectInfo.buildRoom }-${inspectInfo.customerName }">检查项目</a>
						</c:if>
					<%-- <a class="rgt_btn" href="${ctx}/app/pqc/checkItem/itemsList?inspectId=${inspectInfo.id}&customerInfo=${inspectInfo.communityAddress }-${inspectInfo.buildNumber }-${inspectInfo.buildUnit }-${inspectInfo.buildRoom }-${inspectInfo.customerName }">检查项目</a> --%>
					</c:if>
					<c:if test="${inspectInfo.status!='2' &&  inspectInfo.status!='0'}">
						<a class="btn grey_btn">检查项目</a>
					</c:if>
					
					<c:if test="${inspectInfo.status=='5' || inspectInfo.status=='20'}">
						<c:if test="${inspectInfo.noScoreCount !=0}">
							<a class="btn" href="javascript:showNoScore();">确认验收</a>
						</c:if>
						<c:if test="${inspectInfo.noScoreCount ==0}">
							<a class="btn" href="${ctx}/app/pqc/confirm/checkConfirmList?id=${inspectInfo.id}">确认验收</a>
						</c:if>
					</c:if>
					<c:if test="${inspectInfo.status!='5' && inspectInfo.status!='20'}">
						<c:if test="${inspectInfo.noScoreCount !=0}">
							<a class="grey_btn" href="javascript:showNoScore();">确认验收</a>
						</c:if>
						<c:if test="${inspectInfo.noScoreCount ==0}">
							<a class="grey_btn">确认验收</a>
						</c:if>
						<!-- <a class="grey_btn">确认验收</a> -->
					</c:if>
				</div>
			</div>
				</c:forEach>
		</section>
		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="timeAlert">
			<div id="g-done_ask">
				<p class="first">该约检单已经确认验收</p>
				<p class="second">
					
					<a href="#" onclick="queren()">确认</a>
				</p>
			</div> 
		</div>
		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="noScore">
			<div id="g-done_ask" style="width:5.8rem">
				<p class="first">您还有未评价工人的任务包，请先去【评价工人】页面评价后，再检查项目或者确认验收。</p>
				<p class="second">
					
					<a href="#" onclick="hideNoScore();">确认</a>
				</p>
			</div> 
		</div>
	</div>
	
	<input id="lat" hidden="hidden"/>
	<input id="lon" hidden="hidden"/>
	<input id="packId"  hidden="hidden"/>
	<input id="orderId"  hidden="hidden"/>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script type="text/javascript">
		function queren(){
			 $('#timeAlert').hide();
		}
		function hideNoScore(){
			$('#noScore').hide();
		}
		function showNoScore(id){
			console.log(id);
			$('#noScore').show();
		}
		$(function(){
			var time = "${timeForbidden}";
			var backUrl = "${backUrl}";

            $(".sch_btn").bind("click",ajaxSearch);
			//返回url
		if(""!=backUrl){
            var decode = decodeURIComponent(backUrl);
		    $(".back_btn").attr("href",decode);
            $(".sch_text").val("${customerName}");
            ajaxSearch();
        }


			if(""!=time &&"1"==time && null!=time){
				$("#timeAlert").show();
			}

			
			
			$(document).on('click', '.nav_a', function(){
				if ($('.nav_a').hasClass('active')) {
					globalUtil.fn.hideMask();
					$('.nav_a').removeClass('active');
					$('.nav_a').find('img').attr('src','${ctxStatic}/mobile/modules/pqc/images/down_btn.png');
					$('nav .options').addClass('undis');
				}else{
					globalUtil.fn.showMask();
					$('.g-mask').on('click',function(){
						globalUtil.fn.hideMask();
						$(this).removeClass('active');
						$(this).find('img').attr('src','${ctxStatic}/mobile/modules/pqc/images/down_btn.png');
						$('nav .options').addClass('undis');
					});
					$('nav a.active').removeClass('active');
					$('nav a.nav_a img').attr('src','${ctxStatic}/mobile/modules/pqc/images/down_btn.png');
					$(this).addClass('active');
					$('nav a.active img').attr('src','${ctxStatic}/mobile/modules/pqc/images/down_btn_active.png');
					$('nav .options').addClass('undis');
					$('nav .options').removeClass('undis');
				}
			})
			$('nav .options a').each(function(index, val){
				$(val).click(function(){
					// console.log($(val).text());
					globalUtil.fn.hideMask();
					$('nav a.active span').text($(val).text());
					$('nav a.active').addClass('selected').removeClass('active');
					$('nav a img').attr('src','${ctxStatic}/mobile/modules/pqc/images/down_btn.png');
					$('nav .options').addClass('undis');
				});
			});
		}());
		
		
		
		
		
		
		function ajaxSearch(){
			var text = $(".sch_text").val();
            window.location.href = "${ctx}/app/pqc/checkList/list?text="+text;
			<%--$(".task_list").html("");--%>
			<%--$(".task_list").append('<p class="sch_div clearfix">	<input class="sch_text" type="text" placeholder="小区名字、客户姓名、项目经理">'--%>
<%--+'	<a class="sch_btn" href="javascript:;" onclick="ajaxSearch()">搜索</a></p>'	)--%>
			<%----%>
		<%----%>
		<%----%>
		<%----%>
			<%--$.ajax({--%>
				<%--url: "${ctx}/app/pqc/checkList/ajax_search_list?text="+text,--%>
				<%--type : "get",--%>
				<%--success: function (data){--%>
					<%--if(null!=data){--%>
						<%--for(var v = 0;v<data.length;v++){--%>
						<%--$(".task_list").append('	<div class="shadow"><ul><li class="clearfix">	<span>顾客信息：</span><p>'+data[v].communityAddress+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'-'+data[v].customerName+'</p>'--%>
						<%--+'</li><li class="clearfix">	<span>客户手机：</span><p>'+data[v].customerPhone+'</p>'--%>
						<%--+'	</li><li class="clearfix"><span>项目经理：</span><p>'+data[v].managerName+'</p>'--%>
						<%--+'	</li>	<li class="clearfix"><span>实际开工日期：</span>	<p>'+format(data[v].actualStartDate,"yyyy-MM-dd")+'</p>'--%>
						<%--+'	</li><li class="clearfix">	<span>约检节点名称：</span>	<p>'+data[v].checkNodeName+'</p>'--%>
						<%--+'	</li>	<li class="clearfix"><span>期望验收日期：</span>	<p class="">'+format(data[v].checkTime,"yyyy-MM-dd")+'</p>'--%>
						<%--+'<li class="clearfix"><span>项目经理申请延期原因：</span> <p>'+data[v].delayDaysPm+'</p></li>'--%>
						<%--+'	</li></ul>	<div class="btns clearfix" id="divBtn'+v+'">'--%>
						<%--);--%>
						<%----%>
						<%----%>
						<%----%>
						<%----%>
				<%--if(data[v].status==2){--%>
					<%--/* $(("#divBtn"+v)).append('<a class="btn" href="${ctx}/app/pqc/checkList/pqcsign?id='+data[v].id+'&orderId='+data[v].orderId+'">到场签到</a>'); */--%>
					<%--$(("#divBtn"+v)).append('<a class="btn signButton" orderId="'+data[v].orderId+'" taskId="'+data[v].id+'" lon="'+data[v].lon+'" lat="'+data[v].lat+'" href="javascript:void(0)">到场签到</a>');--%>
					<%--$(("#divBtn"+v)).append('<a class="btn" href="${ctx}/app/pqc/checkItem/itemsList?inspectId='+data[v].id+'">检查项目</a>');--%>
					<%----%>
				<%--}else if (data[v].status==0){--%>
					<%--/* $(("#divBtn"+v)).append('<a class="btn" href="${ctx}/app/pqc/checkList/pqcsign?id='+data[v].id+'&orderId='+data[v].orderId+'">到场签到</a>'); */--%>
					<%--$(("#divBtn"+v)).append('<a class="btn signButton" orderId="'+data[v].orderId+'" taskId="'+data[v].id+'" lon="'+data[v].lon+'" lat="'+data[v].lat+'" href="javascript:void(0)">到场签到</a>');--%>
					<%--$(("#divBtn"+v)).append('<a class="rgt_btn" href="${ctx}/app/pqc/checkItem/itemsList?inspectId='+data[v].id+'">检查项目</a>');--%>
					<%----%>
					<%----%>
				<%--}else if (data[v].status!=2&&data[v].status!=0){--%>
					<%--/* $(("#divBtn"+v)).append('<a class="btn" href="${ctx}/app/pqc/checkList/pqcsign?id='+data[v].id+'&orderId='+data[v].orderId+'">到场签到</a>'); */--%>
					<%--$(("#divBtn"+v)).append('<a class="btn signButton" orderId="'+data[v].orderId+'" taskId="'+data[v].id+'" lon="'+data[v].lon+'" lat="'+data[v].lat+'" href="javascript:void(0)">到场签到</a>');--%>
					<%--$(("#divBtn"+v)).append('<a class="btn grey_btn">检查项目</a>');--%>
				<%----%>
					<%----%>
				<%--}else{--%>
					<%----%>
					<%----%>
				<%--}--%>
				<%----%>
				<%--if(data[v].status==5||data[v].status==20){--%>
					<%--$(("#divBtn"+v)).append('<a class="btn" href="${ctx}/app/pqc/confirm/checkConfirmList?id='+data[v].id+'">确认验收</a></div>');--%>
					<%--$(("#divBtn"+v)).append('</div>');--%>
				<%--}else if (data[v].status!=5||data[v].status!=20){--%>
					<%--$(("#divBtn"+v)).append('<a class="grey_btn">确认验收</a></div>');--%>
					<%--$(("#divBtn"+v)).append('</div>');--%>
				<%--}else{--%>
					<%----%>
					<%----%>
				<%--}--%>
				<%----%>
				<%--$(".task_list").append('</div>');--%>
			<%----%>
														<%--}	--%>
						<%----%>
					<%----%>
						<%--$(".sch_text").val(text);   		--%>
						<%----%>
					<%--}else{--%>
						<%----%>
						<%--alert("没找到合适的")--%>
					<%--}--%>
				<%----%>
				<%--}--%>
				<%----%>
				<%----%>
			<%--})--%>
			
			
			
			
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
		
		
		
		//到场签到
		var u = navigator.userAgent;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		window.onerror = function(err) {
			log('window.onerror: ' + err)
		}

		if(isAndroid){
			$(".signButton").each(function(){
				$(this).click(function(){
					var orderId = $(this).attr("orderId");
					var taskId = $(this).attr("taskId");
					var lon = $(this).attr("lon");
					var lat = $(this).attr("lat");
					$("#orderId").val(orderId);
					$("#packId").val(taskId);
					$("#lat").val(lat);
					$("#lon").val(lon);

					android.userlocation();
				});
			});

			function locationResult(json){
				var data = eval(json);
				if(data.type == "succ"){
					var signErrorDistance = getDistance($("#lat").val(),$("#lon").val(),data.latitude,data.longitude);
					var lon = data.longitude;
					var lat = data.latitude;
					var relatedBusinessId = $("#packId").val();
					var signAddress = data.address;
					var text = $(".sch_text").val();
					$.post("${ctx}/app/pqc/checkList/savepqcsign", {lat:lat,lon:lon,relatedBusinessId:relatedBusinessId,signErrorDistance:signErrorDistance,signAddress:signAddress},
							function(result){
								if(result == "success"){
									globalUtil.fn.alert("签到成功!",2.0);
									//setTimeout('window.location.href="${ctx }/app/pqc/checkList/list?text='+text+'"', 2000);
								}else{
									globalUtil.fn.alert("签到失败!",2.0);
								}
							});
				}
				/*if(data.type == "back"){
					window.location.href="${ctx}/app/manager/packageSettlementList";
				}*/
			}
		}

		if(isiOS){
			function setupWebViewJavascriptBridge(callback) {
				if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
				if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
				window.WVJBCallbacks = [callback];
				var WVJBIframe = document.createElement('iframe');
				WVJBIframe.style.display = 'none';
				WVJBIframe.src = 'https://__bridge_loaded__';
				document.documentElement.appendChild(WVJBIframe);
				setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0)
			}

			setupWebViewJavascriptBridge(function(bridge) {
				var uniqueId = 1
				function log(message, data) {
					var log = document.getElementById('log')
					var el = document.createElement('div')
					el.className = 'logLine'
					el.innerHTML = uniqueId++ + '. ' + message + ':<br/>' + JSON.stringify(data)
					if (log.children.length) { log.insertBefore(el, log.children[0]) }
					else { log.appendChild(el) }
				}

				bridge.registerHandler('locationResult', function(data, responseCallback) {
					if(data.type == "succ"){
						var signErrorDistance = getDistance($("#lat").val(),$("#lon").val(),data.latitude,data.longitude);
						var lon = data.longitude;
						var lat = data.latitude;
						var relatedBusinessId = $("#packId").val();
						var signAddress = data.address;
						var text = $(".sch_text").val();
						$.post("${ctx}/app/pqc/checkList/savepqcsign", {lat:lat,lon:lon,relatedBusinessId:relatedBusinessId,signErrorDistance:signErrorDistance,signAddress:signAddress},
								function(result){
							if(result == "success"){
								globalUtil.fn.alert("签到成功!",2.0);
								//setTimeout('window.location.href="${ctx }/app/pqc/checkList/list?text='+text+'"', 2000);
							}else{
								globalUtil.fn.alert("签到失败!",2.0);
							}
						});
					}
					/*if(data.type == "back"){
						window.location.href="${ctx}/app/manager/packageSettlementList";
					}*/
				})

				$(".signButton").each(function(){
					$(this).click(function(){
						var orderId = $(this).attr("orderId");
						var taskId = $(this).attr("taskId");
						var lon = $(this).attr("lon");
						var lat = $(this).attr("lat");
						$("#orderId").val(orderId);
						$("#packId").val(taskId);
						$("#lat").val(lat);
						$("#lon").val(lon);

						bridge.callHandler('userlocation', function(response) {
						})
					});
				});
			})
		}

		function toRad(d) {
			return d * Math.PI / 180;
		}
		function getDistance(lat1, lng1, lat2, lng2) {
			var dis = 0;
			var radLat1 = toRad(lat1);
			var radLat2 = toRad(lat2);
			var deltaLat = radLat1 - radLat2;
			var deltaLng = toRad(lng1) - toRad(lng2);
			var dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLng / 2), 2)));
			return dis * 6378137;
		}
		
	</script>
</body>
</html>
