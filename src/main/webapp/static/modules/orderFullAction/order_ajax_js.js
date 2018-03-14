
//1.隐藏弹框
function hideMessageInfo(){
	$("#subReport").hide();
}

//2.当门店和订单编号改变时，清除标记，并清空加载内容
function changeMark(){
	//2-1.订单id和标志还原
	var num = 0;
	$("#orderId").val(num);
	$("#orderMark").val(num);
	//2-2.全部隐藏
	$('.icon_arrow').removeClass('icon_arrow_up').addClass('icon_arrow_down');
    $(".style_hide_cont").hide();
    //2-3.清除之前的数据
    var html = '';
    //基本信息
    $("#order").html(html);
    //接单派单做预算
    //交底开工
    //基装阶段：材料部分
    $("#purchaseMaterial").html(html);
    $("#purchaseWallAndFloor").html(html);
    $("#purchaseSwitchPanel").html(html);
    //基装阶段：质检部分
    $("#pqcCheck").html(html);
    //基装阶段：变更
    $("#tbodyId").html(html);
    //基装阶段：款项
    $("#prePmSettleFinanceTbodyId").html(html);
    //安装阶段
    $("#install").html(html);
}

//3.动态加载订单信息
function orderDetail(){
	
	var storeId = $("#storeId").val();
	var orderNumber = $.trim($("#orderNumber").val()); 
	if(storeId ==""){
		$("#alertRemarks").text("请选择门店");
		$("#subReport").show();
		return;
	}
	if(orderNumber ==""){
		$("#alertRemarks").text("请输入订单编号");
		$("#subReport").show();
		return;
	}
	
	var sectionObj=$("#order");
	
	param={
			storeId:storeId,
			orderNumber:orderNumber
	}
    html="";    
	$(sectionObj).html(html);
	
    $.ajax({
    	
        url: baseUrl+"/selectFullOrder/selectFullOrder/orderDetail",
        type: "post",
        data:param,
        success: function(data){
        	
        	if(null!=data && null!=data.orderId && data.orderId!=""){
        		
        		$("#orderId").val(data.orderId);
        		$("#orderMark").val("1");
        		
        		html += '<ul class="clearfix">'+
							'<li class="fl"><span>门店 :</span><span class="ml12">'+data.storeName+'</span></li>'+
							'<li class="fl ml134"><span>订单编号 :</span><span class="ml12">'+data.orderNumber+'</span></li>'+
						'</ul>'+
						'<ul class="clearfix">'+
							'<li class="fl"><span>客户姓名 :</span><span class="ml12">'+data.customerName+'</span></li>'+
							'<li class="fl ml134"><span>客户手机 :</span><span class="ml12">'+data.customerPhone+'</span></li>'+
						'</ul>'+
						'<ul class="clearfix info_cont">'+
							'<li class="fl"><span>客户地址 :</span><span class="ml12">'+data.communityName+'-'+data.buildNumber+'-'+data.buildUnit+'-'+data.buildRoom+'</span></li>'+
						'</ul>'+
						'<ul class="clearfix">';
						
				if(null!=data.itemManagerId && data.itemManagerId!="" && data.itemManagerId!='undefined'){
					html += '<li class="fl"><span>项目经理/手机号 :</span><span class="ml12">'+data.itemManagerName+'/'+data.itemManagerPhone+'</span></li>';
				}else{
					html += '<li class="fl"><span>项目经理/手机号 :</span><span class="ml12">/</span></li>';
				}
				if(null!=data.orderInspectorId && data.orderInspectorId!="" && data.orderInspectorId!='undefined'){
					html +=	'<li class="fl ml134 w44"><span class="fl">质检员/手机号 :</span><span class="ml12">'+data.orderInspectorName+'/'+data.orderInspectorPhone+'</span></li>';
				}else{
					html +=	'<li class="fl ml134 w44"><span class="fl">质检员/手机号 :</span><span class="ml12">/</span></li>';
				}
							
				
				html += '</ul>'+
						'<ul class="clearfix">'+
							'<li class="fl"><span class="fl">设计师/手机号 :</span><span class="ml12 ">'+data.designerName+'/'+data.designerPhone+'</span></li>'+
							'<li class="fl w44 ml134"><span class="fl">客服经理/手机号 :</span><span class="ml12 ">'+data.serviceName+'/'+data.servicePhone+'</span></li>'+
						'</ul>';
						
				$(sectionObj).html(html);
				
        	}else{
        		var num = 0;
        		$("orderId").val(num);
        		$("#orderMark").val(num);
        		
        		$("#alertRemarks").text("该订单不存在，请重新输入订单编号");
        		$("#subReport").show();
        		
        	}
        	
        }
    })
	
}




