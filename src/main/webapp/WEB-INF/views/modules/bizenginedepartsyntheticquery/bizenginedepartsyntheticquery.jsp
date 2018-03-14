<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程部统计查询</title>
	<meta name="decorator" content="default"/>
	<%--<link rel="stylesheet" type="text/css" href="/static/modules/css/xcConfirm.css"/>
	<script src="/static/modules/js/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="/static/modules/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>--%>
	<script type="text/javascript">


            $(document).ready(function() {

		    $("tbody tr:not(:last)").each(function(){

                var tdList = $(this).children("td");
                for (var i=1;i<tdList.length;i++) {
                    var tdArr = tdList.eq(i);
                    $("tbody tr:last td").each(function(){

                        if($(this).index()==i){

                            if($(tdArr).text()==""){
                                $(tdArr).text(0);

							}

                          /*  alert(parseInt($(this).text()==""?0:$(this).text()));*/
                          /*  alert(parseInt($(this).text())==NaN?0:parseInt($(this).text()));*/
                        /*  alert(parseInt($(tdArr).text())+parseInt($(this).text())==NaN?0:parseInt($(this).text()))*/
                      $(this).text(parseInt($(tdArr).text())+parseInt($(this).text()==""?0:$(this).text()));


                        }


                    })

                }
            })

			if($("#contractDelayId").prev().text()!="" && $("#contractDelayId").prev().prev().text()!=""){
                $("#contractDelayId").text(($("#contractDelayId").prev().text()/$("#contractDelayId").prev().prev().text()).toFixed(2)*100+"%");



            }
            if($("#sixtyId").prev().text()!="" && $("#sixtyId").prev().prev().text()!=""){
                $("#sixtyId").text(($("#sixtyId").prev().text()/$("#sixtyId").prev().prev().text()).toFixed(2)*100+"%");

            }
            if($("#fortyId").prev().text()!="" && $("#fortyId").prev().prev().text()!=""){

                $("#fortyId").text(($("#fortyId").prev().text()/$("#fortyId").prev().prev().text()).toFixed(2)*100+"%");
            }



        });

        /*
                        var text=0;
                        $(this).each(function(){

                            if($(this).index()!=0){
                                text+=parseInt($(this).text());
                            }
                        })
                        alert(text);
                        text=0;*/
           /*
	*/


			 /*	$("tbody tr td").each(function(){*/

			 	/*if($(this).text().isNumeric()){
			 	    total+=$(this).child().text();



				}
*/

			/*	})*/
			/*	$(this).text(total);*/





		
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

		
		function exportExcel(){
			var storeId = $("#storeId").val();
			if(null == storeId || storeId == ""){
				alertx("请先选择门店后，再导出");
				return;
			}
			// 判断门店
			if($("#storeId").val() != null && '${entity.storeId}' != $("#storeId").val()){
				alertx("请先查询后，再导出");
				return;
			}
			//日期  开始
			var queryDate1 = $("#beginActualStartDate1").val();
			var queryDateId = $("#queryDateId").val();

			if(queryDateId != queryDate1){
				alertx("请先查询后，再导出");
				return;
			}
              loading("正在为您导出所有数据...请稍等....")
            setTimeout("closeTip()",5000);

			$("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/exportInspectorFineMoneyDetailToExcel");
			$("#searchForm").submit();
            $("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/list");
		}

		function searchList(){
			var storeId = $("#storeId").val();
			if(null == storeId || storeId <1){
				alertx("请先选择门店后，再查询");
				return;
			}

      	 	 loading("正在为您查询所有数据...请稍等....")


			$("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/list");
			$("#searchForm").submit();
		}
	
	</script>
	<style>

		.tooltips{ border-width: 1px; border-style: solid; position: absolute; display: none; border-radius: 3px; opacity: 0; filter:alpha( opacity = 0) ; z-index: 999;}
		.tooltips p.content{ padding: 5px; }
		.tooltips .triangle-front,.tooltips .triangle-back{ width: 0; height: 0; overflow: hidden; border-width: 8px; border-style: solid; position: absolute; border-color: transparent ; top: 100%; left: 50%; margin-left: -8px;}
		.tooltips .triangle-back{ margin-top: -1px;}

		.yellow{ border-color: #c7c08d; background-color: #fffac3;}
		.yellow .triangle-front{ border-top-color: #c7c08d;}
		.yellow .triangle-back{ border-top-color: #fffac3;}



	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/engineDepartSyntheticQuery/prelist">工程部统计查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="engineDepartSyntheticQueryEntity"  method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<!-- 日期 -->
		<input id="beginActualStartDate1" type="hidden" value="<fmt:formatDate value="${entity.queryDateForJsp}" pattern="yyyy-MM-dd"/>">

		<ul class="ul-form">
			<li><label>门店：</label>

					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>

			</li>
			<li><label>日期：</label>
				<input name="queryDate" id="queryDateId"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${entity.queryDateForJsp}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="searchList()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出Excel" onclick="exportExcel()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>大区</th>




				<th title="分配给各大区的所有订单数量 不跟随查询时间变化">接单数-累计<i class="icon-question-sign"></i></th>
				<th title=	"各大区接单时间为查询时间的订单数量">接单数-当天 <i class="icon-question-sign"> </i></th>



				<th title="各大区已分配项目经理的订单数 不跟随查询时间变化">派单数-累计 <i class="icon-question-sign"> </i></th>
				<th title="各大区分配项目经理时间  重新分配项目经理不算  为查询时间的订单数量">派单数-当天 <i class="icon-question-sign"> </i></th>



				<th title="各大区订单状态为项目经理已设计交底的数量  不跟随查询时间变化 ">设计交底-累计 <i class="icon-question-sign"> </i></th>
				<th title="各大区订单状态为  项目经理设计交 的时间为查询时间的订单数量">设计交底-当天 <i class="icon-question-sign"> </i></th>



				<th title="各大区订单状态为确认开工的数量  不跟随查询时间变化">实际开工数-累计 <i class="icon-question-sign"> </i></th>
				<th title="各大区订单状态为项目经理已确认开工  的时间  为查询时间的订单数量">实际开工数-当天 <i class="icon-question-sign"> </i></th>




				<th title="各大区订单状态为  项目经理已确认开工(包含)  至订单状态为 项目经理申请竣工(不包含)">在建工地数 <i class="icon-question-sign"> </i></th>




				<th title="第40天基装验收 基装施工中：订单状态为（等于200-施工中并且未质检基装验收通过）的订单数据">基装施工中 <i class="icon-question-sign"> </i></th>
				<th title="基装延期数量：订单状态为（等于200-施工中）>40天并且未质检基装验收通过的订单数量  如果已质检基装验收的订单不算在其中">基装延期数 <i class="icon-question-sign"> </i></th>
				<th title="基装延期占比：基装延期数量/基装施工中数量*100%">基装延期占比 <i class="icon-question-sign"> </i></th>




				<th title="主材施工中：约检状态为质检基装验收通过且订单状态未（320--质检竣工审核通过）的订单数据">主材施工中 <i class="icon-question-sign"> </i></th>
				<th title="主材延期数量：确认开工日期+60天>约检状态为质检竣工验收日期的订单数据  如果已质检竣工验收审核通过的订单不算在其中">60天工期延期数 <i class="icon-question-sign"> </i></th>
				<th title="主材延期数量/主材施工中数量*100%">60天工期延期占比 <i class="icon-question-sign"> </i></th>



				<th title="订单状态为（200-施工中（大于等于）且未320质检竣工审核通过）订单数量">合同约定工期-施工中 <i class="icon-question-sign"> </i></th>
				<th title="当前日期-确认开工日期>订单合同工期">合同约定工期-延期数 <i class="icon-question-sign"> </i></th>
				<th title="延期订单数/施工中订单数量*100%">合同约定工期-延期占比 <i class="icon-question-sign"> </i></th>

			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="enginedepart">
			<tr>
				<td>${enginedepart.engineDepartName}</td>




				<td>${enginedepart.accpetOrderCount}</td>
				<td>${enginedepart.accpetOrderCountNow}</td>





				<td>${enginedepart.distributeOrderCount}</td>
				<td>${enginedepart.distributeOrderCountNow}</td>




				<td>${enginedepart.discloseOrderCount}</td>
				<td>${enginedepart.discloseOrderCountNow}</td>





				<td>${enginedepart.actualStartOrderCount}</td>
				<td>${enginedepart.actualStartOrderCountNow}</td>





				<td>${enginedepart.buildOrderIndustryCount}</td>






				<td>${enginedepart.basicDoneCount}</td>
				<td>${enginedepart.basicDelayCount}</td>
				<td>${enginedepart.basicDelayPercent}</td>





				<td>${enginedepart.mainMaterialStartCount}</td>
				<td>${enginedepart.mainMaterialDaysDelayCount}</td>
				<td>${enginedepart.mainMaterialDelayPercent}</td>





				<td>${enginedepart.contractStartDayCount}</td>
				<td>${enginedepart.contractStartDayDelayCount}</td>
				<td>${enginedepart.contractStartDelayPercent}</td>


			</tr>
		</c:forEach>
			<tr>
				<td>合计</td>





				<td></td>
				<td></td>




				<td></td>
				<td></td>






				<td></td>
				<td></td>





				<td></td>
				<td></td>





				<td></td>






				<td></td>
				<td></td>
				<td id="fortyId"></td>





				<td></td>
				<td></td>
				<td id="sixtyId"></td>




				<td></td>
				<td></td>
				<td id="contractDelayId"></td>




			</tr>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>