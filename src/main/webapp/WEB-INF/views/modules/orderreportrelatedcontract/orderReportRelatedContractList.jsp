<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>返单信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

            $(".btn_n").click(function(){
                $("#relatedContractDivId").hide();



			})
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }


        var reportId =0;
		var commonObj ;
        //查询关联合同
        function relatedContract(returnOrderId,obj){
            reportId =returnOrderId;
            commonObj=obj;
            var text = $(obj).parent().prev().prev().text();
            $("#divStatusId").text(text);
            $("#relatedContractDivId").show();
	var html='<tr> <td>订单编号</td> <td>签约日期</td>   <td>客户姓名</td> <td>小区名称</td><td>工程地址</td>  <td>操作</td> </tr>';
            $.ajax({
				url: "${ctx}/orderreportrelatedcontract/orderReportRelatedContract/findRelatedOrderInfoByReportId?id="+returnOrderId,
				type:"get",

				success: function(data){
				    for(var v=0;v<data.length;v++){

                      html+='<tr id="'+data[v].orderId+'"><td>'+data[v].orderNumber+'</td><td>'+format(data[v].orderSignContractDate,'yyyy-MM-dd HH:mm:ss')+'</td> <td>'+data[v].orderCustomerName+'</td>  <td>'+data[v].orderCommunityName+'</td>  <td>'+data[v].orderDetailAddress+'</td>' +
							' <td class="del" onclick="deleteRelatedOrderInfo('+data[v].id+','+data[v].orderId+',this)">删除</td> </tr>'


                    }
                    $("#relatedOrderInfoTableId").html(html);



				}



			})




           //

		}

		//删除关联合同信息
		function deleteRelatedOrderInfo(reportId,orderId,obj){
		$(obj).parent().remove();



           /* $.ajax({
                url: "${ctx}/orderreportrelatedcontract/orderReportRelatedContract/deleteRelatedInfoByOrderId?id="+reportId+"&orderId="+orderId,
                type:"get",

                success: function(data){
                   if(data==0){
                       $("#"+orderId+"Id").parent().remove();
                       layer.msg("删除关联合同成功", {icon: 1});
                   }else{
                       layer.msg("删除关联合同失败", {icon: 2});
                   }


                }



            })
*/


        }

		//确认关联合同信息
      function   updateOrderReportRelatedContract() {
          var orderIds = [];
			var orderNums =[];

            $("#relatedOrderInfoTableId  tr").each(function () {


                      if(($(this).attr("id"))!=undefined){

                          orderIds.push($(this).attr("id"));
                          orderNums.push($(this).find("td:first").text());
					  }


                  })

              $.ajax({
                  url: "${ctx}/orderreportrelatedcontract/orderReportRelatedContract/updateOrderReportStatusById?id="+reportId+"&orderIds="+orderIds+"&orderNums="+orderNums,
                  type:"get",

                  success: function(data){
                      if(data==0){
                          $("#relatedContractDivId").hide();
                         $(commonObj).parent().parent().remove();
                          layer.msg("关联合同成功", {icon: 1});

                      }else{
                          layer.msg("关联关联合同失败", {icon: 2});
                      }


                  }



              })



      }


/*
   */





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
	</script>
	<script type="text/javascript" src="${ctxStatic}/modules/settleJs/layer/layer.js"></script>
	<script src="${ctxStatic}/modules/settleJs/layer/extend/layer.ext.js"></script>
</head>

