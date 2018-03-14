<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>订单设置</title>
		<meta name="decorator" content="default"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
		<style>
			.delBtn {
		    display: block;
		    font-size: .14rem;
		    color: #0780ec;
		    border: 1px solid #0780ec;
		    margin: 0 auto .20rem;
		    line-height: .21rem;
		    width: 23%;
		    text-align: center;
			}
			.bor{border:1px solid #dedede;}
			.font0{font-size: 0;}
			.mb12{margin-bottom: 12px;}
			.mb2{margin-bottom: -9px;}
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
			.fullwidth li{width: 100%; display:inline-block;}
			.input-mb8{margin-bottom: 8px !important;}
			.text-center{text-align:center !important;}
			.img-photo img{ width:500px !important; height: 300px !important; margin: 10px 0; border: 1px solid #333;}
			html,body{height: 150% !important;}
		</style>
	</head>
	<body>
	    <ul class="nav nav-tabs">                                                                                      
			
			<li class="active"><a href="javascript:void(0)">订单开工时间列表</a></li>
		</ul>  
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2>
							<center style="font-size: 20px;">基本信息</center>
						</h2>
					</div>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tr>
						<td width="50%">
								订单编号：${confirmStartOrder.orderNumber }
							</td>
							<td width="50%">
								小区：${confirmStartOrder.communityName }-${confirmStartOrder.buildNumber }-${confirmStartOrder.buildUnit }-${confirmStartOrder.buildRoom }-${confirmStartOrder.customerName }
							</td>
							
						</tr>
						<tr>
							<td width="50%">
								签约日期：
								<fmt:formatDate value="${confirmStartOrder.signContractDate }" pattern="yyyy-MM-dd"/>
							</td>
							<td width="50%">
								接单日期：
								<fmt:formatDate value="${confirmStartOrder.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
						</tr>
						<tr>
							<td width="50%">
								项目经理：${confirmStartOrder.itemManager }
							</td>
							<td width="50%">
								合同竣工日期：
								<fmt:formatDate value="${confirmStartOrder.contractEndDate }" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
						<tr style="width: 100px;">
							<td width="50%">
								订单状态：${confirmStartOrder.orderStatusDescription }
							</td>
							<td width="50%">
								设计师：${confirmStartOrder.designerName }
							</td>
						</tr>
					</table>
				</div>
			</div>
				
				
				
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2>
							<center style="font-size: 20px;">
								确认开工
							</center>
						</h2>
					</div>
				</div>
			</div>
			<form:form modelAttribute="confirmStartOrder" action="" method="post" id ="jvForm">
			<input type="hidden" id="dateCompare" name="dateCompare" value=""/>
			<input type="hidden" id="num" value="0"/>
			<input type="hidden" id="contractStartDate" value="<fmt:formatDate type="date" value='${confirmStartOrder.contractStartDate }' pattern="yyyy-MM-dd"/>"/>
			<input type="hidden" name="orderId" value="${confirmStartOrder.id }"/>
			<input type="hidden" name="storeId" value="${confirmStartOrder.storeId }"/>
			<input type="hidden" name="houseIsNew" value="${confirmStartOrder.houseIsNew }"/>
			<input type="hidden" name="projectMode" value="${confirmStartOrder.projectMode }"/>
			<input type="text" id="num" value="0" style="display: none"/>
			<input type="text" style="display: none" id="shit"  value="1">
				<ul class="pt3 pl3 pb3 bg_f mb2 font0 bor fullwidth clearfix">
					<li class="mb12 clearfix">
						<span class="col_grey font14 flo_left spanRgt">合同开工日期：</span>
						<p class="font14 col_3 flow">
							<fmt:formatDate value="${confirmStartOrder.contractStartDate }" pattern="yyyy-MM-dd"/>
						</p>
					</li>
					<li class="mb12 clearfix">
						<span class="col_grey font14 flo_left spanRgt">实际开工时间：</span>
						<input name="input_date" type="text" id="txtBeginDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear" onchange="tip()"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</li>					
					<li class="mb12 clearfix">
						<span class="col_grey font14 flo_left spanRgt">客户签字：</span>
						<input type="radio" id="yes1" name="isNeedSign" checked="checked" value="1" class="input-mb8">
							<label data-name="isNeedSign" for="yes1" class="checked">是</label>
							<input type="radio" id="no1" name="isNeedSign" value="0" class="input-mb8">
							<label data-name="isNeedSign" for="no1">否</label>
						<span class="help-inline"><font color="red">*</font> </span>
							
					</li>
					<li class="mb12 clearfix" id = "mytype" style="display: none;">
						<span class="col_grey font14 flo_left spanRgt">延期类型：</span>
						<input type="radio" id="cons" name="delayType" value="0" class="input-mb8" checked="checked">
							<label data-name="delayType" for="cons" class="checked">客户原因</label>
							<input type="radio" id="comp" name="delayType" value="1" class="input-mb8">
							<label data-name="delayType" for="comp">公司原因</label>
					</li>
					<li class="mb12 clearfix" id ='mymarker' style="display: none;">
						<span class="col_grey font14 flo_left spanRgt">延期说明：</span>
						<input type="text" role="3" id="startRemark" name="startRemark">
					</li>
					<li class="mb12 clearfix">
						<span class="col_grey font14 flo_left spanRgt">自装延期天数：</span>
						<p><input type="text" id="decorateDelayDays" name="decorateDelayDays" 
							onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/></p>
					</li>
					<li class="mb12 clearfix">
						<span class="col_grey font14 flo_left spanRgt">自装客户签字：</span>
						<input type="radio" id="yes2" name="isSelfDecorateNeedSign" value="1" class="input-mb8" checked="checked">
							<label data-name="isSelfDecorateNeedSign" for="yes2" class="checked">是</label>
							<input type="radio" id="no2" name="isSelfDecorateNeedSign" value="0" class="input-mb8">
							<label data-name="isSelfDecorateNeedSign" for="no2">否</label>
					</li>
					<li class="mb12 clearfixb text-center img-photo" id="myphoto">
						<span class="col_grey font14 flo_left spanRgt">开工照片：</span>
						<p class="up">
							<div class="camera" id="camera1" href="javascript:void(0)">
								
								<input type="file" name="pic" onchange="preview(this)" id="pic" multiple="multiple" value="请选择图片" style="font-size: 10px;float:left;">
								
							</div>
						</p>
					</li>
					
					
		
					<div class="btns mb12 clearfix text-center">
						<input class="btn" type="button" value="返回" onclick="history.go(-1)"/>
						&nbsp;
						&nbsp;
						&nbsp;
						<input class="btn btn-primary" type="button" value="保存" onclick="submitData()"/>
					</div>
				</ul>
				
		</form:form>
			</div>
		
<script type="text/javascript">
	function tip(){
		if(Date.parse($('#txtBeginDate').val()) > Date.parse($('#contractStartDate').val())){
			$("#mymarker").css("display","block");
			$("#mytype").css("display","block");
			var s = Date.parse($('#txtBeginDate').val()) - Date.parse($('#contractStartDate').val());
			 var days = parseInt(s / (1000 * 60 * 60 * 24));
			$("#decorateDelayDays").val(days)
			
		}else{
			$("#mymarker").css("display","none");
			$("#mytype").css("display","none");
			$("input[name='delayType']:checked").removeAttr("checked");
			$("#startRemark").val("");
			$("#decorateDelayDays").val(0)
		}
		
	}


		function submitData(){
			var orderId = '${confirmStartOrder.id }';
			var storeId = '${confirmStartOrder.storeId }';
			var houseIsNew = '${confirmStartOrder.houseIsNew }';
			var count = $("#num").val();
			
			var txtBeginDate = $("#txtBeginDate").val();
			if(txtBeginDate == ""){
				/* globalUtil.fn.alert("自装延期天数不能为空...",1.0); */
				alertx("实际开工时间不能为空")
				return false;
			}
			
			if(Date.parse($('#txtBeginDate').val()) > Date.parse($('#contractStartDate').val())){
				//延期类型
				var delayType = $("input[name='delayType']:checked").val()/* $('input:radio:checked').val() */;
				var remark = $("#startRemark").val();
				if(remark == ""){
					/* globalUtil.fn.alert(,1.0); */
					alertx("开工延期说明不能为空...")
				  return false;
				}
				if(remark.length > 200){
				/* 	globalUtil.fn.alert("",1.0); */
					alertx("超过200个汉字...")
					return false;
				}
			}else{
				$("#dateCompare").val("3");
			}
			
			//自装延期天数
			var decorateDelayDays = $("#decorateDelayDays").val();
			if(decorateDelayDays == ""){
				/* globalUtil.fn.alert("自装延期天数不能为空...",1.0); */
				alertx("自装延期天数不能为空...")
				return false;
			}
			
			//上传图片
			if(count <= 0){
				/* globalUtil.fn.alert("请至少上传一张图片...",1.0); */
				alertx("请至少上传一张图片...")
				return false;(html);
			}
			
			var reditUrl = "${ctx}/orderStartWorkerDateSet/list";//成功后跳转地址
			$("#startRemark").val( $("#startRemark").val().substring(0,200) );
			
			$("#start").css("color","#CCC");
			$("#start").unbind("click");
			//去掉file的input中无效的base64的串儿
	        $(".file").each(function(){
	            $(this).remove();
	        });
			var options ={
				url : "${ctx}/orderStartWorkerDateSet/updateById",
				type: "POST",
				success : function(data){
					if(data == 0){
						alertx("操作成功...")
						window.location.href = reditUrl;
					}else if(data == 1){
						alertx("订单id为空，请联系管理员...")
					}else if(data == 2){
						alertx("该订单已经确认开工，请不要重复开工")
					}else if(data == 3){
						alertx("该订单没有工程模式，请联系管理员...")
					}else if(data == 4){
						alertx("请至少上传一张图片，请联系管理员...")
					}else if(data == 5){
						alertx("工程进度节点为空，请联系管理员...")
					}else if(data == 6){
						alertx("订单安装项列表为空，请联系管理员...")
					}else{
						alertx("保存失败，请联系管理员...")
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
					$('<li><img style="width:100%;height:100px;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic);" id= "'+num+'"/><a class="delBtn" href="javascript:void(0)">删除</a></div></li>').insertAfter('.camera');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		}
		 $("#startRemark").keyup(function(){   
		    	if($("#startRemark").val().length > 200){
		    	   $("#startRemark").val( $("#startRemark").val().substring(0,200) );
		    	}
		    	$("#word").text( 200 - $("#startRemark").val().length ) ;
		    });
		//删除按钮
		$(document).on('click', '.delBtn', function(){
			var file = $("#pic");
			file.val(null);
	        var numReal=$(this).prev().attr("id");
	        $(("#num"+numReal)).remove();
	        var num = $("#num").val();
	        num--;
	        $("#num").val(num);
			$(this).parent().remove();
		});
		
		function uploadpic(pic){
			var hiddenFrom = document.getElementById("myphoto");
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
		</script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar_confirm_start.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>
	</body>
</html>