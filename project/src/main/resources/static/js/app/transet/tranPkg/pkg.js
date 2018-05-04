$(function() {
    var settings = {
        url: ctx + "pkg/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                chnlno: $(".pkg-table-form").find("input[name='chnlno']").val().trim(),
                ftrancode: $(".pkg-table-form").find("input[name='ftrancode']").val().trim(),
                ftranname: $(".pkg-table-form").find("input[name='ftranname']").val().trim(),
                // status: $(".user-table-form").find("select[name='status']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'CHNLNO',
            title: '渠道号',
        }, {
            field: 'FTRANCODE',
            title: '外部交易码',
        }, {
            field: 'FTRANNAME',
            title: '外部交易名'
        }, {
            field: 'PACKPKGNO',
            title: '打包格式号'
        }, {
            field: 'UNPACKPKGNO',
            title: '解包格式号'
        }, {
            field: 'ERRPKGNO',
            title: '错误包格式号'
        }, {
            field: 'CNAME',
            title: '创建者'
        }, {
            field: 'CTIME',
            title: '创建时间',
            formatter: function(value, row, index) {
                return $MB.dateFormat(value, "yyyy-MM-dd hh:mm:ss");
            },
            visible:false
        }]
    };
    $MB.initTable('pkgTable', settings);

    // $("#pkgTable").on("click", ":button", function(event){
    //     var fdf= $("#text").val($(this).closest("tr").find("td").eq(0).text());
    //     console.log("fdf:"+fdf);
    // });
});



function search() {
    $MB.refreshTable('pkgTable');
}

function refresh() {
    $(".pkg-table-form")[0].reset();
    $MB.refreshTable('pkgTable');
}

function deletePkg() {
    var selected = $("#pkgTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的交易码！');
        return;
    }

    if (selected_length > 1) {
        $MB.n_warning('一次只能删除一个交易码！');
        return;
    }
    var chnlNo=selected[0].CHNLNO;
    var fTranCode = selected[0].FTRANCODE;

    $MB.confirm({
        text: "确定删除选中的交易码？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'pkg/delete', { chnlNo:chnlNo,fTranCode:fTranCode }, function(r) {
            if (r.code == 0) {
                $MB.n_success(r.msg);
                $MB.refreshTable('pkgTable');
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function updatePkg() {
    var selected = $("#pkgTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的交易码！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个交易码！');
        return;
    }
    var chnlNo=selected[0].CHNLNO;
    var fTranCode = selected[0].FTRANCODE;

    initPkgupdate();
    $.post(ctx+'pkg/findByPrimary',{chnlNo:chnlNo,fTranCode:fTranCode},function (data) {
        console.log(data);
        if(data.code==0){
            var $form=$("#pkg-add");
            $form.modal();
            var pkg=data.msg;
            $.post(ctx + "pkg/pkgList", {"chnlno":chnlNo}, function(r) {
                var code = r.code;

                console.info("process 1");
                if (code == 0){
                    console.info("process 2");
                    var data = r.msg;
                    //  console.info(data);
                    for (var i = 0; i < data.length; i++) {
                        console.info("value:"+data[i].datapkgno+"  value2:"+data[i].datapkgname);

                        $("<option value='"+data[i].datapkgno+"'>"+data[i].datapkgno+'-'+data[i].datapkgname+"</option>")
                            .appendTo("#packpkgno");//添加打包拉框的option
                        $("<option value='"+data[i].datapkgno+"'>"+data[i].datapkgno+'-'+data[i].datapkgname+"</option>")
                            .appendTo("#unpackpkgno");//添加解包下// 拉框的option
                        $("<option value='"+data[i].datapkgno+"'>"+data[i].datapkgno+'-'+data[i].datapkgname+"</option>")
                            .appendTo("#errpkgno");//添加错误包下拉框的option
                    }


                    $("#pkg-add-modal-title").html("修改外部交易码");
                    $form.find("select[name='chnlno']").val(pkg.chnlno).attr("readonly",true);
                    $form.find("input[name='ftrancode']").val(pkg.ftrancode).attr("readonly",true);
                    $form.find("input[name='oldftrancode']").val(pkg.ftrancode);
                    $form.find("input[name='ftranname']").val(pkg.ftranname).attr("readonly",true);

                    $form.find("select[name='packpkgno']").val(pkg.packpkgno);
                    $form.find("select[name='unpackpkgno']").val(pkg.unpackpkgno);
                    $form.find("select[name='errpkgno']").val(pkg.errpkgno);
                    $form.find("select[name='pkgtype']").val(pkg.pkgtype);

                    $form.find("input[name='busitype']").val(pkg.busitype);
                    $form.find("input[name='trancode']").val(pkg.trancode);
                    $form.find("input[name='ttranname']").val(pkg.ttranname);
                }
            });




            // $("#pkg-add-modal-title").html("修改外部交易码");
            // $form.find("select[name='chnlno']").val(pkg.chnlno).attr("readonly",true);
            // $form.find("input[name='ftrancode']").val(pkg.ftrancode).attr("readonly",true);
            // $form.find("input[name='ftranname']").val(pkg.ftranname).attr("readonly",true);
            // console.log("11  "+pkg.packpkgno);
            // console.log("22  "+pkg.unpackpkgno);
            // console.log("33  "+pkg.errpkgno);
            //
            // $form.find("select[name='packpkgno']").val(pkg.packpkgno);
            // $form.find("select[name='unpackpkgno']").val(pkg.unpackpkgno);
            // $form.find("select[name='errpkgno']").val(pkg.errpkgno);
            // $form.find("input[name='ownlever']").val(pkg.ownlever);
 /*           var $ownflag = $form.find("input[name='ownflag']");

         //  initPkg(pkg.chnlno);//加载包格式号
            if (pkg.ownflag == '1') {
                $ownflag.attr("checked", true);
                $ownflag.parent().next().html('可修改');
            } else {
                $ownflag.attr("checked", false);
                $ownflag.parent().next().html('锁定');
            }*/

            $("#pkg-add-btn").attr("name", "update");
        }else $MB.n_danger(data.msg);
    });
}


//不在另外的表中设置映射关系了,此段代码无用
function setMap() {
    var selected = $("#pkgTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要映射的交易码！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能映射一个交易码！');
        return;
    }
    var chnlNo=selected[0].CHNLNO;
    var fTranCode = selected[0].FTRANCODE;

    $.post(ctx+'pkg/findConvByPrimary',{chnlNo:chnlNo,fTranCode:fTranCode},function (data) {
        console.log(data);
        if(data.code==0){
            var $form=$("#conv-add");
            $form.modal();
            var tmp=data.msg;
            // $("#conv-add-modal-title").html("交易码映射");
            $form.find("input[name='chnlno']").val(tmp.chnlno).attr("readonly",true);
            $form.find("input[name='ftrancode']").val(tmp.ftrancode).attr("readonly",true);
            $form.find("select[name='busitype']").val(tmp.busitype);
            $form.find("input[name='trancode']").val(tmp.trancode);
            $form.find("input[name='ttranname']").val(tmp.ttranname);

            // $("#conv-add-btn").attr("name", "update");
        }else $MB.n_danger(data.msg);
    });
}