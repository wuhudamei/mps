<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>订单全动作查询</title>
	<link rel="stylesheet" href="${ctxStatic}/modules/orderReportRelatedContract/css/packahesChoice.css">
	<link rel="stylesheet" href="${ctxStatic}/modules/orderFullAction/order_find.css">
	<script>var baseUrl = '${ctx}'</script>
	<script type="text/javascript">
		//格式化日期,
	    function formatDate(date,format){
		  if(date == null || date == undefined){
			  return "";
		  }
	      var paddNum = function(num){
	        num += "";
	        return num.replace(/^(\d)$/,"0$1");
	      }
	      var date = new Date(date);
	      //指定格式字符
	      var cfg = {
	         yyyy : date.getFullYear() //年 : 4位
	        ,yy : date.getFullYear().toString().substring(2)//年 : 2位
	        ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
	        ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
	        ,d  : date.getDate()   //日 : 如果1位的时候不补0
	        ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
	        ,hh : paddNum(date.getHours())  //时
	        ,mm : paddNum(date.getMinutes()) //分
	        ,ss : paddNum(date.getSeconds()) //秒
	      }
	      format || (format = "yyyy-MM-dd hh:mm:ss");
	      return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
   		}
		
		function prePmSettleFinanceClick(obj){
			var cutContentCont = $(obj).parent().find('.style_hide_cont');
			if(cutContentCont.is(":visible")) {
			    $(obj).find('.icon_arrow').removeClass('icon_arrow_up').addClass('icon_arrow_down');
			    $(obj).parent().find(".style_hide_cont").hide();
			}else {
				var orderId = $("#orderId").val();
				var orderMark = $("#orderMark").val();
				
				if(orderId=="" || orderId=="0" || undefined ==orderId || orderMark=="0"){
					$("#alertRemarks").text("请先查询订单信息");
		    		$("#subReport").show();
					return;
				}
				 
				$(obj).find('.icon_arrow').removeClass('icon_arrow_down').addClass('icon_arrow_up');
		        $(obj).parent().find(".style_hide_cont").show();
		        $.ajax({
		        	url:"${ctx}/selectFullOrder/selectFullOrder/queryPrePmSettleFinance?orderId="+orderId,
		            type:"post",
		            success:function(data){
		            	if(data != null && data != "" && data.length > 0){
		            		var html="";
		            		for(var i = 0 ;i < data.length; i++){
		            			if(data[i].receiveMoneyType == "1"){//二期款
		            				html+="<tr><td>二期款</td><td>"+formatDate(data[i].deptMoneyDate,"yyyy-MM-dd hh:mm:ss")+"</td><td>"+formatDate(data[i].receiveMoneyDatetime,"yyyy-MM-dd hh:mm:ss")+"</td></tr>";
		            			}else if(data[i].receiveMoneyType == "2"){//尾款
		            				html+="<tr><td>尾款</td><td></td><td>"+formatDate(data[i].receiveMoneyDatetime,"yyyy-MM-dd hh:mm:ss")+"</td></tr>";
		            			}
		            		}
		            		 $("#prePmSettleFinanceTbodyId").html(html);
		            	}else{
		            		$("#alertRemarks").text("该订单没有款项信息");
				    		$("#subReport").show();
		            	}
		            }
		        });
			   
			}
		}
	</script>
	<style type="text/css">
		.undis{display:none;}
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 380px;border-radius: 4px;background: #fff;font-size: 16px;
		    position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		.maskBtn{display: block;width: 60%;text-align: center;line-height: 38px;}
		.maskBtn{background: #0780ec;border-radius: 4px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16px;margin: 0 auto;}
	</style>

</head>
<body>
	<div class="packagesChoice_warp">

		<div class="packagesChoice_warpper">
			<div class="order_top_info">
				<p>
					<span>门店：</span>
					<select id="storeId" onchange="changeMark()">
						<c:forEach items="${fns:getStoreList()}" var="storeItems">
							<option value="${storeItems.value}">${storeItems.label}</option>
						</c:forEach>
					</select>
				</p>
				<p><span>订单编号：</span> <input type="text" name="" id="orderNumber" onchange="changeMark()"></p>
				
				<!-- 隐藏域 -->
				<input type="text" hidden="hidden" id="orderId" > 
				<input type="text" hidden="hidden" id="orderMark" value="0"> 
				
				<p>
					<input type="button" name="" value="查询" class="findCont" onclick="orderDetail()">
					<input type="button" name="" value="导出excel" class="cameExcel" onclick="exportExcel()">
				</p>
			</div>
			<div class="packagesChoice_content">
				<div class="basic">
					<h2 class="col_blue mb20 mt33"><i class="icon_line"></i> <p class="pl16">基本信息</p></h2>
					<div class="basic_contentAdd" id="order">
						
					</div>
				</div>
				<div class="basic">
					<h2 class="col_blue mb20 mt33 border-1px padding-6px pos_r" id="budgetClick"></i><p class="pl16">接单派单做预算</p> <i class="icon_arrow_down icon_arrow"></i></h2>
					<div class="process_warp style_hide_cont" id="budget">
						<div class="basic_content basic_contentAdd">
							<ul class="clearfix">
								<li class="fl"><span>录入系统日期 :</span><span class="ml12" id="t1"></span></li>
								<li class="fl ml134"><span>操作人 :</span><span class="ml12" id="t2"></span></li>
							</ul>
							<ul class="clearfix info_cont">
								<li class="fl"><span>接单日期 :</span><span class="ml12" id="t3"></span></li>
							</ul>
							<ul class="clearfix">
								<li class="fl"><span>生成任务包日期 :</span><span class="ml12" id="t4"></span></li>
								<li class="fl ml134"><span>操作人 :</span><span class="ml12" id="t5"></span></li>
							</ul>
							<ul class="clearfix">
								<li class="fl"><span>分配项目经理时间 :</span><span class="ml12" id="t6"></span></li>
								<li class="fl ml134"><span>操作人 :</span><span class="ml12" id="t7"></span></li>
							</ul>
							<ul class="clearfix">
								<li class="fl"><span>分配质检员时间 :</span><span class="ml12" id="t8"></span></li>
								<li class="fl ml134"><span>操作人 :</span><span class="ml12" id="t9"></span></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="basic">
					<h2 class="col_blue mb20 mt33 border-1px padding-6px pos_r" id="disclosureClick"></i><p class="pl16">交底开工</p> <i class="icon_arrow_down icon_arrow"></i></h2>
					<div class="process_warp style_hide_cont" id="disclosure">
						<div class="basic_content basic_contentAdd">
							<ul class="clearfix">
								<li class="fl"><span>交底日期 :</span><span class="ml12" id="discloseDate"></span></li>
								<li class="fl ml134"><span>实际开工日期 :</span><span class="ml12" id="actualStartDate"></span></li>
							</ul>
							
							<ul class="clearfix">
								<li class="fl"><span>客户原因延期天数 :</span><span class="ml12" id="selfDecorateDelayDays"></span></li>
								<li class="fl ml134"><span>合同开工日期 :</span><span class="ml12" id="contractStartDate"></span></li>
							</ul>
							<ul class="clearfix info_cont">
								<li class="fl"><span>客户是否签字 :</span><span class="ml12" id="isNeedSign"></span></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="order_content_warp">
				<h2 class="col_blue mb20 mt33 border-1px padding-6px pos_r" onclick="purchaseDetail(this)"><p class="pl16">基装阶段：材料部分</p><i class="icon_arrow_down icon_arrow"></i></h2>
				<div class="order_content style_hide_cont">
					<table>
						<caption class="tl">【辅料】</caption>
						<thead>
							<th>申请顺序</th>
							<th>当前状态</th>
							<th>提交申请时间</th>
							<th>期望到货日期</th>
							<th>收货日期</th>
						</thead>
						<tbody id="purchaseMaterial">
							
						</tbody>
					</table>
					<table>
						<caption class="tl">【墙地砖】</caption>
						<thead>
							<th>申请顺序</th>
							<th>当前状态</th>
							<th>提交申请时间</th>
							<th>期望到货日期</th>
							<th>收货日期</th>
							<th>催单次数</th>
						</thead>
						<tbody  id="purchaseWallAndFloor">
							
						</tbody>
					</table>
					<table>
						<caption class="tl">【开关面板】</caption>
						<thead>
							<th>申请顺序</th>
							<th>当前状态</th>
							<th>提交申请时间</th>
							<th>期望到货日期</th>
							<th>收货日期</th>
						</thead>
						<tbody id="purchaseSwitchPanel">
							
						</tbody>
					</table>
				</div>
			</div>
			<div class="order_content_warp">
				<h2 class="col_blue mb20 mt33 border-1px padding-6px pos_r" onclick="pqcDoneInfo(this)"></i><p class="pl16">基装阶段：质检部分</p> <i class="icon_arrow_down icon_arrow"></i></h2>
				<div class="order_content style_hide_cont">
					<table>
						<thead>
							<th>约检节点</th>
							<th>项目经理提报申请时间</th>
							<th>项目经理申请质检员上门日期 </th>
							<th>质检员实际上门日期</th>
							<th>质检员确认节点验收通过日期 </th>
						</thead>
						<tbody id="pqcCheck">

						</tbody>
					</table>
				</div>
				

			</div>
			<div class="order_content_warp">
				<h2 class="col_blue mb20 mt33 border-1px padding-6px pos_r" id="projectChangeBillClick"></i><p class="pl16">基装阶段：变更</p> <i class="icon_arrow_down icon_arrow"></i></h2>
				<div class="order_content style_hide_cont" id="projectChangeBill">
					<table>
						<thead>
							<th>变更单编号</th>
							<th>提交变更时间</th>
							<th>设计师审核时间</th>
							<th>审计部审核时间 </th>
						</thead>
						<tbody id="tbodyId">
							
						</tbody>
					</table>
				</div>

			</div>
		
			<div class="order_content_warp">
				<h2 class="col_blue mb20 mt33 border-1px padding-6px pos_r" onclick="prePmSettleFinanceClick(this)"></i><p class="pl16">基装阶段：款项</p> <i class="icon_arrow_down icon_arrow"></i></h2>
				<div class="order_content style_hide_cont">
					<table>
						<thead>
							<th>交款批次</th>
							<th>催款时间</th>
							<th>客户交款日期 </th>
						</thead>
						<tbody id="prePmSettleFinanceTbodyId">
							<!-- <tr>
								<td>二期款</td>
								<td>2017-5-1 10:20:30</td>
								<td>2017-5-1 10:20:30</td>							
							</tr>
							<tr>
								<td>尾款</td>
								<td>2017-5-1 10:20:30</td>
								<td>2017-5-1 10:20:30</td>							
							</tr> -->
						</tbody>
					</table>	
				</div>
				
			</div>
			<div class="order_content_warp">
				<h2 class="col_blue mb20 mt33 border-1px padding-6px pos_r" onclick="installDetail(this)"></i><p class="pl16">安装阶段</p> <i class="icon_arrow_down icon_arrow"></i></h2>
				<div class="order_content style_hide_cont">
					<table>
						<thead>
							<th>安装项目</th>
							<th>项目经理提交申请时间</th>
							<th>项目经理期望到场日期 </th>
							<th>供应商答复到场日期</th>
							<th>实际进场日期</th>
							<th>实际完工日期</th>
							<th>项目经理验收日期</th>
							<th>项目经理问题反馈次数</th>
							<th>项目经理问题反馈影响工期</th>
						</thead>
						<tbody id="install">
							
						</tbody>
					</table>
				</div>

			</div>
			
		</div>
		
	</div>
	
	
	<!-- 提示弹框 -->
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarks"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="hideMessageInfo()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div> 
	
	
</body>

<script type="text/javascript" src="${ctxStatic}/modules/orderFullAction/jquery.js"></script>
<script type="text/javascript" src="${ctxStatic}/modules/orderFullAction/order_find.js"></script>
<script type="text/javascript" src="${ctxStatic}/modules/orderFullAction/ajaxJs.js"></script>
<script type="text/javascript" src="${ctxStatic}/modules/orderFullAction/order_ajax_js.js"></script>
<script type="text/javascript" src="${ctxStatic}/modules/orderFullAction/orderFull.js"></script>
</html>