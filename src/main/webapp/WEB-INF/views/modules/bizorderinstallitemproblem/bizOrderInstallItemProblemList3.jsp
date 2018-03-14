<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单安装项问题管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			queryInstallItemProblemType();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		// --全选框被单击---
		function ChkAllClick(sonName, cbAllId){
		    var arrSon = document.getElementsByName(sonName);
		 	var cbAll = document.getElementById(cbAllId);
			var tempState=cbAll.checked;
		 	for(i=0;i<arrSon.length;i++) {
		 		if(arrSon[i].checked!=tempState)
		        arrSon[i].click();
			}
		}
		// --子项复选框被单击---
		function ChkSonClick(sonName, cbAllId) {
		 	var arrSon = document.getElementsByName(sonName);
			var cbAll = document.getElementById(cbAllId);
			for(var i=0; i<arrSon.length; i++) {
			    if(!arrSon[i].checked) {
			    cbAll.checked = false;
			    	return;
			    }
			}
			cbAll.checked = true;
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
		
		function queryInstallItemProblemType(){
			
			$("#problemTypeId").html('');
			var businessType = "1";
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
			            	
			            	if('${bizOrderInstallItemProblemVo.problemTypeId}' == data[i].value){
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
			
			
			var html2='<option value=""></option>';
			var html3='<option value=""></option>';
			var storeId =$("#storeId").val();
			var projectModeId=$("#projectMode").val();
			if (storeId!="" && projectModeId!="" && undefined!=storeId && undefined!=projectModeId) {
				
				$.ajax({
					url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,					
					success: function(data){
						
						if(null!=data&&data.length>0){
					
							for (var v = 0; v < data.length; v++) {
								
								if(data[v].isOn == 1){
									//启用
									if('${bizOrderInstallItemProblemVo.projectInstallItemId}' == data[v].id){
										$("#s2id_projectInstallItemId .select2-chosen").html(data[v].installItemName);
										html2 = html2 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
									}else{
										html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
									}
								}else{
									//停用
									if('${bizOrderInstallItemProblemVo.projectInstallItemIdStop}' == data[v].id){
										$("#s2id_projectInstallItemIdStop .select2-chosen").html(data[v].installItemName);
										html3 = html3 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
									}else{
										html3 = html3 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
									}
									
								}
							}
							
							$("#projectInstallItemId").html(html2);
							$("#projectInstallItemIdStop").html(html3);
						} else {
							$("#projectInstallItemId").html(html2);
							$("#projectInstallItemIdStop").html(html3);
						}
					}
				})
				
			}
			
		}
		
		//根据门店和工程模式动态加载
		function queryInstallItemProblemTypeTwo(){
			
			$("#problemTypeId").html('');
			var businessType = "1";
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
			            	
							html = html + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
			            	
		        		}
		        		html+="";
		        		$("#problemTypeId").append(html);
		        	}
		        }
			});
			
			
			var html2='<option value=""></option>';
			var html3='<option value=""></option>';
			var storeId =$("#storeId").val();
			var projectModeId=$("#projectMode").val();
			if (storeId!="" && projectModeId!="" && undefined!=storeId && undefined!=projectModeId) {
				
				$.ajax({
					url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,					
					success: function(data){
						
						if(null!=data&&data.length>0){
					
							for (var v = 0; v < data.length; v++) {
								
								if(data[v].isOn == 1){
									//启用
									html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								}else{
									//停用
									html3 = html3 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
									
								}
							}
							
							$("#projectInstallItemId").html(html2);
							$("#projectInstallItemIdStop").html(html3);
						} else {
							$("#projectInstallItemId").html(html2);
							$("#projectInstallItemIdStop").html(html3);
						}
					}
				})
				
			}
			
		}
		
		function submitForm(type){
			if (type == 'query'){
				$("#searchForm").attr("action","${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/list3Last");
			}else {
				$("#searchForm").attr("action","${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/export");
			}
			$("#searchForm").submit();
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/list3">订单安装项问题上报查询列表</a></li>
	</ul>
		<form:form id="searchForm" modelAttribute="bizOrderInstallItemProblemVo" action="" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" onchange="queryInstallItemProblemTypeTwo()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" onchange="queryInstallItemProblemTypeTwo()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="queryInstallItemProblemTypeTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="queryInstallItemProblemTypeTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>问题分类:</label>
				<form:select path="problemTypeId" class="input-medium needClear" id="problemTypeId">
					<form:option value="${bizOrderInstallItemProblemVo.problemTypeId }" label="${bizOrderInstallItemProblemVo.problemTypeName }" />
				</form:select> 
			</li>
			<li><label style="width:140px;">安装项名称（停用）：</label>
				<form:select path="projectInstallItemIdStop" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>安装项名称：</label>
				<form:select path="projectInstallItemId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>提交时间 ：</label>
				<input name="beginCreateDate" type="text" id="beginCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" id="endCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:false});"/>
			</li>
			<%-- <li><label style="width:120px">工程部处理时间 ：</label>
				<input name="beginProjectCreateDate" type="text" id="beginProjectCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.beginProjectCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endProjectCreateDate\')}',isShowClear:false});"/> - 
				<input name="endProjectCreateDate" type="text" id="endProjectCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.endProjectCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginProjectCreateDate\')}',isShowClear:false});"/>
			</li> --%>
			<li><label style="width:120px">材料部处理时间 ：</label>
				<input name="beginMaterialCreateDate" type="text" id="beginMaterialCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.beginMaterialCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endMaterialCreateDate\')}',isShowClear:false});"/> - 
				<input name="endMaterialCreateDate" type="text" id="endMaterialCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.endMaterialCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginMaterialCreateDate\')}',isShowClear:false});"/>
			</li>
			<li style="width:100%">
				<label>状态：</label>
				<input id="chkAll" name="chkAll" type="checkbox" value="全选" onclick="ChkAllClick('status','chkAll')" />全选
			</li>
			<li>
				<c:forEach items="${fns:getDictList('install_item_problem_status')}" var="dict">
					<input type="checkbox" name="status" id="status" value="${dict.value}" onclick="ChkSonClick('status','chkAll')"  <c:if test="${fns:checkIsExits(bizOrderInstallItemProblemVo.status,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick ="submitForm('query')"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出" onclick ="submitForm('export')"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>提交时间</th>
				<!-- <th>工程部处理时间</th> -->
				<th>材料部处理时间</th>
				<th>状态</th>
				<th>小区</th>
				<th>客户</th>
				<th>项目经理</th>
				<th>质检员</th>
				<th>安装项名称</th>
				<th>问题分类</th>
				<th>影响工期天数</th>
				<th>描述</th>
				<th>照片</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderInstallItemProblem">
			<tr>
				<td>
					${bizOrderInstallItemProblem.name}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemProblem.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					<fmt:formatDate value="${bizOrderInstallItemProblem.projectCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemProblem.materialCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
					${bizOrderInstallItemProblem.installItemName}
				</td>
				<td>
					${bizOrderInstallItemProblem.problemTypeName}
				</td>
				<td>
					<c:if test="${bizOrderInstallItemProblem.isDelay == '1'}">
						${bizOrderInstallItemProblem.delayDays}天
					</c:if>
					<c:if test="${bizOrderInstallItemProblem.isDelay == '0'}">
						0天
					</c:if>
				</td>
				<td>
					${bizOrderInstallItemProblem.problemDescribe}
				</td>
				<td>
<%-- 					<a href="${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/photo?id=${bizOrderInstallItemProblem.id}">查看</a> --%>
				
				<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${bizOrderInstallItemProblem.id}')">查看</a>
		
				</td>
				<td>
					<a href ="${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/detail?id=${bizOrderInstallItemProblem.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
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
	                url: '${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/ajaxPhoto',
	                data: {
	                	id:installID,
	                	orderID:orderId
	                },
	                success: function (data) {
						
						  	var modelList = data.mapObject;
		                    if (null!==modelList && modelList.length !== 0) {
// 		                        '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
		                        var ainput='';
		                        for (var i = 0; i < modelList.length; i++) {
		                         ainput +=	 '<a class="swiper-slide " ><img src="' + modelList[i].picUrl + '" ></a>';
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
	
	
</body>
</html>