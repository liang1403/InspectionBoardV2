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

function processForm( e ){
    $.ajax({
        url: e.action,
        type: e.method,
        contentType: "application/json",
        data: $(this).serialize()
    });

    e.preventDefault();
}

$('form').submit( function() {
    $.ajax({
        url: e.action,
        type: e.method,
        contentType: "application/json",
        data: $(this).serialize()
    });

    e.preventDefault();
} );

$("#grid")
    .jqGrid({
        url: '/enrollee/entities',
        caption: "Enrollee",
        pager: '#pager',
        height: '250',
        viewrecords: true,
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
                name: 'state',
                label: 'Surname',
                width: 80,
                editable: true,
                edittype:"select",
                editoptions:{value:"FE:FedEx;IN:InTime;TN:TNT;AR:ARAMEX"}
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
                params.url = '/enrollee/update/' + postdata.grid_id;
            },
            serializeEditData: function (data) {
                delete data.oper;
                return JSON.stringify(data);
            }
        },
        {
            mtype: "POST",
            onclickSubmit: function (params, postdata) {
                params.url = '/enrollee/create';
            },
            serializeEditData: function (data) {
                delete data.oper;
                return JSON.stringify(data);
            }
        },
        {
            mtype: 'DELETE',
            onclickSubmit: function (params, postdata) {
                params.url = '/enrollee/delete/' + postdata.grid_id;
            }
        });