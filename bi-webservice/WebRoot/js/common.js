//扫描轨迹表格
function setTable(parentId,contentVal){
		var s = document.getElementById(parentId);
		s.innerHTML = contentVal;
}

// 模糊查询
function highlightListbox(listBoxUuid, highLightValue) {
	var nodes = $("#" + listBoxUuid + " td");
	highLightValue = highLightValue.toUpperCase();
	nodes.each(function() {
		var sourceValue=$(this).text();
		var text = sourceValue.toUpperCase();
		var value;
		if (text.indexOf(highLightValue) >= 0) {
			var str1 = "";
			var str2 = "";
			var str3 = "";
			var startIndex = text.indexOf(highLightValue);
			if (startIndex != 0)
				str1 = sourceValue.substring(0, startIndex);
			if (startIndex != text.length - 1)
				str3 = sourceValue.substring(startIndex + highLightValue.length,
						sourceValue.length);
			str2 = sourceValue.substring(startIndex, startIndex
					+ highLightValue.length);
			str2 = "<span style='color:red'>" + str2 + "</span>"
			value = str1 + str2 + str3;
		}
		$(this).html(value);
	});
}


/**
 * 
 * 交接单局部打印
 * 
 * @param uuid
 *            要打印的区域
 * @param divUUid
 *            打印区域最外层div，用于获取宽度以设置比率
 * @param imageUUid
 *            图片组件id
 * @param barCodeDiv
 *            图片外层div
 */
function preview(uuid,divUUid,imageUUid,barCodeDiv){
	var div=document.getElementById(divUUid);
	var width=div.style.width.replace("px","");// 获取打印区域宽度
	var image=document.getElementById(imageUUid);
	var imageWidth=image.style.width.replace('px','');// 原有图片就宽度
	var imageHeight=image.style.height.replace('px','');// 原有图片就高度
	var rate=width/882;// 计算比率
	var txtSize=rate*16;// 正常文本大小
	var titleSize=24*rate;// 标题文本大小
	var styleString = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><style type='text/css'> "                                           +
	"	.titleStyle{"                                                                      +
	"		font-size:"+titleSize+"px;"                                                    +
	"		font-weight:bold;"                                                             +
	"	}"                                                                                 +  
    "	.textAlignRight{"                                                                  +
    "		text-align:right;"                                                             +
    "		font-family:arial,sans-serif;"                                                 +
    "	}"                                                                                 +
    "	.fontStyle{"                                                                       +
    "		font-size:"+txtSize+"px;"                                                      +
    "		font-family:arial,sans-serif;"                                                 +
    "	}"                                                                                 +
    "	.ewbsStyle{"                                                                       +
    "		font-size:"+rate*14+"px;"                                                      +
    "		font-weight:normal;"                                                           +
    "		font-family:arial,sans-serif;"                                                 +
    "	}"                                                                                 +
    " 	th {  "                                                                            +  
    "     color: #4f6b72;  "                                                               +  
    " 	}  "                                                                               +
    " 	.z-listbox th {  "                                                                 +  
    "     color: #252525;  "                                                               +
    "     border: 1px solid #8fb9d0;  "                                                    +  
    "     font-size:"+txtSize+"px;  "                                                      +
    " 	}  "                                                                               +
    " 	td.z-hbox-sep{width:0.3em;}"                                                       +  
    " 	.z-listbox td {  "                                                                 +  
    "     background: #fff;  "                                                             +  
    "     color: #252525;  "                                                               + 
    "     font-size:"+txtSize+"px;  "                                                      +
    " 	}  "                                                                               +
    "	div.z-listheader-cnt, div.z-listbox-header div.z-auxheader-cnt{"                   +
    "		padding:5px 5px;"                                                              +
    "	}"                                                                                 +
    "	tr.z-vbox-sep{height:0.3em;}"                                                      +
    "	tr td.z-listcell{padding-top:5px;padding-bottom:5px;}"                                                      +
    "	div.z-listbox-header tr.z-listhead, div.z-listbox-header tr.z-auxhead{"            +
    "		background:url('/ne-ops/zkau/web/2b0722c7/sapphire/zul/img/grid/column-bg.png') repeat-x scroll 0 0 #c7e5f1" +
    "	}"                                                                                 +
    "</style>";
	var winname = window.open('', "_blank",'');
	var prnhtml=document.getElementById(uuid).innerHTML;// 获取打印区域
	winname.document.write(styleString + "<div id='main'>"+prnhtml+"</div>"                +
			"<script language='Javascript'>"                                               +
			"	var images=document.getElementById('main').getElementsByTagName('img');"   +/* 获取阅览页面的所有图片 */
			"	var barCode=document.getElementById('"+barCodeDiv+"');"                    +
			"	var image;"                                                                +
			"	for(var i = 0;i<images.length;i++){"                                       +
			"		if(images[i].id === '"+imageUUid+"'){  "                               +
			"			image=images[i];"                                                  +
			"			break;"                                                            +
			"		}                                      "                               +
			"	}                                          "                               +
			"	image.style.width='"+imageWidth*rate+"px';"                                +/* 根据比率设置图片大小 */
			"	image.style.height='"+imageHeight*rate+"px';"                              +
			"	barCode.style.width='"+imageWidth*rate+"px';"                              +
			"	barCode.style.height='"+imageHeight*rate+"px';"                            +
			"   window.onload=function(){ window.print();} "+
			"</script>");
	winname.focus();
	winname.document.close();  
// winname.print();
// winname.close();
	// wb.execwb(1,1)//打开
    // wb.ExecWB(2,1);//关闭现在所有的IE窗口，并打开一个新窗口
    // wb.ExecWB(4,1)//;保存网页
    // wb.ExecWB(6,1)//打印
    // wb.ExecWB(7,1)//打印预览
    // wb.ExecWB(8,1)//打印页面设置
    // wb.ExecWB(10,1)//查看页面属性
    // wb.ExecWB(15,1)//好像是撤销，有待确认
    // wb.ExecWB(17,1)//全选
    // wb.ExecWB(22,1)//刷新
    // wb.ExecWB(45,1)//关闭窗体无提示
}

