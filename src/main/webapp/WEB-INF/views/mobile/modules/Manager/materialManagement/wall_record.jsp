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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/materialManagement/wallAndFloorNew/list"></a>
			<h2 class="title">申请墙地砖</h2>
		</header><!-- /header -->
		<section class="pad_top88">
			<div class="bg_bo">
				<p class="font30 tit-pos">${materialManagement.communityName }-${materialManagement.buildNumber }-${materialManagement.buildUnit }-${materialManagement.buildRoom }-${materialManagement.customerName }</p>
			</div>
			<div id="urgeDetails">
				<!-- <div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
					<ul class="pad_top3 pad_left3 bor_dotted">
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left spanRgt">采购单编号：</span>
							<p class="font28 col_3 pad_rgt3 flow">P201608060001</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left spanRgt">采购单状态：</span>
							<p class="font28 col_3 pad_rgt3 flow">项目经理已申请</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left spanRgt">第N次申请：</span>
							<p class="font28 col_3 pad_rgt3 flow">2016-08-06</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left spanRgt">期望进场日期：</span>
							<p class="font28 col_3 pad_rgt3 flow">2016-08-06</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left spanRgt">申请时间：</span>
							<p class="font28 col_3 pad_rgt3 flow">2016-07-30</p>
						</li>
					</ul>
					<div class="btn_wrapper clearfix">
						<a class="two_btn1" href="javascript:;">催促送货</a>
						<a class="two_btn2" href="javascript:;">详情</a>
					</div>
				</div> -->
				<!-- <div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
					<ul class="pad_top3 pad_left3 bor_dotted">
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left spanRgt">采购单编号：</span>
							<p class="font28 col_3 pad_rgt3 flow">P201608060001</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left spanRgt">采购单状态：</span>
							<p class="font28 col_3 pad_rgt3 flow">项目经理已申请</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left spanRgt">第N次申请：</span>
							<p class="font28 col_3 pad_rgt3 flow">2016-08-06</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left spanRgt">期望进场日期：</span>
							<p class="font28 col_3 pad_rgt3 flow">2016-08-06</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left spanRgt">申请时间：</span>
							<p class="font28 col_3 pad_rgt3 flow">2016-07-30</p>
						</li>
					</ul>
					<div class="btn_wrapper clearfix">
						<a class="two_btn1" href="javascript:;">催促送货</a>
						<a class="two_btn2" href="javascript:;">详情</a>
					</div>
				</div> -->
			</div>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
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
		
		
		var orderId = "${materialManagement.id}";
		searchWallandFloorList();
		
		function searchWallandFloorList(){
			var sectionObj=$("#urgeDetails");
			var html = '';
			
			$.ajax({
				url: "${ctx}/app/manager/search_wallandFloor_list_ajax",
	            type: "post",
	            data: {orderId:orderId},
	            success: function(data) {
	            	if(null!=data){
		            	for(var v=0;v<data.length;v++){
		            		var aa = data.length;
		            		var bb = aa-v;
		            		html += '<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">'+
					    					'<ul class="pad_top3 pad_left3 bor_dotted">'+
												'<li class="mar_btm24 clearfix">'+
													'<span class="col_grey font28 flo_left spanRgt">采购单编号：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].purchaseCode+'</p>'+
												'</li>'+
												'<li class="mar_btm24 clearfix">'+
													'<span class="col_grey font28 flo_left spanRgt">采购单状态：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+data[v].statusName+'</p>'+
												'</li>'+
												'<li class="mar_btm24 clearfix">'+
													'<span class="col_grey font28 flo_left spanRgt">申请次数：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">第'+bb+'次申请</p>'+
												'</li>'+
												'<li class="mar_btm24 clearfix">'+
													'<span class="col_grey font28 flo_left spanRgt">期望进场日期：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+format(data[v].applyReceiveTime,'yyyy-MM-dd')+'</p>'+
												'</li>'+
												'<li class="mar_btm24 clearfix">'+
													'<span class="col_grey font28 flo_left spanRgt">申请时间：</span>'+
													'<p class="font28 col_3 pad_rgt3 flow">'+format(data[v].applyTime,'yyyy-MM-dd HH:mm:ss')+'</p>'+
												'</li>'+
											'</ul>'+
											'<div class="btn_wrapper clearfix">'+
												'<a class="two_btn1" href="${ctx}/app/manager/urgeWallAndFloorList?purchaseId='+data[v].id +'&orderId='+orderId+'">催促送货</a>'+
												'<a class="two_btn2" href="${ctx}/app/manager/wallAndFloorBrickDetails?purchaseId='+data[v].id +'&id='+orderId+'">详情</a>'+
											'</div>'+
										'</div>';
		            	}
	            	}
	            	
	            	$(sectionObj).html(html);
	    	        $('body').waitMe('hide');
	            }
			})
	    }
		
		
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
</body>
</html>