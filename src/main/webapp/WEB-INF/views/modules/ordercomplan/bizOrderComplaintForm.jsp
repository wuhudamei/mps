<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>订单投诉问题管理</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/bizOrderComplaint/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/bizOrderComplaint/css/complain.css"/>
    <%-- 		<script src="${ctxStatic}/bizOrderComplaint/js/creativeGroupForm_qczj.js?v=${uiVersion}" type="text/javascript"></script> --%>
    <%-- 		<script type="text/javascript" src="${ctxStatic}/bizOrderComplaint/jquery/jquery-2.1.1.min.js"></script> --%>

    <style>
        i{

            background: url(${ctxStatic}/mobile/modules/Manager/images/del_pic.png) no-repeat;
            background-size: 100% 100%;
            width: 0.51rem;
            height: 0.51rem;
            display: block;
            position: absolute;
            top: -0.2rem;
            right: -0.2rem;

        }


        .alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);z-index:9999;}
        .maskWrapper{width: 380px;border-radius: 4px;background: #fff;font-size: 16px;
            position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);}
        .col_3{color: #333}
        .col_6{color: #666;}
        .col_f{color: #fff;}
        .col_fdfcfa{color: #fdfcfa;}
        .col_0780ec{color: #0780ec;}
        .font28{font-size: 14px;}
        .font33{font-size: 16.5px;}
        .maskTit{height: 50px;line-height: 50px;text-align: center;}
        .maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
        .maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
        .maskBtn{display: block;width: 60%;text-align: center;line-height: 38px;}
        .maskBtn{background: #0780ec;border-radius: 4px;display: block;width: 47.6%;
            text-align: center;line-height: 38px;font-size:16px;margin: 0 auto;padding:0;}
        .twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
        .twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}


    </style>
    <script type="text/javascript">
        $(document).ready(function () {

            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });

            getModelList()
            getModelList1()
        });

        $(document).on('click', '.delItemBtn', function () {

            var r = confirm("确定删除该投诉问题吗?")
            if (r == true) {
                $(this).parent().remove();

                var num = 1;

                $(".bold").each(function () {

                    $(this).text("投诉问题" + num);
                    num++;


                });
            }
            else {
                return;
            }


        });
        $(document).on('click', '.del_pic', function () {
        	
        	$(this).parent().find("img").attr("src","${ctxStatic}/bizOrderComplaint/images/upPic.png");
        	$(this).parent().find("input").attr("name","deletePic");
//         	$(this).parent().find("img").get(0).src('');
//         	$(this.id).find("div")
//            $(this).parent().remove();
        });
        $(document).on('click', '.checkWrap label', function () {
            if ($(this).hasClass('nochecked')) {
                $(this).parent().parent().find("label").each(function () {

                    $(this).removeClass('checked').addClass('nochecked');
                    $(this).parent().find('input').removeClass('checked');
                    $(this).parent().find('input').attr({"checked": false});

                })

                $(this).removeClass('nochecked').addClass('checked');
                $(this).parent().find('input').addClass('checked');
                $(this).parent().find('input').attr({"checked": true});

                ajaxTypeItemAndEx( $(this).parent().find('input'));
            } else {
            	
                $(this).removeClass('checked').addClass('nochecked');
                $(this).parent().find('input').removeClass('checked');
                $(this).parent().find('input').attr({"checked": false});
                ajaxItemAndExclear( $(this).parent().find('input'));
            }
        });
        var index = 1;
        $(document).on('click', '.addBtn', function () {
            ++index;
            var num = ++$('.itemList').length;

            var itemList = '<div class="mb1d mb12 rela itemList">' +
                '<div class="lab bor_btm_d7 divClass">' +
                '<p class="f16 col3 bold pb20">投诉问题' + num + '</p>' +
                '<p class="col3 f14 mb20 clearfix">' +
                '<label class="f14 col3" for="">问题分类：</label>' +
                ' <select id="select' + num + '" class="input-medium" name="bOrContPros[' + index + '].complaintProblemTypeId"   onchange="gradeChangediv(this)"  > ' +
                '</select>' +
                '<span  id="spanid1' + num + '" class="f14 col3 ml20">对应任务包</span>' +
                '<span id="spanid2' + num + '" class="f14 col3 ml30">被投诉对象</span>' +
                '<div  id="div1' + num + '" ></div>' +
                '<div  id="div2' + num + '" ></div>' +
                '<input id="input' + num + '" type="hidden" value=""></input>' +
                '</p>' +
                '<div class="col3 f14 mb20 clearfix">' +
                ' <span class="must2"> *</span>'+
                '<span class="fl pt8">问题事项：</span>' +
                '<p   id="p1' + num + '"class="overflow checkWrap">' +
                '</p>' +
                '</div>' +

                '<div class="col3 f14 mb20 clearfix">'+
                '<span id="executeTimeLimit' + num + '" class="fl pt8"> 执行时限：</span>天'+
                '<div id="executeTimeLimitDivid' + num + '"></div>'+
			 	'</div>'+
                
                
                '<div class="col3 f14 pb20 clearfix">' +
                '<span class="fl pt8">问题内容：</span>' +
                '<textarea  name="bOrContPros[' + index + '].complaintProblemNr" class="complainText" onkeyup="this.value = this.value.substring(0,300) placeholder="最多输入300个汉字"  maxLength="300"></textarea>' +
                '</div>' +
                '<div class="col3 f14 pb20 clearfix">' +
                '<span class="fl pt8">上传附件：</span>' +
                '<div class="pics font0 clearfix">' +

                '<div  class="pic camera" href="javascript:void(0)">' +
                '<img id="imgPreview1' + num + '" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">' +
                '<input id="imginput1' + num + '" type="file" name="bOrContPros[' + index + '].photo" accept="image/*" onchange="PreviewImage1p(this)">' +
                '<a class="del_pic" href="javascript:void(0)"></a>' +
                '</div>' +
                '<div  class="pic camera" href="javascript:void(0)">' +
                '<img id="imgPreview2' + num + '" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">' +
                '<input id="imginput2' + num + '" type="file" name="bOrContPros[' + index + '].photo" accept="image/*" onchange="PreviewImage2p(this)">' +
                '<a class="del_pic" href="javascript:void(0)"></a>' +
                '</div>' +
                '<div  class="pic camera" href="javascript:void(0)">' +
                '<img id="imgPreview3' + num + '" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">' +
                '<input id="imginput3' + num + '"  type="file" name="bOrContPros[' + index + '].photo" accept="image/*" onchange="PreviewImage3p(this)">' +
                '<a class="del_pic" href="javascript:void(0)"></a>' +
                '</div>' +
                '<div  class="pic camera" href="javascript:void(0)">' +
                '<img id="imgPreview4' + num + '" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">' +
                '<input id="imginput4' + num + '" type="file" name="bOrContPros[' + index + '].photo" accept="image/*" onchange="PreviewImage4p(this)">' +
                '<a class="del_pic" href="javascript:void(0)"></a>' +
                '</div>' +
                '<div  class="pic camera" href="javascript:void(0)">' +
                '<img id="imgPreview5' + num + '" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">' +
                '<input id="imginput5' + num + '" type="file" name="bOrContPros[' + index + '].photo" accept="image/*" onchange="PreviewImage5p(this)">' +
                '<a class="del_pic" href="javascript:void(0)"></a>' +
                '</div>' +
                '<div  class="pic camera" href="javascript:void(0)">' +
                '<img id="imgPreview6' + num + '" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">' +
                '<input id="imginput6' + num + '" type="file" name="bOrContPros[' + index + '].photo" accept="image/*" onchange="PreviewImage6p(this)">' +
                '<a class="del_pic" href="javascript:void(0)"></a>' +
                '</div>' +

                '</div>' +
                '</div>' +
                '</div>' +
                '<a class="delItemBtn" href="javascript:void(0)">删除事项</a>' +
                '</div>';
            $('.itemWrapper').append(itemList);
            getModelList(num);

        });

        //上传图片事件
        function PreviewImage1(imgFile) {
        	$("#imgPreview1").parent().find("input").attr("name","bOrContPros[1].photo");
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview1").innerHTML = "";
                    document.getElementById("imgPreview1").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById('imgPreview1').setAttribute('src', path);
                    
                }
            }
        }
        //上传图片事件
        function PreviewImage2(imgFile) {
        	$("#imgPreview2").parent().find("input").attr("name","bOrContPros[1].photo");
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview2").innerHTML = "";
                    document.getElementById("imgPreview2").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById('imgPreview2').setAttribute('src', path);
                }
            }
        }
        //上传图片事件
        function PreviewImage3(imgFile) {
        	$("#imgPreview3").parent().find("input").attr("name","bOrContPros[1].photo");
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview3").innerHTML = "";
                    document.getElementById("imgPreview3").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById('imgPreview3').setAttribute('src', path);
                }
            }
        }
        //上传图片事件
        function PreviewImage4(imgFile) {
        	$("#imgPreview4").parent().find("input").attr("name","bOrContPros[1].photo");
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview4").innerHTML = "";
                    document.getElementById("imgPreview4").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById('imgPreview4').setAttribute('src', path);
                }
            }
        }
        //上传图片事件
        function PreviewImage5(imgFile) {
        	$("#imgPreview5").parent().find("input").attr("name","bOrContPros[1].photo");
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview5").innerHTML = "";
                    document.getElementById("imgPreview5").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById('imgPreview5').setAttribute('src', path);
                }
            }
        }
        //上传图片事件
        function PreviewImage6(imgFile) {
        	$("#imgPreview6").parent().find("input").attr("name","bOrContPros[1].photo");
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview6").innerHTML = "";
                    document.getElementById("imgPreview6").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById('imgPreview6').setAttribute('src', path);
                }
            }
        }
        //上传图片事件 imginput6
        function PreviewImage1p(imgFile) {
            var num = imgFile.id.substr(9, imgFile.id.length);
            $("#imgPreview1" + num).parent().find("input").attr("name","bOrContPros["+num+"].photo");
// 		  alert(num+":"+imgFile.id);
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview1" + num).innerHTML = "";
                    document.getElementById("imgPreview1" + num).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById("imgPreview1" + num).setAttribute('src', path);
                }
            }
        }
        //上传图片事件
        function PreviewImage2p(imgFile) {
        	 $("#imgPreview2" + num).parent().find("input").attr("name","bOrContPros["+num+"].photo");
            var num = imgFile.id.substr(9, imgFile.id.length);
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview2" + num).innerHTML = "";
                    document.getElementById("imgPreview2" + num).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById("imgPreview2" + num).setAttribute('src', path);
                }
            }
        }
        //上传图片事件
        function PreviewImage3p(imgFile) {
        	 $("#imgPreview3" + num).parent().find("input").attr("name","bOrContPros["+num+"].photo");
            var num = imgFile.id.substr(9, imgFile.id.length);
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview3" + num).innerHTML = "";
                    document.getElementById("imgPreview3" + num).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById("imgPreview3" + num).setAttribute('src', path);
                }
            }
        }
        //上传图片事件
        function PreviewImage4p(imgFile) {
        	 $("#imgPreview4" + num).parent().find("input").attr("name","bOrContPros["+num+"].photo");
            var num = imgFile.id.substr(9, imgFile.id.length);
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview4" + num).innerHTML = "";
                    document.getElementById("imgPreview4" + num).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById("imgPreview4" + num).setAttribute('src', path);
                }
            }
        }
        //上传图片事件
        function PreviewImage5p(imgFile) {
        	 $("#imgPreview5" + num).parent().find("input").attr("name","bOrContPros["+num+"].photo");
            var num = imgFile.id.substr(9, imgFile.id.length);
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview5" + num).innerHTML = "";
                    document.getElementById("imgPreview5" + num).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById('imgPreview5' + num).setAttribute('src', path);
                }
            }
        }
        //上传图片事件
        function PreviewImage6p(imgFile) {
        	 $("#imgPreview6" + num).parent().find("input").attr("name","bOrContPros["+num+"].photo");
            var num = imgFile.id.substr(9, imgFile.id.length);
            var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
            if (!pattern.test(imgFile.value)) {
                alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
                imgFile.focus();
            }
            else {
                var path;

                if (document.all)//IE
                {
                    imgFile.select();
                    path = document.selection.createRange().text;
                    document.getElementById("imgPreview6" + num).innerHTML = "";
                    document.getElementById("imgPreview6" + num).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
                }
                else//FF
                {
                    path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
                    document.getElementById('imgPreview6' + num).setAttribute('src', path);
                }
            }
        }

        //项目触发的事件
        function abandonedxm() {
            var v = $("#storeId").val();

            window.location.href = "${ctx}/cusserviceproblem/bizCusServiceProblem/ProjectView?storeId=" + v;
        }



        function sure(){

            $("#inputForm").attr("action", "${ctx}/ordercomplan/bizOrderComplaint/saveAllBack");
            $("#inputForm").submit();

        }

        function cancel(){

            $("#subReport2").hide();


        }



        function submitForm() {
            if ($("#storeId").val() == '') {
                $("#storeId").focus();
                $("#storeIdText").text("请选择门店");
                return false;
            }
            if ($("#orderId").val() == '') {
                alertx("请选择订单");
                return false;
            }
            if ($("#complaintSource").val() == '') {
                $("#complaintSource").focus();
                $("#complaintSourceText").text("请选择投诉问题来源");
                return false;
            }
            if ($("#select1").val() == '') {
                $("#select1").focus();
                $("#select1Text").text("请选择投诉问题分类");
                return false;
            }

            var is = 1;
            $('.divClass').each(function () {

                var length = $(this).find("input[type='checkbox']:checked").length;
                if (length == 0) {
                    is = 0
                    return;

                }

            });

            if (is == 1) {
                var length = $(".divClass").length;
                $(".maskContent").text("本次创建 "+length+" 个投诉事项，将分别发送信息到被投诉人员");

			$("#subReport2").show();

            }

          


        }
        function delectDiv() {

            $("#complaintSourceText").text('');
        }


        function formsubmit(checkbox1) {
            var storeId = $("#complaintSource").val();
            alert(storeId);

        }
        //为表单的元素添加失去焦点事件
        $("form :input").blur(function () {
            alert(1)
        });

        //手机校验
        function checkPhone() {
            var phone = $("#complaintPersonPhone").val();


            if (!isNaN(phone) && phone.length == 11) {

            } else {
                alert("您的手机格式不正确,请重新输入");
//                 $("#complaintPersonPhone").focus();
//                 $("#complaintPersonPhoneTxt").text("您的手机格式不正确,请重新输入");
                return false;
            }


        }


        // 当门店改变时分类信息也会改变
        function updateStoreId() {
            getModelList();
            getModelList1();
        }


        function getModelList(num) {
            var storeId = $("#storeId").val();
            $("#select" + num).empty(); //清空
            $.ajax({
                url: '${ctx}/ordercomplan/bizOrderComplaint/getModelList',
                type: "post",
                data: {
                    storeId: storeId
                },
                cache: false,
                error: function () {
                },
                success: function (data) {
                    var modelList = data.modelList;
                    if (modelList && modelList.length != 0) {
                        var option = '<option value=""></option>';
                        for (var i = 0; i < modelList.length; i++) {
                            option += "<option value=\"" + modelList[i].id + "\"";
                            option += ">" + modelList[i].typeName + "</option>"; //动态添加数据
                        }

                        $("#select" + num).html(option);
                    }
                }
            });
        }
        function getModelList1() {
            var storeId = $("#storeId").val();
            $("#select1").empty();
            $.ajax({
                url: '${ctx}/ordercomplan/bizOrderComplaint/getModelList',
                type: "post",
                data: {
                    storeId: storeId
                },
                cache: false,
                error: function () {
                },
                success: function (data) {
                    var modelList = data.modelList;
                    if (modelList && modelList.length != 0) {
                        var option = '<option value=""></option>';
                        for (var i = 0; i < modelList.length; i++) {
                            option += "<option value=\"" + modelList[i].id + "\"";
                            option += ">" + modelList[i].typeName + "</option>"; //动态添加数据
                        }

                        $("#select1").html(option);
                    }
                }
            });
        }


        //选中分类触发的事件
        function gradeChange(thisch) {
            $("#select1Text").text('');
            $("#div1a").empty();
            $("#div2a").empty();
            var selectValue = $("#select1").val();
            var orderId = $("#orderId").val();
            if ($("#orderId").val() == '') {
                alert("请选择订单");
                return false;
            }

            $.ajax({
                url: '${ctx}/ordercomplan/bizOrderComplaint/gettasklList',
                type: "post",
                data: {
                    ComplaintProblemTypeId: selectValue,
                    orderId: orderId
                },
                cache: false,
                error: function () {
                },
                success: function (data) {

                    var spanid1 = data.ComplaintProblemType
                    if (spanid1 && spanid1.length != 0) {
                        for (var i = 0; i < spanid1.length; i++) {
                            var packName = spanid1[i].packName
                            var dealPersonType = spanid1[i].dealPersonType

                            var spanid1Html = '<span  id="spanid1a" class="f14 col3 ml20">对应任务包：' + packName + '</span>'
                            var div1a = '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
                            var spanid2Html = '<span  id="spanid2a" class="f14 col3 ml20">被投诉对象：' + dealPersonType + '</span>'
                            var div2a = '<input  type="hidden"   name="bOrContPros[1].complaintRoleType" value="' + dealPersonType + '"></input>'

                            $("#spanid1a").html(spanid1Html);
                            $("#spanid2a").html(spanid2Html);
                            $("#div1a").html(div1a);
                            $("#div2a").html(div2a);
                        }
                    }
                    var heidInput = data.substring;
                    $("#input1").val(heidInput);
                    inputchecked1(selectValue)
                }
            });
        }

        function inputchecked1(selectValue) {

            $("#p1a").empty();
            var dataArray = $("#input1").val();
// 			alert(dataArray+":aaa:"+selectValue);
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/ordercomplan/bizOrderComplaint/ajaxTypeItem',
                data: {
                    complaintProblemTypeId: selectValue
                },
                success: function (data) {
                    var html = "";
                    for (var i = 0; i < data.length; i++) {
                        //alert(data[i].value +" -- "+ data[i].label);
                        var checked = "";
                        //alert(dataArray.length);
                        for (var x = 0; x < dataArray.length; x++) {
                            if (dataArray[x] == data[i].value) {
                                checked = "checked";
                            }
                        }
                        //alert("data[i].value:"+data[i].value+", dataArray[data[i].value]:" + dataArray[data[i].value] + ",checked:" + checked + ";");
                        html += '<span><input type="checkbox"  value="' + data[i].value + '" name="bOrContPros[1].ciIds">'
                            + '<label class="nochecked">' + data[i].label + '</label></span> ';
                    }
// 	            	alert(html);
                    $("#p1a").html(html);
                }
            })
        }

        
        
        
			        //置空
			        function ajaxItemAndExclear(selectValue) {
			        	var itemId = selectValue.attr("value");
			        	 selectValue.attr("value","");
			        	$("#getInitValue").attr("value",itemId);
			        }
			        function ajaxTypeItemAndEx(selectValue) {
			          	//获取事项id   bOrContPros[1].ciIds
			            var itemId =$("#getInitValue").val()
			          	if(itemId !== null && itemId !== undefined && itemId !== ''){
			          		selectValue.attr("value",itemId);
			          		$("#getInitValue").attr("value",'');
			          	}
			            var TypeItem = selectValue.attr("value");
			            var TypeName = selectValue.attr("name");
			         	  var sta=  TypeName.indexOf("[");
			            var end=   TypeName.indexOf("]");
			            var localNum=TypeName.substring(sta+1,end);
// 			         alert(localNum)
			//         $("#executeTimeLimit").empty();
			// 			alert(dataArray+":aaa:"+selectValue);
			            $.ajax({
			                type: 'POST',
			                dataType: 'json',
			                url: '${ctx}/ordercomplan/bizOrderComplaint/ajaxTypeItemAndEx',
			                data: {
			                	itemId: TypeItem
			                },
			                success: function (data) {
			                	var result=data.bizComplaintProblemItem;
			//                 	alert(result.executeTimeLimit)
			
				    			var executeTimeLimit = '<span  id="spanid1a" class="fl pt8">执行时限：' + result.executeTimeLimit + '</span>'
				               	var executeTimeLimitDivid = '<input  type="hidden"  name="ackagename" value="' + result.executeTimeLimit + '"></input>'
			// 	            	alert(executeTimeLimit+":"+executeTimeLimitDivid);
			                    $("#executeTimeLimit" +localNum).html(executeTimeLimit);
			                    if(localNum==1){
			                    $("#executeTimeLimitDivid"+localNum).html(executeTimeLimitDivid);
			                    }
			                }
			            })
			        	
			        }
        
        
        
        

        function gradeChangediv(thisth) {
            var num = thisth.id.substr(6, thisth.id.length);
            var selectValue = $('#' + thisth.id).val();
            var orderId=  $("#orderId").val();
            $("#div1" + num).empty();
            $("#div2" + num).empty();

            $.ajax({
                url: '${ctx}/ordercomplan/bizOrderComplaint/gettasklList',
                type: "post",
                data: {
                    ComplaintProblemTypeId: selectValue,
                    orderId:orderId
                },
                cache: false,
                error: function () {
                },
                success: function (data) {

                    var spanid1 = data.ComplaintProblemType
                    if (spanid1 && spanid1.length != 0) {
                        for (var i = 0; i < spanid1.length; i++) {
                            var packName = spanid1[i].packName
                            var dealPersonType = spanid1[i].dealPersonType

                            var spanid1Html = '<span  id="spanid1a" class="f14 col3 ml20">对应任务包：' + packName + '</span>'
                            var inputdiv1 = '<input  type="hidden"  name="bOrContPros[' + num + '].packagename" value="' + packName + '"></input>'
                            var spanid2Html = '<span  id="spanid2a" class="f14 col3 ml20">被投诉对象：' + dealPersonType + '</span>'
                            var inputdiv2 = '<input  type="hidden"   name="bOrContPros[' + num + '].complaintRoleType" value="' + dealPersonType + '"></input>'

                            $("#spanid1" + num).html(spanid1Html);
                            $("#spanid2" + num).html(spanid2Html);
                            $("#div1" + num).html(inputdiv1);
                            $("#div2" + num).html(inputdiv2);
                        }
                    }
                    var heidInput = data.substring;
                    $("#input" + num).val(heidInput);
                    inputchecked(num, selectValue)
                }
            });
        }

        function inputchecked(num, selectValue) {
            var dataArray = $("#input" + num).val();
// 				alert(dataArray+":aaa:"+selectValue);
            $("#p1" + num).empty();
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/ordercomplan/bizOrderComplaint/ajaxTypeItem',
                data: {
                    complaintProblemTypeId: selectValue
                },
                success: function (data) {
                    var html = "";
                    for (var i = 0; i < data.length; i++) {
                        //alert(data[i].value +" -- "+ data[i].label);
                        var checked = "";
                        //alert(dataArray.length);
                        for (var x = 0; x < dataArray.length; x++) {
                            if (dataArray[x] == data[i].value) {
                                checked = "checked";
                            }
                        }
                        //alert("data[i].value:"+data[i].value+", dataArray[data[i].value]:" + dataArray[data[i].value] + ",checked:" + checked + ";");
                        html += '<span><input type="checkbox"  value="' + data[i].value + '" name="bOrContPros[' + num + '].ciIds">'
                            + '<label class="nochecked">' + data[i].label + '</label></span> ';
                    }
// 		            	alert(html);
                    $("#p1" + num).html(html);
                }
            })
        }
        
        //浮层弹出绑定的事件
        $(document).on('click', '.searchBtn', function () {
            $(".mask").show();
        });
       //点击取消绑定的是事件
        $(document).on('click', '.maskCancleBtn', function () {
            $(".mask").hide();
        });
        

        function relateOrder() {

            var html = ' <tr>'
                + ' <th>客户姓名</th>'
                + ' <th>客户手机号</th>'
                + ' <th>工程地址</th>'
                + ' <th>项目经理</th>'
                + ' <th>操作</th>'
                + '  </tr>';
            $.ajax({

                url: "${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/findOrderByText",
                data: {"text":$(".searchText").val()},
                success: function (data) {

                    if (null != data) {


                        for (var v = 0; v < data.length; v ++) {


                            html += ' <tr>'
                                + '<td>' + data[v].customerName + '</td>'
                                + '<td>' + data[v].customerPhone + '</td>'
                                + ' <td>' + data[v].customerAddress + '</td>'
                                + ' <td>' + data[v].managerName + '</td>'
                                + ' <td><a class="maskChooseBtn" href="javascript:;" onclick="chooseOrder(' + data[v].orderId + ')">选择</a></td>'
                                + ' </tr>';


                        }

                        $(".maskTable").html(html);
                    }


                }


            })

        }



        function chooseOrder(orderId){


            $.ajax({

                url: "${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/findOrderByText",
                data:{"orderId":orderId},
                success: function (data) {

                    if (null != data) {
//                         $(".shop").html('<option value="'+data[0].storeName+'" selected="selected">'+data[0].storeName+'</option>')


                        $("#customerName").val(data[0].customerName);
                        $("#customerPhone").val(data[0].customerPhone);
                        $("#itemManager").val(data[0].managerName);
                        $("#orderInspector").val(data[0].pqcName);
                        $("#projectMode").val(data[0].projectMode);
                        $("#engineDepartName").val(data[0].departName);
                        $("#orderNumber").val(data[0].orderNumber);
                        $("#communityName").val(data[0].communityName);
                        $("#orderId").val(orderId);
                        $(".mask").hide();
                    }

                }

            })
        }
        
        

        

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/ordercomplan/bizOrderComplaint/listall">订单投诉问题列表</a></li>
    <li class="active"><a
            href="${ctx}/ordercomplan/bizOrderComplaint/form?id=${bizOrderComplaint.id}">订单投诉问题<shiro:hasPermission
            name="ordercomplan:bizOrderComplaint:edit">${not empty bizOrderComplaint.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="ordercomplan:bizOrderComplaint:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="bizOrderComplaint" action="${ctx}/ordercomplan/bizOrderComplaint/saveAllBack"
           method="post" class="form-horizontal" enctype="multipart/form-data">
    <sys:message content="${message}"/>

    <div class="font0">
        <input id="orderId" name="orderId" type="hidden" value="${bizOrderComplaint.order.orderId}"/>
        <header><h2 class="title">投诉问题上报</h2></header>
        <section class="con">
            <div>
                <div class="item pl152">订单信息</div>
                <ul class="pl152 bg_e tab pt32 pb32 info font0">
                    <li class="mb20 font0">
                        <label class="f14 col3 ml2em" for="">门店：</label>
                        <form:select id="storeId" path="order.storeId" onchange="updateStoreId()" class="shop">

                            <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value"
                                          htmlEscape="false"/>
                        </form:select>
                        <span class="must2">*  <span id='storeIdText'></span></span>
                        <a class="searchBtn" href="javascript:void(0)" >点击查询项目</a>
                        <span class="must">*</span>
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" for="">客户姓名：</label>
                        <form:input  id="customerName" path="order.customerName" htmlEscape="false" maxlength="16" class="itemInput"
                                    readOnly="true"/>
                        <label class="f14 col3 ml1em ml110" for="">手机号：</label>
                        <form:input id="customerPhone" path="order.customerPhone" htmlEscape="false" maxlength="16" class="itemInput"
                                    readOnly="true"/>
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" for="">项目经理：</label>
                        <form:input id="itemManager" path="order.itemManager" htmlEscape="false" maxlength="16" class="itemInput"
                                    readOnly="true"/>
                        <label class="f14 col3 ml1em ml110" for="">质检员：</label>
                        <form:input  id="orderInspector" path="order.orderInspector" htmlEscape="false" maxlength="16" class="itemInput"
                                    readOnly="true"/>
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" for="">工程模式：</label>
                            <%-- <input disabled="true"   type="text">${fns:getDictLabel(bizOrderComplaint.order.projectMode, 'project_mode','')}</input> --%>

                        <form:input id="projectMode" path="order.projectMode" htmlEscape="false" maxlength="16" class="itemInput"
                                    readOnly="true"/>
                        <label class="f14 col3 ml110" for="">工程区域：</label>
                        <form:input id="engineDepartName" path="order.engineDepartName" htmlEscape="false" maxlength="16" class="itemInput"
                                    readOnly="true"/>
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" for="">订单编号：</label>
                        <form:input id="orderNumber"  path="order.orderNumber" htmlEscape="false" maxlength="16" class="itemInput"
                                    readOnly="true"/>
                        <label class="f14 col3 ml110" for="">小区名称：</label>
                        <form:input  id="communityName" path="order.communityName" htmlEscape="false" maxlength="16" class="itemInput"
                                    readOnly="true"/>
                    </li>
                </ul>
            </div>
            <div>
                <div class="item pl152">投诉信息</div>
                <div class="pl152 bg_e clearfix">
                    <ul class="col3 f14 mb20 comp">
                        <li class="InfoTit">
                            <label class="f14 col3" for="">投诉来源：</label>

                            <form:select onchange="delectDiv()" cssStyle="width:190px" id="complaintSource"
                                         path="complaintSource" class="input-medium">
                                <form:option value="" label=""/>
                                <form:options items="${fns:getDictList('complaintSource')}" itemLabel="label"
                                              itemValue="value" htmlEscape="false"/>
                            </form:select>
                            <span class="must2">* <span id='complaintSourceText'></span></span>
                        </li>
                        <li class="InfoTit">
                            <label class="f14 col3" for="">投诉人：</label>
                            <form:input id="complaintPersonName" path="complaintPersonName" htmlEscape="false"
                                        maxlength="16" class="itemInput"/>
                        </li>
                        <li class="InfoTit">
                            <label class="f14 col3" for="">投诉人手机号：</label>
                            <form:input id="complaintPersonPhone" onblur="checkPhone()" path="complaintPersonPhone"
                                        htmlEscape="false" maxlength="16" class="itemInput"/>
                        </li>
                    </ul>
                    <section class="itemWrapper">
                        <div class="mb1d mb12 itemList">
                            <div class="lab bor_btm_d7 divClass">
                                <p class="f16 col3 bold pb20">投诉问题1</p>
                                <p class="col3 f14 mb20 clearfix">
                                    <label class="f14 col3" for="">问题分类：</label>
                                    <select id="select1" onchange="gradeChange(this)"
                                            name="bOrContPros[1].complaintProblemTypeId" class="input-medium">
                                    </select>
                                    <span class="must2"> *<span id='select1Text'></span> </span>
                                    <span id="spanid1a" class="f14 col3 ml20"> 对应任务包：</span>
                                    <span id="spanid2a" class="f14 col3 ml30"> 被投诉对象: </span>
                                <div id="div1a"></div>
                                <div id="div2a"></div>
                                <input id="input1" type="hidden" value=""></input>
                                </p>


                                <div class="col3 f14 mb20 clearfix">
                                    <span class="must2"> *</span>
                                    <span class="fl pt8">问题事项：</span>
                                    <div id="p1a" onchange="getCheckbox1(this)" class="overflow checkWrap required">
                                    </div>
                                    <input id="getInitValue" type="hidden"   value=""></input>
                                </div>
                                 <div class="col3 f14 mb20 clearfix">
	                                <span id="executeTimeLimit1" class="fl pt8"> 执行时限：</span>天
	                                <div id="executeTimeLimitDivid1"></div>
                    			 </div>

                                <div class="col3 f14 pb20 clearfix">
                                    <span class="fl pt8">问题内容：</span>
                                    <textarea id="complaintextareaId" class="complainText"
                                              name="bOrContPros[1].complaintProblemNr" class="complainText"
                                              onkeyup="this.value = this.value.substring(0,300)"
                                              placeholder="最多输入300个汉字"  maxLength="300"></textarea>
                                </div>
                                <div class="col3 f14 pb20 clearfix">
                                    <span class="fl pt8">上传附件：</span>
                                    <div class="pics font0 clearfix">
                                        <div class="pic camera" href="javascript:void(0)">
                                            <img id="imgPreview1" src="${ctxStatic}/bizOrderComplaint/images/upPic.png"
                                                 alt="">
                                            <input type="file" name="bOrContPros[1].photo" accept="image/*"
                                                   onchange="PreviewImage1(this)">
                                                   <a class="del_pic" href="javascript:void(0)"></a>
                                        </div>
                                        <div class="pic camera" href="javascript:void(0)">
                                            <img id="imgPreview2" src="${ctxStatic}/bizOrderComplaint/images/upPic.png"
                                                 alt="">
                                            <input type="file" name="bOrContPros[1].photo" accept="image/*"
                                                   onchange="PreviewImage2(this)">
                                                   <a class="del_pic" href="javascript:void(0)"></a>
                                        </div>
                                        <div class="pic camera" href="javascript:void(0)">
                                            <img id="imgPreview3" src="${ctxStatic}/bizOrderComplaint/images/upPic.png"
                                                 alt="">
                                            <input type="file" name="bOrContPros[1].photo" accept="image/*"
                                                   onchange="PreviewImage3(this)">
                                                   <a class="del_pic" href="javascript:void(0)"></a>
                                        </div>
                                        <div class="pic camera" href="javascript:void(0)">
                                            <img id="imgPreview4" src="${ctxStatic}/bizOrderComplaint/images/upPic.png"
                                                 alt="">
                                            <input type="file" name="bOrContPros[1].photo" accept="image/*"
                                                   onchange="PreviewImage4(this)">
                                                   <a class="del_pic" href="javascript:void(0)"></a>
                                        </div>
                                        <div class="pic camera" href="javascript:void(0)">
                                            <img id="imgPreview5" src="${ctxStatic}/bizOrderComplaint/images/upPic.png"
                                                 alt="">
                                            <input type="file" name="bOrContPros[1].photo" accept="image/*"
                                                   onchange="PreviewImage5(this)">
                                                   <a class="del_pic" href="javascript:void(0)"></a>
                                        </div>
                                        <div class="pic camera" href="javascript:void(0)">
                                            <img id="imgPreview6" src="${ctxStatic}/bizOrderComplaint/images/upPic.png"
                                                 alt="">
                                            <input type="file" name="bOrContPros[1].photo" accept="image/*"
                                                   onchange="PreviewImage6(this)">
												<a class="del_pic" href="javascript:void(0)"></a>

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport2">
                            <div class="alertMask">
                                <div class="maskWrapper">
                                    <p class="col_3 maskTit">确认提交</p>
                                    <div class="font28 col_6 maskContent"></div>
                                    <div class="maskBtns clearfix twoBtns">
                                        <a class="maskBtn font33 col_fdfcfa" href="javascript:void(0);" onclick="sure()">
                                           	确定
                                        </a>
                                        <a class="maskBtn font33 col_0780ec" href="javascript:void(0);" onclick="cancel()">取消</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- <div class="mb1d mb12 rela undis">
                            <div class="lab bor_btm_d7">
                                <p class="f16 col3 bold pb20">投诉问题2</p>
                                <p class="col3 f14 mb20 clearfix">
                                    <label class="f14 col3" for="">问题分类：</label>
                                    <select class="shop">
                                        <option value="水电修整">水电修整</option>
                                        <option value="泥瓦补齐">泥瓦补齐</option>
                                        <option value="墙地砖安装">墙地砖安装</option>
                                    </select>
                                    <span class="f14 col3 ml20">对应任务包：水电类</span>
                                    <span class="f14 col3 ml30">被投诉对象：项目经理 工人</span>
                                </p>
                                <div class="col3 f14 mb20 clearfix">
                                    <span class="fl pt8">问题事项：</span>
                                    <p class="overflow checkWrap">
                                        <input type="checkbox" id="one" name="one" value="one">
                                        <label class="nochecked" data-name="one" for="one">跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸</label>
                                        <input type="checkbox" id="two" name="two" value="two">
                                        <label class="nochecked" data-name="two" for="two">跳</label>
                                        <input type="checkbox" id="three" name="three" value="three">
                                        <label class="nochecked" data-name="three" for="three">跳闸</label>
                                        <input type="checkbox" id="four" name="four" value="four">
                                        <label class="nochecked" data-name="four" for="four">跳闸跳</label>
                                        <input type="checkbox" id="five" name="five" value="five">
                                        <label class="nochecked" data-name="five" for="five">跳闸跳闸</label>
                                    </p>
                                </div>
                                <div class="col3 f14 pb20 clearfix">
                                    <span class="fl pt8">问题内容：</span>
                                    <textarea class="complainText" placeholder="最多输入100个汉字"></textarea>
                                </div>
                                <div class="col3 f14 pb20 clearfix">
                                    <span class="fl pt8">上传附件：</span>
                                    <div class="pics font0 clearfix">
                                        <div class="pic camera" href="javascript:void(0)">
                                            <img src="../../images/upPic.png" alt="">
                                            <input type="file" accept="image/*" onchange="preview(this)">
                                        </div>
                                        <div class="pic">
                                            <img src="../../images/eg.png" alt="">
                                            <a class="del_pic" href="javascript:void(0)"></a>
                                        </div>
                                        <div class="pic">
                                            <img src="../../images/eg.png" alt="">
                                            <a class="del_pic" href="javascript:void(0)"></a>
                                        </div>
                                        <div class="pic">
                                            <img src="../../images/eg.png" alt="">
                                            <a class="del_pic" href="javascript:void(0)"></a>
                                        </div>
                                        <div class="pic">
                                            <img src="../../images/eg.png" alt="">
                                            <a class="del_pic" href="javascript:void(0)"></a>
                                        </div>
                                        <div class="pic">
                                            <img src="../../images/eg.png" alt="">
                                            <a class="del_pic" href="javascript:void(0)"></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <a class="delItemBtn" href="javascript:;">删除事项</a>
                        </div> -->
                    </section>
                    <div class="btnWrapper">
                        <a class="btn addBtn" href="javascript:void(0);">添加事项</a>
                        <a class="btn subBtn" onclick="submitForm();">提交</a>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <div class="mask font0 undis "  style="height: 40px;over-flow:auto;">
    <div class="bg_f tabWrap">
        <p class="maskTit">匹配订单</p>
        <div class="pb20">
            <input class="searchText" type="text" placeholder="请输入客户姓名/手机号/订单编号进行查询">
            <a class="maskSearchBtn" href="javascript:;" onclick="relateOrder()">查询</a>
        </div>
        <div>
            <table class="maskTable">

            </table>
        </div>
        <p class="maskTip">每个售后工单只能匹配一个订单，请核实后完成匹配</p>
        <div class="maskBtnWrapper">
            <a class="maskBtn maskCancleBtn">取消</a>
        </div>
    </div>
</div>

</form:form>

</body>
</html>