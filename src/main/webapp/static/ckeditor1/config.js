/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.removePlugins='elementspath';
	config.toolbar = 'my';//把默认工具栏改为‘MyToolbar’
	config.image_previewText=' '; //预览区域显示内容
	config.filebrowserImageUploadUrl = '../bizNotice/uploadFile1';
	/*config.filebrowserImageUploadUrl = '../upload/uploadFile.do?uploadType=Img&type=local';*/

	config.toolbar_my =
	[
	['Preview','-'],
	['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	['BidiLtr', 'BidiRtl'],
	'/',
	['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	['Link','Unlink','Anchor'],
	['Table','HorizontalRule','SpecialChar','PageBreak'],
	'/',
	['Styles','Format','Font','FontSize'],
	['TextColor','BGColor'],
	['Image'],
	['Maximize']
	];
};
