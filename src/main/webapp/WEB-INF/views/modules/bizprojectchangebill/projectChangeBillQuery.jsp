<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基装变更单查询页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var sta = '${bizProjectChangeBill.status}';
			if(sta != null && sta != ''){
				var checkArray =sta.split(",");
				var checkBoxAll = $(":checkbox[name=status]");
				for(var i=0;i<checkArray.length;i++){
					$(":checkbox[value="+checkArray[i]+"]").attr("checked",true);
				}
				//删除
				$(":checkbox[name=status]").each(function(){
					var status = $(this).val();
					if(status > 30){
						var id = $(this).attr("id");
						$(this).remove();
						$("[for="+id+"]").remove();
					}
				})
			}else{
				$(":checkbox[name=status]").each(function(){
					var status = $(this).val();
					if(status > 30){
						var id = $(this).attr("id");
						$(this).remove();
						$("[for="+id+"]").remove();
					}
					if(status <= 20){
						$(this).attr("checked",true);
					}
				})
			}
			
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
	<style>
		a{color:#2fa4e7;}
		.undis{display:none;}
		body {
		    background-color: #fff;
		    font-size: 16px;
		}
		
		body {
		    width: 100%;
		    height: 100%;
		    position: relative
		}
		
		.Black {
		    position: fixed;
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
		    width: 560px;
		   	height: 150px; 
		    margin-left: -300px;
		    margin-top: -210px;
		    background: #fff;
		    color: #666;
		    overflow-Y:scroll;
		}
		
		 .Black .tc_center h2 {
		    font-size: 20px;
		    text-align: center;
		    line-height: 40px
		}
		
		 .Black .tc_center .cen_t {
		    width: 100%;
		    margin-top:30px;
		}
		
		 .Black .tc_center .cen_t p {
		    line-height: 30px;
		    font-size: 14px;
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
		    /* position: absolute; */
		    width: 100%;
		    /* bottom: 20px; */
		    text-align: center;
		    line-height: 30px;
		    margin-top: 60px;
		}
		
		 .Black .tc_center .cen_t .cen_btn span {
		    width: 120px;
		    display: inline-block;
		    text-align: center;
		    cursor: pointer;
		    /*border-radius: 3px*/
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
		    line-height: 130px;
		    float: left;
		    width: 28%;
		    text-align: right;
		    vertical-align: middle;
		    font-weight: bold;
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r {
		    
		    text-align: left;
		    vertical-align: middle;
		    position: relative;
		    margin-bottom: 8px;
		    float: none;
    		overflow: hidden;
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r input {
		    width: 90%;
		    background: 0 0
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r textarea {
		    color: #666;
		    width: 90%;
		    height: 120px;
		    padding: 5px 15px;
		    line-height: 20px;
		    font-size: 12px;
		    border: #ccc solid 1px;
		    border-radius: 3px
		}
		
		 .Black .tc_close {
		    background: url(../images/close.png) no-repeat;
		    background-size: 100% 100%;
		    width: 50px;
		    height: 50px;
		    position: absolute;
		    top: -10px;
		    right: -12px
		}
		 .pic {
		    background: #fff;
		}
		
		 .pic textarea {
		    padding-left: 0.92rem;
		    width: 100%;
		    height: 8rem;
		    color: #333;
		    line-height: 2rem;
		    font-size: 1.4rem;
		}
		
		 .pic .span_pic{
			text-align:right;
			width: 18%;
			font-size: 16px;
			margin-bottom:5px;
		}
		 .pic p {
		    padding-left: 0.92rem;
		    padding-right: 0.92rem;
		    line-height: 2.75rem;
		    height: 2.75rem;
		    font-size: 1.5rem;
		    color: #cacaca;
		}
		
		 .pic p span {
		    padding: 2px;
		    float: right;
		    color: #bcbbbb;
		}
		
		 .pic .Fol {
		    height: 200px;
		    overflow: auto;
		    padding-right: 0.92rem;
		}
		
		 .pic .Fol img {
		    margin-bottom: 3px;
		    margin-right: 1px;
		    width: 24.5%;
		    height: 7rem;
		    float: left;
		}
		
		 .pic .Fol span {
		    display: inline-block;
		    width: 25%;
		    height: 7rem;
		    background: url(../images/zhaop_06.png) no-repeat;
		    background-size: 100% 100%;
		}
		
		 .pic .Fol span .Up {
		    width: 100%;
		    height: 100%;
		    opacity: 0;
		}
		.Big {
		    position: absolute;
		    top: -112px;
		    left: 50%;
		    margin-left: -200px;
		    width: 400px !important;
		    height: 300px !important;
		    z-index: 99;
		}
		.black_small{
		    display:none;
		    position:absolute;
		    top: 0;
		    left:0;
		    width: 100%;
		    height: 100%;
		    background:rgba(0,0,0,0.9)
		
		}
		.black_small img{
		    position:absolute;
		    top: 8%;
		    left: 10%;
		    width: 80%;
		    height: 75%;
		}
		.black_small .cen_btn{
		    width: 100%;
		    position:absolute;
		    bottom:5%;
		    left:15px;
		    text-align:center;
		}
		.black_small .cen_btn span {
		    width: 120px;
		    display: inline-block;
		    line-height:30px;
		    text-align: center;
		    cursor: pointer;
		    border-radius: 3px
		}
		
		.black_small .btn_y {
		    background: #3da5ee;
		    color: #fff
		}
		
		.black_small .btn_n {
		    margin-left: 50px;
		    border: solid 1px #3da5ee;
		    color: #3da5ee
		}
		.del_img{
		    position: absolute;
		    bottom: 5%;
		    right: 50%;
		    width: 3rem;
		    height: 3rem;
		    margin-right: -1.5rem;
		}


		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 380px;border-radius: 4px;background: #fff;font-size: 16px;
		    position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		.maskBtn{display: block;width: 60%;text-align: center;line-height: 38px;}
		.maskBtn{background: #0780ec;border-radius: 4px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16px;margin: 0 auto;}
    		
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizprojectchangebill/bizProjectChangeBill/queryAllChangeBill">基装变更单查询页面</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizProjectChangeBill" action="${ctx}/bizprojectchangebill/bizProjectChangeBill/queryAllChangeBill" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			 <li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
				<form:option value=""></form:option>
				<c:forEach items="${storeList}" var = "store">
					<form:option value="${store.value }">${store.label }</form:option>
				</c:forEach>
			</form:select>
			</li>
			
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>审计员：</label>
				<form:input path="auditCheckBy" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			
			<li><label>客户地址：</label>
				<form:input path="communityName" htmlEscape="false" maxlength="16" class="input-medium"/>
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
			
			<li><label style="width: 150px;">基装变更单状态：</label>
				<form:checkboxes items="${fns:getDictList('change_single_status')}" path="status" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>区域</th>
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>提报日期</th>
				
				<!-- <th>设计师</th>
				<th>设计师审核通过日期</th>
				<th>审计师</th>
				<th>审计审核通过日期</th> -->
				<th>设计师和审计员</br>审核通过时间</th>
				
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
					${bizProjectChangeBill.storeName }
					<%-- ${fns:getStoreLabel(bizProjectChangeBill.storeId, '')} --%>
				</td>
				<td>
					${fns:getDictLabel(bizProjectChangeBill.projectMode, 'project_mode', '')}
				</td>
				<td>
					${fns:getElacLabel(bizProjectChangeBill.engineDepartId, '')}
				</td>
				<td>
					${bizProjectChangeBill.orderNumber}
				</td>
				<td>${bizProjectChangeBill.customerName}</td>
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
				
				<%-- <td>
					${bizProjectChangeBill.designerName}
				</td>
				<td>
					<fmt:formatDate value="${bizProjectChangeBill.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
					${bizProjectChangeBill.auditCheckBy}
				</td>
				<td>
					<fmt:formatDate value="${bizProjectChangeBill.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				
				<td align="center"><a href="javascript:void(0);" onclick="tip(${bizProjectChangeBill.id})">查看</a></td>
				
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
    				<c:if test="${bizProjectChangeBill.status=='40'}">
	    				<a href="${ctx}/bizprojectchangebill/bizProjectChangeBill/approvePass?projectChangeId=${bizProjectChangeBill.projectChangeId}&status=${bizProjectChangeBill.status}" onclick="return confirmx('确认要存档吗？', this.href)">存档</a>
    				</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
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
	
	
	
	
	
	<div class="Black undis" id="refuse">
		<div class="tc_center">
			<h2 id="orderAddress3"></h2>
			<div class="cen_t" align="center">
				<table class="table table-striped table-bordered table-condensed" id = "mytable">
					<thead>
						<th>设计师</th>
						<th>设计师审核通过日期</th>
						<th>审计师</th>
						<th>审计审核通过日期</th>
					</thead>
					<tbody id="mybody">
						
					</tbody>
				
				</table>
				
				<div style="margin-top:0;" class="cen_btn">
					<!-- <span class="btn_y" onclick="yes3()">提交</span> -->
					<span class="btn_n" onclick="no3()">返回</span>
				</div>
			</div>
		</div>
	</div>  
	<script type="text/javascript">
		function no3(){
			$("#refuse").hide();	
		}
		function tip(a){
			var html = '';
			$.ajax({
				url:"${ctx}/bizprojectchangebill/bizProjectChangeBill/findDetail",
				type:"post",
				data:{id:a},
				dataType:"json",
				success:function(data){
					$.each(data,function(name,value){
						 html += "<th>"+value.designerName+"</th><th>"+value.checkDate+"</th>"
					});
					$("#mytable tbody").html(null);
					$("#mybody").append(html);
					$("#refuse").show(); 
				}
			});
			
			
			 
		}
	
	</script>
</body>
</html>