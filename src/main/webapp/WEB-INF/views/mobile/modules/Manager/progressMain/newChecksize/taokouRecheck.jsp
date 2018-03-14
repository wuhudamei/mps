<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>套口复尺</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/review_cm.css"/>
</head>
<body>
	<div class="g-materi">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/newChecksizeList"></a>
			<h2 class="title">套口复尺</h2>
		</header>
		<form id="form1" action="" method="post">
		<input type="text" hidden="hidden" id="num" name="num" value="0">
		<input type="text" hidden="hidden" name="orderID" value="${orderID }">
		<input type="text" hidden="hidden" id="addData" name="size" value="">
		<div class="zone shadow">
			<div class="funs">
				<span class="font28 flo_left col_6">预计安装日期</span>
				<p class="cal_container">
					<input id="txtBeginDate" class="goods_date pad_top2 pad_btm2" type="text" name="planInstallDate" readonly="readonly" 
						placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
				</p>
			</div>
			<div class="funs">
				<span class="font28 flo_left col_6">上传照片</span>
				<div class="up">
					<img src="${ctxStatic}/mobile/modules/Manager/images/pic_load.png" alt="上传照片">
					<input type="file" name="pic" onchange="preview(this)" id="pic" multiple="multiple" value="请选择图片">
				</div>
				<a class="wtc_btn" href="javascript:void(0)">已上传<span id="picLength">0</span>张</a>
			</div>
		</div>
		<!-- 加一项 -->
		<ul class="show_sec mar_btm50">
		</ul>
		</form>
		<footer>
			<a class="add_btn" href="javascript:void(0)"><span class="btn font28 bor_rht">加一项</span></a>
			<a href="javascript:void(0)" id="taokouSubmit"><span class="btn font28">提交</span></a>
		</footer>
		<!-- 照片弹出层 -->
		<div class="mask mask1 undis">
			<div class="pic_container clearfix" id="foo">
				<%-- <div class="pic_wraper clearfix">
					<img src="${ctxStatic}/mobile/modules/Manager/images/photo.png" alt="收货单">
					<a class="del_btn" href="javascript:void(0)">删除</a>
				</div> --%>
			</div>
			<a class="back" href="javascript:void(0)">返回</a>
		</div>
		<!-- 查看大图弹出层 -->
		<div class="mask mask2 undis">
			<div class="big_pic_wraper">
				<img class="big_pic" id="big_pic" alt="复尺套口照片">
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>
	<script>
		$(document).ready(function() {
			//绑定onclick事件
			$("#taokouSubmit").bind('click',taokouSubmit);
		});
		
		$(function(){
			$(document).on('click','.wtc_btn',function(){
				$('.mask1').removeClass('undis');
			});
			$(document).on('click','.pic_wraper img',function(){
				var purl = $(this).attr("src");
				$("#big_pic").attr("src",purl);
				$('.mask2').removeClass('undis');
			});
			$(document).on('click','.mask2',function(){
				$('.mask2').addClass('undis');
			});
			$(document).on('click','.back',function(){
				$('.mask1').addClass('undis');
			});
			$(document).on('click','.del_btn',function(){
				$(this).parent().remove();
			});

			// 日历控件
	    	var now = new Date(),
    		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
    		end = new Date(now.setFullYear(now.getFullYear()+1)),
    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');
	    	$("#txtBeginDate").val(start);//初始化当前日期
	    	var lcalendar = start+','+end;
	    	// 将时间限制加到input标签上。
	    	$('#txtBeginDate').attr('data-lcalendar', lcalendar);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
			// 单选按钮
			$(document).on('click','label',function(){
				var thisName = $(this).attr('data-name');
			    $('label[data-name="'+thisName+'"]').removeClass('checked');
			    $(this).addClass('checked');
			});
			// 下拉菜单
			$(document).on('click','.select-value',function(){
				$(this).parent().find('.options').toggle();
			});
			$(document).on('click','.options span',function(){
				$(this).parent().parent().find('.select-value span').html($(this).html());
				$(this).parent().parent().find('.pos').val($(this).html());//往隐藏input class="pos"赋值
				$(this).parent().css({'display':'none'});
			});
			// 查看示意图
			$(document).on('click','.pic_btn',function(){
				$(this).parent().find('.mask_eg').removeClass('undis');
			});
			$(document).on('click','.mask_eg',function(){
				$('.mask_eg').addClass('undis');
			});

			var taokouList = "${taokouList}";
			var sub = taokouList.toString().substr(taokouList.indexOf("[")+1,taokouList.indexOf("]")-1).split(",");

			// 加一项
			var count = 0;
			function add_li(id){
				$('.show_sec').append('<li class="font0 bg_f mar_btm2 shadow"><ul class="relative border_btm pad_left3 pad_rgt3 pad_top2 pad_btm2" id="border_btm'+id+'">')
				$("#border_btm"+id).append('<a id="'+id+'" class="move_btn" href="javascript:void(0)">删除</ a>')
				$("#border_btm"+id).append('<li class="line_hgt clearfix"><span class="font28 col_6 flo_left">复尺位置：</span><div class="select_total"><div class="select-area"><div class="select-value"><input hidden="hidden" name="position" class="pos" value="" id="pos'+id+'"/><span class="font24 col_blue mar_left1" id="id'+id+'"></span><i class="select-btn"></i></div><div class="options undis" id="div'+id+'">')
				if(sub.length > 0){
					for(var i = 0; i < sub.length; i++ ){
						$("#div"+id).append("<span>"+sub[i]+"</span>")
					}
				}
				$(".options").append('</div></div></div>')
				$(".line_hgt").append('</li>')
				$("#border_btm"+id).append('<li class="line_hgt7 clearfix"><span class="font28 col_6 flo_left">包套方式：</span><p class="p_right"><span class="radio_span marrgt1"><input type="radio" id="a1' + id +'" name="packageCover'+id+'" value="0"><label class="font26 col_6 mar_rgt10 checked" data-name="meth' + id +'" for="a1' + id +'">单　　包</label></span><span class="radio_span"><input type="radio" id="b1' + id +'" name="packageCover'+id+'" value="1"><label class="font26 col_6 mar_rgt10" data-name="meth' + id +'" for="b1' + id +'">双　　包</label></span></p></li>')
				$("#border_btm"+id).append('<li class="line_hgt7 font0 clearfix"><span class="font28 col_6">洞口宽度：</span><input id="holeWidth'+id+'" name="holeWidth" class="font28 col_6 wid_314 blue_border pad_top1 pad_btm1 mar_rgt2" type="text" onkeyup="validationData('+id+',this);" maxlength="4"><span class="font28 col_6">mm</span></li>') 
				$("#border_btm"+id).append('<li class="line_hgt7 font0 clearfix"><span class="font28 col_6">洞口高度：</span><input id="holeHigh'+id+'" name="holeHigh" class="font28 col_6 wid_314 blue_border pad_top1 pad_btm1 mar_rgt2" type="text" onkeyup="validationData('+id+',this);" maxlength="4"><span class="font28 col_6">mm</span></li><li class="line_hgt7 font0 clearfix">') 
				$("#border_btm"+id).append('<span class="font28 col_6">洞口厚度：</span><input id="thick'+id+'" name="thick" class="font28 col_6 wid_314 blue_border pad_top1 pad_btm1 mar_rgt2" type="text" onkeyup="validationData('+id+',this);" maxlength="4"><span class="font28 col_6">mm</span></li>') 
				$(".font0").append('</ul>')
				/* $("#border_btm"+id).append('<a class="pic_btn font28 col_blue" href="javascript:void(0)">查看示意图</a><div class="mask mask_eg undis"><div class="pic_div"><p class="pic_tit">示意图</p><div class="pic_container"><div class="pic_wrapper" id="photo'+id+'">')
				$("#photo"+id).append('<img src="${ctxStatic}/mobile/modules/Manager/images/eg_pic.png" alt="示意图"></div></div></div></div></li>') */;
				//$('.show_sec').append(lis.toString());
				var data = $("#addData").val();
				var html = "";
				if(data == ""){
					$("#addData").val(id);
				}else{
					html += data + "," + id;
					$("#addData").val(html);
				}
			}
			$(document).on('click','.add_btn',function(){
				add_li(count);
				count++;
			});
			$(document).on('click','.move_btn',function(){
				$(this).parent().remove();
				var id = $(this).attr("id");
				var split = ($("#addData").val()).split(",");
				$.each(split,function(index,item){
		            // index是索引值（即下标）   item是每次遍历得到的值；
		            if(id == item){
		            	split.splice(index,1);
		            }
		            $("#addData").val(split);
		     	});
			});
		}());
		
		//校验输入正整数
		function validationData(index,obj){
			//先把非数字的都替换掉，除了数字和. 
	        obj.value = obj.value.replace(/[^\d.]/g,""); 
	        //必须保证第一个为数字而不是. 
	        obj.value = obj.value.replace(/^\./g,""); 
	      	//保证只有输入正整数 
	        obj.value = obj.value.replace(/[^\d]/g,'');
		}
		
		//图片上传显示 
		function preview(file) {
			var number = $("#num").val();
			number++;
			var prevDiv = $('.camera'); 
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic_wraper" id="div('+number+')"><img style="width:100%;height:120px;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic);"/><a class="del_btn" href="javascript:void(0)" onclick="delPic('+number+')">删除</a></div>').appendTo('.pic_container');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		}
		
		function uploadpic(pic){
			var hiddenFrom = document.getElementById("form1");
			var input =document.createElement("input");
			var num = $("#num").val();
			if(pic){
				num++;
				input.setAttribute("type","hidden");
				input.setAttribute("id","num"+num);
				input.setAttribute("name","photos");
				input.setAttribute("value", pic);
				hiddenFrom.appendChild(input);//将元素添加到form中
				$("#num").val(num);
			}
			var divNum = $("#foo > div").size();
			
			$("#picLength").text(divNum);
		}
		
		function delPic(index){
			$("#num"+index).attr('name','');//每次进来清空当前input中name属性值
			$("#num"+index).val('');//每次进来清空当前input中value属性值
			globalUtil.fn.alert('删除图片操作成功',1.0);
			$("#div"+index).remove();
			var divNum = $("#foo > div").size();//图片总个数
			$("#picLength").text(divNum-1);
		}
		
		//submit
		function taokouSubmit(){
			var addNum = $("#addData").val();
			if(addNum == ""){
				globalUtil.fn.alert("必须加一项...",1.0);
				return false;
			}
			
			var str = addNum.split(",");
			for(var i=0; i<str.length; i++){
				if($("#id"+str[i]).text() == ""){
					globalUtil.fn.alert("复尺位置不能为空...",1.0);
					return false;
				}
				//alert($("#pos"+i).val());
				//洞口宽度
				if($("#holeWidth"+str[i]).val() == ""){
					globalUtil.fn.alert("复尺位置：【"+$("#id"+str[i]).text()+"】,洞口宽度不能为空...",1.0);
					return false;
				}
				//洞口高度
				if($("#holeHigh"+str[i]).val() == ""){
					globalUtil.fn.alert("复尺位置：【"+$("#id"+str[i]).text()+"】,洞口高度不能为空...",1.0);
					return false;
				}
				//洞口厚度
				if($("#thick"+str[i]).val() == ""){
					globalUtil.fn.alert("复尺位置：【"+$("#id"+str[i]).text()+"】,洞口厚度不能为空...",1.0);
					return false;
				}
			}
			
			$("#taokouSubmit").css("color","#CCC");
			$("#taokouSubmit").unbind("click");
			var options ={
				url : "${ctx}/app/manager/taokouSubmitData",
				type: "post",
				success : function(data){
					if(data == 0){
						 globalUtil.fn.alert("操作成功...",2.0);
						 window.location.href = "${ctx}/app/manager/newChecksizeList";
					  }
					if(data == 1){
					  	 globalUtil.fn.alert("提交复尺数据失败...",2.0);
					  }
					if(data == 2){
					  globalUtil.fn.alert("提交套口数据失败...",2.0);
					}
					if(data == 3){
					  globalUtil.fn.alert("提交图片数据状态失败...",2.0);
					}
					if(data == "error"){
						globalUtil.fn.alert("'请5分钟后再提交！'",2.0);
					}
				}
			}
			//ajax提交form
			$("#form1").ajaxSubmit(options);
		}
	</script>
</body>
</html>