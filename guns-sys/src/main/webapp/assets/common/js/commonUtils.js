var $request = function (args) {
    $.ajax(args);
}
var Common = {
    initDictField: function () {
    },
    //设置当前数据切换key
    setSwitchKey: function (value) {
        var d = new Date();
        d.setTime(d.getTime() + (2 * 60 * 60 * 1000));
        CookieUtil.set("project", value, d, "/");
    },
    //获取当前数据切换key
    getSwitchKey: function () {
        return CookieUtil.get("project");
    },
    dynamicLoadCss: function (url) {
        var head = document.getElementsByTagName('head')[0];
        var link = document.createElement('link');
        link.type = 'text/css';
        link.rel = 'stylesheet';
        link.href = url;
        head.appendChild(link);
    },
    isMobile:function() {
        var Agents =new Array("Android","iPhone","SymbianOS","Windows Phone","iPad","iPod");
        for(var v=0;v<Agents.length;v++) {
            if(navigator.userAgent.indexOf(Agents[v])>0) {
                return true;
            }
        }
        return false;
    }
};

var CookieUtil = {
    get: function (name) {
        var cookieName = encodeURIComponent(name) + "=",
            cookieStart = document.cookie.indexOf(cookieName),
            cookieValue = null,
            cookieEnd;
        if (cookieStart > -1) {
            cookieEnd = document.cookie.indexOf(";", cookieStart);
            if (cookieEnd == -1) {
                cookieEnd = document.cookie.length;
            }
            cookieValue = decodeURIComponent(document.cookie.substring(cookieStart + cookieName.length, cookieEnd));
        }
        return cookieValue;
    },
    set: function (name, value, expires, path, domain, secure) {
        var cookieText = encodeURIComponent(name) + "=" + encodeURIComponent(value);
        if (expires instanceof Date) {
            cookieText += "; expires=" + expires.toGMTString();
        } else {//默认超时1个月
            expires = new Date();
            expires.setDate(expires.getDate() + 30);//设置超时时间一个月
            cookieText += "; expires=" + expires.toGMTString();
        }
        if (path) {
            cookieText += "; path=" + path;
        }
        if (domain) {
            cookieText += "; domain=" + domain;
        }
        if (secure) {
            cookieText += "; secure";
        }
        document.cookie = cookieText;
    },
    unset: function (name, path, domain, secure) {
        this.set(name, "", new Date(0), path, domain, secure);
    }
};

var DrawBoxMap = function ($map) {
    //获取元素的纵坐标
    var pageX = function (o) {
        var offset = o.offsetLeft;
        if (o.offsetParent != null)
            offset += arguments.callee(o.offsetParent);
        return offset;
    }

    //获取元素的横坐标
    var pageY = function (o) {
        var offset = o.offsetTop;
        if (o.offsetParent != null)
            offset += arguments.callee(o.offsetParent);
        return offset;
    }

    $map.mousedown(function (event) {
        $map.empty();
        var $item = $("<div id=\"roomItem\" class=\"l-room-item\"></div>");
        $item.attr("state", "start");
        $item.offset({
            top: (event.offsetY),
            left: (event.offsetX)
        }).width(1).height(1);
        $map.append($item);

    });
    $map.mousemove(function (event) {
        var $item = $("#roomItem");
        if ($item.attr("state") == "start") {
            var width = event.clientX - pageX($item[0]);
            var height = event.clientY - pageY($item[0]);
            $item.width(width).height(height);
        }
    });

    $map.mouseup(function (event) {
        var $item = $("#roomItem");
        $item.attr("state", "end");
    });

    this.getItemPosition = function () {
        var $item = $("#roomItem");
        return {
            x: pageX($item[0]) - pageX($item.parent()[0]),
            y: pageY($item[0]) - pageY($item.parent()[0]),
            w: $item.width(),
            h: $item.height(),
            mw: $item.parent().width(),
            mh: $item.parent().height()
        };
    };
    this.showItem = function (postion) {
        var pjson = $.parseJSON(postion);
        $map.empty();
        var $item = $("<div id=\"roomItem\" class=\"l-room-item\"></div>");
        $item.offset({
            top: pjson.y,
            left: pjson.x
        }).width(pjson.w).height(pjson.h);
        $map.append($item);
    };
    this.showItemAll = function (postion, id) {
        var pjson = $.parseJSON(postion);
        // $map.empty();
        var $item = $("<div id=\"" + id + "\" class=\"l-room-item\";'></div>");
        $item.offset({
            top: pjson.y + pageY($map[0]),
            left: pjson.x + pageX($map[0])
        }).width(pjson.w).height(pjson.h);
        $map.append($item);
    };
    this.setDisable = function () {
        $map.unbind();
    };
}

var QRCodeUtil = function () {
    this.addQRListener=function (id,type,callback) {
        var ajax = new layui.ax(Feng.ctxPath + "/qrCode/add?type='"+type+"'&id=" + id, function (rtn) {
            if(rtn.data==true){
                console.log("二维码被扫描")
                Feng.success("二维码被扫描!");
                //扫码完成回调
                callback();
            }else{
                console.log("二维码监听返回值："+rtn.data);
            }
        }, function (data) {
            Feng.error("服务器异常：" + data.responseJSON.message + "!");
        });
        ajax.async= true;
        var rtn = ajax.start();
    };
    this.qrCodeScaned=function(id,type){
        var ajax = new layui.ax(Feng.ctxPath + "/qrCode/delete?type='"+type+"'&id=" + id, function () {
            console.log("二维码后台已删除");
        }, function (data) {
            Feng.error("服务器异常：" + data.responseJSON.message + "!");
        });
        var rtn = ajax.start();
    }


}