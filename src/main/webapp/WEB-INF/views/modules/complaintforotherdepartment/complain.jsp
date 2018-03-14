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
<body>

<br/>
<div class="font0">
    <header><h2 class="title">投诉问题上报</h2></header>

    <form id="subForm" action="${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/saveComplaintInfo"
          method="post">
        <div><input type="text" name="orderId" value="" id="orderId" hidden="hidden"></div>
        <section class="con">
            <div>
                <div class="item pl152">订单信息</div>
                <ul class="pl152 bg_e tab pt32 pb32 info font0">
                    <li class="mb20 font0">
                        <label class="f14 col3 ml2em" id="storeId">门店：</label>
                        <select class="shop">

                        </select>

                        <a class="searchBtn" href="javascript:;">点击查询项目</a>
                        <span class="must">*</span>
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" id="customerName">客户姓名：</label>
                        <input class="itemInput" readonly type="text">
                        <label class="f14 col3 ml1em ml110" id="customerPhone">手机号：</label>
                        <input class="itemInput" readonly type="text">
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" id="managerName">项目经理：</label>
                        <input class="itemInput" readonly type="text">
                        <label class="f14 col3 ml1em ml110" id="pqcName">质检员：</label>
                        <input class="itemInput" readonly type="text">
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" id="projectMode">工程模式：</label>
                        <input class="itemInput" readonly type="text">
                        <label class="f14 col3 ml110" id="customerAddress">工程地址：</label>
                        <input class="itemInput" readonly type="text">
                    </li>
                    <li class="col3 font0 mb20">
                        <label class="f14 col3" id="orderNumber">订单编号：</label>
                        <input class="itemInput" readonly type="text">
                        <label class="f14 col3 ml110" id="communityName">小区名称：</label>
                        <input class="itemInput" readonly type="text">
                    </li>
                </ul>
            </div>
            <div>
                <div class="item pl152">投诉信息</div>
                <div class="pl152 bg_e clearfix">
                    <%--<ul class="col3 f14 mb20 comp">--%>
                    <%--<li class="InfoTit">--%>
                    <%--<label class="f14 col3" for="">投诉来源：</label>--%>
                    <%--<select class="source">--%>
                    <%--<option value="客户">客户</option>--%>
                    <%--<option value="设计师">设计师</option>--%>
                    <%--<option value="材料部">材料部</option>--%>
                    <%--</select>--%>
                    <%--<span class="must2">*</span>--%>
                    <%--</li>--%>
                    <%--<li class="InfoTit">--%>
                    <%--<label class="f14 col3" for="">投诉人：</label>--%>
                    <%--<input class="infoInput" type="text">--%>
                    <%--</li>--%>
                    <%--<li class="InfoTit">--%>
                    <%--<label class="f14 col3" for="">投诉人手机号：</label>--%>
                    <%--<input class="infoInput" type="text">--%>
                    <%--</li>--%>
                    <%--</ul>--%>
                    <section class="itemWrapper">
                        <div class="mb1d mb12 itemList">
                            <!--	<!--<div class="lab bor_btm_d7">-->
                            <!--<p class="f16 col3 bold pb20">投诉问题1</p>-->
                            <!--<p class="col3 f14 mb20 clearfix">-->
                            <!--<label class="f14 col3" for="">问题分类：</label>-->
                            <!--<select class="shop">-->
                            <!--<option value="水电修整">水电修整</option>-->
                            <!--<option value="泥瓦补齐">泥瓦补齐</option>-->
                            <!--<option value="墙地砖安装">墙地砖安装</option>-->
                            <!--</select>-->
                            <!--<span class="f14 col3 ml20">对应任务包：水电类</span>-->
                            <!--<span class="f14 col3 ml30">被投诉对象：项目经理 工人</span>-->
                            <!--</p>-->
                            <!--<div class="col3 f14 mb20 clearfix">-->
                            <!--<span class="fl pt8">问题事项：</span>-->
                            <!--<p class="overflow checkWrap">-->
                            <!--<input type="checkbox" id="one" name="one" value="one">-->
                            <!--<label class="nochecked" data-name="one" for="one">跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸</label>-->
                            <!--<input type="checkbox" id="two" name="two" value="two">-->
                            <!--<label class="nochecked" data-name="two" for="two">跳</label>-->
                            <!--<input type="checkbox" id="three" name="three" value="three">-->
                            <!--<label class="nochecked" data-name="three" for="three">跳闸</label>-->
                            <!--<input type="checkbox" id="four" name="four" value="four">-->
                            <!--<label class="nochecked" data-name="four" for="four">跳闸跳</label>-->
                            <!--<input type="checkbox" id="five" name="five" value="five">-->
                            <!--<label class="nochecked" data-name="five" for="five">跳闸跳闸</label>-->
                            <!--</p>-->
                            <!--</div>-->
                            <div class="col3 f14 pb20 clearfix">
                                <span class="fl pt8"> 问题内容：</span>
                                <textarea class="complainText" placeholder="最多输入300个汉字" name="complaintText"
                                          onkeyup="this.value = this.value.substring(0,300)"></textarea>

                            </div>
                            <div class="col3 f14 pb20 clearfix">
                                <span class="fl pt8">上传附件：</span>
                                <div class="pics font0 clearfix">
                                    <div class="pic camera" href="javascript:void(0)">
                                        <img src="${ctxStatic}/modules/complaintForOther/images/upPic.png" alt="">
                                        <input type="file" accept="image/*" onchange="preview(this)">
                                        <input type="text" hidden="hidden" id="shit" value="1">
                                    </div>


                                    <%----%>
                                    <%--<div class="pic">--%>
                                    <%--<img src="${ctxStatic}/modules/complaintForOther/images/eg.png" alt="">--%>
                                    <%--<a class="del_pic" href="javascript:void(0)"></a>--%>
                                    <%--</div>--%>
                                    <%--<div class="pic">--%>
                                    <%--<img src="${ctxStatic}/modules/complaintForOther/images/eg.png" alt="">--%>
                                    <%--<a class="del_pic" href="javascript:void(0)"></a>--%>
                                    <%--</div>--%>
                                    <%--<div class="pic">--%>
                                    <%--<img src="${ctxStatic}/modules/complaintForOther/images/eg.png" alt="">--%>
                                    <%--<a class="del_pic" href="javascript:void(0)"></a>--%>
                                    <%--</div>--%>
                                    <%--<div class="pic">--%>
                                    <%--<img src="${ctxStatic}/modules/complaintForOther/images/eg.png" alt="">--%>
                                    <%--<a class="del_pic" href="javascript:void(0)"></a>--%>
                                    <%--</div>--%>
                                    <%--<div class="pic">--%>
                                    <%--<img src="${ctxStatic}/modules/complaintForOther/images/eg.png" alt="">--%>
                                    <%--<a class="del_pic" href="javascript:void(0)"></a>--%>
                                    <%--</div>--%>


                                </div>
                            </div>
                        </div>

                    </section>
                    <div class="btnWrx`apper">
                        <!--<a class="btn addBtn" href="javacript:;">添加事项</a>-->
                        <a class="btn subBtn" href="#" id="subId">提交</a>
                        <a class="btn subBtn"
                           href="${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/list">返回</a>
                    </div>
                </div>
            </div>
        </section>

    </form>
