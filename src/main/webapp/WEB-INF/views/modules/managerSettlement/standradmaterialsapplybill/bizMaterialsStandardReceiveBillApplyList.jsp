<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标化申请记录管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.mask-text{resize:none;width:280px;padding:5px;box-sizing:border-box;}
	</style>
	<script type="text/javascript">

	
		$(document).ready(function() {
			$("tbody>tr").click(function(){

			    $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

			});
		});
		
	
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
		
		function timeClick(id,type){
            if(type==1){
                $("#myModalLabel2").html("你确定配送？");
                $("#sdate").html("配送日期：");
            }else{
                $("#myModalLabel2").html("你确定领取？");
                $("#sdate").html("领取日期：");
            }
			$('#myModal').modal('show');
			$("#myModalHiddenId").val(id);
			
		};
		function onclickFrom(){
			var v = $("#myModalHiddenId").val();
			var date = $("#applyDatetimeWindow").val();
			window.location.href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from/"+v+"/20/"+date;
		};
		function onclickClean(){
			$('#myModal').modal('hide');
			
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		//废弃 触发的事件
		function abandoned(id){
			$("#myAbandonedModal").modal('show');
			$("#myId").val(id);
		}
		
		//废弃 原因提交的事件
		function onclickAbandoned(){
			var v = $("#myId").val();
			var date = $("#reson").val();
			//有没有输入原因
			if(date == ""){
				$("#myNoAbandonedModal").modal('show');
			}else{
					window.location.href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from/"+v+"/30/"+date;
					}
			$("#reson").val('');
			$('#myAbandonedModal').modal('hide');		
			}
		//废弃 取消 事件
		function onclickNoAbandoned(){
			$('#myAbandonedModal').modal('hide');
			$("#reson").val('');
		}
		function onclickNoAbandoned1(){
			$('#myNoAbandonedModal').modal('hide');
			
		}
	</script> 
</head>
<body>                                    
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/list">标化辅材申请记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/findlist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select  path="name" class="input-medium needClear">
					<form:options id = "name" items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单号：</label>
				<form:input id="orderNumber" path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input id="customerName" path="customerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>订单是否作废：</label>
			<form:select path="isScrap"   class="input-medium needClear" >
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('dict_iscountsquare')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>	
			</li>			
			<li>
			<%--<label>状态： </label>
				<form:input path="receiveBillStrtus" htmlEscape="false" maxlength="64" class="input-medium"/> --%>
				
				<label>状态：</label>
					<form:select path="status" class="input-medium needClear">
						<form:options items="${fns:getDictList('application_material_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				
			</li>
			<li><label>申请人：</label>
				<form:input id = "realName" path="realName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>申请日期：</label>
				<input  id = "fristApplyDatetime" readonly="readonly" name="fristApplyDatetime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizMaterialsStandardReceiveBillApply.fristApplyDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input id = "endApplyDatetime" name="endApplyDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizMaterialsStandardReceiveBillApply.endApplyDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>领取/配送日期：</label>
				<input id="fristReceiveDatetime" name="fristReceiveDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizMaterialsStandardReceiveBillApply.fristReceiveDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input id="endReceiveDatetime" name="endReceiveDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizMaterialsStandardReceiveBillApply.endReceiveDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>配送方式：</label>
				<form:select path="shippingType" class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_materials_shipping_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>申请日期 </th>
				<th>申请单号</th>
				<th>申请人</th>
				<th>状态</th>
				<th>配送方式</th>
				<th>领取/配送日期</th>
				<th>订单号 </th>
				<th>客户姓名</th>
				<th>订单是否作废</th>						
				<th>金额</th>
				<shiro:hasPermission name="standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialsStandardReceiveBill">
			<tr>
				<td>
					${bizMaterialsStandardReceiveBill.name }
					<%-- ${fns:getStoreLabel(bizMaterialsStandardReceiveBill.name, '')} --%>
				</td>
				<td>
					<fmt:formatDate value="${bizMaterialsStandardReceiveBill.applyDatetime }" pattern="yyyy-MM-dd hh:mm:ss"/>
					 <%-- ${bizMaterialsStandardReceiveBill.receiveDatetime } --%>
				</td>
				<td>
					${bizMaterialsStandardReceiveBill.materialsStandardReceiveBillCode }
				</td>
				<td>
					${bizMaterialsStandardReceiveBill.realName }
				</td>
				<td>
					<c:if test="${bizMaterialsStandardReceiveBill.status =='10' }">
						项目经理已申请
					</c:if>
					<c:if test="${bizMaterialsStandardReceiveBill.status =='20' }">
						已${bizMaterialsStandardReceiveBill.shippingType==1?'配送':'领取'}
					</c:if>
					<c:if test="${bizMaterialsStandardReceiveBill.status =='30' }">
						已废弃
					</c:if>
				</td>
				<td>
						${fns:getDictLabel(bizMaterialsStandardReceiveBill.shippingType, 'biz_materials_shipping_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${bizMaterialsStandardReceiveBill.receiveDatetime }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizMaterialsStandardReceiveBill.orderNumber }
				</td>
				<td>
					${bizMaterialsStandardReceiveBill.customerName }
				</td>
				<td>
					${fns:getDictLabel(bizMaterialsStandardReceiveBill.isScrap, 'dict_iscountsquare', '')}
				</td>
				<td>
					${bizMaterialsStandardReceiveBill.receiveBillAmount }
				</td>
				<shiro:hasPermission name="standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit"><td>
					<c:if test="${bizMaterialsStandardReceiveBill.status =='10'}">
					<!--data-toggle="modal" data-target="#myModal"  -->
				<a id = "confim" href="javascript:void(0)" onclick="timeClick('${bizMaterialsStandardReceiveBill.id}',${bizMaterialsStandardReceiveBill.shippingType})">${bizMaterialsStandardReceiveBill.shippingType==1?'配送':'领取'}</a>
				
	
				<!-- Modal -->
				<div  class="modal fade" id="myModal" tabindex="-1" role="dialog" style="width:350px">
				<input type="hidden" id="myModalHiddenId">
				<div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
				<h4 id="myModalLabel2" align="center" style="color: black;"></h4><br>
				<form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
					<input id = "ids"name="id" value="${bizMaterialsStandardReceiveBill.id}" type="hidden">
					<input id = "status" name="status" value="20" type="hidden">
				<div  style="margin:10px;min-height:20px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
					<font style="color: black;" id="sdate"></font><input id ="applyDatetimeWindow" name="receiveDatetime" type="text" readonly="readonly" maxlength="10" class="input-medium Wdate"
					value="<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					<div class="modal-footer" style="text-align: center;">
				<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickFrom()">确定</a>
				<a href="javascript:void(0)" class="btn" onclick="onclickClean()">关闭</a>
				</div>
				</div>
				
				</form:form>
				</div>
				</div>
						<a href="javascript:void(0)" onclick="abandoned('${bizMaterialsStandardReceiveBill.id}')" >废弃</a>
						
							<!-- 废弃btn弹框的model -->
							<div  class="modal fade" id="myAbandonedModal" tabindex="-1" role="dialog" style="width:350px">
								<input  id="myId" type="hidden">
								<div class="modal-header">
									<button class="close" type="button" data-dismiss="modal">×</button>
									<h4 id="myModalLabel" align="center" style="color: black;">你确定废弃吗？</h3><br>
									<form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
										<input id = "ids"name="id" value="${bizMaterialsStandardReceiveBill.id}" type="hidden">
										<input id = "status" name="status" value="20" type="hidden">
									<div  style="margin:10px;min-height:20px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
										<textarea id="reson" class="mask-text" placeholder="请输入废弃原因，多行输入，不多于30个汉字" maxlength="30" ></textarea>
										<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandoned()" >确定</a>
										<a href="javascript:void(0)" class="btn" onclick="onclickNoAbandoned()">关闭</a>
									</div>
									</div>
									</form:form>
							</div>
							<!-- 未输入废弃原因的model -->
							<div  class="modal fade" id="myNoAbandonedModal" tabindex="-1" role="dialog" style="width:350px">
								<div class="modal-header">
									<button class="close" type="button" data-dismiss="modal">×</button>
									<h4 id="myModalLabel" align="center" style="color: black;">提示</h3><br>
									<div  style="margin:10px;min-height:20px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
										<p style="font-size:14px;">未输入废弃原因</p>
										<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickNoAbandoned1()">确定</a>
									</div>
									</div>
							</div>
						</div>
						<a href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/detail/${bizMaterialsStandardReceiveBill.id}/2">详情</a>
					</c:if>
					<c:if test="${bizMaterialsStandardReceiveBill.status =='20' }">
						<a href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/detail/${bizMaterialsStandardReceiveBill.id}/1">详情</a>
					</c:if>
					<c:if test="${bizMaterialsStandardReceiveBill.status =='30' }">
						<a href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/detail/${bizMaterialsStandardReceiveBill.id}/1">详情</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>
	</div>
	
		
	</div>
	
	
</body>
</html>