$(function() {
    // $("#cardBlockDict").css("display","none");
    var settings = {
        url: ctx + "transet/spkg/list",
        pageSize: 20,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                chnlno: $(".spkg-table-form").find("input[name='chnlno']").val().trim(),
                commstructno: $(".spkg-table-form").find("input[name='commstructno']").val().trim(),
                // datapkgno: $(".spkg-table-form").find("input[name='datapkgno']").val().trim(),
                datapkgname: $(".spkg-table-form").find("input[name='datapkgname']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'chnlno',
            title: '渠道号'
        },{
            field: 'commstructno',
            title: '通讯结构号'
        }, {
            field: 'datapkgno',
            title: '格式号'
        }, {
            field: 'datapkgname',
            title: '格式名称'
        }
        // ,{
        //     field: 'datapkgtype',
        //     title: '格式类型',
        //     formatter: function(value, row, index) {
        //         if (value == '8') return '8－8583';
        //         else if (value == 'N') return 'N－不含Bitmap的类8583';
        //         else if (value == 'S') return 'S－选择数据';
        //         else if (value == 'X') return 'X－xml';
        //         else return '-';
        //     }
        // }
        ,{
            field: 'preproc',
            title: '前处理',
            formatter: function(value, row, index) {
                if (value == '0000') return '空函数';
                else if (value == '1111') return 'main函数';
                else return '其他函数';
            }
        },{
            field: 'endproc',
            title: '后处理',
            formatter: function(value, row, index) {
                if (value == '0000') return '空函数';
                else if (value == '1111') return 'main函数';
                else return '其他函数';
            }
        },{
            field: 'macmode',
            title: 'mac方式',
            formatter: function(value, row, index) {
                if (value == '0') return '去填充符结束符分隔符';
                else if (value == '1') return '整个字段（只去掉分隔符）';
                else return '-';
            }
        },{
            field: 'pinmode',
            title: 'pin方式',
            formatter: function(value, row, index) {
                if (value == '0') return '去填充符结束符分隔符';
                else if (value == '1') return '整个字段（只去掉分隔符）';
                else return '-';
            }
        },{
            field: 'credtype',
            title: '凭证类别'
        },{
            field: 'icredflag',
            title: '重空标志',
            formatter: function(value, row, index) {
                if (value == '0') return '打印';
                else if (value == '1') return '不打印';
                else return '-';
            }
        }
        ]
    }
    $MB.initTable('spkgTable', settings);

});

function search() {
    $MB.refreshTable('spkgTable');
}

function refresh() {
    $(".spkg-table-form")[0].reset();
    $MB.refreshTable('spkgTable');
}

function deleteSpkg() {
    var selected = $("#spkgTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].datapkgno;
        if (i != (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中？",
        confirmButtonText: "确定删除"
    }, function() {
        // console.info("ids"+ids);
        $.post(ctx + 'transet/spkg/delete', { "datapkgnos": ids }, function(r) {
            if (r.code == 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function spkgDicts() {
    var selected = $("#spkgTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的报文格式的域！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个报文格式的域！');
        return;
    }

    var chnlno = selected[0].chnlno;
    var datapkgno = selected[0].datapkgno;
    var commstructno = selected[0].commstructno;
    //加载配置数据报文域页面
    $.post(ctx + "transet/sysPkgFld/spkgDicts", {"chnlno1":chnlno,"datapkgno1":datapkgno,"commstructno1":commstructno}, function(r) {
        $main_content.html("").append(r);
    });
}


