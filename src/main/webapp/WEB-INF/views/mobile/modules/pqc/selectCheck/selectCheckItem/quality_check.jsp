<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <title>质检</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/lib/lCalendar.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/quality_check.css"/>
    <link type="text/css" rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css">

    <style>
        .tit-pos {
            line-height: .68rem;
            text-align: center;
            margin-bottom: .24rem;
            color: #0780ec;
        }

        .font30 {
            font-size: .3rem;
        }

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
</head>
<body>
<div class="g-qua_check">
    <header>
        <a class="back_btn"
           href="${ctx }/app/pqc/selectCheckList/changeCheckItem?inspectId=${inspectId}&orderId=${orderId}&customerInfo=${customerInfo}"></a>
        <h2 class="title">质检</h2>
        <a class="change"
           href="${ctx }/app/pqc/selectCheckList/changeCheckItem?inspectId=${inspectId}&orderId=${orderId}&customerInfo=${customerInfo}">更改检查项</a>
    </header>
    <!-- /header -->
    <section class="qua_check">
        <p class="font30 tit-pos">${customerInfo}</p>
        <div class="funs">
            <span class="col3">上传照片</span>
            <div class="up camera" id="camera" href="javascript:void(0)">
                <img src="${ctxStatic}/mobile/modules/pqc/images/pic_load.png" alt="调取摄像头">
                <input type="file" accept="image/*" onchange="preview(this)"/>
            </div>
            <input type="text" hidden="hidden" id="shit" value="1">
            <a class="wtc_btn" href="javascript:void(0)">
                已拍<span id="picLength">${picLength }</span>张
            </a>
        </div>

        <form method="post" id="reportForm">

            <input type="text" hidden="hidden" id="num" name="num" value="">
            <input type="text" hidden="hidden" name="inspectId" value="${inspectId}"/>
            <input type="text" name="orderId" value="${orderId}" hidden="hidden"/>
            <input type="text" name="projectMode" value="${projectMode}" hidden="hidden"/>
            <input type="text" name="signNum" value="${signNum}" id="signNum" hidden="hidden"/>
            <input type="text" hidden="hidden" name="status" id="status"/>

            <ul class="item_lists">
                <c:forEach items="${list }" var="checkItem" varStatus="status">
                    <input type="text" hidden="hidden" name="checkItemId" value="${checkItem.checkItemId}"/>
                    <li class="mar_left3 pad_top3 pad_rgt2 bor_btm clearfix">
                        <p class="p_left font28">${checkItem.checkItemName }</p>
                        <p class="p_right">
                            <!-- 合格 -->
                            <c:if test="${checkItem.isOk!='0' }">
									<span class="radio_span marrgt1">
										<input type="radio" id="yes1" name="isOk" value="1">
										<label class="font26 checked" data-name="quality${status.index+1 } " for="yes1" onclick="reverseOk('${checkItem.checkItemId}',this)">合格</label>
									</span>
                                <span class="radio_span">
										<input type="radio" id="no1" name="quality" value="no1">
										<label class="font26" data-name="quality${status.index+1 } " for="no1"
                                               onclick="findIllegalModality('${checkItem.checkItemId}')"
                                               id="${checkItem.checkItemId}">不合格</label>
									</span>
                            </c:if>

                            <!-- 不合格 -->
                            <c:if test="${checkItem.isOk=='0' }">
									<span class="radio_span marrgt1">
										<input type="radio" id="yes1" name="isOk" value="1">
										<label class="font26" data-name="quality${status.index+1 } "
                                               for="yes1" onclick="reverseOk('${checkItem.checkItemId}',this)">合格</label>
									</span>
                                <span class="radio_span">
										<input type="radio" id="no1" name="quality" value="no1">
										<label class="font26  checked" data-name="quality${status.index+1 } " for="no1"
                                               onclick="findIllegalModality('${checkItem.checkItemId}')"
                                               id="${checkItem.checkItemId}">不合格</label>
									</span>
                            </c:if>
                        </p>
                    </li>
                </c:forEach>
                <div style="padding-top:300px;"></div>
            </ul>
        </form>
    </section>
    <footer>
        <a href="javascript:void(0)" id="zancun"><span class="btn font28 bor_rht">暂存</span></a>
        <a href="javascript:void(0)" id="tijiao"><span class="btn font28">提交报告</span></a>
    </footer>

    <!-- 照片弹出层 -->
    <div class="mask mask1 undis">
        <div class="pic_container clearfix" id="foo">
            <c:forEach items="${picList }" var="p">
                <div class="pic_wraper clearfix">
                    <img src="${baseUrl}${p.picUrl}" alt="质检照片"> <a class="del_btn" href="#"
                                                                    onclick="deletePic(${p.picId})">删除</a>
                </div>
            </c:forEach>
        </div>
        <a class="back" href="javascript:void(0)">返回</a>
    </div>

    <!-- 查看大图弹出层 -->
    <div class="mask mask2 undis">
        <div class="big_pic_wraper">
            <img class="big_pic" id="big_pic" alt="质检照片">
        </div>
    </div>

    <!-- 不合格弹框 -->
    <div class="mask mask3 undis" id="illegalDivId">
        <div class="obey">
            <div class="obey_header">违规形式</div>
            <form id="ajaxIllegalModalityFormId">
                <input type="text" name="inspectId" value="${inspectId}" hidden="hidden"/>
                <input type="text" name="orderId" value="${orderId}" hidden="hidden"/>
                <ul class="counts" id="illegalUlId">
                    <!-- js 遍历  填充 违规形式 -->
                </ul>
                <div class="obey_header">处理方式</div>
                <ul class="counts" id="handleWay">
                    <li class="obey_li">
                        <input type="checkbox" id="deal1" name="deal1" value="deal1">
                        <label class="checkbox" data-name="deal1" for="deal1">警告</label>
                    </li>
                    <li class="obey_li">
                        <input type="checkbox" id="deal2" name="deal2" value="deal2">
                        <label class="checkbox" data-name="deal2" for="deal2">现场整改</label>
                    </li>
                    <li class="obey_li" onclick="showChangeWay()">
                        <input type="checkbox" id="deal3" name="deal3" value="deal3">
                        <label class="checkbox" data-name="deal3" for="deal3">限期整改</label>
                        <div class="date_limite mar_left3" id="changeDate" onclick="showChangeWay() ">
                            <span>整改期限</span>
                            <input id="txtBeginDate" name="limitDate" class="date" type="text" readonly="" value=""
                                   placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01"/>
                        </div>
                        <div class="method mar_left3" id="changeWay">
                            <span class="span_left">整改方式</span>
                            <p class="p_right">
									<span class="" onclick="showChangeWay()">
										<input type="radio" id="yes" name="quality" value="yes">
										<label data-name="quality" for="yes" onclick="hideXianXia()"
                                               id="xianshang">线上整改</label>
									</span>
                                <span class="" onclick=showChangeWay()>
										<input type="radio" id="no" name="quality" value="no">
										<label data-name="quality" for="no" id="xianxia">线下整改</label>
									</span>
                            </p>
                        </div>
                    </li>
                    <li class="obey_li" id="punishMoneyLabelId">
                        <input type="text" name="managerFine">
                        <label class="checkbox" data-name="deal4" for="deal4" id="managerFineId">扣分罚款（项目经理）</label>

                        <div class="mar_left3 undis" id="fineDivId">
                            <div class="mar_btm50">
                                <span>项目经理：</span>
                                <input type="text" hidden="hidden" name="managerId" value="${managerId}"/>
                                <input class="short-text" type="text" name="managerScore"
                                       id="managerScoreId"><span>分</span>
                                <input class="short-text" type="text" name="managerMoney"
                                       id="managerMoneyId"><span>元</span>
                            </div>
                    </li>
                    <li class="obey_li" id="">
                        <input type="text" name="workerFine">
                        <label class="checkbox" data-name="deal5" for="deal5" id="workerFineId">扣分罚款（工人）</label>
                        <div class="mar_left3 undis" id="fineDivId2">
                            <div class="mar_btm50">
                                <span>工　　人：</span>
                                <input type="text" hidden="hidden" name="packId" id="packId"/>
                                <select class="long-option mar_btm50">
                                    <c:forEach items="${infoList }" var="workerInfo">
                                        <option value="${workerInfo.packId }">${workerInfo.packName}-${workerInfo.workerGroupLeaderName}</option>
                                    </c:forEach>
                                </select>
                                <div class="mar_btm50 mar_left144">
                                    <input class="short-text" type="text" name="workerGroupScore"
                                           id="workerScoreId"><span>分</span>
                                    <input class="short-text" type="text" name="workerGroupMoney"
                                           id="workerMoneyId"><span>元</span>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="obey_li" id="">
                        <input type="text" name="pqcFine" value="">
                        <label class="checkbox" data-name="deal6" for="deal6" id="pqcFineId">扣分罚款（质检员）</label>
                        <div class="mar_left3 undis" id="fineDivId3">
                            <div class="mar_btm50">
                                <span>质 检 员：</span>
                                <input type="text" hidden="hidden" name="inspectorId" value="${inspectorId}"/>
                                <input class="short-text" type="text" name="inspectorScore"
                                       id="pqcScoreId"><span>分</span>
                                <input class="short-text" type="text" name="inspectorMoney"
                                       id="pqcMoneyId"><span>元</span>
                            </div>
                        </div>
                    </li>
                </ul>
                <div class="obey_header">责任人</div>
                <ul class="counts" id="responsibility">
                    <li class="obey_li">
                        <input type="checkbox" id="deal7" name="responsibilityPersonM" value="${managerId}">
                        <label class="checkbox" data-name="deal7" for="deal7">项目经理-${managerName}</label>
                    </li>
                    <c:if test="${projectMode!=2}">
                        <li class="obey_li">
                            <div class="mar_btm50">
                                <input type="text" name="workerFine">
                                <label class="checkbox checked" style="width:auto" data-name="deal8" for="deal8">工　人：</label>
                                <select class="long-option mar_btm50 selector"  name="responsibilityPersonW" id="taskPackageTemplatId">
                                    <c:forEach items="${infoList }" var="workerInfo">
                                        <option value="${workerInfo.workerGroupId }">${workerInfo.packName}-${workerInfo.workerGroupLeaderName}</option>
                                    </c:forEach>
                                </select>
                                <c:forEach items="${infoList }" var="workerInfo">
                                    <input hidden="hidden" type="text" id="temp${workerInfo.taskPackageTemplatId }" value="${workerInfo.workerGroupId }">
                                </c:forEach>
                            </div>
                        </li>
                    </c:if>
                </ul>
        </div>
        </form>
        <footer class="obey_footer">
            <a href="javascript:void(0)" onclick="hideDiv()">
                <span class="btn font28 bor_rht">取消</span>
            </a>
            <a href="javascript:void(0)" onclick="ajaxFormSubmit()">
                <span class="btn font28">确定</span>
            </a>
        </footer>
    </div>

    <div style="background:rgba(0,0,0,.4);z-index:9;"  class="g-mask undis">
        <div id="g-done_ask">
            <p class="first">您还未签到，确认要提交报告吗？</p>
            <p class="second">
                <a href="#" id="reportNo">取消</a>
                <a href="#" id="reportSubmit">确认</a>
            </p>
        </div>
    </div>
