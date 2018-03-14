<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>汽车之家创意组</title>
	<link href="${resource}/css/creative/creativeGroupForm.css?v=${uiVersion}" rel="stylesheet" type="text/css"/>
	<script src="${ctxStatic}/zui/static/jquery/jquery-form/jquery.form.js" type="text/javascript"></script>
	<style type="text/css">
		.dropdown-menu { min-width: 120px; }
		.deal_form { overflow: hidden; }
		.pic_upload { padding-top: 3px; }
	</style>
</head>
<body>
	<!-- page loading -->
	<div class="zMsg_cover cover_body" style="display: block;">
		<div class="cover_bg"></div>
		<div class="cover_con">
			<span class="cover_icon"></span>
			<span class="cover_text">页面加载中，请稍后......</span>
		</div>
	</div>
	<!-- page content -->
	<div class="cgf_main">
		<h1 class="cgf_title">汽车之家创意组</h1>
		<div class="cgf_content">
			<form id="groupForm">
				<div class="form-group row">
					<label for="groupName" class="form-control-label cgf_fl"><span class="label_must">*</span>创意组名称：</label>
					<div class="col-sm-9">
						<input type="text" class="form-control w300" id="groupName" name="groupName" placeholder="请输入创意组名称">
						<span class="zui_icons icons_question" title="请输入中文、英文字母、数字、下划线或横线；可以输入2-50个字符，一个中文占2个字符。"></span>
						<input type="hidden" id="group_groupId" name="group_groupId" value="${groupId }"/>
						<input type="hidden" id="group_unionid" name="group_unionid" value="${unionid }"/>
						<input type="hidden" id="group_deType" name="group_deType" value="${deType }"/>
					</div>
				</div>
				<div class="form-group row">
					<label for="deType_list" class="form-control-label cgf_fl"><span class="label_must">*</span>平台类型：</label>
					<div class="col-sm-9">
						<select id="sType_list" name="sType_list" class="form-control w200 c-select">
							<option value="1">&nbsp;&nbsp;PC</option>
							<option value="2">&nbsp;&nbsp;移动</option>
						</select>
						<!-- <span class="zui_icons icons_question" title=""></span> -->
					</div>
				</div>
				<div class="form-group row">
					<label for="thirdStaticPerform" class="form-control-label cgf_fl">第三方曝光地址：</label>
					<div class="col-sm-9">
						<input type="text" class="form-control w300" id="thirdStaticPerform" name="thirdStaticPerform" placeholder="请输入第三方曝光地址">
						<span class="zui_icons icons_question" title="例如：http://www.xxx.com/xx"></span>
					</div>
				</div>
				<div class="form-group row">
					<label for="deType_list" class="form-control-label cgf_fl"><span class="label_must">*</span>投放类型：</label>
					<div class="col-sm-9">
						<select id="deType_list" name="deType_list" class="form-control w200 c-select">
							<option value="1">&nbsp;&nbsp;RTB</option>
							<option value="2">&nbsp;&nbsp;PDB</option>
							<option value="3">&nbsp;&nbsp;PD</option>
							<option value="4">&nbsp;&nbsp;PA</option>
						</select>
						<!-- <span class="zui_icons icons_question" title=""></span> -->
					</div>
				</div>
			</form>
			<form id="dealForm" style="display: none;">
				<div class="form-group row">
					<label for="group_dealId" class="form-control-label cgf_fl"><span class="label_must">*</span>订单ID：</label>
					<div class="col-sm-9">
						<input type="text" class="form-control w300" id="group_dealId" name="group_dealId" placeholder="请输入订单ID">
						<span class="zui_icons icons_question" title="投放订单ID，从汽车之家获取。"></span>
					</div>
				</div>
			</form>
			<div class="cgf_oper">
				<button type="button" id="add_group_btn1" class="btn btn-primary">&nbsp;&nbsp;新建创意&nbsp;&nbsp;</button>
			</div>
			<div class="cgf_list" id="cgf_list">
				<div id="cgf_list_empty" class="cgf_list_empty">
					<p>您尚未有创意！</p>
					<button id="add_group_btn2" class="btn btn-primary" type="button" style="padding-left: 20px; padding-right: 20px;">马上新建创意</button>
				</div>
			</div>
		</div>
		
	</div>
	<div class="cgf_btns">
		<div class="cgf_btns_bg"></div>
		<div class="cgf_btns_btn">
			<button type="button" id="groupFormSubmit" class="btn btn-primary">提&nbsp;交</button>
			<button type="button" onclick="window.location.href='${ctx}/bus/creative/listGroup';" class="btn btn-default" >返&nbsp;回</button>
		</div>
	</div>

