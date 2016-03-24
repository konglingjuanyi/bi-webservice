CKEDITOR.editorConfig = function( config )
{
config.toolbar = 'MyToolbar';

config.toolbar_MyToolbar =
[
	['Cut','Copy','Paste','PasteText','PasteFromWord','-','Scayt'],
	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	['Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	'/',
	['Styles','Format'],
	['Bold','Italic','Strike'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	['Link','Unlink','Anchor']
];
};