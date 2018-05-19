/*$.extend($.jgrid.edit, {
    ajaxEditOptions: {contentType: "application/json"},
    mtype: 'PUT',
    serializeEditData: function (data) {
        delete data.oper;
        return JSON.stringify(data);
    }
});
$.extend($.jgrid.del, {
    mtype: 'DELETE',
    serializeDelData: function () {
        return "";
    }
});*/


var URL = '/enrollee/entities';
var options = {
    edit: true,
    add: true,
    del: false,
    search: false
};

var editOptions = {
    mtype: 'PUT',
    contentType: "application/json",
    datatype: 'json',
    onclickSubmit: function (params, postdata) {
        params.url = /enrollee/ + postdata.id;
    }/*,
     serializeEditData: function(data) {
     delete data.oper;
     return JSON.stringify(data);
     }*/
};
var addOptions = {mtype: "POST"};
var delOptions = {
    mtype: 'DELETE',
    onclickSubmit: function (params, postdata) {
        params.url = /enrollee/ + postdata.id;
    }
};

$("#grid")
    .jqGrid({
        url: URL,
        mType: 'GET',
        datatype: 'json',
        ajaxGridOptions: {contentType: 'application/json; charset=utf-8'},
        styleUI: 'Bootstrap',
        colModel: [
            {
                name: 'id', label: 'ID',
                width: 40,
                editable: true,
                editoptions: {disabled: true, size: 5}
            },
            {
                name: 'lastname',
                label: 'Lastname',
                width: 300,
                editable: true,
                editrules: {required: true}
            },
            {
                name: 'name',
                label: 'Name',
                width: 200},
            {
                name: 'surname',
                label: 'Surname',
                width: 80
            }
        ],
        caption: "Enrollee",
        pager: '#pager',
        viewrecords: true,
        rowNum: 20,
        height: '250',
        serializeGridData: function (postData) {
            if (typeof(postData.page) === "number") {
                postData.page--;
            }
            return postData;
        },
        jsonReader: {
            root: "content",
            repeatitems: false,
            records: "totalElements",
            page: "number",
            total: "totalPages"
        }
    })
    .navGrid('#pager', options, editOptions, addOptions, delOptions);