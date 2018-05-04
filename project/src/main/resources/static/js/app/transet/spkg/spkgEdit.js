function updateSpkg() {
    var selected = $("#spkgTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的报文格式！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个报文格式！');
        return;
    }
    var chnlno = selected[0].chnlno;
    var datapkgno = selected[0].datapkgno;
    console.info("报文格式号："+datapkgno)
    $.post(ctx + "transet/spkg/getSPkg", { "datapkgno": datapkgno,"chnlno":chnlno }, function(r) {
        if (r.code == 0) {
            var $form = $('#spkg-add');
            $form.modal();
            var spkg = r.msg;
            // $form.find(".packDef_password").hide();
            $("#spkg-add-modal-title").html('修改报文格式');
            $form.find("select[name='chnlno']").val(spkg.chnlno).attr("selected",true);
            $form.find("select[name='chnlno']").val(spkg.chnlno).attr("readonly",true);
            $form.find("input[name='datapkgno']").val(spkg.datapkgno);
            $form.find("input[name='datapkgname']").val(spkg.datapkgname);
            $form.find("select[name='datapkgtype']").val(spkg.datapkgtype).attr("selected",true);
            $form.find("input[name='preproc']").val(spkg.preproc);
            $form.find("input[name='endproc']").val(spkg.endproc);
            $form.find("select[name='macmode']").val(spkg.macmode).attr("selected",true);
            $form.find("select[name='pinmode']").val(spkg.pinmode).attr("selected",true);
            $form.find("input[name='credtype']").val(spkg.credtype);
            $form.find("input[name='icredflag']").val(spkg.icredflag).attr("checked",true);
            $form.find("input[name='pkgproccomp']").val(spkg.pkgproccomp);

            $.post(ctx + "transet/spkg/dictList", { "chnlno":chnlno }, function(r) {
                if (r.code == 0) {
                    $("#commstructno").empty();
                    var data = r.msg;
                    for (var i = 0; i < data.length; i++) {
                        $("<option value='" + data[i].commstructno + "'>" +data[i].commstructno +"-"+ data[i].commstructname + "</option>")
                            .appendTo("#commstructno")//添加下拉框的option
                    }
                }else{
                    $MB.n_danger(r.msg);
                }
            });

            $form.find("select[name='commstructno']").val(spkg.commstructno).attr("selected",true);
            $("#spkg-add-button").attr("name", "update");

        } else {
            $MB.n_danger(r.msg);
        }
    });
}