/**
 * Created by melodytempleton on 7/29/17.
 */
$(document).ready(function () {

if($("#alreadyFaved").val() == "true"){

    $("#addToFavorite").css("display", "none");
    $("#favoriteButton").css("display", "none");
    $("#addedToFavorite").css("display", "inline-block");
    $("#favoriteChecked").css("display", "inline-block");

}
document.querySelector('form').onsubmit = function(event) {

    var signedIn = $("#cannotFav").val();
    if(signedIn) {
        event.preventDefault();
        $("#favoriteWarning").css("display", "inline-block");
    }

}
});