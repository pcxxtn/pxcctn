$(function() {
    getSpkDicts(); //获取渠道下的通讯结构字典
    getSpkgFld();   //获取报文结构已有的报文域*/
})
var upTableDatas;
//获取报文结构已有的报文域
function getSpkgFld() {
    var settings = {
        url: ctx + "transet/sysPkgFld/list",
        // pageSize: 50,
        pagination: false,
        queryParams: function(params) {
            return {
                // pageSize: params.limit,
                // pageNum: params.offset / params.limit + 1,
                // chnlno: chnlno,
                // datapkgno:datapkgno,
                // commstructno:commstructno
            };
        },
        columns: [{
            checkbox: true
        },{field: 'chnlno',
            visible: false
        },{field: 'datapkgno',
            visible: false,
            title: '格式号'
        },{field: 'datapkgfldno',
            title: '序号',
            visible: true
        },{field: 'commstructno',
            visible: false
        },{field: 'commstructfldno',
            title: '数据字典字段号'
        },{field: 'commstructfldname',
            title: '字段名称'
        },{field: 'commstructfldexpr',
            title: '字段描述'
        },{field: 'cname',
            visible: false
        },{field: 'ctime',
            visible: false
        },{field: 'mname',
            visible: false
        },{field: 'mtime',
            visible: false
        },{field: 'procfunc',
            title: '处理参数|循环次数',
        }
        ],
        //当选中行，拖拽时的哪行数据，并且可以获取这行数据的上一行数据和下一行数据
        onReorderRowsDrag: function (table, row) {
            return false;
        },
        //拖拽完成后的这条数据，并且可以获取这行数据的上一行数据和下一行数据
        onReorderRowsDrop: function (table, row) {
            return false;
        },
        //当拖拽结束后，整个表格的数据
        onReorderRow: function (newData) {
            // console.log(newData);
            updateTable(newData);

            return false;
        }
    }
    $MB.initTable('spkgConfigTable', settings);
}

//更新表序号
function updateTable(newData1) {
    // var tmpdata = newData1;
    var tmpdata = $("#spkgConfigTable").bootstrapTable('getData');
    // console.log(tmpdata);
    var newData = new Array();
    for(var i =0;i<tmpdata.length;i++){
        newData[i]=tmpdata[i];
    }
    $("#spkgConfigTable").bootstrapTable('removeAll');
    for(var i =0;i<newData.length;i++){
        newData[i].datapkgfldno = i+1;
        // console.log( newData[i]);
        $("#spkgConfigTable").bootstrapTable('append', newData[i]);//selected[i]----->新增的数据
    }
    // console.log($("#spkgConfigTable").bootstrapTable('getData'))
}

//获取渠道和通讯结构下的数据字典
function getSpkDicts() {
    var settings = {
        url: ctx + "transet/sysPkgFld/commFldList",
        // pageSize: 50,
        pagination: false,
        queryParams: function(params) {
            return {
                // pageSize: params.limit,
                // pageNum: params.offset / params.limit + 1,
                // chnlno: chnlno,
                // commstructno:commstructno
            };
        },
        columns: [{
            checkbox: true
        },{field: 'chnlno',
            visible: false
        },{field: 'commstructno',
            title: '数据字典号'
        },{field: 'commstructname',
            visible: false
        },{field: 'commstructtype',
            visible: false
        },{field: 'commstructfldno',
            title: '数据字典字段号'
        },{field: 'commstructfldname',
            title: '字段名称'
        },{field: 'commstructfldexpr',
            title: '字段描述'
        },{field: 'fldtype',
            title: '字段类型'
        },{field: 'structvartype',
            title: '结构变量类型'
        },{field: 'mixvartype',
            title: '混合变量类型'
        },{field: 'fldlen',
            title: '字段长度'
        },{field: 'alignmode',
            title: '对齐方式'
        },{field: 'fillsmbl',
            title: '填充字符'
        },{field: 'endsmblflag',
            title: '结束符标志'
        },{
            field: 'breaksmbl',
            title: '分隔符'
        }, {
            field: 'nlvarlen',
            visible: false
        }, {
            field: 'nlvarflag',
            visible: false
        }, {
            field: 'nlvarpower',
            visible: false
        }, {
            field: 'varno',
            visible: false
        }]
    }
    $MB.initTable('spkgDictsTable', settings);
}

