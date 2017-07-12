/**
 * Created by melodytempleton on 7/8/17.
 */
$("#searchUser").click(function() {
    var userID = $("#userID").val();
    var userFirstName = $("#userFirstName").val();
    var userLastName = $("#userLastName").val();

    if (((userFirstName != "") || (userLastName != "")) & (userID != "")) {
        $('#warningMessageNotBoth').css("display", "inline-block");
    }
    function hideWarnings() {
        $('#warningMessageNotBoth').css("display", "none");
        $('#warningMessageNumeric').css("display", "none");
    }

    if (userID != "") {
        if(!($.isNumeric(userID))){
            $('#warningMessageNumeric').css("display", "inline-block");
        }
        else{
        window.location.href = "/users/searchID/" + userID;
        hideWarnings();}
    }
    else if (userFirstName != "") {
        window.location.href = "/users/" + userFirstName + "/searchName/none";
        hideWarnings();
    }

    else if (userLastName != "") {
        window.location.href = "/users/none/searchName/" + userLastName;
        hideWarnings();
    }

    else if (userFirstName != "" & userLastName != "") {
        window.location.href = "/users/" + userFirstName + "/searchName/" + userLastName;
        hideWarnings();
    }

    else {
        window.location.href = "/users/searchAll";
        hideWarnings();
    }


});