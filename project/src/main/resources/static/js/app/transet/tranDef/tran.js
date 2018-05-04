$(function() {
    var settings = {
        url: ctx + "tran/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                busino: $(".tran-table-form").find("input[name='busiNo']").val().trim(),
                tranCode: $(".tran-table-form").find("input[name='tranCode']").val().trim(),
                tranChnName: $(".tran-table-form").find("input[name='chnName']").val().trim(),
                // status: $(".user-table-form").find("select[name='status']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'BUSINO',
            title: '业务号',
        }, {
            field: 'TRAN_CODE',
            title: '交易码',
        }, {
            field: 'TRAN_CHN_NAME',
            title: '交易码中文名'
        }, {
            field: 'TRANCHAR',
            title: '交易性质'
        }, {
            field: 'JOURFLAG',
            title: '流水标志'
        }, {
            field: 'TRANLEVEL',
            title: '交易级别'
            }, {
             field: 'MODITIMES',
            title: '冲正次数'
            }, {
            field: 'MODILEVEL',
            title: '冲正级别'
            }, {
            field: 'NOTE',
            title: '备注'
           },{
            field: 'CREATE_TIME',
            title: '创建时间',
            formatter: function(value, row, index) {
                return $MB.dateFormat(value, "yyyy-MM-dd hh:mm:ss");
            }
        }, {
            // field: 'DICTTYPE',
            // title: '字典类型',
            // formatter: function(value, row, index) {
            //     if (value == 'C') return '<span class="badge badge-success">字符串</span>';
            //     if (value == 'D') return '<span class="badge badge-warning">双精度</span>';
            // }
        }

        ]
    }
    $MB.initTable('tranTable', settings);
});

function search() {
    $MB.refreshTable('tranTable');
}

function refresh() {
    $(".tran-table-form")[0].reset();
    $MB.refreshTable('tranTable');
}

function deleteTran() {
    var selected = $("#tranTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的交易码！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].TRAN_CODE;
        if (i != (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中的交易码？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'tran/delete', { "ids": ids }, function(r) {
            if (r.code == 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function updateTran() {
    var selected = $("#tranTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的交易码！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个交易码！');
        return;
    }
    var tranCode=selected[0].TRAN_CODE;
    console.log(tranCode);
    $.post(ctx+'tran/findByPrimary',{tranCode:tranCode},function (data) {
        console.log(data);
        if(data.code==0){
            var $form=$("#tran-add");
            $form.modal();
            var tran=data.msg;
            $("#tran-add-modal-title").html("修改数据字典");
            $form.find("input[name='busino']").val(tran.busino);
            $form.find("input[name='tranCode']").val(tran.tranCode).attr("readonly",true);
            $form.find("input[name='tranChnName']").val(tran.tranChnName);
            $form.find("select[name='tranchar']").val(tran.tranchar);
            $form.find("select[name='jourflag']").val(tran.jourflag);
            $form.find("select[name='tranlevel']").val(tran.tranlevel);
            $form.find("input[name='moditimes']").val(tran.moditimes);
            $form.find("input[name='modilevel']").val(tran.modilevel);
            $form.find("input[name='note']").val(tran.note);

            $("#tran-add-btn").attr("name", "update");
        }else $MB.n_danger(data.msg);
    });
}