<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>上报复尺</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/review.css" />
</head>

<body>
	<div class="g-review">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/progressList"></a>
			<h2 class="title">上报复尺</h2>
		</header>
		<section class="pad_top10 mar_btm50">
		<ul class="clearfix shadow bg_f radius1 mar_left2 mar_rgt2 mar_btm2 pad_left2 pad_rgt2 font0">
			<c:forEach items="${orderList}" var="orderList" varStatus="status">
				<li class="line_hgt clearfix"><span
					class="font28 col_6 flo_left">顾客信息：</span>
					<p class="font28 col_3 flow">${orderList.communityName }-${orderList.buildNumber }-${orderList.buildUnit }-${orderList.buildRoom }-${orderList.customerName }</p>
				</li>
				<li class="line_hgt clearfix">
					<span class="font28 col_6 flo_left">合同开工：</span>
					<p class="font28 col_3 flow"><fmt:formatDate type="date" value="${orderList.contractStartDate }"/></p></li>
				<li class="line_hgt clearfix">
					<span class="font28 col_6 flo_left">订单状态：</span>
					<p class="font28 col_3 flow">施工中</p></li>
				<li class="line_hgt clearfix">
					<span class="font28 col_6 flo_left">复尺内容：</span>
					<div class="select_total">
						<div class="select-area">
							<div class="select-value">
								<input type="hidden" id="selected${status.index }" value=""/>
								<span class="font24 col_blue mar_left1"></span>
								<i class="select-btn"></i>
							</div>
							<div class="options undis" id="pop">
								<c:forEach items="${dictList }" var="dict">
									<span id="${dict.value }" name="${status.index }">${dict.label }</span>
								</c:forEach>
							</div>
						</div>
					</div></li>
				<div class="btns clearfix">
					<a href="javascript:void(0)" onclick="complex(${orderList.id},${status.index })" class="btn">去复尺</a>
					<a href="${ctx}/app/manager/recheckRecord?orderID=${orderList.id}" class="btn">复尺记录</a>
				</div>
			</c:forEach>
		</ul>
		</section>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script>
		window.onload = function(){
			$('.select-value span').text(
				$(this).parent().parent().find('#pop span:first-child').text()
			);
		}
		//alert($('#pop span:first-child').text());
		/*样式JS**/
		$(document).on('click','.select-value',function(){
			$(this).parent().find('.options').toggle();
		});
		$(document).on('click','.options span',function(){
			var index = $(this).attr("name");
			$("#selected"+index).val($(this).attr("id"));
			$(this).parent().parent().find('.select-value span').html($(this).html());
			$(this).parent().css({'display':'none'});
		});
		/*样式JS**/
		
		/**去复尺**/
		function complex(id,index){
			var selected = $("#selected"+index).val();
			if(selected == ''){
				globalUtil.fn.alert("请选择一个复尺类型...",1.0);
				return false;
			}
			//alert(subKey);
			window.location.href = "${ctx}/app/manager/checkType?recheckType="+selected+"&orderID="+id;
		}
	</script>
</body>
</html>