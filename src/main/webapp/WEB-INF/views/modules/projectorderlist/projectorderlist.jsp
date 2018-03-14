<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程订单列表</title>
	<meta name="decorator" content="default"/>
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
			$("#status option").each(function(index,element){
				var val = $(this).val();
				if(val >= '300'){
					$("#status option[value="+val+"]").remove();
				}
				
			})
			
			
			var checkeds = $("#flag").val();
			  var v = checkeds.substring(1,checkeds.length-1)
			  //去空格
			   var v1 = v.replace(/\s/g,'')
			   //拆分为字符串数组
			   var checkArray =v1.split(",");
			   //获得所有的复选框对象name="storeIds" type="checkbox"
			   var checkBoxAll = $("input[type='checkbox']");
			 
			   //获得所有复选框（新闻,微信,论坛，问答，博客，平媒）的value值，然后，用checkArray中的值和他们比较，如果有，则说明该复选框被选中
			   for(var i=0;i<checkArray.length;i++){
			       //获取所有复选框对象的value属性，然后，用checkArray[i]和他们匹配，如果有，则说明他应被选中
				 $("input[value="+checkArray[i]+"]").attr("checked",true);
		 }
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
		
		function tip(){
			var storeId = $("#storeId").val()
			var projectMode = $("#projectMode").val()
			var orderNumber = $("#orderNumber").val()
			var customerName = $("#customerName").val()
			if(storeId!='' && projectMode != '' || orderNumber !='' ||customerName!=''){
				$("#searchForm").submit();
			}else{
				alertx("必须选择【门店+工程模式】或【订单编号】或【客户姓名】！！")
			}
			
			
		}
		
		function findEngineListByStoreIdAndProjectMode(){
			var html3 = '<option value=""></option>';
		var storeId = $("#sel").val();
		var projectModeValue = $("#projectModeId").val();
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
								html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
							}
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});		
		}
	
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">订单主材完善</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="projectOrderList" action="${ctx}/projectOrderList/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="flag"  type="hidden" value="${projectOrderList.flag}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" id="sel">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" id="projectModeId">
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否有选材：</label>
				<form:select path="isMaterial" class="input-medium needClear" id="engineDepartSelect">
						<form:option value="1" label="是" />
						<form:option value="0" label="否" />
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>订单状态：</label>
				<form:select path="status" class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>客户手机号：</label>
				<form:input path="customerPhone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>设计师：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${projectOrderList.beginCreateDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:true});"/> 至  
				<input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${projectOrderList.endCreateDate }"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:true});"/>
			</li>
			<li><label>是否作废：</label>
				<form:select path="isScrap" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dict_iscountsquare')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label><input  type="checkbox" name="flag" value="1"/></label>
				未上传图纸的订单
			</li>
			<li><label><input type="checkbox" name="flag" value="2"/></label>
				未上传附件的订单
			</li>
			<li><label> <input type="checkbox" name="flag" value="3"/> </label>
				未上传墙地砖的订单
			</li>
			<li><label><input type="checkbox" name="flag" value="4"/></label>
				未设置主材的订单
			</li>
			<li><label><input type="checkbox" name="flag" value="5"/></label>
				墙地砖未变更订单
			</li>
			<li><label><input type="checkbox" name="flag" value="6"/></label>
				安装项未更的订单
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="tip()" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
			<li>
				<span style="font-size:25px;font-weight:bold;text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最新选材变更订单数量：<span style="font-size:25px;font-weight:bold;text-align:center;color:red;">${findChoiceBillCount.counts}</span>
			</li>
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
				<th>客户电话</th>
				<th>订单状态</th>
				<shiro:hasPermission name="bizarea:bizArea:picture">
				<th>图纸</th>
				</shiro:hasPermission>
				<shiro:hasPermission name="bizarea:bizArea:Enclosure">
				<th>附件</th>
				</shiro:hasPermission>
				<shiro:hasPermission name="bizarea:bizArea:wall">
				<th>墙地砖</th>
				</shiro:hasPermission>
				<shiro:hasPermission name="bizarea:bizArea:main">
				<th>主材安装项</th>
				</shiro:hasPermission>
				<th>是否作废</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="projectorderlist">
			<tr>
				<td>
					${fns:getStoreLabel(projectorderlist.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(projectorderlist.projectMode, 'employee_project_mode', '')}
				</td>
				<td>
					${projectorderlist.orderNumber}
				</td>
				<td>
					${projectorderlist.customerName}
				</td>
				<td>
					${projectorderlist.customerPhone}
				</td>
				<td>
					${projectorderlist.status}
				</td>
				<shiro:hasPermission name="bizarea:bizArea:picture">
				<td>
					<a href="${ctx}/ordercad/orderCadfile/list?orderId=${projectorderlist.orderId}&orderNumber=${projectorderlist.orderNumber}&flag=2">查看</a>
				</td>
				</shiro:hasPermission>
				<shiro:hasPermission name="bizarea:bizArea:Enclosure">
				<td>
					<a href="${ctx}/orderexcel/bizOrderExcel/list?orderId=${projectorderlist.orderId}&flag=2">查看</a>
				</td>
					</shiro:hasPermission>
				<shiro:hasPermission name="bizarea:bizArea:wall">
				<td>
					<a href="${ctx }/ordermainmate/bizOrderMainMate/list?orderId=${projectorderlist.orderId}&orderNumber=${projectorderlist.orderNumber}">查看</a>
				</td>
					</shiro:hasPermission>
				<shiro:hasPermission name="bizarea:bizArea:main">
				<td>
					<a href="${ctx }/projectOrderList/materialInstallItem?orderId=${projectorderlist.orderId}&storeId=${projectorderlist.storeId}&projectMode=${projectorderlist.projectMode}&orderNumber=${projectorderlist.orderNumber}&orderStatusNumber=${projectorderlist.orderStatusNumber}&flag=2">查看</a>
				</td>
					</shiro:hasPermission>
				<td>
					<c:if test="${projectorderlist.isScrap eq 1}">是</c:if>
					<c:if test="${projectorderlist.isScrap ne 1}">否</c:if>
				</td>
				<td>
					<a href="${ctx }/order/order/details?id=${projectorderlist.orderId}&flag=2">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>