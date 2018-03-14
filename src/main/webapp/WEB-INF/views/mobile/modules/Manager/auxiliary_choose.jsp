<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"  content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <title>选择辅料</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/auxiliary_choose1.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170918/style.css"/>
    <link type="text/css" rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css">
    <script>var baseUrl = '${ctx}'</script>
    <style type="text/css">
        ul li:last-child {
            margin-bottom: 200px;
        }

        .spanRgt33 {
            text-align: right;
            width: 33%;
        }

        .alertMask {
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            background: rgba(0, 0, 0, .5);
        }

        .maskWrapper {
            width: 84%;
            margin: 30% auto 0;
            border-radius: .1rem;
            background: #fff;
            font-size: .32rem;
        }

        .col_3 {
            color: #333
        }

        .col_6 {
            color: #666;
        }

        .col_f {
            color: #fff;
        }

        .col_fdfcfa {
            color: #fdfcfa;
        }

        .col_0780ec {
            color: #0780ec;
        }

        .font28 {
            font-size: .28rem;
        }

        .font33 {
            font-size: .33rem;
        }

        .maskTit {
            height: 1rem;
            line-height: 1rem;
            text-align: center;
        }

        .maskContent {
            padding: .5rem;
            border-top: 1px solid #ddd;
            border-bottom: 1px solid #ddd;
            line-height: 1.5em;
        }

        .maskBtns {
            width: 83%;
            margin: 0 auto;
            padding-bottom: .2rem;
            padding-top: .2rem;
        }

        .maskBtn {
            display: block;
            width: 60%;
            text-align: center;
            line-height: .76rem;
        }

        .maskBtn {
            background: #0780ec;
            border-radius: .1rem;
            display: block;
            width: 47.6%;
            text-align: center;
            line-height: .76rem;
            font-size: .33rem;
            margin: 0 auto;
        }
    </style>
</head>
<body>

