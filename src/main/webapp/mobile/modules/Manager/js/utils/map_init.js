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
		var point = new BMap.Point(116.46, 39.92);
		map.centerAndZoom(point, 11);
		// map.setCurrentCity("北京"); 
		// var point = new BMap.Point(116.46, 39.92);
		// var marker = new BMap.Marker(point);
		// map.addOverlay(marker);
	var pt = new BMap.Point(116.417, 39.909);
	var myIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(23,25), {
                        offset: new BMap.Size(10, 25), // 指定定位位置
                        imageOffset: new BMap.Size(0, 0 - 10 * 25)}); // 设置图片偏移
	var marker2 = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
	map.addOverlay(marker2);  // 将标注添加到地图中
	var geoc = new BMap.Geocoder();
	map.addEventListener("click", function(e){        
		var pt = e.point;
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
		});        
	});
	}
};
MapInit.Fun.loadScript();
