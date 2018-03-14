<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>任务包派单统计</title>
<script type="text/javascript">
	$(document).ready(function(){
		var arr = [ "1", "2", "3", "4","5","6","7","8","9"];
		var html;
		$.each(arr, function(){ 
			var temp = 0;
				$("."+this).each(function(){
					temp += $(this).text() * 1;
				});
				html += "<td>"+temp+"</td>"
			  
			});
		
		 $("#zongji").append(html);
		 var un = $("table tr").length;
		 
		 if(un == '2'){
			 $("#zongji").attr("hidden",true);
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
		 
		 
		 
		 //区域请选择
		 $("#area option").each(function(){ //遍历全部option
		       var txt = $(this).text(); //获取option的内容
		       var i =  $(this).attr("selected");
		       
		       if(txt == ''){
			        $(this).text("请选择...") 
			        } 
		       
		       
		       if(i == "selected"){
		    	   if(txt == ''){
		    		   $("#s2id_area").find(".select2-chosen").text("请选择...");
		    	   }else{
		    		   $("#s2id_area").find(".select2-chosen").text(txt);
		    	   } 
			        
		       }
		    });
		 
	});
	
function findEngineList(){
		
		
		var html3 = '<option value=""></option>';
		var storeId = $("#storeId").val();
		if (storeId=="" ||undefined==storeId ) {
			return;
		}
		//根据门店    动态加载工程区域
		$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
							}
							
							$("#area").html(html3);
						} else {
							$("#area").html(html3);
						}

					}

				});		
		}
	
	
   
</script>

</head>
<body>
	<form:form modelAttribute="taskPackCount" action="${ctx}/taskpackcount/list" method="post" class="breadcrumb form-search">
	<li><label>门店：</label> 
		<form:select path="storeId" class="input-medium needClear" onchange="findEngineList()">
			<form:option value="" label="请选择..." />
			<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
		</form:select>
	</li>
	<li><label>区域：</label>
		<form:select path="area" class="input-medium needClear">
				<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
		</form:select>
	</li>
	<li><label>派单员：</label>
		<form:input path="employeName" htmlEscape="false" class="input-medium"/>
	</li>
	<li><label>日期：</label>
		<input name="planStartdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
			value="<fmt:formatDate value="${planStartdate}" pattern="yyyy-MM-dd"/>"onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
	</li>
	
	<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit"  value="查询"/></li>
	<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
	</form:form>		
	<table id = "table" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
				<th>门店</th>
				<th>区域</th>
				<th>派单员</th>
				<th>水电类</th>
				<th>瓦工类</th>
				<th>木工类</th>
				<th>油工类</th>
				<th>拆除类</th>
				<th>安装类</th>
				<th>营销保护类</th>
				<th>特殊</th>
				<th>总计</th>
			</tr>
		</thead>
	<tbody>
		<c:forEach items="${list}" var="data" varStatus="status">
			<tr>
				
				<td class = "row2">
				
					${fns:getStoreLabel(data.storeId, '')}
				</td>
				<td>
					${ data.area }
				</td>
				<td>
					${ data.employeName }
				</td>
				<td class="1">
					${ data.shuidian }
				</td>
				<td class="2">
					${ data.wagong }
				</td>
				<td class="3">
					${ data.mugong }
				</td>
				<td class="4">
					${ data.yougong }
				</td>
				<td class="5">
					${ data.chaichu }
				</td>
				<td class="6">
					${ data.anzhuang }
				</td>
				<td class="7">
					${ data.yingxiao }
				</td>
				<td class="8">
					${ data.teshu }
				</td>
				<td class="9">
					${ data.count }
				</td>
			</tr>
		</c:forEach>
			<tr id="zongji">
				<td> </td>
				<td> </td>
				<td>总计</td>
			</tr>
		</tbody>
	
	</table>
	
</body>
</html>