$(function () {
	var tex_all = $(".top input,.top select").attr("name", "tex");
	var one_tab = '<span class="line_del">删除</span></p>';
	var two_tab = '<button class="two_btn" onclick="secondSelectModel(this)">修改二级选项</button><span class="line_del">删除</span></p>';
	/* black 弹层按钮*/

	//弹层的确认框
	$(".cen_y").on("click", function () {
		$(".black").hide();
		
		question_count = 0;
		$("#storeId").val('');
        $("#projectMode").val('');
		$("#returnVisitNode").val('');
		$("#projectNode").val('');
		$(".select2-chosen").html('');
		
		tex_all.attr("disabled", false)
		$(".tex1,.tex3").removeClass("select_none")
		$(".tab").html("");	
		$('body,html').css("overflow", "auto");
	})
		//弹层的取消框
	$(".cen_n").on("click", function () {
		$(".black").hide();
		$('body,html').css("overflow", "auto");
	})

	//点击添加
	$(".add").on("click", function () {
		
		if($("#storeId").val() == "" || $("#storeId").val() == null || $("#returnVisitNode").val() == "" || $("#projectNode").val() == "" || $("#projectNode").val() == null|| $("#projectMode").val() == "" || $("#projectMode").val() == null) {
			$(".black").show();
			$(".info").text("请完整输入基本内容");
		}else {
			if( $(".tab .box").length == 0 ){
				//校验是否已经设置过
				$.ajax({
					'url':baseUrl+'/customerreturnvisit/bizCustomerReturnVisit/returnVisitNodeValidate',
				    'type':'GET', //GET
					'dataType': 'json',
					'data':{"projectNode":$("#projectNode").val(),"storeId":$("#storeId").val(),"projectMode":$("#projectMode").val()},
					'success': function (res) {
						if( res.code != 200 ){
							$("#alertRemarks").text("已经设置过该回访节点！");
			        		$("#subReport").show();
			        		return false;
						}else{
							question_count++;
							var num = $(".tab .box").length + 1;
							var table = '<div class="box">'
									  + '	<div class="lock unlock" ></div>'
									  + '	<h3>回访问题<span class="num">' + question_count + '</span></h3>'
									  + '	<div class="lines">'
									  + '		<span><i></i>问题描述<em>：</em></span>'
									  + '		<input type="hidden" name="validateFlag" id="validateFlag'+ num +'" indexNum="'+ num +'" value="0">'
									  + '		<input type="text" class="tex2" name="questionContent" id="questionContent'+ num +'" maxlength="30" placeholder="输入名称（最多输入30字）">'
									  + '	</div>'
									  + '	<div class="lines">'
									  + '		<span><i></i>统计计数<em>：</em></span>'
									  + '	  	<select class="count" id="statisticsDepartment' + num + '" name="statisticsDepartment" indexNum="' + num + '" style="width: 363px;">'
									  + statisticsDepartmentOptionsStr
									  + '	  	</select>'
									  + '	</div>'
									  + '	<div class="lines">'
									  + '		<span><i></i>答复方式<em>：</em></span>'
									  + '		<input type="hidden" name="replyMode" id="replyMode' + num + '" value="">'
									  + '		<input type="hidden" name="itemContent" id="itemContent' + num + '" value="">'
									  + '		<p class="line_sel only">'
									  + '			<span class="b_sed"><b></b>一级选项</span>'
									  + '			<span><b></b>二级选项</span><span><b></b>填空</span>'
									  + '			<span><b></b>日历</span>'
									  + '		</p>'
									  + '	</div>'
									  +	'	<div class="lines">'
									  + '		<span class="flo_left">&nbsp;&nbsp;设置选项<em>：</em></span>'
									  + '		<div class="tab_s"><div class="select_box"><p class="box_add addline" indexNum="' + num + '">+ 增加</p><p class="revamp" onclick="confirmAnswer(this,' + num + ')" >提交</p><p class="lastremove">删除</p></div></div>'
									  + '	</div>'
									  + '</div>';
							
							if (num > 15) {
								$("#alertRemarks").text("最多只能设置15个问题" );
				        		$("#subReport").show();
							} else {
								tex_all.attr("disabled", true);
								$(".tex1,.tex3").addClass("select_none");
								$(".tab").append(table);
							}
						}
					},
					'error': function (data) {
						console.log("请求错误！");
					}
				});
			}else{
				question_count++;
				var num = $(".tab .box").length + 1;
				//var table = '<div class="box"><div class="lock unlock" ></div><h3>回访问题<span class="num">' + question_count + '</span></h3><div class="lines"><span><i></i>问题描述<em>：</em></span><input type="hidden" name="validateFlag" id="validateFlag'+ num +'" indexNum="'+ num +'" value="0"><input type="text" class="tex2" name="questionContent" id="questionContent'+ num +'" maxlength="30" placeholder="输入名称（最多输入30字）"></div><div class="lines"><span><i></i>答复方式<em>：</em></span><input type="hidden" name="replyMode" id="replyMode' + num + '" value=""><input type="hidden" name="itemContent" id="itemContent' + num + '" value=""><p class="line_sel only"><span class="b_sed"><b></b>一级选项</span><span><b></b>二级选项</span><span><b></b>填空</span><span><b></b>日历</span></p></div><div class="lines"><span class="flo_left">&nbsp;&nbsp;设置选项<em>：</em></span><div class="tab_s"><div class="select_box"><p class="box_add addline" indexNum="' + num + '">+ 增加</p><p class="revamp" onclick="confirmAnswer(this,' + num + ')" >提交</p><p class="lastremove">删除</p></div></div></div></div>';
				
				var table = '<div class="box">'
						  + '<div class="lock unlock" ></div>'
						  + '<h3>回访问题<span class="num">' + question_count + '</span></h3>'
						  + '<div class="lines">'
						  + '  <span><i></i>问题描述<em>：</em></span>'
						  + '  <input type="hidden" name="validateFlag" id="validateFlag'+ num +'" indexNum="'+ num +'" value="0">'
						  + '  <input type="text" class="tex2" name="questionContent" id="questionContent'+ num +'" maxlength="30" placeholder="输入名称（最多输入30字）">'
						  + '</div>'
						  + '<div class="lines"><span><i></i>统计计数<em>：</em></span>'
						  + '	  <select class="count" id="statisticsDepartment' + num + '" name="statisticsDepartment" indexNum="' + num + '" style="width: 363px;">'
						  + statisticsDepartmentOptionsStr
						  + '	  </select>'
						  + '</div>'
						  + '<div class="lines">'
						  + '  <span><i></i>答复方式<em>：</em></span>'
						  + '  <input type="hidden" name="replyMode" id="replyMode' + num + '" value="">'
						  + '  <input type="hidden" name="itemContent" id="itemContent' + num + '" value="">'
						  + '  <p class="line_sel only">'
						  + '    <span class="b_sed"><b></b>一级选项</span>'
						  + '    <span><b></b>二级选项</span><span><b></b>填空</span>'
						  + '    <span><b></b>日历</span>'
						  + '  </p>'
						  + '</div>'
						  + '<div class="lines">'
						  + '  <span class="flo_left">&nbsp;&nbsp;设置选项<em>：</em></span>'
						  + '   <div class="tab_s">'
						  + '    <div class="select_box"><p class="box_add addline" indexNum="' + num + '">+ 增加</p><p class="revamp" onclick="confirmAnswer(this,' + num + ')" >提交</p><p class="lastremove">删除</p>'
						  + '    </div>'
						  + '  </div>'
						  + '</div>'
						  + '</div>';
				
				if (num > 15) {
					$("#alertRemarks").text("最多只能设置15个问题" );
	        		$("#subReport").show();
				} else {
					tex_all.attr("disabled", true);
					$(".tex1,.tex3").addClass("select_none");
					$(".tab").append(table);
				}
			}
		}
	})

	/*判断 不能输入15 条的 确认 取消按钮*/
	$(document).on("click", ".len_y,.len_n", function () {
		$(".question_count").remove();
		$('body,html').css("overflow", "auto");
	})

	//点击保存
	$(".store").on("click", function () {
		//校验合法性
		var validateFlag = true;
		
		if( $("#storeId").val() == '' || $("#storeId").val() == null || $("#returnVisitNode").val() == '' || $("#returnVisitNode").val() == null || $("#projectNode").val() == '' || $("#projectNode").val() == null){
			validateFlag = false;
			$("#alertRemarks").text("节点信息不完整，请完善后再保存！" );
    		$("#subReport").show();
			return false;
		}
		
		//校验回访问题是否设置
		if( $(".tab .box").length == 0 ){
			validateFlag = false;
			$("#alertRemarks").text("请至少设置一个回访问题再 ，请完善后再保存！" );
    		$("#subReport").show();
			return false;
		}
		
		$("input[name=validateFlag]").each(function(){
			if( $(this).val() == 0 ){
				validateFlag = false;
				$("#alertRemarks").text("回访问题"+$(this).attr("indexNum") +"未提交，请提交后再保存！" );
        		$("#subReport").show();
				return false;
			}
		})
		if( validateFlag ){
			
			if( $("#id").val() != '' && $("#id").val() != null ){
				submitFunction();
			}else{
				//校验是否已经设置过
				$.ajax({
					'url':baseUrl+'/customerreturnvisit/bizCustomerReturnVisit/returnVisitNodeValidate',
				    'type':'GET', //GET
					'dataType': 'json',
					'data':{"projectNode":$("#projectNode").val(),"storeId":$("#storeId").val(),"projectMode":$("#projectMode").val()},
					'success': function (res) {
						if( res.code == 200 ){
							submitFunction();
						}else{
							$("#alertRemarks").text("已经设置过该回访节点！");
			        		$("#subReport").show();
						}
						return false;
					},
					'error': function (data) {
						console.log("请求错误！");
					}
				});
			}
		}
	})
	
	//清空按钮
	$(".empty").on("click", function () {
		$(".black").show();
		$(".info").text("确认清空！所填内容将被重置");
		
		$('body,html').css("overflow", "hidden");
	})
	//选择答复方式
	$(document).on("click", ".line_sel span", function () {
		var $curLi = $(this).parents(".lines").next(".lines").find(".select_box p,.two_tex").not(".box_add,.revamp,.lastremove");
		if($(this).parents(".box").find(".lines .count").find("option:selected").val()=="0"){
			$(this).addClass("b_sed").siblings().removeClass("b_sed");
			$curLi.remove();
		}
	})
	//根据答复方式 添加对应的选框
	$(document).on("click", ".box_add", function () {
		//问题下标
		var indexNum = $(this).attr("indexNum");
		//答复方式
		var idx = $(this).parents(".lines").siblings().find(".only").find(".b_sed").index();
		if (idx == 0) {
			$(this).before('<p><input type="text" maxlength="10" name="firstItem" class="firstSelectOptions'+ indexNum +'">' + one_tab);
		}else if (idx == 1) {
			$(this).before('<p class="two_tex"><input type="text" name="secondItem" class="seccondSelectOptions' + indexNum + '" maxlength="10"><input type="hidden" class="selectedItem" value="">' +two_tab);
		}
	})
		//删除添加进去的对应选框
	$(document).on("click", '.line_del', function () {
		$(this).parent("p").remove()
	})
	/*阻止冒泡*/
	$(document).on("click", ".two_btn",".revamp", function () {
		return false;
	})

	//二级菜单里的添加
	$(document).on("click",".left_p span", function () {
		if ($(this).hasClass('span_bj')) {
			return;
		}
		$(this).remove();
		$(this).addClass("span_bj")
		var clone_dom = $(this).removeClass("span_bj").clone();
		$(".right_p").append(clone_dom)
	})
	$(document).on("click", ".right_p span", function () {
		var clone_dom = $(this).clone();
		$(".left_p").append(clone_dom)
		$(this).remove();
	})
		//二级菜单里的 确认 取消
	$(".sel_y,.sel_n").on("click", function () {
		var resultStr = '';
		$.each($(".right_p").find("span"),function(){
			resultStr = resultStr + $(this).html() + ",";
		})
		if( resultStr != '' ){
			returnModelObj.siblings('.selectedItem').val(resultStr.substring(0,resultStr.length-1));
		}else{
			returnModelObj.siblings('.selectedItem').val(resultStr);
		}
		$(".right_p").html('');
		$(".two_sel").hide();
		$('body,html').css("overflow", "auto");
	})
	
	//删除新增的tab 模块；
	$(document).on("click",".lastremove",function(){
		var newblack='<div class="newblack"><div class="center"><div class="info">是否确定删除？</div><div class="cen_btn"><span class="newblack_y">确认</span><span class="newblack_n">取消</span></div></div></div>';
		$(this).parents(".box").append(newblack);
	})

	//删除新增的tab 模块===>确定
	$(document).on("click", ".newblack_y",function () {
		$(this).parents(".newblack").parent().remove();
		//新 (当删除全部的.box后 解锁input)
		if($(".tab .box").length<1){
			tex_all.attr("disabled", false)
			$(".tex1,.tex3").removeClass("select_none")
		}
		//重新排序
		$(".box").each(function(k,v){
			
			var num_val = k+1;
			$(v).find(".num").text( num_val );
			
			//对原有的id值进行修改，避免混乱冲突
			$(v).find(".lines").find("input[name='validateFlag']").attr("id","validateFlag" + num_val ).attr("indexNum",num_val );
			$(v).find(".lines").find("input[name='questionContent']").attr("id","questionContent" + num_val );
			$(v).find(".lines").find("input[name='statisticsDepartment']").attr("id","statisticsDepartment" + num_val );
			$(v).find(".lines").find("input[name='replyMode']").attr("id","replyMode" + num_val );
			$(v).find(".lines").find("input[name='itemContent']").attr("id","itemContent" + num_val );
			$(v).find(".lines").find(".firstSelectOptions").attr("id","replyMode" + num_val );
			$(v).find(".lines").find("input[name='firstItem']").attr("class","firstSelectOptions" + num_val );
			$(v).find(".lines").find("input[name='selectedItem']").attr("class","seccondSelectOptions" + num_val );
			$(v).find(".lines").find(".box_add").attr("indexNum", num_val );
			$(v).find(".lines").find(".revamp").attr("onclick", "confirmAnswer(this,"+ num_val +")" );
		});
		//同步问题个数
		question_count = $(".box").length;
		
	})
	
	$(document).on("click", ".newblack_n",function () {
		$(this).parents(".newblack").remove();
	})
	
	//解锁
	$(document).on("click",".lock",function(){
		$(this).parent(".box").find(".lines").eq(0).find("input[name='validateFlag']").val("0");
		$(this).addClass("unlock");
		$(this).parents(".box").find(".whiteboard").remove();
	})
	
	//选择统计部门
	$(document).on("change",".count",function(){
		var indexNum = $(this).attr("indexNum");
		var obj = $(this).parents('.lines').siblings('.lines').find('.select_box');
		
		obj.empty();
		//$(".select_box").empty();
		if($(this).val() != "0"){
			$(this).parents(".box").find(".only").find("span").eq(1).addClass("b_sed").siblings().removeClass("b_sed");
			
			var fill_content = '<p class="two_tex">'
				+ '	<input type="text" name="secondItem" class="seccondSelectOptions' + indexNum + '" maxlength="10" value="满意">'
				+ '		  <input type="hidden" class="selectedItem" value="">'
				+ '		  <button class="two_btn" onclick="secondSelectModel(this)">修改二级选项</button><span class="line_del">删除</span>'
				+ '		</p>'
				+ '		<p class="two_tex">'
				+ '		  <input type="text" name="secondItem" class="seccondSelectOptions' + indexNum + '" maxlength="10" value="一般">'
				+ '		  <input type="hidden" class="selectedItem" value="">'
				+ '		  <button class="two_btn" onclick="secondSelectModel(this)">修改二级选项</button><span class="line_del">删除</span>'
				+ '		</p>'
				+ '		<p class="two_tex">'
				+ '		  <input type="text" name="secondItem" class="seccondSelectOptions' + indexNum + '" maxlength="10" value="不满意">'
				+ '		  <input type="hidden" class="selectedItem" value="">'
				+ '		  <button class="two_btn" onclick="secondSelectModel(this)">修改二级选项</button><span class="line_del">删除</span>'
				+ '		</p>'
				+ '		<p class="two_tex">'
				+ '		  <input type="text" name="secondItem" class="seccondSelectOptions' + indexNum + '" maxlength="10" value="不评价">'
				+ '		  <input type="hidden" class="selectedItem" value="">'
				+ '		  <button class="two_btn" onclick="secondSelectModel(this)">修改二级选项</button><span class="line_del">删除</span>'
				+ '		</p>'
				+ ' <p class="box_add addline" indexNum="' + indexNum + '">+ 增加</p><p class="revamp" onclick="confirmAnswer(this,' + indexNum + ')" >提交</p><p class="lastremove">删除</p>';
			//$(".select_box .counts").remove();
			obj.prepend(fill_content);
		}else{
			$(this).parents(".box").find(".only").find("span").eq(0).addClass("b_sed").siblings().removeClass("b_sed");
			obj.prepend('<p class="box_add addline" indexNum="' + indexNum + '">+ 增加</p><p class="revamp" onclick="confirmAnswer(this,' + indexNum + ')" >提交</p><p class="lastremove">删除</p>');
		}
	})
	
	$("#projectMode").on("change",function(){
		fillProjectNodeOptions();
	})
	
})