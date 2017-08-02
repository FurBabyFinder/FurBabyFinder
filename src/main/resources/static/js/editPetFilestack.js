/**
 * Created by melodytempleton on 7/13/17.
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

    updateIds('.hiddenChecks', 'hiddenCheck');
    updateIds('.hiddenChecksAdopt', 'hiddenCheckAdopt');
    updateIds('.profilePic', 'profilePic');
    updateIds('.profilePicHidden', 'profilePicHidden');
    updateIds('.afterAdopt', 'afterAdopt');
    updateIds('.fs', 'fs');
    updateIds('.existingfs', 'existingfs');
    updateIds('.fspics', 'fspics');
    updateIds('.existingfspics', 'existingfspics');



// ==========Only one profile pic gets checked === Set Values for profile checks and Adopt Checks======================

    $('.profilePic').click(function(){
        var $inputs = $('.profilePic');
        if($(this).is(':checked')){
            $('.profilePic').prop('checked', false); // uncheck them all so existing images are unchecked
            $(this).prop('checked', true); // recheck the one that just got checked
            var thatID = $(this).attr('id');
            thatID = thatID.substr(-1);
            var newID = "#hiddenCheck" + thatID; // get the id of the hidden fields
            $('.hiddenChecks').val("false"); // make sure everything if false (so just the newly checked is true
            $('.profilePic').val("false");
            $(newID).val("true"); // newly checked is now true
            $(this).val("true");
            $inputs.not(this).prop('disabled',true);  // all other checks for profile pic are disabled until this one is unchecked
        }else{
            $inputs.prop('disabled',false); // if unchecking, make other checkboxes availble again to b selected
            $('.hiddenChecks').val("false");
            $('.profilePic').val("false");
        }
    });


    $('.afterAdopt').click(function(){
        var $inputs = $('.afterAdopt');
        if($(this).is(':checked')){
            var thatID = $(this).attr('id');
            thatID = thatID.substr(-1);
            var newID = "#hiddenCheckAdopt" + thatID;
            $(newID).val("true");
            var value = $(newID).val();

        }
        else if($(this).not(':checked')) {
            var thatID = $(this).attr('id');
            thatID = thatID.substr(-1);
            var newID = "#hiddenCheckAdopt" + thatID;
            $(newID).val("false");

        }
    });



    // ===========check which filters the pet already has and check them ====================

    function checkFiltersPetHas (filterName){
        var hiddenID = "#petHas" + filterName;
        var checkID = "#" + filterName;
        if(($(hiddenID).val()) == "true"){
            $(checkID).prop('checked', true);
        }

    }

    checkFiltersPetHas("male");
    checkFiltersPetHas("female");
    checkFiltersPetHas("cat_friendly");
    checkFiltersPetHas("not_cat_friendly");
    checkFiltersPetHas("neuterd_spayed");
    checkFiltersPetHas("potty_trained");
    checkFiltersPetHas("crate_trained");
    checkFiltersPetHas("great_with_dogs");
    checkFiltersPetHas("great_with_kids");
    checkFiltersPetHas("not_dog_friendly");
    checkFiltersPetHas("medical_foster");
    checkFiltersPetHas("microchipped");

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

// =========================VERIFY FILTER CHECKBOXES ARE CHECKED APPROPRIATELY ===========================
    function verifyMaleOrFemaleChecked(){
        var male = (function(){
            if($("#male").is(':checked')){return true}
            else {return false}
        })();
        var female = (function(){
            if($("#female").is(':checked')){return true}
            else {return false}
        })();
        if(male && female){
            $("#maleFemaleCantBeBoth").css("display", "inline-block");
            return false;
        }
        else if (!male && !female){
            $("#maleFemaleMustBeOne").css("display", "inline-block");
            return false;
        }
        else {return true}
    }

    function theseChecksCantBothBeChecked(idOne, idTwo, warningId) {
        var firstCheck = (function(){
            if($(idOne).is(':checked')){return true}
            else {return false}
        })();
        var secondCheck = (function(){
            if($(idTwo).is(':checked')){return true}
            else {return false}
        })();
        if(firstCheck && secondCheck){
            $(warningId).css("display", "inline-block");
            return false;
        }
        else {return true}
    }


// ===================PREVENT SUBMISSION IF THERE ARE ERRORS ===========================
    document.querySelector('form').onsubmit = function(event) {
        $(".alert").css("display", "none");

        var adoptdateGoodOrNot = isValidDate("#adoptionDate");
        var arrivaldateGoodOrNot = isValidDate("#arrivalDate");
        var maleFemaleChecksGoodOrNot = verifyMaleOrFemaleChecked();
        var catsGoodOrNot = theseChecksCantBothBeChecked("#cat_friendly", "#not_cat_friendly", "#catsOrNoCats");
        var dogsGoodOrNot = theseChecksCantBothBeChecked("#great_with_dogs","#not_dog_friendly", "#dogsOrNoDats");


        if(adoptdateGoodOrNot==false || arrivaldateGoodOrNot == false || maleFemaleChecksGoodOrNot ==false || catsGoodOrNot == false || dogsGoodOrNot == false) {
            event.preventDefault();
        }
        else {
            event.preventDefault();
            // replaceImages();
            this.submit();
        }
    }



});

