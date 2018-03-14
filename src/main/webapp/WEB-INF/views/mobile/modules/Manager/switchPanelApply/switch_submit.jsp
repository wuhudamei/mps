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
	<title>提交开关面板</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<%-- <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/mui.min.css"/> --%>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/auxiliary_choose.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/coat.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/switchpane_pic.css"/>
	<link type="text/css" rel="stylesheet" 	href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
	
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/metri/metriGet.css"/>
	
	
    <script src="${ctxStatic}/mobile/modules/Manager/js/lib/mui.min.js"></script>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
   	 //图片没有 就使用默认图片
   	 function nofind(){

   		 var img=event.srcElement;
   		
   		 img.src="/mobile/modules/Manager/images/eg.png";

   		 img.onerror=null; 

   		 } 
   	  	
      	
      	
    </script>
</head>
<body>
	<div class="g-auxiliary_choose g-auxiliary_count">
	
		<header>
			<a class="back_btn" href="${ctx}/app/manager/applySwitchPanel/orderList" href="javascript:void(0)"></a>
			<h2 class="title">提交开关面板</h2>
		</header><!-- /header -->
		<form action="${ctx}/app/manager/applySwitchPanel/submit" method="post" id="form">
			<input type="text" hidden="hidden" id="isPunish" value="0" name="overCount">
			<input type="text" hidden="hidden" id="currentcount" value="0" name="currentcount">
		<div class="show_sec">
		
			<div class="info shadow clearfix">
				<div>
					<label for="inspire">期望到场日期：</label>
					<input style="width: 190" id="txtBeginDate" class="date" type="text" readonly="" value="" name="hopeForTime" placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
				</div>
				
				<div class="mar_btm24">
					<span class="font28 col_6f6f70">
						<label class="label-left">收货人员信息：</label>
						<textarea id="managerInfoAdd" class="sand-tip" name="remarks"   placeholder="${managerInfo}" onkeyup="this.value = this.value.substring(0,30)" ></textarea>
					<input type="hidden" id="managerInfo" value="${managerInfo }">
					<input type="hidden" id="managerName" value="${managerName }">
					<input type="hidden" id="managerPhone" value="${managerPhone }">
					</span>
				</div>
				<div>
					<label for="get_info">标配数量：</label>
					<span>${purchaseVo.contractAreaForApplySwitchPane}</span>
				</div>
			</div>
			<input type="text" hidden="hidden" name="orderId" value="${orderId}">

			<ul class="buy">
			<c:forEach items="${list }" var="switchPanel">
				<input type="text" hidden="hidden" name="brand" value="${switchPanel.brands}">
				<input type="text" hidden="hidden" name="ids" value="${switchPanel.id }">
				<li class="shadow">
					<div class="img_container">
					<c:if test="${not empty  switchPanel.picUrl }" >
						<img src="${switchPanel.picUrl }" alt="" onerror="nofind()">
					</c:if>
					<c:if test="${ empty  switchPanel.picUrl }" >
						<img src="${ctxStatic}/mobile/modules/Manager/images/eg.png" alt="" onerror="nofind()">
					</c:if>
					</div>
					<p class="brand">【${switchPanel.brands}】${switchPanel.switchPanelName }</p>
					<p class="model_sub">规格:【${switchPanel.specification }】 </p>
					<p class="price">
					&nbsp;&nbsp;&nbsp;<span class="col_red">${switchPanel.price }</span>元/${switchPanel.measurementUnit }
							</p>
					
					
					<%-- <div id="numbox" class="mui-numbox" data-numbox-step='1' data-numbox-min='0' data-numbox-max='999'>
					 <button class="mui-btn mui-numbox-btn-minus" type="button" style="" onclick="setTotal()">-</button> 
					 <!-- 价格需要计算到总量限制中的开关面板 -->
					 <c:if test="${switchPanel.isCount ==1 }">
					 <input class="mui-numbox-input " type="number" value="" style="" name="count" onchange="setTotal()" />
						<input type="text" hidden="hidden" value="521">
					
					 </c:if>
					 <c:if test="${switchPanel.isCount !=1 }">
					 <input class="mui-numbox-input" type="number" value="" style="" name="count" onchange="setTotal()" />
					 </c:if>
					 <button class="mui-btn mui-numbox-btn-plus" type="button" style="" onclick="setTotal()">+</button> 
					</div> --%>
					
					
					
					<div id="numbox" class="mui-numbox" style="width: 2.04rem;" data-numbox-step='1' data-numbox-min='0' data-numbox-max='999'>
					 <button class="mui-btn mui-numbox-btn-minus" type="button" style="border:0;box-sizing:border-box;width: .52rem!important;height: .44rem!important;background-color: #fff!important;border-right: 1px solid #9cccf7;position: absolute;" onclick="setTotal()">-</button> 
					 <!-- 价格需要计算到总量限制中的开关面板 -->
					 <c:if test="${switchPanel.isCount ==1 }">
					 <input class="mui-numbox-input " type="number" value="" style="width: 1rem;height: inherit;position: absolute;left: .52rem;border: 0;font-size: .24rem;text-align: center;" name="count" onchange="setTotal()" />
						<input type="text" hidden="hidden" value="521">
					 </c:if>
					 <c:if test="${switchPanel.isCount !=1 }">
					 <input class="mui-numbox-input" type="number" value="" style="width: 1rem;height: inherit;position: absolute;left: .52rem;border: 0;font-size: .24rem;text-align: center;" name="count" onchange="setTotal()" />
					 </c:if>
					 <button class="mui-btn mui-numbox-btn-plus" type="button" style="border:0;box-sizing:border-box;width: .52rem!important;height: .44rem!important;background-color: #fff!important;border-left: 1px solid #9cccf7;position: absolute;left: 1.52rem;" onclick="setTotal()">+</button> 
					</div>
				</li>
			</c:forEach>
			</ul>
			
			
		</div>
		<input type="text" hidden="hidden" id="totalMoney" name="totalMoney">
		
		<footer class="sub_footer">
		<p class="col_red">合计：<span id="total"></span></p><span hidden="hidden">元</span>
				<p class="goods" id="count"></p>
			<a class="choose_btn" href="javascript:void(0)" onclick="submit()"  id="sub">提交开关面板</a>
		</footer>
	</div>
	
	
	<!-- 不符合标准的弹框 -->
	<div class="coat undis">
		<div class="coat_content">
			<p class="coat_reason font30 col_3"></p>
			<div class="bg_f">
				<p class="mar_left3 pad_top5 mar_btm3 font28 col_6">选择原因</p>
				<div class="select_total">
					<div class="select-area">
						<div class="select-value">
							<span class="font24 col_blue mar_left1">变更</span>
							<i class="select-btn"></i>
						</div>
						<div class="options undis">
							<span>变更</span>
							<span>其他原因</span>
						</div>
					</div>
				</div>
				<div class="mar_left3 font0 must undis">
					<p class="font28 col_6 pad_top5 mar_btm3">备注说明</p> <span id ="wordsInfo" class="col_red font24" hidden="hidden">请填写备注说明</span>
					<input class="note" type="text" name="overApplyWords" placeholder="选择其他原因时，必须填写备注说明">
				</div>
				<p class="mar_left3 pad_top5 mar_btm5 font28 col_6">请上传说明图片</p>
				<div class="pics clearfix mar_left3 pad_btm2">
				
					<div class="pic camera" id="camera" href="javascript:void(0)">
						<img src="${ctxStatic }/mobile/modules/Manager/images/upload_pic.png" alt="调取摄像头">
						<input type="file" accept="image/*"  onchange="preview(this)">
					</div>
					<input type="text" hidden="hidden" id="num"  value="1">
				</div>
				<p class="font24 col_red tipping">请上传图片之后再进行提交</p>
				<div class="btn_wrapper">
					<a class="cancel font28 col_blue" href="javascript:;">取消</a>
					<a class="sure font28 col_blue" href="javascript:;">确定</a>
				</div>
			</div>
		</div>
	</div>
	</form>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/swiper-3.3.1.jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
		<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script>
		$(function () {
			
			$('#managerInfoAdd').val($('#managerInfo').val());
			
			$('.tipping').hide();	
			
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
	    	//默认开始时间
	    		$("#txtBeginDate").val(start);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
			
			
			
	      	// 下拉菜单
	      		$(document).on('click','.select-value',function(){
					$(this).parent().find('.options').toggle();
				});
	      	
	      	
				$(document).on('click','.options span',function(){
					$('.select-value span').html($(this).html());
					$(this).parent().css({'display':'none'});
					if($('.select-value span').text() == '其他原因'){
						$('.must').removeClass('undis');
					}else{
						$('.must').addClass('undis');
					}
				});

				
					
			
				$('.cancel').click(function(){
					$('.coat').addClass('undis');
					$('html').css('overflow','');
				});
				//点击确定 做校验
				$('.sure').click(function(){
					$('html').css('overflow','');
					if($('.select-value span').text() == '其他原因'){
						if($(".note").val().trim()==""){
							
							$("#wordsInfo").show();
							$("#wordsInfo").text("请填写备注说明")
							return;
						}else{
							
							$("#wordsInfo").hide();	
						}
						
						
					}else{
						$(".note").val("");
						
						
						
						
					}
					
					if($(".pics div").size()<2){
						
						$(".tipping").text("请上传图片之后再进行提交")
						$('.tipping').show();
						return;
					}else{
						
						$('.tipping').hide();	
					}
					
					
					
					
					$('.coat').addClass('undis');
					$("#isPunish").val(1);
					submit();
					
			
				 });
	    });
		
		
		
		
	
		
		
      		function setTotal(){
      			setTimeout(function(){		
      	      		var s=0;
      	      		var c = 0;
      	      		$(".show_sec ul li").each(function(){
      	      			
      	      			c+=parseInt($(this).find('input[class*=mui-numbox-input]').val());
      	      			
      	      			
      	      			s+=parseInt($(this).find('input[class*=mui-numbox-input]').val())*parseFloat($(this).find('span[class*=col_red]').text());
      	      		});
      	      		$("#total").html(s.toFixed(2)+"元");
      	      		$("#count").html("您一共选择了"+c+"个商品");
      	      		$("#totalMoney").val(s.toFixed(2));},"50");
      			
      	
      		}
      
		
		
      		
		

	
		
		
		//提交
		function submit(){
		
			if($("#txtBeginDate").val()==""){
				globalUtil.fn.alert("请选择期望到场日期",2);
				return;
			}
			var number = $("#totalMoney").val();
			if(number=="" || number==0){
				$("#sub").attr("disabled",true);
					globalUtil.fn.alert(("您还没有选择任何商品"),2);
				return;
			}else{
				$("#sub").attr("disabled",false);
			}
			
			//数量计算
			var contractArea ="${purchaseVo.contractAreaForApplySwitchPane}";	  
			var historyApplyCount ="${purchaseVo.totalCount}";
			var projectMode = "${purchaseVo.projectMode}";
			if(historyApplyCount==""){
				
				historyApplyCount=0;
			}
			var currentcount=0;
			$(".show_sec ul li").each(function(){
				if($(this).find('input[class*=mui-numbox-input]').next().val()==521){
					currentcount+=parseInt($(this).find('input[class*=mui-numbox-input]').val());
				}
	      			
	      		});
			
			$("#currentcount").val(currentcount);
			//如果数量大于合同面积*0.48  并且没有填写过超定额 	且是产业
			if((parseInt(historyApplyCount)+parseInt(currentcount))>(Math.ceil(contractArea))&&(Math.ceil(contractArea))>0&&($("#isPunish").val()==0)&&projectMode==1){
				
				
				
				$('.coat').removeClass('undis');
				$('html').css("overflow","hidden");
				//弹出蒙层
				
				$(".coat_reason").text("该订单您已申请"+historyApplyCount+"个开关面板，本次申请"+currentcount+"个，已超过标准的"+(Math.ceil(contractArea))+"个。请说明原因");
			}else{
				
				
				run_waitMe("win8","拼命提交中..请稍等...");
				$("#sub").removeAttr("onclick");
				
				$("#isPunish").val((parseInt(historyApplyCount)+parseInt(currentcount))-(parseInt(contractArea+1)))
				var options={
						
						url: "${ctx}/app/manager/applySwitchPanel/submit",
						type: "post",
						success :function(data){
							if(data=="NO"){
								globalUtil.fn.alert('您刚刚申请过开关面板，请您耐心等待五分钟再次操作',3.0);	
								
							}else if(data=='fail'){
								globalUtil.fn.alert('操作失败了~~',5.0);

								
							}else if(data=="noApply"){
								globalUtil.fn.alert('该订单的基础装修质检员已确认验收了，不允许再申请开关面板了。',3.0);
							}else{

                                globalUtil.fn.alert('操作成功',2.0);
                                window.location.href="${ctx}/app/manager/applySwitchPanel/details?id="+data;
                            }
							
						}
				}
				
				$("#form").ajaxSubmit(options);
				
			}
			
			
			
			
		}
		
	
		
		
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
	
		
		
		
		
		
		
		
		
		
		
		
		//图片上传显示 
		function preview(file) {  
			var num = $("#num").val();
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic);" id="'+num+'"/><a class="delBtn" href="javascript:void(0)">删除</a></div>').insertAfter('.camera');
					
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img"  style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		} 
		$(document).on('click', '.delBtn', function(){
			$(this).parent().remove();
			var num=$(this).prev().attr("id");
			$(("#num"+num)).remove();
		});
		
		function uploadpic(pic){
			
			var hiddenForm = document.getElementById("form");
			var input =document.createElement("input");
			$('.tipping').hide();	
			
			var num = $("#num").val();
			if(pic){
				input.setAttribute("id","num"+num);
				input.setAttribute('hidden', 'hidden'); 
				input.setAttribute('name', 'photo'); 
				input.setAttribute("type","text");
				input.setAttribute("value", pic);
				hiddenForm.appendChild(input);
				num++;
				$("#num").val(num);
			}
		}
		
		
		
		
		
	</script>
</body>
</html>