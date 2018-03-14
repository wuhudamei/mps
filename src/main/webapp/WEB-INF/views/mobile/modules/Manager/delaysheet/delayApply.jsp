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
	<title>项目延期申请</title>
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
	
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/delaysheet/list3"></a>
			<h2 class="title">项目延期申请</h2>
		</header><!-- /header -->
		<section class="pt112 shadow mb24">
			<div class="pl30 pr30 pt30 pb28 bor_top_ddd bor_dashed rela bg_f">
				<span class="font28 col_6 fl">客户信息：</span>
				<p class="locate font30 col_3 wid60per flow">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
				<!-- <a class="chooseBtn" href="javascript:;">请选择</a> -->
			</div>
			<form id ="jvForm" action="${ctx}/app/manager/delaysheet/save" method="post" enctype="multipart/form-data">
			<input style="display: none;" value = "${order.id }" name = "orderId" id = "orderId">
			<div class="sec pl30 pr30 font0 border_btm bg_f clearfix">
				<ul class="pad_top3">
					<li class="mb30 rele clearfix">
						<span class="col_6 font28">延期阶段：</span>
						<select class="selectRe font24 col_0780ec" name = "delayBillStageStatus" id = "delayBillStageStatus">
							<option value=""></option>
							<c:forEach items="${list2 }" var = "dict">
								<option value="${dict.value }">${dict.label }</option>
							</c:forEach>
						</select>
					</li>
					<li class="mb30 clearfix">
						<span class="col_6 font28">延期类别：</span>
						<select class="selectRe font24 col_0780ec" onchange="ajaxDelayreson(this)" name="delayBillCategoryId" id="delayBillCategoryId">
						<option value=" "></option>
							<c:forEach items="${list }" var = "dict">
								<option value="${dict.value }">${dict.label }</option>
							</c:forEach>
						</select>
						
					</li>
					<li class="mb30 clearfix">
						<span class="col_6 font28">延期原因：</span>
						<select class="selectRe font24 col_0780ec" id = "reason" name="delayBillCategoryIdReson">
						</select>
						<input style="display: none;" id = "myinputreason" value="${delaySheet.delayBillCategoryIdReson}">
					</li>
					<li class="mb30 clearfix">
						<span class="col_6 font28" >延期开始：</span>
						<input name="delayBeginDatetime" class="date date1" type="text" id = "begin" readonly="" value="${delaySheet.delayBeginDatetime }" placeholder="请输入日期" data-lcalendar="" onchange="tip()">
					</li>
					<li class="mb30 clearfix">
						<span class="col_6 font28">延期结束：</span>
						<input name="delayEndDatetime" class="date date1" id="end" type="text" readonly="" value="${delaySheet.delayEndDatetime }" placeholder="请输入日期" data-lcalendar="" onchange="tip()">
					</li>
					<li class="mb30 rele clearfix">
						<span class="col_6 font28 fl">延期天数：</span>
						<p class="font30 col_3 wid60per flow" id = "myspan">${delaySheet.delayDays }天</p>
						<input style="display: none;" id = "myinput" name = "delayDays">
					</li>
					<li class="mb30 rele clearfix">
						<span class="col_6 font28 ">延期说明：</span>
						<textarea class="instruc" name="deferredInstruction" id="deferredInstruction" >${delaySheet.deferredInstruction }</textarea>
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
		<c:if test="${!empty flag }">
		<div class="tips">
			<span class="tipTit">拒绝原因：</span>
			<span class="tipContent">${delaySheet.remarks }</span>
		</div></c:if>
		<div class="pt80">
			<a class="subBtn" onclick="submitform()">提交</a>
		</div>
		</form>
		<div style="padding-bottom:300px;"></div>
		<div class="g-mask undis" id = "alertMasksu">
			<div class="alertMask">
				<div class="maskWrapper">
					<p class="col_3 maskTit">通知</p>
					<div class="font28 col_6 maskContent align_ctr">提交成功，请耐心等待。</div>
					<div class="maskBtns clearfix">
						<a class="maskBtn font33 col_f" href="javascript:;">我知道了</a>
					</div>
				</div>
			</div>
		</div>
		<div class="g-mask undis" id = "alertMasktip">
			<div class="alertMask">
				<div class="maskWrapper">
					<p class="col_3 maskTit">通知</p>
					<div class="font28 col_6 maskContent align_ctr" >
					<span id = "tiptext"></span>
					</div>
					<div class="maskBtns clearfix">
						<a class="maskBtn font33 col_f" onclick="closewin()">我知道了</a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="g-mask undis" id = "datetime">
			<div class="alertMask">
				<div class="maskWrapper">
					<p class="col_3 maskTit">通知</p>
					<div class="font28 col_6 maskContent align_ctr">延期开始时间不能大于延期结束时间</div>
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
	
	$(document).ready(function() {
		var v1 = "${delaySheet.delayBillStageStatus}";
		var v3 = "${delaySheet.delayBillCategoryIdReson}";
		var v31 = $("#myinputreason");
		var v2 = "${delaySheet.delayBillCategoryId}";
		$("#delayBillStageStatus option").each(function(){
			var temp = $(this).val();
			if(temp == v1){
				$(this).attr("selected","selected");
			}
			
		});
		$("#delayBillCategoryId option").each(function(){
			var temp = $(this).val();
			if(temp == v3){
				$(this).attr("selected","selected");
			}
			
		});
		ajaxDelayreson12(v31);
	});
	
	
	function submitform(){
		var v1 = $("#delayBillStageStatus").val();
		var v2 = $("#reason").val();
		var v3 = $("#begin").val();
		var v4 = $("#end").val();
		var v5 = $("#deferredInstruction").val();
		var v6 = $("#num1").val();
		if(v1 == null || v1 == ""){
			$("#tiptext").text("请选择延期阶段!")
			$("#alertMasktip").show();
			return false;
		}
		if(v2 == null || v2 == ""){
			$("#tiptext").text("请选择延期类型!")
			$("#alertMasktip").show();
			return false;
		}
		if(v3 == null || v3 == ""){
			$("#tiptext").text("请选择延期开始时间!")
			$("#alertMasktip").show();
			return false;
		}
		if(v4 == null || v4 == ""){
			$("#tiptext").text("请选择延期结束时间!")
			$("#alertMasktip").show();
			return false;
		}
		if(v5 == null || v5 == ""){
			$("#tiptext").text("请填写延期说明!")
			$("#alertMasktip").show();
			return false;
		}
		 if(v6 == null || v6 == ""){
			$("#tiptext").text("请上传照片!")
			$("#alertMasktip").show();
			return false;
		} 
		/* var v7 = $("#num3").val();
		if(v7 == null || v7 ==""){
			$("#tiptext").text("最少上传三张图片!")
			$("#alertMasktip").show();
			return false;
		} */
		if(tip()){
			 isSubmit();
		}
		
	}
	
	function ajaxDelayreson(a){
		$.ajax({
			url:"${ctx}/app/manager/delaysheet/ajaxreson",
			type:"post",
			data:{id:$(a).val()},
			dataType:"json",
			success:function(data){
				var s = "";
				$.each(data,function(name,value) {
					s += "<option value="+value.value+">"+value.label+"</option>"
					});
				$("#reason").html(s);
			}
		});
	}
	
	
	function ajaxDelayreson12(a){
		var v2 = "${delaySheet.delayBillCategoryId}";
		$.ajax({
			url:"${ctx}/app/manager/delaysheet/ajaxreson",
			type:"post",
			data:{id:$(a).val()},
			dataType:"json",
			success:function(data){
				var s = "";
				$.each(data,function(name,value) {
					s += "<option value="+value.value+">"+value.label+"</option>"
					});
				$("#reason").html(s);
				$("#reason option").each(function(){
					var temp = $(this).val();
					if(temp == v2){
						$(this).attr("selected","selected");
					}
				});
			}
		});
	}
	
	function isSubmit(){
		var v1 = $("#delayBillStageStatus").val();
		var orderId = $("#orderId").val();
		$.ajax({
			url:"${ctx}/app/manager/delaysheet/isSubmit",
			type:"post",
			data:{orderId:orderId,stageStatus:v1},
			dataType:"text",
			success:function(data){
				var temp = data;
				if(temp == 1){
					$("#jvForm").submit();
					$("#alertMasksu").show()
				}else if(temp == 2){
					$("#tiptext").text("待审核中，不可以提交!")
					$("#alertMasktip").show();
				}else if (temp == 3){
					$("#tiptext").text("该阶段已审核通过不可以重复提交！")
					$("#alertMasktip").show();
				}
			}
		});
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
				days++;
				$("#myspan").text(null)
				$("#myspan").text(days+"天")
				$("#myinput").val(days);
				return true;
			}
		}
		
		function closewin(){
			 $("#datetime").hide();
			 $("#alertMasktip").hide();
			 
		}
		
    </script>
</body>
</html>