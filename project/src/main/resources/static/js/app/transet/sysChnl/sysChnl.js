$(function() {
    var settings = {
        url: ctx + "sysChnl/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                chnlno: $(".syschnl-table-form").find("input[name='chnlno']").val(),
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'CHNLNO',
            title: '渠道号'
        }, {
            field: 'CHNLNAME',
            title: '渠道名称'
        }, {
            field: 'CHNLSTATUS',
            title: '渠道状态',
            formatter: function(value, row, index) {
                if (value == '0') return '<span class="badge badge-success">正常</span>';
                if (value == '1') return '<span class="badge badge-warning">关闭</span>';
                if (value == '2') return '<span class="badge badge-warning">异常</span>';
            }
        }, {
            field: 'COMMPCOL',
            title: '通讯协议'
        }, {
            field: 'COMMMODE',
            title: '通讯模式'
        }, {
            field: 'IPADDRESS',
            title: 'IP地址'
        }, {
            field: 'RECVPORT',
            title: '接收端口'
        }, {
            field: 'SENDPORT',
            title: '发送端口'
        }

        ]
    }
    $MB.initTable('syschnlTable', settings);
});

function search() {
    $MB.refreshTable('syschnlTable');
}

function refresh() {
    $(".syschnl-table-form")[0].reset();
    $MB.refreshTable('syschnlTable');
}

function deletechnls() {
    var selected = $("#syschnlTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的渠道！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].CHNLNO;
        if (i != (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中的渠道？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'sysChnl/delete', { "ids": ids }, function(r) {
            if (r.code == 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}


