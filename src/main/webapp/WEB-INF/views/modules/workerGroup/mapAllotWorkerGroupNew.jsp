<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<meta name="decorator" content="default" />
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<meta content="telephone=no" name="format-detection">
		<meta content="email=no" name="format-detection">
		<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	  <%--  <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=eea8244dadd58de30dd93b36ca8f0f90"></script>
	  --%>
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
				height: 75%;
				font-family: "arial rounded mt bold";

				margin-top: 140px;
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
	         div.info-middle img {
	            height: 80px;
	            width: 100px;
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
	</head>
	<body>
		<ul class="nav nav-tabs">
			<li class="active"><a href="">地图分配工人组</a></li>
		</ul>
		<form:form id="inputForm" modelAttribute="workgroupVo" action="${ctx}/order/order/save" method="post" class="breadcrumb form-search">
			<ul class="ul-form">
				<li><label>要求工期: </label> 
					<input name="startDate" id="start" type="text" readonly="readonly" maxlength="20"
						class="input-medium Wdate" value="<fmt:formatDate value="${taskpakcage.planStartdate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end\')}',isShowClear:true});" /> - 
					<input name="endDate" id="end" type="text" readonly="readonly" maxlength="20" 
						class="input-medium Wdate" value="<fmt:formatDate value="${taskpakcage.planEnddate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'start\')}',isShowClear:true});" />
				</li>
				<li><label>工人组星级：</label>
					<form:select path="starLevel" class="input-medium needClear" id="starLevel">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('emp_star')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
				<li><label>范围：(单位:米)</label>
					<form:input path="scopeDistance" htmlEscape="false" maxlength="11" class="input-medium needClear" 
						onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
						onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" id="scopeDistance"/>
				</li>
				<li><label>工人组长名称: </label>
					<form:input path="groupName" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
				</li>
				<li><label>推荐人名称: </label>
						<c:if test="${projectMode == 1}">
							<input class="needClear" id="itemManageName" name="itemManageName" type="text" maxlength="15" value=""/>
						</c:if>
						<c:if test="${projectMode != 1}">
							<input class="needClear" id="itemManageName" name="itemManageName" type="text" maxlength="15" value="${workgroupVo.itemManageName}"/>
						</c:if>
					
				</li>
				<li class="clearfix"></li>
				<li><input id="btnSubmit" class="btn btn-primary" name="btnSubmit" type="button" value="查询" onclick="getWorkerGroup()" /></li>
				<li><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
				<li><input class="btn btn-primary" type="button" value="返回" onclick="history.go(-1)"/></li>
				<li class="clearfix"></li>
			</ul>
			</form:form>
		<div id="container"></div>
		<script type="text/javascript">
		//任务包经纬度
			var lng = "${taskpakcage.lng}";
			var lat = "${taskpakcage.lat}";
			//之前标记的所有marker的集合 以作清除使用
			var markers=[];

        //初始化地图对象，加载地图
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


        function makeMap(lat,lng){
          //传入任务包的经纬度


            // 创建Map实例
            map.centerAndZoom(new BMap.Point(lat,lng), 11);  // 初始化地图,设置中心点坐标和地图级别  zoom可赋值范围为3-18级。如果center类型为字符串时，比如“北京”，zoom可以忽略，地图将自动根据center适配最佳zoom级别
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
			 cr.addCopyright({id: 1, content: "<a href='#' style='font-size:20px;background:blue'>美得你集团</a>", bounds: bs});*/

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
	
			//输入参数:经纬度   输出:任务包地址的marker
			function getMarker(lng,lat){
                var myIcon = new BMap.Icon("${ctxStatic}/modules/center.png", new BMap.Size(50,27));
                var marker = new BMap.Marker(new BMap.Point(lng,lat),{icon:myIcon});
				var title="任务包地址";
                var content =("地址:${taskpakcage.customerMessage}"+"<br>项目经理:${taskpakcage.itemCustomer}<br>电话:${taskpakcage.customerPhone}<br>");
                var sContent ="<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+title+"</h4>"  + "<p style='margin:0;line-height:1.5;font-size:13px;'>地址:${taskpakcage.customerMessage}"+"<br>项目经理:${taskpakcage.itemCustomer}<br>电话:${taskpakcage.customerPhone}<br></p>" + "</div>";

                var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
                map.addOverlay(marker);
                marker.addEventListener("click", function(){
                    this.openInfoWindow(infoWindow);
                    //图片加载完毕重绘infowindow
                    document.getElementById('imgDemo').onload = function (){
                        infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
                    }
                });


              /*  // 将标注添加到地图中
                addClickHandler(content,marker,title);*/

			}

        function addClickHandler(content,marker){
            marker.addEventListener("click",function(e){
                openInfoForPack(content,e)}
            );
        }


        function addClickHandler(content,marker,title){
            marker.addEventListener("click",function(e){
                openInfo(content,e,title)}
            );
        }
        function openInfo(content,e,title){

            var opts = {
                width : 240,     // 信息窗口宽度
                height: 180,     // 信息窗口高度
                title :title  // 信息窗口标题

            };
            var p = e.target;
            var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
            var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
            map.openInfoWindow(infoWindow,point); //开启信息窗口
        }





		//页面加载 发送ajax 查出工人组marker
			window.onload = function() {
				loading("查询中,请稍等...");
				$.ajax({
					url : "${ctx}/AllotWorkerGroup/allotWorkerGroup/mapAllotNew?packageId=${taskpakcage.id}&packageCode=${taskpakcage.packageCode}&itemManageName=${workgroupVo.itemManageName}&itemManagerId=${workgroupVo.itemManagerId}",
					type:"post",
					success : function(data) {
						makeMap(lng,lat);
						
                        if(data!=null&& data.length>0){
                        	
                        var myIcon = new BMap.Icon("${ctxStatic}/modules/workerGroup.png", new BMap.Size(50,27));

                        //返回对象集合
						var n =0;
						for (var v = 0; v < data.length; v++) {
                            var marker = new BMap.Marker(new BMap.Point(data[v].lat,data[v].lng),{icon:myIcon});


							var title = '工人组长信息<input type="button" onclick="allotWorker('+data[v].workerGroupId+')" value="分配工人" style="height:30px;width:100px;color:blue;"></input>'

                            var content =("<img src='${ctxStatic}/modules/info.png'><p style='margin:buttom:3px;'>地址:"+data[v].address+"</p><p style='margin:buttom:3px;'>组长:"+data[v].groupName+"</p><p style='margin:buttom:3px;'>电话:"+data[v].phone+"</p>");

                            map.addOverlay(marker);               // 将标注添加到地图中
                            addClickHandler(content,marker,title);
						
						}
						//最后调用任务包的marker
						getMarker("${taskpakcage.lng}","${taskpakcage.lat}");
						//关闭loading
						closeTip();
					  }else{
 
                            //最后调用任务包的marker
                            getMarker("${taskpakcage.lng}","${taskpakcage.lat}");
                            //关闭loading
                            closeTip();
                        $("#container").hide();
                		alertx("该条件下没有找到工人组！")

            }
					}

				})
			
			}
			
		//函数 : 创建信息窗体基本函数
		//包括 : 元素内容,标签,节点,事件,样式 等
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
		   
	
				$(document).ready(function() {
				});
				function page(n,s){
					$("#pageNo").val(n);
					$("#pageSize").val(s);
					$("#searchForm").submit();
		        	return false;
		        }
				
				//清空按钮的实现
				function clearCondition(){
					//form 的reset方法
					 document.getElementById("searchForm").reset();
					
					//得到input框对象的集合
					 var inputObjs=jQuery("#searchForm input[type='text']"); 
					//迭代, 写入value: 空
					for(var i=0;i<inputObjs.length;i++){ 
					 var inputObj = inputObjs[i]; 
					 inputObj.value=""; 
					 } 
					
					//得到所有class 是 input-medium 的input框的对象集合
					 var selectObjs = jQuery("#searchForm input[class='input-medium']"); 
					//同样迭代, 写入value:空
					for(var i=0;i<selectObjs.length;i++){ 
					 var selectObj = selectObjs[i]; 
					 selectObj.value=""; 
					 } 
				}
				//查询按钮的函数实现
				function getWorkerGroup(){
					//得到搜索查询的几个条件: 包括不限于 事件查询,距离查询,星级查询
					var start =$("#start").val();
					var end = $("#end").val();
					var scopeDistance=$("#scopeDistance").val();
					var starLevel= $("#starLevel option:selected").val();
					var groupName= $("#groupName").val();
					var itemManageName = $("#itemManageName").val();
					if(scopeDistance==undefined){
						$("#scopeDistance").val()	
						
					}
					if(starLevel==undefined){
						starLevel="";
						
					}
					//两个if判断是由于清空后,使得该元素的值为undefined,从而发送  badrequest 400 
					
					//把之前的所有marker从地图中移除

					loading("查询中,请稍等...");
					//发送ajax 查询本次搜索条件蕴含的工人组
					$.ajax({
						url : "${ctx}/AllotWorkerGroup/allotWorkerGroup/mapAllotNew?packageId=${taskpakcage.id}"+"&startDate="+start+"&endDate="+end+"&scopeDistance="+scopeDistance+"&starLevel="+starLevel+"&groupName="+groupName+"&itemManageName="+itemManageName+"&packageCode=${taskpakcage.packageCode}",
						success : function(data) {
							map = new BMap.Map("container");
   							//初始化地图对象，加载地图
                               makeMap(lng,lat);
							
                            if(data!=null&& data.length>0){
                            	$("#container").show();
								//得到工人组对象的集合
	                            var myIcon = new BMap.Icon("${ctxStatic}/modules/workerGroup.png", new BMap.Size(50,27));
	
	                            for (var v = 0; v < data.length; v++) {
	
	                                var marker = new BMap.Marker(new BMap.Point(data[v].lat,data[v].lng),{icon:myIcon});
	
	
	                                var title = '工人组长信息<input type="button" onclick="allotWorker('+data[v].workerGroupId+')" value="分配工人" style="height:30px;width:100px;color:blue;"></input>'
	
	                                var content =("<img src='${ctxStatic}/modules/info.png'><p style='margin:buttom:3px;'>地址:"+data[v].address+"</p><p style='margin:buttom:3px;'>组长:"+data[v].groupName+"</p><p style='margin:buttom:3px;'>电话:"+data[v].phone+"</p>");
	
	                                map.addOverlay(marker);               // 将标注添加到地图中
	                                addClickHandler(content,marker,title);
	
	                            }
								//显示任务包信息
								getMarker("${taskpakcage.lng}","${taskpakcage.lat}");
								//关闭提示窗体
								closeTip();
                            }else{
                            
                                //最后调用任务包的marker
                                getMarker("${taskpakcage.lng}","${taskpakcage.lat}");
                                //关闭loading
                                closeTip();
                                alertx("该条件下没有找到工人组！")

                            }
						}
					})	
					
					
				}
				
				//分配工人 实现函数:  
					//输入参数: 工人组id
				function allotWorker(workerGroupId){
						var start =$("#start").val();
						var end = $("#end").val();
						if(start==""||start==undefined ||end==""||end==undefined){
							alertx("任务包时间为空,请调整派工计划时间再进行分配工人组")
							return
						}
						//没有任务包时间不允许分配工人组
					loading('分配中，请稍等...');
                    //是否重新分配
                    var isReDistribute = "${isRedistribute}";
                    //待派工
                    var turnpageflag = "${turnpageflag}";
					window.location.href="${ctx}/AllotWorkerGroup/allotWorkerGroup/allotWorker?packageId=${taskpakcage.id}"+"&startDate="+start+"&endDate="+end+"&id="+workerGroupId+"&isReDistribute="+isReDistribute+"&turnpageflag="+turnpageflag;
						
						
					
				}



		</script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	</body>
</html>
