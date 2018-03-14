<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>售后问题列表管理</title>
	<meta name="decorator" content="default"/>

		<style type="text/css">
		.mask-text{resize:none;width:280px;padding:5px;box-sizing:border-box;}
	</style>

	<script type="text/javascript">
		$(document).ready(function() {


            $("tbody>tr").click(function(){

          	   $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

          	          });


			var fistCode ="${entity.liableDepartmentCode}"
// 			alert(fistCode);

            $("#liableDepartmentCodeId").html('<option value=""></option>')
			$("#liableTypeCode1Id").html('<option value=""></option>')
			if(fistCode==92 ||fistCode==103){

                $("#liableDepartmentCodeId").append('<option value=""+fistCode+"" selected=selected>工程部</option>')
                $("#s2id_liableDepartmentCodeId").find(".select2-chosen").text("工程部")
			}else{
                $("#liableDepartmentCodeId").append('<option value=""+fistCode+"" >工程部</option>')

			}


			if(fistCode==95||fistCode==105){
                $("#s2id_liableDepartmentCodeId").find(".select2-chosen").text("质检部")
                $("#liableDepartmentCodeId").append(' <option value=""+fistCode+"" selected=selected>质检部</option>')
			}else {

                $("#liableDepartmentCodeId").append(' <option value=""+fistCode+"">质检部</option>')
            }

            if(fistCode==96 ||fistCode==120){
                $("#s2id_liableDepartmentCodeId").find(".select2-chosen").text("工程售后部")
                $("#liableDepartmentCodeId").append(' <option value=""+fistCode+"" selected=selected>工程售后部</option>')
			}else {

                $("#liableDepartmentCodeId").append(' <option value=""+fistCode+"">工程售后部</option>')
            }
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
		//处理触发的事件
		function abandoned(id , workOrderCode){
			$("#myAbandonedModal").modal('show');
			$("#myId").val(id , workOrderCode);
			$("#workOrderCode").val( workOrderCode);
		}
		//处理 原因提交的事件
		function onclickAbandoned(){
			var v = $("#myId").val();
			var date = $("#reson").val();
			var workOrderCode = $("#workOrderCode").val();
			//有没有输入原因
			if(date == ""){
				$("#myNoAbandonedModal").modal('show');
			}else{
					window.location.href="${ctx}/cusserviceproblem/bizCusServiceProblem/handle?id="+v+"&statusdescribe="+date+"&workOrderCode="+workOrderCode;
					}
			$("#reson").val('');
			$('#myAbandonedModal').modal('hide');
			}
		//处理 取消 事件
		function onclickNoAbandoned(){
			$('#myAbandonedModal').modal('hide');
			$("#reson").val('');
		}





		//驳回触发的事件
		function abandonedReject(id , workOrderCode){
			$("#myAbandonedModalReject").modal('show');
			$("#myIdReject").val(id, workOrderCode);
			$("#workOrderCodeReject").val(workOrderCode);
		}


		//处驳回 原因提交的事件
		function onclickAbandonedReject(){
			var v = $("#myIdReject").val();
			var workOrderCodeReject = $("#workOrderCodeReject").val();
// 			alert(v+":"+workOrderCodeReject);
			var date = $("#resonReject").val();
			//有没有输入原因
			if(date == ""){
				$("#myNoAbandonedModalReject").modal('show');
			}else{
					window.location.href="${ctx}/cusserviceproblem/bizCusServiceProblem/reject?id="+v+"&statusdescribe="+date+"&workOrderCode="+workOrderCodeReject;
					}
			$("#resonReject").val('');
			$('#myAbandonedModalReject').modal('hide');
			}
		//驳回 取消 事件
		function onclickNoAbandonedReject(){
			$('#myAbandonedModalReject').modal('hide');
			$("#resonReject").val('');
		}


		//驳回触发的事件
		function abandonedxm(){
			var v = $("#storeId").val();

			window.location.href="${ctx}/cusserviceproblem/bizCusServiceProblem/ProjectView?id="+v;
		};

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cusserviceproblem/bizCusServiceProblem/list">售后问题列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizCusServiceProblem" action="${ctx}/cusserviceproblem/bizCusServiceProblem/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select  id="storeId" path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
<!-- 			<li><label>项目经理：</label> -->
<%-- 				<form:input path="managerName" htmlEscape="false" maxlength="64" class="input-medium"/> --%>
<!-- 			</li> -->
<!-- 			<li><label>质检：</label> -->
<%-- 				<form:input path="pqcName" htmlEscape="false" maxlength="12" class="input-medium"/> --%>
<!-- 			</li> -->

			<li><label>责任部门：</label>
				<select class="input-medium needClear" name="liableDepartmentCode" id="liableDepartmentCodeId">

				</select>
			</li>



			<li><label>状态：</label>
			<form:select id="statuszhuan1"   path="status" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('status_cus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			</li>


			<li><label>问题创建时间：</label>
				<input name="start" id="beginSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${entity.start}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endSignContractDate\')}',isShowClear:true});"/> 至
				<input name="end" id="endSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${entity.end}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginSignContractDate\')}',isShowClear:true});"/>
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
			<th>订单编号</th>
			<th>地址</th>
			<th>客户姓名</th>
			<th>客户手机号</th>
			<th>问题创建时间</th>
			<th>责任部门</th>
			<th>问题类别</th>
			<th>问题类型</th>
			<th>投诉类型</th>
<!-- 		<th>重要程度</th> -->
			<th>工单来源</th>
			<th>附件</th>
			<th>状态</th>
			<th>操作</th>

		</tr>

		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizCusServiceProblem">
			<tr>

				<td>
						${fns:getStoreLabel(bizCusServiceProblem.storeId, '')}
				</td>

				<td>
					${bizCusServiceProblem.orderCode}


				</td>


				<td>
					${bizCusServiceProblem.customerAddress}


				</td>


				<td>
						${bizCusServiceProblem.customerName}


				</td>


				<td>
						${bizCusServiceProblem.customerMobile}


				</td>

				<td>
					<fmt:formatDate value="${bizCusServiceProblem.problemCreateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>


				</td>


				<td>
						${bizCusServiceProblem.liableDepartmentCode}


				</td>




				<td>
						${bizCusServiceProblem.questionType1}


				</td>



				<td>
						${bizCusServiceProblem.questionType2}


				</td>



				<td>
						${bizCusServiceProblem.complaintTypeName}


				</td>

<!-- 				<td> -->
<%-- 						${bizCusServiceProblem.importantDegreeCode1} --%>


<!-- 				</td> -->

				<td>
						${bizCusServiceProblem.workSource}


				</td>

				<td>
				<%--<a href="${ctx}/cusserviceproblem/bizCusServiceProblem/viewPicsById?id=${bizCusServiceProblem.id}">图片</a>--%>
				<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${bizCusServiceProblem.id}')">查看</a>

				</td>
				<td>
				<c:if test="${bizCusServiceProblem.status==0}">
				待处理

				</c:if>

				<c:if test="${bizCusServiceProblem.status!=0}">

				${fns:getDictLabel(bizCusServiceProblem.status, 'status_cus', '')}

				</c:if>

				</td>
				<td>

					<shiro:hasPermission name="cusserviceproblem:bizCusServiceProblem:edit">

	    				<c:if test="${bizCusServiceProblem.status>=10 && bizCusServiceProblem.status!=50&& bizCusServiceProblem.status!=40}">
	    					<a href="${ctx}/cusserviceproblem/bizCusServiceProblem/details?id=${bizCusServiceProblem.id}">详情</a>
	    				</c:if>
	    				<c:if test="${bizCusServiceProblem.status<10 || empty bizCusServiceProblem.status||bizCusServiceProblem.status==50|| bizCusServiceProblem.status==40}">
	    				<a href="${ctx}/cusserviceproblem/bizCusServiceProblem/update?id=${bizCusServiceProblem.id}&workOrderCode=${bizCusServiceProblem.workOrderCode}&customerNameNot=${bizCusServiceProblem.customerName}">接收</a>
	    				<a href="javascript:void(0)" onclick="abandoned ('${bizCusServiceProblem.id}',' ${bizCusServiceProblem.workOrderCode}')" >处理</a>
						<a href="javascript:void(0)" onclick="abandonedReject ('${bizCusServiceProblem.id} ',' ${bizCusServiceProblem.workOrderCode}')" >驳回</a>
							<a href="${ctx}/cusserviceproblem/bizCusServiceProblem/details?id=${bizCusServiceProblem.id}">详情</a>
	    				</c:if>

					</shiro:hasPermission>


				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>






						<!-- 处理btn弹框的model -->
			<div  class="modal fade" id="myAbandonedModal" tabindex="-1" role="dialog" style="width:350px">
								<input  id="myId" type="hidden">
								<input  id="workOrderCode" type="hidden">
								<div class="modal-header">
									<button class="close" type="button" data-dismiss="modal">×</button>
									<h4 id="myModalLabel" align="center" style="color: black;">处理内容</h3><br>
									<form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
										<input id = "ids"name="id" value="${bizMaterialsStandardReceiveBill.id}" type="hidden">
										<input id = "workOrderCode1"name="workOrderCode" value="${bizMaterialsStandardReceiveBill.workOrderCode}" type="hidden">
										<input id = "status" name="status" value="20" type="hidden">
									<div  style="margin:10px;min-height:22px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
										<textarea id="reson" class="mask-text"  onkeyup="this.value = this.value.substring(0,100)" placeholder="请输入处理内容，多行输入，不多于100个汉字" maxlength="100" ></textarea>
										<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandoned()" >确定</a>
										<a href="javascript:void(0)" class="btn" onclick="onclickNoAbandoned()">关闭</a>
									</div>
									</div>
									</form:form>
		</div>
						<!-- 驳回btn弹框的model -->
			<div  class="modal fade" id="myAbandonedModalReject" tabindex="-1" role="dialog" style="width:350px">
								<input  id="myIdReject" type="hidden">
								<input  id="workOrderCodeReject" type="hidden">
								<div class="modal-header">
									<button class="close" type="button" data-dismiss="modal">×</button>
									<h4 id="myModalLabel" align="center" style="color: black;">驳回内容</h3><br>
									<form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
										<input id = "ids"name="id" value="${bizMaterialsStandardReceiveBill.id}" type="hidden">
										<input id = "workOrderCode2" name="workOrderCode" value="${bizMaterialsStandardReceiveBill.workOrderCode}" type="hidden">
										<input id = "status" name="status" value="20" type="hidden">
									<div  style="margin:10px;min-height:20px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
										<textarea id="resonReject" class="mask-text"  onkeyup="this.value = this.value.substring(0,100)" placeholder="请输入驳回内容，多行输入，不多于100个汉字" maxlength="100" ></textarea>
										<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandonedReject()" >确定</a>
										<a href="javascript:void(0)" class="btn" onclick="onclickNoAbandonedReject()">关闭</a>
									</div>
									</div>
									</form:form>
		</div>




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
                    <h4 class="modal-title text-center" id="myModalLabel" >图片预览</h4>
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
	                url: '${ctx}/cusserviceproblem/bizCusServiceProblem/ajaxViewPicsById',
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
		                         ainput +=	 '<a class="swiper-slide " ><img src="' + modelList[i] + '" ></a>';
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