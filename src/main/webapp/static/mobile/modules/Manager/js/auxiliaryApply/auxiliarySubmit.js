
function run_waitMe(effect) {
    $('#aboveId').waitMe({
        effect: effect,
        text: '经理稍等~正在拼命提交您的辅料..',
        bg: 'rgba(255,255,255,0.7)',
        color: '#000',
        sizeW: '',
        sizeH: '',
        source: 'img.svg'
    });
}

var orderId = $("#orderId").val();

//图片没有 就使用默认图片
function nofind(){
	var img=event.srcElement;
	img.src="/static/mobile/modules/Manager/css/photo/auxiliaryPhotoForNothing.png";
	img.onerror=null; 
} 

//继续添加辅料
function goOnForAuxiliary(){
	run_waitMe("win8","拼命加载商品,请稍等...")
	window.location.href = baseUrl+"/app/manager/auxiliary/goOnChoose?orderId="+orderId;
}

//删除辅料
function delteAuxiliary(auxiliaryCode,price,count,id){
	if(auxiliaryCode==""){
		$("#alertRemarks").text("没有编号");
		$("#alert").show();
		return;
	}
	$.ajax({
		url: baseUrl+"/app/manager/auxiliary/deleteAuxiliary?auxiliaryCode="+auxiliaryCode+"&orderId="+orderId,
		type: "get",
		dataType: "json",
		success: function(data){
			if(data=="1"){
				$("#"+auxiliaryCode).remove();
				var s=0;
				var c = 0;
				$(".buy li").each(function(){
					var num =	parseInt($(this).find('input[class*=mui-numbox-input]').val());
					if(isNaN(num)){
						num=0;
					}
					if(num>999){
						num=999
					}
					var price = parseFloat($(this).find('span[class*=col_red]').text());
					$(this).find('input[class*=count]').val(num);
					$(this).find('span[class*=showCount]').html(num);
					$(this).find('span[class*=showTotal]').html((num*price).toFixed(2));
					c+=num;
					s+=num*price;
				});
				$("#totalMoney").html(s.toFixed(2)+"元");
				$("#total").val(s.toFixed(2));
				$("#totalCount").html("共使用"+c+"个商品");
			}
		}
	});
}

