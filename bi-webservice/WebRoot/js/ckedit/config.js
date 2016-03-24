CKEDITOR.editorConfig = function(config) {
	config.resize_enabled = false;
	config.toolbar = 'Simple';
	config.removePlugins = 'elementspath';
	config.toolbar_None = [];
	config.toolbar_Simple = [ [ 'Bold', 'Italic', 'TextColor', '-', 'Font',
			'FontSize', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', ] ];
	config.toolbar_Complex = [
			[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript',
					'Superscript', 'TextColor', 'BGColor', '-', 'Cut', 'Copy',
					'Paste', 'Link', 'Unlink', 'Image' ],
			[ 'Undo', 'Redo', '-', 'JustifyLeft', 'JustifyCenter',
					'JustifyRight', 'JustifyBlock' ],
			[ 'Table', 'Smiley', 'SpecialChar', 'PageBreak', 'Styles',
					'Format', 'Font', 'FontSize', 'Maximize' ] ];
	config.readOnly = false;
//	config.keystrokes = [ 
//  [ 
//	 CKEDITOR.CTRL + 66 /* B */, 'bold' ], // 粗体
//  ]
	config.blockedKeystrokes = [ 
	     CKEDITOR.CTRL + 83 /*S*/,
	     CKEDITOR.CTRL + 68 /*D*/,
	     CKEDITOR.CTRL + 77 /*M*/,
	     CKEDITOR.CTRL + 81 /*Q*/
	]
};