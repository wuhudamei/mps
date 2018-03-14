<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单明细查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

        $(document).ready(function() {
			var projectModeValue = "${applyCheckEarlyWarningQueryEntity.projectMode}";
			var storeId = "${applyCheckEarlyWarningQueryEntity.storeId}";
			var engineDepartId = "${applyCheckEarlyWarningQueryEntity.engineDepartId}";

            //根据门店个,工程模式    动态加载工程区域
			var html3 = "<option value=''></option>";
            $.ajax({

                url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
                + storeId + "&projectModeValue=" + projectModeValue,
                type : "get",
                success : function(data) {
                    var engineDepartObj =$("#engineDepartSelect");
                    if (null!= data && data.length > 0) {

                        for (var v = 0; v < data.length; v++) {

                            if(data[v].engineDepartId==engineDepartId){
                                html3 +='<option value="'+data[v].engineDepartId+'" selected="selected">'+data[v].engineDepartName+'</option>'
$(".select2-chosen").text(data[v].engineDepartName);
                            }else{
                                html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'

                            }

                        }

                        $(engineDepartObj).html(html3);
                    } else {
                        $(engineDepartObj).html(html3);
                    }

                }

            });
        });
		function page(n, s) {
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
		function goBack(){
			window.location.href="${ctx}/ApplyCheckEarlyEarningQuery/list?projectMode=${applyCheckEarlyWarningQueryEntity.projectMode}&storeId=${applyCheckEarlyWarningQueryEntity.storeId}"
		}



	</script>
</head>
<body>
	<input id="btnCancel" class="btn" type="button" value="返 回" onclick="goBack()"/>
	<br/>
	
	<form:form id="searchForm" modelAttribute="applyCheckEarlyWarningQueryEntity" action="${ctx}/ApplyCheckEarlyEarningQuery/delayOrderInfo" method="post" class="breadcrumb form-search">


		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input  name="storeId" type="hidden" value="${applyCheckEarlyWarningQueryEntity.storeId}"/>
		<input  name="projectMode" type="hidden" value="${applyCheckEarlyWarningQueryEntity.projectMode}"/>
		<input  name="nodeId" type="hidden" value="${applyCheckEarlyWarningQueryEntity.nodeId}"/>


            <li><label>区域：</label>
				<select id="engineDepartSelect" name="engineDepartId" class="input-medium needClear">


				</select>
			</li>
			<li><label>客户姓名：</label>  
				<form:input path="customerName" class="input-medium needClear"/>
             </li>
             <li class="btns"><input id="btnSubmit" class="btn btn-primary"	type="submit" value="查询" /></li>
            <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>

				<th>

					约检节点名称
				</th>

				<th>

					计划完成日期
				</th>

				<th>延期天数</th>

				<th>工程模式</th>

				<th>区域</th>


				<th>小区名称</th>


				<th>客户姓名</th>

				<th>项目经理</th>

				<th>质检员</th>

				<th>实际开工日期</th>


				<th>

					项目经理期望

					上门检查日期
				</th>

				<th>

					质检员上门

					签到日期
				</th>

				<th>

					质检员提交检查报告日期
				</th>

				<th>

					质检员提交

					确认验收日期
				</th>

			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="map">
			<tr>
				<td>
					${fns:getStoreLabel(map.storeId, '')}
				</td>
				<td>
					${map.checkNodeName}
				</td>
				<td>
					<fmt:formatDate value="${map.planEndDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${map.delayDaysCount}天
				</td>
				<td>
					${fns:getDictLabel(map.projectMode, 'project_mode', '')}
				</td>
				<td>
					${map.engineDepartName}
				</td>
				
				<td>
					${map.addressInfo}
				</td>

				<td>
					${map.customerInfo}
				</td>

				<td>
					${map.managerInfo}
				</td>
				<td>
					${map.pqcInfo}
				</td>
				<td>
					<fmt:formatDate value="${map.actualStartDate}" pattern="yyyy-MM-dd"/>

				</td>
				<td>
					<fmt:formatDate value="${map.actualStartDate}" pattern="yyyy-MM-dd"/>

				</td>
				
				<td>
						<fmt:formatDate value="${map.pqcSignDate}" pattern="yyyy-MM-dd"/>
				</td>

				<td>
						<fmt:formatDate value="${map.pqcCheckDate}" pattern="yyyy-MM-dd"/>
				</td>

				<td>
						<fmt:formatDate value="${map.pqcDoneDate}" pattern="yyyy-MM-dd"/>
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
<script type="text/javascript">

</script>
</html>