// 当前地图对象
var map;
var province = "直辖市";
// 当前待分割的多边形
var polygon;
// 当前分割线
var divideLine;
// 临时分区,Polygon
var temp_subPolygons = new Array();
// 已分区,Polygon
var subPolygons = new Array();
// 已分区名称
var labels = new Array();
// 临时分区名称
var temp_label = new Array();
// 选中分区的位置，-1标识选中的是临时分区，其他表示已分区的下标
var state = -1;
var data = 1;
$(document).ready(function() {
	// 创建地图实例
	map = new BMap.Map("map");
	// 初始化地图,设置中心点坐标和地图级别
	map.centerAndZoom("上海市", 13);
	map.addControl(new BMap.NavigationControl());
	map.enableScrollWheelZoom();
	map.disableDoubleClickZoom();
	// 回调获得覆盖物信息
	var overlaycomplete = function(e) {
		// 获取折线
		if (e.drawingMode == BMAP_DRAWING_POLYLINE) {
			if (divideLine) {
				map.removeOverlay(divideLine);
			}
			divideLine = e.overlay;
		}
		// 获取Polygon
		if (e.drawingMode == BMAP_DRAWING_POLYGON) {
			polygon = e.overlay;
			state = -1;
			if (temp_subPolygons[0]) {
				if (temp_subPolygons[1]) {
					map.removeOverlay(temp_subPolygons[0]);
					map.removeOverlay(temp_label[0]);
					temp_subPolygons[0] = polygon;
					temp_label[0] = new BMap.Label("临时分区", {
						position : polygon.getBounds().getCenter()
					});
					temp_label[0].setStyle({
						borderWidth : "0px"
					});
					map.addOverlay(temp_label[0]);
					$("#temp_subregion option[value='" + 0 + "']").remove();
					$("#temp_subregion").append("<option value='" + 0 + "' selected='selected' state ='" + state + "'>" + "临时分区" + "</option>");
					$("#rename").val("临时分区");
				} else {
					temp_subPolygons[1] = polygon;
					temp_label[1] = new BMap.Label("临时分区", {
						position : polygon.getBounds().getCenter()
					});
					temp_label[1].setStyle({
						borderWidth : "0px"
					});
					map.addOverlay(temp_label[1]);
					$("#temp_subregion").append("<option value='" + 1 + "' selected='selected' state ='" + state + "'>" + "临时分区" + "</option>");
					$("#rename").val("临时分区");
				}
			} else {
				temp_subPolygons[0] = polygon;
				temp_label[0] = new BMap.Label("临时分区", {
					position : polygon.getBounds().getCenter()
				});
				temp_label[0].setStyle({
					borderWidth : "0px"
				});
				map.addOverlay(temp_label[0]);
				$("#temp_subregion").append("<option value='" + 0 + "' selected='selected' state ='" + state + "'>" + "临时分区" + "</option>");
				$("#rename").val("临时分区");
			}
		}

	};

	var styleOptions = {
		strokeColor : "red", // 边线颜色。
		fillColor : "#ff6666", // 填充颜色。当参数为空时，圆形将没有填充效果。
		strokeWeight : 2, // 边线的宽度，以像素为单位。
		fillOpacity : 0.5, // 填充的透明度，取值范围0 - 1。
		strokeStyle : 'solid', // 边线的样式，solid或dashed。
		enableEditing : true
	};
	// 实例化鼠标绘制工具
	var drawingManager = new BMapLib.DrawingManager(map, {
		isOpen : false, // 是否开启绘制模式
		enableDrawingTool : true, // 是否显示工具栏
		drawingToolOptions : {
			anchor : BMAP_ANCHOR_TOP_RIGHT, // 位置
			offset : new BMap.Size(5, 5), // 偏离值
			scale : 0.8, // 工具栏缩放比例
			drawingModes : [BMAP_DRAWING_MARKER, BMAP_DRAWING_POLYGON]
		},
		polygonOptions : styleOptions
	});
	// 添加鼠标绘制工具监听事件，用于获取绘制结果
	drawingManager.addEventListener('overlaycomplete', overlaycomplete);

	// 创建CityList对象，并放在citylist_container节点内
	var myCl = new BMapLib.CityList({
		container : "citylist_container",
		map : map
	});

	// 给城市点击时，添加相关操作
	myCl.addEventListener("cityclick", function(e) {
		// 修改当前城市显示
		var pro = $("[code='" + e.code + "']");
		$("#curCity").text(e.name + "市");
		province = pro.attr("province");
		// 点击后隐藏城市列表
		$("#cityList").attr("style", "display:none");

		map.clearOverlays();
		// 清除地图覆盖物
		$("#temp_subregion option[value='" + 0 + "']").remove();
		$("#temp_subregion option[value='" + 1 + "']").remove();
		document.getElementById('rename').value = "";
		polygon = null;
		divideLine = null;
		temp_subPolygons[0] = null;
		temp_subPolygons[1] = null;
		temp_label[0] = null;
		temp_label[1] = null;
		for (var i = 0; i < subPolygons.length; i++) {
			map.removeOverlay(subPolygons[i]);
			$("#subregion option[value='" + i + "']").remove();
		}
		subPolygons = new Array();
		// 获取分区
		/*
		 * $.post("../Ajax/GetPolygon.ashx", {
		 * "province": province, "city":
		 * $("#curCity").text() }, function (data,
		 * status) { if (status == "success") { if (data !=
		 * "null") { var polygonList =
		 * $.parseJSON(data); for (var i = 0; i <
		 * polygonList.length; i++) { var polygonid =
		 * polygonList[i].Id; var name =
		 * polygonList[i].Name; var namepoint =
		 * polygonList[i].NamePoint; var points =
		 * polygonList[i].Points; var labelPoint = new
		 * BMap.Point(namepoint.split(',')[0],
		 * namepoint.split(',')[1]); var label = new
		 * BMap.Label(name, { position: labelPoint });
		 * label.setStyle({ borderWidth: "0px" });
		 * label.disableMassClear(); var ply = new
		 * Ctil.CMap.Polygon(points, { strokeStyle:
		 * 'dashed', strokeColor: 'black', strokeWeight:
		 * 2, fillColor: '', fillOpacity: 0.2,
		 * enableEditing: false, enableClicking: false,
		 * enableMassClear: false });
		 * map.addOverlay(ply); //添加覆盖物
		 * map.addOverlay(label);
		 * $("#subregion").append("<option value='" + i + "'
		 * polygonid='" + polygonid + "'>" + name + "</option>");
		 * subPolygons[i] = ply; labels[i] = label; } } }
		 * else { alert("网络连接失败！"); } });
		 */

	});

	// 给城市链接添加点击操作
	document.getElementById("curCity").onclick = function() {
		var cl = document.getElementById("cityList");
		if (cl.style.display == "none") {
			cl.style.display = "";
		} else {
			cl.style.display = "none";
		}
	};

	// 给城市列表上的关闭按钮添加点击操作
	document.getElementById("popup_close").onclick = function() {
		var cl = document.getElementById("cityList");
		if (cl.style.display == "none") {
			cl.style.display = "";
		} else {
			cl.style.display = "none";
		}
	};

	// 分割区域
	$('#divide').click(function() {

		$("#temp_subregion option[value='" + 0 + "']").remove();
		$("#temp_subregion option[value='" + 1 + "']").remove();
		if (temp_subPolygons[0]) {
			map.removeOverlay(temp_subPolygons[0]);
		}
		if (temp_subPolygons[1]) {
			map.removeOverlay(temp_subPolygons[1]);
		}
		var _isValid = drawingManager.checkValidation(polygon, divideLine);
		if (_isValid) {
			temp_subPolygons = drawingManager.divide(polygon, divideLine, [{
				strokeColor : 'red',
				strokeWeight : 2,
				fillColor : 'green',
				fillOpacity : 0.5,
				enableEditing : true
			}, {
				strokeColor : 'red',
				strokeWeight : 2,
				strokeStyle : 'dashed',
				fillColor : 'yellow',
				fillOpacity : 0.2,
				enableEditing : true
			}]);
			var _sub1 = temp_subPolygons[0];
			var _sub2 = temp_subPolygons[1];
			map.addOverlay(_sub1);
			map.addOverlay(_sub2);
			if (state < 0) {
				map.removeOverlay(polygon);
				// 清除已分割的临时区域，如果分割的区域是已分区的，暂时保留不删除
				map.removeOverlay(temp_label[0]);
			}
			map.removeOverlay(divideLine);
			// 清除分割线
			temp_label[0] = new BMap.Label("临时分区1", {
				position : _sub1.getBounds().getCenter()
			});
			temp_label[0].setStyle({
				borderWidth : "0px"
			});
			map.addOverlay(temp_label[0]);
			temp_label[1] = new BMap.Label("临时分区2", {
				position : _sub2.getBounds().getCenter()
			});
			temp_label[1].setStyle({
				borderWidth : "0px"
			});
			map.addOverlay(temp_label[1]);
			$("#temp_subregion").append("<option value='" + 0 + "' style='background-color:green;' selected='selected' state ='" + state + "'>" + "临时分区1" + "</option>");
			$("#rename").val("临时分区1");
			$("#temp_subregion").append("<option value='" + 1 + "' style='background-color:yellow;' state ='" + state + "'>" + "临时分区2" + "</option>");
			state = -1;

		}
	});
	// 清除覆盖物
	$('#clear').click(function() {
		map.clearOverlays();
		// 清除地图覆盖物
		$("#temp_subregion option[value='" + 0 + "']").remove();
		$("#temp_subregion option[value='" + 1 + "']").remove();
		document.getElementById('rename').value = "";
		polygon = null;
		divideLine = null;
		temp_subPolygons[0] = null;
		temp_subPolygons[1] = null;
		temp_label[0] = null;
		temp_label[1] = null;
	});
	// 临时分区重命名
	$("#rename").change(function() {
		var select = document.getElementById('temp_subregion');
		var optionvalue = select.options[select.selectedIndex].value;
		$("#temp_subregion").find("option:selected").text(this.value);
		if (optionvalue == '0') {
			temp_label[0].setContent(this.value);
		} else {
			temp_label[1].setContent(this.value);
		}
	});

	// 临时分区选中状态更改，选中状态为实线，填充度为0.5，未选中状态为虚线，填充度为0.2
	$("#temp_subregion").click(function() {
		document.getElementById('rename').value = this.options[this.selectedIndex].text;
		var selectedoption = document
		.getElementById('temp_subregion').options[document.getElementById('temp_subregion').selectedIndex];
		var index = selectedoption.value;
		if (index == '0') {
			temp_subPolygons[0].setStrokeStyle("solid");
			temp_subPolygons[0].setFillOpacity(0.5);
			if (temp_subPolygons[1]) {
				temp_subPolygons[1].setStrokeStyle("dashed");
				temp_subPolygons[1].setFillOpacity(0.2);
			}
			polygon = temp_subPolygons[0];
		} else if (index == '1') {
			temp_subPolygons[1].setStrokeStyle("solid");
			temp_subPolygons[1].setFillOpacity(0.5);
			if (temp_subPolygons[0]) {
				temp_subPolygons[0].setStrokeStyle("dashed");
				temp_subPolygons[0].setFillOpacity(0.2);
			}
			polygon = temp_subPolygons[1];
		}
		state = -1;
		map.setViewport(polygon.getPath());
		// 调整视野
		// 更改已分区状态为未选中
		var objSelect = document.getElementById("subregion");
		for (var i = 0; i < objSelect.options.length; i++) {
			if (objSelect.options[i].value == "-1") {
				objSelect.options[i].selected = true;
			} else {
				objSelect.options[i].selected = false;
				subPolygons[objSelect.options[i].value].setStrokeStyle("dashed");
				subPolygons[objSelect.options[i].value].setFillOpacity(0.2);
				subPolygons[objSelect.options[i].value].setFillColor();
			}
		}
	});

	// 删除临时分区
	$("#del_temp_subregion").click(function() {
		var select = document.getElementById('temp_subregion');
		var optionvalue = select.options[select.selectedIndex].value;
		$("#temp_subregion option[value='" + optionvalue + "']").remove();
		document.getElementById('rename').value = "";
		map.removeOverlay(temp_subPolygons[Number(optionvalue)]);
		map.removeOverlay(temp_label[Number(optionvalue)]);
		temp_subPolygons[Number(optionvalue)] = null;
		temp_label[Number(optionvalue)] = null;
	});

	// 保存临时分区
	$("#temp_save").click(function() {
		var select = document.getElementById('temp_subregion');
		var optionvalue = select.options[select.selectedIndex].value;
		var optiontext = select.options[select.selectedIndex].text;
		var optionstate = $("#temp_subregion option[value='" + optionvalue + "']").attr("state");
		var city = $("#curCity").text();
		var namepoint = temp_label[Number(optionvalue)].getPosition().lng + ',' + temp_label[Number(optionvalue)].getPosition().lat;
		var name = temp_label[Number(optionvalue)].content;
		var polygonid = "";
		var polypoints = temp_subPolygons[Number(optionvalue)].getPath();
		var points = "";
		for (var i = 0; i < polypoints.length; i++) {
			points += polypoints[i].lng + ',' + polypoints[i].lat + ";";
		}
		points = points.substr(0, points.length - 1);
		var json = "";
		if (Number(optionstate) < 0) {

			// polygon保存到数据库
			json = {
				"province" : province,
				"city" : city,
				"name" : name,
				"namepoint" : namepoint,
				"polygonid" : polygonid,
				"points" : points
			};
			setJson(json);
			/*
			 * $.post("../Ajax/StorePolygon.ashx",
			 * json, function (data, status) {
			 * if (status == "success") { if
			 * (data != "") {
			 */
			var length = subPolygons.length;
			if (length == undefined) {
				length = 0;
			}
			$("#temp_subregion option[value='" + optionvalue + "']").remove();
			document.getElementById('rename').value = "";
			$("#subregion").append("<option value='" + length + "' polygonid='" + data + "'>" + optiontext + "</option>");
			data = data + 1;
			subPolygons[length] = temp_subPolygons[Number(optionvalue)];
			labels[length] = temp_label[Number(optionvalue)];
			labels[length].disableMassClear();
			subPolygons[length].setStrokeStyle("dashed");
			subPolygons[length].setFillOpacity(0.2);
			subPolygons[length].setFillColor();
			subPolygons[length].setStrokeColor("black");
			subPolygons[length].disableMassClear();
			subPolygons[length].disableEditing();
			temp_subPolygons[Number(optionvalue)] = null;
			temp_label[Number(optionvalue)] = null;

		} else {

			// polygonid = $("#subregion
			// option[value='" + optionstate +
			// "']").attr("polygonid");
			// json = { "province": province,
			// "city": city, "name": name,
			// "namepoint": namepoint,
			// "polygonid": polygonid, "points":
			// points
			// ,"stationid":stationid};
			/*
			 * $.post("../Ajax/StorePolygon.ashx",
			 * json, function (data, status) {
			 * if (status == "success") { if
			 * (data != "") {
			 */
			$("#temp_subregion option[value='" + optionvalue + "']").remove();
			$("#subregion option[value='" + optionstate + "']").text(optiontext);
			map.removeOverlay(subPolygons[Number(optionstate)]);
			$("#temp_subregion option[state='" + optionstate + "']").attr("state", "-1");
			subPolygons[Number(optionstate)] = temp_subPolygons[Number(optionvalue)];
			labels[Number(optionstate)] = temp_label[Number(optionvalue)];
			temp_subPolygons[Number(optionvalue)] = null;
			temp_label[Number(optionvalue)] = null;
			subPolygons[Number(optionstate)].setStrokeStyle("dashed");
			subPolygons[Number(optionstate)].setFillOpacity(0.2);
			subPolygons[Number(optionstate)].setFillColor();
			subPolygons[Number(optionstate)].setStrokeColor("black");
			subPolygons[Number(optionstate)].disableMassClear();
			subPolygons[length].disableEditing();
			temp_subPolygons[Number(optionvalue)] = null;
			temp_label[Number(optionvalue)] = null;
			state = -1;

		}

	});

	// 已分区选中状态更改，选中状态为实线，填充度为0.5，边线颜色为红色，未选中状态为虚线，填充度为0，边线颜色为黑色
	$("#subregion").click(function() {
		var index = Number(this.options[this.selectedIndex].value);
		state = index;
		for (var i = 0; i < subPolygons.length; i++) {
			if (i == index) {
				subPolygons[i].setStrokeStyle("solid");
				subPolygons[i].setFillOpacity(0.5);
				subPolygons[i].setFillColor("red");
				polygon = subPolygons[i];
				map.setViewport(polygon.getPath());
				// 调整视野
			} else {
				subPolygons[i].setStrokeStyle("dashed");
				subPolygons[i].setFillOpacity(0.2);
				subPolygons[i].setFillColor();

			}
		}
		// 更改未分区状态为未选中
		var objSelect = document.getElementById("temp_subregion");
		for (var i = 0; i < objSelect.options.length; i++) {
			if (objSelect.options[i].value == "-1") {
				objSelect.options[i].selected = true;
				document.getElementById('rename').value = "";
			} else {
				objSelect.options[i].selected = false;
				temp_subPolygons[objSelect.options[i].value].setStrokeStyle("dashed");
				temp_subPolygons[objSelect.options[i].value].setFillOpacity(0.2);
			}
		}
	});

	// 删除已分区
	$("#del_subregion").click(function() {
		var select = document.getElementById('subregion');
		var length = select.options.length;
		var optionvalue = select.options[select.selectedIndex].value;
		if (optionvalue == '-1') {
			return;
		}
		var polygonid = $("#subregion option[value='" + optionvalue + "']").attr("polygonid");
		$.post("../Ajax/DeletePolygon.ashx", {
			"polygonid" : polygonid
		}, function(data, status) {
			if (status == "success") {
				if (data) {
					$("#subregion option[value='" + optionvalue + "']").remove();
					for (var i = Number(optionvalue) + 1; i < (length - 1); i++) {
						$("#subregion option[value='" + i + "']").attr("value", "" + i - 1);
					}
					map.removeOverlay(subPolygons[Number(optionvalue)]);
					map.removeOverlay(labels[Number(optionvalue)]);
					subPolygons = subPolygons.del(Number(optionvalue));
				}

			} else {
				alert("网络连接失败！");
			}
		});
	});

});

