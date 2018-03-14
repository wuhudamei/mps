<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/mobile/modules/home/footer.jsp" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>质检报告</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/order_details.css"/>
</head>
<body>
	<div class="report-details">
		<header>
			<a class="back_btn" href="${ctx }/app/home/report/list"></a>
			<h2 class="title">质检报告</h2>
		</header><!-- /header -->
		<section class="total font0">
			<p class="report-tit bg_f mar_top24"><span class="text-tit">基本信息</span></p>
			<ul class="item pad_left3 pad_rgt3 bg_f">
				<li class="mar_btm24">
					<span class="font28 col_grey fl_left">小区名称：</span>
					<p class="font28 col_3">${report.communityName }-${report.buildNumber }-${report.buildUnit }-${report.buildRoom }</p>
				</li>
				<li class="mar_btm24">
					<span class="font28 col_grey fl_left">质 检 员：</span>
					<p class="font28 col_3">${report.inspector }</p>
				</li>
				<li class="mar_btm24">
					<span class="font28 col_grey fl_left">检查类型：</span>
					<c:if test="${report.isRecheck=='0' }">
						<p class="font28 col_3">约检</p>
					</c:if>
					<c:if test="${report.isRecheck=='1' }">
						<p class="font28 col_3">复检</p>
					</c:if>
				</li>
				<li class="mar_btm24">
					<span class="font28 col_grey fl_left">检查内容：</span>
					<p class="font28 col_3">${report.qcCheckNodeName }</p>
				</li>
				<li class="mar_btm24">
					<span class="font28 col_grey fl_left">检查日期：</span>
					<p class="font28 col_3"><fmt:formatDate value="${report.checkDatetime }" pattern="yyyy-MM-dd"/></p>
				</li>
			</ul>
			<p class="report-tit bg_f"><span class="text-tit">质检照片</span></p>
			<ul class="item pad_left3 pad_rgt3 bg_f">
				<li class="mar_btm24">
					<a class="photo-see" href="${ctx }/app/home/report/reportPic?qcBillId=${report.qcBillId}">
						<span class="font28 col_grey fl_left">照片数量：</span>
						<p class="font28 col_3">${report.picCount }张</p>
					</a>
				</li>
			</ul>
			<p class="report-tit bg_f"><span class="text-tit">质检内容</span></p>
			<ul class="border_t border_b pad_left3 pad_rgt3 bg_f mar_btm0">
				<p class="tit-blue">汇总：</p>
				<li class="check-item dot_line">
					<span class="font28 col_grey fl_left">检查项总项：</span>
					<p class="font28 col_3">${report.itemCount }项</p>
				</li>
				<li class="check-item dot_line">
					<span class="font28 col_grey fl_left">合　　　格：</span>
					<p class="font28 col_3">${report.passedCount }项</p>
				</li>
				<li class="check-item">
					<span class="font28 col_grey fl_left">不　合　格：</span>
					<p class="font28 col_cred">${report.noPassedCount }项</p>
				</li>
			</ul>
			<ul class="border_b pad_left3 pad_rgt3 pad_btm90 bg_f mar_btm300">
				<p class="tit-blue">施工详情：</p>
				<c:forEach items="${report.checkItemList }" var="checkItem">
					<li class="check-item dot_line">
						<c:if test="${checkItem.isPassed=='1' }">
							<a class="rgt_bg block" href="javascript:;">
								<span class="font28 col_grey fl_left">${checkItem.qcCheckItemName } </span>
								<p class="rgt_align  font28 col_grey">合格</p>
							</a>
						</c:if>
						<c:if test="${checkItem.isPassed=='0' }">
							<a class="rgt_bg block details-see" href="javascript:;" onclick="buhege('${checkItem.qcBillItemId}')">
								<span class="font28 col_grey fl_left">${checkItem.qcCheckItemName } </span>
								<p class="rgt_align  font28 col_cred">不合格</p>
							</a>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</section>
	</div>
	<div class="mask1 undis">
		<div class="box bg_f font0">
			<p class="tit_mask font30 col_3">小美提醒您</p>
			<div class="mask-content">
			<ul class="" id="weigui">
				<!-- <p class="small_tit">违规形式：</p>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">1、没穿正装。</li>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">2、不按规定清扫现场、现场脏、乱</li>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">3、不按公司和物业指定地点堆放</li>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">4、上段工序完成后未及时清理进入下一道工序</li> -->
			</ul>
			<ul class="pad_btm24" id="chuli">
				<!-- <p class="small_tit">处理方式：</p>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">1、没穿正装。</li>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">2、不按规定清扫现场、现场脏、乱</li>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">3、不按公司和物业指定地点堆放</li>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">4、上段工序完成后未及时清理进入下一道工序</li>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">1、没穿正装。</li>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">2、不按规定清扫现场、现场脏、乱</li>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">3、不按公司和物业指定地点堆放</li>
				<li class="font26 col_3 mar_top24 pad_left3 pad_rgt6">4、上段工序完成后未及时清理进入下一道工序</li> -->
			</ul>
			</div>
			<a class="shut_btn" href="javascript:;">关闭</a>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
		$(function(){
			/* $(document).on('click', '.details-see', function(){
				$('.mask1').removeClass('undis');
				$('html').css({'overflow':'hidden'});
			}); */
			$(document).on('click', '.shut_btn', function(){
				$('.mask1').addClass('undis');
				$('html').css({'overflow':'auto'});
			});
		});
		
		function buhege(qcBillItemId){
			$.ajax({
				url:"${ctx }/app/home/report/checkBreak",
				type : "get",
				data:{
					qcBillItemId : qcBillItemId
					},
				success : function(data){
					
					//违规形式
					var htmls = "<p class='small_tit'>违规形式：</p>";
					var a = data.checkBreakList;
					for(var i=0;i<a.length;i++){
						var num = i+1;
						htmls += "<li class='font26 col_3 mar_top24 pad_left3 pad_rgt6'>"+num+"."+a[i].breakDescribe+"</li>";
						if(a[i].isRequiredRemarks=="1"){
							htmls += "<li class='font26 col_3 mar_top24 pad_left3 pad_rgt6'>备注："+a[i].breakRemarks+"</li>";
						}
					} 
					$("#weigui").html(htmls);
				
					
					
					//处理方式
					var x =1;
					var htmlsTwo = "<p class='small_tit'>处理方式：</p>";
					if(data.isWarned=="1"){
						htmlsTwo += "<li class='font26 col_3 mar_top24 pad_left3 pad_rgt6'>"+x+":警告</li>";
						x++;
					}
					if(data.isLocaleRepaire=="1"){
						htmlsTwo += "<li class='font26 col_3 mar_top24 pad_left3 pad_rgt6'>"+x+":现场整改</li>";
						x++;
					}
					if(data.isLimitDateRepaire=="1"){
						htmlsTwo += "<li class='font26 col_3 mar_top24 pad_left3 pad_rgt6'>"+x+":期限整改";
						if(data.limitDateRepaireCheckStyle == "1"){
							htmlsTwo += "-线下整改";
						}else if(data.limitDateRepaireCheckStyle == "0"){
							htmlsTwo += "-线上整改";
						}
						htmlsTwo += "-整改日期:"+format(data.limitDate,'yyyy-MM-dd')+"</li>";
						x++;
					}
					if(data.isPunishMoney=="1"){
						htmlsTwo += "<li class='font26 col_3 mar_top24 pad_left3 pad_rgt6'>"+x+":扣分/罚款 - 项目经理 "+data.pmPunishScore+"分/"+data.punishMoneyAmountReal+"元 工人 "+data.workerPunishScore+"分/"+data.workerPunishAmount+"元 质检员 "+data.qcPunishScore+"分/"+data.qcPunishAmount+"元</li>";
						x++;
					}	
					$("#chuli").html(htmlsTwo);
					
					
					
					$('.mask1').removeClass('undis');
					$('html').css({'overflow':'hidden'});
			 	 }
			});
			
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