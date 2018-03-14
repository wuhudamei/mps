<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单投诉问题管理</title>
	<meta name="decorator" content="default"/>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/bizOrderComplaint/css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/bizOrderComplaint/css/complain.css"/>
<%-- 		<script type="text/javascript" src="${ctxStatic}/bizOrderComplaint/jquery/jquery-2.1.1.min.js"></script> --%>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			getModelList()
		});
		
		$(document).on('click','.delItemBtn',function(){
			$(this).parent().remove();
		});
		$(document).on('click','.del_pic',function(){
			$(this).parent().remove();
		});
		$(document).on('click','.checkWrap label',function(){
			if ($(this).hasClass('nochecked')) {
				$(this).removeClass('nochecked').addClass('checked');
			}else{
				$(this).removeClass('checked').addClass('nochecked');
			}
		});
		var index =1;
		$(document).on('click','.addBtn',function(){
			++index;
			var num = ++$('.itemList').length;

			var itemList = '<div class="mb1d mb12 rela itemList">'+
							'<div class="lab bor_btm_d7">'+
								'<p class="f16 col3 bold pb20">投诉问题' + num + '</p>'+
								'<p class="col3 f14 mb20 clearfix">'+
									'<label class="f14 col3" for="">问题分类：</label>'+ 
									' <select id="select' + num + '" class="input-medium" name="bizOrderCompProblemList['+index+'].complaintProblemTypeId"   onchange="gradeChangediv(this)"  > '+
									'</select>'+
									'<span  id="spanid1' + num + '" class="f14 col3 ml20">对应任务包：请选择问题分类</span>'+
									'<span id="spanid2' + num + '" class="f14 col3 ml30">被投诉对象：请选择问题分类</span>'+
									'<div  id="div1' + num + '" ></div>'+
									'<div  id="div2' + num + '" ></div>'+
									'<input id="input' + num + '" type="hidden" value=""></input>'+
								'</p>'+
								'<div class="col3 f14 mb20 clearfix">'+
									'<span class="fl pt8">问题事项：</span>'+
									'<p   id="p1' + num + '"class="overflow checkWrap">'+
									'</p>'+
								'</div>'+
								'<div class="col3 f14 pb20 clearfix">'+
									'<span class="fl pt8">问题内容：</span>'+
									'<textarea  name="bizOrderCompProblemList['+index+'].complaintProblemNr" class="complainText" placeholder="最多输入100个汉字"></textarea>'+
								'</div>'+
								'<div class="col3 f14 pb20 clearfix">'+
									'<span class="fl pt8">上传附件：</span>'+
									'<div class="pics font0 clearfix">'+
										'<div class="pic camera" href="javascript:void(0)">'+
											'<img src="../../images/upPic.png" alt="">'+
											'<input type="file" accept="image/*" onchange="preview(this)">'+
										'</div>'+
										'<div class="pic">'+
											'<img src="../../images/eg.png" alt="">'+
											'<a class="del_pic" href="javascript:void(0)"></a>'+
										'</div>'+
									'</div>'+
								'</div>'+
							'</div>'+
							'<a class="delItemBtn" href="javascript:;">删除事项</a>'+
						'</div>';
			$('.itemWrapper').append(itemList);
			getModelList(num);
			
		});
		
		//上传图片事件
	  function PreviewImage1(imgFile) 
	   { 
	    var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;      
	    if(!pattern.test(imgFile.value)) 
	    { 
	     alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
	     imgFile.focus(); 
	    } 
	    else 
	    { 
	     var path; 
	     
	     if(document.all)//IE 
	     { 
	      imgFile.select(); 
	      path = document.selection.createRange().text; 
	      document.getElementById("imgPreview1").innerHTML=""; 
	      document.getElementById("imgPreview1").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
	     } 
	     else//FF 
	     { 
	      path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
	      document.getElementById('imgPreview1').setAttribute('src',path);
	     } 
	    } 
	   } 
		//上传图片事件
	  function PreviewImage2(imgFile) 
	   { 
	    var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;      
	    if(!pattern.test(imgFile.value)) 
	    { 
	     alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
	     imgFile.focus(); 
	    } 
	    else 
	    { 
	     var path; 
	     
	     if(document.all)//IE 
	     { 
	      imgFile.select(); 
	      path = document.selection.createRange().text; 
	      document.getElementById("imgPreview2").innerHTML=""; 
	      document.getElementById("imgPreview2").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
	     } 
	     else//FF 
	     { 
	      path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
	      document.getElementById('imgPreview2').setAttribute('src',path);
	     } 
	    } 
	   } 
		//上传图片事件
	  function PreviewImage3(imgFile) 
	   { 
	    var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;      
	    if(!pattern.test(imgFile.value)) 
	    { 
	     alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
	     imgFile.focus(); 
	    } 
	    else 
	    { 
	     var path; 
	     
	     if(document.all)//IE 
	     { 
	      imgFile.select(); 
	      path = document.selection.createRange().text; 
	      document.getElementById("imgPreview3").innerHTML=""; 
	      document.getElementById("imgPreview3").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
	     } 
	     else//FF 
	     { 
	      path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
	      document.getElementById('imgPreview3').setAttribute('src',path);
	     } 
	    } 
	   } 
		//上传图片事件
	  function PreviewImage4(imgFile) 
	   { 
	    var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;      
	    if(!pattern.test(imgFile.value)) 
	    { 
	     alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
	     imgFile.focus(); 
	    } 
	    else 
	    { 
	     var path; 
	     
	     if(document.all)//IE 
	     { 
	      imgFile.select(); 
	      path = document.selection.createRange().text; 
	      document.getElementById("imgPreview4").innerHTML=""; 
	      document.getElementById("imgPreview4").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
	     } 
	     else//FF 
	     { 
	      path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
	      document.getElementById('imgPreview4').setAttribute('src',path);
	     } 
	    } 
	   } 
		//上传图片事件
	  function PreviewImage5(imgFile) 
	   { 
	    var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;      
	    if(!pattern.test(imgFile.value)) 
	    { 
	     alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
	     imgFile.focus(); 
	    } 
	    else 
	    { 
	     var path; 
	     
	     if(document.all)//IE 
	     { 
	      imgFile.select(); 
	      path = document.selection.createRange().text; 
	      document.getElementById("imgPreview5").innerHTML=""; 
	      document.getElementById("imgPreview5").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
	     } 
	     else//FF 
	     { 
	      path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
	      document.getElementById('imgPreview5').setAttribute('src',path);
	     } 
	    } 
	   } 
		//上传图片事件
	  function PreviewImage6(imgFile) 
	   { 
	    var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;      
	    if(!pattern.test(imgFile.value)) 
	    { 
	     alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
	     imgFile.focus(); 
	    } 
	    else 
	    { 
	     var path; 
	     
	     if(document.all)//IE 
	     { 
	      imgFile.select(); 
	      path = document.selection.createRange().text; 
	      document.getElementById("imgPreview6").innerHTML=""; 
	      document.getElementById("imgPreview6").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
	     } 
	     else//FF 
	     { 
	      path = URL.createObjectURL(imgFile.files[0]);
// 	      document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
	      document.getElementById('imgPreview6').setAttribute('src',path);
	     } 
	    } 
	   } 
		//项目触发的事件
		function abandonedxm(){
			var v = $("#storeId").val();
			
			window.location.href="${ctx}/cusserviceproblem/bizCusServiceProblem/ProjectView?storeId="+v;
		}

		
		function submitForm(){
			   $("#inputForm").attr("action", "${ctx}/ordercomplan/bizOrderComplaint/upload");
				$("#inputForm").submit();
			
		}
		
		var orderarea = '${bizOrderComplaint.biItembeans}';
		function initOrderarea() {
			$("#orderareaDiv").html("");
		    var dataArray = orderarea.split(",");
		    $.ajax({
		        type : 'POST',
		        dataType : 'json',
		        url : '${ctx}/cusserviceproblem/bizCusServiceProblem/ajaxgetItem',
		        data : {
		            'typeName' : $("#typeName").val(),
		        },
	            success : function(data) {
	            	var html = "";
	            	for (var i = 0; i < data.length; i++) {
	            		//alert(data[i].value +" -- "+ data[i].label);
	            		var checked = "";
	            		//alert(dataArray.length);
	            		for (var x = 0; x < dataArray.length; x++) {
	            			if (dataArray[x] == data[i].value) {
	            				checked = "checked";
	            			}
	            		}
	            		//alert("data[i].value:"+data[i].value+", dataArray[data[i].value]:" + dataArray[data[i].value] + ",checked:" + checked + ";");
	            		html += '<input id="ordersarea" type="checkbox" ' + checked + ' value="' + data[i].value + '" name="ordersarea">'
	            		+ '<label class="nochecked">'+data[i].label+'</label> ';
	            	}
	            	$("#orderareaDiv").html(html);
	            }
		    })
		}
		
		
		function getModelList( num ){ 
			 $("#select"+num).empty(); //清空
			 $("#select1").empty();
			 $.ajax({url:'${ctx}/ordercomplan/bizOrderComplaint/getModelList',
			 type:"post",
			 data:{
			 brandId : 2
			 },
			 cache: false,
			 error:function(){
			 }, 
			 success:function(data){
			 var modelList = data.modelList;
			 if(modelList && modelList.length != 0){
				 var option='<option value="">请选择</option>';
			 for(var i=0; i<modelList.length; i++){
			  option+="<option value=\""+modelList[i].id+"\"";
			  option += ">"+modelList[i].typeName+"</option>"; //动态添加数据
			 	}
			
			 $("#select1").html(option);
			 $("#select"+num).html(option);
			 	}
			 }
			 });
			}


		
		//选中分类触发的事件
		function gradeChange(thisch){
				 var selectValue=  $("#select1").val();
				  inputchecked1(selectValue)
				 $.ajax({url:'${ctx}/ordercomplan/bizOrderComplaint/gettasklList',
					 type:"post",
					 data:{
						 ComplaintProblemTypeId : selectValue
					 },
					 cache: false,
					 error:function(){
					 }, 
					 success:function(data){
						
						 var spanid1=data.ComplaintProblemType
						 if(spanid1 && spanid1.length != 0){
						 for(var i=0; i<spanid1.length; i++){
						  var packName= spanid1[i].packName
						 var dealPersonType= spanid1[i].dealPersonType
						
						 var spanid1Html = '<span  id="spanid1a" class="f14 col3 ml20">对应任务包：'+packName+'</span>'
						 var div1a=	'<input  type="hidden"  name="bizOrderCompProblemList[1].packagename" value="'+packName+'"></input>'
						 var spanid2Html = '<span  id="spanid2a" class="f14 col3 ml20">被投诉对象：'+dealPersonType+'</span>'
						 var div2a=	'<input  type="hidden"   name="bizOrderCompProblemList[1].complaintRoleType" value="'+dealPersonType+'"></input>'
											 
							 $("#spanid1a").html(spanid1Html);
							 $("#spanid2a").html(spanid2Html);
							 $("#div1a").html(div1a);
							 $("#div2a").html(div2a);
						 }
						 }
						 var heidInput= data.substring;
						 $("#input1").val(heidInput);
					 }
					 });
		}
		
		function inputchecked1(selectValue) {
			var dataArray =$("#input1").val();
		    $.ajax({
		        type : 'POST',
		        dataType : 'json',
		        url : '${ctx}/ordercomplan/bizOrderComplaint/ajaxTypeItem',
		        data : {
		        	complaintProblemTypeId : selectValue
		        },
	            success : function(data) {
	            	var html = "";
	            	for (var i = 0; i < data.length; i++) {
	            		//alert(data[i].value +" -- "+ data[i].label);
	            		var checked = "";
	            		//alert(dataArray.length);
	            		for (var x = 0; x < dataArray.length; x++) {
	            			if (dataArray[x] == data[i].value) {
	            				checked = "checked";
	            			}
	            		}
	            		//alert("data[i].value:"+data[i].value+", dataArray[data[i].value]:" + dataArray[data[i].value] + ",checked:" + checked + ";");
	            		html += '<input id="ordersarea" type="checkbox" ' + checked + ' value="' + data[i].value + '" name="ordersarea">'
	            		+ '<label class="nochecked">'+data[i].label+'</label> ';
	            	}
	            	$("#p1a").html(html);
	            }
		    })
		}
		
		
		//获取check值
		function chk(){ 
			var obj=document.getElementsByName('test'); //选择所有name="'test'"的对象，返回数组 
			//取到对象数组后，我们来循环检测它是不是被选中 
			var s=''; 
			for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked) s+=obj[i].value+','; //如果选中，将value添加到变量s中 
			} 
			//那么现在来检测s的值就知道选中的复选框的值了 
			alert(s==''?'你还没有选择任何内容！':s); 
			} 

			function jqchk(){ //jquery获取复选框值 
			var chk_value =[]; 
			$('input[name="test"]:checked').each(function(){ 
			chk_value.push($(this).val()); 
			}); 
			alert(chk_value.length==0 ?'你还没有选择任何内容！':chk_value); 
			} 
		
		
		
		
		 function gradeChangediv( thisth){
			  var num =thisth.id.substr(6,thisth.id.length);
			 var selectValue=  $('#'+thisth.id).val();
			  inputchecked(num,selectValue)
			 $.ajax({url:'${ctx}/ordercomplan/bizOrderComplaint/gettasklList',
				 type:"post",
				 data:{
					 ComplaintProblemTypeId : selectValue
				 },
				 cache: false,
				 error:function(){
				 }, 
				 success:function(data){
					
					 var spanid1=data.ComplaintProblemType
					 if(spanid1 && spanid1.length != 0){
					 for(var i=0; i<spanid1.length; i++){
					  var packName= spanid1[i].packName
					 var dealPersonType= spanid1[i].dealPersonType
					
					 var spanid1Html = '<span  id="spanid1a" class="f14 col3 ml20">对应任务包：'+packName+'</span>'
	 				var inputdiv1='<input  type="hidden"  name="bizOrderCompProblemList['+num+'].packagename" value="'+packName+'"></input>'
					 var spanid2Html = '<span  id="spanid2a" class="f14 col3 ml20">被投诉对象：'+dealPersonType+'</span>'
					 var inputdiv2='<input  type="hidden"   name="bizOrderCompProblemList['+num+'].complaintRoleType" value="'+dealPersonType+'"></input>'
					
						 $("#spanid1"+num).html(spanid1Html);
						 $("#spanid2"+num).html(spanid2Html);
						 $("#div1"+num).html(inputdiv1);
						 $("#div2"+num).html(inputdiv2);
					 }
					 }
					 var heidInput= data.substring;
					 $("#input"+num).val(heidInput);
				 }
				 });
		 }
		
			function inputchecked(num,selectValue) {
				var dataArray =$("#input"+num).val();
			    $.ajax({
			        type : 'POST',
			        dataType : 'json',
			        url : '${ctx}/ordercomplan/bizOrderComplaint/ajaxTypeItem',
			        data : {
			        	complaintProblemTypeId : selectValue
			        },
		            success : function(data) {
		            	var html = "";
		            	for (var i = 0; i < data.length; i++) {
		            		//alert(data[i].value +" -- "+ data[i].label);
		            		var checked = "";
		            		//alert(dataArray.length);
		            		for (var x = 0; x < dataArray.length; x++) {
		            			if (dataArray[x] == data[i].value) {
		            				checked = "checked";
		            			}
		            		}
		            		//alert("data[i].value:"+data[i].value+", dataArray[data[i].value]:" + dataArray[data[i].value] + ",checked:" + checked + ";");
		            		html += '<input id="ordersarea" type="checkbox" ' + checked + ' value="' + data[i].value + '" name="bizOrderComplaintProblem['+num+'].complaintProblemItemIds">'
		            		+ '<label class="nochecked">'+data[i].label+'</label> ';
		            	}
		            	$("#p1"+num).html(html);
		            }
			    })
			}
			
			$(function () {
				$("#up").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120 });
				});
			
			
			
			/*
			*名称:图片上传本地预览插件 v1.1
			*作者:周祥
			*时间:2013年11月26日
			*介绍:基于JQUERY扩展,图片上传预览插件 目前兼容浏览器(IE 谷歌 火狐) 不支持safari
			*插件网站:http://keleyi.com/keleyi/phtml/image/16.htm
			*参数说明: Img:图片ID;Width:预览宽度;Height:预览高度;ImgType:支持文件类型;Callback:选择文件显示图片后回调方法;
			*使用方法: 
			<div>
			<img id="ImgPr" width="120" height="120" /></div>
			<input type="file" id="up" />
			把需要进行预览的IMG标签外 套一个DIV 然后给上传控件ID给予uploadPreview事件
			$("#up").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
			*/
			jQuery.fn.extend({
			    uploadPreview: function (opts) {
			        var _self = this,
			            _this = $(this);
			        opts = jQuery.extend({
			            Img: "ImgPr",
			            Width: 100,
			            Height: 100,
			            ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
			            Callback: function () {}
			        }, opts || {});
			        _self.getObjectURL = function (file) {
			            var url = null;
			            if (window.createObjectURL != undefined) {
			                url = window.createObjectURL(file)
			            } else if (window.URL != undefined) {
			                url = window.URL.createObjectURL(file)
			            } else if (window.webkitURL != undefined) {
			                url = window.webkitURL.createObjectURL(file)
			            }
			            return url
			        };
			        _this.change(function () {
			            if (this.value) {
			                if (!RegExp("\.(" + opts.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
			                    alert("选择文件错误,图片类型必须是" + opts.ImgType.join("，") + "中的一种");
			                    this.value = "";
			                    return false
			                }
			                if ($.browser.msie) {
			                    try {
			                        $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
			                    } catch (e) {
			                        var src = "";
			                        var obj = $("#" + opts.Img);
			                        var div = obj.parent("div")[0];
			                        _self.select();
			                        if (top != self) {
			                            window.parent.document.body.focus()
			                        } else {
			                            _self.blur()
			                        }
			                        src = document.selection.createRange().text;
			                        document.selection.empty();
			                        obj.hide();
			                        obj.parent("div").css({
			                            'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
			                            'width': opts.Width + 'px',
			                            'height': opts.Height + 'px'
			                        });
			                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src
			                    }
			                } else {
			                    $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
			                }
			                opts.Callback()
			            }
			        })
			    }
			});
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ordercomplan/bizOrderComplaint/">订单投诉问题列表</a></li>
		<li class="active"><a href="${ctx}/ordercomplan/bizOrderComplaint/form?id=${bizOrderComplaint.id}">订单投诉问题<shiro:hasPermission name="ordercomplan:bizOrderComplaint:edit">${not empty bizOrderComplaint.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ordercomplan:bizOrderComplaint:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizOrderComplaint" action="${ctx}/ordercomplan/bizOrderComplaint/upload" method="post"   enctype="multipart/form-data" class="form-horizontal">
		<sys:message content="${message}"/>		
		
		<div class="font0">
		<input id="orderId" name="orderId" type="hidden" value="${bizOrderComplaint.order.orderId}"/>
		<header><h2 class="title">投诉问题上报</h2></header>
		<section class="con">
			<div>
				<div class="item pl152">订单信息</div>
				<ul class="pl152 bg_e tab pt32 pb32 info font0">
					<li class="mb20 font0">
						<label class="f14 col3 ml2em" for="">门店：</label>
					<form:select  id="storeId" path="order.storeId" class="shop">
						
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="must2">*</span>
						<a class="searchBtn" href="javascript:void(0)" onclick="abandonedxm ()">点击查询项目</a>
						<span class="must">*</span>
					</li>
					<li class="col3 font0 mb20">
						<label class="f14 col3" for="">客户姓名：</label>
						<form:input path="order.customerName" htmlEscape="false" maxlength="16" class="itemInput" readOnly="true"  />
						<label class="f14 col3 ml1em ml110" for="">手机号：</label>
						<form:input path="order.customerPhone" htmlEscape="false" maxlength="16" class="itemInput" readOnly="true"  />
					</li>
					<li class="col3 font0 mb20">
						<label class="f14 col3" for="">项目经理：</label>
						<form:input path="order.itemManager" htmlEscape="false" maxlength="16" class="itemInput" readOnly="true"  />
						<label class="f14 col3 ml1em ml110" for="">质检员：</label>
						<form:input path="order.orderInspector" htmlEscape="false" maxlength="16" class="itemInput" readOnly="true"  />
					</li>
					<li class="col3 font0 mb20">
						<label class="f14 col3" for="">工程模式：</label>
						<form:input path="order.projectMode" htmlEscape="false" maxlength="16" class="itemInput" readOnly="true"  />
						<label class="f14 col3 ml110" for="">工程区域：</label>
						<form:input path="order.acceptArea" htmlEscape="false" maxlength="16" class="itemInput" readOnly="true"  />
					</li>
					<li class="col3 font0 mb20">
						<label class="f14 col3" for="">订单编号：</label>
						<form:input path="order.orderNumber" htmlEscape="false" maxlength="16" class="itemInput" readOnly="true"  />
						<label class="f14 col3 ml110" for="">小区名称：</label>
						<form:input path="order.communityName" htmlEscape="false" maxlength="16" class="itemInput" readOnly="true"  />
					</li>
				</ul>
			</div>
			<div>
				<div class="item pl152">投诉信息</div>
				<div class="pl152 bg_e clearfix">
					<ul class="col3 f14 mb20 comp">
						<li class="InfoTit">
							<label class="f14 col3" for="">投诉来源：</label>
					
						<form:select id="complaintSource"   path="complaintSource" class="source">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('complaintSource')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
							<span class="must2">*</span>
						</li>
						<li class="InfoTit">
							<label class="f14 col3" for="">投诉人：</label>
						<form:input  id="complaintPersonName" path="complaintPersonName" htmlEscape="false" maxlength="16" class="itemInput"  />
						</li>
						<li class="InfoTit">
							<label class="f14 col3" for="">投诉人手机号：</label>
						<form:input  id="complaintPersonPhone" path="complaintPersonPhone" htmlEscape="false" maxlength="16" class="itemInput"   />
						</li>
					</ul>
					<section class="itemWrapper">
						<div class="mb1d mb12 itemList">
							<div class="lab bor_btm_d7">
								<p class="f16 col3 bold pb20">投诉问题1</p>
								<p class="col3 f14 mb20 clearfix">
									<label class="f14 col3" for="">问题分类：</label>
							<select  id="select1" onchange="gradeChange(this)"   name="bizOrderCompProblemList[1].complaintProblemTypeId" class="input-medium">
								

							</select>
									<span class="must2">*</span>
									<span id="spanid1a"  class="f14 col3 ml20"> 对应任务包：</span>
									<span id="spanid2a" class="f14 col3 ml30"> 被投诉对象: </span>
									<div id="div1a"></div>
									<div id="div2a"></div>
								<input id="input1" type="hidden" value=""></input>
								</p>
								<div class="col3 f14 mb20 clearfix">
									<span class="fl pt8">问题事项：</span>
									<div   id="p1a" class="overflow checkWrap">
									</div>
								</div>
								
								<div class="col3 f14 pb20 clearfix">
									<span class="fl pt8">问题内容：</span>
									<textarea  name="  bizOrderCompProblemList[1].complaintProblemNr" class="complainText" placeholder="最多输入100个汉字"></textarea>
								</div>
								<div class="col3 f14 pb20 clearfix" style="margin :0px auto; width:990px;">
									<span class="fl pt8">上传附件：</span>
									<div class="pics font0 clearfix">
										<div  class="pic camera" href="javascript:void(0)">
											<img id="imgPreview1" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">
											<input type="file" name="photo" accept="image/*" onchange="PreviewImage1(this)" multiple="multiple">
										</div>
											<div  class="pic camera" href="javascript:void(0)">
											<img id="imgPreview2" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">
											<input type="file" name="photo" accept="image/*" onchange="PreviewImage2(this)" multiple="multiple">
										</div>
										<div  class="pic camera" href="javascript:void(0)">
											<img id="imgPreview3" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">
											<input type="file"   name="photo" accept="image/*" onchange="PreviewImage3(this)" multiple="multiple"s>
										</div>
									</div>
								</div>
							</div>
						</div>
						
					</section>
					<div class="btnWrapper">
						<a class="btn addBtn" href="javacript:;">添加事项</a>
						<a class="btn subBtn" onclick="submitForm()" >提交</a>
					</div>
				</div>
			</div>
		</section>
	</div>
	

	
</form:form>

</body>
</html>