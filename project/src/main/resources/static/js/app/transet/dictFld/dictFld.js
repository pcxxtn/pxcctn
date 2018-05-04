$(function() {
    var settings = {
        url: ctx + "dictFld/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                dictChnName: $(".dictFld-table-form").find("input[name='chnname']").val(),
                dictName: $(".dictFld-table-form").find("input[name='egnname']").val(),
                dictType: $(".dictFld-table-form").find("select[name='type']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'DICTNO',
            visible: false
        }, {
            field: 'DICTCHNNAME',
            title: '字典中文名'
        }, {
            field: 'DICTNAME',
            title: '字典名'
        }, {
            field: 'DICTTYPE',
            title: '字典类型',
            formatter:function (value, row, index) {
                switch (value){
                    case '0':
                        return '字符串';
                        break;
                    case '1':
                        return '双精度';
                        break;
                    case '2':
                        return '浮点数';
                        break;
                    case '3':
                        return '整型';
                        break;
                    case '4':
                        return '长整型';
                        break;
                    case '5':
                        return '短整型';
                        break;
                    default:
                        break;
                }
            }
        }, {
            field: 'DICTLEN',
            title: '字典长度'
        }, {
            field: 'DICTARRAYNO',
            title: '维度'
        }, {
            field: 'DICTCREATETIME',
            title: '创建时间'
            // formatter: function(value, row, index) {
            //     return $MB.dateFormat(value, "yyyy-MM-dd hh:mm:ss");
            // }
        }, {
            field: 'DICTCATE',
            title: '字典属性',
            formatter: function(value, row, index) {
                switch(value){
                    case 0:
                        return '<span class="badge badge-info">属性</span>';
                        break;
                    case 1:
                        return '<span class="badge badge-primary">元素</span>';
                        break;
                    case 2:
                        return '<span class="badge badge-warning">注释</span>';
                        break;
                    default:
                        break;
                }
            }
        }

        ]
    }
    $MB.initTable('dictFldTable', settings);
});

function search() {
    $MB.refreshTable('dictFldTable');
}

function refresh() {
    $(".dictFld-table-form")[0].reset();
    $MB.refreshTable('dictFldTable');
}

function deleteDictFld() {
    var selected = $("#dictFldTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的数据字典！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].DICTNO;
        if (i != (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中的字典？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'dictFld/delete', { "ids": ids }, function(r) {
            if (r.code == 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function updateDictFld() {
    var selected = $("#dictFldTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的数据字典！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个用户！');
        return;
    }
    var dictNo=selected[0].DICTNO;
    console.log(dictNo);
    $.post(ctx+"dictFld/findByPrimary",{dictNo:dictNo},function (data) {
        console.log(data);
        if(data.code==0){
            var $form=$("#dictFld-add");
            $form.modal();
            var dict=data.msg;
            $("#dictFld-add-modal-title").html("修改数据字典");
            $form.find("input[name='dictNo']").val(dict.dictNo);
            $form.find("input[name='dictName']").val(dict.dictName).attr("readonly",true);
            $form.find("input[name='olddictName']").val(dict.dictName);
            $form.find("input[name='dictChnName']").val(dict.dictChnName);
            $form.find("select[name='dictType']").val(dict.dictType);
            $form.find("select[name='dictCate']").val(dict.dictCate);
            $form.find("input[name='dictLen']").val(dict.dictLen);
            $form.find("input[name='dictArrayNo']").val(dict.dictArrayNo);
            $dictAddForm.find("input[name='dictMacSign']").attr("checked",dict.dictMacSign==0?true:false);
            $form.find("select[name='dictPinSign']").attr("checked",dict.dictPinSign==0?true:false);

            $("#dictFld-add-btn").attr("name", "update");
        }else $MB.n_danger(data.msg);
    });
}