<div class="g-auxiliary_choose">
    <header>
        <a class="back_btn" href="${ctx }/app/manager/auxiliary/order"></a>
        <h2 class="title">选择辅料</h2>
    </header>
    <!-- /header -->
    <nav>
        <a class="nav1" href="javascript:void(0)" onclick="Items('0')"><span class="active">水电</span></a>
        <a class="nav1" href="javascript:void(0)" onclick="Items('2')"><span>木</span></a>
        <a class="nav1" href="javascript:void(0)" onclick="Items('1')"><span>瓦</span></a>
        <a class="nav1" href="javascript:void(0)" onclick="Items('3')"><span>油</span></a>
    </nav>
    <form action="${ctx}/app/manager/auxiliary/auxiliarybuy" method="post"  id="form">
    	
		<input type="text" hidden="hidden" id="orderId" name="orderId" value="${orderId}">
        <input type="text" hidden="hidden" id="projectMode" name="projectMode" value="${order.projectMode}">
        
        <c:forEach items="${packageStateList }" var="packageState">
        
	        <input type="text" hidden="hidden" id="packageState${packageState.empWorkType}" value="${packageState.isCanApplyAuxiliary}">
	        <input type="text" hidden="hidden" id="packageAfterAmount${packageState.empWorkType}" value="${packageState.afterAmount}">
            <input type="text" hidden="hidden" id="packageTemplatName${packageState.empWorkType}" value="${packageState.templatName}">

        </c:forEach>
       
        <div class="show_sec item-div">
        	<ul id="prompt0" class="item-tips undis">
                <li>
                    <div class="item-name">水电：</div>
                    <div class="item-txt">
                        <p><i class="icon icon-tips">icon</i>温馨提示：</p>
                        <p id="promptRemark0"></p>
                    </div>
                </li>
            </ul>
            <ul id="0" class="choose_ul">
            	<%--  <c:forEach items="${waterLightCategory }" var="a" varStatus="status">
                    <input type="text" hidden="hidden" name="id" value="${a.id }"/>
                    <input type="text" hidden="hidden" name="auxiMateCode" value="${a.auxiMateCode}"/>
                    <li class="shadow">
                        <div class="img_container">
                            <img src="${a.pic}" alt="goods" onerror="nofind()">
                        </div>
                        <p class="brand">
                            <span>【</span>${a.brand}<span>】</span> ${a.name }
                        </p>
                        <p class="format">${a.specifications }</p>
                        <p class="price">
                            <span class="col_red">${a.price }</span>元/${a.unit }
                        </p>
                        <div id="numbox" class="mui-numbox" data-numbox-step='1'
                             data-numbox-min='0' data-numbox-max='999' style="">
                            <button class="mui-btn mui-numbox-btn-minus" type="button"
                                    style="" onclick="setTotal(this,2)">-
                            </button>
                            <input id="num" class="mui-numbox-input" type="number" value="0"
                                   style="" name="count" onchange="setTotal(this,3)" onkeyup="isInteger(this)"/>
                            <button class="mui-btn mui-numbox-btn-plus" type="button"
                                    style="" onclick="setTotal(this,1)">+
                            </button>
                        </div>
                    </li>
                </c:forEach> --%>
            </ul>
        </div>
        <div class="show_sec item-div undis">
        	<ul id="prompt2" class="item-tips undis">
                <li>
                    <div class="item-name">木：</div>
                    <div class="item-txt">
                        <p><i class="icon icon-tips">icon</i>温馨提示：</p>
                        <p id="promptRemark2"></p>
                    </div>
                </li>
            </ul>
            <ul id="2" class="choose_ul"></ul>
        </div>
        <div class="show_sec item-div undis">
        	<ul id="prompt1" class="item-tips undis">
                <li>
                    <div class="item-name">瓦</div>
                    <div class="item-txt">
                        <p><i class="icon icon-tips">icon</i>温馨提示：</p>
                        <p id="promptRemark1"></p>
                    </div>
                </li>
            </ul>
            <ul id="1" class="choose_ul"></ul>
        </div>
        <div class="show_sec item-div undis">
        	<ul id="prompt3" class="item-tips undis">
                <li>
                    <div class="item-name">油：</div>
                    <div class="item-txt">
                        <p><i class="icon icon-tips">icon</i>温馨提示：</p>
                        <p id="promptRemark3"></p>
                    </div>
                </li>
            </ul>
            <ul id="3" class="choose_ul"></ul>
        </div>

    </form>


    <div style="padding-top:500px;"></div>


    <footer id="footer">
        <div>
            <p class="col_red">
                合计：<span id="total"></span>
            </p>
            <span hidden="hidden">元</span>
            <p class="goods" id="count"></p>
        </div>
        <a class="choose_btn" href="javascript:void(0)" onclick="cart()">选好了</a>
    </footer>


    <div class="alertMask undis" id="alert">
        <div class="maskWrapper">
            <p class="col_3 maskTit margin-left0">提示信息</p>
            <div class="font28 col_6 maskContent">辅料的申请数量只能输入正整数，请重新输入申请数量。</div>
            <div class="maskBtns clearfix">
                <a class="maskBtn font33 col_f" onclick="queren()" href="javascript:;">我知道了</a>
            </div>
        </div>
    </div>
    <div class="alertMask undis" id="alertTwo">
        <div class="maskWrapper">
            <p class="col_3 maskTit margin-left0">提示信息</p>
            <div class="font28 col_6 maskContent" id="alertMessage"></div>
            <div class="maskBtns clearfix">
                <a class="maskBtn font33 col_f" onclick="alertKnow()" href="javascript:;">我知道了</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/swiper-3.3.1.jquery.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/auxiliaryApply/auxiliaryChoose.js"></script>

<script>
    var index0 = 0;
    $('.g-auxiliary_choose nav').on('click', 'a.nav1', function () {
        index0 = $(this).index();
        $('.nav1').find('span').removeClass('active');
        $(this).find('span').addClass('active');
        $('.show_sec').addClass('undis');
        $('.show_sec').eq(index0).removeClass('undis');
    });
    
    //页面初始值
    $(function(){
        $("#total").html(0.00+ "元");
        $("#count").html("您一共选择了" + 0 + "个商品");
    })
    
   
    
</script>
</body>
</html>