// ready function
$(document).ready(function() {

    // Event click button register insurance
    $(".btn_register").click(function() {
        location.href = 'register?searchFormId=' + getSearchFormId();
    });
    // Event click button export CSV file
    $(".btn_export").click(function() {
        location.href = 'exportCSV?searchFormId=' + getSearchFormId();
    });
    // Event change select box company in dashboard
    $("#searchByCompanyInternalId").change(function () {
        $("#searchByUserFullName").attr("value", "");
        $("#searchByInsuranceNumber").attr("value", "");
        $("#searchByPlaceOfRegister").attr("value", "");
    });
});
// function get search form id from url
var getSearchFormId = function(){
    var searchFormId = "";
    if ($("._action").attr("data-id")) {
        searchFormId = $("._action").attr("data-id");
    }
    return searchFormId;
};

