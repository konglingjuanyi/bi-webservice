CKEDITOR.editorConfig = function(config) {
	config.resize_enabled = false;
	config.removePlugins = 'elementspath';
	config.toolbar = 'Simple';
	config.toolbar_Simple = [ [ 'Bold', 'Italic', 'TextColor', '-', 'Font',
			'FontSize', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', ] ];
	config.readOnly = true;
	config.toolbar_None = [];
};
