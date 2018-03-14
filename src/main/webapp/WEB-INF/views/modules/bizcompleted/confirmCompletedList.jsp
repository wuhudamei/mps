<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>确认竣工</title>
	<meta name="decorator" content="default"/>

	<link href="${ctxStatic}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctxStatic}/vendor/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
	<link href="${ctxStatic}/vendor/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet">
	<link href="${ctxStatic}/modules/popUp/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${ctxStatic}/modules/popUp/css/swiper.css" rel="stylesheet" type="text/css" />



	<script type="text/javascript" src="${ctxStatic}/vendor/jquery/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/vendor/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctxStatic}/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${ctxStatic}/vendor/jquery/swiper.min.js"></script>
	<script type="text/javascript">
        $(document).ready(function() {
            getDepartments();
            //绑定onclick事件
        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

        //审核通过
        function conCompleted(orderID){
            //alert("订单编号："+orderID);
            //禁用提交按钮
			/* $("#conCompleted").css("color","#CCC");
			 $("#conCompleted").unbind("click"); */
            if(orderID){
                if(confirm("您确认竣工吗？")){
                    $.ajax({
                        url : "${ctx}/bizconfirmcompleted/bizConfirmCompleted/conCompleted",
                        type : "POST",
                        //async:false,
                        data : {
                            orderID : orderID
                        },
                        success : function(data){
                            //alert("date="+data);
                            if(data == 0){
                                alert("操作成功!");
                                window.location.href = "${ctx}/bizconfirmcompleted/bizConfirmCompleted/list";
                            }
                            if(data == 1){
                                alert("修改竣工数据错误!");
                                return false;
                            }
                            if(data == 2){
                                alert("修改订单状态错误!");
                                return false;
                            }
                        }
                    });
                }else{
                    return false;
                }
            }
        }

        //清空查询条件
        function clearCondition() {
            document.getElementById("searchForm").reset();

            var inputObjs = jQuery("#searchForm input[type='text']");
            for (var i = 0; i < inputObjs.length; i++) {
                var inputObj = inputObjs[i];
                inputObj.value = "";
            }

            var selectObjs = jQuery("#searchForm input[class='input-medium']");
            for (var i = 0; i < selectObjs.length; i++) {
                var selectObj = selectObjs[i];
                selectObj.value = "";
            }
        }

        function ajaxGetImgs(id){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/bizbusinesspic/bizBusinessPic/getByBusinessIdOrConAjax',
                data: {
                    orderID:id
                },
                success: function (data) {

                    var modelList = data.mapObject;
                    if (null!==modelList && modelList.length !== 0) {
//		                        '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
                        var ainput='';
                        for (var i = 0; i < modelList.length; i++) {
                            ainput +=	 '<a  class="swiper-slide " ><img src="' + modelList[i].picUrl + '" ></a>';
                        }
                        $("#inputId").html(ainput);
                    }else{
                        var ainput='';
                        ainput ='<a  class="swiper-slide " ><img src="${ctxStatic}/images/nopic.jpg" ></a>';
                        $("#inputId").html(ainput);
                    }
                }
            })

        }
        //加载区域信息
        function getDepartments(){
            $("#enginDepartId").html('');
            $.ajax({
                url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
                type:'post',
                dataType : 'json',
                data:{
                    'storeId':$('#storeId').val(),
                    'projectMode':$('#projectMode').val(),
                    'employeeId':$('#employeeId').val()
                },
                success:function(data){
                    if(data == null){
                        $("#enginDepartId").html('');
                    } else {
                        var html = "<option value=''></option>";
                        for(var i=0;i<data.length;i++){
                            var sec = "";
                            if('${bizConfirmCompleted.enginDepartId}' == data[i].value){
                                sec = "selected='selected'";
                                $("#s2id_enginDepartId .select2-chosen").html(data[i].label);
                            }
                            html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>";
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
	<li class="active"><a href="${ctx}/bizconfirmcompleted/bizConfirmCompleted/preList">确认竣工列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="bizConfirmCompleted" action="${ctx}/bizconfirmcompleted/bizConfirmCompleted/list" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
	<ul class="ul-form">
		<li><label>门店：</label>
			<c:if test="${empty storeDropEnable}">
				<form:select path="storeId" class="input-medium" disabled="true" onchange="getDepartments()" id="storeId">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</c:if>
			<c:if test="${!empty storeDropEnable}">
				<form:select path="storeId" class="input-medium needClear" onchange="getDepartments()" id="storeId">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</c:if>
		</li>
		<li><label>工程模式：</label>
			<c:if test="${empty gongcheng}">
				<form:select path="projectMode" class="input-medium" disabled="true" onchange="getDepartments()" id="projectMode">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</c:if>
			<c:if test="${!empty gongcheng}">
				<form:select path="projectMode" class="input-medium needClear" onchange="getDepartments()" id="projectMode">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</c:if>
		</li>
		<li><label>区域：</label>
			<form:select path="enginDepartId" class="input-medium needClear" id="enginDepartId">
				<form:option value="${bizConfirmCompleted.enginDepartId }" label="${bizConfirmCompleted.departmentName }" />
			</form:select>
		</li>
		<li><label>订单编号：</label>
			<form:input path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
		</li>
		<li>
			<label>订单状态：</label>
			<form:select path="orderStatusNumber" class="input-medium needClear">
				<form:option value="" label="" />
				<form:options items="${fns:getDictList('order_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select>
		</li>
		<li><label>项目经理：</label>
			<form:input path="itemManager" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
		</li>
		<li><label>申请日期：</label>
			<input name="appStartDate" type="text" id="appStartDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${bizConfirmCompleted.appStartDate}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'appEndDate\')}',isShowClear:false});"/> &nbsp;至&nbsp;

			<input name="appEndDate" type="text" id="appEndDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${bizConfirmCompleted.appEndDate}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'appStartDate\')}',isShowClear:false});"/>
		</li>
		<li><label>实际竣工日期：</label>
			<input name="realFinishProjectStartDate" type="text" id="realFinishProjectStartDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${bizConfirmCompleted.realFinishProjectStartDate}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'realFinishProjectEndDate\')}',isShowClear:false});"/> &nbsp;至&nbsp;

			<input name="realFinishProjectEndDate" type="text" id="realFinishProjectEndDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${bizConfirmCompleted.realFinishProjectEndDate}" pattern="yyyy-MM-dd"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'realFinishProjectStartDate\')}',isShowClear:false});"/>
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
		<th>工程模式</th>
		<th>区域</th>
		<th>订单编号</th>
		<th>小区门牌号</th>
		<th>顾客</th>
		<th>项目经理</th>
		<th>合同开工日期</th>
		<th>合同竣工日期</th>
		<th>实际开工日期</th>
		<th>申请日期</th>
		<th>实际竣工日期</th>
		<th>图片</th>
		<th>订单状态</th>
		<shiro:hasPermission name="bizcompletedaudit:bizCompletedAudit:edit"><th>操作</th></shiro:hasPermission>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="orderList">
		<tr>
			<td>
					${fns:getStoreLabel(orderList.storeId, '')}
			</td>
			<td>${fns:getDictLabel(orderList.projectMode, 'project_mode','')}</td>
			<td>
					${orderList.departmentName}
			</td>
			<td>
					${orderList.orderNumber}
			</td>
			<td>
					${orderList.communityName}-${orderList.buildNumber }-${orderList.buildUnit }-${orderList.buildRoom }
			</td>
			<td>${orderList.customerName }</td>
			<td>
					${orderList.employeeRealName}
			</td>
			<td>
				<fmt:formatDate type="date" value="${orderList.contractStartDate}"/>
			</td>
			<td>
				<fmt:formatDate type="date" value="${orderList.contractEndDate}"/>
			</td>
			<td>
				<fmt:formatDate type="date" value="${orderList.actualStartDate}"/>
			</td>
			<td>
				<fmt:formatDate type="date" value="${orderList.applyDatetime}"/>
			</td>
			<td>
				<fmt:formatDate type="date" value="${orderList.realFinishProjectDate}"/>
			</td>
				<%-- <td>
					<c:if test="${!empty orderList.countPic}">
						<a href="${ctx }/bizbusinesspic/bizBusinessPic/getByBusinessIdOrCon?orderID=${orderList.id }">${orderList.countPic }</a>
					</c:if>
					<c:if test="${empty orderList.countPic}">0</c:if>张
				</td> --%>
			<td>
					<%--<a href="${ctx }/bizbusinesspic/bizBusinessPic/getByBusinessIdOrCon?orderID=${orderList.id }">查看</a>--%>
				<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs('${orderList.id}')">查看</a>
			</td>
			<td>
					${fns:getDictLabel(orderList.orderStatusNumber, 'order_status', '')}
			</td>
			<td>
				<c:if test="${orderList.orderStatusNumber == '340'}">
					<a href="" id="conCompleted" onclick="conCompleted(${orderList.id});return false;">确认竣工</a>
					<%-- <a href="${ctx}/bizconfirmcompleted/bizConfirmCompleted/conCompleted?orderID=${orderList.id }">确认竣工</a> --%>
				</c:if>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>

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
</script>
</body>
</html>