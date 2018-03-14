var MapInit = {};
MapInit.Fun = {
	loadScript : function() {
		var script = document.createElement("script");  
		script.src = "http://api.map.baidu.com/api?v=2.0&ak=GuVbcVy0uBMXCYzRQYWnHNkIZXXaOVgF&callback=MapInit.Fun.initialize";//此为v2.0版本的引用方式  
	    // http://api.map.baidu.com/api?v=1.4&ak=您的密钥&callback=initialize"; //此为v1.4版本及以前版本的引用方式 
	    //Q3FpuFYi311G6ixYbUA64tzD (个人的)
		document.body.appendChild(script);
	},
	initialize : function() {
		var map = new BMap.Map('map');
		var point = new BMap.Point(116.331398,39.897445);
		map.centerAndZoom(point,12);  
		// var point = new BMap.Point(116.46, 39.92);
		// map.centerAndZoom(point, 11);
		// map.setCurrentCity("北京"); 
		// var point = new BMap.Point(116.46, 39.92);
		// var marker = new BMap.Marker(point);
		// map.addOverlay(marker);
	// var pt = new BMap.Point(116.417, 39.909);
	// var myIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(23,25), {
 //                        offset: new BMap.Size(10, 25), // 指定定位位置
 //                        imageOffset: new BMap.Size(0, 0 - 10 * 25)}); // 设置图片偏移
	// var marker2 = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
	// map.addOverlay(marker2);  // 将标注添加到地图中
	// var geoc = new BMap.Geocoder();
	// map.addEventListener("click", function(e){        
	// 	var pt = e.point;
	// 	geoc.getLocation(pt, function(rs){
	// 		var addComp = rs.addressComponents;
	// 		alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
	// 	});        
	// });

		//解析当前地址(按浏览器定位，百度地图API中－demo－定位示例－浏览器定位)
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				//设置自定义标注图标
				var myIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(23,25), {
	                        offset: new BMap.Size(10, 25), // 指定定位位置
	                        imageOffset: new BMap.Size(0, 0 - 10 * 25)}); // 设置图片偏移
				var mk = new BMap.Marker(r.point,{icon:myIcon});
				map.addOverlay(mk);
				map.panTo(r.point);
				
				
				
				var pointA = new BMap.Point(r.point.lng,r.point.lat);
				//得到经纬度
				ajaxFuc();
				
				var location1 = $("#locat1").val();
				var location2 = $("#locat2").val();
				var pointB = new BMap.Point(location1,location2);
				$("#distance").val((map.getDistance(pointA,pointB)).toFixed(2));
				
				if(null!=pointA){
					
				
					
					
					$('.dispos_btn').removeClass('undis');
					$('.pos_btn').removeClass('undis');
					
				}else{
				
					alert("定位失败,您可以尝试开启gps或上传照片签到");
					
					$('.pos_btn').removeClass('undis');
					$('.dispos_btn').removeClass('undis');
				}
				
				
				
				//console.log('您的位置：'+r.point.lng+','+r.point.lat);
				// var pt = e.point;
				var geoc = new BMap.Geocoder();
				geoc.getLocation(r.point, function(rs){
					var addComp = rs.addressComponents;
					//console.log(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
					// 按需求拼地址，当前地址为：省＋市＋区＋街道＋门牌号
					var position_text = addComp.province + addComp.city + addComp.district  + addComp.street + addComp.streetNumber;
					document.querySelector('.position_text').innerHTML = position_text;	
					
					
					$("#address").val(position_text);
					
					
				/*	
					$.ajax({
						async: false,
						url : "${ctx}/app/manager/getAddress?address="+position_text ,
						type : "get" ,
						dataType: "json",
						success: function(data){
						
					
						}
					
						
					});
					*/
				});
				
				
				
				
			}else {
				alert('failed'+this.getStatus());
			}        
		},{enableHighAccuracy: true})
	}
};
MapInit.Fun.loadScript();
