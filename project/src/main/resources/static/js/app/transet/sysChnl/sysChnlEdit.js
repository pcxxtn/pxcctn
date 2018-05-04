function updatechnl() {
    var selected = $("#syschnlTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的渠道！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个渠道！');
        return;
    }
    var chnlno = selected[0].CHNLNO;

    console.info("渠道号："+chnlno);
    $.post(ctx + "sysChnl/get", { "chnlno": chnlno}, function(r) {
        if (r.code == 0) {
            var $form = $('#chnl-add');
            $form.modal();
            var chnl = r.msg;
            $("#chnl-add-modal-title").html('修改渠道');
            $form.find("input[name='chnlno']").val(chnl.chnlno).attr("readonly",true);
            $form.find("input[name='chnlnoold']").val(chnl.chnlno)
            $form.find("input[name='chnlname']").val(chnl.chnlname);
            $form.find("select[name='chnlstatus']").val(chnl.chnlstatus);
            $form.find("input[name='commpcol']").val(chnl.commpcol);
            $form.find("select[name='commmode']").val(chnl.commmode);
            $form.find("input[name='ipaddress']").val(chnl.ipaddress);
            $form.find("input[name='recvport']").val(chnl.recvport);
            $form.find("input[name='sendport']").val(chnl.sendport);

            $form.find("input[name='recvlen']").val(chnl.recvlen);
            $form.find("input[name='gatewayproc']").val(chnl.gatewayproc);
            $form.find("input[name='trancodelen']").val(chnl.trancodelen);
            $form.find("input[name='trancodeoffset']").val(chnl.trancodeoffset);
            $form.find("input[name='tracecodelen']").val(chnl.tracecodelen);
            $form.find("input[name='tracecodeoffset']").val(chnl.tracecodeoffset);
            $form.find("input[name='tracecodeclrflag']").val(chnl.tracecodeclrflag);
            $form.find("input[name='retcodeoffset']").val(chnl.retcodeoffset);
            $form.find("input[name='retcodelen']").val(chnl.retcodelen);
            $form.find("input[name='intercept']").val(chnl.intercept);

            $("#chnl-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}