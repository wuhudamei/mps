<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>分配工人组</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/popUp/css/base.css"/> 
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#onCreateTaskpackage").hide();
		
		
	});
	function page(n, s) {

		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm1").submit();
		return false;
	}

	<c:if test='${!empty isRedistribute}'>
	function yes(){

		//工人组id
		var workerGroupId = $("#workerId").val();
		
		var startDate =  $('#startDate').val();
		
		var endDate = $('#endDate').val();
		
		var reasonRemarks =$("#reasonRemarks").val();
		
		var reasonType = $("#reasonType").val();
		
		if(reasonType=='-1'){
			alert("原因必须填写、说明允许为空");
			return;
		}
		
		if(endDate==undefined ||endDate == "" ||startDate ==undefined || startDate == ""){
			alertx("请调整任务包计划时间");
			return;
		}
            loading("正在分配该任务包给"+ $('input:radio:checked').parent().next().text()+"...请稍后")
            //是否重新分配
			var isReDistribute = "${isRedistribute}";

			//待派工
            var turnpageflag = "${turnpageflag}";
			
			window.location.href = "${ctx}/AllotWorkerGroup/allotWorkerGroup/allotWorker?packageId="
					+ "${taskPackage.id}" + "&id=" + workerGroupId+"&startDate="+startDate+"&endDate="+endDate+"&isReDistribute="+isReDistribute+"&turnpageflag="+turnpageflag+"&reasonRemarks="+reasonRemarks+"&reasonType="+reasonType;
			
			closeTip();
		}
	
	function no (){
		$("#reasonType").val("");
		$("#reasonRemarks").val("");
		$("#onCreateTaskpackage").hide();	
	}
	
	function allotWorkerGroup(workerGroupId) {
		
		//得到选框
		/* if ($('input:radio:checked').val() == undefined){

			alertx("请选择要分配的工人组..");
		} else { */

			//工人组id
	/* 	var workerGroupId = $('input:radio:checked').val(); */
			
			$("#workerId").val(workerGroupId);
			if('${groupId}'==workerGroupId){
				alertx("目前任务包的工人组已是"+'${groupName}'+",不允许重新派相同的工人组");
				return;
			}
			
		$("#onCreateTaskpackage").show();
			
		/* } */
	}
	</c:if>
	
	<c:if test='${empty isRedistribute}'>
	
		function allotWorkerGroup(workerGroupId) {

			//得到选框
		/* 	if ($('input:radio:checked').val() == undefined){
	
				alertx("请选择要分配的工人组..");
			} else { */
	
				//工人组id
			/* var workerGroupId = $('input:radio:checked').val(); */
				
			var startDate =  $('#startDate').val();
			
			var endDate = $('#endDate').val();
			
			if(endDate==undefined ||endDate == "" ||startDate ==undefined || startDate == ""){
				alertx("请调整任务包计划时间");
				return;
			}
	            loading("正在分配该任务包给"+ $('input:radio:checked').parent().next().text()+"...请稍后")
	            //是否重新分配
				var isReDistribute = "${isRedistribute}";
	
			//待派工
	            var turnpageflag = "${turnpageflag}";
	
	
				window.location.href = "${ctx}/AllotWorkerGroup/allotWorkerGroup/allotWorker?packageId="
						+ "${taskPackage.id}" + "&id=" + workerGroupId+"&startDate="+startDate+"&endDate="+endDate+"&isReDistribute="+isReDistribute+"&turnpageflag="+turnpageflag;
				
			closeTip();
		/* } */
	}
	</c:if>
	
	function mapAllot(){
		var isRedistribute = "${isRedistribute}";
        var turnpageflag = "${turnpageflag}";
        var projectMode = "${projectMode}";
        
        //var itemManagerId = "${workgroupVo.itemManagerId}";
        var itemManageName = "${workgroupVo.itemManageName}"
		//alert(itemManagerId);
		//alert(itemManageName);
		window.location.href = "${ctx}/AllotWorkerGroup/allotWorkerGroup/mapAllotCenter?packageId=${taskPackage.id}&isRedistribute="+isRedistribute+"&turnpageflag="+turnpageflag
				+"&itemManageName="+itemManageName+"&projectMode="+projectMode;
	}
	function backHistory(){
		var isReDistribute = "${isRedistribute}";
		var turnpageflag = "${turnpageflag}";

		//重新分配
		if(isReDistribute == 1){
            window.location.href='${ctx}/scheduling/orderTaskpackage/workerList2'


        }else if("waitAllot"== turnpageflag){
		    //待派工查询

            window.location.href='${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/waitAllotTaskpackage'
		}else{
	//分配工人组
            window.location.href='${ctx}/scheduling/orderTaskpackage/list'
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
		    width: 560px;
		    height: 420px;
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
	<c:if test="${not empty error }">${error }</c:if>
	<c:if test="${empty error }">
		<div class="breadcrumb form-search">
			<form:form id="searchForm1" modelAttribute="WorkgroupVo"
				action="${ctx}/AllotWorkerGroup/allotWorkerGroup/list" method="post"
				class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input id="isRedistribute" name="isRedistribute" type="hidden" value="${isRedistribute}"/>
				<input id="turnpageflag" name="turnpageflag" type="hidden" value="${turnpageflag}"/>
				<input id="packageCode" name="packageCode" type="hidden" value="${packageCode }"/>
				<input id="projectMode" name="projectMode" type="hidden" value="${projectMode }"/>
				
				
				<ul class="ul-form">
					<li><label>工程地址：</label>
						
						<input  type="text" disabled="disabled" style="border: 0px;" value="${taskPackage.customerMessage}"/>
						<input type="hidden" name="packageId" value="${taskPackage.id}" />
					</li>
				
				</ul>
				
				<ul class="ul-form">
					<li><label>开始日期:</label>
					
					<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${taskPackage.planStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
					</li>
					<li><label>结束日期:</label>
							<input name="endDate" id = "endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${taskPackage.planEnddate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</li>
					<li><label>组长姓名:</label>
						<input class="needClear" name="groupName" type="text" maxlength="15" value="${workgroupVo.groupName}"/>
					</li>
					<li><label>推荐人:</label>
						<c:if test="${projectMode == 1 && empty itemManageName}">
							<input class="needClear" name="itemManageName" type="text" maxlength="15" value=""/>
							<!-- <input id="itemManagerId" name="itemManagerId" type="hidden" value=""/> -->
						</c:if>
						<c:if test="${projectMode != 1 || not empty itemManageName}">
							<input class="needClear" name="itemManageName" type="text" maxlength="15" value="${workgroupVo.itemManageName}"/>
							<%-- <input id="itemManagerId" name="itemManagerId" type="hidden" value="${itemManagerId }"/> --%>
						</c:if>
					</li>
					<li>
						<label>归属工程部：</label> 
						<select name="elactricationId" style="width: 170px;font-size: 15px;" disabled="disabled">
								<option value="0">请选择...</option>
								<c:forEach items="${engineList }" var="engine">
									<option  value="${engine.engineDepartId }" 
									<c:if test="${engine.engineDepartId == taskPackage.engineDepartId }">selected="selected"</c:if>>
									${engine.engineDepartName }
									</option>
								</c:forEach>
						</select>
						&nbsp;&nbsp;&nbsp;
					</li>
					<li>
						<input class="btn btn-primary" type="submit" value="查询"/>
						<input  class="btn btn-primary" type="button" value="地图分配" onclick="mapAllot()" >
						<input class="btn btn-primary clearBtn" type="button" value="清空"/>
						<input class="btn btn-primary" type="button" value="返回" onclick="backHistory()"/>
					</li>
					
					
				</ul>
				
				
				
				
				
			</form:form>
			<sys:message content="${message}" />
		</div>


		<sys:message content="${message}" />

		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>组长姓名</th>
					<th>手机</th>
					<th>推荐人</th>
					<th>手机</th>
					<th>被换单次数</th>
					<th>星级</th>
					<th>NPS</th>
					<th>组内成员数</th>
					<th>当前未完成包数</th>
					<th>住址</th>
					<th>住址和工地距离</th>
					<th>操作</th>

					<!-- <shiro:hasPermission name="employee:workerGroup:edit"><th>操作</th></shiro:hasPermission> -->
				</tr>
			</thead>
			<tbody id="tBody">
				<c:forEach items="${page.list}" var="workerGroup" varStatus="status">
					<tr>
						<td>${status.index + 1 }</td>
						<td>${workerGroup.groupName}</td>
						<td>${workerGroup.phone}</td>
						<td>${workerGroup.itemManageName }</td>
						<td>${workerGroup.itemManagerPhone }</td>
						<td>
							<c:if test="${workerGroup.exchangeOrderTimes == null}">
								0
							</c:if>
							<c:if test="${workerGroup.exchangeOrderTimes != null}">
								${workerGroup.exchangeOrderTimes}
							</c:if>
						</td>	
						<td>${workerGroup.starLevel}</td>
						<td>${workerGroup.NPS}</td>
						<td>${workerGroup.groupCount}</td>
						<td><c:if test="${empty workerGroup.targetPackageCount}">0</c:if>
							<c:if test="${not empty workerGroup.targetPackageCount}">${workerGroup.targetPackageCount}</c:if></td>
						<td>${workerGroup.address}</td>
						<td><fmt:formatNumber value="${workerGroup.addressToWorkerAddressDistance }"></fmt:formatNumber>米</td>
						<td>
						<!-- <input class="btn btn-primary" type="button" value="" />
						<input class="btn btn-primary" type="button" value="地图分配" onclick="mapAllot()"/> -->
							<a herf="javascript:void(0);" onclick="allotWorkerGroup(${workerGroup.workerGroupId})" >分配工人</a></br>
						</td>
						
						
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	
	<div class="Black undis" id="onCreateTaskpackage">
		<div class="tc_center">
			<h2 id="orderAddress"></h2>
			<h2>${taskPackage.customerMessage}-${taskPackage.customerName}  ${taskPackage.packageName }</h2>
			<div class="cen_t">
				<div class="cen_tex">
					<input id="workerId" type="hidden"/>
					<span class="span_l">原因：</span>
					<p class="span_r" id="installName">

						<select id="reasonType" name="reasonType">
							<option value="-1">-------请选择-------</option>
							<c:forEach items="${fns:getDictList('re_dispatch_cause')}" var="dis">
								<option value="${dis.value}">${dis.label}</option>
							</c:forEach>
						</select>
					</p>
				</div>
				<div class="cen_tex">
					<span class="span_l">说明：</span>
					<p class="span_r">
						<textarea id="reasonRemarks" maxlength="50"></textarea>
					</p>
				</div>
				<div class="cen_btn">
					<span class="btn_y" onclick="yes()">提交</span>
					<span class="btn_n" onclick="no()">取消</span>
				</div>
			</div>
			<div class="tc_close"></div>
		</div>
	</div> 
	
	<div class="pagination">${page}</div>
	
<!-- 	<object type="application/x-shockwave-flash" style="outline:none;" data="http://cdn.abowman.com/widgets/hamster/hamster.swf?" width="240" height="180" id="__wow_video_player__463292481" title="Adobe Flash Player"><param name="movie" value="http://cdn.abowman.com/widgets/hamster/hamster.swf?"><param name="AllowScriptAccess" value="always"><param name="wmode" value="opaque"></object>  
 --></body>
</html>