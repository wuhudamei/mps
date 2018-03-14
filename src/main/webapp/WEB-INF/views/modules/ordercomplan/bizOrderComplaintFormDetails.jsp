<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单投诉问题管理</title>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/bizOrderComplaint/css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/bizOrderComplaint/css/complain.css"/>
	<meta name="decorator" content="default"/>
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
		$(document).on('click','.addBtn',function(){
			var num = ++$('.itemList').length;
			var itemList = '<div class="mb1d mb12 rela itemList">'+
							'<div class="lab bor_btm_d7">'+
								'<p class="f16 col3 bold pb20">投诉问题' + num + '</p>'+
								'<p class="col3 f14 mb20 clearfix">'+
									'<label class="f14 col3" for="">问题分类：</label>'+
									'<select class="shop">'+
										'<option value="水电修整">水电修整</option>'+
										'<option value="泥瓦补齐">泥瓦补齐</option>'+
										'<option value="墙地砖安装">墙地砖安装</option>'+
									'</select>'+
									'<span class="f14 col3 ml20">对应任务包：水电类</span>'+
									'<span class="f14 col3 ml30">被投诉对象：项目经理 工人</span>'+
								'</p>'+
								'<div class="col3 f14 mb20 clearfix">'+
									'<span class="fl pt8">问题事项：</span>'+
									'<p class="overflow checkWrap">'+
										'<input type="checkbox" id="five" name="five" value="five">'+
										'<label class="nochecked" data-name="five" for="five">跳闸跳闸</label>'+
									'</p>'+
								'</div>'+
								'<div class="col3 f14 pb20 clearfix">'+
									'<span class="fl pt8">问题内容：</span>'+
									'<textarea class="complainText" placeholder="最多输入100个汉字"></textarea>'+
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
			alert(path);
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
	      alert(path);
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
		//选中分类触发的事件
		function gradeChange(){
			var v = $("#orderId").val();
			var typeName = $("#typeName").val();
			var complaintSource = $("#complaintSource").val();
			var tsiphone = $("#complaintPersonPhone").val();
			var tsNmae = $("#complaintPersonName").val();
			
			window.location.href="${ctx}/cusserviceproblem/bizCusServiceProblem/ProjectbyId?orderId="+v+"&typeName="+typeName+"&complaintSource="+complaintSource+"&complaintPersonPhone="+tsiphone+"&complaintPersonName="+tsNmae;
		}
		
		function submitForm(){
			   $("#inputForm").attr("action", "${ctx}/ordercomplan/bizOrderComplaint/saveAll");
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
	            		+ data[i].label;
	            	}
	            	$("#orderareaDiv").html(html);
	            }
		    })
		}
		
		
		
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ordercomplan/bizOrderComplaint/">订单投诉问题列表</a></li>
		<li class="active"><a href="${ctx}/ordercomplan/bizOrderComplaint/form?id=${bizOrderComplaint.id}">订单投诉问题<shiro:hasPermission name="ordercomplan:bizOrderComplaint:edit">${not empty bizOrderComplaint.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ordercomplan:bizOrderComplaint:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizOrderComplaint" action="${ctx}/ordercomplan/bizOrderComplaint/saveAll" method="post" class="form-horizontal">
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
					
							<form:select onchange="gradeChange()"  id="typeName" path="typeName" class="shop">
								<form:option value="" label=""/>
								<form:options items="${fns:getComplaintProblemTypeList()}" itemLabel="typeName" itemValue="typeName" htmlEscape="false"/>
							</form:select>
									<span class="must2">*</span>
									<span class="f14 col3 ml20">对应任务包：${bizOrderComplaint.bizComplaintProblemType.packName}</span>
									<span class="f14 col3 ml30">被投诉对象：${fns:getDictLabel(bizOrderComplaint.bizComplaintProblemType.dealPersonType,'deal_person_type', '')} </span>
								</p>

								
								<div class="col3 f14 mb20 clearfix">
									<span class="fl pt8">问题事项：</span>
									<div   id="orderareaDiv" class="overflow checkWrap">
											<script>
										initOrderarea();
										</script>
									</div>

									
								</div>
			
								
								<div class="col3 f14 pb20 clearfix">
									<span class="fl pt8">问题内容：</span>
									<textarea  name="tsnr" class="complainText" placeholder="最多输入100个汉字"></textarea>
								</div>
								<div class="col3 f14 pb20 clearfix">
									<span class="fl pt8">上传附件：</span>
									<div class="pics font0 clearfix">
										<div  class="pic camera" href="javascript:void(0)">
											<img id="imgPreview1" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">
											<input type="file" name="photo" accept="image/*" onchange="PreviewImage1(this)">
										</div>
										<div  class="pic camera" href="javascript:void(0)">
											<img id="imgPreview2" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">
											<input type="file" name="photo" accept="image/*" onchange="PreviewImage2(this)">
										</div>
										<div  class="pic camera" href="javascript:void(0)">
											<img id="imgPreview3" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">
											<input type="file"   name="photo" accept="image/*" onchange="PreviewImage3(this)">
										</div>
										<div  class="pic camera" href="javascript:void(0)">
											<img id="imgPreview4" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">
											<input type="file"   name="photo" accept="image/*" onchange="PreviewImage4(this)">
										</div>
										<div  class="pic camera" href="javascript:void(0)">
											<img id="imgPreview5" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">
											<input type="file"   name="photo" accept="image/*" onchange="PreviewImage5(this)">
										</div>
										<div  class="pic camera" href="javascript:void(0)">
											<img id="imgPreview6" src="${ctxStatic}/bizOrderComplaint/images/upPic.png" alt="">
											<input type="file"    name="photo" accept="image/*" onchange="PreviewImage6(this)">
										</div>
								
							
								
									</div>
								</div>
							</div>
						</div>
						<!-- <div class="mb1d mb12 rela undis">
							<div class="lab bor_btm_d7">
								<p class="f16 col3 bold pb20">投诉问题2</p>
								<p class="col3 f14 mb20 clearfix">
									<label class="f14 col3" for="">问题分类：</label>
									<select class="shop">
										<option value="水电修整">水电修整</option>
										<option value="泥瓦补齐">泥瓦补齐</option>
										<option value="墙地砖安装">墙地砖安装</option>
									</select>
									<span class="f14 col3 ml20">对应任务包：水电类</span>
									<span class="f14 col3 ml30">被投诉对象：项目经理 工人</span>
								</p>
								<div class="col3 f14 mb20 clearfix">
									<span class="fl pt8">问题事项：</span>
									<p class="overflow checkWrap">
										<input type="checkbox" id="one" name="one" value="one">
										<label class="nochecked" data-name="one" for="one">跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸跳闸</label>
										<input type="checkbox" id="two" name="two" value="two">
										<label class="nochecked" data-name="two" for="two">跳</label>
										<input type="checkbox" id="three" name="three" value="three">
										<label class="nochecked" data-name="three" for="three">跳闸</label>
										<input type="checkbox" id="four" name="four" value="four">
										<label class="nochecked" data-name="four" for="four">跳闸跳</label>
										<input type="checkbox" id="five" name="five" value="five">
										<label class="nochecked" data-name="five" for="five">跳闸跳闸</label>
									</p>
								</div>
								<div class="col3 f14 pb20 clearfix">
									<span class="fl pt8">问题内容：</span>
									<textarea class="complainText" placeholder="最多输入100个汉字"></textarea>
								</div>
								<div class="col3 f14 pb20 clearfix">
									<span class="fl pt8">上传附件：</span>
									<div class="pics font0 clearfix">
										<div class="pic camera" href="javascript:void(0)">
											<img src="../../images/upPic.png" alt="">
											<input type="file" accept="image/*" onchange="preview(this)">
										</div>
										<div class="pic">
											<img src="../../images/eg.png" alt="">
											<a class="del_pic" href="javascript:void(0)"></a>
										</div>
										<div class="pic">
											<img src="../../images/eg.png" alt="">
											<a class="del_pic" href="javascript:void(0)"></a>
										</div>
										<div class="pic">
											<img src="../../images/eg.png" alt="">
											<a class="del_pic" href="javascript:void(0)"></a>
										</div>
										<div class="pic">
											<img src="../../images/eg.png" alt="">
											<a class="del_pic" href="javascript:void(0)"></a>
										</div>
										<div class="pic">
											<img src="../../images/eg.png" alt="">
											<a class="del_pic" href="javascript:void(0)"></a>
										</div>
									</div>
								</div>
							</div>
							<a class="delItemBtn" href="javascript:;">删除事项</a>
						</div> -->
					</section>
					<div class="btnWrapper">
						<a class="btn addBtn" href="javacript:;">添加事项</a>
						<a class="btn subBtn" onclick="submitForm()" >提交</a>
					</di
					v>
				</div>
			</div>
		</section>
	</div>
	
	</form:form>
</body>
</html>