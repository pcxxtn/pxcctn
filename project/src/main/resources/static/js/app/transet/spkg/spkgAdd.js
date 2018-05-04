var validator;
var $spkgAddForm = $("#spkg-add-form");

$(function() {
    validateRule();
    initChnlno();
    createDeptTree();

    $("#spkg-add .btn-save").off().on('click', function() {
        var name = $(this).attr("name");

        var validator = $spkgAddForm.validate();
        var flag = validator.form();
        console.info(flag+"--"+name);
        if (flag) {
            if (name == "save") {
                $.post(ctx + "transet/spkg/add", $spkgAddForm.serialize(), function(r) {
                    if (r.code == 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("spkgTable");
                    } else $MB.n_danger(r.msg, '.modal');
                });
            }
            if (name == "update") {
                $.post(ctx + "transet/spkg/update", $spkgAddForm.serialize(), function(r) {
                    if (r.code == 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("spkgTable");
                    } else $MB.n_danger(r.msg, '.modal');
                });
            }
        }
    });

    $("#spkg-add .btn-close").click(function() {
        $("#spkg-add-modal-title").html('新增报文格式');
        closeModal();
    });


    $('#chnlno').change(function(){
        var chnlno=$(this).children('option:selected').val();//这就是selected的值
        // console.info(chnlno+"渠道号")
        if(chnlno!=""){
            initDict(chnlno);
        }
    })

});


function closeModal() {
    $("#spkg-add-modal-title").html('新增报文格式');
    $("#spkg-add-button").attr("name", "save");
    validator.resetForm();
    // $rolesSelect.multipleSelect("refresh");
    // $MB.resetJsTree("deptTree");
    $MB.closeAndRestModal("spkg-add");
    $('#spkg-add').find("select[name='chnlno']").removeAttr("readonly");
    // $packDefAddForm.find(".packDef_password").show();
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $spkgAddForm.validate({
        rules: {
            chnlno: {
                required: true
            },
            datapkgname: {
                required: true,
                minlength: 1,
                maxlength: 40
            },
            commstructno: {
                required: true
            },
            // datapkgtype: {
            //     required: true
            // },
            preproc: {
                // required: true
            },
            endproc: {
                // required: true
            },
            macmode: {
                // required: true,
                // minlength: 1,
                // maxlength: 10
            },
            pinmode: {
                // required: true,
                // minlength: 1,
                // maxlength: 10
            },
            credtype: {
                // required: true,
                // minlength: 0,
                // maxlength: 10
            },
            icredflag: {
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
            chnlno: {
                required: icon + "请选择渠道"
            },
            datapkgname: {
                required: icon + "请输入格式名称",
                minlength: icon + "格式名称长度1到40个字符"
            },
            commstructno: {
                required: icon + "请输入通讯结构号",
                minlength: icon + "填充字符长度1到10个字符"
            },
            // datapkgtype: icon + "请选择报文格式类型",
            preproc:{
                // required: icon + "请输入前处理",
                // minlength: icon + "填充字符长度1到10个字符"
            },
            endproc:{
                // required: icon + "请输入后处理",
                // minlength: icon + "填充字符长度1到10个字符"
            },
            macmode:{
                // required: icon + "请输入mac方式",
                // minlength: icon + "填充字符长度1到10个字符"
            },
            pinmode:{
                // required: icon + "请输入pin方式",
                // minlength: icon + "填充字符长度1到10个字符"
            },
            credtype:{
                // required: icon + "请输入凭证类别",
                // minlength: icon + "填充字符长度1到10个字符"
            },
            icredflag:{
                // required: icon + "请选择重空标志"
            }
        }
    });
}

function initChnlno() {
    $("#chnlno").empty();
    $("<option value=''> -- 请选择 --</option>")
        .appendTo("#chnlno")
    $.post(ctx+"transet/spkg/sysChnlList",{},function (r) {
        var data = r.msg;
        for (var i = 0; i < data.length; i++) {
            $("<option value='" + data[i].CHNLNO + "'>" + data[i].CHNLNAME + "</option>")
                .appendTo("#chnlno")//添加下拉框的option
        }
    });
}


function initDict(chnlno) {
    $("#commstructno").empty();
    $.post(ctx + "transet/spkg/dictList", {"chnlno":chnlno}, function(r) {
        var data = r.msg;
        for (var i = 0; i < data.length; i++) {
            $("<option value='" + data[i].commstructno + "'>" + data[i].commstructname + "</option>")
                .appendTo("#commstructno")//添加下拉框的option
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


function getDept() {
    var ref = $('#deptTree').jstree(true);
    $("[name='deptId']").val(ref.get_selected()[0]);
}
