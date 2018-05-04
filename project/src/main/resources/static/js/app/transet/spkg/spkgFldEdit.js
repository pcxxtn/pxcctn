$(function() {
$("#updateSpkFld").off().on('click', function(){
    var selected = $("#spkgConfigTable").bootstrapTable('getSelections');
    // console.log("---");
    // console.log(selected);
    // console.log("---");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的数据报文域！');
        return;
    }
    if (selected_length > 1) {
        $MB.confirm({
            text: "请选择一项进行修改；或先保存报文域,再点击左侧方框选中；或刷新报文域,再点击左侧方框选中。",
            confirmButtonText: "确定"
        }, function() {

        });
        // $MB.n_warning('请选择一项进行修改，或 1.先保存报文域，再点击左侧选项进行选中。2.刷新报文域，再点击左侧选项进行选中。');
        return;
    }
    var chnlno=selected[0].chnlno;
    var datapkgno=selected[0].datapkgno;
    var datapkgfldno=selected[0].datapkgfldno;
    var commstructfldno=selected[0].commstructfldno;
    var commstructno=selected[0].commstructno;
    var procfunc = selected[0].procfunc.split("|")[0];
    var circleSum = selected[0].procfunc.split("|")[1];

    var $form=$("#spkg-proc-func");
    $form.modal();
    $form.find("input[name='chnlno']").val(chnlno);
    $form.find("input[name='datapkgno']").val(datapkgno);
    $form.find("input[name='datapkgfldno']").val(datapkgfldno);
    $form.find("input[name='commstructno']").val(commstructno);
    $form.find("input[name='commstructfldno']").val(commstructfldno);
    $form.find("input[name='circleSum']").val(circleSum);
    initPkgNo(chnlno,procfunc);
});

function initPkgNo(chnlno,procfunc) {
    $("#procfunc").empty();
    if ($('#procfunc').is(':empty')){
        $("<option value=''> -- 请选择 --</option>")
            .appendTo("#procfunc");
    }
    $.post(ctx + "pkg/pkgList", {"chnlno":chnlno}, function(r) {
        var code = r.code;

        if (code == 0){
            var data = r.msg;
            for (var i = 0; i < data.length; i++) {
                $("<option value='"+data[i].datapkgno+"'>"+data[i].datapkgname+"</option>")
                    .appendTo("#procfunc");//添加
                if(data[i].datapkgno===procfunc){
                    var $form=$("#spkg-proc-func");
                    $form.find("select[name='procfunc']").val(data[i].datapkgno).attr("selected",true);
                }
            }
        }
    });
}
})