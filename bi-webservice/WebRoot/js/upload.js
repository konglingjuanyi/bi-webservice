/**
 * containerId: div的id
 * styleClass: button的样式
 * showDeleteButton：是否显示删除按钮
 
 * 例子：
 <body onload="initAddFile('container','upload_button_class', 3);">
 
 
 <form action="uploadFile-upload.jspa" method="post" enctype="multipart/form-data">
	    <div id="containerId">
	    </div>
	    <div><input type="button" value="添加附件" onclick="addFile('container', 'upload_button_class', true);"/></div>
	    <s:submit value="上传"/>
 </form>
 */
function addFile(containerId, inputStyleClass, addDeleteStyleClass, showDecriptionInput, showDeleteButton, showAddButton, maxCount) {
	var index = getFileIndex(containerId);
	if(index >= maxCount) return;
	var html = '<input type="file" name="uploadFiles[' + index + '].file" style="' + inputStyleClass + '"/>';
	if(showDecriptionInput) {
		html += ' <input type="text" name="uploadFiles[' + index + '].description" style="' + inputStyleClass + '"/>';		
	}
	if(index > 0 && showDeleteButton) {
		html += ' <a href="#" style="text-decoration:none;" class="' + addDeleteStyleClass + '" onclick="deleteFile(\'' + containerId + '\', this);return false;">删除</a>';
	}
	if(showAddButton) {
		html += ' <a href="#" style="text-decoration:none;" class="' + addDeleteStyleClass + '"';
		html += ' onclick="addFile(\'' + containerId + '\', \'' + inputStyleClass + '\', \'' + addDeleteStyleClass + '\', ' + showDecriptionInput + ', true, false, ' + maxCount + ');return false;">添加</a>';
	} 
	var div = document.createElement('DIV');
	div.innerHTML = html;
	document.getElementById(containerId).appendChild(div);
	
	alert( document.getElementById('all').innerHTML );
	 
}

function deleteFile(containerId, deleteButton) {
	document.getElementById(containerId).removeChild(deleteButton.parentNode);
}

function getFileIndex(containerId) {
	var divs = document.getElementById(containerId).getElementsByTagName('DIV');
	if(divs) {
		return divs.length;
	}
	return 0;
}
/**
 * params 
 * 	inputStyleClass	文件上传框和文件描述框的class名
 *  addDeleteStyleClass 添加删除链接的样式
 *  showDecriptionInput 是否显示文件描述文本框
 *  showDeleteButton 是否显示删除链接
 *  showAddButton 是否显示添加链接
 *  initInputCount 初始文件上传框数量
 *  maxInputCount 文件上传框数量最大值
 */
function showUpload(params) {
	if(!params) params = new Object();
	if(params.inputStyleClass == null) params.inputStyleClass='';
	if(params.addDeleteStyleClass == null) params.addDeleteStyleClass='';
	if(params.showDecriptionInput == null) params.showDecriptionInput=false;
	if(params.showDeleteButton == null) params.showDeleteButton=true;
	if(params.showAddButton == null) params.showAddButton=false;
	if(params.initInputCount == null || isNaN(params.initInputCount)) params.initInputCount=1;
	if(params.maxInputCount == null || isNaN(params.maxInputCount)) params.maxInputCount=1;
	document.writeln('<div id="upload_container_id"></div>');
	for(var i = 0; i < params.initInputCount; i ++) {
		addFile('upload_container_id', 
			params.inputStyleClass, 
			params.addDeleteStyleClass, 
			params.showDecriptionInput, 
			params.showDeleteButton, 
			params.showAddButton, 
			params.maxInputCount);
	}
}