/**
 * Created by melodytempleton on 7/21/17.
 */
$(document).ready(function () {

    vaccinationFlag = $("#vaccination").text();

    if (vaccinationFlag=="true"){

        $("#vaccination").text("Yes");
    }

    else {
        $("#vaccination").text("No");
    }



});
