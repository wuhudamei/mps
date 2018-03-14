<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>员工信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/cusumerValidate/cusumerValidate.js"></script>
<script type="text/javascript">
	$(document).ready({
		
		
		
			
	})
	$(function(){
		$("#inputForm").validate({
			rules : {
				checkId : "required"
			},
			messages : {
				checkId : "必选项！"
			}
		});
		
	})
		$(function(){
			var v = $("#optionvalue").val();
			$("input[value="+v+"]").attr("checked", true);
		})
		
		
		var fun =function(){  
		$(".adds").html("");
		var tom=['CCC','DDD']  
		var collect=document.getElementById("adds")  
		var old=collect.innerHTML  
			alert("aaa")
		    var niw=" "  
		    for(var i=0;i<tom.length;i++){  
		        niw+='<option>'+tom[i]+'</option>';  
		    }  
		    collect.innerHTML=old+niw  
		      
		}  
		

	
	function newAdd(){
		var s = "<select  class='input-medium adds' ><option value='' label=''/><options  itemLabel='label' itemValue='value' htmlEscape='false'/></select>";
		var s2 = "<select  class='input-medium' style='width:80px;'><option value='' label=''/><options  itemLabel='label' itemValue='value' htmlEscape='false'/></select>";
		var newAdd = "<input style='width: 55px' id='btnSubmit' class='btn btn-primary' type='button' value='添加' onclick='newAdd()'/>"
		
		var v = s + s2 +s + newAdd +"<br />";
		var v1 = s2 +s+s2+s+ "<br />";
		$("#addtr").append(v1);
	}
	
	
	function add(){
		/* var s = "<select id='adds' class='input-medium' ><option value='' label=''/></select>";
		var s2 = "<select  class='input-medium' style='width:80px;'><option value='' label=''/><options  itemLabel='label' itemValue='value' htmlEscape='false'/></select>";
		var newAdd = "<input style='width: 55px' id='btnSubmit' class='btn btn-primary' type='button' value='添加' onclick='newAdd()'/>"
		
		var v = s + s2 +s + newAdd +"<br />";
		var v1 = s2 +s+s2+s+ "<br />";
		
		var content ="<HR WIDTH=100% SIZE=7 ALIGN=LEFT NOSHADE><tr><td>";
			content += v;
			content += v1;
			
			content +="</div></td></tr>"; */
			var a = $("#hideList").val();
			
		$("#addtr").append(a);
		
	}
	
   
</script>

</head>
<body>
	<form id="inputForm"
		action="${ctx}/dataAuthority/configure/save" method="post"
		class="form-horizontal">
		
		<table>
		<tr>
				<td>
					<div class="control-group" style="font-size: 25px">
					<br />
						${roleName }<br /><br />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
					<br />
						数据权限配置页面<br /><br />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group"  >
						业务数据：<input  type="text"  value="${data }" disabled="disabled"/>
						<input type="hidden" name="transactionId"  value="${id }"/>
						<input type="hidden" name="roleId"  value="${roleId }"/>
						<input type="hidden" name="roleName"  value="${roleName }"/>
						<input type="hidden" id = "optionvalue" value="${value }"/>
						 <%-- <form:select path="transactionData" class="input-medium" > --%>
						<%-- <form:option value="" label=""/>
						<form:options items="${fns:getDictList('transaction_data')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
						<%-- </form:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input style="width: 55px" id="btnSubmit" class="btn btn-primary" type="button" value="新增" onclick="add()"/> --%>
						
					</div>
				</td>
				<td>
					
				
				</td>
			</tr>
			<tr >
				<td>
					<div class="" style="font-size: 20px">
						数据授权：
					</div>
				</td>
			</tr>
				<c:forEach items = "${list }" var = "datas">
			<tr>
				
				<td height="20px">
					<br />
					<input type="radio" name = "checkId"  value="${datas.id }">
					${datas.transactionData }
					<br />
				</td>
				
			</tr>
			</c:forEach>
			
			<tr>
				<td>
				<br />
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存" />
				</td>
			</tr>
			
		</table>
		</form>
</body>
</html>