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
	<title>返单记录</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/search.css"/>
	<script type="text/javascript">
        var formatStatus = function(status){
            switch(status){
                case '10':
                    return "已上报返单信息";
                    break;
                case '30':
                    return "客户已进店未签单";
                    break;
                case '40':
                    return "客户已进店已签单";
                    break;
                case '50':
                    return "客户已签单";
                    break;
                case '90':
                    return "返单已失效";
                    break;
            }
        }
        var formatDate = function(time, format){
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

        function ajaxSearch(){
            var text = $("#search").val();
            $("#record").html("");
            $.ajax({
                url: "${ctx}/app/worker/orderReport/queryOrderReportByParam?searchText="+text,
                type : "post",
                success: function (data){
                    if(null!=data){
                        for(var i = 0;i<data.length;i++){
                            $("#record").append("<div class='sec font0 border_top border_btm mar_btm24 bg_f clearfix'><ul class='pad_top3 pad_left3'>"
                                +"<li class='mar_btm24 clearfix'>"
                                +"<span class='col_grey font28 flo_left'>客户姓名：</span>"
                                +"<p class='font28 col_3 pad_rgt3 flow'>"+data[i].customerName+"</p>"+
                                +"</li>"
                                +"<li class='mar_btm24 clearfix'>"
                                +"<span class='col_grey font28 flo_left'>手 机 号：</span>"
                                +"<p class='font28 col_3 pad_rgt3 flow'>"+data[i].customerPhone+"</p>"
                                +"</li><li class='mar_btm24 clearfix'>"
                                +"<span class='col_grey font28 flo_left'>当前状态：</span>"
                                +"<p class='font28 col_3 pad_rgt3 flow'>"+formatStatus(data[i].reportStatus)+"</p>"
                                +"</li>"
                                +"<li class='mar_btm24 clearfix'>"
                                +"<span class='col_grey font28 flo_left'>上报日期：</span>"
                                +"<p class='font28 col_3 pad_rgt3 flow'>"+formatDate(data[i].reportDatetime,"yyyy-MM-dd")+"</p>"
                                +"</li>"
                                +"</ul></div>");
                        }
                    }
                }
            });
        }
	</script>
</head>
<body>
	<div class="">
		<header>
			<c:if test="${sta=='0'}">
				<a class="back_btn" href="${ctx}/app/worker/myindex"></a>
			</c:if>
			<c:if test="${sta=='1'}">
				<a class="back_btn" href="${ctx}/app/worker/myindex1"></a>
			</c:if>
			<h2 class="title">返单记录</h2>
		</header><!-- /header -->
		<section class="pad_top11">
			<div class="mar_btm14 font0 search-area">
				<input id="search" class="search-box" type="text" placeholder="客户姓名、客户手机号">
				<a class="search-btn" href="javascript:;"  onclick="ajaxSearch()"></a>
			</div>
			<div id="record">
				<c:if test="${ not empty list }">
					<c:forEach items="${list }" var="orderReport">
						<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
							<ul class="pad_top3 pad_left3">
								<li class="mar_btm24 clearfix">
									<span class="col_grey font28 flo_left">客户姓名：</span>
									<p class="font28 col_3 pad_rgt3 flow">${orderReport.customerName}</p>
								</li>
								<li class="mar_btm24 clearfix">
									<span class="col_grey font28 flo_left">手 机 号：</span>
									<p class="font28 col_3 pad_rgt3 flow">${orderReport.customerPhone}</p>
								</li>
								<li class="mar_btm24 clearfix">
									<span class="col_grey font28 flo_left">当前状态：</span>
									<p class="font28 col_3 pad_rgt3 flow">${fns:getDictLabel(orderReport.reportStatus, 'reportStatus', '')}</p>
								</li>
								<li class="mar_btm24 clearfix">
									<span class="col_grey font28 flo_left">上报日期：</span>
									<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${orderReport.reportDatetime}" pattern="yyyy-MM-dd"/></p>
								</li>
							</ul>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
</html>