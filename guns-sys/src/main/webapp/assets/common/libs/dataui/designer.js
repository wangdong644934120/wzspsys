var prop = {
    "movable": true,
    "resizable": true,
    "rotatable": true,
    "editable": true,
    "copyable": true
};
var zIndex = 5;
var canMove = function (obj, callback, ctrlCopy) {
    var sx, sy;
    var originalRange;
    var mouseDown = function (e) {
//        if (e.target == obj || e.target.parentNode == obj) {
        sx = e.clientX - pageX(obj);
        sy = e.clientY - pageY(obj);
        originalRange = $(obj).offset();
        if (ctrlCopy && e.ctrlKey) {
            var objNew = $(obj).clone();
            $(objNew).attr("index", $(obj).attr("index"));
            objNew.appendTo($(obj).parent());
            new Concrete(objNew[0], prop);
            removeAllActive(e, true);
            setActive(obj, prop);
        }
        document.addEventListener("mousemove", onMove, false);
        document.addEventListener("mouseup", stopDrag, false);
//        }
    };
    obj.addEventListener("mousedown", mouseDown, false);
    var stopDrag = function () {
        document.removeEventListener("mousemove", onMove, false);
        document.removeEventListener("mouseup", stopDrag, false);
        obj.removeEventListener("mousedown", mouseDown, false);
        typeof (callback) == "function" && callback(originalRange);
    };
    var onMove = function (e) {
        e || event;
        window.getSelection ? window.getSelection().removeAllRanges() :
                document.selection.empty();
        if (e.preventDefault)
            e.preventDefault();//这两句便是解决firefox拖动问题的.
        var x = e.clientX - sx, y = e.clientY - sy;
        var isRalative = this instanceof Concrete;
        moveTo(obj, x, y, isRalative);
        refreshModelInfo(obj);
    }
    this.unbindListener = function () {
        obj.removeEventListener("mousedown", mouseDown, false);
    }
}
function moveTo(obj, x, y) {
    obj.style.position = "absolute";
    obj.style.left = x + "px";
    obj.style.top = y + "px";
}
function refreshModelInfo(obj) {
    $("#activeHeight").val(obj.offsetHeight);
    $("#activeWidth").val(obj.offsetWidth);
    $("#activeLeft").val(obj.offsetLeft - $("#container")[0].offsetLeft);
    $("#activeTop").val(obj.offsetTop - $("#container")[0].offsetTop);
    $("#order").val($(obj).attr("order") ? $(obj).attr("order") : "");
}

