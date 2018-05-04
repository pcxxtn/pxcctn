var validate;
var $commStructaddForm=$("#commStruct-add-form");
var $chnlnoSelect=$("#chnlno");

$(function () {
    init();
    validateInit();

    $("#commStruct-add .btn-save").click(function () {
        validate=$commStructaddForm.validate();

        var ifspace=$commStructaddForm.find("input[name='ifspace']").prop("checked");
        if(ifspace)$commStructaddForm.find("input[name='ifspace']").val(1);

        //当选择框属性为disabled时,不能取值 所以去除属性就可以取值了
        $commStructaddForm.find("select[name='chnlno']").removeAttr("disabled");

        var form=validate.form();
        console.log($commStructaddForm.serialize());
        if(form){
            var name=$(this).attr("name");
            if(name=='save'){
                $.post(ctx+"commStruct/add",$commStructaddForm.serialize(),function (data) {
                    console.log(data);
                    if(data.code==0){
                        $MB.n_success(data.msg);
                        closeModal();
                        //移除所有div中class为col-lg-2的元素
                        $("div").remove(".col-lg-2");
                        //再次加载通讯结构
                        initStruct();
                    }else  $MB.n_danger(data.msg);
                });
            }else{
                $.post(ctx+"commStruct/update",$commStructaddForm.serialize(),function (data) {
                    console.log(data);
                    if(data.code==0){
                        $MB.n_success(data.msg);
                        closeModal();
                        //移除所有div中class为col-lg-2的元素
                        $("div").remove(".col-lg-2");
                        //再次加载通讯结构
                        initStruct();
                    }else  $MB.n_danger(data.msg);
                });
            }
        }
    });

    $("#commStruct-add .btn-close").click(function () {
        closeModal();
    });
});

function closeModal() {
    console.log("我想看看");
    $("#commStruct-add-modal-title").html("新增通讯结构");
    $("#commStruct-add-btn").attr("name","save");
    $MB.closeAndRestModal("commStruct-add");
    validate.resetForm();
    $commStructaddForm.find("input[name='commstructname']").removeAttr("readonly");
    $commStructaddForm.find("input[name='commstructno']").removeAttr("readonly");
    // $commStructaddForm.find("input[name='ifspace']").removeAttr("checked");

}

function init() {
    //获取渠道下拉菜单
    $.post(ctx+"sysChnl/getChnlOption",{},function (data) {
        console.log(data);
        var option;
        $chnlnoSelect.html("").append('<option value="">请选择</option>');
        if(data.length>0){
            for(var i=0;i<data.length;i++){
                option += "<option value='" + data[i].chnlno + "'>" + data[i].chnlno+"-"+data[i].chnlname + "</option>";
            }
            $chnlnoSelect.append(option);
        }
    });
}

function validateInit() {
    var icon="<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i>";
    validate=$commStructaddForm.validate({
        rules:{
            commstructname:{
                required:true,
                remote:{
                    url: "commStruct/checkStructName",
                    type: "get",
                    dataType: "json",
                    data: {
                        chnlno:function() {
                            return $("select[name='chnlno']").val();

                        },
                        commstructname: function() {
                            return $("input[name='commstructname']").val();

                        },
                        oldcommstructname:function () {
                            return $("input[name='oldcommstructname']").val();
                        }
                    }
                }
            },
            commstructno:{
                required:true,
                remote:{
                    url: "commStruct/checkStructNo",
                    type: "get",
                    dataType: "json",
                    data: {
                        chnlno:function() {
                            return $("select[name='chnlno']").val();

                        },
                        commstructno: function() {
                            return $("input[name='commstructno']").val();

                        },
                        oldcommstructno:function () {
                            return $("input[name='oldcommstructno']").val();
                        }
                    }
                },
                // digital:true,
                // rangelength:[6,6]
            },
            chnlno:{required:true},
            commstructtype:{required:true},
            version:{required:true},
            encoding:{required:true}
        },errorPlacement:function (error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages:{
            commstructname:{
                required:icon+"请输入通讯结构名称",
                remote:icon+"该结构名称已存在"
            },
            commstructno:{
                required:icon+"请输入通讯结构号",
                remote:icon+"该结构号已存在",
                // digital:icon+"请输入数字",
                // rangelength:icon+"请输入6位数字"
            },
            chnlno:{required:icon+"请选择渠道"},
            commstructtype:{required:icon+"请选择通讯结构类型"},
            version:{required:icon+"请选择xml版本号"},
            encoding:{required:icon+"请选择编码方式"}
        }
    });
}