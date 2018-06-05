$(function () {

    var TAB_ID = "#application_tab";
    var SERVICE_URL = "application";
    var GRID_CAPTION = "Applications";
    var GRID_SUFFIX = "_grid";
    var GRID_SELECTOR = "#" + SERVICE_URL + GRID_SUFFIX;

    var initilialized = false;
    $(".nav-tabs a").on("shown.bs.tab", function (event) {
        if (event.target.hash === TAB_ID && !initilialized) {
            $(GRID_SELECTOR).initAJAXGrid(
                SERVICE_URL,
                {
                    caption: GRID_CAPTION,
                    height: "350",
                    colModel: [
                        {
                            name: 'speciality',
                            label: 'Speciality',
                            jsonmap: 'speciality.name',
                            stype: 'select',
                            searchoptions: {
                                dataUrl: "/speciality/list",
                                buildSelect: $.fn.jqGrid.buildSelect
                            },
                            width: 200
                        },
                        {
                            name: 'enrollee',
                            label: 'Enrollee',
                            jsonmap: 'enrollee.name',
                            width: 200,
                            search: false
                        },
                        {
                            name: 'faculty',
                            label: 'Faculty',
                            jsonmap: 'speciality.faculty.name',
                            width: 200,
                            search: false
                        },
                        {
                            name: 'point',
                            label: 'Point',
                            width: 200,
                            formatter: 'number',
                            formatoptions: {decimalPlaces: 2},
                            search: false
                        }
                    ]
                },
                {
                    add: false,
                    edit: false,
                    del: false,
                    search: true
                }
            ).filterToolbar();
            initilialized = true;
        }
    });
});