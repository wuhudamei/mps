<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审计员审核管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizprojectchangebill/bizProjectChangeBill/list2">审计员审核列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizProjectChangeBill" action="${ctx}/bizprojectchangebill/bizProjectChangeBill/budgeterList2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<%-- <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if> --%>
				<form:select path="storeId" class="input-medium needClear">
						<form:option value=""></form:option>
						<c:forEach items="${storeList}" var = "store">
							<form:option value="${store.value }">${store.label }</form:option>
						</c:forEach>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>设计师：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>提报日期 ：</label>
				<input name="beginApplyDate" id="beginApplyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizProjectChangeBill.beginApplyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endApplyDate\')}',isShowClear:false});"/> - 
				<input name="endApplyDate" id="endApplyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizProjectChangeBill.endApplyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginApplyDate\')}',isShowClear:false});"/>
			</li>
			<li><label>状态 ：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:option value="20" label="">设计师审核通过</form:option>
					<form:option value="25" label="">审计员审核不通过</form:option>
					<form:option value="30" label="">审计员审核通过</form:option>
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
				
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>提报日期</th>
				<th>设计师</th>
				<th>设计师审核通过日期</th>
				<th>增项金额</th>
				<th>减项金额</th>
				<th>状态</th>
				<th>客户签字凭证图片</th>
				<shiro:hasPermission name="bizprojectchangebill:bizProjectChangeBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizProjectChangeBill">
			<tr>
				<td>
					<%-- ${fns:getStoreLabel(bizProjectChangeBill.storeId, '')} --%>
					
					${bizProjectChangeBill.storeName}
				</td>
				<td>
					${fns:getDictLabel(bizProjectChangeBill.projectMode, 'project_mode', '')}
				</td>
				
				<td>
					${bizProjectChangeBill.orderNumber}
					
				</td>
				<td>
					${bizProjectChangeBill.customerName}
				</td>
				<td>
					${bizProjectChangeBill.communityName}-
					${bizProjectChangeBill.buildNumber}-
					${bizProjectChangeBill.buildUnit}-
					${bizProjectChangeBill.buildRoom}
					
				</td>
				<td>
					${bizProjectChangeBill.itemManager}
				</td>
				<td>
					<fmt:formatDate value="${bizProjectChangeBill.applyDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizProjectChangeBill.designerName}
				</td>
				<td>
					<fmt:formatDate value="${bizProjectChangeBill.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
					${bizProjectChangeBill.addItemTotalPrice}
				</td>
				<td>
					${bizProjectChangeBill.subItemTotalPrice}
				</td>
				<td>
					${fns:getDictLabel(bizProjectChangeBill.status, 'change_single_status', '')}
				</td>
				<td>
<%-- 					<a href="javascript:void(0)" onclick="window.open('${ctx}/bizprojectchangebill/bizProjectChangeBill/querySignaturePic?recheckID=${bizProjectChangeBill.projectChangeId}&type=105')"> --%>
<!-- 						查看 -->
<!-- 					</a> -->
				<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${bizProjectChangeBill.projectChangeId}','105')">查看</a>
		
					
				</td>
				<shiro:hasPermission name="bizprojectchangebill:bizProjectChangeBill:edit"><td>
    				<a href="${ctx}/bizprojectchangebill/bizProjectChangeBill/details?projectChangeId=${bizProjectChangeBill.projectChangeId}">详情</a>
    				<c:if test="${bizProjectChangeBill.status=='20'}">
	    				<a href="${ctx}/bizprojectchangebill/bizProjectChangeBill/approvePass?projectChangeId=${bizProjectChangeBill.projectChangeId}&status=${bizProjectChangeBill.status}" onclick="return confirmx('确认要通过吗？', this.href)">通过</a>
						<a href="#" onclick="reject('${bizProjectChangeBill.id}')">驳回</a>
    				</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="g-mask undis" id="refuseSalary">
		<div id="g-done_ask">
			<p class="refuse">请输入驳回理由：</p>
			<textarea class="content" id="reason"></textarea>
			<p class="second">
				<a href="javascript:void(0)" onclick="noReject()">取消</a>
				<a href="javascript:void(0)" onclick="yesReject()">确认</a>
			</p>
		</div>
	</div>
	<div class="pagination">${page}</div>
	
		
	<link href="${ctxStatic}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctxStatic}/vendor/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/vendor/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet">
    <link href="${ctxStatic}/modules/popUp/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/modules/popUp/css/swiper.css" rel="stylesheet" type="text/css" />
	
	
	
    <script type="text/javascript" src="${ctxStatic}/vendor/jquery/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/vendor/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${ctxStatic}/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${ctxStatic}/vendor/jquery/swiper.min.js"></script>
    
    
		
	    <!-- 照片弹层 -->
    <div class="modal _large fade disN" id="myModal-photo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                    <h4 class="modal-title text-center" id="myModalLabel">图片预览</h4>
                </div>
                <div class="modal-body">
                    <div class="swiper-container swiper-item01" id="swiper-item01">
                        <div class="swiper-wrapper clearfix" id="inputId">
