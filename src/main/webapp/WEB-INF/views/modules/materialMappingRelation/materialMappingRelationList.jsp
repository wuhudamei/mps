<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材安装项二级类目关系列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

		$(document).ready(function() {
			findEngineListByStoreIdAndProjectMode("1");
            oneCategory();
			towCategory("1");
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
		

		function findEngineListByStoreIdAndProjectMode(type){
	        var html3 = "<option value='' label=''/>";
	        var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
			if(storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
				return;
			}
            var j = "${materialMappingRelation.installItemId}";
			//根据门店,动态加载主材安装项
			$.ajax({
				url : "${ctx}/materialMappingRelation/findMainItem?storeId="+storeId+"&projectMode="+projectModeValue,
				type : "get",
                async: false,
				dataType:"json",
				success : function(data) {
					if (null!= data && data.length > 0) {
                        if("1"==type){
                            for (var v = 0; v < data.length; v++) {
                                if(data[v].value == j){
                                    $("#s2id_installItemId").find(".select2-chosen").text(data[v].label);
                                    html3 +='<option selected= "selected" value="'+data[v].value+'">'+data[v].label+'</option>'
                                }else{
                                    html3 +='<option  value="'+data[v].value+'">'+data[v].label+'</option>'
                                }
                            }
                        }else{
                            for (var v = 0; v < data.length; v++) {
								html3 +='<option  value="'+data[v].value+'">'+data[v].label+'</option>'
                            }
                        }
						$("#installItemId").html(html3);
					} else {
						$("#installItemId").html(html3);
					}
				}
			});
		}

        function oneCategory(){
            //根据门店个动态 一级类目
            var html4 = "<option value='' label=''/>";
            var i = "${materialMappingRelation.categoryOne}";
            $.ajax({
                url : "${ctx}/materialMappingRelation/findOneCategory",
                type : "POST",
                async: false,
                dataType:"json",
                data:{"level":"1"},
                success : function(data) {
                    if (null!= data && data.length > 0) {
                        for (var v = 0; v < data.length; v++) {
                            if(data[v].value == i){
                                $("#s2id_categoryOne").find(".select2-chosen").text(data[v].label);
                                html4 +='<option selected= "selected" value="'+data[v].value+'">'+data[v].label+'</option>'
                            }else{
                                html4 +='<option  value="'+data[v].value+'">'+data[v].label+'</option>'
                            }
                        }
                        $("#categoryOne").html(html4);
                    } else {
                        $("#categoryOne").html(html4);
                    }
                }
            });
        }

		function towCategory(type){
            var categoryOne = $("#categoryOne").val();
            if (categoryOne=="" || undefined == categoryOne) {
                return false;
            }
			var html4 = "<option value='' label=''/>";
            var h = "${materialMappingRelation.categoryTwo}";
			$.ajax({
				url : "${ctx}/materialMappingRelation/findTowCategory",
				type : "POST",
				dataType:"json",
				data:{"level":"2","parentId":categoryOne},
				success : function(data) {
					if (null!= data && data.length > 0) {
                        if("1"==type){
                            for (var v = 0; v < data.length; v++) {
                                if(data[v].value == h){
                                    $("#s2id_categoryTwo").find(".select2-chosen").text(data[v].label);
                                    html4 +='<option selected= "selected" value="'+data[v].value+'">'+data[v].label+'</option>'
                                }else{
                                    html4 +='<option  value="'+data[v].value+'">'+data[v].label+'</option>'
                                }
                            }
                        }else {
                            for (var v = 0; v < data.length; v++) {
								html4 +='<option  value="'+data[v].value+'">'+data[v].label+'</option>'
                            }
                        }
						$("#categoryTwo").html(html4);
					} else {
						$("#categoryTwo").html(html4);
					}
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">主材安装项二级类目关系列表</a></li>
		<li><a href="${ctx}/materialMappingRelation/save">主材安装项二级类目关系新增</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="materialMappingRelation" action="${ctx}/materialMappingRelation/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				
					<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode('2')">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			
			</li>
			
			<li><label>工程模式：</label>
			<form:select path="projectMode" class="input-medium required needClear" onchange="findEngineListByStoreIdAndProjectMode('2')">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select>
			</li>
			<li><label>一级类目：</label>
				<form:select path="categoryOne" class="input-medium needClear" onchange="towCategory('2')">
				<form:option value="" label=""/>
				</form:select>
					
			</li>
			<li><label>二级类目：</label>
				<form:select path="categoryTwo" class="input-medium needClear">
					<form:option value="" label=""/>
					
				</form:select>
			</li>
			<li><label>主材安装项：</label>
				<form:select path="installItemId" class="input-medium needClear">
					<form:option value="" label=""/>
					
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
				<th>工程模式</th>
				<th>安装项</th>
				<th>一级类目</th>
				<th>二级类目</th>
				<th>最后操作人</th>
				<th>最后操作时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="materialMappingRelation">
			<tr>
				<td>
					${materialMappingRelation.storeName}
				</td>
				<td>
					${materialMappingRelation.projectModeName}
				</td>
				<td>
					${materialMappingRelation.installItemName}
				</td>
				<td>
					${materialMappingRelation.categoryOneName}
				</td>
				<td>
					${materialMappingRelation.categoryTwoName}
				</td>
				<td>
					${materialMappingRelation.userName}
				</td>
				<td>
					<fmt:formatDate value="${materialMappingRelation.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="${ctx}/materialMappingRelation/deleteRelation?id=${materialMappingRelation.id}" onclick="return confirmx('确认要删除该信息吗？', this.href)">删除</a>
    				<%-- <a href="${ctx}/materialMappingRelation/deleteRelation?id=${materialMappingRelation.id}"></a> --%>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>