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
	<title>申请墙地砖</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/main_switch.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/main_wall.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="g-switch g-wall" id="aboveId">
		<header>
			<a class="back_btn"  href="${ctx}/app/manager/applyMainIngredient"></a>
			<h2 class="title">申请墙地砖</h2>
		</header><!-- /header -->
		<!-- <nav>
			<a href="javascript:void(0)"><span class="active">地砖</span></a>
			<a href="javascript:void(0)"><span>墙砖</span></a>
		</nav> -->
		<section class="swicth_sec wall_sec">
			<div class="switch_list shadow">
				<h3>${materialManagement.communityName }-${materialManagement.buildNumber }-${materialManagement.buildUnit }-${materialManagement.buildRoom }-${materialManagement.customerName }</h3>
					<form id="jvForm" class="jvForm"  method="post" enctype="multipart/form-data">
					
			
				<input type="text" hidden="hidden" class="pics" name="pics" value="">
				<input type="text" hidden="hidden" class="num" name="num" value="">
				<input type="text" hidden="hidden" class="count" name="count" value="">
				<input type="text" hidden="hidden" class="projectMode" name="projectMode" id="projectMode" value="${materialManagement.projectMode}">
				
					<input type="text" hidden="hidden" id="orderId" name="orderId" value="${materialManagement.id }" />
					<input type="text" hidden="hidden" class="wallLength" name="wallLength" value="${wallLength }" />
					<input type="text" hidden="hidden" class="floorLength" name="floorLength" value="${floorLength }" />
					<input type="text" hidden="hidden" class="allLength" name="allLength" value="${allLength }" />
					<ul class="clearfix">
						<li>
							<label for="inspire">期望 进场 日期 ：</label>
							<input id="txtBeginDate" class="date" type="text" readonly="" value="${materialManagement.contractStartDate }" name="inputDate" placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
						</li>
						<li>
							<label for="get_info">收 货 人 信 息 ：</label>
							<input class="get_info" name="getInfo" type="text" value="${materialManagement.itemManager }-${materialManagement.phone}">
						</li>
						<li>
							<span class="meter">主 材 内 容 项 ：</span>
							<span class="switch">墙地砖</span>
						</li>
						<li class="evi">
							<div class="pics">
								<label for="up">纸 质 下 料 单 ：</label>
								<div class="camera" id="upload">
										<img src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="调取摄像头">
										<input type="file" accept="image/*" onchange="preview(this)">
								</div>
								
								<!-- <p class="tip">请拍照上传</p> -->
								<a class="watch_btn active" href="javascript:void(0)">查看照片</a>
								<div  class="masks undis" id="uploaddone">
										
								</div>
							</div>
						</li>
					</ul>
				</div>
				<nav>
					<a href="javascript:void(0)"><span class="active">地砖</span></a>
					<a href="javascript:void(0)"><span>墙砖</span></a>
				</nav>
			
				<div class="wall_all">
					<c:forEach items="${floor }" var="a" varStatus="status">
						<ul class="wall_list shadow">
							<input type="text" hidden="hidden" name="id" value="${a.id }" />
							<li class="clearfix">
								<span>材料分类 ：</span>
								<p>地砖</p>
							</li>
							<li class="clearfix">
								<span>使用位置 ：</span>
								<p>${a.position }</p>
							</li>
							<li class="clearfix">
								<span>品牌套餐 ：</span>
								<p>${a.brandCombo }</p>
							</li>
							<li class="clearfix">
								<span>规格型号 ：</span>
								<p>${a.model }  ${a.specification }</p>
							</li>
							<li class="clearfix">
								<span>单位 ：</span>
								<p>${a.unit }</p>
							</li>
							<li class="clearfix">
								<span>预估数量 ：</span>
								<p>${a.includLossCount } （含损耗数量）</p>
							</li>
							<li class="clearfix">
								<span>实际数量 ：</span>
								<c:if test="${purchaseCount==0 }">
									<p><input class="amount" type="text" name="applyCounta" id="floorAmount${status.index }" value="${a.includLossCount }" ></p>
								</c:if>
								<c:if test="${purchaseCount!=0 }">
									<p><input class="amount" type="text" name="applyCounta" id="floorAmount${status.index }" value="0" ></p>
								</c:if>
							</li>
							<li class="clearfix">
								<span>备注信息 ：</span>
								<p><input class="tip_info" type="text" name="remarks" value="" placeholder="实际数量超出1平米，需要填写说明"></p>
							</li>
						</ul>
					</c:forEach>
				</div>
				
				
				<div class="wall_all undis">
					<c:forEach items="${wall}" var="a" varStatus="status">	
						<ul class="wall_list shadow">
							<input type="text" hidden="hidden" name="id" value="${a.id }"/>
							<li class="clearfix">
								<span>材料分类 ：</span>
								<p>墙砖</p>
							</li>
							<li class="clearfix">
								<span>使用位置 ：</span>
								<p>${a.position }</p>
							</li>
							<li class="clearfix">
								<span>品牌套餐 ：</span>
								<p>${a.brandCombo }</p>
							</li>
							<li class="clearfix">
								<span>规格型号 ：</span>
								<p>${a.model }  ${a.specification }</p>
							</li>
							<li class="clearfix">
								<span>单位 ：</span>
								<p>${a.unit }</p>
							</li>
							<li class="clearfix">
								<span>预估数量 ：</span>
								<p>${a.includLossCount } （含损耗数量）</p>
							</li>
							<li class="clearfix">
								<span>实际数量 ：</span>
								<c:if test="${purchaseCount==0 }">
									<p><input class="amount" type="text" name="applyCounta" id="wallAmount${status.index }" value="${a.includLossCount }" ></p>
								</c:if>
								<c:if test="${purchaseCount!=0 }">
									<p><input class="amount" type="text" name="applyCounta" id="wallAmount${status.index }" value="0" ></p>
								</c:if>
							</li>
							<li class="clearfix">
								<span>备注信息 ：</span>
								<p><input class="tip_info" type="text" name="remarks" value="" placeholder="实际数量超出1平米，需要填写说明"></p>
							</li>
						</ul>
					</c:forEach>
				</div>
			</form>
		</section>
		<a class="apply_btn" href="javascript:void(0)" id="submit">确认申请</a>
		<div class="g-mask undis">
			<div id="g-done_ask">
				<p class="first">存在实际数量为空的材料,确认要提交申请吗？</p>
				<p class="second">
					<a href="">取消</a>
					<a href="javascript:void(0)" id="tosubmit">确认</a>
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script>
		
		function run_waitMe(effect,text){
			$('#aboveId').waitMe({
				effect: effect,
				text: text,
				bg: 'rgba(255,255,255,0.7)',
				color:'#000',
				sizeW:'',
				sizeH:'',
				source: 'img.svg'
			});
		}
	
		$('.watch_btn').on('click',function(){
			$('.g-wall .masks').show();
		});
		$('.g-wall .masks').bind("click",function(){
			$('.g-wall .masks').hide();
		})
		
	    $(function () {
			  $('label').click(function(){
			    var radioId = $(this).attr('id');
			    $('label').removeAttr('class') && $(this).attr('class', 'checked');
			    $('input[type="radio"]').removeAttr('checked') && $('#' + radioId).attr('checked', 'checked');
			  });
	       		// 获取当前日期，结束日期
	    	var now = new Date(),
	    		start = new Date(now.setDate(now.getDate()+3)),
	    		start = globalUtil.fn.formatDate(start, 'yyyy-MM-dd'),
	    		end = new Date(now.setFullYear(now.getFullYear()+1)),
	    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');

	    	var lcalendar = start+','+end;
	    	// 将时间限制加到input标签上。
	    	$('#txtBeginDate').attr('data-lcalendar', lcalendar);
	    	$("#txtBeginDate").val(start);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
	    });
	    $('.g-wall nav a').each(function(index,val){
	    	$(val).click(function(){
	    		$('.g-wall nav a').find('span').removeClass('active');
	    		$('.g-wall nav a').eq(index).find('span').addClass('active');
	    		$('.g-wall .wall_all').addClass('undis');
				$('.g-wall .wall_all').eq(index).removeClass('undis');
	    	});
	    });
	    
	    
	    
	    
	    
	    
	    
		//图片上传显示 
		function preview(file) {  
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic_container"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic);"/></div>').appendTo('#uploaddone');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		} 
		
		
		function uploadpic(pic){
			var hiddenForm = document.getElementById("jvForm");
			var input =document.createElement("input");
			
			
			var count = $(".count").val();
			
			if(pic){
				count++;
				input.setAttribute("id","count"+count);
				input.setAttribute('hidden', 'hidden'); 
				input.setAttribute('name', 'photo'); 
				input.setAttribute("type","text");
				input.setAttribute("value", pic);
				hiddenForm.appendChild(input);
				$(".count").val(count);
			}
			
			var picCount = $(".count").val();
			if(picCount!=null && count>0){
				/* $('#upload').addClass('undis'); */
				/* $('#upload').remove(); */
			}
		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    $(document).ready(function() {
			//绑定onclick事件
			$("#submit").bind('click',submitData);
		});
		function submitData(){
			
			var projectMode = $("#projectMode").val();
			var orderId = $("#orderId").val();
			var info = $(".get_info").val();
			var date = $(".date").val();
			var wallLength = $(".wallLength").val();
			var floorLength = $(".floorLength").val();
			var wallAndFloor = wallLength+floorLength;
			var allLength = $(".allLength").val();
			var num = 0;
			$(".num").val(num);
			var count = $(".count").val();
			
			//传统--必须上传图片（纸质单据）
			if(null!=projectMode && projectMode=='2'){
				if(date == null || date ==""){
					globalUtil.fn.alert('请添加期望进场日期！',2.0);
					return false;
				}
				if(info == null || info == ""){
					globalUtil.fn.alert('请添加收货人信息！',2.0);
					return false;
				}
				if(null==count || count<1){
					globalUtil.fn.alert('请上传纸质单据！',2.0);
					return false;
				}else{
					$("#submit").css("color","#CCC");
					$("#submit").unbind("click");
					run_waitMe("win8",'请稍等 ,拼命提交中....');
					var options ={
							url: "${ctx}/app/manager/applyWallAndFloor",
							type: "post",
							success:function(data){
								if(null!=data && data=="lessTime"){
									globalUtil.fn.alert('您刚刚提交过墙地砖，请耐心等待五分钟后再次操作',4.0);	
									$('#aboveId').waitMe('hide');
									$("#submit").bind('click',submitData);
									$("#submit").css("color","#fff");
								}else if(data=="missingDetails"){
									globalUtil.fn.alert('您有未阅读的墙地砖采购单，请到【申请记录】去查阅后再申请墙地砖',4.0);	
									$('#aboveId').waitMe('hide');
									$("#submit").bind('click',submitData);
									$("#submit").css("color","#fff");
								}else if(""!=data && data>1){
									globalUtil.fn.alert('申请墙地砖成功',4.0);	
									var a ="${ctx}/app/manager/wallAndFloorBrickDetails?purchaseId="+data+"&id="+orderId;
									setTimeout('window.location.href="'+a+'"', 4000);
										
								}else{
									globalUtil.fn.alert('申请墙地砖失败',4.0);	
									var b ="${ctx}/app/manager/applyMainIngredient";
									setTimeout('window.location.href="'+b+'"', 4000);
								}
							}
					}
					$("#jvForm").ajaxSubmit(options);
				}
			}else if(null!=projectMode && projectMode=='1'){
				//产业--必须有墙地砖商品，商品不可都为空
				if(wallAndFloor == "00"){
					globalUtil.fn.alert('请联系接单员，导入墙地砖商品！',2.0);
				}else{
					if(date != null && date !=""){
						if(info != null && info != ""){
							for(var v=0;v<floorLength;v++){
								var floorAmount = $("#floorAmount"+v).val();
								if(floorAmount==null || floorAmount=="" || floorAmount=="0" ||floorAmount=="0.0" || floorAmount=="."){
									var num1 = $(".num").val()+1;
									$(".num").val(num1);
								}
								
							}
							for(var n=0;n<wallLength;n++){
								var wallAmount = $("#wallAmount"+n).val();
								if(wallAmount==null || wallAmount=="" || wallAmount=="0" ||wallAmount=="0.0" || wallAmount=="."){
									var num1 = $(".num").val()+1;
									$(".num").val(num1);
								}
							}
							var num2 = $(".num").val();
							var changdu = num2.length;
							if(changdu == allLength){
								globalUtil.fn.alert('请输入材料实际用量！',2.0);
							}else{
								if(num2!=0){
									$('.g-mask').show();
								}else{
									$("#submit").css("color","#CCC");
									$("#submit").unbind("click");
									run_waitMe("win8",'请稍等 ,拼命提交中....');
									var options ={
											url: "${ctx}/app/manager/applyWallAndFloor",
											type: "post",
											success:function(data){
												if(null!=data && data=="lessTime"){
													globalUtil.fn.alert('您刚刚提交过墙地砖，请耐心等待五分钟后再次操作',4.0);	
													$('#aboveId').waitMe('hide');
													$("#submit").bind('click',submitData);
													$("#submit").css("color","#fff");
												}else if(data=="missingDetails"){
													globalUtil.fn.alert('您有未阅读的墙地砖采购单，请到【申请记录】去查阅后再申请墙地砖',4.0);	
													$('#aboveId').waitMe('hide');
													$("#submit").bind('click',submitData);
													$("#submit").css("color","#fff");
												}else if(""!=data && data>1){
													$('#aboveId').waitMe('hide');
													globalUtil.fn.alert('申请墙地砖成功',4.0);	
													var a ="${ctx}/app/manager/wallAndFloorBrickDetails?purchaseId="+data+"&id="+orderId;
													setTimeout('window.location.href="'+a+'"', 4000);
												}else{
													$('#aboveId').waitMe('hide');
													globalUtil.fn.alert('申请墙地砖失败',4.0);	
													var b ="${ctx}/app/manager/applyMainIngredient";
													setTimeout('window.location.href="'+b+'"', 4000);
												}
											}
									}
									$("#jvForm").ajaxSubmit(options);
								}
							}
						}else{
							globalUtil.fn.alert('请添加收货人信息！',2.0);
						}
					}else{
						globalUtil.fn.alert('请添加期望进场日期！',2.0);
					}
				}
			}
		}
		$(document).ready(function() {
			//绑定onclick事件
			$("#tosubmit").bind('click',submitData1);
		});
		
		function submitData1(){
			$("#tosubmit").css("color","#CCC");
			$("#tosubmit").unbind("click");
			run_waitMe("win8",'请稍等 ,拼命提交中....');
			var orderId = $("#orderId").val();
			var options ={
					url: "${ctx}/app/manager/applyWallAndFloor",
					type: "post",
					success:function(data){
						if(null!=data && data=="lessTime"){
							globalUtil.fn.alert('您刚刚提交过墙地砖，请耐心等待五分钟后再次操作',4.0);	
							$('#aboveId').waitMe('hide');
							$("#tosubmit").bind('click',submitData);
							$("#tosubmit").css("color","#fff");
						}else if(data=="missingDetails"){
							globalUtil.fn.alert('您有未阅读的墙地砖采购单，请到【申请记录】去查阅后再申请墙地砖',4.0);	
							$('#aboveId').waitMe('hide');
							$("#tosubmit").bind('click',submitData);
							$("#tosubmit").css("color","#fff");
						}else if(""!=data && data>1){
							$('#aboveId').waitMe('hide');
							globalUtil.fn.alert('申请墙地砖成功',4.0);	
							var a ="${ctx}/app/manager/wallAndFloorBrickDetails?purchaseId="+data+"&id="+orderId;
							setTimeout('window.location.href="'+a+'"', 4000);
								
						}else{
							$('#aboveId').waitMe('hide');
							globalUtil.fn.alert('申请墙地砖失败',4.0);	
							var b ="${ctx}/app/manager/applyMainIngredient";
							setTimeout('window.location.href="'+b+'"', 4000);
								
						}
							
						
					}
			}
			
			$("#jvForm").ajaxSubmit(options);
			
			
			
		}
	</script>
</body>
</html>