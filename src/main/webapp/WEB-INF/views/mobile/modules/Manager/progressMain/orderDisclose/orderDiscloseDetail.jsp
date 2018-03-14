<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>交底</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/complete.css"/>
</head>
<script type="text/javascript">
	//提交
	function disCLoseSubmit(){
		var disCloseDate = $("#txtBeginDate").val();//实际交底日期 
		var measureSquare = $("#measureSquare").val();
		if(null==disCloseDate || disCloseDate==""){
			globalUtil.fn.alert("请输入实际交底日期...",2.0);
			return false;
		}
		if(null==measureSquare || measureSquare==""){
			globalUtil.fn.alert("请输入实测面积...",2.0);
			return false;
		}
		if($("#num1").val() !=undefined || $("#num2").val()!=undefined || $("#num3").val()!=undefined){
			//至少上传了一张图片
		}else{
			globalUtil.fn.alert("请至少上传一张图片...",2.0);
			return false;
		}
	
	$("#submit").css("color","#CCC");
	$("#submit").unbind("click");
    $(".file").each(function(){
        $(this).remove();
	});
	var options ={
		url : "${ctx}/app/manager/disCloseSubmitData",
		type: "post",
		success : function(data){
			if(data == 0){
				 globalUtil.fn.alert("操作成功...",2.0);
				 window.location.href = "${ctx}/app/manager/orderDisclose";
			  }
			  if(data == 1){
			  	 globalUtil.fn.alert("操作交底数据错误...",2.0);
			  }
			  if(data == 2){
				  globalUtil.fn.alert("操作交底图片数据失败...",2.0);
			  }
			  if(data == 3){
				  globalUtil.fn.alert("更新订单状态失败...",2.0);
			  }
			  if(data == 4){
				  globalUtil.fn.alert("该订单已经交底！请不要重复提交！",2.0);
			  }
			  if(data=="noPic"){
				  
				  globalUtil.fn.alert("您没有上传图片, 请至少上传一张图片",2.0);
				  window.location.reload();
				  
			  }
			}
		}
		//ajax提交form
		$("#form1").ajaxSubmit(options);
	}
function fix_amountthis_two(obj){
    	
        //先把非数字的都替换掉，除了数字和. 
	    obj.value = obj.value.replace(/[^\d.]/g,""); 
	    //必须保证第一个为数字而不是. 
	    obj.value = obj.value.replace(/^\./g,""); 
	    //保证只有出现一个.而没有多个. 
	    obj.value = obj.value.replace(/\.{2,}/g,"."); 
	    //保证.只出现一次，而不能出现两次以上 
	    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	    //只能输入2个小数 
	    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
	    //只能输入7个整数
	    var reg = /.*\..*/;
	    if(reg.test(obj.value)){
	    	var aa = obj.value.substring(0,obj.value.indexOf("."));
	    	if(aa.length>7){
	    		obj.value = obj.value.substring(0,7); 
	    	}
	    }else{
	    	if(obj.value.length>7){
	    		obj.value = obj.value.substring(0,7);
	    	}
	    }
     }
	
	
	
	
