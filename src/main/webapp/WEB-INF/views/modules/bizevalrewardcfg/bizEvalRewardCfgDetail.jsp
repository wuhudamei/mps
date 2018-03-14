<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>奖励设置详情</title>
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li class="active"><a href="javascript:void(0)">奖励设置详情</a></li>
</ul>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div>
				<h3>
					<center>
						基本信息
					</center>
				</h3>
			</div>
			<table class="table table-striped table-bordered table-condensed">
				<tr>
					<td width="20%" style="text-align: right">
						门店：
					</td>
					<td style="padding-left: 10px">
						${fns:getStoreLabel(bizEvalRewardCfg.storeId, '')}
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						工程模式：
					</td>
					<td style="padding-left: 10px">
						${fns:getDictLabel(bizEvalRewardCfg.projectMode, 'project_mode', '')}
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						奖励对象：
					</td>
					<td style="padding-left: 10px">
						${fns:getDictLabel(bizEvalRewardCfg.rewardTargetType, 'eval_target_type', '')}
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						方案名称：
					</td>
					<td style="padding-left: 10px">
						${bizEvalRewardCfg.rewardName}
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						活动时间：
					</td>
					<td style="padding-left: 10px">
						<fmt:formatDate value="${bizEvalRewardCfg.rewardStartDatetime}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;至&nbsp;&nbsp;<fmt:formatDate value="${bizEvalRewardCfg.rewardEndDatetime}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right">
						任务包类型：
					</td>
					<td style="padding-left: 10px">
						<c:forEach items="${templatNameList}" var="templatName">
							${templatName}&nbsp;&nbsp;
						</c:forEach>
					</td>
				</tr>
			</table>
		</div>

		<div class="col-md-12 column">
			<div>
				<h3>
					<center>
						星级设置
					</center>
				</h3>
			</div>
			<table class="table table-striped table-bordered table-condensed">
				<tr>
					<td width="33%" style="text-align: center">
						星级
					</td>
					<td width="33%" style="text-align: center">
						分值
					</td>
					<td width="34%" style="text-align: center">
						奖励金额
					</td>
				</tr>
				<c:forEach items="${bizEvalRewardStarList}" var="bizEvalRewardStar">
					<tr>
						<td style="text-align: center">
							${fns:getDictLabel(bizEvalRewardStar.starLevel, 'eval_reward_star', '')}
						</td>
						<td style="text-align: center">
							${bizEvalRewardStar.minScore}&nbsp;&nbsp;-&nbsp;&nbsp;${bizEvalRewardStar.maxScore}
						</td>
						<td style="text-align: center">
							${bizEvalRewardStar.rewardAmount}
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="errorMessage">
		<a class="btn" href="javascript:" onclick="history.go(-1);">返回上一页</a>
	</div>
</div>
</body>
</html>