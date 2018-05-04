$(function () {
    //加上从commStruct_view页面过来的参数chnlno,commstructno
    var paramStruct=$("#paramFormStruct").text();
    if(paramStruct.length>0){
        var param=paramStruct.split(",");
        $(".commFld-table-form").find("input[name='chnlnoType']").val(param[0]);
    }
    console.log(paramStruct);
    var settings = {
        url: ctx + "commFld/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                commstructfldname: $(".commFld-table-form").find("input[name='commfldname']").val(),
                chnlno: $(".commFld-table-form").find("input[name='chnlnoType']").val(),
                fldtype: $(".commFld-table-form").find("select[name='type']").val()
            };
        },
        columns: [{
            checkbox: true
        },{field: 'chnlno',
            visible: false
        },{field: 'commstructno',
            visible: false
        },{field: 'commstructname',
            visible: false
        },{field: 'commstructtype',
            visible: false
        },{field: 'commstructfldno',
            visible: false
        },{field: 'commstructfldname',
            title: '字段名称'
        },{field: 'commstructfldexpr',
            title: '字段描述'
        },{field: 'fldtype',
            title: '字段类型',
            formatter:function (value) {
                switch (value){
                    case '0':
                        return 'B-Sub Begin';
                    case '1':
                        return 'E-Sub End';
                    case '2':
                        return 'V-变量';
                    default:
                        break;
                }
            }
        },{field: 'structvartype',
            title: '结构变量类型',
            formatter:function (value) {
                switch (value){
                    case '0':
                        return '字符串';
                    case '1':
                        return '双精度';
                    case '2':
                        return '浮点数';
                    case '3':
                        return '整型';
                    case '4':
                        return '长整型';
                    case '5':
                        return '短整型';
                    default:
                        break;
                }
            }
        },{field: 'mixvartype',
            title: '混合变量类型',
            formatter:function (value) {
                switch (value){
                    case '0':
                        return 'C-定长';
                    case '1':
                        return 'N-NLVAR';
                    default:
                        break;
                }
            }
        },{field: 'fldlen',
            title: '字段长度'
        },{field: 'alignmode',
            title: '对齐方式',
            formatter:function (value) {
                switch (value){
                    case '0':
                        return '左对齐';
                    case '1':
                        return '右对齐';
                    default:
                        break;
                }
            }
        },{field: 'fillsmbl',
            title: '填充字符'
        },{field: 'endsmblflag',
            title: '结束符标志'
        },{
            field: 'breaksmbl',
            title: '分隔符'
        }, {
            field: 'nlvarlen',
            visible: false
        }, {
            field: 'nlvarflag',
            visible: false
        }, {
            field: 'nlvarpower',
            visible: false
        }, {
            field: 'varno',
            visible: false
        }]
    };
    $MB.initTable('commFldTable', settings);
});

function search() {
    $MB.refreshTable('commFldTable');
}

function refresh() {
    $(".commFld-table-form")[0].reset();
    $MB.refreshTable('commFldTable');
}


function updateCommFld() {
    console.log("大日志啊····");
    var selected = $("#commFldTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的数据字典!');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个!');
        return;
    }
    var $form=$("#commFld-add");
    $form.modal();

    $("#commFld-add-modal-title").html("修改结构字典");
    $form.find("select[name='chnlno']").val(selected[0].chnlno).attr("disabled",true);

    //为了更新commstructno选项
    changeChnlno();
    $form.find("select[name='commstructno']").val(selected[0].commstructno).attr("disabled",true);

    $form.find("input[name='commstructfldno']").val(selected[0].commstructfldno);
    $form.find("input[name='commstructfldname']").val(selected[0].commstructfldname).attr("readonly",true);
    $form.find("input[name='oldcommstructfldname']").val(selected[0].commstructfldname);
    $form.find("input[name='commstructfldexpr']").val(selected[0].commstructfldexpr);
    $form.find("select[name='fldtype']").val(selected[0].fldtype);
    $form.find("select[name='structvartype']").val(selected[0].structvartype);
    $form.find("select[name='mixvartype']").val(selected[0].mixvartype);
    $form.find("input[name='fldlen']").val(selected[0].fldlen);
    $form.find("select[name='alignmode']").val(selected[0].alignmode);

    $form.find("input[name='fillsmbl']").val(selected[0].fillsmbl);
    $form.find("select[name='endsmblflag']").val(selected[0].endsmblflag);
    $form.find("input[name='breaksmbl']").val(selected[0].breaksmbl);
    $form.find("input[name='nlvarlen']").val(selected[0].nlvarlen);
    $form.find("select[name='nlvarflag']").val(selected[0].nlvarflag);
    $form.find("select[name='nlvarpower']").val(selected[0].nlvarpower);
    $form.find("input[name='varno']").val(selected[0].varno);
    $form.find("input[name='pinflag']").val(selected[0].pinflag).attr("checked",selected[0].pinflag=='1'?true:false);
    $form.find("input[name='macflag']").val(selected[0].macflag).attr("checked",selected[0].macflag=='1'?true:false);

    $("#commFld-add-btn").attr("name", "update");
}

function deleteCommFld() {
    var selected = $("#commFldTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的数据!');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].chnlno+"|"+selected[i].commstructno+"|"+selected[i].commstructfldno;
        if (i != (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中的字典？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'commFld/delete', { "ids": ids }, function(r) {
            if (r.code == 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function addCommFld() {
    console.log("-----addCommFld----");
    var commOption=[];
    commOption=chnlcom.filter(function (value) {
        return value.chnlno==$("#chnlno").val();
    });
    var commo="";
    for(var k=0;k<commOption.length;k++){
        console.log(commOption[k]);
        commo+="<option value='"+commOption[k].commstructno+"'>"+commOption[k].commstructname+"</option>"
    }
    $commstructnoSelect.html("").append(commo);
}