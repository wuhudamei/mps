$(function () {
	// 点击选择下面的内容
	var select_choice = {
		select_option: function (select_click) {
			// var this = $(this);
			select_click.on('click',function () {
				var that = $(this);
				$(this).siblings('ul').toggle();
				$(this).siblings('ul').find('li').on('click',function () {
					select_click.html($(this).text());
					that.siblings('ul').hide();
					// 这里获取的隐藏域的val
					console.log($(this).find('input').val())
					var a = $(this).find('input').val();
					$("#problemTypeId").val(a);
				})
			});

		}
	}
	select_choice.select_option($('.select_choice'))
})

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

var orderId = $("#orderId").val();

$(document).ready(function(){
		$("#start").bind('click',tijiao);
	});
    
//提交问题--页面数据校验
function tijiao(){
	//问题分类
    var problemTypeId = $("#problemTypeId").val();
	if(null==problemTypeId || problemTypeId=="" || undefined==problemTypeId){
		$("#alertRemarks").text("请选择问题分类");
		$("#alert").show();
		return;
	}
	//期望完成日期
	var txtDate = $("#txtDate").val();
	if(txtDate == "" || txtDate == null){
		$("#alertRemarks").text("期望完成日期必须填写");
		$("#alert").show();
		return false;
	}
	//责任人
	var inchargeName = $("#inchargeName").val();
	if(inchargeName == "" || inchargeName == null){
		$("#alertRemarks").text("责任人必须填写");
		$("#alert").show();
		return false;
	}
	//问题描述
	var problemDescribe = $("#problemDescribe").val();
	if(problemDescribe == "" || problemDescribe == null){
		$("#alertRemarks").text("问题描述必须填写，最多不能超过100个汉字");
		$("#alert").show();
		return false;
	}
	$("#alert2").show();
}

function sure(){
	$("#alert").hide();
	$("#alert2").hide();
	$("#alert3").hide();
}
function cancel(){
	$("#alert").hide();
	$("#alert2").hide();
	$("#alert3").hide();
}
//返回
function backlast(){
		
	window.location.href = baseUrl+"/app/manager/problem/siteDesign/signDesignproblemRecord?orderId="+orderId;
		
}
//提交数据--校验5分钟内的重复提交问题
function sure2(){
	$("#alert").hide();
	$("#alert2").hide();
	$("#alert3").hide();	
	
	$("#start").css("color","#CCC");
	$("#start").unbind("click");
	
	var businessType = "4";
	$.ajax({
        url: baseUrl+"/app/manager/problem/wallAndFloor/problem_wallAndFloor_ajax_time",
        type: "post",
        data: {
        		orderId:orderId,
        		businessType:businessType
        	  },
        success: function(data){
        	if(null!=data && data == "0"){
        		applySubmit();
        	}else if(data == "1"){
        		$("#alertRemarks").text("订单id传送失败");
        		$("#alert").show();
        	}else{
        		$("#alertRemarks").text("您刚刚提交过工地设计问题上报，请耐心等待五分钟后再次操作");
        		$("#alert").show();
        	}
        }
	 }); 
}


//表单提交
function applySubmit(){
	
	//防止重复提交
	run_waitMe('正在提交数据,请稍等');
	$("#start").css("color","#CCC");
	$("#start").unbind("click");
	
	//上报提交表单
	var options ={
			url : baseUrl+"/app/manager/problem/siteDesign/sign_design_problem_submit",
			type: "post",
			success : function(data){
				$('body').waitMe('hide');
				if(null!=data && data=="0"){
					$("#alert3").show();
				}else if(data=="1"){
					$("#alertRemarks").text("订单id为空");
        			$("#alert").show();
				}else if(data=="2"){
					$("#alertRemarks").text("问题分类为空");
        			$("#alert").show();
				}else if(data=="3"){
					$("#alertRemarks").text("期望完成日期为空");
        			$("#alert").show();
				}else if(data=="4"){
					$("#alertRemarks").text("责任人为空");
        			$("#alert").show();
				}else if(data=="5"){
					$("#alertRemarks").text("上报问题描述为空");
        			$("#alert").show();
				}else if(data=="6"){
					$("#alertRemarks").text("项目经理未登录");
        			$("#alert").show();
				}else if(data=="7"){
					$("#alertRemarks").text("该问题分类内容不存在");
        			$("#alert").show();
				}else if(data=="8"){
					$("#alertRemarks").text("上报问题保存失败");
					$("#alert").show();
				}else if(data=="9"){
					$("#alertRemarks").text("上报问题日志保存失败");
					$("#alert").show();
				}else if(data=="10"){
					$("#alertRemarks").text("上报问题图片保存失败");
					$("#alert").show();
				}
			}
		}
	//ajax提交form
	$("#jvForm").ajaxSubmit(options);
	
}




//图片上传显示 
function preview(file) {  
	var shit = $("#shit").val();
	var prevDiv = $('.camera');  
	if (file.files && file.files[0]) {  
		var reader = new FileReader();  
		reader.onload = function(evt){
			$('<div class="pic"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><a class="del_pic" href="javascript:void(0)">删除</a></div>').insertAfter('#camera');
		}    
		reader.readAsDataURL(file.files[0]);  
	}else {  
		prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
		var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
		console.log(file,file.value);
	}
} 
$(document).on('click', '.del_pic', function(){
	
	var numReal = $(this).prev().attr("id");
	$(("#num"+numReal)).remove();
	
	var num = $("#num").val();
	num--;
	$("#num").val(num);
	$(this).parent().remove();
	globalUtil.fn.alert('删除图片操作成功',2.0);
});

function uploadpic(pic){
	
	var hiddenForm = document.getElementById("jvForm");
	var input =document.createElement("input");
	
	
	var num = $("#num").val();
	var shit = $("#shit").val();
	
	if(pic){
		num++;
		input.setAttribute("id","num"+shit);
		input.setAttribute('hidden', 'hidden'); 
		input.setAttribute('name', 'photo'); 
		input.setAttribute("type","text");
		input.setAttribute("value", pic);
		hiddenForm.appendChild(input);
		$("#num").val(num);
		shit++;
		$("#shit").val(shit);
	}
}
