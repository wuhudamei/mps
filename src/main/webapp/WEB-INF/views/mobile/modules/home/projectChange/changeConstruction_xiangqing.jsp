<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/globalUtil.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/base.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/bxyTopCom.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/bxyMask.css">
    <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/changeConstrucionwarp.css">
    <title>变更详情</title>
</head>
<body>


<!--页面浮层意见start-->
<div class="bxy_advise_mask">
    <div class="bxy_updata_mask_top">
        <p>小美温馨提示</p>
    </div>
    <div class="bxy_advise_mask_con">
        <textarea placeholder="客官，请输入您的意见，我们会认真修改！" id="reason"></textarea>
    </div>
    <div class="bxy_updata_mask_bottom clearfix">
        <div class="bxy_updata_mask_bottom_left fl" id="changeAsked1">
            确定
        </div>
        <div class="bxy_updata_mask_bottom_right fr">
            取消
        </div>
    </div>
</div>
<!--页面浮层是否意见end-->
<!--页面浮层是否变更start-->
<div class="bxy_updata_mask"  id="changeAree">
    <div class="bxy_updata_mask_top">
        <p>小美温馨提示</p>
    </div>
    <div class="bxy_updata_mask_con">
        <p>客官,您确认同意施工变更内容了吗?</p>
    </div>
    <div class="bxy_updata_mask_bottom clearfix">
        <div class="bxy_updata_mask_bottom_left fl" id="changeAsked">
            确定
        </div>
        <div class="bxy_updata_mask_bottom_right fr">
            取消
        </div>
    </div>
</div>
<!--页面浮层是否变更end-->
 <div class="bxy_changeConstrucion_xiangqing_warp">
     <!--顶部公共样式start-->
     <div class="bxy_setIndex_warp_header">
         <header><a href="${ctx}/app/home/NewApplyProjectChange/list?orderId=${projectChange.orderId}"><span class="icon_back"></span></a><p>变更详情</p></header>
     </div>
     <!--顶部公共结构end-->
     <div class="bxy_changeConstrucion_xiangqing_warp_first" style="padding-top: .88rem;">
        <h3><i class="infoBlue"></i>基本信息</h3>
         <div class="hangeConstrucion_xiangqing">
         <input type="text" hidden="hidden" id="projectChangeId" value="${projectChange.projectChangeId }">
         <input type="text" hidden="hidden" id="orderId" value="${projectChange.orderId }">
             <div class="hangeConstrucion_xiangqing_des">
                 <div class="hangeConstrucion_time clearfix">
                     <p class="fl">小&nbsp;区&nbsp;名&nbsp;称:</p>
                     <p class="addressInfo clearfix">${projectChange.communityName }-${projectChange.buildNumber }-${projectChange.buildUnit }-${projectChange.buildRoom }</p>
                 </div>
                 <div class="hangeConstrucion_time clearfix">
                     <p class="fl">变更申请人:</p>
                     <p class="addressInfo clearfix">${projectChange.itemManager }</p>
                 </div>
                 <div class="hangeConstrucion_time clearfix">
                     <p class="fl">申&nbsp;请&nbsp;日&nbsp;期:</p>
                     <p class="addressInfo clearfix"><fmt:formatDate value="${projectChange.applyDate }" pattern="yyyy-MM-dd"/></p>
                 </div>
             </div>

         </div>
     </div>
     <div class="bxy_changeConstrucion_xiangqing_warp_first">
         <h3><i class="infoBlue"></i>变更汇总</h3>
         <div class="hangeConstrucion_xiangqing">
             <div class="hangeConstrucion_xiangqing_des">
                 <div class="hangeConstrucion_time clearfix">
                     <p class="fl">变更原因:</p>
                     <p class="addressfo">${projectChange.changeReason }</p>
                 </div>
                 <div class="hangeConstrucion_time clearfix">
                     <p class="fl">增项总额:</p>
                     <p class="addressfo">${projectChange.addItemTotalPrice }元</p>
                 </div>
                 <div class="hangeConstrucion_time clearfix">
                     <p class="fl">减项总额:</p>
                     <p class="addressfo">${projectChange.subItemTotalPrice }元</p>
                 </div>
                 <div class="hangeConstrucion_time clearfix">
                     <p class="fl">合计总额 :</p>
                     <p class="addressfo">${projectChange.allPrice }元（增项总额-减项总额所得）</p>
                 </div>
             </div>

         </div>
     </div>
     <div class="bxy_changeConstrucion_xiangqing_warp_first mb30" style="margin-bottom:300px;">
         <h3><i class="infoBlue"></i>变更详情</h3>
         <div class="hangeConstrucion_xiangqing">
         	<c:forEach items="${projectChange.changeItemList }" var="item">
         			<div class="hangeConstrucion_xiangqing_des border-btm clearfix">
	         			<c:if test="${item.projectIntemMold=='1' }">
		         			<i class="addCrease"></i>
		         		</c:if>
		         		<c:if test="${item.projectIntemMold=='2' }">
		         			<i class="Crease"></i>
		         		</c:if>
		                 <div class="hangeConstrucion_time">
		                     <p class="fl">施工项:</p>
		                     <p class="addressIn">${item.projectIntemName }</p>
		                 </div>
		                 <div class="hangeConstrucion_time">
		                     <p class="fl">单&nbsp;&nbsp;&nbsp;位:</p>
		                     <p class="addressIn">${item.projectIntemUnit }</p>
		                 </div>
		                 <div class="hangeConstrucion_time">
		                     <p class="fl">单&nbsp;&nbsp;&nbsp;价:</p>
		                     <p class="addressIn">${item.projectIntemPrice }</p>
		                 </div>
		                 <div class="hangeConstrucion_time">
		                     <p class="fl">数&nbsp;&nbsp;&nbsp;量:</p>
		                     <p class="addressIn">${item.projectIntemAmount }</p>
		                 </div>
		                 <div class="hangeConstrucion_time">
		                     <p class="fl">金&nbsp;&nbsp;&nbsp;额:</p>
		                     <p class="addressIn">${item.everyPrice }元</p>
		                 </div>
		                 <div class="hangeConstrucion_time">
		                     <p class="fl">说&nbsp;&nbsp;&nbsp;明:</p>
		                     <p class="addressIn">${item.explainWords }</p>
		                 </div>
	             	</div>
         	</c:forEach>
    	 </div>
   	</div> 	 
     <!--底部start-->
     <c:if test="${projectChange.status=='30' }">
	    <div class="hangeConstrucion_bottom">
	        <ul class="clearfix">
	            <li class="fl" id="agreeChange">同意</li>
	            <i class="lineB"></i>
	            <li class="fr" id="unagreeChange">不同意</li>
	        </ul>
	    </div>
    </c:if>
    <!--底部end-->
