<%@ page contentType="text/html;charset=UTF-8"%>
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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/common.css"/>
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/pqc/css/search.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/pqc/css/dateCheckRecord.css">
    <link type="text/css" rel="stylesheet" 	href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="taskPack">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/indexMine"></a>
			<h2 class="title">质检报告</h2>
		</header>
		<section class="pad_top11">
			<div class="mar_btm14 font0 search-area">
				<input class="search-box" type="text" placeholder="小区名称、客户姓名" value="${text}">
				<a class="search-btn"  onclick="searchOrderList()"  href="javascript:;"></a>
			</div>

		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
</body>

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




    var inspectorId = "${inspectorId}";
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
        run_waitMe('正在加载数据,请稍等');

        var sectionObj=$(".pad_top11");
        var html = '<div class="mar_btm14 font0 search-area"> ' +
						'<input class="search-box" type="text" placeholder="小区名称、客户姓名">  ' +
						'<a class="search-btn"  onclick="searchOrderList()"  href="javascript:;"></a> ' +
					'</div>';

        var text =$(".search-box").val();
        param={
            inspectorId:inspectorId,
            text:text
        }
        $.ajax({
            url: "${ctx}/app/pqc/report/order_list_ajax",
            type: "post",
            data:param,
            success: function(data){

                if(null != data && data.length > 0){

                    for(var v=0;v<data.length;v++){
                        html += '<div class="bg_f pt10 border_top border_btm mb20"> ' +
									'<ul class="pad_left3 mb50"> ' +
										'<li class="mt30 clearfix"> ' +
											'<span class="col_6 f30 flo_left label-rgt">顾客：</span> ' +
											'<p class="f30 col_3 pad_rgt3 flow">'+data[v].communityName+'-'+data[v].buildNumber+'-'+data[v].buildUnit+'-'+data[v].buildRoom+'-'+data[v].customerName+'</p> ' +
										'</li>' +
										' <li class="mt30 clearfix"> ' +
											'<span class="col_6 f30 flo_left label-rgt">客户手机：</span> ' +
											'<p class="f30 col_3 pad_rgt3 flow">'+data[v].customerPhone+'</p> ' +
										'</li>' +
										' <li class="mt30 clearfix"> ' +
											'<span class="col_6 f30 flo_left label-rgt">项目经理：</span> ' +
											'<p class="f30 col_3 pad_rgt3 flow">'+data[v].managerRealName+'</p> ' +
										'</li> ' +
										'<li class="mt30 clearfix"> ' +
											'<span class="col_6 f30 flo_left label-rgt">实际开工日期：</span> ' +
											'<p class="f30 col_3 pad_rgt3 flow">'+data[v].actualStartDateString+'</p> ' +
										'</li> ' +
									'</ul> '+
									'<div class="clearfix"> ' +
										'<a class="detBtn" href="${ctx}/app/pqc/report/reportList?orderId='+data[v].orderId+'">报告</a>' +
									' </div> ' +
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
</html>