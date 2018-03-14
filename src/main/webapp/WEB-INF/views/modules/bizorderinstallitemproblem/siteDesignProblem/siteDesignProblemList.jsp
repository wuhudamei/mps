<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/compresspic.js"></script>
	<style type="text/css">     
    .mask {       
            position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #777;     
            z-index: 1002; left: 0px;     
            opacity:0.5; -moz-opacity:0.5;     
        }
     .ssae{
     	width: 500px;
     }     
	</style>  
	<script type="text/javascript">
		$(document).ready(function() {
			findEngineListByStoreIdAndProjectMode();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		 
		function clearCondition(){
			 document.getElementById("searchForm").reset();
			 var inputObjs=jQuery("#searchForm input[type='text']"); 
			 for(var i=0;i<inputObjs.length;i++){ 
			 var inputObj = inputObjs[i]; 
			 inputObj.value=""; 
			 } 
			
			 var selectObjs = jQuery("#searchForm input[class='input-medium']"); 
			 for(var i=0;i<selectObjs.length;i++){ 
			 var selectObj = selectObjs[i]; 
			 selectObj.value=""; 
			 } 
		}
		
		 //显示遮罩层    
	    function showMask(){     
	        $("#mask").css("height",$(document).height());     
	        $("#mask").css("width",$(document).width());     
	        $("#mask").show();     
	    }  
	    //隐藏遮罩层  
	    function hideMask(){     
	          
	        $("#mask").hide();     
	    }  
		
		
		function findEngineListByStoreIdAndProjectMode(){
			var html3 = '<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
			if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
				return;
			}
			//根据门店个,工程模式    动态加载工程区域
			$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectModeValue,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								
								if('${siteDesignProblem.engineDepartId}' == data[v].engineDepartId){
									$("#s2id_engineDepartSelect .select2-chosen").html(data[v].engineDepartName);
									html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
								}
							
							}
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});	
			
			
			$("#problemTypeId").html('');
			var businessType = "4";
			$.ajax({
				type : 'POST',
		        dataType : 'json',
		        url : '${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/queryInstallItemProblemTypeList',
		        data : {
		            'storeId' : $("#storeId").val(),
		            'projectMode' : $("#projectMode").val(),
		            'businessType' : businessType,
		        },
		        success : function(data){
		        	if(data == null){
		        		$("#problemTypeId").append('');
		        	}else{
		        		var html = "<option value=''></option>";
		        		for(var i=0;i<data.length;i++){
			            	
			            	if('${siteDesignProblem.problemTypeId}' == data[i].value){
								$("#s2id_problemTypeId .select2-chosen").html(data[i].label);
								html = html + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
							}else{
								html = html + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
							}
			            	
		        		}
		        		html+="";
		        		$("#problemTypeId").append(html);
		        	}
		        }
			});
			
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
//			if(pic){
//				num++;
//				input.setAttribute("type","hidden");
//				input.setAttribute("id","num3");
//				input.setAttribute("name","photos3");
//				input.setAttribute("value", pic);
//				hiddenFrom.appendChild(input);//将元素添加到form中
//				$("#num").val(num);
//			}
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


//			if(pic){
//				num++;
//				input.setAttribute("type","hidden");
//				input.setAttribute("id","num2");
//				input.setAttribute("name","photos2");
//				input.setAttribute("value", pic);
//				hiddenFrom.appendChild(input);//将元素添加到form中
//				$("#num").val(num);
//			}
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





