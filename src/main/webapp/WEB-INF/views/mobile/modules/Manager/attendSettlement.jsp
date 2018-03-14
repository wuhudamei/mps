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
	<title>考勤结算</title>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/reset.css">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/common.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/search.css" />
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/Settlement.css">

	<link type="text/css" rel="stylesheet" 	href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<script type="text/javascript">
		
</script>

<body>
	<div class="wrap">
		<header>
			<a class="back_btn New_btns" href="${ctx}/app/manager/toQueryPmGuaranteeMoneyLog"></a>
			<h2 class="title">考勤结算</h2>
		</header>
		<section class="pad_top11">
			<!-- <div class="mar_btm14 font0 search-area">
				<input class="search-box" type="text" placeholder=" 小区名称、客户姓名">
				<a class="search-btn New_searchs" href="javascript:;" onclick="ajaxSearch()"></a>
			</div> -->
			<c:forEach items="${billAuditingBatchList}" var="BatchList">

			<div class="list">
				<div class="list_info">
					<span style="font-size:30px">
						${BatchList.attendMonth }
					</span>
					<p>
						<span class="col_6">项目经理本月考勤周期：</span>
						<span class="col_3">${BatchList.attendRateStart }-${BatchList.attendRateEnd }</span>
					</p>
					<p>
						<span class="col_6">本月实际考勤天数（自然日）：</span>
						<span class="col_3">${BatchList.wcDay }天</span>
					</p>
					<p>
						<span class="col_6">考勤率：</span>
						<span class="col_0780ec">${BatchList.attendRate}%</span>
					</p>
					<p>
						<span class="col_6">星级对照底薪：</span>
						<span class="col_0780ec">${BatchList.pmStarSalary}</span>
					</p>
					<p>
						<span class="col_6">本月实际考勤底薪：</span>
						<span class="col_0780ec">${BatchList.pmStarSalaryAttendDefault}</span>
					</p>
					<p>
						<span class="col_6">项目经理本月实际得到薪酬：</span>
						<span class="col_0780ec">${BatchList.pmStarSalaryReal}</span>
					</p>
                    <p>
                        <span class="col_6" style="color: red">项目经理本月实际得到薪酬=本月考勤底薪*考勤率</span>
                    </p>
				</div>
			</div>
			</c:forEach>

		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
</body>

<script type="text/javascript">
    function run_waitMe(text){
        $('.pad_top11').waitMe({
            effect: 'win8',
            text: text,
            bg: 'rgba(255,255,255,0.7)',
            color:'#000',
            sizeW:'',
            sizeH:'',
            source: 'img.svg'
        });
    }

	function settleApply(orderId){

        run_waitMe("正在申请结算...请稍后");

        window.location.href="${ctx}/app/manager/tradition-manager-settle/settleApply.php?orderId="+orderId

	}


	 /* $(document).ready(function() {
		var date = "${billAuditingBatchList}";
		console.log(date);
		//var day = mGetDate(date.substring(0,date.indexOf("-")),date.substring(date.indexOf("-")+1,date.length));
		
	});
	
	//获取当前月份天数
	function mGetDate(year, month){
	    var d = new Date(year, month, 0);
	    return d.getDate();
	}  */


    function ajaxSearch(){
        run_waitMe("搜索中...请稍后");
        var text = $(".search-box").val();

        $(".pad_top11").html("");
        $(".pad_top11").append('<div class="mar_btm14 font0 search-area">	<input class="search-box" type="text" placeholder=" 小区名称、客户姓名">'
            +'	<a class="search-btn New_searchs" href="javascript:;" onclick="ajaxSearch()"></a>'	)
        $.ajax({
            url: "${ctx}/app/manager/tradition-manager-settle/query_ajax_list?text="+text,
            type : "get",
            success: function (data){

                if(null!=data){
                    for(var v = 0;v<data.length;v++){
                        $(".pad_top11").append('<div class="list"><div class="list_info"><p><span class="col_6">工程地址：</span>'
                            +'<span class="col_3">'+data[v].communityName+"-"+data[v].buildNumber+"-"+data[v].buildUnit+"-"+data[v].buildRoom+'</span></p> <p><span class="col_6">客户姓名：</span>'
                            +'<span class="col_3">'+data[v].customerName+'</span></p> <p> <span class="col_6">订单状态：</span>'
                            +'<span class="col_0780ec">'+data[v].orderStatusDescription+'</span></p></div> <div class="list_btn bortop_dash"> <p onclick="settleApply('+data[v].orderId+')">结算申请</p> </div></div>'
                        );

                    }
                    $(".search-box").val(text);
                }
                $('.pad_top11').waitMe('hide');


            }
        })




    }

</script>
</html>