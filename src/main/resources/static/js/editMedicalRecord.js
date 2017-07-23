/**
 * Created by melodytempleton on 7/23/17.
 */


$(document).ready(function(){

    var imageCount = $('#imageCount').val();
    var addImages = 6 - imageCount;

    var imageInput = document.getElementById("imageInput").innerHTML;

    // ============================insert html to upload additional images=============================
    (function insertImageInputs() {
        var insertImages = imageInput
        for (var j = 1; j < addImages; j++){
            insertImages = insertImages + imageInput;
        }

        document.getElementById("additionalImages").innerHTML = insertImages;
    })();

    function updateIds (classname, idbase){
        var number = 1;
        var id;
        $(classname).each(function () {
            id = idbase + number;

            $(this).attr("id", id);
            number++;
        })
    }

    updateIds('.fs', 'fs');
    updateIds('.existingfs', 'existingfs');
    updateIds('.fspics', 'fspics');
    updateIds('.existingfspics', 'existingfspics');



    // ===============VERIFY DATE FIELDS ARE DATES==============================
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

