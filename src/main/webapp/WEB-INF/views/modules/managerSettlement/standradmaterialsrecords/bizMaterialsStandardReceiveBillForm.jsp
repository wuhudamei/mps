<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标化辅材记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//getEmpByStoreid();
			//$("#name").focus();
			$("#inputForm").validate({
					debug : false,
					rules : {
						receiveDatetime:'required',
						receiveEmployeeId:'required',
						orderId:'required',
					},
					messages : {
						receiveDatetime:'请输入领取日期！',
						receiveEmployeeId:'请选择领取人！',
						orderId:'请选择领取订单！',
					},
				submitHandler: function(form){
					var val=leaderValid();
					if(!val){    					
						return false;
					}
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function getEmpByStoreid()
		{
			$("#receiveEmployeeId").html('');
			$("#materialId").html('');
			 $.ajax({
			        type : 'POST',
			        dataType : 'json',
			        url : '${ctx}/employee/bizEmployee/manager_list_byStoreId',
			        data : {
			            'storeId':$("#storeId").val(),
			        },
			        success : function(data) {
			            var html = "";
			           	var html = "<option value=''></option>";
			            //var html1 = "<option value=''></option>";
			            var employees = data[0];
			            var materials = data[1];
			            for (var i = 0; i < employees.length; i++) {
			            	var sec = "";
			            	if($("#receiveEmployeeId").val() == employees[i].value){
			            		sec = "selected='selected'";
			            	}
			            	html += "<option value='" + employees[i].value +"' " + sec + ">" + employees[i].label + "</option>"
			            }
			            html+="";
			            /* for (var j = 0; j < materials.length; j++) {
			            	var sec = "";
			            	if($("#materialId").val() == materials[j].value){
			            		sec = "selected='selected'";
			            	}
			            	html1 += "<option value='" + materials[j].value +"' " + sec + ">" + materials[j].label + "</option>"
			            }
			            html1+=""; */
			            $("#receiveEmployeeId").append(html);
			            //$("#materialId").append(html1);
			            
			            for(var j = 0; j < materials.length; j++){
			            	addMaterialsRow(materials[j]);
			            }
			        }
			    });
		}
		
		function getOrderByEmpId(){
			
			$("#orderId").html('');
			 $.ajax({
			        type : 'POST',
			        dataType : 'json',
			        url : '${ctx}/order2/order2/order_list_byEmpId',
			        data : {
			            'empId':$("#receiveEmployeeId").val(),
			        },
			        success : function(data) {
			            var html = "<option value=''></option>";
			           // alert(data.length);
			            for (var i = 0; i < data.length; i++) {
			            	var sec = "";
			            	if($("#orderId").val() == data[i].value){
			            		sec = "selected='selected'";
			            	}
			            	html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
			            }
			            html+="";
			            $("#orderId").append(html);
			        }
			    });
		}
		
		var temp;
		/* function addMaterial(){
			if($("#materialId").val() == null || $("#materialId").val() == ''){
				alertx("请先选择标化辅材！");
				return;
			}
			var elements = document.getElementsByName("ids");
			//alert($("#materialId").val());
			$.ajax({
				 type : 'POST',
			     dataType : 'json',
			     url : '${ctx}/standradmaterials/bizMaterialsStandard/getBizMaterialsStandard',
			     data : {
			        'materialId':$("#materialId").val(),
			     },
			     success : function(data) {
			    	 if(elements.length>0){
			    		 for(var i=0;i< elements.length;i++){
			    			 if(data.id == elements[i].value){
			    				 //alert(elements[i].value);
			    				 alertx("已经添加该辅材！");
			    				 return;
			    			 }
			    		 }
			    	}
			    	 addMaterialsRow(data)
			    	 
			     }
			});
		} */
		
		function addMaterialsRow(data){
			var html = "<tr><td>"+data.materialsType+"<input type='hidden' value=\""+data.id+"\" name='ids'></td><td>"+data.materialsName+"</td><td>"+data.materialsPrice+"</td><td><input type='text' value='"+data.receiveNumber+"' name='amounts'></td>"
			/* var html = "<tr><td>"+data.materialsType+"<input type='text' value="+data.id+" name='ids'></td><td>"+data.materialsName+"</td><td>"+data.materialsPrice+"</td><td><input type='text' value='"+data.receiveNumber+"' name='amounts'></td>" */
	    	$("#tbody").append(html);
		}
		
		function leaderValid(){
			var elements = document.getElementsByName("ids");
			if(elements.length == 0){
				alertx("请选择标化辅材和数量");
				return false;
			}
			var elements = document.getElementsByName("amounts");
			for(var i=0;i < elements.length; i++){
				if(elements[i].value != 0){
					return true;
				}
			}
			return true;
		}
		function isExited(){
			$.ajax({
				type : 'POST',
				 url : '${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBill/isExited',
			     data : {
			        'orderId':$("#orderId").val(),
			     },
			     success:function(data){
			    	 if("success"== data){
			    		 alertx("该订单已经添加标化辅材！");
			    	 }
			     }
			});
		}
		function tijiao(){
			//alert(flag);
			/* if(flag == 'success'){
				alertx("该订单已存在领取记录，请直接修改领取的商品信息!");
				return;
			} */
			$("#inputForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBill/list">标化辅材领取记录列表</a></li>
		<li class="active"><a href="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBill/formVo?id=${bizMaterialsStandardReceiveBill.id}">标化辅材记录<shiro:hasPermission name="standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit">${not empty bizMaterialsStandardReceiveBill.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="managersettlement:standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizMaterialsStandardReceiveBillVo" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBill/saveVo" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" value="${bizMaterialsStandardReceiveBill.receiveEmployeeId }" name="hidemployeeId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" id="storeId" class="input-medium" onchange="getEmpByStoreid()" >
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">领取日期：</label>
			<div class="controls">
				<input path="receiveDatetime" name="receiveDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizMaterialsStandardReceiveBill.receiveDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">领取人：</label>
			<div class="controls"> 
				<form:select path="receiveEmployeeId" class="input-medium" id="receiveEmployeeId" onchange="getOrderByEmpId()"> 
					<form:option value="${bizMaterialsStandardReceiveBill.receiveEmployeeId }" label="${bizMaterialsStandardReceiveBill.receiveEmployeeName } ${bizMaterialsStandardReceiveBill.employeeNo }" />
				</form:select> 
				<%-- <input type="hidden" path="receiveEmployeeId" htmlEscape="false" class="input-medium" value="${bizMaterialsStandardReceiveBill.receiveEmployeeId }"/> --%>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单号：</label>
			<div class="controls"> 
				<form:select path="orderId" class="input-medium" id="orderId" onchange="isExited()">
					<form:option value="${bizMaterialsStandardReceiveBill.orderId }" label="${bizMaterialsStandardReceiveBill.orderNo } ${bizMaterialsStandardReceiveBill.customerName}" />
				</form:select> 
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">物料名称：</label>
			<div class="controls">
				<form:select path="materialId" class="input-medium" id="materialId">
					<form:option value="" label="" />
				</form:select>
				<input type="button" id ="addButton" value="添加" onclick="addMaterial()">
			</div>
		</div> --%>
		<div>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>物料类别</th>
						<th>物料名称</th>
						<th>单价</th>
						<th>领取数量</th>
					</tr>
				</thead>
				<tbody id ="tbody">
				</tbody>
			</table>
		</div>
		<div class="form-actions">
			<%-- <shiro:hasPermission name="standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit"><input id="btnSubmit" class="btn btn-primary" type="buttton" value="保 存" onclick="tijiao()" />&nbsp;</shiro:hasPermission> --%>
			<%-- <shiro:hasPermission name="standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
			<shiro:hasPermission name="standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<c:forEach items="${bizMaterialsStandardReceiveBill.details}" var="detail">
        <script>
	        temp = new Object();
	       	temp.id = "${detail.materialsId}";
	        temp.materialsType = "${detail.materialsType}";
	        temp.materialsName = "${detail.materialsName}";
	        temp.materialsPrice = "${detail.materialsPrice}";
	        temp.receiveNumber = "${detail.receiveNumber}";
	        addMaterialsRow(temp);
        </script>
    </c:forEach>
</body>
</html>