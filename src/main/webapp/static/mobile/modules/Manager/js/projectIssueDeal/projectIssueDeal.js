function  ajaxSaveManagerDeal(){
    if($("#dealDescribeId").val().trim().length==0){
        $(".maskContent").text("请输入答复内容");
        $("#mask").show();
        $("#sureId").bind("click",hide);
        return;
    }

    if($("img").length>6){
        $(".maskContent").text("图片最多可以上传6张");
        $("#mask").show();
        $("#sureId").bind("click",hide);
    return;
    }else{

        $("#sureId").bind("click",toList);

    }




  //  $(".one_btn").unbind("click");
    
    $("#mask2").show();
       /*$.ajax({
            url: baseAppUrl+"manager/project-issue/saveManagerDeal",
           type: "post",
           data:$("#jvForm").serialize(),
           success:function(data){
                if(data==1){

                    $(".maskContent").text("投诉问题处理完毕");
                    $("#mask").show();



                }else if (data==2){
                    $(".maskContent").text("您已经处理过该投诉项了");
                    $("#mask").show();

                }else {
                    $(".maskContent").text("网络不好,请稍后再试");
                    $("#mask").show();
                }




           }



       })*/

}


function toList(){

    window.location.href=baseAppUrl+"manager/project-issue/checkIssueProblemByOrderId?orderId="+orderId;


}
function hide(){

    $("#mask").hide()
}

function checkIsIllegal(){

    var val =$("#dealDescribeId").val();
    if(val.trim().length>100){

        $("#dealDescribeId").val(val.substring(0,100));

    }



}


