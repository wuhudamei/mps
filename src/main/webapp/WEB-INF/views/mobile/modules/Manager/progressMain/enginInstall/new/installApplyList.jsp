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
    <title>安装申请</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/mask.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/list.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>

<body>
    <div class="wrap">
        <header>
            <a class="back_btn" href="${ctx }/app/manager/newEnginInstallController/installListNew?apply=1"></a>
            <h2 class="title">安装申请</h2>
        </header>
        <!-- /header -->
        <section class="pad_top88">
          <!--   <div class="pt30 pb30 pl30 pr30">
                <p class="font30 col_blue align_center">鹿港嘉苑鹿港嘉苑-10-3-2001-张三</p>
            </div>
            <div class="sec font0 border_top border_btm mb30 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3 bor_dashed">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">安装项目：</span>
                        <p class="font30 col_3 pad_rgt3 flow">家电安装</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">计划安装日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2017-04-25</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_e90f0f font30 flo_left pl4em">状态：</span>
                        <p class="font30 col_e90f0f pad_rgt3 flow">已驳回</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_e90f0f font30 flo_left pl2em">驳回类型：</span>
                        <p class="font30 col_e90f0f pad_rgt3 flow">undified</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">驳回日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">无</p>
                    </li>
                </ul>
                <div class="btn_wrapper clearfix">
                    <a class="btnBlueOne" href="InstallApply.html">重新申请</a>
                    <a class="btnBlueTwo" href="javascript:;">进度日志</a>
                </div>
            </div>
            /.sec
            <div class="sec font0 border_top border_btm mb30 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3 bor_dashed">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">安装项目：</span>
                        <p class="font30 col_3 pad_rgt3 flow">橱柜、台面</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">计划安装日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2017-04-25</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl4em">状态：</span>
                        <p class="font30 col_3 pad_rgt3 flow">驳回后申请</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">申请时间：</span>
                        <p class="font30 col_3 pad_rgt3 flow">无</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">期望进场日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">无</p>
                    </li>
                </ul>
                <div class="btn_wrapper clearfix">
                    <a class="btnBlueOne" href="InstallApply.html">催促安装</a>
                    <a class="btnBlueTwo" href="javascript:;">进度日志</a>
                </div>
            </div>
            /.sec
            <div class="sec font0 border_top border_btm mb30 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3 bor_dashed">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">安装项目：</span>
                        <p class="font30 col_3 pad_rgt3 flow">灯具开关小五金</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">计划安装日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2017-04-25</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl4em">状态：</span>
                        <p class="font30 col_3 pad_rgt3 flow">已生成计划</p>
                    </li>
                </ul>
                <div class="btn_wrapper clearfix">
                    <a class="btnBlueBorder" href="javascript:;">安装申请</a>
                </div>
            </div>
            /.sec
            <div class="sec font0 border_top border_btm mb30 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3 bor_dashed">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">安装项目：</span>
                        <p class="font30 col_3 pad_rgt3 flow">橱柜、台面</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">计划安装日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2017-04-25</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl4em">状态：</span>
                        <p class="font30 col_3 pad_rgt3 flow">已申请</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">申请时间：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2016-11-19 12：27：27</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">期望进场日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2016-11-23</p>
                    </li>
                </ul>
                <div class="btn_wrapper clearfix">
                    <a class="btnBlueOne" href="InstallApply.html">催促安装</a>
                    <a class="btnBlueTwo" href="javascript:;">进度日志</a>
                </div>
            </div>
            /.sec
            <div class="sec font0 border_top border_btm mb30 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3 bor_dashed">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">安装项目：</span>
                        <p class="font30 col_3 pad_rgt3 flow">门内安装</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">计划安装日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2017-04-25</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl4em">状态：</span>
                        <p class="font30 col_3 pad_rgt3 flow">已转给供应商</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">申请时间：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2016-11-19 12：27：27</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">期望进场日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2016-11-23</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">供应商确认日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">无</p>
                    </li>
                </ul>
                <div class="btn_wrapper clearfix">
                    <a class="btnBlueBorder" href="javascript:;">进度日志</a>
                </div>
            </div> -->
            <!-- /.sec -->
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
            <p id="alertRemark">申请安装后24小时内不能进行催促安装</p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="alertSure()">确 定</button>
        </div>
    </div>
    <div class="maskWrapper" id="alertAlertTwo">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p id="alertRemarkTwo">
            </p>
        </div>
        <div class="maskBtns twoBtns clearfix">
            <button class="maskBtn" type="button" onclick="alertSureTwo()">确 定</button>
            <button class="maskBtn" type="button" onclick="alertApplyTwo()">提前申请</button>
        </div>
    </div>
   
    <div class="maskWrapper" id="alertAlertThree">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p id="alertRemarkThree">
            </p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="alertSureThree()">已提交（提前申请）</button>
        </div>
    </div>
    
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
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
		var orderId = "${order.id}";
		
		searchOrderDetailList();
	    function searchOrderDetailList(){
	    	var sectionObj=$(".pad_top88");
 	        var html ='<div class="pt30 pb30 pl30 pr30">'+
							'<p class="font30 col_blue align_center">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom}-${order.customerName }</p>'+
						'</div>'; 
	    	
			$.ajax({
				url: "${ctx}/app/manager/newEnginInstallController/installApplyList_ajax_list",
	            type: "post",
	            data:{orderId:orderId},
	            success: function(data) {
	            	
	            	if(null!=data){
		            	for(var v=0;v<data.length;v++){
		            		
												
		            		if(data[v].status=="5"){
								/* ------------------------已驳回----重新申请、进度日志-------------------------- */
								
								
								html += '<div class="sec font0 border_top border_btm mb30 bg_f shadow clearfix">'+
							                '<ul class="pad_top3 pad_left3 bor_dashed">'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl2em">安装项目：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+data[v].installItemName+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left">计划安装日期：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].planIntoDate,'yyyy-MM-dd')+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_e90f0f font30 flo_left pl4em">状态：</span>'+
							                        '<p class="font30 col_e90f0f pad_rgt3 flow">'+data[v].statusName+'</p>'+
							                    '</li>';
							                    
		            			
								if(null!=data[v].rejectBusinessRemarks && data[v].rejectBusinessRemarks!=""){
									
									html += '<li class="mb30 clearfix">'+
						                        '<span class="col_e90f0f font30 flo_left pl2em">驳回原因：</span>'+
						                        '<p class="font30 col_e90f0f pad_rgt3 flow">'+data[v].rejectRemarksName+'-'+data[v].rejectBusinessRemarks+'</p>'+
						                    '</li>'
								}else{
									
									html += '<li class="mb30 clearfix">'+
												'<span class="col_e90f0f font30 flo_left pl_2em">驳回原因：</span>'+
												'<p class="font30 col_e90f0f pad_rgt3 flow">'+data[v].rejectRemarksName+'</p>'+
											'</li>';
								}
								
								html += '<li class="mb30 clearfix">'+
					                        '<span class="col_grey font30 flo_left pl2em">驳回日期：</span>'+
					                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].rejectStatusDatetime,'yyyy-MM-dd HH:mm:ss')+'</p>'+
					                    '</li>'
							
								html += '</ul>'+
									'<div class="btn_wrapper clearfix">'+
										'<a class="btnBlueOne" href="javascript:;" onclick="reApply('+data[v].id+',${order.id })">重新申请</a>'+
										'<a class="btnBlueTwo" href="javascript:;" onclick="progressLog('+data[v].id+')">进度日志</a>'+
									'</div>'+
								'</div>';
								
							}else if(data[v].status=="6"){
								/* ------------------------驳回后申请----催促安装、进度日志-------------------------- */
								
												
								html += '<div class="sec font0 border_top border_btm mb30 bg_f shadow clearfix">'+
							                '<ul class="pad_top3 pad_left3 bor_dashed">'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl2em">安装项目：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+data[v].installItemName+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left">计划安装日期：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].planIntoDate,'yyyy-MM-dd')+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl4em">状态：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+data[v].statusName+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl2em">申请时间：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].applyIntoCreateDatetime,'yyyy-MM-dd HH:mm:ss')+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left">期望进场日期：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].applyIntoDate,'yyyy-MM-dd')+'</p>'+
							                    '</li>'+
							                '</ul>'+
							                '<div class="btn_wrapper clearfix">'+
							                    '<a class="btnBlueOne" href="javascript:;" onclick="pushInstallation('+data[v].id+')">催促安装</a>'+
												'<a class="btnBlueTwo" href="javascript:;" onclick="progressLog('+data[v].id+')">进度日志</a>'+
							                '</div>'+
							            '</div>';
										
								
							}else if(data[v].status=="1"){
								/* ------------------------已生成计划----安装申请-------------------------- */
								
								
								html += '<div class="sec font0 border_top border_btm mb30 bg_f shadow clearfix">'+
							                '<ul class="pad_top3 pad_left3 bor_dashed">'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl2em">安装项目：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow" id="installItemName'+data[v].id+'">'+data[v].installItemName+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left">计划安装日期：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].planIntoDate,'yyyy-MM-dd')+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl4em">状态：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+data[v].statusName+'</p>'+
							                    '</li>'+
							                '</ul>'+
							                '<div class="btn_wrapper clearfix">'+
												'<a class="btnBlueBorder"  onclick="sendAjax('+data[v].id+',${order.id })">安装申请</a>'+
							                '</div>'+
							            '</div>';
										
							}else if(data[v].status=="2"){
								
								/* ------------------------已申请----催促安装、进度日志-------------------------- */
								
								
								
								html += '<div class="sec font0 border_top border_btm mb30 bg_f shadow clearfix">'+
							                '<ul class="pad_top3 pad_left3 bor_dashed">'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl2em">安装项目：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+data[v].installItemName+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left">计划安装日期：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].planIntoDate,'yyyy-MM-dd')+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl4em">状态：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+data[v].statusName+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl2em">申请时间：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].applyIntoCreateDatetime,'yyyy-MM-dd HH:mm:ss')+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left">期望进场日期：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].applyIntoDate,'yyyy-MM-dd')+'</p>'+
							                    '</li>'+
							                '</ul>'+
							                '<div class="btn_wrapper clearfix">'+
												'<a class="btnBlueOne" href="javascript:;" onclick="pushInstallation('+data[v].id+')">催促安装</a>'+
												'<a class="btnBlueTwo" href="javascript:;" onclick="progressLog('+data[v].id+')">进度日志</a>'+
							                '</div>'+
							            '</div>';
							            
										
							}else if(data[v].status == "3" || data[v].status == "310" || data[v].status == "320" || data[v].status == "330"){
								/* ------------------------已转供应商----进度日志-------------------------- */
								
								html += '<div class="sec font0 border_top border_btm mb30 bg_f shadow clearfix">'+
							                '<ul class="pad_top3 pad_left3 bor_dashed">'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl2em">安装项目：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+data[v].installItemName+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left">计划安装日期：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].planIntoDate,'yyyy-MM-dd')+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl4em">状态：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+data[v].statusName+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left pl2em">申请时间：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].applyIntoCreateDatetime,'yyyy-MM-dd HH:mm:ss')+'</p>'+
							                    '</li>'+
							                    '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left">期望进场日期：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].applyIntoDate,'yyyy-MM-dd')+'</p>'+
							                    '</li>'+
								                '<li class="mb30 clearfix">'+
							                        '<span class="col_grey font30 flo_left">供应商确认日期：</span>'+
							                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].supplierIntoDate,'yyyy-MM-dd')+'</p>'+
							                    '</li>'+
							                '</ul>'+
							                '<div class="btn_wrapper clearfix">'+
												'<a class="btnBlueBorder" href="javascript:;" onclick="progressLog('+data[v].id+')">进度日志</a>'+
							                '</div>'+
							            '</div>';
							}
	
		            	}
	            	}
	            	$(sectionObj).html(html);
	    	        $('body').waitMe('hide');
				}
			})
	        
	    }

	    var orderGlobal;
	    var installPlanGlobal;
	    

	    //催促安装
	    function pushInstallation(id){
	    	
	    	
	    	$.ajax({
				url: "${ctx}/app/manager/newEnginInstallController/push_installation_ajax",
	            type: "post",
	            data:{id:id},
	            success: function(data) {
	            	if(null!=data && data=="0"){
	            		window.location.href = "${ctx}/app/manager/newEnginInstallController/push_installation?id="+id+"&pushinstall=1&orderId=${order.id}";
	            	}else if(data=="1"){
	            		$("#alertRemark").text("一天只能催促2次，请您明天再来催促。");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
	            	}else{
	            		$("#alertRemark").text("申请安装后24小时内不能进行催促安装");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
	            		
	            	}
	            }
	    	})
	    }
	    
	    //安装申请
	    function sendAjax(id,orderId){
	    	
	    	orderGlobal = orderId;
	    	installPlanGlobal = id;
	    	var installItemName = $("#installItemName"+id).text();
	    	
			$.ajax({
				url : "${ctx}/app/manager/newEnginInstallController/checksizeAjax",
				type : "POST",
				dataType : "json",
				data:{
					id:id
				},
				success : function(data){
					
					if(data == 0){
						window.location.href = "${ctx }/app/manager/newEnginInstallController/appInstall?id="+id+"&orderId="+orderId;
					}else if(data == 1){
						$("#alertRemark").text("安装项id为空");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}else if(data == 2){
						$("#alertRemark").text("该主材只有复尺后才能申请安装!");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}else if(data == 3){
						$("#alertRemark").text("订单包含的橱柜、木门、木地板安装项未验收完毕，请及时处理。");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}else if(data.code == 4){
						$("#alertRemarkTwo").text("该工地" + data.orderConfirmStartWorkDateString + "开工，按照工程部规定主材（" + installItemName + "）开工" + data.workApplyDayString + "天后（" + data.applyIntoRemarks + "）才可以申请安装，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。");
						$("#alertMask").addClass('_in');
				        $("#alertAlertTwo").addClass('_in');
					}else if(data.code == 5){
						$("#alertRemarkThree").text("该工地" + data.orderConfirmStartWorkDateString + "开工，按照工程部规定主材（" + installItemName + "）开工" + data.workApplyDayString + "天后（" + data.applyIntoRemarks + "）才可以申请安装，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。");
						$("#alertMask").addClass('_in');
				        $("#alertAlertThree").addClass('_in');
					}else{
						$("#alertRemark").text("安装项不可申请安装");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}
				}
			});
			
		}
	    
	    //重新申请安装
	    function reApply(id,orderId){
	    	window.location.href = "${ctx }/app/manager/newEnginInstallController/reAppInstall?id="+id+"&orderId="+orderId;
	    }
	    
	    //提前申请
	    function alertApplyTwo(){
	    	window.location.href = "${ctx }/app/manager/newEnginInstallController/installPlanAdvanceApply?id="+installPlanGlobal+"&orderId="+orderGlobal;
	    }
	    
	  	//弹框-关闭
	    function alertSure(){
	    	$("#alertMask").removeClass('_in');
	        $("#alertAlert").removeClass('_in');
	    }
	    
	    function alertSureTwo(){
	    	$("#alertMask").removeClass('_in');
	        $("#alertAlertTwo").removeClass('_in');
	    }
	    
	    function alertSureThree(){
	    	$("#alertMask").removeClass('_in');
	        $("#alertAlertThree").removeClass('_in');
	    }
	    
	    //进度日志
	    function progressLog(id){
	    	window.location.href = "${ctx}/app/manager/newEnginInstallController/progress_log?id="+id;
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
</html>