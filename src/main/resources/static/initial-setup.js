$(function () {
    "use strict";

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

        initAJAXGrid: function (serviceUrl, gridConfig, pagerConfig) {
            var $grid = this;
            var GRID_SUFFIX = "_grid";
            var PAGER_SUFFIX = "_pager";
            var GRID_ID = serviceUrl + GRID_SUFFIX;
            var GRID_SELECTOR = "#" + GRID_ID;
            var PAGER_ID = serviceUrl + PAGER_SUFFIX;
            var PAGER_SELECTOR = "#" + PAGER_ID;

            var config = $.extend(true, {
                url: "/" + serviceUrl + "/entities",
                pager: PAGER_SELECTOR
            }, gridConfig);

            var onclickSubmit = function (params, postdata) {
                var id = postdata[GRID_ID + "_id"];
                params.url = "/" + serviceUrl;
                switch (params.mtype) {
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
                delete data.id;
                return JSON.stringify(data);
            };

            $grid = $grid.jqGrid(config).navGrid(
                PAGER_SELECTOR,
                pagerConfig,
                {
                    mtype: "PUT",
                    onclickSubmit: onclickSubmit,
                    serializeEditData: serializeEditData,
                    closeAfterEdit: true,
                    recreateForm: true
                },
                {
                    mtype: "POST",
                    onclickSubmit: onclickSubmit,
                    serializeEditData: serializeEditData,
                    closeAfterAdd: true,
                    recreateForm: true
                },
                {
                    mtype: "DELETE",
                    onclickSubmit: onclickSubmit
                }
            );

            $(window).bind('resize', function() {
                $grid.setGridWidth($(window).width());
            }).trigger('resize');

            return $grid;
        }
    });

    $.extend($.jgrid.defaults, {
        styleUI: 'Bootstrap4',
        iconSet: "Octicons",
        autowidth: true,
        responsive: true,
        altRows: true,
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
                select += "<option value='" + (item.id || "") + "'>" + (item.name || "") + "</option>";
            }
            return select + '</select>';
        }
    });

    /**
     * INIT GRID ON FIRST TAB
     */
    $('.nav-tabs a:first').tab('show');
});