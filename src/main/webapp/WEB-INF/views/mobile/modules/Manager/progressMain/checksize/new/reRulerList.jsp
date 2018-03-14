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
    <title>申请厂家复尺</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/search.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/list.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/mask.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>

<body>
    <div class="wrap">
        <header>
            <a class="back_btn" href="${ctx }/app/manager/projectInstallMenu"></a>
            <h2 class="title">申请厂家复尺</h2>
        </header>
        <!-- /header -->
        <section class="pt112" id="sectionRemark">
           <!--  <div class="mar_btm14 font0 search-area">
                <input class="search-box" type="text" placeholder=" 小区名称、客户姓名">
                <a class="search-btn" href="javascript:;"></a>
            </div>
            <div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3 bor_dashed">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">顾客信息：</span>
                        <p class="font30 col_3 pad_rgt3 flow">鹿港嘉苑-10-3-2001-张三丰鹿港嘉苑-10-3-2001-张三丰</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">合同开工日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">合同竣工日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
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
                    <a class="btnBlueOne" href="reRuler.html">申请厂家复尺</a>
                    <a class="btnBlueTwo" href="reRulerDetails.html">复尺记录</a>
                </div>
            </div>
            <div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">
                <ul class="pad_top3 pad_left3 bor_dashed">
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left pl2em">顾客信息：</span>
                        <p class="font30 col_3 pad_rgt3 flow">鹿港嘉苑-10-3-2001-张三丰鹿港嘉苑-10-3-2001-张三丰</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">合同开工日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 flo_left">合同竣工日期：</span>
                        <p class="font30 col_3 pad_rgt3 flow">2016-07-30</p>
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
                    <a class="btnBlueOne" href="javascript:;">申请厂家复尺</a>
                    <a class="btnBlueTwo" href="javascript:;">复尺记录</a>
                </div>
            </div> -->
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
            <p id="alertRemark">该订单没有可复尺的安装项，如有疑问请咨询大区经理或大区助理。
            </p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="alertSure()">确 定</button>
        </div>
    </div>
    <!-- /.maskWrapper -->
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
		
		
	    searchOrderList();
	    
	    function searchOrderList(){
	    	var sectionObj=$("#sectionRemark");
	        var html ='';
	        
	        html += '<div class="mar_btm14 font0 search-area">'+
		                '<input class="search-box" type="text" placeholder=" 小区名称、客户姓名">'+
		                '<a class="search-btn" href="javascript:;" onclick="searchOrderList()" ></a>'+
		            '</div>';

	        var text =$(".search-box").val();
	        
	        $.ajax({
	            url: "${ctx}/app/manager/checksize/checksize_order_list_ajax",
	            type: "post",
	            data: {
	            	text:text
	            },
	            success: function(data){
	            	
	            	if(null!=data && data.length>0){
	            		
		            	for(var v=0;v<data.length;v++){
		            		
		            		html += '<div class="sec font0 border_top border_btm mar_btm24 bg_f shadow clearfix">'+
				                        '<ul class="pad_top3 pad_left3 bor_dashed">'+
						                    '<li class="mb30 clearfix">'+
						                        '<span class="col_grey font30 flo_left pl2em">顾客信息：</span>'+
						                        '<p class="font30 col_3 pad_rgt3 flow">'+ data[v].communityName +'-'+ data[v].buildNumber +'-'+ data[v].buildUnit +'-'+ data[v].buildRoom +'-'+ data[v].customerName +'</p>'+
						                    '</li>'+
						                    '<li class="mb30 clearfix">'+
						                        '<span class="col_grey font30 flo_left">合同开工日期：</span>'+
						                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].contractStartDate,'yyyy-MM-dd')+'</p>'+
						                    '</li>'+
						                    '<li class="mb30 clearfix">'+
						                        '<span class="col_grey font30 flo_left">合同竣工日期：</span>'+
						                        '<p class="font30 col_3 pad_rgt3 flow">'+format(data[v].contractEndDate,'yyyy-MM-dd')+'</p>'+
						                    '</li>';
		            		if(null==data[v].contractTime || data[v].contractTime=='undefined' || data[v].contractTime==0 || data[v].contractTime==""){
								
								html +=	'<li class="mb30 clearfix">'+
					                        '<span class="col_grey font30 flo_left pl2em">合同工期：</span>'+
					                        '<p class="font30 col_3 pad_rgt3 flow">天</p>'+
					                    '</li>';
							}else{
								
								html +=	'<li class="mb30 clearfix">'+
					                        '<span class="col_grey font30 flo_left pl2em">合同工期：</span>'+
					                        '<p class="font30 col_3 pad_rgt3 flow">'+ data[v].contractTime +'天</p>'+
					                    '</li>';
							}
						    
		            		html += '<li class="mb30 clearfix">'+
				                        '<span class="col_grey font30 flo_left pl2em">订单状态：</span>'+
				                        '<p class="font30 col_blue pad_rgt3 flow">'+ data[v].orderStatusDescription +'</p>'+
				                    '</li>'+
				                '</ul>'+
				                '<div class="btn_wrapper clearfix">'+
				                    '<a class="btnBlueOne" href="#" onclick="applyChecksize('+data[v].orderId+')">申请厂家复尺</a>'+
				                    '<a class="btnBlueTwo" href="${ctx }/app/manager/checksize/checksizeRecord?orderId='+data[v].orderId+'">复尺记录</a>'+
				                '</div>'+
				            '</div>';
								
		            	}
	            	}
					$(sectionObj).html(html);
		            $(".search-box").val(text);
					$('body').waitMe('hide');	
	            }
	        })
	    }
	    
	    //--------------------------申请厂家复尺校验start----------------------------------------------------
	    
	    function applyChecksize(orderId){
	    	
	    	
	    	$.ajax({
				type : "POST",
				url : "${ctx}/app/manager/checksize/check_checksize_data_ajax",
				data:{
					orderId:orderId,
					type:"1"
				},
				success : function(data){
					if(null!=data && data == 0){
						//可以申请
						window.location.href = "${ctx }/app/manager/checksize/applyChecksize?orderId="+ orderId;
					}else if(data == 1){
						
						$("#alertRemark").text("订单id为空");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}else if(data == 2){
						$("#alertRemark").text("该订单没有可复尺的安装项，如有疑问请咨询大区经理或大区助理。");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}else{
						$("#alertRemark").text("同一个订单两次厂家复尺申请操作时间必须间隔5分钟，请过5分钟后再申请");
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
					}
				}
			});
	    	
	    	
	    }
	    
	    //弹框-关闭
	    function alertSure(){
	    	$("#alertMask").removeClass('_in');
	        $("#alertAlert").removeClass('_in');
	    }
	    
	    //--------------------------申请厂家复尺校验end----------------------------------------------------
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