</div>
<div class="mask font0 undis">
    <div class="bg_f tabWrap">
        <p class="maskTit">匹配订单</p>
        <div class="pb20">
            <input class="searchText" type="text" placeholder="请输入客户姓名/手机号/订单编号进行查询">
            <a class="maskSearchBtn" href="javascript:;" onclick="relateOrder()">查询</a>
        </div>
        <div style="height:200px;overflow-y:scroll;">
            <table class="maskTable">
                <%--<tr>--%>
                <%--<th>客户姓名</th>--%>
                <%--<th>客户手机号</th>--%>
                <%--<th>工程地址</th>--%>
                <%--<th>项目经理</th>--%>
                <%--<th>操作</th>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td></td>--%>
                <%--<td></td>--%>
                <%--<td></td>--%>
                <%--<td><a class="maskChooseBtn" href="javascript:;">选择</a></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td></td>--%>
                <%--<td></td>--%>
                <%--<td></td>--%>
                <%--<td><a class="maskChooseBtn" href="javascript:;">选择</a></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td></td>--%>
                <%--<td></td>--%>
                <%--<td></td>--%>
                <%--<td></td>--%>
                <%--<td><a class="maskChooseBtn" href="javascript:;">选择</a></td>--%>
                <%--</tr>--%>
            </table>
        </div>
        <p class="maskTip">每个售后工单只能匹配一个订单，请核实后完成匹配</p>
        <div class="maskBtnWrapper">
            <a class="maskBtn maskCancleBtn">取消</a>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctxStatic}/modules/complaintForOther/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
