<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自采材料报销单管理</title>
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
            findEngineListByStoreIdAndProjectMode();
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

        function ajaxGetImgs(id){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburse/photo',
                data: {
                    materialId:id
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


        function findEngineListByStoreIdAndProjectMode(){
            var storeId = $("#storeId").val();
            var projectModeValue = $("#projectMode").val();
            if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
                return;
            }
            var html3 = '<option value=""></option>';

            //根据门店个,工程模式    动态加载工程区域
            $.ajax({

                url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
                + storeId + "&projectModeValue=" + projectModeValue,
                type : "get",
                success : function(data) {
                    if (null!= data && data.length > 0) {

                        for (var v = 0; v < data.length; v++) {

                            if('${bizMaterialSelfbuyReimburse.engineDepartId}' == data[v].engineDepartId){
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


            $("#materialSelfbuyId").html('');
            var html2 = '<option value=""></option>';
            //自选材料名称
            $.ajax({

                url : "${ctx}/bizmaterialselfbuy/bizMaterialSelfbuy/find_materialSelfbuy_list_ajax?storeId="
                + storeId + "&projectModeValue=" + projectModeValue,
                type : "get",
                success : function(data) {
                    if (null!= data && data.length > 0) {

                        for (var v = 0; v < data.length; v++) {

                            if('${bizMaterialSelfbuyReimburse.materialSelfbuyId}' == data[v].id){
                                $("#s2id_materialSelfbuyId .select2-chosen").html(data[v].materialName);
                                html2 = html2 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].materialName+"</option>";
                            }else{
                                html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].materialName+"</option>";
                            }

                        }

                        $("#materialSelfbuyId").html(html2);
                    } else {
                        $("#materialSelfbuyId").html(html2);
                    }

                }

            });

        }

	</script>
	<style>
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
		.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}
	</style>
</head>
<body>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburse/preList">自采材料报销单列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="bizMaterialSelfbuyReimburse" action="${ctx}/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburse/list" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<ul class="ul-form">

		<li><label>门店：</label>
			<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
				<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<%-- <form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
			</form:select>
		</li>
		<li><label>客户姓名：</label>
			<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
		</li>
		<li><label>项目经理：</label>
			<form:input path="itemManager" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
		</li>
		<li><label>自采材料名称：</label>
			<form:select path="materialSelfbuyId" class="input-medium needClear">
				<%-- <form:option value="" label=""/>
                <form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
			</form:select>
		</li>
		<li><label style="width: 150px;">项目经理申请时间：</label>
			<input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
				   value="<fmt:formatDate value="${bizMaterialSelfbuyReimburse.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:true});"/> 至
			<input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
				   value="<fmt:formatDate value="${bizMaterialSelfbuyReimburse.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:true});"/>

		</li>
		<li style="width: 100%"><label>报销状态：</label>
			<input name="chkAll" id="chkAll" title="全部" onclick="ChkAllClick('reimburseStatus','chkAll')" type="checkbox" />全部
		</li>
		<li style="height: 80px">
			<c:forEach items="${fns:getDictList('reimburse_status')}" var="dict">
				<input type="checkbox" name="reimburseStatus" id="reimburseStatus" value="${dict.value}"  onclick="ChkSonClick('reimburseStatus','chkAll')"  <c:if test="${fns:checkIsExits(bizMaterialSelfbuyReimburse.reimburseStatus,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
			</c:forEach>
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" /></li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>门店</th>
		<th>提交申请时间</th>
		<th>状态</th>
		<th>小区</th>
		<th>客户</th>
		<th>项目经理</th>
		<th>自采材料名称</th>
		<th>客户交费金额</th>
		<th>结算比例</th>
		<th>结算金额</th>
		<th>说明</th>
		<th>上传凭证</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="bizMaterialSelfbuyReimburse">
		<tr>
			<td>
					${bizMaterialSelfbuyReimburse.storeName}
			</td>
			<td>
				<fmt:formatDate value="${bizMaterialSelfbuyReimburse.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>
					${bizMaterialSelfbuyReimburse.statusName}
			</td>
			<td>
					${bizMaterialSelfbuyReimburse.communityName}-
					${bizMaterialSelfbuyReimburse.buildNumber}-
					${bizMaterialSelfbuyReimburse.buildUnit}-
					${bizMaterialSelfbuyReimburse.buildRoom}
			</td>
			<td>
					${bizMaterialSelfbuyReimburse.customerName}<br>
					${bizMaterialSelfbuyReimburse.customerPhone}
			</td>
			<td>
					${bizMaterialSelfbuyReimburse.itemManager}<br>
					${bizMaterialSelfbuyReimburse.itemManagerPhone}
			</td>
			<td>
					${bizMaterialSelfbuyReimburse.materialName}
			</td>
			<td>
					${bizMaterialSelfbuyReimburse.customerPayAmount}元
			</td>
			<td>
					${bizMaterialSelfbuyReimburse.settleRate}%
			</td>
			<td>
					${bizMaterialSelfbuyReimburse.settleAmount}元
			</td>
			<td>
					${bizMaterialSelfbuyReimburse.reimburseRemarks}
			</td>
			<td>
					<%--<a href="${ctx}/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburse/photo?materialId=${bizMaterialSelfbuyReimburse.id}">图片</a>--%>
				<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs('${bizMaterialSelfbuyReimburse.id}')">图片</a>
			</td>
			<td>
				<c:if test="${bizMaterialSelfbuyReimburse.reimburseStatus eq 10 || bizMaterialSelfbuyReimburse.reimburseStatus eq 15}">
					<a href="#" onclick="pass('${bizMaterialSelfbuyReimburse.id}','${bizMaterialSelfbuyReimburse.relatedReimburseId }')">通过</a>
					<a href="#" onclick="reject(this,'${bizMaterialSelfbuyReimburse.id}','${bizMaterialSelfbuyReimburse.relatedReimburseId }','${bizMaterialSelfbuyReimburse.customerName}')">驳回</a>
				</c:if>
				<a href="${ctx}/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburse/details?materialId=${bizMaterialSelfbuyReimburse.relatedReimburseId}">详情</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>




<div class="Black undis" id="onCreateTaskpackage">
	<form id="urgeForm">
		<div class="tc_center">
			<h2 id="orderAddress"></h2>
			<div class="cen_t">
				<div class="cen_tex">
					<span class="span_l"> 自采材料名称：</span>
					<p class="span_r" id="materialName"></p>
				</div>
				<div class="cen_tex">
					<span class="span_l"> 实际结算金额：</span>
					<p class="span_r" id="settleAmount"></p>
				</div>
				<div class="cen_tex">
					<span class="span_l">驳回原因：</span>
					<p class="span_r">
						<textarea id="reimburseStatusRemarks" maxlength="50" name="reimburseStatusRemarks"></textarea>
					</p>
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
			<div class="font28 col_6 maskContent" id="alertRemarks1">自选材料报销驳回成功</div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"  onclick="sendMessage1()" href="javascript:;">我知道了</a>
			</div>
		</div>
	</div>
</div>


<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport2">
	<div class="alertMask">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">您确认要通过吗？</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtn font33 col_fdfcfa" href="javascript:void(0);" onclick="sendMessage2()">
					确定
				</a>
				<a class="maskBtn font33 col_0780ec" href="javascript:void(0);" onclick="cancel()">取消</a>
			</div>
		</div>
	</div>
</div>






<div class="pagination">${page}</div>




<script type="text/javascript">

    var materialIdGlobal;
    var relatedReimburseIdGlobal;

    //关闭
    function sendMessage(){
        $("#subReport").hide();
    }
    function sendMessage1(){
        //提交表单
        $("#searchForm").submit();
    }
    function cancel(){
        $("#subReport2").hide();
    }

    //驳回
    function reject(obj,materialId,relatedReimburseId,customerName){
        materialIdGlobal = materialId;
        relatedReimburseIdGlobal = relatedReimburseId;

        var orderAddress;
        var materialName;
        var settleAmount;

        var trObj = $(obj).parent().parent().find("td").each(function(){
            if($(this).index()==3){
                orderAddress = $(this).text();
            }
            if($(this).index()==6){
                materialName = $(this).text();
            }
            if($(this).index()==9){
                settleAmount = $(this).text();
            }
        })


        $("#onCreateTaskpackage").show();
        $("#orderAddress").text(orderAddress+"-"+customerName);
        $("#materialName").text(materialName);
        $("#settleAmount").text(settleAmount);

    }

    function no(){
        $("#reimburseStatusRemarks").val("");
        $("#onCreateTaskpackage").hide();
    }

    function yes(){
        var reimburseStatusRemarks = $("#reimburseStatusRemarks").val();

        if(null==reimburseStatusRemarks || reimburseStatusRemarks==""){
            $("#alertRemarks").text("请输入驳回原因");
            $("#subReport").show();
            return false;
        }

        $.ajax({
            url: "${ctx}/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburse/update_reject_ajax",
            type: "post",
            data:{
                materialId:materialIdGlobal,
                relatedReimburseId:relatedReimburseIdGlobal,
                reimburseStatusRemarks:reimburseStatusRemarks
            },
            success: function(data) {

                $("#problemSolveNotes").val("");
                $("#onCreateTaskpackage").hide();

                if(null!=data && data=="0"){
                    $("#alertRemarks1").text("自选材料报销驳回成功");
                    $("#subReport1").show();
                }else if(data=="1"){
                    $("#alertRemarks").text("自采材料ID为空");
                    $("#subReport").show();
                }else if(data=="2"){
                    $("#alertRemarks").text("最初的自采材料ID为空");
                    $("#subReport").show();
                }else if(data=="3"){
                    $("#alertRemarks").text("驳回原因为空");
                    $("#subReport").show();
                }else if(data=="4"){
                    $("#alertRemarks").text("当前登录人未登录");
                    $("#subReport").show();
                }else if(data=="5"){
                    $("#alertRemarks").text("本次自采材料报销详情为空");
                    $("#subReport").show();
                }else if(data=="6"){
                    $("#alertRemarks").text("本次自采材料报销单已经审核");
                    $("#subReport").show();
                }else if(data=="7"){
                    $("#alertRemarks").text("自采材料报销状态日志保存失败");
                    $("#subReport").show();
                }else if(data=="8"){
                    $("#alertRemarks").text("本次申请的自采材料报销更新失败");
                    $("#subReport").show();
                }else if(data=="9"){
                    $("#alertRemarks").text("最初的自采材料报销更新失败");
                    $("#subReport").show();
                }else if(data=="10"){
                    $("#alertRemarks").text("给项目经理发送短信失败");
                    $("#subReport").show();
                }

            }
        })

    }


    //通过
    function pass(materialId,relatedReimburseId){
        materialIdGlobal = materialId;
        relatedReimburseIdGlobal = relatedReimburseId;
        $("#subReport2").show();
    }

    function sendMessage2(){
        $.ajax({
            url: "${ctx}/bizmaterialselfbuyreimburse/bizMaterialSelfbuyReimburse/update_pass_ajax",
            type: "post",
            data:{
                materialId:materialIdGlobal,
                relatedReimburseId:relatedReimburseIdGlobal,
            },
            success: function(data) {

                $("#subReport2").hide();

                if(null!=data && data=="0"){
                    $("#alertRemarks1").text("自选材料报销通过成功");
                    $("#subReport1").show();
                }else if(data=="1"){
                    $("#alertRemarks").text("自采材料ID为空");
                    $("#subReport").show();
                }else if(data=="2"){
                    $("#alertRemarks").text("最初的自采材料ID为空");
                    $("#subReport").show();
                }else if(data=="3"){
                    $("#alertRemarks").text("当前登录人未登录");
                    $("#subReport").show();
                }else if(data=="4"){
                    $("#alertRemarks").text("本次自采材料报销详情为空");
                    $("#subReport").show();
                }else if(data=="5"){
                    $("#alertRemarks").text("本次自采材料报销单已经审核");
                    $("#subReport").show();
                }else if(data=="6"){
                    $("#alertRemarks").text("自采材料报销状态日志保存失败");
                    $("#subReport").show();
                }else if(data=="7"){
                    $("#alertRemarks").text("本次申请的自采材料报销更新失败");
                    $("#subReport").show();
                }else if(data=="8"){
                    $("#alertRemarks").text("最初的自采材料报销更新失败");
                    $("#subReport").show();
                }else if(data=="9"){
                    $("#alertRemarks").text("结算明细插入失败");
                    $("#subReport").show();
                }else if(data == "-1"){
                    $("#alertRemarks1").text("订单已经通过了申请竣工，系统将自动作作废此自采材料申请!");
                    $("#subReport1").show();
                }

            }
        })

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