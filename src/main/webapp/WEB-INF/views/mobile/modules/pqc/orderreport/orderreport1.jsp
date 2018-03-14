<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephyes=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>我要推荐</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/return/returnBrod.css"/>
	<link rel="stylesheet" type="text/css"
		  href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css" />
	<link type="text/css" rel="stylesheet" 	href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/pqcIndex"></a>
			<h2 class="title">我要推荐</h2>
		</header><!-- /header -->
		<form id="inputForm">
		<section class="pad_top11">
			<div class="sec font0 clearfix">
				<ul class="pad_top3 pad_left3">
					<li class="mar_btm24 clearfix">
						<span class="col_5 font28 flo_left">
							<span class="font_fmy col_cb2307 pad_rgt10">*</span>客户姓名：<input class="wrt bor_eb" placeholder="最多15个汉字，必须填写" type="text" name="customerName" onkeyup="check_illegal(1)" id="customerNameId">

						<br/>
							<span hidden="hidden"><font color="red" class="redClass"></font></span>
								</span>

					</li>
					<li class="mar_btm30 clearfix">
						<span class="col_5 font28 flo_left">
							<span class="font_fmy col_cb2307 pad_rgt10">*</span>客户号码：<input class="wrt bor_eb" placeholder="只验证11位数字，必须填写" type="text"  name="customerPhone"  onkeyup="check_illegal(2)" id="customerPhoneId">
						<br/>
								<span><font color="red" class="redClass"></font></span>
								</span>

						</span>
					</li>
					<li class="mar_btm30 clearfix">
						<span class="col_5 font28 flo_left">
							<span class="font_fmy col_cb2307 pad_rgt10">*</span>小区名称：<input class="wrt bor_eb" placeholder="最多10个汉字，必须填写" type="text" name="communityName"  onkeyup="check_illegal(3)" id="communityNameId">
						<br/>
								<span><font color="red" class="redClass"></font></span>
								</span>

						</span>
					</li>
					<li class="mar_btm30 clearfix">
						<span class="col_5 font28 flo_left line_hgt12"><span class="hide font_fmy col_cb2307 pad_rgt10">*</span>详细地址：


						</span>
						<textarea  class="wrt_area bor_eb font24" placeholder="最多30个汉字" name="address" id="addressId"></textarea>

					</li>
				<%--	<a class="destiny" href="javascript:;">获取当前位置</a>--%>
					<li class="mar_btm30 clearfix">
						<span class="col_5 font28 flo_left line_hgt12"><span class="hide font_fmy col_cb2307 pad_rgt10 font28">*</span>备注信息：</span>
						<textarea class="wrt_area bor_eb font24" placeholder="最多30个汉字"  name="reportRemarks" id="remarksId"></textarea>

					</li>
				</ul>
				<div class="singleBox">
					<span class="font_fmy col_cb2307 pad_rgt10 font28">*</span>
					<span class="font30 col_3">本人或家人是否曾在美得你咨询过</span>
				</div>
				<div class="chooseBox clearfix">
					<span class="fl_l">
						<input type="radio" id="yes" name="isAsked" value="1">
						<label data-name="already" for="yes">是</label>
					</span>
					<span class="fl_r">
						<input type="radio" id="no" name="isAsked" value="0">
						<label class="checked" data-name="already" for="no">否</label>
					</span>
				</div>
				<p class="font24 col_cb2307 pad_left34 pad_rgt34 pad_top40 line_hgt175">
					返单奖励须知：<br />
					1.公司员工将以上信息填写保存后，将上传给公司营销部。<br />
					2.公司“小美返单”会将信息进行验证，确认推荐信息是否有效。<br />
					3.有效信息“小美"会邀约客户进店，成功进店客户会给推荐员工兑现推荐奖励。<br />
					4.成功进店信息在签约成功，进入施工阶段公司会给予推荐员工返单奖励。<br />
					5.如果推荐人提供的潜在客户在两个月内，仍未与美得你公司签订预定合同，则此潜在客户将视为无效推荐。
				</p>
				<a class="sub" href="javascript:;">上报返单</a>
			</div>
		</section>
		</form>
		<div style="padding-bottom:300px;"></div>
	</div>
	<div class="alertMask undis">
		<div class="maskWrapper">
			<p class="col_3 maskTit">提示</p>
			<div class="font28 col_6 maskContent">手机号已存在，请确认本次推荐客户是否曾经在美得你公司咨询过？</div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_fdfcfa" href="javascript:;" onclick="hide()">继续添加</a>
				<a class="maskBtn font33 col_0780ec" href="javascript:;" onclick="hide()">取消</a>
			</div>
		</div>
	</div>
	<%--<div class="black">
		<div class="B_cen">
			<div class="icon"></div>
			<div class="icon_info">请确认质检是否完成验收 客户是否缴纳对应款项！</div>
			<div class="icon_btn">确定</div>
		</div>
	</div>--%>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script>
        function run_waitMe(effect,text){
            $('body').waitMe({
                effect: effect,
                text: text,
                bg: 'rgba(255,255,255,0.7)',
                color:'#000',
                sizeW:'',
                sizeH:'',
                source: 'img.svg'
            });
        }
		$(document).on('click','label',function(){
			var thisName = $(this).attr('data-name');
		    $('label[data-name="'+thisName+'"]').removeClass('checked');
		    $(this).addClass('checked');
		});

