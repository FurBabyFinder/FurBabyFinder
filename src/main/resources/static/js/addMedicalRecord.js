
$(document).ready(function(){


    // ===============VERIFY DATE FIELD ID A DATE==============================
    function isValidDate(txtDate) {
        var dateString = $(txtDate).val();
        var warningId = txtDate + "Warning";
        var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
        var txtDatePresent = (function () {if (!($(txtDate).val()=="")){return true}else {return false}})();

        if ((!(date_regex.test(dateString))) && txtDatePresent) {
            $(warningId).css("display", "inline-block");
            return false;
        }

        else {
            return true};
    }



// ===================PREVENT SUBMISSION IF THERE ARE ERRORS ===========================
    document.querySelector('form').onsubmit = function(event) {
        $(".alert").css("display", "none");

        var dateGoodOrNot = isValidDate("#date");


        if(dateGoodOrNot==false) {
            event.preventDefault();
        }

    }



});
