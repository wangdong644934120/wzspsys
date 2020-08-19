layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 商家表管理
     */
    var Store = {
        tableId: "storeTable"
    };

    /**
     * 初始化表格的列
     */
    Store.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'code',  sort: true, title: '编号'},
            {field: 'name', sort: true, title: '名称'},
            {field: 'address', sort: true, title: '地址'},
            {field: 'phone', sort: true, title: '联系电话'},
            {field: 'detail', sort: true, title: '商家简介'},
            {field: 'createTime', hide: true, sort: true, title: '创建时间'},
            {field: 'createUser', hide: true, sort: true, title: '创建人id'},
            {field: 'updateTime', hide: true, sort: true, title: '修改时间'},
            {field: 'updateUser', hide: true, sort: true, title: '修改人id'},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth:250}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Store.search = function () {
        var queryData = {};
        queryData['name'] = $("#condition").val();
        table.reload(Store.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Store.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/store/add';
    };

    /**
     * 导出excel按钮
     */
    Store.exportExcel = function () {
        var checkRows = table.checkStatus(Store.tableId);
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
    Store.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/store/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Store.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/store/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Store.tableId);
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
        elem: '#' + Store.tableId,
        url: Feng.ctxPath + '/store/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Store.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Store.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Store.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Store.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Store.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Store.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Store.onDeleteItem(data);
        }else if (layEvent === 'commodity') {
            Store.commodityManage(data);
        }else if (layEvent === 'manager') {
            Store.managerManage(data);
        }
    });

    Store.commodityManage = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '商品管理',
            area: ['90%', '90%'],
            content: Feng.ctxPath + '/commodity?storeId=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload();
            }
        });
    };

    Store.managerManage = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '负责人管理',
            area: ['90%', '90%'],
            content: Feng.ctxPath + '/userExt?baseID=' + data.id+"&type=store",
            end: function () {
                admin.getTempData('formOk') && table.reload();
            }
        });
    };
});
