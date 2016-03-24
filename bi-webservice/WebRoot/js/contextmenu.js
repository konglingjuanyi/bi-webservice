var selectText;// 用于保存选中的文本
var copyed;//复制的文本
var menuIds=['copy','paste'];// 菜单id
var object;//点击对象
window.onload = function() {
	// 自定义菜单
	var oMenu = document.getElementById("rightKeyMenu");
	var aLi = oMenu.getElementsByTagName("li");
	// 加载后隐藏自定义右键菜单
	oMenu.style.display = "none";
	// 菜单鼠标移入/移出样式
	for (i = 0; i < aLi.length; i++) {
		// 鼠标移入样式
		aLi[i].onmouseover = function() {
			this.className = "active";
		};
		// 鼠标移出样式
		aLi[i].onmouseout = function() {
			this.className = "";
		};
	}

	// 页面点击后自定义菜单消失
	document.onclick = function() {
		oMenu.style.display = "none";
	};
	
	document.oncontextmenu = function(event) {
		var event = event || window.event;
		var style = oMenu.style;
		style.display = "block";
		style.top = event.clientY + "px";
		style.left = event.clientX + "px";
		return false;
	};
};

/**
 * 键盘弹起设置选中内容
 */
document.onmouseup = function(event) {
	var text=getSelectionText(event);
	if(text!=""){
		selectText = text;
	}
}
/**
 * 获取选中内容
 * 
 * @returns
 */
function getSelectionText(event) {
	event = event || window.event; // 事件
    var target    = event.target || event.srcElement; // 获得事件源
    for(var i=0;i<menuIds.length;i++){
    	var id=menuIds[i];
    	if(target.getAttribute('id')==id){
    		return '';
    	}
    }
    object=target;
    if(target.nodeName=="INPUT"||target.nodeName=="TEXTAREA"){
    	// 获取输入框内容
		var selectionStart=target.selectionStart;
		var selectionEnd=target.selectionEnd;
		if(selectionStart != undefined && selectionEnd != undefined){
			return target.value.substring(selectionStart,selectionEnd);
		}
	}else{
		return get_label_selection();
	}
}

/**
 * 复制
 */
function copy(event) {
	copyed=selectText;
}

/**
 * 粘贴
 */
function paste(event) {
	pasteValue(copyed);
}

/**
 * 设置文本到粘贴板
 */
function setCopyedText(meintext){
	
}

/**
 * 获取label或div等选中内容
 * 
 * @returns
 */
function get_label_selection(){
	if (window.getSelection) { // 如果是Firefox、Chrome、Safari、Opera
		return window.getSelection().toString();
	} else if (document.selection && document.selection.createRange) { // 如果是IE
		return document.selection.createRange().text;
	}
	return '';
}

/**
 * 复制文本到文本框
 * @param text
 */
function pasteValue(text){
	if(object.nodeName=="INPUT"||object.nodeName=="TEXTAREA"){
    	// 获取输入框内容
		var selectionStart=object.selectionStart;
		var selectionEnd=object.selectionEnd;
		if(selectionStart != undefined && selectionEnd != undefined){
			var replaced=getReplaceText(object.value,selectionStart,selectionEnd,text);
			object.value=replaced;
		}
	}
}

/**
 * 获取替换字符串
 * 
 * @param str
 * @param begin
 * @param end
 * @param char
 * @returns
 */
function getReplaceText(source,begin,end,replace){
	 var fstStr = source.substring(0,begin);
	 var scdStr =source.substring(begin,end);
	 var lstStr = source.substring(end,source.length);
	 var matchExp = "/w/g";
	 if(scdStr==''){
		 return fstStr+replace+lstStr;
	 }else{
		 return fstStr+scdStr.replace(scdStr,replace)+lstStr;
	 }
}