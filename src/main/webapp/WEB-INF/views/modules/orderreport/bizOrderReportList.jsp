<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>返单信息上报</title>
<meta name="decorator" content="default" />
	<style>
		.clickDom{
			background: #3da5ee;
			color:#fff;
		}

	</style>

<script type="text/javascript">
		$(document).ready(function() {
            $("tbody>tr").click(function(){

                $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

            });


            $(".btn_n").click(function(){
                $("#relatedContractDivId").hide();
                $("#serviceDivId").hide();

                $("#relatedOrderInfoTableId").html("<tr> <td>订单编号</td> <td>签约日期</td>   <td>客户姓名</td> <td>小区名称</td><td>工程地址</td>  <td>操作</td> </tr>");
                $("#relatedOrderNumberValId").val("");

                $("#remarksId").val("");


            })
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

		function openModel(divId,id,cusName,cusPhone,inStoreDateTime){


			$("#orderDiv2").html("");
			$("#orderDiv1").html("");
			$("#openModel :input").each(function () { 
				$(this).val(""); 
			}); 
			$("#reportOrderId").val(id);
			$("#"+divId+">h4").text(cusName+"---"+cusPhone);
            if(inStoreDateTime!=undefined && inStoreDateTime!=""){

                $("#instoreDateTimeId").val(format(inStoreDateTime,'yyyy-MM-dd'))

                $("#instoreDateTimeId").attr("disabled",true);
            }
			timeClick("openModel",divId)
		}
        
        
		function timeClick(modelId,divId){
			$('#'+modelId).modal('show');
			$('#'+divId).show();
			if(divId=="fir"){
				$('#sec').hide();
				$('#ser').hide();
			}else if(divId=="sec"){
				$('#fir').hide();
				$('#ser').hide();
			}else if(divId == "ser"){
				$('#fir').hide();
				$('#sec').hide();
			}
			
		}
		
		function onclickClean(modelId){
			$('#openModel').modal('hide');
		}
		
		function onclickFrom(status){
			if(status == 30){
				jdNoWriteOrder();
			}else if(status == 40){
				jdWriteOrder();
			}else if(status == 50){
				writeOrder();
			}

		}
		
		function jdNoWriteOrder(){
			var id=$("#reportOrderId").val();
			var instoreDatetime = $("#instoreDatetime").val();

            if(instoreDatetime==""){

                layer.msg("请填写进店时间", {icon: 2});
                return;

            }



            var instoreRemarks = $("#instoreRemarks").val();
			$.ajax({
				url : "${ctx}/orderReport/bizOrderReport/jdNoWriteOrder?id=" 
						+ id+"&instoreDatetime="+instoreDatetime+"&instoreRemarks="+instoreRemarks,
				type : "post",
				success : function(data) {
					if (data == "0") {
                        $('#openModel').modal('hide');
						 location.reload();
					} else {
						alertx('系统错误，请联系管理员！');
					}	
				}
			});
		}


        /**
		 * 已进店已签单
         */
		function jdWriteOrder(){
			var id=$("#reportOrderId").val();
			var relOrderNums = $("#relOrderNum").val();
            var instoreDatetime = $("#instoreDateTimeId").val();


            var signBillDatetime = $("#signBillDatetime2").val();

            var signBillRemarks = $("#signBillRemarks2").val();
		if(relOrderNums==""){
        layer.msg("请选择关联的合同", {icon: 2});
        return;

			}



		if(instoreDatetime==""){

    layer.msg("请填写进店时间", {icon: 2});
    return;

		}



		if(signBillDatetime==""){

    layer.msg("请填写签单时间", {icon: 2});
    return;

		}

        $.ajax({
            url : "${ctx}/orderReport/bizOrderReport/jdWriteOrder?id="+ id
            +"&relOrderNums="+relOrderNums+"&instoreDatetime="+instoreDatetime
            +"&signBillDatetime="+signBillDatetime+"&signBillRemarks="+signBillRemarks,
            type : "post",
            success : function(data) {
                if (data == "0") {
                    $('#openModel').modal('hide');
                    location.reload();
                } else {
                    alertx('系统错误，请联系管理员！');
                }
            }
        });






		}
		
		function writeOrder(){
			var id=$("#reportOrderId").val();
			var relOrderNums = $("#relOrderNum").val();
			var signBillDatetime = $("#signBillDatetime3").val();
			var signBillRemarks = $("#signBillRemarks3").val();
			$.ajax({
				url : "${ctx}/orderReport/bizOrderReport/writeOrder?id="+ id
						+"&relOrderNums="+relOrderNums+"&signBillDatetime="+signBillDatetime
						+"&signBillRemarks="+signBillRemarks,
				type : "post",
				success : function(data) {
					if (data == "0") {
                        $('#openModel').modal('hide');
						 location.reload();
					} else {
						alertx('系统错误，请联系管理员！');
					}	
				}
			});
		}
		
		function addRelOrder(divId,orderNumInputId){

			var relOrderNum=$("#"+orderNumInputId).val();

			if($("#"+relOrderNum).attr("id")!=undefined){

                layer.msg("您已经添加过该合同", {icon: 2});
                return;

			}

			var relOrderNums = $("#relOrderNum").val();

			if(relOrderNum == null || relOrderNum == ""){
				return;
			}
		    if(relOrderNums == null || relOrderNums ==""){
		    	relOrderNums = relOrderNum;
		    }else{
		    	relOrderNums = relOrderNums +","+ relOrderNum;
		    }

		    $("#relOrderNum").val(relOrderNums)
			var htmlStr="<div style='float:left;' id="+relOrderNum+" ><font style='color: black; font-size:20px;padding-left: 20px;'>"+relOrderNum+"</font><a href='javascript:void(0)' style='font-size: 20;padding-left: 5px;' onclick='delRelOrder(this)'>删除</a></div>";
			$("#"+divId).append(htmlStr);
			$("#"+orderNumInputId).val(null);
		}

		function delRelOrder(obj){
			var newRelOrderNums="";
			var relOrderNums = $("#relOrderNum").val();
			var delRelOrderNum = $(obj).prev().text();
			var relOrderNumArr = relOrderNums.split(",");
			for(var i = 0;i<relOrderNumArr.length;i++){
				if(delRelOrderNum != relOrderNumArr[i]){
					if(newRelOrderNums==""){
						newRelOrderNums = relOrderNumArr[i];
					}else{
						newRelOrderNums = newRelOrderNums+","+relOrderNumArr[i];
					}
				}
			}
			$(obj).parent().remove();
			$("#relOrderNum").val(newRelOrderNums);
		}
























		/*//已进店已签单
		 function inAndSign(returnOrderId,obj,instoreDateTime){
		 if(instoreDateTime!=undefined && instoreDateTime!=""){

		 $("#instoreDatetimeId").val(format(instoreDateTime,'yyyy-MM-dd'))
		 $("#instoreDatetimeId").attr("disabled",true);
		 }


		 var text = $(obj).text();
		 $("#divStatusId").text(text);
		 $("#relatedContractDivId").show();
		 var html='<tr> <td>订单编号</td> <td>签约日期</td>   <td>客户姓名</td> <td>小区名称</td><td>工程地址</td>  <td>操作</td> </tr>';
		 $.ajax({
		 url: "${ctx}/orderreportrelatedcontract/orderReportRelatedContract/findRelatedOrderInfoByReportId?id="+returnOrderId,
		 type:"get",

		 success: function(data){
		 for(var v=0;v<data.length;v++){

		 html+='<tr id="'+data[v].orderId+'Id"><td>'+data[v].orderNumber+'</td><td>'+format(data[v].orderSignContractDate,'yyyy-MM-dd HH:mm:ss')+'</td> <td>'+data[v].orderCustomerName+'</td>  <td>'+data[v].orderCommunityName+'</td>  <td>'+data[v].orderDetailAddress+'</td>' +
		 ' <td class="del" onclick="deleteRelatedOrderInfo('+data[v].id+','+data[v].orderId+')">删除</td> </tr>'


		 }
		 $("#relatedOrderInfoTableId").html(html);



		 }



		 })



		 }*/

        var commonReturnOrderId =0;
        //补签施工合同
        function replenishSignContract(returnOrderId,obj){
            commonReturnOrderId=returnOrderId;
            $("#relatedContractDivId").show();

          var tdObj =  $(obj).parent().parent().find("td:eq(2)");

$("#customerInfoId").text($(tdObj).text()+" - "+$(tdObj).next().text());
        }







        /**
         * 通过返单id(commonReturnOrderId 和orderNumber 去查询是否订单存在,及插入关联订单合同)
         */


   		 function addRelatedOrder(obj){
             $(obj).text("正在添加...");
   		     $(obj).attr("disabled",true);

         		var orderNumberInputVal =  $("#relatedOrderNumberValId").val();
				if(orderNumberInputVal==undefined ||orderNumberInputVal.trim()==""){
    			layer.msg("您没有输入", {icon: 1});
                    $(obj).attr("disabled",false);
                    $(obj).text("添加");
				}else{

				    if($("#"+orderNumberInputVal).text().trim()!=""){

                        layer.msg("您已经关联该合同", {icon: 2});
                        $(obj).attr("disabled",false);
                        $(obj).text("添加");
					}else{
					if($(obj).text()=='正在添加...'){


                        var html='';
                        $.ajax({
                            url: "${ctx}/orderReport/bizOrderReport/replenish-related-order?returnOrderId="+commonReturnOrderId+"&orderNumber="+orderNumberInputVal,
                            type:"get",
                            async:false,

                            success: function(data){

                                if(""!=data&&data!=undefined) {
                                    html+='<tr id="'+data.orderId+'"><td id="'+data.orderNumber+'">'+data.orderNumber+'</td><td>'+format(data.signContractDate,'yyyy-MM-dd')+'</td> <td>'+data.customerName+'</td>  <td>'+data.communityName+'</td>  <td>'+data.detailAddress+'</td>' +
                                        ' <td class="del" onclick="deleteRelatedOrderInfo('+data.orderId+')">删除</td> </tr>'
                                    $("#relatedOrderInfoTableId").append(html);


                                    $(obj).attr("disabled",false);
                                    $(obj).text("添加");
                                }else{
                                    layer.msg("没有找到或已经存在对应的订单,请查正订单编号再次尝试", {icon: 2});
                                    $(obj).attr("disabled",false);
                                    $(obj).text("添加");
                                }


                            }



                        })
                    }


                    }







}
        }




        /**
		 * 删除关联合同函数
		 *
		 *
		 * */
        function deleteRelatedOrderInfo(orderId){

            $("#"+orderId).remove();
            <%--$.ajax({--%>
                <%--url: "${ctx}/orderreportrelatedcontract/orderReportRelatedContract/deleteRelatedInfoByOrderId?id="+reportId+"&orderId="+orderId,--%>
                <%--type:"get",--%>

                <%--success: function(data){--%>
                    <%--if(data==0){--%>

//                        layer.msg("删除关联合同成功", {icon: 1});
//                    }else{
//                        layer.msg("删除关联合同失败", {icon: 2});
//                    }
//
//
//                }
//
//
//
//            })



        }


        /**
		 *
		 * 确认函数
         */

        function confirmRelated(){
            var contractOrderIds = new Array() ;
            var contractOrderNumbers= new Array() ;
   				 var remarks =$("#remarksId").val();
				if(remarks.length>30){
     		  	 layer.msg("备注不能超过30字", {icon: 2});


				}else{

				    if($("#relatedOrderInfoTableId tr").length==1){

                        layer.msg("没有关联的合同", {icon: 2});
                        return;

					}


                    if($("#relatedOrderInfoTableId tr").length>1){

                        $("#relatedOrderInfoTableId tr").each(function(){

                            if($(this).attr("id")!=undefined){
                                contractOrderIds.push($(this).attr("id"))


							}


                            if($(this).find("td:first").attr("id")!=undefined){

                                contractOrderNumbers.push($(this).find("td:first").attr("id"));

							}


						})
                    }
                    var set = new Set(contractOrderIds);
                    contractOrderIds=Array.from(set);
                    var set2 = new Set(contractOrderNumbers);
                    contractOrderNumbers=Array.from(set2);

       			 $.ajax({
          		  url: "${ctx}/orderReport/bizOrderReport/updateReturnOrderReport?id="+commonReturnOrderId+"&signBillRemarks="+remarks+"&contractOrderIds="+contractOrderIds+"&contractOrderNumbers="+contractOrderNumbers,
          		  type:"get",

          		  success: function(data){
                if(data==0){

                    layer.msg("操作成功", {icon: 1});
                    $("#relatedContractDivId").hide();
                    location.reload();
                }else{
                    layer.msg("操作失败", {icon: 2});
                }


            }



        })



    }

}








var commonReturnOrderIdForServiceTransfer=0;
        //转派客服
        function tranferService(returnOrderId){
            commonReturnOrderIdForServiceTransfer=returnOrderId;
			var serviceName = $("#serviceNameId").val();
            $("#serviceDivId").show();
			var html='';
            $.ajax({
                url: "${ctx}/orderReport/bizOrderReport/findServiceList?serviceName="+serviceName,
                type:"get",

                success: function(data){
                    for(var v=0;v<data.length;v++){
                        html+='<tr><td  hidden="hidden"><div ><input type="text" name="serviceEmployeeId" value="'+data[v].serviceEmployeeId+'"/><input type="text" name="serviceMappingId" value="'+data[v].serviceMappingId+'"/></div> </td> <td>'+data[v].serviceName+'</td> <td>'+data[v].servicePhone+'</td>  </tr> '
                    }
                        $("#serviceTableListId").html(html);
                    $(".tab table tr").on("click",function(){

                        $(this).addClass("clickDom").siblings().removeClass("clickDom");
                    })
                    }





            })


        }

        /**
		 * 确认分配
         */
        function confirmTransferService(){
			var returnOrderId = commonReturnOrderIdForServiceTransfer;
            var hiddenIdObj= $("#serviceTableListId").find("tr[class='clickDom']").find("input[name='serviceEmployeeId']")



			var serviceEmpId =  $(hiddenIdObj).val();

            var servicemappingId =  $(hiddenIdObj).next().val();
         	var serviceName = $(hiddenIdObj).parent().parent().next().text()
         	var servicePhone = $(hiddenIdObj).parent().parent().next().next().text()

          if(serviceEmpId==undefined){

              layer.msg("您没有选择客服", {icon: 2});

		  }else{

              var param={
                  id:returnOrderId,
                  serviceEmployeeId:serviceEmpId,
                  serviceName:serviceName,
                  servicePhone:servicePhone,
                  serviceMappingId:servicemappingId


              }


              $.ajax({
                  url: "${ctx}/orderReport/bizOrderReport/saveTransferServiceInfo",
                  type:"post",
                  data: param,
                  success: function(data) {
                      if(data==0){

                          layer.msg("操作成功", {icon: 1});
                          $("#serviceDivId").hide();
                          location.reload();
                      }


                  }



              })

          }



        }









        var format = function(time, format){
            if(time==undefined || time==""){

                return "无"
            }
            var t = new Date(time);
            var tf = function(i){return (i < 10 ? '0' : '') + i};
            return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
                switch(a){
                    case 'yyyy':
                        return tf(t.getFullYear());
                        break;
                    case 'MM':
                        return tf(t.getMonth() + 1);
                        break;
                    case 'mm':
                        return tf(t.getMinutes());
                        break;
                    case 'dd':
                        return tf(t.getDate());
                        break;
                    case 'HH':
                        return tf(t.getHours());
                        break;
                    case 'ss':
                        return tf(t.getSeconds());
                        break;
                }
            })
        }

</script>
	<script type="text/javascript" src="${ctxStatic}/modules/settleJs/layer/layer.js"></script>
	<script src="${ctxStatic}/modules/settleJs/layer/extend/layer.ext.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/orderReport/bizOrderReport/list" style="font-size: 15px;">返单信息上报列表</a></li>
		<shiro:hasPermission name="orderReport:orderReport:edit">
			<li><a href="${ctx}/orderReport/bizOrderReport/form" style="font-size: 15px;">返单信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderReport"
		action="${ctx}/orderReport/bizOrderReport/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label style="width: 120px">门店：</label> <c:if
					test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>

			<li><label style="width: 120px">客户姓名：</label> <form:input
					path="customerName" htmlEscape="false" maxlength="100"
					class="input-medium" /></li>
			<li><label style="width: 120px">客户手机号：</label> <form:input
					path="customerPhone" htmlEscape="false" maxlength="100"
					class="input-medium" /></li>
			<li><label style="width: 120px">客服姓名：</label> <form:input
					path="serviceName" htmlEscape="false" maxlength="100"
					class="input-medium" /></li>
			<li><label style="width: 120px">返单推荐人类型：</label> <form:select
					path="reporterType" class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('reporterType')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label style="width: 120px">返单推荐人：</label> <form:input
					path="reporterName" htmlEscape="false" maxlength="11"
					class="input-medium" /></li>
			<li><label style="width: 120px">返单推荐人手机号：</label> <form:input
					path="reporterPhone" htmlEscape="false" maxlength="11"
					class="input-medium" /></li>
			<li><label style="width: 120px">返单状态：</label> <form:select
					path="reportStatus" class="input-medium needClear">
					<form:option value="" label="" />
				<form:options items="${fns:getDictList('reportStatus')}" itemLabel="label"
							  itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label style="width: 120px">上报日期：</label> <input
				name="start" id="beginContractStartDate" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizOrderReport.start}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endContractStartDate\')}',isShowClear:true});" />
				至 <input name="end" id="endContractStartDate" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizOrderReport.end}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginContractStartDate\')}',isShowClear:true});" />
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
		class="table table-striped table-bordered table-condensed" style="font-size: 15px;">
		<thead>
			<tr>
				<th>门店</th>
				<th>返单上报日期</th>
				<th>客户姓名</th>
				<th>客户手机号</th>
				<th>小区名称</th>
				<th>详细地址</th>
				<th>返单推荐人</th>
				<th>返单推荐人手机号</th>
				<th>返单推荐人类型</th>
				<th>备注</th>
				<th>返单状态</th>
				<th>前台客服</th>
				<shiro:hasPermission name="balancemid:ErqiEntity:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="orderReport">
				<tr>
					<td>${fns:getStoreLabel(orderReport.storeId, '')}</td>
					<td><fmt:formatDate value="${orderReport.reportDatetime}"
							pattern="yyyy-MM-dd" /></td>
					<td>${orderReport.customerName}</td>
					<td>${orderReport.customerPhone}</td>
					<td>${orderReport.communityName}</td>
					<td>${orderReport.address}</td>
					<td>${orderReport.reporterName}</td>
					<td>${orderReport.reporterPhone}</td>
					<td>${fns:getDictLabel(orderReport.reporterType, 'reporterType', '')}
					</td>
					<td>${orderReport.reportRemarks}</td>
					<td>${fns:getDictLabel(orderReport.reportStatus, 'reportStatus', '')}
					<td>${orderReport.serviceName}
					</td>
					<shiro:hasPermission name="orderReport:orderReport:edit">
						<td><c:if test="${orderReport.reportStatus == 10}">
							<a href="#"
							   onclick="tranferService(${orderReport.id})" style="color: #157ab5">转派客服</a>
						</c:if>

							<c:if test="${orderReport.reportStatus == 20}">

							</c:if>
							<c:if test="${orderReport.reportStatus == 25}">
								<a href="#"
								   onclick="openModel('fir',${orderReport.id},'${orderReport.customerName}','${orderReport.customerPhone}')" style="color: #157ab5">已进店未签单</a>
								<a href="#"
								   onclick="openModel('sec',${orderReport.id},'${orderReport.customerName}','${orderReport.customerPhone}','${orderReport.instoreDatetime}')" style="color: #157ab5">已进店已签单</a>
								<a href="#"
								   onclick="tranferService(${orderReport.id})" style="color: #157ab5">转派客服</a>
							</c:if>
							<c:if test="${orderReport.reportStatus ==30}">
								<a href="#"
								   onclick="openModel('sec',${orderReport.id},'${orderReport.customerName}','${orderReport.customerPhone}','${orderReport.instoreDatetime}')" style="color: #157ab5">已进店已签单</a>
								<a href="#"
								   onclick="tranferService(${orderReport.id})" style="color: #157ab5">转派客服</a>
							</c:if>
							<c:if test="${orderReport.reportStatus ==40}">

							</c:if>
							<c:if test="${orderReport.reportStatus == 50}">
								<a href="#"
								   onclick="replenishSignContract('${orderReport.id}',this)" style="color: #157ab5">补签施工合同 </a>
							</c:if>
							<c:if test="${orderReport.reportStatus == 90}">

							</c:if></td>
					</shiro:hasPermission>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- Modal -->
	<div class="modal fade" id="openModel" tabindex="-1" role="dialog"
		style="top: 10px">
		<input type="hidden" id="reportOrderId" />
		<input type="hidden" id="relOrderNum" />
		<div id="fir" class="modal-header"
			style="width: 500px; height: 390px;" hidden="hidden">
			<h5 align="center" style="color: black;">已进店未签单</h5>
			</h3>
			<br>
			<h4 class="modal-title" align="center" style="color: black;"></h4>
			<div
				style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px; text-align: center;"
				class="modal-body">
				<font style="color: black; font-size: 20">进店日期：</font> <input
					type="text" id="instoreDatetime" style="width: 200px" readonly="readonly" maxlength="10"
					class="input-medium Wdate" value=""
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>

			<div
				style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px; text-align: center;"
				class="modal-body">
				<font style="color: black; font-size: 20">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</font>
				<textarea id="instoreRemarks" rows="3" cols="4" style="width: 200px;"></textarea>
			</div>

			<div class="modal-footer"
				style="text-align: center; padding-top: 10px">
				<a href="javascript:void(0)" class="btn btn-primary"
					onclick="onclickFrom(30)">确定</a> <a href="javascript:void(0)"
					class="btn" onclick="onclickClean()">关闭</a>
			</div>
		</div>

		<div id="sec" class="modal-header" style="position:relative;width: 500px; height: 400px;overflow: auto;"
			hidden="hidden">
			<a href="javascript:void(0)" style="position:absolute;top:20px;right:20px;font-size:10px;" onclick="onclickClean()">X</a>
			<h5 align="center" style="color: black;">已进店已签单</h5>
			</h3>
			<br>
			<h4 class="modal-title" align="center" style="color: black;"></h4>
			<div
				style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px; text-align: center;"
				class="modal-body">
				<font style="color: black; font-size: 20">进店日期：</font> <input
					type="text" id="instoreDateTimeId" style="width: 200px" readonly="readonly" maxlength="20"
					class="input-medium Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>
			
			<div
				style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px; text-align: center;"
				class="modal-body">
				<font style="color: black; font-size: 20">签单日期：</font> <input
					type="text" id="signBillDatetime2" style="width: 200px" readonly="readonly" maxlength="10"
					class="input-medium Wdate" value=""
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>

			<div
				style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px; text-align: center;"
				class="modal-body">
				<font style="color: black; font-size: 20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关联的订单：</font>
				<input type="text" id="relOrderNum1" style="width: 200px" /> 
				<a href="javascript:void(0)" class="btn btn-primary" 
				onclick="addRelOrder('orderDiv1','relOrderNum1')" >添加</a>
			</div>

			<div
				style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px; text-align: center;"
				class="modal-body">
				<font style="color: black; font-size: 20">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</font>
				<textarea id="signBillRemarks2" rows="3" cols="4" style="width: 200px;resize:none;"></textarea>
			</div>

			<div id="orderDiv1"
				style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px;"
				class="modal-body">
				
			</div>

			<div class="modal-footer"
				style="text-align: center; padding-top: 10px">
				<a href="javascript:void(0)" class="btn btn-primary"
					onclick="onclickFrom(40)">确定</a> <a href="javascript:void(0)"
					class="btn" onclick="onclickClean()">关闭</a>
			</div>
		</div>

		<div id="ser" class="modal-header" style="position:relative;width: 500px; height: 400px;overflow: auto;"
			hidden="hidden">
			<a href="javascript:void(0)" style="position:absolute;top:20px;right:20px;font-size:15px;" onclick="onclickClean()">X</a>
			<h5 align="center" style="color: black;">已签单</h5>
			</h3>
			<br>
			<h4 class="modal-title" align="center" style="color: black;"></h4>
			<div
				style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px; padding-top: 1px; text-align: center;"
				class="modal-body">
				<font style="color: black; font-size: 20">签单日期：</font> <input
					type="text" id="signBillDatetime3" style="width: 200px" readonly="readonly" maxlength="10"
					class="input-medium Wdate" value=""
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>

			<div
				style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px; padding-top: 1px; text-align: center;"
				class="modal-body">
				<font style="color: black; font-size: 20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关联的订单：</font>
				<input type="text" id="relOrderNum2" style="width: 200px" /> 
				<a href="javascript:void(0)" class="btn btn-primary" 
				onclick="addRelOrder('orderDiv2','relOrderNum2')" >添加</a>
			</div>

			<div
				style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px; padding-top: 1px; text-align: center;"
				class="modal-body">
				<font style="color: black; font-size: 20">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</font>
				<textarea id="signBillRemarks3" rows="3" cols="4" style="width: 200px;"></textarea>
			</div>

			<div id="orderDiv2"
				style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px;"
				class="modal-body">
			</div>

			<div class="modal-footer"
				style="text-align: center; padding-top: 10px">
				<a href="javascript:void(0)" class="btn btn-primary"
					onclick="onclickFrom(50)">确定</a> <a href="javascript:void(0)"
					class="btn" onclick="onclickClean()">关闭</a>
			</div>
		</div>
	</div>
	<div class="pagination">${page}</div>



	<div class="wrap" id="relatedContractDivId" hidden="hidden">

		<div class="Black">
			<div class="center">
				<h2 id="divStatusId">补签施工合同</h2>
				<div class="cen_t">
				<p id="customerInfoId"></p>

                        <div class="cen_tex">
                            <span class="span_l">备注</span>
                            <p class="span_r">
                                <textarea name="remarks" id="remarksId"></textarea>
                            </p>
                        </div>
                        <div class="cen_tex">
                            <span class="span_l">关联订单</span>
                            <p class="span_r">
                                <input type="text" class="cen_order" id="relatedOrderNumberValId">
                                <span class="addorder" onclick="addRelatedOrder(this)">添加</span>
                            </p>
                        </div>
					<div class="tab_bot">
						<table id="relatedOrderInfoTableId">
							<tr> <td>订单编号</td> <td>签约日期</td>   <td>客户姓名</td> <td>小区名称</td><td>工程地址</td>  <td>操作</td> </tr>
						</table>
					</div>
					<div class="cen_btn">
						<span class="btn_y" onclick="confirmRelated()">确定</span>
						<span class="btn_n" >取消</span>
					</div>
				</div>
			</div>


		</div>


		</div>

	<div class="wrap" id="serviceDivId"  hidden="hidden">

		<div class="Black">
			<div class="center">
				<h2>转派客服</h2>
				<div class="cen_t">
					<p>选择客服 <input type="text" class="cen_name" placeholder="请输入客户姓名" id="serviceNameId" onkeyup="tranferService()"></p>
					<div class="tab">
						<table id ="serviceTableListId">

						</table>
					</div>
					<div class="cen_btn">
						<span class="btn_y" onclick="confirmTransferService()">确定</span>
						<span class="btn_n">取消</span>
					</div>
				</div>
			</div>
		</div>
	</div>


		<link rel="stylesheet" href="${ctxStatic}/modules/orderReportRelatedContract/css/base.css">
		<link rel="stylesheet" href="${ctxStatic}/modules/orderReportRelatedContract/css/adv.css">
</body>
</html>