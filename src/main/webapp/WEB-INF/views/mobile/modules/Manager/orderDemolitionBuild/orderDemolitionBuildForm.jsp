<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>拆改交底</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/mask.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/applyDoneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/delay/delayApply.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/ProblemReport/ProblemReport.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/delay/delayList.css"/>
	<style type="text/css">
		.del_pic {
		    position: absolute;
		    z-index:2008;
		    top: -5px;
		    right: -5px;
		    width: 24px;
		    height: 25px;
		    background: url(${ctxStatic}/mobile/modules/pqc/images/del_pic.png);
		    background-size: 24px 25px;
		}
	</style>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${root}/mobile/modules/manager/orderDemolitionBuild/web/orderDemolitionBuild/list"></a>
			<h2 class="title">拆改交底</h2>
		</header><!-- /header -->
		<section class="pt112 shadow mb24">
			<div class="pl30 pr30 pt30 pb28 bor_top_ddd bor_dashed rela bg_f">
				<span class="font28 col_6 fl">客户信息：</span>
				<p class="locate font30 col_3 wid60per flow">${signDetail.customerAddress }</p>
				<!-- <a class="chooseBtn" href="javascript:;">请选择</a> -->
			</div>
			<form id ="jvForm" action="${root}/mobile/modules/manager/orderDemolitionBuild/web/orderDemolitionBuild/save" method="post" enctype="multipart/form-data">
			<input style="display: none;" value = "${signDetail.orderId }" name = "orderId" id = "orderId">
			<input style="display: none;" value = "${signDetail.managerId }" name = "demolitionBuildEmployeeId">
			<div class="sec pl30 pr30 font0 border_btm bg_f clearfix">
				<ul class="pad_top3">
					<li class="mb30 clearfix">
						<span class="col_6 font28" >交底日期：</span>
						<input name="demolitionBuildDate" class="date date1" type="text" id = "end" readonly="" value="<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd"/>" placeholder="请输入日期" data-lcalendar="" onchange="tip()">
					</li>
					<li class="mb30 clearfix">
						<span class="col_6 font28">签到时间：</span>
						<input name="delayEndDatetime" class="date1" id="begin" type="text" readonly="readonly" value="<fmt:formatDate value="${signDetail.signDate }" pattern="yyyy-MM-dd"/>">
					</li>
					<li class="mb30 rele clearfix">
						<span class="col_6 font28 fl">交底延期：</span>
						<p class="font30 col_3 wid60per flow" id = "myspan"></p>
						<input style="display: none;" id = "myinput" name = "delayDays">
					</li>
				</ul>
			</div>
		</section>
		<section>
			<div class="bg_f bor_bot_1">
					<div class="intro font0 pad_left3">
						<span class="font30 col_3">上传图片</span>
					</div>
					<div class="pics font0 clearfix">
						<div class="pic camera" id="camera" href="javascript:void(0)">
							<img src="${ctxStatic}/mobile/modules/Manager/images/add_click.png" alt="调取摄像头">
							<input type="file" accept="image/*" capture="camera" onchange="preview(this)">
						</div>
						<input type="text" hidden="hidden" id="shit" value="1">
						<input type="text" hidden="hidden" id="num" value="">
					</div>
				</div>
		</section>
		<div class="pt80">
			<a class="subBtn" onclick="submitform()">提交</a>
		</div>
		</form>
		<div style="padding-bottom:300px;"></div>
		
		
		<div class="g-mask undis" id = "alertMasktip">
			<div class="alertMask">
				<div class="maskWrapper">
					<p class="col_3 maskTit">通知</p>
					<div class="font28 col_6 maskContent align_ctr" >
					<span id = "tiptext">拆改交底成功！</span>
					</div>
					<div class="maskBtns clearfix">
						<a class="maskBtn font33 col_f" onclick="closewin()">我知道了</a>
					</div>
				</div>
			</div>
		</div>
		
		
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/mobiscroll.custom-2.16.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/select_choice.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/mobiscroll.custom-2.16.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	
	<script>
	
	$(document).on('click', '.del_pic', function(){
		var numReal = $(this).prev().attr("id");
		$(("#num"+numReal)).remove();
		
		var num = $("#num").val();
		num--;
		$("#num").val(num);
		$(this).parent().remove();
		globalUtil.fn.alert('删除图片操作成功',2.0);
	});
	$(function(){
		tip();
	});
		function submitform(){
			$("#jvForm").submit();
			/* $("#alertMasktip").show(); */
			
		}

	
        $(".date").mobiscroll().date({
            theme: "ios",
            lang: "zh",
            labels: ["年", "月", "日"],
            dateFormat: "yyyy-mm-dd",
            showLabel: "true",
            endYear: "2030",
            startYear: "2015"
        })
        $(document).on('click', '.delBtn', function(){
			$(this).parent().remove();
		});
		$('.radio_span label').click(function(){
		    var thisName = $(this).attr('data-name');
		    $('label[data-name="'+thisName+'"]').removeAttr('class') && $(this).attr('class', 'checked');
		});
		$(document).on('click', '.chooseBtn', function(){
			$('#chooseMask').removeClass('undis');
		});
		$(document).on('click', '.btmContainer li', function(){
			$('.btmContainer li').removeClass('col_0780ec').addClass('col_a8a8a8');
			$(this).removeClass('col_a8a8a8').addClass('col_0780ec');
			$('.locate').text($(this).text());
			$('#chooseMask').addClass('undis');
		});
		
		function tip(){
			var begin = $("#begin").val()
			var end = $("#end").val()
			if(begin ==null || begin=="" ||end == null || end==""){
				return false;
			}
			var begintime_ms = Date.parse(new Date(begin.replace(/-/g, "/")));
			var endtime_ms = Date.parse(new Date(end.replace(/-/g, "/")));
			var date3=endtime_ms - begintime_ms;
			var days=Math.floor(date3/(24*3600*1000))
			if(days < 0){
				$("#datetime").show();
				$("#myspan").text(null)
				$("#myinput").val(null)
				return false;
			}else{
				$("#myspan").text(null)
				$("#myspan").text(days+"天")
				$("#myinput").val(days);
				return true;
			}
		}
		
		function closewin(){
			window.location.href="${root}/mobile/modules/manager/orderDemolitionBuild/web/orderDemolitionBuild/list";
			 $("#datetime").hide();
			 $("#alertMasktip").hide();
			 
		}
		
    </script>
</body>
</html>