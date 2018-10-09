// ready function
$(document).ready(function () {
    $("#userSexDivision1").attr("checked", "true");
    $(".date-time-picker").datepicker({
        showOn: "button",
        buttonImage: "img/calendar_icon.gif",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy",
    });
    showOrHideCompany("old");
    $('input[type=radio][name=company]').change(function () {
        showOrHideCompany(this.value);
    });
    $("#companyInternalId").change(function () {
        showInformationCompany($("#companyInternalId").val());
    });
});

var showOrHideCompany = function (value) {
    var companyInternalId = $("#companyInternalId").val();
    if (value == 'old') {
        $(".area-old-company").show();
        $(".area-new-company").hide();
        showInformationCompany(companyInternalId);
    } else if (value == 'new') {
        $(".area-old-company").hide();
        $(".area-new-company").show();
    }
}

var showInformationCompany = function (companyInternalId) {
    var url = "/loadCompany?companyInternalId=" + companyInternalId;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(companyInternalId),
        dataType: 'json',
        cache: false,
        timeout: 10000,
        success: function (data) {
            $(".companyName").html(data.companyName);
            $(".addressCompany").html(data.addressCompany);
            $(".emailCompany").html(data.emailCompany);
            $(".phoneCompany").html(data.phoneCompany);
        },
        error: function (e) {

        }
    });
}
