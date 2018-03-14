<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材安装验收不合格查询</title>
	<meta name="decorator" content="default"/>
    <script src="${ctxStatic}/vendor/jquery/swiper.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			findInstallName();
			hiddenDate();
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		//清空查询条件
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
		

		 
		
		function findInstallName(){
			var html2='<option value=""></option>';
			var html3='<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeId = $("#projectMode").val();
			
			if (storeId=="" ||projectModeId=="" ||undefined==storeId ||undefined==projectModeId) {
				return;
			}
			$.ajax({
				url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,					
				success: function(data){

					if(null!=data&&data.length>0){

						for (var v = 0; v < data.length; v++) {
							
							if(data[v].isOn == 1){
								//启用
								if('${bizOrderInstallPlan.orderInstallItemId}' == data[v].id){
									$("#s2id_orderInstallItemId .select2-chosen").html(data[v].installItemName);
									html2 = html2 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
								}else{
									html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								}
							}else{
								//停用
								if('${bizOrderInstallPlan.orderInstallItemIdStop}' == data[v].id){
									$("#s2id_orderInstallItemIdStop .select2-chosen").html(data[v].installItemName);
									html3 = html3 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								}
								
							}
						}

						$("#orderInstallItemId").html(html2);
						$("#orderInstallItemIdStop").html(html3);
					} else {
						$("#orderInstallItemId").html(html2);
						$("#orderInstallItemIdStop").html(html3);
					}
				}
			})

            var html4 = '<option value=""></option>';
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
                            if('${bizOrderInstallPlan.enginDepartId}' == data[v].engineDepartId){
                                $("#s2id_enginDepartId .select2-chosen").html(data[v].engineDepartName);
                                html4 = html4 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
                            }else{
                                html4 = html4 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
                            }
                        }
                        $("#enginDepartId").html(html4);
                    } else {
                        $("#enginDepartId").html(html4);
                    }

                }

            });

		}


	</script>
    <script>
        function btnSubmitExport() {
            var storeId = '${bizOrderInstallPlan.storeId}';
            var storeId1 = $("#storeId").val();
            if(storeId != storeId1){
                alertx("请先查询在导出！")
                return false;
            }
            var projectMode = '${bizOrderInstallPlan.projectMode}';
            var projectMode1 = $("#projectMode").val();
            if(projectMode != projectMode1){
                alertx("请先查询在导出！")
                return false;
            }
            var orderNumber = '${bizOrderInstallPlan.orderNumber}';
            var orderNumber1 = $("#orderNumber").val();
            if(orderNumber != orderNumber1){
                alertx("请先查询在导出！")
                return false;
            }

            var customerName = '${bizOrderInstallPlan.customerName}';
            var customerName1 = $("#customerName").val();
            if(customerName != customerName1){
                alertx("请先查询在导出！")
                return false;
            }

            var itemManager = '${bizOrderInstallPlan.itemManager}';
            var itemManager1 = $("#itemManager").val();
            if(itemManager != itemManager1){
                alertx("请先查询在导出！")
                return false;
            }

            var orderInstallItemId = '${bizOrderInstallPlan.orderInstallItemId}';
            var orderInstallItemId1 = $("#orderInstallItemId").val();
            if(orderInstallItemId1 == null){
                orderInstallItemId1 = '';
            }
            if(orderInstallItemId != orderInstallItemId1){
                alertx("请先查询在导出！")
                return false;
            }

            var orderInstallItemIdStop = '${bizOrderInstallPlan.orderInstallItemIdStop}';
            var orderInstallItemIdStop1 = $("#orderInstallItemIdStop").val();
            if(orderInstallItemIdStop1 == null){
                orderInstallItemIdStop1 = '';
            }
            if(orderInstallItemIdStop != orderInstallItemIdStop1){
                alertx("请先查询在导出！")
                return false;
            }


            var unqualifiedReasonConfigure = '${bizOrderInstallPlan.unqualifiedReasonConfigure}';
            var unqualifiedReasonConfigure1 = $("#unqualifiedReasonConfigure").val();
            if(unqualifiedReasonConfigure != unqualifiedReasonConfigure1){
                alertx("请先查询在导出！")
                return false;
            }

            var beginUnqualifiedAcceptTime = '${bizOrderInstallPlan.beginUnqualifiedAcceptTimeString}';
            var beginUnqualifiedAcceptTime1 = $("#beginUnqualifiedAcceptTime").val();
            if(beginUnqualifiedAcceptTime != beginUnqualifiedAcceptTime1){
                alertx("请先查询在导出！")
                return false;
            }

            var endUnqualifiedAcceptTime = '${bizOrderInstallPlan.endUnqualifiedAcceptTimeString}';
            var endUnqualifiedAcceptTime1 = $("#endUnqualifiedAcceptTime").val();
            if(endUnqualifiedAcceptTime != endUnqualifiedAcceptTime1){
                alertx("请先查询在导出！")
                return false;
            }

            var enginDepartId = '${bizOrderInstallPlan.enginDepartId}';
            var enginDepartId1 = $("#enginDepartId").val();
            if(enginDepartId != enginDepartId1){
                alertx("请先查询在导出！")
                return false;
            }

            var status = '${bizOrderInstallPlan.status}';
            var status1 =$('input:radio:checked').val();
            if(status != status1){
                alertx("请先查询在导出！")
                return false;
            }
            window.location.href="${ctx}/bizorderinstallplan/orderinstallacceptquery/exportOrderInstallAccept?storeId="+storeId1+"&projectMode="+projectMode1+"&orderNumber="+orderNumber1+"&customerName="+customerName1+"&itemManager="+itemManager1+"&orderInstallItemId="+orderInstallItemId1+"&orderInstallItemIdStop="+orderInstallItemIdStop1+"&unqualifiedReasonConfigure="+unqualifiedReasonConfigure1+"&beginUnqualifiedAcceptTime="+beginUnqualifiedAcceptTime1+"&endUnqualifiedAcceptTime="+endUnqualifiedAcceptTime1+"&status="+status1+"&enginDepartId="+enginDepartId1;
        }
    </script>

