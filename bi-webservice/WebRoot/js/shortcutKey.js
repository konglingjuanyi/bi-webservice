zk.afterMount(function() {
	$(document).keydown(
			function(event) {
				if ((event.ctrlKey && event.keyCode == 83)
						|| (event.ctrlKey && event.keyCode == 68)
						|| (event.ctrlKey && event.keyCode == 77)
						|| (event.ctrlKey && event.keyCode == 65)
						|| (event.ctrlKey && event.keyCode == 79)
						|| (event.ctrlKey && event.keyCode == 111)) {
					event.preventDefault();
				}
				if (event.keyCode == 13) {
					setNextFocus(event, 1);
				}
				if (event.ctrlKey && event.keyCode == 13) {
					setNextFocus(event, 2);
				}
				if (event.ctrlKey && event.keyCode == 37) {
					event.preventDefault();
					setPreviousFocus();
				}
				if (event.ctrlKey && event.keyCode == 65) {
					setAddFocus();
				}
				if (event.ctrlKey && event.keyCode == 68) {
					setDelFocus();
				}
				if (event.ctrlKey && event.keyCode == 77) {
					setEditFocus();
				}
				if (event.ctrlKey && event.keyCode == 81) {
					setSearchFocus();
				}
				if (event.ctrlKey && event.keyCode == 83) {
					setSaveFocus();
				}
			});
});

function checkobjvisual(e) {
	var objvis = false;
	if (e) {
		if (e.offsetHeight > 0) {
			objvis = true;
		}
		if (e.offsetTop > 0) {
			objvis = true;
		}

		if (e.readOnly == true) {
			objvis = false;
		}

		if (e.name == "usable") {
			objvis = true;
		}
		if (e.name == "disuse") {
			objvis = false;
		}
//		if (e.name == "file") {
//			objvis = false;
//		}
//		if (e.disabled == true) {
//			objvis = false;
//		}

	}
	return objvis;
}

function getInputList() {
	var selectorOwn;
	// var selector1="#"+usualId;
	var selectorInput = ":input:not(:disabled):not(button):not(:checkbox):not(:file)";
	var selector=selectorInput+":not(.z-paging-inp):not([name='focusOFF'])";
	// if(usualId){
	// selectorOwn=selector1+" "+selector;
	// }else{
	selectorOwn = selector;
	// }
	return jq(selectorOwn);
}

function setNextFocus(event, type) {
	var inputList = getInputList();
	var nextindex = -1;
	for ( var i = 0; i < inputList.length; i++) {
		if (inputList[i] == document.activeElement) {
			nextindex = i + 1;
			while ((!checkobjvisual(inputList[nextindex]))
					&& (nextindex <= inputList.length)) {
				nextindex = nextindex + 1;
			}
			if (inputList[nextindex]) {
				if (document.activeElement.type != "textarea"
					&&inputList[nextindex].tagName.toLowerCase() == "textarea") {
					event.preventDefault();
					inputList[nextindex].focus();
					$(inputList[nextindex]).select();
				}else if (document.activeElement.type != "textarea" && type == 1) {
					inputList[nextindex].focus();
					$(inputList[nextindex]).select();
				}else if (document.activeElement.type == "textarea" && type==1) {
                }else if(document.activeElement.type == "textarea" && type == 2){
					inputList[nextindex].focus();
					$(inputList[nextindex]).select();
				}
				break;
			}
		}
	}
}

function setPreviousFocus() {
	var inputList = getInputList();
	var nextindex = inputList.length + 1;
	for ( var i = inputList.length; i > 0; i--) {
		if (inputList[i] == document.activeElement) {
			nextindex = i - 1;
			while ((!checkobjvisual(inputList[nextindex])) && (nextindex >= 0)) {
				nextindex = nextindex - 1;
			}
			if (inputList[nextindex]) {
				inputList[nextindex].focus();
				$(inputList[nextindex]).select();
				break;
			}
		}
	}
}

/** ******************快捷键获取焦点************************* */
var delUuid;
function setDelUuid(uid) {
	delUuid = uid;
}
function setDelFocus() {
	jq("#" + delUuid).focus();
}
var addUuid;
function setAddUuid(uid) {
	addUuid = uid;
}
function setAddFocus() {
	jq("#" + addUuid).focus();
}
var searchUuid;
function setSearchUuid(uid) {
	searchUuid = uid;
}
function setSearchFocus() {
	jq("#" + searchUuid).focus();
}
var saveUuid;
function setSaveUuid(uid) {
	saveUuid = uid;
}
function setSaveFocus() {
	jq("#" + saveUuid).focus();
}
var editUuid;
function setEditUuid(uid) {
	editUuid = uid;
}
function setEditFocus() {
	jq("#" + editUuid).focus();
}
var usualId;
function setUsualId(uid) {
	usualId = uid;
}
function setHtmlFocus() {
	jq("#" + usualId).focus();
}

/**************************************************/
function doHandleCkeditor(uuid){
	jq("#" +uuid).find('iframe').contents().find('html').keydown(function(event) {
		if ((event.ctrlKey && event.keyCode == 83)
				|| (event.ctrlKey && event.keyCode == 68)
				|| (event.ctrlKey && event.keyCode == 77)
				|| (event.ctrlKey && event.keyCode == 65)
				|| (event.ctrlKey && event.keyCode == 79)
				|| (event.ctrlKey && event.keyCode == 111)) {
			event.preventDefault();
		}
	});
}