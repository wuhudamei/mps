
function run_waitMe(effect) {
    $('body').waitMe({
        effect: effect,
        text: '经理稍等~正在拼命提交您的辅料..',
        bg: 'rgba(255,255,255,0.7)',
        color: '#000',
        sizeW: '',
        sizeH: '',
        source: 'img.svg'
    });
}

function alertKnow(){
	$("#alertTwo").hide();
}

//图片没有 就使用默认图片
function nofind() {

    var img = event.srcElement;

    img.src = "/static/mobile/modules/Manager/css/photo/auxiliaryPhotoForNothing.png";

    img.onerror = null;

}
Items("0");
var orderId;
function Items(categoryName) {
	
	//查看工程模式
	//如果是产业或者是准产业时：
	//【1】：任务包限制
    //【1.1】：如果没有工种对应的任务包模板，不做限制
    //		【isCanApplyAuxiliary:1】【不限】
    //【1.2】：如果没有生产该模板的任务包，必须生产任务包
    //		【isCanApplyAuxiliary:2】【不可】：“水电路改造工程”任务包还未生成，请您联系工程部的拆单员进行拆单。
    //【1.3】：如果存在任务包，但是任务包已经验收，则不允许申请其对应的辅料
    //		【isCanApplyAuxiliary:3】【不可】：水电路改造工程”的任务包点了【确认验收】之 后，就不能再申请【水电】类下面所有的辅料商品
    //【2】：金额限制
    //【2.1】：存在任务包，但是任务包预算金额为空，必须要有金额
    //		【isCanApplyAuxiliary:4】【不可】：“水电路改造工程”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。
    //【2.2】：任务包预算金额不为空，但是申请辅料预算比例为空，不做限制
    //		【isCanApplyAuxiliary:5】【不限】
    //【2.3】：任务包预算金额*比例-已申请金额<0，不可以申请
    //		【isCanApplyAuxiliary:6】【不可】【选择页面】：您【水电】类辅料商品申请金额还剩余0元，不可申请【水电】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。
	//		【isCanApplyAuxiliary:6】【不可】【提交页面】：您【油】类辅料商品申请金额还剩余0元，请删除高亮显示的【油】辅料商品。
    //【2.4】：任务包预算金额*比例-已申请金额>0，可以申请,注意校验
    //		【isCanApplyAuxiliary:7】【限制】：您【水电】类辅料商品申请金额还剩余10.00元，请修改【水电】辅料商品数量，如果商品数量确实不够请联系拆单员修改任务包预算金额。
	orderId = $("#orderId").val();
	var projectMode = $("#projectMode").val();
	if(projectMode == '1' || projectMode == '4'){
    	var packageState = $("#packageState" + categoryName).val();
    	//水电
    	if(categoryName == "0"){
            var packageTemplatNameShuidian = $("#packageTemplatName0").val();
    		if(packageState == "2"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNameShuidian+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    		if(packageState == "3"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNameShuidian+"”的任务包点了【确认验收】之 后，就不能再申请【水电】类下面所有的辅料商品。");
    			$("#prompt" + categoryName).show();
    			// $("#prompt" + categoryName).show();
    			return;

    		}
    		if(packageState == "4"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNameShuidian+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    		if(packageState == "6"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("您【水电】类辅料商品申请金额还剩余0元，不可申请【水电】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    	}
    	//瓦工
    	if(categoryName == "1"){
            var packageTemplatNamewa = $("#packageTemplatName1").val();
    		if(packageState == "2"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNamewa+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    		if(packageState == "3"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNamewa+"”的任务包点了【确认验收】之后，就 不能再申请【瓦】类下面所有的辅料商品。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    		if(packageState == "4"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNamewa+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    		if(packageState == "6"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("您【瓦】类辅料商品申请金额还剩余0元，不可申请【瓦】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    	}
    	//木工
    	if(categoryName == "2"){
            var packageTemplatNamemu = $("#packageTemplatName2").val();
    		if(packageState == "2"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNamemu+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    		if(packageState == "3"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNamemu+"”的任务包点了【确认验收】之 后，就不能再申请【木】类下面所有的辅料商品.");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    		if(packageState == "4"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNamemu+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    		if(packageState == "6"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("您【木】类辅料商品申请金额还剩余0元，不可申请【木】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    	}
    	//油工
    	if(categoryName == "3"){
            var packageTemplatNameyou = $("#packageTemplatName3").val();
    		if(packageState == "2"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNameyou+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    		if(packageState == "3"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNameyou+"”的任务包点了【确认验收】之后，就 不能再申请【油】类下面所有的辅料商品。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    		if(packageState == "4"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("“"+packageTemplatNameyou+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    		if(packageState == "6"){
    			$("#" + categoryName).html('');
    			$("#" + categoryName).hide();
    			$("#promptRemark" + categoryName).text("您【油】类辅料商品申请金额还剩余0元，不可申请【油】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
    			$("#prompt" + categoryName).show();
    			return;
    		}
    	}
    	
        
	}
	
	//查看是否已近加载过商品
    if ($("#" + categoryName).find('li').length > 0) {
        return;
    }
	
	//如果没有加载过，则查新商品
    var allHtml = '';
    $("#" + categoryName).html('');
    
    $.ajax({

            url: baseUrl+"/app/manager/auxiliary/categoryItems?workerType="+ categoryName + "&orderId="+orderId,
            type: "get",
            dataType: "json",
            success: function (data) {
                var html = '';
                jQuery.each(data,function (i, auxiliary) {

                            html += '<input type="text" hidden="hidden" name="id" value=' + auxiliary.id + '/><input type="text" hidden="hidden" name="auxiMateCode" value=' + auxiliary.auxiMateCode + '/><li class="shadow _height-auto"><div class="img_container"><img src="'
                                + auxiliary.pic
                                + '" alt="goods" onerror="nofind()" "></div>	<p class="brand brand-name _ellipsis3"><span>【</span>'
                                + auxiliary.brand
                                + '<span>】</span>'
                                + auxiliary.name
                                + '</p><p class="format">'
                                + auxiliary.specifications
                                + '</p><p class="price"><span class="col_red">'
                                + auxiliary.price
                                + '</span>元/'
                                + auxiliary.unit
                                + '</p><div id="numbox" class="mui-numbox" data-numbox-step=' + "'1'" + ' data-numbox-min=' + "'0'" + '  data-numbox-max= ' + "'999'" + ' style="" ><button class="mui-btn mui-numbox-btn-minus" type="button" style="" onclick="setTotal(this,2,'+categoryName+')">-</button> <input  id="num"  class="mui-numbox-input" type="number" value="0" style="" name="count" onchange="setTotal(this,3,'+categoryName+')" onkeyup="isInteger(this)" '
                                + '/><button class="mui-btn mui-numbox-btn-plus" type="button" style="" onclick="setTotal(this,1,'+categoryName+')">+</button></div></li>';
                        });
                allHtml += html;

                $("#" + categoryName).append(allHtml);

            }
        });


}

var commonObj = null;
function isInteger(obj) {
    var num = $(obj).val();
    if (num == "") {
        return;
    }
    if (!isNaN(num) && num > 0 && String(num).split('.')[1] == undefined) {
    } else {
        commonObj = obj
        $(obj).attr("readOnly", true);
        $("#alert").show()
    }
}

function queren() {
    $("#alert").hide()
    $(commonObj).attr("readOnly", false);
    $(commonObj).val(2)
    $(commonObj).val(0)
    
    var s = 0;
    var c = 0;
    $(".show_sec .choose_ul li").each(
        function () {
            c += parseInt($(this).find('input[class*=mui-numbox-input]').val());
            s += parseInt($(this).find('input[class*=mui-numbox-input]').val()) * parseFloat($(this).find('span[class*=col_red]').text());
        });
    if(isNaN(s.toFixed(2)) ||isNaN(c)){
        $("#total").html(0.00+ "元");
        $("#count").html("您一共选择了" + 0 + "个商品");
    }
    if(!isNaN(s.toFixed(2))&&!isNaN(c)){
        $("#total").html(s.toFixed(2) + "元");
        $("#count").html("您一共选择了" + c + "个商品");
    }
    
}

function setTotal(obj, category,categoryName) {

    if (2 == category) {
        var inputObj = $(obj).next();
        var number = $(inputObj).val();
        if (number == 0) {
            return;
        }
        $(inputObj).val(parseInt(number) - 1);
    }
    if (1 == category) {
        var inputObj = $(obj).prev();
        var number = $(inputObj).val();
        if (number == 999) {
            return;
        }
        $(inputObj).val(parseInt(number) + 1);
    }
    if (3 == category) {
        if ($(obj).val() == "") {
            $(obj).val(2)
            $(obj).val(0)
        } else {
            $(obj).val($(obj).val().replace(/\b(0+)/gi, ""))
        }
        if ($(obj).val() > 999) {
            $(obj).val(999);
        }
    }
    setTimeout(function () {
    	var s = 0;
        var c = 0;
        $(".show_sec .choose_ul li").each(
            function () {
                c += parseInt($(this).find('input[class*=mui-numbox-input]').val());
                s += parseInt($(this).find('input[class*=mui-numbox-input]').val()) * parseFloat($(this).find('span[class*=col_red]').text());
            });
        if(isNaN(s.toFixed(2)) ||isNaN(c)){
            $("#total").html(0.00+ "元");
            $("#count").html("您一共选择了" + 0 + "个商品");
        }
        if(!isNaN(s.toFixed(2))&&!isNaN(c)){
            $("#total").html(s.toFixed(2) + "元");
            $("#count").html("您一共选择了" + c + "个商品");
        }
    }, "50");
    
    var projectMode = $("#projectMode").val();
	if(projectMode == '1' || projectMode == '4'){
		var packageState = $("#packageState" + categoryName).val();
		if(packageState == "7"){
			var m = 0;
			var inputObj = $(obj).parent().parent().parent();
			$(inputObj).find('li').each(function () {
				m += parseInt($(this).find('input[class*=mui-numbox-input]').val()) * parseFloat($(this).find('span[class*=col_red]').text());
			});
			
			var afterAmount = $("#packageAfterAmount" + categoryName).val();
			if(!isNaN(m) && parseFloat(m.toFixed(2)) > parseFloat(afterAmount)){
				var canApply = (parseFloat(afterAmount)-parseFloat(m)).toFixed(2);
				if(categoryName == "0"){
 					$("#alertMessage").text("您【水电】类辅料商品申请金额还剩余"+canApply+"元，请修改【水电】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
				}else if(categoryName == "1"){
					$("#alertMessage").text("您【瓦】类辅料商品申请金额还剩余"+canApply+"元，请修改【瓦】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
				}else if(categoryName == "2"){
					$("#alertMessage").text("您【木】类辅料商品申请金额还剩余"+canApply+"元，请修改【木】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
				}else{
					$("#alertMessage").text("您【油】类辅料商品申请金额还剩余"+canApply+"元，请修改【油】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
				}
				$("#alertTwo").show();
				return;
			}
		}
	}
		
}

//选好了--校验任务包
function cart() {
	var projectMode = $("#projectMode").val();
	if(projectMode != '1' && projectMode != '4'){
		cartSubmit();
	}else{
		
		var orderId = $("#orderId").val();
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
		 
	 	//水电
	 	var packageStateShuidian = $("#packageState0").val();
	 	var packageTemplatNameShuidian = $("#packageTemplatName0").val();
		if ($("#0").find('li').length > 0) {
			if(packageStateShuidian == "2"){
    			$("#alertMessage").text("“"+packageTemplatNameShuidian+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
        		$("#alertTwo").show();
    			$("#0").html('');
    			$("#0").hide();
    			$("#promptRemark0").text("“"+packageTemplatNameShuidian+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
    			$("#prompt0").show();
    			return;
    		}
    		if(packageStateShuidian == "3"){
    			$("#alertMessage").text("“"+packageTemplatNameShuidian+"”的任务包点了【确认验收】之 后，就不能再申请【水电】类下面所有的辅料商品。");
        		$("#alertTwo").show();
    			$("#0").html('');
    			$("#0").hide();
    			$("#promptRemark0").text("“"+packageTemplatNameShuidian+"”的任务包点了【确认验收】之 后，就不能再申请【水电】类下面所有的辅料商品。");
    			$("#prompt0").show();
    			return;
    		}
    		if(packageStateShuidian == "4"){
    			$("#alertMessage").text("“"+packageTemplatNameShuidian+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
        		$("#alertTwo").show();
    			$("#0").html('');
    			$("#0").hide();
    			$("#promptRemark0").text("“"+packageTemplatNameShuidian+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
    			$("#prompt0").show();
    			return;
    		}
    		if(packageStateShuidian == "6"){
    			$("#alertMessage").text("您【水电】类辅料商品申请金额还剩余0元，不可申请【水电】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
        		$("#alertTwo").show();
    			$("#0").html('');
    			$("#0").hide();
    			$("#promptRemark0").text("您【水电】类辅料商品申请金额还剩余0元，不可申请【水电】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
    			$("#prompt0").show();
    			return;
    		}
    		if(packageStateShuidian == "7"){
    			var s = 0;
	 	        $("#0 li").each(
	 	            function () {
	 	                s += parseInt($(this).find('input[class*=mui-numbox-input]').val()) * parseFloat($(this).find('span[class*=col_red]').text());
	 	            });
                var afterAmount = $("#packageAfterAmount0").val();
                if(!isNaN(s) && parseFloat(s.toFixed(2)) > parseFloat(afterAmount)){
        		    var canApply = (parseFloat(afterAmount)-parseFloat(s.toFixed(2))).toFixed(2);
        			$("#alertMessage").text("您【水电】类辅料商品申请金额还剩余"+canApply+"元，请修改【水电】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
        			$("#alertTwo").show();
        			return;
                }
    		}
		}
		 	
	 	//木
	 	var packageStateMu = $("#packageState2").val();
        var packageTemplatNamemu = $("#packageTemplatName2").val();
	 	if ($("#2").find('li').length > 0) {
	 		if(packageStateMu == "2"){
	 			$("#alertMessage").text("“"+packageTemplatNamemu+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
	 			$("#alertTwo").show();
	 			$("#2").html('');
	 			$("#2").hide();
	 			$("#promptRemark2").text("“"+packageTemplatNamemu+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
	 			$("#prompt2").show();
	 			return;
	 		}
	 		if(packageStateMu == "3"){
	 			$("#alertMessage").text("“"+packageTemplatNamemu+"”的任务包点了【确认验收】之 后，就不能再申请【木】类下面所有的辅料商品。");
	 			$("#alertTwo").show();
	 			$("#2").html('');
	 			$("#2").hide();
	 			$("#promptRemark2").text("“"+packageTemplatNamemu+"”的任务包点了【确认验收】之 后，就不能再申请【木】类下面所有的辅料商品。");
	 			$("#prompt2").show();
	 			return;
	 		}
	 		if(packageStateMu == "4"){
	 			$("#alertMessage").text("“"+packageTemplatNamemu+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
	 			$("#alertTwo").show();
	 			$("#2").html('');
	 			$("#2").hide();
	 			$("#promptRemark2").text("“"+packageTemplatNamemu+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
	 			$("#prompt2").show();
	 			return;
	 		}
	 		if(packageStateMu == "6"){
	 			$("#alertMessage").text("您【木】类辅料商品申请金额还剩余0元，不可申请【木】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 			$("#alertTwo").show();
	 			$("#2").html('');
	 			$("#2").hide();
	 			$("#promptRemark2").text("您【木】类辅料商品申请金额还剩余0元，不可申请【木】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 			$("#prompt2").show();
	 			return;
	 		}
	 		if(packageStateMu == "7"){
	 			var s = 0;
	 	        $("#2 li").each(
	 	            function () {
	 	                s += parseInt($(this).find('input[class*=mui-numbox-input]').val()) * parseFloat($(this).find('span[class*=col_red]').text());
	 	            });
	 			var afterAmount = $("#packageAfterAmount2").val();
	 			if(!isNaN(s) && parseFloat(s.toFixed(2)) > parseFloat(afterAmount)){
	 				var canApply = (parseFloat(afterAmount)-parseFloat(s.toFixed(2))).toFixed(2);
	 				$("#alertMessage").text("您【木】类辅料商品申请金额还剩余"+canApply+"元，请修改【木】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 				$("#alertTwo").show();
	 				return;
	 			}
	 		}
	 	}
	 	
	 	//瓦
	 	var packageStateWa = $("#packageState1").val();
        var packageTemplatNamewa = $("#packageTemplatName1").val();
	 	if ($("#1").find('li').length > 0) {
	 		if(packageStateWa == "2"){
	 			$("#alertMessage").text("“"+packageTemplatNamewa+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
	 			$("#alertTwo").show();
	 			$("#1").html('');
	 			$("#1").hide();
	 			$("#promptRemark1").text("“"+packageTemplatNamewa+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
	 			$("#prompt1").show();
	 			return;
	 		}
	 		if(packageStateWa == "3"){
	 			$("#alertMessage").text("“"+packageTemplatNamewa+"”的任务包点了【确认验收】之 后，就不能再申请【瓦】类下面所有的辅料商品。");
	 			$("#alertTwo").show();
	 			$("#1").html('');
	 			$("#1").hide();
	 			$("#promptRemark1").text("“"+packageTemplatNamewa+"”的任务包点了【确认验收】之 后，就不能再申请【瓦】类下面所有的辅料商品。");
	 			$("#prompt1").show();
	 			return;
	 		}
	 		if(packageStateWa == "4"){
	 			$("#alertMessage").text("“"+packageTemplatNamewa+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
	 			$("#alertTwo").show();
	 			$("#1").html('');
	 			$("#1").hide();
	 			$("#promptRemark1").text("“"+packageTemplatNamewa+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
	 			$("#prompt1").show();
	 			return;
	 		}
	 		if(packageStateWa == "6"){
	 			$("#alertMessage").text("您【瓦】类辅料商品申请金额还剩余0元，不可申请【瓦】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 			$("#alertTwo").show();
	 			$("#1").html('');
	 			$("#1").hide();
	 			$("#promptRemark1").text("您【瓦】类辅料商品申请金额还剩余0元，不可申请【瓦】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 			$("#prompt1").show();
	 			return;
	 		}
	 		if(packageStateWa == "7"){
	 			var s = 0;
	 			$("#1 li").each(
	 					function () {
	 						s += parseInt($(this).find('input[class*=mui-numbox-input]').val()) * parseFloat($(this).find('span[class*=col_red]').text());
	 					});
	 			var afterAmount = $("#packageAfterAmount1").val();
	 			if(!isNaN(s) && parseFloat(s.toFixed(2)) > parseFloat(afterAmount)){
	 				var canApply = (parseFloat(afterAmount)-parseFloat(s.toFixed(2))).toFixed(2);
	 				$("#alertMessage").text("您【瓦】类辅料商品申请金额还剩余"+canApply+"元，请修改【瓦】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 				$("#alertTwo").show();
	 				return;
	 			}
	 		}
	 	}
	 	
	 	//油
	 	var packageStateYou = $("#packageState3").val();
        var packageTemplatNameyou = $("#packageTemplatName3").val();
	 	if ($("#3").find('li').length > 0) {
	 		if(packageStateYou == "2"){
	 			$("#alertMessage").text("“"+packageTemplatNameyou+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
	 			$("#alertTwo").show();
	 			$("#3").html('');
	 			$("#3").hide();
	 			$("#promptRemark3").text("“"+packageTemplatNameyou+"”任务包还未生成，请您联系工程部的拆单员进行拆单。");
	 			$("#prompt3").show();
	 			return;
	 		}
	 		if(packageStateYou == "3"){
	 			$("#alertMessage").text("“"+packageTemplatNameyou+"”的任务包点了【确认验收】之 后，就不能再申请【油】类下面所有的辅料商品。");
	 			$("#alertTwo").show();
	 			$("#3").html('');
	 			$("#3").hide();
	 			$("#promptRemark3").text("“"+packageTemplatNameyou+"”的任务包点了【确认验收】之 后，就不能再申请【油】类下面所有的辅料商品。");
	 			$("#prompt3").show();
	 			return;
	 		}
	 		if(packageStateYou == "4"){
	 			$("#alertMessage").text("“"+packageTemplatNameyou+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
	 			$("#alertTwo").show();
	 			$("#3").html('');
	 			$("#3").hide();
	 			$("#promptRemark3").text("“"+packageTemplatNameyou+"”任务包的预算金额为0，请您联系工程部的拆单员进行拆单或者修改预算金额。");
	 			$("#prompt3").show();
	 			return;
	 		}
	 		if(packageStateYou == "6"){
	 			$("#alertMessage").text("您【油】类辅料商品申请金额还剩余0元，不可申请【油】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 			$("#alertTwo").show();
	 			$("#3").html('');
	 			$("#3").hide();
	 			$("#promptRemark3").text("您【油】类辅料商品申请金额还剩余0元，不可申请【油】辅料商品,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 			$("#prompt3").show();
	 			return;
	 		}
	 		if(packageStateYou == "7"){
	 			var s = 0;
	 			$("#3 li").each(
	 					function () {
	 						s += parseInt($(this).find('input[class*=mui-numbox-input]').val()) * parseFloat($(this).find('span[class*=col_red]').text());
	 					});
	 			var afterAmount = $("#packageAfterAmount3").val();
	 			if(!isNaN(s) && parseFloat(s.toFixed(2)) > parseFloat(afterAmount)){
	 				var canApply = (parseFloat(afterAmount)-parseFloat(s.toFixed(2))).toFixed(2);
	 				$("#alertMessage").text("您【油】类辅料商品申请金额还剩余"+canApply+"元，请修改【油】辅料商品数量,如果商品数量确实不够请联系拆单员修改任务包预算金额。");
	 				$("#alertTwo").show();
	 				return;
	 			}
	 		}
	 	}
	 	
		cartSubmit();
		 
	}
   

}
//选好了-- 提交
function cartSubmit() {
	
    run_waitMe("win8");
    $("#form").submit();
    $(".choose_btn").attr("disabled", true);
    $(".choose_btn").removeAttr("onclick");

}