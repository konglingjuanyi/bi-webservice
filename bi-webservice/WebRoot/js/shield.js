//屏蔽Ctrl+N、Shift+F10、F11、F5刷新、退格键 

zk.afterMount(function() {
	$(document).keydown(function(event) {
		if (event.altKey && (event.keyCode == 37||event.keyCode == 39)) {
			event.preventDefault();//屏蔽 Alt+ 方向键 ←//屏蔽 Alt+ 方向键 →
		}
		if(!(document.activeElement.type=="text" 
			|| document.activeElement.type=="textarea"
			||document.activeElement.type=="password")){
			if (event.keyCode==8){//屏蔽退格删除键 
				event.preventDefault(); 
			}
	    } 
		if ((event.keyCode==116)||            			//屏蔽 F5 刷新键 
        		(event.ctrlKey && event.keyCode==82)){	//Ctrl + R 
           event.preventDefault(); 
        }
		if (event.keyCode==122){//屏蔽F11 
			event.preventDefault();
		} 
		if (event.ctrlKey && event.keyCode==78){//屏蔽 Ctrl+n 
			event.preventDefault();
		} 
		if (event.ctrlKey && event.keyCode==87){//屏蔽 Ctrl+w 
			event.preventDefault();
		} 
		if (event.shiftKey && event.keyCode==121){//屏蔽 shift+F10 
			event.preventDefault(); 
		}
		if ((event.altKey)&&(event.keyCode==115)) //屏蔽Alt+F4 
		{ 
			jq.alert("不允许ALT+F4关闭浏览器", {mode:"modal", icon:"INFORMATION",title:"关闭浏览器"});
			event.stopPropagation();
			event.preventDefault();
		} 
	});
});
			



var ie = navigator.appName=="Microsoft Internet Explorer"?true:false;

//屏蔽F1帮助 
window.onhelp=function(){ 
	return false;
};
