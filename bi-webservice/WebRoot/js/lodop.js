var CreatedOKLodop7766=null;
function getLodop(){
    var strHtmInstall="<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='../lodop/install_lodop32.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
    var strHtmUpdate="<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='../lodop/install_lodop32.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
    var strHtm64_Install="<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='../lodop/install_lodop64.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
    var strHtm64_Update="<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='../lodop/install_lodop64.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
    var strHtmFireFox="<br><br><font color='#FF00FF'>（注意：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它）</font>";
    var strHtmChrome="<br><br><font color='#FF00FF'>(如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）</font>";
    var LODOP;
	try{	
	     var isIE	 = (navigator.userAgent.indexOf('MSIE')>=0) || (navigator.userAgent.indexOf('Trident')>=0);
	     var is64IE  = isIE && (navigator.userAgent.indexOf('x64')>=0);
	     /*
			 * if (oOBJECT!=undefined || oEMBED!=undefined) { if (isIE)
			 * LODOP=oOBJECT; else LODOP=oEMBED; } else {
			 */
		 if (CreatedOKLodop7766==null){
          	     LODOP=document.createElement("object"); 
		     LODOP.setAttribute("width",0); 
                     LODOP.setAttribute("height",0); 
		     LODOP.setAttribute("style","position:absolute;left:0px;top:-100px;width:0px;height:0px;");  		     
                     if (isIE) LODOP.setAttribute("classid","clsid:2105C259-1E0C-4534-8141-A753534CB4CA"); 
		     else LODOP.setAttribute("type","application/x-print-lodop");
		     document.documentElement.appendChild(LODOP); 
	             CreatedOKLodop7766=LODOP;		     
 	         } else 
                     LODOP=CreatedOKLodop7766;
	     // };
	     if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
	    	 var winname = window.open('', "_blank",'');
	             if (navigator.userAgent.indexOf('Chrome')>=0)
	            	 winname.document.documentElement.innerHTML=strHtmChrome+winname.document.documentElement.innerHTML;
	             if (navigator.userAgent.indexOf('Firefox')>=0)
	            	 winname.document.documentElement.innerHTML=strHtmFireFox+winname.document.documentElement.innerHTML;
	             if (is64IE) winname.document.write(strHtm64_Install); else
	             if (isIE)   winname.document.write(strHtmInstall);    else
	            	 winname.document.documentElement.innerHTML=strHtmInstall+winname.document.documentElement.innerHTML;
	             winname.focus();
	             return LODOP;
	     } else 
	     if (LODOP.VERSION<"6.1.9.4") {
	    	 var winname = window.open('', "_blank",'');
	             if (is64IE) winname.document.write(strHtm64_Update); else
	             if (isIE) winname.document.write(strHtmUpdate); else
	            	 winname.document.documentElement.innerHTML=strHtmUpdate+winname.document.documentElement.innerHTML;
	    	     return LODOP;
	     };
	     LODOP.SET_LICENSES("上海丞风智能科技有限公司","4CF0AC779D3B7FA74B07D2F896DA02A6","","");
	     return LODOP; 
		} catch(err) {
			alert('网页存在错误，请刷新页面后重试，若未能弹出新窗口，则需要允许弹窗或在工具中添加信任站点后重试');
		     //if (is64IE)
	            //document.documentElement.innerHTML="Error:"+strHtm64_Install+document.documentElement.innerHTML;else
	            //document.documentElement.innerHTML="Error:"+strHtmInstall+document.documentElement.innerHTML;
		     return LODOP; 
		};
	}