</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizorderinstallplan/orderinstallacceptquery/findOrderInstallAccept">主材安装订单不合格查询</a></li>
	
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderInstallPlan" action="${ctx}/bizorderinstallplan/orderinstallacceptquery/findOrderInstallAccept" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear"  onchange="findInstallName()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findInstallName()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findInstallName()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
            <li><label>区域：</label>
                <form:select path="enginDepartId" class="input-medium needClear">
                    <form:option value=""></form:option>
                </form:select>
            </li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
            <li><label>客户姓名：</label>
                <form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
            </li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
            <li><label>安装项名称：</label>
                <form:select path="orderInstallItemId" class="input-medium needClear">
                </form:select>
            </li>
			<li><label style="width:140px;">安装项名称（停用）：</label>
				<form:select path="orderInstallItemIdStop" class="input-medium needClear">
				</form:select>
			</li>

            <li><label style="width:140px;">验收不合格原因：</label>
				<form:input path="unqualifiedReasonConfigure" htmlEscape="false" maxlength="50" class="input-medium needClear"/>
            </li>

            <li><label style="width:140px;">安装项状态：</label>
                <form:radiobutton path="status" value = "4" checked = "checked" onchange="hiddenDate()"></form:radiobutton>
                验收合格
                <form:radiobutton path="status" value = "401" onchange="hiddenDate()"></form:radiobutton>
                验收不合格
            </li>


			<li><label style="width: 120px;" id ="lableDate">验收合格时间：</label>
				<input name="beginUnqualifiedAcceptTime" type="datetime" id="beginUnqualifiedAcceptTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallPlan.beginUnqualifiedAcceptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endUnqualifiedAcceptTime\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="endUnqualifiedAcceptTime" type="datetime" id="endUnqualifiedAcceptTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallPlan.endUnqualifiedAcceptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginUnqualifiedAcceptTime\')}',isShowClear:false});"/>
			</li>



			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
            <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="导出Excel" onclick="btnSubmitExport()"/></li>
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
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理（提交人）</th>
				<th>安装项名称</th>
                <th>安装项状态</th>
                <th>安装项不合格次数</th>
                <th>一次合格率</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>	
			<c:forEach items="${page.list}" var="install" >
                <c:if test="${install.storeId != null}">
                    <tr>
                        <td>
                                ${fns:getStoreLabel(install.storeId,"")}
                        </td>
                        <td>
                                ${fns:getDictLabel(install.projectMode, 'project_mode', '')}
                        </td>
                        <td>
                                ${fns:getElacLabel(install.enginDepartId,"")}
                        </td>
                        <td>${install.orderNumber}</td>
                        <td>
                                ${install.customerName }
                        </td>
                        <td>${install.customerAddressDetaill }</td>
                        <td>${install.itemManager}</td>
                        <td>${install.installItemName }</td>
                        <td>
                                ${install.status }
                        </td>
                        <td>${install.unqualifiedTimes }</td>
                        <td>${install.firstPassRate }</td>
                        <td><a href="${ctx}/bizorderinstallplan/orderinstallacceptquery/detail?orderId=${install.orderId }&id=${install.id }&beginUnqualifiedAcceptTime=${bizOrderInstallPlan.beginUnqualifiedAcceptTimeString}&endUnqualifiedAcceptTime=${bizOrderInstallPlan.endUnqualifiedAcceptTimeString}">详情</a></td>

                    </tr>

                </c:if>

			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
    <script>
        function hiddenDate() {
            var s  = $('input:radio:checked').val();
            if(s == 4){
                $("#lableDate").text("验收合格时间：");
            }else {
                $("#lableDate").text("验收不合格时间：");
            }
        }
    </script>
</body>
</html>