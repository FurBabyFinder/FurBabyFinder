/**
 * Created by melodytempleton on 7/8/17.
 */
$("#searchUser").click(function() {
    var userID = $("#userID").val();
    var userFirstName = $("#userFirstName").val();
    var userLastName = $("#userLastName").val();
    var userEmail = $("#userEmail").val();
    var username = $("#username").val();
    var numberOfSelectionsMade = 1;


    function hideWarnings() {
        $('#warningMessageOnlyOne').css("display", "none");
        $('#warningMessageNumeric').css("display", "none");
    }

    if (((userFirstName != "") || (userLastName != "")) ) {
        numberOfSelectionsMade++;
    }

     if ( userID != "" ) {
        numberOfSelectionsMade++;
    }

       if ( userEmail != "" ) {
        numberOfSelectionsMade++;
    }

       if ( username != "" ) {
       numberOfSelectionsMade++;
    }


    if (numberOfSelectionsMade > 2) {
        $('#warningMessageOnlyOne').css("display", "inline-block");
    }

    else if (userID != "") {
        if(!($.isNumeric(userID))){
            $('#warningMessageNumeric').css("display", "inline-block");
        }
        else{
        hideWarnings();}
        window.location.href = "/users/searchID/" + userID;
    }
    else if (userFirstName != "") {
        hideWarnings();
        window.location.href = "/users/" + userFirstName + "/searchName/none";
    }

    else if (userLastName != "") {
        hideWarnings();
        window.location.href = "/users/none/searchName/" + userLastName;
    }

    else if (userFirstName != "" & userLastName != "") {
        hideWarnings();
        window.location.href = "/users/" + userFirstName + "/searchName/" + userLastName;
    }

     else if (userEmail != "") {
        hideWarnings();
        window.location.href = "/users/searchEmail/" + userEmail;
    }

     else if (username != "") {
        hideWarnings();
        window.location.href = "/users/searchUsername/" + username;
    }

    else {
        hideWarnings();
        window.location.href = "/users/searchAll";
    }


});