<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/orderreportrelatedcontract/orderReportRelatedContract/list" style="font-size: 15px;">返单信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderReportRelatedContract" action="${ctx}/orderreportrelatedcontract/orderReportRelatedContract/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width: 120px">门店：</label>

				<form:select path="storeId" class="input-medium needClear" >

					<form:options items="${fns:getStoreList()}" itemLabel="label"
								  itemValue="value" htmlEscape="false" />
				</form:select>
			</li>

			<li><label style="width: 120px">客户姓名：</label> <form:input
					path="customerName" htmlEscape="false" maxlength="100"
					class="input-medium" /></li>
			<li><label style="width: 120px">客户手机号：</label> <form:input
					path="customerPhone" htmlEscape="false" maxlength="100"
					class="input-medium" /></li>
			<li><label style="width: 120px">客服姓名：</label> <form:input
					path="serviceName" htmlEscape="false" maxlength="100"
					class="input-medium" /></li>
			<li><label style="width: 120px">返单推荐人：</label> <form:input
					path="reporterName" htmlEscape="false" maxlength="11"
					class="input-medium" /></li>
			<li><label style="width: 120px">返单推荐人手机号：</label> <form:input
					path="reporterPhone" htmlEscape="false" maxlength="11"
					class="input-medium" /></li>
			<li><label style="width: 120px">返单推荐人类型：</label> <form:select
					path="reporterType" class="input-medium needClear">
				<form:option value="" label="" />
				<form:options items="${fns:getDictList('reporterType')}"
							  itemLabel="label" itemValue="value" htmlEscape="false" />
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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
									type="button" value="清空" onclick="clearCondition()" /></li>
			<li class="clearfix"></li>

		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed"  style="font-size: 15px;">
		<thead>
			<tr>
				<th>门店</th>
				<th>上报日期</th>
				<th>返单推荐人</th>
				<th>返单推荐人手机号</th>
				<th>返单推荐人类型</th>
				<th>客户姓名</th>
				<th>客户手机号</th>
				<th>小区名称</th>
				<th>详细地址</th>
				<th>返单状态</th>
				<th>接单客服</th>

				<shiro:hasPermission name="orderreportrelatedcontract:orderReportRelatedContract:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderReportRelatedContract">
			<tr id="${orderReportRelatedContract.id}TrId">
				<td>${fns:getStoreLabel(orderReportRelatedContract.storeId, '')}</td>
				<td><fmt:formatDate value="${orderReportRelatedContract.reportDatetime}"
									pattern="yyyy-MM-dd" /></td>
				<td>${orderReportRelatedContract.reporterName}</td>
				<td>${orderReportRelatedContract.reporterPhone}</td>
				<td>${fns:getDictLabel(orderReportRelatedContract.reporterType, 'reporterType', '')}
				</td>
				<td>${orderReportRelatedContract.customerName}</td>
				<td>${orderReportRelatedContract.customerPhone}</td>
				<td>${orderReportRelatedContract.communityName}</td>
				<td>${orderReportRelatedContract.address}</td>

				<td>${fns:getDictLabel(orderReportRelatedContract.reportStatus, 'reportStatus', '')}
				<td>${orderReportRelatedContract.serviceName}</td>
				</td>
				<shiro:hasPermission name="orderreportrelatedcontract:orderReportRelatedContract:edit"><td>
    				<a href="#" onclick="relatedContract('${orderReportRelatedContract.id}',this,'${orderReportRelatedContract.customerPhone}')" style="color: #157ab5">关联合同信息</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>


	<div class="wrap" id="relatedContractDivId" hidden="hidden">

		<div class="Black">
			<div class="center">
				<h2 id="divStatusId">已进店已签单</h2>
				<div class="cen_t">
				<%--	<p>张三 15811384514</p>
					<div class="cen_tex">
						<span class="span_l">进店时间</span>
						<p class="span_r">
							<input type="text" name="instoreDatetime" readonly="readonly" maxlength="20" class="input-medium Wdate required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
							<i></i>
						</p>
					</div>
					<div class="cen_tex">
						<span class="span_l">签单时间</span>
						<p class="span_r"><input name="signBillDatetime"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" /><i></i></p>
					</div>
					<div class="cen_tex">
						<span class="span_l">备注</span>
						<p class="span_r">
							<textarea></textarea>
						</p>
					</div>
					<div class="cen_tex">
						<span class="span_l">关联订单</span>
						<p class="span_r">
							<input type="text" class="cen_order">
							<span class="addorder">添加</span>
						</p>
					</div>--%>
					<div class="tab_bot tab_big" style="height: 250px;">
						<table id="relatedOrderInfoTableId">

						</table>
					</div>
					<div class="cen_btn">
						<span class="btn_y" onclick="updateOrderReportRelatedContract()">确定</span>
						<span class="btn_n" >取消</span>
					</div>
				</div>
			</div>
		</div>
		<link rel="stylesheet" href="${ctxStatic}/modules/orderReportRelatedContract/css/base.css">
		<link rel="stylesheet" href="${ctxStatic}/modules/orderReportRelatedContract/css/adv.css">
</body>

</html>