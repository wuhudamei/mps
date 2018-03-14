/**公共返回的方法
 */

function goToHistory(){
    var str=document.referrer;//上一个点击进来的页面
    if(str!=null){
        str = str.split("?")[0].split("/");
        var actionName = str[(str.length-1)];//获取action名字
        if(actionName=="MyText.action"){
            location = "MyText.action";//返回MyText
        }else if(actionName=="YouText.action"){
            var url = "YouText.action"//返回YouText
            location = url;
        }else{
            history.go(-1);
        }
    }else{
        alert("这个页面是初始页面");
    }
}


