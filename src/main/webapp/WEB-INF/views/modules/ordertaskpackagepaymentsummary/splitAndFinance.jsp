<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<title>拆分并导出财务excel</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		
		$(document).ready(function() {
			
			
		});
		/* function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        } */
	</script>
	
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/list">付款批次列表</a></li>
		<li class="active"><a href="javascript:void(0)">拆分并导出财务excel</a></li>
	</ul>
	<div class="breadcrumb form-search">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<tr >
				<td style="text-align:center" colspan="3">
					<font><h3>基本信息</h3></font>
				</td>
			</tr>
			<tr>
				<input type="hidden" id="summaryId" value="${summary.id }">
				<td>付款批次编号:${summary.orderTaskpackagePaymentSummaryCode }</td>
				<td>打款工人数量:${size }</td>
				<td>操作人:${summary.applyEmployeeName }</td>
			</tr>
			<tr>
				<td>付款单数量:${summary.orderTaskpackagePaymentCount }</td>
				<td>打款总金额:${totalMoney }</td>
				<td></td>
			</tr>
		</table>
	</div>
	
	<form id="searchForm" modelAttribute="bizOrderTaskpackagePaymentDetailSplitVo" action="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/splitSave" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<input type="hidden" id="splitsummaryId" value="${summary.id }" name="splitsummaryId"/>
			<li class="btns"><input class="btn btn-primary" type="button" value="提交" onclick="tijiao()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出财务Excel" onclick="exportExcel()"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="拆分" /></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="返回" onclick="history.go(-1)"/></li>
			<li class="clearfix"></li>
		</ul>
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>付款单编号 </th>
					<th>工人姓名</th>
					<th>身份证</th>
					<th>打款银行卡号</th>
					<th>付款金额</th>
					<th>关联姓名</th>
					<th>关联身份证号</th>
					<th>打款金额</th>
					<th>本月剩余打款金额</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${splits }" var ="bizOrderTaskpackagePaymentDetailSplitVo" varStatus="status">
					<tr>
						<td>
							${bizOrderTaskpackagePaymentDetailSplitVo.paymentCode }
						</td>
						<td>
							${bizOrderTaskpackagePaymentDetailSplitVo.salaryEmployeeName }
						</td>
						<td>
							${bizOrderTaskpackagePaymentDetailSplitVo.salaryEmployeeIdcardNo }
						</td>
						<td>
							${bizOrderTaskpackagePaymentDetailSplitVo.salaryEmployeeBankcard }
						</td>
						<td id="payAmountTotal${status.index+1 }">
							${bizOrderTaskpackagePaymentDetailSplitVo.payAmountTotal }
						</td>
						<td>
							${bizOrderTaskpackagePaymentDetailSplitVo.relatedName }
						</td>
						<td id="relatedIdcardNo${status.index+1}">
							${bizOrderTaskpackagePaymentDetailSplitVo.relatedIdcardNo }
						</td>
						<td>
							<input type="hidden" name="splitId" value="${bizOrderTaskpackagePaymentDetailSplitVo.id }"/>
							<input type="text" name="splitMoney" value="${bizOrderTaskpackagePaymentDetailSplitVo.payAmountSplit }" id="splitMoney${status.index+1}" data-option="${bizOrderTaskpackagePaymentDetailSplitVo.orderTaskpackagePaymentDetailId}" onchange="updateMoney('${status.index+1 }')"/>
						</td>
						<td id="tdRestMoney${status.index+1}">
							<%-- <fmt:formatNumber value="${bizOrderTaskpackagePaymentDetailSplitVo.restMoney }" pattern="#.00"/> --%>
							<input type="text" id="restMoney${status.index+1}" value="${bizOrderTaskpackagePaymentDetailSplitVo.restMoney }" readonly="readonly"/>
							<%-- <input type="hidden" id="restMoney${status.index+1}" value="${bizOrderTaskpackagePaymentDetailSplitVo.restMoney }"/> --%>
						</td>
					</tr>
				</c:forEach> 
			</tbody>
		</table>
	</form>
	<div class="table-mask" style="padding: 50px 15px;box-sizing: border-box;width:100%;height:100%;position:absolute;top:10%;left:0;background:#ccc;display:none;">
		<a class="shutBtn" style="position:absolute;top:10px;right:20px;background:#0780ec;padding: 5px 15px;border-radius: 4px;color: #fff;font-size: 14px;text-decoration:none;" href="javascript:;">关闭</a>
		<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th style="text-align: center;">工人姓名 </th>
						<th style="text-align: center;">打款总额</th>
						<th style="text-align: center;">关联身份证本月剩余总额</th>
						<th style="text-align: center;">关联身份证差额</th>
						<th style="text-align: center;">需要关联身份证</th>
					</tr>
				</thead>
				<tbody id="relateMessage">
					<%--<c:forEach items="${splits }" var ="bizOrderTaskpackagePaymentDetailSplitVo" varStatus="status">
						<tr>
							<td style="text-align: center;">
								111
							</td>
							<td style="text-align: center;">
								222
							</td>
							<td style="text-align: center;">
								333
							</td>
							<td style="text-align: center;">	
								444
							</td>
							<td style="text-align: center;">
								555
							</td>
						</tr>
					</c:forEach> --%>
				</tbody>
			</table>
		</div>
	<script type="text/javascript">
		function exportExcel(){
	    	var summaryId = $("#summaryId").val();
	    	window.location.href = "${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/exportFinanceExcel?id="+summaryId;
	    }
		
		function tijiao(){
        	var length=${fn:length(splits)};
        	var a='';
        	for(var i=1;i<=length;i++){
        		var amount = $("#splitMoney"+i).val();
        		var detailId = $("#splitMoney"+i).attr("data-option");
        		if(a==''){
        			a =detailId+"-"+amount;
        		}else{
        			a =a+","+detailId+"-"+amount;
        		}
        	}
        	$.post("${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/splitCheck",{a:a},function(data){
        		//alert(data);
        		if(data == '1' ){
        			$("#searchForm").submit();
        		}else{
        			alertx("分配打款金额有误！请仔细审核后再提交！")
        		}
        	});
        }

		function updateMoney(index){
			//alert(index);
			var money = $("#splitMoney"+index).val();//打款金额
			alert(money+"aaa");
			var idcard = $("#relatedIdcardNo"+index).text();//关联的身份证id
			var restMoney = Number($("#restMoney"+index).val());//当前剩余打款金额
			alert(restMoney+"bbb");
			//var money1 = Number(restMoney-money);
			alert(Number(restMoney-money).toFixed(2)+"ccc");
			var length = ${fn:length(splits)};
			index = Number(index);
			for(var i = index+1; i<= length;i++){
				if(idcard == $("#relatedIdcardNo"+i).text()){
					$("#restMoney"+i).val(Number(restMoney-money).toFixed(2));
					money = Number($("#splitMoney"+i).val());
					restMoney = Number($("#restMoney"+i).val())
					//money1 = Number(restMoney-money);
				}
			}
		} 
		$(document).on('click','.shutBtn',function(){
			$('.table-mask').css({'display':'none'});
		});
		$(document).on('click','#btnSubmit',function(){
			var length = ${fn:length(splits)};
			if(length>0){
				alertx("该批次已经拆分，请不要重复拆分！");
				return;
			}
			$(this).attr("disabled", true);
			loading('正在拆分，请稍等...');
			$.post("${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/splitSummary",{id:${summary.id }},function(result){
				if(result.status == 1){
					closeTip();
					window.location.href = "${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/splitAndFinance?id=${summary.id }";
				}else if(result.status == 2){
					$(this).removeAttr("disabled");
					closeTip();
					var content = "";
					for(var i=0;i<result.data.length;i++){
						content = content + "<tr><td style='text-align: center;'>"+result.data[i].realName+"</td>" +
						"<td style='text-align: center;'>"+result.data[i].splitAmountTotal+"</td>" +
						"<td style='text-align: center;'>"+result.data[i].relateRemainAmount+"</td>" +
						"<td style='text-align: center;'>"+result.data[i].relateDiffAmount+"</td>" +
						"<td style='text-align: center;'>"+result.data[i].relateCount+"</td></tr>";
					}
					$("#relateMessage").html(content);
					$('.table-mask').show();
				}else if(result.status == 3){
					closeTip();
					var content = "";
					for(var i=0;i<result.data.length;i++){
						content = content + "【"+result.data[i]+"】";
					}
					$(this).removeAttr("disabled");
					alertx("工人"+content+"没有添加银行卡，请添加银行卡并关联身份证");
				}else{
					closeTip();
					alertx("拆分失败！！！！");
				}
			});
		});
	</script>
</body>
</html>