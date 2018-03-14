<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<meta name="decorator" content="default"/>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<meta content="telephone=no" name="format-detection">
		<meta content="email=no" name="format-detection">
		<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	   <%-- <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=eea8244dadd58de30dd93b36ca8f0f90"></script>--%>
		<script type="text/javascript"
				src="http://api.map.baidu.com/api?v=2.0&ak=9sTsTQyg2l9Y8GIo5uk2a5Be"></script>

	    <style type="text/css">
		    body, html {
				width: 99%;
				height: 99%;
				font-family: "微软雅黑";
				font-size: 12px;
				font-size-adjust: initial;
			}
			
			#container {
				width: 100%;
				height: 80%;
				font-family: "arial rounded mt bold";

				margin-top: 130px;
			}
			.info {
	            border: solid 1px silver;
	        }
	        div.info-top {
	            position: relative;
	            background: none repeat scroll 0 0 #F9F9F9;
	            border-bottom: 1px solid #CCC;
	            border-radius: 5px 5px 0 0;
	        }
	        div.info-top div {
	            display: inline-block;
	            color: #333333;
	            font-size: 14px;
	            font-weight: bold;
	            line-height: 31px;
	            padding: 0 10px;
	        }
	        div.info-top img {
	            position: absolute;
	            top: 10px;
	            right: 10px;
	            transition-duration: 0.25s;
	        }
	        div.info-top img:hover {
	            box-shadow: 0px 0px 5px #000;
	        }
	        div.info-middle {
	            font-size: 12px;
	            padding: 6px;
	            line-height: 20px;
	        }
	        div.info-bottom {
	            height: 0px;
	            width: 100%;
	            clear: both;
	            text-align: center;
	        }
	        div.info-bottom img {
	            position: relative;
	            z-index: 104;
	        }
	        input[type=button] {
	            margin-left: 22px;
	          
	          
	        }
	        .info-middle img {
	            float: left;
	            margin-right: 6px;
	        }
	        .undis{display:none;}
	        .choose_wrapper{width:160px;height:260px;background:#fff;border:1px solid silver;border-radius:4px;z-index:999;position:fixed;right:0;top:30%;}
	        .choose_wrapper ul,li{list-style:none;}
	        .choose_wrapper ul{padding-left: 20px;}
	        .choose_wrapper li{line-height:50px;font-size:14px;}
	        .km{width:40px;}
	        .choose_title{margin:0;color: #333;font-size: 14px;font-weight: bold;line-height: 31px;width:100%;height:31px;background:#F9F9F9;border-bottom:1px solid silver;text-indent:1em;}
	        .choose_btn{color:blue;font-size: 14px;font-weight: bold;margin-left: 55px;line-height: 31px;padding: 3px 10px;border:1px solid silver;border-radius:4px;text-decoration:none;}
	        .confirm{width:260px;border-radius:4px;border:1px solid #ccc;background:white;z-index:999;position:fixed;left:50%;transform:translateX(-50%);top:28%;}
	        .confirm_p{margin:0;height:60px;line-height:60px;font-size:14px;text-align:center;border-bottom: 1px solid #ccc;}
	        .btns{width:100%;height:30px;}
	        .btns a{text-decoration:none;float:left;width:50%;height:inherit;line-height:30px;text-align:center;font-size:14px;color:blue;}
	        .btns a:first-child{border-right:1px solid #ccc;box-sizing:border-box;}
	    </style>
	    <script type="text/javascript">
	    function findEngineListByStoreIdAndProjectMode(){
		var html3 = '<option value=""></option>';
		var storeId = $("#storeId").val();
		var projectModeValue = $("#projectModeId").val();
		if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
			return;
		}
		//根据门店个,工程模式    动态加载工程区域
		$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectModeValue,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
							}
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});		
		}
	    </script>
	</head>
	<body>
	<c:set var="user" value="${fns:getUser()}"></c:set>
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/order2/order2/managerMapCenterPre">产业项目经理指挥中心</a></li>
		</ul>
		<form:form id="searchForm" method="post" modelAttribute="order2" class="breadcrumb form-search">
			<ul class="ul-form">
				<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()"  id="projectModeId">
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
			</form:select>
			</c:if>
			</li>
			
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
				
				<li><label>项目经理：</label>
					<form:input path="managerName" htmlEscape="false" maxlength="11" class="input-medium"/>
				</li>
			
				<li><label>星级：</label>
				<form:select path="starLevel" class="input-medium needClear">
					<option value="">全部</option>
					<option value="5">5</option>
					<option value="4">4</option>
					<option value="3">3</option>
					<option value="2">2</option>
					<option value="1">1</option>
				</form:select>
			</li>
				<li><input class="btn btn-primary" type="button" value="查询" onclick="chaxun()"/></li>
				<li><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
				<li><input class="btn btn-primary" type="button" value="开启全景" onclick="Dmap(this)"/></li>
				<li class="clearfix"></li>
			</ul>
		</form:form>
		<div id="container"></div>
		<script type="text/javascript">

            var stCtrl = new BMap.PanoramaControl();
            var tileLayer;
			function Dmap(obj){

			    if($(obj).val()=='开启全景'){
                    // 覆盖区域图层测试
                    tileLayer =new BMap.PanoramaCoverageLayer();
                    map.addTileLayer(tileLayer);

                    //构造全景控件
                    stCtrl.setOffset(new BMap.Size(20, 20));
                    map.addControl(stCtrl);//添加全景控件

                    $(obj).val('关闭全景')
				}else if($(obj).val()=='关闭全景'){


                    map.removeControl(stCtrl)
                    map.removeTileLayer(tileLayer)

                    $(obj).val('开启全景')
				}



			}
			//初始化地图对象，加载地图
            var map = new BMap.Map("container");
            makeMap(2);
			function makeMap(storeId){
				var centerLat;//中心纬度
                var centerLng;//中心经度
				if(storeId==2){

                    centerLat=116.397428
                    centerLng=39.90923
				}else if (storeId==3){
                    centerLat=120.3027962992
                    centerLng=30.26

					
				}else{
                    centerLat=116.397428
                    centerLng=39.90923
					
				}

                    // 创建Map实例
                map.centerAndZoom(new BMap.Point(centerLat,centerLng), 11);  // 初始化地图,设置中心点坐标和地图级别  zoom可赋值范围为3-18级。如果center类型为字符串时，比如“北京”，zoom可以忽略，地图将自动根据center适配最佳zoom级别
                map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
                map.enableInertialDragging();

                map.enableContinuousZoom();
                map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
                var mapType1 = new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]});
                var mapType2 = new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_LEFT});
                var overView = new BMap.OverviewMapControl();
                var overViewOpen = new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT});
                var size = new BMap.Size(10, 20);



                changeMapStyle('grayscale')


				/* map.addControl(new BMap.CityListControl({
				 anchor: BMAP_ANCHOR_TOP_LEFT,
				 offset: size,
				 // 切换城市之间事件
				 // onChangeBefore: function(){
				 //    alert('before');
				 // },
				 // 切换城市之后事件
				 // onChangeAfter:function(){
				 //   alert('after');
				 // }
				 }));*/
                /*var cr = new BMap.CopyrightControl({anchor: BMAP_ANCHOR_TOP_RIGHT});   //设置版权控件位置
                map.addControl(cr); //添加版权控件

                var bs = map.getBounds();   //返回地图可视区域
                cr.addCopyright({id: 1, content: "<a href='#' style='font-size:20px;background:blue'>大美装饰管理平台集团</a>", bounds: bs});*/

                add_control(mapType1,mapType2,overView,overViewOpen);
			}
            function changeMapStyle(style){
                map.setMapStyle({style:style});

            }



            //添加地图类型和缩略图
            function add_control(mapType1,mapType2,overView,overViewOpen){
                map.addControl(mapType1);          //2D图，卫星图
                map.addControl(mapType2);          //左上角，默认地图控件
                map.setCurrentCity("北京");        //由于有3D图，需要设置城市哦
                map.addControl(overView);          //添加默认缩略地图控件
                map.addControl(overViewOpen);      //右下角，打开
            }
            //移除地图类型和缩略图
            function delete_control(){
                map.removeControl(mapType1);   //移除2D图，卫星图
                map.removeControl(mapType2);
                map.removeControl(overView);
                map.removeControl(overViewOpen);
            }


			var markers=[];
			function chaxun(){

				var storeId = $("#storeId").val();
				if(!storeId){
					alertx("请选择门店！");
				}else{
					loading("正在查询...请稍等")
					var options ={
							url: "${ctx}/order2/order2/managerMapCenter",
							type: "post",
							success:function(data){

								if(data!=null&& data.length>0){

                                    $("#container").show();
                                    map = new BMap.Map("container");

                                    makeMap(storeId);
                                    var myIcon = new BMap.Icon("${ctxStatic}/modules/manager.png", new BMap.Size(50,27));


                                        for (var v = 0; v < data.length; v++) {
                                            if(data[v].starLevel==undefined){

                                                data[v].starLevel=0
                                            }

                                            var marker = new BMap.Marker(new BMap.Point(data[v].pointY,data[v].pointX),{icon:myIcon});


                                            var content = "姓名:"+data[v].managerName+"<br>星级:"+data[v].starLevel+"星<br>手机:"+data[v].managerPhone+"<br>地址"+data[v].managerAddress;
                                            map.addOverlay(marker);               // 将标注添加到地图中
                                            addClickHandler(content,marker);

                                        }

										closeTip();



                                        <%--{--%>
													<%--icon : "${ctxStatic}/modules/manager.png",--%>
													<%--position : [  ],--%>
													<%--map : map--%>
												<%--});--%>






								}else{
									$("#container").hide();
									alertx("该条件下没有找到经理！")

                                    closeTip();
								}

							}
					}
					$("#searchForm").ajaxSubmit(options);
				}

				}


            function addClickHandler(content,marker){
                marker.addEventListener("click",function(e){
                    openInfo(content,e)}
                );
            }
            function openInfo(content,e){
                var title = '项目经理信息';
                var opts = {
                    width : 200,     // 信息窗口宽度
                    height: 100,     // 信息窗口高度
                    title :title  // 信息窗口标题

                };
                var p = e.target;
                var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
                var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
                map.openInfoWindow(infoWindow,point); //开启信息窗口
            }
		    

		
				//打开信息窗体事件
				/* infowindow.open(map, new AMap.LngLat(lng, lat)); */

				

		    function createInfoWindow(title, content) {
		        var info = document.createElement("div");
		        info.className = "info";
		
		        //可以通过下面的方式修改自定义窗体的宽高
		        //info.style.width = "400px";
		        // 定义顶部标题
		        var top = document.createElement("div");
		        var titleD = document.createElement("div");
		        var closeX = document.createElement("img");
		        top.className = "info-top";
		        titleD.innerHTML = title;
		        closeX.src = "http://webapi.amap.com/images/close2.gif";
		        closeX.onclick = closeInfoWindow;
		
		        top.appendChild(titleD);
		        top.appendChild(closeX);
		        info.appendChild(top);
		
		        // 定义中部内容
		        var middle = document.createElement("div");
		        middle.className = "info-middle";
		        middle.style.backgroundColor = 'white';
		        middle.innerHTML = content;
		        info.appendChild(middle);
		
		        // 定义底部内容
		        var bottom = document.createElement("div");
		        bottom.className = "info-bottom";
		        bottom.style.position = 'relative';
		        bottom.style.top = '0px';
		        bottom.style.margin = '0 auto';
		        var sharp = document.createElement("img");
		        sharp.src = "http://webapi.amap.com/images/sharp.png";
		        bottom.appendChild(sharp);
		        info.appendChild(bottom);
		        return info;
		    }
		 
		 	//关闭信息窗体
		    function closeInfoWindow() {
		        map.clearInfoWindow();
		    }
		   
		
		   
		</script>
		<script type="text/javascript">
				$(document).ready(function() {
					findEngineListByStoreIdAndProjectMode();
				});
				function page(n,s){
					$("#pageNo").val(n);
					$("#pageSize").val(s);
					$("#searchForm").submit();
		        	return false;
		        }
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
				
			</script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	</body>
</html>
