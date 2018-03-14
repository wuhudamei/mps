$(function () {
	// 点击控制显示隐藏
	var bxyOrder = {
			//派单接单
		cutContent : function (clickBtn) {
			clickBtn.on('click',function () {
				
				var cutContentCont = $(this).parent().find('.style_hide_cont');
				//cutContentCont.toggle();
				if(cutContentCont.is(":visible")) {
					
					$(this).find('.icon_arrow').removeClass('icon_arrow_up').addClass('icon_arrow_down');
					$(this).parent().find(".style_hide_cont").hide();
				}else {
					
					var orderId = $("#orderId").val();
					var orderMark = $("#orderMark").val();
					
					if(orderId=="" || orderId=="0" || undefined ==orderId || orderMark=="0"){
						$("#alertRemarks").text("请先查询订单信息");
			    		$("#subReport").show();
						return;
					}
					
					if(orderMark == '1' && orderId != null){
						
						var param={orderId:orderId};
						//var url ='budget';
						
						
						$.ajax({
				            url: 'budget',
				            type: "post",
				            data:param,
				            dataType:"json",
				            success: function(data){
				            	
					            if(null!=data){
					            	
					            	//判断是否不是一个数字
									if(isNaN(data.enteringDate)){
										$("#t1").html('');
									}else{
									//录入系统时间
										$("#t1").html(formatDate(new Date(parseInt(data.enteringDate)).toString(),"yyyy-MM-dd hh:mm:ss"))
									}
									$("#t2").html(data.keyboarder)
									
									if(isNaN(data.receivingDate)){
										$("#t3").html('');
									}else{
										$("#t3").html(formatDate(new Date(parseInt(data.receivingDate)).toString(),"yyyy-MM-dd"));
									}
									
									if(isNaN(data.packageDate)){
										$("#t4").html('');
									}else{
										$("#t4").html(formatDate(new Date(parseInt(data.packageDate)).toString(),"yyyy-MM-dd hh:mm:ss"));
									}
									$("#t5").html(data.packageBy);
									
									if(isNaN(data.assignedManagerDate)){
										$("#t6").html('');
									}else{
										$("#t6").html(formatDate(new Date(parseInt(data.assignedManagerDate)).toString(),"yyyy-MM-dd hh:mm:ss"));
									}
									$("#t7").html(data.assignedManagerBy);
									
									if(isNaN(data.assignedInspectorDate)){
										$("#t8").html('');
									}else{
										$("#t8").html(formatDate(new Date(parseInt(data.assignedInspectorDate)).toString(),"yyyy-MM-dd hh:mm:ss"));
									}
									
									$("#t9").html(data.assignedInspectorBy);
									
									$(this).find('.icon_arrow').removeClass('icon_arrow_down').addClass('icon_arrow_up');
				    				
									$("#budget").show();
					            	
					            }else{
					            	$("#alertRemarks").text("该订单没有派单接单信息");
				            		$("#subReport").show();
					            }
				            }
				            });
						
						};
						
				};
			});
			
		},
		//交底
		disclosureStarts : function (clickBtn) {
			clickBtn.on('click',function () {
				
				var cutContentCont = $(this).parent().find('.style_hide_cont');
				//cutContentCont.toggle();
				if(cutContentCont.is(":visible")) {
					
					$(this).find('.icon_arrow').removeClass('icon_arrow_up').addClass('icon_arrow_down');
					$(this).parent().find(".style_hide_cont").hide();
				}else {
					
					var orderId = $("#orderId").val();
					var orderMark = $("#orderMark").val();
					
					if(orderId=="" || orderId=="0" || undefined ==orderId || orderMark=="0"){
						$("#alertRemarks").text("请先查询订单信息");
			    		$("#subReport").show();
						return;
					}
					
					if(orderMark == '1' && orderId != null){
						
						var param={orderId:orderId};
						//var url ='disclosureStarts';
						
						$.ajax({
				            url: 'disclosureStarts',
				            type: "post",
				            data:param,
				            dataType:"json",
				            success: function(data){
				            	
					            if(null!=data){
					            	
					            	$("#discloseDate").html(data.discloseDate);
									
									if(isNaN(data.actualStartDate)){
										$("#actualStartDate").html('');
									}else{
										$("#actualStartDate").html(formatDate(new Date(parseInt(data.actualStartDate)).toString(),"yyyy-MM-dd hh:mm:ss"));
									}
									
									$("#selfDecorateDelayDays").html(data.selfDecorateDelayDays);
									
									if(isNaN(data.contractStartDate)){
										$("#contractStartDate").html('');
									}else{
										$("#contractStartDate").html(formatDate(new Date(parseInt(data.contractStartDate)).toString(),"yyyy-MM-dd hh:mm:ss"));
									}
									
									if(data.isNeedSign == 1){
										$("#isNeedSign").html('是');
									} else if(data.isNeedSign == 0){
										$("#isNeedSign").html('否')
									}
									
									$(this).find('.icon_arrow').removeClass('icon_arrow_down').addClass('icon_arrow_up');
									$("#disclosure").show();
					            	
					            }else{
					            	
					            	$("#alertRemarks").text("该订单没有交底信息");
				            		$("#subReport").show();
					            }
				            }
				           
					     });    
						
					}
				};
			});
			
		},
		
		//基装变更
		projectChangeBill : function (clickBtn) {
			clickBtn.on('click',function () {
				
				var cutContentCont = $(this).parent().find('.style_hide_cont');
				//cutContentCont.toggle();
				if(cutContentCont.is(":visible")) {
					
					$(this).find('.icon_arrow').removeClass('icon_arrow_up').addClass('icon_arrow_down');
					$(this).parent().find(".style_hide_cont").hide();
				}else {
					
					var orderId = $("#orderId").val();
					var orderMark = $("#orderMark").val();
					
					if(orderId=="" || orderId=="0" || undefined ==orderId || orderMark=="0"){
						$("#alertRemarks").text("请先查询订单信息");
			    		$("#subReport").show();
						return;
					}
															
					if(orderMark == '1' && orderId != null){
						
						var param={orderId:orderId};
						//var url ='projectChangeBill';
						
						
						$.ajax({
				            url: 'projectChangeBill',
				            type: "post",
				            data:param,
				            dataType:"json",
				            success: function(data){
				            	
					            if(null!=data && data.length != 0){
					            	
					            	$.each(data,function(n,value){
										var $tr = $("<tr></tr>");
										var $td=$("<td></td><td></td><td></td><td></td>");
										
										var trd=$tr.append($td);
										
										$("#tbodyId").append(trd);
										
										$("#tbodyId").children('tr').eq(n).children('td').eq(0).html(value.projectChangeBillCode);
										$("#tbodyId").children('tr').eq(n).children('td').eq(1).html(value.applyDate);
										if(isNaN(value.stylistCheckDate)){
											$("#tbodyId").children('tr').eq(n).children('td').eq(2).html('');
										}else{
											$("#tbodyId").children('tr').eq(n).children('td').eq(2).html(formatDate(new Date(parseInt(value.stylistCheckDate)).toString(),"yyyy-MM-dd hh:mm:ss"));
										}
										
										if(isNaN(value.auditCheckDate)){
											$("#tbodyId").children('tr').eq(n).children('td').eq(3).html('');
										}else{
											$("#tbodyId").children('tr').eq(n).children('td').eq(3).html(formatDate(new Date(parseInt(value.auditCheckDate)).toString(),"yyyy-MM-dd hh:mm:ss"));
										}
										
										$(this).find('.icon_arrow').removeClass('icon_arrow_down').addClass('icon_arrow_up');
										$("#projectChangeBill").show();
									});
					            
					            	
					            }else{
					            	$("#alertRemarks").text("该订单没有基装变更信息");
				            		$("#subReport").show();
					            };
				            }
						});
					}
				};
			});
			
		}
	};
	bxyOrder.cutContent($('#budgetClick'));
	bxyOrder.disclosureStarts($('#disclosureClick'));
	bxyOrder.projectChangeBill($('#projectChangeBillClick'));
})