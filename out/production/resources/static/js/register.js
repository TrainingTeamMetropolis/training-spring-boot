// ready function
$(document).ready(function () {
    $(".date-time-picker").datepicker({
        showOn: "button",
        buttonImage: "img/calendar.png",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy",
    });
    var radioCompany = "old";
    if ($("input[name=radioCompanyHidden]").val()) {
        radioCompany = $("input[name=radioCompanyHidden]").val();
    }
    if (radioCompany == "old") {
        $("#radio-company-old").attr("checked", "true");
        $("#radio-company-new").removeAttr('checked');
    } else {
        $("#radio-company-old").removeAttr("checked");
        $("#radio-company-new").attr("checked", "true");
    }
    var userSexDivision = "1";
    if ($("input[name=radioUserSexDivisionHidden]").val()) {
        userSexDivision = $("input[name=radioUserSexDivisionHidden]").val();
    }
    if (userSexDivision == '1') {
        $("#radioUserSexDivision1").attr("checked", "true");
        $("#radioUserSexDivision2").removeAttr("checked");
    } else {
        $("#radioUserSexDivision1").removeAttr("checked");
        $("#radioUserSexDivision2").attr("checked", "true");
    }
    showOrHideCompany(radioCompany);
    $('input[type=radio][name=radioCompany]').change(function () {
        showOrHideCompany(this.value);
    });
    $("#companyInternalId").change(function () {
        showInformationCompany($("#companyInternalId").val());
    });
    $("._cancel").click(function () {
        var searchFormId = $("input[name=searchFormId]").val();
        var url = "dashboard";
        if (searchFormId) {
            url = "dashboard?searchFormId=" + searchFormId;
        }
        location.href = url;
    });
});

var showOrHideCompany = function (value) {
    var companyInternalId = $("#companyInternalId").val();
    if (value == 'old') {
        $(".area-old-company").show();
        $(".area-new-company").hide();
        $(".ui-datepicker-trigger").css("margin-right", "50px");
        showInformationCompany(companyInternalId);
    } else if (value == 'new') {
        $(".area-old-company").hide();
        $(".area-new-company").show();
        $(".ui-datepicker-trigger").css("margin-right", "65px");
    }
}

var showInformationCompany = function (companyInternalId) {
    var url = "/loadCompany?companyInternalId=" + companyInternalId;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        dataType: 'json',
        cache: false,
        timeout: 10000,
        success: function (data) {
            $(".companyName").html(data.companyName);
            $(".addressCompany").html(data.addressCompany);
            $(".emailCompany").html(data.emailCompany);
            $(".phoneCompany").html(data.phoneCompany);
        },
        error: function (e) {}
    });
}
