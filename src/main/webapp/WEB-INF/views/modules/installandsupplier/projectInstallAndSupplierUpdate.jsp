<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材安装供应商设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
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
			getsupplier();
		});
		

		
		function getstoreAndpMode(radiobuttons){
			var storeid = $("#storeid").val();
			var radioValue =radiobuttons.value;
			
			$("#InstallItemNameId").html('');
		    $.ajax({
		        type : 'POST',
		        dataType : 'json',
		        url : '${ctx}/installitem/bizProjectInstallItem/ajaxGetInstallItem',
		        data : {
		        	storeId : storeid,
		        	projectMode : radioValue
		        },
		        success : function(data) {
// 		        	alert(data.resultMap[0].installItemName+":"+data.resultMap[0].id);
		            var html = "<select id='InstallItemNameId' name='projectInstallItemId' class='input-xlarge '>";
		            html += "<option value='' ></option>"
		            for (var i = 0; i < data.resultMap.length; i++) {
		            	html += "<option value='" + data.resultMap[i].id+"' >" + data.resultMap[i].installItemName + "</option>"
		            }
		            html+="</select>";
// 		            alert(html);
		            $("#InstallItemNameId").html('');
		            $("#InstallItemNameId").html(html);
		        }
		    })
		}
		//根据外包商业务类别查询类别下的外包商
		function getsupplier(radiobuttons){
// 			var radioValue =radiobuttons.value;
			
			var radioValue =$("#installitemtypeId").val();
			$("#supplierNameId").html('');
		    $.ajax({
		        type : 'POST',
		        dataType : 'json',
		        url : '${ctx}/supplier/bizSupplier/ajaxgetSupplier',
		        data : {
		        	projectInstallItemName : radioValue
		        },
		        success : function(data) {
// 		        	alert(data.resultMap[0].installItemName+":"+data.resultMap[0].id);
		            var html = "<select id='supplierNameid1' name='supplierName' class='input-xlarge '>";
		            html += "<option value='' ></option>"
		            for (var i = 0; i < data.resultMap.length; i++) {
		            	html += "<option value='" + data.resultMap[i].id+"'>" + data.resultMap[i].supplierName + "</option>"
		            }
		            html+="</select>";
// 		            alert(html);
		            $("#supplierNameId").html('');
		            $("#supplierNameId").html(html);
		        }
		    });
		    
		}
		    
		    //查出一条数供应商数据
			var employeeIdMap = new Object();//临时存储对象,用于保存工人是否已经被选中
		    var employeeTemp;                //临时存储对象，用于工人组工人列表的回
			function onAddSupplier() {
				var supplierNameId = $("#supplierNameId").val();
				//alert("employeeId:" + employeeId +", employeeIdMap['employeeIdMap_" + employeeId + "']:" + employeeIdMap["employeeIdMap_" + employeeId]);
				if (!supplierNameId || supplierNameId == '') {
					alert("请选择供应商");
					return;
				} else if (employeeIdMap["employeeIdMap_" + supplierNameId]) {
					alert("已经选择了该供应商");
					return;
				}
				var aj = $.ajax({
							url : '${ctx}/supplier/bizSupplier/ajaxgetSupplierId',
							data : {
								id:supplierNameId
							},
							type : 'post',
							cache : false,
							dataType : 'json',
							success : function(data) {
								
								console.info(data)
								if("1"==data.hadIn){
									alert("该供应商已经在，请选择其他供应商！");
									return;
								}
								var  listRow =  data.resultMap[0];
// 								alert(listRow[0].id+":"+listRow);
								addEmployeeRow(listRow);
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								// view("异常！");  
								alert("异常！" + errorThrown);
							}
						});
			}

		
			//把查询出来的供应商put到表中
			function addEmployeeRow(data){
				var isLeaderSelected = "";
				var isLeaderUnSelected = "";
				if(data.isLeader && data.isLeader == 1){
					isLeaderSelected = "selected=\"selected\"";
				}else{
					isLeaderUnSelected = "selected=\"selected\"";
				}
				//alert(data.isLeader + " isLeaderSelected:" + isLeaderSelected + " isLeaderUnSelected:" + isLeaderUnSelected);
				var html = "" + "<tr id='employeeTr_"+data.id+"'>"
		        + "  <td>" + data. supplierNo  + "<input type='hidden' id='supplierId1' name='supplierId' value=\""+data.id+"\"></input></td>"
		        + "  <td>" + data.supplierName  + "</td>"
		        + "  <td>" + data.contacts + "</td>"
		        + "  <td>" + data.contactsPhone + "</td>"
// 		        + "  <td>"
// 		        + "    <select id='isLeader_" + data.id + "' id='isLeader' name='isLeader' onchange='leaderValid()'>"
// 		        + "      <option value='1' " + isLeaderSelected + ">是</option>"    
// 		        + "      <option value='0' " + isLeaderUnSelected + "+>否</option>"
// 		        + "    </select>"
// 		        + "  </td>"
// 		        + "  <td>"
// 		        + "     <select id='salaryRatio' id='ratio' name='ratio'>";
// 		        for(var i=10;i<101;i=i+5){
// 		        	var sec = "";
// 		        	if(data.salaryRatio && data.salaryRatio == i){
// 		        		sec = "selected=\"selected\"";
// 		        	}
// 		        	html += "<option value='" + i +"' " + sec + ">" + i + "%</option>";
// 		        }
// 		        html += "</select></td>" 
		        + "  <td><a onclick='removeEmployee(\"" + data.id + "\")'>删除</a></td>" + "</tr>";
		        $("#employeeTbody").append(html);
		        employeeIdMap["employeeIdMap_" + data.id] = "0";
		        //alert("employeeId:" + data.id +", employeeIdMap['employeeIdMap_" + data.id + "']:" + employeeIdMap["employeeIdMap_" + data.id]);
			}

		
		//删除添加到表中的
		function removeEmployee(employeeId) {
			
// 			alert(employeeId);
			$("#employeeTr_" + employeeId).remove();
			employeeIdMap["employeeIdMap_" + employeeId] = undefined;
		}
		
		
		function getelementLth(){
			var lementLenth=$('#employeeTbody').children().length;
// 			alert(lementLenth);
			if(lementLenth<1){
				alert("请添加供应商")
				return;
			}
		}
		function sbumitoc(){
            $("#inputForm").attr("action", "${ctx}/installandsupplier/projectInstallAndSupplier/saveUpdate");
            $("#inputForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/installandsupplier/projectInstallAndSupplier/">主材安装供应商设置列表</a></li>
		<li class="active"><a href="${ctx}/installandsupplier/projectInstallAndSupplier/form?id=${projectInstallAndSupplier.id}">主材安装供应商设置<shiro:hasPermission name="installandsupplier:projectInstallAndSupplier:edit">${not empty projectInstallAndSupplier.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="installandsupplier:projectInstallAndSupplier:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="projectInstallAndSupplier" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select id="storeid" path="storeid"  disabled="true" class="input-xlarge" >
				
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
			<form:radiobuttons onclick="getstoreAndpMode(this)"  disabled="true" path="projectMode" class="input-medium required" items="${fns:getDictList('project_mode')}"
			itemLabel="label" itemValue="value" htmlEscape="false"/><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">安装项：</label>
			<div class="controls">
				<form:select path="projectInstallItemId"  disabled="true"    class="input-xlarge" id="InstallItemNameId">
				<form:option value="${projectInstallAndSupplier.id}" label="${projectInstallAndSupplier.installItemName1}"/>
				</form:select>
			</div>
		</div>
	<div class="control-group">
			<label class="control-label">供应商业务分类：</label>
			<div class="controls">
			<form:select path="projectInstallItemName" onchange="getsupplier(this)" id="installitemtypeId"  class="input-xlarge">
					<form:options items="${fns:getDictList('install_item_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select> 
			</div>
		</div>

	<div class="control-group">
			<label class="control-label">请选择供应商：</label>
	
			<div class="controls">
			<form:select path="supplierName"  id="supplierNameId" class="input-xlarge ">
				</form:select> &nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:onAddSupplier()">添加</a>
			</div>
		</div>
			<table id="employeeTb"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<td>供应商编号</td>
					<td>供应商名称</td>
					<td>联系人</td>
					<td>手机号</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody  id="employeeTbody">
				<c:forEach items="${supplierList}" var="supplierList">
			
			        <script>
			        employeeTemp = new Object();
			        employeeTemp.id = "${supplierList.id}";
			        employeeTemp.supplierNo = "${supplierList.supplierNo}";
			        employeeTemp.supplierName = "${supplierList.supplierName}";
			        employeeTemp.contacts = "${supplierList.contacts}";
			        employeeTemp.contactsPhone = "${supplierList.contactsPhone}";
			        addEmployeeRow(employeeTemp);
			        </script>
			
			</tr>
		</c:forEach>
			</tbody>
		</table>

		<div class="form-actions">
			<shiro:hasPermission name="installandsupplier:projectInstallAndSupplier:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"  onMouseOver="getelementLth()"   onclick="sbumitoc()" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>