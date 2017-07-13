/**
 * Created by melodytempleton on 7/8/17.
 */
$("#searchUser").click(function(){
    var userID = $("#userID").val();
    var userFirstName = $("#userFirstName").val();
    var userLastName = $("#userLastName").val();
    var ready = "false";
    var exclude = "false";

    function hideWarnings() {
        $('#warningMessageNotBoth').css("display", "none");
        $('#warningMessageChooseUser').css("display", "none");
        $('#warningMessageReadyExclude').css("display", "none");
    }

    if($("#readyToAdoptOnly:checked").val()){
        ready = "true";
    }


    if($("#excludedAdopted:checked").val()){
        exclude = "true";
    }



    if (!$("input[name='userType']:checked").val()){
        $('#warningMessageChooseUser').css("display", "inline-block");
    }


    else if (((userFirstName != "") || (userLastName != "")) & (userID != "")) {
        $('#warningMessageNotBoth').css("display", "inline-block");
    }

    else {

        if ($("#adopter").is(':checked')) {
            if ((ready == "true") || (exclude == "true")){$('#warningMessageReadyExclude').css("display", "inline-block");}

            else {
                if (userID != "") {
                    if(!($.isNumeric(userID))){
                        $('#warningMessageNumeric').css("display", "inline-block");
                        return;
                    }
                    window.location.href = "/pets/searchAdopterID/" + userID;
                   hideWarnings();
                }
                else if (userFirstName != "") {
                    window.location.href = "/pets/" + userFirstName + "/searchAdopterName/none";
                    hideWarnings();
                }

                else if (userLastName != "") {
                    window.location.href = "/pets/none/searchAdopterName/" + userLastName;
                    hideWarnings();
                }

                else if (userFirstName != "" & userLastName != "") {
                    window.location.href = "/pets/" + userFirstName + "/searchAdopterName/" + userLastName;
                    hideWarnings();
                }
                else {
                    window.location.href = "/pets/searchAllAdopter";
                    hideWarnings();
                }
            }

        }


        if ($("#foster").is(':checked')) {
            if (userID != "") {
                if(!($.isNumeric(userID))){
                    $('#warningMessageNumeric').css("display", "inline-block");
                    return;
                }
                window.location.href = "/pets/searchFosterID/" + userID +"/" + ready + "/" + exclude;
                hideWarnings();
            }
            else if (userFirstName != "") {
                window.location.href = "/pets/" + userFirstName + "/searchFosterName/none" +"/"+ ready + "/" + exclude;
                hideWarnings();
            }

            else if (userLastName != "") {
                window.location.href = "/pets/none/searchFosterName/" + userLastName +"/" +ready + "/" + exclude;
                hideWarnings();
            }

            else if (userFirstName != "" & userLastName != "") {
                window.location.href = "/pets/" + userFirstName + "/searchFosterName/" + userLastName +"/"+ ready + "/" + exclude;
                hideWarnings();
            }
            // else {
            //     window.location.href = "/pets/searchAllFoster/" + ready +"/" + exclude;
            //     hideWarnings();
            // }
        }
    }

});