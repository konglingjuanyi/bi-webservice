var queryParams = new QueryParams();

function LabelValueBean(label, value) {
    this.label = label;
    this.value = value;
}

function openDialogWindow(url, height, width) {
    source = "";
    // height = 450;
    // width = 550;
    defineWindow = "height=" + height + ",width=" + width + ",menubar=no,scrollbars=auto,location=no,resizable=no";
    if (window.screen) {
        var ah = screen.availHeight - 30;
        var aw = screen.availWidth - 10;

        var xc = (aw - width) / 2;
        var yc = (ah - height) / 2;

        defineWindow += ",left=" + xc + ",screenX=" + xc;
        defineWindow += ",top=" + yc + ",screenY=" + yc;
    }

    var newWin = window.open(url, '', defineWindow);
    newWin.focus();
    return newWin;
}

function openDialogWithScroll(url, height, width) {
    source = "";
    defineWindow = "height=" + height + ",width=" + width + ",menubar=no,scrollbars=yes,location=no,resizable=no";
    if (window.screen) {
        var ah = screen.availHeight - 30;
        var aw = screen.availWidth - 10;

        var xc = (aw - width) / 2;
        var yc = (ah - height) / 2;

        defineWindow += ",left=" + xc + ",screenX=" + xc;
        defineWindow += ",top=" + yc + ",screenY=" + yc;
    }

    var newWin = window.open(url, '', defineWindow);
    newWin.focus();
    return newWin;
}

function QueryParams(str) {
    this.params = new Array();
    if (str == null) str = window.location.search;
    if (str.length > 0) {
        str = window.location.search.substring(1);
        nameValues = str.split("&");
        var count = 0;
        for (var i = 0; i < nameValues.length; i++) {
            nameValue = nameValues[i].split("=");
            if (unescape(nameValue[1]) != "")
                this.params[ count++ ] = new LabelValueBean(nameValue[0], unescape(nameValue[1]))
        }
    }
    this.getValue = function(name) {
        for (i = 0; i < this.params.length; i++) {
            if (this.params[i].label == name) {
                return this.params[i].value;
            }
        }
        return;
    }
    this.exist = function(name) {
        for (i = 0; i < this.params.length; i++) {
            if (this.params[i].label == name) return true;
        }
        return false;
    }
    this.addParam = function(name, value) {
        this.params[this.params.length] = new LabelValueBean(name, value);
        return;
    }
    this.setValue = function(name, value) {
        for (i = 0; i < this.params.length; i++) {
            if (this.params[i].label == name) {
                this.params[i].value = value;
                return;
            }
        }
        this.addParam(name, value);
        return;
    }
    this.toString = function() {
        result = "";
        for (i = 0; i < this.params.length; i++) {
            if (this.params[i].label == 'act')
                continue;

            result = result + this.params[i].label + "=" + escape(this.params[i].value);
            if (i < this.params.length - 1) {
                result = result + '&';
            }
        }
        return result;
    }
}

function queryString(key) {
    return queryParams.getValue(key);
}


function gotoQueryStr(name, value) {
    queryParams.setValue(name, value);

    queryParams.setValue("action", "query");
    //alert( window.location.pathname +'?'+queryParams.toString() );
    window.location.href = window.location.pathname + '?' + queryParams.toString();
}

function getQueryStr(name, value) {
	queryParams.setValue(name, value);
	queryParams.setValue("action", "query");
    return queryParams.toString();
}
