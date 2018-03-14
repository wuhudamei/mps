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
    <title>投诉答复</title>
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new/mask.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/bdstart/bdstart.css" />
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/complaint/complaint.css">
</head>

<body>
    <div class="">
        <header>
            <a class="back_btn" href="${ctx}/app/manager/project-issue/checkIssueProblemByOrderId?orderId=${map.orderId}" ></a>
            <h2 class="title">投诉答复</h2>
        </header>

        <form id="jvForm">

<%--             <input name=delayDays value="${map.delayDays}"  hidden="hidden"> --%>
            <input name="delayDays" value="${map.delayDays}"  hidden="hidden">
            <input name=complaintProblemItemId value="${map.complaintProblemItemId}"  hidden="hidden">
            <input name=orderComplaintProblemId value="${map.orderComplaintProblemId}"  hidden="hidden">
            <input name="orderId" value="${map.orderId}"  hidden="hidden">
            <input name="handleId" value="${map.handleId}"  hidden="hidden">
            <input name="workOrderCode" value="${map.workOrderCode}" id="workOrderCode" hidden="hidden">
            <input name="serviceProblemId" value="${map.serviceProblemId}"  id="serviceProblemId" hidden="hidden">
        <section class="pad_top11" style="padding-top: 1rem;">
            <h2 class="bd_title col_3">${map.customerInfo}</h2>
            <div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
                <ul class="pad_top3 pad_left3">
                    <li class="mar_btm24 clearfix">
                        <span class="col_grey font28 flo_left w2">问题创建时间：</span>
                        <p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${map.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </p>
                    </li>


                    <c:if test="${not empty map.delayDays}">
						<li class="mb30 clearfix">
							<span style="color:#e50e09;" class="font28 flo_left w2">执行时限：</span>
							<p style="color:#e50e09;" class="font28 flow"><fmt:formatNumber pattern="0" value="${map.responseTime}"></fmt:formatNumber>天</p>
						</li>
                        <li class="mar_btm24 clearfix">
                            <span style="color:#e50e09;" class=" font28 flo_left w2">超时天数：</span>
                            <p style="color:#e50e09;" class="font28  pad_rgt3 flow"><fmt:formatNumber pattern="0" value="${map.delayDays}"></fmt:formatNumber>天</p>
                        </li>
                    </c:if>


                    <c:if test="${ empty map.delayDays}">
						<li class="mb30 clearfix">
							<span style="color:#e50e09;" class="font28 flo_left w2">执行时限：</span>
							<p style="color:#e50e09;" class="font28 flow"><fmt:formatNumber pattern="0" value="${map.responseTime}"></fmt:formatNumber>天</p>
						</li>
                        <li class="mar_btm24 clearfix">
                            <span style="color:#e50e09;" class=" font28 flo_left w2">超时天数：</span>
                            <p style="color:#e50e09;" class="font28  pad_rgt3 flow">0天</p>
                        </li>
                    </c:if>

                    <li class="mar_btm24 clearfix">
                        <span class="col_grey font28 flo_left w2">问题事项：</span>
                        <p class="font28 col_3 pad_rgt3 flow">${map.itemName}</p>
                    </li>
                    <li class="mar_btm24 clearfix">
                        <span class="col_grey font28 flo_left w2">问题描述：</span>
                        <p class="font28 col_3 pad_rgt3 flow">${map.problemDescribe}</p>
                    </li>
                </ul>
            </div>
            <div class="complaint">
                <span>答复：</span><textarea placeholder="最多输入100个字" name="dealDescribe" onkeyup="checkIsIllegal()" id="dealDescribeId"></textarea>
            </div>
            <div class="bd_pic">
                <p>上传附件
                    <span class="bd_camera"><input type="file" accept="image/*" onchange="preview(this)">
                    </span>
                    <input type="text" hidden="hidden" id="shit" value="1">

                </p>
            </div>
            <div class="pic_list">
                <%--<span>--%>
                   <%--<img src="${ctxStatic}/mobile/modules/Manager/img/order/drawing.png" alt="">--%>
                   <%--<i></i>--%>
               <%--</span>--%>


                <%--<span>--%>
                   <%--<img src="${ctxStatic}/mobile/modules/Manager/img/order/photo.png" alt="">--%>
                   <%--<i></i>--%>
               <%--</span>--%>
                <%--<span>--%>
                   <%--<img src="${ctxStatic}/mobile/modules/Manager/img/order/drawing.png" alt="">--%>
                   <%--<i></i>--%>
               <%--</span>--%>
            </div>


        </section>
        </form>
        <div class="one_btn">完成答复</div>
        <div style="padding-bottom:200px;"></div>

        <div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id="mask">
            <div class="alertMask">
                <div class="maskWrapper">
                    <p class="col_3 maskTit">提示</p>
                    <div class="font28 col_6 maskContent">此投诉问题已和客户确认处理完成</div>
                    <div class="maskBtns clearfix">
                        <a class="maskBtn font33 col_f"  href="javascript:;" id="sureId">确定</a>
                    </div>
                </div>
            </div>
        </div>
		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id="mask2">
            <div class="alertMask">
                <div class="maskWrapper">
                    <p class="col_3 maskTit">投诉答复</p>
                    <div class="font28 col_6 maskContent">此投诉问题已和客户确认处理完成</div>
                    <div class="maskBtns clearfix">
                        <a class="maskBtn font33 col_f" style="display:inline-block;" href="javascript:;" id="sureId1">已确认</a>
                        <a class="maskBtn font33 col_f" style="display:inline-block;" href="javascript:;" id="sureId2">未确认</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/projectIssueDeal/projectIssueDeal.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>

<script type="text/javascript">

    $(".one_btn").bind("click",ajaxSaveManagerDeal);
	var orderId = ${map.orderId};

	$("#sureId2").click(function(){
		$("#mask2").hide();
	});
	$("#sureId1").click(function(){
		$.ajax({
            url: baseAppUrl+"manager/project-issue/saveManagerDeal",
           type: "post",
           data:$("#jvForm").serialize(),
           success:function(data){
                if(data==1){
                	//查询是否还有未处理的问题
                	$.ajax({
                       url: baseAppUrl+"manager/project-issue/selectCountNoDealByWorkOrderCode",
                       type: "post",
                       data:{workOrderCode:$("#workOrderCode").val()},
                       success:function(result){
                            if(result==0){
                            	var workOrderCode = $("#workOrderCode").val();
                            	var serviceProblemId = $("#serviceProblemId").val();
								var status = '10';
								if(workOrderCode!=''){
									$.ajax({
					                       url: baseAppUrl+"manager/project-issue/ajaxupdateScuStatsapp",
					                       type: "post",
					                       data:{workOrderCode:workOrderCode,cusServiceid:serviceProblemId,status:status},
					                       success:function(data){
					                    	   if(data.code==1){
													//同步状态成功，返回列表
													window.location.href="${ctx}/app/manager/project-issue/list.php";
												}else{
													//同步状态不成功，本来应该做些处理，这里暂缓，先跳转列表
													window.location.href="${ctx}/app/manager/project-issue/list.php";
												}
					                       }
									 });
								}
								
                           }else{
                                window.location.href="${ctx}/app/manager/project-issue/list.php";
                           } 
                       }
                   });
                    
                  //  window.location.href="${ctx}/app/manager/project-issue/list.php";

                }else if (data==2){
                    /* $(".maskContent").text("您已经处理过该投诉项了");
                    $("#mask").show(); */
                	window.location.href="${ctx}/app/manager/project-issue/list.php";

                }else {
                    $(".maskContent").text("网络不好,请稍后再试");
                    $("#mask").show();
                }
           }
       });
	});
    //图片上传显示
    function preview(file) {
        var shit = $("#shit").val();
        var prevDiv = $('.bd_camera');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function(evt){
                $('.pic_list').append(('<span><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><i></i></span>'));
            }
            reader.readAsDataURL(file.files[0]);
        }else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
    }



    $(document).on('click', 'i', function(){

        var numReal = $(this).prev().attr("id");
        $(("#num"+numReal)).remove();

        $(this).parent().remove();
    });

    function uploadpic(pic){

        var hiddenForm = document.getElementById("jvForm");
        var input =document.createElement("input");


        var shit = $("#shit").val();

        if(pic){
            input.setAttribute("id","num"+shit);
            input.setAttribute('hidden', 'hidden');
            input.setAttribute('name', 'photos');
            input.setAttribute("type","text");
            input.setAttribute("value", pic);
            hiddenForm.appendChild(input);
            shit++;
            $("#shit").val(shit);
        }
    }

</script>
</body>
</html>