<!-- 新增 修改 modal begin-->
<div id="groupModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="groupModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document" style="width: 800px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
				<span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="templetModalLabel">汽车之家创意</h4>
			</div>
			<div class="modal-body">
				<form id="groupModalForm">
					<div class="form-group row">
						<label for="creative_name" class="col-sm-3 form-control-label"><span class="label_must">*</span>创意名称：</label>
						<div class="col-sm-9">
							<input type="text" class="form-control w300" id="creative_name" name="creative_name" placeholder="请输入创意名称">
							<span class="zui_icons icons_question" title="请输入中文、英文字母、数字或下划线。可以输入2-50个字符，一个中文占2个字符。"></span>
							<input type="hidden" name="uiid_creative" id="uiid_creative"/>
						</div>
					</div>
					<!-- <div class="form-group row">
						<label for="creative_clickurl" class="col-sm-3 form-control-label"><span class="label_must">*</span>创意类型：</label>
						<div class="col-sm-9">
							<select id="advertType" name="advertType" class="form-control w200 c-select">
								<option value="101">&nbsp;&nbsp;落地页</option>
								<option value="100">&nbsp;&nbsp;下载页</option>
							</select>
						</div>
					</div> -->
					<div class="form-group row">
						<label for="creative_clickurl" class="col-sm-3 form-control-label"><span class="label_must">*</span>目标地址：</label>
						<div class="col-sm-9">
							<input type="text" class="form-control w300" id="creative_clickurl" name="creative_clickurl" placeholder="请输入目标地址">
							<span class="help-block" style="display: inline-block; margin-left: 10px;">例如：http://www.xxx.com/</span>
						</div>
					</div>
					<!-- <div class="form-group row">
						<label class="col-sm-3 form-control-label">创意类型：</label>
						<div class="col-sm-9">
							<label class="radio-inline">
								<input type="radio" checked="checked" name="creative_type" id="creative_type_05" value="5">图文创意
							</label>
						</div>
					</div> -->
					<div class="form-group row">
						<label class="col-sm-3 form-control-label">创意模板：</label>
						<div class="col-sm-9">
							<select class="form-control c-select" id="creative_template" name="creative_template" style="width: 300px;">
							</select>
						</div>
					</div>
				</form>
				<!-- 创意模版 -->
				<form id="creative_template_form" class="creative_template_list" style="display: block;">
				</form>
				<form id="creative_download_form" class="creative_template_list" style="display: none;">
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btn_cancel" data-dismiss="modal">取消</button>
				<button id="groupModalSubmit" type="button" class="btn btn-primary btn_submit">确认</button>
			</div>
		</div>
	</div>
</div>
<!-- 新增 修改 modal end -->

<!-- 图片上传 modal begin -->
<div id="picUploadModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="picUploadModal" aria-hidden="true">
	<div class="modal-dialog" style="width: 700px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title" id="picUploadModalLabel">图片上传</h4>
				<input type="hidden" id="uploadFile_field">
			</div>
			<div class="modal-body" id="picUploadModal_body" style="position: relative;">
				<form role="form" id="picUploadForm" enctype="multipart/form-data" style="padding: 30px 0 20px 0;">
					<div class="form-group row">
						<label for="uploadFile" class="col-sm-3 form-control-label">上传图片：</label>
						<div class="col-sm-9">
							<input type="file" id="uploadFile" name="uploadFile">
							<div id="uploadFile_des" style="padding-bottom: 5px;"></div>
							<input id="cType" name="cType" type="hidden" value="5"/>
							<input id="unionid" name="unionid" type="hidden" value="${unionid }"/>
						</div>
					</div>
				</form>
				<div class="pic_upload_cover" id="picUploadModal_cover">
					<div class="pic_upload_cover_bg"></div>
					<div class="pic_upload_cover_text">正在上传，请稍后...</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" id="picUploadModal_submit">上传</button>
			</div>
		</div>
	</div>
</div>	
<!-- 图片上传 modal end -->
	<script src="${ctxStatic}/bizOrderComplaint/js/creativeGroupForm_qczj.js?v=${uiVersion}" type="text/javascript"></script>
</body>
</html>