</script>
<body>
	<div class="g-sign_list">
		<header class="header">
			<a class="back_btn" href="${ctx}/app/manager/orderDisclose"></a>
			<h2 class="title">交底</h2>
		</header>
		<form id="form1" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" name="orderID" value="${order.id}">
		<input type="hidden" name="count1" value="0">
		<section class="sign_list">
			<h3 class="blue_rnd">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</h3>
			<div class="mar_left5 date label">
				<label for="input_date">实际交底日期：</label>
				<input id="txtBeginDate" class="date" type="text" value="" name="disCloseDate" placeholder="请输入日期" 
				data-lcalendar="2000-01-01,2000-01-01" readonly="readonly"/><br>
				<label for="input_date">墙地砖实测面积：</label>
				<input id="measureSquare" style="width: 100px;" type="text" onkeyup="fix_amountthis_two(this)" onafterpaste="fix_amountthis_two(this)" class="area" name="measureSquare" placeholder="请输入实测面积"/>
				<label for="input_date"><span style="font-size: 13px;"> ㎡</span><span style="color: red;font-size: 13px;"> 含损耗</span></label>
			</div>
				
			<div class="mar_left5 mar_rgt5 clearfix">
				<p class="col_6 font30 float_left">1、客户授权书</p>
				<!-- <a class="col_blue font24 watch" href="javascript:void(0)" onclick="disCloseShowPhotos(1);">查看示例</a> -->
				<%-- <a class="col_blue font24 watch" href="${ctx }/app/manager/disCloseShowPhotos?type=1&orderID=${orderID}">查看示例</a> --%>
				<div class="pic camera" id="pic1">
					<%-- <input type="hidden" class="pics" name="pics1" id="pics1" value="">
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="调取摄像头">
					<input class="file" type="file" name="photos1" accept="image/*" capture="camera" onchange="uploadPic1(1)">
					<div class="pic" id="uploaddone1"></div> --%>
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/upload_pic.png" style="width:2.3rem;height:2.4rem;" alt="调取摄像头">
					<input class="file file1" type="file" name="pic" onchange="preview1(this)" id="pic" multiple="multiple" value="请选择图片">
				</div>
			</div>
			<div class="mar_left5 mar_rgt5 clearfix">
				<p class="col_6 font30 float_left">2、客户特殊要求记录单</p>
				<!-- <a class="col_blue font24 watch" href="javascript:void(0)" onclick="disCloseShowPhotos(2);">查看示例</a> -->
				<%-- <a class="col_blue font24 watch" href="${ctx }/app/manager/disCloseShowPhotos?type=2&orderID=${orderID}">查看示例</a> --%>
				<div class="pic camera"  id="pic2">
					<%-- <input type="hidden" class="pics" name="pics2" id="pics2" value="">
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="调取摄像头">
					<input class="file" type="file" name="photos2" accept="image/*" capture="camera" onchange="uploadPic2(2)">
					<div class="pic" id="uploaddone2"></div> --%>
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/upload_pic.png" style="width:2.3rem;height:2.4rem;" alt="调取摄像头">
					<input class="file file1" type="file" name="pic" onchange="preview2(this)" id="pic" multiple="multiple" value="请选择图片">
				</div>
			</div>
			<div class="mar_left5 mar_rgt5 mar_btm200 clearfix">
				<p class="col_6 font30 float_left">3、原房检测验收表</p>
				<!-- <a class="col_blue font24 watch" href="javascript:void(0)" onclick="disCloseShowPhotos(3);">查看示例</a> -->
				<%-- <a class="col_blue font24 watch" href="${ctx }/app/manager/disCloseShowPhotos?type=3&orderID=${orderID}">查看示例</a> --%>
				<div class="pic camera" id="pic3">
					<%-- <input type="hidden" class="pics" name="pics3" id="pics3" value="">
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="调取摄像头">
					<input class="file" name="photos3" type="file" accept="image/*" capture="camera" onchange="uploadPic3(3)">
					<div class="pic" id="uploaddone3"></div> --%>
					<img class="plus" src="${ctxStatic}/mobile/modules/Manager/images/upload_pic.png" style="width:2.3rem;height:2.4rem;" alt="调取摄像头">
					<input class="file file1" type="file" name="pic" onchange="preview3(this)" id="pic" multiple="multiple" value="请选择图片">
				</div>
			</div>
		</form>
		</section>
		<footer>
			<a class="apply_btn" id="submit" href="javaScript:void(0)">确认交底</a>
		</footer>
		</form>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script>
	(function(){
		var projectMode = "${order.projectMode}";
		if(projectMode!=1 && projectMode!=4){
			//不是产业（传统或其他）--无限制
			 var now = new Date(),
    		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
    		start = new Date(now.setDate(now.getDate()+3)),
    		end = new Date(now.setFullYear(now.getFullYear()+5)),
    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');
    		$('#txtBeginDate').val(globalUtil.fn.formatDate(new Date(), 'yyyy-MM-dd'));
    		var lcalendar = start+','+end;
	    	// 将时间限制加到input标签上。
	    	$('#txtBeginDate').attr('data-lcalendar', lcalendar);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
		}else{
			//产业--有限制
			// 获取当前日期，结束日期
	    	var now = new Date(),
	   		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
	   		end = new Date(now.setFullYear(now.getFullYear()+1)),
	   		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');
	
	    	var lcalendar = start+','+end;
	    	// 将时间限制加到input标签上。
	    	/* $('#txtBeginDate').attr('data-lcalendar', lcalendar);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
			 */
			$("#txtBeginDate").val(globalUtil.fn.formatDate(new Date(), 'yyyy-MM-dd'));
		}
	}())
	
	//图片上传显示 
	function preview1(file) {
		if(!checkPic(file)){
			return false;
		}
	    removePic("num1");
		$(".new1").remove();//每次进来删除当前div
		//$("#num1").attr('name','');//每次进来清空当前input中name属性值
		//$("#num1").val('');//每次进来清空当前input中value属性值
        $("#num1").remove();
		var prevDiv = $('.pic camera'); 
		if (file.files && file.files[0]) {  
			var reader = new FileReader();  
			reader.onload = function(evt){
				$('<div class="new1"><img style="width:100%;height:120px;"  id="" src="' + evt.target.result + '" onload="compresspic(this,uploadpic1);"/></div>').appendTo('#pic1');
			}    
			reader.readAsDataURL(file.files[0]);  
		}else {  
			prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
			var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
			console.log(file,file.value);
		}
	}
		
	//图片上传显示 
	function preview2(file) {
		if(!checkPic(file)){
			return false;
		}
        removePic("num2");
        $(".new2").remove();//每次进来删除当前div
		//$("#num2").attr('name','');//每次进来清空当前input中name属性值
		//$("#num2").val('');//每次进来清空当前input中value属性值
        $("#num2").remove();
		var prevDiv = $('.pic camera1'); 
		if (file.files && file.files[0]) {  
			var reader = new FileReader();  
			reader.onload = function(evt){
				$('<div class="new2"><img style="width:100%;height:120px;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic2);"/></div>').appendTo('#pic2');
			}    
			reader.readAsDataURL(file.files[0]);  
		}else {  
			prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
			var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
			console.log(file,file.value);
		}
	}
		
	//图片上传显示 
	function preview3(file) {
		if(!checkPic(file)){
			return false;
		}
        removePic("num3");
        $(".new3").remove();//每次进来删除当前div
		//$("#num3").attr('name','');//每次进来清空当前input中name属性值
		//$("#num3").val('');//每次进来清空当前input中value属性值
        $("#num3").remove();
		var prevDiv = $('.pic camera2'); 
		if (file.files && file.files[0]) {  
			var reader = new FileReader();  
			reader.onload = function(evt){
				$('<div class="new3"><img style="width:100%;height:120px;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic3);"/></div>').appendTo('#pic3');
			}    
			reader.readAsDataURL(file.files[0]);  
		}else {  
			prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
			var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
			console.log(file,file.value);
		}
	}
	
	function uploadpic3(pic){
		var hiddenFrom = document.getElementById("form1");
		var input = document.createElement("input");
		var num = $("#num").val();
        $.ajax({
            type: "POST",
            url:"${ctx}/app/img/method/upload",
            data:{'baseImg':pic,
                'businessType':'disclose'},
            async: true,
            error: function(request) {
                alert("图片过大，上传出现问题，请联系技术人员。");
            },
            success: function(data) {
                if(data.length>0){
                    num++;
                    input.setAttribute("type","hidden");
                    input.setAttribute("id","num3");
                    input.setAttribute("name","photos3");
                    input.setAttribute("value", data);
                    hiddenFrom.appendChild(input);//将元素添加到form中
                    $("#num").val(num);
                }else{
                    alert("上传出现问题，请联系技术人员支持。");
                }
            }
        });
//		if(pic){
//			num++;
//			input.setAttribute("type","hidden");
//			input.setAttribute("id","num3");
//			input.setAttribute("name","photos3");
//			input.setAttribute("value", pic);
//			hiddenFrom.appendChild(input);//将元素添加到form中
//			$("#num").val(num);
//		}
	}
	function uploadpic2(pic){
		var hiddenFrom = document.getElementById("form1");
		var input =document.createElement("input");
        var num = $("#num").val();
        //此处上传图片然后将返回的urlset到表单中
        $.ajax({
            type: "POST",
            url:"${ctx}/app/img/method/upload",
            data:{'baseImg':pic,
                'businessType':'disclose'},
            async: true,
            error: function(request) {
                alert("图片过大，上传出现问题，请联系技术人员。");
            },
            success: function(data) {
                if(data.length>0){
					input.setAttribute("type","hidden");
					input.setAttribute("id","num2");
					input.setAttribute("name","photos2");
					input.setAttribute("value", data);
					hiddenFrom.appendChild(input);//将元素添加到form中
					$("#num").val(num);
                }else{
                    alert("上传出现问题，请联系技术人员支持。");
                }
            }
        });


//		if(pic){
//			num++;
//			input.setAttribute("type","hidden");
//			input.setAttribute("id","num2");
//			input.setAttribute("name","photos2");
//			input.setAttribute("value", pic);
//			hiddenFrom.appendChild(input);//将元素添加到form中
//			$("#num").val(num);
//		}
	}
	function uploadpic1(pic){
		var hiddenFrom = document.getElementById("form1");
		var input =document.createElement("input");
		//此处上传图片然后将返回的urlset到表单中，若表单中原来有一个url，则传递到后台删掉原有服务器上的老图片
        $.ajax({
            type: "POST",
            url:"${ctx}/app/img/method/upload",
            data:{'baseImg':pic,
				  'businessType':'disclose'},
            async: true,
            error: function(request) {
                alert("图片过大，上传出现问题，请联系技术人员。");
            },
            success: function(data) {
                if(data.length>0){
                input.setAttribute("type","hidden");
                input.setAttribute("id","num1");
                input.setAttribute("name","photos1");
                input.setAttribute("value", data);
                hiddenFrom.appendChild(input);//将元素添加到form中
                }else{
                    alert("上传出现问题，请联系技术人员支持。");
				}
            }
        });





//		if(pic){
//			input.setAttribute("type","hidden");
//			input.setAttribute("id","num1");
//			input.setAttribute("name","photos1");
//			input.setAttribute("value", pic);
//			hiddenFrom.appendChild(input);//将元素添加到form中
//		}
	}
		
	//删除按钮
	$(document).on('click', '.g-sign_list .new > a', function(){
		$(this).parent().remove();
	});

	$(document).ready(function() {
		//绑定onclick事件
		$("#submit").bind('click',disCLoseSubmit);
	});
	//根据id获取value删除服务器上的图片
	function removePic(id){
			var picmode=$("#"+id);
			if(picmode){
					var picUrl=picmode.val();
					if(picUrl!=''&&picUrl!=null){
                        $.post("${ctx}/app/img/method/removepic",{'oldUrl':picUrl},function(data){
                            if(data=="1"){
                                console.log("删除图片成功");
                            }
                        });
					}
			}
		}
	function checkPic(file){
		   if($(file).val()==''||null==$(file).val()){
			   //$(file).parent().find("img").attr("src","${ctxStatic}/mobile/modules/Manager/images/upload_pic.png");
	    		return false;
	    	}
			 if (!/.(jpg|jpeg|png|JPG)$/.test($(file).val())) {       //判断上传图片是否符合格式
			        alert('上传图片格式不正确，请重新上传jpg、png类型的图片!');
			        return false;
			    }
			 var fileSize=$(file)[0].files[0].size;
			 //alert(fileSize);
			 fileSize = Math.round(fileSize / 1024 * 100) / 5120;
			 if (fileSize >= 100) {
			        alert('照片最大尺寸大于5M，请重新上传!');
			        return false;
			    }
			 return true;
		}
	</script>
</body>
</html>