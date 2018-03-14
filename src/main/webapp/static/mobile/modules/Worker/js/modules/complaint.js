

//确认接收, 更新该工人处理记录,添加log
function  applyProblem(handleId){

$.ajax({
    url : baseWorkerUrl+"project-issue/saveWorkerHandle",
    type :"post",
    data: {handleId :handleId},

    success:function (data) {

        if(data==1){

            $("#mask").show();
        }else{
            $(".maskContent").text("网络不好,请稍后再试...");
            $("#mask").show();

        }

    }


})





}

function toList() {
    window.location.href=baseWorkerUrl+"project-issue/list.php";
}