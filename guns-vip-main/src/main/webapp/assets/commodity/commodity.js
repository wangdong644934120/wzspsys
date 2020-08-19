layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var storeId =  Feng.getUrlParam("storeId");

    /**
     * 商品信息管理
     */
    var Commodity = {
        tableId: "commodityTable"
    };

    /**
     * 初始化表格的列
     */
    Commodity.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: 'id'},
            {field: 'name', sort: true, title: '商品名称'},
            {field: 'detail', sort: true, title: '商品描述'},
            {field: 'pics', hide: true, sort: true, title: '商品图片'},
            {field: 'costCoin', sort: true, title: '花费先锋币'},
            {field: 'total', sort: true, title: '总数量'},
            {field: 'surplus', sort: true, title: '剩余数量'},
            {field: 'createTime', sort: true, title: '发布时间'},
            // {field: 'createUser', sort: true, title: '创建人'},
            {field: 'note', sort: true, title: '兑换方式'},
            {field: 'linkPerson', sort: true, title: '兑换联系人'},
            {field: 'linkPhone', sort: true, title: '联系电话'},
            // {field: 'updateUser', sort: true, title: '更新人'},
            // {field: 'storeId', sort: true, title: '商家id'},
            {sort: true, title: '当前状态',templet: function(d){
                switch(d.enable){
                    case 0 :
                        return "全部兑换完成";
                    case 1 :
                        return "可兑换";
                    case -1 :
                        return "中止兑换";
                    default :
                        return "可兑换";
                }
        }},
            {field: 'updateTime', sort: true, title: '中止兑换时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Commodity.search = function () {
        var queryData = {};
        queryData['name'] = $("#condition").val();
        queryData['storeId'] = storeId;
        table.reload(Commodity.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Commodity.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/commodity/add?storeId='+storeId;
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Commodity.tableId,
        url: Feng.ctxPath + '/commodity/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        where:{"storeId": storeId},
        cols: Commodity.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Commodity.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Commodity.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Commodity.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Commodity.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Commodity.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Commodity.onDeleteItem(data);
        }
    });


    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Commodity.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/commodity/delete", function (data) {
                Feng.success("中止成功!");
                Commodity.search();
            }, function (data) {
                Feng.error("中止失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否中止?", operation);
    };
});
