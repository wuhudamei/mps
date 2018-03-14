<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
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
		
		function exportExcel(){
			var txt = $("#pageSize").val();
			if(txt != ''){
				$("#searchForm").attr("action", "${ctx}/bizorderinstallitemproblem/siteDesignProblem/exportExcel");
				$("#searchForm").submit();
				$("#searchForm").attr("action", "${ctx}/bizorderinstallitemproblem/siteDesignProblem/query");
			}else{
				alertx("请查询后在导出！");
			}
				
		}
		function show(a){
			
			var v= $("#"+a).val();
			
			$("#myspan").text(v);
			$("#myAbandonedModal").modal('show');
		}
		
		function onclickNoAbandoned(){
			$('#myAbandonedModal').modal('hide');
			$("#myspan").val('');
		}
		
	</script>
	

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizorderinstallitemproblem/siteDesignProblem/query">工地设计查询列表</a></li>
	</ul>
		<form:form id="searchForm" modelAttribute="siteDesignProblem" action="${ctx}/bizorderinstallitemproblem/siteDesignProblem/query" method="post" class="breadcrumb form-search">
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
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
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
				<form:select path="problemTypeId" class="input-medium ssae needClear" id="problemTypeId"  >
					<form:option value="${siteDesignProblem.problemTypeId }" label="${siteDesignProblem.problemTypeName }" />
				</form:select> 
			</li>
			<li>
				<label>状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label="" id = "statusoption"/>
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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="导出" onclick="exportExcel()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr align="center">
				<th align="center">门店<br></th>
				<th>状态<br></th>
				<th>提交时间<br></th>
				<th>设计部处理时间<br></th>
				<th>工程模式<br></th>
				<th>区域<br></th>
				<th>小区<br></th>
				<th>客户<br></th>
				<th>项目经理<br></th>
				<th>设计师<br></th>
				<th>问题分类<br></th>
				<th>期望完成日期<br></th>
				<th>描述<br></th>
				<th>项目经理<br>上传照片</th>
				<th>扣除分数<br></th>
				<th>罚款金额<br></th>
				<th>操作<br></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderInstallItemProblem">
			<tr>
				<td>
					${bizOrderInstallItemProblem.storeName}
				</td>
				<td>
					${bizOrderInstallItemProblem.statusName}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemProblem.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemProblem.materialCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(bizOrderInstallItemProblem.projectMode, 'employee_project_mode', '')}
				</td>
				<td>
					${bizOrderInstallItemProblem.enginName}
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
					<a href="javascript:void(0);"onclick="show(${bizOrderInstallItemProblem.problemId})">查看</a>
					<input style="display: none" id="${bizOrderInstallItemProblem.problemId}" value = "${bizOrderInstallItemProblem.problemDescribe}"/>
				</td>
				
					
	
		
				
				
				<td style="text-align: center;">
<%-- 					<a href="${ctx}/bizorderinstallitemproblem/siteDesignProblem/photo?id=${bizOrderInstallItemProblem.problemId}&picType=2081">查看</a> --%>
		
			<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${bizOrderInstallItemProblem.problemId}','2081')">查看</a>
				</td>
				<td>
					${bizOrderInstallItemProblem.punishScore}
				</td>
				<td>
					${bizOrderInstallItemProblem.punishMoney}
				</td>
				<td>
					<a href="${ctx}/bizorderinstallitemproblem/siteDesignProblem/details?problemId=${bizOrderInstallItemProblem.problemId}">详情</a>
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
	
	
	<div  class="modal fade" id="myAbandonedModal" tabindex="-1" role="dialog" style="width:350px">
								<input  id="myId" type="hidden">
								<div class="modal-header">
									<button class="close" type="button" data-dismiss="modal">×</button>
									<h4 id="myModalLabel" align="center" style="color: black;">描述</h3><br>
									<div align="center">
										<span id = "myspan"></span></br>
										<button type="button" class="btn" onclick="onclickNoAbandoned()">确定</button>
									</div>
									</div>
									
		</div>
</body>
</html>