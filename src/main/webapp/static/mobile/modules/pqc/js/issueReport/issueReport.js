
function reportIssue(){

if($("#issueSelectId").val()==undefined ||$("#issueSelectId").val()==""){

    $(".maskContent").text("请选择您出现的问题")
    $(".alertMask").show();
    return
}
if(isNaN($("#delayDaysId").val()) && $("#delayDaysId").val().trim()!=""){

    $("#delayDaysId").next().show();
    // $(".maskContent").text("延期天数输入数字即可")
    // $(".alertMask").show();
    return

}else{


    $("#delayDaysId").next().hide();

}

if($("#delayDaysId").val().length>5){

        $(".maskContent").text("你不能延期这么久吧?")
        $(".alertMask").show();
        return
    }
var url =baseUrl +"/app/pqc/issueReport/saveIssueReport"

$.ajax({
    url:url,
    type:"post",
    data:$("#issueReportFormId").serialize(),
    beforeSend: function(){
    $(".subBtn").unbind("click")
        run_waitMe("正在为您上报约检问题...");
    },success:function(data){
        $('body').waitMe('hide');
        globalUtil.fn.alert('问题上报成功...',2.0);
        setTimeout("globalUtil.fn.alert('正在跳转页面...',2.0)",2000);
        setTimeout("window.location.href='"+baseUrl+"/app/pqc/issueReport/issueReportRecord?qcId="+data+"'",4000);

    },error:function(data){
        $('body').waitMe('hide');
        $(".maskContent").text("出错啦"+data)
        $(".alertMask").show();

    }





})


}
function checkNum(obj)
{obj.value=obj.value.replace(/[^\-\d]/g,'');
    if(obj.value.indexOf('-')>=0){
        obj.value='-'+obj.value.replace(/-/g,'');
    }
}