//function Model(obj, target, modelContainer, _prop) {
//    if (_prop) {
//        prop = _prop;
//    }
//    this.obj = obj;
//    this.movable = function () {
//        return true;
//    };
//    var targetX = target.offsetLeft;
//    var targetY = target.offsetTop;
//    var range = [targetX, targetX + target.offsetWidth,
//        targetY, targetY + target.offsetHeight];
//    var isInRange = function () {
//        var objRange = [obj.offsetLeft, obj.offsetLeft + obj.offsetWidth,
//            obj.offsetTop, obj.offsetTop + obj.offsetHeight];
//        return objRange[0] > range[0] && objRange[1] < range[1]
//                && objRange[2] > range[2] && objRange[3] < range[3];
//    }
//    var onDrop = function () {
//        if (!isInRange()) {
//            $(obj).remove();
//        } else {
//            obj.style.position = "absolute";
//            $(obj).appendTo($(target));
//            move.unbindListener(); //把事件都取消，需重新注册
//            //为obj注册新的选中、移动等事件
//            new Concrete(obj, prop);
//            setActive(obj, prop);
//        }
//        if ($(modelContainer).empty()) {
//            $(modelContainer).append("<div class='model'><div class=\"l_model_content\"></div></div>");
//            var modelObj = $(modelContainer).children(".model")[0];
//            $(modelObj).attr("index", $(modelContainer).attr("index"));
//            new Model(modelObj, target, modelContainer, prop);
//        }
//    }
//    var move = new canMove(obj, onDrop);
//}
//具体的实例
function Concrete(obj) {
    if (!prop) {
        prop = new Object();
    }
    var bindActive = function (event) {
//        if (event.target == obj || event.target.parentNode == obj) {
        event.stopPropagation();
        removeAllActive(event, true);
        setActive(obj, prop);
//        }
    };
    $(obj).bind("mousedown", bindActive);
}
function isInParentRange(obj) {
    var objOffset = $(obj).offset();
    var parentOffset = $(obj).parent().offset();
    if (objOffset.left < parentOffset.left) {
        return false;
    } else if (objOffset.left + $(obj).width() > parentOffset.left + $(obj).parent().width()) {
        return false;
    } else if (objOffset.top < parentOffset.top) {
        return false;
    } else if (objOffset.top + $(obj).height() > parentOffset.top + $(obj).parent().height()) {
        return false;
    }
    return true;
}
//设置活跃事件
function setActive(obj, prop) {
    refreshModelInfo(obj);
    var active = $(obj);
    if ($(obj).hasClass("active")) {
        return;
    }
    var height = active.outerHeight();
    var width = active.outerWidth();
    var borderWidth = 2,
            pointWidth = 8;
    prop.borderWidth = borderWidth;
    prop.pointWidth = pointWidth;
    var offetLeft = (active.outerWidth() - active.width()) / 2;
    var offetTop = (active.outerHeight() - active.height()) / 2;

    active.addClass("active");
    if (prop.movable) {
        setDragAble(obj);//设置为可拖拽
    }
    onWidgetActive(active);

    //四条边
    active.append(createMyElement(width + borderWidth, borderWidth, -borderWidth / 2 - offetLeft, -borderWidth / 2 - offetTop, "activeBorder"));
    active.append(createMyElement(width + borderWidth, borderWidth, -borderWidth / 2 - offetLeft, height - borderWidth / 2 - offetTop, "activeBorder"));
    active.append(createMyElement(borderWidth, height + borderWidth, -borderWidth / 2 - offetLeft, -borderWidth / 2 - offetTop, "activeBorder"));
    active.append(createMyElement(borderWidth, height + borderWidth, width - borderWidth / 2 - offetLeft, -borderWidth / 2 - offetTop, "activeBorder"));

    if (prop.resizable) {
        //四个顶点
        active.append(createMyElement(pointWidth, pointWidth, -pointWidth / 2 - offetLeft, -pointWidth / 2 - offetTop, "activeBorder resizePoint"));
        active.append(createMyElement(pointWidth, pointWidth, -pointWidth / 2 - offetLeft, height - pointWidth / 2 - offetTop, "activeBorder resizePoint"));
        active.append(createMyElement(pointWidth, pointWidth, width - pointWidth / 2 - offetLeft, -pointWidth / 2 - offetTop, "activeBorder resizePoint"));
        active.append(createMyElement(pointWidth, pointWidth, width - pointWidth / 2 - offetLeft, height - pointWidth / 2 - offetTop, "activeBorder resizePoint"));
        //四个中点
        active.append(createMyElement(pointWidth, pointWidth, width / 2 - pointWidth / 2 - offetLeft, -pointWidth / 2 - offetTop, "activeBorder resizePoint"));
        active.append(createMyElement(pointWidth, pointWidth, width / 2 - pointWidth / 2 - offetLeft, height - pointWidth / 2 - offetTop, "activeBorder resizePoint"));
        active.append(createMyElement(pointWidth, pointWidth, -pointWidth / 2 - offetLeft, height / 2 - pointWidth / 2 - offetTop, "activeBorder resizePoint"));
        active.append(createMyElement(pointWidth, pointWidth, width - pointWidth / 2 - offetLeft, height / 2 - pointWidth / 2 - offetTop, "activeBorder resizePoint"));
        bindResizePointAction(obj, prop);
    }
}
/**
 *设置为可拖拽
 */
function setDragAble(obj) {
    var callback = function (orginalRange) {
        var isIn = isInParentRange(obj);
        if (!isIn) {
            if (confirm("确定要删除吗？")) {
                var widget = $(obj).data("widget");//看看是否是组件
                if (widget != null) {
                    widget.remove();
                } else {
                    $(obj).remove();
                }
                return;
            } else {
                moveTo(obj, orginalRange.left, orginalRange.top, true);
                updateActive(obj);
            }
        }
        obj.style.zIndex = zIndex++;
    };
    new canMove(obj, callback, true);
}