function setTotal(categoryName){
	var s=0;
	var c = 0;
	$(".buy li").each(function(){
		var num =	parseInt($(this).find('input[class*=mui-numbox-input]').val());
		if(isNaN(num)){
			num=0;
		}
		if(num>999){
			num=999
		}
		var price = parseFloat($(this).find('span[class*=col_red]').text());
		$(this).find('input[class*=count]').val(num);
		$(this).find('span[class*=showCount]').html(num);
		$(this).find('span[class*=showTotal]').html((num*price).toFixed(2));
		c+=num;
		s+=num*price;
	});
	$("#totalMoney").html(s.toFixed(2)+"元");
	$("#total").val(s.toFixed(2));
	$("#totalCount").html("共使用"+c+"个商品");
	
	var projectMode = $("#projectMode").val();
	if(projectMode == '1' || projectMode == '4'){
		var packageState = $("#packageState" + categoryName).val();
		if(packageState == "7"){
			var m = 0;
			$(".buy li").each(function(){
				var workType = $(this).find('input[name*=workType]').val();
				if(categoryName == workType){
					var num = parseInt($(this).find('input[class*=mui-numbox-input]').val());
					if(isNaN(num)){
						num=0;
					}
					if(num>999){
						num=999
					}
					var price = parseFloat($(this).find('span[class*=col_red]').text());
					$(this).find('input[class*=count]').val(num);
					m += num*price;
				}
			});
			
			var afterAmount = $("#packageAfterAmount"+categoryName).val();
 			if(!isNaN(m) && parseFloat(m.toFixed(2)) > parseFloat(afterAmount)){
 				var canApply = (parseFloat(afterAmount)-parseFloat(m)).toFixed(2);
 				if(categoryName == "0"){
 					$("#alertRemarks").text("您【水电】类辅料商品申请金额还剩余"+canApply+"元，请修改【水电】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
				}else if(categoryName == "1"){
					$("#alertRemarks").text("您【瓦】类辅料商品申请金额还剩余"+canApply+"元，请修改【瓦】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
				}else if(categoryName == "2"){
					$("#alertRemarks").text("您【木】类辅料商品申请金额还剩余"+canApply+"元，请修改【木】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
				}else{
					$("#alertRemarks").text("您【油】类辅料商品申请金额还剩余"+canApply+"元，请修改【油】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
				}
 				$("#alert").show();
 				return;
 			}
		}
	}
	
	
}

//确认用量数据校验
function pay(){
	if($("#txtBeginDate").val()==""){
		$("#alertRemarks").text("请选择期望到场日期");
		$("#alert").show();
		return;
	}
	if($('#total').val()=="0.0"){
		$("#alertRemarks").text("您还没有购买商品");
		$("#alert").show();
		return;
	}
	if($('.buy').length<1){
		$("#alertRemarks").text("您还没有购买商品");
		$("#alert").show();
		return;
	}
	var t =0;
	$(".buy li").each(function(){
		var num = parseInt($(this).find('input[class*=mui-numbox-input]').val());
		t+=num;
	});
	if(t==0){
		$("#alertRemarks").text("您还没有购买商品");
		$("#alert").show();
		return;
	}
	
	var projectMode = $("#projectMode").val();
	if(projectMode == '1' || projectMode == '4'){
		$.ajax({
	        url: baseUrl+"/app/manager/auxiliary/check_auxiliary_package_state_ajax",
	        type: "post",
	        async: false,
	        data: {
	    		orderId:orderId
	    	 },
	        success: function(data){
	        	
	        	if(null!=data && data.length>0){
	            	for(var v=0;v<data.length;v++){
	            		if(data[v].empWorkType=='0' || data[v].empWorkType=='1' || data[v].empWorkType=='2' || data[v].empWorkType=='3'){
	            			$("#packageState" + data[v].empWorkType).val(data[v].isCanApplyAuxiliary);
	            			$("#packageAfterAmount" + data[v].empWorkType).val(data[v].afterAmount);
                            $("#packageTemplatName" + data[v].empWorkType).val(data[v].templatName);
	            		}
	            	}
	        	}
	        }
	 	})
	 	
	 	//不同工种申请的辅料【商品种类】【数量】及【金额】
	 	var shuidian = 0;
		var mu = 0;
		var wa = 0;
		var you = 0;
	 	var shuidianCount = 0;
	 	var muCount = 0;
	 	var waCount = 0;
	 	var youCount = 0;
	 	var shuidianAmount = 0;
	 	var muAmount = 0;
	 	var waAmount = 0;
	 	var youAmount = 0;
	 	//水电/木/瓦/油
	 	var packageStateShuidian = $("#packageState0").val();
	 	var packageStateMu = $("#packageState2").val();
	 	var packageStateWa = $("#packageState1").val();
	 	var packageStateYou = $("#packageState3").val();
	 	
	 	$(".buy li").each(function(){
      		var workType = $(this).find('input[name*=workType]').val();
      		var num = parseInt($(this).find('input[class*=mui-numbox-input]').val());
      		var price = parseFloat($(this).find('span[class*=col_red]').text());
			if(isNaN(num)){
				num=0;
			}
			if(num>999){
				num=999
			}
			var amount = num*price;
      		if(''!=workType){
      			$(this).removeClass('_active');
      			if(workType == '0'){
      				shuidian += 1;
      				shuidianCount += num;
      				shuidianAmount += amount;
      				if(packageStateShuidian == "2" || packageStateShuidian == "3" || packageStateShuidian == "4" || packageStateShuidian == "6"){
      					$(this).addClass('_active');
      				}
      			}else if(workType == '1'){
      				wa += 1;
      				waCount += num;
      				waAmount += amount;
      				if(packageStateWa == "2" || packageStateWa == "3" || packageStateWa == "4" || packageStateWa == "6"){
      					$(this).addClass('_active');
      				}
      			}else if(workType == '2'){
      				mu += 1;
      				muCount += num;
      				muAmount += amount;
      				if(packageStateMu == "2" || packageStateMu == "3" || packageStateMu == "4" || packageStateMu == "6"){
      					$(this).addClass('_active');
      				}
      			}else if(workType == '3'){
      				you += 1;
      				youCount += num;
      				youAmount += amount;
      				if(packageStateYou == "2" || packageStateYou == "3" || packageStateYou == "4" || packageStateYou == "6"){
      					$(this).addClass('_active');
      				}
      			}
      		}
    	});
	 	
	 	//水电
	 	if (parseInt(shuidian) > 0) {
            var packageTemplatNameShuidian = $("#packageTemplatName0").val();
	 		if(packageStateShuidian == "2"){
	 			$("#alertRemarks").text("“"+packageTemplatNameShuidian+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
        		$("#alert").show();
        		return;
	 		}
	 		if(packageStateShuidian == "3"){
    			$("#alertRemarks").text("“"+packageTemplatNameShuidian+"”的任务包已经【确认验收】，请删除高亮显示的【水电】类辅料商品。");
        		$("#alert").show();
    			return;
    		}
    		if(packageStateShuidian == "4"){
    			$("#alertRemarks").text("“"+packageTemplatNameShuidian+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
        		$("#alert").show();
    			return;
    		}
    		if(packageStateShuidian == "6"){
        		$("#alertRemarks").text("您【水电】类辅料商品申请金额还剩余0元，请删除高亮显示的【水电】辅料商品。");
	 			$("#alert").show();
    			return;
    		}
    		if(packageStateShuidian == "7"){
	 	        var afterAmount = $("#packageAfterAmount0").val();
                if(!isNaN(shuidianAmount) && parseFloat(shuidianAmount.toFixed(2)) > parseFloat(afterAmount)){
            		var canApply = (parseFloat(afterAmount)-parseFloat(shuidianAmount)).toFixed(2);
	 				$("#alertRemarks").text("您【油】类辅料商品申请金额还剩余"+canApply+"元，请修改【油】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 				$("#alert").show();
        			return;
                }
    		}
	 	}
	 	
	 	//木
	 	if (parseInt(mu) > 0) {
            var packageTemplatNamemu = $("#packageTemplatName2").val();
            if(packageStateMu == "2"){
	 			$("#alertRemarks").text("“"+packageTemplatNamemu+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateMu == "3"){
	 			$("#alertRemarks").text("“"+packageTemplatNamemu+"”的任务包已经【确认验收】，请删除高亮显示的【木】类辅料商品。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateMu == "4"){
	 			$("#alertRemarks").text("“"+packageTemplatNamemu+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateMu == "6"){
	 			$("#alertRemarks").text("您【木】类辅料商品申请金额还剩余0元，请删除高亮显示的【木】辅料商品。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateMu == "7"){
	 			var afterAmount = $("#packageAfterAmount2").val();
	 			if(!isNaN(muAmount) && parseFloat(muAmount.toFixed(2)) > parseFloat(afterAmount)){
	 				var canApply = (parseFloat(afterAmount)-parseFloat(muAmount)).toFixed(2);
	 				$("#alertRemarks").text("您【木】类辅料商品申请金额还剩余"+canApply+"元，请修改【木】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 				$("#alert").show();
	 				return;
	 			}
	 		}
	 	}
	 	
	 	//瓦
	 	if (parseInt(wa) > 0) {
            var packageTemplatNamewa = $("#packageTemplatName1").val();
	 		if(packageStateWa == "2"){
	 			$("#alertRemarks").text("“"+packageTemplatNamewa+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateWa == "3"){
	 			$("#alertRemarks").text("“"+packageTemplatNamewa+"”的任务包已经【确认验收】，请删除高亮显示的【瓦】类辅料商品。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateWa == "4"){
	 			$("#alertRemarks").text("“"+packageTemplatNamewa+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateWa == "6"){
	 			$("#alertRemarks").text("您【瓦】类辅料商品申请金额还剩余0元，请删除高亮显示的【瓦】辅料商品。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateWa == "7"){
	 			var afterAmount = $("#packageAfterAmount1").val();
	 			if(!isNaN(waAmount) && parseFloat(waAmount.toFixed(2)) > parseFloat(afterAmount)){
	 				var canApply = (parseFloat(afterAmount)-parseFloat(waAmount)).toFixed(2);
	 				$("#alertRemarks").text("您【瓦】类辅料商品申请金额还剩余"+canApply+"元，请修改【瓦】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 				$("#alert").show();
	 				return;
	 			}
	 		}
	 	}
	 	
	 	//油
	 	if (parseInt(you) > 0) {
            var packageTemplatNameyou = $("#packageTemplatName3").val();
	 		if(packageStateYou == "2"){
	 			$("#alertRemarks").text("“"+packageTemplatNameyou+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateYou == "3"){
	 			$("#alertRemarks").text("“"+packageTemplatNameyou+"”的任务包已经【确认验收】，请删除高亮显示的【油】类辅料商品。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateYou == "4"){
	 			$("#alertRemarks").text("“"+packageTemplatNameyou+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateYou == "6"){
	 			$("#alertRemarks").text("您【油】类辅料商品申请金额还剩余0元，请删除高亮显示的【油】辅料商品。");
	 			$("#alert").show();
	 			return;
	 		}
	 		if(packageStateYou == "7"){
	 			var afterAmount = $("#packageAfterAmount3").val();
	 			if(!isNaN(youAmount) && parseFloat(youAmount.toFixed(2)) > parseFloat(afterAmount)){
	 				var canApply = (parseFloat(afterAmount)-parseFloat(youAmount)).toFixed(2);
	 				$("#alertRemarks").text("您【油】类辅料商品申请金额还剩余"+canApply+"元，请修改【油】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 				$("#alert").show();
	 				return;
	 			}
	 		}
	 	}
	 	
	}//校验结束
	
	 $.ajax({
         url: baseUrl+"/app/manager/auxiliary/applyAuxiliary_data_check_ajax",
         type: "post",
         async: false,
         data: {
     		orderId:orderId
     	 },
         success: function(data){
         	
         	if(data!=null && data=="0"){
         		$("#alert2").show();
         	}else if(data=="1"){
         		$("#alertRemarks").text("订单id为空");
 				$("#alert").show();
         	}else if(data=="2"){
         		$("#alertRemarks").text("项目经理未登录");
 				$("#alert").show();
         	}else if(data=="3"){
         		$("#alertRemarks").text("该订单的基础装修质检员已确认验收了，不允许再申请辅料了。");
 				$("#alert").show();
         	}else if(data=="4"){
         		$("#alertRemarks").text("您有未阅读的辅料采购单，请到【申请记录】去查阅后再申请辅料");
 				$("#alert").show();
         	}else if(data=="5"){
         		$("#alertRemarks").text("同一个订单两次辅料申请操作时间必须间隔5分钟，请过5分钟后再申请");
 				$("#alert").show();
         	}
         }
	 })	
	
	
}


function cancel(){
	$("#alert").hide();
	$("#alert2").hide();
	$("#alert3").hide();
}
function sure(){
	$("#alert").hide();
	$("#alert2").hide();
	$("#alert3").hide();
}
var purchaseId;
function backlast(){
	window.location.href = baseUrl+"/app/manager/auxiliaryApplyRecord/auxiliaryDetails?purchaseId="+purchaseId;
}

function sure2(){
	
	$("#sure").removeAttr("onclick");
	$("#sure").attr("disabled",true);
	run_waitMe("win8",'经理稍等 ,拼命提交中....');
	$("#alert2").hide();
	var options={
		url: baseUrl+"/app/manager/auxiliary/auxiliarypay",
		type: "post",
		success: function(data){

			if (data=="gaoliang"){
				$("#alertRemarks").text("请重新进入提交辅料，并删除高亮显示的商品，再点击“确认用量”");
 				$("#alert").show();
			}else{
				purchaseId = data;
				$("#alert3").show();
			}
		}
	}
	$("#form").ajaxSubmit(options);
}

