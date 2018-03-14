<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>安装验收</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/apply.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/layout.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/public/style.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>

<body>
<div class="wrap">
	<header>
		<a class="back_btn" href="${ctx}/app/manager/orderInstallPlan/installAcceptance?orderId=${orderInstallPlan.orderId}"></a>
		<h2 class="title">安装验收</h2>
	</header>
	<!-- /header -->
	<form id="jvForm" action="" method="post" enctype="multipart/form-data">

		<input type="text" hidden="hidden" name="id" value="${orderInstallPlan.id}">
		<input type="text" hidden="hidden" name="orderId" value="${orderInstallPlan.orderId}">
		<input type="text" hidden="hidden" name="installMode" value="${orderInstallPlan.installMode}">
		<input type="text" hidden="hidden" name="isQualified" value="${isQualified}">

		<section class="pt112 bor_btm_e5">
			<!-- 基本信息：安装模式：产业 -->
			<c:if test="${orderInstallPlan.installMode eq 1}">
				<div class="sec">
					<p class="pb20">
						<span class="pl30 font30 col_blue">基本信息</span>
					</p>
					<ul class="item pad_top3 mar_btm0 clearfix">
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">顾客信息：</span>${engineInstall.communityName }-${engineInstall.buildNumber }-${engineInstall.buildUnit }-${engineInstall.buildRoom}-${engineInstall.customerName }</p>
						</li>
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">安装项名称：</span>${engineInstall.installItemName}</p>
						</li>
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">安装项状态：</span>${engineInstall.installstatus}-${engineInstall.installstatusName}</p>
						</li>
						<li class="mb30 rele clearfix posR">
							<p class="font30 col_6 flow"><span class="item-name">工人组长：</span>${engineInstall.empGroupName}-${engineInstall.empGroupPhone} <a href="tel:${engineInstall.empGroupPhone}" class="btn-tel">拨打电话</a></p>
						</li>
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">实际工期：</span>${engineInstall.supplierintodate }至${engineInstall.suppliercompletedate }</p>
						</li>
					</ul>
					<p class="item-view shadow"><a href="${ctx }/app/manager/orderInstallPlan/construction_pic?constructionId=${engineInstall.shigongId}" class="col_blue"><span>查看完工图 >></span></a></p>
				</div>
			</c:if>


			<!-- 产业合格 -->
			<c:if test="${orderInstallPlan.installMode eq 1 && isQualified eq 1}">
				<div class="sec">
					<p class="pad_top3 pb20">
						<span class="pl30 font30 col_blue">安装评定</span>
					</p>
					<ul class="sec item shadow pad_top3 clearfix">
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">验收日期：</span><fmt:formatDate value="${orderInstallPlan.realAcceptDate}" pattern="yyyy-MM-dd"/></p>
							<input type="text" hidden="hidden" name="realAcceptDateString" value="<fmt:formatDate value="${orderInstallPlan.realAcceptDate}" pattern="yyyy-MM-dd"/>">
						</li>
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">验收状态：</span><i class="icon icon-ok">icon</i>合格</p>
						</li>
						<li class="mb30 rele clearfix">
							<span class="item-name">是否延期：</span>
							<div class="radio-box">
								<span class="radio-span">
									<input type="radio" id="yes2" name="delay" value="1">
									<label data-name="delay" for="yes2" class="checked">是</label>
								</span>
								<span class="radio-span">
									<input type="radio" id="no2" name="delay" value="0">
									<label data-name="delay" for="no2" id="">否</label>
								</span>
								<input type="text" hidden="hidden" name="isCompleteDelay" id="isCompleteDelayCY">
							</div>
						</li>
						<li class="mb30 rele clearfix isCompleteDelayli">
							<p class="font30 col_6 flow">
								<span class="item-name">自装延期天数：</span>
								<input type="text" class="input-num delayDays" name="delayDays" onkeyup="checkNum(this)"
									   onafterpaste="checkNum(this)" value="" maxlength="3">天
							</p>
						</li>
						<li class="mb30 rele clearfix isCompleteDelayli">
							<p><span class="item-name">延期原因：</span>
								<select name="completeDelayReason"  class="selectRe font24 col_0780ec completeDelayReason">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('delay_reason')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</p>
						</li>
						<li class="mb30 rele clearfix isCompleteDelayli">
							<span class="item-name">延期说明：</span>
							<textarea class="instruc" name="completeDelayRemarks"></textarea>
						</li>
					</ul>
				</div>
			</c:if>


			<!-- 产业不合格 -->
			<c:if test="${orderInstallPlan.installMode eq 1 && isQualified eq 0}">
				<div class="sec">
					<p class="pad_top3 pb20">
						<span class="pl30 font30 col_blue">安装评定</span>
					</p>
					<ul class="sec item shadow pad_top3 clearfix">
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">验收日期：</span><fmt:formatDate value="${orderInstallPlan.realAcceptDate}" pattern="yyyy-MM-dd"/></p>
							<input type="text" hidden="hidden" name="realAcceptDateString" value="<fmt:formatDate value="${orderInstallPlan.realAcceptDate}" pattern="yyyy-MM-dd"/>">
						</li>
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">验收状态：</span><i class="icon icon-fail">icon</i>不合格</p>
						</li>
						<li class="mb30 rele clearfix">
							<p><span class="item-name">不合格原因：</span>
								<select name="unqualifiedReasonId" class="selectRe font24 col_0780ec unqualifiedReasonId">
									<c:forEach items="${unReasonList}" var="reason">
										<option value="${reason.id}">${reason.unqualifiedReason}</option>
									</c:forEach>
								</select>
							</p>
						</li>
						<li class="mb30 rele clearfix">
							<span class="item-name">备注：</span>
							<textarea class="instruc" name="unqualifiedRemarks"></textarea>
						</li>
					</ul>
				</div>
			</c:if>


			<!-- 传统合格 -->
			<c:if test="${orderInstallPlan.installMode eq 2 && isQualified eq 1}">
				<div class="sec">
					<ul class="item pad_top3 bg-grey border0 mar_btm0 clearfix">
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">安装项：</span>${orderInstallPlan.installItemName}</p>
						</li>
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">验收状态：</span><i class="icon icon-ok">icon</i>合格</p>
						</li>
					</ul>
					<ul class="item pad_top3 shadow clearfix">
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">验收日期：</span><fmt:formatDate value="${orderInstallPlan.realAcceptDate}" pattern="yyyy-MM-dd"/></p>
							<input type="text" hidden="hidden" name="realAcceptDateString" value="<fmt:formatDate value="${orderInstallPlan.realAcceptDate}" pattern="yyyy-MM-dd"/>">
						</li>
						<li class="mb30 clearfix">
							<span class="item-name">实际进场日期：</span>
							<input class="date date1" type="text" id="realIntoDate" name="realIntoDateString" readonly="" value="" placeholder="2016-08-01" data-lcalendar="">
						</li>
						<li class="mb30 clearfix">
							<span class="item-name">实际完工日期：</span>
							<input class="date date2" type="text" id="realCompleteDate" name="realCompleteDateString"  readonly="" value="" placeholder="2016-08-01" data-lcalendar="" onchange="checkDelayDays()">
						</li>
					</ul>
					<ul class="item pad_top3 shadow clearfix">
						<li class="mb30 rele clearfix">
							<p class="font30 col_blue flow"><span class="item-name">计划完工日期：</span><fmt:formatDate value="${orderInstallPlan.planCompleteDate}" pattern="yyyy-MM-dd"/></p>
							<input type="text" hidden="hidden" id="planCompleteDate" value="<fmt:formatDate value="${orderInstallPlan.planCompleteDate}" pattern="yyyy-MM-dd"/>">
						</li>
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">自装延期天数：</span><span class="delayDaysSpan"></span>天</p>
							<input type="text" hidden="hidden" name="delayDays" class="delayDays">
							<input type="text" hidden="hidden" name="isCompleteDelay" id="isCompleteDelay">
						</li>
						<li class="mb30 rele clearfix">
							<p><span class="item-name">延期原因：</span>
								<select name="completeDelayReason"  class="selectRe font24 col_0780ec completeDelayReason">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('delay_reason')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</p>
						</li>
						<li class="mb30 rele clearfix">
							<span class="item-name">延期说明 ：</span>
							<textarea class="instruc" name="completeDelayRemarks"></textarea>
						</li>
					</ul>
				</div>
			</c:if>


			<!-- 传统不合格 -->
			<c:if test="${orderInstallPlan.installMode eq 2 && isQualified eq 0}">
				<div class="sec">
					<ul class="item pad_top3 shadow clearfix">
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">安装项名称：</span>${orderInstallPlan.installItemName}</p>
						</li>
						<li class="mb30 rele clearfix">
							<p class="font30 col_6 flow"><span class="item-name">验收状态：</span><i class="icon icon-fail">icon</i>不合格</p>
							<input type="text" hidden="hidden" name="realAcceptDateString" value="<fmt:formatDate value="${orderInstallPlan.realAcceptDate}" pattern="yyyy-MM-dd"/>">
						</li>
						<li class="mb30 rele clearfix">
							<p><span class="item-name">不合格原因：</span>
								<select name="unqualifiedReasonId" class="selectRe font24 col_0780ec unqualifiedReasonId">
									<c:forEach items="${unReasonList}" var="reason">
										<option value="${reason.id}">${reason.unqualifiedReason}</option>
									</c:forEach>
								</select>
							</p>
						</li>
						<li class="mb30 rele clearfix">
							<span class="item-name">备注：</span>
							<textarea class="instruc" name="unqualifiedRemarks"></textarea>
						</li>
					</ul>
				</div>
			</c:if>

		</section>
		<!-- /section -->
		<section>
			<p class="pad_top3 pb20">
				<span class="pl30 font28 col_blue">安装验收单+现场施工图（至少3张）:</span>
			</p>
			<div class="pics bor_top_ddd font0 shadow bg_f clearfix">
				<%--<div class="pic">
					<img src="${ctxStatic}/mobile/modules/manager/img/common/eg.png" alt="">
					<a class="delBtn" href="javascript:void(0)"></a>
				</div>--%>
				<div class="pic camera" id="camera" href="javascript:void(0)">
					<img src="${ctxStatic}/mobile/modules/Manager/img/common/upPic.png" alt="">
					<input type="file" accept="image/*" onchange="preview(this)">
				</div>
				<input type="text" hidden="hidden" id="shit"  value="1">
				<input type="text" hidden="hidden" id="num" name="num" value="">

			</div>
		</section>
	</form>
	<!-- /section -->
	<div class="pt44">
		<a class="subBtn" href="javascript:;">提交申请</a>
	</div>
	<div style="padding-bottom:300px;"></div>
