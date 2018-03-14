<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="viewport"
		  content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>施工变更</title>
	<link rel="stylesheet" type="text/css"
		  href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
	<link rel="stylesheet" type="text/css"
		  href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
	<link rel="stylesheet" type="text/css"
		  href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
	<link rel="stylesheet" type="text/css"
		  href="${ctxStatic}/mobile/modules/Manager/css/change_details.css" />
	<link rel="stylesheet" type="text/css"
		  href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/quesDone.css" />
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
        $(function(){
            $("#picWrapperDiv").hide();
            var count = $(".pic_container > div").size() - 1;
            $("#showPicId").html('客户签字凭证图片('+count+')');


            $("#showPicId").click(function(){
                $("#picWrapperDiv").show();
                return false;
            });

            $("#goToUploadPic").click(function(){
                showDiv();
                $(".alertMask1").hide();
            });

            $(".back_btn").click(function(){
                $("#picWrapperDiv").hide();
                var count = $(".pic_container > div").size()-1;
                $("#showPicId").html('客户签字凭证图片('+count+')');
            });

        });
		/* function hiddenDiv(){
		 $("#picWrapperDiv").hide();
		 var count = $(".pic_container > div").size();
		 $("#spanPic").html('客户签字凭证图片('+count+')');
		 } */

        function showDiv(){
            $("#picWrapperDiv").show();
        }

        var addOrMinus;
        var addOrMinusNum;
        var abc;
        function addAndMinus(a,b) {
            addOrMinus = a;

            var arr = '';
            abc = new Array();
            $($(".bg_light").find("input[name=itemId]")).each(function () {
                var temp = $(this).val();
                abc.push(temp)
            })
            if(b == '2'){
                $("#myformDiv").css("display","none");
                $("#addDiv").show();
                addOrMinusNum = 1;
            }else{
                $("#mymask").show();
                addOrMinusNum = 2;
            }


        }

        function hideMyalert() {
            $("#alertMasksu").hide();
            $("#mymask").hide();

        }

        function hideMask(a) {
            $("#mymask").hide();
            $("#myformDiv").css("display","none");

            if(a == 'innerDiv'){
                $("#innerDiv").show();
            }
            if(a == 'outerDiv'){
                $("#outerDiv").show();
            }
        }
	</script>
	<style>
		#bitian{line-height: .66rem;}
		#showPicId{width:3.2rem;line-height:.66rem;background:#0780ec;font-size:.28rem;color:#fff;border:none;border-radius:.35rem;float:right;}
		#toPicDetails{width:3.2rem;line-height:.66rem;background:#0780ec;font-size:.28rem;color:#fff;border:none;border-radius:.35rem;float:right;}
		.two_btn_act{background: #b1b1b1;text-align:center;}
		/*mask style*/
		.pad_btm40{padding-bottom:.4rem;}
		.alertMask{z-index:99;position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 84%;margin: 30% auto 0;border-radius: .1rem;background: #fff;font-size: .32rem;}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: .28rem;}
		.font33{font-size: .33rem;}
		.maskTit{height: 1rem;line-height: 1rem;text-align: center;}
		.maskContent{padding: .5rem;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: .2rem;padding-top: .2rem;}
		.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
			text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
		.maskBtnOne{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
			text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
		.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}

		.pad_btm40{padding-bottom:.4rem;}
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 84%;margin: 30% auto 0;border-radius: .1rem;background: #fff;font-size: .32rem;}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: .28rem;}
		.font33{font-size: .33rem;}
		.maskTit{height: 1rem;line-height: 1rem;text-align: center;}
		.maskContent{padding: .5rem;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: .2rem;padding-top: .2rem;}
		/* .maskBtn{display: block;width: 60%;} */
		.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
			text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
		.maskBtnOne{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
			text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
		.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}

		.picWrapper{z-index:990;background:#b1b1b1;position:fixed;top:0;left:0;width:100%;bottom:0;}
	</style>
</head>
<body>
<div class="g-review">
	<header>
		<a class="back_btn"  onclick="history.go(-1)" href="#"
		></a>
		<h2 class="title">施工变更</h2>
	</header>
	<form id="submitFormId">
		<section class="pad_top10 mar_btm50 font0" id = "myformDiv">
			<div class="mar_left3 mar_rgt3 mar_top1">
				<span class="font28 co_3" id="bitian">变更原因<em
						class="font24 co_6">（必填）</em>：
				</span>
				<c:if test="${bill.signaturePic !=0}">
					<%-- <button id="toPicDetails"><span class="font28 co_0780ec flo_rgt" id="goToPic">客户签字凭证图片 (${bill.signaturePic})</span></button> --%>
					<button id="toPicDetails">客户签字凭证图片 (${bill.signaturePic})</button>
				</c:if>
				<c:if test="${bill.signaturePic ==0}">
					<!-- <a id="showPicId" href="javascript:void(0)"><button><span class="font28 co_0780ec flo_rgt" id="spanPic">客户签字凭证图片</span></button></a> -->
					<button id="showPicId">客户签字凭证图片</button>
				</c:if>
				<textarea class="reason_area" name="changeReason" id="changeReason" >${bill.changeReason }</textarea>
			</div>
			<!-- 具体项 -->

			<section class="after undis" id="afterSection">

				<c:forEach items="${itemList }" var="item">
					<ul id="${item.itemId}" class="pad_left3 pad_rgt3 pad_top3 pad_btm3 mar_top3 bg_f shadow">
						<input  type="text"  hidden ="hidden" name="itemId" value="${item.itemId}" />
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">施工项　：</span>
							<input class="font24 col_6 wid_476 grey_border pad_top1 pad_btm1 mar_rgt2 bg_grey" value="${item.itemName }" readonly="readonly" type="text" />
						</li>
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">套餐类型：</span>

							<c:if test="${item.groupType == '1' }">	<span class="font24 col_3">套餐内</span></c:if>
							<c:if test="${item.groupType == '2' }">	<span class="font24 col_3">套餐外</span></c:if>

						</li>
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">单　　位：</span>
							<span class="font24 col_3">${item.itemUnit }</span>
						</li>
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">单　　价：</span>
							<input type="text" hidden="hidden" name="price" value="${item.itemPrice }" />
							<span class="font24 col_3 price">${item.itemPrice }</span>
						</li>
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">数　　量：</span>
							<input readonly="readonly" class="itemCount font24 col_6 wid_200 grey_border pad_top1 pad_btm1 mar_rgt2  bg_grey" type="text" value="${item.itemCount }" name="itemCount" onchange="setToal()"  onafterpaste="this.value=this.value.replace(/[^0-9]/g,&apos;&apos;)">
						</li>
						<li class="mar_btm5 clearfix">
							<span class="font28 col_6">金　　额：</span>
							<input name="totalMoney" class="totalMoney font24 col_6 wid_200 grey_border pad_top1 pad_btm1 mar_rgt2 bg_grey" type="text" readonly  value="<fmt:formatNumber value="${item.itemCount * item.itemPrice }" pattern="0.00" ></fmt:formatNumber>" />
						</li>
						<li class="clearfix">
							<span class="font28 col_6">说　　明：</span>
							<input class="font24 col_6 wid_476 grey_border pad_top1 pad_btm1 mar_rgt2  bg_grey" type="text" value="${item.itemDescription }" name="itemDetail">
						</li>
						<a class="del_cross" href="javascript:void(0)"><img src="${ctxStatic}/mobile/modules/Manager/images/del_cross.png" alt=""></a>
						<c:if test="${item.itemWay == '1' }">		<div class="blue">增</div></c:if>
						<c:if test="${item.itemWay == '2' }">	<div class="blue">减</div></c:if>
						<input type="hidden" name="addOrMinusNum" value="${item.itemWay}"/>
					</ul>

				</c:forEach>



			</section>
			<input  type="text"  hidden ="hidden" name="projectChangeId" value="${bill.projectChangeId}" />
			<input  type="text"  hidden ="hidden" name="orderId" value="${bill.orderId}" />
		</section>
	</form>
	<footer>
		<a href="javascript:void(0)" onclick="addAndMinus('增',2)"><span class="btn font28 bor_rht btn1">增项</span></a>
		<a href="javascript:void(0)" onclick="addAndMinus('减',1)"><span class="btn font28 bor_rht btn2">减项</span></a>
		<a href="javascript:void(0)"><span class="btn font28 btn3"
										   onclick="submit()">提交</span></a>
	</footer>
	<div class="mask mask1 undis" id = "mymask" style="height: 500%;">
		<div class="btn_wraper">
			<a class="reduce_btn btn1_1 border_btm" href="javascript:void(0)" onclick="hideMask('innerDiv')">套餐内减项</a>
			<a class="reduce_btn btn1_2 mar_btm2" href="javascript:void(0)" onclick="hideMask('outerDiv')">套餐外减项</a>
			<a class="reduce_btn btn1_3" href="javascript:void(0)" onclick="hideMyalert()">取消</a>
		</div>
	</div>

	<form id="addForm">
		<div class="mask mask2 undis" id="addDiv">
			<div class="g-item">
				<header>
					<h2 class="title">施工项增项列表</h2>
				</header>
				<section class="item">
					<!-- 施工项 和 分类 -->
					<ul id="add">
						<input type="hidden" id = "myInput" name="arr"/>
						<input type="hidden" name="storeId" value="${storeId}"/>
						<c:forEach items="${list }" var="typeNameList" varStatus="status">
						<li class="clearfix font28" id="li${status.index+1}"><p
								class="font28 borBtm relative hgt86 pad_left30">
							<input type="checkbox" id="build"  >
							<span data-name="constr" for="build">${typeNameList.itemTypeName }</span><a
								class="showAll" onclick="showAll('${typeNameList.itemTypeId}','add','${status.index+1}')">展开</a>
						</p>
							<ul class="item_bar undis">

							</ul>
							</c:forEach>
						</li>
					</ul>
				</section>
				<footer>
					<a class="btn_a btn2_1" href="javascript:void(0)"><span
							class="mask_btn bor_rht" href="javascript:void(0)"
							onclick="hideUndis('addDiv')">取消</span></a> <a class="btn_a btn2_2"
																		   href="javascript:void(0)"><span class="mask_btn bor_rht"
																										   href="javascript:void(0)" onclick="saveItem('addForm')">保存</span></a>
				</footer>
			</div>
		</div>

	</form>


	<form id="innerForm">
		<div class="mask mask2 undis" id="innerDiv">
			<div class="g-item">
				<header>
					<h2 class="title">施工项减项套餐内列表</h2>
				</header>
				<section class="item">
					<!-- 施工项 和 分类 -->
					<ul class="container-ul" id="inner">
						<input type="hidden" id = "myInput1" name="arr"/>
						<input type="hidden" name="storeId" value="${storeId}"/>
						<c:forEach items="${list }" var="typeNameList" varStatus="status">
						<li class="clearfix font28" id="li${status.index+200}"><p
								class="font28 borBtm relative hgt86 pad_left30">
							<input type="checkbox" id="build">
							<span data-name="constr" for="build">${typeNameList.itemTypeName }</span><a
								class="showAll" href="javascript:void(0)" onclick="showAll('${typeNameList.itemTypeId}','inner','${status.index+200}')">展开</a>
						</p>
							<ul class="item_bar undis">

							</ul>
							</c:forEach>
					</ul>
				</section>
				<footer>
					<a class="btn_a btn2_1" href="javascript:void(0)"><span
							class="mask_btn bor_rht" href="javascript:void(0)"
							onclick="hideUndis('innerDiv')">取消</span></a> <a class="btn_a btn2_2"
																			 href="javascript:void(0)"><span class="mask_btn bor_rht"
																											 href="javascript:void(0)" onclick="saveItem('innerForm')">保存</span></a>
				</footer>
			</div>
		</div>
	</form>



	<form id="outerForm">
		<div class="mask mask2 undis" id="outerDiv">
			<div class="g-item">
				<header>
					<h2 class="title">施工项减项套餐外列表</h2>
				</header>
				<section class="item">
					<!-- 施工项 和 分类 -->
					<ul class="container-ul" id="outer">
						<input type="hidden" id = "myInput2" name="arr"/>
						<input type="hidden" name="storeId" value="${storeId}"/>
						<c:forEach items="${list }" var="typeNameList" varStatus="status">
						<li class="clearfix font28" id="li${status.index+500}"><p
								class="font28 borBtm relative hgt86 pad_left30">
							<input type="checkbox" id="build" >
							<span data-name="constr" for="build">${typeNameList.itemTypeName }</span><a
								class="showAll" href="javascript:void(0)"  onclick="showAll('${typeNameList.itemTypeId}','outer','${status.index+500}')">展开</a>
						</p>
							<ul class="item_bar undis">

							</ul>
							</c:forEach>


					</ul>
				</section>
				<footer>
					<a class="btn_a btn2_1" href="javascript:void(0)"><span
							class="mask_btn bor_rht" href="javascript:void(0)"
							onclick="hideUndis('outerDiv')">取消</span></a> <a class="btn_a btn2_2"
																			 href="javascript:void(0)"><span class="mask_btn bor_rht"
																											 href="javascript:void(0)" onclick="saveItem('outerForm')">保存</span></a>
				</footer>
			</div>
		</div>

	</form>
</div>


<div class="alertMask alertMask1 undis">
	<div class="maskWrapper">
		<p class="col_3 maskTit">提示</p>
		<div class="font28 col_6 maskContent">上传客户签字凭证图片后才能提交施工变更！！</div>
		<div class="maskBtns clearfix twoBtns">
			<a class="maskBtnOne font33 col_fdfcfa" href="javascript:void(0);" id="goToUploadPic">去上传图片</a>
		</div>
	</div>
</div>

<div class="font0 picWrapper" id="picWrapperDiv">
	<header>
		<a class="back_btn" href="javascript:void(0);"></a>
		<h2 class="title">查看图片</h2>
	</header><!-- /header -->
	<section style="padding-top:.6rem;">
		<div class="bg_f pad_top11">
			<div class="pics pic_container font0 clearfix">
				<div class="pic camera" id="camera" href="javascript:void(0)">
					<img src="${ctxStatic}/mobile/modules/Manager/images/upPic.png" alt="">
					<input type="file" accept="image/*" onchange="preview(this)">
				</div>
				<input type="text" hidden="hidden" id="shit" value="1">
			</div>

		</div>
	</section>
</div>

<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
<script type="text/javascript">


    function showAll(typeId,str,liId){

        if('1'==$(("#li"+liId)).val()){
            return;
        }


        $.get("${ctx}/app/manager/changeManagement/findItemByTypeId",{typeId:typeId,addOrMinus:str,storeId:${storeId}},function(data){

            if(data==null||data.length<1){
                alert('该分类暂无变更项');

            }else{
                var html ='';
                for(var v=0;v<data.length;v++){
                    html+='<li class="hgt86 wid_per90 borBtm mar_left10" id="li'+data[v].itemId+'"><input type="checkbox" id="one"><input type="text" hidden="hidden" name="itemId" value="'+data[v].itemId+'" /> <label onclick="deletePrice(this)" name="'+data[v].itemId+'" data-name="one" for="one">'+data[v].itemName+'</label></li>'

                }
                $(("#li"+liId+" ul")).append(html);
                $(("#li"+liId)).val("1");


            }


        })
    }
    //图片上传显示
    function preview(file) {
        var shit = $("#shit").val();
        var prevDiv = $('.camera');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function(evt){
                $('<div class="pic"><img style="width:100%;height:100px;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><a class="del_pic" href="javascript:void(0)">删除</a></div>').appendTo('.pic_container');
            }
            reader.readAsDataURL(file.files[0]);
        }else {
            prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
            console.log(file,file.value);
        }
    }

    //删除按钮
    $(document).on('click', '.del_pic', function(){
        var numReal=$(this).prev().attr("id");
        $(("#num"+numReal)).remove();

        var num = $("#num").val();
        num--;
        $("#num").val(num);

        //$(this).parent().remove();
        $(this).parent().remove();
        //$("#num").val("0");
    });


    function uploadpic(pic){

        var hiddenForm = document.getElementById("submitFormId");
        var input =document.createElement("input");


        var num = $("#num").val();
        var shit = $("#shit").val();

        if(pic){
            num++;
            input.setAttribute("id","num"+shit);
            input.setAttribute('hidden', 'hidden');
            input.setAttribute('name', 'photo');
            input.setAttribute("type","text");
            input.setAttribute("value", pic);
            hiddenForm.appendChild(input);
            $("#num").val(num);
            shit++;
            $("#shit").val(shit);
        }
    }







    //提交

    function  submit(){
        var size = $("#afterSection > ul").size();
        if(size<1){
            globalUtil.fn.alert('您还没有选择增减项',2.0);
            return;
        }



        var x = 1;
        if(($("#changeReason").val()=="")){

            globalUtil.fn.alert('变更原因 为必选项',2.0);
            $("#bitian").css("color","red");
        }else{
            $(".after ul").each(function(){
                var num =	parseFloat($(this).find('input[class*=itemCount]').val());

                if(isNaN(num)){
                    globalUtil.fn.alert('请检查变更项数量是否填写',2.0);
                    x++;
                }else {
                    $(this).find('input[class*=itemCount]').val(num)
				}



            })

            if(x>1){

                return;
            }

            var count = $(".pic_container > div").size();
            var picConut='${bill.signaturePic}';
            if(picConut >0 || (count != null && count>=2)){

                $(".btn").css("color","#CCC")
                $("footer").hide();
                globalUtil.fn.alert('正在提交..请稍后..',2.0);
                var options ={
                    url: "${ctx}/app/manager/changeManagement/updateChangeForm",
                    type: "post",
                    success:function(data){
                        if(data=="1"){
                            //成功
                            globalUtil.fn.alert('更新变更单成功',2.0);
                            window.location.href="${ctx}/app/manager/changeManagement/applyRecord?orderId=${bill.orderId}&storeId=${storeId}";
                        }else{
                            //失败
                            globalUtil.fn.alert('数据异常,请联系开发人员',2.0);
                        }


                    }
                }

                $("#submitFormId").ajaxSubmit(options);
            }else{
                $(".alertMask1").show();
            }
        }
    }
    //校验数量
    function fix_amountthis(obj){
        //先把非数字的都替换掉，除了数字和.
        obj.value = obj.value.replace(/[^\d.]/g,"");
        //必须保证第一个为数字而不是.
        obj.value = obj.value.replace(/^\./g,"");
        //保证只有出现一个.而没有多个.
        obj.value = obj.value.replace(/\.{2,}/g,".");
        //保证.只出现一次，而不能出现两次以上
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        //只能输入两个小数
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
        //只能输入6个整数
        var reg = /.*\..*/;
        if(reg.test(obj.value)){
            var aa = obj.value.substring(0,obj.value.indexOf("."));
            if(aa.length>6){
                obj.value = obj.value.substring(0,6);

            }
        }else{
            if(obj.value.length>6){
                obj.value = obj.value.substring(0,6);
            }
        }
    }

    //    -------------------------------------------------

    function checkedNum(a) {
        fix_amountthis(a);
        var num = $(a).val();
		/*  if(num == 0){
		 $(a).val(null);
		 /!*alert("请输入大于零的数字！")*!/
		 }*/

        var s = $(a).attr("id");
        var li = $("ul[id="+s+"]");

        var s = $(li).find("input[class*=totalMoney]").val();

        var price = parseFloat($(li).find(
            'span[class*=price]').text());
        console.log(price)
        if (isNaN(num)) {
            $(li).find('input[class*=totalMoney]').val(0);
        } else {
            $(li).find('input[class*=totalMoney]').val(
                (num * price).toFixed(2));
        }
    }




    function setToal(){
        $(".after ul").each(function(){
            var num =	parseFloat($(this).find('input[class*=itemCount]').val());
            var price = parseFloat($(this).find('span[class*=price]').text());
            var reg =/^[1-9]\d*(\.\d+)?$/;
            var isMoneyFormatRight = reg.test(num);
            if(!isMoneyFormatRight){

                globalUtil.fn.alert('请输入正确的数量,支持两位小数', 2.0);
                return;
            }
            $(this).find(
                'input[class*=itemCount]').val(num)
            var price = parseFloat($(this).find(
                'span[class*=price]').text());
            if(isNaN(num)){
                $(this).find('input[class*=totalMoney]').val(0);

            }else{

                $(this).find('input[class*=totalMoney]').val((num*price).toFixed(2));
            }

        })

    }



    function alertPulas(a) {
        alert(a)
    }

    //取消
    function hideUndis(divName){


        $(".after").removeClass('undis');
        $("#" + divName).hide();
        $("#myformDiv").css("display","block");
    }

    function deletePrice(a) {

    }

    function deletePrice1(a) {
        var s = $(a).attr("name");
        var b = $("#"+s).find("input[name=totalMoney]").val();
        if(b >= 0){
            alertPulas("你已经选择此施工项,不可以重复添加此施工项！")
        }else{
            $(a).parent().addClass('bg_light');
            $(a).attr('class', 'checked');
        }
    }

    function closePrice(a) {
        var s = $(a).attr("id");
        $($(".bg_light")).each(function () {
            var temp = $(this).find("input[name=itemId]").val();
            if(s == temp){
                $(this).find("label[class*=checked]").removeClass("checked");
                $(this).removeClass("bg_light");
            }
        })
    }



    function saveItem(formId){

        $("#mymask").hide();
        $("#addDiv").hide();
        $("#innerDiv").hide();
        $("#outerDiv").hide();
        $("#myformDiv").css("display","block");

        //去掉没有选的选项
        $(".item_bar label").each(function() {
            if ($(this).hasClass("checked")) {
                var item = $(this).prev();
                $(item).attr("name","itemId")
            } else {
                var item = $(this).prev();
                $(item).removeAttr("name");

            }

        })
        $("#myInput").val(abc);

        $("#myInput1").val(abc);
        $("#myInput2").val(abc);

        var html ="";
        var options ={
            url: "${ctx}/app/manager/changeManagement/saveItem",
            type: "post",
            success:function(data){

                if(null!=data){
                    for(var v =0;v<data.length;v++){
                        var taocan;
                        if(data[v].groupType==1){
                            taocan="套餐内";

                        }else{

                            taocan="套餐外";
                        }

                        html += '<ul id ="'+data[v].itemId+'" class="pad_left3 pad_rgt3 pad_top3 pad_btm3 mar_top3 bg_f"><li class="mar_btm5 clearfix"> <input type="text" hidden="hidden" name="itemId" value="'+data[v].itemId+'" /> <span class="font28 col_6">施工项　：</span><input class="font24 col_6 wid_476 grey_border pad_top1 pad_btm1 mar_rgt2 bg_grey" value="'+data[v].itemName+'" readonly="readonly" type="text" /></li><li class="mar_btm5 clearfix"><span class="font28 col_6">套餐类型：</span><span class="font24 col_3">'
                            + taocan
                            + '</span></li><li class="mar_btm5 clearfix"><span class="font28 col_6">单　　位：</span><span class="font24 col_3">'
                            + data[v].itemUnit
                            + '</span></li><li class="mar_btm5 clearfix"><span class="font28 col_6">单　　价：</span> <input type="text" name="price" hidden="hidden" value="'+data[v].itemPrice+'" />   <span class="price font24 col_3" >'
                            + data[v].itemPrice
                            + '</span></li><li class="mar_btm5 clearfix"><span class="font28 col_6">数　　量：</span><input class="itemCount  font24 col_6 wid_200 blue_border pad_top1 pad_btm1 mar_rgt2" type="text" name="itemCount" id ="'+data[v].itemId+'" onkeyup="checkedNum(this)" onafterpaste="this.value=this.value.replace(/[^0-9]/g,&apos;&apos;)" ><span class="countWarn" hidden="hidden">数量不允许为空</span></li><li class="mar_btm5 clearfix"><span class="font28 col_6">金　　额：</span><input class="totalMoney font24 col_6 wid_200 grey_border pad_top1 pad_btm1 mar_rgt2 bg_grey" type="text" readonly="readOnly" value="0" name="totalMoney"></li><li class="clearfix"><span class="font28 col_6">说　　明：</span><input class="font24 col_6 wid_476 blue_border pad_top1 pad_btm1 mar_rgt2" type="text" name="itemDetail"></li>' +
                            '<a class="del_cross" id ="'+data[v].itemId+'" onclick="closePrice(this)" href="javascript:void(0)"><img src="${ctxStatic}/mobile/modules/Manager/images/del_cross.png" alt=""></a><div class="blue">'
                            + addOrMinus + '</div>' +
                            '<input hidden="hidden" name="addOrMinusNum" value="'+addOrMinusNum+'" /></ul>';




                    }
                    $("#afterSection").append(html);

                }
            }
        }
        $("#"+formId).ajaxSubmit(options);

    }


    window.onload=(function(){
        $("#afterSection").removeClass("undis");
        var arrObj = {};
        var bindClick = function (liId, selArr,ulName){
            $(document).on('click', '#'+ulName+' #'+liId+' ul label', function(){
                if($(this).attr('class') === 'checked'){
                    // unselect
                    $(this).attr('class', '');
                    $(this).parent().removeClass('bg_light');
                    $('#'+ulName+' #'+liId+' p label').attr('class','');
                    $('#'+ulName+' #'+liId+' p').removeClass('bg_light');
                }else{
                    // select
                    deletePrice1(this);
                    var isAllTrue = '';
                    $.each($(this).parents('#'+ulName+' #'+liId).find('li label'), function(i, v){
                        if ($(v).attr('class') === "checked") {
                            isAllTrue += '1';
                        }else{
                            isAllTrue += '0';
                        }
                    });
                    if (isAllTrue.indexOf('0') > -1) {
                        $('#'+ulName+' #'+liId+' p label').attr('class','');
                        $('#'+ulName+' #'+liId+' p').removeClass('bg_light');
                    }else{
                        $('#'+ulName+' #'+liId+' p label').attr('class','checked');
                        $('#'+ulName+' #'+liId+' p').addClass('bg_light');
                    };
                }
                var theitem = liId;
                selArr.length = 0;
                $.each($('#'+ulName+' #'+liId+' ul label'), function(i, v){
                    if ($(v).attr('class') === 'checked') {
                        selArr.push($(v).parent().find('input').val());
                    }
                });
                console.log(arrObj);
            });

            $(document).on('click', '#'+ulName+' #'+liId+' p label', function(){
                var b = $(this).parent().parent().find('label').attr('class');
                if (b === 'checked') {
                    $(this).parent().parent().find('label').attr('class', '');
                    $('#'+ulName+' #'+liId+' ul label').attr('class', '');
                    selArr.length = 0;
                    $(this).parent().removeClass('bg_light');
                    $(this).parent().parent().find('li').removeClass('bg_light');
                }else{
                    $(this).parent().addClass('bg_light');
                    $(this).parent().parent().find('li').addClass('bg_light');
                    $(this).parent().parent().find('label').attr('class', 'checked');
                    $('#'+ulName+' #'+liId+' ul label').attr('class', 'checked');
                    $.each($('#'+ulName+' #'+liId+' ul label'), function(i, v){
                        selArr.push($(v).parent().find('input').val());
                    });
                }
                console.log(arrObj);
            });
        }
        for (var i = 0, len = $('#add > li').size(); i < len; i++) {
            var liId = $('#add > li').eq(i).attr('id');
            arrObj['selArrli'+i]=[];
            bindClick(liId, arrObj['selArrli'+i],"add");
        }


        for (var i = 0, len = $('#inner > li').size(); i < len; i++) {
            var liId = $('#inner > li').eq(i).attr('id');
            arrObj['selArrli'+i]=[];
            bindClick(liId, arrObj['selArrli'+i],"inner");
        }
        for (var i = 0, len = $('#outer > li').size(); i < len; i++) {
            var liId = $('#outer > li').eq(i).attr('id');
            arrObj['selArrli'+i]=[];
            bindClick(liId, arrObj['selArrli'+i],"outer");
        }





        //以事件委托的形式，绑定元素点击事件
        $(document).on('click', '.showAll', function(){
            $(this).parent().parent().find('.item_bar').show();
            $(this).text('折叠').addClass('showSome').removeClass('showAll');
        });
        $(document).on('click', '.showSome', function(){
            $(this).parent().parent().find('.item_bar').hide();
            $(this).text('展开').addClass('showAll').removeClass('showSome');
        });
        // 选择列表end


        $(document).on('click', '.btn1', function(){
            $('#addDiv').removeClass('undis');
        });
        $(document).on('click', '.btn2', function(){
            $('.mask1').removeClass('undis');
        });
        $(document).on('click', '.btn1_1', function(){
            $('#innerDiv').removeClass('undis');
            $('.mask1').addClass('undis');
        });
        $(document).on('click', '.btn1_2', function(){
            $('#outerDiv').removeClass('undis');
            $('.mask1').addClass('undis');
        });
        $(document).on('click', '.btn1_3', function(){
            $('.mask1').addClass('undis');
        });
        $(document).on('click', '.btn2_2', function(){
            $(this).parent().parent().parent().addClass('undis');
            $('.after').removeClass('undis');
        });
        // 删除按钮
        $(document).on('click', '.del_cross', function(){

            var yes =confirm("确定要删除吗?");
            if(yes){
                $(this).parent().remove();

            }

        });

        $("#toPicDetails").click(function(){
            window.location.href="${ctx }/app/manager/changeManagement/querySignaturePic?businessID="+${bill.projectChangeId}+"&businessType=105";
            return false;
        });

    })
</script>
</body>
</html>