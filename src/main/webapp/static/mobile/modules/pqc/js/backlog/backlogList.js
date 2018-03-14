var totalBacklogCount=0;
var  backUrl  =window.location.href;
var  encodeBackUrl =  encodeURIComponent(backUrl)
function orderPackageReCheck(){




var ajaxUrl =basePqcUrl+"/backlog/pack-recheck-info.php"
    var html='';


$.ajax({

    url:ajaxUrl,
    type:"get",
    async:false,
    dataType:"json",
    beforeSend:function(){

        run_waitMe("任务包复核待办数据加载中...请稍等..")

    },success:function(data){
        closeWaitMe("body");

           if(data.length>0) {

               html='';
               html+='<h2><i class="img2"></i>任务包复核<span id="packRecheckId"></span></h2><div class="todo_list">'
               var length  =data.length;

               //总待办数量
               totalBacklogCount+=length;

               for(var v=0;v<length;v++){

                   html+='<div class="todo_infos">'
                      +' <p style="padding: .24rem 2rem .24rem 0;line-height: .52rem;">【'+data[v].packName+'】'+data[v].customerAddressInfo+'</p >'
                       +'  <a href="'+basePqcUrl+'/taskpackagerecheck/toTaskpackageRecheck?id='+data[v].packId+'&backUrl='+encodeBackUrl+'">去复核</a>'
                       +' </div>';





               }

                html+='</div>';
                
               $("#packRecheckH2Id").html(html);

               $("#packRecheckId").html(length+"个"+'<em class=""></em>');


           }else{
               html='';
               html+='<h2><i class="img2"></i>任务包复核<span id="packRecheckId"></span></h2><div class="todo_list">'
               html+='</div>';
               $("#packRecheckH2Id").html(html);
               $("#packRecheckId").html(0+"个"+'<em class=""></em>');

           }


    }



})



}




















function applyCheckDone(){

    var ajaxUrl =basePqcUrl+"/backlog/apply-check-done.php"
    var html='';
    $.ajax({

        url:ajaxUrl,
        type:"get",
        async:false,
        dataType:"json",
        beforeSend:function(){

            run_waitMe("约检验收待办数据加载中...请稍等..")

        },success:function(data){

            closeWaitMe("body");

            if(data.length>0) {
                html='';
                var length  =data.length;

                html+='<h2><i class="img1"></i>约检验收<span id="applyCheckDoneId"></span></h2><div class="todo_list">'

                totalBacklogCount+=length;
                for(var v=0;v<length;v++){



                    html+=' <div class="todo_infos">'
                        +' <div class="infos">'
                        +' <span class="name">顾客：</span>'
                        +' <p class="address">'+data[v].customerAddressInfo+'</p>'
                        +'  </div>'
                        +' <div class="infos">'
                        +' <span class="name">约检节点：</span>'
                        +' <p class="address">'+data[v].qcNodeName+'</p>'
                        +' </div>'
                        +' <a href="'+basePqcUrl+'/checkList/list?customerName='+data[v].customerName+'&backUrl='+encodeBackUrl+'">去验收</a>'
                        +' </div>';


                }

                    html+='</div>';
                    $("#applyCheckDoneH2Id").html(html);

                    $("#totalBacklogId").text(totalBacklogCount);
                    $("#applyCheckDoneId").html(length+"个"+"<em></em>");

            }else{
            	  html='';
            	
                  html+='<h2><i class="img1"></i>约检验收<span id="applyCheckDoneId"></span></h2><div class="todo_list">'
                	  html+='</div>';
                 
                  $("#applyCheckDoneH2Id").html(html);
                $("#totalBacklogId").text(totalBacklogCount);
                $("#applyCheckDoneId").html(0+"个"+"<em></em>");

            }




        }



    })



}




