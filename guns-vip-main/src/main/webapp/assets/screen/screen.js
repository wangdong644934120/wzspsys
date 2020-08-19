function initDataWorker() {
    requestMapPoint();
    requestSystemSimpleStatis();
    requestRoomTypeStatis();
    requestLevelStatis();
    setTimeout(initDataWorker,1*3600*1000);//每小时定时刷新
}

/**
 * 获取地图点位
 */
function requestMapPoint(){
    $.ajax({
        url: "/datav/getCourtyardList",
        async: true,
        data: null,
        dataType: "json",
        success: function (response) {
            console.log(response);
            if(response.code==200) {
                var data = response.data;
                var points=[];
                for(var i=0;i<data.length;i++){
                    if(data[i].position!=null&&data[i].position.length>0){
                        var postion=data[i].position.split(",");
                        if(postion.length==2){
                            points.push({
                                id:data[i].id,
                                name:data[i].address,
                                value:[parseFloat(postion[0]),parseFloat(postion[1])]
                            });
                        }
                    }
                }
                dataui.get("BigMap").setData({
                    zztsg: [],
                    cssf: [],
                    zxg: points
                });
            }
        }
    });
}

/**
 * 获取系统整体信息
 */
function requestSystemSimpleStatis() {
    $.ajax({
        url: "/datav/getSystemSimpleStatis",
        async: true,
        data: null,
        dataType: "json",
        success: function (response) {
            console.log(response);
            if(response.code==200) {
                var data = response.data;
                dataui.get("buildAreaTotal").setData(data.totalSize+"㎡");
                dataui.get("buildTotal").setData(data.count+"个");
                dataui.get("personTotal").setData(data.personCnt+"人");
                dataui.get("Liquidfill").setData([data.standRatio, 0.7, 0.6, 0.5]);
            }
        }
    });
}

/**
 * 获取房屋分类信息
 */
function requestRoomTypeStatis() {
    $.ajax({
        url: "/datav/getRoomTypeStatisAll",
        async: true,
        data: null,
        dataType: "json",
        success: function (response) {
            console.log(response);
            if(response.code==200){
                var data=response.data;
                //加工数据并展示
                for(var i=0;i<data.length;i++){
                    data[i].value=data[i].size;
                    data[i].name=data[i].typeName.substring(0,2);
                }
                dataui.get("FWDB").setData({
                    sl:data
                });
            }
        }
    });
}

/**
 * 获取级别分类信息
 */
function requestLevelStatis() {
    $.ajax({
        url: "/datav/getLevelStatisAll",
        async: true,
        data: null,
        dataType: "json",
        success: function (response) {
            console.log(response);
            if(response.code==200){
                var data=response.data;
                //加工数据并展示
                var level=new Array(),stand=new Array(),real=new Array();
                for(var i=0;i<data.length;i++){
                    level.push(data[i].levelName);
                    stand.push(data[i].standArea);
                    real.push(data[i].realArea);
                    data[i].name=data[i].levelName;
                    data[i].value=data[i].num;
                }
                dataui.get("RYDD").setData({
                    sf:level,
                    jsl:stand,
                    hsl:real
                });
                dataui.get("DataList").setData(data);
            }
        }
    });
}