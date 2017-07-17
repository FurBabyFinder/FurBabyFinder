/**
 * Created by melodytempleton on 7/16/17.
 */
$(document).ready(function(){

    document.querySelector('form').onsubmit = function(event) {
        $("#noMatch").css("display", "none");

        if($("#email1").val() != $("#email2").val()) {
            $("#noMatch").css("display", "inline-block");
            event.preventDefault();

        }
        else {

            this.submit();
        }



    }



});