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
	<title>进度通报</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/upload_photo.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/progress_condition.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
</head>
<style>
	#del{
		display:block;
		font-size:.28rem;
		color:#0780ec;
		border:1px solid #0780ec;
		margin:.32rem auto .40rem;
		height:.42rem;
		line-height:.42rem;
		width:46%;
		text-align:center;
	}
</style>
<body>
	<div class="g-upload_photo">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/progressBuiletin"></a>
			<h2 class="title">进度通报</h2>
		</header><!-- /header -->
		<form id="from1" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" id="num" value="0"/>
		<input type="hidden" id="nodePlanId" value="${nodePlan.id }"/>
		<div class="times">
			<h3>${nodePlan.communityName }-${nodePlan.buildNumber }-${nodePlan.buildUnit }-${nodePlan.buildRoom }-${nodePlan.customerName }</h3>
			<ul class="">
				<%-- <li class="clearfix">
					<label for="fore">初始计划：</label>
					<input type="text" name="planDoneDate" value="<fmt:formatDate type="date" value="${nodePlan.planDoneDate }"/>" readonly>
				</li> --%>
				<li class="clearfix">
					<label for="fore">计划完工：</label>
					<p class="right_area">
						<input type="text" id="exeDoneDate" name="exeDoneDate" value="<fmt:formatDate value="${nodePlan.exeDoneDate }" pattern="yyy-MM-dd" />" readonly>
					</p>
				</li>
				<li class="clearfix">
					<label for="fore">实际完工：</label>
					<p class="right_area">
						<input id="txtBeginDate" class="date" type="text" readonly="readonly" value="" name="realDoneDate" 
						placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
					</p>
				</li>
				<li class="clearfix">
					<label for="fore">进度节点：</label>
					
					
					
					<!-- 产业 -->
				<c:if test="${nodePlan.projectMode ==1 }">
					<p class="right_area">
						<input type="text" name="nodePlanNodeName" value="${nodePlan.nodeName }" readonly="readonly">
						<div hidden="hidden" >
						
						<input type="text" name="nodePlanId" value="${nodePlan.id }">
						<input type="text" name="nodeIndex" value="${nodePlan.nodeIndex }">
						<c:forEach items="${nodeList }" var="node">
						  <input type="text" value="${node.exeDoneDate }" id="${node.id }"  />
						</c:forEach>
						</div>
					</p> 
					
				</c:if>
				<!-- 准产业 -->
				<c:if test="${nodePlan.projectMode ==4 }">
					<p class="right_area">
						<input type="text" name="nodePlanNodeName" value="${nodePlan.nodeName }" readonly="readonly">
						<div hidden="hidden" >
						
						<input type="text" name="nodePlanId" value="${nodePlan.id }">
						<input type="text" name="nodeIndex" value="${nodePlan.nodeIndex }">
						<c:forEach items="${nodeList }" var="node">
						  <input type="text" value="${node.exeDoneDate }" id="${node.id }"  />
						</c:forEach>
						</div>
					</p> 
				</c:if>
				
					<!-- 传统 -->
				
				<c:if test="${nodePlan.projectMode ==2 }">
					<p class="right_area">
						<select  name="nodePlanId" id="nodePlanChooseId" onchange="traditionChangeForPlanStartDate()">
						<option value="0">请选择</option>
						
							<c:forEach items="${nodeList }" var="node">
	                      	  <option value="${node.id }">${node.nodeName}</option>
							</c:forEach>
	                    </select>
	                    <div hidden="hidden" >
						<c:forEach items="${nodeList }" var="node">
						  <input type="text" value="${node.exeDoneDate }" id="${node.id }"  />
						</c:forEach>
						</div>
	                 </p>
						
				</c:if>
					
					
					
					
					
					
				</li>
				<li class="clearfix">
					<label for="delay_reason">延期原因：</label>
					<p class="right_area">
						<select id="delayType" name="delayType">
	                        <option value="">请选择</option>
	                        <option value="1">业主未及时到场</option>
	                        <option value="2">甲供材料未及时到场</option>
	                        <option value="3">发生设计变更</option>
	                        <option value="4">材料未及时到场</option>
	                        <option value="5">工人未及时到场</option>
	                        <option value="6">出现质量事故返工</option>
	                        <option value="7">停水停电</option>
	                        <option value="8">物业手续办理不及时</option>
	                        <option value="9">节假日禁止噪音施工</option>
	                        <option value="10">其他</option>
	                    </select>
	                 </p>
				</li>
				<div id="dis" class="undis">
				<li class="textarea_line clearfix">
					
					<label for="fore">原因描述：</label>
					<textarea id="delayReason" name="delayReason"><%-- ${nodePlan.delayReason } --%></textarea>
					<p style="font-size:13px">还可以输入<span id="word" class="word">255</span>个字</p>
				</li>
				</div>
			</ul>
		</div>
		<section class="upload_photo_list clearfix shadow">
			<input type="hidden" class="pics" name="pics" value="">
			<%-- <div class="camera">
				<img src="${ctxStatic}/mobile/modules/Manager/images/upload_pic.png" alt="上传图片">
				<input type="file" name="pic" onchange="uploadNodePlanPic()" id="pic" multiple="multiple"  value="请选择图片">
			</div>
			<span class="pic" id="uploadPhoto"></span> --%>
			<div class="camera new clearfix" id="camera" href="javascript:void(0)">
				<img src="${ctxStatic}/mobile/modules/Manager/images/upload_pic.png" style="height:120px;" alt="调取摄像头">
				<input type="file" name="pic" onchange="preview(this)" id="pic" multiple="multiple" value="请选择图片">
				<%-- <img src="${ctxStatic}/mobile/modules/Manager/images/upload_pic.png" style="height:100px;width:100px;" alt="调取摄像头">
				<input type="file" name="pic" multiple="multiple" id="pic" onchange="preview(this)" value="请选择图片"> --%>
			</div>
			<!-- <div class="pic" id="uploadPhoto"></div> -->
			<div> 
				<a id="submit" href="javaScript:void(0)">提交进度通报</a>
			</div>
		</section>
		</form>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script>
	function traditionChangeForPlanStartDate(){
		var nodeId=$("#nodePlanChooseId").val();
		$("#exeDoneDate").val(format($("#"+nodeId).val(),"yyyy-MM-dd"))
		
		
	}
	
	var format = function(time, format){
	    var t = new Date(time);
	    var tf = function(i){return (i < 10 ? '0' : '') + i};
	    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
	        switch(a){
	            case 'yyyy':
	                return tf(t.getFullYear());
	                break;
	            case 'MM':
	                return tf(t.getMonth() + 1);
	                break;
	            case 'mm':
	                return tf(t.getMinutes());
	                break;
	            case 'dd':
	                return tf(t.getDate());
	                break;
	            case 'HH':
	                return tf(t.getHours());
	                break;
	            case 'ss':
	                return tf(t.getSeconds());
	                break;
	        }
	    })
	}
	
	
	
	
	
		$(document).ready(function() {
			//绑定onclick事件
			$("#submit").bind('click',submitData);
		});
		
		//图片上传显示 
		function preview(file) {  
			var prevDiv = $('.camera'); 
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="new"><img style="width:100%;height:120px;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic);"/><a href="javascript:void(0)">删除</a></div>').insertAfter('.camera');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		}
		
		//删除按钮
		$(document).on('click', '.g-upload_photo .new > a', function(){
			$(this).parent().remove();
			//$("#num").val("0");
		});

		function uploadpic(pic){
			var hiddenFrom = document.getElementById("from1");
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
		}
		
		$(function () {
			
			var projectMode = "${projectMode}"
				if(projectMode!=1){
					//不是产业模式 不做限制
					 var now = new Date(),
			    		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
			    		start = new Date(now.setDate(now.getDate()+3)),
			    		// start = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');
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
					//产业有时间限制
					
					//产业有时间限制
					
		       		// 获取当前日期，结束日期
		    	var now = new Date(),
		   		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
		   		end = new Date(now.setFullYear(now.getFullYear()+1)),
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
					
				}
	      
			
			
			
			
	    });
		
		
		
		
		
		
		
		//实时统计文本域输入的文字的个数max:255
		$("#delayReason").keyup(function(){   
	    	if($("#delayReason").val().length > 255){
	    	   $("#delayReason").val( $("#delayReason").val().substring(0,255) );
	    	}
	    	$("#word").text( 255 - $("#delayReason").val().length ) ;
	    });
		
		//是否显示原因描述
		$("#delayType").change(function(){
			var checkText = $("#delayType").val();
			if(checkText != 10){
				$("#dis").addClass("undis");
				//清空文本域value
				$("#delayReason").val("");
				$("#word").text(255);
			}
			if(checkText == 10){
				$("#dis").removeClass("undis");
			}
		});
		
		//submit
		function submitData(){
		
			var projectMode = "${projectMode}"
			var nodePlanId = ${nodePlan.id };
			//var photo = $(".pics").val();
			var count = $("#num").val();
			var checkText = $("#delayType").val();
			var nodePlanChooseId = $("#nodePlanChooseId").val();
		
			if(undefined!=nodePlanChooseId && 0==nodePlanChooseId){
				
				 globalUtil.fn.alert('请选择进度节点...',2.0);
					return false; 
			}
			
			var exeDoneDate = $("#exeDoneDate").val();
			var txtBeginDate = $("#txtBeginDate").val();
			
			if(exeDoneDate != "" && txtBeginDate != ""){
				if(Date.parse($('#txtBeginDate').val()) > Date.parse($('#exeDoneDate').val())){
				
					if(checkText == ''){
						
						if(1==projectMode){
							//产业
							 globalUtil.fn.alert('请选择延期原因...',1.0);
							return false; 
							
						}else{
							/* globalUtil.fn.alert('请选择延期原因...',1.0);
							return false; */
							
						}
						
					}else{
						if(checkText == 10 ){
							var delayReason = $("#delayReason").val();
							if(delayReason == "" || delayReason == null){
								globalUtil.fn.alert('延期原因描述不能为空...',1.0);
								return false;
							}
						}
					}
				}
			}
			
			if(count <= 0){
				if(1==projectMode){
					 globalUtil.fn.alert("请至少上传一张图片...",1.0);
					return false; 
				}else{
					/*  globalUtil.fn.alert("请至少上传一张图片...",1.0);
						return false;  */
				}
				
			}
			
			$("#submit").css("color","#CCC");
			$("#submit").unbind("click");
			
			var options ={
				url : "${ctx}/app/manager/submitNodePlanData",
				type: "post",
				success : function(data){
					if(data == 0){
						globalUtil.fn.alert('操作成功...',2.0);			
						window.location.href = "${ctx}/app/manager/progressBuiletin";//成功后跳转URL
					}
					if(data == 1){
						globalUtil.fn.alert("操作进度通报数据错误...",2.0);
					}
					if(data == 2){
						globalUtil.fn.alert("操作进度通报图片数据失败...",2.0);
					}
				}
			}
			//ajax提交form
			$("#from1").ajaxSubmit(options);
		}
	</script>
</body>
</html>