var validator;
var $commAddForm=$("#commFld-add-form");
var $chnlnoSelect=$("#chnlno");
var $commstructnoSelect=$("#commstructno");
var chnlcom=new Array();

$(function () {
    chnlcom=[];
    initChnlnoAndCommstructNo();
    validatorInit();

    $("#commFld-add .btn-save").click(function () {
        validator=$commAddForm.validate();
        var flag=validator.form();

        var dictmac=$commAddForm.find("input[name='macflag']").prop("checked");
        var dictpin=$commAddForm.find("input[name='pinflag']").prop("checked");
        if(dictmac)$commAddForm.find("input[name='macflag']").val(1);
        if(dictpin)$commAddForm.find("input[name='pinflag']").val(1);

        if(flag){
            var name=$(this).attr("name");
            console.log($commAddForm.serialize());
            if(name=='save'){
                $.post(ctx+"commFld/add",$commAddForm.serialize(),function (data) {
                    console.log(data);
                    if(data.code==0){
                        closeModal();
                        $MB.n_success(data.msg);
                        $MB.refreshTable("commFldTable");
                    }else $MB.n_danger(data.msg);
                });
            }else{
                $.post(ctx+"commFld/update",$commAddForm.serialize(),function (data) {
                    console.log(data);
                    if(data.code==0){
                        closeModal();
                        $MB.n_success(data.msg);
                        $MB.refreshTable("commFldTable");
                    }else $MB.n_danger(data.msg);
                });
            }
        }
    });
    
    $("#commFld-add .btn-close").click(function(){
        closeModal();
    });
});

function closeModal(){
    $("#commFld-add-modal-title").html("新增结构字典");
    $("#commFld-add-btn").attr("name","save");
    $MB.closeAndRestModal("commFld-add");
    validator.resetForm();
    $commAddForm.find("input[name='commstructfldname']").removeAttr("readonly");
    $commAddForm.find("select[name='chnlno']").removeAttr("disabled");
    $commAddForm.find("select[name='commstructno']").removeAttr("disabled");

}

function initChnlnoAndCommstructNo() {
    // 渠道号 通讯结构号
    $.post(ctx+"commStruct/list",{},function (r) {
        console.log(r);
        var data=r.rows;
        var option = "";
        var f=[];
        for (var i = 0; i < data.length; i++) {
            f=chnlcom.find(function (value) {
                return value.chnlno==data[i].chnlno;
            });
            if(f==null){
                option += "<option value='" + data[i].chnlno + "'>" + data[i].chnlno+"-"+data[i].chnlno + "</option>";
            }
            chnlcom.push({
                chnlno: data[i].chnlno,
                commstructno:data[i].commstructno,
                commstructname:data[i].commstructname
            });
        }
        $chnlnoSelect.html("").append(option);

        //取第一个渠道号对应的通讯结构号
        var commOption=chnlcom.filter(function (value) {
            return value.chnlno==chnlcom[0].chnlno;
        });
        var commo="";
        for(var k=0;k<commOption.length;k++){
            console.log(commOption[k]);
            commo+="<option value='"+commOption[k].commstructno+"'>"+commOption[k].commstructno+"-"+commOption[k].commstructname+"</option>"
        }
        $commstructnoSelect.html("").append(commo);
    });
}

function validatorInit() {
    var icon="<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i>"
    validator=$commAddForm.validate({
        rules:{
            commstructfldname:{
                required:true,
                remote: {
                    url: "commFld/checkCommName",
                    type: "get",
                    dataType: "json",
                    data: {
                        chnlno:function() {
                            return $("select[name='chnlno']").val();

                        },
                        commstructno:function () {
                            return $("select[name='commstructno']").val();
                        },
                        commFldName: function() {
                            return $("input[name='commstructfldname']").val();

                        },
                        oldCommFldName:function () {
                            return $("input[name='oldcommstructfldname']").val();
                        }
                    }
                }
            },
            chnlno:{required:true},
            commstructno:{required:true},
            fldlen:{required:true,digits:true}
        },
        errorPlacement:function(error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages:{
            commstructfldname:{
                required:icon+"请输入数据字典名称",
                remote:icon+"该数据字典已存在"
            },
            chnlno:icon+"请选择渠道",
            commstructno:icon+"请选择通讯结构",
            fldlen:icon+"请输入字典长度(数字)"
        }
    });
}

function changeChnlno() {
    console.log($("#chnlno").val());
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