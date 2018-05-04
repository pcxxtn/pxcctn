var validator;
var $dictAddForm=$("#dictFld-add-form");
var $dictSelect=$("#dictType");
var $dictcate=$("#dictCate");

$(function(){

    init();
    validatorRule();

    $("#dictFld-add .btn-save").click(function(){
        //先做表单检查
        validator=$dictAddForm.validate();
        var flag=validator.form();

        var dictmac=$dictAddForm.find("input[name='dictMacSign']").prop("checked");
        var dictpin=$dictAddForm.find("input[name='dictPinSign']").prop("checked");
        if(dictmac)$dictAddForm.find("input[name='dictMacSign']").val(0);
        if(dictpin)$dictAddForm.find("input[name='dictPinSign']").val(0);

        console.log($dictAddForm.serialize());
        if(flag){
            var name = $(this).attr("name");
            if(name=="save"){
                $.post(ctx+"dictFld/add",$dictAddForm.serialize(),function(data){
                    if(data.code==0){
                        closeModal();
                        $MB.n_success(data.msg);
                        $MB.refreshTable("dictFldTable");
                    }else{
                        $MB.n_danger(data.msg, '.modal');
                    }
                });
            }else{
                $.post(ctx+"dictFld/update",$dictAddForm.serialize(),function(data){
                    if(data.code==0){
                        closeModal();
                        $MB.n_success(data.msg);
                        $MB.refreshTable("dictFldTable");
                    }else{
                        $MB.n_danger(data.msg, '.modal');
                    }
                });
            }
        }
    });

    $("#dictFld-add .btn-close").click(function(){
        closeModal();
    });
});

function closeModal() {
    $("#dictFld-add-modal-title").html("新增数据字典");
    $("#dictFld-add-btn").attr("name", "save");
    $MB.closeAndRestModal("dictFld-add");
    $MB.closeModal("dictFld-add");
    validator.resetForm();
    $dictAddForm.find("input[name='dictName']").removeAttr("readonly");
}

function init(){
    //查询数据字典类型
    $.post(ctx+"dict/findByParam",{tableName:"s_dict_fld",fieldName:"dict_type"},function(data){
        console.log(data);
        var option = "";
        for (var i = 0; i < data.length; i++) {
            option += "<option value='" + data[i].key + "'>" + data[i].value + "</option>"
        }
        $dictSelect.html("").append(option);
    });

    //查询字典属性
    $.post(ctx+"dict/findByParam",{tableName:"s_dict_fld",fieldName:"dict_cate"},function(data){
        console.log(data);
        var option = "";
        for (var i = 0; i < data.length; i++) {
            option += "<option value='" + data[i].key + "'>" + data[i].value + "</option>"
        }
        $dictcate.html("").append(option);
    });
    $dictAddForm.find("input[name='dictMacSign']").attr("checked",false);
    $dictAddForm.find("input[name='dictPinSign']").attr("checked",false);
}

function validatorRule() {
    var icon="<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i>"
    validator=$dictAddForm.validate({
        rules:{
            dictName:{
                required:true,
                remote: {
                    url: "dictFld/checkDictName",
                    type: "get",
                    dataType: "json",
                    data: {
                        dictName: function() {
                            return $("input[name='dictName']").val();

                        },
                        olddictName:function () {
                            return $("input[name='olddictName']").val();
                        }
                    }
                }
            },
            dictChnName:{required:true},
            dictLen:{required:true,digits:true},
            dictArrayNo:{required:true,digits:true}
        },
        errorPlacement:function(error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages:{
            dictName:{
                required:icon+"请输入数据字典名称",
                remote:icon+"该数据字典已存在"
            },
            dictChnName:icon+"请输入字典描述",
            dictLen:icon+"请输入字典长度(数字)",
            dictArrayNo:icon+"请输入字典维度(数字)"
        }
    });
}