//4.基装阶段：材料部分
function purchaseDetail(obj){
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
		 
		
        param={
        		orderId:orderId
    	}
        $.ajax({
            url: baseUrl+"/selectFullOrder/selectFullOrder/purchaseDetail",
            type: "post",
            data:param,
            success: function(data){
            	
            	var purchaseCount1 = 0;
    			var html1='';
    			var purchaseCount2 = 0;
    			var html2='';
    			var purchaseCount3 = 0;
    			var html3='';
    			
            	if(null!=data && data.length > 0){
            		for(var v=0;v<data.length;v++){
            			
            			if(data[v].purchaseType=="1"){
            				purchaseCount1++;
            				html1 += '<tr>'+
										'<td>'+purchaseCount1+'</td>'+
										'<td>'+data[v].purchaseStatusName+'</td>'+
										'<td>'+data[v].applyTime+'</td>'+
										'<td>'+data[v].applyReceiveTime+'</td>'+
			            				'<td>'+data[v].receiveDate+'</td>'+
			            			'</tr>';
										
									
            			}
            			if(data[v].purchaseType=="2"){
            				purchaseCount2++;
            				html2 += '<tr>'+
			            				'<td>'+purchaseCount2+'</td>'+
			            				'<td>'+data[v].purchaseStatusName+'</td>'+
			            				'<td>'+formatDate(data[v].applyTime,'yyyy-MM-dd hh:mm:ss')+'</td>'+
			            				'<td>'+data[v].applyReceiveTime+'</td>'+
			            				'<td>'+data[v].receiveDate+'</td>'+
			            			'</tr>';
            			}
            			if(data[v].purchaseType=="5"){
            				purchaseCount3++;
            				html3 += '<tr>'+
			            				'<td>'+purchaseCount3+'</td>'+
			            				'<td>'+data[v].purchaseStatusName+'</td>'+
			            				'<td>'+formatDate(data[v].applyTime,'yyyy-MM-dd hh:mm:ss')+'</td>'+
			            				'<td>'+data[v].applyReceiveTime+'</td>'+
			            				'<td>'+data[v].receiveDate+'</td>'+
			            				'<td>'+data[v].urgeCount+'</td>'+
			            			'</tr>';
            			}
            			
            		}
					
            		
            		$("#purchaseMaterial").html(html1);
                	$("#purchaseWallAndFloor").html(html3);
    				$("#purchaseSwitchPanel").html(html2);
    				$(obj).find('.icon_arrow').removeClass('icon_arrow_down').addClass('icon_arrow_up');
    				$(obj).parent().find(".style_hide_cont").show();
    				
            	}else{
            		
            		$("#alertRemarks").text("该订单没有材料信息");
            		$("#subReport").show();
            		
            	}
            	
				
            }
        })
        
        
		
	}
	
}


//4.安装阶段
function installDetail(obj){
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
		
		
		param={
				orderId:orderId
		}
		$.ajax({
			url: baseUrl+"/selectFullOrder/selectFullOrder/installDetail",
			type: "post",
			data:param,
			success: function(data){
				
				var html = '';
				if(null!=data && data.length > 0){
					for(var v=0;v<data.length;v++){
						html += '<tr>'+
									'<td>'+data[v].installItemName+'</td>'+
									'<td>'+data[v].applyIntoCreateDatetime+'</td>'+
									'<td>'+data[v].applyIntoDate+'</td>'+
									'<td>'+data[v].supplierConfirmIntoDate+'</td>'+
									'<td>'+data[v].realIntoDate+'</td>'+
									'<td>'+data[v].realCompleteDate+'</td>'+
									'<td>'+data[v].realAcceptDate+'</td>'+
									'<td>'+data[v].problemCount+'</td>'+
									'<td>'+data[v].delayDays+'</td>'+
								'</tr>';
					}
					
					$("#install").html(html);
					$(obj).find('.icon_arrow').removeClass('icon_arrow_down').addClass('icon_arrow_up');
					$(obj).parent().find(".style_hide_cont").show();
					
					
				}else{
					
					$("#alertRemarks").text("该订单没有安装项信息");
					$("#subReport").show();
					
				}
				
			}
		})
		
		
	}
	
}

//5.导出
function exportExcel(){
	
	var orderId = $("#orderId").val();
	var orderMark = $("#orderMark").val();
	
	if(orderId=="" || orderId=="0" || undefined ==orderId || orderMark=="0"){
		$("#alertRemarks").text("请先查询订单信息");
		$("#subReport").show();
		return;
	}
	
	window.location.href = baseUrl+"/selectFullOrder/selectFullOrder/exportExcel?orderId="+orderId;
}



function getRootPath(){
    //获取当前网址，如： http://localhost:8088/test/test.jsp
    var curPath=window.document.location.href;
    //获取主机地址之后的目录，如： test/test.jsp
    var pathName=window.document.location.pathname;
    var pos=curPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8088
    var localhostPaht=curPath.substring(0,pos);
    //获取带"/"的项目名，如：/test
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}