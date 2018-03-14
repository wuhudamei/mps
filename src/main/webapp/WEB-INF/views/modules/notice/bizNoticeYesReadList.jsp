<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>已读明细</title>
		<meta name="decorator" content="default"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script type="text/javascript">
		var html = null;
		function showMask(){     
	        $("#mask").css("height",$(document).height());     
	        $("#mask").css("width",$(document).width());
	      /*  $("#mask").append("<span>请等待...</span>") */
	        $("#mask").show();     
	    }  
		function hideMask(){     
	          
	        $("#mask").hide();     
	    }  
			function load(a,id){
				html = null;
				showMask();
				$(".addRole").each(function(index, element) {
							$(this).remove();
						
				});
				 $.ajax({
					url : "${ctx}/notice/bizNotice/ajaxData",
					type : "post",
					data:{"flag":a,"id":id},
					dataType:"json",
					success : function(data) {
						 $.each( data, function(index, view){ 
									var temp = view.viewDatetime
		            			    if(temp == '' || temp==null){
		            			    	temp = "未阅"
		            			    }else{
		            			    	temp = "已阅"
		            			    }
									var date = view.viewDatetime;
									if(date == null){
										date = '';
									}
									html +=  "<tr class='addRole'><td>"+view.realName+"</td>"
									+"<td>"+temp+"</td>"
									+"<td>"+date+"</tr>"
		            			  }); 
						 $("#mytr").after(html);
						 hideMask();
					}

				});
				
			}
			
		</script>
		<style type="text/css">
			.addRole{
			}
			.loader {  
		    position: fixed;  
		    left: 0px;  
		    top: 0px;  
		    width: 100%;  
		    height: 100%;  
		    z-index: 9999;  
		    background: url('http://img.blog.csdn.net/20161205162919763') 50% 50% no-repeat rgb(249,249,249); 
		    }
		    .mask {       
            position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #777;     
            z-index: 1002; left: 0px;     
            opacity:0.5; -moz-opacity:0.5; 
            background: url('http://pic1.16xx8.com/allimg/120703/153221D15-0.jpg') 500px 300px no-repeat rgb(249,249,249); 
        	}    
        
		</style>
	</head>
	<body>
	    <ul class="nav nav-tabs">                                                                                      
			<li class="active"><a href="javascript:void(0)">已读明细</a></li>
		</ul>
	<!-- 遮罩层显示 -->
	<div id="mask" class="mask" align="center">
		
	
	</div> 
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div>
						<h3>
							<center>
								未阅/已阅明细
							</center>
						</h3>
					</div>
					
					<div class="breadcrumb form-search">
						<table style="width: 100%;">
							<tr>
								<td><span>发布总人数： 	<a href="javascript:void(0);" onclick="load(1,${id})">${noRead }</a></span></td>
								<td><span>未阅人数： 	<a href="javascript:void(0);" style="color: red;" onclick="load(2,${id})">${noRead - yesRead }</a></span></td>
								<td><span>已阅人数：	<a href="javascript:void(0);" onclick="load(3,${id})">${yesRead }</a></span></td>
							</tr>
						
						</table>
					
						
					</div>
					<table class="table table-striped table-bordered table-condensed">
						<tr id="mytr">
							<th>姓名</th>
							<th>阅读状态</th>
							<th>阅读时间</th>
						</tr>
						<%-- <c:forEach items="${bizNoticeViewLogList}" var="view">
							<tr>
								<td>${view.realName}</td>
								<td>
									<c:if test="${view.viewDatetime == '' or empty view.viewDatetime}">
										未阅
									</c:if>
									<c:if test="${view.viewDatetime != '' and not empty view.viewDatetime}">
										已阅
									</c:if>
								</td>
								<td><fmt:formatDate value="${view.viewDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
						</c:forEach> --%>
					</table>
				</div>
			</div>
			<div class="errorMessage">
				<a class="btn" href="javascript:" onclick="history.go(-1);">返回</a>
			</div>	
		</div>
	</body>
</html>