function getBoundary() {
	var bdary = new BMap.Boundary();
	var name = document.getElementById("districtName").value;
	bdary.get(name, function(rs) {// 获取行政区域
		// map.clearOverlays(); //清除地图覆盖物
		var count = rs.boundaries.length;
		// 行政区域的点有多少个
		if (count > 0) {
			var ply = new BMap.Polygon(rs.boundaries[0], {
				strokeColor : 'red',
				strokeWeight : 2,
				fillColor : '#ff6666',
				fillOpacity : 0.5,
				enableEditing : true,
				enableClicking : false
			});
			// 添加覆盖物
			map.addOverlay(ply);
			// 调整视野
			map.setViewport(ply.getPath());
			polygon = ply;
			state = -1;
			if (temp_subPolygons[0]) {
				if (temp_subPolygons[1]) {
					map.removeOverlay(temp_subPolygons[0]);
					map.removeOverlay(temp_label[0]);
					temp_subPolygons[0] = polygon;
					temp_label[0] = new BMap.Label(name, {
						position : polygon.getBounds().getCenter()
					});
					temp_label[0].setStyle({
						borderWidth : "0px"
					});
					map.addOverlay(temp_label[0]);
					$("#temp_subregion option[value='" + 0 + "']").remove();
					$("#temp_subregion").append("<option value='" + 0 + "' selected='selected' state ='" + state + "'>" + name + "</option>");
					$("#rename").val(name);
				} else {
					temp_subPolygons[1] = polygon;
					temp_label[1] = new BMap.Label(name, {
						position : polygon.getBounds().getCenter()
					});
					temp_label[1].setStyle({
						borderWidth : "0px"
					});
					map.addOverlay(temp_label[1]);
					$("#temp_subregion").append("<option value='" + 1 + "' selected='selected' state ='" + state + "'>" + name + "</option>");
					$("#rename").val(name);
				}
			} else {
				temp_subPolygons[0] = polygon;
				temp_label[0] = new BMap.Label(name, {
					position : polygon.getBounds().getCenter()
				});
				temp_label[0].setStyle({
					borderWidth : "0px"
				});
				map.addOverlay(temp_label[0]);
				$("#temp_subregion").append("<option value='" + 0 + "' selected='selected' state ='" + state + "'>" + name + "</option>");
				$("#rename").val(name);
			}
		}
	});

}

