var validator;
var $typeAddForm=$("#busi-type-add-form");
// var $tranLevel=$("#tranlevel");

$(function(){
    // 联系电话(手机/电话皆可)验证
    jQuery.validator.addMethod("isTel", function(value,element) {
        var length = value.length;
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
        return this.optional(element) || tel.test(value) || (length==11 && mobile.test(value));
    }, "请正确填写您的联系方式");

    validatorRule();

    $("#busi-type-add .btn-save").click(function(){

        console.info("test1");
        validator=$typeAddForm.validate();
        var flag=validator.form();
        console.info("test2");
        if(flag){
            var name = $(this).attr("name");
            console.info(name);
            if(name=="save"){
                console.info("test3");
                $.post(ctx+"busiType/add",$typeAddForm.serialize(),function(data){
                    console.log("data.code:"+data.code);
                    if(data.code==0){
                        closeModal();
                        $MB.n_success(data.msg);
                        $MB.refreshTable('busiTypeTable');
                    }else{
                        $MB.n_danger(data.msg, '.modal');
                    }
                });
            }else{
                $.post(ctx+"busiType/update",$typeAddForm.serialize(),function(data){
                    console.log("data.code:"+data.code);
                    if(data.code==0){
                        closeModal();
                        $MB.n_success(data.msg);
                        $MB.refreshTable('busiTypeTable');
                    }else{
                        $MB.n_danger(data.msg, '.modal');
                    }
                });
            }

        }
    });

    $("#busi-type-add .btn-close").click(function(){
        $("#busi-type-add-modal-title").html('新增业务种类');
        closeModal();
    });
});

function closeModal() {

    $("#busi-type-add-modal-title").html("新增业务种类");
    $("#busi-type-add-btn").attr("name", "save");
    validator.resetForm();
    $MB.closeAndRestModal("busi-type-add");
    $typeAddForm.find("input[name='busitype']").removeAttr("readonly");
}

function validatorRule() {
    var icon="<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i>"
    validator=$typeAddForm.validate({
        rules:{
            busitype:{
                required:true,
                maxlength:3,
                minlength:3,
                number:true,
                remote: {
                    url: "busiType/checkRepet",
                    type: "get",
                    dataType: "json",
                    data: {
                        busitype: function() {
                            return $("input[name='busitype']").val();
                        },busitypeold: function() {
                            return $("input[name='busitypeold']").val();
                        }

                    }
                }
            },
            businame:{required:true},
            dettblname:{
                maxlength:40,
                required:true
            },
            detstructname:{
                maxlength:40,
                required:true
            },
            busistatus:{
                required:true
            },
            othername:{
                required:true
            },
            otheraddr:{
                required:true
            },
            otherphone:{
                isTel: true
            },
            note:{
                required:true
            },
        },
        errorPlacement:function(error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages:{
            busitype: {
                required: icon + "请输入业务种类",
                maxlength: icon + "用户种类长度3个字符",
                minlength: icon + "用户种类长度3个字符",
                remote: icon + "该业务种类已存在",
            },
            businame:icon+"请输入业务名称",
            dettblname:icon+"请输入明细表名",
            detstructname:icon+"请输入明细结构名称",
            busistatus:icon+"请选择业务状态",
            othername:icon+"请输入地方名称",
            otheraddr:icon+"请输入第三方地址",
            otherphone:icon+"请输入正确的电话号码",
            note:icon+"请输入备注",
        }
    });
}