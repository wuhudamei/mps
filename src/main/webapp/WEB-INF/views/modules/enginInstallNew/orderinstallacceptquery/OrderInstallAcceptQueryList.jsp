<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材安装验收不合格明细查询</title>
	<meta name="decorator" content="default"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			findInstallName();
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
									$("#s2id_projectInstallItemId .select2-chosen").html(data[v].installItemName);
									html2 = html2 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
								}else{
									html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								}
							}else{
								//停用
								if('${bizOrderInstallPlan.orderInstallItemIdStop}' == data[v].id){
									$("#s2id_projectInstallItemIdStop .select2-chosen").html(data[v].installItemName);
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

            window.location.href="${ctx}/bizorderinstallplan/orderinstallacceptquery/exportList?storeId="+storeId1+"&projectMode="+projectMode1+"&orderNumber="+orderNumber1+"&customerName="+customerName1+"&itemManager="+itemManager1+"&orderInstallItemId="+orderInstallItemId1+"&orderInstallItemIdStop="+orderInstallItemIdStop1+"&unqualifiedReasonConfigure="+unqualifiedReasonConfigure1+"&beginUnqualifiedAcceptTime="+beginUnqualifiedAcceptTime1+"&endUnqualifiedAcceptTime="+endUnqualifiedAcceptTime1;
        }
    </script>
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizorderinstallplan/orderinstallacceptquery/list">主材安装验收不合格明细查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderInstallPlan" action="${ctx}/bizorderinstallplan/orderinstallacceptquery/list" class="breadcrumb form-search">
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
					<form:select path="projectMode" class="input-medium needClear"  onchange="findInstallName()">
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

			<li><label style="width: 120px;">提交时间：</label>
				<input name="beginUnqualifiedAcceptTime" type="datetime" id="beginUnqualifiedAcceptTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallPlan.beginUnqualifiedAcceptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endUnqualifiedAcceptTime\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="endUnqualifiedAcceptTime" type="datetime" id="endUnqualifiedAcceptTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallPlan.endUnqualifiedAcceptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginUnqualifiedAcceptTime\')}',isShowClear:false});"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
            <li class="btns"><input class="btn btn-primary" type="button" value="导出Excel" onclick="btnSubmitExport()"/></li>
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
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>安装项名称</th>
				<th>提交时间</th>
				<th>订单不合格原因</th>
				<th>备注</th>
				<th>图片</th>
			</tr>
		</thead>
		<tbody>	
			<c:forEach items="${page.list}" var="install" >
			<tr>
				<td>
                        ${fns:getStoreLabel(install.storeId,"")}
                </td>
				<td>
                        ${fns:getDictLabel(install.projectMode, 'project_mode', '')}
                </td>
				<td>
                        ${install.orderNumber}
                </td>
				<td>
                    ${install.customerName }
                </td>
				<td>${install.customerAddressDetaill }</td>
				<td>${install.itemManager}</td>
				<td>${install.installItemName }</td>
				<td><fmt:formatDate value="${install.createDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td>${install.unqualifiedReasonConfigure}</td>
				<td>${install.acceptRemarks }</td>
                <td><a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs('${install.id}')">图片</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>


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

    function ajaxGetImgs(id){
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '${ctx}/bizorderinstallplan/orderinstallacceptquery/photo',
            data: {
                id:id,
                acceptType:"2"
            },
            success: function (data) {

                var ainput='';
                if (null !=data && data.length != 0) {
                    for (var i = 0; i < data.length; i++) {
                        ainput +=	 '<a  class="swiper-slide " ><img src="' + data[i] + '" ></a>';
                    }
                }else{
                    ainput ='<a  class="swiper-slide " ><img src="${ctxStatic}/images/nopic.jpg" ></a>';
                }
                $("#inputId").html(ainput);
            }
        })

    }
</script>

</body>
</html>