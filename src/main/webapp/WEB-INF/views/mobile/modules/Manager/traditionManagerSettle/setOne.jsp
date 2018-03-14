<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>首期款结算</title>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/reset.css">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/common.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/set.css">
	<link rel="stylesheet" type="text/css"
		  href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css" />
</head>

<body class="back_fff">
	<div class="wrap">
		<header>
			<a class="back_btn New_btns" href="${ctx}/app/manager/tradition-manager-settle/settleApply.php?orderId=${entity.orderId}"></a>
			<h2 class="title">${entity.checkNodeName}</h2>
		</header>
		<section class="pad_top11">
			<div class="set">
				<p><span class="col_6">客户姓名：</span><span class="col_3">${entity.customerName}</span></p>
				<p><span class="col_6">工程地址：</span><span class="col_3">${entity.communityName }-${entity.buildNumber }-${entity.buildUnit }-${entity.buildRoom }</span></p>
			</div>

			<form id="jvForm">
			<div class="set_tex">
				<p class="col_3">添加备注：</p>
				<textarea class="col_9" placeholder="在这里写下要添加的备注内容..." name="settleApplyRemarks"></textarea>
			</div>
			<div class="pic_up">
				<span class="pic_btn"><input type="file" accept="image/*" onchange="preview(this)">
				</span>


			</div>

				<input type="text" hidden="hidden" name="orderId" value="${entity.orderId}">
				<input type="text" hidden="hidden"  name="settleNodeId" value="${entity.settleNodeId}">
				<input type="text" hidden="hidden"  name="storeId" value="${entity.storeId}">
			</form>
			<div class="set_btm">
				<span>申请</span>
				<span onclick="back()">取消</span>
			</div>
			<input type="text" hidden="hidden" id="num" value="1">
		</section>
	</div>

	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script>
	/*	$(".pic_up span i").on("click", function () {
			$(this).parent("span").remove()
		})
*/
$(function () {

    $(".set_btm :first-child").bind("click",ajaxSubmit);


})
function back(){
    window.location.href="${ctx}/app/manager/tradition-manager-settle/settleApply.php?orderId=${entity.orderId}";

}


        //图片上传显示
        function preview(file) {

            var prevDiv = $('.pic_up');
            if (file.files && file.files[0]) {
                var reader = new FileReader();
                reader.onload = function(evt){
					var num =$("#num").val();
                    $('.pic_up').append(' <span><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic);"/> <i onclick="removePic(this,'+num+')"></i></span>');
					/* 	$().insertAfter('#picShow'); */



                }
                reader.readAsDataURL(file.files[0]);
            }else {
                prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
                var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
                console.log(file,file.value);
            }
        }

        function uploadpic(pic){
            var num =$("#num").val();
            var hiddenFrom = document.getElementById("jvForm");
            var input =document.createElement("input");
            if(pic){
                input.setAttribute("type","hidden");
                input.setAttribute("name","photos");
                input.setAttribute("id",num);
                input.setAttribute("value", pic);
                hiddenFrom.appendChild(input);//将元素添加到form中
				num++;
                $("#num").val(num);
            }
        }

        function removePic(obj,num){
            $(obj).parent("span").remove();
            $("#"+num).remove();
		}



		function ajaxSubmit(){

            var picLength=   $(".pic_up span").length;

            if(picLength>7){

                globalUtil.fn.alert("您上传的图片太多啦..最多可以上传6张")
                return;

            }

            $(".set_btm :first-child").unbind("click");



            var options ={
                url : "${ctx}/app/manager/tradition-manager-settle/saveSettleInfo",
                type: "POST",
                success : function(data){
                    if(data == 2){
                        globalUtil.fn.alert('操作成功...',2.0);
					window.location.href="${ctx}/app/manager/tradition-manager-settle/settleApply.php?orderId=${entity.orderId}";
                    }else if(data==3){

                        globalUtil.fn.alert('该结算节点已经操作过了,请不要重复操作',2.0);


                    }
                }

            }

            $("#jvForm").ajaxSubmit(options);


		}


	</script>
</body>

</html>