<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待分配工人任务包列表</title>
	<meta name="decorator" content="default"/>
	<meta content="*" name="Access-Control-Allow-Origin"><!--跨域请求  -->
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		 $(function() {
	           $("#checkAll").click(function() {
	                $('input[name="status"]').attr("checked",this.checked); 
	            });
	            var $status = $("input[name='status']");
	            $status.click(function(){
	                $("#checkAll").attr("checked",$subBox.length == $("input[name='status']:checked").length ? true : false);
	            });
	      });
		 function getDepartments(){
				$("#enginDepartId").html('');
				$.ajax({
					url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
					type:'post',
					dataType:'json',
					data:{
						'storeId':$('#storeId').val(),
						'projectMode':$('#projectMode').val(),
						'employeeId':$('#employeeId').val()
					},
					success:function(data){
						if(data == null){
							$("#enginDepartId").append('');
						} else {
							var html = "<option value=''></option>";
							for(var i=0;i<data.length;i++){
								var sec = "";
								if('${bizOrderTaskpackage.engineDepartId}' == data[i].value){
				            		sec = "selected='selected'";
				            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
				            	}
								html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
							}
							html+='';
			        		$("#enginDepartId").append(html);
						}
					}
				});
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/completedTaskpackagePage">任务包列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackage" action="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/completedTaskpackage" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="getDepartments()" id="storeId">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>区域：</label>
				<form:select path="engineDepartId" id="enginDepartId" class="input-medium needClear">
					<form:option value="${bizOrderTaskpackage.engineDepartId }" label="${bizOrderTaskpackage.departmentName }" />
				</form:select>
			</li>
			<li><label>任务包编号：</label>
				<form:input path="orderTaskpackageCode" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			
			<li><label>任务包名称：</label>
				<form:input path="packageName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>

			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemCustomer" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>工人组长姓名：</label>
				<form:input path="groupRealname" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label style="width:160px;">计划开工日期：</label>
				<input name="beginPlanStartdate" type="text" id="beginPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginPlanStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endPlanStartdate\')}',isShowClear:false});"/> - 
				<input name="endPlanStartdate" type="text" id="endPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endPlanStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginPlanStartdate\')}',isShowClear:false});"/>
			</li>
			<li><label style="width:160px;">计划完工日期：</label>
				<input name="beginPlanEnddate" type="text" id="beginPlanEnddate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginPlanEnddate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endPlanEnddate\')}',isShowClear:false});"/> - 
				<input name="endPlanEnddate" type="text" id="endPlanEnddate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endPlanEnddate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginPlanEnddate\')}',isShowClear:false});"/>
			</li>
			<li><label style="width:160px;">实际开工日期：</label>
				<input name="beginActualStartdate" type="text" id="beginActualStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginActualStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endActualStartdate\')}',isShowClear:false});"/> - 
				<input name="endActualStartdate" type="text" id="endActualStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endActualStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginActualStartdate\')}',isShowClear:false});"/>
			</li>
			<li><label style="width:160px;">实际完工日期：</label>
				<input name="beginActualEnddate" type="text" id="beginActualEnddate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginActualEnddate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endActualEnddate\')}',isShowClear:false});"/> - 
				<input name="endActualEnddate" type="text" id="endActualEnddate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endActualEnddate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginActualEnddate\')}',isShowClear:false});"/>
			</li>
			<li class="btns" style="width:100px"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>任务包状态</th>
				<th>工人组长</th>
				<th>计划开工日期</th>
				<th>计划完工日期</th>
				<th>实际开工日期</th>
				<th>实际完工日期</th>
				<th>完工照片</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="taskpackage" varStatus="index">
			<tr>
				<td>
					${fns:getStoreLabel(taskpackage.storeId, '')}
				</td>
				<td>${fns:getDictLabel(taskpackage.projectMode, 'package_project_mode', '')}</td>
				<td>
					${taskpackage.departmentName }
				</td>
				<td>
					${taskpackage.orderNumber }
				</td>
				<td>
					${taskpackage.customerMessage }-${taskpackage.customerName }
				</td>
				<td>
					${taskpackage.itemCustomer }
				</td>
				<td>
					${taskpackage.orderTaskpackageCode }
				</td>
				<td>
					${taskpackage.packageName }
				</td>
				<td>
					${taskpackage.packageStateId }-${taskpackage.packageStateName }
				</td>
				<td>
					${taskpackage.groupRealname }
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.planStartdate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.planEnddate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.actualStartdate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.actualEnddate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
<%-- 					<a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/completedPhoto?id=${taskpackage.id}">查看</a> --%>
						
		<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${taskpackage.id}')">查看</a>
		
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
	                url: '${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/ajaxCompletedPhoto',
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
		                         ainput +=	 '<a class="swiper-slide " ><img src="' + modelList[i].picturePath + '" ></a>';
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