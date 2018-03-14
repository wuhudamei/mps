<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材关系映射修添加</title>
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
		});
		
		/*"<select name='categoryOne' class='input-medium select2-choice'>"+"<option value='0'/>"+
							"<c:forEach items='${list }' var='type'>"+
	                        	"<option value='${type.value }'>${type.label }</option>"+
							"</c:forEach>"+
							"</select>"  */
		var html = null;
		var htmlone = null;
		var htmltow = null;
		var storeId = null;
		var i = 0;
		var r = 1;
		/* var s = 1; */
		function store(){
			
			var html1 = "<select id = item"+r+" name='installItemId'class='required'><option value=''/>";
			var htmlone1 = "<select id=one"+r+" class='required' name='myone' onchange='TowCategory("+r+",$(this).val())'><option value=''/>";
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
			if(storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
	                return;
	           }
			r++;
			$("#storeId").attr("disabled",true);
			$("#projectMode").attr("disabled",true);
			var s = i + 1;
			var html2 = "</select>";
			$.ajax({
	             type: "POST",
	             url: "${ctx}/materialMappingRelation/findMainItem",
	             dataType: "json",
	             data:{ "storeId": storeId,"projectMode":projectModeValue},
	             success: function(data){
	            	 html = null;
	            	 $.each( data, function(index, content){
	            	     html += "<option value="+content.value+">"+content.label+"</option>";
					 });
	            	 html  = html1 +html +html2;
				 }
	         });
			
			
			var htmlone2 = "</select>";
			$.ajax({
	             type: "POST",
	             url: "${ctx}/materialMappingRelation/findOneCategory",
	             dataType: "json",
	             data:{ "level":'1'},
	             success: function(data){
	            	 htmlone = null;
	            	 $.each( data, function(index, content){
	            		 htmlone += "<option value="+content.value+">"+content.label+"</option>";
					 });
	            	 htmlone  = htmlone1 +htmlone +htmlone2;
				 }
	         });
	    }
		

		function addRole(){
		
			if(i>8){
				alertx("你添加的行数过多，请先保存在添加！");
				return;
			}
			/* var s = i - 1; */
			var q = $("#tow"+i).val();
			/* var w = $("#one"+s).val();
			var e = $("#item"+s).val(); */
			
			
			if(i != 0){
				/* if(w ==''|| w ==null){
					alertx("请选择一级类目！")
					return;
				} */
				if(q==''|| q ==null){
					alertx("请选择一级类目！")
					return;
				}
				
				/* if(e==''|| e ==null){
					alertx("请选择安装项！")
					return;
				} */
				
			}
			
			var s = $("#storeId").val();
			
			store(s);
			var promode = $("#projectMode").val();
			if(s ==''){
				alertx("请选择门店！")
				return;
			}
			if(promode ==''){
				alertx("工程模式！")
				return;
			}
			i++;
			htmltow = "<select name='categoryTwo' class='required' id = 'tow"+i+"'></select>";
			
			var v = "<tr onclick='test("+i+")' id = "+i+">"+
						"<td>"+htmlone+
						"</td>"+
						"<td>"+htmltow+"</td>"+
						"<td>"+html+"</td>"+
						"<td><a  onclick='removeRow("+i+")'>删除</a></td>"+
					"</tr>";
			$("#mytbody").append(v);
			
		}
		function removeRow(a){
			$("#"+a).remove();
			i--;
		}
		function test(u){
			
			
		}
		
		
		 var htmltow1 = "<select name='categoryTwo' class='required' id='tow"+i+"'><option value=''/>";
		 var htmltow2 = "</select>";
		function TowCategory(r,da){
			$("#tow"+r).empty();
			/* var v = $('#one :selected').val() */
			$.ajax({
	             type: "POST",
	             url: "${ctx}/materialMappingRelation/findTowCategory",
	             dataType: "json",
	             data:{"level":'2',"parentId":da},
	             success: function(data){
	            	 htmltow = null;
	            	 $.each( data, function(index, content){
						 htmltow += "<option value="+content.value+">"+content.label+"</option>";
					 });
					 $("#tow"+r).append(htmltow);
				 }
			});
		}
		
		function save(){
			var t = 0;
			var data = $("[name='installItemId']")
			var dataone = $("[name='myone']")
			var datatwo = $("[name='categoryTwo']")
			
			$("[name='myspan']").remove();
			 $.each( data, function(index, content)  
       			  {   
       				if($(content).val() == ''){
       					t++;
       					
       					$(content).after("<span name='myspan' style='color: red;font-size: 10px'>*必填项</span>")
       				}
      			  });
			$("[name='myspanone']").remove();
			$.each( dataone, function(index, content)  
	       			  {   
	       				if($(content).val() == ''){
	       					t++;
	       					
	       					$(content).after("<span name='myspanone' style='color: red;font-size: 10px'>*必填项</span>")
	       				}
	      			  });
			$("[name='datatwo']").remove();
			$.each( datatwo, function(index, content)  
	       			  {   
	       				if($(content).val() == null){
	       					t++;
	       					$(content).after("<span name='datatwo' style='color: red;font-size: 10px'>*必填项</span>")
	       				}
	      			  });
			
			
			
			if(t == 0){
				$("#inputForm").submit();
			}
			
			
			}
	</script>
	<style type="text/css">
		.mywidth{
		width :100px
		}
		.mywidthselect{
		width :150px
		}
		.savewidth{
		width :60px
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/materialMappingRelation/list">主材安装项二级类目关系列表</a></li>
		<li class="active"><a href="JavaScript:void(0);">主材安装项二级类目关系新增</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="materialMappingRelation" action="${ctx}/materialMappingRelation/save" method="post" class="breadcrumb form-search">
	
		<sys:message content="${message}"/>
				
		<div class="control-group">
			<div class="controls">
			门店：
				<form:select path="storeId" class="input-xlarge required mywidthselect" onchange="store()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			工程模式：
			<form:select path="projectMode" class="input-medium required needClear" onchange="store()">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select>
			<input id="btnSubmit" class="btn btn-primary mywidth"  onclick = "addRole()" width="100px;" value="增加一行"/>
			<input id="btnSubmit" class="btn btn-primary savewidth" onclick="save()" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</div>
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>一级类目</th>
					<th>二级类目</th>
					<th>安装项</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id = "mytbody">
		
			</tbody>
 		</table>
	</form:form>
	
	
</body>
</html>