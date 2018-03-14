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
	<title>确认开工</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/start.css"/>
	
	<style>
		.font_num{position: absolute;right: 17%;}
	</style>
</head>
<body>
    <input type="text" hidden="hidden" id="shit"  value="1">
	<div class="g-start">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/confirmStart"></a>
			<h2 class="title">确认开工</h2>
		</header>
		<form id="jvForm" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" id="dateCompare" name="dateCompare" value=""/>
		<input type="hidden" id="num" value="0"/>
		<input type="hidden" id="contractStartDate" value="<fmt:formatDate type="date" value='${confirmStartOrder.contractStartDate }' pattern="yyyy-MM-dd"/>"/>
		<input type="hidden" name="orderId" value="${confirmStartOrder.id }"/>
		<input type="hidden" name="storeId" value="${confirmStartOrder.storeId }"/>
		<input type="hidden" name="houseIsNew" value="${confirmStartOrder.houseIsNew }"/>
		<input type="hidden" name="projectMode" value="${confirmStartOrder.projectMode }"/>
		<section class="start_section">
			<h3>${confirmStartOrder.communityName }-${confirmStartOrder.buildNumber }-${confirmStartOrder.buildUnit }-${confirmStartOrder.buildRoom }-${confirmStartOrder.customerName }</h3>
				<ul class="shadow">
					<li class="clearfix">
						<span class="line_hgt8">合同开工日期：</span>
						<p><fmt:formatDate type="date" value="${confirmStartOrder.contractStartDate }" pattern="yyyy-MM-dd"/></p>
					</li>
					<li class="clearfix">
						<span>实际开工日期：</span>
						
						<p>
							<input id="txtBeginDate" class="date" type="text" readonly="readonly"
							name="input_date" placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01"/>
						</p>
						<div class="calMain"></div>
					</li>
					<li class="clearfix">
						<span>开工客户签字：</span>
						<p>
							<input type="radio" id="yes1" name="isNeedSign" value="1">
							<label data-name="isNeedSign" for="yes1" class="checked">是</label>
							<input type="radio" id="no1" name="isNeedSign" value="0">
							<label data-name="isNeedSign" for="no1">否</label>
						</p>
					</li>
					<div id="dis" class="undis">
					<li class="clearfix">
						<span>开工延期类型：</span>
						<p>
							<input type="radio" id="cons" name="delayType" value="0">
							<label data-name="delayType" for="cons" class="checked">客户原因</label>
							<input type="radio" id="comp" name="delayType" value="1">
							<label data-name="delayType" for="comp">公司原因</label>
						</p>
					</li>
					<li class="clearfix" style="margin-top:.2rem;">
						<span>开工延期说明：</span>
						<p><textarea id="startRemark" name="startRemark"></textarea></p>
						<!-- <p class="font_num" style="font-size:12px;">还可以输入<i id="word">200</i>个字</p> -->
					</li>
					<p></p>
					</div>
					<li class="clearfix" style="margin-top:.2rem;">
						<span>自装延期天数：</span>
						<p><input type="text" id="decorateDelayDays" name="decorateDelayDays" 
							onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/></p>
					</li>
					<li class="clearfix">
						<span>自装客户签字：</span>
						<p>
							<input type="radio" id="yes2" name="isSelfDecorateNeedSign" value="1">
							<label data-name="isSelfDecorateNeedSign" for="yes2" class="checked">是</label>
							<input type="radio" id="no2" name="isSelfDecorateNeedSign" value="0">
							<label data-name="isSelfDecorateNeedSign" for="no2">否</label>
						</p>
					</li>
					<li class="clearfix" style="margin-top:.2rem;">
						<input type="hidden" class="pics" name="pics" value="">
						<span>上传开工照片：</span>
						<p class="up">
							<div class="camera" id="camera1" href="javascript:void(0)">
								<img class="postage_btn" src="${ctxStatic}/mobile/modules/Manager/images/camera_icon.png" alt="上传图片">
								<input type="file" name="pic" onchange="preview(this)" id="pic" multiple="multiple" value="请选择图片">
							</div>
							<!-- <div class="pic_container" id="uploaddone"></div> -->
						</p>
					</li>
					<a id="start" href="javaScript:void(0)">确认开工</a>
				</ul>
		</section>
		</form>
	</div>
	<form id="hiddenFrom">
	</form>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar_confirm_start.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>
	<script>


	$(document).ready(function() {
		//绑定onclick事件
		$("#start").bind('click',submitData);
	});

  //alert(Date.parse($('#txtBeginDate').val()) > Date.parse($('#contractStartDate').val()));







	function submitData(){
		var orderId = ${confirmStartOrder.id };
		var storeId = ${confirmStartOrder.storeId };
		var houseIsNew = ${confirmStartOrder.houseIsNew };
		var count = $("#num").val();
		
		if(Date.parse($('#txtBeginDate').val()) > Date.parse($('#contractStartDate').val())){
			//延期类型
			var delayType = $("input[name='delayType']:checked").val()/* $('input:radio:checked').val() */;
			var remark = $("#startRemark").val();
			if(remark == ""){
				globalUtil.fn.alert("开工延期说明不能为空...",1.0);
			  return false;
			}
			if(remark.length > 200){
				globalUtil.fn.alert("超过200个汉字...",1.0);
				return false;
			}
		}else{
			$("#dateCompare").val("3");
		}
		
		//自装延期天数
		var decorateDelayDays = $("#decorateDelayDays").val();
		if(decorateDelayDays == ""){
			globalUtil.fn.alert("自装延期天数不能为空...",1.0);
			return false;
		}
		
		//上传图片
		if(count <= 0){
			globalUtil.fn.alert("请至少上传一张图片...",1.0);
			return false;(html);
		}
		
		var reditUrl = "${ctx}/app/manager/confirmStart";//成功后跳转地址
		var orderStatusNumber = $("#orderStatusNumber").val();

		$("#startRemark").val( $("#startRemark").val().substring(0,200) );
		
		$("#start").css("color","#CCC");
		$("#start").unbind("click");
		//去掉file的input中无效的base64的串儿
        $(".file").each(function(){
            $(this).remove();
        });
		var options ={
			url : "${ctx}/app/manager/updateById",
			type: "POST",
			success : function(data){
				if(data == 0){
					globalUtil.fn.alert('操作成功...',2.0);			
					window.location.href = reditUrl;
				}else if(data == 1){
					globalUtil.fn.alert("订单id为空，请联系管理员...",2.0);
				}else if(data == 2){
					globalUtil.fn.alert("该订单已经确认开工，请不要重复开工",2.0);
				}else if(data == 3){
					globalUtil.fn.alert("该订单没有工程模式，请联系管理员...",2.0);
				}else if(data == 4){
					globalUtil.fn.alert("请至少上传一张图片，请联系管理员...",2.0);
				}else if(data == 5){
					globalUtil.fn.alert("工程进度节点为空，请联系管理员...",2.0);
				}else if(data == 6){
					globalUtil.fn.alert("订单安装项列表为空，请联系管理员...",2.0);
				}else{
					globalUtil.fn.alert("保存失败，请联系管理员...",2.0);
				}
			}
		}
		//ajax提交FORM
		$("#jvForm").ajaxSubmit(options);
	}
	
	//图片上传显示 
	function preview(file) {
		if(!checkPic(file)){
			return false;
		}
        var num = $("#shit").val();
		var prevDiv = $('.camera'); 
		if (file.files && file.files[0]) {  
			var reader = new FileReader();  
			reader.onload = function(evt){
				$('<div class="pic"><img style="width:100%;height:100px;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic);" id= "'+num+'"/><a class="delBtn" href="javascript:void(0)">删除</a></div>').insertAfter('.camera');
			}    
			reader.readAsDataURL(file.files[0]);  
		}else {  
			prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
			var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
			console.log(file,file.value);
		}
	}
	
	//删除按钮
	$(document).on('click', '.delBtn', function(){
        var numReal=$(this).prev().attr("id");
        $(("#num"+numReal)).remove();

        var num = $("#num").val();
        num--;
        $("#num").val(num);

        //$(this).parent().remove();
		$(this).parent().remove();
		//$("#num").val("0");
	});
	
	function uploadpic(pic){
		var hiddenFrom = document.getElementById("jvForm");
		var input =document.createElement("input");
		var num = $("#num").val();
        var shit =	$("#shit").val();
		if(pic){
			num++;
			input.setAttribute("type","hidden");
			input.setAttribute("id","num"+shit);
			input.setAttribute("name","photos");
			input.setAttribute("value", pic);
			hiddenFrom.appendChild(input);//将元素添加到form中
			shit++;
			$("#num").val(num);
            $("#shit").val(shit);
		}
	}
	
	//样式js
    $(function(){
    	
    	
    	
    	
		$('label').click(function(){
		  /* var radioId = $(this).attr('id');
		  $('label').removeAttr('class') && $(this).attr('class', 'checked');
		  $('input[type="radio"]').removeAttr('checked') && $('#' + radioId).attr('checked', 'checked'); */
			var thisName = $(this).attr('data-name');
			$('label[data-name="'+thisName+'"]').removeAttr('class') && $(this).attr('class', 'checked');
		});
		
		var projectMode = "${projectMode}";
		if(projectMode!=1 && projectMode!=4){
			//不是产业模式 不做限制
			 var now = new Date(),
	    		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
	    		start = new Date(now.setDate(now.getDate()+3)),
	    		// start = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');
	    		end = new Date(now.setFullYear(now.getFullYear()+5)),
	    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');
	    		$('#txtBeginDate').val(now);
	    		var lcalendar = start+','+end;
	    	// 将时间限制加到input标签上。
	    	$('#txtBeginDate').attr('data-lcalendar', lcalendar);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
			
			
		}else{
			//产业有时间限制
			
       		// 获取当前日期，结束日期
    	var now = new Date(),
   		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
   		end = new Date(now.setFullYear(now.getFullYear()+1)),
   		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');

    	$('#txtBeginDate').val(now);
    	/* var lcalendar = start+','+end;
    	// 将时间限制加到input标签上。
    	 $('#txtBeginDate').attr('data-lcalendar', lcalendar);
		var calendar = new lCalendar();
		calendar.init({
		    'trigger': '#txtBeginDate',//标签id
		    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
		});  */
		
		
		
			
		}
		
		$('#txtBeginDate').val(globalUtil.fn.formatDate(new Date(), 'yyyy-MM-dd'));
		
		if(Date.parse($('#txtBeginDate').val()) > Date.parse($('#contractStartDate').val())){
			$("#dis").removeClass("undis");
		}
		

    });
	    
    $("#startRemark").keyup(function(){   
    	if($("#startRemark").val().length > 200){
    	   $("#startRemark").val( $("#startRemark").val().substring(0,200) );
    	}
    	$("#word").text( 200 - $("#startRemark").val().length ) ;
    });
	    
    //自装延期天数校验
    function changeValue(obj){
    	//先把非数字的都替换掉，除了数字和. 
        obj.value = obj.value.replace(/[^\d.]/g,""); 
        //必须保证第一个为数字而不是. 
        obj.value = obj.value.replace(/^\./g,""); 
        //保证只有出现一个.而没有多个. 
        obj.value = obj.value.replace(/\.{2,}/g,"."); 
        //保证.只出现一次，而不能出现两次以上 
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        //只能输入两个小数 
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');
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