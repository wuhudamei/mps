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
	<title>约检验收记录</title>
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
			<h2 class="title">约检验收记录</h2>
		</header><!-- /header -->
		<section class="pad_top11">
          <%--  <div class="mar_btm14 font0 search-area">
                <input class="search-box" type="text" placeholder="小区名称、客户姓名或项目经理">
                <a class="search-btn" href="javascript:;"></a>
            </div>
            <div class="bg_f pt10 border_top border_btm mb20">
                <ul class="pad_left3 mb50">
                    <li class="mt30 clearfix">
                        <span class="col_6 f30 flo_left label-rgt">顾客：</span>
                        <p class="f30 col_3 pad_rgt3 flow">鹿港嘉苑鹿港嘉苑鹿港嘉苑-10-3-2001-张三</p>
                    </li>
                    <li class="mt30 clearfix">
                        <span class="col_6 f30 flo_left label-rgt">客户手机：</span>
                        <p class="f30 col_3 pad_rgt3 flow">13010102020</p>
                    </li>
                    <li class="mt30 clearfix">
                        <span class="col_6 f30 flo_left label-rgt">项目经理：</span>
                        <p class="f30 col_3 pad_rgt3 flow">韩振刚-13010102020</p>
                    </li>
                    <li class="mt30 clearfix">
                        <span class="col_6 f30 flo_left label-rgt">实际开工日期：</span>
                        <p class="f30 col_3 pad_rgt3 flow">2016-10-05</p>
                    </li>
                </ul>
                <div class="progress_Bar pt40 pb40 bor_dotted clearfix">
                    <ul class="tc">
                        <li>
                        <span class="avtive">
                            <i>1</i>
                            <em class="line active"></em>
                        </span>
                            <p class="active">水电隐蔽验收</p>
                        </li>
                        <li>
                        <span >
                            <i>2</i>
                            <em class="line"></em>
                        </span>
                            <p>闭水实验和瓦工隐蔽工程验收</p>
                        </li>
                        <li>
                        <span >
                            <i>3</i>
                        </span>
                            <p>墙地砖验收</p>
                        </li>
                    </ul>
                </div>
                <div class="clearfix">
                    <a class="detBtn" href="javascript:;">详情</a>
                </div>
            </div>
            <div class="bg_f pt10 border_top border_btm mb20">
                <ul class="pad_left3 mb50">
                    <li class="mt30 clearfix">
                        <span class="col_6 f30 flo_left label-rgt">顾客：</span>
                        <p class="f30 col_3 pad_rgt3 flow">鹿港嘉苑鹿港嘉苑鹿港嘉苑-10-3-2001-张三</p>
                    </li>
                    <li class="mt30 clearfix">
                        <span class="col_6 f30 flo_left label-rgt">客户手机：</span>
                        <p class="f30 col_3 pad_rgt3 flow">13010102020</p>
                    </li>
                    <li class="mt30 clearfix">
                        <span class="col_6 f30 flo_left label-rgt">项目经理：</span>
                        <p class="f30 col_3 pad_rgt3 flow">韩振刚-13010102020</p>
                    </li>
                    <li class="mt30 clearfix">
                        <span class="col_6 f30 flo_left label-rgt">实际开工日期：</span>
                        <p class="f30 col_3 pad_rgt3 flow">2016-10-05</p>
                    </li>
                </ul>
                <div class="progress_Bar pt40 pb40 bor_dotted clearfix">
                    <ul class="tc">
                        <li>
                        <span class="avtive">
                            <i>1</i>
                            <em class="line active"></em>
                        </span>
                            <p class="active">水电隐蔽验收</p>
                        </li>
                        <li>
                        <span >
                            <i>2</i>
                            <em class="line"></em>
                        </span>
                            <p>闭水实验和瓦工隐蔽工程验收</p>
                        </li>
                        <li>
                        <span >
                            <i>3</i>
                        </span>
                            <p>墙地砖验收</p>
                        </li>
                    </ul>
                </div>
                <div class="clearfix">
                    <a class="detBtn" href="javascript:;">详情</a>
                </div>
            </div>
            --%>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
    <script type="text/javascript"
            src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>


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




    run_waitMe('正在加载数据,请稍等');
    var pqcId = "${pqcId}";
    var param ={}

    searchOrderList();
    function searchOrderList(){
        var sectionObj=$(".pad_top11");
        var html ='';
        html+='<div class="mar_btm14 font0 search-area"> <input class="search-box" type="text" placeholder="小区名称、客户姓名或项目经理">  <a class="search-btn"  onclick="searchOrderList()"  href="javascript:;"></a> </div>';



        var text =$(".search-box").val();
        param={
            pqcId:pqcId,
            text:text
        }


        $.ajax({
            url: "${ctx}/app/pqc/apply-check-detail/list.php",
            type: "post",
            data:param,
            success: function(data){

                for(var v=0;v<data.length;v++){


                    html+=' <div class="bg_f pt10 border_top border_btm mb20"> <ul class="pad_left3 mb50"> ' +
'<li class="mt30 clearfix"> <span class="col_6 f30 flo_left label-rgt">顾客：</span> <p class="f30 col_3 pad_rgt3 flow">'+data[v].customerInfo+'</p> </li>' +
' <li class="mt30 clearfix"> <span class="col_6 f30 flo_left label-rgt">客户手机：</span> <p class="f30 col_3 pad_rgt3 flow">'+data[v].customerPhone+'</p> </li>' +
' <li class="mt30 clearfix"> <span class="col_6 f30 flo_left label-rgt">项目经理：</span> <p class="f30 col_3 pad_rgt3 flow">'+data[v].managerInfo+'</p> </li> ' +
'<li class="mt30 clearfix"> <span class="col_6 f30 flo_left label-rgt">实际开工日期：</span> <p class="f30 col_3 pad_rgt3 flow">'+format(data[v].actualStartDate,'yyyy-MM-dd')+'</p> </li> </ul> '

          /*          var checkDetailList=data[v].checkDetailList;


                    for(var x=0;x<checkDetailList.length;x++){
   var  q = x+1;



                            html+='<div class="progress_Bar pt40 pb40 bor_dotted clearfix"> <ul class="tc">' +
                            ' <li> <span > <i>'+q  +'</i> </span> <p>'+checkDetailList[x].qcCheckNodeName+'</p> </li> </ul> </div>'


                    }*/

                    html+='<div class="clearfix"> <a class="detBtn" href="${ctx}/app/pqc/apply-check-detail/detail?orderId='+data[v].orderId+'">详情</a>' +
                    ' </div> </div>';


                }


                $(sectionObj).html(html);

                $(".search-box").val(text);
$('body').waitMe('hide');

            }





        })


    }



    var format = function(time, format){

        var t = new Date(time);

        var tf = function(i){return (i < 10 ? '0' : '') + i};
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
            switch(a){
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    }





</script>
</html>