$(function() {
    var settings = {
        url: ctx + "busiType/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                busitype: $(".busi-type-table-form").find("input[name='busitype']").val().trim(),
                businame: $(".busi-type-table-form").find("input[name='businame']").val().trim(),
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'BUSITYPE',
            title: '业务种类',
        }, {
            field: 'BUSINAME',
            title: '业务名称',
        }, {
            field: 'LASTDATE',
            title: '上次日切日期'
        }, {
            field: 'BUSISTATUS',
            title: '业务状态',
            formatter: function(value, row, index) {
                    if (value == '0') return '<span class="badge badge-success">正常</span>';
                    if (value == '1') return '<span class="badge badge-warning">关闭</span>';
                    if (value == '2') return '<span class="badge badge-warning">异常</span>';
                }
        }

        ]
    }
    $MB.initTable('busiTypeTable', settings);
});



function search() {
    $MB.refreshTable('busiTypeTable');
}

function refresh() {
    $(".busi-type-table-form")[0].reset();
    $MB.refreshTable('busiTypeTable');
}

function deleteBusiType() {
    var selected = $("#busiTypeTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的业务种类！');
        return;
    }

    // if (selected_length > 1) {
    //     $MB.n_warning('一次只能删除一个交易码！');
    //     return;
    // }
    // var chnlNo=selected[0].CHNLNO;
    // var fTranCode = selected[0].FTRANCODE;

    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].BUSITYPE;
        if (i != (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中的业务种类吗？",
        confirmButtonText: "确定删除"
    }, function() {
        // $.post(ctx + 'pkg/delete', { chnlNo:chnlNo,fTranCode:fTranCode }, function(r) {
        $.post(ctx + 'busiType/delete', { "ids": ids }, function(r) {
            if (r.code == 0) {
                $MB.n_success(r.msg);
                $MB.refreshTable('busiTypeTable');
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function updateBusiType() {
    var selected = $("#busiTypeTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的业务种类！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个业务种类！');
        return;
    }
    var busiType=selected[0].BUSITYPE;

    $.post(ctx+'busiType/findByPrimary',{busitype:busiType},function (data) {
        console.log(data);
        if(data.code==0){
            var $form=$("#busi-type-add");
            $form.modal();
            var tmp=data.msg;
            $("#busi-type-add-modal-title").html("修改业务种类");
            $form.find("input[name='busitype']").val(tmp.busitype).attr("readonly",true);
            $form.find("input[name='busitypeold']").val(tmp.busitype);
            $form.find("input[name='businame']").val(tmp.businame);
            $form.find("input[name='dettblname']").val(tmp.dettblname);
            $form.find("input[name='detstructname']").val(tmp.detstructname);
            $form.find("input[name='othername']").val(tmp.othername);
            $form.find("input[name='otheraddr']").val(tmp.otheraddr);
            $form.find("input[name='otherphone']").val(tmp.otherphone);
            $form.find("input[name='note']").val(tmp.note);
            $form.find("select[name='busistatus']").val(tmp.busistatus);


            $("#busi-type-add-btn").attr("name", "update");
        }else $MB.n_danger(data.msg);
    });
}
