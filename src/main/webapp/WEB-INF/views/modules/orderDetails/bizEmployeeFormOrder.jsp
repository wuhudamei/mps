<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>员工信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/cusumerValidate/cusumerValidate.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=9sTsTQyg2l9Y8GIo5uk2a5Be"></script>
<script type="text/javascript">
	
</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/employee/bizEmployee/">员工信息列表</a></li>
		<li class="active"><a
			href="${ctx}/employee/bizEmployee/form?id=${bizEmployee.id}">员工信息<shiro:hasPermission
					name="employee:bizEmployee:edit">${not empty bizEmployee.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="employee:bizEmployee:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="orderDetails"
		action="${ctx}/employee/bizEmployee/save" method="post"
		class="form-horizontal">
		<sys:message content="${message}" />
		<table>
			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">
						员工信息
					</div>
				</td>
			</tr>
			<tr>
					<td><div class="control-group">
						<label class="control-label">员工类型：</label>
						<div class="controls">
							<form:input path="orderNumber" htmlEscape="false" maxlength="100"
								class="input-medium " id="realname" name="realname" />
                            <span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
					<td><div class="control-group">
						<label class="control-label">员工类型：</label>
						<div class="controls">
							<form:select path="orderNumber" class="input-medium " onClick="emptypeClick()">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('emp_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false"
									id="empType" name="orderNumber" />
							</form:select>
                            <span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>
			
	</form:form>
	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:300px;height:200px; display:none;"></div>	
	<script type="text/javascript">
	var baiduAK = "9sTsTQyg2l9Y8GIo5uk2a5Be";
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	 
	addMapControl();
	//自动完成
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "address1","location" : map}
	);
	var myValue; //中间变量
	//地图标记对象
	var mkr ;//= new BMap.Marker(0,0,{enableDragging: true,raiseOnDrag: true});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	//鼠标点击下拉列表后的事件
	ac.addEventListener("onconfirm", function(e) {    
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;		
		setPlace();
	});

	function setPlace(){
		//alert(1);
		map.clearOverlays();                      //清除地图上所有覆盖物
		var local = new BMap.LocalSearch(map, {   //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
		function myFun(){
			
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			$("#pointXy").val(pp.lat + "," + pp.lng);
// 			var pp =  new BMap.Point(39.979197,116.301697);
			map.centerAndZoom(pp, 100);
			mkr = new BMap.Marker(pp,{enableDragging: true,raiseOnDrag: true});
			//添加标注
			map.addOverlay(mkr);
			//标记对象点击完成事件
			mkr.addEventListener("dragend", function(e){
				var posi =  e.point.lat + "," + e.point.lng;
				var url = "http://api.map.baidu.com/geocoder/v2/?ak="+baiduAK+"&callback=renderReverse&location=" + posi + "&output=json&pois=1"
				$("#location").val(posi);
				var aj = $.ajax({    
					url : url,// 跳转到 action    
					data:{},    
					type:'get',    
					cache:true,    
					dataType:'jsonp',
					jsonp:"callback", 
					jsonpCallback:"renderReverse", 
					success:function(data){
						if(data.status == "0" ){ 
							$("#address").val(data.result.formatted_address);
							$("#address1").val(data.result.formatted_address);
						}else{    
							alert("地址查询失败！" + data.msg);      
						}    
					 },    
					 error : function() {    
						  alert("地址查询异常！");    
					 }    
				});
			}); 
		}		
	}
	
	function myFun2(pp){
		
		if(pp==null){
			 pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
		}
		map.centerAndZoom(pp, 15);
		mkr = new BMap.Marker(pp,{enableDragging: true,raiseOnDrag: true});
		//添加标注
		map.addOverlay(mkr);
		//标记对象点击完成事件
		mkr.addEventListener("dragend", function(e){
			var posi =  e.point.lat + "," + e.point.lng;
			var url = "http://api.map.baidu.com/geocoder/v2/?ak="+baiduAK+"&callback=renderReverse&location=" + posi + "&output=json&pois=1"
			$("#location").val(posi);
			var aj = $.ajax({    
				url : url,// 跳转到 action    
				data:{},    
				type:'get',    
				cache:true,    
				dataType:'jsonp',
				jsonp:"callback", 
				jsonpCallback:"renderReverse", 
				success:function(data){
					if(data.status == "0" ){
						$("#pointXy").val(data.result.location.lat + "," + data.result.location.lng);
						$("#address").val(data.result.formatted_address);
						$("#address1").val(data.result.formatted_address);
					}else{    
						alert("地址查询失败！" + data.msg);      
					}    
				 },    
				 error : function() {    
					  alert("地址查询异常！");    
				 }    
			});
		}); 
	}	
    function G(id) {
        return document.getElementById(id);
    }
	
	//地图控件添加函数：
    function addMapControl() {
      //向地图中添加缩放控件
      var ctrl_nav = new BMap.NavigationControl({ anchor: BMAP_ANCHOR_TOP_LEFT, type: BMAP_NAVIGATION_CONTROL_LARGE });
      map.addControl(ctrl_nav);
      //向地图中添加缩略图控件
      var ctrl_ove = new BMap.OverviewMapControl({ anchor: BMAP_ANCHOR_BOTTOM_RIGHT, isOpen: 1 });
      map.addControl(ctrl_ove);
      //向地图中添加比例尺控件
      var ctrl_sca = new BMap.ScaleControl({ anchor: BMAP_ANCHOR_BOTTOM_LEFT });
//       map.centerAndZoom("北京",15);      // 初始化地图,设置城市和地图级别。
	var point = new BMap.Point(39.979197,116.301697); //定义一个中心点坐标
// 	 map.centerAndZoom(new BMap.Point(117.269945,31.86713),15); //设定地图的中心点和坐标并将地图显示在地图容器中
	var existPointXy = $("#pointXy").val();
	var xy = existPointXy.split(",");
	 myFun2(new BMap.Point(xy[1],xy[0]));
      map.enableKeyboard(); //启用键盘上下左右键移动地图
      map.addControl(ctrl_sca);
      
      
    }
</script>
</body>
</html>