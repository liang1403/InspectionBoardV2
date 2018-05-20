$.extend($.jgrid.defaults, {
    styleUI: 'Bootstrap',
    forceFit: true,
    datatype: 'json',
    gridview: true,
    rowList: [5, 10, 20],
    rownumbers: true,
    serializeGridData: function (data) {
        data.page--;
        return data;
    },
    jsonReader: {
        root: "content",
        repeatitems: false,
        records: "totalElements",
        page: function(data) {
            data.number++;
            return data;
        },
        total: "totalPages"
    }
});

$.extend($.jgrid.ajaxOptions, {
    contentType: "application/json"
});


$("#grid")
    .jqGrid({
        url: '/enrollee/entities',
        caption: "Enrollee",
        pager: '#pager',
        height: '250',
        colModel: [
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
                width: 200
            },
            {
                name: 'surname',
                label: 'Surname',
                width: 80
            }
        ]
    })
    .navGrid('#pager',
        {
            edit: true,
            add: true,
            del: false,
            search: true
        },
        {
            mtype: 'PUT',
            onclickSubmit: function (params, postdata) {
                params.url = /enrollee/ + postdata.grid_id;
            },
            serializeEditData: function (data) {
                delete data.oper;
                return JSON.stringify(data);
            }
        },
        {
            mtype: "POST"
        },
        {
            mtype: 'DELETE',
            onclickSubmit: function (params, postdata) {
                params.url = /enrollee/ + postdata.grid_id;
            }
        });