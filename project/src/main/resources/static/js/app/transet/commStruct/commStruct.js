var structdata=[];
$(function () {
    //get the first page of commStruct
    initStruct();
});

function initStruct(){
    $.post(ctx+"commStruct/list",{},function (t) {
        console.log(t);
        if(t.total>0){
            structdata=t.rows;
            var comstrHtml="";
            for(var i=0;i<structdata.length;i++){
                // comstrHtml+='<div class="comm-xl-3 col-lg-3 col-sm-4 col-6">'; style="background-image: url(test.jpeg)"
                comstrHtml+='<div class="col-lg-2">';
                comstrHtml+='<div class="groups__item">';
                // comstrHtml+='<li class="comm-img aspectration"></li>';
                comstrHtml+='<div class="groups__info">';
                comstrHtml+='<p>渠道号:<strong>'+structdata[i].chnlno+'</strong></p>';
                comstrHtml+='<strong>'+structdata[i].commstructno+'-'+structdata[i].commstructname+'</strong>';
                comstrHtml+='<div class="dropdown-menu dropdown-menu-right movie-action" style="min-width:10px;background-color:rgba(255,255,255,.9);z-index:1">';
                comstrHtml+='<a class="dropdown-item" href="javascript:void(0)" onclick="viewCommStruct(\''+structdata[i].chnlno+'\',\''+structdata[i].commstructno+'\')">数据字典</a>';
                comstrHtml+='<a class="dropdown-item" href="javascript:void(0)" onclick="updateCommStruct('+i+')">属性修改</a>';
                comstrHtml+='<a class="dropdown-item" href="javascript:void(0)" onclick="deleteCommStruct(\''+structdata[i].chnlno+'\',\''+structdata[i].commstructno+'\')">删除结构</a>';
                comstrHtml+='</div></div></div></div>';
            }
            $("#commlist-div").append(comstrHtml);
            $(".groups__item").each(function(){
                var $this = $(this);
                $this.mouseenter(function(){
                    $this.find(".movie-action").show();
                });
                $this.mouseleave(function(){
                    $this.find(".movie-action").hide();
                });
            });
        }else $MB.n_danger("暂无通讯结构")
    });
}
function addCommStruct() {
    //添加通讯结构
    $("#commStruct-add").modal();
    $("#commStruct-add-modal-title").html("新增通讯结构");
}

function updateCommStruct(index) {
    console.log("看看到底打的哪个?");
    console.log(structdata[index].chnlno);
    var $form=$("#commStruct-add");
    $form.modal();
    $("#commStruct-add-modal-title").html("修改通讯结构");
    $form.find("select[name='chnlno']").val(structdata[index].chnlno).attr("disabled",true);
    $form.find("input[name='commstructno']").val(structdata[index].commstructno).attr("readonly",true);
    $form.find("input[name='oldcommstructno']").val(structdata[index].commstructno);
    $form.find("input[name='commstructname']").val(structdata[index].commstructname).attr("readonly",true);
    $form.find("input[name='oldcommstructname']").val(structdata[index].commstructname);
    $form.find("select[name='commstructtype']").val(structdata[index].commstructtype);
    $form.find("select[name='version']").val(structdata[index].version);
    $form.find("select[name='encoding']").val(structdata[index].encoding);
    $form.find("input[name='ifspace']").attr("checked",structdata[index].ifspace=='1'?true:false);

    $("#commStruct-add-btn").attr("name","update");
}

function deleteCommStruct(chnlno,commstructno) {
    console.log(chnlno);
    console.log(commstructno);
    $.post(ctx+"commStruct/delete",{chnlno:chnlno,commstructno:commstructno},function (data) {
        console.log(data);
        if(data.code==0){
            $MB.n_success(data.msg);
            //移除所有div中class为col-lg-2的元素
            $("div").remove(".col-lg-2");
            //再次加载通讯结构
            initStruct();
        }else $MB.n_danger(data.msg);
    });
}

function viewCommStruct(chnlno,commstructno) {
    $.post(ctx + "commFld_view", {}, function(r) {
        // window.location.assign(ctx+"commFld_view");
        $main_content.html("").append('<div id="paramFormStruct" hidden>'+chnlno+','+commstructno+'</div>');
        $main_content.append(r);
        $main_content.find("#chnlnoType").val(chnlno);
    });
}