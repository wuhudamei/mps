<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>施工变更</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/change.css"/>
</head>
<body>
	<div class="">
		<header>
			<h2 class="title">施工变更</h2>
		</header><!-- /header -->
		<section class="done_apply_list">
			<c:forEach items="${list }" var="list">
				<div class="lists shadow clearfix">
					<a href="${ctx }/app/home/applyProjectChange/details?projectChangeId=${list.projectChangeId}">
						<ul>
							<li class="clearfix">
								<span>提报日期：</span>
								<p><fmt:formatDate value="${list.applyDate }" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="clearfix">
								<span>增项总价：</span>
								<p>${list.addItemTotalPrice }元</p>
							</li>
							<li class="clearfix">
								<span>减项总价：</span>
								<p>-${list.minusItemTotalPrice }元</p>
							</li>
							<li class="clearfix">
								<span>当前状态：</span>
								<p class="">${list.statusName }</p>
							</li>
						</ul>
					</a>
					<div class="btns">
						<a class="btn agree_btn" href="#" onclick="agree('${list.projectChangeId}')">同意</a>
						<a class="btn refuse_btn" href="#" onclick="refuse('${list.projectChangeId}')">不同意</a>
					</div>
				</div>
			</c:forEach>
		</section>
		<div class="g-mask mask_y undis">
			<div id="g-done_ask">
				<p class="first">尊敬的客户，您确认同意施工变更内容？</p>
				<p class="second">
					<a href="#" id="noReject">取消</a>
					<a href="#" id="yesReject">确认</a>
				</p>
			</div>
		</div>
		<div class="g-mask mask_n undis">
			<div id="g-reason">
				<div class="reason_container">
					<div class="reason_wrapper">
						<textarea class="reason_content" placeholder="请输入您的意见，我们会认真修改！" name="reasons" id="reason"></textarea>
					</div>
				</div>
				<p class="second">
					<a href="#" id="noReject2">取消</a>
					<a href="#" id="yesReject2">确认</a>
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
		
	
		$(document).ready(function() {
			//绑定onclick事件
			$("#noReject").bind('click',noReject);
			$("#yesReject").bind('click',yesReject); 
			$("#noReject2").bind('click',noRejectTwo);
		});
	
	
		var projectChangeId;
		//同意
		function agree(id){
			projectChangeId = id;
			$('.mask_y').removeClass('undis');
			
		}
		//同意--取消
		function noReject(){
			$("#reason").val("");
			$('.mask_y').addClass("undis");
		}
		
		//同意--确认
		function yesReject(){
			$("#yesReject").unbind("click");
			$("#noReject").unbind("click");
			var reason="";
			$.ajax({
				url:"${ctx}/app/home/applyProjectChange/agree",
				type : "get",
				dataType : "json",
				data:{
					projectChangeId:projectChangeId,
					reason : reason
					},
				success : function(data){
					$(".mask_y").addClass("undis");
					globalUtil.fn.alert('审核成功',2.0);
			  	 	window.location.href = "${ctx}/app/home/applyProjectChange/list";
			 	 }
			});
		} 
		
		//不同意
		function refuse(id){
			projectChangeId = id;
			$('.mask_n').removeClass('undis');
			
		}
		
		
		//不同意--取消
		function noRejectTwo(){
			$("#reason").val("");
        	$("#yesReject2").unbind("click");
			$("#yesReject2").css("color","#CCC"); 
			$('.mask_n').addClass("undis");
		}
		
		window.onload =function(){
	        document.getElementById("reason").onblur = function(){
	            if(this.value!=""&& this.value.replace(/(^\s*)|(\s*$)/g, "")!=""){
	            	$("#yesReject2").css("color","#0780ec");
	            	$("#yesReject2").bind('click',yesRejectTwo);
	            }else{
	            	$("#reason").val("");
	            	$("#yesReject2").css("color","#CCC"); 
	            	$("#yesReject2").unbind("click");
	            }
	        }
	    }  
		
		//不同意--确认
		function yesRejectTwo(){
			$("#yesReject2").unbind("click");
			$("#noReject2").unbind("click");
			$("#yesReject2").css("color","#CCC"); 
			
			var reason = $("#reason").val();
			
			if(reason!=null && reason.replace(/(^\s*)|(\s*$)/g, "")!=""){
				$.ajax({
					url:"${ctx}/app/home/applyProjectChange/refuse",
					type : "get",
					dataType : "json",
					data:{
						projectChangeId:projectChangeId,
						reason : reason
						},
					success : function(data){
						$("#reason").val("");
						$(".mask_n").addClass("undis");
						globalUtil.fn.alert('审核成功',2.0);
				  	 	window.location.href = "${ctx}/app/home/applyProjectChange/list";
				 	 }
				});
			}else{
				$("#reason").val("");
				globalUtil.fn.alert('请输入您的意见',2.0);
			}
		} 
		
		
	</script>
</body>
</html>