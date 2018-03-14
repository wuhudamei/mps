<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检罚款明细查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
								html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
							}
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});		
		}
		
		
		function exportExcel(){
			//门店
			var storeId1 = '${checkEntity.storeId}';
			var storeId = $("#storeId").val();
			
			if(storeId1 != storeId){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
				
			}
			//工程模式
			var projectMode1 = '${checkEntity.projectMode}';
			var projectMode = $("#projectMode").val();
			if(projectMode1 != projectMode){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
				
			}
			//区域
			var engineDepartId1 = '${checkEntity.engineDepartId}';
			var engineDepartId = $("#engineDepartSelect").val();
			if(engineDepartId1 != engineDepartId){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
				
			}
			
			//订单编号
			var orderNumber1 = '${checkEntity.orderNumber}';
			var orderNumber = $("#orderNumber").val();
			if(orderNumber1 != orderNumber){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
				
			}
			//客户姓名
			var customerName1 = '${checkEntity.customerName}';
			var customerName = $("#customerName").val();
			if(customerName1 != customerName){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
				
			}//项目经理
			var itemManager1 = '${checkEntity.managerName}';
			var itemManager = $("#orderManagerName").val();
			if(itemManager1 != itemManager){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
				
			}
			
			//质检员
			var orderInspector1 = '${checkEntity.orderInspectorName}';
			var orderInspector = $("#orderPqcName").val();
			if(orderInspector1 != orderInspector){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
				
			}//合同开工日期  开始
			var beginContractStartDate1 = $("#startDate").val();
			var beginContractStartDate = $("#startDate1").val();
			if(beginContractStartDate1 != beginContractStartDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
				
			}
			
			//合同开工日期  结束
			var endContractStartDate1 = $("#endDate").val();
			var endContractStartDate = $("#endDate1").val();
			if(endContractStartDate1 != endContractStartDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
				
			}
			
	            $("#searchForm").attr("action", "${ctx}/inspectPunishMoneyQuery/exportInspectorFineMoneyDetailToExcel");

	            $("#searchForm").submit();

            $("#searchForm").attr("action", "${ctx}/inspectPunishMoneyQuery/listInfo");

	        }

	        function formSubmit() {


	            $("#searchForm").attr("action", "${ctx}/inspectPunishMoneyQuery/listInfo");
	            $("#searchForm").submit();
	            loading("查询中...请稍后");
	        }
		
		
		
	</script>
</head>	
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/inspectPunishMoneyQuery/list">质检罚款明细查询</a></li>
	</ul>
	<form:form id="searchForm"  modelAttribute="inspectPunishMoneyQueryEntity" action="${ctx}/inspectPunishMoneyQuery/listInfo" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- 合同开工日期 -->
		<input id="startDate1" type="hidden" value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endDate1" type="hidden" value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId"  class="input-medium needClear"  onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" >
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
				</form:select>
			</c:if>
			
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			</li>
			<li><label>订单编号：</label>
			<form:input path="orderNumber" htmlEscape="false" maxlength="30" class="input-large"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="orderManagerName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			<li><label>质检员：</label>
			<form:input path="orderPqcName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			<li><label>提交报告日期：</label>
				<input name="startDate" type="text" readonly="readonly"
					   value='<fmt:formatDate value="${startDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>'
					   id="startDate" maxlength="20" class="input-medium Wdate"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
				<input name="endDate" type="text" readonly="readonly"
					   value='<fmt:formatDate value="${endDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="endDate"
					   maxlength="20" class="input-medium Wdate"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="formSubmit()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
		 <li class="btns"><input class="btn btn-primary" type="button" value="导出" onclick="exportExcel()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>提交报告时间</th>	
				<th>订单编号</th>
				
				<th>顾客</th>
				<th>订单项目经理</th>
				<th>订单质检员</th>
				<th>质检报告编号</th>
				<th>报告类型</th>
				<th>约检节点</th>
				<th>检查人</th>
				<th>图片</th>
				<th>检查项分类</th>
				<th>检查项</th>
				<th>违规形式</th>
				<th>被罚项目经理</th>
				<th>扣项目经理分值</th>
				<th>罚项目经理金额</th>
				<th>被罚工人</th>
				<th>扣工人分值</th>
				<th>罚工人金额</th>
				<th>被罚质检员</th>
				<th>扣质检员分值</th>
				<th>罚质检员金额</th>
			<!-- 	<th>备注</th> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="report" varStatus="status">
			<tr>
			
				<td>
				${fns:getStoreLabel(report.storeId, '')}
				</td>
				<td>${fns:getDictLabel(report.projectMode, 'project_mode', '')}</td>
				<td>${report.engineDepartName }</td>
				<td>
				<fmt:formatDate value="${report.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			
				
				</td>
				<td>
				${report.orderNumber}
				
				</td>
				<td>
				${report.xiaoqu}-${report.lou}-${report.danyuan}-${report.shi}-${report.customerName}
				</td>
				<td>
						${report.orderManagerName}

				</td>
				<td>
						${report.orderPqcName}

				</td>



				<td>
				${report.reportCode}
				</td>
				<td>
				<c:if test="${report.reportType==1 }">
				约检
				</c:if>
				<c:if test="${report.reportType==2 }">
				抽检
				</c:if>
				</td>
				<td>
				${report.checkNodeName }
				</td>

				<td>
					${report.checkManName}
				</td>
				<td>
<%-- 				<a href="${ctx}/bizorderqcbill/bizOrderQcBill/detailsPic?qcBillId=${report.billId}">图片</a> --%>
				<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${report.billId}')">查看</a>
		
							
				</td>
				<td>
					${report.checkTypeName}
				</td>
				<td>
					${report.checkItemName}
				</td>
				<td>
					${report.illegalName}
				</td>
				<td>
				${report.managerName}
				
				</td>
				<td>
				${report.managerScore}
				
				</td>
				<td>
					${report.punishMoney}
				</td>
				<td>
					${report.workerGroupLeaderName}
				</td>
				<td>
					${report.workerScore}
				</td>
				<td>
					${report.workerMoney}
				</td>
				<td>
					${report.orderInspectorName}
				</td>
				<td>
					${report.inspectorScore}
				</td>
				<td>
					${report.inspectorMoney}
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
	                url: '${ctx}/bizorderqcbill/bizOrderQcBill/ajaxDetailsPic',
	                data: {
	                	qcBillId:installID,
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