<!--                             <a href="http://www.baidu.com" class="swiper-slide img-01"></a> -->
                    
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-pagination swiper-pagination-fraction">
                            <span class="swiper-pagination-current">1</span> / <span class="swiper-pagination-total">10</span>
                        </div>
                    </div>
                    <!--/.swiper-container-->
                </div>
            </div>
        </div>
    </div>
	
	
	

	    <script type="text/javascript">
        $("#myModal-manage").on("shown.bs.modal", function() {
            $(".form_datetime2").datetimepicker({
                format: "dd MM yyyy - hh:ii"
            });
        });

        $("#myModal-photo").on("shown.bs.modal", function() {
            var mySwiper01 = new Swiper('#swiper-item01', {
//                 autoplay: 5000, //可选选项，自动滑动
                speed: 800,
                loop: true, //可选选项，开启循环
                paginationClickable: true,
                nextButton: '.swiper-button-next',
                prevButton: '.swiper-button-prev',
                pagination: '.swiper-pagination',
                paginationType: 'fraction'
            });
        });
		
				 function ajaxGetImgs(installID,orderId){
				 // 	 alert(installID+":"+orderId);
	            $.ajax({
	                type: 'POST',
	                dataType: 'json',
	                url: '${ctx}/bizprojectchangebill/bizProjectChangeBill/ajaxQuerySignaturePic',
	                data: {
	                	recheckID:installID,
	                	type:orderId
	                },
	                success: function (data) {
						
						  	var modelList = data.mapObject;
		                    if (null!==modelList && modelList.length !== 0) {
// 		                        '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
		                        var ainput='';
		                        for (var i = 0; i < modelList.length; i++) {
		                         ainput +=	 '<a class="swiper-slide " ><img src="' + modelList[i].picUrl + '" ></a>';
		                        }
		                        $("#inputId").html(ainput);
		                    }else{
		                        var ainput='';
		                         ainput ='<a class="swiper-slide " ><img src="${ctxStatic}/images/nopic.jpg" ></a>';
		                        $("#inputId").html(ainput);
	                    }
						
		    			
	                }
	            })
	        	
	        }
		
    </script>
	
	
	
	
	
	
	<script type="text/javascript">
		var projectChangeId;
		
		function reject(id){
			projectChangeId = id;
			$("#refuseSalary").removeClass("undis");
		}
		function yesReject(){
			var reason = $("#reason").val();
			$("#reason").val("");
			$("#refuseSalary").addClass("undis");
			if(reason!=null && reason!=""){
				window.location.href = "${ctx}/bizprojectchangebill/bizProjectChangeBill/refusePass?projectChangeId="+projectChangeId+"&reason="+reason+"&status=20";
			}else{
				alert("驳回原因不能为空");
			}
		}
		function noReject(){
			$("#reason").val("");
			$("#refuseSalary").addClass("undis");
		}
	
	</script>
</body>
</html>