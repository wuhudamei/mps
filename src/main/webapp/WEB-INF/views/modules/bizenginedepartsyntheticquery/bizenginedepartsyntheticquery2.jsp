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

		    $("tbody tr:not(:last) ").each(function(){

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

                $("#totalId").next().text("");
            })

//			if($("#contractDelayId").prev().text()!="" && $("#contractDelayId").prev().prev().text()!=""){
//                $("#contractDelayId").text(($("#contractDelayId").prev().text()/$("#contractDelayId").prev().prev().text()).toFixed(2)*100+"%");
//
//
//
//            }
//            if($("#sixtyId").prev().text()!="" && $("#sixtyId").prev().prev().text()!=""){
//                $("#sixtyId").text(($("#sixtyId").prev().text()/$("#sixtyId").prev().prev().text()).toFixed(2)*100+"%");
//
//            }
//            if($("#fortyId").prev().text()!="" && $("#fortyId").prev().prev().text()!=""){
//
//                $("#fortyId").text(($("#fortyId").prev().text()/$("#fortyId").prev().prev().text()).toFixed(2)*100+"%");
//            }



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
			var queryDate1 = $("#startJspId").val();
			var queryDateId = $("#beginCreateDate").val();

			if(queryDateId != queryDate1){
				alertx("请先查询后，再导出");
				return;
			}
            var queryDate2 = $("#endJspId").val();
            var queryDateId3 = $("#endCreateDate").val();

            if(queryDate2 != queryDateId3){
                alertx("请先查询后，再导出");
                return;
            }
              loading("正在为您导出所有数据...请稍等....")
            setTimeout("closeTip()",5000);

			$("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/exportExcel");
			$("#searchForm").submit();
            $("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/smallStatisticsList");
		}

		function searchList(){
			var storeId = $("#storeId").val();
			if(null == storeId || storeId <1){
				alertx("请先选择门店后，再查询");
				return;
			}


            var queryDateId = $("#beginCreateDate").val();

            if(queryDateId ==undefined ||queryDateId==""){
                alertx("请先选择日期");
                return;
            }

            var queryDateId3 = $("#endCreateDate").val();

            if(undefined == queryDateId3||queryDateId3==""){
                alertx("请先选择日期");
                return;
            }



      	 	 loading("正在为您查询所有数据...请稍等....")


			$("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/smallStatisticsList");
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
		<li class="active"><a href="${ctx}/engineDepartSyntheticQuery/smallstatisticsPrelist">工程部统计查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="engineDepartSyntheticQueryEntity"  method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<!-- 日期 -->
		<input id="startJspId" type="hidden" value="<fmt:formatDate value="${entity.start}" pattern="yyyy-MM-dd"/>">
		<input id="endJspId" type="hidden" value="<fmt:formatDate value="${entity.end}" pattern="yyyy-MM-dd"/>">

		<ul class="ul-form">
			<li><label>门店：</label>

					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>

			</li>
			<li><label>日期：</label>
				<input name="start" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${entity.start}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:true});"/> 至
				<input name="end" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${entity.end}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:true});"/>
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
				<th>门店</th>


				<th>大区 </th>

				<th>接单数</th>
				<th>派单数</th>



				<th>设计交底</th>
				<th>确认开工</th>





			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="enginedepart">
			<tr>
				<td>${enginedepart.storeName}</td>
				<td>${enginedepart.engineDepartName}</td>
				<td>${enginedepart.accpetOrderCount}</td>
				<td>${enginedepart.discloseOrderCount}</td>
				<td>${enginedepart.distributeOrderCount}</td>
				<td>${enginedepart.contractStartDayCount}</td>
			</tr>
		</c:forEach>
			<tr>
				<td id="totalId">合计</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div  class="table table-striped table-bordered table-condensed">
		<b style="font-size: larger">说明: </b>
		<br/>
				<b>接单数：</b>
				 各大区查询日期内接受订单数量总和，以“接单日期”为准<br/>
				<b>派单数：</b>
				 各大区查询日期内分配项目经理的订单数量总和，以“派单日期”为准，不含重派次数<br/>
				<b>	设计交底：</b>
				各大区查询日期内项目经理设计交底的订单信息总和，以“实际交底日期”为准<br/>
				<b>	确认开工：</b>
				各大区查询日期内项目经理确认开工的订单数量总和，以“实际开工日期”为准<br/>
	</div>


</body>
</html>