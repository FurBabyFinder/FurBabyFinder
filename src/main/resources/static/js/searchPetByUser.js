/**
 * Created by melodytempleton on 7/8/17.
 */
$("#searchUser").click(function(){
    var userID = $("#userID").val();
    var userFirstName = $("#userFirstName").val();
    var userLastName = $("#userLastName").val();
    var ready = "false";
    var excludeAdopted = "false";

    function hideWarnings() {
        $('#warningMessageNotBoth').css("display", "none");
        $('#warningMessageChooseUser').css("display", "none");
        $('#warningMessageReadyExclude').css("display", "none");
    }

    function resetSearchOptions() {
        ready = "fasle";
        excludeAdopted = "false";
        $("#userID").val("");
        $("#userFirstName").val("");
        $("#userLastName").val("");
        $("#readyToAdoptOnly").prop('checked', false);
        $("#excludeReadyToAdopt").prop('checked', false);
        $("#onlyAdopted").prop('checked', false);
    }

    if($("#readyToAdoptOnly:checked").val()){
        ready = "true";
    }

    if($("#excludeReadyToAdopt:checked").val()){
        ready = "exclude";
    }


    if($("#excludedAdopted:checked").val()){
        excludeAdopted = "true";
    }

   if($("#onlyAdopted:checked").val()){
        excludeAdopted = "only";
    }



    if (!$("input[name='userType']:checked").val() &&((((userFirstName != "") || (userLastName != "")) & (userID != "")))){
        $('#warningMessageChooseUser').css("display", "inline-block");
        resetSearchOptions();
    }


    else if (((userFirstName != "") || (userLastName != "")) & (userID != "")) {
        $('#warningMessageNotBoth').css("display", "inline-block");
        resetSearchOptions();
    }

    else {

        if ($("#adopter").is(':checked')) {
            if ((ready != "false") || (excludeAdopted != "false")){
                $('#warningMessageReadyExclude').css("display", "inline-block");
                resetSearchOptions();
                }

            else {
                if (userID != "") {
                    if(!($.isNumeric(userID))){
                        $('#warningMessageNumeric').css("display", "inline-block");
                        resetSearchOptions();
                        return;
                    }
                   hideWarnings();
                    window.location.href = "/pets/searchAdopterID/" + userID;


                }
                else if (userFirstName != "") {
                    hideWarnings();
                    window.location.href = "/pets/" + userFirstName + "/searchAdopterName/none";
                }

                else if (userLastName != "") {
                    hideWarnings();
                    window.location.href = "/pets/none/searchAdopterName/" + userLastName;
                }

                else if (userFirstName != "" & userLastName != "") {
                    hideWarnings();
                    window.location.href = "/pets/" + userFirstName + "/searchAdopterName/" + userLastName;
                }
                else {
                    hideWarnings();
                    window.location.href = "/pets/searchAllAdopter";
                }
            }

        }


        else if ($("#foster").is(':checked')) {
            if (userID != "") {
                if(!($.isNumeric(userID))){
                    $('#warningMessageNumeric').css("display", "inline-block");
                    resetSearchOptions();
                    return;
                }
                hideWarnings();
                window.location.href = "/pets/searchFosterID/" + userID +"/" + ready + "/" + excludeAdopted;
            }
            else if (userFirstName != "") {
                hideWarnings();
                window.location.href = "/pets/" + userFirstName + "/searchFosterName/none" +"/"+ ready + "/" + excludeAdopted;
            }

            else if (userLastName != "") {
                hideWarnings();
                window.location.href = "/pets/none/searchFosterName/" + userLastName +"/" +ready + "/" + excludeAdopted;
            }

            else if (userFirstName != "" & userLastName != "") {
                hideWarnings();
                window.location.href = "/pets/" + userFirstName + "/searchFosterName/" + userLastName +"/"+ ready + "/" + excludeAdopted;
            }
            else {
                hideWarnings();
                window.location.href = "/pets/searchAllFoster/" + ready +"/" + excludeAdopted;
            }
        }

        else {

                hideWarnings();
                window.location.href = "/pets/searchAll/" + ready + "/" + excludeAdopted;
            }

    }

});