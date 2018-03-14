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
    <title>进度日志</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/search.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/list.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/Details.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/mask.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>

<body>
    <div class="wrap">
        <header>
            <a class="back_btn" href="${ctx}/app/manager/newEnginInstallController/installApplication?id=${order.orderId}"></a>
            <h2 class="title">进度日志</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88">
            <div class="pad_btm3 bg_bo">
                <p class="font30 col_blue pad_top3 pad_left3 pad_rgt3 align_center">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom}-${order.customerName }</p>
                <p class="font30 col_blue pt20 pad_left3 pad_rgt3 align_center">安装项名称：${order.installItemName }</p>
            </div>
            <div class="navWrapper">
                <nav class="logNav">
                    <a class="navA active">催促日志</a>
                    <a class="navA" onclick="operation()">操作日志</a>
                </nav>
            </div>
            <section class="sec" id="urgeDetails">
                <!-- <div class="shadow">
                    <div class="font28 col_0780ec pt20 pb16 pad_left30 pad_rgt30 bor_btm_ddd rela">2017-02-13 14:50:52</div>
                    <ul class="bg_f pad_top24 bor_btm_ddd list">
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl1em">日志类型：</span>
                            <p class="font30 col_3">回复</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl3em">内容：</span>
                            <p class="font30 col_3">供应商未到按约定的时间到场，打电话也不接，不知道什么情况</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl2em">操作人：</span>
                            <p class="font30 col_3">张小小</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey">操作人类型：</span>
                            <p class="font30 col_3">项目经理</p>
                        </li>
                    </ul>
                </div>
                <div class="shadow">
                    <div class="font28 col_0780ec pt20 pb16 pad_left30 pad_rgt30 bor_btm_ddd rela">2017-02-13 14:50:52</div>
                    <ul class="bg_f pad_top24 bor_btm_ddd list">
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl1em">日志类型：</span>
                            <p class="font30 col_3">回复</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl3em">内容：</span>
                            <p class="font30 col_3">供应商未到按约定的时间到场，打电话也不接，不知道什么情况</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl2em">操作人：</span>
                            <p class="font30 col_3">张小小</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey">操作人类型：</span>
                            <p class="font30 col_3">项目经理</p>
                        </li>
                    </ul>
                </div>
                <div class="btn_wrapper clearfix" style="padding-top: .5rem;">
                    <a class="btnBlueOne" href="Installpress.html">催促</a>
                    <a class="btnBlueTwo" href="InstallApplyReply.html">回复</a>
                </div> -->
            </section>
            <section class="sec undis" id="operationDetails">
                <!-- <div class="mt30 bor_top_ddd shadow">
                    <ul class="bg_f pt30 bor_btm_ddd list">
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl2em">操作类型：</span>
                            <p class="font30 col_3">项目经理已申请</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl2em">操作时间：</span>
                            <p class="font30 col_3">2017-02-13 14:50:52</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey">期望进场日期：</span>
                            <p class="font30 col_3">2017-02-13 14:50:52</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl3em">操作人：</span>
                            <p class="font30 col_3">目经理</p>
                        </li>
                    </ul>
                </div>
                <div class="mt30 bor_top_ddd shadow">
                    <ul class="bg_f pt30 bor_btm_ddd list">
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl2em">操作类型：</span>
                            <p class="font30 col_3">项目经理已申请</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl2em">操作时间：</span>
                            <p class="font30 col_3">2017-02-13 14:50:52</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey">期望进场日期：</span>
                            <p class="font30 col_3">2017-02-13 14:50:52</p>
                        </li>
                        <li class="mb30 clearfix pl30 pr30">
                            <span class="font30 col_grey pl3em">操作人：</span>
                            <p class="font30 col_3">目经理</p>
                        </li>
                    </ul>
                </div> -->
            </section>
        </section>
        <!-- /section -->
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
            <button class="maskBtnOne" type="button" onclick="sure()">确 定</button>
        </div>
    </div>
    
    
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
    <script>
    $(document).on('click', '.navA', function() {
        $('.navA').removeClass('active');
        $(this).addClass('active');
        $('.sec').addClass('undis');
        $('.sec').eq($(this).index()).removeClass('undis');
    });
    
    
    
    
    
    
    
    
    
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
    
    var orderId = "${order.orderId}";
	var id = "${order.id}";
	var status = "${order.status}";
	var installMode = "${order.installMode}";

    
    searchUrgeList();
    
    
  //催促日志
	function searchUrgeList(){
		var sectionObj=$("#urgeDetails");
		var html = '';
		$.ajax({
			url: "${ctx}/app/manager/newEnginInstallController/urgeList_ajax_list",
            type: "post",
            data:{id:id},
            success: function(data) {
            	
            	if(null!=data){
	            	for(var v=0;v<data.length;v++){
	            		
	            		html += '<div class="shadow">'+
			                        '<div class="font28 col_0780ec pt20 pb16 pad_left30 pad_rgt30 bor_btm_ddd rela">'+format(data[v].operateDatetime,'yyyy-MM-dd HH:mm:ss')+'</div>'+
			                        '<ul class="bg_f pad_top24 bor_btm_ddd list">'+
			                            '<li class="mb30 clearfix pl30 pr30">'+
			                                '<span class="font30 col_grey pl1em">日志类型：</span>'+
			                                '<p class="font30 col_3">'+data[v].operateTypeName+'</p>'+
			                            '</li>'+
			                            '<li class="mb30 clearfix pl30 pr30">'+
			                                '<span class="font30 col_grey pl3em">内容：</span>'+
			                                '<p class="font30 col_3">'+data[v].operateContent+'</p>'+
			                            '</li>';
			                            '<li class="mb30 clearfix pl30 pr30">'+
			                                '<span class="font30 col_grey pl2em">操作人：</span>'+
			                                '<p class="font30 col_3">张小小</p>'+
			                            '</li>';
			                           
	            		
	            		
			        	if(null!=data[v].operatorEmployeeName && data[v].operatorEmployeeName!=""){
				        	html += '<li class="mb30 clearfix pl30 pr30">'+
			                            '<span class="font30 col_grey pl2em">操作人：</span>'+
			                            '<p class="font30 col_3">'+data[v].operatorEmployeeName+'</p>'+
			                        '</li>';
			        	}else if(data[v].createId == 1){
				        	html += '<li class="mb30 clearfix pl30 pr30">'+
			                            '<span class="font30 col_grey pl2em">操作人：</span>'+
			                            '<p class="font30 col_3">系统管理员</p>'+
			                        '</li>';
			        	}else{
				        	html += '<li class="mb30 clearfix pl30 pr30">'+
			                            '<span class="font30 col_grey pl2em">操作人：</span>'+
			                            '<p class="font30 col_3"></p>'+
			                        '</li>';
			        	}
			        	
			        	
	        			html += '<li class="mb30 clearfix pl30 pr30">'+
			                         '<span class="font30 col_grey">操作人类型：</span>'+
			                         '<p class="font30 col_3">'+data[v].operatorTypeName+'</p>'+
			                     '</li>'+
			                 '</ul>'+
			             '</div>';
	            		
	            	}
	            }
            	
            	if(status=="2" || status=="6"){
            		html += '<div class="btn_wrapper clearfix" style="padding-top: .5rem;">'+
		                        '<a class="btnBlueOne" onclick="pushInstallation()">催促</a>'+
		                        '<a class="btnBlueTwo" onclick="reply()">回复</a>'+
		                    '</div>';
            	}
            	
            	
            	$(sectionObj).html(html);
    	        $('body').waitMe('hide');
            }
		})
	
	}
	
	 //催促安装
    function pushInstallation(){
    	
    	
    	$.ajax({
			url: "${ctx}/app/manager/newEnginInstallController/push_installation_ajax",
            type: "post",
            data:{id:id},
            success: function(data) {
            	
            	if(null!=data && data=="2"){
        			$("#alertRemark").text("申请安装后24小时内不能进行催促安装");
					$("#alertMask").addClass('_in');
			        $("#alertAlert").addClass('_in');
            	}else if(data=="1"){
        			$("#alertRemark").text("一天只能催促2次，请您明天再来催促。");
					$("#alertMask").addClass('_in');
			        $("#alertAlert").addClass('_in');
            	}else{
            		window.location.href = "${ctx}/app/manager/newEnginInstallController/push_installation?id="+id+"&pushinstall=2&orderId="+orderId;
            	}
            	
            	
            }
    	})
    }
    
    
    function sure(){
		$("#alertMask").removeClass('_in');
        $("#alertAlert").removeClass('_in');
	}
    
    //回复
    function reply(){
    	window.location.href = "${ctx}/app/manager/newEnginInstallController/install_reply_page?id="+id;
    }

    //操作日志
	function operation(){
    	
	    if ($("#operationDetails").find('.shadow').length > 0) {
	    	return;
	    	
        }
		  
		run_waitMe('正在加载数据,请稍等');
		var sectionObj=$("#operationDetails");
		var html = '';
		$.ajax({
			url: "${ctx}/app/manager/newEnginInstallController/operationList_ajax_list",
            type: "post",
            data:{id:id},
            success: function(data) {
            	
            	if(null!=data){
	            	for(var v=0;v<data.length;v++){
			    		//已申请  重新申请
			    		if(data[v].businessStatus == 2 || data[v].businessStatus == 6){
			    			
		            		html += '<div class="mt30 bor_top_ddd shadow">'+
				                        '<ul class="bg_f pt30 bor_btm_ddd list">'+
					                        '<li class="mb30 clearfix pl30 pr30">'+
					                            '<span class="font30 col_grey pl2em">操作类型：</span>'+
					                            '<p class="font30 col_3">'+data[v].businessStatusName+'</p>'+
					                        '</li>'+
					                        '<li class="mb30 clearfix pl30 pr30">'+
					                            '<span class="font30 col_grey pl2em">操作时间：</span>'+
					                            '<p class="font30 col_3">'+format(data[v].statusDatetime,'yyyy-MM-dd HH:mm:ss')+'</p>'+
					                        '</li>';
		            		
		            		
			    			if(null!=data[v].remarks && data[v].remarks!=""){
			    				
				    			html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey">期望进场日期：</span>'+
				                            '<p class="font30 col_3">'+data[v].remarks+'</p>'+
				                        '</li>';
				    			
			    			}else{
			    				
				    			html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey">期望进场日期：</span>'+
				                            '<p class="font30 col_3"></p>'+
				                        '</li>';
			    			}
			    			
    						if(null!=data[v].businessEmployeeName && data[v].businessEmployeeName!=""){
    							
	    						html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey pl3em">操作人：</span>'+
				                            '<p class="font30 col_3">'+data[v].businessEmployeeName+'</p>'+
				                        '</li>';
			    						
    						}else{
    							
	    						html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey pl3em">操作人：</span>'+
				                            '<p class="font30 col_3"></p>'+
				                        '</li>';
    						}
    						
				    		html +=	'</ul>'+
				    			'</div>';
		    						
			    		}
			    		
			    		
			    		//已驳回
			    		if(data[v].businessStatus == 5){
			    			
			    			
			    			
			    			
			    			
			    			html += '<div class="mt30 bor_top_ddd shadow">'+
				                        '<ul class="bg_f pt30 bor_btm_ddd list">'+
					                        '<li class="mb30 clearfix pl30 pr30">'+
					                            '<span class="font30 col_grey pl2em">操作类型：</span>'+
					                            '<p class="font30 col_3">'+data[v].businessStatusName+'</p>'+
					                        '</li>'+
					                        '<li class="mb30 clearfix pl30 pr30">'+
					                            '<span class="font30 col_grey pl2em">操作时间：</span>'+
					                            '<p class="font30 col_3">'+format(data[v].statusDatetime,'yyyy-MM-dd HH:mm:ss')+'</p>'+
					                        '</li>';
					                
			    			if(null!=data[v].businessRemarks && data[v].businessRemarks!=""){
			    				
				    			html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey pl2em">驳回原因：</span>'+
				                            '<p class="font30 col_3">'+data[v].remarks+'-'+data[v].businessRemarks+'</p>'+
				                        '</li>';
			    			}else{
			    				
				    			html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey pl2em">驳回原因：</span>'+
				                            '<p class="font30 col_3">'+data[v].remarks+'</p>'+
				                        '</li>';
			    			}
			    			
							if(null!=data[v].businessEmployeeName && data[v].businessEmployeeName!=""){
    							
	    						html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey pl3em">操作人：</span>'+
				                            '<p class="font30 col_3">'+data[v].businessEmployeeName+'</p>'+
				                        '</li>';
			    						
    						}else{
    							
	    						html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey pl3em">操作人：</span>'+
				                            '<p class="font30 col_3"></p>'+
				                        '</li>';
    						}
    						
				    		html +=	'</ul>'+
				    			'</div>';
		    						
			    		}
		    						
	            	}
	            	
	            	
	            	
	            	for(var v=0;v<data.length;v++){
			    		//下达供应商
			    		if(data[v].businessStatus == 3){
			    			
			    			
			    			
			    			html += '<div class="mt30 bor_top_ddd shadow">'+
				                        '<ul class="bg_f pt30 bor_btm_ddd list">'+
					                        '<li class="mb30 clearfix pl30 pr30">'+
					                            '<span class="font30 col_grey pl2em">操作类型：</span>'+
					                            '<p class="font30 col_3">'+data[v].businessStatusName+'</p>'+
					                        '</li>'+
					                        '<li class="mb30 clearfix pl30 pr30">'+
					                            '<span class="font30 col_grey pl2em">操作时间：</span>'+
					                            '<p class="font30 col_3">'+format(data[v].statusDatetime,'yyyy-MM-dd HH:mm:ss')+'</p>'+
					                        '</li>';
		                
		                    var supplierDateOrName = "供应商确认日期：";
			    			if(installMode == "1"){
                                supplierDateOrName = "供应商名称：";
                            }
			    			if(null!=data[v].remarks && data[v].remarks!=""){

				    			html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey">'+supplierDateOrName+'</span>'+
				                            '<p class="font30 col_3">'+data[v].remarks+'</p>'+
				                        '</li>';
			    			}else{

				    			html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey">'+supplierDateOrName+'</span>'+
				                            '<p class="font30 col_3"></p>'+
				                        '</li>';
			    			}

    						
							if(null!=data[v].businessEmployeeName && data[v].businessEmployeeName!=""){
    							
	    						html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey pl3em">操作人：</span>'+
				                            '<p class="font30 col_3">'+data[v].businessEmployeeName+'</p>'+
				                        '</li>';
			    						
    						}else{
    							
	    						html +=	'<li class="mb30 clearfix pl30 pr30">'+
				                            '<span class="font30 col_grey pl3em">操作人：</span>'+
				                            '<p class="font30 col_3"></p>'+
				                        '</li>';
    						}
    						
				    		html +=	'</ul>'+
				    			'</div>';
				    			
			    		}
	            	}
            	}
            	
            	$(sectionObj).html(html);
            	$('body').waitMe('hide');
            }
		})
		
	}
	
	
	
	 var format = function(time, format){
	        if(time==undefined){

	            return "无";
	 	   }
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
</body>

</html>