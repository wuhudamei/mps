<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>竣工审核</title>
<meta name="decorator" content="default" />
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
.undis {
	display: none;
}

.g-mask {
	width: 100%;
	height: 100%;
	position: relative;
	z-index: 99;
	font-size: 0;
}

#g-done_ask {
	width: 400px;
	height: 200px;
	position: fixed;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	border: 1px solid #333;
	border-radius: 4px;
}

.refuse {
	height: 50px;
	line-height: 50px;
	font-size: 20px;
	background: #54b4eb;
	margin: 0;
}

.content {
	color: #000;
	width: 400px;
	height: 100px;
	resize: none;
	margin: 0;
}

.second {
	width: 400px;
}

.second a {
	display: block;
	width: 200px;
	height: 50px;
	line-height: 50px;
	font-size: 24px;
	text-decoration: none;
	float: left;
	text-align: center;
	background: #54b4eb;
	color: #fff;
}

.second a:first-child {
	box-sizing: border-box;
	border-right: 1px solid #ccc;
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
<script type="text/javascript">
	$(document).ready(function() {
		$("tbody>tr").click(function(){

		    $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

		});
		getDepartments();
		//自动提示输入字符串个数
		$("#reason").keyup(function(){
	    	if($("#reason").val().length > 100){
	    	   $("#reason").val( $("#reason").val().substring(0,100) );
	    	}
	    	$("#word").text( 100 - $("#reason").val().length ) ;
	    });
		
		var arrSon = document.getElementsByName("orderStatusNumber");
		var cbAll = document.getElementById("chkAll");
		var boo = true;
		for(var i = 0; i < arrSon.length; i++){
			if(arrSon[i].checked == false){
				boo=false;
			}
		}
		if(boo){
			cbAll.checked=true;
		}
		
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

    function ajaxGetImgs(id){
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '${ctx}/bizbusinesspic/bizBusinessPic/getByBusinessIdOrAuditAjax',
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

	//审核通过
	function auditSucess(orderID,realFinishProjectDate){
		if(orderID){
			var result="确定竣工审核通过吗？";
			$.ajax({
				url:"${ctx}/bizcompletedaudit/bizCompletedAudit/checkOrderTaskpackByOrderId?orderId="+orderID,
				type : "POST",
				success : function(data){
					if(data >0){
					   result="订单还有未结算完成的任务包，您确认要通过吗？";	
					}
					if(confirm(result)){
						//禁用提交按钮
						$("#auditSuccess").css("color","#CCC");
						$("#auditSuccess").unbind("click");
						$.ajax({
							url : "${ctx }/bizorderfinishprojectbill/bizOrderFinishProjectBill/auditSucess",
							type : "POST",
						    //async:false,
						    data : {
						    	orderID : orderID,
						    	realFinishProjectDate:realFinishProjectDate
							},
						  	success : function(data){
						  		//alert("date="+data);
		                        if(data == 3){
								  alertx("请不要重复提交！");	
								}
								if(data == 0){
								  alertx("操作成功!");
								  $("#searchForm").submit();
							  	}
								if(data == 1){
									alertx("修改竣工数据错误!");
								 	return false;
							  	}
								
								if(data == 2){
									alertx("修改订单状态错误!");
								 	return false;
							  	}
								if(data == 4){
									alertx("请财务确认二期款！");
								}
								if(data == -1){
									alertx("项目经理的竣工提成合成金额少于0，请调整竣工扣款金额后，再点击通过！");
								}
								if(data == -2){
									alertx("您还有未审核通过的自采材料报销，请先去审核通过之后再做竣工审核!");
								}
						  }
						});
					}
				}
			});
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
		
		var arrSon = document.getElementsByName("orderStatusNumber");
		for (i = 0; i < arrSon.length; i++) {
			if(arrSon[i].checked){
				arrSon[i].checked=false;
			}
		}
	}
	
	//驳回
	function abolish(id){
		$("#id").val(id);
		$("#abolishSummary").removeClass("undis");
	}
	//驳回取消
	function noAbolish(){
		$("#reason").val("");
		$("#id").val("");
		$("#abolishSummary").attr("class", "undis");
	}
	//驳回确认
	function yesAbolish(){
		//loading('正在提交，请稍等...');
		$("a[data='abolish']").removeAttr("onclick");
		$("a[data='abolish']").removeAttr("href");
		
		var cancelReason = $("#reason").val();
		var id = $("#id").val();
		 $("#abolishSummary").attr("class", "undis");
		$.ajax({
			url : "${ctx}/bizorderfinishprojectbill/bizOrderFinishProjectBill/auditFail",
			type : "POST",
		    data : {orderID : id,remarks : $("#reason").val()},
		  	success : function(data){
		  		//alert("date="+data);
		  		if(data == 3){
					  alertx("请不要重复提交！");	
				}
				if(data == 0){
				  alertx("操作成功!");
				  $("#searchForm").submit();
			  	}
				if(data == 1){
					alertx("修改竣工数据错误!");
				 	return false;
			  	}
				if(data == 2){
					alertx("修改订单状态错误!");
				 	return false;
			  	}
		  }
		});
		//window.location.href="${ctx}/bizorderfinishprojectbill/bizOrderFinishProjectBill/auditFail?orderID="+id;
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
						if('${bizCompletedAudit.enginDepartId}' == data[i].value){
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
	
	// --全选框被单击---
	function ChkAllClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		var tempState = cbAll.checked;
		for (i = 0; i < arrSon.length; i++) {
			if (arrSon[i].checked != tempState)
				arrSon[i].click();
		}
	}

	// --子项复选框被单击---
	function ChkSonClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		for (var i = 0; i < arrSon.length; i++) {
			if (!arrSon[i].checked) {
				cbAll.checked = false;
				return;
			}
		}
		cbAll.checked = true;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/bizcompletedaudit/bizCompletedAudit/preList">竣工审核列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizCompletedAudit"
		action="${ctx}/bizcompletedaudit/bizCompletedAudit/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input id="employeeId" name="employeeId" type="hidden"
			value="${employeeId}" />
		<ul class="ul-form">
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true"
						onchange="getDepartments()" id="storeId">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear"
						onchange="getDepartments()" id="storeId">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>
			<li><label>工程模式：</label> <c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium"
						disabled="true" onchange="getDepartments()" id="projectMode">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear"
						onchange="getDepartments()" id="projectMode">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>
			<li><label>区域：</label> <form:select path="enginDepartId"
					class="input-medium needClear" id="enginDepartId">
					<form:option value="${bizCompletedAudit.enginDepartId }"
						label="${bizCompletedAudit.departmentName }" />
				</form:select></li>
			<li><label>订单编号：</label> <form:input path="orderNumber"
					htmlEscape="false" maxlength="18" class="input-medium needClear" />
			</li>
			<%-- <li><label>订单状态：</label> <form:select path="orderStatusNumber"
					class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('order_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li> --%>
			<li><label>项目经理：</label> <form:input path="itemManager"
					htmlEscape="false" maxlength="255" class="input-medium needClear" />
			</li>
			<li><label>申请日期：</label> <input name="appStartDate" type="text"
				id="appStartDate" readonly="readonly" maxlength="20"
				class="input-medium Wdate"
				value="<fmt:formatDate value="${bizCompletedAudit.appStartDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'appEndDate\')}',isShowClear:false});" />
				&nbsp;至&nbsp; <input name="appEndDate" type="text" id="appEndDate"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizCompletedAudit.appEndDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'appStartDate\')}',isShowClear:false});" />
			</li>
			<li><label>实际竣工日期：</label> <input
				name="realFinishProjectStartDate" type="text"
				id="realFinishProjectStartDate" readonly="readonly" maxlength="20"
				class="input-medium Wdate"
				value="<fmt:formatDate value="${bizCompletedAudit.realFinishProjectStartDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'realFinishProjectEndDate\')}',isShowClear:false});" />
				&nbsp;至&nbsp; <input name="realFinishProjectEndDate" type="text"
				id="realFinishProjectEndDate" readonly="readonly" maxlength="20"
				class="input-medium Wdate"
				value="<fmt:formatDate value="${bizCompletedAudit.realFinishProjectEndDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'realFinishProjectStartDate\')}',isShowClear:false});" />
			</li>
			
			<li style="width:100%">
				<label>状态：</label>
				<input id="chkAll" name="chkAll" type="checkbox" value="全选" onclick="ChkAllClick('orderStatusNumber','chkAll')" />全选
			</li>
			<li>
				<input type="checkbox" name="orderStatusNumber"  value="300"  onclick="ChkSonClick('orderStatusNumber','chkAll')"  <c:forEach items="${bizCompletedAudit.status}" var="item"><c:if test="${item == '300'}">checked="checked"</c:if></c:forEach>  />300--已申请竣工
				<input type="checkbox" name="orderStatusNumber"  value="330"  onclick="ChkSonClick('orderStatusNumber','chkAll')"  <c:forEach items="${bizCompletedAudit.status}" var="item"><c:if test="${item == '330'}">checked="checked"</c:if></c:forEach> />330--结算员竣工审核不通过
				<input type="checkbox" name="orderStatusNumber"  value="340"  onclick="ChkSonClick('orderStatusNumber','chkAll')"  <c:forEach items="${bizCompletedAudit.status}" var="item"><c:if test="${item == '340'}">checked="checked"</c:if></c:forEach> />340--结算员竣工审核通过
				<input type="checkbox" name="orderStatusNumber"  value="400"  onclick="ChkSonClick('orderStatusNumber','chkAll')"  <c:forEach items="${bizCompletedAudit.status}" var="item"><c:if test="${item == '400'}">checked="checked"</c:if></c:forEach> />400--确认已竣工
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearCondition()" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
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
				<th>申请日期</th>
				<th>实际竣工日期</th>
				<th>图片</th>
				<th>订单状态</th>
				<th>任务包数量</th>
				<th>项目经理竣工提成明细</th>
				<shiro:hasPermission name="bizcompletedaudit:bizCompletedAudit:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="orderList">
				<tr>
					<td>${fns:getStoreLabel(orderList.storeId, '')}</td>
					<td>${fns:getDictLabel(orderList.projectMode, 'project_mode','')}</td>
					<td>${orderList.departmentName}</td>
					<td>${orderList.orderNumber}</td>
					<td>${orderList.communityName}-${orderList.buildNumber }-${orderList.buildUnit }-${orderList.buildRoom }
					</td>
					<td>${orderList.customerName }</td>
					<td>${orderList.employeeRealName}</td>
					<td><fmt:formatDate type="date"
							value="${orderList.contractStartDate}" /></td>
					<td><fmt:formatDate type="date"
							value="${orderList.contractEndDate}" /></td>
					<td><fmt:formatDate type="date"
							value="${orderList.applyDatetime}" /></td>
					<td><fmt:formatDate type="date"
							value="${orderList.realFinishProjectDate}" /></td>
					<td><a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs('${orderList.id}')">查看</a>					</td>
					<td>${fns:getDictLabel(orderList.orderStatusNumber, 'order_status', '')}
					</td>
					<td><a href="${ctx}/scheduling/orderTaskpackage/viewByOrderID?orderID=${orderList.id }">查看</a></td>
					<td>
						<c:if
								test="${orderList.orderStatusNumber == '300'}">
						<a href="${ctx}/bizcompletedaudit/bizCompletedAudit/queryManagerCompletedDetail?orderId=${orderList.id}">查看</a>
						</c:if>
					</td>
					<td><c:if
							test="${orderList.orderStatusNumber == '340'}">
							<%--<a href="${ctx }/bizorderfinishprojectbill/bizOrderFinishProjectBill/preUpdate?orderID=${orderList.id }">修改</a>--%>
						<a href="javascript:void(0)" onclick="updateCompletedDate(${orderList.id },'<fmt:formatDate value='${orderList.realFinishProjectDate}' pattern='yyyy-MM-dd HH:mm:ss'/>',${orderList.orderFinishProjectBillId})">修改</a>

					    </c:if> <c:if
							test="${orderList.orderStatusNumber == '300'}">
							<a id="auditSuccess" href="javascript:void(0)"  onclick="auditSucess(${orderList.id},'<fmt:formatDate type="date"
							value="${orderList.realFinishProjectDate}" />')">通过</a>
							<a href="javascript:void(0)" data="abolish"
								onclick="abolish('${orderList.id}')">驳回</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	<!-- 修改可申请日期弹框 -->
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="dealAlert">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">提示</p>
				<div class="font28 col_6 maskContent">
					<input type="hidden" id="orderId" />
					<input type="hidden" id="orderFinishProjectBillId" />
					实际竣工日期：
					<input id="newPlanApplyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate content" style="width: 200px;"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtn font33 col_fdfcfa" href="javascript:void(0)" onclick="dealAlertSure()">确定</a>
					<a class="maskBtn font33 col_0780ec" href="javascript:void(0)" onclick="dealAlertClose()">取消</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="informBox">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="informBoxRemark"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f" href="javascript:void(0)"  onclick="informBoxClose()">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="informBoxTwo">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="informBoxRemarkTwo"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"   onclick="informBoxCloseTwo()">我知道了</a>
				</div>
			</div>
		</div>
	</div>

	<!-- 驳回弹框div -->
	<div class="g-mask undis" id="abolishSummary">
		<div id="g-done_ask">
			<p class="refuse">请输入作废理由：</p>
			<input type="hidden" id="id" />
			<textarea class="content" id="reason"></textarea>
			<p style="font-size: 13px; background: #2fa4e7; margin: 0px">
				还可以输入<i id="word">100</i>个字
			</p>
			<p class="second">
				<a href="javascript:void(0)" onclick="noAbolish()">取消</a> <a
					href="javascript:void(0)" id="yesSubmit" onclick="yesAbolish()">确认</a>
			</p>
		</div>
	</div>

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


		function updateCompletedDate(orderId,completeDate,orderFinishProjectBillId) {
            $("#dealAlert").show();
            $("#newPlanApplyDate").val(completeDate);
            $("#orderId").val(orderId);
            $("#orderFinishProjectBillId").val(orderFinishProjectBillId);
        }

        //提示弹框
        function dealAlertClose(){
            $("#dealAlert").hide();
        }
        function informBoxClose(){
            $("#informBox").hide();
        }
        function informBoxCloseTwo(){
            //提交表单
            $("#searchForm").submit();
        }

        //同意
        function dealAlertSure(){
            var newPlanApplyDate  = $("#newPlanApplyDate").val();
            if(newPlanApplyDate == ""){
                $("#informBoxRemark").text("请输入实际竣工日期");
                $("#informBox").show();
                return;
            }
            $.ajax({
                url : "${ctx}/bizorderfinishprojectbill/bizOrderFinishProjectBill/updateByDate",
                type : "POST",
                //async:false,
                data : {
                    realFinishProjectDate : newPlanApplyDate,
                    id : $("#orderFinishProjectBillId").val(),
                    orderID : $("#orderId").val()
                },
                success : function(data){
                    if(data == 0){
                        $("#informBoxRemarkTwo").text("实际竣工日期修改成功！");
                        $("#informBoxTwo").show();
                    }
                    if(data == 1){
                        alert("新增数据错误!");
                        return false;
                    }
                }
            });
        }
	</script>
</body>
</html>