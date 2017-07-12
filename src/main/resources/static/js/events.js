/**
 * Created by frenchfryes on 7/7/17.
 */
$(document).ready(function () {
    $('.event-details').css('display', 'none');
    $('.event-details').css('height', 'auto');
    $('.event-details').css('margin-top', '-17px');
    $('.event-details > .info').css('height', 'auto');
    $('.disabled').prev().css('cursor', 'default');


    $('.event-list > li').click(function () {
        if (!$(this).nextAll('.event-details').first().hasClass('disabled')) {
            //$(this).nextAll('.event-details').first().height('auto');
            $(this).nextAll('.event-details').first().nextAll('.info').first().height('auto');
            $(this).nextAll('.event-details').first().slideToggle();
        }
    });


    function initMap(location, id) {

        var map = new google.maps.Map(document.getElementById(id), {
            center: location,
            zoom: 15
        });

    }

    function centerAddress(address, id) {
        var geocoder = new google.maps.Geocoder();
        var location = {};

// Geocode our address
        geocoder.geocode({"address": address}, function (results, status) {

            // Check for a successful result
            if (status == google.maps.GeocoderStatus.OK) {
                location.lat = parseFloat(results[0].geometry.location.lat());
                location.lng = parseFloat(results[0].geometry.location.lng());
                initMap(location, id);
                // Recenter the map over the address

            } else {

                // Show an error message with the status if our request fails
                alert("Geocoding was not successful - STATUS: " + status);
            }
        });

    }
    var addressElements = document.getElementsByClassName("address");

    for(var i = 0; i < addressElements.length; i++){
        var targetElement = addressElements[i].getAttribute("data-target");
        var mapAddress = addressElements[i].innerHTML;
        centerAddress(mapAddress, targetElement);

    }

})