<script>

    $("#subId").bind("click", submit);

    function submit() {





        var orderId = $("#orderId").val();

        if ("" == orderId || undefined == orderId) {
            alert("请关联订单再提交");
            return;

        } else {

            if ($(".complainText").val().trim() == "") {
                alert("问题内容为必填项");
                return;

            }else{

                $("#subId").unbind("click");
                $("#subForm").submit();

            }


        }


    }


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
            data: {"text": $(".searchText").val()},
            success: function (data) {

                if (null != data && data.length > 0) {


                    for (var v = 0; v < data.length; v++) {

                        if (data[v].managerName == undefined) {

                            html += ' <tr>'
                                + '<td>' + data[v].customerName + '</td>'
                                + '<td>' + data[v].customerPhone + '</td>'
                                + ' <td>' + data[v].customerAddress + '</td>'
                                + ' <td>' + "无" + '</td>'
                                + ' <td><a class="maskChooseBtn" href="javascript:;" onclick="chooseOrder(' + data[v].orderId + ')">选择</a></td>'
                                + ' </tr>';

                        } else {
                            html += ' <tr>'
                                + '<td>' + data[v].customerName + '</td>'
                                + '<td>' + data[v].customerPhone + '</td>'
                                + ' <td>' + data[v].customerAddress + '</td>'
                                + ' <td>' + data[v].managerName + '</td>'
                                + ' <td><a class="maskChooseBtn" href="javascript:;" onclick="chooseOrder(' + data[v].orderId + ')">选择</a></td>'
                                + ' </tr>';


                        }


                    }

                    $(".maskTable").html(html);
                } else {

                    alert("请输入正确的订单信息")
                }


            }


        })

    }


    function chooseOrder(orderId) {


        $.ajax({

            url: "${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/findOrderByText",
            data: {"orderId": orderId},
            success: function (data) {

                if (null != data) {
                    $(".shop").html('<option value="' + data[0].storeName + '" selected="selected">' + data[0].storeName + '</option>')


                    $("#customerName").next().val(data[0].customerName);
                    $("#customerPhone").next().val(data[0].customerPhone);
                    $("#managerName").next().val(data[0].managerName);
                    $("#pqcName").next().val(data[0].pqcName);
                    $("#projectMode").next().val(data[0].projectMode);
                    $("#customerAddress").next().val(data[0].customerAddress);
                    $("#orderNumber").next().val(data[0].orderNumber);
                    $("#communityName").next().val(data[0].communityName);

                    $("#orderId").val(orderId);
                    $(".mask").hide();
                }


            }


        })


    }


    $(document).on('click', '.delItemBtn', function () {
        $(this).parent().remove();
    });

    $(document).on('click', '.searchBtn', function () {
        $(".mask").show();
    });
    $(document).on('click', '.maskCancleBtn', function () {
        $(".mask").hide();
    });
    $(document).on('click', '.del_pic', function () {

        var numReal = $(this).prev().attr("id");
        $(("#num" + numReal)).remove();
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
            $(this).parent().find('input').addClass('checked');
            $(this).parent().find('input').attr({"checked": true});
        } else {
            $(this).removeClass('checked').addClass('nochecked');
            $(this).parent().find('input').removeClass('checked');
            $(this).parent().find('input').attr({"checked": false});
        }
    });
    $(document).on('click', '.addBtn', function () {
        var num = ++$('.itemList').length;
        var itemList = '<div class="mb1d mb12 rela itemList">' +
            '<div class="lab bor_btm_d7">' +
            '<p class="f16 col3 bold pb20">投诉问题' + num + '</p>' +
            '<p class="col3 f14 mb20 clearfix">' +
            '<label class="f14 col3" for="">问题分类：</label>' +
            '<select class="shop">' +
            '<option value="水电修整">水电修整</option>' +
            '<option value="泥瓦补齐">泥瓦补齐</option>' +
            '<option value="墙地砖安装">墙地砖安装</option>' +
            '</select>' +
            '<span class="f14 col3 ml20">对应任务包：水电类</span>' +
            '<span class="f14 col3 ml30">被投诉对象：项目经理 工人</span>' +
            '</p>' +
            '<div class="col3 f14 mb20 clearfix">' +
            '<span class="fl pt8">问题事项：</span>' +
            '<p class="overflow checkWrap">' +
            '<input type="checkbox" id="five" name="five" value="five">' +
            '<label class="nochecked" data-name="five" for="five">跳闸跳闸</label>' +
            '</p>' +
            '</div>' +
            '<div class="col3 f14 pb20 clearfix">' +
            '<span class="fl pt8">问题内容：</span>' +
            '<textarea class="complainText" placeholder="最多输入300个汉字"  onkeyup="this.value = this.value.substring(0,300)"></textarea>' +
            '</div>' +
            '<div class="col3 f14 pb20 clearfix">' +
            '<span class="fl pt8">上传附件：</span>' +
            '<div class="pics font0 clearfix">' +
            '<div class="pic camera" href="javascript:void(0)">' +
            '<img src="${ctxStatic}/modules/complaintForOther/images/upPic.png" alt="">' +
            '<input type="file" accept="image/*" onchange="preview(this)">' +
            '</div>' +
            '<div class="pic">' +
            '<img src="${ctxStatic}/modules/complaintForOther/images/eg.png" alt="">' +
            '<a class="del_pic" href="javascript:void(0)"></a>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<a class="delItemBtn" href="javascript:;">删除事项</a>' +
            '</div>';
        $('.itemWrapper').append(itemList);
    })


    //图片上传显示
    function preview(file) {
        var shit = $("#shit").val();
        var prevDiv = $('.bd_camera');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                $('.pics').append(('  <div class="pic"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="' + shit + '"/> <a class="del_pic" href="javascript:void(0)"></a> </div>'));
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            console.log(file, file.value);
        }
    }


    function uploadpic(pic) {

        var hiddenForm = document.getElementById("subForm");
        var input = document.createElement("input");


        var shit = $("#shit").val();

        if (pic) {
            input.setAttribute("id", "num" + shit);
            input.setAttribute('hidden', 'hidden');
            input.setAttribute('name', 'photos');
            input.setAttribute("type", "text");
            input.setAttribute("value", pic);
            hiddenForm.appendChild(input);
            shit++;
            $("#shit").val(shit);
        }
    }
</script>
</body>
</html>