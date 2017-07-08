/**
 * Created by melodytempleton on 7/7/17.
 */
$(document).ready(function(){

    var imageCount = $('#imageCount').val();
    var addImages = 6 - imageCount;

    var imageInput = document.getElementById("imageInput").innerHTML;

    (function insertImageInputs() {
        var insertImages;
        for (var j = 1; j < addImages; j++){
            insertImages = insertImages + imageInput;
        }
        document.getElementById("additionalImages").innerHTML = insertImages;
    })();

    (function checkTheProfile() {

      $('.profilePic').each(function(){
           var trueOrNot = $(this).val();
           console.log(trueOrNot);
          if(trueOrNot == "true"){
              console.log('pass');
              $(this).attr('checked', true);
          }
      })

    })();

    function updateIds (classname, idbase){
        var number = 1;
        var id;

        $(classname).each(function () {
             id = idbase + number;
            var that = this;
             console.log("id name is " + id);
            $(this).attr("id", id);
            console.log(this);
            // that.id = id;
            number++;
        })
    }

    updateIds('.hiddenChecks', 'hiddenChecks');
    updateIds('.imageInputs', 'image');
    updateIds('.profilePic', 'profilePic');

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
    checkFiltersPetHas("not_dog_friendly");
    checkFiltersPetHas("medical_foster");
    checkFiltersPetHas("microchipped");


});