</div>



<!-- /.wrap -->
<!--公共弹层背景  class 「_in」 显示弹层背景-->
<div class="alertMask" id="alertMask">
</div>
<!-- /.alertMask -->
<!--公共弹层  class 「_in」 显示弹层内容-->
<div class="maskWrapper" id="maskWrapperOne">
	<div class="maskTit">提 示</div>
	<div class="maskContent">
		<p id="alertRemarks">
		</p>
	</div>
	<div class="maskBtns">
		<button class="maskBtnOne" type="button" onclick="maskOneSure()">确 定</button>
	</div>
</div>

<div class="maskWrapper" id="maskWrapperTwo">
	<div class="maskTit">提 示</div>
	<div class="maskContent">
		<p class="text-center">您确认做
			<span class="text-red">
				<c:if test="${isQualified eq 1}">【合格】</c:if>
				<c:if test="${isQualified eq 0}">【不合格】</c:if>
			</span>验收提交吗？
		</p>
	</div>
	<div class="maskBtns twoBtns clearfix">
		<button class="maskBtn" type="button" onclick="maskTwoSure()">确定</button>
		<button class="maskBtn" type="button" onclick="maskTwoCancel()">取消</button>
	</div>
</div>

<div class="maskWrapper" id="maskWrapperThree">
	<div class="maskTit">提 示</div>
	<div class="maskContent">
		<p>
			<c:if test="${isQualified eq 1}">该安装项【合格】验收成功</c:if>
			<c:if test="${isQualified eq 0}">该安装项【不合格】验收成功</c:if>
		</p>
	</div>
	<div class="maskBtns">
		<button class="maskBtnOne" type="button" onclick="maskThreeSure()">确 定</button>
	</div>
