layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 订单表管理
     */
    var Order = {
        tableId: "orderTable"
    };

    /**
     * 初始化表格的列
     */
    Order.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'place', sort: true, title: '下单地点'},
            {field: 'createTime', sort: true, title: '下单时间'},
            {field: 'userName', sort: true, title: '下单用户名称'},
            {field: 'userPhone', sort: true, title: '下单用户电话'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Order.search = function () {
        var queryData = {};
        queryData['goodsName'] = $("#condition").val();
        table.reload(Order.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Order.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/order/add';
    };

    /**
     * 导出excel按钮
     */
    Order.exportExcel = function () {
        var checkRows = table.checkStatus(Order.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'csv');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    Order.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/order/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Order.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/order/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Order.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Order.tableId,
        url: Feng.ctxPath + '/order/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Order.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Order.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Order.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Order.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Order.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Order.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Order.onDeleteItem(data);
        }
    });
});
