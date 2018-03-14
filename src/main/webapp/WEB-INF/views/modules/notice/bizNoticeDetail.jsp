<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>公告内容</title>
		<meta name="decorator" content="default"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script type="text/javascript">
			$(document).ready(function() {

			});
		</script>
	</head>
	<body>
	    <ul class="nav nav-tabs">                                                                                      
			<li class="active"><a href="javascript:void(0)">公告内容</a></li>
		</ul>  
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div>
						<h3>
							<center>
								公告内容
							</center>
						</h3>
					</div>
					<table class="table table-striped table-bordered table-condensed">
						<tr>
							<td width="100%">
								标题：${bizNotice.noticeTitle}
							</td>
						</tr>
						<tr>
							<td width="100%">
								内容：${bizNotice.noticeContent}
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="errorMessage">
				<a class="btn" href="javascript:" onclick="history.go(-1);">返回</a>
			</div>	
		</div>
	</body>
</html>