//			if(pic){
//				input.setAttribute("type","hidden");
//				input.setAttribute("id","num1");
//				input.setAttribute("name","photos1");
//				input.setAttribute("value", pic);
//				hiddenFrom.appendChild(input);//将元素添加到form中
//			}
		}
			
		//删除按钮
		$(document).on('click', '.g-sign_list .new > a', function(){
			$(this).parent().remove();
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
	
	<style>	
		.imgsbox{
			width: 57%;
    		margin-left: 32%;
    		margin-bottom: 24px;
		
		}
		a{color:#2fa4e7;}
		.undis{display:none;}
		body {
		    background-color: #fff;
		    font-size: 16px;
		}
		
		body {
		    width: 100%;
		    height: 100%;
		    position: relative
		}
		
		 .Black {
		    position: fixed;
		    top: 0;
		    left: 0;
		    background: rgba(0, 0, 0, .6);
		    width: 100%;
		    height: 100%
		}
		
		.Black .tc_center {
		    padding: 15px;
		    position: absolute;
		    top: 50%;
		    left: 50%;
		    width: 560px;
		   	height: 420px; 
		    margin-left: -300px;
		    margin-top: -210px;
		    background: #fff;
		    color: #666;
		    overflow-Y:scroll;
		}
		
		 .Black .tc_center h2 {
		    font-size: 20px;
		    text-align: center;
		    line-height: 40px
		}
		
		 .Black .tc_center .cen_t {
		    width: 100%
		}
		
		 .Black .tc_center .cen_t p {
		    line-height: 30px;
		    font-size: 14px;
		    text-align: center;
		    margin-bottom: 12px
		}
		
		 .Black .tc_center .cen_t p input {
		    width: 50%;
		    line-height: 30px;
		    padding-left: 10px;
		    border-radius: 3px;
		    border: #ccc solid 1px
		}
		
		 .Black .tc_center .cen_t .cen_btn {
		    /* position: absolute; */
		    width: 100%;
		    /* bottom: 20px; */
		    text-align: center;
		    line-height: 30px
		}
		
		 .Black .tc_center .cen_t .cen_btn span {
		    width: 120px;
		    display: inline-block;
		    text-align: center;
		    cursor: pointer;
		    border-radius: 3px
		}
		
		 .Black .tc_center .cen_t .cen_btn .btn_y {
		    background: #3da5ee;
		    color: #fff
		}
		
		 .Black .tc_center .cen_t .cen_btn .btn_n {
		    margin-left: 50px;
		    border: solid 1px #3da5ee;
		    color: #3da5ee
		}
		
		 .Black .tc_center .cen_t .cen_tex {
		    width: 90%;
		    margin-left: 5%;
		    font-size: 16px;
		        clear: both;
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_l {
		    display: block;
		    line-height: 30px;
		    float: left;
		    width: 28%;
		    text-align: right;
		    vertical-align: middle
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r {
		    
		    text-align: left;
		    vertical-align: middle;
		    position: relative;
		    margin-bottom: 8px;
		    float: none;
    		overflow: hidden;
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r input {
		    width: 90%;
		    background: 0 0
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r textarea {
		    color: #666;
		    width: 90%;
		    height: 120px;
		    padding: 5px 15px;
		    line-height: 20px;
		    font-size: 12px;
		    border: #ccc solid 1px;
		    border-radius: 3px
		}
		
		 .Black .tc_close {
		    background: url(../images/close.png) no-repeat;
		    background-size: 100% 100%;
		    width: 50px;
		    height: 50px;
		    position: absolute;
		    top: -10px;
		    right: -12px
		}
		 .pic {
		    background: #fff;
		}
		
		 .pic textarea {
		    padding-left: 0.92rem;
		    width: 100%;
		    height: 8rem;
		    color: #333;
		    line-height: 2rem;
		    font-size: 1.4rem;
		}
		
		 .pic .span_pic{
			text-align:right;
			width: 18%;
			font-size: 16px;
			margin-bottom:5px;
		}
		 .pic p {
		    padding-left: 0.92rem;
		    padding-right: 0.92rem;
		    line-height: 2.75rem;
		    height: 2.75rem;
		    font-size: 1.5rem;
		    color: #cacaca;
		}
		
		 .pic p span {
		    padding: 2px;
		    float: right;
		    color: #bcbbbb;
		}
		
		 .pic .Fol {
		    height: 200px;
		    overflow: auto;
		    padding-right: 0.92rem;
		}
		
		 .pic .Fol img {
		    margin-bottom: 3px;
		    margin-right: 1px;
		    width: 24.5%;
		    height: 7rem;
		    float: left;
		}
		
		 .pic .Fol span {
		    display: inline-block;
		    width: 25%;
		    height: 7rem;
		    background: url(../images/zhaop_06.png) no-repeat;
		    background-size: 100% 100%;
		}
		
		 .pic .Fol span .Up {
		    width: 100%;
		    height: 100%;
		    opacity: 0;
		}
		.Big {
		    position: absolute;
		    top: -112px;
		    left: 50%;
		    margin-left: -200px;
		    width: 400px !important;
		    height: 300px !important;
		    z-index: 99;
		}
		.black_small{
		    display:none;
		    position:absolute;
		    top: 0;
		    left:0;
		    width: 100%;
		    height: 100%;
		    background:rgba(0,0,0,0.9)
		
		}
		.black_small img{
		    position:absolute;
		    top: 8%;
		    left: 10%;
		    width: 80%;
		    height: 75%;
		}
		.black_small .cen_btn{
		    width: 100%;
		    position:absolute;
		    bottom:5%;
		    left:15px;
		    text-align:center;
		}
		.black_small .cen_btn span {
		    width: 120px;
		    display: inline-block;
		    line-height:30px;
		    text-align: center;
		    cursor: pointer;
		    border-radius: 3px
		}
		
		.black_small .btn_y {
		    background: #3da5ee;
		    color: #fff
		}
		
		.black_small .btn_n {
		    margin-left: 50px;
		    border: solid 1px #3da5ee;
		    color: #3da5ee
		}
		.del_img{
		    position: absolute;
		    bottom: 5%;
		    right: 50%;
		    width: 3rem;
		    height: 3rem;
		    margin-right: -1.5rem;
		}


		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 380px;border-radius: 4px;background: #fff;font-size: 16px;
		    position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		.maskBtn{display: block;width: 60%;text-align: center;line-height: 38px;}
		.maskBtn{background: #0780ec;border-radius: 4px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16px;margin: 0 auto;}
    		
	</style>
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">工地设计处理列表</a></li>
	</ul>
		<form:form id="searchForm" modelAttribute="siteDesignProblem" action="${ctx}/bizorderinstallitemproblem/siteDesignProblem/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="text" id="num" value="0" style="display: none"/>
		<input type="text" style="display: none" id="shit"  value="1">
		<ul class="ul-form">
			<%-- <li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li> --%>
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value=""></form:option>
						<c:forEach items="${storeList}" var = "store">
							<form:option value="${store.value }">${store.label }</form:option>
						</c:forEach>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>设计师：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			
			<li><label>问题分类:</label>
				<form:select path="problemTypeId" class="input-medium ssae needClear" id="problemTypeId" >
					<form:option value="${siteDesignProblem.problemTypeId }" label="${siteDesignProblem.problemTypeName }" />
				</form:select> 
			</li>
			<li>
				<label>状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('site_design_problem')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>提交时间 ：</label>
				<input name="beginCreateDate" type="text" id="beginCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${siteDesignProblem.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" id="endCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${siteDesignProblem.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>提交时间</th>
				<th>状态</th>
				<th>小区</th>
				<th>客户</th>
				<th>项目经理</th>
				<th>设计师</th>
				<th>问题分类</th>
				<th>期望完成日期</th>
				<th>描述</th>
				<th>照片 </th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderInstallItemProblem">
			<tr>
				<td>
					${bizOrderInstallItemProblem.storeName}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemProblem.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizOrderInstallItemProblem.statusName}
				</td>
				<td>
					${bizOrderInstallItemProblem.communityName}-${bizOrderInstallItemProblem.buildNumber}-${bizOrderInstallItemProblem.buildUnit}-${bizOrderInstallItemProblem.buildRoom}
				</td>
				<td>
					${bizOrderInstallItemProblem.customerName}</br>${bizOrderInstallItemProblem.customerPhone}
				</td>
				<td>
					${bizOrderInstallItemProblem.itemManager}</br>${bizOrderInstallItemProblem.itemManagerPhone}
				</td>
				<td>
					${bizOrderInstallItemProblem.designerName}</br>${bizOrderInstallItemProblem.designerPhone}
				</td>
				<td>
					${bizOrderInstallItemProblem.problemTypeName}
				</td>
				<td>
					<%-- ${bizOrderInstallItemProblem.expectSolveDatetime} --%>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${bizOrderInstallItemProblem.expectSolveDatetime}"/>
				</td>
				<td>
					${bizOrderInstallItemProblem.problemDescribe}
				</td>
				<td align="center">
<%-- 					<a href="${ctx}/bizorderinstallitemproblem/siteDesignProblem/photo?id=${bizOrderInstallItemProblem.problemId}&picType=2081"> --%>
<!-- 						查看 -->
<!-- 					</a> -->
					<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${bizOrderInstallItemProblem.problemId}','2081')">查看</a>
		
					
				</td>
				<td>
					<c:if test="${bizOrderInstallItemProblem.status eq 10}">
					
						<a href="#" onclick="showDeal(this,'${bizOrderInstallItemProblem.problemId}')">处理</a>
					</c:if></br>
					<a href="${ctx}/bizorderinstallitemproblem/siteDesignProblem/details?problemId=${bizOrderInstallItemProblem.problemId}">详情</a>
					<div id="mask" class="mask"></div>    
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
	<div class="Black undis" id="onCreateTaskpackage">
		<form id="urgeForm" action="${ctx}/bizorderinstallitemproblem/siteDesignProblem/handle" method="post" enctype="multipart/form-data">
			<div class="tc_center" style="height: 700px;width: 966px;top: 50%;left: 50%;margin-top: -350px;margin-left: -483px;overflow: auto;" >
				<h2 id="orderAddress"></h2>
	            <div class="cen_t" style="height: 100%">
	            	<div class="cen_tex">
	            		<span class="span_l"> 问题分类：</span>
	                    <p class="span_r" id="problemTypeName"></p>
	                </div>
	            	<div class="cen_tex">
	            		<span class="span_l"> 描述：</span>
	                    <p class="span_r" id="problemDescribe"></p>
	                </div>
	                <div class="cen_tex">
						<span class="span_l">回复：</span>
						<p class="span_r">
							<textarea id="problemSolveNotes" maxlength="50" name="problemSolveNotes"></textarea>
							<input style="display: none;" name="problemId" id = "problemId">
						</p>
					</div>
	
						<!-- <label class="cen_tex">请上传处理后的图片：</label> -->
						
					<div>
						<p class="up">
							<div class="camera" id="camera1" href="javascript:void(0)" style="margin-left: 19%;margin-bottom: 10px;"> 
								<label>请上传处理后的图片：</label>
								<input type="file" name="pic" onchange="preview(this)" id="pic" multiple="multiple" value="请选择图片">
								<a class="delBtn" href="javascript:void(0)">删除</a></br>
							</div>
							<!-- <div class="pic_container" id="uploaddone"></div> -->
						</p>
					</div>
					<div class="imgsbox" >
					
					</div>
					
	                <div class="cen_btn">
	                	<span class="btn_y" onclick="yes()"> 提交 </span>
	                    <span class="btn_n" onclick="no()"> 取消 </span>
	                </div>
	            </div>
	        </div>
		</form>
    </div>
    
    
    
    
    <div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarks"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport1">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">墙地砖问题上报处理成功</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage1()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	
    
    
	<div class="pagination">${page}</div>
		
	
	<link href="${ctxStatic}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctxStatic}/vendor/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/vendor/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet">
    <link href="${ctxStatic}/modules/popUp/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/modules/popUp/css/swiper.css" rel="stylesheet" type="text/css" />
	
	
	
    <script type="text/javascript" src="${ctxStatic}/vendor/jquery/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/vendor/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${ctxStatic}/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${ctxStatic}/vendor/jquery/swiper.min.js"></script>
    
    
		
	    <!-- 照片弹层 -->
    <div class="modal _large fade disN" id="myModal-photo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                    <h4 class="modal-title text-center" id="myModalLabel">图片预览</h4>
                </div>
                <div class="modal-body">
                    <div class="swiper-container swiper-item01" id="swiper-item01">
                        <div class="swiper-wrapper clearfix" id="inputId">
<!--                             <a href="http://www.baidu.com" class="swiper-slide img-01"></a> -->
                    
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-pagination swiper-pagination-fraction">
                            <span class="swiper-pagination-current">1</span> / <span class="swiper-pagination-total">10</span>
                        </div>
                    </div>
                    <!--/.swiper-container-->
                </div>
            </div>
        </div>
    </div>
	
	
	

	    <script type="text/javascript">
        $("#myModal-manage").on("shown.bs.modal", function() {
            $(".form_datetime2").datetimepicker({
                format: "dd MM yyyy - hh:ii"
            });
        });

        $("#myModal-photo").on("shown.bs.modal", function() {
            var mySwiper01 = new Swiper('#swiper-item01', {
//                 autoplay: 5000, //可选选项，自动滑动
                speed: 800,
                loop: true, //可选选项，开启循环
                paginationClickable: true,
                nextButton: '.swiper-button-next',
                prevButton: '.swiper-button-prev',
                pagination: '.swiper-pagination',
                paginationType: 'fraction'
            });
        });
		
				 function ajaxGetImgs(installID,orderId){
				 // 	 alert(installID+":"+orderId);
	            $.ajax({
	                type: 'POST',
	                dataType: 'json',
	                url: '${ctx}/bizorderinstallitemproblem/siteDesignProblem/ajaxPhoto',
	                data: {
	                	id:installID,
	                	picType:orderId
	                },
	                success: function (data) {
						
						  	var modelList = data.mapObject;
		                    if (null!==modelList && modelList.length !== 0) {
// 		                        '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
		                        var ainput='';
		                        for (var i = 0; i < modelList.length; i++) {
		                         ainput +=	 '<a class="swiper-slide " ><img src="' + modelList[i]+ '" ></a>';
		                        }
		                        $("#inputId").html(ainput);
		                    }else{
		                        var ainput='';
		                         ainput ='<a class="swiper-slide " ><img src="${ctxStatic}/images/nopic.jpg" ></a>';
		                        $("#inputId").html(ainput);
	                    }
						
		    			
	                }
	            })
	        	
	        }

    </script>
	
	<script type="text/javascript">
	
	
		var problemIdGlobal;
		
		 //关闭 
	    function sendMessage(){
	    	$("#subReport").hide();
	    }
	    function sendMessage1(){
	    	//提交表单
	    	$("#searchForm").submit();
	    }
	
	  	//墙地砖处理
		function  showDeal(obj,problemId){
			$("#num").val(0);
			$("#shit").val(1);
			$("img[name='imgFlag']").remove();
			$("input[name='photos']").remove();
	  		problemIdGlobal=problemId;
	  		$("#problemId").val(problemId);
	  		var orderAddress;
	  		var problemTypeName;
	  		var problemDescribe;
	  		
	  		var trObj = $(obj).parent().parent().find("td").each(function(){
	  			if($(this).index()==3){
	  				orderAddress = $(this).text();
	  			}
	  			if($(this).index()==6){
	  				problemTypeName = $(this).text();
	  			}
	  			if($(this).index()==8){
	  				problemDescribe = $(this).text();
	  			}
			})
		
	  		
			$("#onCreateTaskpackage").show();
			$("#orderAddress").text(orderAddress);
			$("#problemTypeName").text(problemTypeName);
			$("#problemDescribe").text(problemDescribe);
			
		}
	  	
		function no (){
			$("#problemSolveNotes").val("");
			$("#onCreateTaskpackage").hide();	
		}
	
		function yes(){
			showMask();
			setTimeout(function(){
				var problemSolveNotes = $("#problemSolveNotes").val();
				var photos = $("input[name='photos']").val();
				if(null==problemSolveNotes || problemSolveNotes==""){
					$("#alertRemarks").text("请输入回复内容");
	           		$("#subReport").show();
					return false;
				}
				$("#urgeForm").submit();
			},1000);
			
			
			
			/* $.ajax({
				url: "${ctx}/bizorderinstallitemproblem/wallAndFloor/problem/update_problem_ajax",
	            type: "post",
	            data:{
	            		problemId:problemIdGlobal,
	            		problemSolveNotes:problemSolveNotes,
	            		photos:photos
	            	},
	            success: function(data) {
	            	
	            	$("#problemSolveNotes").val("");
	    			$("#onCreateTaskpackage").hide();	
	    			
	            	if(null!=data && data=="0"){
	            		$("#subReport1").show();
	            	}else if(data=="1"){
	            		$("#alertRemarks").text("墙地砖问题上报ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("问题上报处理回复内容为空");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks").text("该问题已处理");
	            		$("#subReport").show();
	            	}else if(data=="4"){
	            		$("#alertRemarks").text("当前登录人未登录");
	            		$("#subReport").show();
	            	}else if(data=="5"){
	            		$("#alertRemarks").text("更新问题上报状态失败");
	            		$("#subReport").show();
	            	}else{
	            		$("#alertRemarks").text("保存问题上报日志失败");
	            		$("#subReport").show();
	            	}
	            	
	            }
	    	}) */
	    	
	    	
			
		}
	
		
		function preview(file) {
			if(!checkPic(file)){
				return false;
			}
	        var num = $("#num").val();
			var prevDiv = $('.camera'); 
			if (file.files && file.files[0]) { 
				
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<img name="imgFlag" style="width:25%;height:100px;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic);" id= "'+num+'"/>').appendTo('.imgsbox');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="cen_tex img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="cen_tex img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		}
		 
		function uploadpic(pic){
			var hiddenFrom = document.getElementById("urgeForm");
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
		
		//删除按钮
		$(document).on('click', '.delBtn', function(){
			var file = $("#pic");
			file.val(null);
	        var numReal=$(this).prev().attr("id");
	        var num = $("#num").val();
	        var shit = $("#shit").val();
	        shit--;
	        num--;
	        $("#"+num).remove();
	        $("#br"+num).remove();
	        $("#num"+shit).remove();
	        
	       if(num < '0'){
	    	   $("#num").val(0);
	       }else{
	    	   $("#num").val(num);
	       }
	       if(shit < '0'){
	    	   $("#shit").val(0);
	       }else{
	    	   $("#shit").val(shit);
	       }
	       
		});
		
	</script>
	
	
	
	
	
</body>
</html>