<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>返单信息上报</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
        $("tbody>tr").click(function(){

            $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

        });

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
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


    var format = function(time, format){
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
    function exportExcel(){

        //门店
        var storeId1 = '${bizOrderReport.storeId}';
        var storeId = $("#storeId").val();
        if(storeId1 != storeId){
            alertx("请先点击查询按钮后，再导出订单信息");
            return false;

        }
        //返单上报日期
        var projectMode1 = $("#startDate1").val();


        var projectMode = $("#beginContractStartDate").val();
        if(projectMode1 != projectMode){
            alertx("请先点击查询按钮后，再导出订单信息");
            return false;

        }
        //返单上报日期
        var projectMode2 = $("#endDate1").val();
        var projectModeJsp = $("#endContractStartDate").val();
        if(projectMode2 != projectModeJsp){
            alertx("请先点击查询按钮后，再导出订单信息");
            return false;

        }
        //客户姓名
        var engineDepartId1 = '${bizOrderReport.customerName}';
        var engineDepartId = $("#customerName").val();
        if(engineDepartId1 != engineDepartId){
            alertx("请先点击查询按钮后，再导出订单信息");
            return false;

        }

        //客户手机号
        var customerPhone1 = '${bizOrderReport.customerPhone}';
        var customerPhone = $("#customerPhone").val();
        if(customerPhone != customerPhone1){
            alertx("请先点击查询按钮后，再导出订单信息");
            return false;

        }


        //返单推荐人
        var reporterName = '${bizOrderReport.reporterName}';
        var reporterName1 = $("#reporterName").val();
        if(reporterName != reporterName1){
            alertx("请先点击查询按钮后，再导出订单信息");
            return false;

        }//返单推荐人手机号  开始
        var reporterPhone1 = '${bizOrderReport.reporterPhone}';
        var reporterPhone = $("#reporterPhone").val();
        if(reporterPhone1 != reporterPhone){
            alertx("请先点击查询按钮后，再导出订单信息");
            return false;

        }

        //返单推荐人类型  结束
        var reporterType1 ='${bizOrderReport.reporterType}';
        var reporterType = $("#reporterType").val();
        if(reporterType != reporterType1){
            alertx("请先点击查询按钮后，再导出订单信息");
            return false;

        }



        $("#searchForm").attr("action", "${ctx}/orderReport/bizOrderReport/exportORderReportDetailToExcel");

        $("#searchForm").submit();

        $("#searchForm").attr("action", "${ctx}/orderReport/bizOrderReport/findByParam");

    }
    function formSubmit() {


        $("#searchForm").attr("action", "${ctx}/orderReport/bizOrderReport/findByParam");
        $("#searchForm").submit();
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
			href="${ctx}/orderReport/bizOrderReport/findByParam">返单信息上报列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderReport"
		action="${ctx}/orderReport/bizOrderReport/findByParam" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input id="startDate1" type="hidden" value="<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>">
		<input id="endDate1" type="hidden" value="<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>">


		<ul class="ul-form">
			<li><label style="width: 120px">门店：</label> 
					<form:select path="storeId" class="input-medium needClear">
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

			<li style="width: 100%"><label>返单状态：</label> <input
				name="chkAll" id="chkAll" title="全部"
				onclick="ChkAllClick('reportStatus','chkAll')" type="checkbox" />全部
			</li>
			<li style="height: 80px"><c:forEach
					items="${fns:getDictList('reportStatus')}" var="dict">
					<input type="checkbox" name="reportStatus" id="reportStatus"
						value="${dict.value}"
						onclick="ChkSonClick('reportStatus','chkAll')"
						<c:if test="${fns:checkIsExits(bizOrderReport.reportStatus,dict.value)}"> checked="checked"</c:if> />${dict.label}&nbsp;
				</c:forEach></li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="button" value="查询" onclick="formSubmit()" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearCondition()" /></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出" onclick="exportExcel()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
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
					</td>
					<shiro:hasPermission name="orderReport:orderReport:edit">
						<td><a
							href="${ctx}/orderReport/bizOrderReport/orderReportView?id=${orderReport.id}">详情</a>
						</td>
					</shiro:hasPermission>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="pagination">${page}</div>
</body>
</html>