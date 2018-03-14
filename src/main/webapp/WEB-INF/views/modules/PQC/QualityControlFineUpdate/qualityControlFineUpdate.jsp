<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	System.out.println(basePath);
%>
<html>
	<head>
		<title>质检单罚款明细</title>
		<meta name="decorator" content="default"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/popUp/css/reset.css"/>
		<%-- <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/start.css"/> --%>
		<style>
			.bor{border:1px solid #dedede;}
			.font0{font-size: 0;}
			.mb12{margin-bottom: 12px;}
			.mb2{margin-bottom: 20px;}
			.font20{font-size: 20px;}
			.col_3{color: #333;}
			.font12{font-size: 12px;}
			.col_blue{color: #0780ec;}
			.col_grey{color: #666;}
			.font14{font-size: 14px;}
			.flo_left{float: left;}
			.spanRgt{text-align: right;width: 30%;}
			.pt3{padding-top: 30px;}
			.pl3{padding-left: 30px;}
			.pb3{padding-bottom: 30px;}
			.bg_f{background: #fff;}
			.flow{overflow: hidden;}
			.seeBtn{float: right;padding-right: 30px;}
			.wid_per50{width: 50%;}
			.clearfix{ /*display:block;*/ zoom:1; }
			.clearfix:after { content:"."; display:block; height:0; clear:both; visibility:hidden; font-size:0; }
			.del_btn {
			    display: block;
			    font-size: .28rem;
			    color: #0780ec;
			    margin: .2rem auto;
			    line-height: .42rem;
			    width: 46%;
			    text-align: center;
			    border: 1px solid #0780ec;
			}
			h2{
				font-size:24px;
			}
			.pic {
			    float: left;
			    width: 30%;
			    margin-bottom: .8rem;
			    position: relative;
			    height: 2.6rem;
			}
			.camera {
			    /* width: 33%; */
			    width: 1.4rem;
			    float: left;
			    height: 1.4rem;
			    padding: 0;
			    position: relative;
			    overflow: hidden;
			}
			.camera input {
			    opacity: 0;
			    width: 100%;
			    height: 100%;
			    font-size: 100px;
			    overflow: hidden;
			    position: absolute;
			    top: 0;
			    left: 0;
			}
		</style>
	</head>
	<body>
	    <ul class="nav nav-tabs">                                                                                      
			
			<li class="active"><a href="javascript:void(0)">质检单罚款明细</a></li>
		</ul>  
		<ul class="ul-form breadcrumb">
			<li class="btns"><input class="btn btn-primary" type="button" value="返回" onclick="javascript:window.location.href = document.referrer;"/></li>
		</ul>
		<form id="jvForm" class="jvForm"  method="post" action="${ctx}/qualityControlFineUpdate/saveData ">
			<%-- <input hidden="hidden" name="managerSettleStatus" value="${managerSettleStatus }"/>
			<input hidden="hidden" name="qcSettleStatus" value="${qcSettleStatus }"/> --%>
			<input hidden="hidden" name="managerCanUpdate" value="${managerCanUpdate }"/>
			<input hidden="hidden" name="qcCanUpdate" value="${qcCanUpdate }"/>
			<input hidden="hidden" name="workerCanUpdate" value="${workerCanUpdate }"/>
			<input hidden="hidden" name="checkItemId" value="${report.checkItemId }"/>
			<input hidden="hidden" name="orderId" value="${report.orderId }"/>
			
			<div class="container">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="nav nav-tabs">
							<h2>
								<center>
									基本信息
								</center>
							</h2>
						</div>
					</div>
				</div>
				<div class="row clearfix">
					<div class="col-md-12 column">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<tr>
								<td width="50%">
									订单编号：${report.orderNumber }
								</td>
								<td width="50%">
									小区名称：${report.xiaoqu}-${report.lou}-${report.danyuan}-${report.shi}
								</td>
							</tr>
							<tr>
								<td width="50%">
									客户：${report.customerName}-${report.customerPhone}
								</td>
								<td width="50%">
									项目经理：${report.managerName }
								</td>
							</tr>
							<tr>
								<td width="50%">
									质检报告编号：${report.reportCode}
								</td>
								<td width="50%">
									报告类型：
										<c:if test="${report.reportType==1 }">
											约检
										</c:if>
										<c:if test="${report.reportType==2 }">
											抽检
										</c:if>
								</td>
							</tr>
							<tr>
								<td width="50%">
									质检员：${report.orderInspectorName}
								</td>
								<td width="50%">
									检查人：${report.checkManName}
								</td>
							</tr>
							<tr>
								<td width="50%">
									提交质检报告时间：<fmt:formatDate value="${report.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td width="50%">
									检查项分类：${report.checkTypeName}
								</td>
							</tr>
							<tr>
								<td clospan="2">
									检查项：${report.checkItemName}
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>	
			<div class="container">
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="nav nav-tabs">
							<h2>
								<center>
									罚款金额
								</center>
							</h2>
						</div>
					</div>
				</div>
				<div class="row clearfix">
					<div class="col-md-12 column">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<tr>
								<td colspan="2">
									项目经理：${report.managerName }
								</td>
							</tr>
							<tr>
								<c:choose>
								   <c:when test="${managerCanUpdate}">   
								   		<td width="50%">
											扣项目经理分值：
											<input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="managerScore" name="managerScore"  value="${report.managerScore }"/>
										</td>
										<td width="50%">
											罚项目经理金额：
											<input onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" id="punishMoney" name="punishMoney"  value="${report.punishMoney }"/>
										</td>
								   </c:when>
								    
								   <c:otherwise>  
								   		<td width="50%">
											扣项目经理分值：
											<input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="managerScore" name="managerScore"  readonly="readonly" value="${report.managerScore }"/>
										</td>
										<td width="50%">
											罚项目经理金额：
											<input onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" id="punishMoney" name="punishMoney"  readonly="readonly"  value="${report.punishMoney}"/>
										</td>
								   </c:otherwise>
								</c:choose>
								
							</tr>
							<tr>
								<td colspan="2">
									工人组长：${report.workerGroupLeaderName}
								</td>
							</tr>
							<tr>
								<td width="50%">
									任务包名称：${report.packageName}
								</td>
								<td width="50%">
									任务包状态：${report.packageStateId}-${report.packageStateName }
								</td>
							</tr>
							<tr>
								<c:set var="canUpdateString" value="80,90,95,110,130"/>
								<c:choose>
									<c:when test="${workerCanUpdate}">
										<td width="50%">
											扣工人分值：
											<input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="workerScore" name="workerScore"  value="${report.workerScore}"/>
										</td>
										<td width="50%">
											罚工人金额：
											<input onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" id="workerMoney" name="workerMoney" value="${report.workerMoney}"/>
										</td>
									</c:when>
									<c:otherwise>
										<td width="50%">
											扣工人分值：
											<input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="workerScore" name="workerScore"  readonly="readonly" value="${report.workerScore}"/>
										</td>
										<td width="50%">
											罚工人金额：
											<input onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" id="workerMoney" name="workerMoney" readonly="readonly" value="${report.workerMoney}"/>
										</td>
									</c:otherwise>
									
								</c:choose>
							</tr>
							<tr>
								<td clospan="2">
									质检员：${report.orderInspectorName}
								</td>
							</tr>
							<tr>
								<c:choose>
									<c:when test="${qcCanUpdate}">    
										<td width="50%">
											扣质检员分值：
											<input id="inspectorScore" name="inspectorScore"  value="${report.inspectorScore}"/>
										</td>
										<td width="50%">
											罚质检员金额：
											<input onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" id="inspectorMoney" name="inspectorMoney"  value="${report.inspectorMoney}"/>
										</td>
									</c:when>
									<c:otherwise>  
									   <td width="50%">
											扣质检员分值：
											<input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="inspectorScore" name="inspectorScore"  readonly="readonly" value="${report.inspectorScore}"/>
										</td>
										<td width="50%">
											罚质检员金额：
											<input onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" id="inspectorMoney" name="inspectorMoney"  readonly="readonly" value="${report.inspectorMoney}"/>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</table>
					</div>
				</div>
				<c:if test="${fn:length(pList)>0 }">
					<div id="alreadyUploaded" class="row clearfix alUpload" >
						<span class="font28 col_3">已上传凭证：</span>
						<div class="intro font0 pad_left3 pb68">
							<div  style="height:4.6rem;"  href="javascript:void(0)">	
								<c:forEach items="${pList}" var="p">
									<div class="camera " name="photoUploaded" style="height:4.7rem;width:20%;" >
										<img  style="height: 3.6rem;width:100%;" src="../..${p.picUrl }" picUrl="${p.picUrl }"  id="${p.id}"/>
										<a class="del_btn" href="javascript:void(0)">删除</a>
									</div>
								</c:forEach>			
							</div>
						</div>
					</div>
				</c:if>
				<div class="row clearfix noUpload" >
					<span class="font28 col_3">上传凭证：</span>
					<div class="intro font0 pad_left3 pb68">
						<div class="camera pic" style="height:4.6rem;width:20%;" id="camera" href="javascript:void(0)">
							<img style="height:3.6rem;width:100%;" src="${ctxStatic}/mobile/modules/Manager/images/upPicN.png" alt="">
							<input type="file" accept="image/*" onchange="preview(this)">
						</div>
						<div  style="height:4.6rem;" id="uploaddone" href="javascript:void(0)">				
						</div>
					</div>
				</div>
				<!-- <div >
					<span class="font28 col_3">上传凭证：<input type="file" accept="image/*" onchange="preview(this)" class="inputFile"></span>
					<div  style="height:3.6rem;" id="uploaddone" href="javascript:void(0)">				
					</div>
				</div> -->
			</div>
			<div class="container" style="text-align: center;">
				<input class="btn btn-primary" type="submit" value="保存" onclick="submitData();"/>
			</div>
		</form>	
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
		<script type="text/javascript">
		$(document).on('click', '.noUpload .camera > a', function(){
			$(this).parent().remove();
		});
		$(document).on('click', '.alUpload .camera > a', function(){
			var id = $(this).prev().attr("id");
			var picUrl =  $(this).prev().attr("picUrl");
			$(this).parent().remove();
			$.post("${ctx}/qualityControlFineUpdate/delImgById", { "id": id,"picUrl":picUrl },
			   function(data){
				 //var result = eval('(' + data + ')');
				 var status = data.status;
				 if(status=="success"){
					alert("删除成功！");					 
				 }else{
					 alert("删除失败！");
				 }
			   }, "json");
		});
		//图片上传显示 
		function preview(file) {  
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					var shit = $("#shit").val();
					$('<div class="camera " name="photo" style="height:4.7rem;width:20%;" ><img  style="height: 3.6rem;width:100%;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><a class="del_btn" href="javascript:void(0)">删除</a></div>').appendTo('#uploaddone');
					
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
			}
		} 
		function uploadpic(pic){
			
			var hiddenForm = document.getElementById("jvForm");
			var input =document.createElement("input");
			
			
			if(pic){
				input.setAttribute('style', 'display:none'); 
				input.setAttribute('hidden', 'hidden'); 
				input.setAttribute('name', 'photo'); 
				input.setAttribute("type","text");
				input.setAttribute("value", pic);
				hiddenForm.appendChild(input);
			}
			
		}
		function submitData(){
			/* $.ajax({
				type:"POST",
				url:"${ctx}/qualityControlFineUpdate/saveData",
				data:$("#jvForm").serialize(),
				datatype:"json",
				success:function(data){
					alert("保存成功！");
				},
				error: function(){
	                //请求出错处理
					alert("保存失败！");
	            } 
			}) */
		}
		</script>
	</body>
</html>