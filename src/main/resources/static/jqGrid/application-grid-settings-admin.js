$(function () {

    var SERVICE_URL = "application";
    var GRID_CAPTION = "Applications";
    var GRID_SUFFIX = "_grid";
    var EXAM_RESULT_GRID_SELECTOR = "#" + SERVICE_URL + GRID_SUFFIX;

    $(EXAM_RESULT_GRID_SELECTOR).initAJAXGrid(
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
                }
            ]
        },
        {
            add: false,
            edit: false,
            del: false,
            search: true
        }
    ).filterToolbar(

    );
});