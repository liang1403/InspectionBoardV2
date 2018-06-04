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
                    width: 200,
                    editable: true,
                    editrules: {required: true},
                    edittype: 'select',
                    editoptions: {
                        dataUrl: "/speciality/list",
                        buildSelect: $.fn.jqGrid.buildSelect
                    }
                },
                {
                    name: 'faculty',
                    label: 'Faculty',
                    jsonmap: 'speciality.faculty.name',
                    width: 200
                }
            ]
        },
        {
            add: true,
            edit: false,
            del: false,
            search: false
        }
    );
});