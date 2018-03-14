<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>质检报告订单列表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/search.css" /> 
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/layout.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="wrap">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/qualityControlList"></a>
			<h2 class="title">质检报告</h2>
		</header><!-- /header -->
		<section class="pt112">
			<div class="font0 search-area">
				<input class="search-box" type="text" placeholder=" 小区名称、客户姓名" value="${text}">
				<a class="search-btn" onclick="searchOrderList()" href="javascript:;"></a>
			</div>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	

	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	
</body>
</html>
<script type="text/javascript">

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

    var managerId = "${managerId}";
    var param ={}

    defaultSearchOrder();

    function defaultSearchOrder(){
        var text =$(".search-box").val();
        if(null == text || text == ''){
            return;
        }
        searchOrderList();
    }

    function searchOrderList(){
        var sectionObj=$(".pt112");
        var html ='';
        run_waitMe('正在加载数据,请稍等');

        html += '<div class="font0 search-area"> ' +
                    '<input class="search-box" type="text" placeholder="小区名称、客户姓名">  ' +
                    '<a class="search-btn"  onclick="searchOrderList()"  href="javascript:;"></a> ' +
                '</div>';

        var text =$(".search-box").val();
        param={
            managerId:managerId,
            text:text
        }

        $.ajax({
            url: "${ctx}/app/manager/report/order_list_ajax",
            type: "post",
            data: param,
            success: function(data){

                if(null != data && data.length > 0){

                    for(var v=0;v<data.length;v++){
                        html += '<div class="sec font0 border_top border_btm mb30 bg_f clearfix">'+
                                    '<ul class="pad_top3 pl30 pr30 bor_dashed">'+
                                        '<li class="mb30 clearfix">'+
                                            '<span class="col_grey font30 flo_left pl2em">顾 客 信 息 ：</span>'+
                                            '<p class="font30 col_3 pad_rgt3 flow">'+ data[v].communityName +'-'+ data[v].buildNumber +'-'+ data[v].buildUnit +'-'+ data[v].buildRoom +'-'+ data[v].customerName +'</p>'+
                                        '</li>'+
                                        '<li class="mb30 clearfix">'+
                                            '<span class="col_grey font30 flo_left pl2em">客 户 手 机 ：</span>'+
                                            '<p class="font30 col_3 pad_rgt3 flow">'+data[v].customerPhone+'</p>'+
                                        '</li>'+
                                        '<li class="mb30 rele clearfix">'+
                                            '<span class="col_grey font30 flo_left pl2em">项 目 经 理 ：</span>'+
                                            '<p class="font30 col_3 pad_rgt3 flow">'+data[v].managerRealName+'</p>'+
                                        '</li>'+
                                        '<li class="mb30 rele clearfix">'+
                                            '<span class="col_grey font30 flo_left">实际开工日期：</span>'+
                                            '<p class="font30 col_3 pad_rgt3 flow">'+data[v].actualStartDateString+'</p>'+
                                        '</li>'+
                                    '</ul>'+
                                    '<div class="btn_wrapper clearfix">'+
                                        '<a class="btnBlueBg" href="${ctx}/app/manager/report/reportList?orderId='+data[v].orderId+'">报告</a>'+
                                    '</div>'+
                                '</div>';

                    }
                }
                $(sectionObj).html(html);
                $(".search-box").val(text);
                $('body').waitMe('hide');
            }
        })
    }



</script>
