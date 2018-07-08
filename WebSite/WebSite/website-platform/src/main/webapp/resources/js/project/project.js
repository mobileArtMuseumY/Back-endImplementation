/*
*
*
*
* */

$(function () {

    $('#project_add_submit').click(function () {

        var projectAddUrl = "/project/add";

        var project = {};

        project.projectName = $('#project_name').val();

        project.budget = document.getElementById("budget").value;
        project.expectedTime = document.getElementById("expected_time").value;
        project.tenderPeriod = document.getElementById("tender_period").value;
        project.skillList = "[1]";
        project.projectDescription = document.getElementById("project_description").value;

        var project_attachment = $("#project_attachment")[0].files[0];

        var formData = new FormData();
        formData.append('projectAttachments', project_attachment);
        formData.append('projectStr', JSON.stringify(project));

        $.ajax({
            url: projectAddUrl,
            type: 'POST',
            // contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                var result;
                if (typeof data === 'string') {
                    result = JSON.parse(data);
                    if (result.status == 0) {
                        $.toast('发布成功！');
                        window.location.href="../view/projectaddsuccess";
                    }
                    else {
                        $.toast("发布失败了：" + result.msg);
                    }
                } else {
                    if (data.status == 0) {
                        $.toast('发布成功！');
                        window.location.href="../view/projectaddsuccess";
                    }
                    else {
                        $.toast("发布失败了：" + data.msg);
                    }
                }
            },
            error: function (retData) {
                $.toast("发布失败了：" + result.msg);
            }
        });

    })
})
