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
    <title>厂家复尺</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/apply.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/layout.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/mask.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>

<body>
    <div class="wrap">
        <header>
            <a class="back_btn" href="${ctx}/app/manager/checksize/checksizeList"></a>
            <h2 class="title">厂家复尺</h2>
        </header>
        <!-- /header -->
        <form id="jvForm" method="post" enctype="multipart/form-data">
        	<input type="text" hidden="hidden" name="orderId" id="orderId" value="${order.orderId }">
	        <section class="pt112 shadow bor_btm_e5 mb24">
	            <div class="sec pl30 pr30 font0 bor_top_e5 bg_f clearfix">
	                <ul class="pad_top3">
	                    <li class="mb30 rele clearfix">
	                        <span class="col_grey font30 fl pl2em">顾客信息：</span>
	                        <p class="font30 col_3 flow">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
	                    </li>
	                    <li class="mb30 clearfix">
	                        <span class="col_grey font30">期望复尺日期：</span>
	                        <input class="date date1" type="text" readonly="" value="" id="checksizeDate" name="checksizeDate" placeholder="请输入日期" data-lcalendar="">
	                    </li>
	                    <li class="mb30 rele clearfix">
	                        <p><span class="col_grey font30 pl1em">主材复尺项：</span>
	                            <select class="selectRe font24 col_0780ec" id="orderInstallItemId" name="orderInstallItemId">
	                                <option value=""></option>
	                                <c:forEach items="${list }" var="orderInstallItem">
		                                <option value="${orderInstallItem.type }">${orderInstallItem.name }</option>
	                                </c:forEach>
	                            </select>
	                        </p>
	                        <p class="tips-txt">* 如果复尺内容中没有找到需要申请的安装项，请您联系大区经理或大区助理进行添加.
	                        </p>
	                    </li>
	                    <li class="mb30 rele clearfix">
	                        <span class="col_grey font30 pl2em">备注说明：</span>
	                        <textarea class="instruc" name="remarks"></textarea>
	                    </li>
	                </ul>
	            </div>
	        </section>
	        <!-- /section -->
	        <section>
	            <p class="pb20">
	                <span class="pl30 font28 col_blue bold">上传复尺照片</span>
	            </p>
	            <div class="pics bor_top_ddd font0 shadow bg_f clearfix">
	                <%-- <div class="pic">
	                    <img src="${ctxStatic}/mobile/modules/Manager/img/common/eg.png" alt="">
	                    <a class="delBtn" href="javascript:void(0)"></a>
	                </div>
	                <div class="pic">
	                    <img src="${ctxStatic}/mobile/modules/Manager/img/common/eg.png" alt="">
	                    <a class="delBtn" href="javascript:void(0)"></a>
	                </div>
	                <div class="pic">
	                    <img src="${ctxStatic}/mobile/modules/Manager/img/common/eg.png" alt="">
	                    <a class="delBtn" href="javascript:void(0)"></a>
	                </div>
	                <div class="pic">
	                    <img src="${ctxStatic}/mobile/modules/Manager/img/common/eg.png" alt="">
	                    <a class="delBtn" href="javascript:void(0)"></a>
	                </div>
	                <div class="pic">
	                    <img src="${ctxStatic}/mobile/modules/Manager/img/common/eg.png" alt="">
	                    <a class="delBtn" href="javascript:void(0)"></a>
	                </div> --%>
	                <div class="pic camera" id="camera" href="javascript:void(0)">
	                    <img src="${ctxStatic}/mobile/modules/Manager/img/common/upPic.png" alt="">
	                    <input type="file" accept="image/*" onchange="preview(this)">
	                </div>
	                <input type="text" hidden="hidden" id="shit"  value="1">
	           		<input type="text" hidden="hidden" id="num" name="num" value="">
	            </div>
	        </section>
        </form>
        <!-- /section -->
        <div class="pt44">
            <a class="subBtn" href="javascript:;" id="subBtn">提交申请</a>
        </div>
        <div style="padding-bottom:300px;"></div>
    </div>
    <!-- /.wrap -->
    <!--公共弹层背景  class 「_in」 显示弹层背景-->
    <div class="alertMask" id="alertMask">
    </div>
    <!-- /.alertMask -->
    <!--公共弹层  class 「_in」 显示弹层内容-->
    <div class="maskWrapper" id="alertAlert">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p id="alertRemark">
            </p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="alertSure()">确 定</button>
        </div>
    </div>
   
    <!-- /.maskWrapper -->
    <div class="maskWrapper" id="alertAlertTwo">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p>该主材安装项已经申请过复尺，您可以在复尺记录中查看！
            </p>
        </div>
        <div class="maskBtns twoBtns clearfix">
            <button class="maskBtn" type="button" onclick="alertTwoA()">申请其它主材</button>
            <button class="maskBtn" type="button" onclick="alertTwoB()">查看复尺记录</button>
        </div>
    </div>
    
     <div class="maskWrapper" id="alertAlertThree">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p>该主材安装项申请复尺成功，供应商反馈安装日期后会在复尺记录中展示
            </p>
        </div>
        <div class="maskBtns twoBtns clearfix">
            <button class="maskBtn" type="button" onclick="alertThreeA()">申请其它主材</button>
            <button class="maskBtn" type="button" onclick="alertThreeB()">查看复尺记录</button>
        </div>
    </div>
    
    <div class="maskWrapper" id="alertAlertFour">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p id="alertRemarkFour">
            </p>
        </div>
        <div class="maskBtns twoBtns clearfix">
            <button class="maskBtn" type="button" onclick="alertFourA()">确 定</button>
            <button class="maskBtn" type="button" onclick="alertFourB()">提前申请</button>
        </div>
    </div>
   
    <div class="maskWrapper" id="alertAlertFive">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p id="alertRemarkFive">
            </p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="alertFive()">已提交（提前申请）</button>
        </div>
    </div>
    <!-- /.maskWrapper -->
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/mobiscroll.custom-2.16.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
    <script>
    
      	var now = new Date();
		var currYear = now.getFullYear();
		var currMonth = now.getMonth() + 1;
		var currDay = now.getDate();
		$("#checksizeDate").val(globalUtil.fn.formatDate(new Date(currYear, currMonth - 1, currDay+3),'yyyy-MM-dd'));
		//mobiScroll插件选项
		var opt = {
			theme: "ios",
			lang: 'zh',
			dateFormat: 'yyyy-mm-dd', // 面板日期格式
			dateOrder: 'yyyymmdd', //面板中日期排列格式
			showNow: false,
			nowText: "今",
			endYear: currYear + 3, //结束年份
			minDate: new Date(currYear, currMonth - 1, currDay+3),
			onSelect: function (textVale, inst) { //选中时触发事件
				console.log("我被选中了.....");
			},
			onClose: function (textVale, inst) { //插件效果退出时执行 inst:表示点击的状态反馈：set/cancel
				console.log("textVale--" + textVale);
				console.log(this.id); //this表示调用该插件的对象
			}
		};
		$(".date").mobiscroll().date(opt)
		
	    
	    function run_waitMe(effect,text){
			$('body').waitMe({
				effect: effect,
				text: text,
				bg: 'rgba(255,255,255,0.7)',
				color:'#000',
				sizeW:'',
				sizeH:'',
				source: 'img.svg'
			});
		}
	    
	    
	    
	    
	    
	    
	    var orderId = $("#orderId").val();
	    
	    
	    $(document).ready(function() {
			//绑定onclick事件
			$("#subBtn").bind('click',checkData);
		});
	    //--------------------------------弹框关闭start-------------------------------------------------
	    //1.提示弹框--确定
	    function alertSure(){
	    	$("#alertMask").removeClass('_in');
	        $("#alertAlert").removeClass('_in');
	    }
	    //2.该主材安装项已经申请过复尺，您可以在复尺记录中查看！
	   	//2.1申请其它主材   
	    function alertTwoA(){
	    	$("#alertMask").removeClass('_in');
	        $("#alertAlertTwo").removeClass('_in');
	    }
	   	//2.2查看复尺记录
	    function alertTwoB(){
	    	$("#alertMask").removeClass('_in');
	        $("#alertAlertTwo").removeClass('_in');
	        window.location.href = "${ctx }/app/manager/checksize/checksizeRecord?orderId="+ orderId;
	    }
        //3.该主材安装项申请复尺成功，供应商反馈安 装日期后会在复尺记录中展示
        //3.1申请其它主材   
	    function alertThreeA(){
	    	$("#alertMask").removeClass('_in');
	        $("#alertAlertThree").removeClass('_in');
	        window.location.href = "${ctx }/app/manager/checksize/applyChecksize?orderId="+ orderId;
	    }
	   	//3.2查看复尺记录
	    function alertThreeB(){
	    	$("#alertMask").removeClass('_in');
	        $("#alertAlertThree").removeClass('_in');
	        window.location.href = "${ctx }/app/manager/checksize/checksizeRecord?orderId="+ orderId;
	    }
	    
	  	//4.提前申请
	  	//4.1确定
	  	function alertFourA(){
	    	$("#alertMask").removeClass('_in');
	        $("#alertAlertFour").removeClass('_in');
	    }
	    //4.2提前申请
	  	function alertFourB(){
	  		$(".advanceApplyInput").remove();
			$(".advanceApplyDiv").remove();
			$("#num").val("");
			$("#shit").val("1");
	  		var orderInstallItemId = $("#orderInstallItemId").val();
	    	window.location.href = "${ctx }/app/manager/checksize/installChecksizeAdvanceApply?id="+orderInstallItemId+"&orderId="+orderId;
	    }
	    
	  	//5.已申请【提前申请】
	    function alertFive(){
	    	$("#alertMask").removeClass('_in');
	        $("#alertAlertFive").removeClass('_in');
	    }
	    
	    //--------------------------------弹框关闭end-------------------------------------------------
	    
	    //--------------------------------提交数据校验start-------------------------------------------
	    
	    function checkData(){
	    	
	    	
	    	//期望复尺日期
	    	var checksizeDate = $("#checksizeDate").val();
	    	if(checksizeDate == null || checksizeDate == ""){
				$("#alertRemark").text("期望复尺日期");
				$("#alertMask").addClass('_in');
		        $("#alertAlert").addClass('_in');
				return false;
			}
	    	
	    	//主材复尺项
	    	var orderInstallItemId = $("#orderInstallItemId").val();
			if(orderInstallItemId == null || orderInstallItemId == ""){
				$("#alertRemark").text("主材复尺项为空");
				$("#alertMask").addClass('_in');
		        $("#alertAlert").addClass('_in');
				return false;
			}
			
			var installName = $(":selected").text();
			
			$.ajax({
				type : "POST",
				url : "${ctx}/app/manager/checksize/check_checksize_data_ajax",
				dataType : "json",
				data:{
					orderId:orderId,
					orderInstallItemId:orderInstallItemId,
					type:"2"
				},
				success : function(data){
					if(null!=data && data == 0){
						checksizeSubmit();
					}else if(data == 1){
						$("#alertRemark").text("订单id为空");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}else if(data == 2){
						$("#alertRemark").text("该订单没有可复尺的安装项，如有疑问请咨询大区经理或大区助理。");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}else if(data == 3){
						$("#alertRemark").text("同一个订单两次厂家复尺申请操作时间必须间隔5分钟，请过5分钟后再申请");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}else if(data == 4){
						$("#alertRemark").text("主材复尺项为空");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}else if(data == 5){
				        $("#alertMask").addClass('_in');
						$("#alertAlertTwo").addClass('_in');
					}else if(data.code == 7){
						$("#alertRemarkFour").text("该工地"+ data.orderActualStartDateString +"开工，按照工程部规定主材（"+ installName +"）开工"+ data.daysPlanChecksizeString +"天后（"+ data.canApplyChecksizeDateString +"）才可以申请"+ installName +"复尺，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。");
						$("#alertMask").addClass('_in');
				        $("#alertAlertFour").addClass('_in');
					}else if(data.code == 8){
						$("#alertRemarkFive").text("该工地"+ data.orderActualStartDateString +"开工，按照工程部规定主材（"+ installName +"）开工"+ data.daysPlanChecksizeString +"天后（"+ data.canApplyChecksizeDateString +"）才可以申请"+ installName +"复尺，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。");
						$("#alertMask").addClass('_in');
				        $("#alertAlertFive").addClass('_in');
					}else{
						$("#alertRemark").text("主材复尺项不可申请");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}
				}
			});
	    	
	    }
	    
	    
	    //--------------------------------提交数据校验end-------------------------------------------
	    
	    //--------------------------------提交申请start---------------------------------------------
	   	
	    function checksizeSubmit(){
	    	
	    	$("#subBtn").css("color","#CCC");
			$("#subBtn").unbind("click");
			run_waitMe("win8",'请稍等 ,拼命提交中....');
			
			var options={
					url: "${ctx}/app/manager/checksize/apply_checksize_submit_ajax",
					type : "post",
					success : function(data){
						$('body').waitMe('hide');	
						if(null != data && data == 0){
					        $("#alertMask").addClass('_in');
							$("#alertAlertThree").addClass('_in');
						}else if(data == 1){
							$("#alertRemark").text("订单id为空");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data == 2){
							$("#alertRemark").text("期望复尺日期为空");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data == 3){
							$("#alertRemark").text("主材复尺项为空");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data == 4){
							$("#alertRemark").text("项目经理未登录");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else{
							$("#alertRemark").text("上报厂家复尺信息保存失败");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}
					}
			}
			
			$("#jvForm").ajaxSubmit(options);
			
			
	    }
	    
	    
	    //--------------------------------提交申请end---------------------------------------------
	  
	    
	    
	    //--------------------------------图片上传显示 start--------------------------------------
		function preview(file) { 
			var num = $("#shit").val();
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic advanceApplyDiv"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic);" id= "'+num+'"/><a class="delBtn" href="javascript:void(0)">删除</a></div>').insertBefore('.camera');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		} 
		$(document).on('click', '.delBtn', function(){
			var numReal=$(this).prev().attr("id");
			$(("#num"+numReal)).remove();
			var num = $("#num").val();
			num--;
			$("#num").val(num);
			$(this).parent().remove();
		});
		
		function uploadpic(pic){
			
			var hiddenForm = document.getElementById("jvForm");
			var input =document.createElement("input");
			
			
			var num = $("#num").val();
			var shit =	$("#shit").val();
			if(pic){
				num++;
				input.setAttribute("id","num"+shit);
				input.setAttribute('hidden', 'hidden'); 
				input.setAttribute('name', 'photo'); 
				input.setAttribute("type","text");
				input.setAttribute("value", pic);
				input.setAttribute("class", 'advanceApplyInput');
				hiddenForm.appendChild(input);
				shit++;
				$("#num").val(num);
				$("#shit").val(shit);
			}
		}
	    
	  	//--------------------------------图片上传显示 end--------------------------------------
	    
	    
	    
	    
	    
    </script>
</body>

</html>