<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<title>确认验收</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/lib/lCalendar.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/date_check.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/Photocaption.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/lib/swiper-3.3.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/photo.css"/>
</head>

<body>
	<div class="g-task_list">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/checkList/list"></a>
			<h2 class="title">确认验收</h2>
		</header>
		<section class="task_list confirm">
			<div class="shadow">
				<ul class="pad_top2 pad_btm2">
					<li class="clearfix"><span>顾客：</span>
						<p>${checkConfirm.communityName }-${checkConfirm.buildNumber }-${checkConfirm.buildUnit }-${checkConfirm.buildRoom }-${checkConfirm.customerName }</p>
					</li>
					<li class="clearfix"><span>客户电话：</span>
						<p>${checkConfirm.customerPhone }</p></li>
					<li class="clearfix"><span>项目经理：</span>
						<p>${checkConfirm.managerRealName }</p></li>
					<li class="clearfix"><span>约检节点名称：</span>
						<p>${checkConfirm.qcCheckNodeName }</p></li>


					<li class="clearfix"><span>期望验收日期：</span>
						<p class="">
							<fmt:formatDate value="${checkConfirm.expectCheckDatetime }"
								pattern="yyyy-MM-dd" />
						</p></li>
					<li class="clearfix"><span>项目经理申请延期原因：</span>
						<p class="">${checkConfirm.delayReasonPm }</p>
					</li>
					<li class="clearfix borderBtm">
						<span>项目经理上传延期单：</span>
						<c:if test="${countPic eq 0 }">
							<a class="font28 col_378afa flo_rgt seeBtn" style="color: #red;" >查看照片</a>
						</c:if>
						<c:if test="${countPic ne 0 }">
							<a class="font28 col_378afa flo_rgt col_0780ec" href="${ctx}/app/pqc/confirm/viewPic?qcBillId=${checkConfirm.id}">查看照片</a>
						</c:if>
						<!-- <p class="picShowBtn col_378afa">查看照片&gt;&gt;</p> -->
					</li>
					<form id="jvForm" action="${ctx}/app/pqc/confirm/checkConfirm"
						class="jvForm" method="post" enctype="multipart/form-data">
						<input type="text" hidden="hidden" id="id" name="id"
							value="${checkConfirm.id }"> <input type="text"
							hidden="hidden" id="num" value="">
						<li class="clearfix mb40 mt24"><span>计划验收日期：</span>
							<p class="" id="planCheckDate"><fmt:formatDate value="${checkConfirm.planCheckDate}" pattern="yyyy-MM-dd"/></p></li>

						<li class="clearfix mb40"><span>验收日期：</span>
							<p>
								<input id="txtDate" class="date" type="text" readonly=""
									value="" name="input_date" placeholder="请输入日期"/>
							</p>
						</li>
						<li class="clearfix mb40" id="delayId"><span>延期原因：</span>
							<p>
								<textarea class="reasonArea" name="delayReasonQc" id="delayReasonPmId"></textarea>
							</p></li>
						<c:if test="${checkConfirm.status=='20' }">
							<li class="clearfix"><span>驳回原因：</span>
								<p>${checkConfirm.checkWords }</p></li>
						</c:if>
				</ul>
			</div>

			<c:choose>
				<c:when test="${count > 0}">
					<c:if test="${fn:length(evalStoreList) > 0}">
						<h3 class="font30 col_6 pad_top24 pad_btm24 pad_left3 pad_rgt3 bg_e">评价项目经理</h3>
						<div class="box-bottom">
							<ul class="lists shadow font0">
								<c:forEach items="${evalStoreList}" var="evalStore"
									varStatus="status">
									<li class="mar_btm24 clearfix a"><input type="hidden"
										id="selectCount${status.index}"
										value="${evalStore.selectCount}" /> <span
										class="col_3 font28 mar_ght32 left-small">${evalStore.indexName}</span>
										<p class="flow star-p" id="starP${status.index}">
											<span class="star1"></span> <span class="star1"></span> <span
												class="star1"></span> <span class="star1"></span> <span
												class="star1"></span>
										</p></li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
				</c:when>
				<c:otherwise>
					<%--<c:if test="${fn:length(bizEvalIndexList) > 0}">--%>
						<%--<div class="shadow">--%>
							<%--<div class="work-sec font0 pad_btm24 mar_btm24">--%>
								<%--<div--%>
									<%--class="font30 col_6 pad_top24 pad_btm24 pad_left3 pad_rgt3 bg_e">评价项目经理</div>--%>
								<%--<div class="pt46 pad_left3 bg_f">--%>
									<%--<input type="hidden" name="bizEvalActivityId"--%>
										<%--value="${bizEvalIndexList.get(0).evalActivityId}" />--%>
									<%--<c:forEach items="${bizEvalIndexList}" var="bizEvalIndex"--%>
										<%--varStatus="status">--%>
										<%--<li class="mar_btm46 clearfix a"><input type="hidden"--%>
											<%--name="bizEvalActivityIndexId" value="${bizEvalIndex.id}" /> <input--%>
											<%--type="hidden" name="bizEvalActivityIndexEvalTotalScore"--%>
											<%--value="${bizEvalIndex.evalTotalScore}" /> <input--%>
											<%--type="hidden" id="selectCount${status.index}"--%>
											<%--name="bizEvalActivityIndexSelectCount" /> <span--%>
											<%--class="col_3 font28 flo_left top-small"--%>
											<%--id="indexName${status.index}">${bizEvalIndex.indexName}：</span>--%>
											<%--<p class="flow star-p" id="starP${status.index}">--%>
												<%--<span class="star1"></span> <span class="star1"></span> <span--%>
													<%--class="star1"></span> <span class="star1"></span> <span--%>
													<%--class="star1"></span>--%>
											<%--</p> <em class="must">*</em></li>--%>
									<%--</c:forEach>--%>
								<%--</div>--%>
							<%--</div>--%>
						<%--</div>--%>
					<%--</c:if>--%>
				</c:otherwise>
			</c:choose>



			<div class="shadow">
				<div class="hgt">
					<span class="pic_des">上传验收照片</span>
					<div class="up camera" id="camera" href="javascript:void(0)">
						<%-- <img src="${ctxStatic}/mobile/modules/pqc/images/pic_load.png" alt="上传照片">
						<input type="file" accept="image/*"  onchange="preview(this)"> --%>
						<img src="${ctxStatic}/mobile/modules/pqc/images/pic_load.png"
							alt="调取摄像头"> <input type="file" accept="image/*"
							onchange="preview(this)">
					</div>
					<a class="font28 col_0780ec seePicBtn">验收照片示例说明&gt;&gt;</a>
					<input type="text" hidden="hidden" id="shit" value="1">
				</div>
				<div class="pics">
					<div class="pic_container clearfix">
						<%-- <div class="pic_wraper clearfix">
							<img src="${ctxStatic}/mobile/modules/Inspector/images/photo.png" alt="质检照片">
							<a class="del_btn" href="javascript:void(0)">删除</a>
						</div> --%>
						<c:forEach items="${picList }" var="p">
							<div class="pic_wraper clearfix">
								<img src="${baseUrl}${p.picUrl}" alt="质检照片"> <a
									class="del_btn" href="#" onclick="deletePic(${p.picId})">删除</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			</form>
		</section>
		<a class="sure_btn" href="javascript:void(0)" id="submit">确定</a>
	</div>
	<div class="g-photo undis">
		<header>
			<a class="picCloseBtn" href="javascript:void(0)"></a>
			<h2 class="swiper-pagination"></h2>
		</header><!-- /header -->
		<section class="swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<img src="${ctxStatic}/mobile/modules/pqc/images/Photocaption/01.jpg" alt="">
				</div>
			</div>
			<div class="swiper-button-prev" id="prev_btn"></div>
    		<div class="swiper-button-next" id="next_btn"></div>
    		<div class="swiper-pagination" syle="visibility:none;"></div>
		</section>
	</div>
	<div class="wrap undis">
        <div class="bigPic"></div>
        <div class="caption">
            <h3><a class="closeBtn" href="javascript:;">关闭</a>验收照片示例说明</h3>
            <div class="captionList">
                <h4>1. 第一张照片</h4>
                <p>（五大节点确认验收单拍照上传），标准：图片必须清晰、能看清图片上所有信息（方便结算员审核，如不合格、看不清的图片一律驳回，从新拍照上传）。</p>
                <div class="imgs">
                     <img src="${ctxStatic}/mobile/modules/pqc/images/Photocaption/01.jpg" alt="" style="margin-left: 2.38rem">
                </div>
            </div>
            <div class="captionList">
                <h4>2. 第二张照片</h4>
                <p>(客户、质检、项目经理三方现场拍照合影照片)</p>
                <p>标准：图片清晰</p>
                <div class="imgs">
                     <img src="${ctxStatic}/mobile/modules/pqc/images/Photocaption/02.jpg" alt="" style="margin-left: 2.38rem">
                </div>
            </div>

            <div class="captionList">
                <h4>3. 第三张至第五张图片</h4>
                <p>第三张图片：现场验收节点施工工艺一张图片</p>
                <p>第四张图片：使用工具检测时一张图片</p>
                <p>第五张图片：工地现场文明施工一张图片</p>
                <div class="imgs">
                     <img src="${ctxStatic}/mobile/modules/pqc/images/Photocaption/03.png" alt="">
                     <img src="${ctxStatic}/mobile/modules/pqc/images/Photocaption/04.jpg" alt="">
                     <img src="${ctxStatic}/mobile/modules/pqc/images/Photocaption/05.jpg" alt="">
                </div>
            </div>
            <div style="padding-bottom:30px;"></div>
        </div>
    </div>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/swiper-3.3.1.jquery.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/lib/lCalendarForPqcCheckDate.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script>
		//轮播弹层
		;(function(){
			$(document).ready(function(){
				var photoAll = $('.swiper-slide').length;
				/* console.log(photoAll) */
			   	$('.g-photo h2').text(1 + '/' + photoAll);
				var mySwiper = new Swiper ('.swiper-container',{
			    	loop: false,
			    	nextButton: '.swiper-button-next',
		    		prevButton: '.swiper-button-prev',
		    		pagination : '.swiper-pagination',
					paginationType : 'fraction',
		   //  		onSlideChangeEnd: function(swiper){
		   //  			console.log(swiper.activeIndex);
			  //  			$('.g-photo h2.title').text(swiper.activeIndex+1 + '/' + photoAll);
					// }
			    })
			})
		}());
		//点击看大图；
	    $(document).on("click","img",function(){
	        var NewImg=$(this).clone();
	        $(".bigPic").append(NewImg);
	        $(".bigPic").show();
	    });
		//点击消失；
	    $(document).on("click",".bigPic",function(){
	        $(".bigPic").hide();
	        $(".bigPic").html("");
	    });
	    $(document).on('click',".seePicBtn",function(){
			$('.wrap').show();
		})
		$(document).on('click',".closeBtn",function(){
			$('.wrap').hide();
		})
		$(document).on('click',".picCloseBtn",function(){
			$('.g-photo').hide();
		})
		$(document).on('click',".picShowBtn",function(){
			$('.g-photo').show();
		})
	    function isDelay(actualCheckDate){
            var planCheckDate =$("#planCheckDate").text();
            if(actualCheckDate>planCheckDate){
                $("#delayId").show();

			}else{
                $("#delayId").hide();

			}

		}
		
		$(function(){
//			// 获取当前日期，结束日期
	    	var now = new Date(),
	    		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd');
//	    		end = new Date(now.setFullYear(now.getFullYear()+1)),
//	    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');
//
//	    	var lcalendar = start+','+end;
//	    	// 将时间限制加到input标签上。
//	    	$('#txtDate').attr('data-lcalendar', lcalendar);
	    	$("#txtDate").val(start);
//			var calendar = new lCalendar();
//			calendar.init({
//			    'trigger': '#txtDate',//标签id
//			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
//			});
			
			var planCheckDate =$("#planCheckDate").text();
		   var actualCheckDate =$("#txtDate").val();


		if(actualCheckDate>planCheckDate){

            $("#delayId").show();
		}else{

            $("#delayId").hide();
		}
		}());
		
		
		
		//图片上传显示 
		function preview(file) {  
			var shit = $("#shit").val();
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic_wraper"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><a class="del_btn" href="javascript:void(0)">删除</a></div>').appendTo('.pic_container');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		} 
		$(document).on('click', '.g-task_list .pic_wraper > a', function(){
			
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
			
			
			var num = $("#num").val();
			var shit = $("#shit").val();
			
			if(pic){
				num++;
				input.setAttribute("id","num"+shit);
				input.setAttribute('hidden', 'hidden'); 
				input.setAttribute('name', 'photo'); 
				input.setAttribute("type","text");
				input.setAttribute("value", pic);
				hiddenForm.appendChild(input);
				$("#num").val(num);
				shit++;
				$("#shit").val(shit);
			}
		}
		
		function deletePic(picId){
			$.ajax({
				url : "${ctx}/app/pqc/checkItem/deletePic",
				type : "POST",
			    async : false,
			    data : {
			    	picId : picId
				  },
				  success : function(data){
					  if(data == 0){
						  globalUtil.fn.alert('删除图片操作成功',2.0);
					  }
					  
				  }
			});
		}
		
		$(document).ready(function() {
			//绑定onclick事件
			$("#submit").bind('click',submitData);
		});
		function submitData(){
			var date = $(".date").val();
			var count = $(".pic_container > div").size();
			var qcBillId = $("#id").val();
			 var planCheckDate =  $("#planCheckDate").text();
            var hopeCheckDate =$("#txtDate").val();


            if(hopeCheckDate>planCheckDate&&(""==$("#delayReasonPmId").val()||$("#delayReasonPmId").val().length>50)) {

                globalUtil.fn.alert('延期原因为空或输入过多', 2.0);
                return;
            }
			// 判断评价工人
			var starLength = '${fn:length(bizEvalIndexList)}';
			var starBool = false;
			for(var i=0;i<starLength;i++){
				var indexName = $("#indexName"+i).html();
				var selectLength = $("#starP"+i).find(".star2").length;
				if(selectLength > 0){
					$("#selectCount"+i).val(selectLength);
				}else{
					globalUtil.fn.alert('评价项目经理【'+indexName+'】必选!',2.0);
					starBool = true;
					break;
				}
			}

			if(starBool){
				return;
			}
		
			if(date != null && date !=""){
				if(count!=null && count>=1){
					
					$("#submit").css("color","#CCC");
					$("#submit").unbind("click");
					
					
					var options ={
							url: "${ctx}/app/pqc/confirm/checkConfirm",
							type: "post",
							success:function(data){

							    if(data==101){
                                    globalUtil.fn.alert('保存相关材料信息失败了',2.0);

                                }else if(data==102){
                                    globalUtil.fn.alert('保存相关评价信息失败了',2.0);

                                }else if(data==103){
                                    globalUtil.fn.alert('保存相关约检待办信息失败了',2.0);

                                }else if(data==0){

                                    globalUtil.fn.alert('确认验收操作成功',2.0);
                                    window.location.href="${ctx}/app/pqc/checkList/list";

                                }


								
							}
					}
					
					$("#jvForm").ajaxSubmit(options);
					
					
					
				}else{
					globalUtil.fn.alert('请至少上传一张图片！',2.0);
				}
				
			}else{
				globalUtil.fn.alert('请添加验收日期！',2.0);
			}
			
		}
		$(function(){
			var rewardCount = '${count}';
			if(rewardCount > 0){
				showStore();
			}else{
				$(document).on('click','.star-p span',function(){
					$(this).prevAll().addClass('star2');
					if($(this).hasClass('star2')){
						$(this).nextAll().removeClass('star2').addClass('star1');
					}else{
						$(this).addClass('star2');
					}
				});
			}
			
		}());

		function showStore(){
			var length = '${fn:length(evalStoreList)}';
			for(var i=0;i<length;i++){
				var selectCount = $("#selectCount"+i).val();
				$("#starP"+i).find(".star1:lt("+selectCount+")").addClass("star2");
			}
		}
	</script>
</body>
</html>


