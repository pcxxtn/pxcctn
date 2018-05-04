var validatorConv;
var $convAddForm=$("#conv-add-form");

$(function(){
    initBusiType();
    validatorConvRule();

    $("#conv-add .btn-save").click(function(){
        //先做表单检查
        validatorConv=$convAddForm.validate();
        var flag=validatorConv.form();

        if(flag){
            // var name = $(this).attr("name");
            // if(name=="save"){
                $.post(ctx+"pkg/map",$convAddForm.serialize(),function(data){
                    console.log("data.code:"+data.code);
                    if(data.code==0){
                        closeModal();
                        $MB.n_success(data.msg);
                        $MB.refreshTable('pkgTable');
                    }else{
                        $MB.n_danger(data.msg, '.modal');
                    }
                });
            // }

        }
    });

    $("#conv-add .btn-del").click(function() {
        $MB.confirm({
            text: "确定删除映射关系？",
            confirmButtonText: "确定删除"
        }, function () {
            // var chnlNo = $("chnlno").value;
            // var fTranCode = $("ftrancode").value;;

            $.post(ctx + 'pkg/delConv', $convAddForm.serialize(), function (r) {
                if (r.code == 0) {
                    $MB.n_success(r.msg);
                    refresh();
                } else {
                    $MB.n_danger(r.msg);
                }
            });
        });
    });


    $("#conv-add .btn-close").click(function(){
        $("#conv-add-modal-title").html('交易码映射');
        closeModal();
    });
});

function closeModal() {
    $("#pkg-add-modal-title").html("外部交易码定制");
    validatorConv.resetForm();
    $MB.closeAndRestModal("conv-add");
    $convAddForm.find("input[name='ftrancode']").removeAttr("readonly");
    $convAddForm.find("input[name='chnlno']").removeAttr("readonly");
}


function validatorConvRule() {
    var icon="<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i>"
    validatorConv=$convAddForm.validate({
        rules:{
            busitype:{
                required:true,
            },
            tranpath:{
                maxlength:30,
                required:true
            },
            // ttranname:{
            //     maxlength:30,
            //     required:true
            // },


        },
        errorPlacement:function(error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages:{
            busitype:icon+"请选择业务种类",
            tranpath:icon+"请输入正确内部映射，长度最长30",
        }
    });
}

function initBusiType(){
    $("#busitype").empty();
    $("<option> -- 请选择 --</option>")
        .appendTo("#busitype")
    $.post(ctx+"pkg/busiTypeList",{},function (r) {
        if(r.code ==0){
            var data = r.msg;
            console.info(data);
            for (var i = 0; i < data.length; i++) {
                $("<option value='" + data[i].BUSITYPE + "'>" + data[i].BUSITYPE.trim() + "</option>")
                .appendTo("#busitype")//添加下拉框的option
                 }
        }
    });

}