Array.prototype.del = function(n) {// n表示第几项，从0开始算起。
	// prototype为对象原型，注意这里为对象增加自定义方法的方法。
	if (n < 0)// 如果n<0，则不进行任何操作。
		return this;
	else
		return this.slice(0, n).concat(this.slice(n + 1, this.length));
	/*
	 * concat方法：返回一个新数组，这个新数组是由两个或更多数组组合而成的。
	 * 这里就是返回this.slice(0,n)/this.slice(n+1,this.length) 组成的新数组，这中间，刚好少了第n项。
	 * slice方法： 返回一个数组的一段，两个参数，分别指定开始和结束的位置。
	 */
};

/**
 * 帮助array去除重复元素的对象。
 *
 * @version 1.0
 * @author: Glenlivet
 */
var ArrayHandler = {
	remove : function(array, index) {
		var result = new Array();
		for (var i = 0; i < array.length; i++) {
			if (i == index)
				continue;
			else {
				result.push(array.shift());
			}
		}
		return result;
	},
	removeDups2 : function(array) {
		var result = new Array();
		while (array.length > 0) {
			var first = array[0];
			array.shift();
			var hasDup = false;
			for (var i = 0; i < array.length; i++) {
				if (first == array[i])
					hasDup = true;
			}
			if (!hasDup)
				result.push(first);
		}
		return result;
	},
	removeDups : function(array) {
		var result = new Array();
		while (array.length > 0) {
			var last = array[array.length - 1];
			array.pop();
			var hasDup = false;
			for (var i = 0; i < array.length; i++) {
				if (last == array[i])
					hasDup = true;
			}
			if (!hasDup)
				result.unshift(last);
		}
		return result;
	}
};
/*******************************************************************************
 * @test
 *
 * var _array = [1,2,3,4,3,1,5];
 *
 * var result = ArrayHandler.removeDups(_array); console.dir(result);
 *
 ******************************************************************************/