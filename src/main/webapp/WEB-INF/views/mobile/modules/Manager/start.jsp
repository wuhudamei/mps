<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>确认开工</title>
	<link rel="stylesheet" type="text/css" href="/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="/mobile/modules/Manager/css/lib/calendar.css"/>
	<link rel="stylesheet" type="text/css" href="/mobile/modules/Manager/css/start.css"/>
</head>
<body>
	<div class="g-start">
		<header class="start_header">
			<a id="back_btn" onclick="myhistory.back()" href="javascript:void(0)"></a>
			<h2 class="start_title">确认开工</h2>
		</header><!-- /header -->
		<section class="start_section">
			<h3>鹿港嘉苑-10-3-2001-张三</h3>
			<ul>
				<li class="clearfix">
					<span>开工合同日期：</span>
					<p>2016-08-01</p>
				</li>
				<li class="clearfix">
					<span>实际开工日期：</span>
					<p><input id="txtBeginDate" class="date" name="date" value="2016-08-01" placeholder=""></p>
					<div class="calMain">
						<div class="calTitle">
							<a class="prevMonth"></a>
							<span class="t_date">
								<span class="currentYearText">
									<a class="currentYear">2016</a>年
								</span>
								<span class="currentMonthText">
									<a class="currentMonth">9</a>月
								</span>
							</span>
							<a class="nextMonth"></a>
						</div>
					</div>
				</li>
				<li class="clearfix">
					<span>开工延期类型：</span>
					<p>
						<input type="radio" name="" value="">公司原因
						<input type="radio" name="" value="">客户原因
					</p>
				</li>
				<li class="clearfix">
					<span>开工延期说明：</span>
					<p><textarea name=""></textarea></p>
				</li>
				<a id="start" href="javascript:void(0)"></a>
			</ul>
		</section>
	</div>
	<script type="text/javascript" src="/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="/mobile/modules/Manager/js/lib/calendar.min.js"></script>
	<script type="text/javascript" src="/mobile/modules/Manager/js/utils/history.js"></script>
	<script>
    $(function () {
        $("#txtBeginDate").calendar({
            controlId: "divDate",                                 // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"
            speed: 200,                                           // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200
            complement: true,                                     // 是否显示日期或年空白处的前后月的补充,默认：true
            readonly: true,                                       // 目标对象是否设为只读，默认：true
            upperLimit: new Date(),                               // 日期上限，默认：NaN(不限制)
            lowerLimit: new Date("2011/01/01"),                   // 日期下限，默认：NaN(不限制)
            // callback: function () {                               // 点击选择日期后的回调函数
                // alert("您选择的日期是：" + $("#txtBeginDate").val());
            // }
        });
        // $("#txtEndDate").calendar();
    });
</script>
</body>
</html>