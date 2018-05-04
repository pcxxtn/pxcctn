var validator;
var $chnlAddForm = $("#chnl-add-form");

var $commpcolSelect=$("#commpcol");

$(function() {
    InitCommPcol();
    validatorRule();
    $("#chnl-add .btn-save").click(function() {
        var name = $(this).attr("" +
            "name");
        //console.info("操作===="+name);
        validator = $chnlAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name == "save") {
                $.post(ctx + "sysChnl/add", $chnlAddForm.serialize(), function(r) {
                    if (r.code == 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg, '.modal');
                });
            }
            if (name == "update") {
                $.post(ctx + "sysChnl/update", $chnlAddForm.serialize(), function(r) {
                    if (r.code == 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg, '.modal');
                });
            }
        }
    });

    $("#chnl-add .btn-close").click(function() {
        $("#chnl-add-modal-title").html('新增渠道');
        closeModal();
    });

});

function closeModal() {
    $("#chnl-add-modal-title").html('新增渠道');
    $("#chnl-add-button").attr("name", "save");
    $MB.closeAndRestModal("chnl-add");
    validator.resetForm();
    $chnlAddForm.find("input[name='chnlno']").removeAttr("readonly");

}


function InitCommPcol() {
    $.post(ctx+"dict/findByParam",{tableName:"t_sys_chnl",fieldName:"commpcol"},function (data) {
        console.log(data);
        var option="";
        for(var i=0;i<data.length;i++){
            option+="<option value='" + data[i].key + "'>" + data[i].key+"-"+data[i].value + "</option>";
        }
        $commpcolSelect.html("").append(option);
    });
}

function validatorRule() {
    var icon="<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i>"
    validator=$chnlAddForm.validate({
        rules:{
            chnlno:{
                required:true,
                maxlength:3,
                minlength:3,
                number:true,
                remote: {
                    url: "sysChnl/check",
                    type: "get",
                    dataType: "json",
                    data: {
                        chnlno: function() {
                            return $("input[name='chnlno']").val();
                        },
                        chnlnoold: function() {
                            return $("input[name='chnlnoold']").val();
                        }
                    }
                }
            },
            chnlname: {
                required:true,
                maxlength:40
            },
            chnlstatus:{
                required:true,
                maxlength:1
            },
            commpcol:{
                required:true,
                maxlength:10
            },
            commmode:{
                required:true,
                maxlength:1
            },
            ipaddress:{required:true,ipv4:true},
            recvport:{required:true ,digits:true},
            sendport:{required:true,digits:true},
            tracecodeclrflag:{required:true, maxlength:1},
            recvlen:{required:true,digits:true},
            tracecodeoffset:{required:true,digits:true},
            tracecodelen:{required:true,digits:true},
            trancodeoffset:{required:true,digits:true},
            trancodelen:{required:true,digits:true},
            retcodeoffset:{required:true,digits:true},
            retcodelen:{required:true,digits:true},
            gatewayproc:{ maxlength:20},
            intercept:{ maxlength:10}
        },
        errorPlacement:function(error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages:{
            chnlno:{
                required:icon+"请输入渠道号",
                maxlength: icon + "渠道号长度3个字符",
                minlength: icon + "渠道号长度3个字符",
                remote:icon+"该渠道已存在"
            },
            chnlname:{
                required:icon+"请输入名称",
                maxlength: icon + "渠道号长度40个字符",
            },
            chnlstatus:icon+"请输入渠道状态",

            commpcol:{
                required:icon+"请输入通讯协议",
                maxlength: icon + "通讯协议长度10个字符",
            },
            commmode:{
                required:icon+"请输入通讯模式",
                maxlength: icon + "通讯模式长度1个字符",
            },

            ipaddress:{
                required:icon+"请输入正确的IP地址",
                ipv4: icon + "请输入正确的IP地址",
            },
            ipaddress:icon+"请输入IP地址",
            recvport:icon+"请输入接收端口(数字)",
            sendport:icon+"请输入发送端口（数字）",
            tracecodeclrflag:{
                required:icon+"请输入跟踪码清除标志",
                maxlength: icon + "跟踪码清除标志长度1个字符",
            },
            recvlen:icon+"请输入接受长度（数字）",
            tracecodeoffset:icon+"请输入跟踪码偏移（数字）",
            tracecodelen:icon+"请输入跟踪码长度（数字）",
            trancodeoffset:icon+"请输入交易码偏移（数字）",
            trancodelen:icon+"请输入交易码长度（数字）",
            retcodeoffset:icon+"请输入返回码偏移（数字）",
            retcodelen:icon+"请输入返回码长度（数字）",
            intercept:{
                maxlength: icon + "拦截器最大长度10个字符",
            },
            gatewayproc:{
                maxlength: icon + "处理函数最大长度20个字符",
            }
        }
    });
}