</div>

<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id="subReport">
    <div id="g-done_ask">
        <p class="first">尊敬的美得你质检员,确定提交报告吗?</p>
        <p class="second">

            <a href="#" onclick="hideSubmitAlert()">取消</a>
            <a href="#" onclick="submitForm()" id="formButtonId">确认</a>
        </p>
    </div>
</div>

<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="mask">
    <div class="alertMask">
        <div class="maskWrapper">
            <p class="col_3 maskTit">质检员 您好</p>
            <div class="font28 col_6 maskContent"></div>
            <div class="maskBtns clearfix twoBtns">
                <a class="maskBtn font33 col_fdfcfa" href="javascript:void(0);" onclick="changeItemToQualified()">
                    确定
                </a>
                <a class="maskBtn font33 col_0780ec" href="javascript:void(0);" onclick="closeTwoBtnTip()">取消</a>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/lCalendar.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
<script>

function closeTwoBtnTip(){
    $("#mask").hide();
    $("#"+checkItemId).addClass("checked")
    $(commonObj).removeClass("checked");
}

var checkItemId = "";
var commonObj =null;
    function reverseOk(itemId,obj){

        if($(obj).hasClass("checked")){

        }else{
            checkItemId = itemId;
            commonObj=obj;
            $(".maskContent").text("您确定将该不合格项变为合格吗?");
            $("#mask").show();
        }
    }


  function   changeItemToQualified(){
      $("#mask").hide();
      $(commonObj).addClass("checked");
      $("#"+checkItemId).removeClass("checked")
      $.ajax({

          url: "${ctx}/app/pqc/selectCheckList/changeItemToQualified",
          data: {inspectId: ${inspectId} ,checkItemId:checkItemId},
          type:"post",

          success: function (data) {
              if(data==1){
              }else{
                  console.error("catch  exception e")
              }
          }
      })
  }

    //蒙层效果
    function run_waitMe(effect, text) {
        $('body').waitMe({
            effect: effect,
            text: text,
            bg: 'rgba(255,255,255,0.7)',
            color: '#000',
            sizeW: '',
            sizeH: '',
            source: 'img.svg'
        });
    }
    $(document).on('click', '#managerFineId', function () {
        if ($(this).hasClass("checked")) {

            $("#fineDivId").hide();

        } else {

            $("#fineDivId").show();
        }
    })
    $(document).on('click', '#workerFineId', function () {
        if ($(this).hasClass("checked")) {

            $("#fineDivId2").hide();
            /* $(".short-text").val(""); */

        } else {

            $("#fineDivId2").show();
        }
    })
    $(document).on('click', '#pqcFineId', function () {
        if ($(this).hasClass("checked")) {

            $("#fineDivId3").hide();
            /* $(".short-text").val(""); */

        } else {

            $("#fineDivId3").show();
        }
    })
    $(document).ready(function () {
        //绑定onclick事件
        $("#zancun").bind('click', zancun);
        $("#tijiao").bind('click', alertReport);
        $("#fineDivId").hide();
        //绑定onclick事件
        $("#reportSubmit").bind('click', reportSubmit);
        $("#reportNo").bind('click', reportNo);
    });

    function alertReport() {
        $("#subReport").show();
    }
    function hideSubmitAlert() {
        $("#subReport").hide();
    }

    //提交合格与不合格的检查项
    function submitForm() {
        $("#subReport").hide();
        var signNum = $("#signNum").val();
        if (signNum == "0") {
            //没有签到信息弹出提示框
            $('.g-qua_check .g-mask').show();
        } else {

            run_waitMe("win8", "正在提交报告...请稍等");
            $("#formButtonId").unbind("click");
            $("#formButtonId").attr("disabled", true);

            $("#zancun").unbind("click");
            $("#tijiao").unbind("click");
            $("#tijiao").css("color", "#CCC");
            $("#status").val("5");
            //有签到信息直接提交报告
            var options = {
                url: "${ctx }/app/pqc/selectCheckList/saveCheckItemReport",
                type: "post",
                error: function (data) {
                    $('body').waitMe('hide');
                    console.error(data)
                    globalUtil.fn.alert('网络不好...', 2.0);
                },
                success: function (data) {
                    if (data == 1) {
                        $('body').waitMe('hide');
                        globalUtil.fn.alert('提交报告成功...', 2.0);
                        window.location.href = "${ctx}/app/pqc/selectCheckList/list";
                    }else if (data == 2) {

                        globalUtil.fn.alert('系统繁忙...', 2.0);

                    } else {
                        globalUtil.fn.alert('网络繁忙...', 2.0);

                    }


                }
            }

            $("#reportForm").ajaxSubmit(options);
        }
    }


    //如果没有签到信息--弹框--取消
    function reportNo() {
        $('.g-qua_check .g-mask').hide();

    }

    //如果没有签到信息--弹框--确认提交
    function reportSubmit() {
        run_waitMe("win8", "正在提交报告...请稍等");
        $("#formButtonId").unbind("click");
        $("#formButtonId").attr("disabled", true);


        $("#reportSubmit").unbind("click");
        $("#reportSubmit").css("color", "#CCC");
        $("#reportNo").unbind("click");

        $("#status").val("5");
        var options = {
            url: "${ctx }/app/pqc/selectCheckList/saveCheckItemReport",
            type: "post",
            error: function (data) {
                $('body').waitMe('hide');
                console.error(data)
                globalUtil.fn.alert('网络不好...', 2.0);
            },
            success: function (data) {
                if (data == 1) {
                    $('body').waitMe('hide');
                    globalUtil.fn.alert('提交报告成功...', 2.0);
                    window.location.href = "${ctx}/app/pqc/selectCheckList/list";
                } else if (data == 2) {

                    globalUtil.fn.alert('系统繁忙...', 2.0);

                }else if (data == 9) {

                    globalUtil.fn.alert('您已经操作过该抽检单...', 2.0);
                    window.location.href="${ctx}/app/pqc/selectCheckList/list";

                }  else {
                    globalUtil.fn.alert('网络繁忙...', 2.0);

                }
            }
        }

        $("#reportForm").ajaxSubmit(options);
    }

    //暂存各项
    function zancun() {
        $("#subReport").hide();
        run_waitMe("win8", "正在暂存报告...请稍等");
        $("#formButtonId").unbind("click");
        $("#formButtonId").attr("disabled", true);

        $("#tijiao").unbind("click");

        $("#zancun").css("color", "#CCC");
        $("#zancun").unbind("click");

        $("#zancun").attr("disabled", "disabled");
        $("#tijiao").attr("disabled", "disabled");


        $("#status").val("0");

        var options = {
            url: "${ctx }/app/pqc/selectCheckList/saveCheckItemReport",
            type: "post",
            error: function (data) {
                $('body').waitMe('hide');
                console.error(data)
                globalUtil.fn.alert('网络不好...', 2.0);
            },
            success: function (data) {
                if (data == "1") {
                    $('body').waitMe('hide');
                    globalUtil.fn.alert('暂存成功...', 2.0);
                    window.location.href = "${ctx}/app/pqc/selectCheckList/list";
                }


            }
        }

        $("#reportForm").ajaxSubmit(options);
    }


    function ajaxFormSubmit() {
        //如果没有选择违规形式, 不让提交

        var n = 0;
        var x = 0;
        var j = 0;
        $("#illegalUlId label").each(function () {
            if ($(this).hasClass("checked")) {
                n++;
            }
        })
        $("#handleWay label").each(function () {
            if ($(this).hasClass("checked")) {
                x++;
            }
        })
        $("#responsibility label").each(function () {
            if ($(this).hasClass("checked")) {
                j++;
            }
        })
        if (n < 1 && x < 1) {
            globalUtil.fn.alert('您还没有选择违规形式和处理方式', 2.0);
            return;
        }
        if (n < 1) {
            globalUtil.fn.alert('请选择违规形式', 2.0);
            return;
        }
        if (x < 1) {
            globalUtil.fn.alert('请选择处理方式', 2.0);
            return;
        }
        if (j < 1) {
            globalUtil.fn.alert('请选择责任人', 2.0);
            return;
        }
        /* //如果选择了扣分罚款
         if($("#fineId").hasClass("checked")){
         //分数和金额校验
         var reg = /^\d+$/;
         var y =0;
         $("#handleWay .short-text").each(function(){
         if($(this).val().match(reg)){
         y++;

         }
         })

         if(y==0){
         globalUtil.fn.alert('分数和金额请正确输入',2.0);

         return;
         }


         }
         */
        $("#packId").val($(".long-option option:selected").val());
        /* $("#workerGroupId").val($(".long-option option:selected").prev().val()); */


        //处理方式checked value是1,没checked  value是0
        $("#handleWay label ").each(function () {
            if ($(this).hasClass("checked")) {
                var nameAttr = $(this).prev();
                $(nameAttr).val("1");
            } else {
                //如果没有checked  找到上一级的input 隐藏域
                var nameAttr = $(this).prev();
                //隐藏域0
                $(nameAttr).val("0");
            }
        });

        //违规形式 找到所有class不为checked 的上一个元素,将 name属性去掉
        $("#illegalUlId label ").each(function () {
            if ($(this).hasClass("checked")) {
            } else {
                //如果没有checked  找到上一级的input 隐藏域
                var nameAttr = $(this).prev();
                //去掉name 属性
                $(nameAttr).removeAttr("name");
            }
        });


        //ajax提交form
        var options = {
            url: "${ctx}/app/pqc/selectCheckList/saveIllegalModalityItems",
            type: "post",
            success: function (data) {
                if (data == "OK") {
                    $("#fineDivId").hide();
                    $("#fineDivId2").hide();
                    $("#fineDivId3").hide();
                    $("#illegalDivId").hide();
                    $("#handleWay label ").each(function () {
                        if ($(this).hasClass("checked")) {
                            $(this).removeClass("checked");
                        }

                        var nameAttr = $(this).prev();
                        $(nameAttr).val(0);
                    });


                }else{
                    $("#fineDivId").hide();
                    $("#fineDivId2").hide();
                    $("#fineDivId3").hide();
                    $("#illegalDivId").hide();
                    globalUtil.fn.alert('您已经操作过该抽检单...', 2.0);
                    window.location.href="${ctx}/app/pqc/selectCheckList/list";

                }
            }
        }

        $("#ajaxIllegalModalityFormId").ajaxSubmit(options);

    }

    function hideXianXia() {
        $("#xianxia").removeClass("checked");

    }


    //显示date 和线上线下  div
    var n = 0;
    function showChangeWay() {
        n++;
        var m = $("#changeDate").prev();
        if (m.hasClass("checked")) {
            $("#changeDate").show();
            $("#changeWay").show();
            //默认线下
            if (n < 2) {
                if ($("#xianshang").hasClass("checked")) {
                    $("#xianshang").removeClass("checked");
                }
                $("#xianxia").addClass("checked");
            }
        } else {
            n = 0;
            //隐藏
            $("#changeDate").hide();
            $("#changeWay").hide();
            //去掉checked
            $("#xianxia").removeClass("checked");
            $("#xianshang").removeClass("checked");
        }
    }


    /* 	//显示金额
     function showMoney() {
     var n = $("#assit").prev();
     if (n.hasClass("checkbox checked")) {
     $("#inputMoney").show();
     $("#yuan").show();
     var money = $("#assit").val();
     $("#inputMoney").val(money);

     } else {
     //隐藏金额
     $("#inputMoney").hide();
     $("#yuan").hide();
     $("#inputMoney").val(0);

     }

     } */

    //显示违规形式备注

    function showText() {
        if (typeof ($("#text").attr("hidden")) == "undefined") {

            $("#text").attr("hidden", "hidden");

        } else {
            $("#text").removeAttr("hidden");
        }
    }

    //把悬浮层隐藏  取消 按钮
    function hideDiv() {
        $("#illegalDivId").hide();
        $("#" + checkId).removeClass("checked");
        var yes = $("#" + checkId).parent().parent();
        var y = $(yes).find('label[for*=yes1]');
        $(y).attr("class", "checked");
    }

    //点击取消还原合格的标记
    var checkId;
    //点击不合格, 触发悬浮层 ,查找该检查项下的违规形式
    function findIllegalModality(checkItemId) {
        if($("#"+checkItemId).hasClass("checked")){
            alert("这就是不合格了")

            return;

        }else{



            // 获取当前日期，结束日期
            var now = new Date(),
                start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
                end = new Date(now.setFullYear(now.getFullYear() + 1)),
                end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');

            var lcalendar = start + ',' + end;
            // 将时间限制加到input标签上。
            $('#txtBeginDate').attr('data-lcalendar', lcalendar);
            $('#txtBeginDate').val(start);
            var calendar = new lCalendar();
            calendar.init({
                'trigger': '#txtBeginDate',//标签id
                'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
            });

            $('.p_right label').click(
                function () {
                    var thisName = $(this).attr('data-name');
                    $('label[data-name="' + thisName + '"]').removeAttr('class') && $(this).attr('class', 'checked');
                });

            //如果点击取消   把原来点击不合格的取消 .还原合格
            checkId = checkItemId;
            //去掉第二次不合格点击时,第一次已经选择的所有选项
            $("#changeWay").hide();
            $("#changeDate").hide();
            var taskPackageTemplatId;
            //根据检查项,查找违规形式,及该检查项的默认罚款金额
            $.ajax({
                url: "${ctx}/app/pqc/selectCheckList/findIllegalModality?checkItemId=" + checkItemId,
                success: function (data) {
                    //如果没有违规形式
                    if (data == "") {
                        //把之前写入的违规形式清空
                        $("#illegalUlId").html("");

                    } else {
                        var html = '';
                        $("#illegalUlId").html(html);
                        for (var v = 0; v < data.length; v++) {
                            if( null!=data[v].id && data[v].id!="" && undefined!=data[v].id ) {
                                //违规项对象 数组    属性: id , illegalModalityName ,isRemarks  remarks
                                if (data[v].isRemarks == "1") {
                                    html += '<li class="obey_li" ><input type="checkbox" id="one"  value="one">	<input type="text" hidden="hidden" name="id" value=' + data[v].id + ' /><label class="checkbox" data-name="one" for="one" onclick="showText()">'
                                        + data[v].illegalModalityName
                                        + '</label>  <textarea class="other" name="remarks" hidden="hidden" id="text"></textarea></li>';
                                } else {
                                    html += '<li class="obey_li"><input type="checkbox" id="one" value="one">	<input type="text" hidden="hidden" name="id" value=' + data[v].id + ' /><label class="checkbox" data-name="one" for="one">'
                                        + data[v].illegalModalityName
                                        + '</label></li>';
                                }
                            }
                        }


                        //写入罚款金额和分数 2017-3-14
                        //有分或者罚款, 默认显示不允许修改
                        //没有分或者罚款, 默认0 允许修改
                        taskPackageTemplatId = data[0].responsibilityPersonW;
                        $("#managerMoneyId").val(data[0].managerFineMoney);
                        $("#managerScoreId").val(data[0].managerFineScore)
                        $("#pqcMoneyId").val(data[0].pqcFineMoney);
                        $("#pqcScoreId").val(data[0].pqcFineScore)
                        $("#workerMoneyId").val(data[0].workerFineMoney);
                        $("#workerScoreId").val(data[0].workerFineScore)

                        $("#handleWay .short-text ").each(function () {
                            if ($(this).val() != 0) {

                                $(this).attr("readOnly", true);

                            }


                        });

                        $("#illegalUlId").html(html);
                    }


                    //获取检查项的任务包模板id
                    //将下拉工人组中改项置为默认选中  attr("selected",true);

                    if(null!=taskPackageTemplatId && taskPackageTemplatId!=""){
                        var   hiddentemp=$("#temp"+taskPackageTemplatId).val()
                        $("#taskPackageTemplatId").find("option").each(function(){
                            if(this.value===hiddentemp){
                                $("#taskPackageTemplatId option[value='"+hiddentemp+"']").attr("selected","selected");
                            }
                        })

                    }

                }
            })
            $("#illegalDivId").show();
        }

    }

    $(function () {
        $(document).on('click', '.wtc_btn', function () {
            $('.mask1').removeClass('undis');
        });
        $(document).on('click', '.pic_wraper img', function () {
            var purl = $(this).attr("src");
            $("#big_pic").attr("src", purl);
            $('.mask2').removeClass('undis');
        });
        $(document).on('click', '.radio_span:last-child label', function () {
            setTimeout(function () {
                $('.mask3').removeClass('undis');
            }, 3000);
        });
        $(document).on('click', '.mask2', function () {
            $('.mask2').addClass('undis');
        });
        $(document).on('click', '.back', function () {
            $('.mask1').addClass('undis');
        });

        // 单选框
        $('.qua_check label').click(
            function () {
                var thisName = $(this).attr('data-name');
                $('label[data-name="' + thisName + '"]').removeAttr(
                    'class')
                && $(this).attr('class', 'checked');
            });
        $('.p_right label').click(
            function () {
                var thisName = $(this).attr('data-name');
                $('label[data-name="' + thisName + '"]').removeAttr(
                    'class')
                && $(this).attr('class', 'checked');
            });
        // 复选框
        $(document).on('click', 'label.checkbox', function () {
            if ($(this).hasClass('checked')) {
                $(this).removeClass('checked');
            } else {
                $(this).addClass('checked');
            }
        });
    });

    //图片上传显示
    function preview(file) {
        var shit = $("#shit").val();
        var prevDiv = $('.camera');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                $('<div class="pic_wraper"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="' + shit + '"/><a class="del_btn" href="javascript:void(0)">删除</a></div>').appendTo('.pic_container');
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            console.log(file, file.value);
        }
    }

    //删除页面的图片
    $(document).on('click', '.del_btn', function () {

        var numReal = $(this).prev().attr("id");
        $(("#num" + numReal)).remove();

        var num = $("#num").val();
        num--;
        $("#num").val(num);
        $(this).parent().remove();
        globalUtil.fn.alert('删除图片操作成功', 2.0);
        var divNum = $("#foo > div").size();
        $("#picLength").text(divNum);
    });

    //添加隐藏域   图片
    function uploadpic(pic) {
        var hiddenForm = document.getElementById("reportForm");
        var input = document.createElement("input");
        var num = $("#num").val();
        var shit = $("#shit").val();
        if (pic) {
            num++;
            input.setAttribute("id", "num" + shit);
            input.setAttribute('hidden', 'hidden');
            input.setAttribute('name', 'photo');
            input.setAttribute("type", "text");
            input.setAttribute("value", pic);
            hiddenForm.appendChild(input);
            $("#num").val(num);
            shit++;
            $("#shit").val(shit);
        }
        //统计有多少图片（div）
        var divNum = $("#foo > div").size();
        $("#picLength").text(divNum);
    }


    //删除数据库中的图片
    function deletePic(picId) {
        $.ajax({
            url: "${ctx}/app/pqc/selectCheckList/deletePic",
            type: "POST",
            async: false,
            data: {
                picId: picId
            },
            success: function (data) {
                if (data == 0) {
                    globalUtil.fn.alert('删除图片操作成功', 2.0);
                }

            }
        });
    }

</script>
</body>
</html>
