<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>工程安装</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/search.css" /> 
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/layout.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/mask.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="wrap">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/projectInstallMenu"></a>
			<h2 class="title">工程安装</h2>
		</header><!-- /header -->
		<section class="pt112">
			<!-- <div class="font0 search-area">
				<input class="search-box" type="text" placeholder=" 小区名称、客户姓名">
				<a class="search-btn" href="javascript:;"></a>
			</div>
			<div class="sec font0 border_top border_btm mb30 bg_f clearfix">
                <ul class="pad_top3 pl30 pr30 bor_dashed">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">顾客信息：</span>
                        <p class="font30 col_3 pad_rgt3 flow">海淀区金沟河路12号院7号楼21号号院7号</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">合同开工日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2017-06-29</p>
                    </li>
                    <li class="mb30 rele clearfix">
                        <span class="col_grey font30 flo_left">合同竣工日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2017-07-10</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">合同工期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">60天</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">订单状态：</span>
                        <p class="font30 col_blue pad_rgt3 flow">已分配项目经理</p>
                    </li>
                </ul>
                <div class="btn_wrapper clearfix">
                    <a class="btnBlueBg" href="InstallApplyList.html">安装申请</a>
                </div>
            </div> -->
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	
	
	<!-- /.wrap -->
    <!--公共弹层背景  class 「_in」 显示弹层背景-->
    <div class="alertMask" id="alertMask">
    </div>
    <!-- /.alertMask -->
    <!--公共弹层  class 「_in」 显示弹层内容-->
    <div class="maskWrapper" id="orderInstallCount">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p id="orderInstallCountRemarks">该订单没有可申请安装的安装项，如有疑问请咨询大区经理或大区助理。
            </p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="orderInstallCountSure()">确 定</button>
        </div>
    </div>
    <!-- /.maskWrapper -->
    <div class="maskWrapper" id="subReport">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p>该客户二期款款项在财务系统未缴齐，请通知设计师联系客户到财务缴款。
            </p>
        </div>
        <div class="maskBtns twoBtns clearfix">
            <button class="maskBtn" type="button" onclick="subReportClose()">关闭</button>
            <button class="maskBtn" type="button" onclick="sendMessage()">短信通知设计师</button>
        </div>
    </div>
    <!-- /.maskWrapper -->
    
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	
</body>
<script type="text/javascript">

		function run_waitMe(text){
		    $('body').waitMe({
		        effect: 'win8',
		        text: text,
		        bg: 'rgba(255,255,255,0.7)',
		        color:'#000',
		        sizeW:'',
		        sizeH:'',
		        source: 'img.svg'
		    });
		}
		
		run_waitMe('正在加载数据,请稍等');
	    var managerId = "${managerId}";
	    var apply = "${apply}";
	    var param ={}
	    
	    searchOrderList();
	    
	    function searchOrderList(){
	    	var sectionObj=$(".pt112");
	        var html ='';
	        
	        html+='<div class="font0 search-area"> <input class="search-box" type="text" placeholder="小区名称、客户姓名">  <a class="search-btn"  onclick="searchOrderList()"  href="javascript:;"></a> </div>';



	        var text =$(".search-box").val();
	        param={
	        	managerId:managerId,
	            text:text
	        }
	        
	        $.ajax({
	            url: "${ctx}/app/manager/newEnginInstallController/installListNew_ajax_list",
	            type: "post",
	            data:param,
	            success: function(data){
	            	
	            	if(null!=data){
		            	for(var v=0;v<data.length;v++){
		            		html+='<div class="sec font0 border_top border_btm mb30 bg_f clearfix">'+
				        				'<ul class="pad_top3 pl30 pr30 bor_dashed">'+
											'<li class="mb30 clearfix">'+
												'<span class="col_grey font30 flo_left pl2em">顾 客 信 息 ：</span>'+
												'<p class="font30 col_3 pad_rgt3 flow">'+ data[v].communityName +'-'+ data[v].buildNumber +'-'+ data[v].buildUnit +'-'+ data[v].buildRoom +'-'+ data[v].customerName +'</p>'+
											'</li>'+
											'<li class="mb30 clearfix">'+
												'<span class="col_grey font30 flo_left">合同开工日期：</span>'+
												'<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].contractStartDate,'yyyy-MM-dd')+'</p>'+
											'</li>'+
											'<li class="mb30 rele clearfix">'+
												'<span class="col_grey font30 flo_left">合同竣工日期：</span>'+
												'<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].contractEndDate,'yyyy-MM-dd')+'</p>'+
											'</li>';
								if(null==data[v].contractTime || data[v].contractTime=='undefined' || data[v].contractTime==0 || data[v].contractTime==""){
									
									html +=	'<li class="mb30 clearfix">'+
													'<span class="col_grey font30 flo_left pl2em">合 同 工 期 ：</span>'+
													'<p class="font30 col_3 pad_rgt3 flow">天</p>'+
												'</li>';
								}else{
									
									html +=	'<li class="mb30 clearfix">'+
													'<span class="col_grey font30 flo_left pl2em">合 同 工 期 ：</span>'+
													'<p class="font30 col_3 pad_rgt3 flow">'+ data[v].contractTime +'天</p>'+
												'</li>';
								}
								 
								html +=	'<li class="mb30 clearfix">'+
												'<span class="col_grey font30 flo_left pl2em">订 单 状 态 ：</span>'+
												'<p class="font30 col_blue pad_rgt3 flow">'+ data[v].orderStatusDescription +'</p>'+
											'</li>'+
										'</ul>';
										<%----%>
								<%--html+='<div class="btn_wrapper clearfix">'+--%>
										<%--'<a class="one_btn" href="${ctx }/app/manager/newEnginInstallController/installApplication?id='+ data[v].id +'">安装申请</a>'+--%>
										<%--'</div>'+--%>
									<%--'</div>';--%>
								if(data[v].secondPhase>0 ||data[v].storeId!=2){
									html+='<div class="btn_wrapper clearfix">'+
												'<a class="btnBlueBg" href="#" onclick="orderInstallCount('+data[v].id+')">安装申请</a>'+
												'</div>'+
											'</div>';
								}
								if(data[v].secondPhase==0&&data[v].storeId==2){
									html+='<div class="btn_wrapper clearfix">'+
												'<a class="btnBlueBg" href="#" onclick="inhibitApply('+data[v].id+')">安装申请</a>'+
												'</div>'+
											'</div>';
								}
		            	}
	            	}
					$(sectionObj).html(html);
		            $(".search-box").val(text);
					$('body').waitMe('hide');	
	            }
	        })
	    }



	    var orderId;
	    
		//-------------------------------------------------二期款start-------------------------------------------------------

		//弹框
	    function inhibitApply(orderValue){
	    	
	    	
	     /*   globalUtil.fn.alert("该订单未交二期款,赶紧催客户交钱",3.0);*/
	        orderId=orderValue;
	        $("#alertMask").addClass('_in');
	        $("#subReport").addClass('_in');
	    }
	    //弹框-关闭
	    function subReportClose(){
	    	
	    	$("#alertMask").removeClass('_in');
	        $("#subReport").removeClass('_in');
	    }
	  	//弹框-短信通知设计师
	    function sendMessage(){
			/*  globalUtil.fn.alert("该订单未交二期款,赶紧催客户交钱",3.0)*/
			
	    	$("#alertMask").removeClass('_in');
	        $("#subReport").removeClass('_in');

	        $.get("${ctx}/app/manager/newEnginInstallController/sendUrgeMessage.php?orderId="+orderId, function(data){

	            if(data==0){
	                //成功,但不需要发送短信

	            }else if (data==1){
	                //成功, 已发送今日提醒短信

	            }else if (data==3){
	                //成功, 没有发送短信, 客户手机不存在
	                globalUtil.fn.alert(" 客户手机不存在",3.0);
	            }else if (data==4){
	                //成功, 没有发送短信, 设计师手机或姓名不存在
	                globalUtil.fn.alert("设计师手机或姓名不存在",3.0);
	            }else{
	                globalUtil.fn.alert("数据异常",3.0);

	            }
	        })
	    }
	    
		//-------------------------------------------------二期款end-------------------------------------------------------


		//-------------------------------------------------校验是否有可申请的安装项start-------------------------------------------------------
		
		//校验是否有可申请的安装项
		function orderInstallCount(orderValue){
			orderId=orderValue;
			
			$.ajax({
				type : "POST",
				url : "${ctx}/app/manager/newEnginInstallController/check_install_plan_count_ajax",
				data:{
					orderId:orderId
				},
				success : function(data){
					if(null!=data && data == 0){
						window.location.href = "${ctx }/app/manager/newEnginInstallController/installApplication?id="+ orderId;
					}else if(data == 1){
						
						$("#orderInstallCountRemarks").text("订单id为空");
						$("#alertMask").addClass('_in');
				        $("#orderInstallCount").addClass('_in');
					}else{
						$("#orderInstallCountRemarks").text("该订单没有可申请安装的安装项，如有疑问请咨询大区经理或大区助理。");
						$("#alertMask").addClass('_in');
				        $("#orderInstallCount").addClass('_in');
					}
				}
			});
			
		}
		
		    	
		        
		 
		
		
		//弹框--关闭
		function orderInstallCountSure(){
			$("#alertMask").removeClass('_in');
	        $("#orderInstallCount").removeClass('_in');
		}


		


		//-------------------------------------------------校验是否有可申请的安装项end-------------------------------------------------------

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

</script>
</html>