<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>约检数量配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

        function editObj(obj,key){



            layer.prompt({title: '每天允许约检的订单数量', formType: 3}, function(text){
		if(checkPrice(text)){

	    if(text>0){
	    var param={
	        id:key,
            qcMaxCount: text

		}

   	 $.ajax({

        url : "${ctx}/bizqccount/bizQcMaxCount/ajaxChangeCount",
        type: "post",
        data: param,
        success : function(data) {

            if(1==data){
                $(obj).parent().prev().text(text);

                layer.msg("更改成功");
            }else{
                layer.msg("由于某些原因 失败了");

            }

        }

    });
        }else{
            layer.msg("请输入大于0的合法数量");

        }

}



            },function(){
                layer.msg('您取消了操作', {icon: 2});

            });


		}
        function deleteObj(obj,key){
            //询问框
            layer.confirm('您确定删除吗？', {
                btn: ['确定','取消'] //按钮
            }, function(){

                $.ajax({

                    url : "${ctx}/bizqccount/bizQcMaxCount/delete",
                    type: "post",
                    data: {id:key},
                    success : function(data) {

                        if(1==data){
                            $(obj).parent().parent().remove();

                            layer.msg("删除成功");
                        }else{
                            layer.msg("由于某些原因 失败了");

                        }

                    }

                });


            }, function(){
                layer.msg('您取消了操作', {icon: 2});

            });


        }

        function checkPrice(price){
            return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(price.toString());
        }

	</script>
	<script type="text/javascript" src="${ctxStatic}/modules/settleJs/layer/layer.js"></script>
	<script src="${ctxStatic}/modules/settleJs/layer/extend/layer.ext.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizqccount/bizQcMaxCount/list">约检数量配置列表</a></li>
		<shiro:hasPermission name="bizqccount:bizQcMaxCount:edit"><li><a href="${ctx}/bizqccount/bizQcMaxCount/form">约检数量配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizQcMaxCount" action="${ctx}/bizqccount/bizQcMaxCount/findList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findSettleNodeByStoreId()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>最大约检数量</th>

				<shiro:hasPermission name="bizqccount:bizQcMaxCount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizQcMaxCount">
			<tr>
				<td>
					${fns:getStoreLabel(bizQcMaxCount.storeId, '')}
				</td>
				<td>
					${bizQcMaxCount.qcMaxCount}
				</td>

				<shiro:hasPermission name="bizqccount:bizQcMaxCount:edit"><td>
    				<a href="#" onclick="editObj(this,${bizQcMaxCount.id})">修改</a>
					<a  href="#" onclick="deleteObj(this,${bizQcMaxCount.id})">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>