function hide(){
    $('.alertMask').hide();

}

$(document).ready(function(){
    $(".icon_btn").on("click", function () {
        $(".black").hide();
    })
	$(".sub").bind("click",submit);

})
function submit(){




    if($("#customerNameId").val().trim().length>15 ||$("#customerNameId").val().trim().length<1){
        $("#customerNameId").next().next().find('font').text("客户名称未填写或超过15个字");
        $("#customerNameId").next().next().show();

    }else{

        $("#customerNameId").next().next().find('font').text("");
        $("#customerNameId").next().next().hide();

    }



    if($("#customerPhoneId").val().trim().length!=11 || isNaN(($("#customerPhoneId").val()))||$("#customerPhoneId").val().trim().length<1){
        $("#customerPhoneId").next().next().find('font').text("手机格式有误");
        $("#customerPhoneId").next().next().show();

    }


    if($("#communityNameId").val().trim().length>10 || $("#communityNameId").val().trim().length<1){
        $("#communityNameId").next().next().find('font').text("小区名称未填写或超过10个字");
        $("#communityNameId").next().next().show();


    }
    if($(".redClass").text().length>0){
        $(".sub").attr("disabled",true);

    }else{

        $(".sub").attr("disabled",false);
    }

    if($(".redClass").text().length>0){
        //校验不通过
       return;

    }else{

        if($("#addressId").val().trim().length>30){
            globalUtil.fn.alert('地址超过30个字',2.0)
            return;
        }

        if($("#remarksId").val().trim().length>30){

            globalUtil.fn.alert('备注超过30个字',2.0)
            return;
        }








        run_waitMe("win8","正在上报返单信息...")

	//校验通过
        $.ajax({
            url : "${ctx}/app/pqc/orderReport/orderReportSave",
            type : "post",
            data : $("#inputForm").serialize(),
            success : function(data) {
                if (data == "0") {
                    $('body').waitMe('hide');
         		 globalUtil.fn.alert('上报成功',2.0)
                setTimeout("window.location.href='${ctx}/app/pqc/orderReport/toOrderReportRecord'",2000)


                } else {
                    globalUtil.fn.alert('上报失败了',2.0)
                }
            }
        });





    }



}

function check_illegal(num){
    switch (num){
	case 1 :

		if($("#customerNameId").val().trim().length>15 ||$("#customerNameId").val().trim().length<1){
            $("#customerNameId").next().next().find('font').text("客户名称未填写或超过15个字");
            $("#customerNameId").next().next().show();

		}else{

            $("#customerNameId").next().next().find('font').text("");
            $("#customerNameId").next().next().hide();

		}
		break;
    case 2 :

        if($("#customerPhoneId").val().trim().length!=11 || isNaN(($("#customerPhoneId").val()))||$("#customerPhoneId").val().trim().length<1){
            $("#customerPhoneId").next().next().find('font').text("手机格式有误");
            $("#customerPhoneId").next().next().show();

        }else{
            $("#customerPhoneId").next().next().find('font').text("");
            $("#customerPhoneId").next().next().hide();
            $.ajax({
                url : "${ctx}/app/pqc/orderReport/checkCustomerPhone?customerPhone=" + $("#customerPhoneId").val(),
                type : "post",
                success : function(data) {
                    if (data == "0") {


                    } else {
                        $(".alertMask").show()
                    }
                }
            });


        }
        break;
    case 3 :

        if($("#communityNameId").val().trim().length>10 || $("#communityNameId").val().trim().length<1){
            $("#communityNameId").next().next().find('font').text("小区名称未填写或超过10个字");
            $("#communityNameId").next().next().show();


        }else{
            $("#communityNameId").next().next().find('font').text("");
            $("#communityNameId").next().next().hide();

        }
        break;

	   }


	   if($(".redClass").text().length>0){
           $(".sub").attr("disabled",true);

	   }else{

           $(".sub").attr("disabled",false);
	   }


       }

	</script>
</body>
</html>