/**
 * Created by melodytempleton on 7/29/17.
 */
$(document).ready(function () {


document.querySelector('form').onsubmit = function(event) {
    $(".alert").css("display", "none");
    var signedIn = $("#cannotFav").val();
    if(signedIn) {
        event.preventDefault();
        $("#favoriteWarning").css("display", "inline-block");
    }

}
});