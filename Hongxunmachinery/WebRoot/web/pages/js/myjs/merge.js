/**合并页脚单元格
 * 
 */
var merge = function(datagrid, fields) {
    var rowcounts = datagrid.datagrid("getRows").length;//获得数据条数
    var a = 1;
    var b = 1;
    var arr = new Array();
    var field = fields[0];//根据第一个字段进行合并
    //遍历该字段的每行数据，判断重复情况
    for (var i = 0; i < rowcounts - 1; i++) {
        var x = datagrid.datagrid("getRows")[i][field];
        var y = datagrid.datagrid("getRows")[i + 1][field]
        if (x == y) {
            if (b == 1) {
                arr.push(i);
            }
            a++;
            b++;
            if (i == rowcounts - 2) {
                arr.push(a);
            }
        } else {
            if (a > 1) {
                arr.push(a);
            }
            a = 1;
            b = 1;
        }
    }   
    var arr2 = toArr(arr, rowcounts);
    if (arr.length != 0) {
        var options = datagrid.datagrid('getPager').data("pagination").options;
        var curr = options.pageNumber;//取得当前页码
        var pageSize=options.pageSize;
      //根据页码对序号赋值
        for (var i = 0; i < arr2.length; i++) {
            var page = (curr - 1) * pageSize + arr2[i];
            //根据field属性获得单元格内容并赋值
            $($("td[field=index]")[i + 1]).find('div').eq(0).html(page);
        }
    }
    //根据arr数组对需要合并的列进行合并
    for (var j = 0; j < fields.length; j++) {
        var field = fields[j];
        for (var i = 0; i < arr.length; i += 2) {
            datagrid.datagrid('mergeCells', {
                index: arr[i],
                field: field,
                rowspan: arr[i + 1]
            });
        }
    }
 
}
var toArr = function(arr, length) {
    var arr2 = new Array();
    var x = true;
    for (var j = 0; j < arr.length - 1; j += 2) {
        if (arr[j] == 0) {
            for (var k = 0; k < arr[j + 1]; k++) {
                arr2.push(1);
            }
        } else {
            if (!x) {
                for (var k = 0; k < arr[j] - arr[j - 2] - arr[j - 1]; k++) {
                    var l = arr2[arr2.length - 1];
                    l++;
                    arr2.push(l);
                }
            }
            if (x) {
                for (var k = 1; k < arr[j] + 1; k++) {
                    arr2.push(k);
                }
            }
            var xx = arr2[arr2.length - 1] + 1;
            for (var k = 0; k < arr[j + 1]; k++) {
                arr2.push(xx);
            }
        }
        x = false;
 
    }
    if (arr2.length < length) {
        var xx = arr2[arr2.length - 1] + 1;
        var size = arr2.length;
        for (var i = 0; i < length - size; i++) {
            arr2.push(xx++);
        }
    }
    return arr2;
}
