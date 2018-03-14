<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="email=no" name="format-detection">
    <title>投诉问题上报</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/complaintForOther/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/complaintForOther/css/complain.css"/>
</head>

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
<body>

<br/>
<div class="font0">
    <header><h2 class="title">投诉问题上报</h2></header>

    <form id="subForm"
          action="${ctx}/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal/savePreComplaintInfo"
          method="post">
        <div><input type="text" name="orderId" value="${map.orderId}" id="orderId" hidden="hidden"></div>
        <div><input type="text" name="id" value="${preComplaintId}" id="preComplaintId" hidden="hidden">
        </div>
        <section class="con">
            <div>
                <div class="item pl152">订单信息</div>
                <ul class="pl152 bg_e tab pt32 pb32 info font0">
                    <li class="mb20 font0">
                        <label class="f14 col3 ml2em">门店：</label>
                        <select class="shop" id="storeId">
                            <option selected="selected" value="${map.storeId}">${map.storeName}</option>
                        </select>

                        <%--<a class="searchBtn" href="javascript:;">点击查询项目</a>--%>
                        <%--<span class="must">*</span>--%>
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" id="customerName">客户姓名：</label>
                        <input class="itemInput" readonly type="text" value="${map.customerName}">
                        <label class="f14 col3 ml1em ml110" id="customerPhone">手机号：</label>
                        <input class="itemInput" readonly type="text" value="${map.customerPhone}">
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" id="managerName">项目经理：</label>
                        <input class="itemInput" readonly type="text" value="${map.managerName}">
                        <label class="f14 col3 ml1em ml110" id="pqcName">质检员：</label>
                        <input class="itemInput" readonly type="text" value="${map.pqcName}">
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" id="projectMode">工程模式：</label>
                        <input class="itemInput" readonly type="text"
                               value="${fns:getDictLabel(map.projectMode,'project_mode' ,'' )}">
                        <label class="f14 col3 ml110" id="customerAddress">工程地址：</label>
                        <input class="itemInput" readonly type="text" value="${map.customerInfo}">
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" id="orderNumber">订单编号：</label>
                        <input class="itemInput" readonly type="text" value="${map.orderNumber}">
                        <label class="f14 col3 ml110" id="communityName">小区名称：</label>
                        <input class="itemInput" readonly type="text" value="${map.communityName}">
                    </li>
                </ul>
            </div>
            <div>
                <div class="item pl152">投诉信息</div>
                <div class="pl152 bg_e clearfix">
                    <ul class="col3 f14 mb20 comp">
                        <li class="InfoTit">
                            <label class="f14 col3">投诉来源：</label>
                            <select class="source" readonly>
                                <option selected="selected">${fns:getDictLabel(map.complaintSource,'complaintSource' ,'' )}</option>

                            </select>

                        </li>
                        <li class="InfoTit">
                            <label class="f14 col3">投诉人：</label>
                            <input class="infoInput" type="text" readonly value="${map.complaintName}">
                        </li>
                        <li class="InfoTit">
                            <label class="f14 col3">投诉人手机号：</label>
                            <input class="infoInput" type="text" readonly value="${map.complaintPhone}">
                        </li>
                    </ul>
                    <section class="itemWrapper">
                        <div class="mb1d mb12 itemList">
                            <div class="lab bor_btm_d7">
                                <p class="f16 col3 bold pb20">投诉问题1</p>
                                <p class="col3 f14 mb20 clearfix">
                                    <label class="f14 col3">问题分类：</label>
                                    <select class="shop" id="typeId0" onchange="findItemByTypeId(this)"
                                            name="list[0].typeId">

                                    </select>
                                    <span class="f14 col3 ml20"></span>
                                    <span class="f14 col3 ml30"></span>
                                </p>
                                <div class="col3 f14 mb20 clearfix">
                                    <span class="fl pt8">问题事项：</span>
                                    <p class="overflow checkWrap">

                                    </p>
                                </div>
                                <div class="col3 f14 pb20 clearfix">
                                    <span class="fl pt8">问题内容：</span>
                                    <textarea class="complainText" placeholder="最多输入300个汉字"
                                              name="list[0].itemDescribe"
                                              onkeyup="this.value = this.value.substring(0,300)">${map.describe}</textarea>
                                </div>
                                <div class="col3 f14 pb20 clearfix">
                                    <span class="fl pt8">上传附件：</span>
                                    <div class="pics font0 clearfix">
                                        <div class="pic camera" href="javascript:void(0)">
                                            <img src="${ctxStatic}/modules/complaintForOther/images/upPic.png" alt="">
                                            <input type="file" accept="image/*" onchange="preview(this)">
                                            <input type="text" hidden="hidden" id="shit" value="1">
                                        </div>


                                        <c:forEach items="${list}" var="map">
                                            <div class="pic">
                                                <img src="${pageContext.request.contextPath}${map.url}">
                                                <input type="text" hidden="hidden" value="${map.id}">
                                                <a class="del_pic" href="javascript:void(0)"></a>
                                            </div>

                                        </c:forEach>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <div class="btnWrapper">
                        <a class="btn addBtn" href="javascript:void(0)">添加事项</a>
                        <a class="btn subBtn" href="javascript:void(0)" id="submitId">提交</a>
                        <a class="btn subBtn" href="${ctx}/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal/list">返回</a>
                    </div>
                </div>
            </div>
        </section>
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
    </form>
