<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>墙地砖复尺管理审计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            getDepartments();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		//认可触发的事件
		function abandonedAgre(id){
			$("#myAbandonedModalReject").modal('show');
			$("#myIdReject").val(id);
		}
		//认可 取消 事件
		function onclickNoAbandonedReject(){
			$('#myAbandonedModalReject').modal('hide');
			$("#resonReject").val('');
		}
		
		//认可 提交的事件
		function onclickAbandonedReject(){
			var v = $("#myIdReject").val();
			var date = $("#resonReject").val();
			//有没有输入原因
			if(date == ""){
				$("#myNoAbandonedModalReject").modal('show');
			}else{
					window.location.href="${ctx}/materialwallfloor/wallRecheck/agreRecheckUpdate1?id="+v+"&statusDescribe="+date;
					}
			$("#resonReject").val('');
			$('#myAbandonedModalReject').modal('hide');		
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
        
        function exportRecheck (){
        	$("#searchForm").attr("action" ,"${ctx}/materialwallfloor/wallRecheck/exportRecheck")
        	$("#searchForm").submit();
        }
		
        function query (){
        	$("#searchForm").attr("action" ,"${ctx}/materialwallfloor/wallRecheck/listExamine")
        	$("#searchForm").submit();
        }



        //加载区域信息
        function getDepartments(){
            $("#enginDepartId").html('');
            $.ajax({
                url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
                type:'post',
                dataType : 'json',
                data:{
                    'storeId':$('#storeId').val(),
                    'projectMode':$('#projectMode').val()
                },
                success:function(data){
                    if(data == null){
                        $("#enginDepartId").append('');
                    } else {
                        var html = "<option value=''></option>";
                        for(var i=0;i<data.length;i++){
                            var sec = "";
                            if('${wallRecheck.orderacceptarea}' == data[i].value){
                                sec = "selected='selected'";
                                $("#s2id_enginDepartId .select2-chosen").html(data[i].label);
                            }
                            html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"

                        }
                        html+='';
                        $("#enginDepartId").append(html);
                    }
                }
            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/materialwallfloor/wallRecheck/listExamine">墙地砖复尺列表</a></li>
<%-- 		<shiro:hasPermission name="materialwallfloor:wallRecheck:edit"><li><a href="${ctx}/materialwallfloor/wallRecheck/form">墙地砖复尺添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="wallRecheck" action="${ctx}/materialwallfloor/wallRecheck/listExamine" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<ul class="ul-form">
					<li><label>门店：</label>
						<form:select path="storeId" id="storeId"
									 class="input-medium needClear" onchange="getDepartments()">
							<form:options items="${fns:getStoreList()}" itemLabel="label"
										  itemValue="value" htmlEscape="false" />
						</form:select>
					</li>

					<li><label>工程模式：</label>
						<form:select path="projectMode" class="input-medium needClear"  onchange="getDepartments()"  >
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('project_mode')}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</li>



					<li><label>区域：</label>
						<form:select path="orderacceptarea"	id="enginDepartId" class="input-medium needClear">
						</form:select></li>
		
			<li><label>订单编号：</label>
				<form:input path="orderNmber" htmlEscape="false" maxlength="32" class="input-medium needClear"/>
			</li>
			<li><label>客户：</label>
				<form:input path="coveredAdd" htmlEscape="false" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" class="input-medium needClear"/>
			</li>
			<li><label>设计师：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="1000" class="input-medium needClear"/>
			</li>
			<li><label>实际完工日期：</label>
				<input name="beginContractEndDate" id="beginContractEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${wallRecheck.beginContractEndDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endContractEndDate\')}',isShowClear:true});"/> 至
				<input name="endContractEndDate" id="endContractEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${wallRecheck.endContractEndDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginContractEndDate\')}',isShowClear:true});"/>
			</li>
			<li><label>是否作废：</label>
			<form:select path="isScrap"   class="input-medium needClear" >
<%-- 				<form:option value="" label=""/> --%>
				<form:options items="${fns:getDictList('dict_iscountsquare')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>	
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('wall_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="query()"/></li>
				<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
				<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="导出" onclick="exportRecheck()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>门店</th>
				<th>区域</th>
				<th>工程模式</th>
				<th>订单编号</th>
				<th>业主</th>
				<th>地址</th>
				<th>设计师</th>
			<th>审计员</th>
				<th>项目经理</th>
				<th>实际完工日期</th>
				<th>预算面积</th>
				<th>实测面积</th>
				<th>实际下单面积</th>
				<th>墙地砖单价</th>
				<th>考核金额一</th>
				<th>考核金额二</th>
				<th>被考核人一</th>
				<th>被考核人二</th>
				<th>备注</th>
				<th>状态</th>
				<th>是否作废</th>
				<shiro:hasPermission name="materialwallfloor:wallRecheck:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wallRecheck">
			<tr>
<%-- 				<td><a href="${ctx}/materialwallfloor/wallRecheck/form?id=${wallRecheck.id}"> --%>
<%-- 					${wallRecheck.orderId} --%>
<!-- 				</a></td> -->
				<td>
				${wallRecheck.increase}
				</td>
				<td>
				${fns:getStoreLabel(wallRecheck.storeId, '')}
				</td>
				<td>
				${fns:getElacLabel(wallRecheck.orderacceptarea, '')}
				</td>
				<td>
					${fns:getDictLabel(wallRecheck.projectMode, 'project_mode', '')}
				</td>

				<td>
					${wallRecheck.orderNmber}
				</td>
				<td>
				${wallRecheck.customername}
				</td>
				<td>
				${wallRecheck.coveredAdd1}
				</td>
				<td>
					${wallRecheck.designerName}
				</td>
				<td>
				<c:if test="${wallRecheck.status==70}">
<%-- 					${wallRecheck.orderinspector} --%>
				</c:if>
					${wallRecheck.auditorName}
				</td>
				<td>
					${wallRecheck.itemManager}
				</td>
				<td>
					<fmt:formatDate value="${wallRecheck.contractenddate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${wallRecheck.squareBudget}
				</td>
				<td>
					${wallRecheck.squareMeasure}
				</td>
						
				<td>
					${wallRecheck.squarePurchase}
				</td>
						
				<td >
					${wallRecheck.price}
				</td>
				<td>
					${wallRecheck.assessAmountString1} 
<%-- 					${wallRecheck.assessAmount1} --%>
				</td>
				<td>
					${wallRecheck.assessAmountString2}
<%-- 					${wallRecheck.assessAmount2} --%>
				</td>
				<td>
					${wallRecheck.assessPersonName1}
				</td>
				<td>
					${wallRecheck.assessPersonName2}
				</td>
				<td>
					${wallRecheck.recheckRemarks}
				</td>

		
				<td>
					${fns:getDictLabel(wallRecheck.status, 'wall_status', '')}
				</td>
				<td>
				<c:if test="${wallRecheck.isScrap==1}">
					是
				</c:if>
				<c:if test="${wallRecheck.isScrap==0||wallRecheck.isScrap==null}">
					否
				</c:if>
				</td>
	

				<shiro:hasPermission name="materialwallfloor:wallRecheck:edit">
				<td>
					<c:if test="${wallRecheck.isScrap==0||wallRecheck.isScrap==null}">
				<c:if test="${wallRecheck.status==60}">
    				<a href="${ctx}/materialwallfloor/wallRecheck/queryResultExamine?id=${wallRecheck.id}">审批</a>
				</c:if>
				<c:if test="${wallRecheck.status==65}">
    				<a href="${ctx}/materialwallfloor/wallRecheck/queryResultExamineRenk?id=${wallRecheck.id}">审批</a>
				</c:if>
				<c:if test="${wallRecheck.status==70}">
    				<a href="${ctx}/materialwallfloor/wallRecheck/queryResult?id=${wallRecheck.id}">查看结果</a>
				</c:if>
		


<%-- 					<a href="${ctx}/materialwallfloor/wallRecheck/delete?id=${wallRecheck.id}" onclick="return confirmx('确认要删除该墙地砖复尺吗？', this.href)">删除</a> --%>
				</c:if>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
							<!-- 认可btn弹框的model -->
			<div  class="modal fade" id="myAbandonedModalReject" tabindex="-1" role="dialog" style="width:350px">
								<input  id="myIdReject" type="hidden">
								<div class="modal-header">
									<button class="close" type="button" data-dismiss="modal">×</button>
									<h4 id="myModalLabel" align="center" style="color: black;">认可内容</h3><br>
									<form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
										<input id = "ids"name="id" value="${bizMaterialsStandardReceiveBill.id}" type="hidden">
										<input id = "status" name="status" value="20" type="hidden">
									<div  style="margin:10px;min-height:20px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
										<textarea id="resonReject" class="mask-text"  onkeyup="this.value = this.value.substring(0,100)" placeholder="请输入认可内容，多行输入，不多于200个汉字" maxlength="200" ></textarea>
										<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandonedReject()" >确定</a>
										<a href="javascript:void(0)" class="btn" onclick="onclickNoAbandonedReject()">关闭</a>
									</div>
									</div>
									</form:form>
		</div>
</body>
</html>