<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算单管理</title>
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

            $("tbody>tr").click(function(){

                $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

            });


            var html3 ="";
            var storeId = $("#storeId").val();
            var settleNodeName="${bizNormalPmSettle.settleNodeName}";

            if (storeId!="" &&undefined!=storeId){

                //根据门店加载结算节点
                $.ajax({

                    url : "${ctx}/bizsettlebill/bizNormalPmSettle/findSettleNodeNameByStoreId?storeId="
                    + storeId ,
                    type : "get",
                    async:true,
                    success : function(data) {
                        if (null!= data && data.length > 0) {

                            for (var v = 0; v < data.length; v++) {


                                if(data[v]==settleNodeName){
                                    $("#s2id_settleNodeId .select2-chosen").html(settleNodeName)

                                    html3 +='<option value="'+data[v]+'" selected=selected>'+data[v]+'</option>'

                                }else{

                                    html3 +='<option value="'+data[v]+'">'+data[v]+'</option>'
                                }

                            }

                            $("#settleNodeId").html(html3);
                        }



                    }

                });

            }
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
                url: '${ctx}/bizsettlebill/bizNormalPmSettle/settlePicList',
                data: {
                    settleId:id
                },
                success: function (data) {

                    var modelList = data.mapObject;
                    if (null!==modelList && modelList.length !== 0) {
//		                        '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
                        var ainput='';
                        for (var i = 0; i < modelList.length; i++) {
                            ainput +=	 '<a  class="swiper-slide " ><img src="' + modelList[i] + '" ></a>';
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

        function findSettleNodeByStoreId(storeId){
            var html3 = '<option value=""></option>';
            var storeId = $("#storeId").val();
            var projectModeValue = $("#projectMode").val();
            if (storeId!="" &&undefined!=storeId){

                //根据门店加载结算节点
                $.ajax({

                    url : "${ctx}/bizsettlebill/bizNormalPmSettle/findSettleNodeNameByStoreId?storeId="
                    + storeId ,
                    type : "get",
                    async:true,
                    success : function(data) {
                        if (null!= data && data.length > 0) {

                            for (var v = 0; v < data.length; v++) {
                                html3 +='<option value="'+data[v]+'">'+data[v]+'</option>'
                            }

                            $("#settleNodeId").html(html3);
                        }



                    }

                });

            }



            setTimeout("findEngineListByStoreIdAndProjectMode()",300);


        }
        function findEngineListByStoreIdAndProjectMode(){
            var html3 = '<option value=""></option>';
            var storeId = $("#storeId").val();
            var projectModeValue = $("#projectMode").val();


            if (storeId!="" &&undefined!=storeId && undefined!=projectModeValue && projectModeValue!="") {

                //根据门店个,工程模式    动态加载工程区域
                $.ajax({

                    url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
                    + storeId + "&projectModeValue=" + projectModeValue,
                    type : "get",
                    async:false,
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
            $("#settleNodeId").find("option:selected").val(null)


        }

        function acceptOrRefuseSettle(settleValue,type,obj){


            if(2==type){

                //收到


                //询问框
                layer.confirm('您确定收到吗？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    var param = {
                        settleStatus:type,
                        settleId:settleValue

                    };
                    $.ajax({

                        url : "${ctx}/bizsettlebill/bizNormalPmSettle/updateSettleBill.php",
                        type: "post",
                        data: param,
                        async:false,
                        success : function(data) {

                            if(1==data){

                                layer.msg("收到成功", {icon: 1});

                                $(obj).parent().prev().text("结算中")
                                $(obj).remove();
                            }else if (data==2){

                                layer.msg("您已经操作该结算申请单了,请点击查询刷新数据", {icon: 1});

                            }

                        }

                    });



                }, function(){
                    layer.msg('您取消了操作', {icon: 2});

                });


            }else if(3==type){
                //拒绝

                layer.prompt({title: '请输入拒绝的原因', formType: 2}, function(text){


                    var param = {
                        settleStatus:type,
                        settleId:settleValue,
                        checkReply:text

                    };


                    $.ajax({

                        url : "${ctx}/bizsettlebill/bizNormalPmSettle/updateSettleBill.php",
                        type: "post",
                        data: param,
                        async:false,
                        success : function(data) {

                            if(1==data){
                                layer.msg("拒绝成功", {icon: 1});


                                $(obj).parent().prev().text("已拒绝")
                                $(obj).parent().next().html("不可打款")
                                $(obj).parent().html("不可操作")
                            }else if (data==2){

                                layer.msg("您已经操作该结算申请单了,请点击查询刷新数据", {icon: 1});
                            }

                        }

                    });




                });



            }else if(4==type){
                //打款
                layer.prompt({title: '请输入打款的金额', formType: 3}, function(text){



                    if(checkPrice(text)){
                        //保存
                        var param = {
                            settleStatus:type,
                            settleId:settleValue,
                            settleAmount:text

                        };

                        $.ajax({

                            url : "${ctx}/bizsettlebill/bizNormalPmSettle/updateSettleBill.php",
                            type: "post",
                            data: param,
                            async:false,
                            success : function(data) {

                                if(1==data){
                                    layer.msg("打款成功", {icon: 1});

                                    $(obj).parent().prev().prev().text("已打款")
                                    $(obj).parent().prev().text("不可操作")
                                    $(obj).parent().text(text+"元")


                                }else if (data==2){

                                    layer.msg("您已经操作过该结算申请单了,请点击查询刷新数据", {icon: 1});
                                }

                            }

                        });




                    }else{
                        layer.msg('您输入有误,系统取消了您的这次操作', {icon: 2});

                    }



                });


            }





        }

        function updateSettleStatus(param){
            $.ajax({

                url : "${ctx}/bizsettlebill/bizNormalPmSettle/updateSettleBill.php",
                type: "post",
                data: param,
                async:false,
                success : function(data) {

                    if(1==data){
                        return "操作成功";


                    }else if (data==2){
                        return"您已经操作过该结算申请单了";

                    }else{
                        return "由于某些原因 失败了"

                    }

                }

            });


        }


        function checkPrice(price){
            return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(price.toString());
        }


	</script>
	<script type="text/javascript" src="${ctxStatic}/modules/settleJs/layer/layer.js"></script>
	<script src="${ctxStatic}/modules/settleJs/layer/extend/layer.ext.js"></script>
	<style>

		.asd {background: orange !important;}
	</style>
</head>

<body>

<c:set var="user" value="${fns:getUser()}"></c:set>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/bizsettlebill/bizNormalPmSettle/prelist">结算单列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="bizNormalPmSettle" action="${ctx}/bizsettlebill/bizNormalPmSettle/list" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<ul class="ul-form">
		<li><label>门店：</label>
			<form:select path="storeId" class="input-medium needClear" onchange="findSettleNodeByStoreId()">
				<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</li>
			<%--<li><label class="control-label">工程模式：</label>

					<form:select path="projectMode" class="input-medium needClear" >
						<form:option value="2" label="传统" />

					</form:select>


			</li>--%>


		<li><label>区域：</label>
			<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
				<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>



		</li>


		<li><label>订单编号：</label>
			<form:input path="orderNumber" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
		</li>
		<li>



			<label>结算阶段：</label>
			<select name="settleNodeName" class="input-medium needClear" id="settleNodeId">
			</select>

		</li>









		<li><label>结算单状态：</label>
			<form:select path="settleStatus" class="input-medium needClear">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('settle_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</li>

		<li><label>项目经理：</label>
			<form:input path="managerName" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
		</li>
		<li><label>质检员：</label>
			<form:input path="pqcName" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
		</li>
		<li><label>客户姓名：</label>
			<form:input path="customerName" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
		</li>
		<li><label>申请时间：</label>
			<input id="startApplyDateId" name="startApplyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${bizNormalPmSettle.startApplyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endApplyDateId\')}',isShowClear:false});"/>
			<input id="endApplyDateId" name="endApplyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${bizNormalPmSettle.endApplyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startApplyDateId\')}',isShowClear:false});"/>
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
		<th>模式</th>
		<th>申请时间</th>
		<th>工程地址</th>
		<th>订单编号</th>
		<th>客户姓名</th>
		<th>项目经理</th>
		<th>质检</th>
		<th>区域</th>
		<th>结算阶段</th>
		<th>描述内容</th>
		<th>附件</th>
		<th>状态</th>

		<shiro:hasPermission name="bizsettlebill:bizNormalPmSettle:edit"><th>操作</th></shiro:hasPermission>
		<shiro:hasPermission name="bizsettlebill:bizNormalPmSettle:edit"><th>打款</th></shiro:hasPermission>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="bizNormalPmSettle" varStatus="status">
		<tr>
			<td>${status.index+1}</td>
			<td>
					${bizNormalPmSettle.storeName}
			</td>
			<td>
					${bizNormalPmSettle.projectModeName}
			</td>
			<td>
				<fmt:formatDate value="${bizNormalPmSettle.applyDatetime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
			</td>
			<td>
					${bizNormalPmSettle.customerAddress}
			</td>
			<td>
					${bizNormalPmSettle.orderNumber}
			</td>
			<td>
					${bizNormalPmSettle.customerName}
			</td>
			<td>
					${bizNormalPmSettle.managerName}
			</td>
			<td>
					${bizNormalPmSettle.pqcName}
			</td>
			<td>
					${bizNormalPmSettle.engineDepartName}
			</td>
			<td>
					${bizNormalPmSettle.settleNodeName}
			</td>
			<td>
					${bizNormalPmSettle.settleRemarks}
			</td>
			<td>
					<%--<a href="${ctx}/bizsettlebill/bizNormalPmSettle/settlePicList?settleId=${bizNormalPmSettle.settleId}">图片</a>--%>
				<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs('${bizNormalPmSettle.settleId}')">图片</a>

			</td>
			<td>
					${bizNormalPmSettle.settleStatusName}
			</td>
			<shiro:hasPermission name="bizsettlebill:bizNormalPmSettle:edit">



				<c:if test="${bizNormalPmSettle.settleStatus<3}">
					<td>
						<c:if test="${bizNormalPmSettle.settleStatus==1}">
							<a href="#" onclick="acceptOrRefuseSettle('${bizNormalPmSettle.settleId}','2',this)">收到</a>
						</c:if>

						<a href="#" onclick="acceptOrRefuseSettle('${bizNormalPmSettle.settleId}','3',this)">拒绝</a>

					</td>

					<td>
					<a href="#" onclick="acceptOrRefuseSettle('${bizNormalPmSettle.settleId}','4',this)">打款</a>
				</c:if>
				</td>



				<c:if test="${bizNormalPmSettle.settleStatus==3}">

					<td>
						不可操作
					</td>

					<td>


						不可打款

					</td>

				</c:if>


				<c:if test="${bizNormalPmSettle.settleStatus==4}">

					<td>
						不可操作
					</td>

					<td>


						<fmt:formatNumber value="${bizNormalPmSettle.settleAmount}" pattern="0.00"> </fmt:formatNumber>元

					</td>

				</c:if>




			</shiro:hasPermission>
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