var validator1;
var $spkgProForm = $("#spkg-proc-func-form");
$(function() {
    validateRule1();
    createDeptTree();
    $("#spkg-proc-func-btn").off().on('click', function() {
        var fn = $spkgProForm.find("select[name='procfunc']").val();
        var cir = $spkgProForm.find("input[name='circleSum']").val();
        var selected = $("#spkgConfigTable").bootstrapTable('getSelections');
        selected[0].procfunc = fn + "|" + cir;
        var validator = $spkgProForm.validate();
        var flag = validator.form();
        // console.info(flag);
        if (flag) {
            $.post(ctx + "transet/sysPkgFld/update", $spkgProForm.serialize(), function(r) {
                if (r.code == 0) {
                    closeModals();
                    $MB.n_success(r.msg);
                    // $MB.refreshTable("spkgConfigTable");
                    $('#spkgConfigTable').bootstrapTable('updateRow', {index: getIdSelections(), row: selected[0]});//index---->更新行的索引。row---->要更新的数据
                } else $MB.n_danger(r.msg, '.modal');
            });
        }
    });

    $("#spkg-proc-func .btn-close").click(function() {
        closeModals();
    });


});

//获取选中行的index索引号
function getIdSelections() {
    return $.map($("#spkgConfigTable").bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

function closeModals() {
    validator1.resetForm();
    $MB.closeAndRestModal("spkg-proc-func");
    // $('#spkg-add').find("select[name='chnlno']").removeAttr("readonly");
    // $packDefAddForm.find(".packDef_password").show();
}

function validateRule1() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator1 = $spkgProForm.validate({
        rules: {
            procfunc: {
                required: true
            },
            circleSum:{
                required: true
            }
        },
        errorPlacement: function(error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages: {
            procfunc: {
                required: icon + "请选择处理函数"
            },
            circleSum:{
                required: icon + "请输入循环次数"
            }
        }
    });
}

function createDeptTree() {
    $.post(ctx + "dept/tree", {}, function(r) {
        if (r.code == 0) {
            var data = r.msg;
            $('#deptTree').jstree({
                "core": {
                    'data': data.children,
                    'multiple': false // 取消多选
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false // 取消选择父节点后选中所有子节点
                },
                "plugins": ["wholerow", "checkbox"]
            });
        } else {
            $MB.n_danger(r.msg);
        }
    })

}