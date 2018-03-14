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
	<title>结算申请</title>
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/reset.css">
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/common.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
	<link rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/Settlement.css">
</head>

<body class="back_fff">
	<div class="wrap">
		<header>
			<a class="back_btn New_btns" href="${ctx}/app/manager/tradition-manager-settle/order-settle.php"></a>
			<h2 class="title">结算申请</h2>
		</header>
		<section class="pad_t9">

			<div class="set_apply col_3">
				<c:forEach items="${entity.settleList}" var="settle" varStatus="status">
					<p  onclick="toUploadSettleInfo('${settle.isCheckNodeDone}','${settle.isMoneyReceive}','${status.index+1}','${settle.settleId}',${settle.settleIndex},${settle.nodeIndex})">
						<span>${settle.settleNodeName}</span>
						<input type="text" hidden="hidden" value="${settle.isRequired}" id="id${status.index+1}">
						<input type="text" hidden="hidden" value="${settle.settleStatus}">



						<c:if test="${settle.settleStatus==3}">
					<span class="col_red">
								${settle.settleStatusName} <i></i></span>


						</c:if>
						<c:if test="${settle.settleStatus!=3}">
			<span>
								${settle.settleStatusName} <i></i></span>


						</c:if>

					</p>
				</c:forEach>



				<c:forEach items="${checkDoneList}" var="isCheckDone" varStatus="checkDoneStatus">

					<input type="text" hidden="hidden" value="${isCheckDone}" id="checkNodeDone${checkDoneStatus.index+1}">
				</c:forEach>
			<%--	<p>
					<span>拆改款</span>
					<span onclick="toUploadSettleInfo()">已打款<i></i></span>
				</p>
				<p>
					<span>首期款</span>
					<span>结算中<i></i></span>
				</p>
				<p>
					<span>中期款</span>
					<span class="col_red">已拒绝<i></i></span>
				</p>
				<p class="btn">
					<span>尾期款</span>
					<span>待处理<i></i></span>
				</p>--%>
			</div>
		</section>
	</div>
	<div class="black">
		<div class="B_cen">
			<div class="icon"></div>
			<div class="icon_info">请确认质检是否完成验收 客户是否缴纳对应款项！</div>
			<div class="icon_btn">确定</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>

<script>
    $(".icon_btn").on("click", function () {
        $(".black").hide();
    })
	function toUploadSettleInfo(isCheckNodeDone,isMoneyReceive,index,settleId,settleIndex,nodeIndex){

	    //检查是否必选和上个节点的状态值是否允许申请结算
 if(index>1){

     for(var v=1;v<index;v++){
	//是否必选
        var isRequired= $("#id"+v).val();
        //状态值
        var settleStatus = $("#id"+v).next().val();



        if(isRequired==2){

            if(settleStatus=="" ||settleStatus==1 ){


            $(".icon_info").text("您上一个结算节点还没有处理完毕,请耐心等待");
            $(".black").show();
            return;
            }
		}

	 }

 }

 //当前节点如果没有验收,要查看他之后的节点是否验收, 如果已经验收那么通过,默认当前节点也已经验收
 var isNodeDone=0;

// var indexLength=parseInt(index);

	if(isCheckNodeDone==0){
        for(var v=1;v<100;v++){

    if($("#checkNodeDone"+v)!=undefined){

       if($("#checkNodeDone"+v).val()>nodeIndex){
              //之后的节点已经验收
              isNodeDone=1;
              break;
	   }

    }else{
        //节点已经遍历完毕,结束

        break;
	}
        }

	}

	    //发出提示
	if(isNodeDone==0&&isCheckNodeDone==0 /*||isMoneyReceive==0*/){
			//节点是否验收  0:没有验收 对应的款项是否已经收到  0 没有收到

            $(".icon_info").text("请确认质检是否完成验收 客户是否缴纳对应款项！");
			$(".black").show();
			return;

		}


        window.location.href="${ctx}/app/manager/tradition-manager-settle/toUploadSettleInfo.php?settleId="+settleId+"&orderId=${entity.orderId}&settleIndex="+settleIndex


	}

</script>
</html>