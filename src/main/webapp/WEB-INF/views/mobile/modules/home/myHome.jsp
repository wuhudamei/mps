<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/mobile/modules/home/footer.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/globalUtil.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/bxyTopCom.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/myHome.css">
</head>

<body>
    <div class="bxy_setIndex_warp">
        <!--顶部公共样式start-->
        <div class="top-header">
            <!-- <header> -->
                <a class="set" href="${ctx}/app/home/setPage"></a>
            <!-- </header> -->
        </div>
        <!--顶部公共结构end-->
        <!--内容部分start-->
        <div class="bxy_setIndex_warp_con main">
            <div class="top">
                <dl>
                    <dt><img src="${ctxStatic}/mobile/modules/home/images/photo.png" alt=""></dt>
                    <dd>
                        <p class="name">${customerPhone}</p>
                        <p class="title"><span></span>让家变成温馨的港湾！</p>
                    </dd>
                </dl>
                <p class="top_lis">我的装修</p>
            </div>
            <div class="section">
            	 <a class="line" href="${ctx}/app/home/myOrder">
	                <div class="lis">
	                    <div class="lis_icon">
	                        <img src="${ctxStatic}/mobile/modules/home/images/lis1.png" alt="">
	                    </div>
	                    <div class="lis_info">
	                 	     我的订单<span></span><u></u>
	                    </div>
	                </div>
                </a>
                <%-- <a class="line" href="${ctx}/app/home/orderTeam">
	                <div class="lis">
	                    <div class="lis_icon">
	                        <img src="${ctxStatic}/mobile/modules/home/images/lis2.png" alt="">
	                    </div>
	                    <div class="lis_info">
	                      	  施工团队<span></span>
	                    </div>
	                </div>
	            </a> --%>
	            <a class="line" href="${ctx}/app/home/score/team">
	                <div class="lis">
	                    <div class="lis_icon">
	                        <img src="${ctxStatic}/mobile/modules/home/images/workTeam.png" alt="">
	                    </div>
	                    <div class="lis_info">
	                      	  施工团队<span></span>
	                    </div>
	                </div>
	            </a>
	            
	            <a class="line" href="${ctx }/app/home/report/list">
	                <div class="lis">
	                    <div class="lis_icon">
	                        <img src="${ctxStatic}/mobile/modules/home/images/lis3.png" alt="">
	                    </div>
	                    <div class="lis_info">
	                	        质检报告<c:if test="${findUnReadReportCountByCustomerPhone>0}"><span><i class="tips">${findUnReadReportCountByCustomerPhone}</i></span></c:if>
	                    </div>
	                </div>
                </a>
                <a class="line" href="${ctx }/app/home/jobsite/index.php?orderId=4cdbc040-657a-4847-b266-7e31d9e2c3d9,4cdbc040657a4847b2667e31d9e2c3d9">
	                <div class="lis">
	                    <div class="lis_icon">
	                        <img src="${ctxStatic}/mobile/modules/home/images/lis4.png" alt="">
	                    </div>
	                    <div class="lis_info">
							工地直播<c:if test="${findProgressCountByCustomerPhone>0}"><span><i class="tips">${findProgressCountByCustomerPhone}</i></span></c:if>
	                    </div>
	                </div>
                </a>
              <%--  <a class="line" href="${ctx }/app/home/NewApplyProjectChange/list">
	                <div class="lis">
	                    <div class="lis_icon">
	                        <img src="${ctxStatic}/mobile/modules/home/images/lis1.png" alt="">
	                    </div>
	                    <div class="lis_info">
	                    	    我的变更<c:if test="${findProjectChangeCountByCustomerPhone>0}"><span><i class="tips">${findProjectChangeCountByCustomerPhone}</i></span></c:if>
	                    </div>
	                </div>
                </a>--%>
                <a class="line" href="${ctx }/app/home/projectprogress/list.php">
	                <div class="lis">
	                    <div class="lis_icon">
	                        <img src="${ctxStatic}/mobile/modules/home/images/buildIcon.png" alt="">
	                    </div>
	                    <div class="lis_info">
	                    	    施工进度<c:if test="${findProjectProgressCountByCustomerPhone>0}"><span><i class="tips">${findProjectProgressCountByCustomerPhone}</i></span></c:if>
	                    </div>
	                </div>
                </a>
                <%-- <a class="line" href="${ctx }/app/home/evaluate/evalWorker/list">
	                <div class="lis">
	                    <div class="lis_icon">
	                        <img src="${ctxStatic}/mobile/modules/home/images/comment.png" alt="">
	                    </div>
	                    <div class="lis_info">
	                    	    我要评价<c:if test="${findEvalCountByCustomerPhone}"><span><i class="tips">${findEvalCountByCustomerPhone}</i></span></c:if>
	                    </div>
	                </div>
                </a> --%>
                <a class="line" href="${ctx }/app/home/score/order">
	                <div class="lis">
	                    <div class="lis_icon">
	                        <img src="${ctxStatic}/mobile/modules/home/images/icon_Evaluation.png" alt="">
	                    </div>
	                    <div class="lis_info">
	                    	    我的评价<span></span>
	                    </div>
	                </div>
                </a>
                <!--<a class="line" href="${ctx }/app/home/score/complaint">-->
                <a class="line" href="tel:4000010801">
	                <div class="lis">
	                    <div class="lis_icon">
	                        <img src="${ctxStatic}/mobile/modules/home/images/icon_Feedback.png" alt="">
	                    </div>
	                    <!-- <div class="lis_info">
	                    	    投诉反馈<span></span>
	                    </div> -->
	                    <div class="lis_info">
	                    	    咨询投诉（400-001-0801）<span></span>
	                    </div>
	                </div>
                </a>
            </div>
        </div>
        <!--内容部分end-->
        <!--底部公共结构start-->
	
        <!--底部公共结构start-->
        <!-- <div class="bxy_setIndex_warp_footer">
            <footer class="clearfix">
                <div class=" fl myIndex_box">
                    <p class=" myIndex"><span class="icon_myHome"></span>首页</p>
                </div>
                <div class=" myHome_boxC fl">
                    <p class="myHome"><span class="icon_myIndex"></span>我的</p>
                </div>
            </footer>
        </div> -->
        <!--底部公共结构end-->
    </div>
    <div class="black" style="display:none;">
        <div class="cen">

            <div class="loding">
                <img src="${ctxStatic}/mobile/modules/home/images/headAdd.png" alt="">
                <span class="getRefash"></span>
            </div>
            <p>客官 正在加载中 请稍后！</p>
        </div>
    </div>
    <!-- <div class="alertDialog">
    	<div class="tip-wrap">
    		<p class="tip-tit">小美温馨提示</p>
	    	<p class="tip-content">客官，您的订单（长阳欣悦都二期-17-2-1801）在2017年4月3日开工。</p>
	    	<a class="tip-btn" href="javascript:;">我知道了</a>
    	</div>
    </div> -->
</body>
<script src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/base.js"></script>
<!-- <script>
	$(document).on('click', '.tip-btn', function(){
		$('.alertDialog').hide();
	});
</script> -->
</body>
</html>
