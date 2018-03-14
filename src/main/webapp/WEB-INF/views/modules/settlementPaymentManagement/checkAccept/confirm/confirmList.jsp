<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算员审核验收单</title>
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

	<style>
		.undis{display:none;}
		.g-mask{width:100%;height:100%;position:relative;z-index:99;font-size:0;}
		#g-done_ask{width:400px;height:200px;position:fixed;left:50%;top:50%;transform:translate(-50%,-50%);border:1px solid #333;border-radius:4px;}
		.refuse{height:50px;line-height:50px;font-size:20px;background:#54b4eb;margin:0;}
		.content{color:#000;width:400px;height:100px;resize: none;margin:0;}
		.second{width:400px;}
		.second a{display:block;width:200px;height:50px;line-height:50px;font-size:24px;text-decoration:none;float:left;text-align:center;background:#54b4eb;color:#fff;}
		.second a:first-child{box-sizing:border-box;border-right:1px solid #ccc;}
	</style>
	<script type="text/javascript">
        $(document).ready(function() {
            $("tbody>tr").click(function(){

                $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

            });
            getDepartments();
        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

        function ajaxGetImgs(id){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/settlementPaymentManagement/checkAccept/confirm/pic',
                data: {
                    qcBillId:id
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
                            if('${inspectorConfirm.enginDepartId}' == data[i].value){
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
	<li class="active"><a href="${ctx}/settlementPaymentManagement/checkAccept/confirm/list">结算员审核验收单</a></li>
</ul>
<form:form id="searchForm" modelAttribute="inspectorConfirm" action="${ctx}/settlementPaymentManagement/checkAccept/confirm/list2" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
	<ul class="ul-form">
		<li><label>门店：</label>
			<c:if test="${empty storeDropEnable}">
				<form:select path="storeId" class="input-medium" disabled="true" id="storeId" onchange="getDepartments()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</c:if>
			<c:if test="${!empty storeDropEnable}">
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="getDepartments()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</c:if>
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
			<form:select path="enginDepartId" class="input-medium needClear" id="enginDepartId">
				<form:option value="${inspectorConfirm.enginDepartId }" label="${inspectorConfirm.departmentName }" />
			</form:select>
		</li>
		<li><label>客户姓名：</label>
			<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
		</li>
		<li><label>确认验收日期：</label>
			<input name="beginAcceptCheckDatetime" type="text" readonly="readonly" id="beginAcceptCheckDatetime" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${inspectorConfirm.beginAcceptCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endAcceptCheckDatetime\')}',isShowClear:false});"/> -
			<input name="endAcceptCheckDatetime" type="text" readonly="readonly" id="endAcceptCheckDatetime" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${inspectorConfirm.endAcceptCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginAcceptCheckDatetime\')}',isShowClear:false});"/>
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
		<th>序号</th>
		<th>门店</th>
		<th>工程模式</th>
		<th>区域</th>
		<th>质检单编号</th>
		<th>约检内容</th>
		<th>客户信息</th>
		<th>项目经理</th>
		<th>质检员</th>
		<th>质检员确认验收日期</th>
		<th>验收照片</th>
		<th>应打尾款付款单</th>
		<th>项目经理中期提成</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="inspectorConfirm" varStatus="status">
		<tr>
			<td>
					${status.index+1}
			</td>
			<td>
					${fns:getStoreLabel(inspectorConfirm.storeId, '')}
			</td>
			<td>${fns:getDictLabel(inspectorConfirm.projectMode, 'package_project_mode', '')}</td>
			<td>
					${inspectorConfirm.departmentName}
			</td>
			<td>
					${inspectorConfirm.qcBillCode}
			</td>
			<td>
					${inspectorConfirm.qcCheckNodeName }
			</td>
			<td>
					${inspectorConfirm.communityName }-${inspectorConfirm.buildNumber }-${inspectorConfirm.buildUnit }-${inspectorConfirm.buildRoom }-${inspectorConfirm.customerName }
			</td>
			<td>
					${inspectorConfirm.itemManager }
			</td>
			<td>${inspectorConfirm.checkEmployeeName}</td>
			<td>
				<fmt:formatDate value="${inspectorConfirm.acceptCheckDatetime}" pattern="yyyy-MM-dd"/>
			</td>
			<td>
					<%--<a href="${ctx}/settlementPaymentManagement/checkAccept/confirm/pic?qcBillId=${inspectorConfirm.qcBillId}">查看</a>--%>
				<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs('${inspectorConfirm.qcBillId}')">查看</a>

			</td>
			<td>
				<a href="${ctx}/settlementPaymentManagement/checkAccept/confirm/payment?qcBillId=${inspectorConfirm.qcBillId}">查看</a>
			</td>
			<td>
				<a href="${ctx}/settlementPaymentManagement/checkAccept/confirm/managerAmortizationDetail?orderId=${inspectorConfirm.orderId}&qcBillId=${inspectorConfirm.qcBillId}">查看</a>
			</td>
			<td>
					<%-- <a id="approvePass" href="${ctx}/settlementPaymentManagement/checkAccept/confirm/pass?orderId=${inspectorConfirm.orderId}&qcBillId=${inspectorConfirm.qcBillId}&qcCheckNodeId=${inspectorConfirm.qcCheckNodeId }" onclick="return confirmx('确认要通过吗？', this.href)">通过</a> --%>
				<a id="approvePass" href="javascript:void(0)" onclick="pass(${inspectorConfirm.orderId},${inspectorConfirm.qcBillId},${inspectorConfirm.qcCheckNodeId})">通过</a>
				<a href="#" onclick="reject('${inspectorConfirm.qcBillId}')">驳回</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="g-mask undis" id="refuseSalary">
	<div id="g-done_ask">
		<p class="refuse">请输入驳回理由：</p>
		<textarea class="content" id="reason"></textarea>
		<p class="second">
			<a href="javascript:void(0)" onclick="noReject()">取消</a>
			<a href="javascript:void(0)" onclick="yesReject()">确认</a>
		</p>
	</div>
</div>
<div class="pagination">${page}</div>
<script type="text/javascript">
    var qcBillId;

    function reject(id){
        qcBillId = id;
        $("#refuseSalary").removeClass("undis");
    }
    function yesReject(){
        var reason = $("#reason").val();
        $("#reason").val("");
        $("#refuseSalary").addClass("undis");
        if(reason!=null && reason!=""){
            window.location.href = "${ctx}/settlementPaymentManagement/checkAccept/confirm/reject?qcBillId="+qcBillId+"&reason="+reason;
        }else{
            alertx("驳回原因不能为空");
        }
    }
    function noReject(){
        $("#reason").val("");
        $("#refuseSalary").addClass("undis");
    }

    function pass(orderId,qcBillId,qcCheckNodeId){
        top.$.jBox.confirm("您确认要通过吗？","系统提示",function(v,h,f){
            if(v=="ok"){
                //loading('正在提交，请稍等...');
                //?${inspectorConfirm.orderId}&${inspectorConfirm.qcBillId}&qcCheckNodeId=${inspectorConfirm.qcCheckNodeId }
                $("#approvePass").removeAttr("href");
                $("#approvePass").removeAttr("onclick");
                $.ajax({
                    url:"${ctx}/settlementPaymentManagement/checkAccept/confirm/pass?orderId="+orderId+"&qcBillId="+qcBillId+"&qcCheckNodeId="+qcCheckNodeId,
                    type:"post",
                    success:function(data){
                        if(data == "0"){
                            alertx("操作成功!");
                            $("#searchForm").submit();
                        }else if(data == "1"){
                            alertx("系统异常，请联系管理员！");
                        }else if(data == "2"){
                            alertx("请不要重复提交！");
                        }else if(data == "-1"){
                            alertx("项目经理的中期提成合成金额少于0，请调整中期扣款金额后，再点击通过！");
                        }
                    }
                });
            }
        },{buttonsFocus:1});
        top.$('.jbox-body .jbox-icon').css('top','55px');
    }
</script>

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