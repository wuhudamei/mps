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
	<title>约检记录详情</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/dateCheckRecordDetails.css"/>
	<link type="text/css" rel="stylesheet" 	href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >

</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/apply-check-detail/pre-list.php"></a>
			<h2 class="title">约检记录详情</h2>
		</header>
		<section class="pad_top88">
		<!-- <p class="locate font28 col_3 pad_top30 pad_btm30">创业公社-10-3-2001-张三</p>
			<div class="mar_btm26 bor_top_ea bor_btm_ea">
				<div class="font30 col_0780ec pad_top24 pad_btm24 pad_left30 pad_rgt30 bor_btm_ccc bg_f rela">水电隐蔽验收<a class="font24 col_0780ec seeBtn" href="javascript:;">查看照片</a></div>
				<ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">项目经理提报申请时间：</span>
						<p class="font28 col_10">2017-04-18</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">申请质检员上门日期：</span>
						<p class="font28 col_10">2017-04-20</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">质检员上门签到日期：</span>
						<p class="font28 col_10">2017-04-20</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">质检员提交检查日期：</span>
						<p class="font28 col_10">2017-04-20</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">质检员确认验收日期：</span>
						<p class="font28 col_10">2017-04-20</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">实际得分/总分：</span>
						<p class="font28 col_c20e0e">95分/100分</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">项目经理申请延期原因：</span>
						<p class="font28 col_10">XXX</p>
					</li>
					<li class="mar_btm26 clearfix">
						<span class="font28 col_9">质检员验收延期原因：</span>
						<p class="font28 col_10">XXX</p>
					</li>
					<a class="one_btn" href="javascript:;">查看评价</a>
				</ul>
			</div> -->
		</section>
		<div style="padding-top:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
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
    var orderId = "${orderId}"; 


    searchOrderDetailList();
    function searchOrderDetailList(){
        var sectionObj=$(".pad_top88");
        var html ='';

        $.ajax({
            url: "${ctx}/app/pqc/apply-check-detail/detail.php",
            type: "post",
            data:{ orderId:orderId},
            success: function(data) {

				html+='<p class="locate font28 col_3 pad_top30 pad_btm30">'+data.customerInfo+'</p>'+
                    ' <div class="mar_btm26 bor_top_ea bor_btm_ea">'
					var list = data.checkDetailList;
				for(var x =0;x<list.length;x++){
				    html+=' <div class="font30 col_0780ec pad_top24 pad_btm24 pad_left30 pad_rgt30 bor_btm_ccc bg_f rela">'+list[x].qcCheckNodeName+'<a class="font24 col_0780ec seeBtn" href="${ctx}/app/pqc/apply-check-detail/photo.php?qcBillId='+list[x].qcBillId+'">验收照片</a></div>'+
                        ' <ul class="bg_f pad_left30 pad_rgt30 pad_top24 pad_btm24 list">'+
                        ' <li class="mar_btm26 clearfix">'+
                        ' <span class="font28 col_9">项目经理提报申请时间：</span>'+
                        ' <p class="font28 col_10">'+format(list[x].managerApplyDate,'yyyy-MM-dd HH:mm:ss')+'</p>'+
                        ' </li>'+
                        ' <li class="mar_btm26 clearfix">'+
                        ' <span class="font28 col_9">申请质检员上门日期：</span>'+
                        ' <p class="font28 col_10">'+format(list[x].hopePqcCheckDate,'yyyy-MM-dd')+'</p>'+
                        ' </li>'+
                        ' <li class="mar_btm26 clearfix">'+
                        '  <span class="font28 col_9">质检员上门签到日期：</span>'+
                        '  <p class="font28 col_10">'+format(list[x].pqcSignDate,'yyyy-MM-dd  HH:mm:ss')+'</p>'+
                        '  </li>'+
                        '   <li class="mar_btm26 clearfix">'+
                        '  <span class="font28 col_9">质检员提交检查日期：</span>'+
                        '   <p class="font28 col_10">'+format(list[x].pqcSubmitDate,'yyyy-MM-dd  HH:mm:ss')+'</p>'+
                        ' </li>'+
                        '  <li class="mar_btm26 clearfix">'+
                        '  <span class="font28 col_9">质检员确认验收日期：</span>'+
                        ' <p class="font28 col_10">'+format(list[x].pqcCheckDoneDate,'yyyy-MM-dd')+'</p>'+
                        '  </li>'+
                        '  <li class="mar_btm26 clearfix">'+
                        '  <span class="font28 col_9">实际得分/总分：</span>'+
                        ' <p class="font28 col_c20e0e">'+list[x].scores+'</p>'+
                        ' </li>'+
						'<li class="mar_btm26 clearfix"><span class="font28 col_9">项目经理申请延期原因：</span>  <p class="font28 col_10">'+list[x].delayReasonPm+'</p>'+
                       ' </li><li class="mar_btm26 clearfix"> <span class="font28 col_9">质检员验收延期原因：</span> <p class="font28 col_10">'+list[x].delayReasonQc+'</p> </li>';
				    if(list[x].bizEvalScoreId != null && list[x].bizEvalScoreId != ""){
                    	html+="<a class='one_btn' href='${ctx}/app/pqc/apply-check-detail/evalDetail?evalScoreId="+list[x].bizEvalScoreId+"'>查看评价</a>";
                    }
                     html+= '</ul>'+
                        ' </div>';
                }


                $(sectionObj).html(html);
                $('body').waitMe('hide');
            }
        })


    }


    var format = function(time, format){
       if(time==undefined){

           return "无";
	   }
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


