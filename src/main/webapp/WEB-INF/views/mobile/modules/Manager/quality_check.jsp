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
	<title>申请约检</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/start.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/date_check.css" />
</head>
<style>
	.spanRgt33{text-align: right;width: 33%;}
	.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);z-index:99;}
	.maskWrapper{width: 84%;margin: 30% auto 0;border-radius: .1rem;background: #fff;font-size: .32rem;}
	.col_3{color: #333}
	.col_6{color: #666;}
	.col_f{color: #fff;}
	.col_fdfcfa{color: #fdfcfa;}
	.col_0780ec{color: #0780ec;}
	.font28{font-size: .28rem;}
	.font33{font-size: .33rem;}
	.maskTit{height: 1rem;line-height: 1rem;text-align: center;}
	.maskContent{padding: .5rem;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
	.maskBtns{width: 83%;margin: 0 auto;padding-bottom: .2rem;padding-top: .2rem;}
	.maskBtn{display: block;width: 60%;text-align: center;line-height: .76rem;}
	.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
	
</style>
<body id="aboveId">
	<div class="swiper-wrapper" id="showPic" style="display:none;">
			<div class="swiper-slide">
				<img onclick="hidePic(this)"  style="width:100%" alt="">
			</div>
	   </div>
	<div class="g-start g-qulity_check" >
		<header>
			<a class="back_btn" href="${ctx}/app/manager/qualityApply"></a>
			<h2 class="title">申请约检</h2>
		</header><!-- /header -->
		<section class="start_section">
			<h3>${info }</h3>
			<ul class="shadow">
				<form id="jvForm" class="jvForm"  method="post">
					<input type="text" hidden="hidden" name="orderId" value="${orderId }">
					<input type="text" hidden="hidden" name="checkId" value="${checkId }">
					<input type="text" hidden="hidden" name="planCheckDate" value="" id="hiddenPlanCheckDateId">
					<input type="text" hidden="hidden" name="projectMode" value="${order.projectMode }" id="projectMode">
					<input type="text" hidden="hidden" id="qcBillStatus" name="qcBillStatus" value="${qcBillStatus }">
					<div hidden="hidden">
						<c:forEach items="${traditionalNodeList }" var="item">
						<input id="${item.id}" value="${item.planCheckDate}">
						</c:forEach>

					</div>

					<li class="clearfix">
						<span class="spanRgt33">约检节点名称：</span>
						<c:if test="${order.projectMode=='1' }">
							<p><input class="enable" type="text" readonly="readonly" name="check" value="${check }"></p>
						</c:if>
						<c:if test="${order.projectMode=='2' }">
							<select id="selectCheck" name="selectCheck" onchange="setPlanCheckDate()">
								<c:forEach items="${traditionalNodeList }" var="item">
									<option value="${item.id }">${item.qcCheckNodeName }</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${order.projectMode=='4' }">
							<p><input class="enable" type="text" readonly="readonly" name="check" value="${check }"></p>
						</c:if>
						<!-- <select id="">
	                        <option value="1" selected="selected">水电路改造工程</option>
	                        <option value="2">泥瓦工程</option>
	                        <option value="3">涂饰工程</option>
	                        <option value="4">吊顶及隔墙工程</option>
	                    </select> -->
					</li>
					 <li class="clearfix" style="margin-top:.2rem;">
						<span class="spanRgt33" style="margin-top: .12rem;">计划验收日期：</span>
						<p class="" id="planCheckDateId"><fmt:formatDate value="${planCheckDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </p>
					</li>
					<li class="clearfix" style="margin-top:.2rem;">

						<span class="spanRgt33">期望验收日期：</span>
						<p>
							<input id="txtBeginDate" class="date" type="text" readonly="" value="" name="input_date" placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
						</p>
					</li>
					<li class="clearfix" style="margin-top:.2rem;">
						<span class="spanRgt33">备　　　　注：</span>
						<p><textarea name="remarks">${managerInfo }</textarea></p>
					</li>
					<li class="clearfix" style="margin-top:.2rem;" id="delayId">
						<span class="spanRgt33">延期原因：</span>
						<p><textarea name="delayReasonPm" id="delayReasonPmId"></textarea></p>
					</li>
					<div class="delayUpPic undis">
						<div class="intro font0 pad_left3 pb68">
							<span class="font28 col_3">上传延期照片：</span>
							<a class="col_0780ec font28 seeDelayBtn" href="javascript:;">延期确认单示例说明&gt;&gt;</a>
							<div class="camera pic" style="height:2.6rem;" id="camera" href="javascript:void(0)">
								<img src="${ctxStatic}/mobile/modules/Manager/images/upPicN.png" alt="">
								<input type="file" accept="image/*" onchange="preview(this)">
							</div>
							<div  style="height:2.6rem;" id="uploaddone" href="javascript:void(0)">				
							</div>
						</div>
						<!-- <div class="pics font0 clearfix">
							
						</div> -->
							
					</div>
				</form>
				<c:if test="${order.projectMode=='1' }">
					<c:if test="${check !=''}">
						<a id="start" href="javascript:void(0)">确认申请</a>
					</c:if>
				</c:if>
				<c:if test="${order.projectMode=='2' }">
					<a id="start" href="javascript:void(0)">确认申请</a>
				</c:if>
				<c:if test="${order.projectMode=='4' }">
					<c:if test="${check !=''}">
						<a id="start" href="javascript:void(0)">确认申请</a>
					</c:if>
				</c:if>
			</ul>
		</section>
	</div>
		<div class="alertMask undis" id ="alert">
			<div class="maskWrapper">
				<p class="col_3 maskTit">项目经理您好</p>
				<div class="font28 col_6 maskContent">上一个约检节点还未验收，请联系质检员</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="queren()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>



		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
			<div id="g-done_ask">
				<p class="first">确认要申请约检吗？</p>
				<p class="second">
					<a href="#" onclick="hideSubmitAlert()">取消</a>
					<a href="#" onclick="submitData()">确认</a>
				</p>
			</div>
		</div>

		<div class="alertMask undis" id ="timeForBidden">
			<div class="maskWrapper">
				<p class="col_3 maskTit">项目经理您好</p>
				<div class="font28 col_6 maskContent" id="forBiddenWordId">上一个约检节点还未验收，请联系质检员</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="querenTime()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
		
		<div class="alertMask undis" id="delayPic">
			<div class="MaskImgWrapper">
				<img src="${ctxStatic}/mobile/modules/Manager/images/delay.jpg" />
			</div>
		</div>
		
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendarForManagerQcApply.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/modules/settleJs/layer/layer.js"></script>
	<script src="${ctxStatic}/modules/settleJs/layer/extend/layer.ext.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script>
		$(document).on('click','.del_pic',function(){
			$(this).parent().remove();
		});
		$(document).on('click','.seeDelayBtn',function(){
			$('#delayPic img').attr('src','${ctxStatic}/mobile/modules/Manager/images/delay.jpg');
			$('#delayPic').removeClass('undis');
		});
		$(document).on('click','#delayPic',function(){
			$(this).addClass('undis');
		});
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


        function alertReport(){

            $("#subReport").show();
        }
        function querenTime(){
            $("#timeForBidden").hide();

		}

        function hideSubmitAlert(){

            $("#subReport").hide();
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
	
		$(document).ready(function() {
			//绑定onclick事件
			$("#start").bind('click',alertReport);
			$("#delayId").hide()
            var nodeId=  $("#selectCheck").val();
			if(undefined!=nodeId &&""!=nodeId){
                var planCheckDate= $("#"+nodeId).val();
                $("#planCheckDateId").text(format(planCheckDate,"yyyy-MM-dd"));
               
			}

		});

        function  setPlanCheckDate(){

          var nodeId=  $("#selectCheck").val();

          var planCheckDate= $("#"+nodeId).val();
          var planCheckDateVal = format(planCheckDate,"yyyy-MM-dd");
          $("#planCheckDateId").text(planCheckDateVal);
          var hopeCheckDate =$("#txtBeginDate").val();
          if(hopeCheckDate>planCheckDateVal&&planCheckDateVal!=null&&planCheckDateVal!=' '&&planCheckDateVal!=''){
              $("#delayId").show();
              $('.delayUpPic').show();
 		  }else{

               $("#delayId").hide();
               $('.delayUpPic').hide();
 		  }

		}

		function isDelay(hopeCheckDate){

          var planCheckDate =  $("#planCheckDateId").text();
          if(hopeCheckDate>planCheckDate&&planCheckDate!=null&&planCheckDate!=' '&&planCheckDate!=''){

              $("#delayId").show();
              $('.delayUpPic').show();
		  }else{

              $("#delayId").hide();
              $('.delayUpPic').hide();
		  }

		}






		function queren(){
			 $('#alert').hide();
			 $("#start").bind('click',alertReport);
			 $("#start").css("color","#fff");
		}
		function submitData(){
            $("#subReport").hide();
            var planCheckDate =  $("#planCheckDateId").text();
            var hopeCheckDate =$("#txtBeginDate").val();

            if(hopeCheckDate>planCheckDate&&(""==$("#delayReasonPmId").val()||$("#delayReasonPmId").val().length>50)){

                globalUtil.fn.alert('延期原因为空或输入过多',2.0);
                return;
            }
            if(hopeCheckDate>planCheckDate&&($("div[name='photo']").length<1)){
            	
        	   globalUtil.fn.alert('至少上传一张照片',2.0);
        	   return ;
            }
			var qcBillStatus = $("#qcBillStatus").val();
			var selectCheck = $("#selectCheck").val();
			var projectMode = $("#projectMode").val();
			if(null!=projectMode && (projectMode=='1'||projectMode=='4')){
			}else{
				if(null!=selectCheck && selectCheck>0){
					
				}else{
					globalUtil.fn.alert('请选择约检节点',2.0);
					return;
				}
			}
			if(qcBillStatus>=10){

			    //该天是否有时间
                var pass=0;
                $.ajax({

                    url : "${ctx}/app/manager/comparePqcDateIsAllowed",
                    type: "post",
                    data: {
                        date:$("#txtBeginDate").val() , //时间
                        orderId:${orderId}				//订单id关联 门店

                    },
                    async:false,
                    success : function(data) {

                        if('numberLimited'==data){
                            pass=1;


							$("#forBiddenWordId").text($("#txtBeginDate").val()+"的 预约检已满了，请选择"+$("#txtBeginDate").val()+" 之后的期望约检日期。");
                            $("#timeForBidden").show();

                            return;
                        }

                    }

                });

				$("#hiddenPlanCheckDateId").val($("#planCheckDateId").text());
                if(pass==0){

				$("#start").css("color","#CCC");
				$("#start").unbind("click");
				run_waitMe("win8",'请稍等 ,拼命提交中....');

				var options={
							url: "${ctx}/app/manager/qualityCheckSubmit",
							type : "post",
							dataType : "json" ,
							success : function(data){
								if(data == 0){
									globalUtil.fn.alert('申请约检成功',2.0);
									window.location.href = "${ctx}/app/manager/qualityApply";//成功后跳转URL
								}
								/* if(data == 1){
									globalUtil.fn.alert('您刚刚提交过约检单，请耐心等待五分钟后再次操作',2.0);
									$('#aboveId').waitMe('hide');
									$("#start").bind('click',submitData);
									$("#start").css("color","#fff");
								} */
								if(data == 2){
									globalUtil.fn.alert('该约检节点已经申请',2.0);
									$('#aboveId').waitMe('hide');
									$("#start").bind('click',submitData);
									$("#start").css("color","#fff");
								}
								if(data == 3){
									globalUtil.fn.alert('该订单有未验收的约检节点',2.0);
									$('#aboveId').waitMe('hide');
									$("#start").bind('click',submitData);
									$("#start").css("color","#fff");
								}
								if(data == 4){
									globalUtil.fn.alert('申请约检失败',2.0);
									$('#aboveId').waitMe('hide');
									$("#start").bind('click',submitData);
									$("#start").css("color","#fff");
								}
							}
					}
					
					$("#jvForm").ajaxSubmit(options);	
				}
			}else{

				 $('#alert').show();
			}
			
		}
		
	    $(function () {
	       		// 获取当前日期，结束日期
	    	var now = new Date(),
                nowTradition = new Date(now),
                nowTraditionStart = new Date(nowTradition.setFullYear(nowTradition.getFullYear()-2)),
                nowTraditionStart = globalUtil.fn.formatDate(nowTraditionStart, 'yyyy-MM-dd'),
                nowTraditionEnd = new Date(nowTradition.setFullYear(nowTradition.getFullYear()+5)),
                nowTraditionEnd = globalUtil.fn.formatDate(nowTraditionEnd, 'yyyy-MM-dd'),
	    		start = new Date(now.setDate(now.getDate()+2)),
	    		start = globalUtil.fn.formatDate(start, 'yyyy-MM-dd'),
	    		end = new Date(now.setFullYear(now.getFullYear()+1)),
	    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');

            var projectMode = $("#projectMode").val();
            var lcalendar;
            if(null!=projectMode && projectMode=='2'){
                lcalendar = nowTraditionStart+','+nowTraditionEnd;
            }else{
                lcalendar = start+','+end;
            }
            // 将时间限制加到input标签上。
            $('#txtBeginDate').attr('data-lcalendar', lcalendar);
            $("#txtBeginDate").val(start);
            var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
            var planCheckDate= $("#planCheckDateId").text();
            var hopeCheckDate =$("#txtBeginDate").val();
            if(hopeCheckDate>planCheckDate&&planCheckDate!=null&&planCheckDate!=' '&&planCheckDate!=''){
                $("#delayId").show();
                $('.delayUpPic').show();
	   		 }else{
	
	                $("#delayId").hide();
	                $('.delayUpPic').hide();
	   		 }

	    });
	  //图片上传显示 
		function preview(file) {  
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					var shit = $("#shit").val();
					$('<div class="camera " name="photo" style="height:3.7rem;width:30%;" ><img onclick="viewPic(this)" style="height: 2.6rem;width:100%;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><a class="del_btn" href="javascript:void(0)">删除</a></div>').appendTo('#uploaddone');
					
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
			}
		} 
	  	function viewPic(obj){
	  		var purl = $(obj).attr("src");
	  		
	  		/* $("#showPic img").attr('src',purl); 
	  		$("#showPic").show();
	  		$(".g-start").hide(); */
	  		
	  		$('#delayPic img').attr('src',purl); 
	  		$('#delayPic').removeClass('undis');
	  		/* $("#showPic").show();
	  		$(".g-start").hide(); */
	  		//console.log(purl);
	  	}
	  	function hidePic(obj){
	  		$('#delayPic').addClass('undis');
	  		/* $("#showPic img").attr('src',''); 
	  		$("#showPic").hide();
	  		$(".g-start").show(); */
	  	}
		
		$(document).on('click', '.g-start .camera > a', function(){
			
			var numReal = $(this).prev().attr("id");
			$(("#num"+numReal)).remove();
			
			var num = $("#num").val();
			num--;
			$("#num").val(num);
			$(this).parent().remove();
			globalUtil.fn.alert('删除图片操作成功',2.0);
		});
		
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

	</script>
</body>
</html>