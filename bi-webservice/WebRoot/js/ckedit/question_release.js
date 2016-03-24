CKEDITOR.editorConfig = function(config) {
	config.extraPlugins = 'onchanging';
	config.minimumChangeMilliseconds = 100; // 100 milliseconds (default value)
	config.resize_enabled = false;
	config.toolbar = 'Simple';
	config.removePlugins = 'elementspath';
	config.toolbar_Simple = [ [ 'Bold', 'Italic', 'TextColor', '-', 'Font',
			'FontSize' , '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight',] ];
	config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋;楷体/楷体;微软雅黑/微软雅黑;新宋体/新宋体;'
		+ config.font_names;//设置字体	
	config.readOnly = false;// 设置是否只读
};
CKEDITOR.plugins.add('onchanging', {
	init : function(editor) {
		// Test:
		editor.on('changing', function(e) {
			var element = jq("$lblContentWords");
			editor = e.editor;
			var text = editor.document.getBody().getText().trim();
			var regExp = new RegExp(" ", "g");
			text = text.replace(regExp, "");
			text = text.replace(/\r\n/g, "");
			var realLength = 0, len = text.length, charCode = -1;
			for ( var i = 0; i < len; i++) {
				charCode = text.charCodeAt(i);
				if (charCode >= 0 && charCode <= 128)
					realLength += 1;
				else
					realLength += 2;
			}
			$(element).text(realLength + "/1000");
			somethingChanged();
		});

		var timer;
		// Avoid firing the event too often
		function somethingChanged() {
			if (timer)
				return;

			timer = setTimeout(function() {
				timer = 0;
				editor.fire('changing');
			}, editor.config.minimumChangeMilliseconds || 100);
		}
		// Kill the timer on editor destroy
		editor.on('destroy', function() {
			if (timer)
				clearTimeout(timer);
			timer = null;
		});

		// Set several listeners to watch for changes to the content
		editor.on('saveSnapshot', function(evt) {
			if (!evt.data || !evt.data.contentOnly)
				somethingChanged();
		});

		editor.getCommand('undo').on('afterUndo', somethingChanged);
		editor.getCommand('redo').on('afterRedo', somethingChanged);

		// Changes in WYSIWYG mode
		editor.on('contentDom', function() {
			editor.document.on('keydown', function(event) {
				// Do not capture CTRL hotkeys.
				if (event.data.$.ctrlKey || event.data.$.metaKey)
					return;

				var keyCode = event.data.$.keyCode;
				// Filter movement keys and related
				if (keyCode == 0 || keyCode == 8 || keyCode == 13 || keyCode == 32
						|| (keyCode >= 46 && keyCode <= 90)
						|| (keyCode >= 96 && keyCode <= 111)
						|| (keyCode >= 186 && keyCode <= 222))
					somethingChanged();
			});

			// Firefox OK
			editor.document.on('drop', somethingChanged);
			// IE OK
			editor.document.getBody().on('drop', somethingChanged);
		});

		// Detect changes in source mode
		editor.on('mode', function(e) {
			if (editor.mode != 'source')
				return;

			editor.textarea.on('keydown', function(event) {
				// Do not capture CTRL hotkeys.
				if (!event.data.$.ctrlKey && !event.data.$.metaKey)
					somethingChanged();
			});

			editor.textarea.on('drop', somethingChanged);
			editor.textarea.on('input', somethingChanged);
		});

		editor.on('afterCommandExec', function(event) {
			if (event.data.name == 'source')
				return;

			if (event.data.command.canUndo !== false)
				somethingChanged();
		});

	} // Init
});