</div>
</body>
<script src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/global.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/setIndex.js"></script>
<script src="${ctxStatic}/mobile/modules/home/js/utils/changeCare.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		//绑定onclick事件
		$("#changeAsked1").bind('click',bohui);
		$("#changeAsked").bind('click',tongguo); 
	});
	
	function bohui(){
		var projectChangeId = $("#projectChangeId").val();
		var orderId = $("#orderId").val();
		var reason = $("#reason").val();
		if(reason!=null && reason.replace(/(^\s*)|(\s*$)/g, "")!=""){
			$.ajax({
				url:"${ctx}/app/home/NewApplyProjectChange/refuse",
				type : "get",
				dataType : "json",
				data:{
					projectChangeId:projectChangeId,
					reason : reason
					},
				success : function(data){
					$("#reason").val("");
					globalUtil.fn.alert("变更单审核成功!",2.0);
					window.location.href = "${ctx}/app/home/NewApplyProjectChange/list?orderId="+orderId;
			 	 }
			});
		}else{
			$("#reason").val("");
			globalUtil.fn.alert('请输入您的意见',2.0);
		}
		
	}
	function tongguo(){
		var projectChangeId = $("#projectChangeId").val();
		var orderId = $("#orderId").val();
		var reason="";
		$.ajax({
			url:"${ctx}/app/home/NewApplyProjectChange/agree",
			type : "get",
			dataType : "json",
			data:{
				projectChangeId:projectChangeId,
				reason : reason
				},
			success : function(data){
				globalUtil.fn.alert("变更单审核成功!",2.0);
				window.location.href = "${ctx}/app/home/NewApplyProjectChange/list?orderId="+orderId;
		 	 }
		});
		
	}


</script>
</html>