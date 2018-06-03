$(function () {
    $.ajaxSetup({
        contentType: "application/json",
        dataType: "json"
    });

    $.fn.extend({
        ajaxForm: function (callback, scope) {
            var $form = this;
            $.ajax({
                url: $form.action,
                type: $form.method,
                contentType: "application/json",
                data: $form.serialize(),
                success: function (data, textStatus, jqXHR) {
                    callback.call(scope || this, data);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });
            $form.preventDefault();
        },

        initAJAXGrid: function(serviceUrl, gridConfig, pagerConfig) {
            var $grid = this;
            var GRID_SUFFIX = "_grid";
            var PAGER_SUFFIX = "_pager";

            var SERVICE_URL = serviceUrl;
            var GRID_SELECTOR = "#" + SERVICE_URL + GRID_SUFFIX;
            var PAGER_SELECTOR = "#" + SERVICE_URL + PAGER_SUFFIX;

            var config = $.extend(true, {
                url: "/" + SERVICE_URL + "/entities",
                pager: PAGER_SELECTOR
            }, gridConfig);

            var onclickSubmit = function (params, postdata) {
                var id = postdata[GRID_SELECTOR + "_id"];
                params.url = "/" + SERVICE_URL;
                switch(params.mtype) {
                    case "POST":
                        params.url += "/create/";
                        break;
                    case "PUT":
                        params.url += "/update/" + id;
                        break;
                    case "DELETE":
                        params.url += "/delete/" + id;
                        break;
                }
            };

            var serializeEditData = function (data) {
                delete data.oper;
                delete data.id;
                return JSON.stringify(data);
            };

            $grid.jqGrid(config).navGrid(
                PAGER_SELECTOR,
                pagerConfig,
                {
                    mtype: "PUT",
                    onclickSubmit: onclickSubmit,
                    serializeEditData: serializeEditData
                },
                {
                    mtype: "POST",
                    onclickSubmit: onclickSubmit,
                    serializeEditData: serializeEditData
                },
                {
                    mtype: "DELETE",
                    onclickSubmit: onclickSubmit
                }
            )
        }
    });

    $.extend($.jgrid.defaults, {
        styleUI: 'Bootstrap4',
        iconSet: "Octicons",
        forceFit: true,
        datatype: 'json',
        gridview: true,
        rownumbers: true,
        viewrecords: true,
        serializeGridData: function (data) {
            data.page--;
            return data;
        },
        jsonReader: {
            root: "content",
            repeatitems: false,
            records: "totalElements",
            page: function (data) {
                data.number++;
                return data;
            },
            total: "totalPages"
        }
    });

    $.jgrid.extend({
        buildSelect: function (data) {
            data = $.parseJSON(data);
            var select = '<select>', i, l = data.length, item;
            for (i = 0; i < l; i++) {
                item = data[i];
                select += "<option value='" + item.id + "'>" + item.name + "</option>";
            }
            return select + '</select>';
        }
    });
});