function changeQuoteGridBodyStyle(){
	var node = $(".grdQuote .z-grid-body");
	$(node[0]).css("overflow-x","visible");
}

// 修改树的背景色显示一半问题
function changeTreeSecondTd(treeUUid){
	var nodes = $("#" + treeUUid + " .z-treechildren");
	var trs=nodes.find("tr");
	var tds=$(trs[0]).find("td");
	$(tds[1]).css("display","none");
	
	var nodes = $("#" + treeUUid + " .z-tree-faker");
	if(nodes.length>0){
		for(var i=0;i<nodes.length;i++){
			var th=$(nodes[i]).find("th:last");
			$(th).css("display","none");
		}
	}
}

function setInNumberOnly(com) {
	$("#" + com).keydown(
			function(e) {
				var keycode = event.keyCode;
				if ($.browser.msie) {
					if (((keycode > 47) && (keycode < 58)) || (keycode == 8)) {
						return true;
					} else {
						return false;
					}
				} else {
					if (((e.which > 47) && (e.which < 58)) || (e.which == 8)
							|| (keycode == 17)) {
						return true;
					} else {
						return false;
					}
				}
			}).focus(function() {
		this.style.imeMode = 'disabled';
	});
}

function GetInput() {
	var k = event.keyCode;
	if ((k <= 57 && k >= 48) || (k <= 105 && k >= 96) || (k == 8)) {
		return true;
	} else {
		return false;
	}
}

function catchError(message,title){
	var oldAlert = jq.alert;
	jq.alert = function (msg, opts) {
		if (!msg.indexOf('the request was rejected because its size'))
			oldAlert(message, {mode:"modal", icon:"INFORMATION",title:title});
		else
			oldAlert(msg, opts);
	}
}
   