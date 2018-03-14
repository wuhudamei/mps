$(function(){
	'use strict';
	var numS=1;
	var length='';
	var customer = {
		editChoice:function (){
			$('.editMask').on('click',function(){
				alertx("请先解锁再操作！！！");
			})
			if( $("#sendRuleId").val() != null && $("#sendRuleId").val() != '' ){
				$('.editMask').show();
				$('#editChoice').on('click',function(){
					$(this).find('.icon-clock').attr('src',ctxStatic + '/modules/orderreportsendrule/images/unclock.png');
					$('.editMask').hide();
				})
			}else{
				$('.icon-clock').attr('src',ctxStatic + '/modules/orderreportsendrule/images/unclock.png');
			}
		
		},
		addBtn: function () {
			$(document).on('click', '.addBtn', function() {
				if($(this).text()==="已添加"){
					return;
				};
				if(numS<=length) {
					$(this).parents('tr').attr('nameinde',numS).clone().prepend('<td class="numS">'+numS+'</td>').appendTo($('#customerBack'));
					numS++;
				}
				$(this).parents('tr').find('.addBtn').text('已添加').css('color','#999');
				$('#customerBack').find('.colBtn').text('删除').removeClass('addBtn').addClass('deleteBtn');
				$("#customerBack").find(".hidden").attr('name','rightEmployeeId');
			});
			
		},
		deleteBtn: function () {
			$(document).on('click', '.deleteBtn', function() {
				var currentThis=$(this);
				var indexCustom = currentThis.parents('tr').attr('nameinde');
				$('#customerList').find('tr').each(function(index){
					if($(this).attr('nameinde')===indexCustom){
						$(this).find('.addBtn').text('添加').css('color','#3da5ee');
						currentThis.parents('tr').remove();
					}
				})
				$('#customerList').find('.col_blue').removeClass('deleteBtn').addClass('addBtn');
				var ShortObject = $('#customerBack tr .numS');
				ShortObject.each(function (i,ele) {
					$(this).text(i+1);
				});
				numS=ShortObject.length+1;
			});

		},
		strCont: function () {
			length =$('#customerList').find('tr').length;
			$("#customerList tr").each(function(index,el){
				var valCon = $(this).find('.addBtn').text();
				if(valCon === "已添加") {
					if(numS<=length) {
						$(this).attr('nameinde',numS).clone().prepend('<td class="numS">'+numS+'</td>').appendTo($('#customerBack'));
						$('#customerBack').find('.colBtn').text('删除').removeClass('addBtn').addClass('deleteBtn col_blue ');
						$("#customerBack").find(".hidden").attr('name','rightEmployeeId');
						numS++;
					}
				}
			})
							
		}
	}
	customer.strCont();
	customer.addBtn();
	customer.deleteBtn();
	customer.editChoice();
});