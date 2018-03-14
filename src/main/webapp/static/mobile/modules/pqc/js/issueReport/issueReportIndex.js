
function findIssueReportIndex(){

   var text= $("#textId").val();
   if(text==undefined){

       text='';
   }
html='';

    $.ajax({
        url:baseUrl+"/app/pqc/issueReport/issueReportIndex?text="+text.trim(),
        success:function(data){
            $(".pad_top11").html('<div class="mar_btm14 font0 search-area"><input class="search-box" type="text" placeholder=" 小区名称、客户姓名、项目经理" id="textId" > <a onclick="findIssueReportIndex()" class="search-btn" href="javascript:;"></a></div>');

            if(data.length>0){
                var length =data.length;
                for(var v=0;v<length;v++){


    html+='<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">'
    +'<ul class="pad_top3 pad_left3 bor_dotted">'
    +'<li class="mar_btm24 clearfix">'
    +'<span class="col_grey font28 flo_left spanRgt">顾客信息：</span>'
    +' <p class="font28 col_3 pad_rgt3 flow">'+data[v].customerInfo+'</p>'
    +' </li>'
    +' <li class="mar_btm24 clearfix">'
    +'    <span class="col_grey font28 flo_left spanRgt">项目经理：</span>'
    +'   <p class="font28 col_3 pad_rgt3 flow">'+data[v].managerName+'</p>'
    +'</li>'
    +'<li class="mar_btm24 clearfix">'
    +'<span class="col_grey font28 flo_left spanRgt">约检内容：</span>'
    +'<p class="font28 col_3 pad_rgt3 flow">'+data[v].qcCheckNodeName+'</p>'
    +'</li>'
    +' <li class="mar_btm24 clearfix">'
    +'<span class="col_grey font28 flo_left spanRgt">约检日期：</span>'
    +'<p class="font28 col_3 pad_rgt3 flow">'+data[v].qcExpectCheckDate+'</p>'
    +'</li>'
    +'<li class="mar_btm24 clearfix">'
    +' <span class="col_grey font28 flo_left spanRgt">约检状态：</span>'
    +'<p class="font28 col_3 pad_rgt3 flow">'+data[v].qcStatus+'</p>'
    +' </li>'
    +' </ul>'
    +' <div class="btn_wrapper clearfix">'
    +' <a class="two_btn1" href="'+baseUrl+'/app/pqc/issueReport/issueReport?qcId='+data[v].qcId+'">问题上报</a>'
    +' <a class="two_btn2"   href="'+baseUrl+'/app/pqc/issueReport/issueReportRecord?qcId='+data[v].qcId+'">上报记录</a>'
    +'  </div>'
    +'   </div>'

                }


             $(".pad_top11").append(html);
                $("#textId").val(text)

            }

        }



    })


}