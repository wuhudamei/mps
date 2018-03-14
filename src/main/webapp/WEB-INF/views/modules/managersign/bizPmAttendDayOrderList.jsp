<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经理签到查询</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.undis{display:none;}
		.Black {
		    position: absolute;
		    top: 0;
		    left: 0;
		    background: rgba(0, 0, 0, .6);
		    width: 100%;
		    height: 100%
		}
		
		.Black .tc_center {
		    padding: 15px;
		    position: absolute;
		    top: 50%;
		    left: 50%;
		    width: 460px;
		    height: 220px;
		    margin-left: -300px;
		    margin-top: -210px;
		    background: #fff;
		    color: #666
		}
		
		 .Black .tc_center h2 {
		    font-size: 20px;
		    text-align: center;
		    line-height: 40px
		}
		
		 .Black .tc_center .cen_t {
		    width: 100%
		}
		
		 .Black .tc_center .cen_t p {
		    line-height: 30px;
		    font-size: 16px;
		    text-align: center;
		    margin-bottom: 12px
		}
		
		 .Black .tc_center .cen_t p input {
		    width: 50%;
		    line-height: 30px;
		    padding-left: 10px;
		    border-radius: 3px;
		    border: #ccc solid 1px
		}
		
		 .Black .tc_center .cen_t .cen_btn {
		    position: absolute;
		    width: 100%;
		    bottom: 20px;
		    text-align: center;
		    line-height: 30px
		}
		
		 .Black .tc_center .cen_t .cen_btn span {
		    width: 120px;
		    display: inline-block;
		    text-align: center;
		    cursor: pointer;
		    border-radius: 3px
		}
		
		 .Black .tc_center .cen_t .cen_btn .btn_y {
		    background: #3da5ee;
		    color: #fff
		}
		
		 .Black .tc_center .cen_t .cen_btn .btn_n {
		    margin-left: 50px;
		    border: solid 1px #3da5ee;
		    color: #3da5ee
		}
		
		 .Black .tc_center .cen_t .cen_tex {
		    width: 90%;
		    margin-left: 5%;
		    font-size: 16px;
		        clear: both;
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_l {
		    display: block;
		    line-height: 30px;
		    float: left;
		    width: 28%;
		    text-align: right;
		    vertical-align: middle
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r {
		    
		    text-align: left;
		    vertical-align: middle;
		    position: relative;
		    margin-bottom: 8px
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r input {
		    width: 90%;
		    background: 0 0
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r textarea {
		    color: #666;
		    width: 90%;
		    height: 200px;
		    padding: 5px 15px;
		    line-height: 20px;
		    font-size: 12px;
		    border: #ccc solid 1px;
		    border-radius: 3px
		}
		
	
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
		
		//产业
		function noNew(){
			$("#supplierDateBoxNew").hide();	
		}
		//
		function  updateIsValid(obj,id,isValid,signErrorDistance,signId){
			$("#signErrorDistance").text(signErrorDistance+'米');
			$("#isValidUpdate").html("");
			if(isValid==0){
				jQuery("<option value='0'>不合格</option>").appendTo("#isValidUpdate");
				jQuery("<option value='1'>合格</option>").appendTo("#isValidUpdate");
				$("#s2id_isValidUpdate .select2-chosen").text("不合格");
			}else{
				jQuery("<option value='1' >合格</option>").appendTo("#isValidUpdate");
				jQuery("<option value='0'>不合格</option>").appendTo("#isValidUpdate");
				$("#s2id_isValidUpdate .select2-chosen").text("合格");
			}
			$("#dayOrderId").val(id);
            $("#signId").val(signId);
			$("#supplierDateBoxNew").show();
		}
		function yesNew(){
			var id = $("#dayOrderId").val();
			var isValid = $("#isValidUpdate option:selected").val();
            var signId = $("#signId").val();
			$.ajax({
				
				url : "${ctx}/managersign/managerSign/updateIsEnabledById?id="+id+"&isValid="+isValid+"&signId="+signId,
				type : "get",
				success : function(data) {
					if(data=="success"){
						window.location.href="${ctx}/managersign/managerSign/getDayOrderlist?storeId="+$("#storeId").val()+"&orderProjectMode="+$("#orderProjectMode").val()+"&engineDepartId="+$("#engineDepartSelect").val()+"&orderNumber="+$("#orderNumber").val()
                            +"&managerName="+$("#managerName").val()+"&customerName="+$("#customerName").val()+"&customerInfo="+$("#customerInfo").val()+"&signDate1="+$("#signDate1").val()
                            +"&signDate2="+$("#signDate2").val()+"&conditionDistance1="+$("#conditionDistance1").val()+"&conditionDistance2="+$("#conditionDistance2").val()+"&isValid="+$("#isValid").val();
					}else{
						$("#supplierDateBoxNew").hide();
						alertx("已生成考勤单，不能修改！");
					}
				}

			});	
		//	window.location.href="${ctx}/managersign/managerSign/updateIsEnabledById?id="+id+"&isValid="+isValid;
		}
		function findEngineListByStoreIdAndProjectMode(){
			var html3 = '<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeValue = $("#orderProjectMode").val();
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
		function formSubmit() {
            $("#searchForm").attr("action", "${ctx}/managersign/managerSign/getDayOrderlist");
            $("#searchForm").submit();
        }
	</script>
	
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/managersign/managerSign/">经理签到修改列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="managerSign" action="${ctx}/managersign/managerSign/getDayOrderlist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			
			<li><label>门店：</label>
				<form:select path="storeId"  class="input-medium needClear"  onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="orderProjectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" >
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="orderProjectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
				</form:select>
			</c:if>
			
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			
			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>客户地址：</label>
				<form:input path="customerInfo" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			<li><label>签到日期：</label>
				<input name="signDate1" id="signDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${signDate1}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> &nbsp;至&nbsp;
					<input name="signDate2" id="signDate2" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${signDate2}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>误差(单位:米)：</label>
				<form:input path="conditionDistance1" htmlEscape="false" class="input-medium"  maxlength="7"  />&nbsp;至&nbsp;
					<form:input path="conditionDistance2" htmlEscape="false" class="input-medium" maxlength="7" />
			</li>
			<li><label>签到状态：</label>
				<form:select path="isValid" class="input-medium needClear">
					<form:option label=""	value=""/>
					<form:option label="合格"	value="1"/>
					<form:option label="不合格"	value="0"/>
				</form:select>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="formSubmit()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
					<th>门店</th>
					<th>区域</th>
					<th>订单编号</th>
					<th>客户姓名</th>
					<th>客户地址</th>
					<th>项目经理</th>
					<th>签到时间</th>
					<th>签到阶段</th>
					<th>签到误差</th>
					<th>签到状态</th>
					<th>操作</th>

			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="managersign">
			<tr>
				<td>
					${fns:getStoreLabel(managersign.storeId, '')}
				</td>
				<td>${fns:getElacLabel(managersign.engineDepartId, '')}</td>
				<td>
					${managersign.orderNumber}
				</td>
				<td>
					${managersign.customerName}
				</td>
				<td>
					${managersign.customerAddress}${managersign.communityName}${managersign.buildNumber}${managersign.buildUnit}${managersign.buildRoom}
				</td>
				<td>
					${managersign.managerName}
				</td>
				<td>
					<fmt:formatDate value="${managersign.signDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					<c:if test="${managersign.signStep==10}">基装阶段</c:if>
					<c:if test="${managersign.signStep==20}">主材阶段</c:if>
				</td>
				<td>
					${managersign.signErrorDistance}
				</td>
				<td>
					<c:if test="${managersign.isValid==0}">不合格</c:if>
					<c:if test="${managersign.isValid==1}">合格</c:if>
				</td>
				<td>
					<a href="#" onclick="updateIsValid(this,'${managersign.id}','${managersign.isValid}','${managersign.signErrorDistance}','${managersign.signId}')">修改</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	 <!-- 下达供应商弹框 安装模式产业 -->
	<div class="Black undis" id="supplierDateBoxNew" >
		<div class="tc_center">
			<h2 id="orderAddressNew"></h2>
			<div class="cen_t">
				<div class="cen_tex">
					<span class="span_l">签到误差：</span>
					<p class="span_r" id="signErrorDistance"></p>
				</div>
				<div class="cen_tex">
					<span class="span_l">签到状态：</span>
					<p class="span_r">
						<select id="isValidUpdate" style="width: 200px;">
						</select>
					</p>
				</div>
				<input id="dayOrderId" type="hidden"/>
				<input id="signId" type="hidden"/>
				<div class="cen_btn">
					<span class="btn_y" onclick="yesNew()">提交</span>
					<span class="btn_n" onclick="noNew()">取消</span>
				</div>
			</div>
		</div>
	</div> 
	<div class="pagination">${page}</div>
</body>
</html>