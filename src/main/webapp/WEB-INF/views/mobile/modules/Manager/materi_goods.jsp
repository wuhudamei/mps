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
	<title>收货</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/materi.css"/>
</head>
<body>
	<div class="g-materi">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/purchaseList"></a>
			<h2 class="title">收货</h2>
		</header><!-- /header -->
		<form id="jvForm" method="post">
		<input type="hidden" id="purchaseId" name="purchaseId" value="${id }">
		<input type="hidden" name = "purchaseType" value ="${purchaseType}">
		<div class="zone shadow">
			<div class="funs">
				<span class="col">实际收货日期</span>
				<p class="align_rgt">
					<input id="txtBeginDate" class="goods_date" type="text" value="" name="txtBeginDate" readonly="readonly" placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
				</p>
			</div>
			<div class="funs">
				<span class="col">上传收货单</span>
				<div class="up camera" id ="camera"> 
					<img src="${ctxStatic}/mobile/modules/Manager/images/pic_load.png" alt="上传照片">
					<input type="file" accept="image/*"  onchange="preview(this)">
				</div>
					<input type="text" hidden="hidden" id="shit"  value="1">
				<a class="wtc_btn" href="javascript:void(0)">已上传<span id="picLength">${fn:length(picList)}</span>张</a>
			</div>
		</div>
		
		<input type="text" hidden="hidden" id="num" name="num" value="">
		<ul class="show_sec">
			<c:forEach items = "${list }" var ="material" varStatus="status">
				<input type="hidden" name="ids" value="${material.id}"/>
				<li class="item shadow">
					<div class="img_container">
						<img src="${baseUrl }${material.picUrl}" alt="goods" onerror="nofind()" >
					</div>
					<c:if test="${ material.brands != null}">
						<p class="brand"><em>【${ material.brands}】</em>${material.name }</p>
					</c:if>
					<c:if test="${ material.brands == null}">
						<p class="brand">${material.name }</p>
					</c:if>
					<c:if test="${ material.model == null}">
						<p class="format">型号/规格：/${material.specifications }</p>
					</c:if>
					<c:if test="${ material.model != null}">
						<p class="format">型号/规格：${material.model}/${material.specifications }</p>
					</c:if>
					<p class="price">申请数量/已收货数量：
						<em>
							${material.count }
						</em>${material.unit } / 
						<em>															 
							<%-- <c:if test="${ material.receivedCount != null } ">${material.receivedCount }</c:if>
							<c:if test="${ material.receivedCount == null } ">0.0</c:if> --%>
							${material.receivedCount }
						</em>${material.unit }
					</p>
					<div class="clearfix shopcount">
						<a class="reduce" href="javascript:;"></a>
						<input class="count" type="text" id="receivingCount${status.index+1}" name="receivingCounts" value="${material.owedCount}" placeholder=""><!-- readonly="" -->
						<input type="hidden" id ="owedCount${status.index+1}" value="${material.owedCount}">
						<input type="hidden" id = "materialName${status.index+1}" value="${ material.brands }${material.name }">
						<a class="plus" href="javascript:;"></a>
					</div>
				</li>
			</c:forEach>
		</ul>
		</form>
		<footer>
			<p class="goods">共使用${fn:length(list)}种商品</p>
			<a class="choose_btn" id="comfirmbtn" href="javascript:void(0)">确认收货</a>
		</footer>
		<!-- 照片弹出层 -->
		<div class="mask mask1 undis">
			<div class="pic_container clearfix" id="foo">
				<c:forEach items = "${picList }" var = "picture">
					<div class="pic_wraper clearfix">
						<img src="${baseUrl }${picture.picUrl}" alt="收货单">
						<a class="del_btn" href="javascript:void(0)" onclick="deletePic('${picture.id}')">删除</a>
					</div>
					
				</c:forEach>
			</div>
			<a class="back" href="javascript:void(0)">返回</a>
		</div>
		<!-- 查看大图弹出层 -->
		<div class="mask mask2 undis">
			<div class="big_pic_wraper">
				<img class="big_pic" id="big_pic" alt="收货单">
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
<%-- 	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/shopcount.js"></script> --%>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script>
		$(document).ready(function() {
			 //绑定onclick事件
			$("#comfirmbtn").bind('click',submitForm); 
		});	
		
		function submitForm(){
			var length = ${fn:length(list)};
			var flag = false;
			var flag1 = false;
			for(var i=1;i<=length;i++){
				var receivingCount = $("#receivingCount"+i).val();//本次收货的数量
				//判断本次收货的数量是否为零
				if(receivingCount != 0){
					flag1 = true;
					break;
				}  
			}
			if(!flag1){
				globalUtil.fn.alert('请选择收货的数量',1.5);
				return;
			}
			for(var i=1;i<=length;i++){
				var owedCount1 = $("#owedCount"+i).val();//欠货的数量
				//alert(owedCount1+"欠货数量");
				var receivingCount = $("#receivingCount"+i).val();//本次收货的数量
				//alert(receivingCount+"本次收货的数量");
				/* var userCount = (owedCount1 == null || owedCount1 == "") ? 0 : owedCount1;
				alert(userCount+"c"); */
				var materialName = $("#materialName"+i).val();//材料的名称
				//判断本次收货的数量是否大于欠货的数量
				if(receivingCount>owedCount1){
					globalUtil.fn.alert(materialName+'收货数量大于欠货数量',1.5);
					flag = true;
					break;
				}
			}
			if(flag){
				return;
			}
			$("#comfirmbtn").unbind("click");//禁止重复提交
			var options ={
					url: "${ctx}/app/manager/comfirmReceive",
					type: "post",
					success:function(data){
						if(data=="0"){
							globalUtil.fn.alert('收货成功!',2.0);			
							var a ="${ctx}/app/manager/purchaseList";
							setTimeout('window.location.href="'+a+'"', 2000);
						}else if(data=="1"){
							globalUtil.fn.alert('您刚刚提交过收货单，请耐心等待五分钟后再次操作',2.0);			
							var a ="${ctx}/app/manager/purchaseList";
							setTimeout('window.location.href="'+a+'"', 2000);
						}
					}
			}
			
			$("#jvForm").ajaxSubmit(options);
		}
		
		$(function(){
			$(document).on('click','.wtc_btn',function(){
				$('.mask1').removeClass('undis');
			});
			$(document).on('click','.pic_wraper img',function(){
				var purl = $(this).attr("src");
				$("#big_pic").attr("src",purl);
				$('.mask2').removeClass('undis');
			});
			$(document).on('click','.mask2',function(){
				$('.mask2').addClass('undis');
			});
			$(document).on('click','.back',function(){
				$('.mask1').addClass('undis');
			});
	    	var now = new Date(),
	    		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
	    		end = new Date(now.setFullYear(now.getFullYear()+1)),
	    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');
	    	var lcalendar = start+','+end;
	    	// 将时间限制加到input标签上。
	    	$('#txtBeginDate').val(start);
	    	$('#txtBeginDate').attr('data-lcalendar', lcalendar);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
			// 购物车加减
			$(document).on('click', '.reduce', function(){
				var num = parseInt($(this).parent().find('.count').val());
				if(num > 0){
					num --;
					$(this).parent().find('.count').val(num);
				}else{
					console.log('就不改')
				}
			});
			$(document).on('click', '.plus', function(){
				var num = parseInt($(this).parent().find('.count').val());
				if(num < 501){
					num ++;
					$(this).parent().find('.count').val(num);
				}else{
					console.log('您最多购买500个')
				}
			});
			
			
			
		}());
		
		//图片没有 就使用默认图片
		function nofind(){

			 var img = event.srcElement;
			
			 img.src="/static/mobile/modules/Manager/css/photo/auxiliaryPhotoForNothing.png";

			 img.onerror=null; 

			 } 
		
		
		//图片上传显示 
		function preview(file) {  
			var prevDiv = $('.camera');  
			var num = $("#shit").val();
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic_wraper"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic);" id="'+num+'"/><a class="del_btn" href="javascript:void(0)">删除</a></div>').appendTo('.pic_container');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		} 
		$(document).on('click', '.del_btn', function(){
			var numReal=$(this).prev().attr("id");
			$(("#num"+numReal)).remove();
			var num = $("#num").val();
			num--;
			$("#num").val(num);
			
			$(this).parent().remove();
			
			globalUtil.fn.alert('删除图片操作成功',2.0);
			
			
			var divNum = $("#foo > div").size();
			
			$("#picLength").text(divNum);
		});
		
		function uploadpic(pic){
			
			var hiddenForm = document.getElementById("jvForm");
			var input =document.createElement("input");
			
			var shit =	$("#shit").val();
			var num = $("#num").val();
			if(pic){
				num++;
				input.setAttribute("id","num"+shit);
				input.setAttribute('hidden', 'hidden'); 
				input.setAttribute('name', 'photo'); 
				input.setAttribute("type","text");
				input.setAttribute("value", pic);
				hiddenForm.appendChild(input);
				
				shit++;
				$("#num").val(num);
				$("#shit").val(shit);
			}
			
			
			var divNum = $("#foo > div").size();
			
			$("#picLength").text(divNum);
		}
		
		
		
		function deletePic(id){
			$.ajax({
				url : "${ctx}/app/manager/deletePic",
				type : "POST",
			    async : false,
			    data : {
			    	id : id
				  },
				  success : function(data){
					  if(data == 0){
						  globalUtil.fn.alert('删除图片操作成功',2.0);
					  }
					  
				  }
			});
		}
	</script>
</html>