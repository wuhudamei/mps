function pqcDoneInfo(obj){


    var cutContentCont = $(obj).parent().find('.style_hide_cont');
    console.log(cutContentCont)
    if(cutContentCont.is(":visible")) {

        $(obj).find('.icon_arrow').removeClass('icon_arrow_up').addClass('icon_arrow_down');
        $(obj).parent().find(".style_hide_cont").hide();
    }else {

        var orderIdVal =$("#orderId").val();
         var orderMarkVal =$("#orderMark").val();

        if(1==orderMarkVal && undefined !=orderIdVal &&0 !=orderIdVal){

            $.ajax({

                url:baseUrl+"/selectFullOrder/selectFullOrder/pqcDetail?orderId="+orderIdVal,
                type:"get",
                success:function(data){

                    if(data.length>0){
                        var html ="";

                        for(var v =0;v<data.length;v++){


                            html+="<tr><td>"+data[v].qcCheckNodeName+"</td> <td>"+data[v].managerApplyDate+"</td> <td>"+data[v].managerExpectPqcDate+"</td> <td>"+data[v].pqcAcutalCheckDate+"</td> <td>"+data[v].pqcDoneDate+"</td> </tr>";

                        }
                        $(obj).parent().find("tbody").html(html);

                        $(obj).find('.icon_arrow').removeClass('icon_arrow_down').addClass('icon_arrow_up');

                        $(obj).parent().find(".style_hide_cont").show();
                    }else{
                        $("#alertRemarks").text("该订单没有质检部分数据");
                        $("#subReport").show();
                        //提示没有找到数据

                    }




                }



            })



        }else{
            $("#alertRemarks").text("请点击查询按钮");
            $("#subReport").show();
            //提示查询

        }

    }




}
function getRootPath(){
    //获取当前网址，如： http://localhost:8088/test/test.jsp
    var curPath=window.document.location.href;
    //获取主机地址之后的目录，如： test/test.jsp
    var pathName=window.document.location.pathname;
    var pos=curPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8088
    var localhostPaht=curPath.substring(0,pos);
    //获取带"/"的项目名，如：/test
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}