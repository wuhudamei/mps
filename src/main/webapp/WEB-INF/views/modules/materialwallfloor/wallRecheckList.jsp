<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>墙地砖复尺管理设计师</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/modules/popUp/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${ctxStatic}/modules/popUp/css/swiper.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctxStatic}/vendor/jquery/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/vendor/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctxStatic}/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${ctxStatic}/vendor/jquery/swiper.min.js"></script>
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
		<li class="active"><a href="${ctx}/materialwallfloor/wallRecheck/">墙地砖复尺列表</a></li>
	<%-- <shiro:hasPermission name="materialwallfloor:wallRecheck:edit"><li><a href="${ctx}/materialwallfloor/wallRecheck/form">墙地砖复尺添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="wallRecheck" action="${ctx}/materialwallfloor/wallRecheck/" method="post" class="breadcrumb form-search">
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
				<form:input path="itemManager" htmlEscape="false" class="input-medium needClear" />
			</li>
			<li><label>设计师：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="1000" class="input-medium needClear" />
			</li>

			<li><label>是否作废：</label>
			
			<form:select path="isScrap"    class="input-medium needClear" >
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

			<li><label>期望复核日期：</label>
				<input name="beginPromiseComDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${wallRecheck.beginPromiseComDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endPromiseComDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${wallRecheck.endPromiseComDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>序号</th>
				<th>门店</th>
				<th>区域</th>
				<th>工程模式</th>
				<th>项目经理</th>
				<th>设计师</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>期望复核日期</th>
				<th>实际测量日期</th>
				<th>预算面积</th>
				<th>实际下单面积</th>
				<th>实测面积</th>
				<th>实测照片</th>
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
					${wallRecheck.itemManager}
				</td>
				<td>
					${wallRecheck.designerName}
				</td>
				<td>
					${wallRecheck.orderNmber}
				</td>
				<td>
				${wallRecheck.coveredAdd}
				</td>

				<td>
					<fmt:formatDate value="${wallRecheck.planMeasureDate}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${wallRecheck.realMeasureDate}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					${wallRecheck.squareBudget}
				</td>
				<td>
					${wallRecheck.squarePurchase}
				</td>
				<td>
					${wallRecheck.squareMeasure}
				</td>
		
				<td>
				<a href="#" data-toggle="modal" data-target="#myModal-photo" onclick="ajaxGetImgs('${wallRecheck.id}')">
				查看图片
				</a>
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
				<c:if test="${wallRecheck.status==10}">
    				<a href="${ctx}/materialwallfloor/wallRecheck/agreRecheck?id=${wallRecheck.id}">同意复尺</a></br>
    				<a href="javascript:void(0)" onclick="abandonedAgre ('${wallRecheck.id}')" >认可</a>
				</c:if>
				<c:if test="${wallRecheck.status==50}">
    				<a href="${ctx}/materialwallfloor/wallRecheck/agreRecheckUpdate2?id=${wallRecheck.id}">同意复尺结果</a></br>
    				<a href="${ctx}/materialwallfloor/wallRecheck/NotagreRecheckUpdate2?id=${wallRecheck.id}">不同意</a>
				</c:if>
				<c:if test="${wallRecheck.status==55}">
    				<a href="${ctx}/materialwallfloor/wallRecheck/agreRecheckUpdate2?id=${wallRecheck.id}">同意复尺结果</a></br>
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
										<textarea id="resonReject" class="mask-text"  onkeyup="this.value = this.value.substring(0,200)" placeholder="请输入认可内容，多行输入，不多于200个汉字" maxlength="200" ></textarea>
										<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandonedReject()" >确定</a>
										<a href="javascript:void(0)" class="btn" onclick="onclickNoAbandonedReject()">关闭</a>
									</div>
									</div>
									</form:form>
		</div>
	<script type="text/javascript">
        function ajaxGetImgs(id){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/materialwallfloor/wallRecheck/querypic',
                data: {
                    id:id
                },
                success: function (data) {
                    var modelList = data.mapObject;
                    if (null!==modelList && modelList.length !== 0) {
//		                        '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
                        var ainput='';
                        for (var i = 0; i < modelList.length; i++) {
                            ainput +=	 '<a  class="swiper-slide " ><img src="' + modelList[i].picUrl + '" ></a>';
                        }
                        $("#inputId").html(ainput);
                    }else{
                        var ainput='';
                        ainput ='<a  class="swiper-slide " ><img src="${ctxStatic}/images/nopic.jpg" ></a>';
                        $("#inputId").html(ainput);
                    }
                }
            })

        }
	</script>
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
	</script>
</body>
</html>