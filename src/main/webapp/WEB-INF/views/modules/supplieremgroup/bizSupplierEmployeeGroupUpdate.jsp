<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材安装供应商和工人组管理</title>
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
			getEmployeeGroupId();
			getsupplier();

			

			
		});
		//根据外包商业务类别查询类别下的外包商
		function getsupplier(radiobuttons){
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
		            var html = "<select id='supplierNameId' name='supplierName' class='input-xlarge '>";
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
		//根据门店和工种查询工人组
		function getEmployeeGroupId(radiobuttons){
// 			alert(radioValue);
			
			var storeid=$("#storeid").val();
			if(storeid==''){
				alert("请选择门店");
			}
			var worktypeid=$("#worktypeid").val();
			
			$("#employeeGroupId").html('');
		    $.ajax({
		        type : 'POST',
		        dataType : 'json',
		        url : '${ctx}/empgroup/bizEmployeegroup/ajaxemployeegroup',
		        data : {
		        	storeId : storeid,
		        	workType:worktypeid
		        },
		        success : function(data) {
// 		        	alert(data.resultMap[0].installItemName+":"+data.resultMap[0].id);
		            var html = "<select id='supplierNameId' name='supplierName' class='input-xlarge '>";
		            html += "<option value='' ></option>"
		            for (var i = 0; i < data.resultMap.length; i++) {
		            	html += "<option value='" + data.resultMap[i].id+"'>" + data.resultMap[i].leaderRealName + "</option>"
		            }
		            html+="</select>";
// 		            alert(html);
		            $("#employeeGroupId").html('');
		            $("#employeeGroupId").html(html);
		        }
		    });
		    
		}
		
		
		
		function xiaoyan(){
			var worktypeid=$("#worktypeid").val();
			if(worktypeid==''){
				
			alert("请选择工种")
			}
		}
	    
	    //查出一条数供应商数据
		var employeeIdMap = new Object();//临时存储对象,用于保存工人是否已经被选中
	    var employeeTemp;                //临时存储对象，用于工人组工人列表的回
		function onAddemployeeGroup() {
			var supplierNameId = $("#employeeGroupId").val();
// 			alert("employeeId:" + supplierNameId +", employeeIdMap['employeeIdMap_" + supplierNameId + "']:" + employeeIdMap["employeeIdMap_" + supplierNameId]);
			if (!supplierNameId || supplierNameId == '') {
				alert("请选择工人组");
				return;
			} else if (employeeIdMap["employeeIdMap_" + supplierNameId]) {
				alert("已经选择了该工人组");
				return;
			}
			var aj = $.ajax({
						url : '${ctx}/empgroup/bizEmployeegroup/ajaxemployeegroup',
						data : {
							id:supplierNameId
						},
						type : 'post',
						cache : false,
						dataType : 'json',
						success : function(data) {
							
							console.info(data)
							if("1"==data.hadIn){
								alert("该工人组已经在其他工人组里了，请选择其他工人！");
								return;
							}
							var  listRow =  data.resultMap[0];
//								alert(listRow[0].id+":"+listRow);

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
			if(data.star==1){
				data.star="一星";
			} else if (data.star==2){
				data.star="二星";
			}else if (data.star==3){
				data.star="三星";
			}else if (data.star==4){
				data.star="四星";
			}else if (data.star==5){
				data.star="五星";
			}
			
			var isLeaderSelected = "";
			var isLeaderUnSelected = "";
			if(data.isLeader && data.isLeader == 1){
				isLeaderSelected = "selected=\"selected\"";
			}else{
				isLeaderUnSelected = "selected=\"selected\"";
			}
			//alert(data.isLeader + " isLeaderSelected:" + isLeaderSelected + " isLeaderUnSelected:" + isLeaderUnSelected);
			var html = "" + "<tr id='employeeTr_"+data.id+"'>"
	        + "<td>" + data.star + "</td>"
	        + "  <td>" + data.leaderRealNamezhen  + "<input type='hidden' id='employeeGroupId1' name='employeeGroupId1' value=\""+data.id+"\"></input></td>"
	        + "  <td>" + data.leaderPhone + "</td>"

	        + "  <td><a onclick='removeEmployee(\"" + data.id + "\")'>删除</a></td>" + "</tr>";
	        $("#employeeTbody").append(html);
	        employeeIdMap["employeeIdMap_" + data.id] = "0";
	        //alert("employeeId:" + data.id +", employeeIdMap['employeeIdMap_" + data.id + "']:" + employeeIdMap["employeeIdMap_" + data.id]);
		}


	
	//删除添加到表中的
	function removeEmployee(employeeId) {
		$("#employeeTr_" + employeeId).remove();
		employeeIdMap["employeeIdMap_" + employeeId] = undefined;
	}
	
	function getelementLth(){
		var lementLenth=$('#employeeTbody').children().length;
// 			alert(lementLenth);
		if(lementLenth<1){
			alert("请添加工人组")
			return;
		}

	}
	function sbumitoc(){
        $("#inputForm").attr("action", "${ctx}/supplieremgroup/bizSupplierEmployeeGroup/saveUpdate");
        $("#inputForm").submit();
	}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/supplieremgroup/bizSupplierEmployeeGroup/">主材安装供应商和工人组列表</a></li>
		<li class="active"><a href="${ctx}/supplieremgroup/bizSupplierEmployeeGroup/form?id=${bizSupplierEmployeeGroup.id}">主材安装供应商和工人组<shiro:hasPermission name="supplieremgroup:bizSupplierEmployeeGroup:edit">${not empty bizSupplierEmployeeGroup.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="supplieremgroup:bizSupplierEmployeeGroup:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizSupplierEmployeeGroup" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
	<div class="control-group">
			<label class="control-label">供应商业务分类：</label>
			<div class="controls">
			<form:select path="projectInstallItemName" onchange="getsupplier(this)" id="installitemtypeId"  class="input-xlarge">
					<form:options items="${fns:getDictList('install_item_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商：</label>
	
			<div class="controls">
			<form:select path="supplierName" disabled="true"  id="supplierNameId" class="input-xlarge needClear">
			<form:option value="${bizSupplierEmployeeGroup.id}" label="${bizSupplierEmployeeGroup.supplierName}" />
			
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select id="storeid" path="storeid" class="input-xlarge needClear" >
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工种：</label>
			<div class="controls">
				<form:select id="worktypeid" path="worktype" disabled="true"   onchange="getEmployeeGroupId(this)" class="input-xlarge needClear" >
						<form:option value="4" label="安装工"/>
				</form:select><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工人组：</label>
			<div class="controls">
			<form:select path="employeeGroupId"  onclick="xiaoyan()" id="employeeGroupId" class="input-xlarge ">
				</form:select> &nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:onAddemployeeGroup()">添加</a>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="supplieremgroup:bizSupplierEmployeeGroup:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" onMouseOver="getelementLth()"  onclick="sbumitoc()"  value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
		<table id="employeeTb"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<td>星级</td>
					<td>组长姓名</td>
					<td>手机号</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody id="employeeTbody" >
			<c:forEach items="${bizEmgrouprelationList}" var="bizEmgrouprelationList">
			
			        <script>
			        employeeTemp = new Object();
			        employeeTemp.id = "${bizEmgrouprelationList.id}";
			        employeeTemp.star = "${bizEmgrouprelationList.star}";
			        employeeTemp.leaderRealNamezhen = "${bizEmgrouprelationList.leaderRealName}";
			        employeeTemp.leaderPhone = "${bizEmgrouprelationList.leaderPhone}";
			        addEmployeeRow(employeeTemp);
			        </script>
			
			</tr>
		</c:forEach>
			</tbody>

		</table>
		
	</form:form>
</body>
</html>