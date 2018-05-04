var validator;
var $pkgAddForm=$("#pkg-add-form");
// var $tranLevel=$("#tranlevel");

$(function(){

    initChnl();
    validatorRule();

    // $("input[name='ownflag']").change(function() {
    //     var checked = $(this).is(":checked");
    //     var $sownflag_label = $("#ownflag");
    //     if (checked) $sownflag_label.html('可修改');
    //     else $sownflag_label.html('锁定');
    // });

    $("#pkg-add .btn-save").click(function(){
        //先做表单检查
        validator=$pkgAddForm.validate();
        var flag=validator.form();


        console.log($pkgAddForm.serialize());
        if(flag){
            var name = $(this).attr("name");
            if(name=="save"){
                $.post(ctx+"pkg/add",$pkgAddForm.serialize(),function(data){
                    console.log("data.code:"+data.code);
                    if(data.code==0){
                        closeModalPkg();
                        $MB.n_success(data.msg);
                        $MB.refreshTable('pkgTable');
                    }else{
                        $MB.n_danger(data.msg, '.modal');
                    }
                });
            }else{
                $.post(ctx+"pkg/update",$pkgAddForm.serialize(),function(data){
                    console.log("data.code:"+data.code);
                    if(data.code==0){
                        closeModalPkg();
                        $MB.n_success(data.msg);
                        $MB.refreshTable('pkgTable');
                    }else{
                        $MB.n_danger(data.msg, '.modal');
                    }
                });
            }

        }
    });

    $("#pkg-add .btn-close").click(function(){
        $("#pkg-add-modal-title").html('外部交易码定制');
        closeModalPkg();
    });

    $('#chnlno').change(function(){
        var chnlno=$(this).children('option:selected').val();//这就是selected的值
        if(chnlno!=""){
            initPkg(chnlno);
        }
    })

});

function closeModalPkg() {

    $("#pkg-add-modal-title").html("外部交易码定制");
    $("#pkg-add-btn").attr("name", "save");
    validator.resetForm();
    $MB.closeAndRestModal("pkg-add");
    $pkgAddForm.find("input[name='ftrancode']").removeAttr("readonly");
    $pkgAddForm.find("input[name='chnlno']").removeAttr("readonly");
    $pkgAddForm.find("input[name='ftranname']").removeAttr("readonly");

    // $("#packpkgno").empty();
    // $("#unpackpkgno").empty();
    // $("#errpkgno").empty();

}


function initChnl(){
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

//获取渠道下的包格式号
function initPkg(chnlno) {
    $("#packpkgno").empty();
    $("#unpackpkgno").empty();
    $("#errpkgno").empty();

    $.post(ctx + "pkg/pkgList", {"chnlno":chnlno}, function(r) {
        var code = r.code;
        console.info("process 1");
        if (code == 0){
            console.info("process 2");
            var data = r.msg;

             console.info("init ======="+data);
            for (var i = 0; i < data.length; i++) {
               console.info("value:"+data[i].datapkgno+"  value2:"+data[i].datapkgname);

                $("<option value='"+data[i].datapkgno+"'>"+data[i].datapkgname+"</option>")
                    .appendTo("#packpkgno");//添加打包拉框的option
              $("<option value='"+data[i].datapkgno+"'>"+data[i].datapkgname+"</option>")
                    .appendTo("#unpackpkgno");//添加解包下// 拉框的option
                $("<option value='"+data[i].datapkgno+"'>"+data[i].datapkgname+"</option>")
                    .appendTo("#errpkgno");//添加错误包下拉框的option
            }
        }
    });

}


function initPkgupdate() {
    $("#packpkgno").empty();
    $("#unpackpkgno").empty();
    $("#errpkgno").empty();
}


function validatorRule() {
    var icon="<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i>"
    validator=$pkgAddForm.validate({
        rules:{
            chnlno:{
                required:true,
                maxlength:3,
                number:true,
            }, ftrancode:{
                required:true,
                remote: {
                    url: "pkg/checkRepet",
                    type: "get",
                    dataType: "json",
                    data: {
                        chnlNo: function() {
                            return $("select[name='chnlno']").val();
                        },
                        fTranCode: function() {
                            return $("input[name='ftrancode']").val();
                        },
                        oldfTRanCode:function() {
                            return $("input[name='oldftrancode']").val();
                        }
                    }
                }
            },
            ftranname:{required:true},
            packpkgno:{
                maxlength:6,
                number:true,
                required:true
            },
            unpackpkgno:{
                maxlength:6,
                number:true,
                required:true
            },
            errpkgno:{
                maxlength:6,
                number:true,
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
            ftranname:icon+"请输入交易码描述",
            ftrancode: {
                required: icon + "请输入交易码",
                remote: icon + "该交易码已存在"
            },
            packpkgno:icon+"请输入打包格式号",
            unpackpkgno:icon+"请输入正确解包格式号",
            errpkgno:icon+"请输入错误包格式号",
            chnlno:icon+"请选择渠道号",
        }
    });
}