</div>

<!-- /.maskWrapper -->
<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/mobiscroll.custom-2.16.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
<script>

    function run_waitMe(text){
        $('body').waitMe({
            effect: 'win8',
            text: text,
            bg: 'rgba(255,255,255,0.7)',
            color:'#000',
            sizeW:'',
            sizeH:'',
            source: 'img.svg'
        });
    }

    $(document).ready(function() {
        //绑定onclick事件
        $(".subBtn").bind('click',cartSubmit);
    });

    $('.radio-span label').click(function() {
        var thisName = $(this).attr('data-name');
        $('label[data-name="' + thisName + '"]').removeAttr('class') && $(this).attr('class', 'checked');
        var isCompleteDelay = $('label[data-name="delay"][class="checked"]').prev().val();
        if(isCompleteDelay == "1"){
            $(".isCompleteDelayli").removeClass("undis");
        }else{
            $(".isCompleteDelayli").addClass("undis");
		}
    });

    $(".date").mobiscroll().date({
        theme: "ios",
        lang: "zh",
        labels: ["年", "月", "日"],
        dateFormat: "yyyy-mm-dd",
        showLabel: "true",
        endYear: "2030",
        startYear: "2015"
    })

    var now = new Date();
    var currYear = now.getFullYear();
    var currMonth = now.getMonth() + 1;
    var currDay = now.getDate();
    $("#realIntoDate").val(globalUtil.fn.formatDate(new Date(currYear, currMonth - 1, currDay),'yyyy-MM-dd'));
    $("#realCompleteDate").val(globalUtil.fn.formatDate(new Date(currYear, currMonth - 1, currDay),'yyyy-MM-dd'));

    var orderId = '${orderInstallPlan.orderId}';
    var installMode = '${orderInstallPlan.installMode}';
    var isQualified = '${isQualified}';

    //--------------------------------传统合格 得出延期天数 start--------------------------------------
    checkDelayDays();
    // 传统合格  得出延期天数 （自装延期天数=实际完工日期-计划完工日期）
    function checkDelayDays() {
		if(installMode == "2" && isQualified == "1"){
            var realCompleteDate = $("#realCompleteDate").val();
            var planCompleteDate = $("#planCompleteDate").val();
            var delayDays = 0;
            var isCompleteDelay = 0;
            if(realCompleteDate > planCompleteDate){
                delayDays = DateDiff(realCompleteDate,planCompleteDate);
                isCompleteDelay = 1;
            }
            $("#isCompleteDelay").val(isCompleteDelay);
            $(".delayDays").val(delayDays);
            $(".delayDaysSpan").text(delayDays);
        }
    }

    //计算天数差的函数，通用
    function  DateDiff(startDate,endDate){
        var startTime = new Date(Date.parse(startDate.replace(/-/g,   "/"))).getTime();
        var endTime = new Date(Date.parse(endDate.replace(/-/g,   "/"))).getTime();
        var dates = Math.abs((startTime - endTime))/(1000*60*60*24);
        return  dates;
    }

    //只能输入数字的校验
    function checkNum(obj){
        if(obj.value.length == 1){
            obj.value=obj.value.replace(/[^1-9]/g,'')
		}else{
            obj.value=obj.value.replace(/\D/g,'')
		}
	}
    //--------------------------------传统合格 得出延期天数 end--------------------------------------

    //--------------------------------图片上传显示 start--------------------------------------
    function preview(file) {
        var num = $("#shit").val();
        var prevDiv = $('.camera');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function(evt){
                $('<div class="pic"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic);" id= "'+num+'"/><a class="delBtn" href="javascript:void(0)">删除</a></div>').insertBefore('.camera');
            }
            reader.readAsDataURL(file.files[0]);
        }else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            console.log(file,file.value);
        }
    }
    $(document).on('click', '.delBtn', function(){
        var numReal=$(this).prev().attr("id");
        $(("#num"+numReal)).remove();
        var num = $("#num").val();
        num--;
        $("#num").val(num);
        $(this).parent().remove();
    });

    function uploadpic(pic){

        var hiddenForm = document.getElementById("jvForm");
        var input =document.createElement("input");
        var num = $("#num").val();
        var shit =	$("#shit").val();
        if(pic){
            num++;
            input.setAttribute("id","num"+shit);
            input.setAttribute('hidden', 'hidden');
            input.setAttribute('name', 'photo');
            input.setAttribute("type","text");
            input.setAttribute("value", pic);
            hiddenForm.appendChild(input);
            shit++;
            $("#num").val(num);
            $("#shit").val(shit);
        }
    }

    //--------------------------------图片上传显示 end--------------------------------------

    //--------------------------------提示弹框  start--------------------------------------
    function maskOneSure() {
        $("#alertMask").removeClass('_in');
        $("#maskWrapperOne").removeClass('_in');
    }
    function maskTwoCancel() {
        $("#alertMask").removeClass('_in');
        $("#maskWrapperTwo").removeClass('_in');
    }
    function maskThreeSure(){
        window.location.href = "${ctx }/app/manager/orderInstallPlan/installAcceptance?orderId="+orderId;
	}
    //-------------------------------- 提示弹框  end --------------------------------------

	//-------------------------------- 确认验收  数据校验  start --------------------------------------
	function cartSubmit() {


        //合格
        if(isQualified == "1"){
            //产业合格
            if(installMode == "1"){
                var isCompleteDelay = $('label[data-name="delay"][class="checked"]').prev().val();
                $("#isCompleteDelayCY").val(isCompleteDelay);
                if(isCompleteDelay == "1"){
                    var delayDays = $(".delayDays").val();
                    if(delayDays == ''|| parseInt(delayDays) <= 0){
                        $("#alertRemarks").text("请填写延期天数！")
                        $("#alertMask").addClass('_in');
                        $("#maskWrapperOne").addClass('_in');
                        return false;
                    }
                    var completeDelayReason = $(".completeDelayReason").val();
                    if(completeDelayReason == ''){
                        $("#alertRemarks").text("请选择延期原因！")
                        $("#alertMask").addClass('_in');
                        $("#maskWrapperOne").addClass('_in');
                        return false;
                    }
                }
            }else{
                //传统合格
                var delayDays = $(".delayDays").val();
                if(parseInt(delayDays) > 0){
                    var completeDelayReason = $(".completeDelayReason").val();
                    if(completeDelayReason == ''){
                        $("#alertRemarks").text("请选择延期原因！")
                        $("#alertMask").addClass('_in');
                        $("#maskWrapperOne").addClass('_in');
                        return false;
                    }
                }
            }
        }else{
            //不合格
            var unqualifiedReasonId = $(".unqualifiedReasonId").val();
            if( undefined == unqualifiedReasonId || unqualifiedReasonId == ''){
                $("#alertRemarks").text("请选择验收不合格原因！")
                $("#alertMask").addClass('_in');
                $("#maskWrapperOne").addClass('_in');
                return false;
            }
        }

        //图片
		var num = $("#num").val();
        if(num < 3){
            $("#alertRemarks").text("请选择上传最少三张验收图片！")
            $("#alertMask").addClass('_in');
            $("#maskWrapperOne").addClass('_in');
            return false;
        }
        $("#alertMask").addClass('_in');
        $("#maskWrapperTwo").addClass('_in');

    }
    //--------------------------------确认验收  数据校验  end--------------------------------------


    //--------------------------------提交表单  start--------------------------------------
    function maskTwoSure(){
		//防止重复提交
        run_waitMe('正在提交数据,请稍等');
        $(".subBtn").css("color","#CCC");
        $(".subBtn").unbind("click");

        //上报提交表单
        var options ={
            url : "${ctx}/app/manager/orderInstallPlan/acceptance_submit",
            type: "post",
            success : function(data){
                $('body').waitMe('hide');
                $("#alertMask").removeClass('_in');
                $("#maskWrapperTwo").removeClass('_in');
                if(null!=data && data=="0"){
                    $("#alertMask").addClass('_in');
                    $("#maskWrapperThree").addClass('_in');
                }else if(data=="1"){
                    $("#alertRemarks").text("该安装项是否已经验收【合格】！")
                    $("#alertMask").addClass('_in');
                    $("#maskWrapperOne").addClass('_in');
                }else if(data=="2"){
                    $("#alertRemarks").text("您刚刚提交了该安装项的验收结果，请耐心等待五分钟后再次操作！")
                    $("#alertMask").addClass('_in');
                    $("#maskWrapperOne").addClass('_in');
                }else{
                    $("#alertRemarks").text("安装验收失败！")
                    $("#alertMask").addClass('_in');
                    $("#maskWrapperOne").addClass('_in');
                }

            }
        }
        //ajax提交form
        $("#jvForm").ajaxSubmit(options);

	}
    //--------------------------------提交表单  end--------------------------------------



</script>
</body>

</html>