
/**
 * 分页工具初始化
 * @param url ajax提交地址
 * @param sequence 是否加入序号 true/false
 * @param checkbox 是否有勾选框 true/false
 * @param operate 操作列 true/false
 * @param info 操作列信息 ["add","edit","delete","detail"]
 */
function initTablePageTool(url,sequence,checkbox,operate,info) {
	var pageTool = jQuery(".tablePageTool");
	var head_tr =  jQuery('.tablePageTool thead').find("tr");
	var columns = [];
	if(sequence == true){
	    var seq_columns ={
            "sTitle" : "序号",
            "className" : "aCenter",
            "bSortable" : false,
            "sWidth" : "4%",
            "data" : null,
            "render":function(data,type,row,meta) {
                return meta.row + 1 + meta.settings._iDisplayStart;
            }
        };
        columns.push(seq_columns);

    }
    if(checkbox == true){
		var check_columns = {
            "sTitle" : '<span>全选</span><input id="checkAll" type="checkbox">',
            "className" : "aCenter",
            "bSortable" : false,
            "sWidth" : "4%",
            "data" : null,
            "targets" : 1,
			"render": function () {
			    return '<input type="checkbox" />';
            }
		}
        columns.push(check_columns);
    }

    //遍历thead下tr各个td的name
    head_tr.children().each(function(){
		var name = jQuery(this).attr("name");
		if(name!= undefined){
		    var column ={
		        "data":jQuery(this).attr("name")
		    };
            columns.push(column);
		}
    });
    if(operate==true && info!=null){
        var operation_column = {
            "sTitle" : '操作',
            "className" : "aCenter",
            "bSortable" : false,
            "data" : null,
            "render": function (data,type,row) {
                return getOperateColumn(info,row);
            }
        }
        columns.push(operation_column);
    }
    pageTool.dataTable({
        "sServerMethod": "POST", //数据获取方式 POST/GET，默认是GET
        "bDeferRender": false, //是否启用延迟加载：默认为false。
        "sScrollXInner": "100%", //表格宽度
        "bPaginate": true, //是否开启分页,默认是true
        "bLengthChange": false, //是否允许用户在下拉列表自定义 默认为true
        "aLengthMenu": [[10, 20, 50, -1], [10, 20, 50, "ALL"]], //选择每页显示多少条记录
        'iDisplayLength': 10, //每页初始显示10条记录
        "bFilter": false, //是否启用内置搜索功能：可以跨列搜索。  默认为true
        "bSort": false, //是否开启列排序功能
        "columnDefs": [{"defaultContent": "", "targets": "_all"}], //兼容空值
        "paging": true, //是否开启分页
        "serverSide": true,  //启用服务器端分页
        "bInfo": true, //是否显示表格相关信息：例如翻页信息 默认值：True
        "bAutoWidth": true, //是否启用自动适应列宽 默认值：True
        "sPaginationType": "full_numbers", //分页方式  默认是two_button
        "bProcessing": false, //是否显示加载时进度条，默认为false
        "oLanguage": {
            "sProcessing" : "<img src='/images/colorbox/loading.gif'/>",
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "抱歉， 没有找到",
            "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
            "sInfoEmpty": "没有数据",
            "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
			},
			"sZeroRecords": "没有检索到数据",
		},
        "fnInitComplete": function () {}, //表格初始化完成后的回调
        //表格绘制完成后的回调
        "fnDrawCallback": function () {},
        "sAjaxSource": url,// 后台请求地址
        "sAjaxDataProp": "data",
        "fnServerData": loadTableData,
        "columns":columns //定义列: 取相应属性字段
    });
}

/**
 * @param sSource 请求地址
 * @param aoData 分页参数 pageNo pageSize等
 * @param fnCallback 回调函数 重绘表格
 */
function loadTableData(sSource, aoData, fnCallback) {
    //封装请求参数
    var pageInfo = getParamMap(aoData);//解析页面参数
    var param = {};
    var pageStart = pageInfo.iDisplayStart;
    var pageLength = pageInfo.iDisplayLength;
    param.pageSize = pageLength;//每页显示多少条数据大小  
    param.pageNo = pageStart % pageLength == 0 ? pageStart / pageLength + 1 : pageStart/ pageLength; //页码    
    jQuery.ajax({ //ajax请求数据
        type: "post",
        url: sSource,
        cache: false, //禁用缓存
        data: param, //传入组装的参数
        dataType: "json",
        success: function (result) {
            //封装返回数据
            var returnData = {};
            //这里直接自行返回了draw计数器
            returnData.draw = pageInfo.draw;
            //返回数据全部记录
            returnData.recordsTotal = result.recordsTotal;
            //后台不实现过滤功能，每次查询均视作全部结果
            returnData.recordsFiltered = result.recordsFiltered;
            //返回的数据列表
            returnData.data = result.data;
            //调用DataTables提供的callback方法，
            fnCallback(returnData);
        }
    });
}
//获取页面参数信息
function getParamMap(aData) {
    var map = {};
    for (var i = 0; i < aData.length; i++) {
        var item = aData[i];
        if (!map[item.name]) {
            map[item.name] = item.value;
        }
    }
    return map;
}

/**
 * 设置操作列
 * @param info list形如["id","add","edit"];
 * @param row 行信息

 * @returns {*} 需要添加的操作列<a></a>
 */
function getOperateColumn(info,row) {
    var index = info[0];
    var id= row[index];
    var add_info = '<button class="btn_blue" style="background: #0f91f5;"  onclick="fnAdd('+"'"+id+"'"+');" >新增</button>&nbsp;&nbsp;';
    var edit_info = '<button class="btn_orange" style="background: #ff9702;" onclick="fnEdit('+"'"+id+"'"+');" >修改</button>&nbsp;&nbsp;';
    var delete_info = '<button class="btn_red" style="background: #eb2f30;" onclick="fnDelete('+"'"+id+"'"+');" >删除</button>&nbsp;&nbsp;';
    var detail_info = '<button class="btn_lime" style="background: #6adc0b;" onclick="fnDetail('+"'"+id+"'"+');" >详情</button>&nbsp;&nbsp;';
    if(info!=null && info != undefined){
        var column = '';
        for(var i = 0; i < info.length; i++){
            if (info[i]=='add'){ column = column + add_info} ;
            if (info[i]=='edit'){ column = column + edit_info} ;
            if (info[i]=='delete'){ column = column + delete_info} ;
            if (info[i]=='detail'){ column = column + detail_info} ;
        }

        return column;
    }
}