//从下往上添加数据字典
$("#dicts-add").unbind('click').click(function (){
    var selected = $("#spkgDictsTable").bootstrapTable('getSelections');
    var spkgConfigTableData = $("#spkgConfigTable").bootstrapTable('getData');//获取表格的所有内容行

    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要添加的数据字典！');
        return;
    }
    console.log(selected);
    console.log(spkgConfigTableData);
    if(spkgConfigTableData.length>0){
        // console.log("ff");
        var ids="";
        //获取datapkgfldno的最大值
        var fldTempList =  new Array();//定义一个数组
        for (var j = 0; j < spkgConfigTableData.length; j++){
            fldTempList.push(spkgConfigTableData[j].datapkgfldno);
        }
        var datafldno = fldTempList[0];
        if(fldTempList.length>1){
            var compare = function (x, y) {//比较函数
                if (x < y) {
                    return -1;
                } else if (x > y) {
                    return 1;
                } else {
                    return 0;
                }
            }
            var newList = fldTempList.sort(compare);
            datafldno=newList[newList.length-1];
        }

        var tmp = 0;
        for (var i = 0; i < selected_length; i++) {
            var num =0;
            for (var j = 0; j < spkgConfigTableData.length; j++){
                var commstructfldno1= selected[i].commstructfldno;
                var commstructfldno2 = spkgConfigTableData[j].commstructfldno;
                var commstructno1= selected[i].commstructno;
                var commstructno2= spkgConfigTableData[j].commstructno;
                console.log(commstructfldno1+"---"+commstructfldno2)
                if(commstructfldno1 === commstructfldno2  &&  commstructno1 === commstructno2){
                    ids += commstructfldno1;
                    if (i != (selected_length - 1)) ids += ",";
                    num++;
                    tmp++;
                }
            }
            if(num===0){
                console.log("datano"+datafldno);
                selected[i].datapkgfldno=datafldno+i+1-tmp;
                selected[i].procfunc="000000|0";
                $("#spkgConfigTable").bootstrapTable('append', selected[i]);//selected[i]----->新增的数据
            }
        }
        if(ids!=""){
            $MB.n_warning('报文结构已存在数据字典：'+ids);
        }
    }else{
        for(var i = 0; i < selected_length; i++){
            selected[i].datapkgfldno=i+1;
            selected[i].procfunc="000000|0";
        }
        $("#spkgConfigTable").bootstrapTable('append', selected);//selected----->新增的数据
    }

});

//刷新报文域
function refresh(){
    $MB.refreshTable('spkgConfigTable');
}

//提交保存
$("#dicts-save").off().on('click', function(){
    var spkgConfigTableData = $("#spkgConfigTable").bootstrapTable('getData');//获取表格的所有内容行
    var savedata = spkgConfigTableData;
    var orderNosList = "";

    console.log(savedata);
    if(savedata.length>0){
        for(var i=0; i<savedata.length; i++){
            var jsonObj = {"chnlno": savedata[i].chnlno,
                "datapkgno": savedata[i].datapkgno,
                "datapkgfldno": savedata[i].datapkgfldno,
                "commstructno": savedata[i].commstructno,
                "commstructfldno": savedata[i].commstructfldno,
                "procfunc": savedata[i].procfunc};
            orderNosList += JSON.stringify(jsonObj);
            if (i != (savedata.length - 1)) orderNosList += "+";
        }
        $.post(ctx + "transet/sysPkgFld/add", {sysPkgFldList:orderNosList.toString()}, function(r) {
            if (r.code == 0) {
                $MB.n_success(r.msg);
                $MB.refreshTable("spkgConfigTable");
            } else $MB.n_danger(r.msg, '.modal');
        });
    }
});

//获取选中行的index索引号
function getIdSelections() {
    return $.map($("#spkgConfigTable").bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

//删除报文域
$("#delSelectedDicts").off().on('click', function(){
// $("#delSelectedDicts").unbind('click').click(function (){
    var selected = $("#spkgConfigTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    // console.log("selected_length:"+selected_length);
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除！');
        return;
    }
    var deldata = selected;
    var orderNosList = "";
    var ids = new Array();//定义一个数组

    // console.log(deldata+"del");
    for(var i=0; i<deldata.length; i++){
        var jsonObj = {"chnlno": deldata[i].chnlno,
            "datapkgno": deldata[i].datapkgno,
            "datapkgfldno": deldata[i].datapkgfldno,
            "commstructno": deldata[i].commstructno,
            "commstructfldno": deldata[i].commstructfldno
        };
        orderNosList += JSON.stringify(jsonObj);
        if (i != (deldata.length - 1)) orderNosList += "+";
    }
    $MB.confirm({
        text: "确定删除选中？",
        confirmButtonText: "确定删除"
    }, function() {
        if(orderNosList!=""){
            $.post(ctx + 'transet/sysPkgFld/delete', {deldata:orderNosList.toString()}, function(r) {
                if (r.code == 0) {
                    $MB.n_success(r.msg);
                     $MB.refreshTable('spkgConfigTable');
                     // updateTable();
                    // $("#spkgConfigTable").bootstrapTable('remove', {field: 'commstructfldno', values: ids2});//field:根据field的值来判断要删除的行  values：删除行所对应的值
                } else {
                    $MB.n_danger(r.msg);
                }
            });
            // if(ids.length!==0){
            //         $("#spkgConfigTable").bootstrapTable('remove', {field: 'commstructfldno', values: ids});//field:根据field的值来判断要删除的行  values：删除行所对应的值
            //     }
        }
    });
});

//返回上一页
function pageBack(){
    $.post(ctx + "transet/spkg/spkg_view", {}, function(r) {
        $main_content.html("").append(r);
    });
}
