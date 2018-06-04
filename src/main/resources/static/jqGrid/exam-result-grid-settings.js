$(function () {

    var SERVICE_URL = "exam_result";
    var GRID_CAPTION = "Exam Results";
    var GRID_SUFFIX = "_grid";
    var EXAM_RESULT_GRID_SELECTOR = "#" + SERVICE_URL + GRID_SUFFIX;

    $(EXAM_RESULT_GRID_SELECTOR).initAJAXGrid(
        SERVICE_URL,
        {
            caption: GRID_CAPTION,
            height: "350",
            colModel: [
                {
                    name: 'subject',
                    label: 'Subject',
                    jsonmap: 'subject.name',
                    width: 200,
                    editable: true,
                    editrules: {required: true},
                    edittype: 'select',
                    editoptions: {
                        dataUrl: "/subject/list",
                        buildSelect: $.fn.jqGrid.buildSelect
                    }
                },
                {
                    name: 'mark',
                    label: 'Mark',
                    width: 200,
                    editable: true,
                    editrules: {required: true}
                },
                {
                    name: 'state',
                    label: 'State',
                    jsonmap: 'state.name',
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