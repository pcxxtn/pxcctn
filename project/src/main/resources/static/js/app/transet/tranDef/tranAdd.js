var validator;
var $tranAddForm=$("#tran-add-form");
// var $tranLevel=$("#tranlevel");

$(function(){

    init();
    validatorRule();

    $("#tran-add .btn-save").click(function(){
        //先做表单检查
        validator=$tranAddForm.validate();
        var flag=validator.form();

        // $dictAddForm.find("input[name='dictMacSign']").val(1);
        // $dictAddForm.find("input[name='dictPinSign']").val(1);
        if(flag){
            var name = $(this).attr("name");
            if(name=="save"){
                $.post(ctx+"tran/add",$tranAddForm.serialize(),function(data){
                    console.log("data.code:"+data.code);
                    if(data.code==0){
                        closeModal();
                        $MB.n_success(data.msg);
                        $MB.refreshTable('tranTable');
                    }else{
                        $MB.n_danger(data.msg, '.modal');
                    }
                });
            }else{
                $.post(ctx+"tran/update",$tranAddForm.serialize(),function(data){
                    console.log("data.code:"+data.code);
                    if(data.code==0){
                        closeModal();
                        $MB.n_success(data.msg);
                        $MB.refreshTable('tranTable');
                    }else{
                        $MB.n_danger(data.msg, '.modal');
                    }
                });
            }

        }
    });

    $("#tran-add .btn-close").click(function(){
        $("#tran-add-modal-title").html('交易码定制');
        closeModal();
    });
});

function closeModal() {
    $("#tran-add-modal-title").html("交易码定制");
    $("#tran-add-btn").attr("name", "save");
    validator.resetForm();
    $MB.closeAndRestModal("tran-add");
    // $MB.closeModal("tran-add");
    $tranAddForm.find("input[name='tranCode']").removeAttr("readonly");
}

function init(){

}

function validatorRule() {
    var icon="<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i>"
    validator=$tranAddForm.validate({
        rules:{
            tranCode:{
                required:true,
                remote: {
                    url: "tran/checkTranCode",
                    type: "get",
                    dataType: "json",
                    data: {
                        tranCode: function() {
                            return $("input[name='tranCode']").val();
                        },
                        oldusername: function() {
                            return $("input[name='oldTranCode']").val();
                        }
                    }
                }
            },
            tranChnName:{required:true},
            busino:{required:true},
            miditimes:{
                maxlength: 2,
                number:true,
            },
            tranlevel:{required:true},
            tranchar:{required:true},
            jourflag:{required:true},

        },
        errorPlacement:function(error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages:{
            tranCode:icon+"该交易码已存在",
            tranChnName:icon+"请输入交易码描述",
            busino:icon+"请输入业务号",
            miditimes:icon+"请输入正确冲正次数",
            tranlevel:icon+"请选择交易级别",
            tranchar:icon+"请选择交易性质",
            jourflag:icon+"请选择流水标志",

        }
    });
}