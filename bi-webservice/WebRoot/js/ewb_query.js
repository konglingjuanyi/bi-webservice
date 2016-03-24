function hideTip() {
	var fm = getTipDiv();
	if (fm != undefined) {
		fm.style.display = "none";
	}
}
function showTip(message, e) {
	var fm = getTipDiv();
	var posX, posY;
	if (document.all) {
		posX = window.event.clientX + document.documentElement.scrollLeft
				+ document.body.scrollLeft;
		posY = window.event.clientY + document.documentElement.scrollTop
				+ document.body.scrollTop;
	} else if (e) {
		posX = document.body.scrollLeft + e.pageX;
		posY = document.body.scrollTop + e.pageY;
	} else {
		posX = 10;
		posY = 10;
	}
	var top = posY + 15;
	var left = posX + 5;
	fm.style.left = left + "px";
	fm.style.top = top + "px";
	fm.style.display = "";
	fm.innerHTML = message;
}
var getTipDiv = function() {
	var id = "__popids";
	var getObj = document.getElementById(id);
	if (getObj != undefined)
		return getObj;
	var div = document.createElement("DIV");
	div.setAttribute("id", id);
	div.cssText = "-moz-opacity:.8;";
	div.style.display = "none";
	div.style.position = "absolute";
	div.style.top = "0px";
	div.style.left = "0px";
	div.style.background = "#FFFF99";
	div.style.border = "1px";
	div.style.zIndex=10;
	div.style.padding="5px"
	div.style.fontSize="12px";
	div.style.filter = "alpha(opacity=80)";
	document.body.appendChild(div);
	return div;
}