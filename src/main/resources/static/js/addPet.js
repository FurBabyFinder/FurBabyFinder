/**
 * Created by melodytempleton on 7/9/17.
 */
$(document).ready(function(){


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

    }

    // ==================  LIMIT ONLY ONE PROFILE PIC SELECTOR AND UPDATE VALUE OF HIDDEN INPUTS ===============

    $('.profilePic').click(function(){
        var $inputs = $('.profilePic');
        if($(this).is(':checked')){
            var thatID = $(this).attr('id');
            thatID = thatID.substr(-1);
            var newID = "#hiddenCheck" + thatID;
            $(newID).val("true");
            $inputs.not(this).prop('disabled',true);
        }else{
            $inputs.prop('disabled',false);
            $('.hiddenChecks').val("false");
        }
    });



});