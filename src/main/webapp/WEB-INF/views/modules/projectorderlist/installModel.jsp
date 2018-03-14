<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安装模式</title>
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
		
		});
		

		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function a(obj){
			
		   if($(obj).attr("checked"))
		    {
		        $(obj).removeAttr('checked');
		    }
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
// 			alert()
			var s = $("input:radio[checked='checked']").length;
			var l = $("input:checkbox[checked='checked']").length
			
			
			var temp = '';
			var nameradio = '';
			$("input:radio[checked='checked']").each(function(){
				var v = $(this).val();
				var name = $(this).attr("name");
				nameradio += name;
				temp += v+",";
			});
			var temp1 = '';
			var namecheckbox = '';
			$("input:checkbox[checked='checked']").each(function(){
                var v = $(this).val();
                var name = $(this).attr("name");
                name = $.trim(name);
                if(name != 'undefined' && name != ''){
                    namecheckbox += name;
                    temp1 += v+",";
                }
			});
			if($.trim(nameradio)==$.trim(namecheckbox)){
				
				$("#installItemIds").val(temp1);
				$("#installMode").val(temp);
				$("#searchForm").submit();
			}else{
				alertx("请勾选对应的安装模式或安装项！")
				return false;
			}
		}
		
		function formOrderNum(){
			var v = $("#orderNumberid").val();
// 			alert(v);
			location.href = "${ctx}/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBill/materialsChangeBillList?orderNumber="+v;
		}
		function gotofun(){
			var v = $("#orderId").val();
// 			location.href = "${ctx}/projectOrderList/updateWallStatus?flag=1&orderId="+v;
			
			$("#searchForm").attr("action","${ctx}/projectOrderList/updateWallStatus?flag=2");
			$("#searchForm").submit();
			
		}
		function checkval(obj){
			//获取cheack属性
			var ch = $(obj).attr("checked");
			//获取name属性
			var name = $(obj).attr("name");
			
			var val = $(obj).val();
			//如果取消选中，后面的安装模式也跟着取消
			if(ch == undefined){
				 $("input:radio[name="+name+"][checked='checked']").removeAttr("checked");
			}else{
			//如果选中，判断安装模式的值，
			var und = $("input:radio[name="+name+"][checked='checked']").val()
			//如果没有值就不加载，模板里的值
			if(und != '1' && und != '2' ){
				$.ajax({
		             type: "GET",
		             url: "${ctx}/projectOrderList/findInstallModel",
		             data: {id:val},
		             dataType: "text",
		             success: function(data){
		                      $("input:radio[name="+name+"][value="+data+"]").attr("checked",true);
		             }
		         });
			}else{
				//有值就什么也不做
			}
		}
	}
			
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">主材安装项</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/projectOrderList/save" method="post">
		<input id = "installItemIds" name = "installItemIds" style="display: none;"/>
		<input id = "installMode" name = "installMode" style="display: none;"/>
		<input id = "orderId" name = "orderId" value="${order.orderId }" style="display: none;"/>
		<input  id = "storeId"  name = "storeId" value="${order.storeId }" style="display: none;"/>
		<input  id = "projectModeid"  name = "projectMode" value="${order.projectMode }" style="display: none;"/>
		<input  id = "orderNumberid"  name = "orderNumber" value="${order.orderNumber }" style="display: none;"/>
		<input  id = "orderStatusNumberId"  name = "orderStatusNumber" value="${order.orderStatusNumber }" style="display: none;"/>
		<input name = "flag" value="${flag }" style="display: none;"/>
	</form>
	<table style="width:100%" align="center" valign="center" class="table table-striped table-bordered table-condensed">
					<tbody><tr>
						<td colspan="2" style="width:30%"><label><span style="font-size: 18px;">客户姓名：</span></label>
							${order.customerName }
						</td>
						<td colspan="2" style="width:30%"><label> <span style="font-size: 18px;">客户地址：</span></label>
							${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }
						</td>
						<td colspan="2" style="width:40%">
						<c:if test="${isxin==0}">
							<input id="gotofunid" class="btn btn-primary" type="button" value="选材清单(新)" onclick = "gotofun()"/>
						</c:if>
						<c:if test="${isxin==6}">
							<input id="gotofunid1" class="btn btn-primary" type="button" value="选材清单" onclick = "gotofun()"/>
						</c:if>
							<input id="btnSubmitOrderNum" class="btn btn-primary" type="button" value="变更单" onclick = "formOrderNum()"/>
						</td>
					</tr></tbody>
	</table>
	
	
	
	
	
	
	<input type="checkbox" onclick="swapCheck()"/>全选/全不选
	<input type="checkbox" onchange="checkProject(1)" id = "allProject1">全选产业/全不选产业
	<input type="checkbox" onchange="checkProject(2)" id = "allProject2">全选传统/全不选传统
	<sys:message content="${message}"/>
	<script type="text/javascript">
		var isCheckProjects1 = true; 
		var isCheckProjects2 = true; 
		function checkProject(a){
			
			
			var isCheckProject;
			if(a == '1'){
				isCheckProject = isCheckProjects1;
				 $("#allProject2").attr("checked",false) 
				 isCheckProjects2 = true; 
			}else{
				isCheckProject = isCheckProjects2;
				 $("#allProject1").attr("checked",false) 
				 isCheckProjects1 = true; 
			}
				if(isCheckProject){
					$("input[type='radio'][value="+a+"]").each(function(){
						var temps = this.onclick;
						if(temps == null){
							this.checked = true;
						}
					});
					if(a == '1'){
						 isCheckProjects1 = false;
					}else{
						isCheckProjects2 = false;
					}
				} else{
					$("input[type='radio'][value="+a+"]").each(function(){
						var temps = this.onclick;
						if(temps == null){
							this.checked = false;
						}
					});
					if(a == '1'){
						 isCheckProjects1 = true;
					}else{
						isCheckProjects2 = true;
					}
				}
			
		}
		
		function fistStr(a){
			 var temps = a.onclick;
			 console.log(temps)
			 if(temps != null){
				 var temp = $.trim(temps);
				 if(temp != ''){
					 var s = this.onclick.toString.replace(/^.*?\{([^\}]*?)\}/,"$1");
					 return s.slice(0,1);
				 }
				 
				 return '';
				
			 }
            return '';
		}
		
	
		
		 var isCheckAll = false;  
	     function swapCheck() {  
	            if (isCheckAll) {  
	                $("input[type='checkbox'][name]").each(function() {  
	                  
	                    var temps = this.onclick.toString().replace(/^.*?\{([^\}]*?)\}/,"$1")
	                    var temp = $.trim(temps)
	                   	 	if(temp.slice(0,1) != 'r'){
	                    	  this.checked = false;
	                    }
	                });  
	                isCheckAll = false;  
	            } else {  
	                $("input[type='checkbox'][name]").each(function() { 
	                	 var temps = this.onclick.toString().replace(/^.*?\{([^\}]*?)\}/,"$1")
	                	  var temp = $.trim(temps)
	                   	 	if(temp.slice(0,1) != 'r'){
		                    	  this.checked = true;
		                    }
	                });  
	                isCheckAll = true;  
	            }  
	        }  
	</script>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>安装项</th>
				<th>安装模式</th>
				<th>状态</th>
				<th>日期</th>
			</tr>
		</thead>
		<tbody>
			
				<c:forEach items="${installItemList }" var="item" varStatus = "index">
					<tr>
						<!-- 订单下的安装项选中 -->
						<c:if test="${item.isChoosed=='1' }">

							<!--2: 这个订单为200(施工中)以上,安装项计划是否为已申请或已验收 ,就不可修改 -->
							<c:if test="${item.status =='1' }">
									<td>
										<input type="checkbox" value="${item.projectInstallItemId }" 
													name="${index.index }" checked="checked"
													onclick="return false" />
										<label>${item.projectInstallItemName }</label>
									</td>
									<td>
									<c:if test="${item.installModeFlag=='1' }">
											<c:if test="${item.installMode=='1' }">
											<input type="radio" name="${index.index }" onclick="return false" value="1" checked="checked">产业
                               				<input type="radio" name="${index.index }" onclick="return false" value="2">传统 
										</c:if>
										<c:if test="${item.installMode=='2' }">
											<input type="radio" name="${index.index }" onclick="return false" value="1">产业
                               				<input type="radio" name="${index.index }" onclick="return false" value="2" checked="checked">传统 
										</c:if>
										<c:if test="${empty item.installMode}">
											<input type="radio" name="${index.index }" onclick="return false" value="1">产业
                               				<input type="radio" name="${index.index }" onclick="return false" value="2">传统 
										</c:if>
									</c:if>
									<c:if test="${empty item.installModeFlag }">
											<c:if test="${item.installMode=='1' }">
											<input type="radio" name="${index.index }" ondblclick='a(this)' value="1" checked="checked">产业
                               				<input type="radio" name="${index.index }" ondblclick='a(this)' value="2">传统 
										</c:if>
										<c:if test="${item.installMode=='2' }">
											<input type="radio" name="${index.index }" ondblclick='a(this)' value="1">产业
                               				<input type="radio" name="${index.index }" ondblclick='a(this)' value="2" checked="checked">传统 
										</c:if>
										<c:if test="${empty item.installMode}">
											<input type="radio" name="${index.index }" ondblclick='a(this)' value="1">产业
                               				<input type="radio" name="${index.index }" ondblclick='a(this)' value="2">传统 
										</c:if>
									</c:if>
									</td>
									<td>
										${fns:getDictLabel(item.installStatus, 'install_status', '')}
									</td>
									<td><fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							
									
							</c:if>

							<!-- 2: 如果不大于状态200,则可以修改 -->
							<c:if test="${item.status=='0' ||  empty item.status }">
								<td>
										<input type="checkbox" value="${item.projectInstallItemId }" name="${index.index }" checked="checked"  onclick="checkval(this)" />
										<label>${item.projectInstallItemName }</label>
								</td>
								<td>
										<c:if test="${item.installModeFlag=='1' }">
											<c:if test="${item.installMode=='1' }">
											<input type="radio" name="${index.index }" onclick="return false" value="1" checked="checked">产业
                               				<input type="radio" name="${index.index }" onclick="return false" value="2">传统 
										</c:if>
										<c:if test="${item.installMode=='2' }">
											<input type="radio" name="${index.index }" onclick="return false" value="1">产业
                               				<input type="radio" name="${index.index }" onclick="return false" value="2" checked="checked">传统 
										</c:if>
										<c:if test="${empty item.installMode}">
											<input type="radio" name="${index.index }" onclick="return false" value="1">产业
                               				<input type="radio" name="${index.index }" onclick="return false" value="2">传统 
										</c:if>
									</c:if>
									<c:if test="${empty item.installModeFlag }">
											<c:if test="${item.installMode=='1' }">
											<input type="radio" name="${index.index }" ondblclick='a(this)' value="1" checked="checked">产业
                               				<input type="radio" name="${index.index }" ondblclick='a(this)' value="2">传统 
										</c:if>
										<c:if test="${item.installMode=='2' }">
											<input type="radio" name="${index.index }" ondblclick='a(this)' value="1">产业
                               				<input type="radio" name="${index.index }" ondblclick='a(this)' value="2" checked="checked">传统 
										</c:if>
										<c:if test="${empty item.installMode}">
											<input type="radio" name="${index.index }" ondblclick='a(this)' value="1">产业
                               				<input type="radio" name="${index.index }" ondblclick='a(this)' value="2">传统 
										</c:if>
									</c:if>
									</td>
									
									<td>
										${fns:getDictLabel(item.installStatus, 'install_status', '')}
									</td>
									<td><fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</c:if>


						</c:if>
						<!-- 不是订单下的安装项 -->
							<c:if test="${item.isChoosed!='1' }">
								<td>
									<input type="checkbox" value="${item.projectInstallItemId }" name="${index.index }" onclick="checkval(this)"/>
									<label>${item.projectInstallItemName }</label>
								</td>
								<td>
								
									<c:if test="${item.installModeFlag=='1' }">
											<c:if test="${item.installMode=='1' }">
											<input type="radio" name="${index.index }" onclick="return false" value="1" checked="checked">产业
                               				<input type="radio" name="${index.index }" onclick="return false" value="2">传统 
										</c:if>
										<c:if test="${item.installMode=='2' }">
											<input type="radio" name="${index.index }" onclick="return false" value="1">产业
                               				<input type="radio" name="${index.index }" onclick="return false" value="2" checked="checked">传统 
										</c:if>
										<c:if test="${empty item.installMode}">
											<input type="radio" name="${index.index }" onclick="return false" value="1">产业
                               				<input type="radio" name="${index.index }" onclick="return false" value="2">传统 
										</c:if>
									</c:if>
									<c:if test="${empty item.installModeFlag }">
											<c:if test="${item.installMode=='1' }">
											<input type="radio" name="${index.index }" ondblclick='a(this)' value="1" checked="checked">产业
                               				<input type="radio" name="${index.index }" ondblclick='a(this)' value="2">传统 
										</c:if>
										<c:if test="${item.installMode=='2' }">
											<input type="radio" name="${index.index }" ondblclick='a(this)' value="1">产业
                               				<input type="radio" name="${index.index }" ondblclick='a(this)' value="2" checked="checked">传统 
										</c:if>
										<c:if test="${empty item.installMode}">
											<input type="radio" name="${index.index }" ondblclick='a(this)' value="1">产业
                               				<input type="radio" name="${index.index }" ondblclick='a(this)' value="2">传统 
										</c:if>
									</c:if>
								</td>
								<td>
										${fns:getDictLabel(item.installStatus, 'install_status', '')}
								</td>
								
								<td><fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</c:if>
						</tr>
					</c:forEach>
					
					
		</tbody>
	</table>
	<div align="center"> 
		<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="tip()"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="btnCancel" class="btn" type="button" value="返 回 列 表" onclick="history.go(-1)"/>
	</div>
		
	<div class="pagination">${page}</div>
</body>
</html>