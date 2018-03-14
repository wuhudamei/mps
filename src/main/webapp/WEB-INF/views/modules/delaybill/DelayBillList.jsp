<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>延期单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		 $(document).ready(function() {

			 ajaxDelayreson($("#delayBillCategoryId"));
             ajaxDelayStage();
             var checkeds = '${delayBill.status}';
             if(checkeds != ''){
                 var v1 = checkeds.replace(/\s/g,'')
                 var checkArray =v1.split(",");
                 var checkBoxAll = $("input[type='checkbox']");
                 for(var i=0;i<checkArray.length;i++){
                     $("input[value="+checkArray[i]+"]").prop("checked",true);
                 }
                 var num = $("input[type=checkbox][name=status]").length;
                 if(checkArray.length == num){
                     $("#chkAll").prop("checked",true);
                 }

             }





		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

         // --全选框被单击---
         function ChkAllClick(sonName, cbAllId){
             var arrSon = document.getElementsByName(sonName);
             var cbAll = document.getElementById(cbAllId);
             var tempState=cbAll.checked;

             for(i=0;i<arrSon.length;i++) {
                 if(arrSon[i].checked!=tempState)
                     arrSon[i].click();
             }
             $("#"+cbAllId).prop("checked",tempState);
         }

         // --子项复选框被单击---
         function ChkSonClick(sonName, cbAllId) {
            /* var arrSon = document.getElementsByName(sonName);*/
             var cbAll = document.getElementById(cbAllId);
             var arrSon = $("input[type=checkbox][name="+sonName+"]");
             for(var i=0; i<arrSon.length; i++) {
                 if(!arrSon[i].checked) {
                     cbAll.checked = false;
                     return;
                 }
             }
             $("#"+cbAllId).prop("checked",true);
         }

		function tip(a){
			$("#myinputId").val(a);
			$("#refusespan").hide();
			$("#refuse").show();
			
		}
		function no3(){
			$("#rejectId").val("");
			$("#rejectedRemarks").val("");
			$("#refuse").hide();
			$("#refuseedit").hide();
			
		}
		function changeColour(a) {
			$.each($("#contentTable").find("td[style]"),function () {
                $(this).css("background-color","#f9f9f9");
            })
			$("td").removeClass("background-color");

			$.each($(a).find("td"),function () {
                $(this).css("background-color","orange");
            })
        }
		
		function yes3(){
			var v= $.trim($("#remarks").val())
			if(v == null || v == ""){
				$("#refusespan").show();
			}else{
				$("#myform").submit();
			}
		}
		
		
		
		function ajaxDelayreson(a){
			var temp = "${delayBill.delayBillCategoryIdReson}";
			$.ajax({
				url:"${ctx}/delayBill/ajaxreson",
				type:"post",
				data:{id:$(a).val()},
				dataType:"json",
				success:function(data){
					var s = "<option value=''></option>";
					$.each(data,function(name,value) {
					if(value.value == temp){
                           $("#s2id_delayBillCategoryIdReson").find(".select2-chosen").text(value.label);
                           s +='<option selected="selected" value="'+value.value+'">'+value.label+'</option>'
                     }else{
                    	   s += "<option value="+value.value+">"+value.label+"</option>"
                      }
					});
					
					$("#delayBillCategoryIdReson").html(s);
				}
			});
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
		   	height: 350px; 
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
	<script>
        function ajaxDelayStage(){
            var temp = "${delayBill.delayBillStageStatus}";
            var storeId= $("#storeId").val();
            var projectMode = $("#projectMode").val();
            if(storeId == '' || storeId == null || projectMode == '' || projectMode == null){
                return false;
			}
            $.ajax({
                url:"${ctx}/delayBill/ajaxDelayStage",
                type:"post",
                data:{storeId:storeId,projectMode:projectMode},
                dataType:"json",
                success:function(data){
                    var s = "<option value=''></option>";
                    $.each(data,function(name,value) {
                        if(value.value == temp){
                            $("#s2id_delayBillStageStatus").find(".select2-chosen").text(value.label);
                            s +='<option selected="selected" value="'+value.value+'">'+value.label+'</option>'
                        }else{
                            s += "<option value="+value.value+">"+value.label+"</option>"
                        }
                    });
                    $("#delayBillStageStatus").html(s);
                }
            });
        }

		function allChecked(a) {
            var ps = $(a).prop("checked");
           if(ps){
               $.each($("input[name=status]"),function () {
                   $(this).prop("checked",true);
               })

		   }else{
               $.each($("input[name=status]"),function () {
                   $(this).prop("checked",false);
               })

           }
        }

	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/purchase/bizPurchase/list">延期单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="delayBill" action="${ctx}/delayBill/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="ajaxDelayStage()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear" onchange="ajaxDelayStage()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>

			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			
			<li><label>延期阶段：</label>
				<form:select path="delayBillStageStatus" class="input-medium needClear">
					<form:option value="" label=""/>
					<%--<form:options items="${fns:getDictList('delayed_stage')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				</form:select>
			</li>
			<li><label>延期类别：</label>
				<form:select path="delayBillCategoryId" class="input-medium needClear" onchange="ajaxDelayreson(this)">
					<form:option value="" label=""/>
					<c:forEach items="${list }" var = "dict">
						<form:option value="${dict.value }">${dict.label }</form:option>
					</c:forEach>
				</form:select>
			</li>
			<li><label>延期原因：</label>
				<form:select path="delayBillCategoryIdReson" class="input-medium needClear">
				</form:select>
			</li>

			<li><label>状态：</label>
				<input name="chkAll" id="chkAll" title="全部" onclick="ChkAllClick('status','chkAll')" type="checkbox" />全部
			</li>
			<li>
				<c:forEach items="${fns:getDictList('delayed_approval_status')}" var="dict">
					<input type="checkbox" name="status" id="status" value="${dict.value}"  onclick="ChkSonClick('status','chkAll')" <c:if test="${fns:checkIsExits(selectOrder.orderStatusNumber,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出" onclick="checkExcel()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="cleanContext()"/></li>
			<li class="clearfix"></li>
			<script>
				function cleanContext() {
					$.each($("input[type=checkbox][name=status]"),function () {
						$(this).prop("checked",false);
                    })

                }

			</script>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>订单编号 </th>
				<th>项目经理 </th>
				<th>项目经理电话 </th>
				<th>客户姓名</th>
				<th>客户电话</th>
				<th>地址</th>
				<th>申请时间</th>
				<th>延期阶段</th>
				<th>延期类别</th>
				<th>延期原因 </th>
				<th>延期开始 </th>
				<th>延期结束</th>
				<th>延期天数</th>
				<th>延期证据</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="delay"  varStatus="status">
			<tr onclick="changeColour(this)">
				<td>${status.index +1 }</td>
				<td>
					${fns:getStoreLabel(delay.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(delay.projectMode,'project_mode','')}
				</td>
				<td>
					${delay.orderNumber }
				</td>
				<td>
					${delay.itemManager }
				</td>
				<td>
					${delay.itemManagerPhone }
				</td>
				<td>
					${delay.customerName }
				</td>
				<td>
					${delay.customerPhone }
				</td>
				<td>
					${delay.communityName }-${delay.buildNumber }-${delay.buildUnit }-${delay.buildRoom }
				</td>
				<td><fmt:formatDate value="${delay.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					${delay.delayBillStageStatusName }
				</td>
				<td>
					${delay.delayBillCategoryId }
				</td>
				<td>
					${delay.delayBillCategoryIdReson }
				</td>
				<td>
					${delay.delayBeginDatetime }
				</td>
				<td>
					${delay.delayEndDatetime }
					
				</td>
				<td>
					${delay.delayDays }天
				</td>
				<td>
					<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${delay.id }')">查看</a>
		
				</td>
				<td>
					${fns:getDictLabel(delay.status, 'delayed_approval_status', '')}
				</td>
				<td>
					<c:if test="${delay.status == '10' }">
						<a href="${ctx}\delayBill\pass?id=${delay.id }" onclick="return confirmx('确认要通过该延期单吗？', this.href)">通过</a>
						<a href="javascript:void(0);" onclick="edit(${delay.id},'${delay.communityName }-${delay.buildNumber }-${delay.buildUnit }-${delay.buildRoom }-${delay.customerName }','${delay.delayBeginDatetime }','${delay.delayEndDatetime }',${delay.delayDays })">编辑</a>
						<a href="javascript:void(0);" onclick="tip(${delay.id},2)">拒绝</a>
					</c:if>
					<a href="${ctx}/delayBill/detail?orderNumber=${delay.orderNumber}">详情</a>
					<c:if test="${delay.status == '20' }">
						<a onclick="delayBillInvalid(${delay.id },90)" href="javascript:void(0);">作废</a>
					</c:if>
				</td>
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
	                url: '${ctx}/delayBill/ajaxPhoto',
	                data: {
	                	id:installID,
	                	orderID:orderId
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

	        function delayBillInvalid(a,b) {

					var e = confirm("你确定要作废该延期单？");
                	if(e){
                        $.ajax({
                            url:"${ctx}/delayBill/delayBillInvalid",
                            type:"post",
                            data:{id:a,status:"90"},
                            success:function (data) {
                                $("#searchForm").submit();
                            }
                        })
					}
            }
    </script>
	
	
	
	
	
	
		<div class="Black undis" id="refuse">
		<div class="tc_center">
			<h2 id="orderAddress3"></h2>
			<div class="cen_t" align="center">
				<form action="${ctx}\delayBill\refuse" method="post" id="myform">
				<p style="font-size: 20px;font-weight: bold;border-bottom:1px solid #ddd;">延期单审批</p>
				<div class="cen_tex" id ="refusediv">
					<span style="padding-top: 15px;" class="span_l">拒绝理由：</span></br>
					<p class="span_r">
						<textarea style="width: 300px;height: 100px;border: 1px solid #0780ec;resize: none;" id="remarks" name="remarks" maxlength="200"></textarea>
					</p>
				</div>
				<span  id = "refusespan" style="color: red;display: none;text-align: center;">请填写拒绝理由!</span>
				<input id = "myinputId" name = "id" style="display: none;">
				<input id = "statusid" name = "status" style="display: none;">
				</form>
				<div style="margin-top:0;" class="cen_btn">
					<span class="btn_y" onclick="yes3()">提交</span>
					<span class="btn_n" onclick="no3()">取消</span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="Black undis" id="refuseedit">
		<div class="tc_center">
			<h2 id="orderAddress3"></h2>
			<div class="cen_t" align="center">
				<p style="font-size: 20px;font-weight: bold;border-bottom:1px solid #ddd;" id = 'myp'></p>
				<div class="cen_tex" id ="refusediv">
					<span style="font-weight: bold;">延期开始：</span>
						<input name="delayBeginDatetime" type="text" id="delayBeginDatetime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'delayEndDatetime\')}',isShowClear:false});" onchange="calculatedDate()"/></br>
					<span style="font-weight: bold;">延期结束：</span>
						<input name="delayEndDatetime" type="text" id="delayEndDatetime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'delayBeginDatetime\')}',isShowClear:false});" onchange="calculatedDate()"/></br>
					<span style="font-weight: bold;text-align: right;">延期天数：</span>
						<input name="deely" type="text" id="days" readonly="readonly" maxlength="20" class="input-medium"/>
					<input id ='myinputIdedit' type="hidden">
				</div>
				<div style="margin-top:0;" class="cen_btn">
					<span class="btn_y" onclick="yes4()">提交</span>
					<span class="btn_n" onclick="no3()">取消</span>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<script type="text/javascript">
	
		function yes4(){
			var a = $("#delayBeginDatetime").val();
			var b = $("#delayEndDatetime").val();
			var c = $("#days").val();
			var e = $("#myinputIdedit").val();
			 $.ajax({
	                url : "${ctx}/delayBill/update",
	                type : "post",
	                data : {delayBeginDatetime:a,delayEndDatetime:b,delayDays:c,id:e},
	                success : function(data) {
	                     $("#refuseedit").hide();
	                    $("#searchForm").submit();
	                }
	            });
			
		}
	
	
	
		function calculatedDate(){
			var a = $("#delayBeginDatetime").val();
			var b = $("#delayEndDatetime").val();
			var c = daysBetween(a,b) + 1;
			$("#days").val(c);
		}
		
		function daysBetween(DateOne,DateTwo){   
		    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
		    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
		    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  
		    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
		    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
		    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  
		    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000); 
		    return Math.abs(cha);  
		}
	
	
		function edit(a,b,c,d,e){
			$("#myp").text(b)
			$("#delayBeginDatetime").val(c);
			$("#delayEndDatetime").val(d);
			$("#days").val(e);
			$("#myinputIdedit").val(a);
			calculatedDate();
			$("#refuseedit").show();
		}
		
		function checkExcel(){
			var storeId = $("#storeId").val();
			var storeId1 = '${delayBill.storeId}';
			if(storeId != storeId1){

				alertx("请先查询在导出！")
				return false;
			}
			var projectMode = $("#projectMode").val();
			var projectMode1 = '${delayBill.projectMode}';

			if(projectMode != projectMode1){


				alertx("请先查询在导出！")
				return false;
			}
			
			var orderNumber = $("#orderNumber").val();
			var orderNumber1 = '${delayBill.orderNumber}';
			if(orderNumber != orderNumber1){

				alertx("请先查询在导出！")
				return false;
			}
			
			var customerName = $("#customerName").val();
			var customerName1 = '${delayBill.customerName}';
			if(customerName != customerName1){

				alertx("请先查询在导出！")
				return false;
			}


			
			var status = "";
            $.each($("input[name=status][type=checkbox]"),function () {
                if($(this).prop("checked")){
                    var temp = $(this).val()
                    if(typeof(temp) != "undefined"){
                        status += temp + ",";
                    }
                }


            })
            var status2 = status.substr(0,status.length - 1);
			var status1 = '${delayBill.status}';
			if(status2 != status1){
				alertx("请先查询在导出！")
				return false;
			}
			
			
			var itemManager = $("#itemManager").val();
			var itemManager1 = '${delayBill.itemManager}';
			if(itemManager != itemManager1){

				alertx("请先查询在导出！")
				return false;
			}
			
			var delayBillStageStatus = $("#delayBillStageStatus").val();
			var delayBillStageStatus1 = '${delayBill.delayBillStageStatus}';

			if(delayBillStageStatus != delayBillStageStatus1){

				alertx("请先查询在导出！")
				return false;
			}
			
			var delayBillCategoryId = $("#delayBillCategoryId").val();
			var delayBillCategoryId1 = '${delayBill.delayBillCategoryId}';
			if(delayBillCategoryId != delayBillCategoryId1){
				alertx("请先查询在导出！")
				return false;
			}

			var delayBillCategoryIdReson = $("#delayBillCategoryIdReson").val();
			var delayBillCategoryIdReson1 = '${delayBill.delayBillCategoryIdReson}';
			if(delayBillCategoryIdReson != delayBillCategoryIdReson1){
				alertx("请先查询在导出！")
				return false;
			}
			
			$("#searchForm").attr("action","${ctx}/delayBill/export")
			
			$("#searchForm").submit();
			
			$("#searchForm").attr("action","${ctx}/delayBill/list")
			
			alertx("正在导出，请稍等...")
		}
		
		
	
	</script>    
</body>
</html>