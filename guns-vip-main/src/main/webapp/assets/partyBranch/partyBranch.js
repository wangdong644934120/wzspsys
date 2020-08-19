layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var partCommittee =  Feng.getUrlParam("partCommittee");
    /**
     * 党支部表管理
     */
    var PartyBranch = {
        tableId: "partyBranchTable"
    };

    /**
     * 初始化表格的列
     */
    PartyBranch.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: 'id'},
            {field: 'code', sort: true, title: '编号'},
            {field: 'name', sort: true, title: '名称'},
            {field: 'communityName', sort: true, title: '报到社区'},
            {field: 'phone',hide: true, sort: true, title: '电话'},
            {field: 'createTime',hide: true, sort: true, title: '创建时间'},
            {field: 'createUser',hide: true, sort: true, title: '创建人id'},
            {field: 'updateTime',hide: true, sort: true, title: '修改时间'},
            {field: 'updateUser',hide: true, sort: true, title: '修改人id'},
            {field: 'partCommittee',hide: true, sort: true, title: '党委id'},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth:400}
        ]];
    };

    /**
     * 点击查询按钮
     */
    PartyBranch.search = function () {
        var queryData = {};
        queryData['name'] = $("#condition").val();
        queryData['partCommittee'] = partCommittee;
        table.reload(PartyBranch.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    PartyBranch.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/partyBranch/add?partCommittee='+partCommittee;
    };

    /**
     * 导出excel按钮
     */
    PartyBranch.exportExcel = function () {
        var checkRows = table.checkStatus(PartyBranch.tableId);
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
    PartyBranch.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/partyBranch/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    PartyBranch.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/partyBranch/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(PartyBranch.tableId);
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
        elem: '#' + PartyBranch.tableId,
        url: Feng.ctxPath + '/partyBranch/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        where:{"partCommittee": partCommittee},
        cols: PartyBranch.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        PartyBranch.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        PartyBranch.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        PartyBranch.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + PartyBranch.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            PartyBranch.openEditDlg(data);
        } else if (layEvent === 'delete') {
            PartyBranch.onDeleteItem(data);
        }else if (layEvent === 'manager') {
            PartyBranch.managerManage(data);
        }else if (layEvent === 'person') {
            PartyBranch.personManage(data);
        }else if (layEvent === 'comminity') {
            PartyBranch.setComminity(data);
        }
    });

    PartyBranch.personManage = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '党员管理',
            area: ['90%', '90%'],
            content: Feng.ctxPath + '/partyBranchPerson?branch=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload();
            }
        });
    };

    PartyBranch.managerManage = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '负责人管理',
            area: ['90%', '90%'],
            content: Feng.ctxPath + '/userExt?baseID=' + data.id+"&type=branch",
            end: function () {
                admin.getTempData('formOk') && table.reload();
            }
        });
    };

    PartyBranch.setComminity = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '设置报到社区',
            area: ['500px', '400px'],
            content: Feng.ctxPath + '/partyBranch/toCommunity?branch=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload();
            }
        });
    };

});
