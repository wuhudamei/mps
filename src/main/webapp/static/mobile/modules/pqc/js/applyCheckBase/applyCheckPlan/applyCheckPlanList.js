function serachOrderInfoBytext(){

var text = $(".search-box").val();
    var url = basePqcUrl +"/apply-check-plan/list.php";
var html ='';
    $.ajax({


        url: url,
        data: {text:text},
        type: "get",
        dataType:"json",
        beforeSend:function(){
            run_waitMe("查询中...请稍等...");


        },

        success:function(data){

            $('body').waitMe('hide');

          html+='<div class="mar_btm14 font0 search-area">'+
              '<input class="search-box" type="text" placeholder="小区名称、客户姓名或项目经理">'+
              ' <a class="search-btn" href="javascript:;" onclick="serachOrderInfoBytext()"></a>'+
              ' </div>';
          var length = data.length;
            for(var index=0;index<data.length;index++){

                html+='<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">'
                     +'<ul class="pad_top3 pad_left3 bor_dotted">'
                    + '<li class="mar_btm24 clearfix">'
                    + '<span class="col_grey font28 flo_left spanRgt">顾客：</span>'
                    + '<p class="font28 col_3 pad_rgt3 flow">'+data[index].customerAddressInfo+'</p>'
                    + '</li>'
                    + '<li class="mar_btm24 clearfix">'
                    + '<span class="col_grey font28 flo_left spanRgt">客户手机：</span>'
                    + '<p class="font28 col_3 pad_rgt3 flow">'+data[index].customerPhone+'</p>'
                    + '</li>'
                    + '<li class="mar_btm24 clearfix">'
                    + '<span class="col_grey font28 flo_left spanRgt">项目经理：</span>'
                    +  '<p class="font28 col_3 pad_rgt3 flow">'+data[index].managerName+"-"+data[index].managerPhone+'</p>'
                    +  '</li>'
                    +  '<li class="mar_btm24 clearfix">'
                    +  '<span class="col_grey font28 flo_left spanRgt">实际开工日期：</span>'
                    +  '<p class="font28 col_3 pad_rgt3 flow">'+data[index].actualStartDate+'</p>'
                    +  '</li>'
                    +  '</ul>'
                    +  '<div class="clearfix">'
                    +  '<a class="one_btn" href="'+basePqcUrl+'/apply-check-plan/'+data[index].orderId+'">约检计划</a>'
                    +  '</div>'
                    +  '</div>';


        }
        $(".pad_top11").html(html);

    },error:function(data){
                console.log(data);
            $('body').waitMe('hide');
            $('body').waitMe('hide');
            globelAlert("网络不好...",2.0);

        }




})
}