function bindResizePointAction(obj, prop) {
    $(obj).children(".resizePoint").bind("mousedown", function (e) {
        e.stopPropagation();
        var index = $(obj).children(".resizePoint").index(e.target);
        var dir = [0, 0];
        //判断改变大小的移动的是哪个点，向哪个方向移动
        if (index === 0 || index === 4 || index === 2) {
            dir[1] = -1;
        } else if (index === 1 || index === 5 || index === 3) {
            dir[1] = 1;
        }
        if (index === 0 || index === 6 || index === 1) {
            dir[0] = -1;
        } else if (index === 2 || index === 7 || index === 3) {
            dir[0] = 1;
        }
        function resize(e) {
            var dir = e.data.dir;
            if (dir[0] === 1) {
                obj.style.width = Math.abs(e.pageX - obj.offsetLeft) + "px";
            } else if (dir[0] == -1) {
                obj.style.width = Math.abs(obj.offsetLeft + obj.offsetWidth - e.pageX) + "px";
                obj.style.left = e.pageX + "px";
            }
            if (dir[1] === 1) {
                obj.style.height = Math.abs(e.pageY - obj.offsetTop) + "px";
            } else if (dir[1] === -1) {
                obj.style.height = Math.abs(obj.offsetTop + obj.offsetHeight - e.pageY) + "px";
                obj.style.top = e.pageY + "px";
            }
            updateActive(obj, e.data.prop);
        }
        $(document).bind("mousemove", {
            "dir": dir,
            "prop": prop
        }, resize);
        function stop() {
            $(document).unbind("mousemove");
            $(document).unbind("mouseup");
            var widget = $(obj).data("widget");
            if (widget != null) {
                widget.resize();
            }
        }
        $(document).bind("mouseup", stop);
    });
}
function setDivPosition(element, width, height, left, top) {
    element.style.width = width + "px";
    element.style.height = height + "px";
    element.style.top = top + "px";
    element.style.left = left + "px";
}
function updateActive(obj) {
    var active = $(obj);
    var borderWidth = prop.borderWidth;
    var pointWidth = prop.pointWidth;
    var height = active.outerHeight();
    var width = active.outerWidth();
    var offetLeft = (active.outerWidth() - active.width()) / 2;
    var offetTop = (active.outerHeight() - active.height()) / 2;

    //四条边
    setDivPosition($(obj).children(".activeBorder").eq(0)[0], width + borderWidth, borderWidth, -borderWidth / 2 - offetLeft, -borderWidth / 2 - offetTop);
    setDivPosition($(obj).children(".activeBorder").eq(1)[0], width + borderWidth, borderWidth, -borderWidth / 2 - offetLeft, height - borderWidth / 2 - offetTop);
    setDivPosition($(obj).children(".activeBorder").eq(2)[0], borderWidth, height + borderWidth, -borderWidth / 2 - offetLeft, -borderWidth / 2 - offetTop);
    setDivPosition($(obj).children(".activeBorder").eq(3)[0], borderWidth, height + borderWidth, width - borderWidth / 2 - offetLeft, -borderWidth / 2 - offetTop);

    if (prop.resizable) {
        //四个顶点
        setDivPosition($(obj).children(".resizePoint").eq(0)[0], pointWidth, pointWidth, -pointWidth / 2 - offetLeft, -pointWidth / 2 - offetTop);
        setDivPosition($(obj).children(".resizePoint").eq(1)[0], pointWidth, pointWidth, -pointWidth / 2 - offetLeft, height - pointWidth / 2 - offetTop);
        setDivPosition($(obj).children(".resizePoint").eq(2)[0], pointWidth, pointWidth, width - pointWidth / 2 - offetLeft, -pointWidth / 2 - offetTop);
        setDivPosition($(obj).children(".resizePoint").eq(3)[0], pointWidth, pointWidth, width - pointWidth / 2 - offetLeft, height - pointWidth / 2 - offetTop);
        //四个中点0
        setDivPosition($(obj).children(".resizePoint").eq(4)[0], pointWidth, pointWidth, width / 2 - pointWidth / 2 - offetLeft, -pointWidth / 2 - offetTop);
        setDivPosition($(obj).children(".resizePoint").eq(5)[0], pointWidth, pointWidth, width / 2 - pointWidth / 2 - offetLeft, height - pointWidth / 2 - offetTop);
        setDivPosition($(obj).children(".resizePoint").eq(6)[0], pointWidth, pointWidth, -pointWidth / 2 - offetLeft, height / 2 - pointWidth / 2 - offetTop);
        setDivPosition($(obj).children(".resizePoint").eq(7)[0], pointWidth, pointWidth, width - pointWidth / 2 - offetLeft, height / 2 - pointWidth / 2 - offetTop);
    }
    refreshModelInfo(obj);

}
function removeAllActive(event, flag) {
    if (flag || !$(event.target).hasClass("active")) {
        $(".activeBorder").remove();
        $(".active").removeClass("active");
    }

}

function createMyElement(width, height, left, top, className) {
    var element = document.createElement("div");
    element.style.width = width + "px";
    element.style.height = height + "px";
    element.style.top = top + "px";
    element.style.left = left + "px";
    if (className) {
        element.className = className;
    }
    return element;
}

//获取元素的纵坐标 
function pageX(o) {
    var offset = o.offsetLeft;
    if (o.offsetParent != null)
        offset += arguments.callee(o.offsetParent);
    return offset;
}
//获取元素的横坐标 
function pageY(o) {
    var offset = o.offsetTop;
    if (o.offsetParent != null)
        offset += arguments.callee(o.offsetParent);
    return offset;
}
