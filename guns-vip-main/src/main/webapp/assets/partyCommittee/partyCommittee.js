layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 党委表管理
     */
    var PartyCommittee = {
        tableId: "partyCommitteeTable"
    };

    /**
     * 初始化表格的列
     */
    PartyCommittee.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'code', sort: true, title: '编号'},
            {field: 'name', sort: true, title: '党委名称'},
            {field: 'phone', hide: true, sort: true, title: '电话'},
            {field: 'createTime', hide: true, sort: true, title: '创建时间'},
            {field: 'createUser', hide: true, sort: true, title: '创建人id'},
            {field: 'updateTime', hide: true, sort: true, title: '修改时间'},
            {field: 'updateUser', hide: true, sort: true, title: '修改人id'},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth:300}
        ]];
    };

    /**
     * 点击查询按钮
     */
    PartyCommittee.search = function () {
        var queryData = {};
        queryData['name'] = $("#condition").val();
        table.reload(PartyCommittee.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    PartyCommittee.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/partyCommittee/add';
    };

    /**
     * 导出excel按钮
     */
    PartyCommittee.exportExcel = function () {
        var checkRows = table.checkStatus(PartyCommittee.tableId);
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
    PartyCommittee.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/partyCommittee/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    PartyCommittee.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/partyCommittee/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(PartyCommittee.tableId);
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
        elem: '#' + PartyCommittee.tableId,
        url: Feng.ctxPath + '/partyCommittee/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: PartyCommittee.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        PartyCommittee.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        PartyCommittee.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        PartyCommittee.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + PartyCommittee.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            PartyCommittee.openEditDlg(data);
        } else if (layEvent === 'delete') {
            PartyCommittee.onDeleteItem(data);
        }else if (layEvent === 'manager') {
            PartyCommittee.managerManage(data);
        }else if (layEvent === 'branch') {
            PartyCommittee.branchManage(data);
        }
    });

    PartyCommittee.branchManage = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '支部管理',
            area: ['90%', '90%'],
            content: Feng.ctxPath + '/partyBranch?partCommittee=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload();
            }
        });
    };

    PartyCommittee.managerManage = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '负责人管理',
            area: ['90%', '90%'],
            content: Feng.ctxPath + '/userExt?baseID=' + data.id+"&type=committee",
            end: function () {
                admin.getTempData('formOk') && table.reload();
            }
        });
    };
});
