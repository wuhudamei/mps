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
		var mp = new BMap.Map('map');  
		mp.centerAndZoom(new BMap.Point(116.46, 39.92), 11);  
	}
};
MapInit.Fun.loadScript();
