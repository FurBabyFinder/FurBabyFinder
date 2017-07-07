/**
 * Created by melodytempleton on 7/7/17.
 */
$(document).ready(function(){

    var imageCount = $('#imageCount').val();
    var addImages = 6 - imageCount;

    var imageInput = document.getElementById("imageInput").innerHTML;

    (function insertImageInputs() {
        var insertImages;
        for (var j = 0; j < addImages; j++){
            insertImages = insertImages + imageInput;
        }
        document.getElementById("additionalImages").innerHTML = insertImages;
    })();


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
    checkFiltersPetHas("medical_foster");
    checkFiltersPetHas("microchipped");


});