</div>

</div>
<script type="text/javascript" src="${ctxStatic}/modules/complaintForOther/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspicForComplaint.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
<script>




    function sure(){


        $("#submitId").unbind("click");
        $("#subForm").submit();

    }

    function cancel(){

        $("#subReport2").hide();


    }



























    $(function () {

        $('.addBtn').scrollTop($('.addBtn')[0].scrollHeight);
        $('.addBtn').focus();

    })


    findTypeByStoreId("typeId0");


    function findTypeByStoreId(typeId) {


        var html = '<option value=""></option>';
        var storeId = $("#storeId").val();
        $.ajax({
            url: "${ctx}/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal/findComplaintTypeByStoreId",
            data: {storeId: storeId},
            success: function (data) {

                if (data.length > 0) {

                    for (var i = 0; i < data.length; i++) {

                        html += '<option value="' + data[i].typeId + '">' + data[i].typeName + '</option>';

                    }

                    $("#" + typeId).html(html);

                }


            }


        })


    }

    function findItemByTypeId(obj) {

        var itemName = $(obj).attr("name").split(".");

        var typeId = $(obj).val();
//        var text = $(obj).parent().prev().text();
//        var text2 = text.split("投诉问题");
//
//
//        var index = parseInt(text2[1]) - 1


        var html = '';

        if ("" != typeId) {


            $.ajax({
                url: "${ctx}/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal/findComplaintItemByTypeId",
                data: {typeId: typeId},
                success: function (data) {

                    if (data.length > 0) {
                        var packName = data[0].packName
                        var dealPersonType = data[0].dealPersonType
                        for (var i = 0; i < data.length; i++) {

                            if (undefined != data[i].itemName) {
                                html += '<input type="checkbox" value="' + data[i].itemId + '"  class="itemIdClass"><label class="nochecked" data-name="two" for="two">' + data[i].itemName + '</label>';


                            }


                        }
                        html += '<input type="text" name="' + itemName[0] + '.itemId"  hidden="hidden"/> ';

                        $(obj).parent().parent().find(".checkWrap").html(html);
                        if (undefined == packName) {

                            $(obj).next().text("对应任务包：" + "无");
                        } else {

                            $(obj).next().text("对应任务包：" + packName);
                        }

                        $(obj).next().next().text("被投诉对象：" + dealPersonType);
                    } else {


                    }


                }


            })


        } else {

            $(obj).parent().parent().find(".checkWrap").html(html);
            $(obj).next().text("对应任务包：" + "无");
            $(obj).next().next().text("被投诉对象：" + "无");

        }


    }


    $("#submitId").bind("click", submit);


    function submit() {

        var booleanNum = true;

        $(".complainText").each(function () {

            if(booleanNum){

                if($(this).val()==""){

                    alert("投诉项的问题内容为必填项")
                    booleanNum=false;
                    return;
                }

            }


        })










        $(".bold").each(function () {

            $(this).next().find(".shop").each(function () {


                if (booleanNum) {

                    if ($(this).val() == "" || $(this).val() == undefined) {

                        alert("你还有没选择的投诉分类")
                        booleanNum = false;
                        return;
                    }

                }


                if (booleanNum) {

                    var itemChoose = 1;

                    $(this).parent().next().find(".itemIdClass").each(function () {


                        if ($(this).attr("checked") == "checked") {

                            itemChoose = 2;

                        }
                    })

                    if (itemChoose == 1) {
                        alert("你还有没选择的投诉项")
                        booleanNum = false;
                        return;
                    }

                }

            })


        })
        var orderId = $("#orderId").val();

        if ("" == orderId || undefined == orderId) {
            alert("请关联订单再提交");
            booleanNum = false;

        }

        if (booleanNum) {
            var length =$(".bold").length;
            $(".maskContent").text("本次创建 "+length+" 个投诉事项，将分别发送信息到被投诉人员");

            $("#subReport2").show();


        }


    }


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

    $(document).on('click', '.searchBtn', function () {
        $(".mask").show();
    });
    $(document).on('click', '.maskCancleBtn', function () {
        $(".mask").hide();
    });
    $(document).on('click', '.del_pic', function () {

        if ($(this).prev().val() != "") {
            var picId = $(this).prev().val();

            deletePic(picId);

        } else {


            var numReal = $(this).prev().attr("id");
            var hiddenImg = "num" + numReal


            $(("#" + hiddenImg)).remove();
        }


        $(this).parent().remove();

    });
    $(document).on('click', '.checkWrap label', function () {
        if ($(this).hasClass('nochecked')) {
            $(this).parent().parent().find("label").each(function () {

                $(this).removeClass('checked').addClass('nochecked');
                $(this).parent().find('input').removeClass('checked');
                $(this).parent().find('input').attr({"checked": false});

            })

            $(this).removeClass('nochecked').addClass('checked');

            $(this).prev().attr({"checked": true});
            $(this).parent().find("input[type='text']").val($(this).prev().val())
        } else {
            $(this).removeClass('checked').addClass('nochecked');

            $(this).prev().attr({"checked": false});
            $(this).parent().find("input[type='text']").val($(this).prev().val())
        }
    });


    var num = 2;
    $(document).on('click', '.addBtn', function () {

        $('.addBtn').scrollTop($('.addBtn')[0].scrollHeight);
        $('.addBtn').focus();

        var number = $('.itemList').length;

        var index = number + 1;

        var index2 = num - 1;
        var itemList = '<div class="mb1d mb12 rela itemList">' +
            '<div class="lab bor_btm_d7">' +
            '<p class="f16 col3 bold pb20">投诉问题' + index + '</p>' +
            '<p class="col3 f14 mb20 clearfix">' +
            '<label class="f14 col3" >问题分类：</label>' +
            '<select class="shop" onchange="findItemByTypeId(this)" id="typeId' + num + '" name="list[' + index2 + '].typeId">' +
            '</select>' +
            '<span class="f14 col3 ml20"></span>' +
            '<span class="f14 col3 ml30"></span>' +
            '</p>' +
            '<div class="col3 f14 mb20 clearfix">' +
            '<span class="fl pt8">问题事项：</span>' +
            '<p class="overflow checkWrap">' +

            '</p>' +
            '</div>' +
            '<div class="col3 f14 pb20 clearfix">' +
            '<span class="fl pt8">问题内容：</span>' +
            '<textarea class="complainText" placeholder="最多输入300个汉字" name="list[' + index2 + '].itemDescribe"  onkeyup="this.value = this.value.substring(0,300)"></textarea>' +
            '</div>' +
            '<div class="col3 f14 pb20 clearfix">' +
            '<span class="fl pt8">上传附件：</span>' +
            '<div class="pics font0 clearfix">' +
            ' <div class="pic camera" href="javascript:void(0)">' +
            '<img src="${ctxStatic}/modules/complaintForOther/images/upPic.png" alt="">' +
            '<input type="file" accept="image/*" onchange="preview(this)">' +

            ' </div>' +
            ' </div>' +
            ' </div>' +
            ' </div>' +
            '<a class="delItemBtn" href="javascript:void(0;)">删除事项</a>' +

            ' </div>'


        findTypeByStoreId("typeId" + num);
        num++;
        $('.itemWrapper').append(itemList);

    })


    //图片上传显示
    function preview(file) {
        var itemList = $(file).parent().parent().parent().parent();
        var picNamePre = $(file).parent().parent().parent().prev().find(".complainText").attr("name");


        var numPre = picNamePre.split("]");
        var num = numPre[0].split("[")[1];


        var picLength = $(itemList).find(".pic").length;


        var prevDiv = $('.bd_camera');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {


                $(file).parent().parent().append(('  <div class="pic"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="' + num + '--' + picLength + '"/> <a class="del_pic" href="javascript:void(0)"></a> </div>'));
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            console.log(file, file.value);
        }
    }


    function uploadpic(pic, obj) {

        var value = $(obj).attr("id");

        var val = value.split("--")[0]
        var realVal = parseInt(val);

        var hiddenForm = document.getElementById("subForm");
        var input = document.createElement("input");


        if (pic) {


            input.setAttribute("id", "num" + value);
            input.setAttribute('hidden', 'hidden');
            input.setAttribute('name', 'list[' + realVal + '].photos');
            input.setAttribute("type", "text");
            input.setAttribute("value", pic);
            hiddenForm.appendChild(input);

        }
    }


    function deletePic(picId) {

        $.ajax({

            url: "${ctx}/complaintforotherdepartmentDeal/complaintForOtherDepartMentDeal/deletePic",
            data: {picId: picId},
            success: function (data) {

                //贼烦,这个页面


            }

        })


    }

</script>
</body>
</html>